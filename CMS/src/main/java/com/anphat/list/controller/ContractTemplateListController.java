/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.list.ui.PopupAddContractTemplateList;
import com.cms.common.controller.ConmonController;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.AppParamsDTO;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.UI;
import com.cms.dto.ContractTemplateListDTO;
import com.cms.service.AppParamsServiceImpl;
import com.cms.service.ContractTemplateListServiceImpl;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.FileDownloader;
import java.util.Map;
import com.cms.view.ContractTemplateListView;
import com.google.common.collect.Lists;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Link;
import java.util.LinkedHashMap;
import com.vaadin.ui.Window;
import com.vwf5.base.utils.ConditionBean;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author QuyenDM
 */
public class ContractTemplateListController extends ConmonController<ContractTemplateListDTO> {

    private ContractTemplateListView contractTemplateListView;
    private CommonTableFilterPanel panelContractTemplateList;
    private CustomPageTableFilter<IndexedContainer> tblContractTemplateList;
    private BeanItemContainer beanItemContainerContractTemplateList;
    private List<ContractTemplateListDTO> lstContractTemplateListDTO = Lists.newArrayList();
    private ContractTemplateListServiceImpl serviceContractTemplateList = new ContractTemplateListServiceImpl();
    private AppParamsServiceImpl serviceAppParams = new AppParamsServiceImpl();
    private String lblDelete = "delete";
    private String lblEdit = "edit";
    private LinkedHashMap<String, CustomTable.Align> headerData = BundleUtils.getHeadersFilter("contractTemplateList.header");
    private List<AppParamsDTO> lstAppParamsDTO;
    private List<AppParamsDTO> lstStatus;
    private List<AppParamsDTO> lstService;
    private List<AppParamsDTO> lstType;
    private List<AppParamsDTO> lstProvider;
    private PopupAddContractTemplateList popupAddContractTemplateList;
    private ComboComponent comboUtils;
    private Map<String, String> mapStatus;
    private Map<String, String> mapService;
    private Map<String, String> mapProvider;
    private Map<String, String> mapType;
    private Boolean isUpdate;

    public ContractTemplateListController(ContractTemplateListView contractTemplateListView) {
        super(ContractTemplateListDTO.class);
        this.contractTemplateListView = contractTemplateListView;
        panelContractTemplateList = contractTemplateListView.getTblContractTemplateList();
        tblContractTemplateList = contractTemplateListView.getTblContractTemplateList().getMainTable();
        init();
    }

    private void init() {
        comboUtils = new ComboComponent();
        initButton();
        getDataWS();
        initComboBox();
        initTable();
    }

    public void initComboBox() {
        //ComboBox service
        String statusDefault = DataUtil.isListNullOrEmpty(lstService) ? Constants.NULL : lstService.get(0).getParCode();
        comboUtils.fillDataCombo(contractTemplateListView.getCbxStatus(), Constants.ALL, statusDefault, lstStatus, Constants.APP_PARAMS.PAR_NAME);
        String serviceDefault = DataUtil.isListNullOrEmpty(lstService) ? Constants.NULL : lstService.get(0).getParCode();
        comboUtils.fillDataCombo(contractTemplateListView.getCboService(), Constants.NULL, serviceDefault, lstService, Constants.APP_PARAMS.PAR_NAME);
        comboUtils.fillDataCombo(contractTemplateListView.getCboType(), Constants.ALL, Constants.NULL, lstType, Constants.APP_PARAMS.PAR_NAME);
        comboUtils.fillDataCombo(contractTemplateListView.getCboProvider(), Constants.ALL, Constants.NULL, lstProvider, Constants.APP_PARAMS.PAR_NAME);
    }

