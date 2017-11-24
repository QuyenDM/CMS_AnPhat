/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.login.ws;

import com.cms.dto.RolesDTO;
import com.cms.login.dto.ObjectsDTO;
import com.cms.service.ObjectsServiceImpl;
import com.cms.utils.BundleUtils;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
public class WSObjects {

    List<ObjectsDTO> lstObjectsDTO;
    List<ObjectsDTO> lstObjectsConditionBean;
    //Duong dan Websevice
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    //Duong dan ten dich
    public static String targetNamePath = "xmlns:cms=\"http://service.cms.com\"";
    //Url WS Stock
    public static String strWSUrl = strWsWMSUrl + "departmentService";

    //Lay toan bo danh sach kho
    public static List<ObjectsDTO> getListObjectsDTO(ObjectsDTO departmentDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
//        try {
//            //Ten ham lay du lieu kho
//            String functionWS = "cms:getListObjectsDTO";
//            //
//            Map<String, Object> mapObjects = new HashMap<>();
//            //Set cac tham so cau hinh
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//            //Put du lieu tham so dau vao
//            mapObjects.put("departmentDTO", departmentDTO);
//            mapObjects.put("rowStart", rowStart);
//            mapObjects.put("maxRow", maxRow);
//            mapObjects.put("sortType", sortType);
//            mapObjects.put("sortFieldList", sortFieldList);
//            //Set tham so dau vao
//            wsConfig.setBodyArgAlias(mapObjects);
//            //Map doi tuong 
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.addImplicitCollection(parseObject, WSObjects.class, "lstObjectsDTO")
//                    .alias(parseObject, WSObjects.class, "ns2:getListObjectsDTOResponse")
//                    .alias(parseObject, ObjectsDTO.class, "ns2:departmentDTO");
//            //Kho tao doc/ghi du lieu tu xml ra Object
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            //Caller WS
//            WebServiceCaller webServiceCaller = new WebServiceCaller();
//            String strResult = webServiceCaller.webServiceCaller(wsConfig);
//            //Handler WS
//            WSObjects wsList = (WSObjects) WebServiceHandler.wsServiceHandler(strResult, xmlStream);
//            //
//            return wsList.lstObjectsDTO;
//
//        } catch (Exception e) {
//            throw e;
//        }
        ObjectsServiceImpl departmentService = new ObjectsServiceImpl();
        return departmentService.getListObjectsDTO(departmentDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    public static List<ObjectsDTO> getListObjectsByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
//        try {
//            //Ten ham lay du lieu kho
//            String functionWS = "cms:getListObjectsByCondition";
//            //
//            Map<String, Object> mapObjects = new HashMap<>();
//            //Set cac tham so cau hinh
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//            //Put du lieu tham so dau vao
//            mapObjects.put("lstCondition", lstCon);
//            mapObjects.put("rowStart", rowStart);
//            mapObjects.put("maxRow", maxRow);
//            mapObjects.put("sortType", sortType);
//            mapObjects.put("sortFieldList", sortFieldList);
//            //Set tham so dau vao
//            wsConfig.setBodyArgAlias(mapObjects);
//            //Map doi tuong 
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.addImplicitCollection(parseObject, WSObjects.class, "lstObjectsConditionBean")
//                    .alias(parseObject, WSObjects.class, "ns2:getListObjectsByConditionResponse")
//                    .alias(parseObject, ObjectsDTO.class, "ns2:ObjectsDTO");
//            //Kho tao doc/ghi du lieu tu xml ra Object
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            //Caller WS
//            WebServiceCaller webServiceCaller = new WebServiceCaller();
//            String strResult = webServiceCaller.webServiceCaller(wsConfig);
//            //Handler WS
//            WSObjects wsList = (WSObjects) WebServiceHandler.wsServiceHandler(strResult, xmlStream);
//            //
//            return wsList.lstObjectsConditionBean;
//
//        } catch (Exception e) {
//            throw e;
//        }
        ObjectsServiceImpl departmentService = new ObjectsServiceImpl();
        return departmentService.getListObjectsByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert Objects
    public static ResultDTO insertObjects(ObjectsDTO departmentDTO) {
//        try {
//            String functionWS = "cms:insertObjects";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapObjects = new HashMap<>();
//            mapObjects.put("departmentDTO", departmentDTO);
//            wsConfig.setBodyArgAlias(mapObjects);
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
        ObjectsServiceImpl departmentService = new ObjectsServiceImpl();
        return departmentService.insertObjects(departmentDTO);
    }

    //Update Objects
    public static String updateObjects(ObjectsDTO departmentDTO) {
//        try {
//            String functionWS = "cms:updateObjects";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapObjects = new HashMap<>();
//            mapObjects.put("departmentDTO", departmentDTO);
//            wsConfig.setBodyArgAlias(mapObjects);
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
        ObjectsServiceImpl departmentService = new ObjectsServiceImpl();
        return departmentService.updateObjects(departmentDTO);
    }

    //Delete Objects
    public static String deleteObjects(String id) {
//        try {
//            String functionWS = "cms:deleteObjects";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapObjects = new HashMap<>();
//            mapObjects.put("departmentDTOId", id);
//            wsConfig.setBodyArgAlias(mapObjects);
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
        ObjectsServiceImpl departmentService = new ObjectsServiceImpl();
        return departmentService.deleteObjects(Long.parseLong(id));
    }

    //find Objects by id
    public static ObjectsDTO findObjectsById(String id) {
//        try {
//            String functionWS = "cms:findObjectsById";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapObjects = new HashMap<>();
//            mapObjects.put("departmentDTOId", id);
//            wsConfig.setBodyArgAlias(mapObjects);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, ObjectsDTO.class, "ns2:department");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            ObjectsDTO departmentDTO = (ObjectsDTO) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return departmentDTO;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        ObjectsServiceImpl departmentService = new ObjectsServiceImpl();
        return departmentService.findObjectsById(Long.parseLong(id));
    }

    // xoa nhieu Objects
    public static String deleteLstObjects(List<ObjectsDTO> lstObjectsDTO) {
//        try {
//            String functionWS = "cms:deleteListObjects";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapObjects = new HashMap<>();
//            mapObjects.put("departmentListDTO", lstObjectsDTO);
//            wsConfig.setBodyArgAlias(mapObjects);
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
        ObjectsServiceImpl departmentService = new ObjectsServiceImpl();
        return departmentService.deleteListObjects(lstObjectsDTO);
    }

    public static List<ObjectsDTO> getListObjectByRole(RolesDTO roles) {
        ObjectsServiceImpl departmentService = new ObjectsServiceImpl();
        return departmentService.getListObjectByRole(roles);

    }

    public static List<ObjectsDTO> getListObjectDTOByStaffId(String staffId) {
        ObjectsServiceImpl departmentService = new ObjectsServiceImpl();
        return departmentService.getListObjectDTOByStaffId(staffId);
    }
}
