/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.business.service.CustomerBusinessInterface;
import com.cms.dto.CustomerCareHistoryDTO;
import com.cms.dto.CustomerContactDTO;
import com.cms.dto.CustomerDTO;
import com.cms.dto.CustomerInfomationDTO;
import com.cms.dto.CustomerStatusDTO;
import com.cms.dto.CustomerUserInfoDTO;
import com.cms.dto.TermInformationDTO;
import com.cms.model.Customer;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;
import javax.jws.WebService;
import java.util.ArrayList;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.DataUtil;
import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.StringUtils;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:55 AM
 */
@WebService(endpointInterface = "com.cms.service.CustomerService")
//@net.anotheria.moskito.aop.annotation.Monitor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    BaseFWServiceInterface customerBusiness;
    @Autowired
    BaseFWServiceInterface customerContactBusiness;
    @Autowired
    BaseFWServiceInterface customerCareHistoryBusiness;
    @Autowired
    BaseFWServiceInterface termInformationBusiness;
    @Autowired
    BaseFWServiceInterface customerStatusBusiness;
    @Autowired
    CustomerBusinessInterface customerBusinessInterface;

    @Override
    public String updateCustomer(CustomerDTO customerDTO) {
        return customerBusiness.update(customerDTO);
    }

    @Override
    public String deleteCustomer(Long id) {
        return customerBusiness.delete(id);
    }

    @Override
    public String deleteListCustomer(List<CustomerDTO> customerListDTO) {
        return customerBusiness.delete(customerListDTO);
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        if (id != null && id > 0) {
            return (CustomerDTO) customerBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<CustomerDTO> getListCustomerDTO(CustomerDTO customerDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (customerDTO != null) {
            return customerBusiness.search(customerDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertCustomer(CustomerDTO customerDTO) {
        return customerBusiness.createObject(customerDTO);
    }

    //
    @Override
    public List<CustomerDTO> insertOrUpdateListCustomer(List<CustomerDTO> customerDTO) {
        List<CustomerDTO> lstFailed = new ArrayList<>();
        for (CustomerDTO c : customerDTO) {
            if (DataUtil.isStringNullOrEmpty(c.getCustId())) {
                try {
                    customerBusiness.createObject(c);
                } catch (Exception e) {
                    lstFailed.add(c);
                }
            } else {
                try {
                    customerBusiness.update(c);
                } catch (Exception e) {
                    lstFailed.add(c);
                }
            }
        }
        return lstFailed;
    }

    @Override
    public List<String> getSequenseCustomer(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);

        return customerBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<CustomerDTO> getListCustomerByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<CustomerDTO> lstCustomer = new ArrayList<>();
        for (ConditionBean con : lstCondition) {
            if (con.getType().equalsIgnoreCase(ParamUtils.TYPE_DATE)) {
                con.setField(StringUtils.formatFunction("trunc", con.getField()));
            } else if (con.getType().equalsIgnoreCase(ParamUtils.NUMBER)) {
                con.setType(ParamUtils.TYPE_NUMBER);
            } else if (con.getType().equalsIgnoreCase(ParamUtils.NUMBER_DOUBLE)) {
                con.setType(ParamUtils.NUMBER_DOUBLE);
            } else {
                String value = "";
                if (con.getOperator().equalsIgnoreCase(ParamUtils.NAME_LIKE)) {
                    value = StringUtils.formatLike(con.getValue());
                } else {
                    value = con.getValue();
                }
                con.setValue(value.toLowerCase());
                con.setField(StringUtils.formatFunction("lower", con.getField()));
            }
            con.setOperator(StringUtils.convertTypeOperator(con.getOperator()));

        }
        lstCustomer = customerBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstCustomer;
    }

    @Override
    public String saveOrUpdateAndReturnErrors(List<CustomerDTO> lstCustomer) {
        return customerBusinessInterface.insertOrUpdateReturnRecordErrors(lstCustomer);
    }

    /**
     * QuyenDM Lay thong tin khach hang - user theo username
     *
     * @param userCode
     * @return
     */
    @Override
    public CustomerUserInfoDTO getCustUserInfor(String userCode) {
        return customerBusinessInterface.getCustUserInfo(userCode);
    }

    @Override
    public CustomerInfomationDTO getCustInfo(String taxCode, String staffCode, String mineName) {
        //Condition for search CustomerContact
        List<ConditionBean> lstConditionCusContact = new ArrayList<>();
        lstConditionCusContact.add(new ConditionBean("taxCode", ParamUtils.OP_EQUAL, taxCode, ParamUtils.TYPE_STRING));
        if (!DataUtil.isStringNullOrEmpty(staffCode)) {
            lstConditionCusContact.add(new ConditionBean("staffCode", ParamUtils.OP_EQUAL, staffCode, ParamUtils.TYPE_STRING));
        }
        List<CustomerContactDTO> lstCustomerContactDTOs = customerContactBusiness.searchByConditionBean(lstConditionCusContact, 0, Integer.MAX_VALUE, "asc", "name");

        //Condition for search Customer Care History
        List<ConditionBean> lstConditionCustCareHis = new ArrayList<>();
        lstConditionCustCareHis.add(new ConditionBean("taxCode", ParamUtils.OP_EQUAL, taxCode, ParamUtils.TYPE_STRING));
        if (!DataUtil.isStringNullOrEmpty(staffCode)) {
            lstConditionCustCareHis.add(new ConditionBean("staffCode", ParamUtils.OP_EQUAL, staffCode, ParamUtils.TYPE_STRING));
        }
        if (!DataUtil.isStringNullOrEmpty(mineName)) {
            lstConditionCustCareHis.add(new ConditionBean("mineName", ParamUtils.OP_EQUAL, mineName, ParamUtils.TYPE_STRING));
        }
        List<CustomerCareHistoryDTO> lstCustomerCareHistoryDTOs = customerCareHistoryBusiness.searchByConditionBean(
                lstConditionCustCareHis, 0, Integer.MAX_VALUE, "desc", "createDate");

        //Condition for search Term information
        List<ConditionBean> lstConditionTermInfo = new ArrayList<>();
        lstConditionTermInfo.add(new ConditionBean("taxCode", ParamUtils.OP_EQUAL, taxCode, ParamUtils.TYPE_STRING));
        if (!DataUtil.isStringNullOrEmpty(mineName)) {
            lstConditionTermInfo.add(new ConditionBean("mineName", ParamUtils.OP_EQUAL, mineName, ParamUtils.TYPE_STRING));
        }
        List<TermInformationDTO> lstTermInformationDTOs = termInformationBusiness.searchByConditionBean(lstConditionTermInfo, 0, Integer.MAX_VALUE, "desc", "endTime");
        
        //Condition for search Customer Status
        List<ConditionBean> lstConditionCustStatus = new ArrayList<>();
        lstConditionCustStatus.add(new ConditionBean("taxCode", ParamUtils.OP_EQUAL, taxCode, ParamUtils.TYPE_STRING));
        if (!DataUtil.isStringNullOrEmpty(mineName)) {
            lstConditionCustStatus.add(new ConditionBean("mineName", ParamUtils.OP_EQUAL, mineName, ParamUtils.TYPE_STRING));
        }
        if (!DataUtil.isStringNullOrEmpty(staffCode)) {
            lstConditionCustStatus.add(new ConditionBean("staffCode", ParamUtils.OP_EQUAL, staffCode, ParamUtils.TYPE_STRING));
        }
        List<CustomerStatusDTO> lstCustomerStatusDTOs = customerStatusBusiness.searchByConditionBean(lstConditionCustStatus, 0, Integer.MAX_VALUE, "desc", "lastUpdated");
        return new CustomerInfomationDTO(lstTermInformationDTOs, lstCustomerContactDTOs, lstCustomerCareHistoryDTOs, lstCustomerStatusDTOs);
    }

    @Override
    public List<CustomerDTO> searchCustomers(CustomerDTO customerDTO, int maxResult) {
        return customerBusinessInterface.searchCustomers(customerDTO, maxResult);
    }

    @Override
    public List<CustomerDTO> getListCustomerFromTermInfo(List<ConditionBean> lstConditions) {
        return customerBusinessInterface.getListCustomerFromTermInfo(lstConditions);
    }

    @Override
    public List<CustomerDTO> getListCustomerFromTermInfoWithoutTaxCodes(List<ConditionBean> lstConditions, List<String> taxCodesExecuted) {
        return customerBusinessInterface.getListCustomerFromTermInfoWithoutTaxCodes(lstConditions, taxCodesExecuted);
    }

    @Override
    public List<CustomerDTO> getCustomerExisted(List<String> taxCodes) {
        return customerBusinessInterface.getCustomerExisted(taxCodes);
    }

    @Override
    public List<CustomerDTO> getListCustomerOfMineName(String mineName) {
        return customerBusinessInterface.getListCustomerOfMineName(mineName);
    }

    @Override
    public List<CustomerDTO> getListDevidedCustomerOfMineName(String mineName) {
        return customerBusinessInterface.getListDevidedCustomerOfMineName(mineName);
    }

    @Override
    public List<CustomerDTO> getCustomerFromIHTKK(List<String> taxCodes) {
        return customerBusinessInterface.getCustomerFromIHTKK(taxCodes);
    }

}
