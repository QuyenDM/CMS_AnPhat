/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.customer.controller.SearchCustomerController;
import com.anphat.list.ui.CustomerDialog;
import com.cms.common.ws.WSAppParams;
import com.cms.login.ws.WSCustomer;
import com.cms.login.ws.WSCustomerStatus;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Runo;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CategoryListDTO;
import com.cms.dto.CustomerDTO;
import com.cms.dto.CustomerStatusDTO;
import com.cms.dto.StaffDTO;
import com.cms.dto.TaxAuthorityDTO;
import com.cms.login.ws.WSCategoryList;
import com.cms.login.ws.WSStaff;
import com.cms.login.ws.WSTaxAuthority;
import com.cms.ui.CommonButtonClickListener;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.ShortcutUtils;
import com.cms.utils.StringUtils;
import com.cms.utils.TableUtils;
import com.cms.view.MapStaffCustomerView;
import com.google.common.collect.Lists;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.MethodProperty;
import com.vfw5.base.utils.DateUtil;
import com.vwf5.base.utils.ConditionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.NullValueInNestedPathException;
import org.vaadin.addons.comboboxmultiselect.ComboBoxMultiselect;

/**
 * Lop Controller cho dialog Gan nhan vien khach hang. Dieu kien la phai chon
 * danh sach cac nhan vien truoc khi gan
 *
 * @author quyen
 */
public class MapStaffCustomerViewController implements Serializable {

    private final MapStaffCustomerView mapStaffCustomerView;
    private CommonTableFilterPanel panelTableCustomer;
    private CommonTableFilterPanel panelTableMapStaffCustomer;
    private CustomPageTableFilter tblCustomer;
    private CustomPageTableFilter tblStaffMapCustomer;
    private List<AppParamsDTO> lstAllAppParams;
    private List<StaffDTO> lstStaffs;
    private List<StaffDTO> lstStaffsSelected;
    private List<CustomerDTO> lstCustomerSelected;
    private List<CustomerStatusDTO> lstCustomerStatusSelected;
    private List<AppParamsDTO> lstServices;
    private List<AppParamsDTO> lstCustomerStatus;
    private List<AppParamsDTO> lstMaxSearch;
    private List<CategoryListDTO> lstCategoryList;
    private List<TaxAuthorityDTO> lstTaxAuthority;
    private Map<String, String> mapServices;
    private Map<String, String> mapCustServiceStatus;
    private BeanItemContainer containerCustomer;
    private BeanItemContainer containerMapStaffCustomer;
    private static final LinkedHashMap<String, CustomTable.Align> HEADER_CUSTOMER
            = BundleUtils.getHeadersFilter("search.customer.header.mapstaff");
    private static final LinkedHashMap<String, CustomTable.Align> HEADER_MAP_STAFF_CUSTOMER
            = BundleUtils.getHeadersFilter("map.staff.customer.header");
    private final String CAPTION_CUSTOMER
            = BundleUtils.getString("tbl.caption.list.customer");
    private final String CAPTION_MAP_STAFF_CUSTOMER
            = BundleUtils.getString("mapStaffCustomerForm.gridTitle");
    //Cac nut thao tac tren dialog
    private Button btnSearch;
    private Button btnReset;
    private Button btnExecute;
    private Button btnSave;
    //Cac thanh phan cua grid tim kiem khach hang
    private ComboBox cbxServices;
    private ComboBox cbxMaxSearch;
    private ComboBox cbxMineName;
    private ComboBox cbxCustomerStatus;
    private ComboComponent comboUtils;
    private List<AppParamsDTO> lstProvider;
    private TableUtils tblUtils;
    private ComboBoxMultiselect cboMultiProvider;
    private ComboBoxMultiselect cboMultiCity;
    private List<String> lstTaxCodeExecuted;
    private List<CustomerDTO> lstCustomersExcuted;
    private Map<String, String> mapTaxAuthority;
    private List<TaxAuthorityDTO> lstTaxAuthorityToMap;
    private boolean isMineNameChanged = true;
    private boolean isCityChanged = true;
    private boolean isProviderChanged = true;

    public MapStaffCustomerViewController(MapStaffCustomerView mapStaffCustomerView) {
        this.mapStaffCustomerView = mapStaffCustomerView;
        initComponents();
    }

