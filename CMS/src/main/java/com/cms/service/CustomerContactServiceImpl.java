
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.CustomerContactDTO;
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
public class CustomerContactServiceImpl implements CustomerContactService {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CustomerContactServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private CustomerContactService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static CustomerContactServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized CustomerContactServiceImpl getInstance() {
        if (instance == null) {
            instance = new CustomerContactServiceImpl();
        }
        return instance;
    }

    public CustomerContactServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(CustomerContactService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(CustomerContactService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public String updateCustomerContact(CustomerContactDTO appParamsDTO) {
        return client.updateCustomerContact(appParamsDTO);
    }

    @Override
    public String deleteListCustomerContact(List<CustomerContactDTO> appParamsListDTO) {
        return client.deleteListCustomerContact(appParamsListDTO);
    }

    @Override
    public CustomerContactDTO findCustomerContactById(Long id) {
        return client.findCustomerContactById(id);
    }

    @Override
    public List<CustomerContactDTO> getListCustomerContactDTO(CustomerContactDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListCustomerContactDTO(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertCustomerContact(CustomerContactDTO appParamsDTO) {
        return client.insertCustomerContact(appParamsDTO);
    }

    @Override
    public String insertOrUpdateListCustomerContact(List<CustomerContactDTO> appParamsDTO) {
        return client.insertOrUpdateListCustomerContact(appParamsDTO);
    }

    @Override
    public List<String> getSequenseCustomerContact(String seqName, int... size) {
        return client.getSequenseCustomerContact(seqName, size);
    }

    @Override
    public List<CustomerContactDTO> getListCustomerContactByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListCustomerContactByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteCustomerContact(Long id) {
        return client.deleteCustomerContact(id);
    }

}
