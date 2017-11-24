/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.anphat.customer.controller.ImportCustomerController;
import com.anphat.customer.ui.ImportCustomerUploadForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.cms.component.CommonOnePanelUI;
import com.cms.utils.BundleUtils;

/**
 *
 * @author quyen
 */
public class ImportCustomer extends CommonOnePanelUI implements View {

    private ImportCustomerUploadForm importCustomerUploadForm;
    private ImportCustomerController importCustomerController;
    static final String PANEL_CAPTION = BundleUtils.getString("import.customer.caption");

    public ImportCustomer() {
        panelMain.setCaption(PANEL_CAPTION);
        buildPanel();
        initController();
    }

    private void buildPanel() {
        importCustomerUploadForm = new ImportCustomerUploadForm();
        mainLayout.addComponent(importCustomerUploadForm);
    }

    private void initController() {
        importCustomerController = new ImportCustomerController(this);
        importCustomerController.initController();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Getter
    public ImportCustomerUploadForm getImportCustomerUploadForm() {
        return importCustomerUploadForm;
    }

}
