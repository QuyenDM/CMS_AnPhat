package com.cms.service;

import com.cms.business.service.ContractTemplateListBusinessInterface;
import com.cms.dto.ContractTemplateListDTO;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 03/11/2016 22:17:52
 */
@WebService(endpointInterface = "com.cms.service.ContractTemplateListService")
public class ContractTemplateListServiceImpl implements ContractTemplateListService {

    @Autowired
    ContractTemplateListBusinessInterface contractTemplateListBusiness;

    @Override
    public String updateContractTemplateList(ContractTemplateListDTO contractTemplateListDTO) {
        return contractTemplateListBusiness.update(contractTemplateListDTO);
    }

    @Override
    public String deleteContractTemplateList(Long id) {
        return contractTemplateListBusiness.delete(id);
    }

    @Override
    public String deleteListContractTemplateList(List<ContractTemplateListDTO> contractTemplateListListDTO) {
        return contractTemplateListBusiness.delete(contractTemplateListListDTO);
    }

    @Override
    public ContractTemplateListDTO findContractTemplateListById(Long id) {
        if (id != null && id > 0) {
            return (ContractTemplateListDTO) contractTemplateListBusiness.findById(id).toDTO();
        }
        return null;
    }

    public List<ContractTemplateListDTO> getListContractTemplateListDTO(ContractTemplateListDTO contractTemplateListDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (contractTemplateListDTO != null) {
            return contractTemplateListBusiness.search(contractTemplateListDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertContractTemplateList(ContractTemplateListDTO contractTemplateListDTO) {
        return contractTemplateListBusiness.createObject(contractTemplateListDTO);
    }

    @Override
    public String insertOrUpdateListContractTemplateList(List<ContractTemplateListDTO> contractTemplateListDTO) {
        return contractTemplateListBusiness.insertList(contractTemplateListDTO);
    }

    public List<String> getSequenseContractTemplateList(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);
        return contractTemplateListBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<ContractTemplateListDTO> getListContractTemplateListByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<ContractTemplateListDTO> lstContractTemplateList = new ArrayList<>();
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

        lstContractTemplateList = contractTemplateListBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstContractTemplateList;
    }
}
