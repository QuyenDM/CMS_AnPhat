/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dao;

import com.vfw5.base.dao.BaseFWDAOImpl;
import com.cms.model.Staff;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 1:28 PM
 */
@Repository("staffDAO")
public class StaffDAO extends BaseFWDAOImpl<Staff, Long> {

    public StaffDAO() {
        this.model = new Staff();
    }

    public StaffDAO(Session session) {
        this.session = session;
    }

}
