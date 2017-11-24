/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.vfw5.base.dto;

import com.vfw5.base.model.BaseFWModel;

/**
*
* @author kdvt_binhnt22@viettel.com.vn
* @version 1.0
* @since since_text
*/
public interface BaseFWDTOInterface<TModel extends BaseFWModel> extends Comparable<BaseFWDTOInterface> {
    TModel toModel();
    Long getFWModelId();
    String catchName();
    PojoDTO toBean();    
}