/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.RoleObjectsDTO;
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
public interface RoleObjectsService {

    @WebMethod(operationName = "getListRoleObjectsDTO")
    @WebResult(name = "roleObjectsDTO", targetNamespace = "http://service.cms.com")
    public List<RoleObjectsDTO> getListRoleObjectsDTO(@WebParam(name = "roleObjectsDTO") RoleObjectsDTO roleObjectsDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod(operationName = "updateRoleObjects")
    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String updateRoleObjects(@WebParam(name = "roleObjectsDTO") RoleObjectsDTO roleObjectsDTO);

    @WebMethod(operationName = "deleteRoleObjects")
    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteRoleObjects(@WebParam(name = "roleObjectsDTOId") Long id);

    @WebMethod(operationName = "deleteListRoleObjects")
    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteListRoleObjects(@WebParam(name = "roleObjectsListDTO") List<RoleObjectsDTO> roleObjectsListDTO);

    @WebMethod(operationName = "findRoleObjectsById")
    @WebResult(name = "roleObjects", targetNamespace = "http://service.cms.com")
    public RoleObjectsDTO findRoleObjectsById(@WebParam(name = "roleObjectsDTOId") Long id);

    @WebMethod(operationName = "insertRoleObjects")
    @WebResult(name = "resultDTO", targetNamespace = "http://service.cms.com")
//    thanh cong tra ve SUCCESS
    public ResultDTO insertRoleObjects(@WebParam(name = "roleObjectsDTO") RoleObjectsDTO roleObjectsDTO);

    @WebMethod(operationName = "insertOrUpdateListRoleObjects")
    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
//    thanh cong tra ve SUCCESS
    public String insertOrUpdateListRoleObjects(@WebParam(name = "roleObjectsDTO") List<RoleObjectsDTO> roleObjectsDTO);

//    
    @WebMethod(operationName = "getSequenseRoleObjects")
    @WebResult(name = "getSequense", targetNamespace = "http://service.cms.com")
    public List<String> getSequenseRoleObjects(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
//    demo

    @WebMethod(operationName = "getListRoleObjectsByCondition")
    @WebResult(name = "RoleObjectsDTO", targetNamespace = "http://service.cms.com")
    public List<RoleObjectsDTO> getListRoleObjectsByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
