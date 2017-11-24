package com.cms.dao;

import com.cms.dto.CategoryListDTO;
import com.cms.model.CategoryList;
import com.vfw5.base.dao.BaseFWDAOImpl;
import com.vfw5.base.utils.DataUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/19/2016 12:12 AM
 */
@Repository("categoryListDAO")
public class CategoryListDAO extends BaseFWDAOImpl<CategoryList, Long> {

    public CategoryListDAO() {
        this.model = new CategoryList();
    }

    public CategoryListDAO(Session session) {
        this.session = session;
    }

    public List<CategoryListDTO> getCategoryListWithQuanlity() {
        StringBuilder sb = new StringBuilder();
        sb.append("    SELECT cl.code, ");
        sb.append("      cl.name, ");
        sb.append("      cl.RECEIVED_DATE, ");
        sb.append("      cl.END_DATE, ");
        sb.append("      cl.DESCRIPTION, ");
        sb.append("      cl.CREATOR, ");
        sb.append("      COUNT(*) AS cust_quanlity ");
        sb.append("    FROM CATEGORY_LIST cl ");
        sb.append("    JOIN TERM_INFORMATION ti ");
        sb.append("    ON cl.ID = ti.MINE_NAME ");
        sb.append("    GROUP BY cl.code, ");
        sb.append("      cl.name, ");
        sb.append("      cl.RECEIVED_DATE, ");
        sb.append("      cl.END_DATE, ");
        sb.append("      cl.DESCRIPTION, ");
        sb.append("      cl.CREATOR");
        return null;
    }

    public void updateQuanlityForCategoryList(String categoryId) {
        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE CATEGORY_LIST a SET a.CUST_QUANTITY = "
                + "(select count(distinct ti.tax_code) from term_information ti "
                + "where ti.MINE_NAME = a.id) WHERE a.id= :idx0 ");
        try {
            SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
            query.setParameter("idx0", categoryId);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int updateMineNameForCustomer(String mineName) {
        StringBuilder sqlProvider = new StringBuilder();
        sqlProvider.append("	update customer c set c.MINE_NAME = :mineName where MINE_NAME IS NULL AND EXISTS ( ");
        sqlProvider.append("	select TAX_CODE from TERM_INFORMATION where MINE_NAME = :mineName AND c.tax_code = tax_code) ");
        int result = -1;
        try {
            SQLQuery query = getSession().createSQLQuery(sqlProvider.toString());
            query.setParameter("mineName", mineName);
            result = query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateDevidedQuanlityForCategoryList() {
        Connection connection = null;
        try {
            connection = sessionFactory.getSessionFactoryOptions().
                    getServiceRegistry().getService(ConnectionProvider.class).getConnection();
            CallableStatement cs = connection.prepareCall("{call UPDATEDEVIDEDQUANTITY}");
            cs.executeQuery();
            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }

        }
    }

    public List<CategoryListDTO> getCategoryListFromStaffCode(String staffCode, String service) {
        List<CategoryListDTO> lstCategoryList = null;
        StringBuilder sb = new StringBuilder();
        sb.append("    SELECT cl.code code, ");
        sb.append("      cl.id id, ");
        sb.append("      cl.name name, ");
        sb.append("      to_char(cl.RECEIVED_DATE,'dd/MM/yyyy') receivedDate, ");
        sb.append("      to_char(cl.END_DATE,'dd/MM/yyyy') endDate, ");
        sb.append("      cl.DESCRIPTION description, ");
        sb.append("      cl.CREATOR creator ");
        sb.append("    FROM category_list cl ");
        sb.append("    WHERE cl.ID IN ");
        sb.append("      (SELECT DISTINCT mine_name FROM customer_status WHERE 1 = 1 ");
        if (!DataUtil.isStringNullOrEmpty(staffCode)) {
            sb.append("      AND staff_code= :staffCode ");
        }
        sb.append("      ) ");
        if (!DataUtil.isStringNullOrEmpty(service)) {
            sb.append("      AND cl.service= :service ");
        }
        sb.append("      AND cl.cust_quantity > 0 ");
        try {
            SQLQuery query = getSession().createSQLQuery(sb.toString());
            if (!DataUtil.isStringNullOrEmpty(staffCode)) {
                query.setParameter("staffCode", staffCode);
            }
            if (!DataUtil.isStringNullOrEmpty(service)) {
                query.setParameter("service", service);
            }
            query.setResultTransformer(Transformers.aliasToBean(CategoryListDTO.class));
            query.addScalar("code", new StringType());
            query.addScalar("id", new StringType());
            query.addScalar("name", new StringType());
            query.addScalar("receivedDate", new StringType());
            query.addScalar("endDate", new StringType());
            query.addScalar("description", new StringType());
            query.addScalar("creator", new StringType());
            lstCategoryList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCategoryList;
    }
}
