/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.CustomerDTO;
import com.cms.dto.CustomerInfomationDTO;
import com.cms.dto.CustomerUserInfoDTO;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:55 AM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface CustomerService {

    @WebMethod(operationName = "getListCustomerDTO")
//    @WebResult(name = "customerDTO", targetNamespace = "http://service.cms.com")
    public List<CustomerDTO> getListCustomerDTO(@WebParam(name = "customerDTO") CustomerDTO customerDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod(operationName = "updateCustomer")
    public String updateCustomer(@WebParam(name = "customerDTO") CustomerDTO customerDTO);

    //
    @WebMethod(operationName = "deleteCustomer")
    public String deleteCustomer(@WebParam(name = "customerDTOId") Long id);

    //
    @WebMethod(operationName = "deleteListCustomer")
    public String deleteListCustomer(@WebParam(name = "customerListDTO") List<CustomerDTO> customerListDTO);

    //
    @WebMethod(operationName = "findCustomerById")
    public CustomerDTO findCustomerById(@WebParam(name = "customerDTOId") Long id);

//    //
    @WebMethod(operationName = "insertCustomer")
    public ResultDTO insertCustomer(@WebParam(name = "customerDTO") CustomerDTO customerDTO);

    //
    @WebMethod(operationName = "insertOrUpdateListCustomer")
    public List<CustomerDTO> insertOrUpdateListCustomer(@WebParam(name = "customerDTO") List<CustomerDTO> customerDTO);

    @WebMethod(operationName = "getSequenseCustomer")
    public List<String> getSequenseCustomer(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod(operationName = "getListCustomerByCondition")
    public List<CustomerDTO> getListCustomerByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod(operationName = "saveOrUpdateAndReturnErrors")
    public String saveOrUpdateAndReturnErrors(@WebParam(name = "lstCustomer") List<CustomerDTO> lstCustomer);

    @WebMethod(operationName = "getCustUserInfor")
    public CustomerUserInfoDTO getCustUserInfor(@WebParam(name = "userCode") String userCode);

    @WebMethod(operationName = "getCustInfo")
    public CustomerInfomationDTO getCustInfo(
            @WebParam(name = "taxCode") String taxCode,
            @WebParam(name = "staffCode") String staffCode,
            @WebParam(name = "mineName") String mineName);

    @WebMethod(operationName = "searchCustomers")
    public List<CustomerDTO> searchCustomers(CustomerDTO customerDTO, int maxResult);

    @WebMethod(operationName = "getListCustomerFromTermInfo")
    public List<CustomerDTO> getListCustomerFromTermInfo(List<ConditionBean> lstConditions);

    @WebMethod(operationName = "getListCustomerFromTermInfoWithoutTaxCodes")
    public List<CustomerDTO> getListCustomerFromTermInfoWithoutTaxCodes(List<ConditionBean> lstConditions, List<String> taxCodesExecuted);

    @WebMethod(operationName = "getCustomerExisted")
    public List<CustomerDTO> getCustomerExisted(@WebParam(name = "taxCodes") List<String> taxCodes);

    @WebMethod(operationName = "getListCustomerOfMineName")
    public List<CustomerDTO> getListCustomerOfMineName(@WebParam(name = "mineName") String mineName);

    @WebMethod(operationName = "getListDevidedCustomerOfMineName")
    public List<CustomerDTO> getListDevidedCustomerOfMineName(@WebParam(name = "mineName") String mineName);
    
    @WebMethod(operationName = "getCustomerFromIHTKK")
    public List<CustomerDTO> getCustomerFromIHTKK(@WebParam(name = "taxCodes") List<String> taxCodes);
}
