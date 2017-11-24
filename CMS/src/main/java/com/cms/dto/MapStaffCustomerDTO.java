/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dto;

import com.cms.common.basedto.BaseDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/4/2016 12:02 AM
 */
public class MapStaffCustomerDTO extends BaseDTO {
    //Fields

    private String staffId;
    private String staffCode;
    private String staffName;
    private String custTaxCode;
    private String custName;
    private String mapId;
    private String staffType;

    //Constructor
    public MapStaffCustomerDTO() {
    }

    public MapStaffCustomerDTO(String staffId, String staffCode, String staffName, String custTaxCode, String custName, String mapId, String staffType) {
        this.staffId = staffId;
        this.staffCode = staffCode;
        this.staffName = staffName;
        this.custTaxCode = custTaxCode;
        this.custName = custName;
        this.mapId = mapId;
        this.staffType = staffType;
    }
    //Getters and setters

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setCustTaxCode(String custTaxCode) {
        this.custTaxCode = custTaxCode;
    }

    public String getCustTaxCode() {
        return custTaxCode;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustName() {
        return custName;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public String getMapId() {
        return mapId;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public String getStaffType() {
        return staffType;
    }

}
