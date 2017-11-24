/**
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business;

import com.cms.business.service.CategoryListBusinessInterface;
import com.cms.dao.CategoryListDAO;
import com.cms.dto.CategoryListDTO;
import com.cms.model.CategoryList;
import com.vfw5.base.service.BaseFWServiceImpl;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 8/19/2016 12:12 AM
 */
@Service("categoryListBusiness")
@Transactional
public class CategoryListBusiness extends BaseFWServiceImpl<CategoryListDAO, CategoryListDTO, CategoryList> implements CategoryListBusinessInterface {

    @Autowired
    private CategoryListDAO categoryListDAO;

    public CategoryListBusiness() {
        tModel = new CategoryList();
        tDAO = categoryListDAO;
    }

    @Override
    public CategoryListDAO gettDAO() {
        return categoryListDAO;
    }

    public CategoryListBusiness(Session session) {
        this.session = session;
        tModel = new CategoryList();
        tDAO = categoryListDAO;
    }

    @Override
    public List<CategoryListDTO> getCategoryListFromStaffCode(String staffCode, String service) {
        return gettDAO().getCategoryListFromStaffCode(staffCode, service);
    }

    @Override
    public void updateQuanlityForCategoryList(String categoryId) {
        gettDAO().updateQuanlityForCategoryList(categoryId);
        gettDAO().updateMineNameForCustomer(categoryId);
    }

    @Override
    public void updateDevidedQuanlityForCategoryList() {
        gettDAO().updateDevidedQuanlityForCategoryList();
    }

}
