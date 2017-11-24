/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.controller;

import com.anphat.customer.ui.ImportCustomerUploadForm;
import com.anphat.list.controller.CategoryListController;
import com.cms.login.ws.WSCustomer;
import com.cms.login.ws.WSTermInformation;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Link;
import com.vaadin.ui.Upload;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.component.WindowProgress;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CategoryListDTO;
import com.cms.dto.CustomerDTO;
import com.cms.dto.TermInformationDTO;
import com.cms.login.ws.WSCategoryList;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.ui.CommonButtonClickListener;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonMessages;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.DateUtil;
import com.cms.utils.FileDownloader;
import com.google.common.collect.Lists;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quyen
 */
public class ImportCustomerUploadController implements Serializable {

    private final ImportCustomerUploadForm uploadForm;
    private Upload uFileCommonInfo;
    private Upload uFileTermInfo;
    private ComboBox cboChoiceTypeUpload;
    private ComboBox cboService;
    private ComboBox cboMineName;
//    private TextField tfMineName;
    private Button btnUpload;
    private CustomerInfoUploader receiverCustUploader;
    private TermInformationUploader receiverTermInformationUploader;
    private CommonTableFilterPanel panelTblUploadCommon;
    private CommonTableFilterPanel panelTblUploadTerm;

    private CustomPageTableFilter tblUploadCommon;
    private CustomPageTableFilter tblUploadTerm;
    private Button btnSave;
    private Link linkTemplate;
    private List<AppParamsDTO> lstTypeUpload;
    private List<AppParamsDTO> lstServices;
    private List<CategoryListDTO> lstCategoryList;
    private AppParamsDTO selectedChoiceType;
    private ComboComponent comboBoxUtils;

    private final LinkedHashMap<String, CustomTable.Align> HEADER_UPLOAD_COMMON_INFOR
            = BundleUtils.getHeadersFilter("upload.customer.header");
    private final LinkedHashMap<String, CustomTable.Align> HEADER_UPLOAD_TERM_INFOR
            = BundleUtils.getHeadersFilter("upload.term.infor.header");
    private final LinkedHashMap<String, CustomTable.Align> HEADER_UPLOAD_CONTACT_INFO
            = BundleUtils.getHeadersFilter("upload.contact.info.header");
    private final String TBL_UPLOAD_CAPTION = BundleUtils.getString("customer.import.tbl.upload.caption");

    private BeanItemContainer containerCustomer;
    private BeanItemContainer containerTermInfor;

    private List<CustomerDTO> lstCustomerUpload;
    private List<TermInformationDTO> lstTermUpload;

    private WindowProgress wp;

    public ImportCustomerUploadController(ImportCustomerUploadForm uploadForm) {
        this.uploadForm = uploadForm;
        getDatas();
        buildGridUpload();
        buildTableUpload();
        addListener();
    }

    private void getDatas() {
        if (DataUtil.isListNullOrEmpty(lstTypeUpload)) {
            lstTypeUpload = new ArrayList<>();
            lstTypeUpload.add(new AppParamsDTO("nhap_thong_tin_chung_khach_hang.xlsx", "1", "Thông tin chung của khách hàng", "1", Constants.APP_PARAMS.IMPORT_CUSTOMER));
            lstTypeUpload.add(new AppParamsDTO("nhap_thong_tin_han.xlsx", "2", "Thông tin hạn", "2", Constants.APP_PARAMS.IMPORT_CUSTOMER));
            lstTypeUpload.add(new AppParamsDTO("nhap_thong_tin_danh_ba.xlsx", "3", "Thông tin danh bạ", "3", Constants.APP_PARAMS.IMPORT_CUSTOMER));
        }
        lstServices = DataUtil.getListApParams(Constants.APP_PARAMS.SERVICE_TYPE);
    }

