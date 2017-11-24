/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.list.ui.DialogAddMapStaffCustomer;
import com.cms.common.ws.WSAppParams;
import com.cms.login.ws.WSCustomer;
import com.cms.login.ws.WSMapStaffCustomer;
import com.cms.login.ws.WSStaff;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CustomerDTO;
import com.cms.dto.MapStaffCustomerDTO;
import com.cms.dto.StaffDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.TableUtils;
import com.vwf5.base.utils.ConditionBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author quyendm
 */
public class DialogAddMapStaffCustomerController {

    private final DialogAddMapStaffCustomer dialogAddMapStaffCustomer;
    private CommonTableFilterPanel leftPanelTable;
    private CustomPageTableFilter tblLeft;
    private CommonTableFilterPanel rightPanelTable;
    private CustomPageTableFilter tblRight;
    private Button btnAdd;
    private Button btnSearch;
    private Button btnSave;
    private Button btnCancel;
    private List<StaffDTO> lstStaffDTOs;
    private List<CustomerDTO> lstCustDTOs;
    private List<MapStaffCustomerDTO> lstAddStaffs;
    private List<MapStaffCustomerDTO> lstAddCusts;
    private List<MapStaffCustomerDTO> lstMapStaffCustomerDTOs;
    private final LinkedHashMap<String, CustomTable.Align> headerStaffLeft = BundleUtils.getHeadersFilter("map.staff.header.left");
    private final LinkedHashMap<String, CustomTable.Align> headerCustLeft = BundleUtils.getHeadersFilter("map.staff.header.left.cust");
    private final LinkedHashMap<String, CustomTable.Align> headerStaffRight = BundleUtils.getHeadersFilter("map.staff.header.right");
    private final LinkedHashMap<String, CustomTable.Align> headerCustRight = BundleUtils.getHeadersFilter("map.staff.header.right.cust");
    private final String langStaff = "lb.header.staff";
    private final String langCust = "map.staff.customer";
    private final int tblSize = 10;
    private final String captionStaffTable = BundleUtils.getString("caption.dept.staff.listStaffInfo");
    private final String captionStaffCustomerTable = BundleUtils.getString("map.staff.customer.caption");
    private final String captionCustTable = BundleUtils.getString("caption.dept.staff.listCustInfo");
    private final String captionCustTableView = BundleUtils.getString("caption.dept.staff.listCustInfo.view");
    private BeanItemContainer containerLeft;
    private BeanItemContainer containerRight;
    private CustomerDTO customerDTO;
    private TableUtils tableUtils;
    private boolean isSaveSuccess = false;
    private List<AppParamsDTO> lstStaffType;
    private ComboComponent comboComponent;
    //Man hinh nhap map staff customer tu giao dien danh sach nhan vien
    private StaffDTO staffDTO;

    //Ham khoi tao thi gan nhan vien quan ly cho khach hang
    public DialogAddMapStaffCustomerController(DialogAddMapStaffCustomer dialogAddMapStaffCustomer, List<MapStaffCustomerDTO> lstMapStaffCustomerDTOs, CustomerDTO customerDTO) {
        this.dialogAddMapStaffCustomer = dialogAddMapStaffCustomer;
        if (DataUtil.isListNullOrEmpty(lstMapStaffCustomerDTOs)) {
            this.lstMapStaffCustomerDTOs = new ArrayList<>();
        } else {
            this.lstMapStaffCustomerDTOs = lstMapStaffCustomerDTOs;
        }
        this.customerDTO = customerDTO;
        getDatas();
        initComponents(true);
    }
    
    public DialogAddMapStaffCustomerController(DialogAddMapStaffCustomer dialogAddMapStaffCustomer) {
        this.dialogAddMapStaffCustomer = dialogAddMapStaffCustomer;
        //getDatas();
        initComponents(false);
    }

