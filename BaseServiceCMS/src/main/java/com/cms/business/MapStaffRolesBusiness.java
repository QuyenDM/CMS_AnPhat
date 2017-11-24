/**
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business;

import com.cms.business.service.MapStaffRolesBusinessInterface;
import com.cms.dao.MapStaffRolesDAO;
import com.cms.dto.MapStaffRolesDTO;
import com.cms.model.MapStaffRoles;
import com.vfw5.base.service.BaseFWServiceImpl;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 8/14/2016 2:37 PM
 */
@Service("mapStaffRolesBusiness")
@Transactional
public class MapStaffRolesBusiness extends BaseFWServiceImpl<MapStaffRolesDAO, MapStaffRolesDTO, MapStaffRoles> implements MapStaffRolesBusinessInterface {

    @Autowired
    private MapStaffRolesDAO mapStaffRolesDAO;

    public MapStaffRolesBusiness() {
        tModel = new MapStaffRoles();
        tDAO = mapStaffRolesDAO;
    }

    @Override
    public MapStaffRolesDAO gettDAO() {
        return mapStaffRolesDAO;
    }

    public MapStaffRolesBusiness(Session session) {
        this.session = session;
        tModel = new MapStaffRoles();
        tDAO = mapStaffRolesDAO;
    }
}