    private void getListMineNameFromService() {

        try {
            List<ConditionBean> lstConditions = new ArrayList<>();
            AppParamsDTO serviceDTO = (AppParamsDTO) cboService.getValue();
            if (serviceDTO != null && !DataUtil.isStringNullOrEmpty(serviceDTO.getParCode())) {
                lstConditions.add(new ConditionBean("service", serviceDTO.getParCode(), ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
            }
            lstConditions.add(new ConditionBean("endDate", DateUtil.date2ddMMyyyyString(new Date()), ConditionBean.Operator.NAME_GREATER_EQUAL, ConditionBean.Type.DATE));
            lstCategoryList = WSCategoryList.getListCategoryListByCondition(lstConditions, 0, Constants.INT_100, Constants.ASC, Constants.CATEGORY_LIST.CODE);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buildGridUpload() {
        cboChoiceTypeUpload = uploadForm.getChoiceTypeUpload();
        cboService = uploadForm.getCboService();
        cboMineName = uploadForm.getCboMineName();
//        tfMineName = uploadForm.getTfMineName();
        uFileTermInfo = uploadForm.getuFileTermInfo();
        uFileCommonInfo = uploadForm.getuFileCommonInfo();
        btnUpload = uploadForm.getBtnUpload();
        btnSave = uploadForm.getBtnSave();
        linkTemplate = uploadForm.getLinkTemplate();

        comboBoxUtils = new ComboComponent();
        comboBoxUtils.fillDataCombo(cboChoiceTypeUpload, Constants.NULL,
                Constants.CUSTOMER.IMPORT_COMMON_INFO, lstTypeUpload,
                Constants.APP_PARAMS.IMPORT_CUSTOMER);
        //Khoi tao comboBox dich vu
        String valueDefault = Constants.NULL;
        if (!DataUtil.isListNullOrEmpty(lstServices)) {
            valueDefault = lstServices.get(0).getParCode();
        }
        comboBoxUtils.fillDataCombo(cboService, Constants.NULL,
                valueDefault, lstServices, Constants.APP_PARAMS.SERVICE_TYPE);
        setData2CboMineName();
        //Gia tri mac dinh la nhap thong tin khach hang
        selectedChoiceType = (AppParamsDTO) cboChoiceTypeUpload.getValue();
        if (selectedChoiceType != null) {
            File file = new File(Constants.PATH_TEMPLATE + selectedChoiceType.getDescription());
            FileDownloader downloader = new FileDownloader(file, selectedChoiceType.getDescription());
            linkTemplate.setResource(downloader);
            uploadForm.setVisibleUpload(false);
        }
    }

    private void setData2CboMineName() {
        getListMineNameFromService();
        comboBoxUtils.setValues(cboMineName, lstCategoryList, Constants.CATEGORY_LIST.NAME);
    }

    private void buildTableUpload() {
        panelTblUploadCommon = uploadForm.getTblUploadCommon();
        panelTblUploadCommon.getToolbar().setVisible(false);
        tblUploadCommon = panelTblUploadCommon.getMainTable();
        panelTblUploadTerm = uploadForm.getTblUploadTerm();
        panelTblUploadTerm.getToolbar().setVisible(false);
        tblUploadTerm = panelTblUploadTerm.getMainTable();
        panelTblUploadTerm.setVisible(false);
        containerCustomer = new BeanItemContainer(CustomerDTO.class);
        containerTermInfor = new BeanItemContainer(TermInformationDTO.class);
        CommonFunctionTableFilter.initTable(panelTblUploadCommon, HEADER_UPLOAD_COMMON_INFOR, containerCustomer, TBL_UPLOAD_CAPTION, 10, Constants.CAPTION.CUSTOMER);
        CommonFunctionTableFilter.initTable(panelTblUploadTerm, HEADER_UPLOAD_TERM_INFOR, containerTermInfor, TBL_UPLOAD_CAPTION, 10, Constants.CAPTION.TERM_INFORMATION);
    }

    private void addListener() {
        addListenerCboChoiceType();
        addListenerUpload();
        addListenerBtnUpload();
        //Them nut them danh sach han
        addListenerBtnAddMineName();
        addListenerValueServiceChange();
    }

    private void addListenerCboChoiceType() {
        cboChoiceTypeUpload.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                selectedChoiceType = (AppParamsDTO) event.getProperty().getValue();
                if (!DataUtil.isStringNullOrEmpty(selectedChoiceType.getParCode())) {
                    //Doi header cua bang
                    if (Constants.CUSTOMER.IMPORT_COMMON_INFO.equalsIgnoreCase(selectedChoiceType.getParCode())) {
                        containerCustomer.removeAllItems();
                        CommonFunctionTableFilter.refreshTable(panelTblUploadCommon, HEADER_UPLOAD_COMMON_INFOR, containerCustomer);
//                        uFileCommonInfo.setVisible(true);
//                        uFileTermInfo.setVisible(false);
//                        panelTblUploadTerm.setVisible(false);
//                        panelTblUploadCommon.setVisible(true);
                        uploadForm.setVisibleUpload(false);
                    } else {
//                        if ("2".equalsIgnoreCase(selectedChoiceType.getParCode())) {
                        containerTermInfor.removeAllItems();
                        CommonFunctionTableFilter.refreshTable(panelTblUploadTerm, HEADER_UPLOAD_TERM_INFOR, containerTermInfor);
//                        } else {
//                            containerTermInfor.removeAllItems();
//                            CommonFunctionTableFilter.refreshTable(panelTblUploadTerm, HEADER_UPLOAD_CONTACT_INFO, containerTermInfor);
//                        }
                        receiverTermInformationUploader.setTypeOfTermInformation(selectedChoiceType.getParCode());
                        uploadForm.setVisibleUpload(true);
//                        uFileCommonInfo.setVisible(false);
//                        uFileTermInfo.setVisible(true);
//                        panelTblUploadTerm.setVisible(true);
//                        panelTblUploadCommon.setVisible(false);
                    }
                    //Doi file bieu mau tai bang
                    selectedChoiceType = (AppParamsDTO) cboChoiceTypeUpload.getValue();
                    if (!DataUtil.isStringNullOrEmpty(selectedChoiceType.getParCode())) {
                        //Doi file bieu mau tai bang
                        File file = new File(Constants.PATH_TEMPLATE + selectedChoiceType.getDescription());
                        FileDownloader downloader = new FileDownloader(file, selectedChoiceType.getDescription());
                        linkTemplate.setResource(downloader);
                        //Doi header cua bang
                    }
                }
            }
        });
    }

    private void addListenerUpload() {
        receiverCustUploader = new CustomerInfoUploader(uFileCommonInfo, wp, panelTblUploadCommon, HEADER_UPLOAD_COMMON_INFOR);
        receiverTermInformationUploader = new TermInformationUploader(uFileTermInfo, wp, panelTblUploadTerm, HEADER_UPLOAD_TERM_INFOR);
        uFileCommonInfo.setReceiver(receiverCustUploader);
        uFileTermInfo.setReceiver(receiverTermInformationUploader);
        uFileTermInfo.setVisible(false);
    }

    private void addListenerValueServiceChange() {
        cboService.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                setData2CboMineName();
            }
        });
    }

    private void addListenerBtnUpload() {
        btnUpload.addClickListener(new CommonButtonClickListener() {
            @Override
            public void execute() {
                uploadCustomerData();
            }

            @Override
            public boolean isValidated() {
                getDataFromTblCustCommonInfor();
                if (uFileCommonInfo.isVisible()) {
                    if (DataUtil.isListNullOrEmpty(lstCustomerUpload)) {
                        CommonMessages.showChooseFileUpload();
                        return false;
                    }
                } else if ((uFileTermInfo.isVisible())) {
                    if (DataUtil.isListNullOrEmpty(lstTermUpload)) {
                        CommonMessages.showChooseFileUpload();
                        return false;
                    } else if (isValidateInput()) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }
        }
        );

    }

    private void addListenerBtnAddMineName() {
        uploadForm.addBtnAddMineNameListner(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                CategoryListController categoryListController = new CategoryListController();
                categoryListController.InsertOrUpdate(new CategoryListDTO(), false);
                categoryListController.setCboMineName(cboMineName);
//                if (categoryListController.isAddSuccessed()) {
//                    getListMineName();
//                    setData2CboMineName();
//                }
                event.getButton().setEnabled(true);
            }
        });
    }

    protected void getDataFromTblCustCommonInfor() {
        if (uFileCommonInfo.isVisible()) {
            if (DataUtil.isListNullOrEmpty(lstCustomerUpload)) {
                lstCustomerUpload = Lists.newArrayList();
            } else {
                lstCustomerUpload.clear();
            }
            lstCustomerUpload.addAll((Collection<? extends CustomerDTO>) tblUploadCommon.getItemIds());
        } else {
            if (DataUtil.isListNullOrEmpty(lstTermUpload)) {
                lstTermUpload = Lists.newArrayList();
            } else {
                lstTermUpload.clear();
            }
            lstTermUpload.addAll((Collection<? extends TermInformationDTO>) tblUploadTerm.getItemIds());
        }
    }

    protected void uploadCustomerData() {
        if (uFileCommonInfo.isVisible()) {
            List<CustomerDTO> lstErrors = new ArrayList<>();
            if (DataUtil.isListNullOrEmpty(lstCustomerUpload)) {
                CommonMessages.showChooseFileUpload();
                return;
            } else if (lstCustomerUpload.size() > 1000) {
                int size = lstCustomerUpload.size();
                int numberOfSpit = size / 1000;
                int itemPerSpit = 1000;
                int postFrom;
                int postTo;
                List<CustomerDTO> lstErrorsTemp;
                for (int i = 0; i <= numberOfSpit; i++) {
                    postFrom = itemPerSpit * i;
                    postTo = itemPerSpit * (i + 1);
                    if (postTo > size) {
                        postTo = size;
                    }
                    lstErrorsTemp = WSCustomer.newInsertOrUpdateListCustomer(lstCustomerUpload.subList(postFrom, postTo));
                    if (!DataUtil.isListNullOrEmpty(lstErrorsTemp)) {
                        lstErrors.addAll(lstErrorsTemp);
                    }
                }
            } else {
                lstErrors = WSCustomer.newInsertOrUpdateListCustomer(lstCustomerUpload);
            }
            if (lstErrors == null) {
                lstErrors = new ArrayList<>();
            }
            CommonMessages.showHumanizedMessage(BundleUtils.getString("inform.upload.result")
                    .replace("@s", String.valueOf(lstCustomerUpload.size() - lstErrors.size()))
                    .replace("@f", String.valueOf(lstErrors.size())));
            containerCustomer.removeAllItems();
            if (!DataUtil.isListNullOrEmpty(lstErrors)) {
                containerCustomer.addAll(lstErrors);
            }
            CommonFunctionTableFilter.refreshTable(panelTblUploadCommon, HEADER_UPLOAD_COMMON_INFOR, containerCustomer);
        } else if ((uFileTermInfo.isVisible())) {

            if (DataUtil.isListNullOrEmpty(lstTermUpload)) {
                CommonMessages.showChooseFileUpload();
            } else if (isValidateInput()) {
                AppParamsDTO serviceDTO = (AppParamsDTO) cboService.getValue();
                String service = serviceDTO.getParCode();
//                String mineName = DataUtil.getStringNullOrZero(tfMineName.getValue());
                CategoryListDTO category = (CategoryListDTO) cboMineName.getValue();
                String mineName = DataUtil.getStringNullOrZero(category.getId());
                for (TermInformationDTO i : lstTermUpload) {
                    i.setService(service);
                    i.setMineName(mineName);
                }
                ResultDTO result = WSTermInformation.insertListTermInformation(lstTermUpload);

                if (result != null && Constants.SUCCESS.equalsIgnoreCase(result.getMessage())) {
                    CommonMessages.showMessageImportSuccess("term.information", result.getQuantitySucc());
                    containerTermInfor.removeAllItems();
                    CommonFunctionTableFilter.refreshTable(panelTblUploadTerm, HEADER_UPLOAD_TERM_INFOR, containerTermInfor);
                } else {
                    CommonMessages.showInsertFail(BundleUtils.getString("term.information"));
                }
            }
        }
    }

    private boolean isValidateInput() {
        if (DataUtil.isNullObject(cboService.getValue())) {
            cboService.focus();
            return false;
        }
//        if (DataUtil.isStringNullOrEmpty(tfMineName.getValue())) {
//            tfMineName.setRequiredError(CommonMessages.messageRequire("customer.mineName"));
//            tfMineName.focus();
//            return false;
//        }
        CategoryListDTO category = (CategoryListDTO) cboMineName.getValue();
        if (DataUtil.isNullObject(category) || DataUtil.isStringNullOrEmpty(category.getCode())) {
            cboMineName.setRequiredError(CommonMessages.messageRequire("customer.mineName"));
            cboMineName.focus();
            return false;
        }
        return true;
    }
}
