
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.TermInformationDTO;
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
public class TermInformationServiceImpl implements TermInformationService {

    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(TermInformationServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private TermInformationService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
//    public static String timeOut = BundleUtils.getStringCas("timeOut");
    public static String timeOut = "360000";

    private static TermInformationServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized TermInformationServiceImpl getInstance() {
        if (instance == null) {
            instance = new TermInformationServiceImpl();
        }
        return instance;
    }

    public TermInformationServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
//            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(TermInformationService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
//            client = wsClientFactory.createWsClient(TermInformationService.class);
            client = wsClientFactory.createWsClient(TermInformationService.class, 0);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex);
        }
    }

    @Override
    public String updateTermInformation(TermInformationDTO appParamsDTO) {
        return client.updateTermInformation(appParamsDTO);
    }

    @Override
    public String deleteListTermInformation(List<TermInformationDTO> appParamsListDTO) {
        return client.deleteListTermInformation(appParamsListDTO);
    }

    @Override
    public TermInformationDTO findTermInformationById(Long id) {
        return client.findTermInformationById(id);
    }

    @Override
    public List<TermInformationDTO> getListTermInformationDTO(TermInformationDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListTermInformationDTO(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertTermInformation(TermInformationDTO appParamsDTO) {
        return client.insertTermInformation(appParamsDTO);
    }

    @Override
    public String insertOrUpdateListTermInformation(List<TermInformationDTO> appParamsDTO) {
        return client.insertOrUpdateListTermInformation(appParamsDTO);
    }

    @Override
    public List<String> getSequenseTermInformation(String seqName, int... size) {
        return client.getSequenseTermInformation(seqName, size);
    }

    @Override
    public List<TermInformationDTO> getListTermInformationByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListTermInformationByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteTermInformation(Long id) {
        return client.deleteTermInformation(id);
    }

    @Override
    public ResultDTO insertListTermInformation(List<TermInformationDTO> termInformationDTO) {
        return client.insertListTermInformation(termInformationDTO);
    }
}
