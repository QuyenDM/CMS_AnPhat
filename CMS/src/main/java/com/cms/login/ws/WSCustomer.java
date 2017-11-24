/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.login.ws;

import com.cms.dto.CustomerDTO;
import com.cms.dto.CustomerInfomationDTO;
import com.cms.service.CustomerServiceImpl;
import com.cms.utils.BundleUtils;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
public class WSCustomer {

    List<CustomerDTO> lstCustomerDTO;
    List<CustomerDTO> lstCustomerConditionBean;
    //Duong dan Websevice
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    //Duong dan ten dich
    public static String targetNamePath = "xmlns:cms=\"http://service.cms.com\"";
    //Url WS Stock
    public static String strWSUrl = strWsWMSUrl + "service";

    //Lay toan bo danh sach kho
    public static List<CustomerDTO> getListCustomerDTO(CustomerDTO departmentDTO, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.getListCustomerDTO(departmentDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    public static List<CustomerDTO> getListCustomerByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.getListCustomerByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

//    //Insert Customer
    public static ResultDTO insertCustomer(CustomerDTO departmentDTO) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.insertCustomer(departmentDTO);
    }

    //Update Customer
    public static String updateCustomer(CustomerDTO departmentDTO) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.updateCustomer(departmentDTO);
    }

    //Delete Customer
    public static String deleteCustomer(String id) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.deleteCustomer(Long.parseLong(id));
    }

    //find Customer by id
    public static CustomerDTO findCustomerById(String id) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.findCustomerById(Long.parseLong(id));
    }

    // xoa nhieu Customer
    public static String deleteLstCustomer(List<CustomerDTO> lstCustomerDTO) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.deleteListCustomer(lstCustomerDTO);
    }

    // Them moi hoac cap nhat 1 danh sach Customer
    public static List<CustomerDTO> insertOrUpdateListCustomer(List<CustomerDTO> lstCustomerDTO) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.insertOrUpdateListCustomer(lstCustomerDTO);
    }

    // Lay thong tin cua khach hang
    public static CustomerInfomationDTO getCustInfo(String taxCode, String staffCode, String mineName) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.getCustInfo(taxCode, staffCode, mineName);
    }

    // Lay thong tin cua khach hang
    public static List<CustomerDTO> searchCustomers(CustomerDTO customerDTO, int maxResult) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.searchCustomers(customerDTO, maxResult);
    }

    public static List<CustomerDTO> getListCustomerFromTermInfo(List<ConditionBean> lstConditions) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.getListCustomerFromTermInfo(lstConditions);
    }

    public static List<CustomerDTO> getListCustomerFromTermInfoWithoutTaxCodes(List<ConditionBean> lstConditions, List<String> taxCodesExecuted) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.getListCustomerFromTermInfoWithoutTaxCodes(lstConditions, taxCodesExecuted);
    }

    public static List<CustomerDTO> getCustomerExisted(List<String> taxCodes) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.getCustomerExisted(taxCodes);
    }

    public static List<CustomerDTO> getListCustomerOfMineName(String mineName) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.getListCustomerOfMineName(mineName);
    }

    public static List<CustomerDTO> getListDevidedCustomerOfMineName(String mineName) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.getListDevidedCustomerOfMineName(mineName);
    }

    // Them moi hoac cap nhat 1 danh sach Customer
    public static List<CustomerDTO> newInsertOrUpdateListCustomer(List<CustomerDTO> lstCustomerDTO) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.insertOrUpdateListCustomer(lstCustomerDTO);
    }
    
    public static String saveOrUpdateAndReturnErrors(List<CustomerDTO> lstCustomer){
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.saveOrUpdateAndReturnErrors(lstCustomer);
    }
    
    public static List<CustomerDTO> getCustomerFromIHTKK(List<String> taxCodes) {
        CustomerServiceImpl service = new CustomerServiceImpl();
        return service.getCustomerFromIHTKK(taxCodes);
    }
}
