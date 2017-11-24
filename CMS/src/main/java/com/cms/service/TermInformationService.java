/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.TermInformationDTO;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebParam;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 16-Apr-15 1:28 PM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface TermInformationService {

    @WebMethod(operationName = "getListTermInformationDTO")
//    @WebResult(name = "termInformationDTO", targetNamespace = "http://service.cms.com")
    public List<TermInformationDTO> getListTermInformationDTO(@WebParam(name = "termInformationDTO") TermInformationDTO termInformationDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod(operationName = "updateTermInformation")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String updateTermInformation(@WebParam(name = "termInformationDTO") TermInformationDTO termInformationDTO);

    //
    @WebMethod(operationName = "deleteTermInformation")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteTermInformation(@WebParam(name = "termInformationDTOId") Long id);

    //
    @WebMethod(operationName = "deleteListTermInformation")
//    @WebResult(name = "message", targetNamespace = "http://service.cms.com")
    public String deleteListTermInformation(@WebParam(name = "termInformationListDTO") List<TermInformationDTO> termInformationListDTO);

    //
    @WebMethod(operationName = "findTermInformationById")
//    @WebResult(name = "termInformation", targetNamespace = "http://service.cms.com")
    public TermInformationDTO findTermInformationById(@WebParam(name = "termInformationDTOId") Long id);

    @WebMethod(operationName = "insertTermInformation")
//    @WebResult(name = "resultDTO", targetNamespace = "http://service.cms.com")
    public ResultDTO insertTermInformation(@WebParam(name = "termInformationDTO") TermInformationDTO termInformationDTO);

    //
    @WebMethod(operationName = "insertOrUpdateListTermInformation")
//    @WebResult(name = "insertListTermInformation", targetNamespace = "http://service.cms.com")
    public String insertOrUpdateListTermInformation(@WebParam(name = "termInformationDTO") List<TermInformationDTO> termInformationDTO);

    //    
    @WebMethod(operationName = "getSequenseTermInformation")
//    @WebResult(name = "getSequense", targetNamespace = "http://service.cms.com")
    public List<String> getSequenseTermInformation(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //
    @WebMethod(operationName = "getListTermInformationByCondition")
//    @WebResult(name = "TermInformationDTO", targetNamespace = "http://service.cms.com")
    public List<TermInformationDTO> getListTermInformationByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod
    public ResultDTO insertListTermInformation(@WebParam(name = "termInformationDTO") List<TermInformationDTO> termInformationDTO);

//    @WebMethod(operationName = "getTermInformationInfor")
//    @WebResult(name = "termInformationInforDTO", targetNamespace = "http://service.cms.com")
//    public TermInformationInforDTO getTermInformationInfor(@WebParam(name = "userName") String userName);
}
