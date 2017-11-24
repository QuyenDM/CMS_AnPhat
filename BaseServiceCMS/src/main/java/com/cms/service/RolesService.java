/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.RolesDTO;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebParam;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface RolesService {

    @WebMethod(operationName = "getListRolesDTO")
//    @WebResult(name = "rolesDTO", targetNamespace = "http://service.cms.com")
    public List<RolesDTO> getListRolesDTO(@WebParam(name = "rolesDTO") RolesDTO rolesDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod(operationName = "updateRoles")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String updateRoles(@WebParam(name = "rolesDTO") RolesDTO rolesDTO);

    @WebMethod(operationName = "deleteRoles")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteRoles(@WebParam(name = "rolesDTOId") Long id);

    @WebMethod(operationName = "deleteListRoles")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteListRoles(@WebParam(name = "rolesListDTO") List<RolesDTO> rolesListDTO);

    @WebMethod(operationName = "findRolesById")
//    @WebResult(name = "roles", targetNamespace = "http://service.cms.com")
    public RolesDTO findRolesById(@WebParam(name = "rolesDTOId") Long id);

    @WebMethod(operationName = "insertRoles")
//    @WebResult(name = "resultDTO", targetNamespace = "http://service.cms.com")
//    thanh cong tra ve SUCCESS
    public ResultDTO insertRoles(@WebParam(name = "rolesDTO") RolesDTO rolesDTO);

    @WebMethod(operationName = "insertOrUpdateListRoles")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
//    thanh cong tra ve SUCCESS
    public String insertOrUpdateListRoles(@WebParam(name = "rolesDTO") List<RolesDTO> rolesDTO);

//    
    @WebMethod(operationName = "getSequenseRoles")
//    @WebResult(name = "getSequense", targetNamespace = "http://service.cms.com")
    public List<String> getSequenseRoles(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
//    demo

    @WebMethod(operationName = "getListRolesByCondition")
    public List<RolesDTO> getListRolesByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod(operationName = "getListRolesByStaffId")
    public List<RolesDTO> getListRolesByStaffId(@WebParam(name = "staffId") String staffId);

}
