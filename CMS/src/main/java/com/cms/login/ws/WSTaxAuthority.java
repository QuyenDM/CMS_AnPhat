package com.cms.login.ws;

import com.cms.dto.TaxAuthorityDTO;
import com.cms.service.TaxAuthorityServiceImpl;
import com.cms.utils.BundleUtils;
import com.vwf5.base.dto.ResultDTO;
import java.util.List;
import java.util.Map;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/23/2016 11:13 PM
 */
public class WSTaxAuthority {

    List<TaxAuthorityDTO> lstTaxAuthorityDTO;
    List<TaxAuthorityDTO> lstTaxAuthorityConditionBean;

    //Duong dan Websevice
    public static final String WS_URL = BundleUtils.getStringCas("cms_ws_url");
    //Duong dan ten dich
    public static final String NAME_PATH = "xmlns:cms=\"http://service.cms.com\"";
    //Url WS TaxAuthority
    public static final String SERVICE_URL = WS_URL + "taxAuthorityService?wsdl";

    //Lay toan bo danh sach TaxAuthority
    public static List<TaxAuthorityDTO> getListTaxAuthorityDTO(TaxAuthorityDTO taxAuthorityDTO, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        TaxAuthorityServiceImpl service = new TaxAuthorityServiceImpl();
        return service.getListTaxAuthorityDTO(taxAuthorityDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert TaxAuthority
    public static ResultDTO insertTaxAuthority(TaxAuthorityDTO taxAuthorityDTO) {
        TaxAuthorityServiceImpl service = new TaxAuthorityServiceImpl();
        return service.insertTaxAuthority(taxAuthorityDTO);
    }

    //Update TaxAuthority
    public static String updateTaxAuthority(TaxAuthorityDTO taxAuthorityDTO) {
        TaxAuthorityServiceImpl service = new TaxAuthorityServiceImpl();
        return service.updateTaxAuthority(taxAuthorityDTO);
    }

    //Delete TaxAuthority
    public static String deleteTaxAuthority(String id) {
        TaxAuthorityServiceImpl service = new TaxAuthorityServiceImpl();
        return service.deleteTaxAuthority(Long.parseLong(id));
    }

    //find TaxAuthority by id
    public static TaxAuthorityDTO findTaxAuthorityById(String id) {
        TaxAuthorityServiceImpl service = new TaxAuthorityServiceImpl();
        return service.findTaxAuthorityById(Long.parseLong(id));
    }

    // Delete nhieu TaxAuthority
    public static String deleteLstTaxAuthority(List<TaxAuthorityDTO> lstTaxAuthorityDTO) {
        TaxAuthorityServiceImpl service = new TaxAuthorityServiceImpl();
        return service.deleteListTaxAuthority(lstTaxAuthorityDTO);
    }

    // Them moi hoac cap nhat 1 danh sach TaxAuthority
    public static String insertOrUpdateListTaxAuthority(List<TaxAuthorityDTO> lstTaxAuthorityDTO) {
        TaxAuthorityServiceImpl service = new TaxAuthorityServiceImpl();
        return service.insertOrUpdateListTaxAuthority(lstTaxAuthorityDTO);
    }

    public static List<TaxAuthorityDTO> getListProvineTaxAuthority() {
        TaxAuthorityServiceImpl service = new TaxAuthorityServiceImpl();
        return service.getListProvineTaxAuthority();
    }

    public static List<TaxAuthorityDTO> getListTaxAuthorityFromMineName(String mineName, Map<String, String> map) {
        TaxAuthorityServiceImpl service = new TaxAuthorityServiceImpl();
        return service.getListTaxAuthorityFromMineName(mineName, map);
    }

    public static List<TaxAuthorityDTO> getListTaxAuthorityFromMineNameAndStaffCode(String mineName, String staffCode) {
        TaxAuthorityServiceImpl service = new TaxAuthorityServiceImpl();
        return service.getListTaxAuthorityFromMineNameAndStaffCode(mineName, staffCode);
    }

    public static List<TaxAuthorityDTO> getListTaxAuthorityFromMineNameAndStaffCodeAndProvider(
            String mineName, String staffCode, String provider, String status) {
        TaxAuthorityServiceImpl service = new TaxAuthorityServiceImpl();
        return service.getListTaxAuthorityFromMineNameAndStaffCodeAndProvider(
                mineName, staffCode, provider, status);
    }
}
