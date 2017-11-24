/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.cms.appparams.controller;

import com.cms.common.ws.WSAppParams;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomTable;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.AppParamsDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.ShortcutUtils;
import com.anphat.cms.appparams.ui.DialogCreateAppParams;
import com.google.common.collect.Lists;
import com.vwf5.base.dto.ResultDTO;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
//import viettel.passport.client.UserToken;

/**
 *
 * @author hungkv
 */
public class DialogCreateAppParamController {

    String action;
    String message;
    DialogCreateAppParams dialogCreateAppParams;
    AppParamsDTO appParamsDTO;
    CustomPageTableFilter<IndexedContainer> tblAppParam;
    List<AppParamsDTO> listStatusType = Lists.newArrayList();
//    List<StockDTO> lstStockDTOs = Lists.newArrayList();
    public Map<String, AppParamsDTO> mapNameFromKey = new HashMap<>();
    private ComboComponent comboComponent = new ComboComponent();
    private LinkedHashMap<String, CustomTable.Align> headerData = BundleUtils.getHeadersFilter("appparam.header");
    ComboBox cboStatus;
    //ket qua tra ve
    ResultDTO resultDTO;
    String lblActive = BundleUtils.getString("status.type.1");
    String lblDeActive = BundleUtils.getString("status.type.0");
    String strStatus = "1";
    Button btnSave;
    Button btnCancel;
    Button btnReset;
//    UserToken userToken;
    CommonTableFilterPanel commonTable;

    public DialogCreateAppParamController(final DialogCreateAppParams dialogCreateAppParams, final String action, CommonTableFilterPanel table) {
        this.dialogCreateAppParams = dialogCreateAppParams;
        this.action = action;
        this.cboStatus = dialogCreateAppParams.getCboStatus();
        this.commonTable = table;
        this.tblAppParam = commonTable.getMainTable();
        this.appParamsDTO = dialogCreateAppParams.getAppParamsDTO();
//        this.userToken = userToken;
        this.btnSave = dialogCreateAppParams.getBtnSave();
        this.btnCancel = dialogCreateAppParams.getBtnCancel();
//        this.btnReset = dialogCreateAppParams.getBtnReset();
        ShortcutUtils.setShortcutKey(dialogCreateAppParams.getBtnSave());
        ShortcutUtils.setQuit(dialogCreateAppParams.getBtnCancel());
        ShortcutUtils.doTextChangeUppercase(dialogCreateAppParams.getTxtParamType());
//        ShortcutUtils.doTextTrim(dialogCreateAppParams.getTxtParamCode());
//        ShortcutUtils.doTextTrim(dialogCreateAppParams.getTxtParamName());
//        ShortcutUtils.doTextTrim(dialogCreateAppParams.getTxtParamOrder());
//        ShortcutUtils.doTextTrim(dialogCreateAppParams.getTxtParamType());
        //thuc thi
        btnSave.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!isNullValue()) {
                    if (action.equalsIgnoreCase(Constants.EDIT)) {
                        if (!validateField()) {
                            CommonUtils.enableButtonAfterClick(event);
                            return;
                        }
                        message = updateAppParam();
                        if (message.equalsIgnoreCase(Constants.SUCCESS)) {
                            CommonUtils.reloadTable(tblAppParam, appParamsDTO);
                            CommonUtils.showUpdateSuccess(BundleUtils.getString("appparam"));
                            dialogCreateAppParams.close();
                        } else {
                            CommonUtils.showUpdateFail(BundleUtils.getString("appparam"));
                        }
                    }
                    if (action.equalsIgnoreCase(Constants.COPY)) {
                        if (!validateField()) {
                            CommonUtils.enableButtonAfterClick(event);
                            return;
                        }
                        resultDTO = createAppParam();
                        if (resultDTO == null) {
                            resultDTO = new ResultDTO();
                        }
                        if (!DataUtil.isStringNullOrEmpty(resultDTO.getMessage())
                                && Constants.SUCCESS.equalsIgnoreCase(resultDTO.getMessage())) {
                            appParamsDTO.setParId(resultDTO.getId());
                            CommonUtils.reloadTable(tblAppParam, appParamsDTO);
                            CommonUtils.showCopySuccess(BundleUtils.getString("appparam"));
                            dialogCreateAppParams.getTxtParamCode().setValue("");
                            ShortcutUtils.setFocus(dialogCreateAppParams.getTxtParamCode());
                        } else {
                            //Check record duplicate in database table
                            CommonUtils.showMessageDuplicate(resultDTO);
                            ShortcutUtils.setFocus(dialogCreateAppParams.getTxtParamCode());
                        }
                    }
                    if (action.equalsIgnoreCase(Constants.ADD)) {
                        if (!validateField()) {
                            CommonUtils.enableButtonAfterClick(event);
                            return;
                        }
                        resultDTO = createAppParam();
                        if (resultDTO == null) {
                            resultDTO = new ResultDTO();
                        }
                        if (!DataUtil.isStringNullOrEmpty(resultDTO.getMessage())
                                && Constants.SUCCESS.equalsIgnoreCase(resultDTO.getMessage())) {
                            //propertise
                            appParamsDTO.setParId(resultDTO.getId());
                            CommonUtils.reloadTable(tblAppParam, appParamsDTO);
                            CommonUtils.showInsertSuccess(BundleUtils.getString("appparam"));
                            doResetInput();
                            ShortcutUtils.setFocus(dialogCreateAppParams.getTxtParamCode());
                        } else {
                            //Check record duplicate in database table
                            CommonUtils.showMessageDuplicate(resultDTO);
                            ShortcutUtils.setFocus(dialogCreateAppParams.getTxtParamCode());
                        }
                    }
                }
                CommonUtils.enableButtonAfterClick(event);
            }
        });
        btnCancel.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                dialogCreateAppParams.close();
            }
        });
