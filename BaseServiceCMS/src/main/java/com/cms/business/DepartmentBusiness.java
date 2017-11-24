/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business;

import com.vfw5.base.service.BaseFWServiceImpl;
import com.cms.dto.DepartmentDTO;
import com.cms.model.Department;
import com.cms.dao.DepartmentDAO;
import org.hibernate.Session;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author TruongBX3
 * @version 1.0
 * @since 22-Apr-15 6:02 PM
 */
@Service("departmentBusiness")
@Transactional
public class DepartmentBusiness extends BaseFWServiceImpl<DepartmentDAO, DepartmentDTO, Department> {
	
    @Autowired
    private DepartmentDAO departmentDAO;

    public DepartmentBusiness() {
        tModel = new Department();
        tDAO = departmentDAO;
    }
    @Override
    public DepartmentDAO gettDAO() {
        return departmentDAO;
    }
    
    public DepartmentBusiness(Session session) {
        this.session = session;
        tModel = new Department();
        tDAO = departmentDAO;
    }
}


