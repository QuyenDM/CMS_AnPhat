/**
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business;

import com.cms.business.service.CustomerStatusBusinessInterface;
import com.vfw5.base.service.BaseFWServiceImpl;
import com.cms.dto.CustomerStatusDTO;
import com.cms.model.CustomerStatus;
import com.cms.dao.CustomerStatusDAO;
import com.cms.dto.StatusQuantityDTO;
import com.cms.statistics.dto.StatisticsCategoryListDTO;
import java.util.List;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 7/30/2016 5:27 PM
 */
@Service("customerStatusBusiness")
@Transactional
public class CustomerStatusBusiness extends BaseFWServiceImpl<CustomerStatusDAO, CustomerStatusDTO, CustomerStatus> implements CustomerStatusBusinessInterface {

    @Autowired
    private CustomerStatusDAO customerStatusDAO;

    public CustomerStatusBusiness() {
        tModel = new CustomerStatus();
        tDAO = customerStatusDAO;
    }

    @Override
    public CustomerStatusDAO gettDAO() {
        return customerStatusDAO;
    }

    public CustomerStatusBusiness(Session session) {
        this.session = session;
        tModel = new CustomerStatus();
        tDAO = customerStatusDAO;
    }

    @Override
    public List<StatisticsCategoryListDTO> getStatisticsCategoryListByStaff(
            String service, String staffCode, String categoryId, String beginLastUpdated, String endLastUpdated) {
        return gettDAO().getStatisticsCategoryListByStaff(service, staffCode, categoryId, beginLastUpdated, endLastUpdated);
    }
    
    @Override
    public List<StatusQuantityDTO> getStatusQuantity(String staffCode) {
        return gettDAO().getStatusQuantity(staffCode);
    }
}
