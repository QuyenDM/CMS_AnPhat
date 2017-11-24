/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.ui;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.cms.component.CommonDialog;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.GridOneButton;
import com.cms.dto.CustomerStatusDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.vfw5.base.utils.DataUtil;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author quyen
 */
public class CustomerDialog extends CommonDialog {

    private CommonTableFilterPanel panelCustomer;
    private String caption = BundleUtils.getString("customer.dialog.map.table.caption");
    private LinkedHashMap<String, CustomTable.Align> header = BundleUtils.getHeadersFilter("customer.status.header");
    private BeanItemContainer container;
    private Map<String, String> mapServices;
    private Map<String, String> mapCustomerServiceStatus;
    private Map<String, String> mapTaxAuthority;
    private String lang = "customerStatusForm";
    
    public CustomerDialog(Map<String, String> mapServices, Map<String, String> mapCustomerServiceStatus) {
        super.setInfo("95%", "-1px", BundleUtils.getString("customer.dialog.caption"));
        this.mapServices = mapServices;
        this.mapCustomerServiceStatus = mapCustomerServiceStatus;
        buildMainLayout();
        buildTablePanel();
    }
    
    public CustomerDialog(LinkedHashMap<String, CustomTable.Align> header, String caption, String lang) {
        this.caption = caption;
        this.header = header;
        this.lang = lang;
        super.setInfo("95%", "-1px", caption);        
        buildMainLayout();
    }
    public void initDialog(Map<String, String> mapServices, Map<String, String> mapCustomerServiceStatus, Class classDTO){        
        this.mapServices = mapServices;
        this.mapCustomerServiceStatus = mapCustomerServiceStatus;
        buildTablePanel(classDTO);
    }

    private void buildMainLayout() {
        panelCustomer = new CommonTableFilterPanel();
        panelCustomer.getToolbar().setVisible(false);
        mainLayout.addComponent(panelCustomer);
        GridOneButton btnCancel = new GridOneButton(Constants.BUTTON_CANCEL);
        btnCancel.getBtnCommon().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                closeDialog();
            }
        });
        mainLayout.addComponent(btnCancel);
    }

    private void closeDialog() {
        this.close();
    }

    private void buildTablePanel() {
        container = new BeanItemContainer<>(CustomerStatusDTO.class);
        CommonFunctionTableFilter.initTable(panelCustomer, header, container, caption, 15, lang );
        CommonUtils.convertFieldAppParamTable(panelCustomer.getMainTable(),
                "service", Constants.APP_PARAMS.SERVICE_TYPE, mapServices);
        CommonUtils.convertFieldAppParamTable(panelCustomer.getMainTable(),
                "status", Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS, mapCustomerServiceStatus);
    }
    
    private void buildTablePanel(Class classDTO) {
        container = new BeanItemContainer<>(classDTO);
        CommonFunctionTableFilter.initTable(panelCustomer, header, container, caption, 15, lang);       
        CommonUtils.convertFieldAppParamTable(panelCustomer.getMainTable(),
                "customerStatus", Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS, mapCustomerServiceStatus);   
        CommonUtils.convertFieldAppParamTable(panelCustomer.getMainTable(),
                "service", Constants.APP_PARAMS.SERVICE_TYPE, mapServices);
    }

    /**
     * Truyen du lieu vao cho bang chi tiet
     *
     * @param lstMaps
     */
    public void setData2Table(List<CustomerStatusDTO> lstMaps) {
        container.removeAllItems();
        if(!DataUtil.isListNullOrEmpty(lstMaps)){
            container.addAll(lstMaps);
        }
        CommonFunctionTableFilter.refreshTable(panelCustomer, header, container);
    }
    
    /**
     * Truyen du lieu vao cho bang chi tiet
     *
     * @param lstCustomer
     */
    public void setCustomerList2Table(List<?> lstCustomer) {
        container.removeAllItems();
        if(!DataUtil.isListNullOrEmpty(lstCustomer)){
            container.addAll(lstCustomer);
        }
        CommonFunctionTableFilter.refreshTable(panelCustomer, header, container);
    }
}
