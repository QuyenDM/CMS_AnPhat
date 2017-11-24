/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.business.service.AppParamsInterface;
import com.cms.dto.AppParamsDTO;

import java.util.List;
import javax.jws.WebService;
import java.util.ArrayList;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.service.BaseFWServiceInterface;
import java.util.Map;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:18 AM
 */
@WebService(endpointInterface = "com.cms.service.AppParamsService")
public class AppParamsServiceImpl implements AppParamsService {

    @Autowired
    BaseFWServiceInterface appParamsBusiness;

    @Autowired
    AppParamsInterface appParamsInterface;

    @Override
    public String updateAppParams(AppParamsDTO appParamsDTO) {
        return appParamsBusiness.update(appParamsDTO);
    }

    @Override
    public String deleteAppParams(Long id) {
        return appParamsBusiness.delete(id);
    }

    @Override
    public String deleteListAppParams(List<AppParamsDTO> appParamsListDTO) {
        return appParamsBusiness.delete(appParamsListDTO);
    }

    @Override
    public AppParamsDTO findAppParamsById(Long id) {
        if (id != null && id > 0) {
            return (AppParamsDTO) appParamsBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<AppParamsDTO> getListAppParamsDTO(AppParamsDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (appParamsDTO != null) {
            return appParamsBusiness.search(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertAppParams(AppParamsDTO appParamsDTO) {
        return appParamsBusiness.createObject(appParamsDTO);
    }

    @Override
    public String insertOrUpdateListAppParams(List<AppParamsDTO> appParamsDTO) {
        return appParamsBusiness.insertList(appParamsDTO);
    }

    @Override
    public List<String> getSequenseAppParams(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);

        return appParamsBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<AppParamsDTO> getListAppParamsByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<AppParamsDTO> lstAppParams = new ArrayList<>();
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

        lstAppParams = appParamsBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstAppParams;
    }

    @Override
    public List<AppParamsDTO> getListProviderFromMineName(String mineName, Map<String, String> map) {
        return appParamsInterface.getListProviderFromMineName(mineName, map);
    }

    @Override
    public List<AppParamsDTO> getProviderFromStaffCode(String staffCode, String mineName) {
        return appParamsInterface.getProviderFromStaffCode(staffCode, mineName);
    }

    @Override
    public List<AppParamsDTO> getProviderFromStaffCodeAndConditions(String staffCode, Map<String, String> map) {
        return appParamsInterface.getProviderFromStaffCodeAndConditions(staffCode, map);
    }

    @Override
    public List<AppParamsDTO> getStatusFromStaffCode(String staffCode, String mineName) {
        return appParamsInterface.getStatusFromStaffCode(staffCode, mineName);
    }

}
