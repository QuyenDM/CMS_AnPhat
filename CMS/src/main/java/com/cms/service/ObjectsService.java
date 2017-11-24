/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.cms.service;

import com.cms.dto.RolesDTO;
import com.cms.login.dto.ObjectsDTO;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
/**
* @author QuyenDM
* @version 1.0
* @since 7/8/2016 9:18 AM
*/
@WebService(targetNamespace = "http://service.cms.com")
public interface ObjectsService {
    @WebMethod
//    @WebResult(name = "objectsDTO", targetNamespace = "http://service.cms.com")
    public List<ObjectsDTO> getListObjectsDTO(@WebParam(name="objectsDTO") ObjectsDTO objectsDTO,@WebParam(name="rowStart") int rowStart,@WebParam(name="maxRow") int maxRow,@WebParam(name="sortType") String sortType,@WebParam(name="sortFieldList") String sortFieldList);
    //
    @WebMethod
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String updateObjects(@WebParam(name = "objectsDTO") ObjectsDTO objectsDTO);
    //
    @WebMethod
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteObjects(@WebParam(name = "objectsDTOId") Long id);
    //
    @WebMethod
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteListObjects(@WebParam(name = "objectsListDTO") List<ObjectsDTO> objectsListDTO);
    //
    @WebMethod
//    @WebResult(name = "objectsDTO", targetNamespace = "http://service.cms.com")
    public ObjectsDTO findObjectsById(@WebParam(name = "objectsDTOId") Long id);  
    //
    @WebMethod
//    @WebResult(name = "resultDTO", targetNamespace = "http://service.cms.com")
    public ResultDTO insertObjects(@WebParam(name="objectsDTO") ObjectsDTO objectsDTO);
    //
    @WebMethod
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String insertOrUpdateListObjects(@WebParam(name = "objectsDTO") List<ObjectsDTO> objectsDTO);   
    //
    @WebMethod
//    @WebResult(name = "getSequense", targetNamespace = "http://service.cms.com")
    public List<String> getSequenseObjects(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);
    //    
    @WebMethod
//    @WebResult(name = "objectsDTO", targetNamespace = "http://service.cms.com")
    public List<ObjectsDTO> getListObjectsByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
    //Lay danh sach chuc nang tu staffId
    @WebMethod
//    @WebResult(name = "objectsDTO", targetNamespace = "http://service.cms.com")
    public List<ObjectsDTO> getListObjectDTOByStaffId(@WebParam(name = "staffId") String staffId);
    
    @WebMethod
    public List<ObjectsDTO> getListObjectByRole(@WebParam(name = "roles") RolesDTO roles);
}
