
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.MapStaffCustomerDTO;
import com.cms.utils.BundleUtils;
import java.util.List;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import com.ws.provider.CxfWsClientFactory;
import com.ws.provider.WsEndpoint;
import java.util.HashMap;
import java.util.Map;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 1/19/2016 11:58 PM
 */
public class MapStaffCustomerServiceImpl implements MapStaffCustomerService {

    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(MapStaffCustomerServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private MapStaffCustomerService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static MapStaffCustomerServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized MapStaffCustomerServiceImpl getInstance() {
        if (instance == null) {
            instance = new MapStaffCustomerServiceImpl();
        }
        return instance;
    }

    public MapStaffCustomerServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(MapStaffCustomerService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(MapStaffCustomerService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOG.error(ex);
        }
    }

    @Override
    public String updateMapStaffCustomer(MapStaffCustomerDTO mapStaffCustomer) {
        return client.updateMapStaffCustomer(mapStaffCustomer);
    }

    @Override
    public String deleteListMapStaffCustomer(List<MapStaffCustomerDTO> appParamsListDTO) {
        return client.deleteListMapStaffCustomer(appParamsListDTO);
    }

    @Override
    public MapStaffCustomerDTO findMapStaffCustomerById(Long id) {
        return client.findMapStaffCustomerById(id);
    }

    @Override
    public List<MapStaffCustomerDTO> getListMapStaffCustomerDTO(MapStaffCustomerDTO mapStaffCustomer, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListMapStaffCustomerDTO(mapStaffCustomer, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertMapStaffCustomer(MapStaffCustomerDTO mapStaffCustomer) {
        return client.insertMapStaffCustomer(mapStaffCustomer);
    }

    @Override
    public String insertOrUpdateListMapStaffCustomer(List<MapStaffCustomerDTO> mapStaffCustomer) {
        return client.insertOrUpdateListMapStaffCustomer(mapStaffCustomer);
    }

    @Override
    public List<String> getSequenseMapStaffCustomer(String seqName, int... size) {
        return client.getSequenseMapStaffCustomer(seqName, size);
    }

    @Override
    public List<MapStaffCustomerDTO> getListMapStaffCustomerByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListMapStaffCustomerByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteMapStaffCustomer(Long id) {
        return client.deleteMapStaffCustomer(id);
    }

}
