/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.controller;

import com.anphat.customer.ui.SearchCustomerForm;
import com.anphat.list.ui.MapStaffCustomerDialog;
import com.cms.common.ws.WSAppParams;
import com.vaadin.ui.Button;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CategoryListDTO;
import com.cms.dto.CustomerDTO;
import com.cms.dto.StaffDTO;
import com.cms.dto.TaxAuthorityDTO;
import com.cms.login.ws.WSCategoryList;
import com.cms.login.ws.WSStaff;
import com.cms.login.ws.WSTaxAuthority;
import com.cms.utils.ComboComponent;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.google.common.collect.Lists;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vwf5.base.utils.ConditionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vaadin.addons.comboboxmultiselect.ComboBoxMultiselect;

/**
 *
 * @author quyen
 */
public class SearchCustomerController implements Serializable{

    private final SearchCustomerForm searchForm;
    private CustomerDTO searchDTO;
    //Cac thuoc tinh tren form search
    private String taxCode;
    private String name;
    private String telNumber;
    private String email;
    private String officeAddress;
    private String taxAuthority;
    private String taxDepartment;
    private String provider;

    private AppParamsDTO providerDTO;
    private AppParamsDTO statusDTO;
    private List<AppParamsDTO> lstCustStatus;
    private List<AppParamsDTO> lstMaxSearch;
    private List<AppParamsDTO> lstProvider;
    private List<AppParamsDTO> lstService;
    private List<TaxAuthorityDTO> lstTaxAuthority;
    private List<CategoryListDTO> lstCategoryList;
    private List<StaffDTO> lstStaffs;
    private ComboComponent comboBoxUtil;
    private final List<AppParamsDTO> lstAppParamsAll;
    private Button btnReset;
    private String mineName;

    private ComboBoxMultiselect cboMultiProvider;
    private ComboBoxMultiselect cboMultiCity;
    private ComboBox cboStatus;
    private ComboBox cboService;
    private ComboBox cboMineName;
    private ComboBox cboStaff;
    private StaffDTO staff;

    public SearchCustomerController(SearchCustomerForm searchForm, List<AppParamsDTO> lstAppParamsAll) {
        this.searchForm = searchForm;
        this.lstAppParamsAll = lstAppParamsAll;
        getDatas();
        addListenerResetButton();
    }

    //Lay du lieu
    private void getDatas() {
        comboBoxUtil = new ComboComponent();
        lstMaxSearch = DataUtil.getListApParams(lstAppParamsAll, Constants.APP_PARAMS.MAX_SEARCH);
        lstProvider = DataUtil.getListApParams(lstAppParamsAll, Constants.APP_PARAMS.PROVIDER);
        try {
            StaffDTO search = new StaffDTO();
            search.setStaffType(Constants.STAFF.STAFF_TYPE_3);
            search.setStatus(Constants.ACTIVE);
            lstStaffs = WSStaff.getListStaffDTO(search, 0, Constants.INT_100, "asc", "code");
        } catch (Exception e) {
            e.printStackTrace();
        }
        lstService = DataUtil.getListApParams(lstAppParamsAll, Constants.APP_PARAMS.SERVICE_TYPE);
    }

