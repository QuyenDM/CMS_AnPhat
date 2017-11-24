/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business;

import com.vfw5.base.service.BaseFWServiceImpl;
import com.cms.dto.RolesDTO;
import com.cms.model.Roles;
import com.cms.dao.RolesDAO;
import java.util.List;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:27 AM
 */
@Service("rolesBusiness")
@Transactional
public class RolesBusiness extends BaseFWServiceImpl<RolesDAO, RolesDTO, Roles> implements RolesBusinessInterface {

    @Autowired
    private RolesDAO rolesDAO;

    public RolesBusiness() {
        tModel = new Roles();
        tDAO = rolesDAO;
    }

    @Override
    public RolesDAO gettDAO() {
        return rolesDAO;
    }

    public RolesBusiness(Session session) {
        this.session = session;
        tModel = new Roles();
        tDAO = rolesDAO;
    }

    @Override
    public List<RolesDTO> getListRolesByStaffId(String staffId) {
        return gettDAO().getListRolesByStaffId(staffId);
    }
}
