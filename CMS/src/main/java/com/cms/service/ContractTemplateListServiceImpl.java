
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.ContractTemplateListDTO;
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
public class ContractTemplateListServiceImpl implements ContractTemplateListService {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ContractTemplateListServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private ContractTemplateListService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static ContractTemplateListServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized ContractTemplateListServiceImpl getInstance() {
        if (instance == null) {
            instance = new ContractTemplateListServiceImpl();
        }
        return instance;
    }

    public ContractTemplateListServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(ContractTemplateListService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(ContractTemplateListService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public String updateContractTemplateList(ContractTemplateListDTO appParamsDTO) {
        return client.updateContractTemplateList(appParamsDTO);
    }

    @Override
    public String deleteListContractTemplateList(List<ContractTemplateListDTO> appParamsListDTO) {
        return client.deleteListContractTemplateList(appParamsListDTO);
    }

    @Override
    public ContractTemplateListDTO findContractTemplateListById(Long id) {
        return client.findContractTemplateListById(id);
    }

    @Override
    public List<ContractTemplateListDTO> getListContractTemplateListDTO(ContractTemplateListDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListContractTemplateListDTO(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertContractTemplateList(ContractTemplateListDTO appParamsDTO) {
        return client.insertContractTemplateList(appParamsDTO);
    }

    @Override
    public String insertOrUpdateListContractTemplateList(List<ContractTemplateListDTO> appParamsDTO) {
        return client.insertOrUpdateListContractTemplateList(appParamsDTO);
    }

    @Override
    public List<String> getSequenseContractTemplateList(String seqName, int... size) {
        return client.getSequenseContractTemplateList(seqName, size);
    }

    @Override
    public List<ContractTemplateListDTO> getListContractTemplateListByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListContractTemplateListByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteContractTemplateList(Long id) {
        return client.deleteContractTemplateList(id);
    }

}
