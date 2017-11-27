/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.cms.dto.AppParamsDTO;
import com.cms.dto.CategoryListDTO;
import com.cms.dto.CustomerStatusDTO;
import com.cms.login.ws.WSCategoryList;
import com.cms.login.ws.WSCustomerStatus;
import com.cms.ui.CommonButtonClickListener;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.view.ManageCustomerStatusView;
import com.vaadin.ui.UI;
import com.vwf5.base.utils.ConditionBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author Administrator
 */
public class ManageCustomerStatusController {

    private final ManageCustomerStatusView customerStatusView;
    private List<CategoryListDTO> lstCategoryList;

    public ManageCustomerStatusController(ManageCustomerStatusView customerStatusView) {
        this.customerStatusView = customerStatusView;
    }

    public void initialScreen() {
        addBtnSeachClickListener();
        addBtnDeleteClickListener();
        addValueChangeListener();
    }

    private void addBtnSeachClickListener() {
        customerStatusView.getBtnSearch().addClickListener(new CommonButtonClickListener() {
            @Override
            public void execute() throws Exception {
                List<ConditionBean> condition = customerStatusView.getSearchCondition();
                List<CustomerStatusDTO> searchResult
                        = WSCustomerStatus.getListCustomerByCondition(condition, 0, Integer.MAX_VALUE, Constants.ASC, "staffCode");
                if (DataUtil.isListNullOrEmpty(searchResult)) {
                    CommonUtils.showNotFountData();
                }
                customerStatusView.setData2TableCustomerStatus(searchResult);
            }

            @Override
            public boolean isValidated() {
                return customerStatusView.isValidSearchCondition();
            }
        });
    }

    private void addBtnDeleteClickListener() {
        customerStatusView.getBtnDelete().addClickListener(new CommonButtonClickListener() {
            @Override
            public void execute() throws Exception {
                ConfirmDialog.show(UI.getCurrent(), BundleUtils.getString("delete.item.title"), BundleUtils.getString("delete.item.body"),
                        "Đồng ý", "Huỷ bỏ", new ConfirmDialog.Listener() {
                    @Override
                    public void onClose(ConfirmDialog dialog) {
                        if (dialog.isConfirmed()) {
                            Set<CustomerStatusDTO> selected = customerStatusView.getSelectedCustomerStatus();
                            Collection dataNoDelete = customerStatusView.getAllDataInTableCustomerStatus();
                            
                            List<CustomerStatusDTO> lstSelected = new ArrayList<>();
                            lstSelected.addAll(selected);
                            String deleteResult = WSCustomerStatus.deleteLstCustomerStatus(lstSelected);
                            if (Constants.SUCCESS.equals(deleteResult)) {
                                CommonUtils.showDeleteSuccess("Dữ liệu đã phân bổ");
                                List<CustomerStatusDTO> lstNoDeleted;
                                if (dataNoDelete.size() == selected.size()) {
                                    lstNoDeleted = null;
                                } else {
                                    lstNoDeleted = new ArrayList<>();
                                    lstNoDeleted.addAll(dataNoDelete);
                                    lstNoDeleted = lstNoDeleted.stream().filter(c -> !selected.contains(c)).collect(Collectors.toList());
                                }
                                customerStatusView.setData2TableCustomerStatus(lstNoDeleted);
                            } else {
                                CommonUtils.showDeleteFail("Dữ liệu đã phân bổ");
                            }
                        }
                    }
                });

            }

            @Override
            public boolean isValidated() {
                return customerStatusView.isValidDeleteCondition();
            }
        });
    }

    private void addValueChangeListener() {
        customerStatusView.getCbxService().addValueChangeListener(e -> {
            List<ConditionBean> lstConditions = new ArrayList<>();
            AppParamsDTO serviceDTO = (AppParamsDTO) customerStatusView.getCbxService().getValue();
            if (serviceDTO != null && !DataUtil.isStringNullOrEmpty(serviceDTO.getParCode())) {
                lstConditions.add(new ConditionBean("service", serviceDTO.getParCode(), ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));
            }
            try {
                lstCategoryList = WSCategoryList.getListCategoryListByCondition(lstConditions, 0, Constants.INT_100, Constants.ASC, Constants.CATEGORY_LIST.CODE);
            } catch (Exception ex) {
                lstCategoryList = new ArrayList<>();
            }
            customerStatusView.setData2CategoryList(lstCategoryList);
        });
    }
}
