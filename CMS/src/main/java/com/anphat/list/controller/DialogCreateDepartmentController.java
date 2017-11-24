package com.anphat.list.controller;

import com.anphat.list.ui.DialogCreateDepartment;
import com.cms.common.ws.WSAppParams;
import com.cms.login.ws.WSDepartment;
import com.cms.login.ws.WSStaff;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.cms.component.CustomPageTableFilter;
import com.cms.component.MappingCombobox;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.DepartmentDTO;
import com.cms.dto.StaffDTO;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonUtils;
import com.cms.utils.CommonValidator;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.DateUtil;
import com.cms.utils.ShortcutUtils;
import com.google.common.collect.Lists;
import com.vwf5.base.dto.ResultDTO;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
//import viettel.passport.client.UserToken;

/**
 *
 * @author hungkv
 */
public class DialogCreateDepartmentController {

//    String deptPath = "";
    String action;
    String message;
    DialogCreateDepartment dialogCreateDepartment;
    DepartmentDTO departmentDTO;
//    MappingCombobox comboDeptTopLevel;
    CustomPageTableFilter<IndexedContainer> tblDepartment;
    CustomPageTableFilter<IndexedContainer> tblStaff;
    //list department
//    List<DepartmentDTO> lstCboSuperiorUnit = null;//fill f9
    List<AppParamsDTO> listStatusType = Lists.newArrayList();
    public Map<String, AppParamsDTO> mapNameFromKey = new HashMap<>();
    private ComboComponent comboComponent = new ComboComponent();
    ComboBox cboStatus;
    //ket qua tra ve
    ResultDTO resultDTO;
    String deptId;
    String success = null;
    String select = BundleUtils.getString("all");
    String email;
    String phone;
    private MappingCombobox f9ComboDepartments;

    public DialogCreateDepartmentController(final DialogCreateDepartment dialogCreateDepartment, final String action, final CustomPageTableFilter<IndexedContainer> table, final CustomPageTableFilter<IndexedContainer> tableStaff, MappingCombobox f9ComboDepartments) {
        this.action = action;
        this.dialogCreateDepartment = dialogCreateDepartment;
        this.cboStatus = dialogCreateDepartment.getCbxStatus();
        this.tblDepartment = table;
        this.tblStaff = tableStaff;
        this.departmentDTO = dialogCreateDepartment.getDepartmentDTO();
        this.f9ComboDepartments = f9ComboDepartments;
        actionListener();
    }

    //khoi tao
    public void init(DepartmentDTO ddto) {
        this.departmentDTO = ddto;
        deptId = ddto.getDeptId();
        fillDataComboStatus();
        fillDataDepartment(ddto);
    }

