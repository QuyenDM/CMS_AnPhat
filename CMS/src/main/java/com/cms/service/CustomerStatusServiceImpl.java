
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.CustomerStatusDTO;
import com.cms.dto.StatisticsCategoryListDTO;
import com.cms.dto.StatusQuantityDTO;
import com.cms.utils.BundleUtils;
import java.util.List;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import com.ws.provider.CxfWsClientFactory;
import com.ws.provider.WsEndpoint;
import java.util.HashMap;
import java.util.Map;
import javax.jws.WebParam;

/**
 * @author thieulq1
 * @version 1.0
 * @since 1/19/2016 11:58 PM
 */
public class CustomerStatusServiceImpl implements CustomerStatusService {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CustomerStatusServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private CustomerStatusService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static CustomerStatusServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized CustomerStatusServiceImpl getInstance() {
        if (instance == null) {
            instance = new CustomerStatusServiceImpl();
        }
        return instance;
    }

    public CustomerStatusServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(CustomerStatusService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(CustomerStatusService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public String updateCustomerStatus(CustomerStatusDTO customerStatus) {
        return client.updateCustomerStatus(customerStatus);
    }

    @Override
    public String deleteListCustomerStatus(List<CustomerStatusDTO> appParamsListDTO) {
        return client.deleteListCustomerStatus(appParamsListDTO);
    }

    @Override
    public CustomerStatusDTO findCustomerStatusById(Long id) {
        return client.findCustomerStatusById(id);
    }

    @Override
    public List<CustomerStatusDTO> getListCustomerStatusDTO(CustomerStatusDTO customerStatus, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListCustomerStatusDTO(customerStatus, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertCustomerStatus(CustomerStatusDTO customerStatus) {
        return client.insertCustomerStatus(customerStatus);
    }

    @Override
    public String insertOrUpdateListCustomerStatus(List<CustomerStatusDTO> customerStatus) {
        return client.insertOrUpdateListCustomerStatus(customerStatus);
    }

    @Override
    public List<String> getSequenseCustomerStatus(String seqName, int... size) {
        return client.getSequenseCustomerStatus(seqName, size);
    }

    @Override
    public List<CustomerStatusDTO> getListCustomerStatusByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListCustomerStatusByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteCustomerStatus(Long id) {
        return client.deleteCustomerStatus(id);
    }

    @Override
    public List<StatisticsCategoryListDTO> getStatisticsCategoryListByStaff(String service, String staffCode, String categoryId, String beginLastUpdated, String endLastUpdated) {
        return client.getStatisticsCategoryListByStaff(service, staffCode, categoryId, beginLastUpdated, endLastUpdated);
    }

    @Override
    public List<StatusQuantityDTO> getStatusQuantity(String staffCode){
        return client.getStatusQuantity(staffCode);
    }
    
}
