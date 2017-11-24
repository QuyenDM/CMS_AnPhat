/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business.service;

import com.cms.dto.CategoryListDTO;
import com.cms.model.CategoryList;
import com.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 8/19/2016 12:12 AM
 */
public interface CategoryListBusinessInterface extends BaseFWServiceInterface<CategoryListDTO, CategoryList> {

    public List<CategoryListDTO> getCategoryListFromStaffCode(String staffCode, String service);
    
    public void updateQuanlityForCategoryList(String categoryId);
    
    public void updateDevidedQuanlityForCategoryList();
}
