/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dao;

import com.vfw5.base.dao.BaseFWDAOImpl;
import com.cms.model.CustomerCareHistory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/30/2016 1:26 AM
 */
@Repository("customerCareHistoryDAO")
public class CustomerCareHistoryDAO extends BaseFWDAOImpl<CustomerCareHistory, Long> {

    public CustomerCareHistoryDAO() {
        this.model = new CustomerCareHistory();
    }

    public CustomerCareHistoryDAO(Session session) {
        this.session = session;
    }
}
