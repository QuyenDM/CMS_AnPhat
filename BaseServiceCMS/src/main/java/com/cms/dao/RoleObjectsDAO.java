/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dao;

import com.vfw5.base.dao.BaseFWDAOImpl;
import com.cms.model.RoleObjects;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:32 AM
 */
@Repository("roleObjectsDAO")
public class RoleObjectsDAO extends BaseFWDAOImpl<RoleObjects, Long> {

    public RoleObjectsDAO() {
        this.model = new RoleObjects();
    }

    public RoleObjectsDAO(Session session) {
        this.session = session;
    }
}