    //Khoi tao cac gia tri cho combobox
    private void initSearchForm() {
        cboStaff = searchForm.getCboStaff();
        cboMultiProvider = searchForm.getCboMulProvider();
        cboMultiCity = searchForm.getCboMulCity();
        cboStatus = searchForm.getStatus();
        cboMineName = searchForm.getCbxMineName();
        cboService = searchForm.getCboService();
        //So luong tim kiem        
        String valueDefaultMaxSearch = Constants.NULL;
        if (!DataUtil.isListNullOrEmpty(lstMaxSearch)) {
            valueDefaultMaxSearch = lstMaxSearch.get(0).getParCode();
        }
        //So luong tim kiem        
        String valueDefaultService = Constants.NULL;
        if (!DataUtil.isListNullOrEmpty(lstService)) {
            valueDefaultService = lstService.get(0).getParCode();
        }
        comboBoxUtil.fillDataCombo(cboService,
                Constants.NULL, valueDefaultService,
                lstService, Constants.APP_PARAMS.SERVICE_TYPE);
        comboBoxUtil.fillDataCombo(searchForm.getCboMaxSearch(),
                Constants.ALL, Constants.NULL,
                lstMaxSearch, Constants.APP_PARAMS.MAX_SEARCH);
        comboBoxUtil.setValues(cboStaff, lstStaffs, Constants.STAFF.CODE, Boolean.TRUE);

        lstCategoryList = getListCategoryFromService();
        String mineNameToSearch = null;
        if (!DataUtil.isListNullOrEmpty(lstCategoryList)) {
            List<String> lstMineName = new ArrayList<>();
            lstCategoryList.forEach((k) -> {
                lstMineName.add(k.getId());
            });
            mineNameToSearch = DataUtil.convertList2StringToSearchConditionIN(lstMineName);
        }
        String service = getServiceToSearch();
        if (DataUtil.isAdmin(staff)) {
            lstCustStatus = WSAppParams.getStatusFromStaffCode(null, mineNameToSearch);
            lstProvider = WSAppParams.getProviderFromStaffCode(null, mineNameToSearch);
            lstTaxAuthority = WSTaxAuthority.getListTaxAuthorityFromMineNameAndStaffCode(mineNameToSearch, null);
        } else {
            String staffCode = staff.getCode();
            lstCustStatus = WSAppParams.getStatusFromStaffCode(staffCode, mineNameToSearch);
            lstProvider = WSAppParams.getProviderFromStaffCode(staffCode, mineNameToSearch);
            lstTaxAuthority = WSTaxAuthority.getListTaxAuthorityFromMineNameAndStaffCode(mineNameToSearch, staffCode);
        }

        comboBoxUtil.fillDataCombo(cboStatus,
                Constants.ALL, Constants.NULL,
                lstCustStatus, Constants.APP_PARAMS.CUSTOMER_STATUS);

        BeanItemContainer<AppParamsDTO> beanContainerProvider = new BeanItemContainer<>(AppParamsDTO.class);
        if (!DataUtil.isListNullOrEmpty(lstProvider)) {
            beanContainerProvider.addAll(lstProvider);
        }
        cboMultiProvider.setContainerDataSource(beanContainerProvider);

        comboBoxUtil.setValues(cboMineName, lstCategoryList, Constants.CATEGORY_LIST.CODE, Boolean.TRUE);

        BeanItemContainer<TaxAuthorityDTO> beanContainerTaxAuthoriry = new BeanItemContainer<>(TaxAuthorityDTO.class);
        if (!DataUtil.isListNullOrEmpty(lstTaxAuthority)) {
            beanContainerTaxAuthoriry.addAll(lstTaxAuthority);
        }
        cboMultiCity.setContainerDataSource(beanContainerTaxAuthoriry);
    }

    //An cbo staff neu khong phai admin
    public void setStaff(StaffDTO staff) {
        this.staff = staff;
        initSearchForm();
        if (!DataUtil.isAdmin(staff)) {
            for (StaffDTO st : lstStaffs) {
                if (staff.getStaffId().equalsIgnoreCase(st.getStaffId())) {
                    cboStaff.select(st);
                    cboStaff.setVisible(false);
                    break;
                }
            }
            String serviceStr = getServiceToSearch();
            lstCategoryList = WSCategoryList.getCategoryListFromStaffCode(staff.getCode(), serviceStr);
            comboBoxUtil.setValues(cboMineName, lstCategoryList, Constants.CATEGORY_LIST.CODE, Boolean.TRUE);
        } else {
            addStaffComboBoxValueChange();
        }
        addComboBoxValueChange();
    }

