/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dao;

import com.cms.dto.ObjectsDTO;
import com.cms.dto.RolesDTO;
import com.vfw5.base.dao.BaseFWDAOImpl;
import com.cms.model.Roles;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:27 AM
 */
@Repository("rolesDAO")
public class RolesDAO extends BaseFWDAOImpl<Roles, Long> {

    public RolesDAO() {
        this.model = new Roles();
    }

    public RolesDAO(Session session) {
        this.session = session;
    }

    /**
     * Lay danh sach vai tro du nhan vien
     *
     * @param staffId
     * @return
     */
    public List<RolesDTO> getListRolesByStaffId(String staffId) {
        List<RolesDTO> lstRoles;
        StringBuilder sql = new StringBuilder();
        sql.append("     SELECT DISTINCT a.ROLE_ID roleId, ");
        sql.append("                     a.CODE code, ");
        sql.append("                     a.NAME name, ");
        sql.append("                     a.DESCRIPTION description, ");
        sql.append("                     a.STATUS status, ");
        sql.append("                     b.MAP_ID mapId ");
        sql.append("        FROM roles a ");
        sql.append("        JOIN MAP_STAFF_ROLES b ");
        sql.append("                  ON b.ROLE_ID   = a.ROLE_ID ");
        sql.append("                 AND b.STAFF_ID = ? ");
        try {
            SQLQuery query = getSession().createSQLQuery(sql.toString());
            query.setResultTransformer(Transformers.aliasToBean(RolesDTO.class));
            query.addScalar("roleId", new StringType());
            query.addScalar("code", new StringType());
            query.addScalar("name", new StringType());
            query.addScalar("description", new StringType());
            query.addScalar("status", new StringType());
            query.addScalar("mapId", new StringType());

            query.setParameter(0, staffId);

            lstRoles = query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lstRoles;
    }
}
