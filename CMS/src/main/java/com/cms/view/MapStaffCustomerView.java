/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.anphat.list.controller.MapStaffCustomerViewController;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.cms.component.CommonOnePanelUI;
import com.cms.component.GridManyButton;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CategoryListDTO;
import com.cms.dto.TaxAuthorityDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vwf5.base.utils.ConditionBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vaadin.addons.comboboxmultiselect.ComboBoxMultiselect;

/**
 *
 * @author quyen
 */
public class MapStaffCustomerView extends CommonOnePanelUI implements View {

    /**
     *
     * @author quyen
     */
    private CommonTableFilterPanel panelTblCustomer;
    private CommonTableFilterPanel panelTblCustomerStatus;
    private GridLayout searchLayout;
    private HorizontalLayout mapLayout;
    private VerticalLayout leftLayout;
    private VerticalLayout rightLayout;
    private VerticalLayout centerLayout;
    private Button btnSearch;
    private Button btnReset;
    private Button btnExecute;
    private Button btnSave;
    private final String CAPTION = BundleUtils.getString("mapStaffCustomerForm.title");
    private final String SEARCH_LAYOUT = BundleUtils.getString("customer.management.header.search");
    private final String PANEL_CAPTION = BundleUtils.getString("staff.customer.map");

    private ComboBox cbxService;
    private ComboBox cbxStatus;

    private DateField dfFromStartTime;
    private DateField dfToStartTime;
    private DateField dfFromEndTime;
    private DateField dfToEndTime;
    private DateField dfFromDateRegister;
    private DateField dfToDateRegister;
    private ComboBox cbxMaxSearch;
    private ComboBox cbxMineName;
    private Button btnGetData;

    private TextField tfMineName;

    private ComboBoxMultiselect cboMulProvider;
    private ComboBoxMultiselect cboMulCity;
    
    
    public MapStaffCustomerView() {
        layoutMain.setMargin(true);
        layoutMain.setSpacing(true);
        panelMain.setCaption(PANEL_CAPTION);
        buildViewLayout();

        MapStaffCustomerViewController controller = new MapStaffCustomerViewController(this);
    }

    private void buildViewLayout() {
        buildSearchLayout();
        buildMapLayout();
        layoutMain.addComponent(mapLayout);
    }

    private void buildMapLayout() {
        // leftLayout
        mapLayout = new HorizontalLayout();
        mapLayout.setWidth("100%");
        mapLayout.setHeight("-1px");
        mapLayout.setImmediate(true);
        mapLayout.setMargin(true);
        mapLayout.setSpacing(true);
        buildFirstLayout();
        mapLayout.addComponent(leftLayout);
        mapLayout.setExpandRatio(leftLayout, 1.0f);

        // centerLayout
//        buildCenterLayout();
//        mapLayout.addComponent(centerLayout);
//        mapLayout.setComponentAlignment(centerLayout, Alignment.MIDDLE_CENTER);
        // rightLayout
        buildCustomerStatusLayout();
        mapLayout.addComponent(rightLayout);
        mapLayout.setExpandRatio(rightLayout, 1.0f);
    }

    private void buildFirstLayout() {
        // common part: create layout
        leftLayout = new VerticalLayout();
        leftLayout.setImmediate(true);
        leftLayout.setWidth("100.0%");
        leftLayout.setHeight("-1px");
        leftLayout.setMargin(true);
        leftLayout.setSpacing(true);

        // tblLeftLayout
        panelTblCustomer = new CommonTableFilterPanel();
        panelTblCustomer.getToolbar().setVisible(false);
        leftLayout.addComponent(panelTblCustomer);
    }

