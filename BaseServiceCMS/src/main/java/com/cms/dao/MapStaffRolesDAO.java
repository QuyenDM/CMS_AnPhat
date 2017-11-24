/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dao;

import com.cms.model.MapStaffRoles;
import com.vfw5.base.dao.BaseFWDAOImpl;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/14/2016 2:37 PM
 */
@Repository("mapStaffRolesDAO")
public class MapStaffRolesDAO extends BaseFWDAOImpl<MapStaffRoles, Long> {

    public MapStaffRolesDAO() {
        this.model = new MapStaffRoles();
    }

    public MapStaffRolesDAO(Session session) {
        this.session = session;
    }
}
