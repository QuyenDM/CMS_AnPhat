
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.PriceInfoDTO;
import com.cms.utils.BundleUtils;
import java.util.List;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import com.ws.provider.CxfWsClientFactory;
import com.ws.provider.WsEndpoint;
import java.util.HashMap;
import java.util.Map;

/**
 * @author quyendm
 * @version 1.0
 * @since 1/19/2016 11:58 PM
 */
public class PriceInfoServiceImpl implements PriceInfoService {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PriceInfoServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private PriceInfoService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static PriceInfoServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized PriceInfoServiceImpl getInstance() {
        if (instance == null) {
            instance = new PriceInfoServiceImpl();
        }
        return instance;
    }

    public PriceInfoServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(PriceInfoService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(PriceInfoService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public String updatePriceInfo(PriceInfoDTO appParamsDTO) {
        return client.updatePriceInfo(appParamsDTO);
    }

    @Override
    public String deleteListPriceInfo(List<PriceInfoDTO> appParamsListDTO) {
        return client.deleteListPriceInfo(appParamsListDTO);
    }

    @Override
    public PriceInfoDTO findPriceInfoById(Long id) {
        return client.findPriceInfoById(id);
    }

    @Override
    public List<PriceInfoDTO> getListPriceInfoDTO(PriceInfoDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListPriceInfoDTO(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertPriceInfo(PriceInfoDTO appParamsDTO) {
        return client.insertPriceInfo(appParamsDTO);
    }

    @Override
    public String insertOrUpdateListPriceInfo(List<PriceInfoDTO> appParamsDTO) {
        return client.insertOrUpdateListPriceInfo(appParamsDTO);
    }

    @Override
    public List<String> getSequensePriceInfo(String seqName, int... size) {
        return client.getSequensePriceInfo(seqName, size);
    }

    @Override
    public List<PriceInfoDTO> getListPriceInfoByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListPriceInfoByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deletePriceInfo(Long id) {
        return client.deletePriceInfo(id);
    }

}
