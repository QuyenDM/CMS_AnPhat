/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.RolesDTO;
import com.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import com.vwf5.base.dto.ResultDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:18 AM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface RolesService {

    @WebMethod
    public List<RolesDTO> getListRolesDTO(@WebParam(name = "rolesDTO") RolesDTO rolesDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod
    public String updateRoles(@WebParam(name = "rolesDTO") RolesDTO rolesDTO);

    //
    @WebMethod
    public String deleteRoles(@WebParam(name = "rolesDTOId") Long id);

    //
    @WebMethod
    public String deleteListRoles(@WebParam(name = "rolesListDTO") List<RolesDTO> rolesListDTO);

    //
    @WebMethod
    public RolesDTO findRolesById(@WebParam(name = "rolesDTOId") Long id);

    //
    @WebMethod
    public ResultDTO insertRoles(@WebParam(name = "rolesDTO") RolesDTO rolesDTO);

    //
    @WebMethod
    public String insertOrUpdateListRoles(@WebParam(name = "rolesDTO") List<RolesDTO> rolesDTO);

    //
    @WebMethod
    public List<String> getSequenseRoles(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod
    public List<RolesDTO> getListRolesByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod(operationName = "getListRolesByStaffId")
    public List<RolesDTO> getListRolesByStaffId(@WebParam(name = "staffId") String staffId);
}
