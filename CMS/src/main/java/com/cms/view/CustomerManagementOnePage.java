/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.anphat.customer.controller.CustomerManagementOnePageController;
import com.anphat.customer.ui.SearchCustomerForm;
import com.cms.component.CommonOnePanelUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.cms.ui.CommonTableFilterPanel;
import com.vfw5.base.utils.DataUtil;

/**
 *
 * @author quyen
 */
public class CustomerManagementOnePage extends CommonOnePanelUI implements View {

    private SearchCustomerForm searchCustomerForm;
    private CommonTableFilterPanel tblPanelCustomers;
//    private CustomerDetailForm customerDetailForm;
//    private Button btnCreateContractDoc;
//    private Button btnCreateContract;
    private Button btnUpdateStatus;

    private final CustomerManagementOnePageController custManagementController;

    public CustomerManagementOnePage() {
//        super(BundleUtils.getString("customer.management.header.search"), BundleUtils.getString("customer.management.header.DetailsInfo"));
//        mainLayout.setSplitPosition(50, Unit.PERCENTAGE);
        buildLeftPanel();
//        buildRightPanel();
        custManagementController = new CustomerManagementOnePageController(this);
        custManagementController.initController();
    }

    private void buildLeftPanel() {
        searchCustomerForm = new SearchCustomerForm();
        mainLayout.addComponent(searchCustomerForm);
        tblPanelCustomers = new CommonTableFilterPanel();
        tblPanelCustomers.getToolbar().setVisible(false);
        mainLayout.addComponent(tblPanelCustomers);
    }
//
//    private void buildRightPanel() {
//        customerDetailForm = new CustomerDetailForm();
//        rightLayout.addComponent(customerDetailForm);
//    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (event.getParameters() != null) {
            String[] msgs = event.getParameters().split("/");
            if (!DataUtil.isStringNullOrEmpty(msgs[0])) {
                custManagementController.clickSearchWithStatus(msgs[0]);
            }
        }
    }

    public SearchCustomerForm getSearchCustomerForm() {
        return searchCustomerForm;
    }

    public CommonTableFilterPanel getTblPanelCustomers() {
        return tblPanelCustomers;
    }

//    public CustomerDetailForm getCustomerDetailForm() {
//        return customerDetailForm;
//    }
//    public CustomerContactForm getCustomerContactForm() {
//        return customerContactForm;
//    }
}
