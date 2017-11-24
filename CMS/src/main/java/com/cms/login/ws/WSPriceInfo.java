package com.cms.login.ws;

import com.cms.dto.PriceInfoDTO;
import com.cms.service.PriceInfoServiceImpl;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
public class WSPriceInfo {

    //Lay toan bo danh sach 
    public static List<PriceInfoDTO> getListPriceInfoDTO(PriceInfoDTO priceInfoDTO, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        PriceInfoServiceImpl service = new PriceInfoServiceImpl();
        return service.getListPriceInfoDTO(priceInfoDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    public static List<PriceInfoDTO> getListPriceInfoByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        PriceInfoServiceImpl service = new PriceInfoServiceImpl();
        return service.getListPriceInfoByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert PriceInfo
    public static ResultDTO insertPriceInfo(PriceInfoDTO priceInfoDTO) {
        PriceInfoServiceImpl service = new PriceInfoServiceImpl();
        return service.insertPriceInfo(priceInfoDTO);
    }

    //Update PriceInfo
    public static String updatePriceInfo(PriceInfoDTO priceInfoDTO) {
        PriceInfoServiceImpl service = new PriceInfoServiceImpl();
        return service.updatePriceInfo(priceInfoDTO);
    }

    //Delete PriceInfo
    public static String deletePriceInfo(String id) {
        PriceInfoServiceImpl service = new PriceInfoServiceImpl();
        return service.deletePriceInfo(Long.parseLong(id));
    }

    //find PriceInfo by id
    public static PriceInfoDTO findPriceInfoById(String id) {
        PriceInfoServiceImpl service = new PriceInfoServiceImpl();
        return service.findPriceInfoById(Long.parseLong(id));
    }

    // xoa nhieu PriceInfo
    public static String deleteLstPriceInfo(List<PriceInfoDTO> lstPriceInfoDTO) {
        PriceInfoServiceImpl service = new PriceInfoServiceImpl();
        return service.deleteListPriceInfo(lstPriceInfoDTO);
    }

    // Them moi hoac cap nhat 1 danh sach PriceInfo
    public static String insertOrUpdateListPriceInfo(List<PriceInfoDTO> lstPriceInfoDTO) {
        PriceInfoServiceImpl service = new PriceInfoServiceImpl();
        return service.insertOrUpdateListPriceInfo(lstPriceInfoDTO);
    }

}
