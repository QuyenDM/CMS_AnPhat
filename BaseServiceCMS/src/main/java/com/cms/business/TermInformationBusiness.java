/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business;

import com.cms.business.service.TermInformationBusinessInterface;
import com.cms.dao.CategoryListDAO;
import com.vfw5.base.service.BaseFWServiceImpl;
import com.cms.dto.TermInformationDTO;
import com.cms.model.TermInformation;
import com.cms.dao.TermInformationDAO;
import com.cms.model.CategoryList;
import com.cms.utils.Constants;
import com.vfw5.base.dto.ResultDTO;
import java.util.List;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 7/19/2016 12:01 AM
 */
@Service("termInformationBusiness")
@Transactional
public class TermInformationBusiness extends BaseFWServiceImpl<TermInformationDAO, TermInformationDTO, TermInformation> implements TermInformationBusinessInterface {

    @Autowired
    private TermInformationDAO termInformationDAO;
    @Autowired
    public CategoryListDAO categoryListDAO;

    public TermInformationBusiness() {
        tModel = new TermInformation();
        tDAO = termInformationDAO;
    }

    @Override
    public TermInformationDAO gettDAO() {
        return termInformationDAO;
    }

    public TermInformationBusiness(Session session) {
        this.session = session;
        tModel = new TermInformation();
        tDAO = termInformationDAO;
    }

    @Override
    public ResultDTO insertListTermInformation(List<TermInformationDTO> lstInserts) {
        return gettDAO().insertBatchTermInformations(lstInserts);
    }
}
