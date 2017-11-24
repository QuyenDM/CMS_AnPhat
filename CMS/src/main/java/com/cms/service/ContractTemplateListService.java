/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.ContractTemplateListDTO;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/19/2016 12:12 AM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface ContractTemplateListService {

    @WebMethod
    public List<ContractTemplateListDTO> getListContractTemplateListDTO(@WebParam(name = "contractTemplateListDTO") ContractTemplateListDTO contractTemplateListDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod
    public String updateContractTemplateList(@WebParam(name = "contractTemplateListDTO") ContractTemplateListDTO contractTemplateListDTO);

    //
    @WebMethod
    public String deleteContractTemplateList(@WebParam(name = "contractTemplateListDTOId") Long id);

    //
    @WebMethod
    public String deleteListContractTemplateList(@WebParam(name = "contractTemplateListListDTO") List<ContractTemplateListDTO> contractTemplateListListDTO);

    //
    @WebMethod
    public ContractTemplateListDTO findContractTemplateListById(@WebParam(name = "contractTemplateListDTOId") Long id);

    //
    @WebMethod
    public ResultDTO insertContractTemplateList(@WebParam(name = "contractTemplateListDTO") ContractTemplateListDTO contractTemplateListDTO);

    //
    @WebMethod
    public String insertOrUpdateListContractTemplateList(@WebParam(name = "contractTemplateListDTO") List<ContractTemplateListDTO> contractTemplateListDTO);

    //
    @WebMethod
    public List<String> getSequenseContractTemplateList(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod
    public List<ContractTemplateListDTO> getListContractTemplateListByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
