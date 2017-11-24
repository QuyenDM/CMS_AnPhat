
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.CustomerCareHistoryDTO;
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
public class CustomerCareHistoryServiceImpl implements CustomerCareHistoryService {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CustomerCareHistoryServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private CustomerCareHistoryService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static CustomerCareHistoryServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized CustomerCareHistoryServiceImpl getInstance() {
        if (instance == null) {
            instance = new CustomerCareHistoryServiceImpl();
        }
        return instance;
    }

    public CustomerCareHistoryServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(CustomerCareHistoryService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(CustomerCareHistoryService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public String updateCustomerCareHistory(CustomerCareHistoryDTO dto) {
        return client.updateCustomerCareHistory(dto);
    }

    @Override
    public String deleteListCustomerCareHistory(List<CustomerCareHistoryDTO> appParamsListDTO) {
        return client.deleteListCustomerCareHistory(appParamsListDTO);
    }

    @Override
    public CustomerCareHistoryDTO findCustomerCareHistoryById(Long id) {
        return client.findCustomerCareHistoryById(id);
    }

    @Override
    public List<CustomerCareHistoryDTO> getListCustomerCareHistoryDTO(CustomerCareHistoryDTO dto, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListCustomerCareHistoryDTO(dto, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertCustomerCareHistory(CustomerCareHistoryDTO dto) {
        return client.insertCustomerCareHistory(dto);
    }

    @Override
    public String insertOrUpdateListCustomerCareHistory(List<CustomerCareHistoryDTO> dto) {
        return client.insertOrUpdateListCustomerCareHistory(dto);
    }

    @Override
    public List<String> getSequenseCustomerCareHistory(String seqName, int... size) {
        return client.getSequenseCustomerCareHistory(seqName, size);
    }

    @Override
    public List<CustomerCareHistoryDTO> getListCustomerCareHistoryByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListCustomerCareHistoryByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteCustomerCareHistory(Long id) {
        return client.deleteCustomerCareHistory(id);
    }

}
