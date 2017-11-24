/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.list.ui.DialogCreateStaff;
import com.cms.common.ws.WSAppParams;
import com.cms.login.ws.WSDepartment;
import com.cms.login.ws.WSStaff;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
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
import com.cms.utils.ShortcutUtils;
import com.google.common.collect.Lists;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import com.vwf5.base.utils.DateUtil;
import java.text.ParseException;
import java.util.ArrayList;
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
public final class DialogCreateStaffController {

    String action;
    String message;
    DialogCreateStaff dialogCreateStaff;
    ListStaffController staffController;
    StaffDTO staffDTO;
    StaffDTO staffDTOClone;
    DepartmentDTO departmentDTO;
    MappingCombobox comboDeptTopLevel;
    CustomPageTableFilter<IndexedContainer> tblStaff;
    //list department
    List<DepartmentDTO> lstCboSuperiorUnit = null;//fill f9
    List<AppParamsDTO> listStaffType = Lists.newArrayList();
    List<AppParamsDTO> listStatusType = Lists.newArrayList();
//    List<StockDTO> lstStockDTOs = Lists.newArrayList();
    public Map<String, AppParamsDTO> mapNameFromKey = new HashMap<>();
    private ComboComponent comboComponent = new ComboComponent();
    MappingCombobox comboStockMapping;
    ComboBox cboStatus;
    ComboBox cboStaffType;
    //ket qua tra ve
    ResultDTO resultDTO;
    ResultDTO result;//result insert staffstock
//    StockDTO stockDTO;
//    StaffStockDTO ssdto;
    String deptId;
    String staffId;
    String staffStockId;
    String resultMessage;
//    UserToken userToken;
    String email;
    String phone;
    //duyot add 21/07
    TextField txtEmpCode;
    String oldDept;
//    StaffInforDTO staffInforDTO;
    //NgocND6
    List<ConditionBean> lstCondition = new ArrayList<>();
    List<ConditionBean> lstConditionMessage = new ArrayList<>();
    List<ConditionBean> lstConditionStaff = new ArrayList<>();
//    List<MapStaffGoodsDTO> listMapStaffGoods = Lists.newArrayList();
    List<StaffDTO> listStaff = Lists.newArrayList();
//    List<MapStaffGoodsDTO> listUpdateMapStaffGoods = Lists.newArrayList();
//    List<MessagesDTO> listUpdateMessage = Lists.newArrayList();
//    List<MessagesDTO> listMessage = Lists.newArrayList();
    String staffEmailCheck;

