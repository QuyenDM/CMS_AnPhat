/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.cms.appparams.controller;

import com.cms.common.ws.WSAppParams;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.cms.component.CommonFunction;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.AppParamsDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.ShortcutUtils;
import com.cms.view.AppParams;
import com.anphat.cms.appparams.ui.DialogCreateAppParams;
import com.anphat.cms.appparams.ui.SearchAppParamsForm;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vaadin.dialogs.ConfirmDialog;
//import viettel.passport.client.UserToken;

/**
 *
 * @author hungkv
 */
public class ListAppParamsController implements Serializable{

    private SearchAppParamsForm appParamsForm;
    private AppParams appParams;
    private DialogCreateAppParams dialogCreateAppParams;
    private DialogCreateAppParamController dialogCreateAppParamController;
    CustomPageTableFilter<IndexedContainer> tblAppParam;
    private CommonTableFilterPanel commonTableParamPanel;
    public Map<String, AppParamsDTO> mapNameFromKey = new HashMap<>();
    private List<AppParamsDTO> lstApParams;
    private List<AppParamsDTO> listStatusType = Lists.newArrayList();
    AppParamsDTO appParamsDTO;
    private BeanItemContainer beanItemContainer;
    private LinkedHashMap<String, CustomTable.Align> headerData = BundleUtils.getHeadersFilter("appparam.header");
    private Button btnSearch;
    private Button btnRefresh;
    private Button btnAdd;
    private Button btnEdit;
    private Button btnCopy;
    private Button btnDel;
    private ComboBox cboStatus;
    ComboComponent comboComponent;
    String lblActive = BundleUtils.getString("status.type.1");
    String lblDeActive = BundleUtils.getString("status.type.0");
    String strStatus = "1";
    private String lblAppParamInfo = BundleUtils.getString("caption.appparam.listAppParam");
    String message;
//    UserToken userToken;

    public ListAppParamsController(AppParams mainUI) {
        this.appParams = mainUI;
        commonTableParamPanel = mainUI.getAppParamTablePanel();
        this.tblAppParam = commonTableParamPanel.getMainTable();

        tblAppParam.setImmediate(true);
        btnSearch = mainUI.getBtnSearch();
        btnAdd = commonTableParamPanel.getAddButton();
        btnCopy = commonTableParamPanel.getCoppyButton();
        btnEdit = commonTableParamPanel.getEditButton();
        btnDel = commonTableParamPanel.getDelContraintButton();
        btnDel.setDescription(Constants.BUTTON_CANCEL);
        btnDel.setVisible(true);
        commonTableParamPanel.getDeleteButton().setVisible(false);
        btnRefresh = mainUI.getBtnRefresh();
        appParamsForm = mainUI.getAppParamsForm();
        this.cboStatus = appParamsForm.getCboStatus();
        this.appParamsDTO = new AppParamsDTO();
        //init combo
        comboComponent = new ComboComponent();
        comboComponent.fillDataCombo(cboStatus, "all", "1");
        //init table
        initTable();
        init();
        //action
        actionListener();
    }

