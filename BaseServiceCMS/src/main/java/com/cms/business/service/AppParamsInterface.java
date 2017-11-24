/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.business.service;

import com.cms.dto.AppParamsDTO;
import com.cms.model.AppParams;
import com.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TruongBx3
 */
public interface AppParamsInterface extends BaseFWServiceInterface<AppParamsDTO, AppParams> {

    public List<AppParamsDTO> getListProviderFromMineName(String mineName, Map<String, String> map);
    
    public List<AppParamsDTO> getProviderFromStaffCodeAndConditions(String mineName, Map<String, String> map);

    public List<AppParamsDTO> getProviderFromStaffCode(String staffCode, String mineName);
    
    public List<AppParamsDTO> getStatusFromStaffCode(String staffCode, String mineName);

}
