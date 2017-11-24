/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.CustomerCareHistoryDTO;
import com.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import com.vfw5.base.dto.ResultDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/30/2016 1:26 AM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface CustomerCareHistoryService {

    @WebMethod
    public List<CustomerCareHistoryDTO> getListCustomerCareHistoryDTO(@WebParam(name = "customerCareHistoryDTO") CustomerCareHistoryDTO customerCareHistoryDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod
    public String updateCustomerCareHistory(@WebParam(name = "customerCareHistoryDTO") CustomerCareHistoryDTO customerCareHistoryDTO);

    //
    @WebMethod
    public String deleteCustomerCareHistory(@WebParam(name = "customerCareHistoryDTOId") Long id);

    //
    @WebMethod
    public String deleteListCustomerCareHistory(@WebParam(name = "customerCareHistoryListDTO") List<CustomerCareHistoryDTO> customerCareHistoryListDTO);

    //
    @WebMethod
    public CustomerCareHistoryDTO findCustomerCareHistoryById(@WebParam(name = "customerCareHistoryDTOId") Long id);

    //
    @WebMethod
    public ResultDTO insertCustomerCareHistory(@WebParam(name = "customerCareHistoryDTO") CustomerCareHistoryDTO customerCareHistoryDTO);
    
    //
    @WebMethod
    public String insertOrUpdateListCustomerCareHistory(@WebParam(name = "customerCareHistoryDTO") List<CustomerCareHistoryDTO> customerCareHistoryDTO);

    //
    @WebMethod
    public List<String> getSequenseCustomerCareHistory(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod
    public List<CustomerCareHistoryDTO> getListCustomerCareHistoryByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
