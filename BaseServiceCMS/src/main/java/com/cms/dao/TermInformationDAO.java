/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dao;

import com.cms.dto.TermInformationDTO;
import com.vfw5.base.dao.BaseFWDAOImpl;
import com.cms.model.TermInformation;
import com.cms.utils.Constants;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.utils.DataUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.stereotype.Repository;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/19/2016 12:01 AM
 */
@Repository("termInformationDAO")
public class TermInformationDAO extends BaseFWDAOImpl<TermInformation, Long> {

    public TermInformationDAO() {
        this.model = new TermInformation();
    }

    public TermInformationDAO(Session session) {
        this.session = session;
    }

    public Map<String, List<String>> getProviderAndTaxAuthorityByCategoryList(String categoryId) {
        StringBuilder sqlProvider = new StringBuilder();
        sqlProvider.append("SELECT DISTINCT lower(a.PROVIDER) ");
        sqlProvider.append("  FROM term_information a ");
        sqlProvider.append("  WHERE a.IS_CONTACT_INFO IS NULL AND a.MINE_NAME = ? ");
        SQLQuery queryProvider = getSession().createSQLQuery(sqlProvider.toString());
        StringBuilder sqlTaxAuthority = new StringBuilder();
        return null;
    }

    public ResultDTO insertBatchTermInformations(List<TermInformationDTO> lstTermInformationDTO) {
        ResultDTO resultDTO = new ResultDTO();
        try {
            Session sessionBatch = sessionFactory.openSession();
            Connection connection;
            SessionFactory sessionFactoryBatch = sessionBatch.getSessionFactory();
            connection = sessionFactoryBatch.getSessionFactoryOptions().
                    getServiceRegistry().getService(ConnectionProvider.class).getConnection();

            StringBuilder sql = new StringBuilder();
            List params;

            sql.append(" INSERT INTO TERM_INFORMATION(ID,EMAIL,END_TIME,MINE_NAME,"
                    + "PHONE,PROVIDER,SERVICE,START_TIME,TAX_CODE,SOURCE_DATA, IS_CONTACT_INFO) "
                    + "values(TERM_INFORMATION_SEQ.nextval, ?, to_date(?,'dd/MM/yyyy'), ?, ?, ?, ?, to_date(?,'dd/MM/yyyy') , ?, ?, ?) "
                    + " LOG ERRORS REJECT LIMIT UNLIMITED ");

            //tao statement bang preparestatement
            PreparedStatement stm = connection.prepareStatement(sql.toString());
            int numberNeedToCommit = 0;
            int numberOfSuccess = 0;
            int numberOfFail = 0;
            for (TermInformationDTO t : lstTermInformationDTO) {
                params = getParamsFromTermInfo(t);
                for (int idx = 0; idx < params.size(); idx++) {
                    try {
                        stm.setString(idx + 1, DataUtil.nvl(params.get(idx), "").toString());
                    } catch (Exception e) {
                        System.out.println(idx);
                    }
                }
                stm.addBatch();
                numberNeedToCommit++;
                if (numberNeedToCommit >= 1000) {
                    try {
                        stm.executeBatch();
                        numberOfSuccess = numberOfSuccess + numberNeedToCommit;
                    } catch (Exception ex) {
                        numberOfFail = numberOfFail + numberNeedToCommit;
                    }
                    numberNeedToCommit = 0;
                }
            }

            if (numberNeedToCommit > 0) {
                try {
                    stm.executeBatch();
                    numberOfSuccess += numberNeedToCommit;
                } catch (Exception ex) {
                    numberOfFail += numberNeedToCommit;
                }
            }
            
            stm.close();
            sessionBatch.close();
            numberOfFail = getNumberOfErrorRecord(connection);
            connection.close();
            numberOfSuccess -= numberOfFail;
            resultDTO.setKey(Constants.SUCCESS);
            resultDTO.setMessage(Constants.SUCCESS);
            resultDTO.setQuantityFail(numberOfFail);
            resultDTO.setQuantitySucc(numberOfSuccess);
        } catch (SQLException ex) {
            resultDTO.setKey(Constants.FAIL);
            resultDTO.setMessage(Constants.FAIL);
            Logger.getLogger(TermInformationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultDTO;
    }

    private List<String> getParamsFromTermInfo(TermInformationDTO termInformationDTO) {
        List<String> lstParams = new ArrayList<>();
        lstParams.add(termInformationDTO.getEmail());
        lstParams.add(termInformationDTO.getEndTime());
        lstParams.add(termInformationDTO.getMineName());
        lstParams.add(termInformationDTO.getPhone());
        lstParams.add(termInformationDTO.getProvider());
        lstParams.add(termInformationDTO.getService());
        lstParams.add(termInformationDTO.getStartTime());
        lstParams.add(termInformationDTO.getTaxCode());
        lstParams.add(termInformationDTO.getSourceData());
        lstParams.add(termInformationDTO.getIsContactInfo());
        return lstParams;
    }

    public void deleteErrTermInformationTable() {
        StringBuilder sqlProvider = new StringBuilder();
        sqlProvider.append("DELETE FROM ERR$_TERM_INFORMATION ");
        try {
            SQLQuery queryProvider = getSession().createSQLQuery(sqlProvider.toString());
            queryProvider.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfErrorRecord(Connection con) {
        
        StringBuilder sqlProvider = new StringBuilder();
        sqlProvider.append("select count(*) as numberError from ERR$_TERM_INFORMATION ");
        int numberFailed = -1;
        try {
            Statement stmt3 = con.createStatement();
            ResultSet rs3 = stmt3.executeQuery(sqlProvider.toString());   
            if(rs3.next()){
                numberFailed = rs3.getInt("numberError");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        deleteErrTermInformationTable();
        return numberFailed;
    }
}
