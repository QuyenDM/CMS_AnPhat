/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.login.ws;

import com.cms.dto.DepartmentDTO;
import com.cms.service.DepartmentServiceImpl;
import com.cms.utils.BundleUtils;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
public class WSDepartment {

    List<DepartmentDTO> lstDepartmentDTO;
    List<DepartmentDTO> lstDepartmentConditionBean;
    //Duong dan Websevice
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    //Duong dan ten dich
    public static String targetNamePath = "xmlns:cms=\"http://service.cms.com\"";
    //Url WS Stock
    public static String strWSUrl = strWsWMSUrl + "departmentService";

    //Lay toan bo danh sach kho
    public static List<DepartmentDTO> getListDepartmentDTO(DepartmentDTO departmentDTO, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
//        try {
//            //Ten ham lay du lieu kho
//            String functionWS = "cms:getListDepartmentDTO";
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
//            Mapper.addImplicitCollection(parseObject, WSDepartment.class, "lstDepartmentDTO")
//                    .alias(parseObject, WSDepartment.class, "ns2:getListDepartmentDTOResponse")
//                    .alias(parseObject, DepartmentDTO.class, "ns2:departmentDTO");
//            //Kho tao doc/ghi du lieu tu xml ra Object
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            //Caller WS
//            WebServiceCaller webServiceCaller = new WebServiceCaller();
//            String strResult = webServiceCaller.webServiceCaller(wsConfig);
//            //Handler WS
//            WSDepartment wsList = (WSDepartment) WebServiceHandler.wsServiceHandler(strResult, xmlStream);
//            //
//            return wsList.lstDepartmentDTO;
//
//        } catch (Exception e) {
//            throw e;
//        }
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        return departmentService.getListDepartmentDTO(departmentDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    public static List<DepartmentDTO> getListDepartmentByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
//        try {
//            //Ten ham lay du lieu kho
//            String functionWS = "cms:getListDepartmentByCondition";
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
//            Mapper.addImplicitCollection(parseObject, WSDepartment.class, "lstDepartmentConditionBean")
//                    .alias(parseObject, WSDepartment.class, "ns2:getListDepartmentByConditionResponse")
//                    .alias(parseObject, DepartmentDTO.class, "ns2:DepartmentDTO");
//            //Kho tao doc/ghi du lieu tu xml ra Object
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            //Caller WS
//            WebServiceCaller webServiceCaller = new WebServiceCaller();
//            String strResult = webServiceCaller.webServiceCaller(wsConfig);
//            //Handler WS
//            WSDepartment wsList = (WSDepartment) WebServiceHandler.wsServiceHandler(strResult, xmlStream);
//            //
//            return wsList.lstDepartmentConditionBean;
//
//        } catch (Exception e) {
//            throw e;
//        }
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        return departmentService.getListDepartmentByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert Department
    public static ResultDTO insertDepartment(DepartmentDTO departmentDTO) {
//        try {
//            String functionWS = "cms:insertDepartment";
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
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        return departmentService.insertDepartment(departmentDTO);
    }

    //Update Department
    public static String updateDepartment(DepartmentDTO departmentDTO) {
//        try {
//            String functionWS = "cms:updateDepartment";
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
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        return departmentService.updateDepartment(departmentDTO);
    }

    //Delete Department
    public static String deleteDepartment(String id, String username) {
//        try {
//            String functionWS = "cms:deleteDepartment";
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
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        return departmentService.deleteDepartment(Long.parseLong(id), username);
    }

    //find Department by id
    public static DepartmentDTO findDepartmentById(String id) {
//        try {
//            String functionWS = "cms:findDepartmentById";
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
//            Mapper.alias(parseObject, DepartmentDTO.class, "ns2:department");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            DepartmentDTO departmentDTO = (DepartmentDTO) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return departmentDTO;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        return departmentService.findDepartmentById(Long.parseLong(id));
    }

    // xoa nhieu Department
    public static String deleteLstDepartment(List<DepartmentDTO> lstDepartmentDTO) {
//        try {
//            String functionWS = "cms:deleteListDepartment";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapObjects = new HashMap<>();
//            mapObjects.put("departmentListDTO", lstDepartmentDTO);
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
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        return departmentService.deleteListDepartment(lstDepartmentDTO);
    }

    public static String insertOrUpdateListDepartment(List<DepartmentDTO> lstDeptDTO) {
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        return departmentService.insertOrUpdateListDepartment(lstDeptDTO);
    }
}
