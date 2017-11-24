/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.AppParamsDTO;
import com.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import com.vfw5.base.dto.ResultDTO;
import java.util.Map;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:18 AM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface AppParamsService {

    @WebMethod
    public List<AppParamsDTO> getListAppParamsDTO(@WebParam(name = "appParamsDTO") AppParamsDTO appParamsDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod
    public String updateAppParams(@WebParam(name = "appParamsDTO") AppParamsDTO appParamsDTO);

    //
    @WebMethod
    public String deleteAppParams(@WebParam(name = "appParamsDTOId") Long id);

    //
    @WebMethod
    public String deleteListAppParams(@WebParam(name = "appParamsListDTO") List<AppParamsDTO> appParamsListDTO);

    //
    @WebMethod
    public AppParamsDTO findAppParamsById(@WebParam(name = "appParamsDTOId") Long id);

    //
    @WebMethod
    public ResultDTO insertAppParams(@WebParam(name = "appParamsDTO") AppParamsDTO appParamsDTO);

    //
    @WebMethod
    public String insertOrUpdateListAppParams(@WebParam(name = "appParamsDTO") List<AppParamsDTO> appParamsDTO);

    //
    @WebMethod
    public List<String> getSequenseAppParams(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod
    public List<AppParamsDTO> getListAppParamsByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod
    public List<AppParamsDTO> getListProviderFromMineName(@WebParam(name = "mineName") String mineName, @WebParam(name = "map") Map<String, String> map);

    @WebMethod
    public List<AppParamsDTO> getProviderFromStaffCode(@WebParam(name = "staffCode") String staffCode, @WebParam(name = "mineName") String mineName);
    
    @WebMethod
    public List<AppParamsDTO> getProviderFromStaffCodeAndConditions(@WebParam(name = "staffCode") String staffCode, @WebParam(name = "map") Map<String, String> map);
    
    @WebMethod
    public List<AppParamsDTO> getStatusFromStaffCode(@WebParam(name = "staffCode") String staffCode, @WebParam(name = "mineName") String mineName);
}
