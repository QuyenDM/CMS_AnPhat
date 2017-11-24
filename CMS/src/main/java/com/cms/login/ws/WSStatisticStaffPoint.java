package com.cms.login.ws;

import com.cms.dto.StatisticStaffPointDTO;
import com.cms.service.StatisticStaffPointServiceImpl;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
public class WSStatisticStaffPoint {

    //Lay toan bo danh sach 
    public static List<StatisticStaffPointDTO> getListStatisticStaffPointDTO(StatisticStaffPointDTO statisticStaffPointDTO, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        StatisticStaffPointServiceImpl service = new StatisticStaffPointServiceImpl();
        return service.getListStatisticStaffPointDTO(statisticStaffPointDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    public static List<StatisticStaffPointDTO> getListStatisticStaffPointByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        StatisticStaffPointServiceImpl service = new StatisticStaffPointServiceImpl();
        return service.getListStatisticStaffPointByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert StatisticStaffPoint
    public static ResultDTO insertStatisticStaffPoint(StatisticStaffPointDTO statisticStaffPointDTO) {
        StatisticStaffPointServiceImpl service = new StatisticStaffPointServiceImpl();
        return service.insertStatisticStaffPoint(statisticStaffPointDTO);
    }

    //Update StatisticStaffPoint
    public static String updateStatisticStaffPoint(StatisticStaffPointDTO statisticStaffPointDTO) {
        StatisticStaffPointServiceImpl service = new StatisticStaffPointServiceImpl();
        return service.updateStatisticStaffPoint(statisticStaffPointDTO);
    }

    //Delete StatisticStaffPoint
    public static String deleteStatisticStaffPoint(String id) {
        StatisticStaffPointServiceImpl service = new StatisticStaffPointServiceImpl();
        return service.deleteStatisticStaffPoint(Long.parseLong(id));
    }

    //find StatisticStaffPoint by id
    public static StatisticStaffPointDTO findStatisticStaffPointById(String id) {
        StatisticStaffPointServiceImpl service = new StatisticStaffPointServiceImpl();
        return service.findStatisticStaffPointById(Long.parseLong(id));
    }

    // xoa nhieu StatisticStaffPoint
    public static String deleteLstStatisticStaffPoint(List<StatisticStaffPointDTO> lstStatisticStaffPointDTO) {
        StatisticStaffPointServiceImpl service = new StatisticStaffPointServiceImpl();
        return service.deleteListStatisticStaffPoint(lstStatisticStaffPointDTO);
    }

    // Them moi hoac cap nhat 1 danh sach StatisticStaffPoint
    public static String insertOrUpdateListStatisticStaffPoint(List<StatisticStaffPointDTO> lstStatisticStaffPointDTO) {
        StatisticStaffPointServiceImpl service = new StatisticStaffPointServiceImpl();
        return service.insertOrUpdateListStatisticStaffPoint(lstStatisticStaffPointDTO);
    }

}
