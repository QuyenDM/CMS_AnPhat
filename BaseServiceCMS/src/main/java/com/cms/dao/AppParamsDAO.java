/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dao;

import com.cms.dto.AppParamsDTO;
import com.vfw5.base.dao.BaseFWDAOImpl;
import com.cms.model.AppParams;
import com.vfw5.base.utils.DataUtil;
import com.vfw5.base.utils.StringUtils;
import java.util.List;
import java.util.Map;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
@Repository("appParamsDAO")
public class AppParamsDAO extends BaseFWDAOImpl<AppParams, Long> {

    public AppParamsDAO() {
        this.model = new AppParams();
    }

    public AppParamsDAO(Session session) {
        this.session = session;
    }

    public List<AppParamsDTO> getListProviderFromMineName(String mineName, Map<String, String> map) {
        List<AppParamsDTO> listAppParams = null;
        String taxAuthority = map.get("taxAuthority");
        String startFromDate = map.get("startFromDate");
        String endFromDate = map.get("endFromDate");
        String startToDate = map.get("startToDate");
        String endToDate = map.get("endToDate");
        String fromDateRegister = map.get("fromDateRegister");
        String toDateRegister = map.get("toDateRegister");
        StringBuilder sb = new StringBuilder();
        sb.append("		SELECT 	PAR_ID parId,	");
        sb.append("		  		DESCRIPTION description,	");
        sb.append("		  		PAR_CODE parCode,	");
        sb.append("		  		PAR_NAME parName,	");
        sb.append("		  		PAR_ORDER parOrder,	");
        sb.append("		  		PAR_TYPE parType,	");
        sb.append("		  		STATUS status	");
        sb.append("		  FROM APP_PARAMS a	");
        sb.append("		  WHERE a.PAR_TYPE = 'PROVIDER' ");
        sb.append("                     AND lower(a.PAR_CODE) = ANY	");
        sb.append("		  		(SELECT DISTINCT lower(ti.PROVIDER)	");
        sb.append("		  			FROM TERM_INFORMATION ti	");
        sb.append("		  			LEFT JOIN CUSTOMER_STATUS cs on ti.TAX_CODE=cs.TAX_CODE and ti.MINE_NAME = cs.MINE_NAME ");
        sb.append("		  			JOIN CUSTOMER c on c.TAX_CODE = ti.TAX_CODE ");
        sb.append("		  			WHERE ti.IS_CONTACT_INFO is null AND cs.TAX_CODE IS NULL	");
        sb.append("		  			AND ti.MINE_NAME = ANY (:mineName)	");

        if (!DataUtil.isStringNullOrEmpty(taxAuthority)) {
            sb.append("         and c.TAX_AUTHORITY = ANY(:taxAuthority)	");
        }

        if (!DataUtil.isStringNullOrEmpty(startFromDate)) {
            sb.append("         and ti.START_TIME >= TO_DATE(:startFromDate,'dd/MM/yyyy') - 1	");
        }
        if (!DataUtil.isStringNullOrEmpty(endFromDate)) {
            sb.append("         and ti.START_TIME <= TO_DATE(:endFromDate,'dd/MM/yyyy')	+ 1 ");
        }
        if (!DataUtil.isStringNullOrEmpty(startToDate)) {
            sb.append("         and ti.END_TIME >= TO_DATE(:startToDate,'dd/MM/yyyy') - 1 	");
        }
        if (!DataUtil.isStringNullOrEmpty(endToDate)) {
            sb.append("         and ti.END_TIME <= TO_DATE(:endToDate,'dd/MM/yyyy') + 1	");
        }
        if (!DataUtil.isStringNullOrEmpty(fromDateRegister)) {
            sb.append("   AND ti.DATE_REGISTER >= TO_DATE(:fromDateRegister,'dd/MM/yyyy') - 1 ");
        }
        if (!DataUtil.isStringNullOrEmpty(toDateRegister)) {
            sb.append("   AND ti.DATE_REGISTER <= TO_DATE(:toDateRegister,'dd/MM/yyyy') + 1");
        }
        sb.append("		  		)	");
        sb.append("		  ORDER BY a.PAR_CODE asc	");
        SQLQuery query;
        try {
            query = getSession().createSQLQuery(sb.toString());
            //Thuc hien chuyen du lieu lay ve thanh thanh doi tuong            
            query.setResultTransformer(Transformers.aliasToBean(AppParamsDTO.class));
            query.addScalar("parId", new StringType());
            query.addScalar("description", new StringType());
            query.addScalar("parCode", new StringType());
            query.addScalar("parName", new StringType());
            query.addScalar("parOrder", new StringType());
            query.addScalar("parType", new StringType());
            query.addScalar("status", new StringType());
            query.setParameterList("mineName", DataUtil.parseInputListString(mineName));
            if (!DataUtil.isStringNullOrEmpty(taxAuthority)) {
                query.setParameterList("taxAuthority", DataUtil.parseInputListString(taxAuthority));
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
            listAppParams = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAppParams;
    }

    public List<AppParamsDTO> getProviderFromStaffCode(String staffCode, String mineName) {
        List<AppParamsDTO> lstCategoryList = null;
        StringBuilder sb = new StringBuilder();
        sb.append("   SELECT 	PAR_ID parId,	");
        sb.append("		  	DESCRIPTION description,	");
        sb.append("		  	PAR_CODE parCode,	");
        sb.append("		  	PAR_NAME parName,	");
        sb.append("		  	PAR_ORDER parOrder,	");
        sb.append("		  	PAR_TYPE parType,	");
        sb.append("		  	STATUS status	");
        sb.append("		  FROM APP_PARAMS a	");
        sb.append("    WHERE        a.PAR_TYPE       = 'PROVIDER' ");
        sb.append("         AND a.STATUS = '1' ");
        sb.append("         AND lower(a.PAR_CODE) IN ");
        sb.append("      ( SELECT DISTINCT lower(ti.provider) ");
        sb.append("        FROM TERM_INFORMATION ti ");
        sb.append("        WHERE ti.IS_CONTACT_INFO is NULL AND exists ");
        sb.append("             (   SELECT DISTINCT cs.tax_code ");
        sb.append("                 FROM customer_status cs ");
        sb.append("                 WHERE 1 = 1 ");
        sb.append("                 AND cs.tax_code = ti.tax_code  ");
        if (!StringUtils.isStringNullOrEmpty(staffCode)) {
            sb.append("             AND cs.staff_code = :staffCode ");
        } else { //Add 15/04/2017 Them dieu kien neu la admin
            return getProviderFromMineName(mineName);
        }
        if (!StringUtils.isStringNullOrEmpty(mineName)) {
            if (mineName.contains(",")) {
                sb.append("             AND cs.mine_name IN (:mineName) ");
            } else {
                sb.append("             AND cs.mine_name = :mineName ");
            }
        }
        sb.append("             ) ");
        sb.append("      ) ");
        try {
            SQLQuery query = getSession().createSQLQuery(sb.toString());
//            if (!DataUtil.isStringNullOrEmpty(mineName)) {
//                
//                query.setParameter("mineName", mineName);
//            }
            if (!DataUtil.isStringNullOrEmpty(mineName)) {
                if (mineName.contains(",")) {
                    query.setParameterList("mineName", DataUtil.parseInputListString(mineName));
                } else {
                    query.setParameter("mineName", mineName);
                }
            }
            if (!StringUtils.isStringNullOrEmpty(staffCode)) {
                query.setParameter("staffCode", staffCode);
            }
            query.setResultTransformer(Transformers.aliasToBean(AppParamsDTO.class));
            query.addScalar("parId", new StringType());
            query.addScalar("description", new StringType());
            query.addScalar("parCode", new StringType());
            query.addScalar("parName", new StringType());
            query.addScalar("parOrder", new StringType());
            query.addScalar("parType", new StringType());
            query.addScalar("status", new StringType());
            lstCategoryList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCategoryList;
    }

    public List<AppParamsDTO> getProviderFromStaffCodeAndConditions(String staffCode, Map<String, String> map) {
        
        List<AppParamsDTO> lstCategoryList = null;
        String mineName = map.get("mineName");
        String taxAuthority = map.get("taxAuthority");
        String status = map.get("status");

        if (StringUtils.isStringNullOrEmpty(staffCode)) {       
         //Add 17/04/2017 Them dieu kien neu la admin + cac dieu kien status, tinh
            return getProviderFromOtherConditions(mineName, taxAuthority, status);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("   SELECT 	PAR_ID parId,	");
        sb.append("		  	DESCRIPTION description,	");
        sb.append("		  	PAR_CODE parCode,	");
        sb.append("		  	PAR_NAME parName,	");
        sb.append("		  	PAR_ORDER parOrder,	");
        sb.append("		  	PAR_TYPE parType,	");
        sb.append("		  	STATUS status	");
        sb.append("		  FROM APP_PARAMS a	");
        sb.append("    WHERE        a.PAR_TYPE       = 'PROVIDER' ");
        sb.append("         AND a.STATUS = '1' ");
        sb.append("         AND lower(a.PAR_CODE) IN ");
        sb.append("      ( SELECT DISTINCT lower(ti.provider) ");
        sb.append("        FROM TERM_INFORMATION ti ");

        if (!StringUtils.isStringNullOrEmpty(taxAuthority)) {
            sb.append("        INNER JOIN CUSTOMER c ON c.TAX_CODE = ti.TAX_CODE ");
        }
        sb.append("        WHERE 1= 1 ");
        if (!StringUtils.isStringNullOrEmpty(taxAuthority)) {
            sb.append("    AND c.TAX_AUTHORITY = ANY(:taxAuthority) ");
        }
        sb.append("        AND  exists ");
        sb.append("             (   SELECT DISTINCT cs.tax_code ");
        sb.append("                 FROM customer_status cs ");
        sb.append("                 WHERE 1 = 1  ");
        sb.append("                 AND cs.tax_code = ti.tax_code  ");
        if (!StringUtils.isStringNullOrEmpty(status)) {
            sb.append("                 AND cs.status = ANY (:status) ");
        }
        if (!StringUtils.isStringNullOrEmpty(staffCode)) {
            sb.append("             AND cs.staff_code = :staffCode ");
        } else { //Add 17/04/2017 Them dieu kien neu la admin + cac dieu kien status, tinh
            return getProviderFromOtherConditions(mineName, taxAuthority, status);
        }
        if (!StringUtils.isStringNullOrEmpty(mineName)) {
            if (mineName.contains(",")) {
                sb.append("             AND cs.mine_name IN (:mineName) ");
            } else {
                sb.append("             AND cs.mine_name = :mineName ");
            }
        }
        sb.append("             ) ");
        sb.append("      ) ");
        try {
            SQLQuery query = getSession().createSQLQuery(sb.toString());
            if (!DataUtil.isStringNullOrEmpty(mineName)) {
                if (mineName.contains(",")) {
                    query.setParameterList("mineName", DataUtil.parseInputListString(mineName));
                } else {
                    query.setParameter("mineName", mineName);
                }
            }
            if (!StringUtils.isStringNullOrEmpty(staffCode)) {
                query.setParameter("staffCode", staffCode);
            }
            if (!StringUtils.isStringNullOrEmpty(status)) {
                query.setParameterList("status", DataUtil.parseInputListString(status));
            }
            if (!StringUtils.isStringNullOrEmpty(taxAuthority)) {
                query.setParameterList("taxAuthority", DataUtil.parseInputListString(taxAuthority));
            }
            query.setResultTransformer(Transformers.aliasToBean(AppParamsDTO.class));
            query.addScalar("parId", new StringType());
            query.addScalar("description", new StringType());
            query.addScalar("parCode", new StringType());
            query.addScalar("parName", new StringType());
            query.addScalar("parOrder", new StringType());
            query.addScalar("parType", new StringType());
            query.addScalar("status", new StringType());
            lstCategoryList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCategoryList;
    }

    public List<AppParamsDTO> getProviderFromMineName(String mineName) {
        List<AppParamsDTO> lstCategoryList = null;
        StringBuilder sb = new StringBuilder();
        sb.append("   SELECT 	ap.PAR_ID parId,	");
        sb.append("		  	ap.DESCRIPTION description,	");
        sb.append("		  	ap.PAR_CODE parCode,	");
        sb.append("		  	ap.PAR_NAME parName,	");
        sb.append("		  	ap.PAR_ORDER parOrder,	");
        sb.append("		  	ap.PAR_TYPE parType,	");
        sb.append("		  	ap.STATUS status	");
        sb.append("   FROM APP_PARAMS ap ");
        sb.append("   WHERE PAR_CODE IN ");
        sb.append("     (SELECT DISTINCT provider ");
        sb.append("     FROM term_information ti ");
        sb.append("     WHERE ti.IS_CONTACT_INFO is NULL AND lower(ti.PROVIDER) LIKE (lower(ap.par_code)||'%') ");
        if (!StringUtils.isStringNullOrEmpty(mineName)) {
            if (mineName.contains(",")) {
                sb.append("             AND ti.mine_name IN (:mineName) ");
            } else {
                sb.append("             AND ti.mine_name = :mineName ");
            }
//            sb.append("     AND ti.MINE_NAME = :mineName ");
        }
        sb.append("     ) ");
        sb.append("   ORDER BY ap.PAR_ORDER ");

        try {
            SQLQuery query = getSession().createSQLQuery(sb.toString());
            if (!DataUtil.isStringNullOrEmpty(mineName)) {
                if (mineName.contains(",")) {
                    query.setParameterList("mineName", DataUtil.parseInputListString(mineName));
                } else {
                    query.setParameter("mineName", mineName);
                }
            }

            query.setResultTransformer(Transformers.aliasToBean(AppParamsDTO.class));
            query.addScalar("parId", new StringType());
            query.addScalar("description", new StringType());
            query.addScalar("parCode", new StringType());
            query.addScalar("parName", new StringType());
            query.addScalar("parOrder", new StringType());
            query.addScalar("parType", new StringType());
            query.addScalar("status", new StringType());
            lstCategoryList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCategoryList;
    }

    public List<AppParamsDTO> getProviderFromOtherConditions(String mineName, String taxAuthority, String status) {
        List<AppParamsDTO> lstCategoryList = null;
        StringBuilder sb = new StringBuilder();
        sb.append("   SELECT 	ap.PAR_ID parId,	");
        sb.append("		  	ap.DESCRIPTION description,	");
        sb.append("		  	ap.PAR_CODE parCode,	");
        sb.append("		  	ap.PAR_NAME parName,	");
        sb.append("		  	ap.PAR_ORDER parOrder,	");
        sb.append("		  	ap.PAR_TYPE parType,	");
        sb.append("		  	ap.STATUS status	");
        sb.append("   FROM APP_PARAMS ap ");
        sb.append("   WHERE lower(PAR_CODE) IN ");
        sb.append("     (SELECT DISTINCT lower(provider) ");
        sb.append("     FROM term_information ti ");
        if (!StringUtils.isStringNullOrEmpty(taxAuthority)) {
            sb.append("     INNER JOIN CUSTOMER c ON c.TAX_CODE = ti.TAX_CODE ");
        }
        if (!StringUtils.isStringNullOrEmpty(status)) {
            sb.append("     INNER JOIN CUSTOMER_STATUS cs ON cs.TAX_CODE = ti.TAX_CODE ");
        }
        sb.append("     WHERE ti.IS_CONTACT_INFO is NULL AND lower(ti.PROVIDER) = (lower(ap.par_code))");
        if (!StringUtils.isStringNullOrEmpty(mineName)) {
            if (mineName.contains(",")) {
                sb.append("             AND ti.mine_name IN (:mineName) ");
            } else {
                sb.append("             AND ti.mine_name = :mineName ");
            }
        }
        if (!StringUtils.isStringNullOrEmpty(taxAuthority)) {
            sb.append("     AND c.TAX_AUTHORITY = ANY (:taxAuthority) ");
        }
        if (!StringUtils.isStringNullOrEmpty(status)) {
            sb.append("     AND cs.STATUS = :status ");
        }
        sb.append("     ) ");
        sb.append("   ORDER BY ap.PAR_ORDER ");

        try {
            SQLQuery query = getSession().createSQLQuery(sb.toString());
            if (!DataUtil.isStringNullOrEmpty(mineName)) {
                if (mineName.contains(",")) {
                    query.setParameterList("mineName", DataUtil.parseInputListString(mineName));
                } else {
                    query.setParameter("mineName", mineName);
                }
            }
            if (!StringUtils.isStringNullOrEmpty(taxAuthority)) {
                query.setParameterList("taxAuthority", DataUtil.parseInputListString(taxAuthority));
            }
            if (!StringUtils.isStringNullOrEmpty(status)) {
                query.setParameter("status", status);
            }
            query.setResultTransformer(Transformers.aliasToBean(AppParamsDTO.class));
            query.addScalar("parId", new StringType());
            query.addScalar("description", new StringType());
            query.addScalar("parCode", new StringType());
            query.addScalar("parName", new StringType());
            query.addScalar("parOrder", new StringType());
            query.addScalar("parType", new StringType());
            query.addScalar("status", new StringType());
            lstCategoryList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCategoryList;
    }

    public List<AppParamsDTO> getStatusFromStaffCode(String staffCode, String mineName) {
        List<AppParamsDTO> lstCategoryList = null;
        StringBuilder sb = new StringBuilder();
        sb.append("   SELECT 	PAR_ID parId,	");
        sb.append("		  	DESCRIPTION description,	");
        sb.append("		  	PAR_CODE parCode,	");
        sb.append("		  	PAR_NAME parName,	");
        sb.append("		  	PAR_ORDER parOrder,	");
        sb.append("		  	PAR_TYPE parType,	");
        sb.append("		  	STATUS status	");
        sb.append("		  FROM APP_PARAMS a	");
        sb.append("    WHERE        a.PAR_TYPE       = 'CUSTOMER_SERVICE_STATUS' ");
        sb.append("         AND a.STATUS = '1' ");
        sb.append("         AND a.PAR_CODE IN ");
        sb.append("             (   SELECT DISTINCT cs.status ");
        sb.append("                 FROM customer_status cs ");
        sb.append("                 WHERE 1 = 1  ");
        if (!StringUtils.isStringNullOrEmpty(staffCode)) {
            sb.append("             AND cs.staff_code = :staffCode ");
        }
        if (!StringUtils.isStringNullOrEmpty(mineName)) {
            if (mineName.contains(",")) {
                sb.append("             AND cs.mine_name IN (:mineName) ");
            } else {
                sb.append("             AND cs.mine_name = :mineName ");
            }
        }
        sb.append("             ) ");
        try {
            SQLQuery query = getSession().createSQLQuery(sb.toString());
            if (!DataUtil.isStringNullOrEmpty(mineName)) {
                if (mineName.contains(",")) {
                    query.setParameterList("mineName", DataUtil.parseInputListString(mineName));
                } else {
                    query.setParameter("mineName", mineName);
                }

            }
            if (!StringUtils.isStringNullOrEmpty(staffCode)) {
                query.setParameter("staffCode", staffCode);
            }
            query.setResultTransformer(Transformers.aliasToBean(AppParamsDTO.class));
            query.addScalar("parId", new StringType());
            query.addScalar("description", new StringType());
            query.addScalar("parCode", new StringType());
            query.addScalar("parName", new StringType());
            query.addScalar("parOrder", new StringType());
            query.addScalar("parType", new StringType());
            query.addScalar("status", new StringType());
            lstCategoryList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCategoryList;
    }

}
