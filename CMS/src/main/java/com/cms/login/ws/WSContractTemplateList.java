package com.cms.login.ws;

import com.cms.dto.ContractTemplateListDTO;
import com.cms.service.ContractTemplateListServiceImpl;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
public class WSContractTemplateList {

    //Lay toan bo danh sach 
    public static List<ContractTemplateListDTO> getListContractTemplateListDTO(ContractTemplateListDTO departmentDTO, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        ContractTemplateListServiceImpl service = new ContractTemplateListServiceImpl();
        return service.getListContractTemplateListDTO(departmentDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    public static List<ContractTemplateListDTO> getListContractTemplateListByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        ContractTemplateListServiceImpl service = new ContractTemplateListServiceImpl();
        return service.getListContractTemplateListByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert ContractTemplateList
    public static ResultDTO insertContractTemplateList(ContractTemplateListDTO departmentDTO) {
        ContractTemplateListServiceImpl service = new ContractTemplateListServiceImpl();
        return service.insertContractTemplateList(departmentDTO);
    }

    //Update ContractTemplateList
    public static String updateContractTemplateList(ContractTemplateListDTO departmentDTO) {
        ContractTemplateListServiceImpl service = new ContractTemplateListServiceImpl();
        return service.updateContractTemplateList(departmentDTO);
    }

    //Delete ContractTemplateList
    public static String deleteContractTemplateList(String id) {
        ContractTemplateListServiceImpl service = new ContractTemplateListServiceImpl();
        return service.deleteContractTemplateList(Long.parseLong(id));
    }

    //find ContractTemplateList by id
    public static ContractTemplateListDTO findContractTemplateListById(String id) {
        ContractTemplateListServiceImpl service = new ContractTemplateListServiceImpl();
        return service.findContractTemplateListById(Long.parseLong(id));
    }

    // xoa nhieu ContractTemplateList
    public static String deleteLstContractTemplateList(List<ContractTemplateListDTO> lstContractTemplateListDTO) {
        ContractTemplateListServiceImpl service = new ContractTemplateListServiceImpl();
        return service.deleteListContractTemplateList(lstContractTemplateListDTO);
    }

    // Them moi hoac cap nhat 1 danh sach ContractTemplateList
    public static String insertOrUpdateListContractTemplateList(List<ContractTemplateListDTO> lstContractTemplateListDTO) {
        ContractTemplateListServiceImpl service = new ContractTemplateListServiceImpl();
        return service.insertOrUpdateListContractTemplateList(lstContractTemplateListDTO);
    }

}
