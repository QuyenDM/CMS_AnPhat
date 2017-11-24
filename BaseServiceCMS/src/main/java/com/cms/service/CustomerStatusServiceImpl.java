/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.business.service.CategoryListBusinessInterface;
import com.cms.business.service.CustomerStatusBusinessInterface;
import com.cms.dto.CustomerStatusDTO;
import com.cms.dto.StatusQuantityDTO;
import com.cms.statistics.dto.StatisticsCategoryListDTO;
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
 * @since 7/30/2016 5:27 PM
 */
@WebService(endpointInterface = "com.cms.service.CustomerStatusService")
public class CustomerStatusServiceImpl implements CustomerStatusService {

    @Autowired
    CustomerStatusBusinessInterface customerStatusBusiness;
    
    @Autowired
    CategoryListBusinessInterface categoryListBusinessInterface;

    @Override
    public String updateCustomerStatus(CustomerStatusDTO customerStatusDTO) {
        return customerStatusBusiness.update(customerStatusDTO);
    }

    @Override
    public String deleteCustomerStatus(Long id) {
        return customerStatusBusiness.delete(id);
    }

    @Override
    public String deleteListCustomerStatus(List<CustomerStatusDTO> customerStatusListDTO) {
        return customerStatusBusiness.delete(customerStatusListDTO);
    }

    @Override
    public CustomerStatusDTO findCustomerStatusById(Long id) {
        if (id != null && id > 0) {
            return (CustomerStatusDTO) customerStatusBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<CustomerStatusDTO> getListCustomerStatusDTO(CustomerStatusDTO customerStatusDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (customerStatusDTO != null) {
            return customerStatusBusiness.search(customerStatusDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertCustomerStatus(CustomerStatusDTO customerStatusDTO) {
        return customerStatusBusiness.createObject(customerStatusDTO);
    }

    @Override
    public String insertOrUpdateListCustomerStatus(List<CustomerStatusDTO> customerStatusDTO) {
        String result = customerStatusBusiness.insertList(customerStatusDTO);
        categoryListBusinessInterface.updateDevidedQuanlityForCategoryList();
        return result;
    }

    @Override
    public List<String> getSequenseCustomerStatus(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);

        return customerStatusBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<CustomerStatusDTO> getListCustomerStatusByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<CustomerStatusDTO> lstCustomerStatus = new ArrayList<>();
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

        lstCustomerStatus = customerStatusBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstCustomerStatus;
    }

    @Override
    public List<StatisticsCategoryListDTO> getStatisticsCategoryListByStaff(String service, String staffCode, String categoryId, String beginLastUpdated, String endLastUpdated) {
        return customerStatusBusiness.getStatisticsCategoryListByStaff(service, staffCode, categoryId, beginLastUpdated, endLastUpdated);
    }
    
    @Override
    public List<StatusQuantityDTO> getStatusQuantity(String staffCode) {
        return customerStatusBusiness.getStatusQuantity(staffCode);
    }

}
