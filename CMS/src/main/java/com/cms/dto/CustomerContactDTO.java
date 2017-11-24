/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dto;

import com.cms.common.basedto.BaseDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/26/2016 10:32 PM
 */
public class CustomerContactDTO extends BaseDTO {
    //Fields

    private String id;
    private String custId;
    private String taxCode;
    private String name;
    private String telNumber;
    private String email;
    private String regency;
    private String status;
    private String staffCode;
    //Constructor
    public CustomerContactDTO() {
    }

    public CustomerContactDTO(String taxCode) {
        this.taxCode = taxCode;
    }

    public CustomerContactDTO(String id, String custId, String taxCode, String name, String telNumber, String regency, String status) {
        this.id = id;
        this.custId = custId;
        this.taxCode = taxCode;
        this.name = name;
        this.telNumber = telNumber;
        this.regency = regency;
        this.status = status;
    }

    public CustomerContactDTO(String taxCode, String telNumber, String email) {
        this.taxCode = taxCode;
        this.telNumber = telNumber;
        this.email = email;
    }
    
    
    //Getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustId() {
        return custId;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setRegency(String regency) {
        this.regency = regency;
    }

    public String getRegency() {
        return regency;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

}
