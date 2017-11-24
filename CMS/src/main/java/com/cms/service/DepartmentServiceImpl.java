
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.DepartmentDTO;
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
public class DepartmentServiceImpl implements DepartmentService {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DepartmentServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private DepartmentService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static DepartmentServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized DepartmentServiceImpl getInstance() {
        if (instance == null) {
            instance = new DepartmentServiceImpl();
        }
        return instance;
    }

    public DepartmentServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(DepartmentService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(DepartmentService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public String updateDepartment(DepartmentDTO appParamsDTO) {
        return client.updateDepartment(appParamsDTO);
    }

    @Override
    public String deleteListDepartment(List<DepartmentDTO> appParamsListDTO) {
        return client.deleteListDepartment(appParamsListDTO);
    }

    @Override
    public DepartmentDTO findDepartmentById(Long id) {
        return client.findDepartmentById(id);
    }

    @Override
    public List<DepartmentDTO> getListDepartmentDTO(DepartmentDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListDepartmentDTO(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertDepartment(DepartmentDTO appParamsDTO) {
        return client.insertDepartment(appParamsDTO);
    }

    @Override
    public String insertOrUpdateListDepartment(List<DepartmentDTO> appParamsDTO) {
        return client.insertOrUpdateListDepartment(appParamsDTO);
    }

    @Override
    public List<String> getSequenseDepartment(String seqName, int... size) {
        return client.getSequenseDepartment(seqName, size);
    }

    @Override
    public List<DepartmentDTO> getListDepartmentByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListDepartmentByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteDepartment(Long id, String username) {
        return client.deleteDepartment(id, username);
    }
}
