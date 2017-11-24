/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.controller;

import com.anphat.customer.ui.CustomerManagementDetailDialog;
import com.cms.login.ws.WSCustomer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomTable;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CategoryListDTO;
import com.cms.dto.CustomerDTO;
import com.cms.dto.TaxAuthorityDTO;
import com.cms.login.ws.WSCategoryList;
import com.cms.login.ws.WSTaxAuthority;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vwf5.base.utils.ConditionBean;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quyen
 */
public class ListCustomerControllerForOnePage implements Serializable {

    private final CommonTableFilterPanel panel;
    private CustomPageTableFilter tblCustomer;
    private BeanItemContainer tblContainer;
    private final LinkedHashMap<String, CustomTable.Align> HEADER = BundleUtils.getHeadersFilter("search.customer.onepage.header");
    private final LinkedHashMap<String, CustomTable.Align> HEADER_SIMPLE = BundleUtils.getHeadersFilter("search.customer.onepage.header.simple");
    final String CAPTION = BundleUtils.getString("tbl.caption.list.customer");
    static final String LANG = "cms.common.columnheader.customers";
    static final int SIZE = 10;
    private List<CustomerDTO> lstSearchedDTO;
    private List<AppParamsDTO> lstCustomerStatus;
    private List<AppParamsDTO> lstService;
    private List<CategoryListDTO> lstMineName;
    private List<TaxAuthorityDTO> lstTaxAuthority;
    private Map<String, String> mapCustomerStatus;
    private Map<String, String> mapMineName;
    private Map<String, String> mapTaxAuthority;

    public ListCustomerControllerForOnePage(CommonTableFilterPanel panel, int pageLength) {
        this.panel = panel;
        getDatas();
        initTable(pageLength);
    }

    public ListCustomerControllerForOnePage(CommonTableFilterPanel panel) {
        this.panel = panel;
        getDatas();
        initTableSimple(-1);
    }

