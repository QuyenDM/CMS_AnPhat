/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business;

import com.cms.business.service.CustomerBusinessInterface;
import com.vfw5.base.service.BaseFWServiceImpl;
import com.cms.dto.CustomerDTO;
import com.cms.model.Customer;
import com.cms.dao.CustomerDAO;
import com.cms.dto.CustomerUserInfoDTO;
import com.vfw5.base.pojo.ConditionBean;
import org.hibernate.Session;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:55 AM
 */
@Service("customerBusiness")
@Transactional
public class CustomerBusiness extends BaseFWServiceImpl<CustomerDAO, CustomerDTO, Customer> implements CustomerBusinessInterface {

    @Autowired
    private CustomerDAO customerDAO;

    public CustomerBusiness() {
        tModel = new Customer();
        tDAO = customerDAO;
    }

    @Override
    public CustomerDAO gettDAO() {
        return customerDAO;
    }

    public CustomerBusiness(Session session) {
        this.session = session;
        tModel = new Customer();
        tDAO = customerDAO;
    }

    @Override
    public String insertOrUpdateReturnRecordErrors(List<CustomerDTO> lstCustomerDTO) {
        List<Customer> model = convertListDTOtoModel(lstCustomerDTO);
        return gettDAO().saveOrUpdateReturnErrors(model);
    }

    @Override
    public CustomerUserInfoDTO getCustUserInfo(String userCode) {
        return gettDAO().getCustUserInforDTO(userCode);
    }

    @Override
    public List<CustomerDTO> searchCustomers(CustomerDTO customer, int maxResult) {
        return gettDAO().searchCustomers(customer, maxResult);
    }

    @Override
    public List<CustomerDTO> getListCustomerFromTermInfo(List<ConditionBean> lstConditions) {
        return gettDAO().getListCustomerWithTermInfo(lstConditions);
    }
    
    @Override
    public List<CustomerDTO> getListCustomerFromTermInfoWithoutTaxCodes(List<ConditionBean> lstConditions, List<String> taxCodesExecuted) {
        return gettDAO().getListCustomerFromTermInfoWithoutTaxCodes(lstConditions,taxCodesExecuted);
    }

    @Override
    public List<CustomerDTO> getCustomerExisted(List<String> taxCodes) {
        return gettDAO().getCustomerExisted(taxCodes);
    }
    
    @Override
    public List<CustomerDTO> getListCustomerOfMineName(String mineName) {
        return gettDAO().getListCustomerOfMineName(mineName);
    }
    @Override
    public List<CustomerDTO> getListDevidedCustomerOfMineName(String mineName) {
        return gettDAO().getListDevidedCustomerOfMineName(mineName);
    }

    @Override
    public List<CustomerDTO> getCustomerFromIHTKK(List<String> taxCodes) {
        return gettDAO().getCustomerFromIHTKK(taxCodes);
    }

}
