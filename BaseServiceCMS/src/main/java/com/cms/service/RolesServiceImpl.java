/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.RolesDTO;
import com.cms.business.RolesBusinessInterface;
import java.util.List;
import javax.jws.WebService;
import java.util.ArrayList;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.vfw5.base.dto.ResultDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:27 AM
 */
@WebService(endpointInterface = "com.cms.service.RolesService")
public class RolesServiceImpl implements RolesService {

    @Autowired
    RolesBusinessInterface rolesBusiness;

    @Override
    public String updateRoles(RolesDTO rolesDTO) {
        return rolesBusiness.update(rolesDTO);
    }

    @Override
    public String deleteRoles(Long id) {
        return rolesBusiness.delete(id);
    }

    @Override
    public String deleteListRoles(List<RolesDTO> rolesListDTO) {
        return rolesBusiness.delete(rolesListDTO);
    }

    @Override
    public RolesDTO findRolesById(Long id) {
        if (id != null && id > 0) {
            return (RolesDTO) rolesBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<RolesDTO> getListRolesDTO(RolesDTO rolesDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (rolesDTO != null) {
            return rolesBusiness.search(rolesDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertRoles(RolesDTO rolesDTO) {
        return rolesBusiness.createObject(rolesDTO);
    }

    @Override
    public String insertOrUpdateListRoles(List<RolesDTO> rolesDTO) {
        return rolesBusiness.insertList(rolesDTO);
    }

    @Override
    public List<String> getSequenseRoles(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);

        return rolesBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<RolesDTO> getListRolesByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<RolesDTO> lstRoles = new ArrayList<>();
        for (ConditionBean con : lstCondition) {
            if (con.getType().equalsIgnoreCase(ParamUtils.TYPE_DATE)) {
                con.setField(StringUtils.formatFunction("trunc", con.getField()));
            } else if (con.getType().equalsIgnoreCase(ParamUtils.NUMBER)) {
                con.setType(ParamUtils.TYPE_NUMBER);
            } else if (con.getType().equalsIgnoreCase(ParamUtils.NUMBER_DOUBLE)) {
                con.setType(ParamUtils.NUMBER_DOUBLE);
            } else {
                String value = "";
                if (con.getOperator().equalsIgnoreCase(ParamUtils.NAME_LIKE)) {
                    value = StringUtils.formatLike(con.getValue());
                } else {
                    value = con.getValue();
                }
                con.setValue(value.toLowerCase());
                con.setField(StringUtils.formatFunction("lower", con.getField()));
            }
            con.setOperator(StringUtils.convertTypeOperator(con.getOperator()));
        }

        lstRoles = rolesBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstRoles;
    }

    @Override
    public List<RolesDTO> getListRolesByStaffId(String staffId) {
        return rolesBusiness.getListRolesByStaffId(staffId);
    }

}
