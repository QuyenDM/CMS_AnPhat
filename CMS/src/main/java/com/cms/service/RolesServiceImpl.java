
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.RolesDTO;
import com.cms.utils.BundleUtils;
import java.util.List;
import com.vwf5.base.dto.ResultDTO;
import com.vfw5.base.pojo.ConditionBean;
import com.ws.provider.CxfWsClientFactory;
import com.ws.provider.WsEndpoint;
import java.util.HashMap;
import java.util.Map;

/**
 * @author thieulq1
 * @version 1.0
 * @since 1/19/2016 11:58 PM
 */
public class RolesServiceImpl implements RolesService {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RolesServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private RolesService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static RolesServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized RolesServiceImpl getInstance() {
        if (instance == null) {
            instance = new RolesServiceImpl();
        }
        return instance;
    }

    public RolesServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(RolesService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(RolesService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public String updateRoles(RolesDTO appParamsDTO) {
        return client.updateRoles(appParamsDTO);
    }

    @Override
    public String deleteListRoles(List<RolesDTO> appParamsListDTO) {
        return client.deleteListRoles(appParamsListDTO);
    }

    @Override
    public RolesDTO findRolesById(Long id) {
        return client.findRolesById(id);
    }

    @Override
    public List<RolesDTO> getListRolesDTO(RolesDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListRolesDTO(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertRoles(RolesDTO appParamsDTO) {
        return client.insertRoles(appParamsDTO);
    }

    @Override
    public String insertOrUpdateListRoles(List<RolesDTO> appParamsDTO) {
        return client.insertOrUpdateListRoles(appParamsDTO);
    }

    @Override
    public List<String> getSequenseRoles(String seqName, int... size) {
        return client.getSequenseRoles(seqName, size);
    }

    @Override
    public List<RolesDTO> getListRolesByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListRolesByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteRoles(Long id) {
        return client.deleteRoles(id);
    }

    @Override
    public List<RolesDTO> getListRolesByStaffId(String staffId) {
        return client.getListRolesByStaffId(staffId);
    }

}
