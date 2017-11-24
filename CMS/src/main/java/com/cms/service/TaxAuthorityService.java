/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.TaxAuthorityDTO;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.Map;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/23/2016 11:13 PM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface TaxAuthorityService {

    @WebMethod
    public List<TaxAuthorityDTO> getListTaxAuthorityDTO(@WebParam(name = "taxAuthorityDTO") TaxAuthorityDTO taxAuthorityDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod
    public String updateTaxAuthority(@WebParam(name = "taxAuthorityDTO") TaxAuthorityDTO taxAuthorityDTO);

    //
    @WebMethod
    public String deleteTaxAuthority(@WebParam(name = "taxAuthorityDTOId") Long id);

    //
    @WebMethod
    public String deleteListTaxAuthority(@WebParam(name = "taxAuthorityListDTO") List<TaxAuthorityDTO> taxAuthorityListDTO);

    //
    @WebMethod
    public TaxAuthorityDTO findTaxAuthorityById(@WebParam(name = "taxAuthorityDTOId") Long id);

    //
    @WebMethod
    public ResultDTO insertTaxAuthority(@WebParam(name = "taxAuthorityDTO") TaxAuthorityDTO taxAuthorityDTO);

    //
    @WebMethod
    public String insertOrUpdateListTaxAuthority(@WebParam(name = "taxAuthorityDTO") List<TaxAuthorityDTO> taxAuthorityDTO);

    //
    @WebMethod
    public List<String> getSequenseTaxAuthority(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod
    public List<TaxAuthorityDTO> getListTaxAuthorityByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod
    public List<TaxAuthorityDTO> getListProvineTaxAuthority();
    
    @WebMethod
    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineName(@WebParam(name = "mineName") String mineName, @WebParam(name = "map") Map<String, String> map);
    
    @WebMethod
    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineNameAndStaffCode(@WebParam(name = "mineName") String mineName, @WebParam(name = "staffCode") String staffCode );
    
    @WebMethod
    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineNameAndStaffCodeAndProvider(
            @WebParam(name = "mineName") String mineName,
            @WebParam(name = "staffCode") String staffCode,
            @WebParam(name = "provider") String provider,
            @WebParam(name = "status") String status);
}
