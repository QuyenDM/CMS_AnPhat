/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.cms.common.controller.ConmonController;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.PriceInfoDTO;
import com.cms.service.AppParamsServiceImpl;
import com.cms.service.PriceInfoServiceImpl;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.view.PriceInfoView;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author QuyenDM
 */
public class PriceInfoController extends ConmonController<PriceInfoDTO> {

    private PriceInfoView priceInfoView;
    private CommonTableFilterPanel panelPriceInfo;
    private CustomPageTableFilter<IndexedContainer> tblPriceInfo;
    private BeanItemContainer beanItemContainerPriceInfo;
    private List<PriceInfoDTO> lstPriceInfoDTO = new ArrayList<>();
    private PriceInfoServiceImpl servicePriceInfo = new PriceInfoServiceImpl();
    private AppParamsServiceImpl serviceAppParams = new AppParamsServiceImpl();
    private String lblDelete = "delete";
    private String lblEdit = "edit";
    private LinkedHashMap<String, CustomTable.Align> headerData = BundleUtils.getHeadersFilter("priceInfo.header");
    private List<AppParamsDTO> lstAppParamsDTO;
    private List<AppParamsDTO> lstProvider;
    private Map<String, String> mapProvider;
    private List<AppParamsDTO> lstType;
    private Map<String, String> mapType;
    private List<AppParamsDTO> lstStatus;
    private Map<String, String> mapStatus;
    private PopupAddPriceInfo popupAddPriceInfo;
    private boolean isUpdate;
    private ComboComponent comboComponent;

    public PriceInfoController(PriceInfoView priceInfoView) {
        super(PriceInfoDTO.class);
        this.priceInfoView = priceInfoView;
        panelPriceInfo = priceInfoView.getTblPriceInfo();
        tblPriceInfo = priceInfoView.getTblPriceInfo().getMainTable();
        init();
    }

    private void init() {
        getDataWS();
        initTable();
        initButton();
        initComboBox();
    }

    public void initComboBox() {
        BeanItemContainer containerProvider = new BeanItemContainer<>(AppParamsDTO.class);
        containerProvider.addAll(lstProvider);
        CommonUtils.initCombobox(priceInfoView.getCbxProvider(), containerProvider, Constants.APP_PARAMS.PAR_NAME);
        BeanItemContainer containerType = new BeanItemContainer<>(AppParamsDTO.class);
        containerType.addAll(lstType);
        CommonUtils.initCombobox(priceInfoView.getCbxType(), containerType, Constants.APP_PARAMS.PAR_NAME);
        BeanItemContainer containerStatus = new BeanItemContainer<>(AppParamsDTO.class);
        containerStatus.addAll(lstStatus);
        ComboBox cboStatus = priceInfoView.getCbxStatus();
        CommonUtils.initCombobox(priceInfoView.getCbxStatus(), containerStatus, Constants.APP_PARAMS.PAR_NAME);
        comboComponent = new ComboComponent();
        comboComponent.fillDataCombo(cboStatus, "all", "1");

    }

