/**
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dao;

import com.vfw5.base.dao.BaseFWDAOImpl;
import com.cms.model.CustomerContact;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/26/2016 10:32 PM
 */
@Repository("customerContactDAO")
public class CustomerContactDAO extends BaseFWDAOImpl<CustomerContact, Long> {

    public CustomerContactDAO() {
        this.model = new CustomerContact();
    }

    public CustomerContactDAO(Session session) {
        this.session = session;
    }
}
