/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.statistics.controller;

import com.anphat.statistics.ui.StatisticsCategoryListSearchPanel;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CategoryListDTO;
import com.cms.dto.StaffDTO;
import com.cms.dto.StatisticsCategoryListDTO;
import com.cms.login.ws.WSCategoryList;
import com.cms.login.ws.WSCustomerStatus;
import com.cms.utils.DataUtil;
import java.util.LinkedHashMap;
import com.cms.login.ws.WSStaff;
import com.cms.ui.CommonButtonClickListener;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonMessages;
import com.cms.utils.Constants;
import com.cms.view.StatisticsCategoryListView;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomTable;
import com.vwf5.base.utils.ConditionBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author quyen
 */
public class StatisticsCategoryListController {

    private final StatisticsCategoryListView viewStatisticsCategoryList;
    private StatisticsCategoryListSearchPanel searchForm;
    private List<AppParamsDTO> lstService;
    private List<CategoryListDTO> lstCategoryList;
    private List<StaffDTO> lstStaff;
    private ComboComponent comboBoxUtil;
    private final LinkedHashMap<String, CustomTable.Align> HEADER = BundleUtils.getHeadersFilter("statistics.categoryList.header");
    static final String CAPTION = BundleUtils.getString("title.statistics.categoryList");
    static final String LANG = "statistics.categoryList";
    static final int SIZE = 10;
    private BeanItemContainer tblContainer;
    private CustomPageTableFilter tblStatisticsCategoryList;
    private List<StatisticsCategoryListDTO> lstStatisticsCategoryListDTOs;
    private CommonTableFilterPanel panelStatisticsCategoryList;
    private StaffDTO staff;
    private ComboBox cboService;

    public StatisticsCategoryListController(StatisticsCategoryListView viewStatisticsCategoryList) {
        this.viewStatisticsCategoryList = viewStatisticsCategoryList;
        searchForm = viewStatisticsCategoryList.getCategoryListForm();
        init();
    }

    private void init() {
        getDatas();
        initSearchForm();
        initTable();
        addActionListeners();
    }

    private void initTable() {
        panelStatisticsCategoryList = viewStatisticsCategoryList.getPanelTblStatisticsCategoryList();
        tblStatisticsCategoryList = panelStatisticsCategoryList.getMainTable();
        tblStatisticsCategoryList.setMultiSelect(false);
        tblContainer = new BeanItemContainer<>(StatisticsCategoryListDTO.class);
        addColumnTotalQuanlity();
        addColumnContactedQuanlity();
        CommonFunctionTableFilter.initTable(panelStatisticsCategoryList, HEADER, tblContainer, CAPTION, 10, LANG);

        buildFooter();
    }