    /**
     * Khoi tao cac thanh phan cua dialog
     */
    private void initComponents() {
        getDatas();
        initSearchGrid();
        initTables();
        addActionListeners();
    }

    //Lay du lieu danh sach dich vu va danh sach trang thai khach hang - dich vu
    private void getDatas() {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setStatus(Constants.ACTIVE);
        staffDTO.setStaffType(Constants.STAFF.STAFF_TYPE_3);
        lstStaffs = WSStaff.getListStaffDTO(staffDTO, 0, 1000, Constants.ASC, Constants.STAFF.CODE);
        AppParamsDTO appParams = new AppParamsDTO();
        appParams.setStatus(Constants.ACTIVE);
        lstAllAppParams = WSAppParams.getListAppParamsDTO(appParams, 0, 1000, Constants.ASC, Constants.APP_PARAMS.PAR_ORDER);

        cbxServices = mapStaffCustomerView.getCbxService();
        cbxMaxSearch = mapStaffCustomerView.getCbxMaxSearch();
        cbxMineName = mapStaffCustomerView.getCbxMineName();
        lstServices = DataUtil.getListApParams(lstAllAppParams, Constants.APP_PARAMS.SERVICE_TYPE);
        cbxCustomerStatus = mapStaffCustomerView.getCbxStatus();
        lstCustomerStatus = DataUtil.getListApParams(lstAllAppParams, Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS);
        lstMaxSearch = DataUtil.getListApParams(lstAllAppParams, Constants.APP_PARAMS.MAX_SEARCH);
        try {
            mapServices = DataUtil.buildHasmap(lstServices, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
            mapCustServiceStatus = DataUtil.buildHasmap(lstCustomerStatus, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException ex) {
            Logger.getLogger(MapStaffCustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboUtils = new ComboComponent();
        //Khoi tao comboBox dich vu
        String serviceDefault = Constants.NULL;
        if (!DataUtil.isListNullOrEmpty(lstServices)) {
            serviceDefault = lstServices.get(0).getParCode();
        }
        comboUtils.fillDataCombo(cbxServices, Constants.NULL, serviceDefault,
                lstServices, Constants.APP_PARAMS.SERVICE_TYPE);
        List<String> lstMineName = getListCategoryFromService();

        String mineName = DataUtil.isListNullOrEmpty(lstMineName) ? Constants.NULL : DataUtil.convertList2StringToSearchConditionIN(lstMineName);
        Map<String, String> map = new HashMap<>();
        getTaxAuthorityFromMineName(mineName, map);
        getProviderFromMineName(mineName, map);
        //ComboBox Multiselect
        cboMultiProvider = mapStaffCustomerView.getCboMulProvider();
        cboMultiCity = mapStaffCustomerView.getCboMulCity();
        //
        lstCustomersExcuted = new ArrayList<>();
        lstCustomerStatusSelected = new ArrayList<>();
        // Get map tinh/thanh pho
        lstTaxAuthorityToMap = WSTaxAuthority.getListProvineTaxAuthority();
        if (!DataUtil.isListNullOrEmpty(lstTaxAuthorityToMap)) {
            try {
                mapTaxAuthority = DataUtil.buildHasmap(lstTaxAuthorityToMap, Constants.TAXAUTHORITY.MA_CQT, Constants.TAXAUTHORITY.TEN_CQT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Khoi tao cac thanh phan cua grid tim kiem
    private void initSearchGrid() {
        //Khoi tao comboBox dich vu
        String serviceDefault = Constants.NULL;
        if (!DataUtil.isListNullOrEmpty(lstServices)) {
            serviceDefault = lstServices.get(0).getParCode();
        }
        comboUtils.fillDataCombo(cbxServices, Constants.NULL, serviceDefault,
                lstServices, Constants.APP_PARAMS.SERVICE_TYPE);
        //ComboBox maxSearch
        String maxSearchDefault = Constants.NULL;
        if (!DataUtil.isListNullOrEmpty(lstMaxSearch)) {
            maxSearchDefault = lstMaxSearch.get(0).getParCode();
        }
        comboUtils.fillDataCombo(cbxMaxSearch, Constants.ALL, maxSearchDefault,
                lstMaxSearch, Constants.APP_PARAMS.MAX_SEARCH);
        cbxMaxSearch.setNewItemsAllowed(true);
        //ComboBox ncc
//        comboUtils.fillDataCombo(cbxProvider,
//                Constants.ALL, Constants.NULL,
//                lstProvider, Constants.APP_PARAMS.PROVIDER);
        //ComboBox Tinh
//        comboUtils.setValues(cbxCity, lstTaxAuthority, Constants.TAXAUTHORITY.TEN_CQT, true);
        //ComboBox MineName
        comboUtils.setValues(cbxMineName, lstCategoryList, Constants.CATEGORY_LIST.NAME, true);

        //Khoi tao comboBox customerStatus
        String custStatusDefault = Constants.NULL;
        if (!DataUtil.isListNullOrEmpty(lstCustomerStatus)) {
            custStatusDefault = lstCustomerStatus.get(0).getParCode();
        }
        comboUtils.fillDataCombo(cbxCustomerStatus, Constants.NULL,
                custStatusDefault, lstCustomerStatus, Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS);
        cboMultiProvider.removeAllItems();
        cboMultiCity.removeAllItems();
        BeanItemContainer<AppParamsDTO> beanContainerProvider = new BeanItemContainer<>(AppParamsDTO.class);
        beanContainerProvider.addAll(lstProvider);
        cboMultiProvider.setContainerDataSource(beanContainerProvider);
        BeanItemContainer<TaxAuthorityDTO> beanContainerTaxAuthoriry = new BeanItemContainer<>(TaxAuthorityDTO.class);
        if (!DataUtil.isListNullOrEmpty(lstTaxAuthority)) {
            beanContainerTaxAuthoriry.addAll(lstTaxAuthority);
        }
        cboMultiCity.setContainerDataSource(beanContainerTaxAuthoriry);
    }

    /**
     * Khoi tao cac bang
     */
    private void initTables() {
        tblUtils = new TableUtils();
        //Khoi tao bang khach hang
        panelTableCustomer = mapStaffCustomerView.getPanelTblCustomer();
        tblCustomer = panelTableCustomer.getMainTable();
        tblUtils.generateColumn(tblCustomer);
        containerCustomer = new BeanItemContainer(CustomerDTO.class);
        CommonFunctionTableFilter.initTable(panelTableCustomer, HEADER_CUSTOMER,
                containerCustomer, CAPTION_CUSTOMER, 5, "customer");
        CommonUtils.convertFieldTable(tblCustomer, "taxAuthority", mapTaxAuthority);
//        name#1,startTime#2,endTime#2,taxAuthority#1
        tblCustomer.setColumnExpandRatio("taxCode", 1);
        tblCustomer.setColumnExpandRatio("name", 2);
        tblCustomer.setColumnExpandRatio("startTime", 1);
        tblCustomer.setColumnExpandRatio("endTime", 1);
        tblCustomer.setColumnExpandRatio("taxAuthority", 1);
        //Khoi tao bang khach hang
        panelTableMapStaffCustomer = mapStaffCustomerView.getPanelTblCustomerStatus();
        tblStaffMapCustomer = panelTableMapStaffCustomer.getMainTable();
        tblUtils.generateColumn(tblStaffMapCustomer);
        containerMapStaffCustomer = new BeanItemContainer(StaffDTO.class);
        containerMapStaffCustomer.addAll(lstStaffs);
        //Them nut chi tiet
        tblStaffMapCustomer.addGeneratedColumn("detail", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                try {
                    final StaffDTO staff = (StaffDTO) itemId;

                    if (DataUtil.isStringNullOrEmpty(staff.getQuanlity()) || "0".equals(staff.getQuanlity())) {
                        return null;
                    } else {
                        Button btnDetail = new Button(BundleUtils.getString("statistic.list.detail"));
                        btnDetail.setDisableOnClick(true);
                        btnDetail.addStyleName(Runo.BUTTON_LINK);
                        btnDetail.addStyleName("v-link-button-left");
                        btnDetail.addClickListener(new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                CustomerDialog customerDialog = new CustomerDialog(mapServices, mapCustServiceStatus);
                                customerDialog.setData2Table(staff.getLstCustomers());
                                UI.getCurrent().addWindow(customerDialog);
                                event.getButton().setEnabled(true);
                            }
                        });
                        return btnDetail;
                    }
                } catch (NullValueInNestedPathException nvnpe) {
                    return null;
                }
            }
        });
        //Them nut huy bo
        tblStaffMapCustomer.addGeneratedColumn("cancel", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(final CustomTable source, Object itemId, Object columnId) {
                try {
                    final StaffDTO staff = (StaffDTO) itemId;
                    if (DataUtil.isStringNullOrEmpty(staff.getQuanlity()) || "0".equals(staff.getQuanlity())) {
                        return null;
                    } else {
                        Button btnDetail = new Button(BundleUtils.getString("statistic.list.cancel"));
                        btnDetail.setDisableOnClick(true);
                        btnDetail.addStyleName(Runo.BUTTON_LINK);
                        btnDetail.addStyleName("v-link-button-left");
                        btnDetail.addClickListener(new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                doReset(staff);
                            }
                        });
                        return btnDetail;
                    }
                } catch (NullValueInNestedPathException nvnpe) {
                    return null;
                }
            }
        });
        CommonFunctionTableFilter.initTable(panelTableMapStaffCustomer,
                HEADER_MAP_STAFF_CUSTOMER, containerMapStaffCustomer,
                CAPTION_MAP_STAFF_CUSTOMER, 5, "customerStatusForm");

    }

    /**
     * Khoi tao cac action listener cho cac nut
     */
    private void addActionListeners() {
        addBtnSearchClickListener();
        addBtnResetClickListener();
        addBtnExecuteClickListener();
        addBtnSaveClickListener();
        addListenerValueChangeCboCategoryList();
    }

    //Nut tim kiem
    private void addBtnSearchClickListener() {
        btnSearch = mapStaffCustomerView.getBtnSearch();
        ShortcutUtils.setShortcutKey(btnSearch);
        btnSearch.addClickListener(new CommonButtonClickListener() {
            @Override
            public void execute() {
                List<ConditionBean> lstConditionBeans = mapStaffCustomerView.getLstCondition2Search();

                if (DataUtil.isListNullOrEmpty(lstCustomersExcuted)) {
                    lstTaxCodeExecuted = null;
                } else {
                    lstTaxCodeExecuted = DataUtil.getTaxCodes(lstCustomersExcuted);
                }
                List<CustomerDTO> lstCustSearched = null;
                if (!DataUtil.isListNullOrEmpty(lstConditionBeans)) {
                    lstCustSearched = WSCustomer.getListCustomerFromTermInfoWithoutTaxCodes(lstConditionBeans, lstTaxCodeExecuted);
                }
                setData2TableCustomer(lstCustSearched);
                if (DataUtil.isListNullOrEmpty(lstCustSearched)) {
                    CommonMessages.showDataNotFound();
                }
            }

            @Override
            public boolean isValidated() {
                CategoryListDTO mineNameDTO = (CategoryListDTO) cbxMineName.getValue();
                if (mineNameDTO != null && !DataUtil.isStringNullOrEmpty(mineNameDTO.getId())) {
                    return true;
                } else {
                    cbxMineName.focus();
                    CommonMessages.showMessageRequired("customer.mineName");
                    return false;
                }
            }

        });
    }

    //Nut Reset
    private void addBtnResetClickListener() {
        btnReset = mapStaffCustomerView.getBtnReset();
        btnReset.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                mapStaffCustomerView.doResetData();
                initSearchGrid();
                setData2TableStaff(lstStaffs);
                lstCustomersExcuted = new ArrayList<>();
                lstCustomerStatusSelected = new ArrayList<>();
                event.getButton().setEnabled(true);
            }
        });
    }

    //Nut thuc hien phan bo
    private void addBtnExecuteClickListener() {
        btnExecute = mapStaffCustomerView.getBtnExecute();
        btnExecute.addClickListener(new CommonButtonClickListener() {
            @Override
            public void execute() {
                Set<CustomerDTO> collectionCustomers = (Set) tblCustomer.getValue();
                Set<StaffDTO> collectionStaffs = (Set) tblStaffMapCustomer.getValue();
                lstCustomerSelected = Lists.newArrayList(collectionCustomers);
                lstStaffsSelected = Lists.newArrayList(collectionStaffs);
                if (DataUtil.isListNullOrEmpty(lstCustomerSelected)) {
                    CommonMessages.showWarningMessage(BundleUtils.getString("notification.staff.customer.choice.require"));
                } else if (DataUtil.isListNullOrEmpty(lstStaffsSelected)) {
                    CommonMessages.showWarningMessage(BundleUtils.getString("notification.staff.choice.require"));
                } else if (assignCustomerForStaff(lstCustomerSelected, lstStaffsSelected)) {
                    setData2TableStaff(lstStaffs);
                    setData2TableCustomer(null);
                } else {
                    CommonMessages.showErrorMessage(BundleUtils.getString("map.customer.staff.error"));
                }
            }
        });
    }

    //Nut luu lai
    private void addBtnSaveClickListener() {
        btnSave = mapStaffCustomerView.getBtnSave();
        btnSave.addClickListener(new CommonButtonClickListener() {
            @Override
            public void execute() {
                doSave(lstStaffs);
            }
        });
    }

    /**
     * Set du lieu lai cho bang nhan vien
     */
    private void setData2TableStaff(List<StaffDTO> lstStaffs) {
        containerMapStaffCustomer.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstStaffs)) {
            containerMapStaffCustomer.addAll(lstStaffs);
        }
        CommonFunctionTableFilter.refreshTable(panelTableMapStaffCustomer, HEADER_MAP_STAFF_CUSTOMER, containerMapStaffCustomer);
        tblStaffMapCustomer.sort(new Object[]{"quanlity"}, new boolean[]{false});
    }

    /**
     * Set du lieu cho bang khach hang
     *
     * @param lstCustomers
     */
    private void setData2TableCustomer(List<CustomerDTO> lstCustomers) {
        containerCustomer.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstCustomers)) {
//            lstCustomers = removeDupplicateCustomer(lstCustomers);
            containerCustomer.addAll(lstCustomers);
        }
        CommonFunctionTableFilter.refreshTable(panelTableCustomer, HEADER_CUSTOMER, containerCustomer);
    }

    /**
     * Thuc hien gan khach hang cho nhan vien
     *
     * @param lstCustomers
     * @param lstStaffs
     * @return
     */
    private boolean assignCustomerForStaff(
            List<CustomerDTO> lstCustomers, List<StaffDTO> lstStaffs) {
        try {
            int sizeOfCustomer = lstCustomers.size();
            int sizeOfStaff = lstStaffs.size();
            int qualityCustPerStaff = sizeOfCustomer / sizeOfStaff;
            int module = sizeOfCustomer % sizeOfStaff;
            int size;
            List<CustomerDTO> lstCustTemp;
            if (qualityCustPerStaff > 0) {
                if (module != 0) {
                    size = sizeOfStaff - 1;
                    lstCustTemp = new ArrayList<>(lstCustomers.subList((sizeOfStaff - 1) * qualityCustPerStaff, sizeOfCustomer));
                    setLstCustomerToStaff(lstStaffs.get(size), lstCustTemp);

                } else {
                    size = sizeOfStaff;
                }
                for (int i = 0; i < size; i++) {
                    lstCustTemp = new ArrayList<>(lstCustomers.subList(i * qualityCustPerStaff, (i + 1) * qualityCustPerStaff));
                    setLstCustomerToStaff(lstStaffs.get(i), lstCustTemp);

                }
            } else {
                for (int i = 0; i < sizeOfCustomer; i++) {
                    lstCustTemp = new ArrayList<>(lstCustomers.subList(i, i + 1));
                    setLstCustomerToStaff(lstStaffs.get(i), lstCustTemp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Thuc hien luu thong tin gan khach hang
     *
     * @param lstStaffDTO
     */
    public void doSave(List<StaffDTO> lstStaffDTO) {
        List<CustomerStatusDTO> lstCustomerStatusDTOs = new ArrayList<>();
        for (StaffDTO s : lstStaffDTO) {
            if (!DataUtil.isListNullOrEmpty(s.getLstCustomers())) {
                lstCustomerStatusDTOs.addAll(s.getLstCustomers());
            }
        }
        if (DataUtil.isListNullOrEmpty(lstCustomerStatusDTOs)) {
            CommonMessages.showWarningMessage(BundleUtils.getString("warning.choice.customer.before.execute"));
            return;
        }
        String result = WSCustomerStatus.insertOrUpdateListCustomerStatus(lstCustomerStatusDTOs);
        if (Constants.SUCCESS.equalsIgnoreCase(result)) {
            CommonMessages.showHumanizedMessage(BundleUtils.getString("customer.assign.customer.successed"));
            //Set lai du lieu cho bang khach hang
            setData2TableCustomer(null);
            //Xoa du lieu da gan cho bang nhan vien
            for (StaffDTO s : lstStaffDTO) {
                s.setLstCustomers(null);
                s.setLstCustDTO(null);
                s.setQuanlity("0");
            }
            setData2TableStaff(lstStaffDTO);
            //Chọn lại ds
            Object selectedmineName = cbxMineName.getValue();
            isMineNameChanged = false;
            cbxMineName.select(null);
            cbxMineName.select(selectedmineName);
        } else {
            CommonMessages.showMessageFail(BundleUtils.getString("customer.assign.customer.failed"));
        }
    }

    private void addListenerValueChangeCboCategoryList() {
//        addValueChangeListener(new Property.ValueChangeListener() {
//            @Override
//            public void valueChange(Property.ValueChangeEvent event) {
//                CategoryListDTO categoryListDTO = (CategoryListDTO) cbxMineName.getValue();
//                if (categoryListDTO != null) {
////                    isCityChanged = false;
////                    cboMultiCity.removeAllItems();
////                    isProviderChanged = false;
////                    cboMultiProvider.removeAllItems();
//                    getTaxAuthorityFromCondition(categoryListDTO);
//                    setValueToMultiTaxAuthority(lstTaxAuthority);
//                    getProviderFromCondition(categoryListDTO);
//                    setValueToMultiProvider(lstProvider);
//                }
//            }
//        });
        cbxServices.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                getListCategoryFromService();
                comboUtils.setValues(cbxMineName, lstCategoryList, Constants.CATEGORY_LIST.NAME, true);
            }
        });
        addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (isMineNameChanged) {
                    CategoryListDTO categoryListDTO = (CategoryListDTO) cbxMineName.getValue();
                    if (categoryListDTO != null) {
                        isCityChanged = false;
                        cboMultiCity.removeAllItems();
                        isProviderChanged = false;
                        cboMultiProvider.removeAllItems();
                        getTaxAuthorityFromCondition(categoryListDTO);
                        setValueToMultiTaxAuthority(lstTaxAuthority);
                        getProviderFromCondition(categoryListDTO);
                        setValueToMultiProvider(lstProvider);
                        isCityChanged = true;
                        isProviderChanged = true;
                    } else {
                        isCityChanged = false;
                        cboMultiCity.removeAllItems();
                        isProviderChanged = false;
                        cboMultiProvider.removeAllItems();
                    }
                } else {
                    isMineNameChanged = true;
                }
            }
        });
        cboMultiCity.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (isCityChanged) {
                    CategoryListDTO categoryListDTO = (CategoryListDTO) cbxMineName.getValue();
                    if (categoryListDTO != null) {
                        getProviderFromCondition(categoryListDTO);
                        setValueToMultiProvider(lstProvider);
                    }
                } else {
                    isCityChanged = true;
                }
            }
        });
        cboMultiProvider.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (isProviderChanged) {
                    CategoryListDTO categoryListDTO = (CategoryListDTO) cbxMineName.getValue();
                    if (categoryListDTO != null) {
                        getTaxAuthorityFromCondition(categoryListDTO);
                        setValueToMultiTaxAuthority(lstTaxAuthority);
                    }
                } else {
                    isProviderChanged = true;
                }
            }
        });

    }

    private void getProviderFromCondition(CategoryListDTO categoryListDTO) {
        String mineName = categoryListDTO.getId();
        Map<String, String> map = getValueFromSearchForm();
        if (mineName == null) {
            try {
                List<String> lstMineName = DataUtil.getListValueFromList(lstCategoryList, Constants.CATEGORY_LIST.ID);
                getProviderFromMineName(DataUtil.convertList2StringToSearchConditionIN(lstMineName), map);
            } catch (NoSuchMethodException | IllegalAccessException ex) {
                Logger.getLogger(MapStaffCustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            getProviderFromMineName(mineName, map);
        }
    }

    private void getTaxAuthorityFromCondition(CategoryListDTO categoryListDTO) {
        String mineName = categoryListDTO.getId();
        Map<String, String> map = getValueFromSearchForm();
        if (mineName == null) {
            try {
                List<String> lstMineName = DataUtil.getListValueFromList(lstCategoryList, Constants.CATEGORY_LIST.ID);
                getTaxAuthorityFromMineName(DataUtil.convertList2StringToSearchConditionIN(lstMineName), map);
            } catch (NoSuchMethodException | IllegalAccessException ex) {
                Logger.getLogger(MapStaffCustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            getTaxAuthorityFromMineName(mineName, map);
        }
    }

    Container beanContainerProvider;

    private void setValueToMultiProvider(List<AppParamsDTO> lstProvider) {
        Collection selectedValue = (Collection) cboMultiProvider.getValue();
        isProviderChanged = false;
        cboMultiProvider.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstProvider)) {
            cboMultiProvider.addItems(lstProvider);
            Map<String, AppParamsDTO> mapProvider = new HashMap<>();
            try {
                mapProvider = DataUtil.buildHasmap(lstProvider, "parCode");
            } catch (Exception e) {
            }
            if (selectedValue != null && !selectedValue.isEmpty()) {
                AppParamsDTO temp;
                List<AppParamsDTO> lst = new ArrayList<>();
                for (Object o : selectedValue) {
                    temp = (AppParamsDTO) o;
                    if (mapProvider.containsKey(temp.getParCode())) {
                        lst.add(mapProvider.get(temp.getParCode()));
                    }
                }
                if (!DataUtil.isListNullOrEmpty(lst)) {
                    Set<Object> set = new HashSet<>();
                    set.addAll(lst);
                    if (lst.size() != selectedValue.size()) {
                        isProviderChanged = true;
                    } else {
                        isProviderChanged = false;
                    }
                    cboMultiProvider.setValue(set);
                }
            }
        }
    }

    private void setValueToMultiTaxAuthority(List<TaxAuthorityDTO> lstTaxAuthority) {
        Collection collection = (Collection) cboMultiCity.getValue();
        isCityChanged = false;
        cboMultiCity.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstTaxAuthority)) {
            cboMultiCity.addItems(lstTaxAuthority);
            Map<String, TaxAuthorityDTO> mapTaxCode = new HashMap<>();
            try {
                mapTaxCode = DataUtil.buildHasmap(lstTaxAuthority, "maCqt");
            } catch (Exception e) {
            }
            if (collection != null && !collection.isEmpty()) {
                TaxAuthorityDTO temp;
                List<TaxAuthorityDTO> lst = new ArrayList<>();
                for (Object o : collection) {
                    temp = (TaxAuthorityDTO) o;
                    if (mapTaxCode.containsKey(temp.getMaCqt())) {
                        lst.add(mapTaxCode.get(temp.getMaCqt()));
                    }
                }
                if (!DataUtil.isListNullOrEmpty(lst)) {
                    Set<Object> set = new HashSet<>();
                    set.addAll(lst);
                    if (lst.size() != collection.size()) {
                        isCityChanged = true;
                    } else {
                        isCityChanged = false;
                    }
                    cboMultiCity.setValue(set);
                }
            }
        }
    }

    private void getTaxAuthorityFromMineName(String mineName, Map<String, String> map) {
        try {
            if (!StringUtils.isNullOrEmpty(mineName)) {
                lstTaxAuthority = WSTaxAuthority.getListTaxAuthorityFromMineName(mineName, map);
            } else {
                lstTaxAuthority = new ArrayList<>();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getProviderFromMineName(String mineName, Map<String, String> map) {
        try {
            if (!StringUtils.isNullOrEmpty(mineName)) {
                lstProvider = WSAppParams.getListProviderFromMineName(mineName, map);
            } else {
                lstProvider = new ArrayList<>();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addValueChangeListener(Property.ValueChangeListener valueChangeListener) {
        cbxMineName.addValueChangeListener(valueChangeListener);
        mapStaffCustomerView.getDfFromDateRegister().addValueChangeListener(valueChangeListener);
        mapStaffCustomerView.getDfToDateRegister().addValueChangeListener(valueChangeListener);
        mapStaffCustomerView.getDfFromStartTime().addValueChangeListener(valueChangeListener);
        mapStaffCustomerView.getDfToStartTime().addValueChangeListener(valueChangeListener);
        mapStaffCustomerView.getDfFromEndTime().addValueChangeListener(valueChangeListener);
        mapStaffCustomerView.getDfToEndTime().addValueChangeListener(valueChangeListener);
    }

    private Map<String, String> getValueFromSearchForm() {
        return mapStaffCustomerView.getConditionValue();
    }

    private void setLstCustomerToStaff(StaffDTO staff, List<CustomerDTO> lstCustomers) {
        List<CustomerStatusDTO> lstTemp;
        List<CustomerStatusDTO> convertStatusDTO;
        lstTemp = staff.getLstCustomers();
        convertStatusDTO = DataUtil.convertListCust2CustStatus(lstCustomers, staff);
        if (DataUtil.isListNullOrEmpty(lstTemp)) {
            lstTemp = convertStatusDTO;
        } else {
            lstTemp.addAll(convertStatusDTO);
        }
        staff.setQuanlity(String.valueOf(lstTemp.size()));
        staff.setLstCustDTO(lstCustomers);
        staff.setLstCustomers(lstTemp);
        lstCustomersExcuted.addAll(lstCustomers);
        lstCustomerStatusSelected.addAll(convertStatusDTO);
    }

    public void doReset(StaffDTO staff) {
        BeanItem staffReset = (BeanItem) tblStaffMapCustomer.getItem(staff);
        MethodProperty quanlityProperty = (MethodProperty) staffReset.getItemProperty("quanlity");
        quanlityProperty.setValue("0");
        quanlityProperty.fireValueChange();
        List<CustomerStatusDTO> lstCustomerStatusOfStaff = staff.getLstCustomers();
        lstCustomerStatusSelected.removeAll(lstCustomerStatusOfStaff);
        List<CustomerDTO> lstCustomerOfStaff = staff.getLstCustDTO();
        lstCustomersExcuted.removeAll(lstCustomerOfStaff);
        staff.setLstCustomers(null);
        staff.setLstCustDTO(null);
        staff.setQuanlity("0");
        MethodProperty lstCustDTOProperty = (MethodProperty) staffReset.getItemProperty("lstCustDTO");
        lstCustDTOProperty.setValue(null);
        lstCustDTOProperty.fireValueChange();
        MethodProperty lstCustomersDTOProperty = (MethodProperty) staffReset.getItemProperty("lstCustomers");
        lstCustomersDTOProperty.setValue(null);
        lstCustomersDTOProperty.fireValueChange();
        tblStaffMapCustomer.refreshRowCache();
    }

    public List<CustomerDTO> removeDupplicateCustomer(List<CustomerDTO> inputList) {
        Map<String, CustomerDTO> map = new HashMap<>();
        for (CustomerDTO cust : inputList) {
            addCustomer(map, cust);
        }
        return Lists.newArrayList(map.values());
    }

    private void addCustomer(Map<String, CustomerDTO> map, CustomerDTO cus) {
        if (!map.containsKey(cus.getTaxCode())) {
            map.put(cus.getTaxCode(), cus);
        }
    }

    private List<String> getListCategoryFromService() {
        List<String> lstMineName = null;
        try {
            List<ConditionBean> lstConditions = new ArrayList<>();
            AppParamsDTO serviceDTO = (AppParamsDTO) cbxServices.getValue();
            if (serviceDTO != null && !DataUtil.isStringNullOrEmpty(serviceDTO.getParCode())) {
                lstConditions.add(new ConditionBean("service", serviceDTO.getParCode(), ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
            }
            lstConditions.add(new ConditionBean("endDate", DateUtil.date2ddMMyyyyString(new Date()), ConditionBean.Operator.NAME_GREATER_EQUAL, ConditionBean.Type.DATE));
            lstCategoryList = WSCategoryList.getListCategoryListByCondition(lstConditions, 0, Constants.INT_100, Constants.ASC, Constants.CATEGORY_LIST.CODE);
            lstMineName = DataUtil.getListValueFromList(lstCategoryList, Constants.CATEGORY_LIST.ID);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstMineName;
    }
}