    //Getdata
    private void getDatas() {
        AppParamsDTO apdto = new AppParamsDTO();
        apdto.setParType(Constants.APP_PARAMS.STAFF_TYPE);
        apdto.setStatus(Constants.ACTIVE);
        try {
            lstStaffType = WSAppParams.getListAppParamsDTO(apdto, Constants.INT_0, Constants.INT_100, Constants.ASC, Constants.APP_PARAMS.PAR_ORDER);
            if (lstStaffType == null) {
                lstStaffType = new ArrayList<>();
            }
        } catch (Exception e) {
            lstStaffType = new ArrayList<>();
        }
    }

    //Ham khoi tao thi gan khach hang cho nhan vien
    public DialogAddMapStaffCustomerController(DialogAddMapStaffCustomer dialogAddMapStaffCustomer, StaffDTO staffDTO) {
        this.dialogAddMapStaffCustomer = dialogAddMapStaffCustomer;
        this.staffDTO = staffDTO;
        initComponents(false);
    }

    //Khoi tao cac thanh phan
    private void initComponents(boolean isCustInfo) {
        initCustInfo(isCustInfo);
        leftPanelTable = dialogAddMapStaffCustomer.getPanelStaffAdd();
        rightPanelTable = dialogAddMapStaffCustomer.getPanelStaffOld();
        //Nut tim kiem
        btnSearch = dialogAddMapStaffCustomer.getBtnSearch();
        btnSave = dialogAddMapStaffCustomer.getBtnSave();
        btnCancel = dialogAddMapStaffCustomer.getBtnCancel();
        btnAdd = dialogAddMapStaffCustomer.getBtnAdd();
        //Neu la them nhan vien quan ly cho khach hang
        if (isCustInfo) {
            comboComponent = new ComboComponent();
            comboComponent.fillDataCombo(dialogAddMapStaffCustomer.getCboStaffType(), "all", "", lstStaffType, Constants.APP_PARAMS.STAFF_TYPE);
            initStaffTable(leftPanelTable, true);
            initStaffTable(rightPanelTable, false);
            //ActionListener
            addActionStaffListener();
        } else {
            initCustTable(leftPanelTable, true);
            initCustTable(rightPanelTable, false);
            getDefaultMapStaffCustomers();
            //ActionListener
            addActionCustListener();
        }

    }

    //Lay danh sach map staff customer theo custId
    public void getDefaultMapStaffCustomers() {
        MapStaffCustomerDTO dTO = new MapStaffCustomerDTO();
        dTO.setStaffId(staffDTO.getStaffId());
        try {
            lstMapStaffCustomerDTOs = WSMapStaffCustomer.getListMapStaffCustomerDTO(dTO, Constants.INT_0, Constants.INT_100, Constants.ASC, "custCode");
        } catch (Exception e) {
        }
        if (!DataUtil.isListNullOrEmpty(lstMapStaffCustomerDTOs)) {
            setData2TableRight(lstMapStaffCustomerDTOs, false);
        }
    }

    //Truyen thong tin vao form thong tin khach hang
    private void initCustInfo(boolean isCustInfo) {
        if (isCustInfo) {
//            dialogAddMapStaffCustomer.getTxtCustCode().setValue(customerDTO.getCode());
            dialogAddMapStaffCustomer.getTxtCustName().setValue(customerDTO.getName());
        } else {
//            dialogAddMapStaffCustomer.getTxtCustCode().setValue(staffDTO.getCode());
            dialogAddMapStaffCustomer.getTxtCustName().setValue(staffDTO.getName());
        }
    }

