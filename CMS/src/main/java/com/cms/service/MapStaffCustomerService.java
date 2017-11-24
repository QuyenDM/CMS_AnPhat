/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.MapStaffCustomerDTO;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/4/2016 12:02 AM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface MapStaffCustomerService {

    @WebMethod
    public List<MapStaffCustomerDTO> getListMapStaffCustomerDTO(@WebParam(name = "mapStaffCustomerDTO") MapStaffCustomerDTO mapStaffCustomerDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod
    public String updateMapStaffCustomer(@WebParam(name = "mapStaffCustomerDTO") MapStaffCustomerDTO mapStaffCustomerDTO);

    //
    @WebMethod
    public String deleteMapStaffCustomer(@WebParam(name = "mapStaffCustomerDTOId") Long id);

    //
    @WebMethod
    public String deleteListMapStaffCustomer(@WebParam(name = "mapStaffCustomerListDTO") List<MapStaffCustomerDTO> mapStaffCustomerListDTO);

    //
    @WebMethod
    public MapStaffCustomerDTO findMapStaffCustomerById(@WebParam(name = "mapStaffCustomerDTOId") Long id);

    //
    @WebMethod
    public ResultDTO insertMapStaffCustomer(@WebParam(name = "mapStaffCustomerDTO") MapStaffCustomerDTO mapStaffCustomerDTO);

    //
    @WebMethod
    public String insertOrUpdateListMapStaffCustomer(@WebParam(name = "mapStaffCustomerDTO") List<MapStaffCustomerDTO> mapStaffCustomerDTO);

    //
    @WebMethod
    public List<String> getSequenseMapStaffCustomer(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod
    public List<MapStaffCustomerDTO> getListMapStaffCustomerByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
