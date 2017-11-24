/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.business.service.CategoryListBusinessInterface;
import com.cms.dto.TermInformationDTO;
import com.cms.business.service.TermInformationBusinessInterface;
import java.util.List;
import javax.jws.WebService;
import java.util.ArrayList;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.utils.DataUtil;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/19/2016 12:01 AM
 */
@WebService(endpointInterface = "com.cms.service.TermInformationService")
public class TermInformationServiceImpl implements TermInformationService {

    @Autowired
    TermInformationBusinessInterface termInformationBusiness;

    @Autowired
    CategoryListBusinessInterface categoryListBusinessInterface;

    @Override
    public String updateTermInformation(TermInformationDTO termInformationDTO) {
        return termInformationBusiness.update(termInformationDTO);
    }

    @Override
    public String deleteTermInformation(Long id) {
        return termInformationBusiness.delete(id);
    }

    @Override
    public String deleteListTermInformation(List<TermInformationDTO> termInformationListDTO) {
        return termInformationBusiness.delete(termInformationListDTO);
    }

    @Override
    public TermInformationDTO findTermInformationById(Long id) {
        if (id != null && id > 0) {
            return (TermInformationDTO) termInformationBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<TermInformationDTO> getListTermInformationDTO(TermInformationDTO termInformationDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (termInformationDTO != null) {
            return termInformationBusiness.search(termInformationDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertTermInformation(TermInformationDTO termInformationDTO) {
        return termInformationBusiness.createObject(termInformationDTO);
    }

    @Override
    public String insertOrUpdateListTermInformation(List<TermInformationDTO> termInformationDTO) {
        return termInformationBusiness.insertList(termInformationDTO);
    }

    @Override
    public ResultDTO insertListTermInformation(List<TermInformationDTO> termInformationDTO) {
        ResultDTO result = termInformationBusiness.insertListTermInformation(termInformationDTO);
        if (!DataUtil.isListNullOrEmpty(termInformationDTO)) {
            TermInformationDTO term = termInformationDTO.get(0);
            String mineName = term.getMineName();
            categoryListBusinessInterface.updateQuanlityForCategoryList(mineName);
        }
        return result;
    }

    @Override
    public List<String> getSequenseTermInformation(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);

        return termInformationBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<TermInformationDTO> getListTermInformationByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<TermInformationDTO> lstTermInformation = new ArrayList<>();
        for (ConditionBean con : lstCondition) {
            if (con.getType().equalsIgnoreCase(ParamUtils.TYPE_DATE)) {
                con.setField(StringUtils.formatFunction("trunc", con.getField()));
            } else if (con.getType().equalsIgnoreCase(ParamUtils.NUMBER)) {
                con.setType(ParamUtils.TYPE_NUMBER);
            } else if (con.getType().equalsIgnoreCase(ParamUtils.NUMBER_DOUBLE)) {
                con.setType(ParamUtils.NUMBER_DOUBLE);
            } else {
                String value = "";
                if (con.getOperator().equalsIgnoreCase(ParamUtils.NAME_LIKE)) {
                    value = StringUtils.formatLike(con.getValue());
                } else {
                    value = con.getValue();
                }
                con.setValue(value.toLowerCase());
                con.setField(StringUtils.formatFunction("lower", con.getField()));
            }
            con.setOperator(StringUtils.convertTypeOperator(con.getOperator()));
        }

        lstTermInformation = termInformationBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstTermInformation;
    }

}
