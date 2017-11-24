/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.business.service.CommonBusinessInterface;
import com.cms.dto.StaffDTO;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;
import javax.jws.WebService;
import java.util.ArrayList;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 1:28 PM
 */
@WebService(endpointInterface = "com.cms.service.StaffService")
//@net.anotheria.moskito.aop.annotation.Monitor
public class StaffServiceImpl implements StaffService {

    @Autowired
    BaseFWServiceInterface staffBusiness;

    @Autowired
    CommonBusinessInterface commonBusinessInterface;

    @Override
    public String updateStaff(StaffDTO staffDTO) {
        //update ghi log
//        List<StaffDTO> lstStaffDTOs = new ArrayList<>();
//        lstStaffDTOs.add(staffDTO);
        return staffBusiness.update(staffDTO);
    }

    @Override
    public String deleteStaff(Long id, String username) {
        return staffBusiness.delete(id);
    }

    @Override
    public String deleteListStaff(List<StaffDTO> staffListDTO) {
        return staffBusiness.delete(staffListDTO);
    }

    @Override
    public StaffDTO findStaffById(Long id) {
        if (id != null && id > 0) {
            return (StaffDTO) staffBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<StaffDTO> getListStaffDTO(StaffDTO staffDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (staffDTO != null) {
            return staffBusiness.search(staffDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertStaff(StaffDTO staffDTO) {
        return staffBusiness.createObject(staffDTO);
    }
//    @Override
//    public ResultDTO insertStaff(StaffDTO staffDTO) {
//        List<StaffDTO> lstStaffDTOs = new ArrayList<>();
//        ResultDTO resultDTO = new ResultDTO();
//        Class<?> c = staffDTO.getClass();
//        HashMap<String, String> hasmap = new HashMap<>();
//        Staff staff = new Staff();
//        String[] lstColumnUnique = staff.getUniqueColumn();
//        for (int i = 0; i < lstColumnUnique.length; i++) {
//            try {
//                Method method = c.getMethod(DataUtil.getGetterOfColumn(lstColumnUnique[i]));
//                try {
//                    hasmap.put(lstColumnUnique[i], (String) method.invoke(staffDTO));
//                } catch (IllegalAccessException ex) {
//                    Logger.getLogger(StaffServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IllegalArgumentException ex) {
//                    Logger.getLogger(StaffServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (InvocationTargetException ex) {
//                    Logger.getLogger(StaffServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } catch (NoSuchMethodException ex) {
//                Logger.getLogger(StaffServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SecurityException ex) {
//                Logger.getLogger(StaffServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        try {
//            Boolean check = staffBusiness.isDuplicate(null, hasmap);
//            if (!check) {
//                lstStaffDTOs.add(staffDTO);
//                resultDTO = staffBusiness.insertAndRecordLog(lstStaffDTOs);
//                resultDTO.setKey(ParamUtils.SUCCESS);
//            } else {
//
//                resultDTO.setKey("ERROR_EXISTED");
//
//            }
//        } catch (Exception e) {
//            resultDTO.setKey("ERROR_SYSTEM");
//        }
//        return resultDTO;
//    }

    @Override
    public String insertOrUpdateListStaff(List<StaffDTO> staffDTO) {
        return staffBusiness.insertList(staffDTO);
    }

    @Override
    public List<String> getSequenseStaff(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);

        return commonBusinessInterface.getListSequense(seqName, number);
    }

    @Override
    public List<StaffDTO> getListStaffByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<StaffDTO> lstStaff = new ArrayList<>();
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
        lstStaff = staffBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstStaff;
    }

}