    private void getDatas() {
        lstCustomerStatus = DataUtil.getListApParams(Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS);
        try {
            mapCustomerStatus = DataUtil.buildHasmap(lstCustomerStatus, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        lstService = DataUtil.getListApParams(Constants.APP_PARAMS.SERVICE_TYPE);
//        try {
//            mapService = DataUtil.buildHasmap(lstService, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        lstMineName = WSCategoryList.getListCategoryListDTO(new CategoryListDTO(), 0, Constants.INT_100, Constants.ASC, "id");
        if (!DataUtil.isListNullOrEmpty(lstMineName)) {
            try {
                mapMineName = DataUtil.buildHasmap(lstMineName, Constants.CATEGORY_LIST.ID, Constants.CATEGORY_LIST.CODE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        lstTaxAuthority = WSTaxAuthority.getListProvineTaxAuthority();
        if (!DataUtil.isListNullOrEmpty(lstTaxAuthority)) {
            try {
                mapTaxAuthority = DataUtil.buildHasmap(lstTaxAuthority, Constants.TAXAUTHORITY.MA_CQT, Constants.TAXAUTHORITY.TEN_CQT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initTableSimple(int pageLength) {
        tblCustomer = panel.getMainTable();
        tblContainer = new BeanItemContainer<>(CustomerDTO.class);
        tblCustomer.setMultiSelect(false);        
        tblCustomer.addGeneratedColumn("taxAuthorityName", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                CustomerDTO cust = (CustomerDTO) itemId;
                if (!DataUtil.isStringNullOrEmpty(cust.getTaxAuthority())) {
                    String a = mapTaxAuthority.get(cust.getTaxAuthority());
                    if (a == null) {
                        return cust.getTaxAuthority();
                    } else {
                        return mapTaxAuthority.get(cust.getTaxAuthority());
                    }
                } else {
                    return "";
                }
            }
        });
        CommonFunctionTableFilter.initTable(panel, HEADER_SIMPLE, tblContainer, CAPTION, pageLength, LANG);

        tblCustomer.setColumnExpandRatio("taxCode", 1);
        tblCustomer.setColumnExpandRatio("name", 2);
        tblCustomer.setColumnExpandRatio("taxAuthorityName", 1);
        tblCustomer.setMultiSelect(false);
    }

    private void initTable(int pageLength) {
        tblCustomer = panel.getMainTable();
        tblContainer = new BeanItemContainer<>(CustomerDTO.class);
        tblCustomer.setMultiSelect(false);
        tblCustomer.addGeneratedColumn("mineNameCode", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                CustomerDTO cust = (CustomerDTO) itemId;
                if (!DataUtil.isStringNullOrEmpty(cust.getMineName())) {
                    return mapMineName.get(cust.getMineName());
                } else {
                    return "";
                }
            }
        });
        tblCustomer.addGeneratedColumn("taxCodeLink", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                final CustomerDTO cust = (CustomerDTO) itemId;
                Button btn = new Button(cust.getTaxCode());
                btn.setStyleName("v-button-link");
//                btn.setStyleName("v-button-link-left");
                btn.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        CustomerManagementDetailDialog detailDialog = new CustomerManagementDetailDialog();
                        CustomerManagementDetailController controller
                                = new CustomerManagementDetailController(detailDialog, tblCustomer);
                        controller.fillData(cust);
                        UI.getCurrent().addWindow(detailDialog);
                    }
                });
                return btn;
            }
        });
        tblCustomer.addGeneratedColumn("taxAuthorityName", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                CustomerDTO cust = (CustomerDTO) itemId;
                if (!DataUtil.isStringNullOrEmpty(cust.getTaxAuthority())) {
                    String a = mapTaxAuthority.get(cust.getTaxAuthority());
                    if (a == null) {
                        return cust.getTaxAuthority();
                    } else {
                        return mapTaxAuthority.get(cust.getTaxAuthority());
                    }
                } else {
                    return "";
                }
            }
        });
        CommonFunctionTableFilter.initTable(panel, HEADER, tblContainer, CAPTION, pageLength, LANG);

        tblCustomer.setColumnExpandRatio("taxCode", 1);
        tblCustomer.setColumnExpandRatio("name", 2);
        tblCustomer.setColumnExpandRatio("notes", 2);
        tblCustomer.setColumnExpandRatio("createDate", 1);
        tblCustomer.setColumnExpandRatio("status", 0.75f);
        tblCustomer.setColumnExpandRatio("mineNameCode", 1.5f);
        tblCustomer.setColumnExpandRatio("taxAuthorityName", 1);
        CommonUtils.convertFieldAppParamTable(tblCustomer, "status", Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS, mapCustomerStatus);
        tblCustomer.setMultiSelect(false);
        tblCustomer.setColumnWidth(Constants.STT, 65);
    }

    private void setData2Table(List<CustomerDTO> lstDatas) {
        tblContainer.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstDatas)) {
            tblContainer.addAll(lstDatas);
        }
        CommonFunctionTableFilter.refreshTable(panel, HEADER, tblContainer);
    }
    private void setData2TableSimple(List<CustomerDTO> lstDatas) {
        tblContainer.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstDatas)) {
            tblContainer.addAll(lstDatas);
        }
        CommonFunctionTableFilter.refreshTable(panel, HEADER_SIMPLE, tblContainer);
    }

    public void doSearchCustomerDTO(CustomerDTO searchDTO, int maxResult) {
        try {
//            lstSearchedDTO = WSCustomer.getListCustomerDTO(searchDTO, Constants.INT_0, Constants.INT_100, Constants.ASC, Constants.CUSTOMER.NAME);
            lstSearchedDTO = WSCustomer.searchCustomers(searchDTO, maxResult);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ListCustomerControllerForOnePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (DataUtil.isListNullOrEmpty(lstSearchedDTO)) {
            CommonMessages.showDataNotFound();
        }
        setData2Table(lstSearchedDTO);
    }

    public void doSearchCustomerDTO(List<ConditionBean> lstConditionBeans) {
        try {
            lstSearchedDTO = WSCustomer.getListCustomerByCondition(lstConditionBeans, Constants.INT_0, Constants.INT_100, Constants.ASC, Constants.CUSTOMER.NAME);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ListCustomerControllerForOnePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (DataUtil.isListNullOrEmpty(lstSearchedDTO)) {
            CommonMessages.showDataNotFound();
        }
        setData2Table(lstSearchedDTO);
    }

    public void doSearchCustomerSimple(List<ConditionBean> lstConditionBeans) {
        try {
            lstSearchedDTO = WSCustomer.getListCustomerByCondition(lstConditionBeans, Constants.INT_0, Constants.INT_100, Constants.ASC, Constants.CUSTOMER.NAME);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ListCustomerControllerForOnePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (DataUtil.isListNullOrEmpty(lstSearchedDTO)) {
            CommonMessages.showDataNotFound();
        }
        setData2TableSimple(lstSearchedDTO);
    }
    
    public void doValueChangedListener(Property.ValueChangeListener e) {
        tblCustomer.addValueChangeListener(e);
    }

    public void doItemClickListener(ItemClickEvent.ItemClickListener e) {
        tblCustomer.addItemClickListener(e);
    }
}
