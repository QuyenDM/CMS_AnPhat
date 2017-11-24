/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.DepartmentDTO;
import com.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import com.vfw5.base.dto.ResultDTO;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 22-Apr-15 6:02 PM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface DepartmentService {

    @WebMethod(operationName = "getListDepartmentDTO")
//    @WebResult(name = "departmentDTO", targetNamespace = "http://service.cms.com")
    public List<DepartmentDTO> getListDepartmentDTO(@WebParam(name = "departmentDTO") DepartmentDTO departmentDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod(operationName = "updateDepartment")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String updateDepartment(@WebParam(name = "departmentDTO") DepartmentDTO departmentDTO);

    //
    @WebMethod(operationName = "deleteDepartment")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteDepartment(@WebParam(name = "departmentDTOId") Long id, @WebParam(name = "userNameLogging") String username);
//    @WebMethod(operationName = "deleteDepartment")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
//    public String deleteDepartment(@WebParam(name = "departmentDTOId") Long id);
    //

    @WebMethod(operationName = "deleteListDepartment")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteListDepartment(@WebParam(name = "departmentListDTO") List<DepartmentDTO> departmentListDTO);

    //
    @WebMethod(operationName = "findDepartmentById")
//    @WebResult(name = "department", targetNamespace = "http://service.cms.com")
//    @WebResult(name = "department", targetNamespace = "http://service.cms.com")
    public DepartmentDTO findDepartmentById(@WebParam(name = "departmentDTOId") Long id);

    //
    @WebMethod(operationName = "insertDepartment")
//    @WebResult(name = "resultDTO", targetNamespace = "http://service.cms.com")
    public ResultDTO insertDepartment(@WebParam(name = "departmentDTO") DepartmentDTO departmentDTO);

    //
    @WebMethod(operationName = "insertOrUpdateListDepartment")
//    @WebResult(name = "insertListDepartment", targetNamespace = "http://service.cms.com")
    public String insertOrUpdateListDepartment(@WebParam(name = "departmentDTO") List<DepartmentDTO> departmentDTO);

    //
    @WebMethod(operationName = "getSequenseDepartment")
//    @WebResult(name = "getSequense", targetNamespace = "http://service.cms.com")
    public List<String> getSequenseDepartment(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod(operationName = "getListDepartmentByCondition")
//    @WebResult(name = "DepartmentDTO", targetNamespace = "http://service.cms.com")
    public List<DepartmentDTO> getListDepartmentByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
