/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.list.ui.DepartmentSearchPanel;
import com.cms.login.ws.WSDepartment;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.DepartmentDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.google.common.collect.Lists;
import com.vwf5.base.utils.ConditionBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hungkv
 */
public class ListDepartmentController {

    //show data in table
    CustomPageTableFilter<IndexedContainer> tblDepartments;
    CommonTableFilterPanel deptTablePanel;
    DepartmentDTO departmentDTO = new DepartmentDTO();
    List<DepartmentDTO> lstDepartmentDTOs = Lists.newArrayList();
    LinkedHashMap<String, CustomTable.Align> headerData = BundleUtils.getHeadersFilter("departments.header");
    private BeanItemContainer<DepartmentDTO> itemContainer;
    private final String captionfieldsetListDepartment = BundleUtils.getString("caption.dept.staff.listDeptInfo");
    DepartmentSearchPanel searchDepartmentForm;
    DepartmentSearchPanelController searchFormDeptController;
    //table with toolbar
    Button btnDel;
    private final Map<String, String> mapId2NameDepartments;

    public ListDepartmentController(CommonTableFilterPanel deptTablePanel) {
        CommonUtils.setVisibleBtnTablePanel(deptTablePanel, true, false, true, true);
        this.deptTablePanel = deptTablePanel;
        this.searchDepartmentForm = new DepartmentSearchPanel();
        this.searchFormDeptController = new DepartmentSearchPanelController(searchDepartmentForm);
        btnDel = deptTablePanel.getDelContraintButton();
        deptTablePanel.getDeleteButton().setVisible(false);
        btnDel.setVisible(false);
        mapId2NameDepartments = new HashMap<>();
        initTable();
    }

    private void initTable() {
        tblDepartments = deptTablePanel.getMainTable();
        itemContainer = new BeanItemContainer<>(DepartmentDTO.class);
//        tblDepartments.setColumnWidth(Constants.DEPARTMENT.STATUS, 100);
//        tblDepartments.setColumnWidth(Constants.DEPARTMENT.CODE, 100);
//        tblDepartments.setColumnWidth(Constants.DEPARTMENT.NAME, 300);
//        tblDepartments.setColumnWidth(Constants.DEPARTMENT.PARENT_DEPT_NAME, 300);
//        tblDepartments.setColumnWidth(Constants.DEPARTMENT.ADDRESS, 300);
//        tblDepartments.setColumnWidth(Constants.DEPARTMENT.EMAIL, 200);
//        tblDepartments.setColumnWidth(Constants.DEPARTMENT.TEL_NUMBER, 100);
//        tblDepartments.setColumnWidth(Constants.DEPARTMENT.DESCRIPTION, 300);
//        tblDepartments.setColumnWidth(Constants.DEPARTMENT.CREATE_DATE, 100);
//        tblDepartments.setColumnWidth(Constants.DEPARTMENT.CONTACT_NAME, 300);
        CommonFunctionTableFilter.initTable(deptTablePanel, headerData, itemContainer,
                captionfieldsetListDepartment, 10, "lb.header.department");
        CommonUtils.convertFieldAppParamTable(tblDepartments, Constants.DEPARTMENT.STATUS, Constants.APP_PARAMS.COMMON_STATUS);
    }

    public void init() {
        //lay ds phong
        lstDepartmentDTOs = getListDepartmentDTO(departmentDTO);
        setDataTable(lstDepartmentDTOs);
    }

    //set parentName
    private void getMapId2NameDepartments(List<DepartmentDTO> lstDepartmentDTOs) {
        StringBuilder lstIds = new StringBuilder();
        List<DepartmentDTO> lstDTOs = new ArrayList<>();
        for (DepartmentDTO dTO : lstDepartmentDTOs) {
            if (mapId2NameDepartments.get(dTO.getParentDeptId()) == null) {
                mapId2NameDepartments.put(dTO.getDeptId(), dTO.getName());
                lstIds.append(",").append(dTO.getParentDeptId());
            } else {
                dTO.setParentDeptName(mapId2NameDepartments.get(dTO.getParentDeptId()));
            }
        }
        if (!lstIds.toString().isEmpty()) {
            try {
                List<ConditionBean> lstConditionBeans = new ArrayList<>();
                ConditionBean conditionBean = new ConditionBean(Constants.DEPARTMENT.DEPT_ID, lstIds.substring(1), ConditionBean.Operator.NAME_IN, ConditionBean.Type.NUMBER);
                lstConditionBeans.add(conditionBean);
                lstDTOs = WSDepartment.getListDepartmentByCondition(lstConditionBeans, Constants.INT_0, Constants.INT_100, Constants.ASC, Constants.DEPARTMENT.CODE);
            } catch (Exception e) {
            }
            if (!DataUtil.isListNullOrEmpty(lstDTOs)) {
                for (DepartmentDTO ddto : lstDTOs) {
                    mapId2NameDepartments.put(ddto.getDeptId(), ddto.getName());
                }
            }
            for (DepartmentDTO dTO : lstDepartmentDTOs) {
                if (mapId2NameDepartments.get(dTO.getParentDeptId()) != null) {
                    dTO.setParentDeptName(mapId2NameDepartments.get(dTO.getParentDeptId()));
                }
            }
        }
    }

    //get list
    public List<DepartmentDTO> getListDepartmentDTO(DepartmentDTO departmentDTO) {
        try {
            lstDepartmentDTOs = WSDepartment.getListDepartmentDTO(departmentDTO, 0, Constants.INT_100, Constants.ASC, "code");
        } catch (Exception e) {
            lstDepartmentDTOs = new ArrayList<>();
        }
        if (!DataUtil.isListNullOrEmpty(lstDepartmentDTOs)) {
//            getMapId2NameDepartments(lstDepartmentDTOs);
        }
        return lstDepartmentDTOs;
    }

    //set data
    public void setDataTable(List<DepartmentDTO> lstDepartment) {
        //fill data vao bang
        itemContainer.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstDepartment)) {
            itemContainer.addAll(lstDepartment);
        }
        CommonFunctionTableFilter.refreshTable(deptTablePanel, headerData, itemContainer);
    }

    public boolean checkDuplicate(String code) {
        //code = searchPriceFactorsForm.getTxtFactorCode().getValue().toString();
        getListDepartmentDTO(departmentDTO);
        if (code != null) {
            for (DepartmentDTO deptCode : lstDepartmentDTOs) {
                if (code.equalsIgnoreCase(deptCode.getCode().toLowerCase())) {
                    return false;
                } else {
                    return true;
                }
            }
        } else {

        }
        return true;
    }

    public CustomPageTableFilter<IndexedContainer> getTblDepartments() {
        return tblDepartments;
    }

    public void setTblDepartments(CustomPageTableFilter<IndexedContainer> tblDepartments) {
        this.tblDepartments = tblDepartments;
    }

    public CommonTableFilterPanel getDeptTablePanel() {
        return deptTablePanel;
    }

    public void setDeptTablePanel(CommonTableFilterPanel deptTablePanel) {
        this.deptTablePanel = deptTablePanel;
    }

    public DepartmentDTO getDepartmentDTO() {
        return departmentDTO;
    }

    public void setDepartmentDTO(DepartmentDTO departmentDTO) {
        this.departmentDTO = departmentDTO;
    }

    public Button getBtnDel() {
        return btnDel;
    }

    public void setBtnDel(Button btnDel) {
        this.btnDel = btnDel;
    }

}
