
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.login.dto.MapStaffRolesDTO;
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
public class MapStaffRolesServiceImpl implements MapStaffRolesService {

    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(MapStaffRolesServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private MapStaffRolesService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static MapStaffRolesServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized MapStaffRolesServiceImpl getInstance() {
        if (instance == null) {
            instance = new MapStaffRolesServiceImpl();
        }
        return instance;
    }

    public MapStaffRolesServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(MapStaffRolesService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(MapStaffRolesService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOG.error(ex);
        }
    }

    @Override
    public String updateMapStaffRoles(MapStaffRolesDTO mapStaffRoles) {
        return client.updateMapStaffRoles(mapStaffRoles);
    }

    @Override
    public String deleteListMapStaffRoles(List<MapStaffRolesDTO> appParamsListDTO) {
        return client.deleteListMapStaffRoles(appParamsListDTO);
    }

    @Override
    public MapStaffRolesDTO findMapStaffRolesById(Long id) {
        return client.findMapStaffRolesById(id);
    }

    @Override
    public List<MapStaffRolesDTO> getListMapStaffRolesDTO(MapStaffRolesDTO mapStaffRoles, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListMapStaffRolesDTO(mapStaffRoles, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertMapStaffRoles(MapStaffRolesDTO mapStaffRoles) {
        return client.insertMapStaffRoles(mapStaffRoles);
    }

    @Override
    public String insertOrUpdateListMapStaffRoles(List<MapStaffRolesDTO> mapStaffRoles) {
        return client.insertOrUpdateListMapStaffRoles(mapStaffRoles);
    }

    @Override
    public List<String> getSequenseMapStaffRoles(String seqName, int... size) {
        return client.getSequenseMapStaffRoles(seqName, size);
    }

    @Override
    public List<MapStaffRolesDTO> getListMapStaffRolesByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListMapStaffRolesByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteMapStaffRoles(Long id) {
        return client.deleteMapStaffRoles(id);
    }

    

}