    private void actionListener() {
        //xu li nut save
        ShortcutUtils.setShortcutKey(dialogCreateDepartment.getBtnSave());
        dialogCreateDepartment.getBtnSave().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!isNullValue()) {//neu khong trong
                    if (action.equalsIgnoreCase(Constants.EDIT)) {
                        if (!validateField()) {
                            return;
                        }
                        departmentDTO = getControllDepartment();
                        //duyot: 22/08: add check dieu dkien
                        /*
                         1. Khong la phong ban cha
                         2. Khong co nhan vien || nhan vien het hieu luc
                         */
                        //NEU TRANG THAI THAY DOI THANH HET HIEU LUC
                        if (departmentDTO.getStatus().equalsIgnoreCase(Constants.DEPARTMENT.INVALID)) {
                            // 2.Khong co nhan vien || nhan vien het hieu luc
                            if (!isValidEmptInDept(departmentDTO)) {
                                Notification.show(BundleUtils.getString("dept.staff.deldept.invalidstaff"), Notification.Type.HUMANIZED_MESSAGE);
                                return;
                            }
                        }
                        success = updateDepartment();
                        if (success.equalsIgnoreCase(Constants.SUCCESS)) {
                            mapCode2Name(departmentDTO);
                            reloadTable(departmentDTO);
                            tblDepartment.resetPage();
                            CommonUtils.showUpdateSuccess(BundleUtils.getString("department"));
                            dialogCreateDepartment.close();
                        } else {
                            CommonUtils.showUpdateFail(BundleUtils.getString("department"));
                        }
                    }
                    if (action.equalsIgnoreCase(Constants.COPY)) {
                        if (!validateField()) {
                            return;
                        }
                        resultDTO = createDepartment();
                        if (resultDTO == null) {
                            resultDTO = new ResultDTO();
                        }
                        //duyot: result has no message
                        if (resultDTO.getMessage() != null
                                && Constants.SUCCESS.equalsIgnoreCase(resultDTO.getMessage())) {
                            deptId = resultDTO.getId();//lay deptid sau khi them thanh cong
                            departmentDTO.setDeptId(deptId);
                            success = insertOrUpdateDepartment(departmentDTO);
                            mapCode2Name(departmentDTO);
                            CommonUtils.reloadTable(tblDepartment, departmentDTO);
                            reloadF9DepartmentOnSearchStaffScreen();
                            dialogCreateDepartment.close();
                            if (success.equalsIgnoreCase(Constants.SUCCESS)) {
                                CommonUtils.showCopySuccess(BundleUtils.getString("department"));
                            }
                        } else //Check record duplicate in database table
                        {
                            CommonUtils.showFail();
                        }
                    }
                    if (action.equalsIgnoreCase(Constants.ADD)) {
                        if (!validateField()) {
                            return;
                        }
                        //insert and return id
                        resultDTO = createDepartment();
                        if (resultDTO == null) {
                            resultDTO = new ResultDTO();
                        }
                        if (resultDTO.getMessage() != null && Constants.SUCCESS.equalsIgnoreCase(resultDTO.getMessage())) {
                            deptId = resultDTO.getId();//lay deptid sau khi them thanh cong                                                       
                            departmentDTO.setDeptId(deptId);
                            //neu thanh cong
                            mapCode2Name(departmentDTO);
                            //load lai bang                            
                            CommonUtils.reloadTable(tblDepartment, departmentDTO);
                            //load lai combobox
                            reloadF9DepartmentOnSearchStaffScreen();
                            //Dong dialog
                            dialogCreateDepartment.close();
                            CommonUtils.showInsertSuccess(BundleUtils.getString("department"));
                        } else //Check record duplicate in database table
                        {
                            if (resultDTO.getKey() != null && resultDTO.getKey().equalsIgnoreCase(Constants.ERROR.ERROR_EXISTED)) {
                                Notification.show(BundleUtils.getString(Constants.ERROR.ERROR_EXISTED), Notification.Type.TRAY_NOTIFICATION);
                                ShortcutUtils.setFocus(dialogCreateDepartment.getTxtDepartmentCode());
                            } else if (resultDTO.getKey() != null && resultDTO.getKey().equalsIgnoreCase(Constants.ERROR.ERROR_SYSTEM)) {
                                Notification.show(BundleUtils.getString(Constants.ERROR.ERROR_SYSTEM), Notification.Type.TRAY_NOTIFICATION);
                            } else {
                                CommonUtils.showFail();
                            }
                        }
                    }
                }
            }
        });

        //xu li nut close
        dialogCreateDepartment.getBtnCancel().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                dialogCreateDepartment.close();
            }
        });
    }

    public void fillDataComboStatus() {
        AppParamsDTO appParamsDTO = new AppParamsDTO();
        appParamsDTO.setParType(Constants.APP_PARAMS.COMMON_STATUS);
        listStatusType = getListStatusType(appParamsDTO);
        comboComponent.fillDataCombo(cboStatus, "", "1", listStatusType, Constants.APP_PARAMS.COMMON_STATUS);
    }

    //get data department form to edit or copy
    public DepartmentDTO getControllDepartment() {
        try {
            String strDeptCode, strDeptName, strContact, strDesc, strPhone, strFax, strEmail, strAddress, deptLevel;
            String strStatus;
            strDeptCode = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtDepartmentCode().getValue().trim()));
            strDeptName = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtDepartmentName().getValue().trim()));
            strDesc = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTextArea_1().getValue().trim()));
            strPhone = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtPhoneNumber().getValue().trim()));
            strEmail = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtEmail().getValue().trim()));
            strAddress = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtAddress().getValue().trim()));

            AppParamsDTO appStatus = (AppParamsDTO) dialogCreateDepartment.getCbxStatus().getValue();
            strStatus = DataUtil.getStringNullOrZero(appStatus.getParCode());
            departmentDTO.setDeptId(departmentDTO.getDeptId());
            departmentDTO.setCode(strDeptCode);
            departmentDTO.setName(strDeptName);
            departmentDTO.setDescription(strDesc);
            departmentDTO.setTelNumber(strPhone);
            departmentDTO.setEmail(strEmail);
            departmentDTO.setAddress(strAddress);
            departmentDTO.setStatus(strStatus);

            return departmentDTO;
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(DialogCreateDepartmentController.class.getName()).log(Level.SEVERE, null, ex);
            return new DepartmentDTO();
        }
    }

    //lay ds status
    public List<AppParamsDTO> getListStatusType(AppParamsDTO appParamsDTO) {
        try {
            listStatusType = WSAppParams.getListAppParamsDTO(appParamsDTO, 0, Integer.parseInt(Constants.PAGE_SIZE_DEFAULT_20), "", "parOrder");
            //Put danh sach trang thai khach hang vao map
            for (AppParamsDTO paramsDTO : listStatusType) {
                String key = paramsDTO.getParType().replace('_', '.').toLowerCase() + "." + paramsDTO.getParCode();
                paramsDTO.setDisplayName(BundleUtils.getString(key));
                mapNameFromKey.put(paramsDTO.getParCode(), paramsDTO);
                mapNameFromKey.put(paramsDTO.getParName(), paramsDTO);
            }
        } catch (Exception ex) {
//            StringBuilder sb = new StringBuilder();
            ex.printStackTrace();
//            String param_list = BusinessLog.getParamList(sb.toString());
//            ErrorLogs.createLogs(ex, param_list);
            Logger.getLogger(DialogCreateDepartmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listStatusType;
    }

    //Update department
    public String updateDepartment() {
        return WSDepartment.updateDepartment(departmentDTO);
    }

    //Tao moi department
    public ResultDTO createDepartment() {
        ResultDTO result;
        try {
            departmentDTO = getValueForm();
            result = WSDepartment.insertDepartment(departmentDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(DialogCreateDepartmentController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }

    //insert or update department
    public String insertOrUpdateDepartment(DepartmentDTO deptDTO) {
        try {
            return WSDepartment.insertOrUpdateListDepartment(Lists.newArrayList(deptDTO));
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(DialogCreateDepartmentController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //fill data
    public void fillDataDepartment(DepartmentDTO departmentDTO) {

        if (action.equalsIgnoreCase(Constants.EDIT)) {
            dialogCreateDepartment.getTxtDepartmentCode().setValue(DataUtil.getStringNullOrZero(departmentDTO.getCode()));
            ShortcutUtils.setVisibleTextfield(dialogCreateDepartment.getTxtDepartmentCode(), false);
            dialogCreateDepartment.getTxtDepartmentName().setValue(DataUtil.getStringNullOrZero(departmentDTO.getName()));
        }
        if (action.equalsIgnoreCase(Constants.COPY)) {
            ShortcutUtils.setFocus(dialogCreateDepartment.getTxtDepartmentCode());
            dialogCreateDepartment.getTxtDepartmentName().setValue(DataUtil.getStringNullOrZero(departmentDTO.getName()));
        }
        if (action.equalsIgnoreCase(Constants.ADD)) {
            ShortcutUtils.setFocus(dialogCreateDepartment.getTxtDepartmentCode());
        }
//        if (!DataUtil.isStringNullOrEmpty(departmentDTO.getCode())) {
//            try {
//                for (DepartmentDTO department : lstCboSuperiorUnit) {
//                    if (departmentDTO.getParentDeptId().equalsIgnoreCase(department.getDeptId()) && departmentDTO.getParentDeptId() != null) {
//                        dialogCreateDepartment.getComboDeptTopLevel().getNameCombo().setValue(department);
//                        break;
//                    }
//                }
//            } catch (Exception ex) {
//                StringBuilder sb = new StringBuilder();
//                String param_list = BusinessLog.getParamList(sb.toString());
//                ErrorLogs.createLogs(ex, param_list);
//                Logger.getLogger(DialogCreateDepartmentController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

//        dialogCreateDepartment.getTxtContact().setValue(DataUtil.getStringNullOrZero(departmentDTO.getContactName()));
        dialogCreateDepartment.getTxtPhoneNumber().setValue(DataUtil.getStringNullOrZero(departmentDTO.getTelNumber()));
        dialogCreateDepartment.getTxtEmail().setValue(DataUtil.getStringNullOrZero(departmentDTO.getEmail()));
        dialogCreateDepartment.getTxtAddress().setValue(DataUtil.getStringNullOrZero(departmentDTO.getAddress()));
        dialogCreateDepartment.getTextArea_1().setValue(DataUtil.getStringNullOrZero(departmentDTO.getDescription()));
//        dialogCreateDepartment.getTxtFax().setValue(DataUtil.getStringNullOrZero(departmentDTO.getFax()));
    }

    public DepartmentDTO getValueForm() {
        String deptCode;
        String deptName;
//        String deptContactName;
        String deptDesc;
        String deptPhone;
//        String deptFax;
        String deptEmail;
        String deptAddr;
        String deptStatus = "";
//        String deptLevel = "";
        DepartmentDTO deptDTO;
        Date createdDate;
        deptCode = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtDepartmentCode().getValue().trim()));
        deptName = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtDepartmentName().getValue().trim()));
//        deptContactName = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtContact().getValue().trim()));
        deptDesc = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTextArea_1().getValue().trim()));
        deptPhone = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtPhoneNumber().getValue().trim()));
//        deptFax = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtFax().getValue().trim()));
        deptEmail = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtEmail().getValue().trim()));
        deptAddr = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtAddress().getValue().trim()));

        if (dialogCreateDepartment.getCbxStatus().getValue() != null) {
            AppParamsDTO appParamsStatus = (AppParamsDTO) dialogCreateDepartment.getCbxStatus().getValue();

            deptStatus = DataUtil.getStringNullOrZero(appParamsStatus.getParCode());

        }

        departmentDTO.setCode(deptCode);
        departmentDTO.setName(deptName);
