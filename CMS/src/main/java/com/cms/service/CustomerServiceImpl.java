
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.CustomerDTO;
import com.cms.dto.CustomerInfomationDTO;
import com.cms.utils.BundleUtils;
import java.util.List;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import com.ws.provider.CxfWsClientFactory;
import com.ws.provider.WsEndpoint;
import java.util.HashMap;
import java.util.Map;

/**
 * @author thieulq1
 * @version 1.0
 * @since 1/19/2016 11:58 PM
 */
public class CustomerServiceImpl implements CustomerService {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CustomerServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private CustomerService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static CustomerServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized CustomerServiceImpl getInstance() {
        if (instance == null) {
            instance = new CustomerServiceImpl();
        }
        return instance;
    }

    public CustomerServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(CustomerService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(CustomerService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public String updateCustomer(CustomerDTO appParamsDTO) {
        return client.updateCustomer(appParamsDTO);
    }

    @Override
    public String deleteListCustomer(List<CustomerDTO> appParamsListDTO) {
        return client.deleteListCustomer(appParamsListDTO);
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        return client.findCustomerById(id);
    }

    @Override
    public List<CustomerDTO> getListCustomerDTO(CustomerDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListCustomerDTO(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertCustomer(CustomerDTO appParamsDTO) {
        return client.insertCustomer(appParamsDTO);
    }

    @Override
    public List<CustomerDTO> insertOrUpdateListCustomer(List<CustomerDTO> appParamsDTO) {
        return client.insertOrUpdateListCustomer(appParamsDTO);
    }

    @Override
    public List<String> getSequenseCustomer(String seqName, int... size) {
        return client.getSequenseCustomer(seqName, size);
    }

    @Override
    public List<CustomerDTO> getListCustomerByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListCustomerByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteCustomer(Long id) {
        return client.deleteCustomer(id);
    }

    @Override
    public String saveOrUpdateAndReturnErrors(List<CustomerDTO> lstCustomer) {
        return client.saveOrUpdateAndReturnErrors(lstCustomer);
    }

    @Override
    public CustomerInfomationDTO getCustInfo(String taxCode, String staffCode, String mineName) {
        return client.getCustInfo(taxCode, staffCode, mineName);
    }

    @Override
    public List<CustomerDTO> searchCustomers(CustomerDTO customerDTO, int maxResult) {
        return client.searchCustomers(customerDTO, maxResult);
    }

    @Override
    public List<CustomerDTO> getListCustomerFromTermInfo(List<ConditionBean> lstConditions) {
        return client.getListCustomerFromTermInfo(lstConditions);
    }
    
    @Override
    public List<CustomerDTO> getListCustomerFromTermInfoWithoutTaxCodes(List<ConditionBean> lstConditions, List<String> taxCodesExecuted) {
        return client.getListCustomerFromTermInfoWithoutTaxCodes(lstConditions, taxCodesExecuted);
    }

    @Override
    public List<CustomerDTO> getCustomerExisted(List<String> taxCodes) {
        return client.getCustomerExisted(taxCodes);
    }
    
    @Override
    public List<CustomerDTO> getListCustomerOfMineName(String mineName) {
        return client.getListCustomerOfMineName(mineName);
    }
    
    @Override
    public List<CustomerDTO> getListDevidedCustomerOfMineName(String mineName) {
        return client.getListDevidedCustomerOfMineName(mineName);
    }

    @Override
    public List<CustomerDTO> getCustomerFromIHTKK(List<String> taxCodes) {
        return client.getCustomerFromIHTKK(taxCodes);
    }

    
}