    public void getDataWS() {
        // ds appparam
        AppParamsDTO appParamsDTO = new AppParamsDTO();
        appParamsDTO.setStatus(Constants.ACTIVE);
        lstAppParamsDTO = serviceAppParams.getListAppParamsDTO(appParamsDTO, 0, Integer.MAX_VALUE, "", "parOrder");
        if (lstAppParamsDTO == null) {
            lstAppParamsDTO = Lists.newArrayList();
        }
        lstStatus = DataUtil.getListApParams(lstAppParamsDTO, "COMMON_STATUS");
        lstService = DataUtil.getListApParams(lstAppParamsDTO, Constants.APP_PARAMS.SERVICE_TYPE);
        lstType = DataUtil.getListApParams(lstAppParamsDTO, Constants.APP_PARAMS.TYPE_CONTRACT);
        lstProvider = DataUtil.getListApParams(lstAppParamsDTO, Constants.APP_PARAMS.PROVIDER);

        try {
            mapStatus = DataUtil.buildHasmap(lstStatus, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
            mapService = DataUtil.buildHasmap(lstService, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
            mapType = DataUtil.buildHasmap(lstType, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
            mapProvider = DataUtil.buildHasmap(lstProvider, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException ex) {
            Logger.getLogger(ContractTemplateListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void initButton() {
        contractTemplateListView.getBtnSearch().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                doSearch();
                event.getButton().setEnabled(true);
            }
        });
        contractTemplateListView.getBtnRefresh().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                contractTemplateListView.getTxtCode().setValue("");
                contractTemplateListView.getTxtName().setValue("");
                contractTemplateListView.getTxtPathFile().setValue("");
                initComboBox();
                event.getButton().setEnabled(true);
            }
        });
        panelContractTemplateList.getAddButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                isUpdate = false;
                InsertOrUpdate(new ContractTemplateListDTO());
                event.getButton().setEnabled(true);
            }
        });
        panelContractTemplateList.getEditButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                isUpdate = true;
                ContractTemplateListDTO selected = (ContractTemplateListDTO) tblContractTemplateList.getValue();
                if (selected == null) {
                    CommonUtils.showChoseOne();
                } else {
                    InsertOrUpdate(selected);
                    
                }
                event.getButton().setEnabled(true);
            }
        });
    }

    public void InsertOrUpdate(final ContractTemplateListDTO contractTemplateListDTO) {
        popupAddContractTemplateList = new PopupAddContractTemplateList();
        popupAddContractTemplateList.setIsUpdate(isUpdate, contractTemplateListDTO);
        popupAddContractTemplateList.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                if (popupAddContractTemplateList.isIsAddOrUpdateSuccess()) {
                    doSearch();
                }
            }
        });

        if (contractTemplateListDTO.getContractTemplateId() != null) {
            popupAddContractTemplateList.setContractTemplateId(contractTemplateListDTO.getContractTemplateId());
        }

        if (contractTemplateListDTO.getCode() != null) {
            popupAddContractTemplateList.getTxtCode().setValue(contractTemplateListDTO.getCode());
        } else {
            popupAddContractTemplateList.getTxtCode().setValue("");
        }
        if (contractTemplateListDTO.getName() != null) {
            popupAddContractTemplateList.getTxtName().setValue(contractTemplateListDTO.getName());
        } else {
            popupAddContractTemplateList.getTxtName().setValue("");
        }

        fillData2ComboBox();

        if (contractTemplateListDTO.getStatus() != null) {
            AppParamsDTO statusDefault = null;
            for (AppParamsDTO status : lstStatus) {
                if (status.getParCode().equals(contractTemplateListDTO.getStatus())) {
                    statusDefault = status;
                }
            }
            popupAddContractTemplateList.getCbxStatus().setValue(statusDefault);
        }

        if (contractTemplateListDTO.getService() != null) {
            AppParamsDTO serviceDefault = null;
            for (AppParamsDTO service : lstService) {
                if (service.getParCode().equals(contractTemplateListDTO.getService())) {
                    serviceDefault = service;
                }
            }
            popupAddContractTemplateList.getCboService().setValue(serviceDefault);
        }

        if (contractTemplateListDTO.getType() != null) {
            AppParamsDTO typeDefault = null;
            for (AppParamsDTO type : lstType) {
                if (type.getParCode().equals(contractTemplateListDTO.getType())) {
                    typeDefault = type;
                }
            }
            popupAddContractTemplateList.getCboType().setValue(typeDefault);
        }

        if (contractTemplateListDTO.getProvider() != null) {
            AppParamsDTO providerDefault = null;
            for (AppParamsDTO provider : lstProvider) {
                if (provider.getParCode().equals(contractTemplateListDTO.getProvider())) {
                    providerDefault = provider;
                }
            }
            popupAddContractTemplateList.getCboProvider().setValue(providerDefault);
        }

        popupAddContractTemplateList.getTxtCode().focus();
        UI.getCurrent().addWindow(popupAddContractTemplateList);
    }

    public void fillData2ComboBox() {
        String serviceDefault = DataUtil.isListNullOrEmpty(lstService) ? Constants.NULL : lstService.get(0).getParCode();
        String statusDefault = DataUtil.isListNullOrEmpty(lstStatus) ? Constants.NULL : lstStatus.get(0).getParCode();
        String typeDefault = DataUtil.isListNullOrEmpty(lstType) ? Constants.NULL : lstType.get(0).getParCode();
        String providerDefault = DataUtil.isListNullOrEmpty(lstProvider) ? Constants.NULL : lstProvider.get(0).getParCode();
        comboUtils.fillDataCombo(popupAddContractTemplateList.getCboService(), Constants.NULL, serviceDefault, lstService, Constants.APP_PARAMS.PAR_NAME);
        comboUtils.fillDataCombo(popupAddContractTemplateList.getCbxStatus(), Constants.NULL, statusDefault, lstStatus, Constants.APP_PARAMS.PAR_NAME);
        comboUtils.fillDataCombo(popupAddContractTemplateList.getCboType(), Constants.NULL, typeDefault, lstType, Constants.APP_PARAMS.PAR_NAME);
        comboUtils.fillDataCombo(popupAddContractTemplateList.getCboProvider(), Constants.NULL, providerDefault, lstProvider, Constants.APP_PARAMS.PAR_NAME);
    }

    public void initTable() {
        beanItemContainerContractTemplateList = new BeanItemContainer<>(ContractTemplateListDTO.class);
        tblContractTemplateList.addGeneratedColumn("download", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                ContractTemplateListDTO ctldto = (ContractTemplateListDTO) itemId;
                Link btnDownload;
                btnDownload = new Link("Tải về", new ThemeResource(Constants.ICON.DOCX));
                btnDownload.setImmediate(true);
                File file = new File(Constants.PATH_TEMPLATE + ctldto.getPathFile());
                if (file.exists()) {
                    FileDownloader downloader = new FileDownloader(file, ctldto.getPathFile());
                    btnDownload.setResource(downloader);
                    return btnDownload;
                } else {
                    return "";
                }
            }
        });
        CommonFunctionTableFilter.initTable(panelContractTemplateList, headerData,
                beanItemContainerContractTemplateList, BundleUtils.getString("table.list.contractTemplateList"),
                10, "contractTemplateList.header", true, true, false, false, false);
        CommonUtils.convertFieldAppParamTable(panelContractTemplateList.getMainTable(), "status", Constants.APP_PARAMS.COMMON_STATUS, mapStatus);
        CommonUtils.convertFieldAppParamTable(panelContractTemplateList.getMainTable(), "service", Constants.APP_PARAMS.SERVICE_TYPE, mapService);
        CommonUtils.convertFieldAppParamTable(panelContractTemplateList.getMainTable(), "type", Constants.APP_PARAMS.TYPE_CONTRACT, mapType);
        CommonUtils.convertFieldAppParamTable(panelContractTemplateList.getMainTable(), "provider", Constants.APP_PARAMS.PROVIDER, mapProvider);
        panelContractTemplateList.getCoppyButton().setVisible(false);
        panelContractTemplateList.getDeleteButton().setVisible(false);
        tblContractTemplateList.setMultiSelect(false);
    }

    @Override
    public void onDoSearch() {
        List<ConditionBean> lstCondition2Search = getLstConditionBeanSearch();
        try {
            lstContractTemplateListDTO = serviceContractTemplateList.getListContractTemplateListByCondition(lstCondition2Search, 0, Integer.MAX_VALUE, "", "name");
        } catch (Exception e) {
            lstContractTemplateListDTO = Lists.newArrayList();
        }
        beanItemContainerContractTemplateList.removeAllItems();
        if (DataUtil.isListNullOrEmpty(lstContractTemplateListDTO)) {
            CommonMessages.showDataNotFound();
        } else {
            beanItemContainerContractTemplateList.addAll(lstContractTemplateListDTO);
            CommonFunctionTableFilter.refreshTable(panelContractTemplateList, headerData, beanItemContainerContractTemplateList);
        }
        contractTemplateListView.getBtnSearch().setEnabled(true);
    }

    public List<ConditionBean> getLstConditionBeanSearch() {
        List<ConditionBean> lstConditionBean = Lists.newArrayList();
        if (!DataUtil.isStringNullOrEmpty(contractTemplateListView.getTxtCode().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("code");
            conditionBean.setValue(contractTemplateListView.getTxtCode().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(contractTemplateListView.getTxtName().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("name");
            conditionBean.setValue(contractTemplateListView.getTxtName().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(contractTemplateListView.getTxtPathFile().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("pathFile");
            conditionBean.setValue(contractTemplateListView.getTxtPathFile().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }

        if (contractTemplateListView.getCbxStatus().getValue() != null) {
            AppParamsDTO a = (AppParamsDTO) contractTemplateListView.getCbxStatus().getValue();
            if (!DataUtil.isStringNullOrEmpty(a.getParCode())) {
                ConditionBean conditionBean = new ConditionBean();
                conditionBean.setField("status");
                conditionBean.setValue(a.getParCode());
                conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
                conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
                lstConditionBean.add(conditionBean);
            }
        }
        if (contractTemplateListView.getCboService().getValue() != null) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("service");
            conditionBean.setValue(((AppParamsDTO) contractTemplateListView.getCboService().getValue()).getParCode());
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (contractTemplateListView.getCboType().getValue() != null) {
            AppParamsDTO a = (AppParamsDTO) contractTemplateListView.getCboType().getValue();
            if (!DataUtil.isStringNullOrEmpty(a.getParCode())) {
                ConditionBean conditionBean = new ConditionBean();
                conditionBean.setField("type");
                conditionBean.setValue(((AppParamsDTO) contractTemplateListView.getCboType().getValue()).getParCode());
                conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
                conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
                lstConditionBean.add(conditionBean);
            }
        }
        Object appParams = contractTemplateListView.getCboProvider().getValue();
        if (appParams != null
                && !DataUtil.isStringNullOrEmpty(((AppParamsDTO) appParams).getParCode())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("provider");
            conditionBean.setValue(((AppParamsDTO) contractTemplateListView.getCboProvider().getValue()).getParCode());
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        return lstConditionBean;
    }
}
