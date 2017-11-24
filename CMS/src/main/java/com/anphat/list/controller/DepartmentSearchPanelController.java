/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.list.ui.DepartmentSearchPanel;
import com.cms.common.ws.WSAppParams;
import com.cms.login.ws.WSDepartment;
import com.vaadin.ui.ComboBox;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.DepartmentDTO;
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
public class DepartmentSearchPanelController {

    ComboBox cboStatus;
//    MappingCombobox cboDeptTopLevel;
    List<AppParamsDTO> listStatusType = Lists.newArrayList();
    List<DepartmentDTO> listDepartmentDTOs = Lists.newArrayList();
    DepartmentSearchPanel searchDepartmentForm;
    public Map<String, AppParamsDTO> mapNameFromKey = new HashMap<>();
    public Map<String, String> mapNameFromKeyDept = new HashMap<>();
    Map<String, String> mapsSearch = new HashMap<String, String>();
    DepartmentDTO departmentDTO;
    String selectAll = BundleUtils.getString("common.select.all");
    String select = BundleUtils.getString("all");
    public DepartmentSearchPanelController(DepartmentSearchPanel searchDepartmentForm) {
        this.searchDepartmentForm = searchDepartmentForm;
        this.cboStatus = searchDepartmentForm.getCboStatus();
//        this.cboDeptTopLevel = searchDepartmentForm.getDeptTopLevel();
    }

    public void init() {
        AppParamsDTO appParamsDTO = new AppParamsDTO();

        appParamsDTO.setParType(Constants.APP_PARAMS.COMMON_STATUS);
        listStatusType = getListStatusType(appParamsDTO);
        fillDataComboStatus();
        initCombobox();
    }

//    private void actionMapDepartment() {
//        cboDeptTopLevel.codeCombo.addValueChangeListener(new Property.ValueChangeListener() {
//
//            @Override
//            public void valueChange(Property.ValueChangeEvent event) {
//                departmentDTO = (DepartmentDTO) event.getProperty().getValue();
//            }
//        });
//    }
    public void fillDataComboStatus() {
        ComboComponent comboComponent = new ComboComponent();
        comboComponent.fillDataCombo(cboStatus, "all", "", listStatusType, Constants.APP_PARAMS.COMMON_STATUS);
        cboStatus.select(BundleUtils.getString("common.status.1"));
    }

