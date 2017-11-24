/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business;

import com.cms.business.service.ObjectsBusinessInterface;
import com.vfw5.base.service.BaseFWServiceImpl;
import com.cms.dto.ObjectsDTO;
import com.cms.dao.ObjectsDAO;
import com.cms.dto.RolesDTO;
import com.cms.model.Objects;
import java.util.List;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:18 AM
 */
@Service("objectsBusiness")
@Transactional
public class ObjectsBusiness extends BaseFWServiceImpl<ObjectsDAO, ObjectsDTO, Objects> implements ObjectsBusinessInterface {

    @Autowired
    private ObjectsDAO objectsDAO;

    public ObjectsBusiness() {
        tModel = new Objects();
        tDAO = objectsDAO;
    }

    @Override
    public ObjectsDAO gettDAO() {
        return objectsDAO;
    }

    public ObjectsBusiness(Session session) {
        this.session = session;
        tModel = new Objects();
        tDAO = objectsDAO;
    }

    @Override
    public List<ObjectsDTO> getListObjectByStaffId(String staffId) {
        return gettDAO().getLstObjectsDTOByStaffId(staffId);
    }

    @Override
    public List<ObjectsDTO> getListObjectByRole(RolesDTO roles) {
        return gettDAO().getListObjectByRole(roles);
    }

}
