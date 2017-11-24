/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.login.ws;

import com.cms.login.dto.RoleObjectsDTO;
import com.cms.service.RoleObjectsServiceImpl;
import com.cms.utils.BundleUtils;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
public class WSRoleObjects {

    List<RoleObjectsDTO> lstRoleObjectsDTO;
    List<RoleObjectsDTO> lstRoleObjectsConditionBean;
    //Duong dan Websevice
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    //Duong dan ten dich
    public static String targetNamePath = "xmlns:cms=\"http://service.cms.com\"";
    //Url WS Stock
    public static String strWSUrl = strWsWMSUrl + "departmentService";

    //Lay toan bo danh sach kho
    public static List<RoleObjectsDTO> getListRoleObjectsDTO(RoleObjectsDTO departmentDTO, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
//        try {
//            //Ten ham lay du lieu kho
//            String functionWS = "cms:getListRoleObjectsDTO";
//            //
//            Map<String, Object> mapRoleObjects = new HashMap<>();
//            //Set cac tham so cau hinh
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//            //Put du lieu tham so dau vao
//            mapRoleObjects.put("departmentDTO", departmentDTO);
//            mapRoleObjects.put("rowStart", rowStart);
//            mapRoleObjects.put("maxRow", maxRow);
//            mapRoleObjects.put("sortType", sortType);
//            mapRoleObjects.put("sortFieldList", sortFieldList);
//            //Set tham so dau vao
//            wsConfig.setBodyArgAlias(mapRoleObjects);
//            //Map doi tuong 
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.addImplicitCollection(parseObject, WSRoleObjects.class, "lstRoleObjectsDTO")
//                    .alias(parseObject, WSRoleObjects.class, "ns2:getListRoleObjectsDTOResponse")
//                    .alias(parseObject, RoleObjectsDTO.class, "ns2:departmentDTO");
//            //Kho tao doc/ghi du lieu tu xml ra Object
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            //Caller WS
//            WebServiceCaller webServiceCaller = new WebServiceCaller();
//            String strResult = webServiceCaller.webServiceCaller(wsConfig);
//            //Handler WS
//            WSRoleObjects wsList = (WSRoleObjects) WebServiceHandler.wsServiceHandler(strResult, xmlStream);
//            //
//            return wsList.lstRoleObjectsDTO;
//
//        } catch (Exception e) {
//            throw e;
//        }
        RoleObjectsServiceImpl departmentService = new RoleObjectsServiceImpl();
        return departmentService.getListRoleObjectsDTO(departmentDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    public static List<RoleObjectsDTO> getListRoleObjectsByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
//        try {
//            //Ten ham lay du lieu kho
//            String functionWS = "cms:getListRoleObjectsByCondition";
//            //
//            Map<String, Object> mapRoleObjects = new HashMap<>();
//            //Set cac tham so cau hinh
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//            //Put du lieu tham so dau vao
//            mapRoleObjects.put("lstCondition", lstCon);
//            mapRoleObjects.put("rowStart", rowStart);
//            mapRoleObjects.put("maxRow", maxRow);
//            mapRoleObjects.put("sortType", sortType);
//            mapRoleObjects.put("sortFieldList", sortFieldList);
//            //Set tham so dau vao
//            wsConfig.setBodyArgAlias(mapRoleObjects);
//            //Map doi tuong 
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.addImplicitCollection(parseObject, WSRoleObjects.class, "lstRoleObjectsConditionBean")
//                    .alias(parseObject, WSRoleObjects.class, "ns2:getListRoleObjectsByConditionResponse")
//                    .alias(parseObject, RoleObjectsDTO.class, "ns2:RoleObjectsDTO");
//            //Kho tao doc/ghi du lieu tu xml ra Object
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            //Caller WS
//            WebServiceCaller webServiceCaller = new WebServiceCaller();
//            String strResult = webServiceCaller.webServiceCaller(wsConfig);
//            //Handler WS
//            WSRoleObjects wsList = (WSRoleObjects) WebServiceHandler.wsServiceHandler(strResult, xmlStream);
//            //
//            return wsList.lstRoleObjectsConditionBean;
//
//        } catch (Exception e) {
//            throw e;
//        }
        RoleObjectsServiceImpl departmentService = new RoleObjectsServiceImpl();
        return departmentService.getListRoleObjectsByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert RoleObjects
    public static ResultDTO insertRoleObjects(RoleObjectsDTO departmentDTO) {
//        try {
//            String functionWS = "cms:insertRoleObjects";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapRoleObjects = new HashMap<>();
//            mapRoleObjects.put("departmentDTO", departmentDTO);
//            wsConfig.setBodyArgAlias(mapRoleObjects);
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
        RoleObjectsServiceImpl departmentService = new RoleObjectsServiceImpl();
        return departmentService.insertRoleObjects(departmentDTO);
    }

    //Update RoleObjects
    public static String updateRoleObjects(RoleObjectsDTO departmentDTO) {
//        try {
//            String functionWS = "cms:updateRoleObjects";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapRoleObjects = new HashMap<>();
//            mapRoleObjects.put("departmentDTO", departmentDTO);
//            wsConfig.setBodyArgAlias(mapRoleObjects);
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
        RoleObjectsServiceImpl departmentService = new RoleObjectsServiceImpl();
        return departmentService.updateRoleObjects(departmentDTO);
    }

    //Delete RoleObjects
    public static String deleteRoleObjects(String id) {
//        try {
//            String functionWS = "cms:deleteRoleObjects";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapRoleObjects = new HashMap<>();
//            mapRoleObjects.put("departmentDTOId", id);
//            wsConfig.setBodyArgAlias(mapRoleObjects);
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
        RoleObjectsServiceImpl departmentService = new RoleObjectsServiceImpl();
        return departmentService.deleteRoleObjects(Long.parseLong(id));
    }

    //find RoleObjects by id
    public static RoleObjectsDTO findRoleObjectsById(String id) {
//        try {
//            String functionWS = "cms:findRoleObjectsById";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapRoleObjects = new HashMap<>();
//            mapRoleObjects.put("departmentDTOId", id);
//            wsConfig.setBodyArgAlias(mapRoleObjects);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, RoleObjectsDTO.class, "ns2:department");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            RoleObjectsDTO departmentDTO = (RoleObjectsDTO) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return departmentDTO;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        RoleObjectsServiceImpl departmentService = new RoleObjectsServiceImpl();
        return departmentService.findRoleObjectsById(Long.parseLong(id));
    }

    // xoa nhieu RoleObjects
    public static String deleteLstRoleObjects(List<RoleObjectsDTO> lstRoleObjectsDTO) {
//        try {
//            String functionWS = "cms:deleteListRoleObjects";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapRoleObjects = new HashMap<>();
//            mapRoleObjects.put("departmentListDTO", lstRoleObjectsDTO);
//            wsConfig.setBodyArgAlias(mapRoleObjects);
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
        RoleObjectsServiceImpl departmentService = new RoleObjectsServiceImpl();
        return departmentService.deleteListRoleObjects(lstRoleObjectsDTO);
    }

    public static String insertOrUpdateListRoleObjects(List<RoleObjectsDTO> lstRoleObjectsDTO) {
        RoleObjectsServiceImpl service = new RoleObjectsServiceImpl();
        return service.insertOrUpdateListRoleObjects(lstRoleObjectsDTO);
    }
}
