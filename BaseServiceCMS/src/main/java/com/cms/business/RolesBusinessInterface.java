/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business;

import com.cms.dto.RolesDTO;
import com.cms.model.Roles;
import com.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:27 AM
 */
public interface RolesBusinessInterface extends BaseFWServiceInterface<RolesDTO, Roles> {

    public List<RolesDTO> getListRolesByStaffId(String staffId);
        
}
