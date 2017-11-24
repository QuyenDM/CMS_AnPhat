package com.cms.business;

import com.cms.business.service.ContractTemplateListBusinessInterface;
import com.cms.dto.ContractTemplateListDTO;
import com.cms.model.ContractTemplateList;
import com.cms.dao.ContractTemplateListDAO;
import com.vfw5.base.service.BaseFWServiceImpl;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 03/11/2016 22:17:52
 */
@Service("contractTemplateListBusiness")
@Transactional
public class ContractTemplateListBusiness extends BaseFWServiceImpl<ContractTemplateListDAO, ContractTemplateListDTO, ContractTemplateList> implements ContractTemplateListBusinessInterface {

    @Autowired
    private ContractTemplateListDAO contractTemplateListDAO;

    public ContractTemplateListBusiness() {
        tModel = new ContractTemplateList();
        tDAO = contractTemplateListDAO;
    }

    @Override
    public ContractTemplateListDAO gettDAO() {
        return contractTemplateListDAO;
    }

    public ContractTemplateListBusiness(Session session) {
        this.session = session;
        tModel = new ContractTemplateList();
        tDAO = contractTemplateListDAO;
    }
}
