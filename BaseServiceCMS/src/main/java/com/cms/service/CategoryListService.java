/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.CategoryListDTO;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.pojo.ConditionBean;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/19/2016 12:12 AM
 */
@WebService(targetNamespace = "http://service.cms.com")
public interface CategoryListService {

    @WebMethod
    public List<CategoryListDTO> getListCategoryListDTO(@WebParam(name = "categoryListDTO") CategoryListDTO categoryListDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //
    @WebMethod
    public String updateCategoryList(@WebParam(name = "categoryListDTO") CategoryListDTO categoryListDTO);

    //
    @WebMethod
    public String deleteCategoryList(@WebParam(name = "categoryListDTOId") Long id);

    //
    @WebMethod
    public String deleteListCategoryList(@WebParam(name = "categoryListListDTO") List<CategoryListDTO> categoryListListDTO);

    //
    @WebMethod
    public CategoryListDTO findCategoryListById(@WebParam(name = "categoryListDTOId") Long id);

    //
    @WebMethod
    public ResultDTO insertCategoryList(@WebParam(name = "categoryListDTO") CategoryListDTO categoryListDTO);

    //
    @WebMethod
    public String insertOrUpdateListCategoryList(@WebParam(name = "categoryListDTO") List<CategoryListDTO> categoryListDTO);

    //
    @WebMethod
    public List<String> getSequenseCategoryList(@WebParam(name = "sequenseName") String seqName, @WebParam(name = "Size") int... size);

    //    
    @WebMethod
    public List<CategoryListDTO> getListCategoryListByCondition(@WebParam(name = "lstCondition") List<ConditionBean> lstCondition, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    //    
    @WebMethod
    public List<CategoryListDTO> getCategoryListFromStaffCode(@WebParam(name = "staffCode") String staffCode,@WebParam(name = "service") String service);
}
