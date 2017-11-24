package com.cms.login.ws;

import com.cms.dto.CustomerContactDTO;
import com.cms.service.CustomerContactServiceImpl;
import com.cms.utils.BundleUtils;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
public class WSCustomerContact {

    //Lay toan bo danh sach 
    public static List<CustomerContactDTO> getListCustomerContactDTO(CustomerContactDTO departmentDTO, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        CustomerContactServiceImpl service = new CustomerContactServiceImpl();
        return service.getListCustomerContactDTO(departmentDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    public static List<CustomerContactDTO> getListCustomerContactByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        CustomerContactServiceImpl service = new CustomerContactServiceImpl();
        return service.getListCustomerContactByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert CustomerContact
    public static ResultDTO insertCustomerContact(CustomerContactDTO departmentDTO) {
        CustomerContactServiceImpl service = new CustomerContactServiceImpl();
        return service.insertCustomerContact(departmentDTO);
    }

    //Update CustomerContact
    public static String updateCustomerContact(CustomerContactDTO departmentDTO) {
        CustomerContactServiceImpl service = new CustomerContactServiceImpl();
        return service.updateCustomerContact(departmentDTO);
    }

    //Delete CustomerContact
    public static String deleteCustomerContact(String id) {
        CustomerContactServiceImpl service = new CustomerContactServiceImpl();
        return service.deleteCustomerContact(Long.parseLong(id));
    }

    //find CustomerContact by id
    public static CustomerContactDTO findCustomerContactById(String id) {
        CustomerContactServiceImpl service = new CustomerContactServiceImpl();
        return service.findCustomerContactById(Long.parseLong(id));
    }

    // xoa nhieu CustomerContact
    public static String deleteLstCustomerContact(List<CustomerContactDTO> lstCustomerContactDTO) {
        CustomerContactServiceImpl service = new CustomerContactServiceImpl();
        return service.deleteListCustomerContact(lstCustomerContactDTO);
    }

    // Them moi hoac cap nhat 1 danh sach CustomerContact
    public static String insertOrUpdateListCustomerContact(List<CustomerContactDTO> lstCustomerContactDTO) {
        CustomerContactServiceImpl service = new CustomerContactServiceImpl();
        return service.insertOrUpdateListCustomerContact(lstCustomerContactDTO);
    }

}
