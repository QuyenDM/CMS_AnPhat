/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business;

import com.cms.business.service.RoleObjectsBusinessInterface;
import com.vfw5.base.service.BaseFWServiceImpl;
import com.cms.dto.RoleObjectsDTO;
import com.cms.model.RoleObjects;
import com.cms.dao.RoleObjectsDAO;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:32 AM
 */
@Service("roleObjectsBusiness")
@Transactional
public class RoleObjectsBusiness extends BaseFWServiceImpl<RoleObjectsDAO, RoleObjectsDTO, RoleObjects> implements RoleObjectsBusinessInterface {

    @Autowired
    private RoleObjectsDAO roleObjectsDAO;

    public RoleObjectsBusiness() {
        tModel = new RoleObjects();
        tDAO = roleObjectsDAO;
    }

    @Override
    public RoleObjectsDAO gettDAO() {
        return roleObjectsDAO;
    }

    public RoleObjectsBusiness(Session session) {
        this.session = session;
        tModel = new RoleObjects();
        tDAO = roleObjectsDAO;
    }
}
