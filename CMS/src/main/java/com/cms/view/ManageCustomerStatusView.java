/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.anphat.list.controller.ManageCustomerStatusController;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CommonOnePanelUI;
import com.cms.component.CustomPageTableFilter;
import com.cms.component.GridManyButton;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CategoryListDTO;
import com.cms.dto.CustomerStatusDTO;
import com.cms.dto.StaffDTO;
import com.cms.dto.TaxAuthorityDTO;
import com.cms.login.ws.WSCategoryList;
import com.cms.login.ws.WSStaff;
import com.cms.login.ws.WSTaxAuthority;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.DateUtil;
import com.cms.utils.TableUtils;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vwf5.base.utils.ConditionBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Administrator
 */
public class ManageCustomerStatusView extends CommonOnePanelUI implements View {

    private CommonTableFilterPanel panelTblCustomerStatus;
    private CustomPageTableFilter tblCustomer;
    private Map<String, String> mapTaxAuthority;
    private Map<String, String> mapMineName;
    private GridLayout searchLayout;
    private Button btnSearch;
    private Button btnDelete;
    private ComboBox cbxService;
    private ComboBox cbxStatus;
    private ComboBox cbxMineName;
    private ComboBox cbxStaff;

    private DateField dfFromStartTime;
    private DateField dfToStartTime;
    private static final LinkedHashMap<String, CustomTable.Align> HEADER_CUSTOMER
            = BundleUtils.getHeadersFilter("customer.status.manager.header");
    private final String CAPTION_CUSTOMER
            = "Danh sách khách hàng đã phân bổ";
    private BeanItemContainer containerCustomer;
    private List<AppParamsDTO> lstServices;
    private List<AppParamsDTO> lstCustomerStatus;
    private ComboComponent comboUtils;
    private List<CategoryListDTO> lstCategoryList;
    private Map<String, String> mapCustServiceStatus;
    private List<StaffDTO> lstStaffs;

    public ManageCustomerStatusView() {
        layoutMain.setMargin(true);
        layoutMain.setSpacing(true);
        panelMain.setCaption("Xoá khách hàng đã phân bổ");
        buildSearchLayout();
        buildTableCustomerStatus();
        ManageCustomerStatusController controller = new ManageCustomerStatusController(this);
        controller.initialScreen();
    }

