/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.login.ws;

import com.cms.dto.TermInformationDTO;
import com.cms.service.TermInformationServiceImpl;
import com.cms.utils.BundleUtils;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
public class WSTermInformation {

    List<TermInformationDTO> lstTermInformationDTO;
    List<TermInformationDTO> lstTermInformationConditionBean;
    //Duong dan Websevice
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    //Duong dan ten dich
    public static String targetNamePath = "xmlns:cms=\"http://service.cms.com\"";
    //Url WS Stock
    public static String strWSUrl = strWsWMSUrl + "service";

    //Lay toan bo danh sach kho
    public static List<TermInformationDTO> getListTermInformationDTO(TermInformationDTO departmentDTO, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
//        try {
//            //Ten ham lay du lieu kho
//            String functionWS = "cms:getListTermInformationDTO";
//            //
//            Map<String, Object> mapTermInformation = new HashMap<>();
//            //Set cac tham so cau hinh
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//            //Put du lieu tham so dau vao
//            mapTermInformation.put("departmentDTO", departmentDTO);
//            mapTermInformation.put("rowStart", rowStart);
//            mapTermInformation.put("maxRow", maxRow);
//            mapTermInformation.put("sortType", sortType);
//            mapTermInformation.put("sortFieldList", sortFieldList);
//            //Set tham so dau vao
//            wsConfig.setBodyArgAlias(mapTermInformation);
//            //Map doi tuong 
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.addImplicitCollection(parseObject, WSTermInformation.class, "lstTermInformationDTO")
//                    .alias(parseObject, WSTermInformation.class, "ns2:getListTermInformationDTOResponse")
//                    .alias(parseObject, TermInformationDTO.class, "ns2:departmentDTO");
//            //Kho tao doc/ghi du lieu tu xml ra Object
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            //Caller WS
//            WebServiceCaller webServiceCaller = new WebServiceCaller();
//            String strResult = webServiceCaller.webServiceCaller(wsConfig);
//            //Handler WS
//            WSTermInformation wsList = (WSTermInformation) WebServiceHandler.wsServiceHandler(strResult, xmlStream);
//            //
//            return wsList.lstTermInformationDTO;
//
//        } catch (Exception e) {
//            throw e;
//        }
        TermInformationServiceImpl service = new TermInformationServiceImpl();
        return service.getListTermInformationDTO(departmentDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    public static List<TermInformationDTO> getListTermInformationByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
//        try {
//            //Ten ham lay du lieu kho
//            String functionWS = "cms:getListTermInformationByCondition";
//            //
//            Map<String, Object> mapTermInformation = new HashMap<>();
//            //Set cac tham so cau hinh
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//            //Put du lieu tham so dau vao
//            mapTermInformation.put("lstCondition", lstCon);
//            mapTermInformation.put("rowStart", rowStart);
//            mapTermInformation.put("maxRow", maxRow);
//            mapTermInformation.put("sortType", sortType);
//            mapTermInformation.put("sortFieldList", sortFieldList);
//            //Set tham so dau vao
//            wsConfig.setBodyArgAlias(mapTermInformation);
//            //Map doi tuong 
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.addImplicitCollection(parseObject, WSTermInformation.class, "lstTermInformationConditionBean")
//                    .alias(parseObject, WSTermInformation.class, "ns2:getListTermInformationByConditionResponse")
//                    .alias(parseObject, TermInformationDTO.class, "ns2:TermInformationDTO");
//            //Kho tao doc/ghi du lieu tu xml ra Object
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            //Caller WS
//            WebServiceCaller webServiceCaller = new WebServiceCaller();
//            String strResult = webServiceCaller.webServiceCaller(wsConfig);
//            //Handler WS
//            WSTermInformation wsList = (WSTermInformation) WebServiceHandler.wsServiceHandler(strResult, xmlStream);
//            //
//            return wsList.lstTermInformationConditionBean;
//
//        } catch (Exception e) {
//            throw e;
//        }
        TermInformationServiceImpl service = new TermInformationServiceImpl();
        return service.getListTermInformationByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert TermInformation
    public static ResultDTO insertTermInformation(TermInformationDTO departmentDTO) {
//        try {
//            String functionWS = "cms:insertTermInformation";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapTermInformation = new HashMap<>();
//            mapTermInformation.put("departmentDTO", departmentDTO);
//            wsConfig.setBodyArgAlias(mapTermInformation);
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
        TermInformationServiceImpl service = new TermInformationServiceImpl();
        return service.insertTermInformation(departmentDTO);
    }

    //Update TermInformation
    public static String updateTermInformation(TermInformationDTO departmentDTO) {
//        try {
//            String functionWS = "cms:updateTermInformation";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapTermInformation = new HashMap<>();
//            mapTermInformation.put("departmentDTO", departmentDTO);
//            wsConfig.setBodyArgAlias(mapTermInformation);
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
        TermInformationServiceImpl service = new TermInformationServiceImpl();
        return service.updateTermInformation(departmentDTO);
    }

    //Delete TermInformation
    public static String deleteTermInformation(String id) {
//        try {
//            String functionWS = "cms:deleteTermInformation";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapTermInformation = new HashMap<>();
//            mapTermInformation.put("departmentDTOId", id);
//            wsConfig.setBodyArgAlias(mapTermInformation);
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
        TermInformationServiceImpl service = new TermInformationServiceImpl();
        return service.deleteTermInformation(Long.parseLong(id));
    }

    //find TermInformation by id
    public static TermInformationDTO findTermInformationById(String id) {
//        try {
//            String functionWS = "cms:findTermInformationById";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapTermInformation = new HashMap<>();
//            mapTermInformation.put("departmentDTOId", id);
//            wsConfig.setBodyArgAlias(mapTermInformation);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, TermInformationDTO.class, "ns2:department");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            TermInformationDTO departmentDTO = (TermInformationDTO) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return departmentDTO;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        TermInformationServiceImpl service = new TermInformationServiceImpl();
        return service.findTermInformationById(Long.parseLong(id));
    }

    // xoa nhieu TermInformation
    public static String deleteLstTermInformation(List<TermInformationDTO> lstTermInformationDTO) {
//        try {
//            String functionWS = "cms:deleteListTermInformation";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapTermInformation = new HashMap<>();
//            mapTermInformation.put("departmentListDTO", lstTermInformationDTO);
//            wsConfig.setBodyArgAlias(mapTermInformation);
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
        TermInformationServiceImpl service = new TermInformationServiceImpl();
        return service.deleteListTermInformation(lstTermInformationDTO);
    }

    // Them moi hoac cap nhat 1 danh sach TermInformation
    public static String insertOrUpdateListTermInformation(List<TermInformationDTO> lstTermInformationDTO) {
        TermInformationServiceImpl service = new TermInformationServiceImpl();
        return service.insertOrUpdateListTermInformation(lstTermInformationDTO);
    }

    public static ResultDTO insertListTermInformation(List<TermInformationDTO> lstTermInformationDTO) {
        TermInformationServiceImpl service = new TermInformationServiceImpl();
        return service.insertListTermInformation(lstTermInformationDTO);
    }
}