    public void getDataWS() {
// ds appparam
        AppParamsDTO appParamsDTO = new AppParamsDTO();
        appParamsDTO.setStatus(Constants.ACTIVE);
        lstAppParamsDTO = serviceAppParams.getListAppParamsDTO(appParamsDTO, 0, Integer.MAX_VALUE, "", "parOrder");
        if (lstAppParamsDTO == null) {
            lstAppParamsDTO = new ArrayList<>();
        }
        lstProvider = DataUtil.getListApParams(lstAppParamsDTO, "PROVIDER");
        lstType = DataUtil.getListApParams(lstAppParamsDTO, "TYPE_CONTRACT");
        lstStatus = DataUtil.getListApParams(lstAppParamsDTO, "COMMON_STATUS");

        try {
            mapStatus = DataUtil.buildHasmap(lstStatus, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
            mapType = DataUtil.buildHasmap(lstType, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
            mapProvider = DataUtil.buildHasmap(lstProvider, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ContractTemplateListController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ContractTemplateListController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ContractTemplateListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initButton() {
        tblPriceInfo.setMultiSelect(false);
        priceInfoView.getBtnSearch().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                doSearch();
                event.getButton().setEnabled(true);
            }
        });
        priceInfoView.getBtnRefresh().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                priceInfoView.getTxtCode().setValue("");
                priceInfoView.getTxtName().setValue("");
                priceInfoView.getTxtPrice().setValue("");
                priceInfoView.getTxtTokenPrice().setValue("");
                priceInfoView.getCbxProvider().setValue(null);
                priceInfoView.getCbxType().setValue(null);
                priceInfoView.getCbxStatus().setValue(null);
                event.getButton().setEnabled(true);
            }
        });
        panelPriceInfo.getAddButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                isUpdate = false;
                InsertOrUpdate(new PriceInfoDTO());
                event.getButton().setEnabled(true);
            }
        });

        panelPriceInfo.getEditButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                isUpdate = true;
                PriceInfoDTO selected = (PriceInfoDTO) tblPriceInfo.getValue();
                if (selected == null) {
                    CommonUtils.showChoseOne();
                } else {
                    InsertOrUpdate(selected);
                }
                event.getButton().setEnabled(true);
            }
        });

        panelPriceInfo.getCoppyButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                isUpdate = false;
                PriceInfoDTO selected = (PriceInfoDTO) tblPriceInfo.getValue();
                if (selected == null) {
                    CommonUtils.showChoseOne();
                } else {
                    PriceInfoDTO priceINfo = new PriceInfoDTO(selected);
                    InsertOrUpdate(priceINfo);
                }
                event.getButton().setEnabled(true);
            }
        });

    }

    public void InsertOrUpdate(final PriceInfoDTO priceInfoDTO) {
        popupAddPriceInfo = new PopupAddPriceInfo();
        popupAddPriceInfo.setIsUpdate(isUpdate);

        if (!isUpdate) {
           priceInfoDTO.setId(null);
        }
        
        BeanItemContainer containerProvider = new BeanItemContainer<>(AppParamsDTO.class);
        containerProvider.addAll(lstProvider);
        CommonUtils.initCombobox(popupAddPriceInfo.getCbxProvider(), containerProvider, Constants.APP_PARAMS.PAR_NAME);
        BeanItemContainer containerType = new BeanItemContainer<>(AppParamsDTO.class);
        containerType.addAll(lstType);
        CommonUtils.initCombobox(popupAddPriceInfo.getCbxType(), containerType, Constants.APP_PARAMS.PAR_NAME);
        BeanItemContainer containerStatus = new BeanItemContainer<>(AppParamsDTO.class);
        containerStatus.addAll(lstStatus);
        CommonUtils.initCombobox(popupAddPriceInfo.getCbxStatus(), containerStatus, Constants.APP_PARAMS.PAR_NAME);
//        comboComponent.fillDataCombo(popupAddPriceInfo.getCbxStatus(), "", "1");
        if (priceInfoDTO.getCode() != null) {
            popupAddPriceInfo.getTxtCode().setValue(priceInfoDTO.getCode());
        } else {
            popupAddPriceInfo.getTxtCode().setValue("");
        }
        if (priceInfoDTO.getName() != null) {
            popupAddPriceInfo.getTxtName().setValue(priceInfoDTO.getName());
        } else {
            popupAddPriceInfo.getTxtName().setValue("");
        }
        if (priceInfoDTO.getPrice() != null) {
            popupAddPriceInfo.getTxtPrice().setValue(priceInfoDTO.getPrice());
        } else {
            popupAddPriceInfo.getTxtPrice().setValue("");
        }
        if (priceInfoDTO.getTokenPrice() != null) {
            popupAddPriceInfo.getTxtTokenPrice().setValue(priceInfoDTO.getTokenPrice());
        } else {
            popupAddPriceInfo.getTxtTokenPrice().setValue("");
        }
        if (priceInfoDTO.getProvider() != null) {
            AppParamsDTO providerDefault = null;
            for (AppParamsDTO provider : lstProvider) {
                if (provider.getParCode().equals(priceInfoDTO.getProvider())) {
                    providerDefault = provider;
                }
            }
            popupAddPriceInfo.getCbxProvider().setValue(providerDefault);
        } else {
            popupAddPriceInfo.getCbxProvider().setValue(null);
        }
        if (priceInfoDTO.getType() != null) {
            AppParamsDTO typeDefault = null;
            for (AppParamsDTO type : lstType) {
                if (type.getParCode().equals(priceInfoDTO.getType())) {
                    typeDefault = type;
                }
            }
            popupAddPriceInfo.getCbxType().setValue(typeDefault);
        } else {
            popupAddPriceInfo.getCbxType().setValue(null);
        }
        if (priceInfoDTO.getStatus() != null) {
//            AppParamsDTO statusDefault = null;
//            for (AppParamsDTO status : lstStatus) {
//                if (status.getParCode().equals(priceInfoDTO.getStatus())) {
//                    statusDefault = status;
//                }
//            }
//            popupAddPriceInfo.getCbxStatus().setValue(statusDefault);
            comboComponent.fillDataCombo(popupAddPriceInfo.getCbxStatus(), "", priceInfoDTO.getStatus());
        } else {
            comboComponent.fillDataCombo(popupAddPriceInfo.getCbxStatus(), "", "1");
        }

        popupAddPriceInfo.getBtnSave().addClickListener((event) -> {
            if (popupAddPriceInfo.getTxtCode().getValue() != null || popupAddPriceInfo.getTxtCode().getValue().equals("")) {
                priceInfoDTO.setCode(popupAddPriceInfo.getTxtCode().getValue());
            } else {
                Notification.show(BundleUtils.getString("priceInfo.code.isnotnull"));
                return;
            }
            priceInfoDTO.setName(popupAddPriceInfo.getTxtName().getValue());
            priceInfoDTO.setPrice(popupAddPriceInfo.getTxtPrice().getValue());
            priceInfoDTO.setTokenPrice(popupAddPriceInfo.getTxtTokenPrice().getValue());
            AppParamsDTO provider = (AppParamsDTO) popupAddPriceInfo.getCbxProvider().getValue();
            priceInfoDTO.setProvider(provider.getParCode());
            AppParamsDTO type = (AppParamsDTO) popupAddPriceInfo.getCbxType().getValue();
            priceInfoDTO.setType(type.getParCode());
            AppParamsDTO status = (AppParamsDTO) popupAddPriceInfo.getCbxStatus().getValue();
            priceInfoDTO.setStatus(status.getParCode());
            if (priceInfoDTO.getId() == null) {
                try {
                    ResultDTO resultDTO = servicePriceInfo.insertPriceInfo(priceInfoDTO);
                    if (Constants.SUCCESS.equals(resultDTO.getMessage())) {
                        PriceInfoDTO priceInfoDTO1 = new PriceInfoDTO(priceInfoDTO);
                        priceInfoDTO1.setId(resultDTO.getId());
                        tblPriceInfo.unselect(priceInfoDTO);
                        tblPriceInfo.addItemAfter(null, priceInfoDTO1);
                        tblPriceInfo.select(priceInfoDTO1);
                        popupAddPriceInfo.close();
                        CommonUtils.showInsertSuccess(BundleUtils.getString("price.info"));
                    } else {
                        CommonUtils.showInsertFail(BundleUtils.getString("price.info"));
                    }

                } catch (Exception e) {
                    CommonUtils.showInsertFail(BundleUtils.getString("price.info"));
                }

            } else {
                try {
                    String message = servicePriceInfo.updatePriceInfo(priceInfoDTO);
                    if (message.equals(Constants.SUCCESS)) {
                        CommonUtils.reloadTable(tblPriceInfo, priceInfoDTO);
                        popupAddPriceInfo.close();
                        CommonUtils.showUpdateSuccess(BundleUtils.getString("price.info"));
                    } else {
                        CommonUtils.showUpdateFail(BundleUtils.getString("price.info"));

                    }
                } catch (Exception e) {
                    CommonUtils.showUpdateFail(BundleUtils.getString("price.info"));
                }
            }
        });
        popupAddPriceInfo.getBtnClose()
                .addClickListener(e -> {
                    popupAddPriceInfo.close();
                });

        UI.getCurrent()
                .addWindow(popupAddPriceInfo);
    }

    public void initTable() {
        beanItemContainerPriceInfo = new BeanItemContainer<>(PriceInfoDTO.class);
        tblPriceInfo.setMultiSelect(false);
        CommonFunctionTableFilter.initTable(panelPriceInfo, headerData, beanItemContainerPriceInfo, BundleUtils.getString("table.list.priceInfo"), 10, "label.PriceInfo", true, true, false, false, false);
        CommonUtils.setVisibleBtnTablePanel(panelPriceInfo, true, false, true, true);
        CommonUtils.convertFieldAppParamTable(panelPriceInfo.getMainTable(), "status", Constants.APP_PARAMS.COMMON_STATUS, mapStatus);
        CommonUtils.convertFieldAppParamTable(panelPriceInfo.getMainTable(), "type", Constants.APP_PARAMS.TYPE_CONTRACT, mapType);
        CommonUtils.convertFieldAppParamTable(panelPriceInfo.getMainTable(), "provider", Constants.APP_PARAMS.PROVIDER, mapProvider);
    }

    @Override
    public void onDoSearch() {
        lstConditionBeanSearch = getLstConditionBeanSearch();
        if (!DataUtil.isListNullOrEmpty(lstConditionBeanSearch)) {
            try {
                lstPriceInfoDTO = servicePriceInfo.getListPriceInfoByCondition(lstConditionBeanSearch, 0, Integer.MAX_VALUE, "", "code");
            } catch (Exception e) {
                lstPriceInfoDTO = new ArrayList<>();
            }
        } else {
            lstPriceInfoDTO = servicePriceInfo.getListPriceInfoDTO(new PriceInfoDTO(), 0, Constants.INT_1000, "asc", "provider");
        }

        beanItemContainerPriceInfo.removeAllItems();
        if (DataUtil.isListNullOrEmpty(lstPriceInfoDTO)) {
            CommonMessages.showDataNotFound();
        } else {
            beanItemContainerPriceInfo.addAll(lstPriceInfoDTO);
            CommonFunctionTableFilter.refreshTable(panelPriceInfo, headerData, beanItemContainerPriceInfo);
        }
        priceInfoView.getBtnSearch().setEnabled(true);
    }

    public List<ConditionBean> getLstConditionBeanSearch() {
        List<ConditionBean> lstConditionBean = new ArrayList<>();
        if (!DataUtil.isStringNullOrEmpty(priceInfoView.getTxtCode().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("code");
            conditionBean.setValue(priceInfoView.getTxtCode().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(priceInfoView.getTxtName().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("name");
            conditionBean.setValue(priceInfoView.getTxtName().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(priceInfoView.getTxtPrice().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("price");
            conditionBean.setValue(priceInfoView.getTxtPrice().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(priceInfoView.getTxtTokenPrice().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("tokenPrice");
            conditionBean.setValue(priceInfoView.getTxtTokenPrice().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (priceInfoView.getCbxProvider().getValue() != null) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("provider");
            conditionBean.setValue(((AppParamsDTO) priceInfoView.getCbxProvider().getValue()).getParCode());
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (priceInfoView.getCbxType().getValue() != null) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("type");
            conditionBean.setValue(((AppParamsDTO) priceInfoView.getCbxType().getValue()).getParCode());
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (priceInfoView.getCbxStatus().getValue() != null) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("status");
            conditionBean.setValue(((AppParamsDTO) priceInfoView.getCbxStatus().getValue()).getParCode());
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        return lstConditionBean;

    }
}
