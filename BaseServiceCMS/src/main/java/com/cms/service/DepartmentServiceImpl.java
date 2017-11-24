/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.DepartmentDTO;
import com.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;
import javax.jws.WebService;
import java.util.ArrayList;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.vfw5.base.dto.ResultDTO;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 22-Apr-15 6:02 PM
 */
@WebService(endpointInterface = "com.cms.service.DepartmentService")
//@net.anotheria.moskito.aop.annotation.Monitor
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    BaseFWServiceInterface departmentBusiness;

    @Override
    public String updateDepartment(DepartmentDTO departmentDTO) {
        return departmentBusiness.update(departmentDTO);
    }

    @Override
    public String deleteDepartment(Long id, String username) {
        return departmentBusiness.delete(id);
    }

    @Override
    public String deleteListDepartment(List<DepartmentDTO> departmentListDTO) {
        String result = departmentBusiness.delete(departmentListDTO);
        return result;
    }

    @Override
    public DepartmentDTO findDepartmentById(Long id) {
        if (id != null && id > 0) {
            return (DepartmentDTO) departmentBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<DepartmentDTO> getListDepartmentDTO(DepartmentDTO departmentDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (departmentDTO != null) {
            return departmentBusiness.search(departmentDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertDepartment(DepartmentDTO departmentDTO) {
        return departmentBusiness.createObject(departmentDTO);
    }

    @Override
    public String insertOrUpdateListDepartment(List<DepartmentDTO> departmentDTO) {
        return departmentBusiness.insertList(departmentDTO);
    }

    @Override
    public List<String> getSequenseDepartment(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);
        return departmentBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<DepartmentDTO> getListDepartmentByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<DepartmentDTO> lstDepartment = new ArrayList<>();
        for (ConditionBean con : lstCondition) {
            if (con.getType().equalsIgnoreCase(ParamUtils.TYPE_DATE)) {
                con.setField(StringUtils.formatFunction("trunc", con.getField()));
            } else if (con.getType().equalsIgnoreCase(ParamUtils.NUMBER)) {
                con.setType(ParamUtils.TYPE_NUMBER);
            } else {
                String value = StringUtils.formatLike(con.getValue());
                value = value.toLowerCase();
                con.setValue(value);
                con.setField(StringUtils.formatFunction("lower", con.getField()));
            }
            con.setOperator(StringUtils.convertTypeOperator(con.getOperator()));

        }
        lstDepartment = departmentBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstDepartment;
    }

}
