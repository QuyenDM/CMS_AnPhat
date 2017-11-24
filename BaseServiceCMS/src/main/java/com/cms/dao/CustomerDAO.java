/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dao;

import com.cms.dto.CustomerDTO;
import com.cms.dto.CustomerUserInfoDTO;
import com.vfw5.base.dao.BaseFWDAOImpl;
import com.cms.model.Customer;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.DataUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:55 AM
 */
@Repository("customerDAO")
public class CustomerDAO extends BaseFWDAOImpl<Customer, Long> {

    public CustomerDAO() {
        this.model = new Customer();
    }

    public CustomerDAO(Session session) {
        this.session = session;
    }

    public String saveOrUpdateReturnErrors(List<Customer> lstCus) {
        List<Customer> lstCustomer = new ArrayList<>();
        Customer cs = null;
        Session ss = sessionFactory.openSession();
        Transaction tx = ss.beginTransaction();
        try {
            for (Customer lstCustomer1 : lstCus) {

                ss.save(lstCustomer1);
                cs = lstCustomer1;
                ss.flush();
            }
            tx.commit();
        } catch (HibernateException he) {
            log.error(he.getMessage(), he);
            lstCustomer.add(cs);

            return String.valueOf(lstCustomer.size());
        } finally {
            tx.rollback();
            ss.close();
        }

        return String.valueOf(lstCustomer.size());
    }

