/**
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business;

import com.cms.business.service.CustomerCareHistoryBusinessInterface;
import com.vfw5.base.service.BaseFWServiceImpl;
import com.cms.dto.CustomerCareHistoryDTO;
import com.cms.model.CustomerCareHistory;
import com.cms.dao.CustomerCareHistoryDAO;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 7/30/2016 1:26 AM
 */
@Service("customerCareHistoryBusiness")
@Transactional
public class CustomerCareHistoryBusiness extends BaseFWServiceImpl<CustomerCareHistoryDAO, CustomerCareHistoryDTO, CustomerCareHistory> implements CustomerCareHistoryBusinessInterface {

    @Autowired
    private CustomerCareHistoryDAO customerCareHistoryDAO;

    public CustomerCareHistoryBusiness() {
        tModel = new CustomerCareHistory();
        tDAO = customerCareHistoryDAO;
    }

    @Override
    public CustomerCareHistoryDAO gettDAO() {
        return customerCareHistoryDAO;
    }

    public CustomerCareHistoryBusiness(Session session) {
        this.session = session;
        tModel = new CustomerCareHistory();
        tDAO = customerCareHistoryDAO;
    }
}
