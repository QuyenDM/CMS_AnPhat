/**
 */
package com.cms.service;

import com.cms.business.service.CategoryListBusinessInterface;
import com.cms.dto.CategoryListDTO;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/19/2016 12:12 AM
 */
@WebService(endpointInterface = "com.cms.service.CategoryListService")
public class CategoryListServiceImpl implements CategoryListService {

    @Autowired
    CategoryListBusinessInterface categoryListBusiness;

    @Override
    public String updateCategoryList(CategoryListDTO categoryListDTO) {
        return categoryListBusiness.update(categoryListDTO);
    }

    @Override
    public String deleteCategoryList(Long id) {
        return categoryListBusiness.delete(id);
    }

    @Override
    public String deleteListCategoryList(List<CategoryListDTO> categoryListListDTO) {
        return categoryListBusiness.delete(categoryListListDTO);
    }

    @Override
    public CategoryListDTO findCategoryListById(Long id) {
        if (id != null && id > 0) {
            return (CategoryListDTO) categoryListBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<CategoryListDTO> getListCategoryListDTO(CategoryListDTO categoryListDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (categoryListDTO != null) {
            return categoryListBusiness.search(categoryListDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertCategoryList(CategoryListDTO categoryListDTO) {
        return categoryListBusiness.createObject(categoryListDTO);
    }

    @Override
    public String insertOrUpdateListCategoryList(List<CategoryListDTO> categoryListDTO) {
        return categoryListBusiness.insertList(categoryListDTO);
    }

    @Override
    public List<String> getSequenseCategoryList(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);

        return categoryListBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<CategoryListDTO> getListCategoryListByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<CategoryListDTO> lstCategoryList = new ArrayList<>();
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

        lstCategoryList = categoryListBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstCategoryList;
    }

    @Override
    public List<CategoryListDTO> getCategoryListFromStaffCode(String staffCode, String service) {
        return categoryListBusiness.getCategoryListFromStaffCode(staffCode, service);
    }

}
