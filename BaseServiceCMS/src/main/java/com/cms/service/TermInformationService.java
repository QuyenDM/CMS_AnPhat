/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.TermInformationDTO;
import com.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import com.vfw5.base.dto.ResultDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/19/2016 12:01 AM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface TermInformationService {

    @WebMethod
    public List<TermInformationDTO> getListTermInformationDTO(@WebParam(name = "termInformationDTO") TermInformationDTO termInformationDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod
    public String updateTermInformation(@WebParam(name = "termInformationDTO") TermInformationDTO termInformationDTO);

    //
    @WebMethod
    public String deleteTermInformation(@WebParam(name = "termInformationDTOId") Long id);

    //
    @WebMethod
    public String deleteListTermInformation(@WebParam(name = "termInformationListDTO") List<TermInformationDTO> termInformationListDTO);

    //
    @WebMethod
    public TermInformationDTO findTermInformationById(@WebParam(name = "termInformationDTOId") Long id);

    //
    @WebMethod
    public ResultDTO insertTermInformation(@WebParam(name = "termInformationDTO") TermInformationDTO termInformationDTO);

    //
    @WebMethod
    public String insertOrUpdateListTermInformation(@WebParam(name = "termInformationDTO") List<TermInformationDTO> termInformationDTO);

    @WebMethod
    public ResultDTO insertListTermInformation(@WebParam(name = "termInformationDTO") List<TermInformationDTO> termInformationDTO);

    //
    @WebMethod
    public List<String> getSequenseTermInformation(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod
    public List<TermInformationDTO> getListTermInformationByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

}
