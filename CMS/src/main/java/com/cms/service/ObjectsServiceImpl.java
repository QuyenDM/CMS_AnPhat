
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.RolesDTO;
import com.cms.login.dto.ObjectsDTO;
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
public class ObjectsServiceImpl implements ObjectsService {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ObjectsServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private ObjectsService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static ObjectsServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized ObjectsServiceImpl getInstance() {
        if (instance == null) {
            instance = new ObjectsServiceImpl();
        }
        return instance;
    }

    public ObjectsServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(ObjectsService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(ObjectsService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public String updateObjects(ObjectsDTO appParamsDTO) {
        return client.updateObjects(appParamsDTO);
    }

    @Override
    public String deleteListObjects(List<ObjectsDTO> appParamsListDTO) {
        return client.deleteListObjects(appParamsListDTO);
    }

    @Override
    public ObjectsDTO findObjectsById(Long id) {
        return client.findObjectsById(id);
    }

    @Override
    public List<ObjectsDTO> getListObjectsDTO(ObjectsDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListObjectsDTO(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertObjects(ObjectsDTO appParamsDTO) {
        return client.insertObjects(appParamsDTO);
    }

    @Override
    public String insertOrUpdateListObjects(List<ObjectsDTO> appParamsDTO) {
        return client.insertOrUpdateListObjects(appParamsDTO);
    }

    @Override
    public List<String> getSequenseObjects(String seqName, int... size) {
        return client.getSequenseObjects(seqName, size);
    }

    @Override
    public List<ObjectsDTO> getListObjectsByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListObjectsByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteObjects(Long id) {
        return client.deleteObjects(id);
    }

    @Override
    public List<ObjectsDTO> getListObjectDTOByStaffId(String staffId) {
        return client.getListObjectDTOByStaffId(staffId);
    }

    @Override
    public List<ObjectsDTO> getListObjectByRole(RolesDTO roles) {
        return client.getListObjectByRole(roles);
    }

}