//        if (action.equalsIgnoreCase(Constants.EDIT)) {
//            btnReset.setVisible(false);
//        } else {
//            btnReset.addClickListener(new Button.ClickListener() {
//
//                @Override
//                public void buttonClick(Button.ClickEvent event) {
//
//                    doResetInput();
//                }
//            });
//        }
    }

    /**
     * initialize
     *
     * @param apdto
     */
    public void init(AppParamsDTO apdto) {
        switch (action) {
            case Constants.EDIT:
                appParamsDTO = apdto;
                break;
            case Constants.COPY:
                appParamsDTO = (AppParamsDTO) apdto.copy();
                break;
            case Constants.ADD:
                appParamsDTO = (AppParamsDTO) apdto.copy();
                break;
        }
        fillDataComboStatus();
        fillData2ParForm(appParamsDTO);
    }

    public void fillDataComboStatus() {
        //init combo
        comboComponent = new ComboComponent();
        comboComponent.fillDataCombo(cboStatus, "all", "1");
    }

    public ResultDTO createAppParam() {
        try {
//            appParamsDTO.setStatusName(null);
            appParamsDTO = getValueForm();
            appParamsDTO.setParId(null);
            return WSAppParams.insertAppParams(appParamsDTO);

        } catch (Exception ex) {
            Logger.getLogger(DialogCreateAppParamController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Update app param
     *
     * @return
     */
    public String updateAppParam() {
        try {
            appParamsDTO = getValueForm();
            return WSAppParams.updateAppParams(appParamsDTO);
        } catch (Exception ex) {
            Logger.getLogger(DialogCreateAppParamController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void fillData2ParForm(AppParamsDTO appParamsDTO) {
        if (action.equalsIgnoreCase(Constants.EDIT)) {
            dialogCreateAppParams.getTxtParamCode().setValue(DataUtil.getStringNullOrZero(appParamsDTO.getParCode()));
            ShortcutUtils.setVisibleTextfield(dialogCreateAppParams.getTxtParamCode(), false);
            dialogCreateAppParams.getTxtParamName().setValue(DataUtil.getStringNullOrZero(appParamsDTO.getParName()));
        }
        if (action.equalsIgnoreCase(Constants.COPY)) {
            ShortcutUtils.setFocus(dialogCreateAppParams.getTxtParamCode());
            dialogCreateAppParams.getTxtParamName().setValue(DataUtil.getStringNullOrZero(appParamsDTO.getParName()));
        }
        dialogCreateAppParams.getTxtParamOrder().setValue(DataUtil.getStringNullOrZero(appParamsDTO.getParOrder()));
        dialogCreateAppParams.getTxtParamType().setValue(DataUtil.getStringNullOrZero(appParamsDTO.getParType()));
        dialogCreateAppParams.getTxtParamDesc().setValue(DataUtil.getStringNullOrZero(appParamsDTO.getDescription()));
        if (!DataUtil.isStringNullOrEmpty(appParamsDTO.getStatus())) {
            comboComponent = new ComboComponent();
            comboComponent.fillDataCombo(cboStatus, "", appParamsDTO.getStatus());
        } else {
            fillDataComboStatus();
        }
    }

    public AppParamsDTO getValueForm() {
        String parCode = "";
        String parName = "";
        String parType = "";
        String parDesc = "";
        String parOrder = "";
        String status = "";

        parCode = DataUtil.getStringEscapeHTML4(dialogCreateAppParams.getTxtParamCode().getValue().trim());
        parName = DataUtil.getStringEscapeHTML4(dialogCreateAppParams.getTxtParamName().getValue().trim());
        parType = DataUtil.getStringEscapeHTML4(dialogCreateAppParams.getTxtParamType().getValue().trim());
        parDesc = DataUtil.getStringEscapeHTML4(dialogCreateAppParams.getTxtParamDesc().getValue().trim());
        parOrder = DataUtil.getStringEscapeHTML4(dialogCreateAppParams.getTxtParamOrder().getValue().trim());
        if (dialogCreateAppParams.getCboStatus().getValue() != null) {
            AppParamsDTO appParamsStatus = (AppParamsDTO) dialogCreateAppParams.getCboStatus().getValue();
            status = DataUtil.getStringNullOrZero(appParamsStatus.getParCode());
        }
        appParamsDTO.setParCode(parCode);
        appParamsDTO.setParName(parName);
        appParamsDTO.setParType(parType);
        appParamsDTO.setStatus(status);
        appParamsDTO.setParOrder(parOrder);
        appParamsDTO.setDescription(parDesc);
        return appParamsDTO;
    }

    /**
     * map code to name
     *
     * @param staffDTO
     * @return
     */
//    public AppParamsDTO mapCode2Name(AppParamsDTO appParamsDTO) {
//        String parId = "";
//        parId = appParamsDTO.getParId();
//        AppParamsDTO apdto = new AppParamsDTO();
//        try {
//            apdto = WSAppParams.findAppParamsById(parId);
//
//            if (!DataUtil.isStringNullOrEmpty(apdto.getStatus())) {
//                apdto.setStatusName(BundleUtils.getString("common.status." + apdto.getStatus()));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return apdto;
//    }
    public void doResetInput() {
        ShortcutUtils.setFocus(dialogCreateAppParams.getTxtParamCode());
        dialogCreateAppParams.getTxtParamCode().setValue("");
        dialogCreateAppParams.getTxtParamName().setValue("");
        dialogCreateAppParams.getTxtParamOrder().setValue("");
        dialogCreateAppParams.getTxtParamDesc().setValue("");
        dialogCreateAppParams.getTxtParamType().setValue("");
        appParamsDTO = new AppParamsDTO();
        comboComponent.fillDataCombo(cboStatus, "all", "1");
    }

    /**
     * check null for form
     *
     * @return
     */
    public boolean isNullValue() {
        if (DataUtil.isStringNullOrEmpty(dialogCreateAppParams.getTxtParamCode().getValue().trim())) {
            dialogCreateAppParams.getTxtParamCode().focus();
            CommonUtils.showMessageRequired("lb.appParam.code");
            return true;
        }
        if (DataUtil.isStringNullOrEmpty(dialogCreateAppParams.getTxtParamName().getValue().trim())) {
            dialogCreateAppParams.getTxtParamName().focus();
            CommonUtils.showMessageRequired("lb.appParam.name");
            return true;
        }
        if (DataUtil.isStringNullOrEmpty(dialogCreateAppParams.getTxtParamType().getValue())) {
            dialogCreateAppParams.getTxtParamType().focus();
            CommonUtils.showMessageRequired("lb.appParam.type");
            return true;
        }
        return false;
    }

    public boolean validateField() {
//        String code = dialogCreateAppParams.getTxtParamCode().getValue().trim();
        String order = dialogCreateAppParams.getTxtParamOrder().getValue().trim();
//        if (!CommonValidator.isValueInteger(code)) {
//            dialogCreateAppParams.getTxtParamCode().setRequiredError(BundleUtils.getString("message.error.numberformat"));
//            dialogCreateAppParams.getTxtParamCode().focus();
//            return false;
//        }
        if (!DataUtil.isInteger(order)) {
            dialogCreateAppParams.getTxtParamOrder().setRequiredError(BundleUtils.getString("message.error.numberformat"));
            dialogCreateAppParams.getTxtParamOrder().focus();
            return false;
        }
        return true;
    }
}
