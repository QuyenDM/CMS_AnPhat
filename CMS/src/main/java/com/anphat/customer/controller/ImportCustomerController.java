/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.controller;

import com.anphat.customer.ui.ImportCustomerUploadForm;
import com.cms.view.ImportCustomer;
import java.io.Serializable;

/**
 *
 * @author quyen
 */
public class ImportCustomerController implements Serializable {

    private final ImportCustomer view;
    private ImportCustomerUploadForm uploadForm;
    private ImportCustomerUploadController uploadController;

    public ImportCustomerController(ImportCustomer view) {
        this.view = view;
        getDatas();
    }

    private void getDatas() {
//        lstAppParamsAll = DataUtil.getListAppParamsDTOs();
    }

    //Khoi tao controller
    public void initController() {
        uploadForm = view.getImportCustomerUploadForm();
        uploadController = new ImportCustomerUploadController(uploadForm);
    }

    //Them su kien cho cac nut
    public void addListenerAllButton() {
        actionListenerBtnSearch();
    }

    //Nut Tim kiem khach hang
    private void actionListenerBtnSearch() {
//        btnSearch = view.getSearchCustomerForm().getBtnSearch();
//        btnSearch.addClickListener(new Button.ClickListener() {
//            @Override
//            public void buttonClick(Button.ClickEvent event) {
//                searchDTO = searchCustomerController.getDTO2Search();
//                lstCustController.doSearchCustomerDTO(searchDTO);
//                event.getButton().setEnabled(true);
//            }
//        });
    }
}
