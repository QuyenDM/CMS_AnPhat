/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.business.service;

import com.cms.dto.CustomerDTO;
import com.cms.dto.CustomerUserInfoDTO;
import com.cms.model.Customer;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author TruongBx3
 */
public interface CustomerBusinessInterface extends BaseFWServiceInterface<CustomerDTO, Customer> {

    public String insertOrUpdateReturnRecordErrors(List<CustomerDTO> lstCustomerDTO);

    public CustomerUserInfoDTO getCustUserInfo(String userCode);

    public List<CustomerDTO> searchCustomers(CustomerDTO customer, int maxResult);
    
    public List<CustomerDTO> getListCustomerFromTermInfo(List<ConditionBean> lstConditions);
    
    public List<CustomerDTO> getListCustomerFromTermInfoWithoutTaxCodes(List<ConditionBean> lstConditions, List<String> taxCodesExecuted);
    
    public List<CustomerDTO> getCustomerExisted(List<String> taxCodes);
    
    public List<CustomerDTO> getListCustomerOfMineName(String mineName);
    public List<CustomerDTO> getListDevidedCustomerOfMineName(String mineName);
    
    public List<CustomerDTO> getCustomerFromIHTKK(List<String> taxCodes);
}