    private void addColumnTotalQuanlity() {
        tblStatisticsCategoryList.addGeneratedColumn("totalQuanlity", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                Long totalQuanlity = 0L;
                StatisticsCategoryListDTO scld = (StatisticsCategoryListDTO) itemId;
                totalQuanlity += Long.parseLong(scld.getStatus1());
                totalQuanlity += Long.parseLong(scld.getStatus2());
                totalQuanlity += Long.parseLong(scld.getStatus3());
                totalQuanlity += Long.parseLong(scld.getStatus4());
                totalQuanlity += Long.parseLong(scld.getStatus5());
                totalQuanlity += Long.parseLong(scld.getStatus6());
                totalQuanlity += Long.parseLong(scld.getStatus7());
                totalQuanlity += Long.parseLong(scld.getStatus8());
                totalQuanlity += Long.parseLong(scld.getStatus9());
                totalQuanlity += Long.parseLong(scld.getStatus10());
                totalQuanlity += Long.parseLong(scld.getStatus11());
                totalQuanlity += Long.parseLong(scld.getStatus12());
                totalQuanlity += Long.parseLong(scld.getStatus13());
                totalQuanlity += Long.parseLong(scld.getStatus14());
                return totalQuanlity;
            }
        });
    }

    private void buildFooter() {
        tblStatisticsCategoryList.setFooterVisible(true);
        // Add some total sum and description to footer
        tblStatisticsCategoryList.setColumnFooter("code",
                BundleUtils.getString("statistics.categoryList.totalQuanlity"));
    }

    private void addColumnContactedQuanlity() {
        tblStatisticsCategoryList.addGeneratedColumn("contactedQuanlity", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                Long totalQuanlity = 0L;
                StatisticsCategoryListDTO scld = (StatisticsCategoryListDTO) itemId;
//                totalQuanlity += Long.parseLong(scld.getStatus1());
                totalQuanlity += Long.parseLong(scld.getStatus2());
                totalQuanlity += Long.parseLong(scld.getStatus3());
                totalQuanlity += Long.parseLong(scld.getStatus4());
                totalQuanlity += Long.parseLong(scld.getStatus5());
                totalQuanlity += Long.parseLong(scld.getStatus6());
                totalQuanlity += Long.parseLong(scld.getStatus7());
                totalQuanlity += Long.parseLong(scld.getStatus8());
                totalQuanlity += Long.parseLong(scld.getStatus9());
                totalQuanlity += Long.parseLong(scld.getStatus10());
                totalQuanlity += Long.parseLong(scld.getStatus11());
                totalQuanlity += Long.parseLong(scld.getStatus12());
                totalQuanlity += Long.parseLong(scld.getStatus13());
                totalQuanlity += Long.parseLong(scld.getStatus14());
                return totalQuanlity;
            }
        });
    }

    private void getDatas() {
        comboBoxUtil = new ComboComponent();
        lstService = DataUtil.getListApParams(Constants.APP_PARAMS.SERVICE_TYPE);

        setValueToCboCategoryList();
        try {
            StaffDTO search = new StaffDTO();
            search.setStaffType(Constants.STAFF.STAFF_TYPE_3);
            search.setStatus(Constants.ACTIVE);
            lstStaff = WSStaff.getListStaffDTO(search, 0, Constants.INT_100, "asc", "code");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setValueToCboCategoryList() {
        try {
            cboService = searchForm.getCboService();
            List<ConditionBean> lstCondition = new ArrayList<>();
            AppParamsDTO serviceDTO = (AppParamsDTO) cboService.getValue();
            if (serviceDTO != null && !DataUtil.isStringNullOrEmpty(serviceDTO.getParCode())) {
                lstCondition.add(new ConditionBean("service", serviceDTO.getParCode(), ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
            }
            lstCondition.add(new ConditionBean("devided_Quantity", "0", ConditionBean.Operator.NAME_GREATER, ConditionBean.Type.STRING));

            lstCategoryList = WSCategoryList.getListCategoryListByCondition(lstCondition, 0, Constants.INT_100, Constants.ASC, Constants.CATEGORY_LIST.CODE);

            comboBoxUtil.setValues(searchForm.getCboMineName(), lstCategoryList, Constants.CATEGORY_LIST.CODE, Boolean.TRUE);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void initSearchForm() {
        cboService = searchForm.getCboService();
        if (!DataUtil.isListNullOrEmpty(lstService)) {
            String serviceDefault = lstService.get(0).getParCode();
            comboBoxUtil.fillDataCombo(cboService, Constants.NULL, serviceDefault, lstService, Constants.APP_PARAMS.PAR_NAME);
        }
        setValueToCboCategoryList();
        comboBoxUtil.setValues(searchForm.getCboStaff(), lstStaff, Constants.STAFF.CODE, Boolean.TRUE);
    }

    //An cbo staff neu khong phai admin
    public void setStaff(StaffDTO staff) {
        this.staff = staff;
        if (!DataUtil.isAdmin(staff)) {
            for (StaffDTO st : lstStaff) {
                if (staff.getStaffId().equalsIgnoreCase(st.getStaffId())) {
                    searchForm.getCboStaff().select(st);
                    searchForm.getCboStaff().setVisible(false);
                    break;
                }
            }
        }
    }

    private void addActionListeners() {
        searchForm.getBtnSearch().setClickShortcut(KeyCode.ENTER);
        searchForm.getBtnSearch().addClickListener(new CommonButtonClickListener() {
            String service;

            @Override
            public void execute() throws Exception {
                String categoryListId = "";
                String startTime;
                String endTime;
                String staffCode = "";
                CategoryListDTO cld = (CategoryListDTO) searchForm.getCboMineName().getValue();
                if (cld != null && !DataUtil.isStringNullOrEmpty(cld.getId())) {
                    categoryListId = cld.getId();
                }
                startTime = DataUtil.getDateNullOrZero(searchForm.getDfStartTime());
                endTime = DataUtil.getDateNullOrZero(searchForm.getDfEndTime());
                if (!DataUtil.isAdmin(staff)) {
                    staffCode = staff.getCode();
                } else {
                    StaffDTO staffDTO = (StaffDTO) searchForm.getCboStaff().getValue();
                    if (!DataUtil.isStringNullOrEmpty(staffDTO.getStaffId())) {
                        staffCode = staffDTO.getCode();
                    }
                }
                doSearch(service, staffCode, categoryListId, startTime, endTime);
            }

            @Override
            public boolean isValidated() {
                AppParamsDTO serviceDTO = (AppParamsDTO) cboService.getValue();
                if (serviceDTO != null && !DataUtil.isStringNullOrEmpty(serviceDTO.getParCode())) {
                    service = serviceDTO.getParCode();
                } else {
                    CommonMessages.showMessageRequired("term.information.service");
                    cboService.focus();
                    return false;
                }
                return true;
            }

        });

        searchForm.btnReset.addClickListener(new CommonButtonClickListener() {
            @Override
            public void execute() throws Exception {
                searchForm.refreshData();
                comboBoxUtil.setValues(searchForm.getCboMineName(), lstCategoryList, Constants.CATEGORY_LIST.CODE, Boolean.TRUE);
                comboBoxUtil.setValues(searchForm.getCboStaff(), lstStaff, Constants.STAFF.CODE, Boolean.TRUE);
            }
        });
        cboService.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                setValueToCboCategoryList();
            }
        });
    }

    private void doSearch(String service, String staffCode, String categoryListId, String startTime, String endTime) {
        lstStatisticsCategoryListDTOs
                = WSCustomerStatus.getStatisticsCategoryListByStaff(service, staffCode, categoryListId, startTime, endTime);
        setData2TableStatisticsCategoryList(lstStatisticsCategoryListDTOs);
    }

    private void setData2TableStatisticsCategoryList(List<StatisticsCategoryListDTO> lstStatisticsCategoryList) {
        tblContainer.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstStatisticsCategoryList)) {
            tblContainer.addAll(lstStatisticsCategoryList);
        }
        CommonFunctionTableFilter.refreshTable(panelStatisticsCategoryList, HEADER, tblContainer);
        setColumnFooterForTable(lstStatisticsCategoryList);
    }

    private void setColumnFooterForTable(List<StatisticsCategoryListDTO> lstStatisticsCategoryList) {
        Map<String, Integer> map = new HashMap<>();
        Integer status1Quanlity = 0;
        Integer status2Quanlity = 0;
        Integer status3Quanlity = 0;
        Integer status4Quanlity = 0;
        Integer status5Quanlity = 0;
        Integer status6Quanlity = 0;
        Integer status7Quanlity = 0;
        Integer status8Quanlity = 0;
        Integer status9Quanlity = 0;
        Integer status10Quanlity = 0;
        Integer status11Quanlity = 0;
        Integer status12Quanlity = 0;
        Integer status13Quanlity = 0;
        Integer status14Quanlity = 0;
        Integer status15Quanlity = 0;
        Integer totalDataQuanlity = 0;
        Integer contactedDataQuanlity = 0;
        if (!DataUtil.isListNullOrEmpty(lstStatisticsCategoryList)) {
            for (StatisticsCategoryListDTO s : lstStatisticsCategoryList) {
                status1Quanlity += Integer.parseInt(s.getStatus1());
                status2Quanlity += Integer.parseInt(s.getStatus2());
                status3Quanlity += Integer.parseInt(s.getStatus3());
                status4Quanlity += Integer.parseInt(s.getStatus4());
                status5Quanlity += Integer.parseInt(s.getStatus5());
                status6Quanlity += Integer.parseInt(s.getStatus6());
                status7Quanlity += Integer.parseInt(s.getStatus7());
                status8Quanlity += Integer.parseInt(s.getStatus8());
                status9Quanlity += Integer.parseInt(s.getStatus9());
                status10Quanlity += Integer.parseInt(s.getStatus10());
                status11Quanlity += Integer.parseInt(s.getStatus11());
                status12Quanlity += Integer.parseInt(s.getStatus12());
                status13Quanlity += Integer.parseInt(s.getStatus13());
                status14Quanlity += Integer.parseInt(s.getStatus14());
                status15Quanlity += Integer.parseInt(s.getStatus15());
            }
        }
        tblStatisticsCategoryList.setColumnFooter("status1", String.valueOf(status1Quanlity));
        tblStatisticsCategoryList.setColumnFooter("status2", String.valueOf(status2Quanlity));
        tblStatisticsCategoryList.setColumnFooter("status3", String.valueOf(status3Quanlity));
        tblStatisticsCategoryList.setColumnFooter("status4", String.valueOf(status4Quanlity));
        tblStatisticsCategoryList.setColumnFooter("status5", String.valueOf(status5Quanlity));
        tblStatisticsCategoryList.setColumnFooter("status6", String.valueOf(status6Quanlity));
        tblStatisticsCategoryList.setColumnFooter("status7", String.valueOf(status7Quanlity));
        tblStatisticsCategoryList.setColumnFooter("status8", String.valueOf(status8Quanlity));
        tblStatisticsCategoryList.setColumnFooter("status9", String.valueOf(status9Quanlity));
        tblStatisticsCategoryList.setColumnFooter("status10", String.valueOf(status10Quanlity));
        tblStatisticsCategoryList.setColumnFooter("status11", String.valueOf(status11Quanlity));
        tblStatisticsCategoryList.setColumnFooter("status12", String.valueOf(status12Quanlity));
        tblStatisticsCategoryList.setColumnFooter("status13", String.valueOf(status13Quanlity));
        tblStatisticsCategoryList.setColumnFooter("status14", String.valueOf(status14Quanlity));
        tblStatisticsCategoryList.setColumnFooter("status15", String.valueOf(status15Quanlity));
        totalDataQuanlity = status1Quanlity + status2Quanlity + status3Quanlity + status4Quanlity
                + status5Quanlity + status6Quanlity + status7Quanlity + status8Quanlity
                + status9Quanlity + status10Quanlity + status11Quanlity
                + status12Quanlity + status13Quanlity + status14Quanlity + status15Quanlity;
        contactedDataQuanlity = status2Quanlity + status3Quanlity + status4Quanlity
                + status5Quanlity + status6Quanlity + status7Quanlity + status8Quanlity
                + status9Quanlity + status10Quanlity + status11Quanlity
                + status12Quanlity + status13Quanlity + status14Quanlity + status15Quanlity;
        tblStatisticsCategoryList.setColumnFooter("totalQuanlity", String.valueOf(totalDataQuanlity));
        tblStatisticsCategoryList.setColumnFooter("contactedQuanlity", String.valueOf(contactedDataQuanlity));
    }
}
