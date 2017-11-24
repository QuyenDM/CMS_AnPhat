/*
 * Copyright (C) 2015 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.vfw5.base.dao;

import com.vfw5.base.model.BaseFWModel;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.Constants;
import com.vfw5.base.utils.DataUtil;
import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.StringUtils;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.PersistenceException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author BinhNT22
 * @version: 2.0
 * @param <T> - a Model that extends BaseFWModel
 * @param <ID> - id of the model
 * @since: 31/03/2015
 */
@Repository
public class BaseFWDAOImpl<T extends BaseFWModel, ID extends Serializable> {

    // Trong truong hop co nhieu data source thi tao SessionFactory moi
    // extend HibernateSessionFactory va khai bao them vao hibernate-config.xml
    long i;
    @Autowired
    public SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Session session;
    protected static Logger log = Logger.getLogger(BaseFWDAOImpl.class);
    protected T model;

    public List<T> findByCriteria(
            String sessionName, int firstResult, int maxResult,
            ProjectionList projs, Criterion[] criterion, Order[] orders) {
        Criteria crit
                = getSession().createCriteria(model.getClass());
        if (projs != null) {
            crit.setProjection(projs);
        }
        if (criterion != null && criterion.length > 0) {
            for (Criterion c : criterion) {
                crit.add(c);
            }
        }
        if (orders != null && orders.length > 0) {
            for (Order o : orders) {
                crit.addOrder(o);
            }
        }
        if (firstResult > 0) {
            crit.setFirstResult(firstResult);
        }
        if (maxResult > 0) {
            crit.setMaxResults(maxResult);
        }
        return crit.list();
    }

    public List<T> findByCriteria(ProjectionList projections,
            Criterion[] criterions, Order[] orders) {
        return findByCriteria("default session", 0, 0,
                projections, criterions, orders);
    }

    public List<T> findByCriteria(ProjectionList projections,
            Criterion[] criterions, Order order) {
        Order orders[] = new Order[1];
        orders[0] = order;
        return findByCriteria("default session", 0, 0,
                projections, criterions, orders);
    }

    public List<T> findByCriteria(ProjectionList projections, Order order) {
        Order orders[] = new Order[1];
        orders[0] = order;
        return findByCriteria("default session", 0, 0,
                projections, null, orders);
    }

    public List<T> findByCriteria(
            String sessionName, int firstResult, int maxResult,
            Criterion[] criterions, Order[] orders) {
        return findByCriteria(sessionName, firstResult, maxResult,
                null, criterions, orders);
    }

    public List<T> findByCriteria(int firstResult, int maxResult,
            Criterion[] criterion, Order[] orders) {
        return findByCriteria("default session",
                firstResult, maxResult, criterion, orders);
    }

    public List<T> findByCriteria(
            String sessionName, Criterion[] criterion, Order[] orders) {
        return findByCriteria(sessionName, 0, 0, criterion, orders);
    }

    public List<T> findByCriteria(Criterion[] criterion, Order[] orders) {
        return findByCriteria("default session", criterion, orders);
    }

    public List<T> findByCriteria(Criterion[] criterion, Order order) {
        Order orders[] = new Order[1];
        orders[0] = order;
        return findByCriteria("default session", criterion, orders);
    }

    public List<T> findByCriteria(Criterion crit, Order order) {
        Order orders[] = new Order[1];
        orders[0] = order;
        Criterion[] criterion = new Criterion[1];
        criterion[0] = crit;
        return findByCriteria("default session", criterion, orders);
    }

    public List<T> findByCriteria(String sessionName,
            int firstResult, int maxResult, Order[] orders) {
        return findByCriteria(sessionName,
                firstResult, maxResult, new Criterion[0], orders);
    }

    public List<T> findByCriteria(int firstResult, int maxResult, Order[] orders) {
        return findByCriteria("default session",
                firstResult, maxResult, new Criterion[0], orders);
    }

    public List<T> findByCriteria(String sessionName,
            int firstResult, int maxResult) {
        return findByCriteria(sessionName, firstResult, maxResult, new Order[0]);
    }

