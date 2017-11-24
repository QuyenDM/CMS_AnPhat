/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.controller;

import com.anphat.customer.ui.CustomerDetailToCreateContractDialog;
import com.cms.dto.CategoryListDTO;
import com.cms.dto.CustomerDTO;
import com.cms.dto.TermInformationDTO;
import com.cms.login.ws.WSCategoryList;
import com.cms.login.ws.WSTermInformation;
import com.cms.ui.CommonButtonClickListener;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.view.SearchCustomerFromTaxCode;
import com.vaadin.ui.UI;
import com.vwf5.base.utils.ConditionBean;
import com.vwf5.base.utils.DataUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class SearchCustomerFromTaxCodeController implements Serializable {

    private ListCustomerControllerForOnePage lstCustController;
    private final SearchCustomerFromTaxCode form;
    private Map<String, String> mapMineName;
    private CustomerDTO custSelected;
    private TermInformationDTO termInfoSelected;

    public SearchCustomerFromTaxCodeController(SearchCustomerFromTaxCode form) {
        this.form = form;
        getMapMinename();
    }

    public void buildController() {
        lstCustController = new ListCustomerControllerForOnePage(form.getTblPanelCustomers());
        // Add btn click listener
        addListener();
    }

    public void addListener() {
        form.addBtnSearchListener(new CommonButtonClickListener() {
            String taxCodeInputted;

            @Override
            public void execute() throws Exception {
                List<ConditionBean> lstConditionBeans = new ArrayList<>();
                lstConditionBeans.add(new ConditionBean("taxCode", taxCodeInputted,
                        ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
                lstCustController.doSearchCustomerSimple(lstConditionBeans);
            }

            @Override
            public boolean isValidated() {
                taxCodeInputted = form.getTaxCodeInputted();
                if (DataUtil.isStringNullOrEmpty(taxCodeInputted)) {
                    CommonUtils.showMessageRequired("customer.taxCode");
                    form.setTaxCodeFocus();
                    return false;
                }
                taxCodeInputted = taxCodeInputted.trim();
                return true;
            }
        });

        form.addTableCustomerValueChangeListener(event -> {
            custSelected = (CustomerDTO) event.getProperty().getValue();
            List<TermInformationDTO> lstTermInfos = null;
            if (custSelected != null) {
                lstTermInfos = getListTermInformation(custSelected);
                form.setButtonCreateDocEnabled(true);
            } else { 
                form.setButtonCreateDocEnabled(false);
            }
            form.setDataToTblTermInformation(lstTermInfos);
        });

        form.addBtnCreateDocListener(new CommonButtonClickListener() {
            @Override
            public void execute() {
                if (custSelected != null) {
                    CustomerDetailToCreateContractDialog contractDialog = new CustomerDetailToCreateContractDialog();
                    contractDialog.initDialog(custSelected, null);   
                    termInfoSelected = form.getSelectedTermInfo();
                    contractDialog.setContactInformation(termInfoSelected);
                    contractDialog.addBtnCreateDocListener(custSelected);
                    UI.getCurrent().addWindow(contractDialog);
                }
            }
        });
    }

    private List<TermInformationDTO> getListTermInformation(CustomerDTO cust) {
        List<TermInformationDTO> lstTermInfos = null;
        TermInformationDTO searchDTO = new TermInformationDTO(cust.getTaxCode());
        try {
            lstTermInfos = WSTermInformation.getListTermInformationDTO(searchDTO, 0, Integer.MAX_VALUE, "asc", "mineName");
            if (!com.cms.utils.DataUtil.isListNullOrEmpty(lstTermInfos)) {
                lstTermInfos.forEach(k -> k.setMineName(mapMineName.get(k.getMineName())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTermInfos;
    }

    private Map<String, String> getMapMinename() {
        mapMineName = new HashMap<>();
        List<CategoryListDTO> lstMineName = WSCategoryList.getListCategoryListDTO(new CategoryListDTO(), 0, Constants.INT_100, Constants.ASC, "id");
        if (!com.cms.utils.DataUtil.isListNullOrEmpty(lstMineName)) {
            try {
                lstMineName.forEach(k -> mapMineName.put(k.getId(), k.getCode()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapMineName;
    }
}
