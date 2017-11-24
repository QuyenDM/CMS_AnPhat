/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.business.service.TaxAuthorityBusinessInterface;
import com.cms.dto.TaxAuthorityDTO;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/23/2016 11:13 PM
 */
@WebService(endpointInterface = "com.cms.service.TaxAuthorityService")
public class TaxAuthorityServiceImpl implements TaxAuthorityService {

    @Autowired
    TaxAuthorityBusinessInterface taxAuthorityBusiness;

    @Override
    public String updateTaxAuthority(TaxAuthorityDTO taxAuthorityDTO) {
        return taxAuthorityBusiness.update(taxAuthorityDTO);
    }

    @Override
    public String deleteTaxAuthority(Long id) {
        return taxAuthorityBusiness.delete(id);
    }

    @Override
    public String deleteListTaxAuthority(List<TaxAuthorityDTO> taxAuthorityListDTO) {
        return taxAuthorityBusiness.delete(taxAuthorityListDTO);
    }

    @Override
    public TaxAuthorityDTO findTaxAuthorityById(Long id) {
        if (id != null && id > 0) {
            return (TaxAuthorityDTO) taxAuthorityBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<TaxAuthorityDTO> getListTaxAuthorityDTO(TaxAuthorityDTO taxAuthorityDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (taxAuthorityDTO != null) {
            return taxAuthorityBusiness.search(taxAuthorityDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertTaxAuthority(TaxAuthorityDTO taxAuthorityDTO) {
        return taxAuthorityBusiness.createObject(taxAuthorityDTO);
    }

    @Override
    public String insertOrUpdateListTaxAuthority(List<TaxAuthorityDTO> taxAuthorityDTO) {
        return taxAuthorityBusiness.insertList(taxAuthorityDTO);
    }

    @Override
    public List<String> getSequenseTaxAuthority(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);

        return taxAuthorityBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<TaxAuthorityDTO> getListTaxAuthorityByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<TaxAuthorityDTO> lstTaxAuthority = new ArrayList<>();
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

        lstTaxAuthority = taxAuthorityBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstTaxAuthority;
    }

    @Override
    public List<TaxAuthorityDTO> getListProvineTaxAuthority() {
        return taxAuthorityBusiness.getListProvineTaxAuthority();
    }

    @Override
    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineName(String mineName, Map<String, String> map) {
        return taxAuthorityBusiness.getListTaxAuthorityFromMineName(mineName, map);
    }

    @Override
    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineNameAndStaffCode(String mineName, String staffCode) {
        return taxAuthorityBusiness.getListTaxAuthorityFromMineName(mineName, staffCode);
    }

    @Override
    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineNameAndStaffCodeAndProvider(
            String mineName, String staffCode, String provider, String status) {
        return taxAuthorityBusiness.getListTaxAuthorityFromMineNameAndStaffCodeAndProvider(
                mineName, staffCode, provider, status);
    }
}