    //Khoi tao bang danh sach nhan vien    
    private void initStaffTable(CommonTableFilterPanel filterPanel, boolean isAddTable) {
        filterPanel.getToolbar().setVisible(false);
        if (isAddTable) {
            containerLeft = new BeanItemContainer(StaffDTO.class);
            tblLeft = filterPanel.getMainTable();
            tableUtils = new TableUtils();
            tableUtils.generateColumn(tblLeft);
            tblLeft.setColumnExpandRatio(Constants.STAFF.NAME, 6);
            tblLeft.setColumnExpandRatio(Constants.STAFF.CODE, 3);
            tblLeft.setColumnExpandRatio(Constants.STAFF.STAFF_TYPE, 3);
            tblLeft.setColumnExpandRatio(Constants.CHECKBOX_COLUMN, 1);
            CommonFunctionTableFilter.initTable(filterPanel, headerStaffLeft, containerLeft, captionStaffTable, tblSize, langStaff);
            tblLeft.setColumnHeader(Constants.CHECKBOX_COLUMN, "");
            CommonUtils.convertFieldAppParamTable(tblLeft, Constants.STAFF.STAFF_TYPE, "STAFF_TYPE");
        } else {
            containerRight = new BeanItemContainer(MapStaffCustomerDTO.class);
            if (!DataUtil.isListNullOrEmpty(lstMapStaffCustomerDTOs)) {
                containerRight.addAll(lstMapStaffCustomerDTOs);
            }
            tblRight = filterPanel.getMainTable();
            tblRight.addGeneratedColumn("delete", new CustomTable.ColumnGenerator() {

                @Override
                public Object generateCell(final CustomTable source, final Object itemId, Object columnId) {
                    MapStaffCustomerDTO sdto = (MapStaffCustomerDTO) itemId;
                    if (!DataUtil.isStringNullOrEmpty(sdto.getMapId())) {
                        return "";
                    }
                    Button btnDelete = new Button(new ThemeResource(Constants.ICON.CANCEL));
                    btnDelete.addStyleName(Constants.ICON.V_LINK);
                    btnDelete.addClickListener(new Button.ClickListener() {

                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            source.removeItem(itemId);
                            tblRight.resetPage();
                        }
                    });
                    return btnDelete;
                }
            });
            tblRight.setColumnWidth("delete", 60);
            tblRight.setColumnExpandRatio("staffName", 3);
            tblRight.setColumnExpandRatio("staffCode", 1);
            tblRight.setColumnExpandRatio("staffType", 1);
            CommonFunctionTableFilter.initTable(filterPanel, headerStaffRight, containerRight, captionStaffCustomerTable, tblSize, langStaff);
            tblRight.setColumnHeader("delete", Constants.NULL);
            CommonUtils.convertFieldAppParamTable(tblRight, Constants.STAFF.STAFF_TYPE, "STAFF_TYPE");
        }
    }

    //Khoi tao bang danh sach khach hang    
    private void initCustTable(CommonTableFilterPanel filterPanel, boolean isAddTable) {
        filterPanel.getToolbar().setVisible(false);
        if (isAddTable) {
            containerLeft = new BeanItemContainer(CustomerDTO.class);
            tblLeft = filterPanel.getMainTable();
            tableUtils = new TableUtils();
            tableUtils.generateColumn(tblLeft);
            tblLeft.setColumnExpandRatio(Constants.CUSTOMER.NAME, 3);
            tblLeft.setColumnExpandRatio(Constants.CUSTOMER.CODE, 1);
            tblLeft.setColumnWidth(Constants.CHECKBOX_COLUMN, 40);
            CommonFunctionTableFilter.initTable(filterPanel, headerCustLeft, containerLeft, captionCustTable, tblSize, langCust);
            tblLeft.setColumnHeader(Constants.CHECKBOX_COLUMN, "");
        } else {
            containerRight = new BeanItemContainer(MapStaffCustomerDTO.class);
            if (!DataUtil.isListNullOrEmpty(lstMapStaffCustomerDTOs)) {
                containerRight.addAll(lstMapStaffCustomerDTOs);
            }
            tblRight = filterPanel.getMainTable();
            tblRight.addGeneratedColumn("delete", new CustomTable.ColumnGenerator() {

                @Override
                public Object generateCell(final CustomTable source, final Object itemId, Object columnId) {
                    final MapStaffCustomerDTO sdto = (MapStaffCustomerDTO) itemId;
                    if (DataUtil.isStringNullOrEmpty(sdto.getMapId())) {
                        Button btnCancel = new Button(new ThemeResource(Constants.ICON.CANCEL));
                        btnCancel.setDescription(BundleUtils.getString("common.button.cancel"));
                        btnCancel.addStyleName(Constants.ICON.V_LINK);
                        btnCancel.addClickListener(new Button.ClickListener() {

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                source.removeItem(itemId);
                                tblRight.resetPage();
                            }
                        });
                        return btnCancel;
                    } else {
                        Button btnDelete = new Button(new ThemeResource(Constants.ICON.DELETE));
                        btnDelete.setDescription(BundleUtils.getString("common.button.delete"));
                        btnDelete.addStyleName(Constants.ICON.V_LINK);
                        btnDelete.addClickListener(new Button.ClickListener() {

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                ConfirmDialog.show(UI.getCurrent(), BundleUtils.getString("delete.item.title"), BundleUtils.getString("delete.item.body"),
                                        BundleUtils.getString("yes"), BundleUtils.getString("no"), new ConfirmDialog.Listener() {
                                    @Override
                                    public void onClose(ConfirmDialog dialog) {
                                        if (dialog.isConfirmed()) {
                                            String returnValue = WSMapStaffCustomer.deleteMapStaffCustomer(sdto.getMapId());
                                            if (returnValue.equalsIgnoreCase(Constants.SUCCESS)) {
                                                tblRight.removeItem(itemId);
                                                tblRight.resetPage();
                                                Notification.show(BundleUtils.getString("actionSuccess"), Notification.Type.HUMANIZED_MESSAGE);
                                            } else {
                                                Notification.show(BundleUtils.getString("actionFail"), Notification.Type.ERROR_MESSAGE);
                                            }
                                        }

                                    }
                                });
                            }
                        });
                        return btnDelete;
                    }
                }
            });
