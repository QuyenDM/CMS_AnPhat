
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.login.dto.RoleObjectsDTO;
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
public class RoleObjectsServiceImpl implements RoleObjectsService {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RoleObjectsServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private RoleObjectsService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static RoleObjectsServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized RoleObjectsServiceImpl getInstance() {
        if (instance == null) {
            instance = new RoleObjectsServiceImpl();
        }
        return instance;
    }

    public RoleObjectsServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(RoleObjectsService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(RoleObjectsService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public String updateRoleObjects(RoleObjectsDTO appParamsDTO) {
        return client.updateRoleObjects(appParamsDTO);
    }

    @Override
    public String deleteListRoleObjects(List<RoleObjectsDTO> appParamsListDTO) {
        return client.deleteListRoleObjects(appParamsListDTO);
    }

    @Override
    public RoleObjectsDTO findRoleObjectsById(Long id) {
        return client.findRoleObjectsById(id);
    }

    @Override
    public List<RoleObjectsDTO> getListRoleObjectsDTO(RoleObjectsDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListRoleObjectsDTO(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertRoleObjects(RoleObjectsDTO appParamsDTO) {
        return client.insertRoleObjects(appParamsDTO);
    }

    @Override
    public String insertOrUpdateListRoleObjects(List<RoleObjectsDTO> appParamsDTO) {
        return client.insertOrUpdateListRoleObjects(appParamsDTO);
    }

    @Override
    public List<String> getSequenseRoleObjects(String seqName, int... size) {
        return client.getSequenseRoleObjects(seqName, size);
    }

    @Override
    public List<RoleObjectsDTO> getListRoleObjectsByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListRoleObjectsByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteRoleObjects(Long id) {
        return client.deleteRoleObjects(id);
    }
}