    public void initTable() {
        tblAppParam.addGeneratedColumn("strStatus", new CustomTable.ColumnGenerator() {

            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                AppParamsDTO apdto = (AppParamsDTO) itemId;
                if (!DataUtil.isStringNullOrEmpty(apdto.getStatus())) {
                    return BundleUtils.getString("common.status." + apdto.getStatus());
                } else {
                    return Constants.NULL;
                }
            }
        });
        beanItemContainer = new BeanItemContainer<>(AppParamsDTO.class);
        CommonFunctionTableFilter.initTable(commonTableParamPanel, headerData, beanItemContainer, lblAppParamInfo, 10, "lb.appparam.header");
    }

    public void init() {

        this.tblAppParam.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null && !tblAppParam.isMultiSelect()) {
                    appParamsDTO = (AppParamsDTO) event.getProperty().getValue();
                }
            }
        });
        getListAppParams(appParamsDTO);
        setDataTable(lstApParams);
    }

    public void actionListener() {

        btnSearch.setClickShortcut(KeyCode.ENTER);//Shift+S
        btnSearch.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                doSearchListAppParams();
                btnSearch.setEnabled(true);
            }
        });
        btnRefresh.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                doResetInput();
                btnRefresh.setEnabled(true);
            }
        });
        //them moi tham so ung dung
        btnAdd.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                dialogCreateAppParams = new DialogCreateAppParams(BundleUtils.getString("appparam.management.panel.addParam"), new AppParamsDTO());
                dialogCreateAppParamController = new DialogCreateAppParamController(dialogCreateAppParams, Constants.ADD, commonTableParamPanel);
                dialogCreateAppParamController.init(new AppParamsDTO());
                UI.getCurrent().addWindow(dialogCreateAppParams);
                CommonFunction.enableButtonAfterClick(event);
            }
        });
        //sua tham so ung dung
        btnEdit.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                final Set<AppParamsDTO> selectedValues = (Set<AppParamsDTO>) tblAppParam.getValue();
                if (!selectedValues.isEmpty() && selectedValues.size() == 1) {
                    AppParamsDTO apdto = (AppParamsDTO) selectedValues.toArray()[0];
                    dialogCreateAppParams = new DialogCreateAppParams(BundleUtils.getString("appparam.management.panel.updateParam"), apdto);
                    dialogCreateAppParamController = new DialogCreateAppParamController(dialogCreateAppParams, Constants.EDIT, commonTableParamPanel);
                    dialogCreateAppParamController.init(apdto);
                    UI.getCurrent().addWindow(dialogCreateAppParams);
                } else {
                    CommonUtils.showChoseOne();
                }
                CommonFunction.enableButtonAfterClick(event);
            }
        });
        //sua tham so ung dung
        btnCopy.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                final Set<AppParamsDTO> selectedValues = (Set<AppParamsDTO>) tblAppParam.getValue();
                if (!selectedValues.isEmpty()) {
                    AppParamsDTO apdto = (AppParamsDTO) selectedValues.toArray()[0];
                    apdto.setParId(null);
                    dialogCreateAppParams = new DialogCreateAppParams(BundleUtils.getString("appparam.management.panel.copyParam"), apdto);
                    dialogCreateAppParamController = new DialogCreateAppParamController(dialogCreateAppParams, Constants.COPY, commonTableParamPanel);
                    dialogCreateAppParamController.init(apdto);
                    UI.getCurrent().addWindow(dialogCreateAppParams);
                } else {
                    CommonUtils.showChoseOne();
                }
                CommonFunction.enableButtonAfterClick(event);
            }
        });

        tblAppParam.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null && !tblAppParam.isMultiSelect()) {
                    appParamsDTO = (AppParamsDTO) event.getProperty().getValue();
                }
            }
        });

        ShortcutUtils.setTooltipForFields(tblAppParam, new String[]{Constants.APP_PARAMS.PAR_NAME, Constants.APP_PARAMS.PAR_TYPE, Constants.APP_PARAMS.DESCRIPTION});

        btnDel.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                final Set<AppParamsDTO> selectedValues = (Set<AppParamsDTO>) tblAppParam.getValue();
                if (!selectedValues.isEmpty()) {
                    //Xac nhan huy dich vu
                    ConfirmDialog.show(UI.getCurrent(), BundleUtils.getString("wms.confirmdialog.confirm"), BundleUtils.getString("bodyMessage"),
                            BundleUtils.getString("comfirm.yes"), BundleUtils.getString("comfirm.no"), new ConfirmDialog.Listener() {
                        @Override
                        public void onClose(ConfirmDialog dialog) {
                            if (dialog.isConfirmed()) {
                                AppParamsDTO apdto = (AppParamsDTO) selectedValues.toArray()[0];
                                apdto.setStatus("0");
                                String delete = WSAppParams.updateAppParams(apdto);
                                if (delete != null && delete.equalsIgnoreCase(Constants.SUCCESS)) {
                                    tblAppParam.removeItem(apdto);
                                    CommonMessages.showCancelSuccess("appparam");
                                } else {
                                    CommonMessages.showCancelFail("appparam");
                                }
                            }
                        }
                    });

                } else {
                    CommonUtils.showChoseOne();
                }
                CommonFunction.enableButtonAfterClick(event);
            }
        });
    }

    public void getListAppParams(AppParamsDTO appParamsDTO) {
        try {
            lstApParams = WSAppParams.getListAppParamsDTO(appParamsDTO, 0, Integer.MAX_VALUE, Constants.DESC, "parCode");
        } catch (Exception ex) {
            StringBuilder sb = new StringBuilder();
            Logger.getLogger(ListAppParamsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (lstApParams == null) {
            lstApParams = Lists.newArrayList();
        }
    }

    public void doSearchListAppParams() {
        appParamsDTO = new AppParamsDTO();
        appParamsDTO = getValueForm();
        try {
            getListAppParams(appParamsDTO);
            if (lstApParams == null || lstApParams.isEmpty()) {
                lstApParams = new ArrayList<>();
                Notification.show(BundleUtils.getString("notFoundData"), Notification.Type.TRAY_NOTIFICATION);
            }
            //set data into table
            setDataTable(lstApParams);
        } catch (Exception ex) {
            StringBuilder sb = new StringBuilder();
            Logger.getLogger(ListAppParamsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AppParamsDTO getValueForm() {
        String parCode = "";
        String parName = "";
        String parType = "";
        String parDesc = "";
        String parOrder = "";
        String status = "";

        parCode = DataUtil.getStringNullOrZero(appParamsForm.getTxtParamCode().getValue().trim());
        parName = DataUtil.getStringNullOrZero(appParamsForm.getTxtParamName().getValue().trim());
        parType = DataUtil.getStringNullOrZero(appParamsForm.getTxtParamType().getValue().trim());
        parDesc = DataUtil.getStringNullOrZero(appParamsForm.getTxtParamDesc().getValue().trim());
        parOrder = DataUtil.getStringNullOrZero(appParamsForm.getTxtParamOrder().getValue().trim());
        if (appParamsForm.getCboStatus().getValue() != null) {
            AppParamsDTO appParamsStatus = (AppParamsDTO) appParamsForm.getCboStatus().getValue();
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

    public void doResetInput() {
        ShortcutUtils.setFocus(appParams.getAppParamsForm().getTxtParamCode());
        appParams.getAppParamsForm().getTxtParamCode().setValue("");
        appParams.getAppParamsForm().getTxtParamName().setValue("");
        appParams.getAppParamsForm().getTxtParamOrder().setValue("");
        appParams.getAppParamsForm().getTxtParamDesc().setValue("");
        appParams.getAppParamsForm().getTxtParamType().setValue("");
        appParamsDTO = new AppParamsDTO();
        comboComponent.fillDataCombo(cboStatus, "all", "1");
    }

    public void setDataTable(List<AppParamsDTO> listAppParams) {
        //fill data
        if (beanItemContainer == null) {
            beanItemContainer = new BeanItemContainer<>(AppParamsDTO.class);
        } else {
            beanItemContainer.removeAllItems();
        }
        beanItemContainer.addAll(listAppParams);
        CommonFunctionTableFilter.refreshTable(commonTableParamPanel, headerData, beanItemContainer);
    }
}