    public void addStaffComboBoxValueChange() {
        cboStaff.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
//                StaffDTO staff = (StaffDTO) event.getProperty().getValue();
//                String serviceStr = getServiceToSearch();
//                if (!DataUtil.isStringNullOrEmpty(staff.getStaffId())) {
//                    lstCategoryList = WSCategoryList.getCategoryListFromStaffCode(staff.getCode(), serviceStr);
//                } else {
//                    lstCategoryList = getListCategoryFromService();
//                }
                lstCategoryList = getListCategoryFromService();
                comboBoxUtil.setValues(cboMineName, lstCategoryList, Constants.CATEGORY_LIST.CODE, Boolean.TRUE);
            }
        });
    }

    public void addComboBoxValueChange() {
        cboService.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                lstCategoryList = getListCategoryFromService();
                isMineNameChanged = false;                
                comboBoxUtil.setValues(cboMineName, lstCategoryList, Constants.CATEGORY_LIST.CODE, Boolean.TRUE);
            }
        });

        cboMineName.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (isMineNameChanged) {
                    CategoryListDTO category = (CategoryListDTO) event.getProperty().getValue();
                    String mineName = null;
                    if (category != null && !DataUtil.isStringNullOrEmpty(category.getId())) {
                        mineName = category.getId();
                    } else {
                        try {
                            List<String> lstMineName = DataUtil.getListValueFromList(lstCategoryList, "id");
                            mineName = DataUtil.convertList2StringToSearchConditionIN(lstMineName);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
//                    searchTaxAuthorityByMineName(mineName);
//                    searchProviderByMineName(mineName);

                    searchCustomerStatusByMineName(mineName);
                } else {
                    isMineNameChanged = true;
                }
            }
        });
        //Tax Authority combobox value change listener
        cboMultiProvider.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (isProviderChanged) {
                    String provider = getProviderToSearch();
                    String mineName = getCategoryListToSearch();
                    String staffCode = getStaffCodeToSearch();
                    String status = getStatusToSearch();
//                    String service = getServiceToSearch();
                    searchTaxAuthorityByMineNameAndStaffCodeAndProvider(
                            staffCode, mineName, provider, status);
                } else {
                    isProviderChanged = true;
                }
            }
        });
        //Tax Authority combobox value change listener
        cboMultiCity.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (isCityChanged) {
                    String provider = getProviderToSearch();
                    String mineName = getCategoryListToSearch();
                    String staffCode = getStaffCodeToSearch();
                    String status = getStatusToSearch();
                    String service = getServiceToSearch();

                    Map<String, String> map = new HashMap<>();
                    map.put("mineName", mineName);
                    map.put("provider", provider);
                    map.put("status", status);
                    map.put("service", service);
                    searchProviderByStaffCodeAndOtherConditions(
                            staffCode, map);
                } else {
                    isCityChanged = true;
                }
            }
        });
        cboStatus.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (isStatusChanged) {
                    String provider = getProviderToSearch();
                    String mineName = getCategoryListToSearch();
                    String staffCode = getStaffCodeToSearch();
                    String status = getStatusToSearch();
                    Map<String, String> map = new HashMap<>();
                    map.put("mineName", mineName);
                    map.put("provider", provider);
                    map.put("status", status);
                    searchProviderByStaffCodeAndOtherConditions(
                            staffCode, map);
                    provider = getProviderToSearch();
                    searchTaxAuthorityByMineNameAndStaffCodeAndProvider(
                            staffCode, mineName, provider, status);
                } else {
                    isStatusChanged = true;
                }
            }
        });
    }

    //Thuc hien lay doi tuong tim kiem
    public CustomerDTO getDTO2Search() {
        searchDTO = new CustomerDTO();
        //Lay ma so thue
        taxCode = searchForm.getTaxCode().getValue();
        name = searchForm.getName().getValue();
        telNumber = searchForm.getTelNumber().getValue();
        email = searchForm.getEmail().getValue();
//        officeAddress = searchForm.getDeployAddress().getValue();
//        taxAuthority = searchForm.getDeployAddress().getValue();
//        officeAddress = searchForm.getDeployAddress().getValue();
//        mineName = searchForm.getMineName().getValue();
//        mineName = cboMineName.getValue();
        statusDTO = (AppParamsDTO) cboStatus.getValue();
        //Neu du lieu khac null thi set vao doi tuong de tim kiem
        if (statusDTO != null
                && !DataUtil.isStringNullOrEmpty(statusDTO.getParCode())) {
            searchDTO.setStatus(statusDTO.getParCode());
        }
        //Lay du lieu nha cung cap
//        Collection collectionProvider = (Collection) searchForm.getCboMulProvider().getValue();
//        List<AppParamsDTO> listProvider = Lists.newArrayList(collectionProvider);

        String serviceStr = getServiceToSearch();
        if (!DataUtil.isStringNullOrEmpty(serviceStr)) {
            searchDTO.setService(serviceStr);
        }
        if (!DataUtil.isStringNullOrEmpty(taxCode)) {
            searchDTO.setTaxCode(taxCode);
        }
        if (!DataUtil.isStringNullOrEmpty(name)) {
            searchDTO.setName(name);
        }
        if (!DataUtil.isStringNullOrEmpty(telNumber)) {
            searchDTO.setTelNumber(telNumber);
        }
        if (!DataUtil.isStringNullOrEmpty(email)) {
            searchDTO.setEmail(email);
        }
        CategoryListDTO categoryList = (CategoryListDTO) cboMineName.getValue();
        if (!DataUtil.isNullObject(categoryList)) {
            searchDTO.setMineName(categoryList.getId());
        }

        Collection collectionCity = (Collection) cboMultiCity.getValue();
        List<AppParamsDTO> listCity = Lists.newArrayList(collectionCity);

        List<String> taxAuthority = null;
        try {
            taxAuthority = DataUtil.getListValueFromList(listCity, Constants.TAXAUTHORITY.MA_CQT);
        } catch (NoSuchMethodException | IllegalAccessException ex) {
            ex.printStackTrace();
        }

        if (!DataUtil.isListNullOrEmpty(taxAuthority)) {
            String taxAuthorityCondition = DataUtil.convertList2StringToSearchConditionIN(taxAuthority);
            searchDTO.setTaxAuthority(taxAuthorityCondition);
        }
        Collection coProvider = (Collection) cboMultiProvider.getValue();
        List<AppParamsDTO> providerDTOs = new ArrayList<>();
        providerDTOs.addAll(coProvider);
        List<String> provider = null;
        try {
            provider = DataUtil.getListValueFromList(providerDTOs, Constants.APP_PARAMS.PAR_CODE);
        } catch (NoSuchMethodException | IllegalAccessException ex) {
            ex.printStackTrace();
        }

        if (!DataUtil.isListNullOrEmpty(provider)) {
            String multiProvider = DataUtil.convertList2StringToSearchConditionIN(provider);
            searchDTO.setProvider(multiProvider);
        }

        StaffDTO staff = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");
        if (!cboStaff.isVisible()) {
            searchDTO.setStaffId(staff.getStaffId());
        } else {
            staff = (StaffDTO) cboStaff.getValue();
            if (!DataUtil.isNullObject(staff)) {
                searchDTO.setStaffId(staff.getStaffId());
            }
        }
        String custCareCreatedDate = DataUtil.getDateNullOrZero(searchForm.getDfContactCreatedDate());
        if (!DataUtil.isStringNullOrEmpty(custCareCreatedDate)) {
            searchDTO.setCustCareHistoryCreatedDate(custCareCreatedDate);
        }
        return searchDTO;
    }
    //Thuc hien lay doi tuong tim kiem

    public List<ConditionBean> getListCondition2Search() {
        List<ConditionBean> lstConditionBeans = new ArrayList<>();
        //Lay ma so thue
        taxCode = searchForm.getTaxCode().getValue();
        name = searchForm.getName().getValue();
        telNumber = searchForm.getTelNumber().getValue();
        email = searchForm.getEmail().getValue();
        officeAddress = searchForm.getDeployAddress().getValue();
        statusDTO = (AppParamsDTO) cboStatus.getData();
        //Neu du lieu khac null thi set vao doi tuong de tim kiem
        if (statusDTO != null
                && !DataUtil.isStringNullOrEmpty(statusDTO.getParCode())) {
            lstConditionBeans.add(new ConditionBean("status",
                    statusDTO.getParCode(), ConditionBean.Operator.NAME_EQUAL,
                    ConditionBean.Type.STRING));
        }
        if (!DataUtil.isStringNullOrEmpty(taxCode)) {
            lstConditionBeans.add(new ConditionBean("taxCode",
                    taxCode, ConditionBean.Operator.NAME_LIKE,
                    ConditionBean.Type.STRING));
        }
        if (!DataUtil.isStringNullOrEmpty(name)) {
            lstConditionBeans.add(new ConditionBean("name",
                    name, ConditionBean.Operator.NAME_LIKE,
                    ConditionBean.Type.STRING));
        }
        if (!DataUtil.isStringNullOrEmpty(telNumber)) {
            lstConditionBeans.add(new ConditionBean("telNumber",
                    telNumber, ConditionBean.Operator.NAME_LIKE,
                    ConditionBean.Type.STRING));
        }
        if (!DataUtil.isStringNullOrEmpty(email)) {
            lstConditionBeans.add(new ConditionBean("email",
                    email, ConditionBean.Operator.NAME_LIKE,
                    ConditionBean.Type.STRING));
        }
        return lstConditionBeans;
    }

    //Reset form
    public void resetForm() {
        searchForm.getTaxCode().clear();
        searchForm.getEmail().clear();
        searchForm.getTelNumber().clear();
        searchForm.getName().clear();
        searchForm.getDfContactCreatedDate().clear();

//        initSearchForm();
    }

    private void addListenerResetButton() {
        btnReset = searchForm.getBtnReset();
        btnReset.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                resetForm();
                event.getButton().setEnabled(true);
            }
        });
    }

    public int getMaxSearch() {
        return DataUtil.getMaxSearch(searchForm.getCboMaxSearch());
    }

    public void searchTaxAuthorityByMineName(String mineName) {
        String staffCode = null;
        if (DataUtil.isAdmin(staff)) {
            StaffDTO staffSelected = (StaffDTO) cboStaff.getValue();
            if (!DataUtil.isStringNullOrEmpty(staffSelected.getStaffId())) {
                staffCode = staffSelected.getCode();
            }
        } else {
            staffCode = staff.getCode();
        }
        lstTaxAuthority = WSTaxAuthority.getListTaxAuthorityFromMineNameAndStaffCode(mineName, staffCode);
        cboMultiCity.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstTaxAuthority)) {
            cboMultiCity.addItems(lstTaxAuthority);
        }
    }

    public void searchTaxAuthorityByMineNameAndStaffCodeAndProvider(
            String staffCode, String mineName, String provider, String status) {
        lstTaxAuthority
                = WSTaxAuthority.getListTaxAuthorityFromMineNameAndStaffCodeAndProvider(
                        mineName, staffCode, provider, status);
        setValueToMultiTaxAuthority(lstTaxAuthority);
    }

    public void searchProviderByMineName(String mineName) {
        String staffCode = null;
        if (DataUtil.isAdmin(staff)) {
            StaffDTO staffSelected = (StaffDTO) cboStaff.getValue();
            if (!DataUtil.isStringNullOrEmpty(staffSelected.getStaffId())) {
                staffCode = staffSelected.getCode();
            }
        } else {
            staffCode = staff.getCode();
        }
        lstProvider = WSAppParams.getProviderFromStaffCode(staffCode, mineName);
//        isProviderChanged = false;
        cboMultiProvider.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstProvider)) {            
            cboMultiProvider.addItems(lstProvider);
        }
    }

    private List<CategoryListDTO> getListCategoryFromService() {
        List<CategoryListDTO> lstResultSearch = null;
        StaffDTO selectedStaffDTO;
        if (cboStaff.isVisible()) {
            selectedStaffDTO = (StaffDTO) cboStaff.getValue();
        } else {
            selectedStaffDTO = staff;
        }
        String serviceStr = getServiceToSearch();
        if (!DataUtil.isStringNullOrEmpty(selectedStaffDTO.getStaffId())) {
            lstResultSearch = WSCategoryList.getCategoryListFromStaffCode(selectedStaffDTO.getCode(), serviceStr);
        } else {
            try {
                List<ConditionBean> lstConditions = new ArrayList<>();
                lstConditions.add(new ConditionBean("service", serviceStr, ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
                lstConditions.add(new ConditionBean("cust_quantity", "0", ConditionBean.Operator.NAME_GREATER, ConditionBean.Type.NUMBER));

                lstResultSearch = WSCategoryList.getListCategoryListByCondition(lstConditions, 0, Constants.INT_100, Constants.ASC, Constants.CATEGORY_LIST.CODE);
            } catch (Exception ex) {
                ex.printStackTrace();
                Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstResultSearch;
    }

    /**
     * Search provider by staff Code and other Conditions
     *
     * @param staffCode
     * @param map
     */
    public void searchProviderByStaffCodeAndOtherConditions(
            String staffCode, Map<String, String> map) {
        lstProvider = WSAppParams.getProviderFromStaffCodeAndConditions(staffCode, map);
        setValueToMultiProvider(lstProvider);
    }

    public void searchCustomerStatusByMineName(String mineName) {
        String staffCode = null;
        if (DataUtil.isAdmin(staff)) {
            StaffDTO staffSelected = (StaffDTO) cboStaff.getValue();
            if (!DataUtil.isStringNullOrEmpty(staffSelected.getStaffId())) {
                staffCode = staffSelected.getCode();
            }
        } else {
            staffCode = staff.getCode();
        }
        lstCustStatus = WSAppParams.getStatusFromStaffCode(staffCode, mineName);
        isStatusChanged = false;
        comboBoxUtil.fillDataCombo(cboStatus,
                Constants.ALL, Constants.NULL,
                lstCustStatus, Constants.APP_PARAMS.CUSTOMER_STATUS);
    }

    public List<TaxAuthorityDTO> getLstTaxAuthority() {
        return lstTaxAuthority;
    }

    public void setLstTaxAuthority(List<TaxAuthorityDTO> lstTaxAuthority) {
        this.lstTaxAuthority = lstTaxAuthority;
    }

    public String getProviderToSearch() {
        String multiProvider = null;
        Collection coProvider = (Collection) cboMultiProvider.getValue();
        List<AppParamsDTO> providerDTOs = new ArrayList<>();
        providerDTOs.addAll(coProvider);
        List<String> providerList = null;
        try {
            if (!DataUtil.isListNullOrEmpty(providerDTOs)) {
                providerList = DataUtil.getListValueFromList(providerDTOs, Constants.APP_PARAMS.PAR_CODE);
            }
        } catch (NoSuchMethodException | IllegalAccessException ex) {
            Logger.getLogger(MapStaffCustomerDialog.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        if (!DataUtil.isListNullOrEmpty(providerList)) {
            multiProvider = DataUtil.convertList2StringToSearchConditionIN(providerList);
        }
        return multiProvider;
    }

    private String getStaffCodeToSearch() {
        String staffCode = null;
        if (DataUtil.isAdmin(staff)) {
            StaffDTO staffSelected = (StaffDTO) cboStaff.getValue();
            if (!DataUtil.isStringNullOrEmpty(staffSelected.getStaffId())) {
                staffCode = staffSelected.getCode();
            }
        } else {
            staffCode = staff.getCode();
        }
        return staffCode;
    }

    private String getCategoryListToSearch() {
        CategoryListDTO category = (CategoryListDTO) cboMineName.getValue();
        String mineNameToSearch = null;
        if (category != null && !DataUtil.isStringNullOrEmpty(category.getId())) {
            mineNameToSearch = category.getId();
        } else {
            try {
                List<String> lstMineName = DataUtil.getListValueFromList(lstCategoryList, "id");
                mineNameToSearch = DataUtil.convertList2StringToSearchConditionIN(lstMineName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mineNameToSearch;
    }

    private String getStatusToSearch() {
        AppParamsDTO status = (AppParamsDTO) cboStatus.getValue();
        String statusToSearch = null;
        if (status != null && !DataUtil.isStringNullOrEmpty(status.getParCode())) {
            statusToSearch = status.getParCode();
        }
        return statusToSearch;
    }

    private String getServiceToSearch() {
        AppParamsDTO service = (AppParamsDTO) cboService.getValue();
        String serviceToSearch = null;
        if (service != null && !DataUtil.isStringNullOrEmpty(service.getParCode())) {
            serviceToSearch = service.getParCode();
        }
        return serviceToSearch;
    }

    boolean isCityChanged = true;
    boolean isMineNameChanged = true;
    boolean isStatusChanged = true;

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
    boolean isProviderChanged = true;

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

    public void setValueStatusDefault(String status) {
//        isStatusChanged = false;
        comboBoxUtil.fillDataCombo(cboStatus,
                Constants.ALL, status,
                lstCustStatus, Constants.APP_PARAMS.CUSTOMER_STATUS);
//        isStatusChanged = true;
        cboStatus.setEnabled(true);
    }
}
