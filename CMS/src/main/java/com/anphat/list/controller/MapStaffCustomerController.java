/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.customer.controller.SearchCustomerController;
import com.anphat.list.ui.CustomerDialog;
import com.anphat.list.ui.MapStaffCustomerDialog;
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
import com.cms.login.ws.WSTaxAuthority;
import com.cms.ui.CommonButtonClickListener;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonMessages;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.ShortcutUtils;
import com.cms.utils.StringUtils;
import com.cms.utils.TableUtils;
import com.google.common.collect.Lists;
import com.vaadin.data.Property;
import com.vwf5.base.utils.ConditionBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vaadin.addons.comboboxmultiselect.ComboBoxMultiselect;

/**
 * Lop Controller cho dialog Gan nhan vien khach hang. Dieu kien la phai chon
 * danh sach cac nhan vien truoc khi gan
 *
 * @author quyen
 */
public class MapStaffCustomerController {

    private final MapStaffCustomerDialog mapStaffCustomerDialog;
    private CommonTableFilterPanel panelTableCustomer;
    private CommonTableFilterPanel panelTableMapStaffCustomer;
    private CustomPageTableFilter tblCustomer;
    private CustomPageTableFilter tblStaffMapCustomer;
    private final List<AppParamsDTO> lstAllAppParams;
    private final List<StaffDTO> lstStaffs;
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
    private static final String CAPTION_CUSTOMER
            = BundleUtils.getString("tbl.caption.list.customer");
    private static final String CAPTION_MAP_STAFF_CUSTOMER
            = BundleUtils.getString("mapStaffCustomerForm.gridTitle");
    //Cac nut thao tac tren dialog
    private Button btnSearch;
    private Button btnReset;
    private Button btnExecute;
    private Button btnSave;
    //Cac thanh phan cua grid tim kiem khach hang
    private ComboBox cbxProvider;
    private ComboBox cbxServices;
    private ComboBox cbxMaxSearch;
    private ComboBox cbxCity;
    private ComboBox cbxMineName;
    private ComboBox cbxCustomerStatus;
    private ComboComponent comboUtils;
    private List<AppParamsDTO> lstProvider;
    private TableUtils tblUtils;
//    private Map<StaffDTO, List<CustomerDTO>> mapStaff2ListCustomer;
    private ComboBoxMultiselect cboMultiProvider;
    private ComboBoxMultiselect cboMultiCity;
    private List<StaffDTO> lstStaffsSelected;
    private List<CustomerDTO> lstCustomerSelected;

