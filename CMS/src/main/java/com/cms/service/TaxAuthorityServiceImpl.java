
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.TaxAuthorityDTO;
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
public class TaxAuthorityServiceImpl implements TaxAuthorityService {

    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(TaxAuthorityServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private TaxAuthorityService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static TaxAuthorityServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized TaxAuthorityServiceImpl getInstance() {
        if (instance == null) {
            instance = new TaxAuthorityServiceImpl();
        }
        return instance;
    }

    public TaxAuthorityServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(TaxAuthorityService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(TaxAuthorityService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex);
        }
    }

    @Override
    public String updateTaxAuthority(TaxAuthorityDTO appParamsDTO) {
        return client.updateTaxAuthority(appParamsDTO);
    }

    @Override
    public String deleteListTaxAuthority(List<TaxAuthorityDTO> appParamsListDTO) {
        return client.deleteListTaxAuthority(appParamsListDTO);
    }

    @Override
    public TaxAuthorityDTO findTaxAuthorityById(Long id) {
        return client.findTaxAuthorityById(id);
    }

    @Override
    public List<TaxAuthorityDTO> getListTaxAuthorityDTO(TaxAuthorityDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListTaxAuthorityDTO(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertTaxAuthority(TaxAuthorityDTO appParamsDTO) {
        return client.insertTaxAuthority(appParamsDTO);
    }

    @Override
    public String insertOrUpdateListTaxAuthority(List<TaxAuthorityDTO> appParamsDTO) {
        return client.insertOrUpdateListTaxAuthority(appParamsDTO);
    }

    @Override
    public List<String> getSequenseTaxAuthority(String seqName, int... size) {
        return client.getSequenseTaxAuthority(seqName, size);
    }

    @Override
    public List<TaxAuthorityDTO> getListTaxAuthorityByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListTaxAuthorityByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteTaxAuthority(Long id) {
        return client.deleteTaxAuthority(id);
    }

    @Override
    public List<TaxAuthorityDTO> getListProvineTaxAuthority() {
        return client.getListProvineTaxAuthority();
    }

    @Override
    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineName(String mineName, Map<String, String> map) {
        return client.getListTaxAuthorityFromMineName(mineName, map);
    }

    @Override
    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineNameAndStaffCode(String mineName, String staffCode) {
        return client.getListTaxAuthorityFromMineNameAndStaffCode(mineName, staffCode);
    }

    @Override
    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineNameAndStaffCodeAndProvider(
            String mineName, String staffCode, String provider, String status) {
        return client.getListTaxAuthorityFromMineNameAndStaffCodeAndProvider(
                mineName, staffCode, provider, status);
    }

}
