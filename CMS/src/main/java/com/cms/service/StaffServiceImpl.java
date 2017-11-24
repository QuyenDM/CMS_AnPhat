
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.StaffDTO;
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
public class StaffServiceImpl implements StaffService {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(StaffServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private StaffService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static StaffServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized StaffServiceImpl getInstance() {
        if (instance == null) {
            instance = new StaffServiceImpl();
        }
        return instance;
    }

    public StaffServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(StaffService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(StaffService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public String updateStaff(StaffDTO appParamsDTO) {
        return client.updateStaff(appParamsDTO);
    }

    @Override
    public String deleteListStaff(List<StaffDTO> appParamsListDTO) {
        return client.deleteListStaff(appParamsListDTO);
    }

    @Override
    public StaffDTO findStaffById(Long id) {
        return client.findStaffById(id);
    }

    @Override
    public List<StaffDTO> getListStaffDTO(StaffDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListStaffDTO(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertStaff(StaffDTO appParamsDTO) {
        return client.insertStaff(appParamsDTO);
    }

    @Override
    public String insertOrUpdateListStaff(List<StaffDTO> appParamsDTO) {
        return client.insertOrUpdateListStaff(appParamsDTO);
    }

    @Override
    public List<String> getSequenseStaff(String seqName, int... size) {
        return client.getSequenseStaff(seqName, size);
    }

    @Override
    public List<StaffDTO> getListStaffByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListStaffByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteStaff(Long id, String username) {
        return client.deleteStaff(id, username);
    }
}
