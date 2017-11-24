/**
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.business.service;

import com.cms.dto.TermInformationDTO;
import com.cms.model.TermInformation;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 7/19/2016 12:01 AM
 */
public interface TermInformationBusinessInterface extends BaseFWServiceInterface<TermInformationDTO, TermInformation> {

    public ResultDTO insertListTermInformation(List<TermInformationDTO> lstInserts);
}
