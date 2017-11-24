/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.login.ws;

import com.cms.dto.RolesDTO;
import com.cms.service.RolesServiceImpl;
import com.cms.utils.BundleUtils;
import com.vfw5.base.pojo.ConditionBean;
import com.vwf5.base.dto.ResultDTO;
import java.util.List;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
public class WSRoles {

    List<RolesDTO> lstRolesDTO;
    List<RolesDTO> lstRolesConditionBean;
    //Duong dan Websevice
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    //Duong dan ten dich
    public static String targetNamePath = "xmlns:cms=\"http://service.cms.com\"";
    //Url WS Stock
    public static String strWSUrl = strWsWMSUrl + "rolesService";

    //Lay toan bo danh sach kho
    public static List<RolesDTO> getListRolesDTO(RolesDTO rolesDTO, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
//        try {
//            //Ten ham lay du lieu kho
//            String functionWS = "cms:getListRolesDTO";
//            //
//            Map<String, Object> mapRoles = new HashMap<>();
//            //Set cac tham so cau hinh
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//            //Put du lieu tham so dau vao
//            mapRoles.put("rolesDTO", rolesDTO);
//            mapRoles.put("rowStart", rowStart);
//            mapRoles.put("maxRow", maxRow);
//            mapRoles.put("sortType", sortType);
//            mapRoles.put("sortFieldList", sortFieldList);
//            //Set tham so dau vao
//            wsConfig.setBodyArgAlias(mapRoles);
//            //Map doi tuong 
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.addImplicitCollection(parseObject, WSRoles.class, "lstRolesDTO")
//                    .alias(parseObject, WSRoles.class, "ns2:getListRolesDTOResponse")
//                    .alias(parseObject, RolesDTO.class, "ns2:rolesDTO");
//            //Kho tao doc/ghi du lieu tu xml ra Object
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            //Caller WS
//            WebServiceCaller webServiceCaller = new WebServiceCaller();
//            String strResult = webServiceCaller.webServiceCaller(wsConfig);
//            //Handler WS
//            WSRoles wsList = (WSRoles) WebServiceHandler.wsServiceHandler(strResult, xmlStream);
//            //
//            return wsList.lstRolesDTO;
//
//        } catch (Exception e) {
//            throw e;
//        }
        RolesServiceImpl rolesService = new RolesServiceImpl();
        return rolesService.getListRolesDTO(rolesDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    public static List<RolesDTO> getListRolesByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
//        try {
//            //Ten ham lay du lieu kho
//            String functionWS = "cms:getListRolesByCondition";
//            //
//            Map<String, Object> mapRoles = new HashMap<>();
//            //Set cac tham so cau hinh
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//            //Put du lieu tham so dau vao
//            mapRoles.put("lstCondition", lstCon);
//            mapRoles.put("rowStart", rowStart);
//            mapRoles.put("maxRow", maxRow);
//            mapRoles.put("sortType", sortType);
//            mapRoles.put("sortFieldList", sortFieldList);
//            //Set tham so dau vao
//            wsConfig.setBodyArgAlias(mapRoles);
//            //Map doi tuong 
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.addImplicitCollection(parseObject, WSRoles.class, "lstRolesConditionBean")
//                    .alias(parseObject, WSRoles.class, "ns2:getListRolesByConditionResponse")
//                    .alias(parseObject, RolesDTO.class, "ns2:RolesDTO");
//            //Kho tao doc/ghi du lieu tu xml ra Object
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            //Caller WS
//            WebServiceCaller webServiceCaller = new WebServiceCaller();
//            String strResult = webServiceCaller.webServiceCaller(wsConfig);
//            //Handler WS
//            WSRoles wsList = (WSRoles) WebServiceHandler.wsServiceHandler(strResult, xmlStream);
//            //
//            return wsList.lstRolesConditionBean;
//
//        } catch (Exception e) {
//            throw e;
//        }
        RolesServiceImpl rolesService = new RolesServiceImpl();
        return rolesService.getListRolesByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert Roles
    public static ResultDTO insertRoles(RolesDTO rolesDTO) {
//        try {
//            String functionWS = "cms:insertRoles";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapRoles = new HashMap<>();
//            mapRoles.put("rolesDTO", rolesDTO);
//            wsConfig.setBodyArgAlias(mapRoles);
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
        RolesServiceImpl rolesService = new RolesServiceImpl();
        return rolesService.insertRoles(rolesDTO);
    }

    //Update Roles
    public static String updateRoles(RolesDTO rolesDTO) {
//        try {
//            String functionWS = "cms:updateRoles";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapRoles = new HashMap<>();
//            mapRoles.put("rolesDTO", rolesDTO);
//            wsConfig.setBodyArgAlias(mapRoles);
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
        RolesServiceImpl rolesService = new RolesServiceImpl();
        return rolesService.updateRoles(rolesDTO);
    }

    //Delete Roles
    public static String deleteRoles(String id) {
//        try {
//            String functionWS = "cms:deleteRoles";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapRoles = new HashMap<>();
//            mapRoles.put("rolesDTOId", id);
//            wsConfig.setBodyArgAlias(mapRoles);
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
        RolesServiceImpl rolesService = new RolesServiceImpl();
        return rolesService.deleteRoles(Long.parseLong(id));
    }

    //find Roles by id
    public static RolesDTO findRolesById(String id) {
//        try {
//            String functionWS = "cms:findRolesById";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapRoles = new HashMap<>();
//            mapRoles.put("rolesDTOId", id);
//            wsConfig.setBodyArgAlias(mapRoles);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, RolesDTO.class, "ns2:roles");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            RolesDTO rolesDTO = (RolesDTO) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return rolesDTO;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        RolesServiceImpl rolesService = new RolesServiceImpl();
        return rolesService.findRolesById(Long.parseLong(id));
    }

    // xoa nhieu Roles
    public static String deleteLstRoles(List<RolesDTO> lstRolesDTO) {
//        try {
//            String functionWS = "cms:deleteListRoles";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapRoles = new HashMap<>();
//            mapRoles.put("rolesListDTO", lstRolesDTO);
//            wsConfig.setBodyArgAlias(mapRoles);
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
        RolesServiceImpl rolesService = new RolesServiceImpl();
        return rolesService.deleteListRoles(lstRolesDTO);
    }

    public static List<RolesDTO> getListRolesByStaffId(String staffId) {
        RolesServiceImpl rolesService = new RolesServiceImpl();
        return rolesService.getListRolesByStaffId(staffId);
    }
}
