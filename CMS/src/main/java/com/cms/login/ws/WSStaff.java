/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.login.ws;

import com.cms.dto.StaffDTO;
import com.cms.service.StaffServiceImpl;
import com.cms.utils.BundleUtils;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
public class WSStaff {

    List<StaffDTO> lstStaffDTO;
    List<StaffDTO> lstStaffConditionBean;
    //Duong dan Websevice
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    //Duong dan ten dich
    public static String targetNamePath = "xmlns:cms=\"http://service.cms.com\"";
    //Url WS Stock
    public static String strWSUrl = strWsWMSUrl + "staffService";

    //Lay toan bo danh sach kho
    public static List<StaffDTO> getListStaffDTO(StaffDTO staffDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        StaffServiceImpl staffService = new StaffServiceImpl();
        return staffService.getListStaffDTO(staffDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    public static List<StaffDTO> getListStaffByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
//        try {
//            //Ten ham lay du lieu kho
//            String functionWS = "cms:getListStaffByCondition";
//            //
//            Map<String, Object> mapStaff = new HashMap<>();
//            //Set cac tham so cau hinh
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//            //Put du lieu tham so dau vao
//            mapStaff.put("lstCondition", lstCon);
//            mapStaff.put("rowStart", rowStart);
//            mapStaff.put("maxRow", maxRow);
//            mapStaff.put("sortType", sortType);
//            mapStaff.put("sortFieldList", sortFieldList);
//            //Set tham so dau vao
//            wsConfig.setBodyArgAlias(mapStaff);
//            //Map doi tuong 
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.addImplicitCollection(parseObject, WSStaff.class, "lstStaffConditionBean")
//                    .alias(parseObject, WSStaff.class, "ns2:getListStaffByConditionResponse")
//                    .alias(parseObject, StaffDTO.class, "ns2:StaffDTO");
//            //Kho tao doc/ghi du lieu tu xml ra Object
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            //Caller WS
//            WebServiceCaller webServiceCaller = new WebServiceCaller();
//            String strResult = webServiceCaller.webServiceCaller(wsConfig);
//            //Handler WS
//            WSStaff wsList = (WSStaff) WebServiceHandler.wsServiceHandler(strResult, xmlStream);
//            //
//            return wsList.lstStaffConditionBean;
//
//        } catch (Exception e) {
//            throw e;
//        }
        StaffServiceImpl staffService = new StaffServiceImpl();
        return staffService.getListStaffByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert Staff
    public static ResultDTO insertStaff(StaffDTO staffDTO) {
//        try {
//            String functionWS = "cms:insertStaff";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapStaff = new HashMap<>();
//            mapStaff.put("staffDTO", staffDTO);
//            wsConfig.setBodyArgAlias(mapStaff);
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
        StaffServiceImpl staffService = new StaffServiceImpl();
        return staffService.insertStaff(staffDTO);
    }

    //Update Staff
    public static String updateStaff(StaffDTO staffDTO) {
//        try {
//            String functionWS = "cms:updateStaff";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapStaff = new HashMap<>();
//            mapStaff.put("staffDTO", staffDTO);
//            wsConfig.setBodyArgAlias(mapStaff);
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
        StaffServiceImpl staffService = new StaffServiceImpl();
        return staffService.updateStaff(staffDTO);
    }

    //Delete Staff
    public static String deleteStaff(String id, String username) {
//        try {
//            String functionWS = "cms:deleteStaff";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapStaff = new HashMap<>();
//            mapStaff.put("staffDTOId", id);
//            wsConfig.setBodyArgAlias(mapStaff);
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
        StaffServiceImpl staffService = new StaffServiceImpl();
        return staffService.deleteStaff(Long.parseLong(id), username);
    }

    //find Staff by id
    public static StaffDTO findStaffById(String id) {
//        try {
//            String functionWS = "cms:findStaffById";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapStaff = new HashMap<>();
//            mapStaff.put("staffDTOId", id);
//            wsConfig.setBodyArgAlias(mapStaff);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, StaffDTO.class, "ns2:staff");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            StaffDTO staffDTO = (StaffDTO) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return staffDTO;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        StaffServiceImpl staffService = new StaffServiceImpl();
        return staffService.findStaffById(Long.parseLong(id));
    }

    // xoa nhieu Staff
    public static String deleteLstStaff(List<StaffDTO> lstStaffDTO) {
//        try {
//            String functionWS = "cms:deleteListStaff";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapStaff = new HashMap<>();
//            mapStaff.put("staffListDTO", lstStaffDTO);
//            wsConfig.setBodyArgAlias(mapStaff);
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
        StaffServiceImpl staffService = new StaffServiceImpl();
        return staffService.deleteListStaff(lstStaffDTO);
    }

    public static String insertOrUpdateListStaffs(List<StaffDTO> listStaffDel) {
        StaffServiceImpl staffService = new StaffServiceImpl();
        return staffService.insertOrUpdateListStaff(listStaffDel);
    }
}