    public List<T> findByCriteria(int firstResult, int maxResult) {
        return findByCriteria("default session", firstResult, maxResult);
    }

    public List<T> findAll(String sessionName, Order[] orders) {
        return findByCriteria(sessionName, new Criterion[0], orders);
    }

    public List<T> findAll(Order[] orders) {
        return findAll("default session", orders);
    }

    public List<T> findAll(Order order) {
        Order orders[] = new Order[1];
        orders[0] = order;
        return findAll("default session", orders);
    }

    //
    public Long getSequenId(String sequense, Session session) {
        String sql = "select " + sequense + ".nextval from dual";
        Query query = session.createSQLQuery(sql);
        return ((BigDecimal) query.list().get(0)).longValue();
    }

    public List<T> findDistinct(String[] pros, Criterion[] crits) {
        ProjectionList listProjections = Projections.projectionList();
        ProjectionList listProperties = Projections.projectionList();
        Order[] orders = new Order[pros.length];
        int index = 0;
        for (String property : pros) {
            listProperties.add(Projections.property(property));
            orders[index++] = Order.asc(property);
        }
        listProjections.add(Projections.distinct(listProperties));
        return findByCriteria("default session", 0, 0, listProjections, crits, orders);
    }

    public List<T> findDistinct(String[] pros) {
        return findDistinct(pros, new Criterion[0]);
    }

    public List<T> findByCriteria(Criterion[] crits) {
        return findByCriteria(crits, new Order[0]);
    }

    public List<T> findByCondition(int firstResult, int maxResult, Criterion[] crits) {
        return findByCriteria("default session", firstResult, maxResult, crits, new Order[0]);
    }

