/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.anphat.customer.controller.CustomerManagementController;
import com.anphat.customer.ui.CustomerDetailForm;
import com.anphat.customer.ui.SearchCustomerForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.ui.CommonUI;
import com.cms.utils.BundleUtils;

/**
 *
 * @author quyen
 */
public class CustomerManagement extends CommonUI implements View {

    private SearchCustomerForm searchCustomerForm;
    private CommonTableFilterPanel tblPanelCustomers;
    private CustomerDetailForm customerDetailForm;
//    private Button btnCreateContractDoc;
//    private Button btnCreateContract;
    private Button btnUpdateStatus;
      
    private final CustomerManagementController custManagementController;

    public CustomerManagement() {
        super(BundleUtils.getString("customer.management.header.search"), BundleUtils.getString("customer.management.header.DetailsInfo"));
        mainLayout.setSplitPosition(50, Unit.PERCENTAGE);
        buildLeftPanel();
        buildRightPanel();
        custManagementController = new CustomerManagementController(this);
        custManagementController.initController();
    }

    private void buildLeftPanel() {
        searchCustomerForm = new SearchCustomerForm();
        leftLayout.addComponent(searchCustomerForm);
        tblPanelCustomers = new CommonTableFilterPanel();
        tblPanelCustomers.getToolbar().setVisible(false);
        leftLayout.addComponent(tblPanelCustomers);
    }

    private void buildRightPanel() {
        customerDetailForm = new CustomerDetailForm();
        rightLayout.addComponent(customerDetailForm);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public SearchCustomerForm getSearchCustomerForm() {
        return searchCustomerForm;
    }

    public CommonTableFilterPanel getTblPanelCustomers() {
        return tblPanelCustomers;
    }

    public CustomerDetailForm getCustomerDetailForm() {
        return customerDetailForm;
    }

//    public CustomerContactForm getCustomerContactForm() {
//        return customerContactForm;
//    }

}