//        departmentDTO.setContactName(deptContactName);
        departmentDTO.setStatus(deptStatus);
        departmentDTO.setAddress(deptAddr);
        departmentDTO.setEmail(deptEmail);
//        departmentDTO.setFax(deptFax);
        departmentDTO.setTelNumber(deptPhone);
//        departmentDTO.setParentDeptId(deptLevel);
        departmentDTO.setDescription(deptDesc);
//        departmentDTO.setDeptPath(deptPath);
        createdDate = DateUtil.sysDate();
        departmentDTO.setCreateDate(DateUtil.date2ddMMyyyyHHMMss(createdDate));
        return departmentDTO;
    }

    public DepartmentDTO mapCode2Name(DepartmentDTO departmentDTO) {
        String parentDeptId = "";
        parentDeptId = departmentDTO.getParentDeptId();
        DepartmentDTO deptDTO = new DepartmentDTO();//map code 2 name
        deptDTO.setDeptId(parentDeptId);
        try {
            deptDTO = WSDepartment.findDepartmentById(parentDeptId);
            if (deptDTO != null) {
                if (!DataUtil.isStringNullOrEmpty(departmentDTO.getStatus())) {
                    departmentDTO.setStatusName(BundleUtils.getString("common.status." + departmentDTO.getStatus()));
                }

                if (parentDeptId.equalsIgnoreCase(deptDTO.getDeptId()) && parentDeptId != null) {
                    departmentDTO.setParentDeptName(deptDTO.getName());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
//            StringBuilder sb = new StringBuilder();
//            String param_list = BusinessLog.getParamList(sb.toString());
//            ErrorLogs.createLogs(ex, param_list);
            Logger.getLogger(DialogCreateDepartmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departmentDTO;
    }

    public void resetValue() {
        ShortcutUtils.setFocus(dialogCreateDepartment.getTxtDepartmentCode());
        dialogCreateDepartment.getTxtDepartmentCode().setValue("");
        dialogCreateDepartment.getTxtDepartmentName().setValue("");
//        dialogCreateDepartment.getComboDeptTopLevel().codeCombo.setValue(null);
//        dialogCreateDepartment.getComboDeptTopLevel().nameCombo.setValue(null);
        dialogCreateDepartment.getTxtAddress().setValue("");
        dialogCreateDepartment.getTextArea_1().setValue("");
        dialogCreateDepartment.getTxtEmail().setValue("");
//        dialogCreateDepartment.getTxtFax().setValue("");
        dialogCreateDepartment.getTxtPhoneNumber().setValue("");
//        dialogCreateDepartment.getTxtContact().setValue("");
        departmentDTO = new DepartmentDTO();
        fillDataComboStatus();
    }

    public boolean isNullValue() {
        if (DataUtil.isStringNullOrEmpty(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtDepartmentCode().getValue().trim()))) {
            dialogCreateDepartment.getTxtDepartmentCode().focus();
            CommonUtils.showMessageRequired("lb.deptstaff.dept.code");
            return true;
        }
        if (DataUtil.isStringNullOrEmpty(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtDepartmentName().getValue().trim()))) {
            dialogCreateDepartment.getTxtDepartmentName().focus();
            CommonUtils.showMessageRequired("lb.deptstaff.dept.name");
            return true;
        }
        if (DataUtil.isStringNullOrEmpty(dialogCreateDepartment.getCbxStatus().getValue())) {
            dialogCreateDepartment.getCbxStatus().focus();
            CommonUtils.showMessageRequired("lb.deptstaff.dept.status");
            return true;
        }
        if (DataUtil.isStringNullOrEmpty(DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtAddress().getValue().trim()))) {
            dialogCreateDepartment.getTxtAddress().focus();
            CommonUtils.showMessageRequired("lb.deptstaff.dept.addr");
            return true;
        }
        return false;
    }

    public void reloadTable(DepartmentDTO ddto) {
        Collection values = tblDepartment.getItemIds();
        List<DepartmentDTO> lst = Lists.newArrayList();
        lst.addAll(values);
        for (DepartmentDTO a : lst) {
            if (a.getDeptId().equals(ddto.getDeptId())) {
                a.setCode(ddto.getCode());
                a.setName(ddto.getName());
                a.setEmail(ddto.getEmail());
                a.setContactName(ddto.getContactName());
                a.setDescription(ddto.getDescription());
                a.setEmail(ddto.getEmail());
                a.setTelNumber(ddto.getTelNumber());
                if (mapNameFromKey != null) {
                    a.setStatusName(mapNameFromKey.get(ddto.getStatus()).getDisplayName());
                }
                a.setAddress(ddto.getAddress());
                CommonUtils.reloadTable(tblDepartment, a);
                break;
            }
        }
    }

    public boolean validateField() {
        email = DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtEmail().getValue().trim());
        phone = DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtPhoneNumber().getValue().trim());
