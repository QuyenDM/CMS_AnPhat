/**
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.RoleObjectsDTO;
import com.cms.business.service.RoleObjectsBusinessInterface;
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
 * @since 7/8/2016 9:32 AM
 */
@WebService(endpointInterface = "com.cms.service.RoleObjectsService")
public class RoleObjectsServiceImpl implements RoleObjectsService {

    @Autowired
    RoleObjectsBusinessInterface roleObjectsBusiness;

    @Override
    public String updateRoleObjects(RoleObjectsDTO roleObjectsDTO) {
        return roleObjectsBusiness.update(roleObjectsDTO);
    }

    @Override
    public String deleteRoleObjects(Long id) {
        return roleObjectsBusiness.delete(id);
    }

    @Override
    public String deleteListRoleObjects(List<RoleObjectsDTO> roleObjectsListDTO) {
        return roleObjectsBusiness.delete(roleObjectsListDTO);
    }

    @Override
    public RoleObjectsDTO findRoleObjectsById(Long id) {
        if (id != null && id > 0) {
            return (RoleObjectsDTO) roleObjectsBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<RoleObjectsDTO> getListRoleObjectsDTO(RoleObjectsDTO roleObjectsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (roleObjectsDTO != null) {
            return roleObjectsBusiness.search(roleObjectsDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertRoleObjects(RoleObjectsDTO roleObjectsDTO) {
        return roleObjectsBusiness.createObject(roleObjectsDTO);
    }

    @Override
    public String insertOrUpdateListRoleObjects(List<RoleObjectsDTO> roleObjectsDTO) {
        return roleObjectsBusiness.insertList(roleObjectsDTO);
    }

    @Override
    public List<String> getSequenseRoleObjects(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);

        return roleObjectsBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<RoleObjectsDTO> getListRoleObjectsByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<RoleObjectsDTO> lstRoleObjects = new ArrayList<>();
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

        lstRoleObjects = roleObjectsBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstRoleObjects;
    }

}
