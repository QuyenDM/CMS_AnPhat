
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.service;

import com.cms.dto.ServicesDTO;
import com.cms.model.Department;
import com.vfw5.base.service.BaseFWServiceInterface;
import java.util.List;
import javax.jws.WebService;
import java.util.ArrayList;
import com.vfw5.base.pojo.ConditionBean;
import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.vfw5.base.dto.ResultDTO;
import com.vfw5.base.utils.DataUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Truongbx3
 * @version 1.0
 * @since 5/25/2015 2:33 PM
 */
@WebService(endpointInterface = "com.cms.service.ServicesService")
//@net.anotheria.moskito.aop.annotation.Monitor
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    BaseFWServiceInterface servicesBusiness;

    @Override
    public String updateServices(ServicesDTO servicesDTO) {
        return servicesBusiness.update(servicesDTO);
    }
//    @Override
//    public String updateServices(ServicesDTO servicesDTO) {
//        return servicesBusiness.update(servicesDTO);
//    }

    @Override
    public String deleteServices(Long id) {
        return servicesBusiness.delete(id);
    }

    @Override
    public String deleteListServices(List<ServicesDTO> servicesListDTO) {
        return servicesBusiness.delete(servicesListDTO);
    }

    @Override
    public ServicesDTO findServicesById(Long id) {
        if (id != null && id > 0) {
            return (ServicesDTO) servicesBusiness.findById(id).toDTO();
        }
        return null;
    }

    @Override
    public List<ServicesDTO> getListServicesDTO(ServicesDTO servicesDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        if (servicesDTO != null) {
            return servicesBusiness.search(servicesDTO, rowStart, maxRow, sortType, sortFieldList);
        }
        return null;
    }

    @Override
    public ResultDTO insertServices(ServicesDTO servicesDTO) {
        List<ServicesDTO> lstServicesDTOs = new ArrayList<>();
        ResultDTO resultDTO = new ResultDTO();
        Class<?> c = servicesDTO.getClass();
        HashMap<String, String> hasmap = new HashMap<>();
        Department department = new Department();
        String[] lstColumnUnique = department.getUniqueColumn();
        for (int i = 0; i < lstColumnUnique.length; i++) {
            try {
                Method method = c.getMethod(DataUtil.getGetterOfColumn(lstColumnUnique[i]));
                try {
                    hasmap.put(lstColumnUnique[i], (String) method.invoke(servicesDTO));
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(DepartmentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(DepartmentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(DepartmentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(DepartmentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(DepartmentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Boolean check = servicesBusiness.isDuplicate(null, hasmap);
            if (!check) {
                resultDTO = servicesBusiness.createObject(servicesDTO);
//                resultDTO.setKey(ParamUtils.SUCCESS);
            } else {
                resultDTO.setKey("ERROR_EXISTED");
            }
        } catch (Exception e) {
            resultDTO.setKey("ERROR_SYSTEM");
        }
        return resultDTO;
    }
//    @Override
//    public ResultDTO insertServices(ServicesDTO servicesDTO) {
//        return servicesBusiness.createObject(servicesDTO);
//    }

    @Override
    public String insertOrUpdateListServices(List<ServicesDTO> servicesDTO) {
        return servicesBusiness.insertList(servicesDTO);
    }

    @Override
    public List<String> getSequenseServices(String seqName, int... size) {
        int number = (size[0] > 0 ? size[0] : 1);

        return servicesBusiness.getListSequense(seqName, number);
    }

    @Override
    public List<ServicesDTO> getListServicesByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        List<ServicesDTO> lstServices = new ArrayList<>();
        for (ConditionBean con : lstCondition) {
            if (con.getType().equalsIgnoreCase(ParamUtils.TYPE_DATE)) {
                con.setField(StringUtils.formatFunction("trunc", con.getField()));
            } else if (con.getType().equalsIgnoreCase(ParamUtils.NUMBER)) {
                con.setType(ParamUtils.TYPE_NUMBER);
            } else {
                String value = StringUtils.formatLike(con.getValue());
                value.toLowerCase();
                con.setValue(value);
                con.setField(StringUtils.formatFunction("lower", con.getField()));
            }
            con.setOperator(StringUtils.convertTypeOperator(con.getOperator()));

        }
        lstServices = servicesBusiness.searchByConditionBean(lstCondition, rowStart, maxRow, sortType, sortFieldList);
        return lstServices;
    }

}
