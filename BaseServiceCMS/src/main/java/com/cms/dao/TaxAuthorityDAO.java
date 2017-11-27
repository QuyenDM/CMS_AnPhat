/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dao;

import com.cms.dto.TaxAuthorityDTO;
import com.cms.model.TaxAuthority;
import com.vfw5.base.dao.BaseFWDAOImpl;
import com.vfw5.base.utils.DataUtil;
import java.util.List;
import java.util.Map;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/23/2016 11:13 PM
 */
@Repository("taxAuthorityDAO")
public class TaxAuthorityDAO extends BaseFWDAOImpl<TaxAuthority, Long> {

    public TaxAuthorityDAO() {
        this.model = new TaxAuthority();
    }

    public TaxAuthorityDAO(Session session) {
        this.session = session;
    }

    public List<TaxAuthorityDTO> getListProvinces() {
        List<TaxAuthorityDTO> lstTaxAuthorities;
//        List params = new ArrayList();
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("       SELECT a.MA_CQT maCqt, ");
        sqlQuery.append("         a.TEN_CQT tenCqt, ");
        sqlQuery.append("         a.MA_QUAN_HUYEN maQuanHuyen, ");
        sqlQuery.append("         a.MA_TINH maTinh, ");
        sqlQuery.append("         a.STATUS status, ");
        sqlQuery.append("         a.MA_MST maMST ");
        sqlQuery.append("       FROM tax_authority a ");
        sqlQuery.append("       WHERE SUBSTR(a.MA_CQT,4,2) ='00' ");

        try {
            SQLQuery query = getSession().createSQLQuery(sqlQuery.toString());
            //Thuc hien chuyen du lieu lay ve thanh thanh doi tuong            
            query.setResultTransformer(Transformers.aliasToBean(TaxAuthorityDTO.class));
            query.addScalar("maCqt", new StringType());
            query.addScalar("tenCqt", new StringType());
            query.addScalar("maQuanHuyen", new StringType());
            query.addScalar("maTinh", new StringType());
            query.addScalar("status", new StringType());
            query.addScalar("maMST", new StringType());

            //Truyen cac tham so truyen vao de thuc hien tim kiem
//            for (int i = 0; i < params.size(); i++) {
//                query.setParameter(i, params.get(i));
//            }
            //Day du lieu ra danh sach doi tuong
            lstTaxAuthorities = query.list();
        } catch (Exception e) {
            e.printStackTrace();
            lstTaxAuthorities = null;
        }
        return lstTaxAuthorities;
    }

    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineName(String mineName, Map<String, String> map) {
        String startFromDate = map.get("startFromDate");
        String provider = map.get("provider");
        String endFromDate = map.get("endFromDate");
        String startToDate = map.get("startToDate");
        String endToDate = map.get("endToDate");
        String fromDateRegister = map.get("fromDateRegister");
        String toDateRegister = map.get("toDateRegister");
        List<TaxAuthorityDTO> lstTaxAuthorities = null;
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("       SELECT ID id,	");
        sqlQuery.append("         MA_CQT maCqt,	");
        sqlQuery.append("         MA_QUAN_HUYEN maQuanHuyen,	");
        sqlQuery.append("         MA_TINH maTinh,	");
        sqlQuery.append("         STATUS status,	");
        sqlQuery.append("         TEN_CQT tenCqt	");
        sqlQuery.append("       FROM TAX_AUTHORITY ta	");
        sqlQuery.append("       WHERE ta.MA_CQT = ANY	");
        sqlQuery.append("         ( SELECT DISTINCT c.TAX_AUTHORITY	");
        sqlQuery.append("         FROM CUSTOMER c	");
        sqlQuery.append("         JOIN	 TERM_INFORMATION ti ON ti.TAX_CODE = c.TAX_CODE ");
        sqlQuery.append("           WHERE ti.IS_CONTACT_INFO is NULL AND ti.MINE_NAME = ANY (:mineName)	");
        if (!DataUtil.isStringNullOrEmpty(provider)) {
            sqlQuery.append("         and lower(ti.PROVIDER) = ANY (:provider)	");
        }
        if (!DataUtil.isStringNullOrEmpty(startFromDate)) {
            sqlQuery.append("         and ti.START_TIME >= TO_DATE(:startFromDate,'dd/MM/yyyy') - 1	");
        }
        if (!DataUtil.isStringNullOrEmpty(endFromDate)) {
            sqlQuery.append("         and ti.START_TIME <= TO_DATE(:endFromDate,'dd/MM/yyyy') + 1 	");
        }
        if (!DataUtil.isStringNullOrEmpty(startToDate)) {
            sqlQuery.append("         and ti.END_TIME >= TO_DATE(:startToDate,'dd/MM/yyyy') - 1	");
        }
        if (!DataUtil.isStringNullOrEmpty(endToDate)) {
            sqlQuery.append("         and ti.END_TIME <= TO_DATE(:endToDate,'dd/MM/yyyy')   + 1	");
        }
        if (!DataUtil.isStringNullOrEmpty(fromDateRegister)) {
            sqlQuery.append("   AND ti.DATE_REGISTER >= TO_DATE(:fromDateRegister,'dd/MM/yyyy') - 1 ");
        }
        if (!DataUtil.isStringNullOrEmpty(toDateRegister)) {
            sqlQuery.append("   AND ti.DATE_REGISTER <= TO_DATE(:toDateRegister,'dd/MM/yyyy')  + 1");
        }
        sqlQuery.append("       AND NOT "
                + "                EXISTS ( "
                + "                    SELECT "
                + "                        cs.tax_code, "
                + "                        cs.mine_name "
                + "                    FROM "
                + "                        customer_status cs"
                + "                    WHERE "
                + "                            cs.tax_code = ti.tax_code "
                + "                        AND "
                + "                            cs.mine_name = ti.mine_name "
                + "                ) ");
        sqlQuery.append("       )  ");
        sqlQuery.append("       ORDER BY ta.TEN_CQT asc ");
        SQLQuery query;
        try {
            query = getSession().createSQLQuery(sqlQuery.toString());
            //Thuc hien chuyen du lieu lay ve thanh thanh doi tuong            
            query.setResultTransformer(Transformers.aliasToBean(TaxAuthorityDTO.class));
            query.addScalar("id", new StringType());
            query.addScalar("maCqt", new StringType());
            query.addScalar("maQuanHuyen", new StringType());
            query.addScalar("maTinh", new StringType());
            query.addScalar("status", new StringType());
            query.addScalar("tenCqt", new StringType());
            query.setParameterList("mineName", DataUtil.parseInputListString(mineName));
            if (!DataUtil.isStringNullOrEmpty(provider)) {
                query.setParameterList("provider", DataUtil.parseInputListString(provider.toLowerCase()));
            }
            if (!DataUtil.isStringNullOrEmpty(startFromDate)) {
                query.setString("startFromDate", startFromDate);
            }
            if (!DataUtil.isStringNullOrEmpty(endFromDate)) {
                query.setString("endFromDate", endFromDate);
            }
            if (!DataUtil.isStringNullOrEmpty(startToDate)) {
                query.setString("startToDate", startToDate);
            }
            if (!DataUtil.isStringNullOrEmpty(endToDate)) {
                query.setString("endToDate", endToDate);
            }
            if (!DataUtil.isStringNullOrEmpty(fromDateRegister)) {
                query.setString("fromDateRegister", fromDateRegister);
            }
            if (!DataUtil.isStringNullOrEmpty(toDateRegister)) {
                query.setString("toDateRegister", toDateRegister);
            }
            lstTaxAuthorities = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTaxAuthorities;
    }

    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineName(String mineName, String staffCode) {
        List<TaxAuthorityDTO> lstTaxAuthorities = null;
        if (DataUtil.isStringNullOrEmpty(staffCode)) {
            return getListTaxAuthorityFromMineName(mineName);
        }
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("              SELECT ID id,   ");
        sqlQuery.append("                MA_CQT maCqt,  ");
        sqlQuery.append("                MA_QUAN_HUYEN maQuanHuyen,  ");
        sqlQuery.append("                MA_TINH maTinh,  ");
        sqlQuery.append("                STATUS status,  ");
        sqlQuery.append("                TEN_CQT tenCqt  ");
        sqlQuery.append("              FROM TAX_AUTHORITY  ");
        sqlQuery.append("              WHERE MA_CQT IN  ");
        sqlQuery.append("                ( SELECT DISTINCT cs.TAX_AUTHORITY  ");
        sqlQuery.append("                FROM customer_status cs  ");
        sqlQuery.append("                WHERE 1=1 ");
        if (!DataUtil.isStringNullOrEmpty(mineName)) {
            if (mineName.contains(",")) {
                sqlQuery.append("             AND cs.mine_name IN (:mineName) ");
            } else {
                sqlQuery.append("             AND cs.mine_name = :mineName ");
            }
//            sqlQuery.append("            AND cs.mine_name = :mineName  ");
        }
        if (!DataUtil.isStringNullOrEmpty(staffCode)) {
            sqlQuery.append("                AND cs.staff_code  = :staffCode ");
        }
        sqlQuery.append("             ) ORDER BY maCqt");
        SQLQuery query;
        try {
            query = getSession().createSQLQuery(sqlQuery.toString());
            //Thuc hien chuyen du lieu lay ve thanh thanh doi tuong            
            query.setResultTransformer(Transformers.aliasToBean(TaxAuthorityDTO.class));
            query.addScalar("id", new StringType());
            query.addScalar("maCqt", new StringType());
            query.addScalar("maQuanHuyen", new StringType());
            query.addScalar("maTinh", new StringType());
            query.addScalar("status", new StringType());
            query.addScalar("tenCqt", new StringType());

            if (!DataUtil.isStringNullOrEmpty(mineName)) {
                if (mineName.contains(",")) {
                    query.setParameterList("mineName", DataUtil.parseInputListString(mineName));
                } else {
                    query.setParameter("mineName", mineName);
                }
            }
            if (!DataUtil.isStringNullOrEmpty(staffCode)) {
                query.setParameter("staffCode", staffCode);
            }

            lstTaxAuthorities = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTaxAuthorities;
    }

    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineNameAndStaffCodeAndProvider(
            String mineName, String staffCode, String provider, String status) {
        if (DataUtil.isStringNullOrEmpty(staffCode)) {
            return getListTaxAuthorityFromMineNameAndProvider(mineName, provider, status);
        }
        List<TaxAuthorityDTO> lstTaxAuthorities = null;
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("              SELECT ID id,   ");
        sqlQuery.append("                MA_CQT maCqt,  ");
        sqlQuery.append("                MA_QUAN_HUYEN maQuanHuyen,  ");
        sqlQuery.append("                MA_TINH maTinh,  ");
        sqlQuery.append("                STATUS status,  ");
        sqlQuery.append("                TEN_CQT tenCqt  ");
        sqlQuery.append("              FROM TAX_AUTHORITY  ");
        sqlQuery.append("              WHERE MA_CQT IN  ");
        sqlQuery.append("                ( SELECT DISTINCT cs.TAX_AUTHORITY  ");
        sqlQuery.append("                FROM customer_status cs  ");
        if (!DataUtil.isStringNullOrEmpty(provider)
                || !DataUtil.isStringNullOrEmpty(mineName)) {
            sqlQuery.append("            JOIN term_information ti ON ti.TAX_CODE = cs.TAX_CODE ");
        }
        sqlQuery.append("                WHERE 1=1 ");
        if (!DataUtil.isStringNullOrEmpty(mineName)) {
            if (mineName.contains(",")) {
                sqlQuery.append("            AND cs.mine_name IN (:mineName)  ");
            } else {
                sqlQuery.append("            AND cs.mine_name = :mineName  ");
            }
        }
        if (!DataUtil.isStringNullOrEmpty(staffCode)) {
            sqlQuery.append("                AND cs.staff_code  = :staffCode ");
        }
        if (!DataUtil.isStringNullOrEmpty(provider)) {
            sqlQuery.append("            AND lower(ti.provider) = ANY (:provider)  ");
        }
        if (!DataUtil.isStringNullOrEmpty(status)) {
            sqlQuery.append("            AND cs.status = ANY (:status)  ");
        }
        sqlQuery.append("             ) ORDER BY maCqt");
        SQLQuery query;
        try {
            System.out.println(sqlQuery.toString());
            query = getSession().createSQLQuery(sqlQuery.toString());
            //Thuc hien chuyen du lieu lay ve thanh thanh doi tuong            
            query.setResultTransformer(Transformers.aliasToBean(TaxAuthorityDTO.class));
            query.addScalar("id", new StringType());
            query.addScalar("maCqt", new StringType());
            query.addScalar("maQuanHuyen", new StringType());
            query.addScalar("maTinh", new StringType());
            query.addScalar("status", new StringType());
            query.addScalar("tenCqt", new StringType());

            if (!DataUtil.isStringNullOrEmpty(mineName)) {
                if (mineName.contains(",")) {
                    query.setParameterList("mineName", DataUtil.parseInputListString(mineName));
                } else {
                    query.setParameter("mineName", mineName);
                }
            }
            if (!DataUtil.isStringNullOrEmpty(provider)) {
                query.setParameterList("provider", DataUtil.parseInputListString(provider.toLowerCase()));
            }
            if (!DataUtil.isStringNullOrEmpty(status)) {
                query.setParameterList("status", DataUtil.parseInputListString(status));
            }
            if (!DataUtil.isStringNullOrEmpty(staffCode)) {
                query.setParameter("staffCode", staffCode);
            }

            lstTaxAuthorities = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTaxAuthorities;
    }

    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineName(String mineName) {
        List<TaxAuthorityDTO> lstTaxAuthorities = null;
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("              SELECT ID id,   ");
        sqlQuery.append("                MA_CQT maCqt,  ");
        sqlQuery.append("                MA_QUAN_HUYEN maQuanHuyen,  ");
        sqlQuery.append("                MA_TINH maTinh,  ");
        sqlQuery.append("                STATUS status,  ");
        sqlQuery.append("                TEN_CQT tenCqt  ");
        sqlQuery.append("              FROM TAX_AUTHORITY  ");
        sqlQuery.append("              WHERE MA_CQT IN  ");
        sqlQuery.append("                ( SELECT DISTINCT cs.TAX_AUTHORITY  ");
        sqlQuery.append("                FROM CUSTOMER cs INNER JOIN TERM_INFORMATION ti ON ti.TAX_CODE = cs.TAX_CODE ");
        sqlQuery.append("                WHERE 1=1 AND ti.IS_CONTACT_INFO is NULL ");
        if (!DataUtil.isStringNullOrEmpty(mineName)) {
            if (mineName.contains(",")) {
                sqlQuery.append("            AND ti.mine_name IN (:mineName)  ");
            } else {
                sqlQuery.append("            AND ti.mine_name = :mineName  ");
            }
        }
        sqlQuery.append("             ) ORDER BY maCqt");
        SQLQuery query;
        try {
            query = getSession().createSQLQuery(sqlQuery.toString());
            //Thuc hien chuyen du lieu lay ve thanh thanh doi tuong            
            query.setResultTransformer(Transformers.aliasToBean(TaxAuthorityDTO.class));
            query.addScalar("id", new StringType());
            query.addScalar("maCqt", new StringType());
            query.addScalar("maQuanHuyen", new StringType());
            query.addScalar("maTinh", new StringType());
            query.addScalar("status", new StringType());
            query.addScalar("tenCqt", new StringType());

            if (!DataUtil.isStringNullOrEmpty(mineName)) {
                if (mineName.contains(",")) {
                    query.setParameterList("mineName", DataUtil.parseInputListString(mineName));
                } else {
                    query.setParameter("mineName", mineName);
                }
            }
            lstTaxAuthorities = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTaxAuthorities;
    }

    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineNameAndProvider(String mineName, String provider, String status) {
        List<TaxAuthorityDTO> lstTaxAuthorities = null;
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("              SELECT ID id,   ");
        sqlQuery.append("                MA_CQT maCqt,  ");
        sqlQuery.append("                MA_QUAN_HUYEN maQuanHuyen,  ");
        sqlQuery.append("                MA_TINH maTinh,  ");
        sqlQuery.append("                STATUS status,  ");
        sqlQuery.append("                TEN_CQT tenCqt  ");
        sqlQuery.append("              FROM TAX_AUTHORITY  ");
        sqlQuery.append("              WHERE MA_CQT IN  ");
        sqlQuery.append("                ( SELECT DISTINCT c.TAX_AUTHORITY  ");
        sqlQuery.append("                FROM CUSTOMER c ");
        if (!DataUtil.isStringNullOrEmpty(provider)) {
            sqlQuery.append("                INNER JOIN TERM_INFORMATION ti ON ti.TAX_CODE = c.TAX_CODE ");
        }
        if (!DataUtil.isStringNullOrEmpty(mineName) || !DataUtil.isStringNullOrEmpty(status)) {
            sqlQuery.append("                INNER JOIN CUSTOMER_STATUS cs ON c.TAX_CODE = cs.TAX_CODE ");
        }
        sqlQuery.append("                   WHERE 1=1 ");
        if (!DataUtil.isStringNullOrEmpty(provider)) {
            sqlQuery.append("                   AND ti.IS_CONTACT_INFO is NULL ");
        }
        if (!DataUtil.isStringNullOrEmpty(mineName)) {
            if (mineName.contains(",")) {
                sqlQuery.append("                   AND cs.mine_name IN (:mineName)  ");
            } else {
                sqlQuery.append("                   AND cs.mine_name = :mineName  ");
            }
        }
        if (!DataUtil.isStringNullOrEmpty(provider)) {
            sqlQuery.append("                   AND lower(ti.provider) = ANY (:provider)  ");
        }
        if (!DataUtil.isStringNullOrEmpty(status)) {
            sqlQuery.append("                   AND cs.STATUS = :status  ");
        }
        sqlQuery.append("                ) ORDER BY maCqt");
        SQLQuery query;
        try {
            query = getSession().createSQLQuery(sqlQuery.toString());
            //Thuc hien chuyen du lieu lay ve thanh thanh doi tuong            
            query.setResultTransformer(Transformers.aliasToBean(TaxAuthorityDTO.class));
            query.addScalar("id", new StringType());
            query.addScalar("maCqt", new StringType());
            query.addScalar("maQuanHuyen", new StringType());
            query.addScalar("maTinh", new StringType());
            query.addScalar("status", new StringType());
            query.addScalar("tenCqt", new StringType());

            if (!DataUtil.isStringNullOrEmpty(mineName)) {
                if (mineName.contains(",")) {
                    query.setParameterList("mineName", DataUtil.parseInputListString(mineName));
                } else {
                    query.setParameter("mineName", mineName);
                }
            }
            if (!DataUtil.isStringNullOrEmpty(provider)) {
                query.setParameterList("provider", DataUtil.parseInputListString(provider.toLowerCase()));
            }
            if (!DataUtil.isStringNullOrEmpty(status)) {
                query.setParameter("status", status);
            }
            lstTaxAuthorities = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTaxAuthorities;
    }
}
