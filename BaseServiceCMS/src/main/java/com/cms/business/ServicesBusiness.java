package com.cms.business;

import com.cms.business.service.ServicesBusinessInterface;
import com.cms.dao.ServicesDAO;
import com.cms.dto.ServicesDTO;
import com.cms.model.Services;
import com.vfw5.base.service.BaseFWServiceImpl;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
*
 * @author <%=Author%>
 * @version 1.0
* @since 06/08/2016 10:03:59
*/
@Service("servicesBusiness")
@Transactional
public class ServicesBusiness extends BaseFWServiceImpl<ServicesDAO, ServicesDTO, Services> implements ServicesBusinessInterface{

    @Autowired
    private ServicesDAO servicesDAO;

    public ServicesBusiness() {
        tModel = new Services();
        tDAO = servicesDAO;
    }
    @Override
    public ServicesDAO gettDAO() {
        return servicesDAO;
    }

    public ServicesBusiness(Session session) {
        this.session = session;
        tModel = new Services();
        tDAO = servicesDAO;
    }
}
