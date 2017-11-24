/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.list.ui.StaffSearchPanel;
import com.cms.login.ws.WSDepartment;
import com.vaadin.ui.ComboBox;
import com.cms.component.MappingCombobox;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.DepartmentDTO;
import com.cms.dto.StaffDTO;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hungkv
 */
public class StaffSearchPanelController {

    ComboBox cboStatus;
    ComboBox cboStaffType;
    MappingCombobox cboDepartment;
    List<AppParamsDTO> listAppParams = Lists.newArrayList();
    List<AppParamsDTO> listStaffType = Lists.newArrayList();
    List<StaffDTO> listStaffDTOs = Lists.newArrayList();
    List<DepartmentDTO> listDepartmentDTOs = Lists.newArrayList();
    protected Map<String, DepartmentDTO> mapId2DepartmentDTO;
    List<DepartmentDTO> listDepartmentId = Lists.newArrayList();
    StaffSearchPanel searchStaffForm;
    public Map<String, AppParamsDTO> mapNameFromKey = new HashMap<>();
    public Map<String, AppParamsDTO> mapNameFromKeyStaffType = new HashMap<>();
    public Map<String, AppParamsDTO> mapNameFromKeyStaffStatus = new HashMap<>();
    public Map<String, DepartmentDTO> mapNameFromKeyDeptName = new HashMap<>();
    Map<String, String> mapsSearch = new HashMap<>();
    StaffDTO staffDTO;
    DepartmentDTO departmentDTO;
    String select = BundleUtils.getString("all");

    public StaffSearchPanelController(StaffSearchPanel searchStaffForm) {
        this.searchStaffForm = searchStaffForm;
        this.cboStatus = searchStaffForm.getCboStatus();
        this.cboStaffType = searchStaffForm.getCboEmpType();
        this.cboDepartment = searchStaffForm.getF9Departments();
    }

    public void init() {
        getData();
        fillDataCombo();
        initCombobox();
    }

    //Lay danh sach tham so ung dung

    private void getData() {
        listAppParams = DataUtil.getListAppParamsDTOs();
        listStaffType = DataUtil.getListApParams(listAppParams, Constants.DEPARTMENT.STAFF_TYPE);
    }

    public void fillDataCombo() {
        ComboComponent comboComponent = new ComboComponent();
        comboComponent.fillDataCombo(cboStatus, "all", Constants.ACTIVE);
        comboComponent.fillDataCombo(cboStaffType, "all", "", listStaffType, Constants.DEPARTMENT.STAFF_TYPE);
    }

    //Lay danh sach phong ban
    public void getDepartment() {
        departmentDTO = new DepartmentDTO();
        listDepartmentDTOs.clear();
        try {
            listDepartmentDTOs = WSDepartment.getListDepartmentDTO(departmentDTO, 0, Constants.INT_100, Constants.ASC, Constants.DEPARTMENT.CODE);
        } catch (Exception e) {
            listDepartmentDTOs = Lists.newArrayList();
        }
        if (listDepartmentDTOs == null) {
            listDepartmentDTOs = Lists.newArrayList();
        } else {
            mapId2DepartmentDTO = new HashMap<>();
            for (DepartmentDTO dTO : listDepartmentDTOs) {
                mapId2DepartmentDTO.put(dTO.getDeptId(), dTO);
            }
        }
    }

    //khoi tao combobox phong ban
    public void initCombobox() {
        getDepartment();
        cboDepartment.setValues(listDepartmentDTOs, Constants.DEPARTMENT.CODE, Constants.DEPARTMENT.NAME);
    }

    //Truyen du lieu cho combobox phong ban

    public void fillData2StaffForm(Object object) {
        fillDataCombo();
        DepartmentDTO ddto = (DepartmentDTO) object;
        if (!DataUtil.isListNullOrEmpty(listDepartmentDTOs) && mapId2DepartmentDTO.get(ddto.getDeptId()) != null) {
            searchStaffForm.getF9Departments().getNameCombo().setValue(mapId2DepartmentDTO.get(ddto.getDeptId()));
        }
    }

    //Lay thong tin tim kiem tu form
    public void getValueSearchStaff() {
        String empCode;
        String empName;
        String empType = "";
        String empDept = "";
        String empStatus = "";
        empCode = DataUtil.getStringNullOrZero(searchStaffForm.getTxtEmpCode().getValue());
        empName = DataUtil.getStringNullOrZero(searchStaffForm.getTxtEmpName().getValue());

        if (searchStaffForm.getCboStatus().getValue() != null) {
            AppParamsDTO appParamsStatus = (AppParamsDTO) searchStaffForm.getCboStatus().getValue();
            empStatus = DataUtil.getStringNullOrZero(appParamsStatus.getParCode());
        }
        if (searchStaffForm.getCboEmpType().getValue() != null) {
            AppParamsDTO appParamsEmpType = (AppParamsDTO) searchStaffForm.getCboEmpType().getValue();
            empType = DataUtil.getStringNullOrZero(appParamsEmpType.getParCode());
        }
        if (searchStaffForm.getF9Departments().codeCombo.getValue() != null && searchStaffForm.getF9Departments().nameCombo.getValue() != null) {
            departmentDTO = (DepartmentDTO) cboDepartment.getNameCombo().getValue();
            empDept = DataUtil.getStringNullOrZero(departmentDTO.getDeptId());
        }
        mapsSearch.put(Constants.STAFF.CODE, empCode);
        mapsSearch.put(Constants.STAFF.NAME, empName);
        mapsSearch.put(Constants.STAFF.STATUS, empStatus);
        mapsSearch.put(Constants.STAFF.STAFF_TYPE, empType);
        if (empDept != null) {
            mapsSearch.put(Constants.DEPARTMENT.DEPT_ID, empDept);
        }
    }

