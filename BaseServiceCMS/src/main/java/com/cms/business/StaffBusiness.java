/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business;

import com.cms.business.service.CommonBusinessInterface;
import com.vfw5.base.service.BaseFWServiceImpl;
import com.cms.dto.StaffDTO;
import com.cms.model.Staff;
import com.cms.dao.StaffDAO;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 1:28 PM
 */
@Service("staffBusiness")
@Transactional
public class StaffBusiness extends BaseFWServiceImpl<StaffDAO, StaffDTO, Staff> implements CommonBusinessInterface {

    @Autowired
    private StaffDAO staffDAO;

    public StaffBusiness() {
        tModel = new Staff();
        tDAO = staffDAO;
    }

    @Override
    public StaffDAO gettDAO() {
        return staffDAO;
    }

    public StaffBusiness(Session session) {
        this.session = session;
        tModel = new Staff();
        tDAO = staffDAO;
    }
}
