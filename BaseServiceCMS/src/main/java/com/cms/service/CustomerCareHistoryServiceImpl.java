/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.CustomerCareHistoryDTO;
import com.cms.business.service.CustomerCareHistoryBusinessInterface;
import com.cms.dto.CustomerStatusDTO;
import java.util.List;
import javax.jws.WebService;
import java.util.ArrayList;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.service.BaseFWServiceInterface;
import com.vfw5.base.utils.DataUtil;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/30/2016 1:26 AM
 */
@WebService(endpointInterface = "com.cms.service.CustomerCareHistoryService")
public class CustomerCareHistoryServiceImpl implements CustomerCareHistoryService {

    @Autowired
    CustomerCareHistoryBusinessInterface customerCareHistoryBusiness;

    @Autowired
    BaseFWServiceInterface customerStatusBusiness;

    @Override
    public String updateCustomerCareHistory(CustomerCareHistoryDTO customerCareHistoryDTO) {
        return customerCareHistoryBusiness.update(customerCareHistoryDTO);
    }

    @Override
    public String deleteCustomerCareHistory(Long id) {
        return customerCareHistoryBusiness.delete(id);
    }

    @Override
    public String deleteListCustomerCareHistory(List<CustomerCareHistoryDTO> customerCareHistoryListDTO) {
        return customerCareHistoryBusiness.delete(customerCareHistoryListDTO);
    }

    @Override
    public CustomerCareHistoryDTO findCustomerCareHistoryById(Long id) {
        if (id != null && id > 0) {
            return (CustomerCareHistoryDTO) customerCareHistoryBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<CustomerCareHistoryDTO> getListCustomerCareHistoryDTO(CustomerCareHistoryDTO customerCareHistoryDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (customerCareHistoryDTO != null) {
            return customerCareHistoryBusiness.search(customerCareHistoryDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertCustomerCareHistory(CustomerCareHistoryDTO customerCareHistoryDTO) {
        ResultDTO resultDTO = customerCareHistoryBusiness.createObject(customerCareHistoryDTO);
        if (ParamUtils.SUCCESS.equalsIgnoreCase(resultDTO.getMessage())) {
            //Tim kiem trang thai dich vu xem da co chua.
            CustomerStatusDTO customerStatusDTO = new CustomerStatusDTO();
            customerStatusDTO.setStaffCode(customerCareHistoryDTO.getStaffCode());
            customerStatusDTO.setService(customerCareHistoryDTO.getService());
            customerStatusDTO.setTaxCode(customerCareHistoryDTO.getTaxCode());
            //25/04/2017 - Them truong mineName
            customerStatusDTO.setMineName(customerCareHistoryDTO.getMineName());
            List<CustomerStatusDTO> lstReturn = customerStatusBusiness.search(customerStatusDTO, 0, 10, "asc", "staffCode");
            //Neu co roi thi thuc hien cap nhat thong tin trang thai
            if (!DataUtil.isListNullOrEmpty(lstReturn)) {
                customerStatusDTO = lstReturn.get(0);
                //25/04/2017 - Them truong lastNotes
                customerStatusDTO.setLastNotes(customerCareHistoryDTO.getNotes());
                customerStatusDTO.setLastUpdated(customerCareHistoryDTO.getCreateDate());
                customerStatusDTO.setStatus(customerCareHistoryDTO.getStatus());
                String update = customerStatusBusiness.update(customerStatusDTO);
                if (!ParamUtils.SUCCESS.equalsIgnoreCase(update)) {
                    resultDTO.setMessage(ParamUtils.FAIL);
                    customerCareHistoryBusiness.delete(Long.parseLong(resultDTO.getId()));
                }
            }//Neu chua co thi thuc hien them moi
            else {                
                //25/04/2017 - Them truong lastNotes
                customerStatusDTO.setLastNotes(customerCareHistoryDTO.getNotes());
                customerStatusDTO.setLastUpdated(customerCareHistoryDTO.getCreateDate());
                customerStatusDTO.setStatus(customerCareHistoryDTO.getStatus());
                customerStatusDTO.setCreatedDate(customerCareHistoryDTO.getCreateDate());
                customerStatusDTO.setStaffId(customerCareHistoryDTO.getStaffId());
                customerStatusDTO.setCustId(customerCareHistoryDTO.getCustId());
                ResultDTO result = customerStatusBusiness.createObject(customerStatusDTO);
                if (!ParamUtils.SUCCESS.equalsIgnoreCase(result.getMessage())) {
                    resultDTO.setMessage(ParamUtils.FAIL);
                }
            }
        }
        return resultDTO;
    }

    @Override
    public String insertOrUpdateListCustomerCareHistory(List<CustomerCareHistoryDTO> customerCareHistoryDTO) {
        return customerCareHistoryBusiness.insertList(customerCareHistoryDTO);
    }

    @Override
    public List<String> getSequenseCustomerCareHistory(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);

        return customerCareHistoryBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<CustomerCareHistoryDTO> getListCustomerCareHistoryByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<CustomerCareHistoryDTO> lstCustomerCareHistory = new ArrayList<>();
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

        lstCustomerCareHistory = customerCareHistoryBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstCustomerCareHistory;
    }

}
