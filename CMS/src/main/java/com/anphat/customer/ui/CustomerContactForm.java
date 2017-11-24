/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.ui;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.cms.component.CommonLayout;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;

/**
 *
 * @author quyen
 */
public class CustomerContactForm extends CommonLayout {

    private TabSheet tabContact;
    private VerticalLayout layoutContact;
    private VerticalLayout layoutHistory;
    private VerticalLayout layoutCustomerStatus;
    private CommonTableFilterPanel tblContact;
    private CommonTableFilterPanel tblHistory;
    private CommonTableFilterPanel tblCustomerStatus;

    public CustomerContactForm() {
        buildTabSheet();
        buildTabHistory();
        buildTabContact();
        buildTabCustomerStatus();
    }

    private void buildTabSheet() {
        tabContact = new TabSheet();
        tabContact.setHeight(100.0f, Unit.PERCENTAGE);
        tabContact.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabContact.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        root.addComponent(tabContact);
    }

    private void buildTabContact() {
        layoutContact = new VerticalLayout();
        tblContact = new CommonTableFilterPanel();
        layoutContact.addComponent(tblContact);
        tabContact.addTab(layoutContact, "Liên hệ");
    }

    private void buildTabHistory() {
        layoutHistory = new VerticalLayout();
        tblHistory = new CommonTableFilterPanel();
        layoutHistory.addComponent(tblHistory);
        tabContact.addTab(layoutHistory, BundleUtils.getString("label.history.care.caption"));
    }

    private void buildTabCustomerStatus() {
        layoutCustomerStatus = new VerticalLayout();
        tblCustomerStatus = new CommonTableFilterPanel();
        tblCustomerStatus.getToolbar().setVisible(false);
        layoutCustomerStatus.addComponent(tblCustomerStatus);
        tabContact.addTab(layoutCustomerStatus, "Trạng thái dịch vụ");
    }

    public CommonTableFilterPanel getTblContact() {
        return tblContact;
    }

    public CommonTableFilterPanel getTblHistory() {
        return tblHistory;
    }

    public CommonTableFilterPanel getTblCustomerStatus() {
        return tblCustomerStatus;
    }

}
