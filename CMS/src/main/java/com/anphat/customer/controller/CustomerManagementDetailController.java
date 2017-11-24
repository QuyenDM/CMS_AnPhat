/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.controller;

import com.anphat.customer.ui.CustomerDetailToCreateContractDialog;
import com.anphat.customer.ui.CustomerManagementDetailDialog;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.CustomerContactDTO;
import com.cms.dto.CustomerDTO;
import com.cms.ui.CommonButtonClickListener;
import com.cms.utils.CommonUtils;
import com.vaadin.ui.UI;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author quyen
 */
public class CustomerManagementDetailController implements Serializable {

    private final CustomerManagementDetailDialog customerManagementDetailDialog;
    private CustomerContactController customerContactController;
    private CustomPageTableFilter tblCustomer;

    public CustomerManagementDetailController(CustomerManagementDetailDialog detailDialog, 
            CustomPageTableFilter tblCustomer) {
        this.customerManagementDetailDialog = detailDialog;
        this.tblCustomer = tblCustomer;
        initialController();
    }

    private void initialController() {
        customerContactController = new CustomerContactController(
                customerManagementDetailDialog.getCustomerDetailForm(), tblCustomer);
    }

    public void fillData(CustomerDTO custDTO) {
        customerContactController.setData2DetailPanel(custDTO);
        actionListenerBtnCreateDoc(custDTO);
    }

    private void actionListenerBtnCreateDoc(final CustomerDTO selectedDTO) {
        customerManagementDetailDialog.getCustomerDetailForm().getBtnCreateContractDoc().addClickListener(new CommonButtonClickListener() {
            @Override
            public void execute() {
                List<CustomerContactDTO> lstCustomerContactDTO = customerContactController.getLstCustContact();
                CustomerDetailToCreateContractDialog contractDialog = new CustomerDetailToCreateContractDialog();
                contractDialog.initDialog(selectedDTO, lstCustomerContactDTO);
                addBtnCreateDocListener(contractDialog, selectedDTO);
                UI.getCurrent().addWindow(contractDialog);
            }
        });
    }

    public void addBtnCreateDocListener(final CustomerDetailToCreateContractDialog contractDialog, final CustomerDTO selectedDTO) {
        contractDialog.addBtnCreateDocListener(new CommonButtonClickListener() {
            ExportContractToDocController docController;

            @Override
            public void execute() throws Exception {
                if (contractDialog.validate()) {
                    try {
                        docController = new ExportContractToDocController(contractDialog.getValueInputed());
                        docController.generateFile(selectedDTO);
                    } catch (Exception e) {
                        CommonUtils.showErrorMessage("err.cannot.read.template");
                    }
                }
            }
        });
    }
}
