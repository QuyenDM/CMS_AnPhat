
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.AppParamsDTO;
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
public class AppParamsServiceImpl implements AppParamsService {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AppParamsServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private AppParamsService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static AppParamsServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized AppParamsServiceImpl getInstance() {
        if (instance == null) {
            instance = new AppParamsServiceImpl();
        }
        return instance;
    }

    public AppParamsServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(AppParamsService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(AppParamsService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public String updateAppParams(AppParamsDTO appParamsDTO) {
        return client.updateAppParams(appParamsDTO);
    }

    @Override
    public String deleteAppParams(Long id) {
        return client.deleteAppParams(id);
    }

    @Override
    public String deleteListAppParams(List<AppParamsDTO> appParamsListDTO) {
        return client.deleteListAppParams(appParamsListDTO);
    }

    @Override
    public AppParamsDTO findAppParamsById(Long id) {
        return client.findAppParamsById(id);
    }

    @Override
    public List<AppParamsDTO> getListAppParamsDTO(AppParamsDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListAppParamsDTO(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertAppParams(AppParamsDTO appParamsDTO) {
        return client.insertAppParams(appParamsDTO);
    }

    @Override
    public String insertOrUpdateListAppParams(List<AppParamsDTO> appParamsDTO) {
        return client.insertOrUpdateListAppParams(appParamsDTO);
    }

    @Override
    public List<String> getSequenseAppParams(String seqName, int... size) {
        return client.getSequenseAppParams(seqName, size);
    }

    @Override
    public List<AppParamsDTO> getListAppParamsByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListAppParamsByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public List<AppParamsDTO> getListProviderFromMineName(String mineName, Map<String, String> map) {
        return client.getListProviderFromMineName(mineName, map);
    }
    @Override
    public List<AppParamsDTO> getProviderFromStaffCodeAndConditions(String mineName, Map<String, String> map) {
        return client.getProviderFromStaffCodeAndConditions(mineName, map);
    }

    @Override
    public List<AppParamsDTO> getProviderFromStaffCode(String staffCode, String mineName) {
        return client.getProviderFromStaffCode(staffCode, mineName);
    }
        
    @Override
    public List<AppParamsDTO> getStatusFromStaffCode(String staffCode, String mineName) {
        return client.getStatusFromStaffCode(staffCode, mineName);
    }
}
