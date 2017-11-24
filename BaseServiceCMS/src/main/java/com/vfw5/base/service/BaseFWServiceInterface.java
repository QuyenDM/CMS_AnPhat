/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.vfw5.base.service;

import com.vfw5.base.model.BaseFWModel;
import com.vfw5.base.dto.BaseFWDTO;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.pojo.ConditionBean;
import java.util.HashMap;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author kdvt_binhnt22@viettel.com.vn
 * @version 1.0
 * @since May 2012
 */
public interface BaseFWServiceInterface<TDTO extends BaseFWDTO, TModel extends BaseFWModel> {

    // work with WF3.3+
    public List<ConditionBean> prepareCondition(TDTO tDTO);

    public List<TDTO> getAll();

    public List<TDTO> search(TDTO tDTO,
            int start, int maxResult, String sortType, String sortField);

    public String create(TDTO tDTO);

    public List<TDTO> searchByConditionBean(List<ConditionBean> lstConditionBean,
            int start, int maxResult, String sortType, String sortField);
//
//    public ResultDTO updateByConditionBean(List<ConditionBean> lstConditionBean);

    public ResultDTO createObject(TDTO tDTO);

    public ResultDTO createObject(TDTO tDTO, Session session);

    public String insertList(List<TDTO> tForm);

    public String insertList(List<TDTO> tForm, Session session);

    public List<String> getListSequense(String seq, int size);

    public String update(TDTO tDTO);

    //thienNG1 addby 13/07/2015
    public String update(TDTO tDTO, Session session);

    public String delete(Long id);

    //thienNG1 addby 13/07/2015
    public String delete(Long id, Session session);

    public String delete(List<TDTO> tDTOOnGrid);

    public TModel findById(Long id);

    public boolean isDuplicate(String name, Long id);

    public long count(TDTO tDTO);

    public List<TDTO> convertListModeltoDTO(List<TModel> listModel);

    public List<TModel> convertListDTOtoModel(List<TDTO> listDTO);

    public String getSysDate(String pattern);

    public String getSysDateExtend(String pattern, int addDay);

    public boolean isDuplicate(Long id, HashMap<String, String> hashmap);

    public String getSequenceSession(String seq, Session session);
}
