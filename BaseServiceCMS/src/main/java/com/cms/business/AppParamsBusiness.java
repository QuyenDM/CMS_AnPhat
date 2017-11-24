/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business;

import com.cms.business.service.AppParamsInterface;
import com.vfw5.base.service.BaseFWServiceImpl;
import com.cms.dto.AppParamsDTO;
import com.cms.model.AppParams;
import com.cms.dao.AppParamsDAO;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
@Service("appParamsBusiness")
@Transactional
public class AppParamsBusiness extends BaseFWServiceImpl<AppParamsDAO, AppParamsDTO, AppParams> implements AppParamsInterface {

    @Autowired
    private AppParamsDAO appParamsDAO;

    public AppParamsBusiness() {
        tModel = new AppParams();
        tDAO = appParamsDAO;
    }

    @Override
    public AppParamsDAO gettDAO() {
        return appParamsDAO;
    }

    public AppParamsBusiness(Session session) {
        this.session = session;
        tModel = new AppParams();
        tDAO = appParamsDAO;
    }

    @Override
    public List<AppParamsDTO> getListProviderFromMineName(String mineName, Map<String,String> map) {
        return gettDAO().getListProviderFromMineName(mineName, map);
    }
    
    @Override
    public List<AppParamsDTO> getProviderFromStaffCodeAndConditions(String mineName, Map<String,String> map) {
        return gettDAO().getProviderFromStaffCodeAndConditions(mineName, map);
    }
    
    @Override
    public List<AppParamsDTO> getProviderFromStaffCode(String staffCode, String mineName) {
        return gettDAO().getProviderFromStaffCode(staffCode, mineName);
    }
    
    @Override
    public List<AppParamsDTO> getStatusFromStaffCode(String staffCode, String mineName) {
        return gettDAO().getStatusFromStaffCode(staffCode, mineName);
    }
}