    private void buildComboBoxMultiProvider() {
        // Initialize the ComboBoxMultiselect
        cboMulProvider = new ComboBoxMultiselect();
        cboMulProvider.setImmediate(true);
        cboMulProvider.setInputPrompt("Chọn nhà cung cấp");
        cboMulProvider.setCaption("Nhà cung cấp");
        cboMulProvider.setWidth("100%");
        cboMulProvider.setClearButtonCaption("Bỏ chọn");
        cboMulProvider.setSelectAllButtonCaption("Tất cả");
        cboMulProvider.setShowSelectAllButton(new ComboBoxMultiselect.ShowButton() {
            @Override
            public boolean isShow(String filter, int page) {
                return true;
            }
        });
        cboMulProvider.setItemCaptionPropertyId(Constants.APP_PARAMS.PAR_CODE);
        searchLayout.addComponent(cboMulProvider, 2, 0);
    }

    private void buildComboBoxMultiCity() {
        cboMulCity = new ComboBoxMultiselect();
        cboMulCity.setImmediate(true);
        cboMulCity.setInputPrompt("Chọn tỉnh");
        cboMulCity.setCaption("Tỉnh");
        cboMulCity.setWidth("100%");
        cboMulCity.setClearButtonCaption("Bỏ chọn");
        cboMulCity.setSelectAllButtonCaption("Tất cả");
        cboMulCity.setShowSelectedOnTop(true);
        cboMulCity.setShowSelectAllButton(new ComboBoxMultiselect.ShowButton() {
            @Override
            public boolean isShow(String filter, int page) {
                return true;
            }
        });
        cboMulCity.setItemCaptionPropertyId("tenCqt");

        searchLayout.addComponent(cboMulCity, 3, 0);
    }

    private void buildSearchLayout() {
        searchLayout = new GridLayout(4, 3);
        CommonUtils.setBasicAttributeLayout(searchLayout, SEARCH_LAYOUT, true);
        cbxService = CommonUtils.buildComboBox(BundleUtils.getString("customerStatusForm.service"));
        cbxService.setRequired(true);
        cbxStatus = CommonUtils.buildComboBox(BundleUtils.getString("customerStatusForm.status"));
        dfFromStartTime = CommonUtils.buildDateField(BundleUtils.getString("term.information.fromStartTime"));
        dfToStartTime = CommonUtils.buildDateField(BundleUtils.getString("term.information.toStartTime"));
        dfFromEndTime = CommonUtils.buildDateField(BundleUtils.getString("term.information.fromEndTime"));
        dfToEndTime = CommonUtils.buildDateField(BundleUtils.getString("term.information.toEndTime"));
        dfFromDateRegister = CommonUtils.buildDateField(BundleUtils.getString("term.information.fromDateRegister"));
        dfToDateRegister = CommonUtils.buildDateField(BundleUtils.getString("term.information.toDateRegister"));
        cbxMaxSearch = CommonUtils.buildComboBox(BundleUtils.getString("max.search"));
        cbxMineName = CommonUtils.buildComboBox(BundleUtils.getString("customer.mineName"));
        cbxMineName.setInputPrompt("Chọn danh sách");
        tfMineName = CommonUtils.buildTextField(BundleUtils.getString("customer.mineName"), 100);
        cbxMineName.setRequired(true);
        
        searchLayout.addComponent(cbxService, 0, 0);
        searchLayout.addComponent(cbxMineName, 1, 0);

        searchLayout.addComponent(dfFromStartTime, 0, 1);
        searchLayout.addComponent(dfToStartTime, 1, 1);
        searchLayout.addComponent(dfFromEndTime, 2, 1);
        searchLayout.addComponent(dfToEndTime, 3, 1);

        searchLayout.addComponent(dfFromDateRegister, 0, 2);
        searchLayout.addComponent(dfToDateRegister, 1, 2);

        buildComboBoxMultiProvider();//0,2
        buildComboBoxMultiCity();// 1,2        
        searchLayout.addComponent(cbxMaxSearch, 2, 2);

        layoutMain.addComponent(searchLayout);

        GridManyButton gridManyButton = new GridManyButton(
                new String[]{Constants.BUTTON_SEARCH, Constants.BUTTON_REFRESH,
                    Constants.BUTTON_INSERT, Constants.BUTTON_SAVE});
        btnSearch = gridManyButton.getBtnCommon().get(0);
        btnReset = gridManyButton.getBtnCommon().get(1);
        btnExecute = gridManyButton.getBtnCommon().get(2);
        btnExecute.setCaption(BundleUtils.getString("staff.customer.map"));
        btnSave = gridManyButton.getBtnCommon().get(3);
        layoutMain.addComponent(gridManyButton);
    }

