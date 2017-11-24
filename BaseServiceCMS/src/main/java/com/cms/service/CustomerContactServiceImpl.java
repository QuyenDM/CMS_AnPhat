/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.business.service.CustomerContactBusinessInterface;
import com.cms.dto.CustomerContactDTO;
import java.util.List;
import javax.jws.WebService;
import java.util.ArrayList;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.vfw5.base.dto.ResultDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/26/2016 10:32 PM
 */
@WebService(endpointInterface = "com.cms.service.CustomerContactService")
public class CustomerContactServiceImpl implements CustomerContactService {

    @Autowired
    CustomerContactBusinessInterface customerContactBusiness;

    @Override
    public String updateCustomerContact(CustomerContactDTO customerContactDTO) {
        return customerContactBusiness.update(customerContactDTO);
    }

    @Override
    public String deleteCustomerContact(Long id) {
        return customerContactBusiness.delete(id);
    }

    @Override
    public String deleteListCustomerContact(List<CustomerContactDTO> customerContactListDTO) {
        return customerContactBusiness.delete(customerContactListDTO);
    }

    @Override
    public CustomerContactDTO findCustomerContactById(Long id) {
        if (id != null && id > 0) {
            return (CustomerContactDTO) customerContactBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<CustomerContactDTO> getListCustomerContactDTO(CustomerContactDTO customerContactDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (customerContactDTO != null) {
            return customerContactBusiness.search(customerContactDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertCustomerContact(CustomerContactDTO customerContactDTO) {
        return customerContactBusiness.createObject(customerContactDTO);
    }

    @Override
    public String insertOrUpdateListCustomerContact(List<CustomerContactDTO> customerContactDTO) {
        return customerContactBusiness.insertList(customerContactDTO);
    }

    @Override
    public List<String> getSequenseCustomerContact(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);

        return customerContactBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<CustomerContactDTO> getListCustomerContactByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<CustomerContactDTO> lstCustomerContact = new ArrayList<>();
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

        lstCustomerContact = customerContactBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstCustomerContact;
    }

}
