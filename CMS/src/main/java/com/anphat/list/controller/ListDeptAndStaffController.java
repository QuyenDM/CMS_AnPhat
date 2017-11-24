/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.list.ui.DepartmentSearchPanel;
import com.anphat.list.ui.DialogCreateDepartment;
import com.anphat.list.ui.DialogCreateStaff;
import com.anphat.list.ui.MapStaffCustomerDialog;
import com.anphat.list.ui.MapStaffRolesDiaglog;
import com.anphat.list.ui.StaffSearchPanel;
import com.cms.login.ws.WSDepartment;
import com.cms.login.ws.WSStaff;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.cms.component.CommonFunction;
import com.cms.component.CustomPageTableFilter;
import com.cms.component.WindowProgress;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.DepartmentDTO;
import com.cms.dto.StaffDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.ShortcutUtils;
import com.cms.view.ListStaffDepartment;
import com.google.common.collect.Lists;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author hungkv
 */
public final class ListDeptAndStaffController implements Serializable {

    private ListStaffDepartment listDepartmentsAndStaffUI;
    private DepartmentSearchPanelController searchFormDeptController;
    private ListDepartmentController listDepartmentController;
    private StaffSearchPanelController searchFormStaffController;
    private ListStaffController listStaffController;

    private DialogCreateDepartmentController dialogCreateDepartmentController;
    private DialogCreateStaffController dialogCreateStaffController;
//    private DialogImportExcelStaffsController dialogImportExcelStaffsController;
    //14032016 NgocND6 khoi tao controller tranfer role
//    private DialogTRGMController dialogTRGMController;
    private DepartmentSearchPanel departmentForm;
    CustomPageTableFilter<IndexedContainer> tblDepartment;
    StaffSearchPanel staffForm;
    CustomPageTableFilter<IndexedContainer> tblStaff;
    private CommonTableFilterPanel commonTableDeptPanel;
    private CommonTableFilterPanel commonTableEmpPanel;
    DepartmentDTO departmentDTO;
    StaffDTO staffDTO;
//    StockDTO stockDTO;
    ResultDTO resultDTO;
    Set<DepartmentDTO> selectedValues;
    Set<StaffDTO> selectedStaffValues;
    public Map<String, AppParamsDTO> mapNameFromKey = new HashMap<>();
    String message;
    String deptId;
    //button toolbar
    Button btnAddDept;
    Button btnEditDept;
    Button btnCopyDept;
    Button btnDelDept;
    Button btnImportDept;
    Button btnExportDept;

