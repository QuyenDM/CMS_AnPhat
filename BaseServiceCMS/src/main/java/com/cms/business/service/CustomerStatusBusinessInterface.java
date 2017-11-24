/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business.service;

import com.cms.dto.CustomerStatusDTO;
import com.cms.dto.StatusQuantityDTO;
import com.cms.model.CustomerStatus;
import com.cms.statistics.dto.StatisticsCategoryListDTO;
import com.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 7/30/2016 5:27 PM
 */
public interface CustomerStatusBusinessInterface extends BaseFWServiceInterface<CustomerStatusDTO, CustomerStatus> {

    public List<StatisticsCategoryListDTO> getStatisticsCategoryListByStaff(String service,
            String staffCode, String categoryId, String beginLastUpdated, String endLastUpdated);
    
    public List<StatusQuantityDTO> getStatusQuantity(String staffCode);
}
