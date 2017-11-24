package com.cms.login.ws;

import com.cms.dto.MapStaffCustomerDTO;
import com.cms.service.MapStaffCustomerServiceImpl;
import com.cms.utils.BundleUtils;
import com.vwf5.base.dto.ResultDTO;
import java.util.List;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/4/2016 12:02 AM
 */
public class WSMapStaffCustomer {

    List<MapStaffCustomerDTO> lstMapStaffCustomerDTO;
    List<MapStaffCustomerDTO> lstMapStaffCustomerConditionBean;

    //Duong dan Websevice
    public static final String WS_URL = BundleUtils.getStringCas("cms_ws_url");
    //Duong dan ten dich
    public static final String NAME_PATH = "xmlns:cms=\"http://service.cms.com\"";
    //Url WS MapStaffCustomer
    public static final String SERVICE_URL = WS_URL + "mapStaffCustomerService?wsdl";

    //Lay toan bo danh sach MapStaffCustomer
    public static List<MapStaffCustomerDTO> getListMapStaffCustomerDTO(MapStaffCustomerDTO mapStaffCustomerDTO, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        MapStaffCustomerServiceImpl service = new MapStaffCustomerServiceImpl();
        return service.getListMapStaffCustomerDTO(mapStaffCustomerDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert MapStaffCustomer
    public static ResultDTO insertMapStaffCustomer(MapStaffCustomerDTO mapStaffCustomerDTO) {
        MapStaffCustomerServiceImpl service = new MapStaffCustomerServiceImpl();
        return service.insertMapStaffCustomer(mapStaffCustomerDTO);
    }

    //Update MapStaffCustomer
    public static String updateMapStaffCustomer(MapStaffCustomerDTO mapStaffCustomerDTO) {
        MapStaffCustomerServiceImpl service = new MapStaffCustomerServiceImpl();
        return service.updateMapStaffCustomer(mapStaffCustomerDTO);
    }

    //Delete MapStaffCustomer
    public static String deleteMapStaffCustomer(String id) {
        MapStaffCustomerServiceImpl service = new MapStaffCustomerServiceImpl();
        return service.deleteMapStaffCustomer(Long.parseLong(id));
    }

    //find MapStaffCustomer by id
    public static MapStaffCustomerDTO findMapStaffCustomerById(String id) {
        MapStaffCustomerServiceImpl service = new MapStaffCustomerServiceImpl();
        return service.findMapStaffCustomerById(Long.parseLong(id));
    }

    // Delete nhieu MapStaffCustomer
    public static String deleteLstMapStaffCustomer(List<MapStaffCustomerDTO> lstMapStaffCustomerDTO) {
        MapStaffCustomerServiceImpl service = new MapStaffCustomerServiceImpl();
        return service.deleteListMapStaffCustomer(lstMapStaffCustomerDTO);
    }

    // Them moi hoac cap nhat 1 danh sach MapStaffCustomer
    public static String insertOrUpdateListMapStaffCustomer(List<MapStaffCustomerDTO> lstMapStaffCustomerDTO) {
        MapStaffCustomerServiceImpl service = new MapStaffCustomerServiceImpl();
        return service.insertOrUpdateListMapStaffCustomer(lstMapStaffCustomerDTO);
    }
}