    public MapStaffCustomerController(MapStaffCustomerDialog mapStaffCustomerDialog,
            List<StaffDTO> lstStaffs, List<AppParamsDTO> lstAllAppParams) {
        this.mapStaffCustomerDialog = mapStaffCustomerDialog;
        this.lstStaffs = lstStaffs;
        this.lstAllAppParams = lstAllAppParams;
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
        cbxProvider = mapStaffCustomerDialog.getCbxProvider();
        cbxServices = mapStaffCustomerDialog.getCbxService();
        cbxMaxSearch = mapStaffCustomerDialog.getCbxMaxSearch();
        cbxCity = mapStaffCustomerDialog.getCbxCity();
        cbxMineName = mapStaffCustomerDialog.getCbxMineName();
        lstServices = DataUtil.getListApParams(lstAllAppParams, Constants.APP_PARAMS.SERVICE_TYPE);
        cbxCustomerStatus = mapStaffCustomerDialog.getCbxStatus();
        lstCustomerStatus = DataUtil.getListApParams(lstAllAppParams, Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS);
        lstMaxSearch = DataUtil.getListApParams(lstAllAppParams, Constants.APP_PARAMS.MAX_SEARCH);
        try {
            mapServices = DataUtil.buildHasmap(lstServices, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
            mapCustServiceStatus = DataUtil.buildHasmap(lstCustomerStatus, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException ex) {
            Logger.getLogger(MapStaffCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboUtils = new ComboComponent();
        List<String> lstMineName = null;
        try {
            lstCategoryList = WSCategoryList.getListCategoryListDTO(new CategoryListDTO(), 0, Constants.INT_100, Constants.ASC, Constants.CATEGORY_LIST.CODE);
            lstMineName = DataUtil.getListValueFromList(lstCategoryList, Constants.CATEGORY_LIST.ID);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String mineName = DataUtil.isListNullOrEmpty(lstMineName) ? Constants.NULL : DataUtil.convertList2StringToSearchConditionIN(lstMineName);
        Map<String, String> map = new HashMap<>();
        getTaxAuthorityAndProviderFromMineName(mineName, map);
        //ComboBox Multiselect
        cboMultiProvider = mapStaffCustomerDialog.getCboMulProvider();
        cboMultiCity = mapStaffCustomerDialog.getCboMulCity();
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
        beanContainerTaxAuthoriry.addAll(lstTaxAuthority);
        cboMultiCity.setContainerDataSource(beanContainerTaxAuthoriry);
    }

    /**
     * Khoi tao cac bang
     */
    private void initTables() {
        tblUtils = new TableUtils();
        //Khoi tao bang khach hang
        panelTableCustomer = mapStaffCustomerDialog.getPanelTblCustomer();
        tblCustomer = panelTableCustomer.getMainTable();
        tblUtils.generateColumn(tblCustomer);
        containerCustomer = new BeanItemContainer(CustomerDTO.class);
        CommonFunctionTableFilter.initTable(panelTableCustomer, HEADER_CUSTOMER,
                containerCustomer, CAPTION_CUSTOMER, 5, "customer");

        //Khoi tao bang khach hang
        panelTableMapStaffCustomer = mapStaffCustomerDialog.getPanelTblCustomerStatus();
        tblStaffMapCustomer = panelTableMapStaffCustomer.getMainTable();
        tblUtils.generateColumn(tblStaffMapCustomer);
        containerMapStaffCustomer = new BeanItemContainer(StaffDTO.class);
        containerMapStaffCustomer.addAll(lstStaffs);
        //Them nut chi tiet
        tblStaffMapCustomer.addGeneratedColumn("detail", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                final StaffDTO staff = (StaffDTO) itemId;
                if (DataUtil.isListNullOrEmpty(staff.getLstCustomers())) {
                    return "";
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

            }
        });
        //Them nut so luong
        tblStaffMapCustomer.addGeneratedColumn("quanlity", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                StaffDTO staff = (StaffDTO) itemId;
                if (DataUtil.isListNullOrEmpty(staff.getLstCustomers())) {
                    return 0;
                } else {
                    return staff.getLstCustomers().size();
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
        btnSearch = mapStaffCustomerDialog.getBtnSearch();
        ShortcutUtils.setShortcutKey(btnSearch);
        btnSearch.addClickListener(new CommonButtonClickListener() {
            @Override
            public void execute() {
                List<ConditionBean> lstConditionBeans = mapStaffCustomerDialog.getLstCondition2Search();
                List<CustomerDTO> lstCustSearched = null;
                if (!DataUtil.isListNullOrEmpty(lstConditionBeans)) {
                    lstCustSearched = WSCustomer.getListCustomerFromTermInfo(lstConditionBeans);
                }
                setData2TableCustomer(lstCustSearched);
                if (!DataUtil.isListNullOrEmpty(lstCustSearched)) {
                    mapStaffCustomerDialog.center();
                } else {
                    CommonMessages.showDataNotFound();
                }
            }
        });
    }

    //Nut Reset
    private void addBtnResetClickListener() {
        btnReset = mapStaffCustomerDialog.getBtnReset();
        btnReset.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                mapStaffCustomerDialog.doResetData();
                initSearchGrid();
                setData2TableStaff(lstStaffs);
                event.getButton().setEnabled(true);
            }
        });
    }

    //Nut thuc hien phan bo
    private void addBtnExecuteClickListener() {
        btnExecute = mapStaffCustomerDialog.getBtnExecute();
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
                    setData2TableStaff(lstStaffsSelected);
                } else {
                    CommonMessages.showErrorMessage(BundleUtils.getString("map.customer.staff.error"));
                }
            }
        });
    }

    //Nut luu lai
    private void addBtnSaveClickListener() {
        btnSave = mapStaffCustomerDialog.getBtnSave();
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
    }

    /**
     * Set du lieu cho bang khach hang
     *
     * @param lstCustomers
     */
    private void setData2TableCustomer(List<CustomerDTO> lstCustomers) {
        containerCustomer.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstCustomers)) {
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
            List<CustomerStatusDTO> lstTemp;
            List<CustomerDTO> lstCustTemp;
            if (qualityCustPerStaff > 0) {
                if (module != 0) {
                    size = sizeOfStaff - 1;
//                    lstStaffs.get(size).setLstCustomers(null);
                    lstCustTemp = lstCustomers.subList((sizeOfStaff - 1) * qualityCustPerStaff, sizeOfCustomer);
                    lstTemp = DataUtil.convertListCust2CustStatus(lstCustTemp, lstStaffs.get(size));
                    lstStaffs.get(size).setLstCustomers(lstTemp);
                } else {
                    size = sizeOfStaff;
                }
                for (int i = 0; i < size; i++) {
//                    lstStaffs.get(i).setLstCustomers(null);
                    lstCustTemp = lstCustomers.subList(i * qualityCustPerStaff, (i + 1) * qualityCustPerStaff);
                    lstTemp = DataUtil.convertListCust2CustStatus(lstCustTemp, lstStaffs.get(i));
                    lstStaffs.get(i).setLstCustomers(lstTemp);
                }
            } else {
                for (int i = 0; i < sizeOfCustomer; i++) {
//                    lstStaffs.get(i).setLstCustomers(null);
                    lstCustTemp = lstCustomers.subList(i, i + 1);
                    lstTemp = DataUtil.convertListCust2CustStatus(lstCustTemp, lstStaffs.get(i));
                    lstStaffs.get(i).setLstCustomers(lstTemp);
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
            }
            setData2TableStaff(lstStaffDTO);
        } else {
            CommonMessages.showMessageFail(BundleUtils.getString("customer.assign.customer.failed"));
        }
    }

    private void addListenerValueChangeCboCategoryList() {
        addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                CategoryListDTO categoryListDTO = (CategoryListDTO) cbxMineName.getValue();
                if (categoryListDTO != null) {
                    String mineName = categoryListDTO.getId();
                    Map<String, String> map = getValueFromSearchForm();
                    if (mineName == null) {
                        try {
                            List<String> lstMineName = DataUtil.getListValueFromList(lstCategoryList, Constants.CATEGORY_LIST.ID);
                            getTaxAuthorityAndProviderFromMineName(DataUtil.convertList2StringToSearchConditionIN(lstMineName), map);
                        } catch (NoSuchMethodException | IllegalAccessException ex) {
                            Logger.getLogger(MapStaffCustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        getTaxAuthorityAndProviderFromMineName(mineName, map);
                    }
                    Collection collection = (Collection) cboMultiCity.getValue();

                    cboMultiProvider.removeAllItems();
                    cboMultiCity.removeAllItems();
//                    cboMultiCity.markAsDirtyRecursive();
                    if (!DataUtil.isListNullOrEmpty(lstProvider)) {
                        cboMultiProvider.addItems(lstProvider);
                    }
                    if (!DataUtil.isListNullOrEmpty(lstTaxAuthority)) {
                        cboMultiCity.addItems(lstTaxAuthority);
                        if (collection != null && !collection.isEmpty()) {
                            TaxAuthorityDTO temp;
                            List<TaxAuthorityDTO> lst = new ArrayList<>();
                            for (Object o : collection) {
                                temp = (TaxAuthorityDTO) o;
                                if (lstTaxAuthority.contains(temp)) {
                                    lst.add(temp);
                                }
                            }
                            if (!DataUtil.isListNullOrEmpty(lst)) {
                                cboMultiCity.select(lst);
                            }
                        }
                    }
                    cboMultiCity.commit();
                    cboMultiProvider.markAsDirty();
                }
                cboMultiCity.focus();
            }
        });

    }

    private void getTaxAuthorityAndProviderFromMineName(String mineName, Map<String, String> map) {
        try {
            if (!StringUtils.isNullOrEmpty(mineName)) {
                lstTaxAuthority = WSTaxAuthority.getListTaxAuthorityFromMineName(mineName, map);
                lstProvider = WSAppParams.getListProviderFromMineName(mineName, map);
            } else {
                lstTaxAuthority = new ArrayList<>();
                lstProvider = new ArrayList<>();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addValueChangeListener(Property.ValueChangeListener valueChangeListener) {
        cbxMineName.addValueChangeListener(valueChangeListener);
        mapStaffCustomerDialog.getDfFromDateRegister().addValueChangeListener(valueChangeListener);
        mapStaffCustomerDialog.getDfToDateRegister().addValueChangeListener(valueChangeListener);
        mapStaffCustomerDialog.getDfFromStartTime().addValueChangeListener(valueChangeListener);
        mapStaffCustomerDialog.getDfToStartTime().addValueChangeListener(valueChangeListener);
        mapStaffCustomerDialog.getDfFromEndTime().addValueChangeListener(valueChangeListener);
        mapStaffCustomerDialog.getDfToEndTime().addValueChangeListener(valueChangeListener);
    }

    private Map<String, String> getValueFromSearchForm() {
        return mapStaffCustomerDialog.getConditionValue();
    }
}