    public DialogCreateStaffController(final DialogCreateStaff dialogCreateStaff, final String action, final CustomPageTableFilter<IndexedContainer> table) {
        this.action = action;
        this.dialogCreateStaff = dialogCreateStaff;
        this.comboDeptTopLevel = dialogCreateStaff.getComboDeptTopLevel();
        this.comboStockMapping = dialogCreateStaff.getComboStock();
        this.cboStatus = dialogCreateStaff.getCbxStatus();
        this.cboStaffType = dialogCreateStaff.getCbxStaffType();
        this.tblStaff = table;
        this.staffDTO = dialogCreateStaff.getStaffDTO();
        staffDTOClone = staffDTO.copy();
        this.departmentDTO = dialogCreateStaff.getDeptDTO();
//        this.userToken = userToken;
        this.txtEmpCode = dialogCreateStaff.getTxtStaffCode();
        oldDept = staffDTO.getDeptId();
        //neu la them moi -> disable combo phong ban
        if (action.equalsIgnoreCase(Constants.ADD)) {
            comboDeptTopLevel.getCodeCombo().setEnabled(false);
            comboDeptTopLevel.getNameCombo().setEnabled(false);
        }
        //Lay thong tin nguoi dang nhap
//        staffInforDTO = (StaffInforDTO) VaadinSession.getCurrent().getAttribute(Constants.CURRENT_STAFF_INFO);

        //xu li nut save
        ShortcutUtils.setShortcutKey(dialogCreateStaff.getBtnSave());
        ShortcutUtils.setQuit(dialogCreateStaff.getBtnCancel());
//        ShortcutUtils.doTextChangePhoneNumber(dialogCreateStaff.getTxtPhoneNumber());
//        ShortcutUtils.doTextTrim(dialogCreateStaff.getTxtEmail());
//        ShortcutUtils.doTextTrim(dialogCreateStaff.getTxtPhoneNumber());
        //NgocND6 goi khi khoi tao
//        getListGoodsStaffManagement();
//        getListMessageByStaffEmail();
        dialogCreateStaff.getBtnSave().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!isNullValue()) {//neu khong trong

                    if (action.equalsIgnoreCase(Constants.EDIT)) {
                        if (!validateField()) {
                            return;
                        }
                        message = updateEmployee();
                        if (message == null) {
                            return;
                        }
                        if (message.equalsIgnoreCase(Constants.SUCCESS)) {

                            mapCode2Name(staffDTO);
                            //kiem tra -> neu thay doi phong ban -> chuyen
                            if (!oldDept.equalsIgnoreCase(staffDTO.getDeptId())) {
                                tblStaff.removeItem(staffDTO);
                            }
                            tblStaff.refreshRowCache();
                            tblStaff.resetPage();
                            CommonUtils.showUpdateSuccess(BundleUtils.getString("staff"));
                            dialogCreateStaff.close();
//                            updateStaffInfoMapStaffGoods();
//                            updateMessages();
                            //xoa khoi bang ds nhan vien
                        } else {
                            CommonUtils.showUpdateSuccess(BundleUtils.getString("staff"));
                        }
                    }
                    if (action.equalsIgnoreCase(Constants.COPY)) {
                        if (!validateField()) {
                            return;
                        }
                        resultDTO = createEmployee();
                        if (resultDTO == null) {
                            return;
                        }
                        if (!DataUtil.isStringNullOrEmpty(resultDTO.getMessage()) 
                                && Constants.SUCCESS.equalsIgnoreCase(resultDTO.getMessage())) {
                            mapCode2Name(staffDTO);
                            staffDTO.setStaffId(resultDTO.getId());
                            CommonUtils.reloadTable(tblStaff, staffDTO);
                            CommonUtils.showCopySuccess(BundleUtils.getString("staff"));
                            dialogCreateStaff.getTxtStaffCode().setValue("");
                        } else {
                            //Check record duplicate in database table
                            CommonUtils.showMessageDuplicate(resultDTO);
                        }
                    }
                    if (action.equalsIgnoreCase(Constants.ADD)) {
                        if (!validateField()) {
                            return;
                        }
                        resultDTO = createEmployee();
                        if (resultDTO == null) {
                            return;
                        }
                        if (!DataUtil.isStringNullOrEmpty(resultDTO.getMessage()) 
                                && Constants.SUCCESS.equalsIgnoreCase(resultDTO.getMessage())) {
                            staffId = resultDTO.getId();//xu li sau
                            staffDTO.setStaffId(staffId);
                            mapCode2Name(staffDTO);
                            CommonUtils.reloadTable(tblStaff, staffDTO);
                            tblStaff.addItem(staffDTO);
                            CommonUtils.showInsertSuccess(BundleUtils.getString("staff"));
                            dialogCreateStaff.close();

                        } else {
                            //Check record duplicate in database table
                            CommonUtils.showMessageDuplicate(resultDTO);
                        }
                    }
                }
            }
        }
        );

        //xu li nut close
        dialogCreateStaff.getBtnCancel()
                .addClickListener(new Button.ClickListener() {

                    @Override
                    public void buttonClick(Button.ClickEvent event
                    ) {
                        dialogCreateStaff.close();
                    }
                }
                );
    }

    //khoi tao
    public void init(StaffDTO sdto, String deptId, DepartmentDTO deptDTO) {
        this.deptId = deptId;
        this.departmentDTO = deptDTO;
        this.staffDTO = sdto;
        fillDataComboStatus();
        fillDataStaffType();
        initCombobox();
        fillDataStaff(staffDTO, deptDTO);
        //

    }

    //=======================
    public void fillDataComboStatus() {
        ComboComponent comboComponent = new ComboComponent();
        AppParamsDTO appParamsDTO = new AppParamsDTO();
        appParamsDTO.setParType(Constants.APP_PARAMS.COMMON_STATUS);
        listStatusType = getListStatusType(appParamsDTO);
        comboComponent.fillDataCombo(cboStatus, "", "1", listStatusType, Constants.APP_PARAMS.COMMON_STATUS);
    }

    public void fillDataStaffType() {
        ComboComponent comboStaffType = new ComboComponent();
        AppParamsDTO appParamsDTO = new AppParamsDTO();
        appParamsDTO.setParType(Constants.DEPARTMENT.STAFF_TYPE);
        listStaffType = getListStaffType(appParamsDTO);
        String staffTypeDefault = listStaffType.get(0).getParCode();
        comboStaffType.fillDataCombo(cboStaffType, "", staffTypeDefault, listStaffType, Constants.DEPARTMENT.STAFF_TYPE);
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
            ex.printStackTrace();
        }
        return listStatusType;
    }

    public List<AppParamsDTO> getListStaffType(AppParamsDTO appParamsDTO) {
        List<AppParamsDTO> listStaffTypeExacly = Lists.newArrayList();
        try {
            listStaffType = WSAppParams.getListAppParamsDTO(appParamsDTO, 0, Integer.MAX_VALUE, "desc", "parOrder");
            //Put danh sach trang thai khach hang vao map
            for (AppParamsDTO paramsDTO : listStaffType) {
                String key = paramsDTO.getParType().replace('_', '.').toLowerCase() + "." + paramsDTO.getParCode();
                paramsDTO.setDisplayName(BundleUtils.getString(key));
                mapNameFromKey.put(paramsDTO.getParCode(), paramsDTO);
                mapNameFromKey.put(paramsDTO.getParName(), paramsDTO);
                if (paramsDTO.getParType().equalsIgnoreCase(Constants.DEPARTMENT.STAFF_TYPE)) {
                    listStaffTypeExacly.add(paramsDTO);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listStaffTypeExacly;
    }

    public void getListDeptTopLevel() {
        departmentDTO = new DepartmentDTO();
        try {
            lstCboSuperiorUnit = WSDepartment.getListDepartmentDTO(departmentDTO, 0, 100, Constants.ASC, Constants.DEPARTMENT.CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (lstCboSuperiorUnit == null) {
            lstCboSuperiorUnit = Lists.newArrayList();
        }
    }

    public void initCombobox() {
        getListDeptTopLevel();
        if (lstCboSuperiorUnit != null) {
            comboDeptTopLevel.setValues(lstCboSuperiorUnit, Constants.DEPARTMENT.CODE, Constants.DEPARTMENT.NAME);
        }
    }

    //lay ds kho
//    public void getListStock() {
//        stockDTO = new StockDTO();
//        try {
//            lstStockDTOs = WSStock.getListStockDTO(stockDTO, 0, Integer.MAX_VALUE, "asc", "code");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (lstStockDTOs == null) {
//            lstStockDTOs = Lists.newArrayList();
//        }
//    }
    //khoi tao f9
//    public void initComboboxStock() {
//        getListStock();
//        comboStockMapping.setValues(lstStockDTOs, Constants.STOCK.CODE, Constants.STOCK.NAME);
//    }
    public void format(StaffDTO staff) {
        staff.setPassword(DataUtil.md5(BundleUtils.getStringCas(Constants.PASSWORD_DEFAULT)));
    }

    //Update staff
    public String updateEmployee() {
        StaffDTO staff;
        String tempEmail = "";
        String inputEmail = "";
        try {
            staff = getValueForm();
            //check valid info here
            staffDTO.setCode(staff.getCode());
            staffDTO.setName(staff.getName());
            staffDTO.setStaffType(staff.getStaffType());
            staffDTO.setStatus(staff.getStatus());
            staffDTO.setTelNumber(staff.getTelNumber());
            //Thuc hien kiem tra khac null vs khac "" doi voi du lieu truoc khi cap nhat thi moi get va trim
            if (staffDTOClone.getEmail() != null && !staffDTOClone.getEmail().equalsIgnoreCase("")) {
                tempEmail = staffDTOClone.getEmail().trim();
            }
            //Tuong tu doi voi  du lieu cua truong email nhap vao tren form update
            if (staff.getEmail() != null && !staff.getEmail().equalsIgnoreCase("")) {
                inputEmail = staff.getEmail().trim();
            }
            String flag = "UPDATE";
            //So sanh gia tri ban dau voi gia tri khi save vao db neu = nhau nghia la khoong co thay doi gi va bo qua buoc set gia tri
            if (!tempEmail.equalsIgnoreCase("") || !inputEmail.equalsIgnoreCase("")) {
                if (!tempEmail.equalsIgnoreCase(inputEmail)) {
                    //Sau khi check pass buoc tren thuc hien validate tiep theo voi gia tri moi neu da ton tai trong db roi thi return va day ra thong bao
                    if (!checkDuplicateEmail(flag, inputEmail)) {
                        Notification.show(BundleUtils.getString("staff.department.emailexist"), Notification.Type.WARNING_MESSAGE);
                        return null;
                        //Neu chua ton tai thi thuc hien lay gia tri moi
                    } else {
                        staffDTO.setEmail(staff.getEmail());
                    }
                }
            }
            staffDTO.setBirthDate(staff.getBirthDate());
            staffDTO.setDeptId(staff.getDeptId());
//            staffDTO.setUserNameLogging("Username");
            //staffDTO = staff;
            format(staffDTO);
            String value = staffDTO.getCode();
            boolean isValid = value.matches("^[a-zA-Z0-9-_]+$");
            if (!isValid) {
                txtEmpCode.focus();
                Notification.show(BundleUtils.getString("lb.deptstaff.emp.code") + " " + BundleUtils.getString("message.error.code.format"), Notification.Type.TRAY_NOTIFICATION);
                return null;
            } else {
                return WSStaff.updateStaff(staffDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param flag
     * @param emailVal
     * @return
     */
    public boolean checkDuplicateEmail(String flag, String emailVal) {
        getListStaff(flag, emailVal);
        if (listStaff != null && listStaff.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * Condition tim kiem voi email vua nhap Thuc hien set condition theo flag
     *
     * @param flag
     * @param emailVal
     * @return
     */
    public List<ConditionBean> listConditionStaff(String flag, String emailVal) {
        //Neu flag = Update thi se lay gia tri email tren form.
        if (flag.equalsIgnoreCase("UPDATE")) {
            StaffDTO staff = getValueForm();
            if (staff.getEmail() != null) {
                lstConditionStaff.add(new ConditionBean("email", staff.getEmail().trim(), ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
            }
            //Neu là Insert thi lay gia tri truyen vao la du lieu tren truong email cua form them moi
        } else {
            lstConditionStaff.add(new ConditionBean("email", emailVal, ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
        }

        return lstConditionStaff;
    }

    /**
     * Lay danh sach nhan vien de check email nhap vao da ton tai voi nhan vien
     * nao tren he thong chua
     *
     * @param flag
     * @param emailVal
     */
    public void getListStaff(String flag, String emailVal) {
        try {
            listConditionStaff(flag, emailVal);
            listStaff = WSStaff.getListStaffByCondition(lstConditionStaff, 0, Integer.MAX_VALUE, Constants.DESC, "staffId");
        } catch (Exception e) {
        }
    }

    /**
     * NgocND6 040416 Set dieu kien lay danh sach hang hoa duoc phan quyen tu
     * user transfer
     *
     * @return
     */
    public List<ConditionBean> listConditionSearchMSG() {
        if (staffDTO.getStaffId() != null) {
            lstCondition.add(new ConditionBean("staffId", staffDTO.getStaffId(), ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.NUMBER));
        }
        return lstCondition;
    }

    /**
     * NgocND6 040416 Lay danh sach hang hoa duoc phan quyen cua user staff
     * tranfer de thay doi theo email va sdt khi cap nhat thong tin cua staff
     */
//    public void getListGoodsStaffManagement() {
//        try {
//            listConditionSearchMSG();
//            listMapStaffGoods = WSMapStaffGoods.getListMapSGByCondition(lstCondition, 0, Integer.MAX_VALUE, Constants.DESC, "staffId");
//        } catch (Exception e) {
//        }
//    }
    /**
     * Get dieu kien tim kiem trong bang message theo email cua nhan vien va
     * trang thai
     *
     * @return
     */
//    public List<ConditionBean> listConditionMessage() {
//        if (staffDTO.getEmail() != null) {
//            //Chi lay nhung ban ghi chua gui di co trang thai = 0 trong bang message de cap nhat, khong cap nhat nhung ban ghi da duoc gui di co trang thai bang 1.
//            lstConditionMessage.add(new ConditionBean("status", "0", ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
//            //Them dieu kien tim kiem chinh bang email cua ban ghi dang chon.
//            lstConditionMessage.add(new ConditionBean("email", staffDTO.getEmail().trim(), ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
//        }
//        return lstConditionMessage;
//    }
    /**
     * Lay danh sach dua tren dieu kien tim kiem da put, neu tim kiem trong bang
     * messages ton tai ban ghi co dieu kien tim kiem nhu tren thi thuc hien lay
     * ra danh sach
     */
//    public void getListMessageByStaffEmail() {
//        try {
//            listConditionMessage();
//            listMessage = WSMessages.getListMessagesByCondition(lstConditionMessage, 0, Integer.MAX_VALUE, Constants.DESC, "email");
//        } catch (Exception e) {
//        }
//    }
    /**
     * Dong thoi cap nhat bang message de gui email, tin nhan khi co su thay doi
     * thong tin tu nhan vien
     *
     * @return
     */
//    public String updateMessages() {
//        String resultmes = "";
//        StaffDTO staff = getValueForm();
//        if (listMessage != null && listMessage.size() > 0) {
//            for (MessagesDTO mdto : listMessage) {
//                mdto.setEmail(staff.getEmail());
//                listUpdateMessage.add(mdto);
//            }
//            resultmes = WSMessages.insertOrUpdateMessages(listUpdateMessage);
//        }
//        return resultmes;
//    }
    /**
     * NgocND6 040416 Update staff info to table map_staff_goods when have
     * change info in table staff
     *
     * @return
     */
//    public String updateStaffInfoMapStaffGoods() {
//        String resultupdate = "";
//        StaffDTO staff = getValueForm();
//        listUpdateMapStaffGoods.clear();
//        if (listMapStaffGoods != null && listMapStaffGoods.size() > 0) {
//            for (MapStaffGoodsDTO msgdto : listMapStaffGoods) {
//                msgdto.setStaffPhone(staff.getTelNumber());
//                msgdto.setStaffEmail(staff.getEmail());
//                listUpdateMapStaffGoods.add(msgdto);
//            }
//            resultupdate = WSMapStaffGoods.insertOrUpdateListMapStaffGoods(listUpdateMapStaffGoods);
//        }
//        return resultupdate;
//    }
    //End NgocND6 050416
    //Tao moi staff
    public ResultDTO createEmployee() {
        StaffDTO staff;
        try {
            staff = getValueForm();
            String emailVal = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateStaff.getTxtEmail().getValue().trim()));
            String flag = "INSERT";
            //Truyen flag va gia tri lay tu truong email tren form vao
            if (!checkDuplicateEmail(flag, emailVal)) {
                dialogCreateStaff.getTxtPhoneNumber().focus();
                Notification.show(BundleUtils.getString("staff.department.emailexist"), Notification.Type.WARNING_MESSAGE);
                return null;
            }
            staff.setStaffId(null);
            staffDTO = staff;
            //Them password mac dinh
            format(staffDTO);
            //check valid staff code here
            //check valid info here
            String value = staffDTO.getCode();
            boolean isValid = value.matches("^[a-zA-Z0-9-_]+$");
            if (!isValid) {
                txtEmpCode.focus();
                Notification.show(BundleUtils.getString("lb.deptstaff.emp.code") + " " + BundleUtils.getString("message.error.code.format"), Notification.Type.TRAY_NOTIFICATION);
                return null;
            } else {
                return WSStaff.insertStaff(staff);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //them stock cho staff
//    public ResultDTO addStockForStaff(StaffStockDTO staffStockDTO) {
//        try {
//            return WSStaffStock.insertStaffStock(staffStockDTO);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public String updateStockForStaff(StaffStockDTO staffStockDTO) {
//        return WSStaffStock.updateStaffStock(staffStockDTO);
//    }
    //fill data
    public void fillDataStaff(StaffDTO staffDTO, DepartmentDTO departmentDTO) {

        if (action.equalsIgnoreCase(Constants.EDIT)) {
            staffId = staffDTO.getStaffId();
//            staffStockId = staffDTO.getStaffStockId();
            dialogCreateStaff.getTxtStaffCode().setValue(DataUtil.getStringNullOrZero(staffDTO.getCode()));
            ShortcutUtils.setVisibleTextfield(dialogCreateStaff.getTxtStaffCode(), false);
            dialogCreateStaff.getTxtStaffName().setValue(DataUtil.getStringNullOrZero(staffDTO.getName()));
//            dialogCreateStaff.getTxtVofficeAcc().setValue(DataUtil.getStringNullOrZero(staffDTO.getVofficeAccount()));
//            dialogCreateStaff.getTxtTtnsAcc().setValue(DataUtil.getStringNullOrZero(staffDTO.getTtnsAccount()));
//            dialogCreateStaff.getTxtOtherAcc().setValue(DataUtil.getStringNullOrZero(staffDTO.getOtherAccount()));
        }
        if (action.equalsIgnoreCase(Constants.COPY)) {
            ShortcutUtils.setFocus(dialogCreateStaff.getTxtStaffCode());
            dialogCreateStaff.getTxtStaffName().setValue(DataUtil.getStringNullOrZero(staffDTO.getName()));
//            dialogCreateStaff.getTxtVofficeAcc().setValue(DataUtil.getStringNullOrZero(staffDTO.getVofficeAccount()));
//            dialogCreateStaff.getTxtTtnsAcc().setValue(DataUtil.getStringNullOrZero(staffDTO.getTtnsAccount()));
//            dialogCreateStaff.getTxtOtherAcc().setValue(DataUtil.getStringNullOrZero(staffDTO.getOtherAccount()));
        }
        //Fill Department DTO
        if (action.equalsIgnoreCase(Constants.ADD)) {
            ShortcutUtils.setFocus(dialogCreateStaff.getTxtStaffCode());
            try {
                if (lstCboSuperiorUnit != null) {
                    for (DepartmentDTO staffDept : lstCboSuperiorUnit) {
                        if (departmentDTO.getDeptId().equalsIgnoreCase(staffDept.getDeptId())) {
                            dialogCreateStaff.getComboDeptTopLevel().getNameCombo().setValue(staffDept);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                for (DepartmentDTO staffDept : lstCboSuperiorUnit) {
                    if (staffDTO.getDeptId().equalsIgnoreCase(staffDept.getDeptId())) {
                        dialogCreateStaff.getComboDeptTopLevel().getNameCombo().setValue(staffDept);
                        break;
                    }
                }
//            fill stock mapping
//                for (StockDTO stock : lstStockDTOs) {
//                    if (staffDTO.getStockId().equalsIgnoreCase(stock.getStockId())) {
//                        dialogCreateStaff.getComboStock().getNameCombo().setValue(stock);
//                        break;
//                    }
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //neu la add thi fai truyen phong ban sang combo
        if (!DataUtil.isStringNullOrEmpty(staffDTO.getCode())) {
            try {
                String valueStatus = DataUtil.isStringNullOrEmpty(staffDTO.getStatus()) ? "" : mapNameFromKey.get(staffDTO.getStatus()).getParCode();
                comboComponent.fillDataCombo(dialogCreateStaff.getCbxStatus(), "", valueStatus, listStatusType, Constants.APP_PARAMS.COMMON_STATUS);
                String valueStaffType = DataUtil.isStringNullOrEmpty(staffDTO.getStaffType()) ? "" : mapNameFromKey.get(staffDTO.getStaffType()).getParCode();
                
                comboComponent.fillDataCombo(dialogCreateStaff.getCbxStaffType(), "", valueStaffType, listStaffType, Constants.DEPARTMENT.STAFF_TYPE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dialogCreateStaff.getTxtPhoneNumber().setValue(DataUtil.getStringNullOrZero(staffDTO.getTelNumber()));
        dialogCreateStaff.getTxtEmail().setValue(DataUtil.getStringNullOrZero(staffDTO.getEmail()));
        try {
            dialogCreateStaff.getPdfBirthDate().setValue(DateUtil.string2Date(staffDTO.getBirthDate()));
        } catch (ParseException ex) {
            Logger.getLogger(DialogCreateStaffController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StaffDTO getValueForm() {
        StaffDTO temp = new StaffDTO();
        String empCode;
        String empName;
        String empDept = "";
        String empType = "";
        String empStatus = "";
        String empPhone;
        String empEmail;
        String empBirthDate;
        String stock = "";
        String vOfficeAcc = "";
        String tnnsAcc = "";
        String otherAcc = "";

        empCode = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateStaff.getTxtStaffCode().getValue().trim()));

        empName = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateStaff.getTxtStaffName().getValue().trim()));
        empPhone = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateStaff.getTxtPhoneNumber().getValue().trim()));
        empEmail = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateStaff.getTxtEmail().getValue().trim()));
//        vOfficeAcc = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateStaff.getTxtVofficeAcc().getValue().trim()));
//        tnnsAcc = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateStaff.getTxtTtnsAcc().getValue().trim()));
//        otherAcc = DataUtil.getStringNullOrZero(DataUtil.getStringEscapeHTML4(dialogCreateStaff.getTxtOtherAcc().getValue().trim()));
        empBirthDate = DateUtil.date2ddMMyyyyString(dialogCreateStaff.getPdfBirthDate().getValue());
        if (dialogCreateStaff.getCbxStatus().getValue() != null) {
            AppParamsDTO appParamsStatus = (AppParamsDTO) dialogCreateStaff.getCbxStatus().getValue();
            empStatus = DataUtil.getStringNullOrZero(appParamsStatus.getParCode());
        }
        if (dialogCreateStaff.getCbxStaffType().getValue() != null) {
            AppParamsDTO appParamsEmpType = (AppParamsDTO) dialogCreateStaff.getCbxStaffType().getValue();
            empType = DataUtil.getStringNullOrZero(appParamsEmpType.getParCode());
        }
        if (dialogCreateStaff.getComboDeptTopLevel().codeCombo.getValue() != null && dialogCreateStaff.getComboDeptTopLevel().nameCombo.getValue() != null) {
            departmentDTO = (DepartmentDTO) dialogCreateStaff.getComboDeptTopLevel().getNameCombo().getValue();
            empDept = DataUtil.getStringNullOrZero(departmentDTO.getDeptId());
        }
        temp.setCode(empCode);
        temp.setName(empName);
        temp.setStaffType(empType);
        temp.setStatus(empStatus);
        temp.setTelNumber(empPhone);
        temp.setEmail(empEmail);
        temp.setBirthDate(empBirthDate);
        temp.setDeptId(empDept);
//        temp.setStockId(stock);
//        temp.setVofficeAccount(vOfficeAcc);
//        temp.setTtnsAccount(tnnsAcc);
//        temp.setOtherAccount(otherAcc);
//        if (userToken != null) {
//            temp.setUserNameLogging(DataUtil.getStringNullOrZero("Username"));
//        }
        return temp;
    }

    //map code sang ten
    public StaffDTO mapCode2Name(StaffDTO staffDTO) {
        String deptId;
        deptId = staffDTO.getDeptId();
        DepartmentDTO departmentDTO;
        try {
            departmentDTO = WSDepartment.findDepartmentById(deptId);

//            if (!DataUtil.isStringNullOrEmpty(staffDTO.getStatus())) {
//                staffDTO.setStatusName(BundleUtils.getString("common.status." + staffDTO.getStatus()));
//            }
//            if (!DataUtil.isStringNullOrEmpty(staffDTO.getStaffType())) {
//                staffDTO.setStaffTypeName(BundleUtils.getString("staff.type." + staffDTO.getStaffType()));
//            }
//            if (deptId.equalsIgnoreCase(staffDTO.getDeptId())) {
//                staffDTO.setDeptName(departmentDTO.getName());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffDTO;
    }

    public void resetValue() {
        ShortcutUtils.setFocus(dialogCreateStaff.getTxtStaffCode());
        dialogCreateStaff.getTxtStaffCode().setValue("");
        dialogCreateStaff.getTxtStaffName().setValue("");
        if (action.equalsIgnoreCase(Constants.EDIT) || action.equalsIgnoreCase(Constants.COPY)) {
            dialogCreateStaff.getComboDeptTopLevel().codeCombo.setValue(null);
            dialogCreateStaff.getComboDeptTopLevel().nameCombo.setValue(null);
        } else {

        }
        dialogCreateStaff.getTxtPhoneNumber().setValue("");
        dialogCreateStaff.getTxtEmail().setValue("");
        dialogCreateStaff.getPdfBirthDate().setValue(null);
        staffDTO = new StaffDTO();
        fillDataComboStatus();
        fillDataStaffType();
    }

    public boolean isNullValue() {
        if (DataUtil.isStringNullOrEmpty(dialogCreateStaff.getTxtStaffCode().getValue().trim())) {
            dialogCreateStaff.getTxtStaffCode().focus();
            CommonUtils.showMessageRequired("lb.deptstaff.emp.code");
            return true;
        }
        if (DataUtil.isStringNullOrEmpty(dialogCreateStaff.getTxtStaffName().getValue().trim())) {
            dialogCreateStaff.getTxtStaffName().focus();
            CommonUtils.showMessageRequired("lb.deptstaff.emp.name");
            return true;
        }
        if (DataUtil.isStringNullOrEmpty(dialogCreateStaff.getCbxStatus().getValue())) {
            dialogCreateStaff.getCbxStatus().focus();
            CommonUtils.showMessageRequired("lb.deptstaff.common.status");
            return true;
        }

        return false;
    }

    public boolean validateField() {
        email = DataUtil.getStringEscapeHTML4(dialogCreateStaff.getTxtEmail().getValue().trim());
        phone = DataUtil.getStringEscapeHTML4(dialogCreateStaff.getTxtPhoneNumber().getValue().trim());
        if (!CommonValidator.isEmailValid(email)) {
            dialogCreateStaff.getTxtEmail().setRequiredError(BundleUtils.getString("common.error.email"));
            dialogCreateStaff.getTxtEmail().focus();
            return false;
        }
        if (!CommonValidator.isPhoneValid(phone)) {
            dialogCreateStaff.getTxtPhoneNumber().setRequiredError(BundleUtils.getString("common.error.phone"));
            dialogCreateStaff.getTxtPhoneNumber().focus();
            return false;
        }
        return true;
    }
}
