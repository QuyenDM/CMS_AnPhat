/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business;

import com.cms.business.service.CustomerContactBusinessInterface;
import com.vfw5.base.service.BaseFWServiceImpl;
import com.cms.dto.CustomerContactDTO;
import com.cms.model.CustomerContact;
import com.cms.dao.CustomerContactDAO;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 7/26/2016 10:32 PM
 */
@Service("customerContactBusiness")
@Transactional
public class CustomerContactBusiness extends BaseFWServiceImpl<CustomerContactDAO, CustomerContactDTO, CustomerContact> implements CustomerContactBusinessInterface{
	
    @Autowired
    private CustomerContactDAO customerContactDAO;

    public CustomerContactBusiness() {
        tModel = new CustomerContact();
        tDAO = customerContactDAO;
    }
    @Override
    public CustomerContactDAO gettDAO() {
        return customerContactDAO;
    }
    
    public CustomerContactBusiness(Session session) {
        this.session = session;
        tModel = new CustomerContact();
        tDAO = customerContactDAO;
    }
}


