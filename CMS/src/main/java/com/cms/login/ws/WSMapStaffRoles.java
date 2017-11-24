package com.cms.login.ws;

import com.cms.login.dto.MapStaffRolesDTO;
import com.cms.service.MapStaffRolesServiceImpl;

import com.vwf5.base.dto.ResultDTO;
import java.util.List;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/4/2016 12:02 AM
 */
public class WSMapStaffRoles {

    List<MapStaffRolesDTO> lstMapStaffRolesDTO;
    List<MapStaffRolesDTO> lstMapStaffRolesConditionBean;

    //Lay toan bo danh sach MapStaffRoles
    public static List<MapStaffRolesDTO> getListMapStaffRolesDTO(MapStaffRolesDTO mapStaffRolesDTO, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        MapStaffRolesServiceImpl service = new MapStaffRolesServiceImpl();
        return service.getListMapStaffRolesDTO(mapStaffRolesDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert MapStaffRoles
    public static ResultDTO insertMapStaffRoles(MapStaffRolesDTO mapStaffRolesDTO) {
        MapStaffRolesServiceImpl service = new MapStaffRolesServiceImpl();
        return service.insertMapStaffRoles(mapStaffRolesDTO);
    }

    //Update MapStaffRoles
    public static String updateMapStaffRoles(MapStaffRolesDTO mapStaffRolesDTO) {
        MapStaffRolesServiceImpl service = new MapStaffRolesServiceImpl();
        return service.updateMapStaffRoles(mapStaffRolesDTO);
    }

    //Delete MapStaffRoles
    public static String deleteMapStaffRoles(String id) {
        MapStaffRolesServiceImpl service = new MapStaffRolesServiceImpl();
        return service.deleteMapStaffRoles(Long.parseLong(id));
    }

    //find MapStaffRoles by id
    public static MapStaffRolesDTO findMapStaffRolesById(String id) {
        MapStaffRolesServiceImpl service = new MapStaffRolesServiceImpl();
        return service.findMapStaffRolesById(Long.parseLong(id));
    }

    // Delete nhieu MapStaffRoles
    public static String deleteLstMapStaffRoles(List<MapStaffRolesDTO> lstMapStaffRolesDTO) {
        MapStaffRolesServiceImpl service = new MapStaffRolesServiceImpl();
        return service.deleteListMapStaffRoles(lstMapStaffRolesDTO);
    }

    // Them moi hoac cap nhat 1 danh sach MapStaffRoles
    public static String insertOrUpdateListMapStaffRoles(List<MapStaffRolesDTO> lstMapStaffRolesDTO) {
        MapStaffRolesServiceImpl service = new MapStaffRolesServiceImpl();
        return service.insertOrUpdateListMapStaffRoles(lstMapStaffRolesDTO);
    }
}
