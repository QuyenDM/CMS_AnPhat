
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.AppParamsDTO;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;
import java.util.Map;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author thieulq1
 * @version 1.0
 * @since 1/19/2016 11:58 PM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface AppParamsService {

    @WebMethod
    public List<AppParamsDTO> getListAppParamsDTO(@WebParam(name = "appParamsDTO") AppParamsDTO appParamsDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod
    public String updateAppParams(@WebParam(name = "appParamsDTO") AppParamsDTO appParamsDTO);

    @WebMethod
    public String deleteAppParams(@WebParam(name = "appParamsDTOId") Long id);

    @WebMethod
    public String deleteListAppParams(@WebParam(name = "appParamsListDTO") List<AppParamsDTO> appParamsListDTO);

    @WebMethod
    public AppParamsDTO findAppParamsById(@WebParam(name = "appParamsDTOId") Long id);

    @WebMethod
//    thanh cong tra ve SUCCESS
    public ResultDTO insertAppParams(@WebParam(name = "appParamsDTO") AppParamsDTO appParamsDTO);

    @WebMethod
//    thanh cong tra ve SUCCESS
    public String insertOrUpdateListAppParams(@WebParam(name = "appParamsDTO") List<AppParamsDTO> appParamsDTO);

//    
    @WebMethod
    public List<String> getSequenseAppParams(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
//    demo
//    @WebMethod(operationName = "insertAndRecordLog")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
//    thanh cong tra ve SUCCESS
//    public String insertAndRecordLog(@WebParam(name = "appParamsDTO") List<AppParamsDTO> appParamsDTO);

    @WebMethod
    public List<AppParamsDTO> getListAppParamsByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCon, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod
    public List<AppParamsDTO> getListProviderFromMineName(@WebParam(name = "mineName") String mineName, @WebParam(name = "map") Map<String, String> map);

    @WebMethod
    public List<AppParamsDTO> getProviderFromStaffCodeAndConditions(@WebParam(name = "staffCode") String staffCode, @WebParam(name = "map") Map<String, String> map);

    @WebMethod
    public List<AppParamsDTO> getProviderFromStaffCode(@WebParam(name = "staffCode") String staffCode, @WebParam(name = "mineName") String mineName);

    @WebMethod
    public List<AppParamsDTO> getStatusFromStaffCode(@WebParam(name = "staffCode") String staffCode, @WebParam(name = "mineName") String mineName);
}
