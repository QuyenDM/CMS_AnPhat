package com.cms.service;

import com.cms.business.service.StatisticStaffPointBusinessInterface;
import com.cms.dto.StatisticStaffPointDTO;
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
* @since 06/12/2016 23:05:01
*/
@WebService(endpointInterface = "com.cms.service.StatisticStaffPointService")
public class StatisticStaffPointServiceImpl implements StatisticStaffPointService {
    @Autowired
    StatisticStaffPointBusinessInterface statisticStaffPointBusiness;

    @Override
    public String updateStatisticStaffPoint(StatisticStaffPointDTO statisticStaffPointDTO) {
        return statisticStaffPointBusiness.update(statisticStaffPointDTO);
    }

    @Override
    public String deleteStatisticStaffPoint(Long id) {
        return statisticStaffPointBusiness.delete(id);
    }

    @Override
    public String deleteListStatisticStaffPoint(List<StatisticStaffPointDTO> statisticStaffPointListDTO) {
        return statisticStaffPointBusiness.delete(statisticStaffPointListDTO);
    }

    @Override
    public StatisticStaffPointDTO findStatisticStaffPointById(Long id) {
        if (id != null && id > 0) {
            return (StatisticStaffPointDTO)statisticStaffPointBusiness.findById(id).toDTO();
        }
        return null;
    }

    public List<StatisticStaffPointDTO> getListStatisticStaffPointDTO(StatisticStaffPointDTO statisticStaffPointDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
         if (statisticStaffPointDTO != null ) {
            return statisticStaffPointBusiness.search(statisticStaffPointDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertStatisticStaffPoint(StatisticStaffPointDTO statisticStaffPointDTO) {
        return statisticStaffPointBusiness.createObject(statisticStaffPointDTO);
    }
    @Override
    public String insertOrUpdateListStatisticStaffPoint(List<StatisticStaffPointDTO> statisticStaffPointDTO) {
         return statisticStaffPointBusiness.insertList(statisticStaffPointDTO);
    }

    public List<String> getSequenseStatisticStaffPoint(String seqName, int... size) {
        int number = ( size[0] > 0 ? size[0] : 1 );
        return statisticStaffPointBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<StatisticStaffPointDTO> getListStatisticStaffPointByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<StatisticStaffPointDTO> lstStatisticStaffPoint = new ArrayList<>();
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
                } else{
                    value = con.getValue();
                }
                con.setValue(value.toLowerCase());
                con.setField(StringUtils.formatFunction("lower", con.getField()));
            }
            con.setOperator(StringUtils.convertTypeOperator(con.getOperator()));
        }

        lstStatisticStaffPoint = statisticStaffPointBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstStatisticStaffPoint;
    }
}
