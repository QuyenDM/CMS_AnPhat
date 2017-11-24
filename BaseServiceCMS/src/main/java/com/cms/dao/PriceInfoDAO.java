package com.cms.dao;

import com.cms.model.PriceInfo;
import com.vfw5.base.dao.BaseFWDAOImpl;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
@Repository("priceInfoDAO")
public class PriceInfoDAO extends BaseFWDAOImpl<PriceInfo, Long> {

    public PriceInfoDAO() {
        this.model = new PriceInfo();
    }

    public PriceInfoDAO(Session session) {
        this.session = session;
    }
}