    @Transactional
    public String delete(T obj) {
        try {
            getSession().delete(obj);
            return ParamUtils.SUCCESS;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    @Transactional
    public String save(T obj) {
        try {
            getSession().save(obj);
            return ParamUtils.SUCCESS;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    @Transactional
    public String saveList(List<T> obj, String methodName) {
        try {
//            Class c = obj.get(0).getClass();
//            Method method = c.getMethod("get" + StringUtils.upperFirstChar(methodName));
            Session session = getSession();
            for (T item : obj) {
                session.saveOrUpdate(item);
//                String value;
//                try {
//                    value = String.valueOf(method.invoke(item));
//
//                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//                    return ParamUtils.ERROR;
//                }
//
//                if (value == "null") {
//                    getSession().save(item);
//                } else {
//                    getSession().update(item);
//                }
            }
            return ParamUtils.SUCCESS;
        } catch (Exception ex) {
            return ex.getMessage();
        }

    }

    @Transactional
    public String saveList(List<T> obj, String methodName, Session session) {
        try {
            Class c = obj.get(0).getClass();
            Method method = c.getMethod("get" + StringUtils.upperFirstChar(methodName));

            for (T item : obj) {
                String value;
                try {
                    value = String.valueOf(method.invoke(item));

                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    return ParamUtils.ERROR;
                }

                if (value == "null") {
                    session.save(item);
                } else {
                    session.update(item);
                }
            }
            return ParamUtils.SUCCESS;
        } catch (NoSuchMethodException | SecurityException ex) {
            session.getTransaction().rollback();
            return ex.getMessage();

        }

    }

    @Transactional
    public String update(T obj) {
        try {
            getSession().update(obj);
            return ParamUtils.SUCCESS;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    public List<T> find(String boName, List<ConditionBean> lstCondition) {
        return find(boName, lstCondition, null, 0, 0, null);
    }

    public List<T> find(String boName, List<ConditionBean> lstCondition, String logic) {
        return find(boName, lstCondition, null, 0, 0, logic);
    }

    public List<T> find(String boName, List<ConditionBean> lstCondition, String order, int start, int maxResult) {
        return find(boName, lstCondition, order, start, maxResult, null);
    }

    public List<T> find(String boName, List<ConditionBean> lstCondition, String order, int start, int maxResult, String logic) {
//        if (logic == null) {
//            logic = ParamUtils.LOGIC_AND;
//        }
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" from ");
            sql.append(boName);
            sql.append(" where 1=1 ");
            if (lstCondition != null && lstCondition.size() > 0) {
                buildConditionQuery(sql, lstCondition);
            }
            if (order != null && !order.equals("")) {
                sql.append(" order by ");
                sql.append(order);
            }
            Query query = getSession().createQuery(sql.toString());
            if (maxResult != 0) {
                query.setFirstResult(start);
                query.setMaxResults(maxResult);
            }
            fillConditionQuery(query, lstCondition);
            return query.list();
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return null;
        }
    }
//
//    public String upDateByCondition(String boName, List<ConditionBean> lstCondition) {
////        if (logic == null) {
////            logic = ParamUtils.LOGIC_AND;
////        }
//        try {
//            StringBuilder sql = new StringBuilder();
//            sql.append(" update ");
//            sql.append(boName);
//            sql.append(" set ");
//            if (lstCondition != null && lstCondition.size() > 0) {
//                buildConditionQuery(sql, lstCondition);
//            }
//
//            Query query = getSession().createQuery(sql.toString());
//
//            fillConditionQuery(query, lstCondition);
//            return query.list();
//        } catch (HibernateException he) {
//            log.error(he.getMessage(), he);
//            return null;
//        }
//    }

//    public String delete(String boName, List<ConditionBean> lstCondition, Long transId) {
//        try {
//            List<T> listDelete = find(boName, lstCondition);
//            if (listDelete != null && !listDelete.isEmpty()) {
//                for (T obj : listDelete) {
//                    List<RaRecycleBinBO> listRecycle = getListRecycle(obj, transId);
//                    if (listRecycle != null && !listRecycle.isEmpty()) {
//                        for (RaRecycleBinBO bo : listRecycle) {
//                            session.save(bo);
//                        }
//                    }
//                    session.delete(obj);
//                }
//            }
//            return ParamUtils.SUCCESS;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return e.getMessage();
//        }
//
//    }
    public String delete(String boName, List<ConditionBean> lstCondition) {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("delete from ");
            sql.append(boName);
            sql.append(" where 1=1 ");
            int result = 0;
            if (lstCondition != null) {
                buildConditionQuery(sql, lstCondition);
                Query query = getSession().createQuery(sql.toString());
                fillConditionQuery(query, lstCondition);
                result = query.executeUpdate();
            }
            if (result == 0) {
                return ParamUtils.FAIL;
            } else {
                return ParamUtils.SUCCESS;
            }
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    public int deleteList(String boName, List<ConditionBean> lstCondition) {
        try {
//            Transaction tx = (Transaction) getSession().beginTransaction();
            StringBuilder sql = new StringBuilder();
            sql.append("delete from ");
            sql.append(boName);
            sql.append(" where 1=1 ");
            int result = 0;
            if (lstCondition != null) {
                buildConditionQueryDeleteList(sql, lstCondition);
                Query query = getSession().createQuery(sql.toString());
                fillConditionQuery(query, lstCondition);
                result = query.executeUpdate();

            }

//            if (result == 0) {
//                return ParamUtils.FAIL;
//            } else {
//                return ParamUtils.SUCCESS;
//            }
            return result;
        } catch (HibernateException he) {
            return 0;
        }
    }

    public long count(String boName, List<ConditionBean> lstCondition) {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select count(*) from ");
            sql.append(boName);
            sql.append(" where 1=1 ");
            long result = 0;
            if (lstCondition != null) {
                buildConditionQuery(sql, lstCondition);
                Query query = getSession().createQuery(sql.toString());
                fillConditionQuery(query, lstCondition);
                result = (Long) query.list().get(0);
            }
            return result;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return 0l;
        }
    }

    public void buildConditionQuery(StringBuilder sql, List<ConditionBean> lstCondition) {
        if (lstCondition != null) {
            int index = 0;
            for (ConditionBean con : lstCondition) {
                sql.append(ParamUtils.LOGIC_AND);
                sql.append(con.getField());
                sql.append(con.getOperator());
                if (con.getType().equals(ParamUtils.TYPE_STRING)) {
                    sql.append(":idx").append(String.valueOf(index++));
                    if (con.getOperator().equals(ParamUtils.OP_LIKE)) {
                        sql.append(" ESCAPE '\\' ");
                    }
                } else if (con.getType().equals(ParamUtils.TYPE_DATE)) {
                    sql.append(" to_date(:idx").append(String.valueOf(index++))
                            .append(", '").append(ParamUtils.ddMMyyyy).append("')");
                } else if (!con.getOperator().equals(ParamUtils.OP_IN)) {
                    sql.append(" :idx").append(String.valueOf(index++));
                } else {
                    sql.append("( :idx").append(String.valueOf(index++)).append(" )");
                }
            }
        }
    }

    //Test
    public void buildConditionQueryDeleteList(StringBuilder sql, List<ConditionBean> lstCondition) {
        if (lstCondition != null) {
            int index = 0;
            sql.append(ParamUtils.LOGIC_AND);
            for (ConditionBean con : lstCondition) {
                if (con != lstCondition.get(0)) {
                    sql.append(ParamUtils.LOGIC_OR);
                }
                sql.append(con.getField());
                sql.append(con.getOperator());
                if (con.getType().equals(ParamUtils.TYPE_STRING)) {
                    sql.append(":idx").append(String.valueOf(index++));
                    if (con.getOperator().equals(ParamUtils.OP_LIKE)) {
                        sql.append(" ESCAPE '\\' ");
                    }
                } else if (con.getType().equals(ParamUtils.TYPE_DATE)) {
                    sql.append(" to_date(:idx").append(String.valueOf(index++))
                            .append(", '").append(ParamUtils.ddMMyyyy).append("')");
                } else if (!con.getOperator().equals(ParamUtils.OP_IN)) {
                    sql.append(":idx").append(String.valueOf(index++));
                } else {
                    sql.append(con.getValue());
                }
            }
        }
    }
    //endTest

    public void fillConditionQuery(Query query, List<ConditionBean> lstCondition) {
        int index = 0;
        for (ConditionBean con : lstCondition) {
            if (con.getType().equals(ParamUtils.TYPE_NUMBER)) {
                if (!con.getOperator().equals(ParamUtils.OP_IN)) {
                    query.setParameter("idx" + String.valueOf(index++), Long.parseLong(con.getValue()));
                } else {
                    query.setParameterList("idx" + String.valueOf(index++), DataUtil.parseInputListLong(con.getValue()));
                }

            } else if (con.getType().equals(ParamUtils.NUMBER_DOUBLE)) {
                if (!con.getOperator().equals(ParamUtils.OP_IN)) {
                    query.setParameter("idx" + String.valueOf(index++), Double.parseDouble(con.getValue()));
                } else {
                    query.setParameterList("idx" + String.valueOf(index++), DataUtil.parseInputListDouble(con.getValue()));
                }
            } else if (con.getType().equals(ParamUtils.TYPE_STRING)) {
                if (con.getOperator().equals(ParamUtils.OP_IN)) {
                    query.setParameterList("idx" + String.valueOf(index++), DataUtil.parseInputListString(con.getValue()));
                } else {
                    query.setParameter("idx" + String.valueOf(index++), con.getValue());
                }
            } else {
                query.setParameter("idx" + String.valueOf(index++), con.getValue());
            }
        }
    }

    public static String genSubQuery(String tableName, String colId, String colName, String value) {
        return "(select " + colId + tableName + " where " + StringUtils.formatFunction("lower", colName) + " like '" + StringUtils.formatLike(value) + "'  ESCAPE '\\' )";
    }

    public Long getTransactionId() {
        String sql = "select " + Constants.TRANSACTION_SEQUENCE + ".nextval from dual";
        Query query = getSession().createSQLQuery(sql);
        return ((BigDecimal) query.list().get(0)).longValue();
    }

    public Long getSequenId(String sequense) {
        String sql = "select " + sequense + ".nextval from dual";
        Query query = getSession().createSQLQuery(sql);
        return ((BigDecimal) query.list().get(0)).longValue();

    }

    public String saveObject(T obj) {
        try {
            long id = (long) getSession().save(obj);
            getSession().flush();
            return String.valueOf(id);
        } catch (PersistenceException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        } catch (EJBTransactionRolledbackException e) {
            return e.getMessage();
        }

    }

    public boolean saveObjectNoReturnError(T obj) {
        try {
            long id = (long) getSession().save(obj);
            getSession().flush();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public String saveObject(T obj, Session session) {
        try {
            long id = (long) session.save(obj);
            return String.valueOf(id);
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            session.getTransaction().rollback();
            return he.getMessage();
        }
    }

    //
    public String getSysDate(String pattern) throws Exception {
        String queryString = "SELECT to_char(sysdate,:id)  from dual";
        Query query = getSession().createSQLQuery(queryString);
        query.setParameter("id", pattern);
        return query.list().get(0).toString();

    }

    public String getSysDateExtend(String pattern, int addDay) throws Exception {
        String queryString = "SELECT to_char(sysdate + :addDay ,:id)  from dual";
        Query query = getSession().createSQLQuery(queryString);
        query.setParameter("addDay", addDay);
        query.setParameter("id", pattern);
        return query.list().get(0).toString();

    }

    public List<T> findSession(String boName, List<ConditionBean> lstCondition, String order, int start, int maxResult, String logic, Session session) {
//        if (logic == null) {
//            logic = ParamUtils.LOGIC_AND;
//        }
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" from ");
            sql.append(boName);
            sql.append(" where 1=1 ");
            if (lstCondition != null && lstCondition.size() > 0) {
                buildConditionQuery(sql, lstCondition);
            }
            if (order != null && !order.equals("")) {
                sql.append(" order by ");
                sql.append(order);
            }
            Query query = session.createQuery(sql.toString());
            if (maxResult != 0) {
                query.setFirstResult(start);
                query.setMaxResults(maxResult);
            }
            fillConditionQuery(query, lstCondition);
            return query.list();
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return null;
        }
    }

//    public List<RaRecycleBinBO> getListRecycle(T obj, Long transId) {
//        List<RaRecycleBinBO> listRecycle = new ArrayList<RaRecycleBinBO>();
//        if (obj != null) {
//            Class c = obj.getClass();
//            Table t = (Table) c.getAnnotation(Table.class);
//            Method methods[] = c.getDeclaredMethods();
//            Long recordId = null;
//            for (Method method : methods) {
//                if (ReflectUtils.isGetter(method)) {
//                    if (method.getAnnotation(Id.class) != null) {
//                        try {
//                            recordId = (Long) method.invoke(obj);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//            for (Method method : methods) {
//                if (ReflectUtils.isGetter(method)) {
//                    RaRecycleBinBO bo = new RaRecycleBinBO();
//                    bo.setTransactionId(transId);
//                    bo.setTableName(t.name());
//                    bo.setColumnName(ReflectUtils.getColumnName(method));
//                    bo.setRecordId(recordId);
//                    try {
//                        Object value = method.invoke(obj);
//                        bo.setColumnValue(String.valueOf(value));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    bo.setDateDelete(new Date());
//                    listRecycle.add(bo);
//                }
//            }
//        }
//        return listRecycle;
//    }
    //ThienNG1 addby 13/07/2015
    public String delete(String boName, List<ConditionBean> lstCondition, Session session) {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("delete from ");
            sql.append(boName);
            sql.append(" where 1=1 ");
            int result = 0;
            if (lstCondition != null) {
                buildConditionQuery(sql, lstCondition);
                Query query = session.createQuery(sql.toString());
                fillConditionQuery(query, lstCondition);
                result = query.executeUpdate();
            }
            if (result == 0) {
                return ParamUtils.FAIL;
            } else {
                return ParamUtils.SUCCESS;
            }
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

    //ThienNG1 AddBy 13/07/2015
    public String update(T obj, Session session) {
        try {
            session.update(obj);
            session.flush();
            return ParamUtils.SUCCESS;
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            return he.getMessage();
        }
    }

}
