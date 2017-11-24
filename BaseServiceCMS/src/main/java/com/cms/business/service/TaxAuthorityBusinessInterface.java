/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business.service;

import com.cms.dto.TaxAuthorityDTO;
import com.cms.model.TaxAuthority;
import com.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;
import java.util.Map;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 8/23/2016 11:13 PM
 */
public interface TaxAuthorityBusinessInterface extends BaseFWServiceInterface<TaxAuthorityDTO, TaxAuthority> {

    public List<TaxAuthorityDTO> getListProvineTaxAuthority();

    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineName(String mineName, Map<String, String> map);
    
    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineName(String mineName, String staffCode);
    
    public List<TaxAuthorityDTO> getListTaxAuthorityFromMineNameAndStaffCodeAndProvider(
            String mineName, String staffCode, String provider, String status);
}
