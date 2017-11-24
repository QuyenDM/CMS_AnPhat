/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.controller;

import com.anphat.customer.ui.CustomerDetailToCreateContractDialog;
import com.anphat.customer.ui.SearchCustomerForm;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CustomerDTO;
import com.cms.dto.StaffDTO;
import com.cms.ui.CommonButtonClickListener;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.CommonUtils;
import com.cms.utils.DataUtil;
import com.cms.utils.ShortcutUtils;
import com.cms.view.CustomerManagementOnePage;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 *
 * @author quyen
 */
public class CustomerManagementOnePageController {

    private CustomerManagementOnePage view;
    private final SearchCustomerForm searchCustomerForm;
    private final CommonTableFilterPanel tblPanelCustomers;
//    private CustomerContactController customerContactController;
    private ListCustomerControllerForOnePage lstCustController;
    private SearchCustomerController searchCustomerController;
    private List<AppParamsDTO> lstAppParamsAll;
    private Button btnSearch;
    private CustomerDTO searchDTO;
    private List<ConditionBean> lstConditionBeanCust;
    private CustomerDTO selectedDTO;
    private StaffDTO staff;

    public CustomerManagementOnePageController(CustomerManagementOnePage view) {
        this.view = view;
        this.searchCustomerForm = view.getSearchCustomerForm();
        this.tblPanelCustomers = view.getTblPanelCustomers();
        getDatas();
    }

    public CustomerManagementOnePageController(SearchCustomerForm searchCustomerForm,
            CommonTableFilterPanel tblPanelCustomers) {
        this.searchCustomerForm = searchCustomerForm;
        this.tblPanelCustomers = tblPanelCustomers;
        getDatas();
    }

    private void getDatas() {
        lstAppParamsAll = DataUtil.getListAppParamsDTOs();
        staff = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");
    }

    //Khoi tao controller
    public void initController() {

        searchCustomerController = new SearchCustomerController(searchCustomerForm, lstAppParamsAll);
        searchCustomerController.setStaff(staff);
        lstCustController = new ListCustomerControllerForOnePage(tblPanelCustomers, -1);
//        customerDetailForm = view.getCustomerDetailForm();
//        customerContactForm = customerDetailForm.getCustomerContactForm();
//        customerContactController = new CustomerContactController(customerDetailForm);
        addListenerAllButton();
    }

    //Them su kien cho cac nut
    public void addListenerAllButton() {
        actionListenerBtnSearch();
//        actionListenerItemClick();
        actionListenerBtnCreateDoc();
//        actionListenerValueChanged();
//        actionListenerBtnCreateDoc();
    }

    //Nut Tim kiem khach hang
    private void actionListenerBtnSearch() {
        btnSearch = searchCustomerForm.getBtnSearch();
        ShortcutUtils.setShortcutKey(btnSearch);
        btnSearch.addClickListener(new CommonButtonClickListener() {
            @Override
            public void execute() {
                doSearch();
            }
        });
    }

    public void doSearch() {
        searchDTO = searchCustomerController.getDTO2Search();
        lstCustController.doSearchCustomerDTO(searchDTO, searchCustomerController.getMaxSearch());
    }
    public void doSearch(String status) {
        searchCustomerController.setValueStatusDefault(status);
        doSearch();
    }

    public void clickSearchWithStatus(String status) {
        searchCustomerController.setValueStatusDefault(status);
        doSearch();
    }

    private void actionListenerBtnCreateDoc() {
//        customerDetailForm.getBtnCreateContractDoc().addClickListener(new CommonButtonClickListener() {
//            @Override
//            public void execute() {
//                List<CustomerContactDTO> lstCustomerContactDTO = customerContactController.getLstCustContact();
//                CustomerDetailToCreateContractDialog contractDialog = new CustomerDetailToCreateContractDialog();
//                contractDialog.initDialog(selectedDTO, lstCustomerContactDTO);
//                addBtnCreateDocListener(contractDialog);
//                UI.getCurrent().addWindow(contractDialog);
//            }
//        });
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