    private void buildCustomerStatusLayout() {
        rightLayout = new VerticalLayout();
        rightLayout.setImmediate(true);
        rightLayout.setWidth("100.0%");
        rightLayout.setHeight("-1px");
        rightLayout.setMargin(true);
        rightLayout.setSpacing(true);
        panelTblCustomerStatus = new CommonTableFilterPanel();
        panelTblCustomerStatus.getToolbar().setVisible(false);
        rightLayout.addComponent(panelTblCustomerStatus);
    }

    //Reset all data
    public void doResetData() {
        cbxMineName.clear();
        cboMulProvider.clear();
        cboMulCity.clear();
        dfFromStartTime.clear();
        dfToStartTime.clear();
        dfFromEndTime.clear();
        dfToEndTime.clear();
        dfFromDateRegister.clear();
        dfToDateRegister.clear();
        cbxService.clear();
        cbxStatus.clear();
    }

    //Get CustomerDTO to Search
    public List<ConditionBean> getLstCondition2Search() {
        List<ConditionBean> lstConditionBeans = new ArrayList<>();
        //Lay ma so thue
        String fromStartTime = DataUtil.getDateNullOrZero(dfFromStartTime);
        String toStartTime = DataUtil.getDateNullOrZero(dfToStartTime);
        String fromEndTime = DataUtil.getDateNullOrZero(dfFromEndTime);
        String toEndTime = DataUtil.getDateNullOrZero(dfToEndTime);
        String fromDateRegister = DataUtil.getDateNullOrZero(dfFromDateRegister);
        String toDateRegister = DataUtil.getDateNullOrZero(dfToDateRegister);
        if (!DataUtil.isStringNullOrEmpty(fromStartTime)) {
            lstConditionBeans.add(new ConditionBean("startTime", fromStartTime,
                    ConditionBean.Operator.NAME_GREATER_EQUAL, ConditionBean.Type.DATE));
        }

        if (!DataUtil.isStringNullOrEmpty(toStartTime)) {
            lstConditionBeans.add(new ConditionBean("startTime", toStartTime,
                    ConditionBean.Operator.NAME_LESS_EQUAL, ConditionBean.Type.DATE));
        }

        if (!DataUtil.isStringNullOrEmpty(fromEndTime)) {
            lstConditionBeans.add(new ConditionBean("endTime", fromEndTime,
                    ConditionBean.Operator.NAME_GREATER_EQUAL, ConditionBean.Type.DATE));
        }

        if (!DataUtil.isStringNullOrEmpty(toEndTime)) {
            lstConditionBeans.add(new ConditionBean("endTime", toEndTime,
                    ConditionBean.Operator.NAME_LESS_EQUAL, ConditionBean.Type.DATE));
        }

        if (!DataUtil.isStringNullOrEmpty(fromDateRegister)) {
            lstConditionBeans.add(new ConditionBean("fromDateRegister", fromDateRegister,
                    ConditionBean.Operator.NAME_GREATER_EQUAL, ConditionBean.Type.DATE));
        }

        if (!DataUtil.isStringNullOrEmpty(toDateRegister)) {
            lstConditionBeans.add(new ConditionBean("toDateRegister", toDateRegister,
                    ConditionBean.Operator.NAME_LESS_EQUAL, ConditionBean.Type.DATE));
        }

        CategoryListDTO mineNameDTO = (CategoryListDTO) cbxMineName.getValue();
        if (mineNameDTO != null && !DataUtil.isStringNullOrEmpty(mineNameDTO.getId())) {
            String mineName = mineNameDTO.getId();
            lstConditionBeans.add(new ConditionBean("mineName", mineName,
                    ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.NUMBER));
        }

        AppParamsDTO serviceDTO = (AppParamsDTO) cbxService.getValue();
        if (serviceDTO != null && !DataUtil.isStringNullOrEmpty(serviceDTO.getParCode())) {
            lstConditionBeans.add(new ConditionBean("service", serviceDTO.getParCode(),
                    ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
        }
        AppParamsDTO maxSearchDTO = (AppParamsDTO) cbxMaxSearch.getValue();
        if (maxSearchDTO != null && !DataUtil.isStringNullOrEmpty(maxSearchDTO.getParCode())) {
            lstConditionBeans.add(new ConditionBean("maxSearch", maxSearchDTO.getParCode(),
                    ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
        }
        List<TaxAuthorityDTO> taxAuthorityDTOs = new ArrayList();
        Collection co = (Collection) cboMulCity.getValue();
        taxAuthorityDTOs.addAll(co);
        List<String> taxAuthority = null;
        try {
            taxAuthority = DataUtil.getListValueFromList(taxAuthorityDTOs, Constants.TAXAUTHORITY.MA_CQT);
        } catch (NoSuchMethodException | IllegalAccessException ex) {
            Logger.getLogger(MapStaffCustomerView.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!DataUtil.isListNullOrEmpty(taxAuthority)) {
            String taxAuthorityCondition = DataUtil.convertList2StringToSearchConditionIN(taxAuthority);
            lstConditionBeans.add(new ConditionBean("taxAuthority", taxAuthorityCondition,
                    ConditionBean.Operator.NAME_IN, ConditionBean.Type.STRING));
        }
        Collection coProvider = (Collection) cboMulProvider.getValue();
        List<AppParamsDTO> providerDTOs = new ArrayList<>();
        providerDTOs.addAll(coProvider);
        List<String> provider = null;
        try {
            provider = DataUtil.getListValueFromList(providerDTOs, Constants.APP_PARAMS.PAR_CODE);
        } catch (NoSuchMethodException | IllegalAccessException ex) {
            Logger.getLogger(MapStaffCustomerView.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!DataUtil.isListNullOrEmpty(provider)) {
            String multiProvider = DataUtil.convertList2StringToSearchConditionIN(provider);
            lstConditionBeans.add(new ConditionBean("provider", multiProvider,
                    ConditionBean.Operator.NAME_IN, ConditionBean.Type.STRING));
        }
        return lstConditionBeans;
    }

    public CommonTableFilterPanel getPanelTblCustomer() {
        return panelTblCustomer;
    }

    public CommonTableFilterPanel getPanelTblCustomerStatus() {
        return panelTblCustomerStatus;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public Button getBtnReset() {
        return btnReset;
    }

    public Button getBtnExecute() {
        return btnExecute;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public ComboBox getCbxService() {
        return cbxService;
    }

    public ComboBox getCbxStatus() {
        return cbxStatus;
    }

    public ComboBox getCbxMaxSearch() {
        return cbxMaxSearch;
    }

    public ComboBox getCbxMineName() {
        return cbxMineName;
    }

    public ComboBoxMultiselect getCboMulProvider() {
        return cboMulProvider;
    }

    public void setCboMulProvider(ComboBoxMultiselect cboMulProvider) {
        this.cboMulProvider = cboMulProvider;
    }

    public ComboBoxMultiselect getCboMulCity() {
        return cboMulCity;
    }

    public void setCboMulCity(ComboBoxMultiselect cboMulCity) {
        this.cboMulCity = cboMulCity;
    }

    public Map<String, String> getConditionValue() {
        Map<String, String> map = new HashMap<>();
        String fromStartTime = DataUtil.getDateNullOrZero(dfFromStartTime);
        String toStartTime = DataUtil.getDateNullOrZero(dfToStartTime);
        String fromEndTime = DataUtil.getDateNullOrZero(dfFromEndTime);
        String toEndTime = DataUtil.getDateNullOrZero(dfToEndTime);
        String fromDateRegister = DataUtil.getDateNullOrZero(dfFromDateRegister);
        String toDateRegister = DataUtil.getDateNullOrZero(dfToDateRegister);

        //Get provider
        Collection coProvider = (Collection) cboMulProvider.getValue();
        if (coProvider != null & !coProvider.isEmpty()) {
            List<AppParamsDTO> providerDTOs = new ArrayList<>();
            providerDTOs.addAll(coProvider);

            List<String> provider = null;
            try {
                provider = DataUtil.getListValueFromList(providerDTOs, Constants.APP_PARAMS.PAR_CODE);
            } catch (NoSuchMethodException | IllegalAccessException ex) {
                Logger.getLogger(MapStaffCustomerView.class.getName()).log(Level.SEVERE, null, ex);
            }
            String multiProvider;
            if (!DataUtil.isListNullOrEmpty(provider)) {
                multiProvider = DataUtil.convertList2StringToSearchConditionIN(provider);
                map.put("provider", multiProvider);
            }
        }
        //Get Tax Authority
        List<TaxAuthorityDTO> taxAuthorityDTOs = new ArrayList();
        Collection co = (Collection) cboMulCity.getValue();
        if (co != null & !co.isEmpty()) {
            taxAuthorityDTOs.addAll(co);
            List<String> taxAuthority = null;
            try {
                taxAuthority = DataUtil.getListValueFromList(taxAuthorityDTOs, Constants.TAXAUTHORITY.MA_CQT);
            } catch (NoSuchMethodException | IllegalAccessException ex) {
                Logger.getLogger(MapStaffCustomerView.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!DataUtil.isListNullOrEmpty(taxAuthority)) {
                String taxAuthorityCondition = DataUtil.convertList2StringToSearchConditionIN(taxAuthority);
                map.put("taxAuthority", taxAuthorityCondition);
            }
        }
        // Get Start from date
        if (!DataUtil.isStringNullOrEmpty(fromStartTime)) {
            map.put("startFromDate", fromStartTime);
        }
        // Get End from date
        if (!DataUtil.isStringNullOrEmpty(toStartTime)) {
            map.put("endFromDate", toStartTime);
        }
        // Get start to date
        if (!DataUtil.isStringNullOrEmpty(fromEndTime)) {
            map.put("startToDate", fromEndTime);
        }
        // Get end to date
        if (!DataUtil.isStringNullOrEmpty(toEndTime)) {
            map.put("endToDate", toEndTime);
        }
        // Get from date register
        if (!DataUtil.isStringNullOrEmpty(fromDateRegister)) {
            map.put("fromDateRegister", fromDateRegister);
        }
        // Get to date register
        if (!DataUtil.isStringNullOrEmpty(toDateRegister)) {
            map.put("toDateRegister", toDateRegister);
        }
        return map;
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

    public DateField getDfFromEndTime() {
        return dfFromEndTime;
    }

    public void setDfFromEndTime(DateField dfFromEndTime) {
        this.dfFromEndTime = dfFromEndTime;
    }

    public DateField getDfToEndTime() {
        return dfToEndTime;
    }

    public void setDfToEndTime(DateField dfToEndTime) {
        this.dfToEndTime = dfToEndTime;
    }

    public DateField getDfFromDateRegister() {
        return dfFromDateRegister;
    }

    public void setDfFromDateRegister(DateField dfFromDateRegister) {
        this.dfFromDateRegister = dfFromDateRegister;
    }

    public DateField getDfToDateRegister() {
        return dfToDateRegister;
    }

    public void setDfToDateRegister(DateField dfToDateRegister) {
        this.dfToDateRegister = dfToDateRegister;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
