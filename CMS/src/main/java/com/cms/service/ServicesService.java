package com.cms.service;

import com.cms.dto.ServicesDTO;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author <%=Author%> @version 1.0
 * @since 06/08/2016 10:03:59
 */
public interface ServicesService {

    @WebMethod(operationName = "getListServicesDTO")
    public List<ServicesDTO> getListServicesDTO(@WebParam(name = "servicesDTO") ServicesDTO servicesDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod(operationName = "updateServices")
    public String updateServices(@WebParam(name = "servicesDTO") ServicesDTO servicesDTO);

    //
    @WebMethod(operationName = "deleteServices")
    public String deleteServices(@WebParam(name = "servicesDTOId") Long id);

    //
    @WebMethod(operationName = "deleteListServices")
    public String deleteListServices(@WebParam(name = "servicesListDTO") List<ServicesDTO> servicesListDTO);

    //
    @WebMethod(operationName = "findServicesById")
    public ServicesDTO findServicesById(@WebParam(name = "servicesDTOId") Long id);

    //
    @WebMethod(operationName = "insertServices")
    public ResultDTO insertServices(@WebParam(name = "servicesDTO") ServicesDTO servicesDTO);

    //
    @WebMethod(operationName = "insertOrUpdateListServices")
    public String insertOrUpdateListServices(@WebParam(name = "servicesDTO") List<ServicesDTO> servicesDTO);

    //
    @WebMethod(operationName = "getSequenseServices")
    public List<String> getSequenseServices(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //
    @WebMethod(operationName = "getListServicesByCondition")
    public List<ServicesDTO> getListServicesByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
