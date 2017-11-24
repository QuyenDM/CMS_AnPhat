/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.ui;

import com.cms.component.CommonDialog;
import com.cms.utils.BundleUtils;

/**
 *
 * @author quyen
 */
public class CustomerManagementDetailDialog extends CommonDialog {
    private CustomerDetailForm customerDetailForm;

    public CustomerManagementDetailDialog() {
        initialDialog();
    }

    private void initialDialog() {
        setInfo("95%", "-1", BundleUtils.getString("customer.dialog.caption"));
        customerDetailForm = new CustomerDetailForm();
        mainLayout.addComponent(customerDetailForm);
    }

    public CustomerDetailForm getCustomerDetailForm() {
        return customerDetailForm;
    }

    public void setCustomerDetailForm(CustomerDetailForm customerDetailForm) {
        this.customerDetailForm = customerDetailForm;
    }

    
    
    
}