//        fax = DataUtil.getStringEscapeHTML4(dialogCreateDepartment.getTxtFax().getValue().trim());
        if (!CommonValidator.isEmailValid(email)) {
            dialogCreateDepartment.getTxtEmail().setRequiredError(BundleUtils.getString("common.error.email"));
            dialogCreateDepartment.getTxtEmail().focus();
            return false;
        }
        if (!CommonValidator.isPhoneValid(phone)) {
            dialogCreateDepartment.getTxtPhoneNumber().setRequiredError(BundleUtils.getString("common.error.phone"));
            dialogCreateDepartment.getTxtPhoneNumber().focus();
            return false;
        }
        return true;
    }

    //chekc xem co nhan vien || nhan vien het hieu luc
    public boolean isValidEmptInDept(DepartmentDTO departmentDTO) {
        //1. lay ra danh sach nhan vien thuoc phong ban voi trang thai bang 1
        List<StaffDTO> lstStaff;
        StaffDTO serachStaff = new StaffDTO();
        serachStaff.setDeptId(departmentDTO.getDeptId());
        serachStaff.setStatus(Constants.STAFF.VALID);
        try {
            lstStaff = WSStaff.getListStaffDTO(serachStaff, 0, Integer.MAX_VALUE, Constants.ASC, "code");
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        //check
        return DataUtil.isListNullOrEmpty(lstStaff); //neu k co du lieu
    }

    private void reloadF9DepartmentOnSearchStaffScreen() {
        Collection values = tblDepartment.getItemIds();
        List<DepartmentDTO> lstDepartments = Lists.newArrayList();
        lstDepartments.addAll(values);
        f9ComboDepartments.setValues(lstDepartments, Constants.DEPARTMENT.CODE, Constants.DEPARTMENT.NAME);
    }
}
