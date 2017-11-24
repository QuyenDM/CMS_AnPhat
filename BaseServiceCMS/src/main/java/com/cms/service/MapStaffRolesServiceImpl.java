/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.business.service.MapStaffRolesBusinessInterface;
import com.cms.dto.MapStaffRolesDTO;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/14/2016 2:37 PM
 */
@WebService(endpointInterface = "com.cms.service.MapStaffRolesService")
public class MapStaffRolesServiceImpl implements MapStaffRolesService {

    @Autowired
    MapStaffRolesBusinessInterface mapStaffRolesBusiness;

    @Override
    public String updateMapStaffRoles(MapStaffRolesDTO mapStaffRolesDTO) {
        return mapStaffRolesBusiness.update(mapStaffRolesDTO);
    }

    @Override
    public String deleteMapStaffRoles(Long id) {
        return mapStaffRolesBusiness.delete(id);
    }

    @Override
    public String deleteListMapStaffRoles(List<MapStaffRolesDTO> mapStaffRolesListDTO) {
        return mapStaffRolesBusiness.delete(mapStaffRolesListDTO);
    }

    @Override
    public MapStaffRolesDTO findMapStaffRolesById(Long id) {
        if (id != null && id > 0) {
            return (MapStaffRolesDTO) mapStaffRolesBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<MapStaffRolesDTO> getListMapStaffRolesDTO(MapStaffRolesDTO mapStaffRolesDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (mapStaffRolesDTO != null) {
            return mapStaffRolesBusiness.search(mapStaffRolesDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertMapStaffRoles(MapStaffRolesDTO mapStaffRolesDTO) {
        return mapStaffRolesBusiness.createObject(mapStaffRolesDTO);
    }

    @Override
    public String insertOrUpdateListMapStaffRoles(List<MapStaffRolesDTO> mapStaffRolesDTO) {
        return mapStaffRolesBusiness.insertList(mapStaffRolesDTO);
    }

    @Override
    public List<String> getSequenseMapStaffRoles(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);

        return mapStaffRolesBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<MapStaffRolesDTO> getListMapStaffRolesByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<MapStaffRolesDTO> lstMapStaffRoles = new ArrayList<>();
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

        lstMapStaffRoles = mapStaffRolesBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstMapStaffRoles;
    }

}
