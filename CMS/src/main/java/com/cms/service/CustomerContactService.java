/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.CustomerContactDTO;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/26/2016 10:32 PM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface CustomerContactService {

    @WebMethod
    public List<CustomerContactDTO> getListCustomerContactDTO(@WebParam(name = "customerContactDTO") CustomerContactDTO customerContactDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod
    public String updateCustomerContact(@WebParam(name = "customerContactDTO") CustomerContactDTO customerContactDTO);

    //
    @WebMethod
    public String deleteCustomerContact(@WebParam(name = "customerContactDTOId") Long id);

    //
    @WebMethod
    public String deleteListCustomerContact(@WebParam(name = "customerContactListDTO") List<CustomerContactDTO> customerContactListDTO);

    //
    @WebMethod
    public CustomerContactDTO findCustomerContactById(@WebParam(name = "customerContactDTOId") Long id);

    //
    @WebMethod
    public ResultDTO insertCustomerContact(@WebParam(name = "customerContactDTO") CustomerContactDTO customerContactDTO);

    //
    @WebMethod
    public String insertOrUpdateListCustomerContact(@WebParam(name = "customerContactDTO") List<CustomerContactDTO> customerContactDTO);

    //
    @WebMethod
    public List<String> getSequenseCustomerContact(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod
    public List<CustomerContactDTO> getListCustomerContactByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
