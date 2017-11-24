package com.cms.service;

import com.cms.dto.ContractTemplateListDTO;
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
public interface ContractTemplateListService {

    @WebMethod(operationName = "getListContractTemplateListDTO")
    public List<ContractTemplateListDTO> getListContractTemplateListDTO(@WebParam(name = "contractTemplateListDTO") ContractTemplateListDTO contractTemplateListDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod(operationName = "updateContractTemplateList")
    public String updateContractTemplateList(@WebParam(name = "contractTemplateListDTO") ContractTemplateListDTO contractTemplateListDTO);

    //
    @WebMethod(operationName = "deleteContractTemplateList")
    public String deleteContractTemplateList(@WebParam(name = "contractTemplateListDTOId") Long id);

    //
    @WebMethod(operationName = "deleteListContractTemplateList")
    public String deleteListContractTemplateList(@WebParam(name = "contractTemplateListListDTO") List<ContractTemplateListDTO> contractTemplateListListDTO);

    //
    @WebMethod(operationName = "findContractTemplateListById")
    public ContractTemplateListDTO findContractTemplateListById(@WebParam(name = "contractTemplateListDTOId") Long id);

    //
    @WebMethod(operationName = "insertContractTemplateList")
    public ResultDTO insertContractTemplateList(@WebParam(name = "contractTemplateListDTO") ContractTemplateListDTO contractTemplateListDTO);

    //
    @WebMethod(operationName = "insertOrUpdateListContractTemplateList")
    public String insertOrUpdateListContractTemplateList(@WebParam(name = "contractTemplateListDTO") List<ContractTemplateListDTO> contractTemplateListDTO);

    //
    @WebMethod(operationName = "getSequenseContractTemplateList")
    public List<String> getSequenseContractTemplateList(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //
    @WebMethod(operationName = "getListContractTemplateListByCondition")
    public List<ContractTemplateListDTO> getListContractTemplateListByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);
}
