
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.StatisticStaffPointDTO;
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
public class StatisticStaffPointServiceImpl implements StatisticStaffPointService {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(StatisticStaffPointServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private StatisticStaffPointService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static StatisticStaffPointServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized StatisticStaffPointServiceImpl getInstance() {
        if (instance == null) {
            instance = new StatisticStaffPointServiceImpl();
        }
        return instance;
    }

    public StatisticStaffPointServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(StatisticStaffPointService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(StatisticStaffPointService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public String updateStatisticStaffPoint(StatisticStaffPointDTO appParamsDTO) {
        return client.updateStatisticStaffPoint(appParamsDTO);
    }

    @Override
    public String deleteListStatisticStaffPoint(List<StatisticStaffPointDTO> appParamsListDTO) {
        return client.deleteListStatisticStaffPoint(appParamsListDTO);
    }

    @Override
    public StatisticStaffPointDTO findStatisticStaffPointById(Long id) {
        return client.findStatisticStaffPointById(id);
    }

    @Override
    public List<StatisticStaffPointDTO> getListStatisticStaffPointDTO(StatisticStaffPointDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListStatisticStaffPointDTO(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertStatisticStaffPoint(StatisticStaffPointDTO appParamsDTO) {
        return client.insertStatisticStaffPoint(appParamsDTO);
    }

    @Override
    public String insertOrUpdateListStatisticStaffPoint(List<StatisticStaffPointDTO> appParamsDTO) {
        return client.insertOrUpdateListStatisticStaffPoint(appParamsDTO);
    }

    @Override
    public List<String> getSequenseStatisticStaffPoint(String seqName, int... size) {
        return client.getSequenseStatisticStaffPoint(seqName, size);
    }

    @Override
    public List<StatisticStaffPointDTO> getListStatisticStaffPointByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListStatisticStaffPointByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteStatisticStaffPoint(Long id) {
        return client.deleteStatisticStaffPoint(id);
    }

}
