

package com.cms.dao;
import com.cms.model.StatisticStaffPoint;
import com.vfw5.base.dao.BaseFWDAOImpl;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
* @author QuyenDM
* @version 1.0
* @since 16-Apr-15 11:11 AM
*/
@Repository("statisticStaffPointDAO")
public class StatisticStaffPointDAO extends BaseFWDAOImpl<StatisticStaffPoint, Long> {

    public StatisticStaffPointDAO() {
        this.model= new StatisticStaffPoint();
    }

    public StatisticStaffPointDAO(Session session) {
        this.session = session;
    }
}


