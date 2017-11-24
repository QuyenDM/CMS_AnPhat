package com.cms.login.ws;

import com.cms.dto.CategoryListDTO;
import com.cms.service.CategoryListServiceImpl;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.List;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
public class WSCategoryList {

    //Lay toan bo danh sach 
    public static List<CategoryListDTO> getListCategoryListDTO(CategoryListDTO departmentDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        CategoryListServiceImpl service = new CategoryListServiceImpl();
        return service.getListCategoryListDTO(departmentDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    public static List<CategoryListDTO> getListCategoryListByCondition(List<ConditionBean> lstCon, int rowStart, int maxRow, String sortType, String sortFieldList) throws Exception {
        CategoryListServiceImpl service = new CategoryListServiceImpl();
        return service.getListCategoryListByCondition(lstCon, rowStart, maxRow, sortType, sortFieldList);
    }

    //Insert CategoryList
    public static ResultDTO insertCategoryList(CategoryListDTO departmentDTO) {
        CategoryListServiceImpl service = new CategoryListServiceImpl();
        return service.insertCategoryList(departmentDTO);
    }

    //Update CategoryList
    public static String updateCategoryList(CategoryListDTO departmentDTO) {
        CategoryListServiceImpl service = new CategoryListServiceImpl();
        return service.updateCategoryList(departmentDTO);
    }

    //Delete CategoryList
    public static String deleteCategoryList(String id) {
        CategoryListServiceImpl service = new CategoryListServiceImpl();
        return service.deleteCategoryList(Long.parseLong(id));
    }

    //find CategoryList by id
    public static CategoryListDTO findCategoryListById(String id) {
        CategoryListServiceImpl service = new CategoryListServiceImpl();
        return service.findCategoryListById(Long.parseLong(id));
    }

    // xoa nhieu CategoryList
    public static String deleteLstCategoryList(List<CategoryListDTO> lstCategoryListDTO) {
        CategoryListServiceImpl service = new CategoryListServiceImpl();
        return service.deleteListCategoryList(lstCategoryListDTO);
    }

    // Them moi hoac cap nhat 1 danh sach CategoryList
    public static String insertOrUpdateListCategoryList(List<CategoryListDTO> lstCategoryListDTO) {
        CategoryListServiceImpl service = new CategoryListServiceImpl();
        return service.insertOrUpdateListCategoryList(lstCategoryListDTO);
    }

    // lay categoryList theo ma nhan vien 
    public static List<CategoryListDTO> getCategoryListFromStaffCode(String staffCode, String serviceStr) {
        CategoryListServiceImpl service = new CategoryListServiceImpl();
        return service.getCategoryListFromStaffCode(staffCode, serviceStr);
    }

}
