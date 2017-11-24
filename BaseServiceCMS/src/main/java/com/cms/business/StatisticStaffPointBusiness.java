package com.cms.business;

import com.cms.business.service.StatisticStaffPointBusinessInterface;
import com.cms.dao.StatisticStaffPointDAO;
import com.cms.dto.StatisticStaffPointDTO;
import com.cms.model.StatisticStaffPoint;
import com.vfw5.base.service.BaseFWServiceImpl;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*
 * @author <%=Author%>
 * @version 1.0
* @since 06/12/2016 23:05:01
*/
@Service("statisticStaffPointBusiness")
@Transactional
public class StatisticStaffPointBusiness extends BaseFWServiceImpl<StatisticStaffPointDAO, StatisticStaffPointDTO, StatisticStaffPoint> implements StatisticStaffPointBusinessInterface{

    @Autowired
    private StatisticStaffPointDAO statisticStaffPointDAO;

    public StatisticStaffPointBusiness() {
        tModel = new StatisticStaffPoint();
        tDAO = statisticStaffPointDAO;
    }
    @Override
    public StatisticStaffPointDAO gettDAO() {
        return statisticStaffPointDAO;
    }

    public StatisticStaffPointBusiness(Session session) {
        this.session = session;
        tModel = new StatisticStaffPoint();
        tDAO = statisticStaffPointDAO;
    }
}