//            tblRight.addGeneratedColumn("cancel", new CustomTable.ColumnGenerator() {
//
//                @Override
//                public Object generateCell(final CustomTable source, final Object itemId, Object columnId) {
//                    MapStaffCustomerDTO sdto = (MapStaffCustomerDTO) itemId;
//                    if (!DataUtil.isStringNullOrEmpty(sdto.getMapId())) {
//                        return "";
//                    }
//                    Button btnCancel = new Button(new ThemeResource(Constants.ICON.CANCEL));
//                    btnCancel.setDescription(BundleUtils.getString("common.button.cancel"));
//                    btnCancel.addStyleName(Constants.ICON.V_LINK);
//                    btnCancel.addClickListener(new Button.ClickListener() {
//
//                        @Override
//                        public void buttonClick(Button.ClickEvent event) {
//                            source.removeItem(itemId);
//                            tblRight.resetPage();
//                        }
//                    });
//                    return btnCancel;
//                }
//            });
            tblRight.setColumnWidth("delete", 100);
//            tblRight.setColumnWidth("cancel", 60);
            tblRight.setColumnExpandRatio("custName", 3);
            tblRight.setColumnExpandRatio("custCode", 1);
            CommonFunctionTableFilter.initTable(filterPanel, headerCustRight, containerRight, captionCustTableView, tblSize, langCust);
            tblRight.setColumnHeader("delete", BundleUtils.getString("common.button.delete") + "/" + BundleUtils.getString("common.button.cancel"));