    Button btnAddEmp;
    Button btnEditEmp;
    Button btnCopyEmp;
    Button btnDelEmp;
//    Button btnImportEmp;
//    Button btnExportEmp;
    //160311 NgocND6 button chuyen quyen quan ly hang hoa cho nhan vien
    Button btnTransferRole;
    private WindowProgress wp;
    Collection value;
    Button btnResetDept;
    Button btnResetEmp;
//    UserToken userToken;
//    StaffInforDTO staffInforDTO;
    //
    Button btnAssignRole;
    Button btnAddMapStaffCusts;
    Window window = new Window();
    private List<AppParamsDTO> lstAllAppParams;

//    String path_import_template_mapStaffGoods = BundleUtils.getStringCas("path_import_template_mapStaffGoods");
    public ListDeptAndStaffController(ListStaffDepartment mainUI) {

        this.listDepartmentsAndStaffUI = mainUI;
        commonTableDeptPanel = mainUI.getTblListDepartmentUI();
        //define button toolbar dept
        btnAddDept = commonTableDeptPanel.getAddButton();
        btnEditDept = commonTableDeptPanel.getEditButton();
        btnCopyDept = commonTableDeptPanel.getCoppyButton();
        btnDelDept = commonTableDeptPanel.getDeleteButton();
        btnDelDept.setEnabled(false);
        btnImportDept = commonTableDeptPanel.getImportButton();
        btnExportDept = commonTableDeptPanel.getExportButton();
        btnResetDept = mainUI.getBtnRefreshDept();
        //define button toolbar emp
        commonTableEmpPanel = mainUI.getTblListStaffUI();
        btnAddEmp = commonTableEmpPanel.getAddButton();
        btnEditEmp = commonTableEmpPanel.getEditButton();
        btnCopyEmp = commonTableEmpPanel.getCoppyButton();
        btnDelEmp = commonTableEmpPanel.getDeleteButton();
        btnDelEmp.setEnabled(false);
//        btnImportEmp = commonTableEmpPanel.getImportButton();
//        btnExportEmp = commonTableEmpPanel.getExportButton();
        btnResetEmp = mainUI.getBtnRefreshEmp();
        //NgocND6 160311
        btnTransferRole = listDepartmentsAndStaffUI.getBtnTransferRoleCusts();
        btnAddMapStaffCusts = mainUI.getBtnAddMapStaffCustomer();

        searchFormDeptController = new DepartmentSearchPanelController(mainUI.getSearchDepartmentForm());
        searchFormDeptController.init();
        listDepartmentController = new ListDepartmentController(listDepartmentsAndStaffUI.getTblListDepartmentUI());
        listDepartmentController.init();

        getData();

        searchFormStaffController = new StaffSearchPanelController(mainUI.getSearchStaffForm());
        searchFormStaffController.init();
        listStaffController = new ListStaffController(listDepartmentsAndStaffUI.getTblListStaffUI());
        listStaffController.setMapId2DeparmentDTO(searchFormStaffController.mapId2DepartmentDTO);
        staffForm = mainUI.getSearchStaffForm();
        //xu li cac nut tai day
        departmentDTO = new DepartmentDTO();
        staffDTO = new StaffDTO();
        searchFormDeptController.setNotRequired();
        deptActionListener();
        staffActionListener();

        //show table
        tblDepartment = listDepartmentsAndStaffUI.getTblListDepartmentUI().getMainTable();
        tblStaff = listDepartmentsAndStaffUI.getTblListStaffUI().getMainTable();
        //style cho notification
        Page.Styles styles = Page.getCurrent().getStyles();
//        styles.add("*{font-family: tahoma; font-size: 13px;}");
        styles.add(".v-Notification {}\n"
                + "  .popupContent {}\n"
                + "    .gwt-HTML {}\n"
                + "      h1 {}\n"
                + "      p  {}");
        styles.add(".v-Notification.mystyle {\n"
                + "    background: #FFFF00;\n"
                + "    border: 10px solid #C00000;\n"
                + "    color: black;\n"
                + "}");
        //get data fill to next panel
//        tblDepartment.setMultiSelect(false);
        tblDepartment.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
//                //open progress sdf
                value = new ArrayList<>();
                DepartmentDTO dept = (DepartmentDTO) event.getItemId();
                value.add(dept.copy());
                if (value.size() == 1) {//neu chon 1 row
                    fillDataStaff(value.toArray()[0]);
                    fillData2StaffForm(value.toArray()[0]);
                }
                if (value.isEmpty()) {
                    deptId = null;
                    searchFormStaffController.getCboDepartment().getNameCombo().setValue(null);
                    searchFormStaffController.getCboDepartment().getCodeCombo().setValue(null);
                }
            }
        });
    }

    //==cac button departmentUI -- dept section
    public void deptActionListener() {

        ShortcutUtils.setShortcutKey(listDepartmentsAndStaffUI.getBtnSearchDept());
        ShortcutUtils.doTextChangeUppercase(searchFormDeptController.getSearchDepartmentForm().getTxtDeptCode());
        listDepartmentsAndStaffUI.getBtnSearchDept().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                doSearchDepartments();
                CommonFunction.enableButtonAfterClick(event);
            }
        });

        btnResetDept.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                doResetInputDept();
                CommonFunction.enableButtonAfterClick(event);
            }
        });

        btnAddDept.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                DialogCreateDepartment dialogCreateDepartment = new DialogCreateDepartment(BundleUtils.getString("department.management.panel.addDepartment"), new DepartmentDTO());
                dialogCreateDepartmentController
                        = new DialogCreateDepartmentController(
                                dialogCreateDepartment, Constants.ADD, tblDepartment, tblStaff, staffForm.getF9Departments());
                dialogCreateDepartmentController.init(new DepartmentDTO());
                UI.getCurrent().addWindow(dialogCreateDepartment);
                CommonFunction.enableButtonAfterClick(event);
            }
        });

        btnEditDept.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<DepartmentDTO> selectedValues = (Set<DepartmentDTO>) tblDepartment.getValue();
                if (selectedValues != null) {
                    List<DepartmentDTO> lstDepartment = Lists.newArrayList();
                    for (DepartmentDTO departmentDTO : selectedValues) {
                        lstDepartment.add(departmentDTO);
                    }
                    if (selectedValues.size() == 1) {
                        DepartmentDTO departmentDTO;
                        departmentDTO = (DepartmentDTO) selectedValues.toArray()[0];
                        DialogCreateDepartment dialogCreateContract = new DialogCreateDepartment(BundleUtils.getString("department.management.panel.updateDepartment"), departmentDTO);
                        dialogCreateDepartmentController
                                = new DialogCreateDepartmentController(
                                        dialogCreateContract, Constants.EDIT, tblDepartment, tblStaff, staffForm.getF9Departments());
                        dialogCreateDepartmentController.init(departmentDTO);
                        UI.getCurrent().addWindow(dialogCreateContract);
                    } else {
                        Notification.show(BundleUtils.getString("dept.staff.alert.message.pleasechoose"));
                    }
                } else {
                    CommonUtils.showChoseOne();
                }
                CommonFunction.enableButtonAfterClick(event);
            }
        });

        btnCopyDept.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
