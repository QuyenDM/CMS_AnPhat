package com.cms.service;

import com.cms.business.service.PriceInfoBusinessInterface;
import com.cms.dto.PriceInfoDTO;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author QuyenDM
 * @version 1.0
 * @since 03/11/2016 22:17:52
 */
@WebService(endpointInterface = "com.cms.service.PriceInfoService")
public class PriceInfoServiceImpl implements PriceInfoService {

    @Autowired
    PriceInfoBusinessInterface priceInfoBusiness;

    @Override
    public String updatePriceInfo(PriceInfoDTO priceInfoDTO) {
        return priceInfoBusiness.update(priceInfoDTO);
    }

    @Override
    public String deletePriceInfo(Long id) {
        return priceInfoBusiness.delete(id);
    }

    @Override
    public String deleteListPriceInfo(List<PriceInfoDTO> priceInfoListDTO) {
        return priceInfoBusiness.delete(priceInfoListDTO);
    }

    @Override
    public PriceInfoDTO findPriceInfoById(Long id) {
        if (id != null && id > 0) {
            return (PriceInfoDTO) priceInfoBusiness.findById(id).toDTO();
        }
        return null;
    }

    public List<PriceInfoDTO> getListPriceInfoDTO(PriceInfoDTO priceInfoDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (priceInfoDTO != null) {
            return priceInfoBusiness.search(priceInfoDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertPriceInfo(PriceInfoDTO priceInfoDTO) {
        return priceInfoBusiness.createObject(priceInfoDTO);
    }

    @Override
    public String insertOrUpdateListPriceInfo(List<PriceInfoDTO> priceInfoDTO) {
        return priceInfoBusiness.insertList(priceInfoDTO);
    }

    public List<String> getSequensePriceInfo(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);
        return priceInfoBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<PriceInfoDTO> getListPriceInfoByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<PriceInfoDTO> lstPriceInfo = new ArrayList<>();
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

        lstPriceInfo = priceInfoBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstPriceInfo;
    }
}