    private void buildSearchLayout() {
        searchLayout = new GridLayout(4, 2);
        CommonUtils.setBasicAttributeLayout(searchLayout, "Tìm kiếm khách hàng đã phân bổ", true);

        cbxService = CommonUtils.buildComboBox("Dịch vụ");
        cbxService.setRequired(true);
        cbxStatus = CommonUtils.buildComboBox(BundleUtils.getString("customerStatusForm.status"));
        dfFromStartTime = CommonUtils.buildDateField("Từ ngày phân bổ");
        dfToStartTime = CommonUtils.buildDateField("Đến ngày phân bổ");

        cbxMineName = CommonUtils.buildComboBox(BundleUtils.getString("customer.mineName"));
        cbxMineName.setInputPrompt("Chọn danh sách");

        lstServices = DataUtil.getListApParams(Constants.APP_PARAMS.SERVICE_TYPE);
        lstCustomerStatus = DataUtil.getListApParams(Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS);
        // Loại bỏ những trường hợp OK
        List<String> lstNoDelete = Arrays.asList("3", "8", "16");
        lstCustomerStatus = lstCustomerStatus.stream().filter(a -> !lstNoDelete.contains(a.getParCode())).collect(Collectors.toList());
        comboUtils = new ComboComponent();
        //Khoi tao comboBox dich vu
        String serviceDefault = Constants.NULL;
        if (!DataUtil.isListNullOrEmpty(lstServices)) {
            serviceDefault = lstServices.get(0).getParCode();
        }
        comboUtils.fillDataCombo(cbxService, Constants.NULL, serviceDefault,
                lstServices, Constants.APP_PARAMS.SERVICE_TYPE);
        List<ConditionBean> lstConditions = new ArrayList<>();
        AppParamsDTO serviceDTO = (AppParamsDTO) cbxService.getValue();
        if (serviceDTO != null && !DataUtil.isStringNullOrEmpty(serviceDTO.getParCode())) {
            lstConditions.add(new ConditionBean("service", serviceDTO.getParCode(), ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
        }
        try {
            lstCategoryList = WSCategoryList.getListCategoryListByCondition(lstConditions, 0, Constants.INT_100, Constants.ASC, Constants.CATEGORY_LIST.CODE);
        } catch (Exception e) {
            lstCategoryList = new ArrayList<>();
        }
        try {
            mapCustServiceStatus = DataUtil.buildHasmap(lstCustomerStatus, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
        } catch (Exception e) {
            mapCustServiceStatus = new HashMap();
        }
        comboUtils.setValues(cbxMineName, lstCategoryList, Constants.CATEGORY_LIST.NAME, true);
        //Khoi tao comboBox customerStatus
        String custStatusDefault = Constants.NULL;
        if (!DataUtil.isListNullOrEmpty(lstCustomerStatus)) {
            custStatusDefault = lstCustomerStatus.get(0).getParCode();
        }
        comboUtils.fillDataCombo(cbxStatus, Constants.NULL,
                custStatusDefault, lstCustomerStatus, Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS);
        cbxStaff = CommonUtils.buildComboBox("Nhân viên");
        cbxStaff.setRequired(true);

        try {
            StaffDTO search = new StaffDTO();
            search.setStaffType(Constants.STAFF.STAFF_TYPE_3);
            search.setStatus(Constants.ACTIVE);
            lstStaffs = WSStaff.getListStaffDTO(search, 0, Constants.INT_100, "asc", "code");
        } catch (Exception e) {
            lstStaffs = new ArrayList<>();
//            e.printStackTrace();
        }
        comboUtils.setValues(cbxStaff, lstStaffs, Constants.STAFF.CODE, Boolean.TRUE);
        searchLayout.addComponent(cbxService, 0, 0);
        searchLayout.addComponent(cbxMineName, 1, 0);
        searchLayout.addComponent(cbxStaff, 2, 0);
        searchLayout.addComponent(cbxStatus, 3, 0);

        searchLayout.addComponent(dfFromStartTime, 0, 1);
        searchLayout.addComponent(dfToStartTime, 1, 1);

        layoutMain.addComponent(searchLayout);

        GridManyButton gridManyButton = new GridManyButton(
                new String[]{Constants.BUTTON_SEARCH, Constants.BUTTON_DELETE});
        btnSearch = gridManyButton.getBtnCommon().get(0);

        btnDelete = gridManyButton.getBtnCommon().get(1);
        layoutMain.addComponent(gridManyButton);
    }

    private void buildTableCustomerStatus() {
        // Get map tinh/thanh pho
        List<TaxAuthorityDTO> lstTaxAuthorityToMap = WSTaxAuthority.getListProvineTaxAuthority();
        if (!DataUtil.isListNullOrEmpty(lstTaxAuthorityToMap)) {
            try {
                mapTaxAuthority = DataUtil.buildHasmap(lstTaxAuthorityToMap, Constants.TAXAUTHORITY.MA_CQT, Constants.TAXAUTHORITY.TEN_CQT);
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException e) {
                mapTaxAuthority = new HashMap();
            }
        }
        CategoryListDTO category = new CategoryListDTO();
        List<CategoryListDTO> lstMineName = WSCategoryList.getListCategoryListDTO(
                category, 0, Integer.MAX_VALUE, Constants.ASC, Constants.CATEGORY_LIST.CODE);
        if (!DataUtil.isListNullOrEmpty(lstMineName)) {
            try {
                mapMineName = DataUtil.buildHasmap(lstMineName, Constants.CATEGORY_LIST.ID, Constants.CATEGORY_LIST.NAME);
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException e) {
                mapMineName = new HashMap();
            }
        }

        panelTblCustomerStatus = new CommonTableFilterPanel();
        panelTblCustomerStatus.setToolbar(Boolean.FALSE);
        containerCustomer = new BeanItemContainer(CustomerStatusDTO.class);
        TableUtils tblUtils = new TableUtils();
        //Khoi tao bang khach hang
        tblCustomer = panelTblCustomerStatus.getMainTable();
        tblUtils.generateColumn(tblCustomer);
        CommonFunctionTableFilter.initTable(panelTblCustomerStatus, HEADER_CUSTOMER,
                containerCustomer, CAPTION_CUSTOMER, -1, "customer");

        CommonUtils.convertFieldTable(tblCustomer, "taxAuthority", mapTaxAuthority);
        CommonUtils.convertFieldTable(tblCustomer, "mineName", mapMineName);
        CommonUtils.convertFieldTable(tblCustomer, "status", mapCustServiceStatus);
//        name#1,startTime#2,endTime#2,taxAuthority#1
        tblCustomer.setColumnExpandRatio("taxCode", 1);
        tblCustomer.setColumnExpandRatio("custName", 2);
        tblCustomer.setColumnExpandRatio("createdDate", 1);
        tblCustomer.setColumnExpandRatio("staffCode", 1);
        tblCustomer.setColumnExpandRatio("mineName", 1);
        tblCustomer.setColumnExpandRatio("taxAuthority", 1);
        tblCustomer.setColumnExpandRatio("status", 1);
        layoutMain.addComponent(panelTblCustomerStatus);
    }

    public List<ConditionBean> getSearchCondition() {
        List<ConditionBean> conditions = new ArrayList<>();
        // Dich vu
        AppParamsDTO serviceDTO = (AppParamsDTO) cbxService.getValue();
        if (!DataUtil.isStringNullOrEmpty(serviceDTO.getParCode())) {
            conditions.add(new ConditionBean("service", serviceDTO.getParCode(),
                    ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
        }
        // Danh sach khai thac
        CategoryListDTO categoryDTO = (CategoryListDTO) cbxMineName.getValue();
        if (!DataUtil.isStringNullOrEmpty(categoryDTO.getCode())) {
            conditions.add(new ConditionBean("mineName", categoryDTO.getId(),
                    ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
        }
        // Nhan vien
        StaffDTO staffDTO = (StaffDTO) cbxStaff.getValue();
        if (!DataUtil.isStringNullOrEmpty(staffDTO.getName())) {
            conditions.add(new ConditionBean("staffCode", staffDTO.getCode(),
                    ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
        }
        // Trang thai
        AppParamsDTO statusDTO = (AppParamsDTO) cbxStatus.getValue();
        if (!DataUtil.isStringNullOrEmpty(statusDTO.getParCode())) {
            conditions.add(new ConditionBean("status", statusDTO.getParCode(),
                    ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.NUMBER));
        }
        // Tu ngay tao
        Date fromCreatedDate = dfFromStartTime.getValue();
        if (fromCreatedDate != null) {
            conditions.add(new ConditionBean("createdDate", DateUtil.date2ddMMyyyyString(fromCreatedDate),
                    ConditionBean.Operator.NAME_GREATER_EQUAL, ConditionBean.Type.DATE));
        }
        // Den ngay tao
        Date toCreatedDate = dfToStartTime.getValue();
        if (toCreatedDate != null) {
            conditions.add(new ConditionBean("createdDate", DateUtil.date2ddMMyyyyString(toCreatedDate),
                    ConditionBean.Operator.NAME_LESS_EQUAL, ConditionBean.Type.DATE));
        }
        return conditions;

    }

    public void setData2TableCustomerStatus(List<CustomerStatusDTO> lstCustomerStatus) {
        containerCustomer.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstCustomerStatus)) {
            containerCustomer.addAll(lstCustomerStatus);
        }
        CommonFunctionTableFilter.refreshTable(panelTblCustomerStatus, HEADER_CUSTOMER, containerCustomer);
    }

    public boolean isValidSearchCondition() {
        StaffDTO staff = (StaffDTO) cbxStaff.getValue();
        if (DataUtil.isStringNullOrEmpty(staff.getName())) {
            cbxStaff.focus();
            CommonUtils.showMessageRequired("customer.staffCode");
            return false;
        }
        // Trang thai
        AppParamsDTO statusDTO = (AppParamsDTO) cbxStatus.getValue();
        if (DataUtil.isStringNullOrEmpty(statusDTO.getParCode())) {
            cbxStatus.focus();
            CommonUtils.showMessageRequired("customer.status");
            return false;
        }
        return true;
    }

    public Collection getAllDataInTableCustomerStatus(){
        return tblCustomer.getItemIds();
    }
    public Set<CustomerStatusDTO> getSelectedCustomerStatus() {
        return (Set) tblCustomer.getValue();
    }

    public boolean isValidDeleteCondition() {
        Set<CustomerStatusDTO> selected = (Set) tblCustomer.getValue();
        if (selected == null || selected.isEmpty()) {
            CommonUtils.showChoseOne();
            return false;
        }
        return true;
    }

    public void setData2CategoryList(List<CategoryListDTO> lstCategories) {
        comboUtils.setValues(cbxMineName, lstCategories, Constants.CATEGORY_LIST.NAME, true);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public CommonTableFilterPanel getPanelTblCustomerStatus() {
        return panelTblCustomerStatus;
    }

    public void setPanelTblCustomerStatus(CommonTableFilterPanel panelTblCustomerStatus) {
        this.panelTblCustomerStatus = panelTblCustomerStatus;
    }

    public CustomPageTableFilter getTblCustomer() {
        return tblCustomer;
    }

    public void setTblCustomer(CustomPageTableFilter tblCustomer) {
        this.tblCustomer = tblCustomer;
    }

    public Map<String, String> getMapTaxAuthority() {
        return mapTaxAuthority;
    }

    public void setMapTaxAuthority(Map<String, String> mapTaxAuthority) {
        this.mapTaxAuthority = mapTaxAuthority;
    }

    public GridLayout getSearchLayout() {
        return searchLayout;
    }

    public void setSearchLayout(GridLayout searchLayout) {
        this.searchLayout = searchLayout;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(Button btnSearch) {
        this.btnSearch = btnSearch;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    public ComboBox getCbxService() {
        return cbxService;
    }

    public void setCbxService(ComboBox cbxService) {
        this.cbxService = cbxService;
    }

    public ComboBox getCbxStatus() {
        return cbxStatus;
    }

    public void setCbxStatus(ComboBox cbxStatus) {
        this.cbxStatus = cbxStatus;
    }

    public ComboBox getCbxMineName() {
        return cbxMineName;
    }

    public void setCbxMineName(ComboBox cbxMineName) {
        this.cbxMineName = cbxMineName;
    }

    public ComboBox getCbxStaff() {
        return cbxStaff;
    }

    public void setCbxStaff(ComboBox cbxStaff) {
        this.cbxStaff = cbxStaff;
    }

    public DateField getDfFromStartTime() {
        return dfFromStartTime;
    }

    public void setDfFromStartTime(DateField dfFromStartTime) {
        this.dfFromStartTime = dfFromStartTime;
    }

    public DateField getDfToStartTime() {
        return dfToStartTime;
    }

    public void setDfToStartTime(DateField dfToStartTime) {
        this.dfToStartTime = dfToStartTime;
    }

    public BeanItemContainer getContainerCustomer() {
        return containerCustomer;
    }

    public void setContainerCustomer(BeanItemContainer containerCustomer) {
        this.containerCustomer = containerCustomer;
    }

    public List<AppParamsDTO> getLstServices() {
        return lstServices;
    }

    public void setLstServices(List<AppParamsDTO> lstServices) {
        this.lstServices = lstServices;
    }

    public List<AppParamsDTO> getLstCustomerStatus() {
        return lstCustomerStatus;
    }

    public void setLstCustomerStatus(List<AppParamsDTO> lstCustomerStatus) {
        this.lstCustomerStatus = lstCustomerStatus;
    }

    public ComboComponent getComboUtils() {
        return comboUtils;
    }

    public void setComboUtils(ComboComponent comboUtils) {
        this.comboUtils = comboUtils;
    }

    public List<CategoryListDTO> getLstCategoryList() {
        return lstCategoryList;
    }

    public void setLstCategoryList(List<CategoryListDTO> lstCategoryList) {
        this.lstCategoryList = lstCategoryList;
    }

    public Map<String, String> getMapCustServiceStatus() {
        return mapCustServiceStatus;
    }

    public void setMapCustServiceStatus(Map<String, String> mapCustServiceStatus) {
        this.mapCustServiceStatus = mapCustServiceStatus;
    }

    public Map<String, String> getMapMineName() {
        return mapMineName;
    }

    public void setMapMineName(Map<String, String> mapMineName) {
        this.mapMineName = mapMineName;
    }

    public List<StaffDTO> getLstStaffs() {
        return lstStaffs;
    }

    public void setLstStaffs(List<StaffDTO> lstStaffs) {
        this.lstStaffs = lstStaffs;
    }

}
