package com.cms.dao;

import com.cms.model.ContractTemplateList;
import com.vfw5.base.dao.BaseFWDAOImpl;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
@Repository("contractTemplateListDAO")
public class ContractTemplateListDAO extends BaseFWDAOImpl<ContractTemplateList, Long> {

    public ContractTemplateListDAO() {
        this.model = new ContractTemplateList();
    }

    public ContractTemplateListDAO(Session session) {
        this.session = session;
    }
}
