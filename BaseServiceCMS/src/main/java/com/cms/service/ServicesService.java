
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.cms.service;

import com.cms.dto.ServicesDTO;
import com.vfw5.base.pojo.ConditionBean;
import java.util.List;
import java.util.Locale;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebParam;
import com.vfw5.base.dto.ResultDTO;
/**
* @author Truongbx3
* @version 1.0
* @since 5/25/2015 2:33 PM
*/
@WebService(targetNamespace = "http://service.cms.com")
public interface ServicesService {
    @WebMethod(operationName = "getListServicesDTO")
    @WebResult(name = "servicesDTO", targetNamespace = "http://service.cms.com")
    public List<ServicesDTO> getListServicesDTO(@WebParam(name="servicesDTO") ServicesDTO servicesDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod(operationName = "updateServices")
    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String updateServices(@WebParam(name = "servicesDTO") ServicesDTO servicesDTO);
    //
    @WebMethod(operationName = "deleteServices")
    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteServices(@WebParam(name = "servicesDTOId") Long id);
    //
    @WebMethod(operationName = "deleteListServices")
    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteListServices(@WebParam(name = "servicesListDTO") List<ServicesDTO> servicesListDTO);
    //
    @WebMethod(operationName = "findServicesById")
    @WebResult(name = "services", targetNamespace = "http://service.cms.com")
    public ServicesDTO findServicesById(@WebParam(name = "servicesDTOId") Long id);  
    //
    @WebMethod(operationName = "insertServices")
    @WebResult(name = "resultDTO", targetNamespace = "http://service.cms.com")
    public ResultDTO insertServices(@WebParam(name="servicesDTO") ServicesDTO servicesDTO);
    //
    @WebMethod(operationName = "insertOrUpdateListServices")
    @WebResult(name = "insertListServices", targetNamespace = "http://service.cms.com")
    public String insertOrUpdateListServices(@WebParam(name = "servicesDTO") List<ServicesDTO> servicesDTO);   
    //
    @WebMethod(operationName = "getSequenseServices")
    @WebResult(name = "getSequense", targetNamespace = "http://service.cms.com")
    public List<String> getSequenseServices(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod(operationName = "getListServicesByCondition")
    @WebResult(name = "ServicesDTO", targetNamespace = "http://service.cms.com")
    public List<ServicesDTO> getListServicesByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
