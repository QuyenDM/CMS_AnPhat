/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.login.ws;

import com.cms.dto.CustomerCareHistoryDTO;
import com.cms.service.CustomerCareHistoryServiceImpl;
import com.cms.utils.BundleUtils;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
public class WSCustomerCareHistory {

    List<CustomerCareHistoryDTO> lstCustomerCareHistoryDTO;
    List<CustomerCareHistoryDTO> lstCustomerCareHistoryConditionBean;
    //Duong dan Websevice
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    //Duong dan ten dich
    public static String targetNamePath = "xmlns:cms=\"http://service.cms.com\"";
    //Url WS Stock
    public static String strWSUrl = strWsWMSUrl + "service";

    //Lay toan bo danh sach kho
    public static List<CustomerCareHistoryDTO> getListCustomerCareHistoryDTO(CustomerCareHistoryDTO dto, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        CustomerCareHistoryServiceImpl service = new CustomerCareHistoryServiceImpl();
        return service.getListCustomerCareHistoryDTO(dto, rowStart, maxRow, sortType, sortFieldList);
    }

    public static List<CustomerCareHistoryDTO> getListCustomerCareHistoryByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
//        try {
//            //Ten ham lay du lieu kho
//            String functionWS = "cms:getListCustomerCareHistoryByCondition";
//            //
//            Map<String, Object> mapCustomerCareHistory = new HashMap<>();
//            //Set cac tham so cau hinh
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//            //Put du lieu tham so dau vao
//            mapCustomerCareHistory.put("lstCondition", lstCon);
//            mapCustomerCareHistory.put("rowStart", rowStart);
//            mapCustomerCareHistory.put("maxRow", maxRow);
//            mapCustomerCareHistory.put("sortType", sortType);
//            mapCustomerCareHistory.put("sortFieldList", sortFieldList);
//            //Set tham so dau vao
//            wsConfig.setBodyArgAlias(mapCustomerCareHistory);
//            //Map doi tuong 
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.addImplicitCollection(parseObject, WSCustomerCareHistory.class, "lstCustomerCareHistoryConditionBean")
//                    .alias(parseObject, WSCustomerCareHistory.class, "ns2:getListCustomerCareHistoryByConditionResponse")
//                    .alias(parseObject, CustomerCareHistoryDTO.class, "ns2:CustomerCareHistoryDTO");
//            //Kho tao doc/ghi du lieu tu xml ra Object
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            //Caller WS
//            WebServiceCaller webServiceCaller = new WebServiceCaller();
//            String strResult = webServiceCaller.webServiceCaller(wsConfig);
//            //Handler WS
//            WSCustomerCareHistory wsList = (WSCustomerCareHistory) WebServiceHandler.wsServiceHandler(strResult, xmlStream);
//            //
//            return wsList.lstCustomerCareHistoryConditionBean;
//
//        } catch (Exception e) {
//            throw e;
//        }
        CustomerCareHistoryServiceImpl service = new CustomerCareHistoryServiceImpl();
        return service.getListCustomerCareHistoryByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert CustomerCareHistory
    public static ResultDTO insertCustomerCareHistory(CustomerCareHistoryDTO dto) {
//        try {
//            String functionWS = "cms:insertCustomerCareHistory";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapCustomerCareHistory = new HashMap<>();
//            mapCustomerCareHistory.put("dto", dto);
//            wsConfig.setBodyArgAlias(mapCustomerCareHistory);
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
        CustomerCareHistoryServiceImpl service = new CustomerCareHistoryServiceImpl();
        return service.insertCustomerCareHistory(dto);
    }

    //Update CustomerCareHistory
    public static String updateCustomerCareHistory(CustomerCareHistoryDTO dto) {
//        try {
//            String functionWS = "cms:updateCustomerCareHistory";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapCustomerCareHistory = new HashMap<>();
//            mapCustomerCareHistory.put("dto", dto);
//            wsConfig.setBodyArgAlias(mapCustomerCareHistory);
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
        CustomerCareHistoryServiceImpl service = new CustomerCareHistoryServiceImpl();
        return service.updateCustomerCareHistory(dto);
    }

    //Delete CustomerCareHistory
    public static String deleteCustomerCareHistory(String id) {
//        try {
//            String functionWS = "cms:deleteCustomerCareHistory";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapCustomerCareHistory = new HashMap<>();
//            mapCustomerCareHistory.put("dtoId", id);
//            wsConfig.setBodyArgAlias(mapCustomerCareHistory);
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
        CustomerCareHistoryServiceImpl service = new CustomerCareHistoryServiceImpl();
        return service.deleteCustomerCareHistory(Long.parseLong(id));
    }

    //find CustomerCareHistory by id
    public static CustomerCareHistoryDTO findCustomerCareHistoryById(String id) {
//        try {
//            String functionWS = "cms:findCustomerCareHistoryById";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapCustomerCareHistory = new HashMap<>();
//            mapCustomerCareHistory.put("dtoId", id);
//            wsConfig.setBodyArgAlias(mapCustomerCareHistory);
//
//            List<XStreamStorage> parseObject = new ArrayList<>();
//            Mapper.alias(parseObject, CustomerCareHistoryDTO.class, "ns2:department");
//            XmlStream xmlStream = new XmlStream();
//            xmlStream.config(parseObject);
//            xmlStream.isSingleType = true;
//            String strWSConfig = WebServiceHandler.webServiceCaller(wsConfig);
//            CustomerCareHistoryDTO dto = (CustomerCareHistoryDTO) WebServiceHandler.wsServiceHandler(strWSConfig, xmlStream);
//            return dto;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        CustomerCareHistoryServiceImpl service = new CustomerCareHistoryServiceImpl();
        return service.findCustomerCareHistoryById(Long.parseLong(id));
    }

    // xoa nhieu CustomerCareHistory
    public static String deleteLstCustomerCareHistory(List<CustomerCareHistoryDTO> lstCustomerCareHistoryDTO) {
//        try {
//            String functionWS = "cms:deleteListCustomerCareHistory";
//            WsRequestCreator wsConfig = new WsRequestCreator();
//            wsConfig.setWsAddress(strWSUrl);
//            wsConfig.setServiceName(functionWS);
//            wsConfig.setTargetNameSpace(targetNamePath);
//
//            Map<String, Object> mapCustomerCareHistory = new HashMap<>();
//            mapCustomerCareHistory.put("departmentListDTO", lstCustomerCareHistoryDTO);
//            wsConfig.setBodyArgAlias(mapCustomerCareHistory);
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
        CustomerCareHistoryServiceImpl service = new CustomerCareHistoryServiceImpl();
        return service.deleteListCustomerCareHistory(lstCustomerCareHistoryDTO);
    }

    // Them moi hoac cap nhat 1 danh sach CustomerCareHistory
    public static String insertOrUpdateListCustomerCareHistory(List<CustomerCareHistoryDTO> lstCustomerCareHistoryDTO) {
        CustomerCareHistoryServiceImpl service = new CustomerCareHistoryServiceImpl();
        return service.insertOrUpdateListCustomerCareHistory(lstCustomerCareHistoryDTO);
    }

}