//            tblRight.setColumnHeader("cancel", "");
        }
    }

    //Khoi tao cac listener cho cac thanh phan cua man hinh them nhan vien quan ly
    private void addActionStaffListener() {
        //Nut huy bo
        btnCancel.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                dialogAddMapStaffCustomer.close();
            }
        });
        //Nut tim kiem
        btnSearch.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                StaffDTO staffDTO = getStaffDTOs();
                getListStaffs(staffDTO);
                setData2TableStaffLeft(lstStaffDTOs);
                dialogAddMapStaffCustomer.center();
                CommonUtils.enableButtonAfterClick(event);
            }
        });

        //Nut them
        btnAdd.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Collection collection = (Collection) tblLeft.getValue();
                List<StaffDTO> lst = new ArrayList<>();
                lst.addAll(collection);
                //Them vao bang moi
                List<MapStaffCustomerDTO> lstRight = new ArrayList<>();
                Collection rightCollection = (Collection) tblRight.getItemIds();
                lstRight.addAll(rightCollection);
                MapStaffCustomerDTO mapDTO;
                if (!DataUtil.isListNullOrEmpty(lst)) {
                    //Xoa du lieu cua bang ben trai va them du lieu cho bang ben phai
                    for (StaffDTO o : lst) {
                        tblLeft.removeItem(o);
                        mapDTO = new MapStaffCustomerDTO();
                        mapDTO.setCustTaxCode(customerDTO.getTaxCode());
                        mapDTO.setCustName(customerDTO.getName());
                        mapDTO.setStaffId(o.getStaffId());
                        mapDTO.setStaffName(o.getName());
                        mapDTO.setStaffCode(o.getCode());
                        mapDTO.setStaffType(o.getStaffType());
                        lstRight.add(mapDTO);
                    }
                } else {
                    //Thong bao vui long chon 1 ban ghi
                    CommonUtils.showChoseOne();
                }

                setData2TableRight(lstRight, true);
                CommonUtils.enableButtonAfterClick(event);
            }
        });
        //Button save
        btnSave.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                isSaveSuccess = false;
                Collection lstSave = (Collection) tblRight.getItemIds();

                lstAddStaffs = new ArrayList();
                MapStaffCustomerDTO mapStaffCustomerDTO;
                for (Object dTO : lstSave) {
                    mapStaffCustomerDTO = (MapStaffCustomerDTO) dTO;
                    if (DataUtil.isStringNullOrEmpty(mapStaffCustomerDTO.getMapId())) {
                        lstAddStaffs.add(mapStaffCustomerDTO);
                    }
                }
                if (DataUtil.isListNullOrEmpty(lstAddStaffs)) {
                    //Thong bao phai chon nhan vien truoc khi luu 
                    CommonMessages.showWarningMessage(BundleUtils.getString("map.staff.customer.warning.add.first"));
                } else {
                    String insertResult = WSMapStaffCustomer.insertOrUpdateListMapStaffCustomer(lstAddStaffs);
                    if (insertResult.equalsIgnoreCase(Constants.SUCCESS)) {
                        //Thong bao them thanh cong 
                        CommonMessages.showMessageInsertSuccess("map.staff.customer.title");
                        //Dong dialog                    
                        isSaveSuccess = true;
                        dialogAddMapStaffCustomer.close();
                    } else {
                        //Thong bao them that bai
                        CommonMessages.showInsertFail(BundleUtils.getString("map.staff.customer.title"));
                    }
                }
                CommonUtils.enableButtonAfterClick(event);
            }
        });

    }

    //Khoi tao cac listener cho cac thanh phan cua man hinh them khach hang
    private void addActionCustListener() {
        //Nut huy bo
        btnCancel.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                dialogAddMapStaffCustomer.close();
            }
        });
        //Nut tim kiem
        btnSearch.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                List<ConditionBean> lstConditionBeans = getCustConditionBeans();
                getListCusts(lstConditionBeans);
                setData2TableCustLeft(lstCustDTOs);
                dialogAddMapStaffCustomer.center();
                CommonUtils.enableButtonAfterClick(event);
            }
        });

        //Nut them
        btnAdd.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Collection collection = (Collection) tblLeft.getValue();
                List<CustomerDTO> lst = new ArrayList<>();
                lst.addAll(collection);
                //Them vao bang moi
                List<MapStaffCustomerDTO> lstRight = new ArrayList<>();
                Collection rightCollection = (Collection) tblRight.getItemIds();
                lstRight.addAll(rightCollection);
                MapStaffCustomerDTO mapDTO;
                if (!DataUtil.isListNullOrEmpty(lst)) {
                    //Xoa du lieu cua bang ben trai va them du lieu cho bang ben phai
                    for (CustomerDTO o : lst) {
                        tblLeft.removeItem(o);
                        mapDTO = new MapStaffCustomerDTO();
                        mapDTO.setCustTaxCode(o.getTaxCode());
                        mapDTO.setCustName(o.getName());
                        mapDTO.setStaffId(staffDTO.getStaffId());
                        mapDTO.setStaffName(staffDTO.getName());
                        mapDTO.setStaffCode(staffDTO.getCode());
                        mapDTO.setStaffType(staffDTO.getStaffType());
                        lstRight.add(mapDTO);
                    }
                } else {
                    //Thong bao vui long chon 1 ban ghi
                    CommonUtils.showChoseOne();
                }

                setData2TableRight(lstRight, false);
                CommonUtils.enableButtonAfterClick(event);
            }
        });
        //Button save
        btnSave.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                isSaveSuccess = false;
                Collection lstSave = (Collection) tblRight.getItemIds();
                lstAddCusts = new ArrayList();
                MapStaffCustomerDTO mapStaffCustomerDTO;
                for (Object dTO : lstSave) {
                    mapStaffCustomerDTO = (MapStaffCustomerDTO) dTO;
                    if (DataUtil.isStringNullOrEmpty(mapStaffCustomerDTO.getMapId())) {
                        lstAddCusts.add(mapStaffCustomerDTO);
                    }
                }
                if (DataUtil.isListNullOrEmpty(lstAddCusts)) {
                    //Thong bao phai chon nhan vien truoc khi luu 
                    CommonMessages.showWarningMessage(BundleUtils.getString("map.staff.customer.warning.add.first.customer"));
                } else {
                    String insertResult = WSMapStaffCustomer.insertOrUpdateListMapStaffCustomer(lstAddCusts);
                    if (insertResult.equalsIgnoreCase(Constants.SUCCESS)) {
                        //Thong bao them thanh cong 
                        CommonMessages.showMessageInsertSuccess("map.staff.customer.onlyView.caption");
                        //Dong dialog                    
                        isSaveSuccess = true;
                        dialogAddMapStaffCustomer.close();
                    } else {
                        //Thong bao them that bai
                        CommonMessages.showInsertFail(BundleUtils.getString("map.staff.customer.onlyView.caption"));
                    }
                }
                CommonUtils.enableButtonAfterClick(event);
            }
        });

    }

    //Tim kiem nhan vien 
    private void getListStaffs(StaffDTO staff) {
        StaffDTO sdto;
        if (staff == null) {
            sdto = new StaffDTO();
            sdto.setStatus(Constants.ACTIVE);
        } else {
            sdto = staff;
            sdto.setStatus(Constants.ACTIVE);
        }
        List<StaffDTO> lst;
        try {
            lst = WSStaff.getListStaffDTO(sdto, Constants.INT_0, Constants.INT_100, Constants.ASC, Constants.STAFF.CODE);
            if (DataUtil.isListNullOrEmpty(lst)) {
                lst = new ArrayList<>();
            }
        } catch (Exception e) {
            lst = new ArrayList<>();
        }
        lstStaffDTOs = new ArrayList<>();
        //Loc nhung danh sach da co 
        boolean isContain;
        for (StaffDTO dTO : lst) {
            isContain = false;
            if (!DataUtil.isListNullOrEmpty(lstMapStaffCustomerDTOs)) {
                for (MapStaffCustomerDTO mscdto : lstMapStaffCustomerDTOs) {
                    if (dTO.getStaffId().equals(mscdto.getStaffId())) {
                        isContain = true;
                        break;
                    }
                }
            }
            if (!isContain) {
                lstStaffDTOs.add(dTO);
            }
        }
    }

    //Tim kiem khach hang 
    private void getListCusts(List<ConditionBean> lstConditionBeans) {
        List<CustomerDTO> lst;
        try {
            lst = WSCustomer.getListCustomerByCondition(lstConditionBeans, Constants.INT_0, Constants.INT_100, Constants.ASC, Constants.CUSTOMER.CODE);
            if (DataUtil.isListNullOrEmpty(lst)) {
                lst = new ArrayList<>();
            }
        } catch (Exception e) {
            lst = new ArrayList<>();
        }
        lstCustDTOs = new ArrayList<>();
        //Loc nhung danh sach da co 
        boolean isContain;
        for (CustomerDTO dTO : lst) {
            isContain = false;
            if (!DataUtil.isListNullOrEmpty(lstMapStaffCustomerDTOs)) {
                for (MapStaffCustomerDTO mscdto : lstMapStaffCustomerDTOs) {
                    if (dTO.getTaxCode().equals(mscdto.getCustTaxCode())) {
                        isContain = true;
                        break;
                    }
                }
            }
            if (!isContain) {
                lstCustDTOs.add(dTO);
            }
        }
    }

    //Truyen du lieu cho bang nhan vien
    private void setData2TableStaffLeft(List<StaffDTO> lstStaffDTOs) {

        if (containerLeft == null) {
            containerLeft = new BeanItemContainer(StaffDTO.class);
        } else {
            containerLeft.removeAllItems();
        }
        if (!DataUtil.isListNullOrEmpty(lstStaffDTOs)) {
            containerLeft.addAll(lstStaffDTOs);
        }
        CommonFunctionTableFilter.refreshTable(leftPanelTable, headerStaffLeft, containerLeft);
    }

    //Truyen du lieu cho bang khach hang
    private void setData2TableCustLeft(List<CustomerDTO> lstCustomerDTOs) {

        if (containerLeft == null) {
            containerLeft = new BeanItemContainer(CustomerDTO.class);
        } else {
            containerLeft.removeAllItems();
        }
        if (!DataUtil.isListNullOrEmpty(lstCustomerDTOs)) {
            containerLeft.addAll(lstCustomerDTOs);
        }
        CommonFunctionTableFilter.refreshTable(leftPanelTable, headerCustLeft, containerLeft);
    }

    private void setData2TableRight(List<MapStaffCustomerDTO> lstStaffDTOs, boolean isCustInfo) {
        if (containerRight == null) {
            containerRight = new BeanItemContainer(MapStaffCustomerDTO.class);
        } else {
            containerRight.removeAllItems();
        }
        if (!DataUtil.isListNullOrEmpty(lstStaffDTOs)) {
            containerRight.addAll(lstStaffDTOs);
        }
        if (isCustInfo) {
            CommonFunctionTableFilter.refreshTable(rightPanelTable, headerStaffRight, containerRight);
        } else {
            CommonFunctionTableFilter.refreshTable(rightPanelTable, headerCustRight, containerRight);
        }
    }

    //Lay thong tin tim kiem tu form tim kiem 
    protected StaffDTO getStaffDTOs() {
        StaffDTO sdto = new StaffDTO();
        sdto.setStatus(Constants.ACTIVE);
        if (!DataUtil.isStringNullOrEmpty(dialogAddMapStaffCustomer.getTxtStaffCode().getValue())) {
            sdto.setCode(DataUtil.getStringEscapeHTML4(dialogAddMapStaffCustomer.getTxtStaffCode().getValue()));
        }
        if (!DataUtil.isStringNullOrEmpty(dialogAddMapStaffCustomer.getTxtStaffName().getValue())) {
            sdto.setName(DataUtil.getStringEscapeHTML4(dialogAddMapStaffCustomer.getTxtStaffName().getValue()));
        }
        if (!DataUtil.isStringNullOrEmpty(dialogAddMapStaffCustomer.getTxtEmail().getValue())) {
            sdto.setEmail(DataUtil.getStringEscapeHTML4(dialogAddMapStaffCustomer.getTxtEmail().getValue()));
        }
        if (!DataUtil.isStringNullOrEmpty(dialogAddMapStaffCustomer.getTxtTelephone().getValue())) {
            sdto.setTelNumber(DataUtil.getStringEscapeHTML4(dialogAddMapStaffCustomer.getTxtTelephone().getValue()));
        }
        AppParamsDTO staffType = (AppParamsDTO) dialogAddMapStaffCustomer.getCboStaffType().getValue();
        if (!DataUtil.isStringNullOrEmpty(staffType.getParCode())) {
            sdto.setStaffType(staffType.getParCode());
        }
        return sdto;
    }

    //Lay thong tin tim kiem tu form tim kiem 
    protected List<ConditionBean> getCustConditionBeans() {
        List<ConditionBean> lstConditionBeans = new ArrayList<>();
        ConditionBean condition;
        if (!DataUtil.isStringNullOrEmpty(dialogAddMapStaffCustomer.getTxtStaffCode().getValue())) {
            String code = DataUtil.getStringEscapeHTML4(dialogAddMapStaffCustomer.getTxtStaffCode().getValue());
            condition = new ConditionBean(Constants.CUSTOMER.CODE, code, ConditionBean.Operator.NAME_LIKE, ConditionBean.Type.STRING);
            lstConditionBeans.add(condition);
        }
        if (!DataUtil.isStringNullOrEmpty(dialogAddMapStaffCustomer.getTxtStaffName().getValue())) {
            String name = DataUtil.getStringEscapeHTML4(dialogAddMapStaffCustomer.getTxtStaffName().getValue());
            condition = new ConditionBean(Constants.CUSTOMER.NAME, name, ConditionBean.Operator.NAME_LIKE, ConditionBean.Type.STRING);
            lstConditionBeans.add(condition);
        }
        if (!DataUtil.isStringNullOrEmpty(dialogAddMapStaffCustomer.getTxtEmail().getValue())) {
            String email = DataUtil.getStringEscapeHTML4(dialogAddMapStaffCustomer.getTxtEmail().getValue());
            condition = new ConditionBean(Constants.CUSTOMER.EMAIL, email, ConditionBean.Operator.NAME_LIKE, ConditionBean.Type.STRING);
            lstConditionBeans.add(condition);
        }
        if (!DataUtil.isStringNullOrEmpty(dialogAddMapStaffCustomer.getTxtTelephone().getValue())) {
            String telNumber = DataUtil.getStringEscapeHTML4(dialogAddMapStaffCustomer.getTxtTelephone().getValue());
            condition = new ConditionBean(Constants.CUSTOMER.TELNUMBER, telNumber, ConditionBean.Operator.NAME_LIKE, ConditionBean.Type.STRING);
            lstConditionBeans.add(condition);
        }
        condition = new ConditionBean(Constants.CUSTOMER.STATUS, "6", ConditionBean.Operator.NAME_NOT_EQUAL, ConditionBean.Type.STRING);
        lstConditionBeans.add(condition);
        return lstConditionBeans;
    }

    //Getter and Setter
    public Button getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(Button btnSave) {
        this.btnSave = btnSave;
    }

    public List<MapStaffCustomerDTO> getLstAddStaffs() {
        return lstAddStaffs;
    }

    public void setLstAddStaffs(List<MapStaffCustomerDTO> lstAddStaffs) {
        this.lstAddStaffs = lstAddStaffs;
    }

    public boolean isIsSaveSuccess() {
        return isSaveSuccess;
    }

    public void setIsSaveSuccess(boolean isSaveSuccess) {
        this.isSaveSuccess = isSaveSuccess;
    }

}