    public ComboBox getCboStatus() {
        return cboStatus;
    }

    public void setCboStatus(ComboBox cboStatus) {
        this.cboStatus = cboStatus;
    }

    public ComboBox getCboStaffType() {
        return cboStaffType;
    }

    public void setCboStaffType(ComboBox cboStaffType) {
        this.cboStaffType = cboStaffType;
    }

    public MappingCombobox getCboDepartment() {
        return cboDepartment;
    }

    public void setCboDepartment(MappingCombobox cboDepartment) {
        this.cboDepartment = cboDepartment;
    }

    public List<AppParamsDTO> getListStaffType() {
        return listStaffType;
    }

    public void setListStaffType(List<AppParamsDTO> listStaffType) {
        this.listStaffType = listStaffType;
    }

    public List<StaffDTO> getListStaffDTOs() {
        return listStaffDTOs;
    }

    public void setListStaffDTOs(List<StaffDTO> listStaffDTOs) {
        this.listStaffDTOs = listStaffDTOs;
    }

    public List<DepartmentDTO> getListDepartmentDTOs() {
        return listDepartmentDTOs;
    }

    public void setListDepartmentDTOs(List<DepartmentDTO> listDepartmentDTOs) {
        this.listDepartmentDTOs = listDepartmentDTOs;
    }

    public StaffSearchPanel getSearchStaffForm() {
        return searchStaffForm;
    }

    public void setSearchStaffForm(StaffSearchPanel searchStaffForm) {
        this.searchStaffForm = searchStaffForm;
    }

    public Map<String, AppParamsDTO> getMapNameFromKey() {
        return mapNameFromKey;
    }

    public void setMapNameFromKey(Map<String, AppParamsDTO> mapNameFromKey) {
        this.mapNameFromKey = mapNameFromKey;
    }

    public Map<String, String> getMapsSearch() {
        return mapsSearch;
    }

    public void setMapsSearch(Map<String, String> mapsSearch) {
        this.mapsSearch = mapsSearch;
    }

    public StaffDTO getStaffDTO() {
        return staffDTO;
    }

    public void setStaffDTO(StaffDTO staffDTO) {
        this.staffDTO = staffDTO;
    }

    public DepartmentDTO getDepartmentDTO() {
        return departmentDTO;
    }

    public void setDepartmentDTO(DepartmentDTO departmentDTO) {
        this.departmentDTO = departmentDTO;
    }

    public List<DepartmentDTO> getListDepartmentId() {
        return listDepartmentId;
    }

    public void setListDepartmentId(List<DepartmentDTO> listDepartmentId) {
        this.listDepartmentId = listDepartmentId;
    }

    public Map<String, AppParamsDTO> getMapNameFromKeyStaffType() {
        return mapNameFromKeyStaffType;
    }

    public void setMapNameFromKeyStaffType(Map<String, AppParamsDTO> mapNameFromKeyStaffType) {
        this.mapNameFromKeyStaffType = mapNameFromKeyStaffType;
    }

    public Map<String, AppParamsDTO> getMapNameFromKeyStaffStatus() {
        return mapNameFromKeyStaffStatus;
    }

    public void setMapNameFromKeyStaffStatus(Map<String, AppParamsDTO> mapNameFromKeyStaffStatus) {
        this.mapNameFromKeyStaffStatus = mapNameFromKeyStaffStatus;
    }

    public Map<String, DepartmentDTO> getMapNameFromKeyDeptName() {
        return mapNameFromKeyDeptName;
    }

    public void setMapNameFromKeyDeptName(Map<String, DepartmentDTO> mapNameFromKeyDeptName) {
        this.mapNameFromKeyDeptName = mapNameFromKeyDeptName;
    }

    public void setNotRequired() {
        searchStaffForm.getTxtEmpCode().setRequired(false);
        searchStaffForm.getTxtEmpName().setRequired(false);
        searchStaffForm.getF9Departments().nameCombo.setRequired(false);
        searchStaffForm.getCboStatus().setRequired(false);
    }

    public void setRequired() {
        searchStaffForm.getTxtEmpCode().setRequired(true);
        searchStaffForm.getTxtEmpName().setRequired(true);
        searchStaffForm.getF9Departments().nameCombo.setRequired(true);
        searchStaffForm.getCboStatus().setRequired(true);
    }

    public boolean isNullValue() {
        if (DataUtil.isStringNullOrEmpty(searchStaffForm.getTxtEmpCode().getValue().trim())) {
            return true;
        }
        if (DataUtil.isStringNullOrEmpty(searchStaffForm.getTxtEmpName().getValue().trim())) {
            return true;
        }
        if (DataUtil.isStringNullOrEmpty(searchStaffForm.getF9Departments().codeCombo.getValue())) {
            return true;
        }
        if (DataUtil.isStringNullOrEmpty(searchStaffForm.getCboStatus().getValue())) {
            return true;
        }

        return false;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

}
