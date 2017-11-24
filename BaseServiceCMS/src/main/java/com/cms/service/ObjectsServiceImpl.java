/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.ObjectsDTO;
import com.cms.business.service.ObjectsBusinessInterface;
import com.cms.dto.RolesDTO;
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
 * @since 7/8/2016 9:18 AM
 */
@WebService(endpointInterface = "com.cms.service.ObjectsService")
public class ObjectsServiceImpl implements ObjectsService {

    @Autowired
    ObjectsBusinessInterface objectsBusiness;

    @Override
    public String updateObjects(ObjectsDTO objectsDTO) {
        return objectsBusiness.update(objectsDTO);
    }

    @Override
    public String deleteObjects(Long id) {
        return objectsBusiness.delete(id);
    }

    @Override
    public String deleteListObjects(List<ObjectsDTO> objectsListDTO) {
        return objectsBusiness.delete(objectsListDTO);
    }

    @Override
    public ObjectsDTO findObjectsById(Long id) {
        if (id != null && id > 0) {
            return (ObjectsDTO) objectsBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<ObjectsDTO> getListObjectsDTO(ObjectsDTO objectsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (objectsDTO != null) {
            return objectsBusiness.search(objectsDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertObjects(ObjectsDTO objectsDTO) {
        return objectsBusiness.createObject(objectsDTO);
    }

    @Override
    public String insertOrUpdateListObjects(List<ObjectsDTO> objectsDTO) {
        return objectsBusiness.insertList(objectsDTO);
    }

    @Override
    public List<String> getSequenseObjects(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);

        return objectsBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<ObjectsDTO> getListObjectsByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<ObjectsDTO> lstObjects = new ArrayList<>();
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

        lstObjects = objectsBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstObjects;
    }

    @Override
    public List<ObjectsDTO> getListObjectDTOByStaffId(String staffId) {
        return objectsBusiness.getListObjectByStaffId(staffId);
    }

    @Override
    public List<ObjectsDTO> getListObjectByRole(RolesDTO roles) {
        return objectsBusiness.getListObjectByRole(roles);
    }
    
    
}
