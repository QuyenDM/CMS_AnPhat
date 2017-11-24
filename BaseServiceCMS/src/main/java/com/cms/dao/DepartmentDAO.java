/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dao;

import com.vfw5.base.dao.BaseFWDAOImpl;
import com.cms.model.Department;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 22-Apr-15 6:02 PM
 */
@Repository("departmentDAO")
public class DepartmentDAO extends BaseFWDAOImpl<Department, Long> {

    public DepartmentDAO() {
        this.model = new Department();
    }

    public DepartmentDAO(Session session) {
        this.session = session;
    }
}