    /**
     * QuyenDM getCustomerUserInfoDTO
     *
     * @param userCode
     * @return
     */
    public CustomerUserInfoDTO getCustUserInforDTO(String userCode) {
        //Doi tuong tra ve
        CustomerUserInfoDTO CustUserInforDTO = new CustomerUserInfoDTO();
        StringBuilder sql = new StringBuilder();
        //Cau lenh truy van du lieu        
        sql.append("SELECT a.cust_id custId, a.code custCode,a.name custName, ");
        sql.append("       a.cust_type custType,b.code userCode,b.name userName, ");
        sql.append("       b.cust_user_type custUserType, b.email userEmail, b.tel_number userTelNumber ");
        sql.append("FROM   customer a, customer_user b ");
        sql.append("WHERE b.cust_id = a.cust_id ");
        sql.append("AND a.status <> 6 AND b.status = 1 ");
        sql.append("AND LOWER(b.code) = LOWER(?)");
        //Su dung SQLQuery tao cau truy van
        SQLQuery query = getSession().createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.aliasToBean(CustomerUserInfoDTO.class));
        query.addScalar("custId", new StringType());
        query.addScalar("custCode", new StringType());
        query.addScalar("custName", new StringType());
        query.addScalar("custType", new StringType());
        query.addScalar("userCode", new StringType());
        query.addScalar("userName", new StringType());
        query.addScalar("custUserType", new StringType());
        query.addScalar("userEmail", new StringType());
        query.addScalar("userTelNumber", new StringType());
        //Truyen tham so vao cau query
        query.setParameter(0, userCode);
        List<CustomerUserInfoDTO> listCustUserInforDTO = query.list();
        //Neu danh sach tra ve co du lieu thi gan doi tuong tra ve        
        if (listCustUserInforDTO != null && listCustUserInforDTO.size() > 0) {
            CustUserInforDTO = listCustUserInforDTO.get(0);
        }
        return CustUserInforDTO;
    }

    //Tim kiem khach hang join voi trang thai khach hang va so dien thoai khach hang
    public List<CustomerDTO> searchCustomers(CustomerDTO customer, int maxResult) {
        List<CustomerDTO> lstCustomers;
        List params = new ArrayList();
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("       SELECT DISTINCT a.TAX_CODE taxCode, ");
        sqlQuery.append("               a.NAME name, ");
        sqlQuery.append("               a.REPRESENTATIVE_NAME representativeName, ");
        sqlQuery.append("               a.TEL_NUMBER telNumber, ");
        sqlQuery.append("               a.EMAIL email, ");
        sqlQuery.append("               a.OFFICE_ADDRESS officeAddress, ");
        sqlQuery.append("               a.TAX_AUTHORITY taxAuthority, ");
        //Neu truong so dien thoai duoc dien vao thi them dieu kien        
//        if (!DataUtil.isStringNullOrEmpty(customer.getStaffId())
//                || !DataUtil.isStringNullOrEmpty(customer.getStatus())) {
//            sqlQuery.append("               b.MINE_NAME mineName, ");
//        } else {
            sqlQuery.append("               d.MINE_NAME mineName, ");
//        }
//        sqlQuery.append("               e.notes notes, ");
//        sqlQuery.append("               to_char(e.create_date,'dd/MM/yyyy HH24:Mi:ss') createDate, ");
//        sqlQuery.append("               e.status status ");
        //25/04/2017: Lay thong tin tu bang customer status        
        sqlQuery.append("               d.service service, ");
        sqlQuery.append("               b.last_notes notes, ");
        sqlQuery.append("               to_char(b.last_updated,'dd/MM/yyyy HH24:Mi:ss') createDate, ");
        sqlQuery.append("               b.status status ");
        sqlQuery.append("       FROM CUSTOMER a ");
        sqlQuery.append("            JOIN TERM_INFORMATION d ");
        sqlQuery.append("                ON d.TAX_CODE = a.TAX_CODE ");
        //Dich vu
        if (!DataUtil.isStringNullOrEmpty(customer.getService())) {
            sqlQuery.append("                AND d.Service = :service ");
        }
        //Danh sach khai thac
        if (!DataUtil.isStringNullOrEmpty(customer.getMineName())) {
            sqlQuery.append("           AND d.MINE_NAME = :mineName ");
        }
        //Nha cung cap
        if (!DataUtil.isStringNullOrEmpty(customer.getProvider())) {
            sqlQuery.append("           AND lower(d.PROVIDER) in (:provider) ");
        }

        if (!DataUtil.isStringNullOrEmpty(customer.getStaffId())
                || !DataUtil.isStringNullOrEmpty(customer.getStatus()) 
                || !DataUtil.isStringNullOrEmpty(customer.getCustCareHistoryCreatedDate()))  {
            sqlQuery.append("            JOIN Customer_status b ");
            sqlQuery.append("                ON b.TAX_CODE = a.TAX_CODE ");
        } else {
            sqlQuery.append("           LEFT JOIN Customer_status b ");
            sqlQuery.append("                ON b.TAX_CODE = a.TAX_CODE ");
        }
        sqlQuery.append("                   AND b.TAX_CODE = d.TAX_CODE ");
//        if (!DataUtil.isStringNullOrEmpty(customer.getService())) {
        sqlQuery.append("                   AND b.Service = d.service ");
//        }
//        if (!DataUtil.isStringNullOrEmpty(customer.getMineName())) {
        sqlQuery.append("                   AND b.MINE_NAME = d.mine_name ");
//        }
        //Trang thai cua bang khach hang - dich vu
        if (!DataUtil.isStringNullOrEmpty(customer.getStatus())) {
            sqlQuery.append("               AND b.STATUS = :status ");
        }
        //Them ngay cham soc khach hang
        if (!DataUtil.isStringNullOrEmpty(customer.getCustCareHistoryCreatedDate())) {
            sqlQuery.append("               AND TO_CHAR(b.LAST_UPDATED,'dd/MM/yyyy') = :createDate ");
        }
//        //Neu truong trang thai duoc dien vao thi them dieu kien        
//        if (!DataUtil.isStringNullOrEmpty(customer.getStatus())
//                || !DataUtil.isStringNullOrEmpty(customer.getStaffId())
//                || !DataUtil.isStringNullOrEmpty(customer.getMineName())) {
//        }
        //Neu truong email duoc dien vao thi them dieu kien        
        if (!DataUtil.isStringNullOrEmpty(customer.getEmail())
                || !DataUtil.isStringNullOrEmpty(customer.getTelNumber())) {
            sqlQuery.append("            LEFT JOIN CUSTOMER_CONTACT c ");
            sqlQuery.append("                ON c.TAX_CODE = a.TAX_CODE ");
        }

        sqlQuery.append("       WHERE 1=1 ");        
        //MST
        if (!DataUtil.isStringNullOrEmpty(customer.getTaxCode())) {
            sqlQuery.append("           AND lower(a.TAX_CODE) LIKE lower(:taxCode) ");
        }
        //Staff id
        if (!DataUtil.isStringNullOrEmpty(customer.getStaffId())) {
            sqlQuery.append("           AND b.STAFF_ID = :staffId ");
        }
        //Ten cong ty
        if (!DataUtil.isStringNullOrEmpty(customer.getName())) {
            sqlQuery.append("           AND lower(a.NAME) LIKE lower(:custName) ");
        }
        //Dia chi dang ky kinh doanh
        if (!DataUtil.isStringNullOrEmpty(customer.getOfficeAddress())) {
            sqlQuery.append("           AND lower(a.OFFICE_ADDRESS) LIKE lower(:officeAdd) ");
        }

        if (!DataUtil.isStringNullOrEmpty(customer.getEmail())) {
            sqlQuery.append("           AND ( (lower(a.EMAIL) like :email1)  OR  (lower(c.EMAIL) like :email2) OR (lower(d.EMAIL) like :email3))");
        }
        if (!DataUtil.isStringNullOrEmpty(customer.getTelNumber())) {
            sqlQuery.append("           AND ( (lower(a.TEL_NUMBER) like :tel1)  OR  (lower(c.TEL_NUMBER) LIKE :tel2 ) OR (lower(d.PHONE) LIKE :tel3 ))");
        }
        //Neu ma tinh duoc dien tim kiem theo ma tinh
        if (!DataUtil.isStringNullOrEmpty(customer.getTaxAuthority())) {
            sqlQuery.append("   AND a.TAX_AUTHORITY in (:taxAuthority) ");
        }        
        sqlQuery.append("       ORDER BY To_date(createDate,'dd/MM/yyyy HH24:Mi:ss') desc, taxCode");
        try {
            SQLQuery query = getSession().createSQLQuery(sqlQuery.toString());
            //Thuc hien chuyen du lieu lay ve thanh thanh doi tuong            
            query.setResultTransformer(Transformers.aliasToBean(CustomerDTO.class));
            query.addScalar("taxCode", new StringType());
            query.addScalar("name", new StringType());
            query.addScalar("representativeName", new StringType());
            query.addScalar("telNumber", new StringType());
            query.addScalar("email", new StringType());
            query.addScalar("officeAddress", new StringType());
            query.addScalar("taxAuthority", new StringType());
            query.addScalar("mineName", new StringType());
            query.addScalar("notes", new StringType());
            query.addScalar("createDate", new StringType());
            query.addScalar("status", new StringType());
            query.addScalar("service", new StringType());            
//            query.addScalar("provider", new StringType());

            //MST
            if (!DataUtil.isStringNullOrEmpty(customer.getTaxCode())) {
                query.setParameter("taxCode", "%" + customer.getTaxCode() + "%");
            }
            //Staff id
            if (!DataUtil.isStringNullOrEmpty(customer.getStaffId())) {
                query.setParameter("staffId", customer.getStaffId());
            }
            //Ten cong ty
            if (!DataUtil.isStringNullOrEmpty(customer.getName())) {
                query.setParameter("custName", "%" + customer.getName() + "%");
            }
            //Dia chi dang ky kinh doanh
            if (!DataUtil.isStringNullOrEmpty(customer.getOfficeAddress())) {
                query.setParameter("officeAdd", "%" + customer.getOfficeAddress() + "%");
            }
            //Danh sach khai thac
            if (!DataUtil.isStringNullOrEmpty(customer.getMineName())) {
                query.setParameter("mineName", customer.getMineName());
            }
            //Dich vu
            if (!DataUtil.isStringNullOrEmpty(customer.getService())) {
                query.setParameter("service", customer.getService());
            }
            //Nha cung cap
            if (!DataUtil.isStringNullOrEmpty(customer.getProvider())) {
                query.setParameterList("provider", DataUtil.parseInputListString(customer.getProvider().toLowerCase()));
            }
            //Trang thai cua bang khach hang - dich vu
            if (!DataUtil.isStringNullOrEmpty(customer.getStatus())) {
                query.setParameter("status", customer.getStatus());
            }
            if (!DataUtil.isStringNullOrEmpty(customer.getEmail())) {
                query.setParameter("email1", "%" + customer.getEmail().toLowerCase() + "%");
                query.setParameter("email2", "%" + customer.getEmail().toLowerCase() + "%");
                query.setParameter("email3", "%" + customer.getEmail().toLowerCase() + "%");
            }
            //So dien thoai cua bang lich su giao dich
            if (!DataUtil.isStringNullOrEmpty(customer.getTelNumber())) {
                query.setParameter("tel1", "%" + customer.getTelNumber() + "%");
                query.setParameter("tel2", "%" + customer.getTelNumber() + "%");
                query.setParameter("tel3", "%" + customer.getTelNumber() + "%");
            }
            //Neu ma tinh duoc dien tim kiem theo ma tinh
            if (!DataUtil.isStringNullOrEmpty(customer.getTaxAuthority())) {
                query.setParameterList("taxAuthority", DataUtil.parseInputListString(customer.getTaxAuthority()));
            }

            //Them ngay cham soc khach hang
            if (!DataUtil.isStringNullOrEmpty(customer.getCustCareHistoryCreatedDate())) {
                query.setParameter("createDate", customer.getCustCareHistoryCreatedDate());
            }

            if (maxResult != Integer.MAX_VALUE) {
                query.setMaxResults(maxResult);
            }
            //Day du lieu ra danh sach doi tuong
            lstCustomers = query.list();
        } catch (Exception e) {
            e.printStackTrace();
            lstCustomers = null;
        }
        return lstCustomers;
    }

    public List<CustomerDTO> getListCustomerWithTermInfo(List<ConditionBean> lstConditions) {
        String fromStartTime = null;
        String toStartTime = null;
        String fromEndTime = null;
        String toEndTime = null;
        String fromDateRegister = null;
        String toDateRegister = null;
        String provider = null;
        String service = null;
        String mineName = null;
        String taxAuthority = null;
        String maxSearch = null;
        SQLQuery query;
        for (ConditionBean c : lstConditions) {
            if ("service".equalsIgnoreCase(c.getField())) {
                service = c.getValue();
            }
            if ("provider".equalsIgnoreCase(c.getField())) {
                provider = c.getValue();
            }
            if ("taxAuthority".equalsIgnoreCase(c.getField())) {
                taxAuthority = c.getValue();
            }
            if ("mineName".equals(c.getField())) {
                mineName = c.getValue();
            }
            if ("maxSearch".equals(c.getField())) {
                maxSearch = c.getValue();
            }
            if ("startTime".equals(c.getField())) {
                if (String.valueOf(ConditionBean.Operator.NAME_GREATER_EQUAL).equalsIgnoreCase(c.getOperator())) {
                    fromStartTime = c.getValue();
                }
                if (String.valueOf(ConditionBean.Operator.NAME_LESS_EQUAL).equalsIgnoreCase(c.getOperator())) {
                    toStartTime = c.getValue();
                }
            }
            if ("endTime".equals(c.getField())) {
                if (String.valueOf(ConditionBean.Operator.NAME_GREATER_EQUAL).equalsIgnoreCase(c.getOperator())) {
                    fromEndTime = c.getValue();
                }
                if (String.valueOf(ConditionBean.Operator.NAME_LESS_EQUAL).equalsIgnoreCase(c.getOperator())) {
                    toEndTime = c.getValue();
                }
            }
            if ("dateRegister".equals(c.getField())) {
                if (String.valueOf(ConditionBean.Operator.NAME_GREATER_EQUAL).equalsIgnoreCase(c.getOperator())) {
                    fromDateRegister = c.getValue();
                }
                if (String.valueOf(ConditionBean.Operator.NAME_LESS_EQUAL).equalsIgnoreCase(c.getOperator())) {
                    toDateRegister = c.getValue();
                }
            }
        }
        List<CustomerDTO> lstCustomers = null;
        List params = new ArrayList();
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("       SELECT DISTINCT a.TAX_CODE taxCode, ");
        sqlQuery.append("               a.NAME name, ");
        sqlQuery.append("               a.REPRESENTATIVE_NAME representativeName, ");
        sqlQuery.append("               a.TEL_NUMBER telNumber, ");
        sqlQuery.append("               a.EMAIL email, ");
        sqlQuery.append("               a.STATUS status, ");
        sqlQuery.append("               a.OFFICE_ADDRESS officeAddress, ");
        sqlQuery.append("               d.MINE_NAME mineName, ");
        sqlQuery.append("               d.SERVICE service, ");
        sqlQuery.append("               to_char(d.END_TIME,'dd/MM/yyyy') endTime, ");
        sqlQuery.append("               to_char(d.Start_time,'dd/MM/yyyy') startTime, ");
        sqlQuery.append("               a.TAX_AUTHORITY taxAuthority ");
        sqlQuery.append("       FROM CUSTOMER a ");
//        if (!DataUtil.isStringNullOrEmpty(provider)
//                || !DataUtil.isStringNullOrEmpty(fromStartTime)
//                || !DataUtil.isStringNullOrEmpty(toStartTime)
//                || !DataUtil.isStringNullOrEmpty(fromEndTime)
//                || !DataUtil.isStringNullOrEmpty(toEndTime)
//                || !DataUtil.isStringNullOrEmpty(mineName)) {
//        }
        sqlQuery.append("       JOIN TERM_INFORMATION d ");
        sqlQuery.append("            ON d.TAX_CODE = a.TAX_CODE ");
        sqlQuery.append("       LEFT JOIN CUSTOMER_STATUS b ");
        sqlQuery.append("            ON b.TAX_CODE = a.TAX_CODE ");
        sqlQuery.append("       WHERE 1=1 ");
        sqlQuery.append("       AND b.TAX_CODE IS NULL ");
        if (!DataUtil.isStringNullOrEmpty(taxAuthority)) {
            sqlQuery.append("   AND a.TAX_AUTHORITY in (:tax) ");
        }
        if (!DataUtil.isStringNullOrEmpty(service)) {
            sqlQuery.append("   AND d.SERVICE = :service ");
//            params.add(service);
        }
        if (!DataUtil.isStringNullOrEmpty(mineName)) {
            sqlQuery.append("           AND d.MINE_NAME = :mineName ");
//            params.add(mineName);
        }
        //Nha cung cap
        if (!DataUtil.isStringNullOrEmpty(provider)) {
            sqlQuery.append("           AND lower(d.PROVIDER) in (:provider) ");
        }
        if (!DataUtil.isStringNullOrEmpty(fromStartTime)) {
            sqlQuery.append("   AND d.START_TIME >= TO_DATE(:fromStartTime,'dd/MM/yyyy') - 1 ");
//            params.add(fromStartTime);
        }
        if (!DataUtil.isStringNullOrEmpty(toStartTime)) {
            sqlQuery.append("   AND d.START_TIME <= TO_DATE(:toStartTime,'dd/MM/yyyy') + 1 ");
//            params.add(toStartTime);
        }
        if (!DataUtil.isStringNullOrEmpty(fromEndTime)) {
            sqlQuery.append("   AND d.END_TIME >= TO_DATE(:fromEndTime,'dd/MM/yyyy') - 1 ");
//            params.add(fromEndTime);
        }
        if (!DataUtil.isStringNullOrEmpty(toEndTime)) {
            sqlQuery.append("   AND d.END_TIME <= TO_DATE(:toEndTime,'dd/MM/yyyy') + 1");
//            params.add(toEndTime);
        }
        if (!DataUtil.isStringNullOrEmpty(fromDateRegister)) {
            sqlQuery.append("   AND d.DATE_REGISTER >= TO_DATE(:fromDateRegister,'dd/MM/yyyy') - 1 ");
//            params.add(fromDateRegister);
        }
        if (!DataUtil.isStringNullOrEmpty(toDateRegister)) {
            sqlQuery.append("   AND d.DATE_REGISTER <= TO_DATE(:toDateRegister,'dd/MM/yyyy') + 1 ");
//            params.add(toDateRegister);
        }
        sqlQuery.append("           ORDER BY endTime desc, startTime desc ");

        try {
            query = getSession().createSQLQuery(sqlQuery.toString());
            //Thuc hien chuyen du lieu lay ve thanh thanh doi tuong            
            query.setResultTransformer(Transformers.aliasToBean(CustomerDTO.class));
            query.addScalar("taxCode", new StringType());
            query.addScalar("name", new StringType());
            query.addScalar("representativeName", new StringType());
            query.addScalar("telNumber", new StringType());
            query.addScalar("email", new StringType());
            query.addScalar("status", new StringType());
            query.addScalar("officeAddress", new StringType());
            query.addScalar("mineName", new StringType());
            query.addScalar("service", new StringType());
            query.addScalar("startTime", new StringType());
            query.addScalar("endTime", new StringType());
            query.addScalar("taxAuthority", new StringType());

//            for (int i = 0; i < params.size(); i++) {
//                query.setParameter(i, params.get(i));
//            }
            //Danh sach khai thac
            if (!DataUtil.isStringNullOrEmpty(mineName)) {
                query.setParameter("mineName", Long.parseLong(mineName));
            }
            //Nha cung cap
            if (!DataUtil.isStringNullOrEmpty(provider)) {
                query.setParameterList("provider", DataUtil.parseInputListString(provider.toLowerCase()));
            }
            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(taxAuthority)) {
                query.setParameterList("tax", DataUtil.parseInputListString(taxAuthority));
            }
            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(service)) {
                query.setParameter("service", Long.parseLong(service));
            }

            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(fromStartTime)) {
                query.setParameter("fromStartTime", fromStartTime);
            }
            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(toStartTime)) {
                query.setParameter("toStartTime", toStartTime);
            }

            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(fromEndTime)) {
                query.setParameter("fromEndTime", fromEndTime);
            }
            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(toEndTime)) {
                query.setParameter("toEndTime", toEndTime);
            }
            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(fromDateRegister)) {
                query.setParameter("fromDateRegister", fromDateRegister);
            }
            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(toDateRegister)) {
                query.setParameter("toDateRegister", toDateRegister);
            }
            //Day du lieu ra danh sach doi tuong
            if (!DataUtil.isStringNullOrEmpty(maxSearch)) {
                if (DataUtil.isInteger(maxSearch)) {
                    query.setMaxResults(Integer.parseInt(maxSearch));
                }
            }
            lstCustomers = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCustomers;
    }

    public List<CustomerDTO> getListCustomerFromTermInfoWithoutTaxCodes(List<ConditionBean> lstConditions, List<String> taxCodesExecuted) {
        String fromStartTime = null;
        String toStartTime = null;
        String fromEndTime = null;
        String toEndTime = null;
        String fromDateRegister = null;
        String toDateRegister = null;
        String provider = null;
        String service = null;
        String mineName = null;
        String taxAuthority = null;
        String maxSearch = null;
        SQLQuery query;
        for (ConditionBean c : lstConditions) {
            if ("service".equalsIgnoreCase(c.getField())) {
                service = c.getValue();
            }
            if ("provider".equalsIgnoreCase(c.getField())) {
                provider = c.getValue();
            }
            if ("taxAuthority".equalsIgnoreCase(c.getField())) {
                taxAuthority = c.getValue();
            }
            if ("mineName".equals(c.getField())) {
                mineName = c.getValue();
            }
            if ("maxSearch".equals(c.getField())) {
                maxSearch = c.getValue();
            }
            if ("startTime".equals(c.getField())) {
                if (String.valueOf(ConditionBean.Operator.NAME_GREATER_EQUAL).equalsIgnoreCase(c.getOperator())) {
                    fromStartTime = c.getValue();
                }
                if (String.valueOf(ConditionBean.Operator.NAME_LESS_EQUAL).equalsIgnoreCase(c.getOperator())) {
                    toStartTime = c.getValue();
                }
            }
            if ("endTime".equals(c.getField())) {
                if (String.valueOf(ConditionBean.Operator.NAME_GREATER_EQUAL).equalsIgnoreCase(c.getOperator())) {
                    fromEndTime = c.getValue();
                }
                if (String.valueOf(ConditionBean.Operator.NAME_LESS_EQUAL).equalsIgnoreCase(c.getOperator())) {
                    toEndTime = c.getValue();
                }
            }
            if ("dateRegister".equals(c.getField())) {
                if (String.valueOf(ConditionBean.Operator.NAME_GREATER_EQUAL).equalsIgnoreCase(c.getOperator())) {
                    fromDateRegister = c.getValue();
                }
                if (String.valueOf(ConditionBean.Operator.NAME_LESS_EQUAL).equalsIgnoreCase(c.getOperator())) {
                    toDateRegister = c.getValue();
                }
            }
        }
        List<CustomerDTO> lstCustomers = null;
        StringBuilder sqlQuery = new StringBuilder();

        sqlQuery.append("       SELECT e.taxCode, "
                + "  e.name, "
                + "  e.representativeName, "
                + "  e.telNumber,"
                + "  e.email, "
                + "  e.status, "
                + "  e.officeAddress, "
                + "  e.mineName, "
                + "  e.service, "
                + "  to_char(max(e.endTime),'dd/MM/yyyy')  endTime, "
                + "  to_char(min(e.startTime),'dd/MM/yyyy') startTime, "
                + "  e.taxAuthority "
                + "FROM "
                + "  ( ");
        sqlQuery.append("       SELECT  a.TAX_CODE taxCode, ");
        sqlQuery.append("               a.NAME name, ");
        sqlQuery.append("               a.REPRESENTATIVE_NAME representativeName, ");
        sqlQuery.append("               a.TEL_NUMBER telNumber, ");
        sqlQuery.append("               a.EMAIL email, ");
        sqlQuery.append("               a.STATUS status, ");
        sqlQuery.append("               a.OFFICE_ADDRESS officeAddress, ");
        sqlQuery.append("               d.MINE_NAME mineName, ");
        sqlQuery.append("               d.SERVICE service, ");
        sqlQuery.append("               d.END_TIME endTime, ");
        sqlQuery.append("               d.START_TIME startTime, ");
        sqlQuery.append("               a.TAX_AUTHORITY taxAuthority ");
        sqlQuery.append("       FROM CUSTOMER a ");
        sqlQuery.append("        JOIN TERM_INFORMATION d ");
        sqlQuery.append("            ON d.TAX_CODE = a.TAX_CODE ");
        sqlQuery.append("       WHERE 1=1 ");
        sqlQuery.append("       AND d.IS_CONTACT_INFO is null ");
        sqlQuery.append("       AND NOT EXISTS (select c.tax_code, c.MINE_NAME ");
        sqlQuery.append("                              from customer_status c ");
        sqlQuery.append("                              where c.tax_code = d.tax_code ");
        sqlQuery.append("                                AND c.mine_name = d.mine_name) ");
        if (!DataUtil.isStringNullOrEmpty(taxAuthority)) {
            sqlQuery.append("   AND a.TAX_AUTHORITY in (:tax) ");
        }
        if (!DataUtil.isStringNullOrEmpty(service)) {
            sqlQuery.append("   AND d.SERVICE = :service ");
        }
        if (!DataUtil.isStringNullOrEmpty(mineName)) {
            sqlQuery.append("           AND d.MINE_NAME = :mineName ");
        }
        //Nha cung cap
        if (!DataUtil.isStringNullOrEmpty(provider)) {
            sqlQuery.append("           AND lower(d.PROVIDER) in (:provider) ");
        }
        if (!DataUtil.isStringNullOrEmpty(fromStartTime)) {
            sqlQuery.append("   AND d.START_TIME >= TO_DATE(:fromStartTime,'dd/MM/yyyy') - 1 ");
        }
        if (!DataUtil.isStringNullOrEmpty(toStartTime)) {
            sqlQuery.append("   AND d.START_TIME <= TO_DATE(:toStartTime,'dd/MM/yyyy') + 1 ");
        }
        if (!DataUtil.isStringNullOrEmpty(fromEndTime)) {
            sqlQuery.append("   AND d.END_TIME >= TO_DATE(:fromEndTime,'dd/MM/yyyy') - 1 ");
        }
        if (!DataUtil.isStringNullOrEmpty(toEndTime)) {
            sqlQuery.append("   AND d.END_TIME <= TO_DATE(:toEndTime,'dd/MM/yyyy') + 1");
        }
        if (!DataUtil.isStringNullOrEmpty(fromDateRegister)) {
            sqlQuery.append("   AND d.DATE_REGISTER >= TO_DATE(:fromDateRegister,'dd/MM/yyyy') - 1 ");
        }
        if (!DataUtil.isStringNullOrEmpty(toDateRegister)) {
            sqlQuery.append("   AND d.DATE_REGISTER <= TO_DATE(:toDateRegister,'dd/MM/yyyy') + 1 ");
        }
        //Them viec bo nhung tax code da phan bo
        if (!DataUtil.isListNullOrEmpty(taxCodesExecuted)) {
            for (int i = 0; i < taxCodesExecuted.size(); i++) {
                sqlQuery.append(" AND  a.tax_code not in (:taxCode").append(i).append(" ) ");
            }
        }
//        sqlQuery.append("           ORDER BY endTime desc, startTime desc ");
        sqlQuery.append(" ) e   GROUP BY taxCode, name,representativeName,telNumber,email,status,officeAddress,mineName,service,taxAuthority\n"
                + "    ORDER BY taxCode, name,representativeName,telNumber,email,status,officeAddress,mineName,service,taxAuthority");

        try {
            query = getSession().createSQLQuery(sqlQuery.toString());
            //Thuc hien chuyen du lieu lay ve thanh thanh doi tuong            
            query.setResultTransformer(Transformers.aliasToBean(CustomerDTO.class));
            query.addScalar("taxCode", new StringType());
            query.addScalar("name", new StringType());
            query.addScalar("representativeName", new StringType());
            query.addScalar("telNumber", new StringType());
            query.addScalar("email", new StringType());
            query.addScalar("status", new StringType());
            query.addScalar("officeAddress", new StringType());
            query.addScalar("mineName", new StringType());
            query.addScalar("service", new StringType());
            query.addScalar("startTime", new StringType());
            query.addScalar("endTime", new StringType());
            query.addScalar("taxAuthority", new StringType());

            //Danh sach khai thac
            if (!DataUtil.isStringNullOrEmpty(mineName)) {
                query.setParameter("mineName", Long.parseLong(mineName));
            }
            //Nha cung cap
            if (!DataUtil.isStringNullOrEmpty(provider)) {
                query.setParameterList("provider", DataUtil.parseInputListString(provider.toLowerCase()));
            }
            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(taxAuthority)) {
                query.setParameterList("tax", DataUtil.parseInputListString(taxAuthority));
            }
            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(service)) {
                query.setParameter("service", Long.parseLong(service));
            }

            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(fromStartTime)) {
                query.setParameter("fromStartTime", fromStartTime);
            }
            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(toStartTime)) {
                query.setParameter("toStartTime", toStartTime);
            }

            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(fromEndTime)) {
                query.setParameter("fromEndTime", fromEndTime);
            }
            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(toEndTime)) {
                query.setParameter("toEndTime", toEndTime);
            }
            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(fromDateRegister)) {
                query.setParameter("fromDateRegister", fromDateRegister);
            }
            //Truyen cac tham so truyen vao de thuc hien tim kiem
            if (!DataUtil.isStringNullOrEmpty(toDateRegister)) {
                query.setParameter("toDateRegister", toDateRegister);
            }
            //Them viec bo nhung tax code da phan bo
            if (!DataUtil.isListNullOrEmpty(taxCodesExecuted)) {
                for (int i = 0; i < taxCodesExecuted.size(); i++) {
                    query.setParameterList("taxCode" + i, DataUtil.parseInputListString(taxCodesExecuted.get(i)));
                }
            }
            //Day du lieu ra danh sach doi tuong
            if (!DataUtil.isStringNullOrEmpty(maxSearch)) {
                if (DataUtil.isInteger(maxSearch)) {
                    query.setMaxResults(Integer.parseInt(maxSearch));
                }
            }
            lstCustomers = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCustomers;
    }

    public ResultDTO insertOrUpdateListCustomer(List<CustomerDTO> lstCustomers) {

        return null;
    }

    /**
     * Get list customer existed
     *
     * @param lstTaxCodes
     * @return
     */
    public List<CustomerDTO> getCustomerExisted(List<String> lstTaxCodes) {
        List<CustomerDTO> lstCustomers = null;
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("       SELECT DISTINCT a.TAX_CODE taxCode, a.cust_id custId,  a.tax_authority taxAuthority   ");
        sqlQuery.append("       FROM CUSTOMER a WHERE 1=1 " );
        if (!DataUtil.isListNullOrEmpty(lstTaxCodes)) {
            sqlQuery.append("       AND   a.TAX_CODE IN ");
            sqlQuery.append("( :idx").append(String.valueOf(0)).append(" )");
            if (lstTaxCodes.size() > 1) {
                for (int index = 1; index < lstTaxCodes.size(); index++) {
                    sqlQuery.append("       OR   a.TAX_CODE IN ");
                    sqlQuery.append("( :idx").append(String.valueOf(index)).append(" )");
                }
            }
            SQLQuery query;
            query = getSession().createSQLQuery(sqlQuery.toString());
            try {
                //Thuc hien chuyen du lieu lay ve thanh thanh doi tuong            
                query.setResultTransformer(Transformers.aliasToBean(CustomerDTO.class));
                query.addScalar("taxCode", new StringType());
                query.addScalar("custId", new StringType());
                query.addScalar("taxAuthority", new StringType());
                for (int index = 0; index < lstTaxCodes.size(); index++) {
                    query.setParameterList("idx" + String.valueOf(index), DataUtil.parseInputListString(lstTaxCodes.get(index)));
                }
                lstCustomers = query.list();

            } catch (Exception e) {
                e.printStackTrace();
                lstCustomers = null;
            }
        }
        return lstCustomers;
    }

    public List<CustomerDTO> getCustomerFromIHTKK(List<String> lstTaxCodes) {
        List<CustomerDTO> lstCustomers = null;
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("       SELECT DISTINCT a.tin taxCode, a.norm_name name,  a.tran_prov taxAuthority   ");
        sqlQuery.append("       FROM IHTKK_MV_PAYER a WHERE 1=1 " );
        if (!DataUtil.isListNullOrEmpty(lstTaxCodes)) {
            sqlQuery.append("       AND   a.tin IN ");
            sqlQuery.append("( :idx").append(String.valueOf(0)).append(" )");
            if (lstTaxCodes.size() > 1) {
                for (int index = 1; index < lstTaxCodes.size(); index++) {
                    sqlQuery.append("       OR   a.tin IN ");
                    sqlQuery.append("( :idx").append(String.valueOf(index)).append(" )");
                    
                }
            }
            SQLQuery query;
            query = getSession().createSQLQuery(sqlQuery.toString());
            try {
                //Thuc hien chuyen du lieu lay ve thanh thanh doi tuong            
                query.setResultTransformer(Transformers.aliasToBean(CustomerDTO.class));
                query.addScalar("taxCode", new StringType());
                query.addScalar("name", new StringType());
                query.addScalar("taxAuthority", new StringType());
                for (int index = 0; index < lstTaxCodes.size(); index++) {
                    query.setParameterList("idx" + String.valueOf(index), DataUtil.parseInputListString(lstTaxCodes.get(index)));
                }
                lstCustomers = query.list();

            } catch (Exception e) {
                e.printStackTrace();
                lstCustomers = null;
            }
        }
        return lstCustomers;
    }
    
    public List<CustomerDTO> getListCustomerOfMineName(String mineName) {
        List<CustomerDTO> lstCustomerOfMineName = null;
        if (DataUtil.isStringNullOrEmpty(mineName)) {
            return null;
        } else {
            SQLQuery query;
            StringBuilder sqlQuery = new StringBuilder();
            sqlQuery.append("       SELECT DISTINCT a.TAX_CODE taxCode, ");
            sqlQuery.append("               a.NAME name, ");
            sqlQuery.append("               a.REPRESENTATIVE_NAME representativeName, ");
            sqlQuery.append("               a.TEL_NUMBER telNumber, ");
            sqlQuery.append("               a.EMAIL email, ");
            sqlQuery.append("               a.STATUS status, ");
            sqlQuery.append("               a.OFFICE_ADDRESS officeAddress, ");
            sqlQuery.append("               a.TAX_AUTHORITY taxAuthority ");
            sqlQuery.append("       FROM CUSTOMER a ");
            sqlQuery.append("       JOIN TERM_INFORMATION d ");
            sqlQuery.append("            ON d.TAX_CODE = a.TAX_CODE ");
            sqlQuery.append("       WHERE 1=1 ");
            sqlQuery.append("          AND d.mine_name = :mineName ");

            try {
                query = getSession().createSQLQuery(sqlQuery.toString());
                //Thuc hien chuyen du lieu lay ve thanh thanh doi tuong            
                query.setResultTransformer(Transformers.aliasToBean(CustomerDTO.class));
                query.addScalar("taxCode", new StringType());
                query.addScalar("name", new StringType());
                query.addScalar("representativeName", new StringType());
                query.addScalar("telNumber", new StringType());
                query.addScalar("email", new StringType());
                query.addScalar("status", new StringType());
                query.addScalar("officeAddress", new StringType());
                query.addScalar("taxAuthority", new StringType());

                //Danh sach khai thac                
                query.setParameter("mineName", Long.parseLong(mineName));
                lstCustomerOfMineName = query.list();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return lstCustomerOfMineName;
        }
    }

    public List<CustomerDTO> getListDevidedCustomerOfMineName(String mineName) {
        List<CustomerDTO> lstCustomerOfMineName = null;
        if (DataUtil.isStringNullOrEmpty(mineName)) {
            return null;
        } else {
            SQLQuery query;
            StringBuilder sqlQuery = new StringBuilder();
            sqlQuery.append("       SELECT DISTINCT a.TAX_CODE taxCode, ");
            sqlQuery.append("               a.NAME name, ");
            sqlQuery.append("               a.REPRESENTATIVE_NAME representativeName, ");
            sqlQuery.append("               a.TEL_NUMBER telNumber, ");
            sqlQuery.append("               a.EMAIL email, ");
            sqlQuery.append("               a.STATUS status, ");
            sqlQuery.append("               a.OFFICE_ADDRESS officeAddress, ");
            sqlQuery.append("               a.TAX_AUTHORITY taxAuthority, ");
            sqlQuery.append("               d.STATUS customerStatus ");
            sqlQuery.append("       FROM CUSTOMER a ");
            sqlQuery.append("       JOIN CUSTOMER_STATUS d ");
            sqlQuery.append("            ON d.TAX_CODE = a.TAX_CODE ");
            sqlQuery.append("       WHERE 1=1 ");
            sqlQuery.append("          AND d.mine_name = :mineName ");

            try {
                query = getSession().createSQLQuery(sqlQuery.toString());
                //Thuc hien chuyen du lieu lay ve thanh thanh doi tuong            
                query.setResultTransformer(Transformers.aliasToBean(CustomerDTO.class));
                query.addScalar("taxCode", new StringType());
                query.addScalar("name", new StringType());
                query.addScalar("representativeName", new StringType());
                query.addScalar("telNumber", new StringType());
                query.addScalar("email", new StringType());
                query.addScalar("status", new StringType());
                query.addScalar("officeAddress", new StringType());
                query.addScalar("taxAuthority", new StringType());
                query.addScalar("customerStatus", new StringType());

                //Danh sach khai thac                
                query.setParameter("mineName", Long.parseLong(mineName));
                lstCustomerOfMineName = query.list();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return lstCustomerOfMineName;
        }
    }
}
