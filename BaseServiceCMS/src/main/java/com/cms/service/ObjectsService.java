/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.ObjectsDTO;
import com.cms.dto.RolesDTO;
import com.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import com.vfw5.base.dto.ResultDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:18 AM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface ObjectsService {

    @WebMethod
    public List<ObjectsDTO> getListObjectsDTO(@WebParam(name = "objectsDTO") ObjectsDTO objectsDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod
    public String updateObjects(@WebParam(name = "objectsDTO") ObjectsDTO objectsDTO);

    //
    @WebMethod
    public String deleteObjects(@WebParam(name = "objectsDTOId") Long id);

    //
    @WebMethod
    public String deleteListObjects(@WebParam(name = "objectsListDTO") List<ObjectsDTO> objectsListDTO);

    //
    @WebMethod
    public ObjectsDTO findObjectsById(@WebParam(name = "objectsDTOId") Long id);

    //
    @WebMethod
    public ResultDTO insertObjects(@WebParam(name = "objectsDTO") ObjectsDTO objectsDTO);

    //
    @WebMethod
    public String insertOrUpdateListObjects(@WebParam(name = "objectsDTO") List<ObjectsDTO> objectsDTO);

    //
    @WebMethod
    public List<String> getSequenseObjects(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod
    public List<ObjectsDTO> getListObjectsByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //Lay danh sach chuc nang tu staffId
    @WebMethod
    public List<ObjectsDTO> getListObjectDTOByStaffId(@WebParam(name = "staffId") String staffId);

    //Lay gia tri chuc nang tu vai tro
    @WebMethod
    public List<ObjectsDTO> getListObjectByRole(@WebParam(name = "roles") RolesDTO roles);

}
