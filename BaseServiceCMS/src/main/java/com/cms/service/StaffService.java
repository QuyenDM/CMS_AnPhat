/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.StaffDTO;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 1:28 PM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface StaffService {

    @WebMethod(operationName = "getListStaffDTO")
//    @WebResult(name = "staffDTO", targetNamespace = "http://service.cms.com")
    public List<StaffDTO> getListStaffDTO(@WebParam(name = "staffDTO") StaffDTO staffDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod(operationName = "updateStaff")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String updateStaff(@WebParam(name = "staffDTO") StaffDTO staffDTO);

    //
    @WebMethod(operationName = "deleteStaff")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteStaff(@WebParam(name = "staffDTOId") Long id, @WebParam(name = "userNameLogging") String username);

    //
    @WebMethod(operationName = "deleteListStaff")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteListStaff(@WebParam(name = "staffListDTO") List<StaffDTO> staffListDTO);

    //
    @WebMethod(operationName = "findStaffById")
//    @WebResult(name = "staff", targetNamespace = "http://service.cms.com")
    public StaffDTO findStaffById(@WebParam(name = "staffDTOId") Long id);

    @WebMethod(operationName = "insertStaff")
//    @WebResult(name = "resultDTO", targetNamespace = "http://service.cms.com")
    public ResultDTO insertStaff(@WebParam(name = "staffDTO") StaffDTO staffDTO);

    //
    @WebMethod(operationName = "insertOrUpdateListStaff")
//    @WebResult(name = "insertListStaff", targetNamespace = "http://service.cms.com")
    public String insertOrUpdateListStaff(@WebParam(name = "staffDTO") List<StaffDTO> staffDTO);

    //    
    @WebMethod(operationName = "getSequenseStaff")
//    @WebResult(name = "getSequense", targetNamespace = "http://service.cms.com")
    public List<String> getSequenseStaff(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //
    @WebMethod(operationName = "getListStaffByCondition")
//    @WebResult(name = "StaffDTO", targetNamespace = "http://service.cms.com")
    public List<StaffDTO> getListStaffByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

}
