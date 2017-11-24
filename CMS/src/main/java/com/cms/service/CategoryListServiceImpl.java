
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.CategoryListDTO;
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
public class CategoryListServiceImpl implements CategoryListService {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CategoryListServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private CategoryListService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static CategoryListServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized CategoryListServiceImpl getInstance() {
        if (instance == null) {
            instance = new CategoryListServiceImpl();
        }
        return instance;
    }

    public CategoryListServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(CategoryListService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(CategoryListService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public String updateCategoryList(CategoryListDTO appParamsDTO) {
        return client.updateCategoryList(appParamsDTO);
    }

    @Override
    public String deleteListCategoryList(List<CategoryListDTO> appParamsListDTO) {
        return client.deleteListCategoryList(appParamsListDTO);
    }

    @Override
    public CategoryListDTO findCategoryListById(Long id) {
        return client.findCategoryListById(id);
    }

    @Override
    public List<CategoryListDTO> getListCategoryListDTO(CategoryListDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListCategoryListDTO(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertCategoryList(CategoryListDTO appParamsDTO) {
        return client.insertCategoryList(appParamsDTO);
    }

    @Override
    public String insertOrUpdateListCategoryList(List<CategoryListDTO> appParamsDTO) {
        return client.insertOrUpdateListCategoryList(appParamsDTO);
    }

    @Override
    public List<String> getSequenseCategoryList(String seqName, int... size) {
        return client.getSequenseCategoryList(seqName, size);
    }

    @Override
    public List<CategoryListDTO> getListCategoryListByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListCategoryListByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String deleteCategoryList(Long id) {
        return client.deleteCategoryList(id);
    }

    @Override
    public List<CategoryListDTO> getCategoryListFromStaffCode(String staffCode, String service) {
        return client.getCategoryListFromStaffCode(staffCode, service);
    }

}
