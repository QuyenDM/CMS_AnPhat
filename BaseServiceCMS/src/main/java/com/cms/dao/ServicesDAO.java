package com.cms.dao;

import com.cms.model.Services;
import com.vfw5.base.dao.BaseFWDAOImpl;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
@Repository("servicesDAO")
public class ServicesDAO extends BaseFWDAOImpl<Services, Long> {

    public ServicesDAO() {
        this.model = new Services();
    }

    public ServicesDAO(Session session) {
        this.session = session;
    }
}