    public List<AppParamsDTO> getListStatusType(AppParamsDTO appParamsDTO) {
        try {
            listStatusType = WSAppParams.getListAppParamsDTO(appParamsDTO, 0, Integer.parseInt(Constants.PAGE_SIZE_DEFAULT_20), "", "parOrder");
            //Put danh sach trang thai khach hang vao map
            for (AppParamsDTO paramsDTO : listStatusType) {
                String key = paramsDTO.getParType().replace('_', '.').toLowerCase() + "." + paramsDTO.getParCode();
                paramsDTO.setDisplayName(BundleUtils.getString(key));
                mapNameFromKey.put(paramsDTO.getParCode(), paramsDTO);
                mapNameFromKey.put(paramsDTO.getParName(), paramsDTO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listStatusType;
    }

    public void getListDeptTopLevel() {
        departmentDTO = new DepartmentDTO();
        try {
            listDepartmentDTOs = WSDepartment.getListDepartmentDTO(departmentDTO, 0, 100, Constants.ASC, Constants.DEPARTMENT.CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listDepartmentDTOs == null) {
            listDepartmentDTOs = Lists.newArrayList();
        }
    }

    public String getDeptName(String deptId) {
        if (deptId != null) {
            DepartmentDTO deptDTO = new DepartmentDTO();
            try {
                deptDTO = WSDepartment.findDepartmentById(deptId);
                if(deptDTO != null){
                return deptDTO.getName();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }

    //f9
    public void initCombobox() {
//        DepartmentDTO dTO = new DepartmentDTO();
//        dTO.setCode(select);
//        dTO.setName(select);
//        getListDeptTopLevel();
        //duyot: delete select row
//        listDepartmentDTOs.add(dTO);
//        Collections.reverse(listDepartmentDTOs);
//        cboDeptTopLevel.setValues(listDepartmentDTOs, Constants.DEPARTMENT.CODE, Constants.DEPARTMENT.NAME);
    }

    //get value search
    public void getValueSearchDepartment() {
        String deptCode = "";
        String deptName = "";
        String deptContactName = "";
        String deptDesc = "";
        String deptPhone = "";
        String deptFax = "";
        String deptEmail = "";
        String deptAddr = "";
        String deptStatus = "";
        String deptLevel = "";
        deptCode = DataUtil.getStringNullOrZero(searchDepartmentForm.getTxtDeptCode().getValue().trim());
        deptName = DataUtil.getStringNullOrZero(searchDepartmentForm.getTxtDeptName().getValue().trim());
        deptEmail = DataUtil.getStringNullOrZero(searchDepartmentForm.getTxtDeptEmail().getValue().trim());
        deptAddr = DataUtil.getStringNullOrZero(searchDepartmentForm.getTxtDeptAddr().getValue().trim());

        if (searchDepartmentForm.getCboStatus().getValue() != null) {
            AppParamsDTO appParamsStatus = (AppParamsDTO) searchDepartmentForm.getCboStatus().getValue();
            deptStatus = DataUtil.getStringNullOrZero(appParamsStatus.getParCode());
        }
//        if (searchDepartmentForm.getDeptTopLevel().codeCombo.getValue() != null && searchDepartmentForm.getDeptTopLevel().nameCombo.getValue() != null) {
//            departmentDTO = (DepartmentDTO) cboDeptTopLevel.getNameCombo().getValue();
//            deptLevel = DataUtil.getStringNullOrZero(departmentDTO.getDeptId());
//        }
        mapsSearch.put(Constants.DEPARTMENT.CODE, deptCode);
        mapsSearch.put(Constants.DEPARTMENT.NAME, deptName);
//        mapsSearch.put(Constants.DEPARTMENT.CONTACT_NAME, deptContactName);
//        mapsSearch.put(Constants.DEPARTMENT.DESCRIPTION, deptDesc);
//        mapsSearch.put(Constants.DEPARTMENT.TEL_NUMBER, deptPhone);
//        mapsSearch.put(Constants.DEPARTMENT.FAX, deptFax);
        mapsSearch.put(Constants.DEPARTMENT.EMAIL, deptEmail);
        mapsSearch.put(Constants.DEPARTMENT.ADDRESS, deptAddr);
        mapsSearch.put(Constants.DEPARTMENT.STATUS, deptStatus);
        if (deptLevel != null) {
            mapsSearch.put(Constants.DEPARTMENT.PARENT_DEPT_ID, deptLevel);
        }

    }

    //fill data vao form
    public void fillData2Popup(DepartmentDTO deptDTO) {
        //edit or copy
        if (!DataUtil.isStringNullOrEmpty(deptDTO.getCode())) {
            try {
                searchDepartmentForm.getTxtDeptCode().setValue(DataUtil.getStringNullOrZero(departmentDTO.getCode()));
                searchDepartmentForm.getTxtDeptName().setValue(DataUtil.getStringNullOrZero(departmentDTO.getName()));
                searchDepartmentForm.getTxtContactPerson().setValue(DataUtil.getStringNullOrZero(departmentDTO.getContactName()));
                searchDepartmentForm.getTxtDeptAddr().setValue(DataUtil.getStringNullOrZero(departmentDTO.getAddress()));
                searchDepartmentForm.getTxtDeptDesc().setValue(DataUtil.getStringNullOrZero(departmentDTO.getDescription()));
                searchDepartmentForm.getTxtDeptFax().setValue(DataUtil.getStringNullOrZero(departmentDTO.getFax()));
                searchDepartmentForm.getTxtDeptPhone().setValue(DataUtil.getStringNullOrZero(departmentDTO.getTelNumber()));
                searchDepartmentForm.getTxtDeptEmail().setValue(DataUtil.getStringNullOrZero(departmentDTO.getEmail()));
                //set value for combobox
                String valueStatus = mapNameFromKey.get(departmentDTO.getStatus()).getParCode();
                ComboComponent comboComponent = new ComboComponent();
                comboComponent.fillDataCombo(searchDepartmentForm.getCboStatus(), "", valueStatus);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Fill data department * NgocND modifiers *
    public void fillDataDepartment(Object object) {
        if (!DataUtil.isStringNullOrEmpty(object)) {
            String valueStatus = null;
            departmentDTO = (DepartmentDTO) object;
            searchDepartmentForm.getTxtDeptCode().setValue(DataUtil.getStringNullOrZero(departmentDTO.getCode()));
            searchDepartmentForm.getTxtDeptName().setValue(DataUtil.getStringNullOrZero(departmentDTO.getName()));
            searchDepartmentForm.getTxtDeptDesc().setValue(DataUtil.getStringNullOrZero(departmentDTO.getDescription()));
            searchDepartmentForm.getTxtDeptPhone().setValue(DataUtil.getStringNullOrZero(departmentDTO.getTel()));
            searchDepartmentForm.getTxtDeptFax().setValue(DataUtil.getStringNullOrZero(departmentDTO.getFax()));
            searchDepartmentForm.getTxtDeptEmail().setValue(DataUtil.getStringNullOrZero(departmentDTO.getEmail()));
            searchDepartmentForm.getTxtDeptAddr().setValue(DataUtil.getStringNullOrZero(departmentDTO.getAddress()));
            searchDepartmentForm.getTxtContactPerson().setValue(DataUtil.getStringNullOrZero(departmentDTO.getContactName()));
            ComboComponent comboComponent = new ComboComponent();
            if (mapNameFromKey != null) {
                valueStatus = mapNameFromKey.get(departmentDTO.getStatus()).getParCode();

            }
            if (valueStatus != null) {
                comboComponent.fillDataCombo(searchDepartmentForm.getCboStatus(), "", valueStatus);
            } else {
                comboComponent.fillDataCombo(searchDepartmentForm.getCboStatus(), "1", valueStatus);
            }
//            for (DepartmentDTO department : listDepartmentDTOs) {
//                if (departmentDTO.getParentDeptId().equalsIgnoreCase(department.getDeptId())) {
//                    searchDepartmentForm.getDeptTopLevel().getNameCombo().setValue(departmentDTO);
//                    break;
//                }
//                if (departmentDTO.getParentDeptId().equalsIgnoreCase(department.getName())) {
//                    searchDepartmentForm.getDeptTopLevel().getNameCombo().setValue(departmentDTO);
//                    break;
//                }
//            }
        }
    }

    public ComboBox getCboStatus() {
        return cboStatus;
    }

    public void setCboStatus(ComboBox cboStatus) {
        this.cboStatus = cboStatus;
    }

    public List<AppParamsDTO> getListStatusType() {
        return listStatusType;
    }

    public void setListStatusType(List<AppParamsDTO> listStatusType) {
        this.listStatusType = listStatusType;
    }

    public DepartmentSearchPanel getSearchDepartmentForm() {
        return searchDepartmentForm;
    }

    public void setSearchDepartmentForm(DepartmentSearchPanel searchDepartmentForm) {
        this.searchDepartmentForm = searchDepartmentForm;
    }

    public Map<String, AppParamsDTO> getMapNameFromKey() {
        return mapNameFromKey;
    }

    public void setMapNameFromKey(Map<String, AppParamsDTO> mapNameFromKey) {
        this.mapNameFromKey = mapNameFromKey;
    }

//    public MappingCombobox getCboDeptTopLevel() {
//        return cboDeptTopLevel;
//    }

//    public void setCboDeptTopLevel(MappingCombobox cboDeptTopLevel) {
//        this.cboDeptTopLevel = cboDeptTopLevel;
//    }

    public List<DepartmentDTO> getListDepartmentDTOs() {
        return listDepartmentDTOs;
    }

    public void setListDepartmentDTOs(List<DepartmentDTO> listDepartmentDTOs) {
        this.listDepartmentDTOs = listDepartmentDTOs;
    }

    public Map<String, String> getMapNameFromKeyDept() {
        return mapNameFromKeyDept;
    }

    public void setMapNameFromKeyDept(Map<String, String> mapNameFromKeyDept) {
        this.mapNameFromKeyDept = mapNameFromKeyDept;
    }

    public DepartmentDTO getDepartmentDTO() {
        return departmentDTO;
    }

    public void setDepartmentDTO(DepartmentDTO departmentDTO) {
        this.departmentDTO = departmentDTO;
    }

    public Map<String, String> getMapsSearch() {
        return mapsSearch;
    }

    public void setMapsSearch(Map<String, String> mapsSearch) {
        this.mapsSearch = mapsSearch;
    }

    public void setNotRequired() {
        searchDepartmentForm.getTxtDeptCode().setRequired(false);
        searchDepartmentForm.getTxtDeptName().setRequired(false);
//        searchDepartmentForm.getDeptTopLevel().nameCombo.setRequired(false);
        searchDepartmentForm.getCboStatus().setRequired(false);
        searchDepartmentForm.getTxtDeptAddr().setRequired(false);
    }

    public void setRequired() {
        searchDepartmentForm.getTxtDeptCode().setRequired(true);
        searchDepartmentForm.getTxtDeptName().setRequired(true);
//        searchDepartmentForm.getDeptTopLevel().nameCombo.setRequired(true);
        searchDepartmentForm.getCboStatus().setRequired(true);
        searchDepartmentForm.getTxtDeptAddr().setRequired(true);
    }

    public boolean isNullValue() {
        if (DataUtil.isStringNullOrEmpty(searchDepartmentForm.getTxtDeptCode().getValue().trim())) {
            return true;
        }
        if (DataUtil.isStringNullOrEmpty(searchDepartmentForm.getTxtDeptName().getValue().trim())) {
            return true;
        }
//        if (DataUtil.isStringNullOrEmpty(searchDepartmentForm.getDeptTopLevel().codeCombo.getValue())) {
//            return true;
//        }
        if (DataUtil.isStringNullOrEmpty(searchDepartmentForm.getCboStatus().getValue())) {
            return true;
        }
        if (DataUtil.isStringNullOrEmpty(searchDepartmentForm.getTxtDeptAddr().getValue().trim())) {
            return true;
        }
        return false;
    }
}
