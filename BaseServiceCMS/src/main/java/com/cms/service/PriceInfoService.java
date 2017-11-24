package com.cms.service;

import com.cms.dto.PriceInfoDTO;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.WebParam;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 03/11/2016 22:17:52
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface PriceInfoService {

    @WebMethod(operationName = "getListPriceInfoDTO")
    public List<PriceInfoDTO> getListPriceInfoDTO(@WebParam(name = "priceInfoDTO") PriceInfoDTO priceInfoDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod(operationName = "updatePriceInfo")
    public String updatePriceInfo(@WebParam(name = "priceInfoDTO") PriceInfoDTO priceInfoDTO);

    //
    @WebMethod(operationName = "deletePriceInfo")
    public String deletePriceInfo(@WebParam(name = "priceInfoDTOId") Long id);

    //
    @WebMethod(operationName = "deleteListPriceInfo")
    public String deleteListPriceInfo(@WebParam(name = "priceInfoListDTO") List<PriceInfoDTO> priceInfoListDTO);

    //
    @WebMethod(operationName = "findPriceInfoById")
    public PriceInfoDTO findPriceInfoById(@WebParam(name = "priceInfoDTOId") Long id);

    //
    @WebMethod(operationName = "insertPriceInfo")
    public ResultDTO insertPriceInfo(@WebParam(name = "priceInfoDTO") PriceInfoDTO priceInfoDTO);

    //
    @WebMethod(operationName = "insertOrUpdateListPriceInfo")
    public String insertOrUpdateListPriceInfo(@WebParam(name = "priceInfoDTO") List<PriceInfoDTO> priceInfoDTO);

    //
    @WebMethod(operationName = "getSequensePriceInfo")
    public List<String> getSequensePriceInfo(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //
    @WebMethod(operationName = "getListPriceInfoByCondition")
    public List<PriceInfoDTO> getListPriceInfoByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
