/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.StatisticStaffPointDTO;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/19/2016 12:12 AM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface StatisticStaffPointService {

    @WebMethod
    public List<StatisticStaffPointDTO> getListStatisticStaffPointDTO(@WebParam(name = "statisticStaffPointDTO") StatisticStaffPointDTO statisticStaffPointDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod
    public String updateStatisticStaffPoint(@WebParam(name = "statisticStaffPointDTO") StatisticStaffPointDTO statisticStaffPointDTO);

    //
    @WebMethod
    public String deleteStatisticStaffPoint(@WebParam(name = "statisticStaffPointDTOId") Long id);

    //
    @WebMethod
    public String deleteListStatisticStaffPoint(@WebParam(name = "statisticStaffPointListDTO") List<StatisticStaffPointDTO> statisticStaffPointListDTO);

    //
    @WebMethod
    public StatisticStaffPointDTO findStatisticStaffPointById(@WebParam(name = "statisticStaffPointDTOId") Long id);

    //
    @WebMethod
    public ResultDTO insertStatisticStaffPoint(@WebParam(name = "statisticStaffPointDTO") StatisticStaffPointDTO statisticStaffPointDTO);

    //
    @WebMethod
    public String insertOrUpdateListStatisticStaffPoint(@WebParam(name = "statisticStaffPointDTO") List<StatisticStaffPointDTO> statisticStaffPointDTO);

    //
    @WebMethod
    public List<String> getSequenseStatisticStaffPoint(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod
    public List<StatisticStaffPointDTO> getListStatisticStaffPointByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
