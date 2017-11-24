/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.common.ws;

import com.cms.dto.AppParamsDTO;
import com.cms.service.AppParamsServiceImpl;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;
import java.util.Map;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
public class WSAppParams {

    //Lay toan bo danh sach kho
    public static List<AppParamsDTO> getListAppParamsDTO(AppParamsDTO appParamsDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        AppParamsServiceImpl appParamsService = new AppParamsServiceImpl();
        return appParamsService.getListAppParamsDTO(appParamsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    public static List<AppParamsDTO> getListAppParamsByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        AppParamsServiceImpl appParamsService = new AppParamsServiceImpl();
        return appParamsService.getListAppParamsByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert AppParams
    public static ResultDTO insertAppParams(AppParamsDTO appParamsDTO) {
//        try {
//            String functionWS = "cms:insertAppParams";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapAppParams = new HashMap<>();
//            mapAppParams.put("appParamsDTO", appParamsDTO);
//            wsConfig.setBodyArgAlias(mapAppParams);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, ResultDTO.class, "ns2:resultDTO");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            ResultDTO message = (ResultDTO) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return message;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        AppParamsServiceImpl appParamsService = new AppParamsServiceImpl();
        return appParamsService.insertAppParams(appParamsDTO);
    }

    //Update AppParams
    public static String updateAppParams(AppParamsDTO appParamsDTO) {
//        try {
//            String functionWS = "cms:updateAppParams";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapAppParams = new HashMap<>();
//            mapAppParams.put("appParamsDTO", appParamsDTO);
//            wsConfig.setBodyArgAlias(mapAppParams);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, String.class, "ns2:message");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            String message = (String) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return message;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "FAIL";
//        }
        AppParamsServiceImpl appParamsService = new AppParamsServiceImpl();
        return appParamsService.updateAppParams(appParamsDTO);
    }

    //Delete AppParams
    public static String deleteAppParams(String id) {
//        try {
//            String functionWS = "cms:deleteAppParams";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapAppParams = new HashMap<>();
//            mapAppParams.put("appParamsDTOId", id);
//            wsConfig.setBodyArgAlias(mapAppParams);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, String.class, "ns2:message");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            String message = (String) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return message;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "FAIL";
//        }
        AppParamsServiceImpl appParamsService = new AppParamsServiceImpl();
        return appParamsService.deleteAppParams(Long.parseLong(id));
    }

    //find AppParams by id
    public static AppParamsDTO findAppParamsById(String id) {
//        try {
//            String functionWS = "cms:findAppParamsById";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapAppParams = new HashMap<>();
//            mapAppParams.put("appParamsDTOId", id);
//            wsConfig.setBodyArgAlias(mapAppParams);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, AppParamsDTO.class, "ns2:appParams");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            AppParamsDTO appParamsDTO = (AppParamsDTO) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return appParamsDTO;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        AppParamsServiceImpl appParamsService = new AppParamsServiceImpl();
        return appParamsService.findAppParamsById(Long.parseLong(id));
    }

    // xoa nhieu AppParams
    public static String deleteLstAppParams(List<AppParamsDTO> lstAppParamsDTO) {
//        try {
//            String functionWS = "cms:deleteListAppParams";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapAppParams = new HashMap<>();
//            mapAppParams.put("appParamsListDTO", lstAppParamsDTO);
//            wsConfig.setBodyArgAlias(mapAppParams);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, String.class, "ns2:message");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            String message = (String) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return message;
//        } catch (Exception e) {
//
//            e.printStackTrace();
//            return "FAIL";
//        }
        AppParamsServiceImpl appParamsService = new AppParamsServiceImpl();
        return appParamsService.deleteListAppParams(lstAppParamsDTO);
    }

    public static List<AppParamsDTO> getListProviderFromMineName(String mineName, Map<String, String> map) {
        AppParamsServiceImpl appParamsService = new AppParamsServiceImpl();
        return appParamsService.getListProviderFromMineName(mineName, map);
    }
    
    public static List<AppParamsDTO> getProviderFromStaffCodeAndConditions(String mineName, Map<String, String> map) {
        AppParamsServiceImpl appParamsService = new AppParamsServiceImpl();
        return appParamsService.getProviderFromStaffCodeAndConditions(mineName, map);
    }

    public static List<AppParamsDTO> getProviderFromStaffCode(String staffCode, String mineName) {
        AppParamsServiceImpl appParamsService = new AppParamsServiceImpl();
        return appParamsService.getProviderFromStaffCode(staffCode, mineName);
    }
    
    public static List<AppParamsDTO> getStatusFromStaffCode(String staffCode, String mineName) {
        AppParamsServiceImpl appParamsService = new AppParamsServiceImpl();
        return appParamsService.getStatusFromStaffCode(staffCode, mineName);
    }
}