//                DepartmentDTO selectedValues = (DepartmentDTO) tblDepartment.getValue();
                Set<DepartmentDTO> selectedValues = (Set<DepartmentDTO>) tblDepartment.getValue();
                if (selectedValues != null) {
                    List<DepartmentDTO> lstDepartment = Lists.newArrayList();
                    for (DepartmentDTO departmentDTO : selectedValues) {
                        lstDepartment.add(departmentDTO);
                    }
                    if (selectedValues.size() == 1) {
                        DepartmentDTO departmentDTO = (DepartmentDTO) selectedValues.toArray()[0];
                        DialogCreateDepartment dialogCreateContract
                                = new DialogCreateDepartment(BundleUtils.getString("department.management.panel.copyDepartment"), departmentDTO);
                        dialogCreateDepartmentController
                                = new DialogCreateDepartmentController(
                                        dialogCreateContract, Constants.COPY, tblDepartment, tblStaff, staffForm.getF9Departments());
                        dialogCreateDepartmentController.init(departmentDTO);
                        UI.getCurrent().addWindow(dialogCreateContract);
                    } else {
                        Notification.show(BundleUtils.getString("dept.staff.alert.message.pleasechoose"));
                    }
                } else {
                    CommonUtils.showChoseOne();
                }
                CommonFunction.enableButtonAfterClick(event);
            }
        });

        listDepartmentController.getBtnDel().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                selectedValues = (Set<DepartmentDTO>) tblDepartment.getValue();

                if (selectedValues.isEmpty()) {
                    CommonUtils.showChoseOne();
                } else {
                    final DepartmentDTO departmentDTO = (DepartmentDTO) selectedValues.toArray()[0];
                    ConfirmDialog.show(UI.getCurrent(),
                            BundleUtils.getString("titleMessage") + "",
                            BundleUtils.getString("bodyMessage"),
                            BundleUtils.getString("yes"),
                            BundleUtils.getString("no"),
                            new ConfirmDialog.Listener() {
                        @Override
                        public void onClose(ConfirmDialog dialog) {
                            // tiepnv6 edit 16h 15/07/15
                            // check neu phong ban la cha thi khong xoa
                            if (deptIsParent(departmentDTO)) {
                                Notification.show(
                                        BundleUtils.getString("dept.staff.required.delete.staff.1"), 
                                        Notification.Type.HUMANIZED_MESSAGE);
                                return;
                            }
                            //check neu bang phong ban co nhan vien thi xoa phong ban fai xoa het nv
                            if (!checkHasValue(departmentDTO)) {
                                if (dialog.isConfirmed()) {
                                    List<DepartmentDTO> lstDeptDel = Lists.newArrayList();
                                    lstDeptDel.addAll(selectedValues);
                                    if (selectedValues.size() > 1) {
                                        //neu dept khong co dept cap con thi xoa
                                        List<DepartmentDTO> listChildOfDept = Lists.newArrayList();
                                        DepartmentDTO deptDTODel = new DepartmentDTO();
                                        deptDTODel = lstDeptDel.get(0);
                                        listChildOfDept = getChildDepartment(deptDTODel);
                                        if (listChildOfDept != null) {
                                            // Notification with default settings for a warning
                                            String showDepartmentChild = "";
                                            for (DepartmentDTO listChildOfDept1 : listChildOfDept) {
                                                listChildOfDept1.getName();
                                                showDepartmentChild += listChildOfDept1.getName() + ", ";
                                            }
                                            Notification notif = new Notification(
                                                    BundleUtils.getString("Warning"),
                                                    BundleUtils.getString("message.required.deleteDepartment")
                                                    + "<br/>" + showDepartmentChild,
                                                    Notification.Type.TRAY_NOTIFICATION);
                                            notif.setDelayMsec(20000);
                                            notif.setPosition(Position.BOTTOM_RIGHT);
                                            notif.setStyleName("mystyle");
                                            notif.show(Page.getCurrent());
                                            //
                                        } else {
                                            message = doDeleteLstDept(lstDeptDel);
                                        }
                                        if (message.contains(Constants.SUCCESS)) {
                                            for (DepartmentDTO deptDTO : lstDeptDel) {
                                                tblDepartment.removeItem(deptDTO);
                                            }
                                            tblDepartment.refreshRowCache();
                                            tblDepartment.resetPage();
                                            CommonUtils.showDeleteSuccess(BundleUtils.getString("department"));
                                        } else {
                                            CommonUtils.showDeleteFail(BundleUtils.getString("department"));
                                        }
                                    } else {
//                                                tblDepartment = listDepartmentsAndStaffUI.getTblListDepartmentUI().getMainTable();
                                        List<DepartmentDTO> listDepartment = Lists.newArrayList();
                                        selectedValues = (Set<DepartmentDTO>) tblDepartment.getValue();
                                        DepartmentDTO departmentDTO = (DepartmentDTO) selectedValues.toArray()[0];
//                                                departmentDTO.setUserNameLogging("Username");
                                        listDepartment.add(departmentDTO);
                                        message = doDeleteLstDept(listDepartment);
                                        if (message.contains(Constants.SUCCESS)) {

                                            tblDepartment.removeItem(departmentDTO);
                                            tblDepartment.resetPage();
                                            CommonUtils.showDeleteSuccess(BundleUtils.getString("department"));
                                        } else {
                                            CommonUtils.showDeleteFail(BundleUtils.getString("department"));
                                        }
                                    }
                                }
                            } else {
                                Notification.show(BundleUtils.getString("dept.staff.required.delete.staff"), Notification.Type.HUMANIZED_MESSAGE);
                            }
                        }
                    });
                }
                CommonFunction.enableButtonAfterClick(event);
            }

        });
        btnAddMapStaffCusts.setDisableOnClick(true);
        btnAddMapStaffCusts.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<StaffDTO> selectedValues = (Set<StaffDTO>) tblStaff.getValue();
                if (!DataUtil.isListNullOrEmpty(Lists.newArrayList(selectedValues))) {
                    MapStaffCustomerDialog dialogAddMapStaffGoods = new MapStaffCustomerDialog();
                    MapStaffCustomerController mapStaffCustomerController
                            = new MapStaffCustomerController(dialogAddMapStaffGoods,
                                    Lists.newArrayList(selectedValues), lstAllAppParams);
                    DataUtil.reloadWindow(dialogAddMapStaffGoods);
                    UI.getCurrent().addWindow(dialogAddMapStaffGoods);
                } else {
                    CommonMessages.showWarningMessage(BundleUtils.getString("notification.staff.customer.choice"));
                }
                CommonFunction.enableButtonAfterClick(event);
            }
        });
    }

    private void getData() {
        lstAllAppParams = DataUtil.getListAppParamsDTOs();
    }
