/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dao;

import com.cms.dto.ObjectsDTO;
import com.cms.dto.RolesDTO;
import com.cms.model.Objects;
import com.vfw5.base.dao.BaseFWDAOImpl;
import com.vfw5.base.utils.DataUtil;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:18 AM
 */
@Repository("objectsDAO")
public class ObjectsDAO extends BaseFWDAOImpl<Objects, Long> {

    public ObjectsDAO() {
        this.model = new Objects();
    }

    public ObjectsDAO(Session session) {
        this.session = session;
    }

    public List<ObjectsDTO> getLstObjectsDTOByStaffId(String staffId) {
        List<ObjectsDTO> lstObjects;
        StringBuilder sql = new StringBuilder();

        sql.append("     SELECT DISTINCT a.OBJECT_ID objectId, ");
        sql.append("                     a.CODE code, ");
        sql.append("                     a.NAME name, ");
        sql.append("                     a.DESCRIPTION description, ");
        sql.append("                     a.OBJECT_TYPE objectType, ");
        sql.append("                     a.STATUS status, ");
        sql.append("                     a.URL url ");
        sql.append("        FROM OBJECTS a ");
        sql.append("        JOIN ROLE_OBJECTS b ");
        sql.append("        	ON b.OBJECT_ID = a.OBJECT_ID ");
        sql.append("        AND b.ROLE_ID IN ");
        sql.append("           	(SELECT a.ROLE_ID ");
        sql.append("           	FROM roles a ");
        sql.append("           	JOIN MAP_STAFF_ROLES b ");
        sql.append("           	ON b.ROLE_ID   = a.ROLE_ID ");
        sql.append("           	AND b.STAFF_ID = ? )");
        try {
            SQLQuery query = getSession().createSQLQuery(sql.toString());
            query.setResultTransformer(Transformers.aliasToBean(ObjectsDTO.class));
            query.addScalar("objectId", new StringType());
            query.addScalar("code", new StringType());
            query.addScalar("name", new StringType());
            query.addScalar("description", new StringType());
            query.addScalar("objectType", new StringType());
            query.addScalar("status", new StringType());
            query.addScalar("url", new StringType());
            //
            query.setParameter(0, staffId);

            lstObjects = query.list();
        } catch (Exception e) {
            e.printStackTrace();
            lstObjects = null;
        }
        if (DataUtil.isListNullOrEmpty(lstObjects)) {
            return null;
        } else {
            return lstObjects;
        }
    }

    public List<ObjectsDTO> getListObjectByRole(RolesDTO role) {
        List<ObjectsDTO> lstObjects;
        StringBuilder sql = new StringBuilder();
//        List lstParams = new ArrayList();
        sql.append("     SELECT DISTINCT a.OBJECT_ID objectId, ");
        sql.append("                     a.CODE code, ");
        sql.append("                     a.NAME name,");
        sql.append("                     a.DESCRIPTION description, ");
        sql.append("                     a.OBJECT_TYPE objectType, ");
        sql.append("                     a.STATUS status, ");
        sql.append("                     a.URL url, ");
        sql.append("                     b.ID roleObjectId ");
        sql.append("        FROM OBJECTS a ");
        sql.append("        JOIN ROLE_OBJECTS b ");
        sql.append("             ON b.OBJECT_ID = a.OBJECT_ID ");
        sql.append("            AND b.ROLE_ID = ? ");
        try {
            SQLQuery query = getSession().createSQLQuery(sql.toString());
            query.setResultTransformer(Transformers.aliasToBean(ObjectsDTO.class));
            query.addScalar("objectId", new StringType());
            query.addScalar("code", new StringType());
            query.addScalar("name", new StringType());
            query.addScalar("description", new StringType());
            query.addScalar("objectType", new StringType());
            query.addScalar("status", new StringType());
            query.addScalar("url", new StringType());
            query.addScalar("roleObjectId", new StringType());

            query.setParameter(0, role.getRoleId());

            lstObjects = query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lstObjects;
    }
}
