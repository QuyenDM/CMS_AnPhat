/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.controller;

import com.anphat.customer.ui.CustomerDetailForm;
import com.anphat.customer.ui.CustomerDetailToCreateContractDialog;
import com.cms.component.CommonValueChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CustomerContactDTO;
import com.cms.dto.CustomerDTO;
import com.cms.dto.StaffDTO;
import com.cms.ui.CommonButtonClickListener;
import com.cms.utils.CommonUtils;
import com.cms.utils.DataUtil;
import com.cms.utils.ShortcutUtils;
import com.cms.view.CustomerManagement;
import com.vaadin.ui.UI;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 *
 * @author quyen
 */
public class CustomerManagementController {

    private final CustomerManagement view;
    private CustomerDetailForm customerDetailForm;
//    private CustomerContactForm customerContactForm;
    private CustomerContactController customerContactController;
    private ListCustomerController lstCustController;
    private SearchCustomerController searchCustomerController;
    private List<AppParamsDTO> lstAppParamsAll;
    private Button btnSearch;
    private CustomerDTO searchDTO;
    private List<ConditionBean> lstConditionBeanCust;
    private CustomerDTO selectedDTO;
    private StaffDTO staff;

    public CustomerManagementController(CustomerManagement view) {
        this.view = view;
        getDatas();
    }

    private void getDatas() {
        lstAppParamsAll = DataUtil.getListAppParamsDTOs();
        staff = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");
    }

    //Khoi tao controller
    public void initController() {

        searchCustomerController = new SearchCustomerController(view.getSearchCustomerForm(), lstAppParamsAll);
        searchCustomerController.setStaff(staff);
        lstCustController = new ListCustomerController(view.getTblPanelCustomers(), 25);
        customerDetailForm = view.getCustomerDetailForm();
//        customerContactForm = customerDetailForm.getCustomerContactForm();
        customerContactController = new CustomerContactController(customerDetailForm, view.getTblPanelCustomers().getMainTable());
        addListenerAllButton();
    }

    //Them su kien cho cac nut
    public void addListenerAllButton() {
        actionListenerBtnSearch();
        actionListenerValueChanged();
        actionListenerBtnCreateDoc();
    }

    //Nut Tim kiem khach hang
    private void actionListenerBtnSearch() {
        btnSearch = view.getSearchCustomerForm().getBtnSearch();
        ShortcutUtils.setShortcutKey(btnSearch);
        btnSearch.addClickListener(new CommonButtonClickListener() {
            @Override
            public void execute() {
                searchDTO = searchCustomerController.getDTO2Search();
                lstCustController.doSearchCustomerDTO(searchDTO, searchCustomerController.getMaxSearch());
            }
        });
    }

    //Click vao 1 ban ghi trong khach hang
    private void actionListenerValueChanged() {
        lstCustController.doValueChangedListener(new CommonValueChangeListener() {
            @Override
            public void execute() {
                selectedDTO = (CustomerDTO) selected;
                if (selectedDTO == null) {
                    selectedDTO = new CustomerDTO();
                }
                customerContactController.setData2DetailPanel(selectedDTO);
            }
        });
    }

    private void actionListenerBtnCreateDoc() {
        customerDetailForm.getBtnCreateContractDoc().addClickListener(new CommonButtonClickListener() {
            @Override
            public void execute() {
                List<CustomerContactDTO> lstCustomerContactDTO = customerContactController.getLstCustContact();
                CustomerDetailToCreateContractDialog contractDialog = new CustomerDetailToCreateContractDialog();
                contractDialog.initDialog(selectedDTO, lstCustomerContactDTO);
                addBtnCreateDocListener(contractDialog);
                UI.getCurrent().addWindow(contractDialog);
            }
        });
    }

    public void addBtnCreateDocListener(final CustomerDetailToCreateContractDialog contractDialog) {
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