//============Staff section============================================

    public void staffActionListener() {

        ShortcutUtils.setShortcutKey(listDepartmentsAndStaffUI.getBtnSearchEmp());
        listDepartmentsAndStaffUI.getBtnSearchEmp().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                doSearchStaff();
            }
        });

        btnResetEmp.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                doResetInputStaff();
            }
        });

        btnAddEmp.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<DepartmentDTO> selectedValues = (Set<DepartmentDTO>) tblDepartment.getValue();
                if (selectedValues != null) {
                    List<DepartmentDTO> lstDepartment = Lists.newArrayList();
                    for (DepartmentDTO ddto : selectedValues) {
                        lstDepartment.add(ddto);
                    }
                    if (selectedValues.size() == 1) {
                        DepartmentDTO deptDTO = (DepartmentDTO) selectedValues.toArray()[0];
                        DialogCreateStaff dialogCreateStaff = new DialogCreateStaff(BundleUtils.getString("staff.management.panel.addStaff"), new StaffDTO(), deptDTO);
                        dialogCreateStaffController = new DialogCreateStaffController(dialogCreateStaff, Constants.ADD, tblStaff);
                        dialogCreateStaffController.init(new StaffDTO(), null, deptDTO);
                        UI.getCurrent().addWindow(dialogCreateStaff);
                    } else {
                        Notification.show(BundleUtils.getString("dept.staff.alert.message.pleasechooseonedept"));
                    }
                } else {
                    CommonUtils.showChoseOne();
                }
                CommonFunction.enableButtonAfterClick(event);
            }
        });
        //160311 NgocND6 xu ly su kien khi click button chuyen quyen quan ly hang hoa
        btnTransferRole.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<StaffDTO> selectedValues = (Set<StaffDTO>) tblStaff.getValue();
                if (selectedValues != null) {
                    List<StaffDTO> lstStaff = Lists.newArrayList();
                    for (StaffDTO staffDTO : selectedValues) {
                        lstStaff.add(staffDTO);
                    }
                    if (selectedValues.size() == 1) {
                        StaffDTO staffDTO = (StaffDTO) selectedValues.toArray()[0];
                        if (staffDTO.getStatus().equalsIgnoreCase("1")) {
//                            DialogTRGM dtrgm = new DialogTRGM(BundleUtils.getString("transfer.goods.manage.tranfgoodsmanagetitle"));
//                            dialogTRGMController = new DialogTRGMController(dtrgm, staffDTO);
//                            UI.getCurrent().addWindow(dtrgm);
                        } else {
                            Notification.show(BundleUtils.getString("dept.staff.alert.message.notActive"));
                        }
                    } else {
                        Notification.show(BundleUtils.getString("transfer.goods.manage.coldselectstaff"));
                    }
                } else {
                    Notification.show(BundleUtils.getString("transfer.goods.manage.coldselectstaff"));
                }
            }
        });

        btnEditEmp.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<StaffDTO> selectedValues = (Set<StaffDTO>) tblStaff.getValue();
                if (selectedValues != null) {
                    List<StaffDTO> lstStaff = Lists.newArrayList();
                    for (StaffDTO staffDTO : selectedValues) {
                        lstStaff.add(staffDTO);
                    }
                    if (selectedValues.size() == 1) {
                        StaffDTO staffDTO = (StaffDTO) selectedValues.toArray()[0];
                        DialogCreateStaff dialogCreateStaff = new DialogCreateStaff(BundleUtils.getString("staff.management.panel.updateStaff"), staffDTO, null);
                        dialogCreateStaffController = new DialogCreateStaffController(dialogCreateStaff, Constants.EDIT, tblStaff);
                        dialogCreateStaffController.init(lstStaff.get(0), deptId, null);
                        UI.getCurrent().addWindow(dialogCreateStaff);

                    } else {
                        Notification.show(BundleUtils.getString("dept.staff.alert.message.pleasechoose"));
                    }
                } else {
                    CommonUtils.showChoseOne();
                }
                CommonFunction.enableButtonAfterClick(event);
            }
        });

        btnCopyEmp.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<StaffDTO> selectedValues = (Set<StaffDTO>) tblStaff.getValue();
                if (selectedValues != null) {
                    List<StaffDTO> lstStaff = Lists.newArrayList();
                    for (StaffDTO staffDTO : selectedValues) {
                        lstStaff.add(staffDTO);
                    }
                    if (selectedValues.size() == 1) {
                        StaffDTO staffDTO = (StaffDTO) selectedValues.toArray()[0];
                        DialogCreateStaff dialogCreateStaff = new DialogCreateStaff(BundleUtils.getString("staff.management.panel.copyStaff"), staffDTO, null);
                        dialogCreateStaffController = new DialogCreateStaffController(dialogCreateStaff, Constants.COPY, tblStaff);
                        dialogCreateStaffController.init(lstStaff.get(0), deptId, null);
                        UI.getCurrent().addWindow(dialogCreateStaff);
                    } else {
                        Notification.show(BundleUtils.getString("dept.staff.alert.message.pleasechoose"));
                    }
                } else {
                    CommonUtils.showChoseOne();
                }
                CommonFunction.enableButtonAfterClick(event);
            }
        });

        listStaffController.getBtnDel().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                selectedStaffValues = (Set<StaffDTO>) tblStaff.getValue();
                if (selectedStaffValues.isEmpty()) {
                    CommonUtils.showChoseOne();
                } else {
                    ConfirmDialog.show(UI.getCurrent(), BundleUtils.getString("titleMessage") + "", BundleUtils.getString("bodyMessage"),
                            BundleUtils.getString("yes"), BundleUtils.getString("no"), new ConfirmDialog.Listener() {
                        @Override
                        public void onClose(ConfirmDialog dialog) {
//                                    if (!checkStaffHasValue(staffDTO)) {
                            if (dialog.isConfirmed()) {
                                List<StaffDTO> lstStaffDel = Lists.newArrayList();
                                lstStaffDel.addAll(selectedStaffValues);
                                if (selectedStaffValues.size() > 1) {
                                    //add user loging
                                    StaffDTO userLog = new StaffDTO();
//                                            userLog.setUserNameLogging("Username");
                                    lstStaffDel.add(userLog);
                                    message = doDeleteLstStaff(lstStaffDel);
                                    if (message.contains(Constants.SUCCESS)) {
                                        for (StaffDTO staffDTO : lstStaffDel) {
                                            tblStaff.removeItem(staffDTO);
                                        }
                                        tblStaff.resetPage();
                                        CommonUtils.showDeleteSuccess(BundleUtils.getString("staff"));
                                    } else {
                                        CommonUtils.showDeleteFail(BundleUtils.getString("staff"));
                                    }
                                } else {
                                    tblStaff = listDepartmentsAndStaffUI.getTblListStaffUI().getMainTable();
                                    selectedStaffValues = (Set<StaffDTO>) tblStaff.getValue();
                                    StaffDTO staffDTO = (StaffDTO) selectedStaffValues.toArray()[0];
//                                            staffDTO.setUserNameLogging("Username");
                                    message = doDeleteStaff(staffDTO);
                                    if (message.contains(Constants.SUCCESS)) {
                                        tblStaff.removeItem(staffDTO);
                                        tblStaff.resetPage();
                                        CommonUtils.showDeleteSuccess(BundleUtils.getString("staff"));
                                    } else {
                                        CommonUtils.showDeleteFail(BundleUtils.getString("staff"));
                                    }
                                }
                            }
                        }
                    });
                }
                CommonFunction.enableButtonAfterClick(event);
            }

        });

        addBtnAssignRolesClickedListener();
    }

    // Kiem tra phong ban da co nhan vien hay chưa
    public boolean deptIsParent(DepartmentDTO departmentDTO) {
        boolean isParent = false;
        List<DepartmentDTO> lst = null;
        lst = getListDept(departmentDTO);
        if (DataUtil.isListNullOrEmpty(lst)) {
            return false;
        }
        String deptPath = "";
        String deptId = departmentDTO.getDeptId();
        int length = 0;
        int index = 0;
        for (DepartmentDTO departmentDTO1 : lst) {
            deptPath = departmentDTO1.getDeptPath();
            index = deptPath.lastIndexOf(deptId);
            if (deptPath.length() > index + deptId.length() + 1) {
                return true;
            }
        }
        return isParent;
    }

    public List<DepartmentDTO> getListDept(DepartmentDTO departmentDTO) {
        List<ConditionBean> lstCondition = new ArrayList<>();
        lstCondition.add(new ConditionBean(Constants.DEPARTMENT.DEPT_PATH, departmentDTO.getDeptId(), ConditionBean.Operator.NAME_LIKE, ConditionBean.Type.STRING));
        List<DepartmentDTO> lst = null;
        try {
            lst = WSDepartment.getListDepartmentByCondition(lstCondition, 0, Integer.MAX_VALUE, Constants.ASC, "code");
        } catch (Exception ex) {
            Logger.getLogger(ListDeptAndStaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    public boolean checkHasValue(Object object) {
        DepartmentDTO department = (DepartmentDTO) object;
        StaffDTO staff = new StaffDTO();
        //set deptid cua staff la deptid o phong ban
        staff.setDeptId(department.getDeptId());
        List<StaffDTO> listStaffDTO;
        try {
            listStaffDTO = WSStaff.getListStaffDTO(staff, 0, Integer.MAX_VALUE, Constants.ASC, "code");

            if (listStaffDTO != null) {
                return true;

            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkStaffHasValue(Object object) {
        StaffDTO staff = (StaffDTO) object;
//        StaffStockDTO staffStockDTO = new StaffStockDTO();
        //set deptid cua staff la deptid o phong ban
//        staffStockDTO.setStaffId(staff.getStaffId());
//        List<StaffStockDTO> listStaffStockDTO;
//        try {
//            listStaffStockDTO = WSStaffStock.getListStaffStockDTO(staffStockDTO, 0, Integer.MAX_VALUE, Constants.ASC, "code");
//
//            if (listStaffStockDTO != null) {
//                return true;
//            } else {
//                return false;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return false;
    }

    //click row get id and getlistStaff by deptId
    public void fillDataStaff(Object object) {
        //cast value get from table row
        DepartmentDTO department = (DepartmentDTO) object;
        StaffDTO staff = new StaffDTO();
        //set deptid cua staff la deptid o phong ban
        staff.setDeptId(department.getDeptId());
        List<StaffDTO> listStaffDTO;
        try {
            listStaffDTO = WSStaff.getListStaffDTO(staff, 0, Integer.MAX_VALUE, Constants.ASC, "code");
//            if (listStaffDTO == null) {
//                listStaffDTO = Lists.newArrayList();
//                btnImportEmp.setEnabled(false);
//                btnExportEmp.setEnabled(false);
//            } else {
//                btnImportEmp.setEnabled(true);
//                btnExportEmp.setEnabled(true);
//            }
            listStaffController.setDataTable(listStaffDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Fill du lieu cua khach hang qua panel tim kiem hang hoa
    public void fillData2StaffForm(Object object) {
        searchFormStaffController.fillData2StaffForm(object);
    }

    //Fill department info to search field panel - using when edit or coppy deparment
    public void fillDataToDepartmentSearchField(Object object) {
        searchFormDeptController.fillDataDepartment(object);
    }

    public void doResetInputDept() {
        ShortcutUtils.setFocus(listDepartmentsAndStaffUI.getSearchDepartmentForm().getTxtDeptCode());
        listDepartmentsAndStaffUI.getSearchDepartmentForm().getTxtDeptCode().setValue("");
        listDepartmentsAndStaffUI.getSearchDepartmentForm().getTxtDeptName().setValue("");
//        listDepartmentsAndStaffUI.getSearchDepartmentForm().getDeptTopLevel().codeCombo.setValue(null);
//        listDepartmentsAndStaffUI.getSearchDepartmentForm().getDeptTopLevel().nameCombo.setValue(null);
        listDepartmentsAndStaffUI.getSearchDepartmentForm().getTxtDeptAddr().setValue("");
        listDepartmentsAndStaffUI.getSearchDepartmentForm().getTxtDeptEmail().setValue("");

        departmentDTO = new DepartmentDTO();
        searchFormDeptController.fillDataComboStatus();
    }

    public void doResetInputStaff() {
        ShortcutUtils.setFocus(listDepartmentsAndStaffUI.getSearchStaffForm().getTxtEmpCode());
        listDepartmentsAndStaffUI.getSearchStaffForm().getTxtEmpCode().setValue("");
        listDepartmentsAndStaffUI.getSearchStaffForm().getTxtEmpName().setValue("");
        listDepartmentsAndStaffUI.getSearchStaffForm().getF9Departments().codeCombo.setValue(null);
        listDepartmentsAndStaffUI.getSearchStaffForm().getF9Departments().nameCombo.setValue(null);

        staffDTO = new StaffDTO();
        searchFormStaffController.fillDataCombo();
    }

    //Get Dept Controll
    private DepartmentDTO getControllDepartment() {
        DepartmentDTO department = new DepartmentDTO();
        department.setCode(searchFormDeptController.getMapsSearch().get(Constants.DEPARTMENT.CODE));
        department.setName(searchFormDeptController.getMapsSearch().get(Constants.DEPARTMENT.NAME));
        department.setAddress(searchFormDeptController.getMapsSearch().get(Constants.DEPARTMENT.ADDRESS));
        department.setStatus(searchFormDeptController.getMapsSearch().get(Constants.DEPARTMENT.STATUS));
        department.setParentDeptId(searchFormDeptController.getMapsSearch().get(Constants.DEPARTMENT.PARENT_DEPT_ID));
        department.setDescription(searchFormDeptController.getMapsSearch().get(Constants.DEPARTMENT.DESCRIPTION));
        department.setTelNumber(searchFormDeptController.getMapsSearch().get(Constants.DEPARTMENT.TEL_NUMBER));
        department.setFax(searchFormDeptController.getMapsSearch().get(Constants.DEPARTMENT.FAX));
        department.setEmail(searchFormDeptController.getMapsSearch().get(Constants.DEPARTMENT.EMAIL));
        department.setContactName(searchFormDeptController.getMapsSearch().get(Constants.DEPARTMENT.CONTACT_NAME));
        return department;
    }

    //Get GoodsController
    private StaffDTO getControlStaffs() {
        StaffDTO staff = new StaffDTO();
        staff.setCode(searchFormStaffController.getMapsSearch().get(Constants.STAFF.CODE));
        staff.setName(searchFormStaffController.getMapsSearch().get(Constants.STAFF.NAME));
        staff.setEmail(searchFormStaffController.getMapsSearch().get(Constants.STAFF.EMAIL));
        staff.setStatus(searchFormStaffController.getMapsSearch().get(Constants.STAFF.STATUS));
        staff.setTelNumber(searchFormStaffController.getMapsSearch().get(Constants.STAFF.TEL_NUMBER));
        staff.setDeptId(searchFormStaffController.getMapsSearch().get(Constants.STAFF.DEPT_ID));
        if (staff.getDeptId().equalsIgnoreCase("")) {
            staff.setDeptId(null);
        }
        staff.setStaffType(searchFormStaffController.getMapsSearch().get(Constants.STAFF.STAFF_TYPE));
        return staff;
    }

    //ham xoa 1 ban ghi
    public String doDeleteDept(DepartmentDTO deptDTO) {
        try {
            return WSDepartment.deleteDepartment(deptDTO.getDeptId(), staffDTO.getName());
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public String doDeleteLstDept(List<DepartmentDTO> lstDept) {
        try {
            return WSDepartment.deleteLstDepartment(lstDept);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    //ham tim kiem phong ban
    public void doSearchDepartments() {
        //lay gia tri tim kiem
        searchFormDeptController.getValueSearchDepartment();
        //set value
        DepartmentDTO department = getControllDepartment();
        //get list
        List<DepartmentDTO> lstDepartment = listDepartmentController.getListDepartmentDTO(department);
        if (lstDepartment == null || lstDepartment.isEmpty()) {
            Notification.show(BundleUtils.getString("dataNotFound"), Notification.Type.HUMANIZED_MESSAGE);
        }
        listDepartmentController.setDataTable(lstDepartment);
    }

    //ham tim kiem nhan vien
    public void doSearchStaff() {
        //get value search
        searchFormStaffController.getValueSearchStaff();
        //set staffdto
        StaffDTO staff = getControlStaffs();
        departmentDTO = (DepartmentDTO) searchFormStaffController.getCboDepartment().codeCombo.getValue();
        //Lay danh sach nhan vien        
        List<StaffDTO> lstStaffs = listStaffController.getListStaffDTO(staff);
        if (lstStaffs == null || lstStaffs.isEmpty()) {
            Notification.show(BundleUtils.getString("dataNotFound"), Notification.Type.HUMANIZED_MESSAGE);
//            btnExportEmp.setEnabled(false);
            lstStaffs = new ArrayList<>();
        }
        //Hien thi danh sach nhan vien
        listStaffController.setDataTable(lstStaffs);
    }

    //ham xoa 1 ban ghi staff
    public String doDeleteStaff(StaffDTO staffDTO) {
        try {
            StaffDTO sdto = staffDTO.copy();
            sdto.setStatus("0");
            return WSStaff.updateStaff(sdto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //ham xoa nhieu staff
    public String doDeleteLstStaff(List<StaffDTO> lstStaffDel) {
//        lstStaffDel.remove(staffDTO.getDeptName());
        List<StaffDTO> listStaffDel = new ArrayList<>();
        for (StaffDTO staff : lstStaffDel) {
            staff.setStatus("0");
            listStaffDel.add(staff);
        }
        return WSStaff.insertOrUpdateListStaffs(listStaffDel);
    }

    //ham xoa 1 staffstock
    public String doDeleteStaffStock(StaffDTO staffDTO) {
        try {
//            return WSStaffStock.deleteStaffStock(staffDTO.getStaffStockId());
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    //get children of department
    public List<DepartmentDTO> getChildDepartment(DepartmentDTO deptDTO) {
        List<DepartmentDTO> lstChildren = Lists.newArrayList();
        DepartmentDTO ddto = new DepartmentDTO();
        ddto.setParentDeptId(deptDTO.getParentDeptId());
        //lay danh sach phong ban cap con
        try {
            lstChildren = WSDepartment.getListDepartmentDTO(ddto, 0, Integer.MAX_VALUE, "asc", "code");
        } catch (Exception e) {
            e.getMessage();
        }
        return lstChildren;
    }

    private void addBtnAssignRolesClickedListener() {
        listDepartmentsAndStaffUI.addBtnAssignRolesListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<StaffDTO> selectedValues = (Set<StaffDTO>) tblStaff.getValue();
                List<StaffDTO> lstStaffs = Lists.newArrayList(selectedValues);
                if (!DataUtil.isListNullOrEmpty(lstStaffs)) {
                    if (lstStaffs.size() > 1) {
                        CommonUtils.showChoseOnlyOne();
                    } else {
                        StaffDTO staff = lstStaffs.get(0);
                        MapStaffRolesDiaglog dialog = new MapStaffRolesDiaglog(BundleUtils.getString("map.staff.roles.dialog.caption"));
                        MapStaffRolesController controller = new MapStaffRolesController(dialog);
                        controller.init(staff);
                        UI.getCurrent().addWindow(dialog);
                    }
                } else {
                    CommonUtils.showChoseOne();
                }
                event.getButton().setEnabled(true);
            }
        });
    }
}
