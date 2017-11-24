package com.cms.business;

import com.cms.business.service.PriceInfoBusinessInterface;
import com.cms.dto.PriceInfoDTO;
import com.cms.model.PriceInfo;
import com.cms.dao.PriceInfoDAO;
import com.vfw5.base.service.BaseFWServiceImpl;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author <%=Author%> @version 1.0
 * @since 16/01/2017 09:45:29
 */
@Service("priceInfoBusiness")
@Transactional
public class PriceInfoBusiness extends BaseFWServiceImpl<PriceInfoDAO, PriceInfoDTO, PriceInfo> implements PriceInfoBusinessInterface {

    @Autowired
    private PriceInfoDAO priceInfoDAO;

    public PriceInfoBusiness() {
        tModel = new PriceInfo();
        tDAO = priceInfoDAO;
    }

    @Override
    public PriceInfoDAO gettDAO() {
        return priceInfoDAO;
    }

    public PriceInfoBusiness(Session session) {
        this.session = session;
        tModel = new PriceInfo();
        tDAO = priceInfoDAO;
    }
}
