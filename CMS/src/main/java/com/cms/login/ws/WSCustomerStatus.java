package com.cms.login.ws;

import com.cms.dto.CustomerStatusDTO;
import com.cms.dto.StatisticsCategoryListDTO;
import com.cms.dto.StatusQuantityDTO;
import com.cms.service.CustomerStatusServiceImpl;
import com.cms.utils.BundleUtils;
import com.vwf5.base.dto.ResultDTO;
import java.util.List;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/30/2016 5:27 PM
 */
public class WSCustomerStatus {
    
    List<CustomerStatusDTO> lstCustomerStatusDTO;
    List<CustomerStatusDTO> lstCustomerStatusConditionBean;

    //Duong dan Websevice
    public static final String WS_URL = BundleUtils.getStringCas("cms_ws_url");
    //Duong dan ten dich
    public static final String NAME_PATH = "xmlns:cms=\"http://service.cms.com/\"";
    //Url WS CustomerStatus
    public static final String SERVICE_URL = WS_URL + "customerStatusService?wsdl";

    //Lay toan bo danh sach CustomerStatus
    public static List<CustomerStatusDTO> getListCustomerStatusDTO(CustomerStatusDTO customerStatusDTO, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        CustomerStatusServiceImpl service = new CustomerStatusServiceImpl();
        return service.getListCustomerStatusDTO(customerStatusDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert CustomerStatus
    public static ResultDTO insertCustomerStatus(CustomerStatusDTO customerStatusDTO) {
        CustomerStatusServiceImpl service = new CustomerStatusServiceImpl();
        return service.insertCustomerStatus(customerStatusDTO);
    }

    //Update CustomerStatus
    public static String updateCustomerStatus(CustomerStatusDTO customerStatusDTO) {
        CustomerStatusServiceImpl service = new CustomerStatusServiceImpl();
        return service.updateCustomerStatus(customerStatusDTO);
    }

    //Delete CustomerStatus
    public static String deleteCustomerStatus(String id) {
        CustomerStatusServiceImpl service = new CustomerStatusServiceImpl();
        return service.deleteCustomerStatus(Long.parseLong(id));
    }

    //find CustomerStatus by id
    public static CustomerStatusDTO findCustomerStatusById(String id) {
        CustomerStatusServiceImpl service = new CustomerStatusServiceImpl();
        return service.findCustomerStatusById(Long.parseLong(id));
    }

    // Delete nhieu CustomerStatus
    public static String deleteLstCustomerStatus(List<CustomerStatusDTO> lstCustomerStatusDTO) {
        CustomerStatusServiceImpl service = new CustomerStatusServiceImpl();
        return service.deleteListCustomerStatus(lstCustomerStatusDTO);
    }

    // Them moi hoac cap nhat 1 danh sach CustomerStatus
    public static String insertOrUpdateListCustomerStatus(List<CustomerStatusDTO> lstCustomerStatusDTO) {
        CustomerStatusServiceImpl service = new CustomerStatusServiceImpl();
        return service.insertOrUpdateListCustomerStatus(lstCustomerStatusDTO);
    }
    
    //Thong ke danh sach goi
    public static List<StatisticsCategoryListDTO> getStatisticsCategoryListByStaff(
            String service,
            String staffCode,
            String categoryId,
            String beginLastUpdated,
            String endLastUpdated) {
        CustomerStatusServiceImpl serviceImpl = new CustomerStatusServiceImpl();
        return serviceImpl.getStatisticsCategoryListByStaff(service, staffCode, categoryId, beginLastUpdated, endLastUpdated);
    }
    
    //Thong ke trang thai - so luong
    public static List<StatusQuantityDTO> getStatusQuantity(String staffCode) {
        CustomerStatusServiceImpl serviceImpl = new CustomerStatusServiceImpl();
        return serviceImpl.getStatusQuantity(staffCode);
    }
    
    
}
