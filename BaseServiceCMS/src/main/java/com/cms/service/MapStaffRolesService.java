/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.cms.service;

import com.cms.dto.MapStaffRolesDTO;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
* @author QuyenDM
* @version 1.0
* @since 8/14/2016 2:37 PM
*/
@WebService(targetNamespace = "http://service.cms.com")
public interface MapStaffRolesService {
    @WebMethod
    public List<MapStaffRolesDTO> getListMapStaffRolesDTO(@WebParam(name="mapStaffRolesDTO") MapStaffRolesDTO mapStaffRolesDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod
    public String updateMapStaffRoles(@WebParam(name = "mapStaffRolesDTO") MapStaffRolesDTO mapStaffRolesDTO);
    //
    @WebMethod
    public String deleteMapStaffRoles(@WebParam(name = "mapStaffRolesDTOId") Long id);
    //
    @WebMethod
    public String deleteListMapStaffRoles(@WebParam(name = "mapStaffRolesListDTO") List<MapStaffRolesDTO> mapStaffRolesListDTO);
    //
    @WebMethod
    public MapStaffRolesDTO findMapStaffRolesById(@WebParam(name = "mapStaffRolesDTOId") Long id);  
    //
    @WebMethod
    public ResultDTO insertMapStaffRoles(@WebParam(name="mapStaffRolesDTO") MapStaffRolesDTO mapStaffRolesDTO);
    //
    @WebMethod
    public String insertOrUpdateListMapStaffRoles(@WebParam(name = "mapStaffRolesDTO") List<MapStaffRolesDTO> mapStaffRolesDTO);   
    //
    @WebMethod
    public List<String> getSequenseMapStaffRoles(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod
    public List<MapStaffRolesDTO> getListMapStaffRolesByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
