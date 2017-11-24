/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.anphat.customer.controller.SearchCustomerController;
import com.anphat.list.controller.ManageCustomerStatusController;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CommonOnePanelUI;
import com.cms.component.CustomPageTableFilter;
import com.cms.component.GridManyButton;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CategoryListDTO;
import com.cms.dto.CustomerStatusDTO;
import com.cms.dto.TaxAuthorityDTO;
import com.cms.login.ws.WSCategoryList;
import com.cms.login.ws.WSTaxAuthority;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.TableUtils;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vfw5.base.utils.DateUtil;
import com.vwf5.base.utils.ConditionBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ManageCustomerStatusView extends CommonOnePanelUI implements View {

    private CommonTableFilterPanel panelTblCustomerStatus;
    private CustomPageTableFilter tblCustomer;
    private Map<String, String> mapTaxAuthority;
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
            = BundleUtils.getHeadersFilter("customer.status.header");
    private final String CAPTION_CUSTOMER
            = "Danh sách khách hàng đã phân bổ";
    private BeanItemContainer containerCustomer;
    private List<AppParamsDTO> lstServices;
    private List<AppParamsDTO> lstCustomerStatus;
    private ComboComponent comboUtils;
    private List<CategoryListDTO> lstCategoryList;
    private Map<String, String> mapCustServiceStatus;
    private Map<String, String> mapMineName;

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
        dfFromStartTime = CommonUtils.buildDateField(BundleUtils.getString("term.information.fromStartTime"));
        dfToStartTime = CommonUtils.buildDateField(BundleUtils.getString("term.information.toStartTime"));

        cbxMineName = CommonUtils.buildComboBox(BundleUtils.getString("customer.mineName"));
        cbxMineName.setInputPrompt("Chọn danh sách");

        lstServices = DataUtil.getListApParams(Constants.APP_PARAMS.SERVICE_TYPE);
        lstCustomerStatus = DataUtil.getListApParams(Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS);
        comboUtils = new ComboComponent();
        //Khoi tao comboBox dich vu
        String serviceDefault = Constants.NULL;
        if (!DataUtil.isListNullOrEmpty(lstServices)) {
            serviceDefault = lstServices.get(0).getParCode();
        }
        comboUtils.fillDataCombo(cbxService, Constants.NULL, serviceDefault,
                lstServices, Constants.APP_PARAMS.SERVICE_TYPE);
        List<String> lstMineName = getListCategoryFromService();
        try {
            mapCustServiceStatus = DataUtil.buildHasmap(lstCustomerStatus, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
        } catch (Exception e) {
            mapCustServiceStatus = new HashMap();
        }
        
        cbxStaff = CommonUtils.buildComboBox("Nhân viên");
        cbxStaff.setRequired(true);

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

    private List<String> getListCategoryFromService() {
        List<String> lstMineName = null;
        try {
            List<ConditionBean> lstConditions = new ArrayList<>();
            AppParamsDTO serviceDTO = (AppParamsDTO) cbxService.getValue();
            if (serviceDTO != null && !DataUtil.isStringNullOrEmpty(serviceDTO.getParCode())) {
                lstConditions.add(new ConditionBean("service", serviceDTO.getParCode(), ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
            }
            lstCategoryList = WSCategoryList.getListCategoryListByCondition(lstConditions, 0, Constants.INT_100, Constants.ASC, Constants.CATEGORY_LIST.CODE);
            lstMineName = DataUtil.getListValueFromList(lstCategoryList, Constants.CATEGORY_LIST.ID);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SearchCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstMineName;
    }

    private void buildTableCustomerStatus() {
        // Get map tinh/thanh pho
        List<TaxAuthorityDTO> lstTaxAuthorityToMap = WSTaxAuthority.getListProvineTaxAuthority();
        if (!DataUtil.isListNullOrEmpty(lstTaxAuthorityToMap)) {
            try {
                mapTaxAuthority = DataUtil.buildHasmap(lstTaxAuthorityToMap, Constants.TAXAUTHORITY.MA_CQT, Constants.TAXAUTHORITY.TEN_CQT);
            } catch (Exception e) {
                mapTaxAuthority = new HashMap();
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
                containerCustomer, CAPTION_CUSTOMER, 5, "customer");

        CommonUtils.convertFieldTable(tblCustomer, "taxAuthority", mapTaxAuthority);
//        name#1,startTime#2,endTime#2,taxAuthority#1
        tblCustomer.setColumnExpandRatio("taxCode", 1);
        tblCustomer.setColumnExpandRatio("custName", 2);
        tblCustomer.setColumnExpandRatio("createdDate", 1);
        tblCustomer.setColumnExpandRatio("staffCode", 1);
        tblCustomer.setColumnExpandRatio("mineName", 1);
        tblCustomer.setColumnExpandRatio("taxAuthority", 1);
        layoutMain.addComponent(panelTblCustomerStatus);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
