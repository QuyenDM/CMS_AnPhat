/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business.service;

import com.cms.dto.ObjectsDTO;
import com.cms.dto.RolesDTO;
import com.cms.model.Objects;
import com.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:18 AM
 */
public interface ObjectsBusinessInterface extends BaseFWServiceInterface<ObjectsDTO, Objects> {
    public List<ObjectsDTO> getListObjectByStaffId(String staffId);
    public List<ObjectsDTO> getListObjectByRole(RolesDTO roles);
}
