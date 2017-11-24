/**
 * @(#)CustomerContactForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.dto;

import com.vfw5.base.dto.BaseFWDTO;
import com.cms.model.CustomerContact;
import com.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/26/2016 10:32 PM
 */
@XmlRootElement(name = "CustomerContact")
public class CustomerContactDTO extends BaseFWDTO<CustomerContact> {
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
        setDefaultSortField("name");
    }

    public CustomerContactDTO(String taxCode) {
        this.taxCode = taxCode;
    }

    public CustomerContactDTO(String id, String custId, String taxCode, String name,
            String telNumber, String email, String regency, String status, String staffCode) {
        this.id = id;
        this.custId = custId;
        this.taxCode = taxCode;
        this.name = name;
        this.telNumber = telNumber;
        this.email = email;
        this.regency = regency;
        this.status = status;
        this.staffCode = staffCode;
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

    
    @Override
    public CustomerContact toModel() {
        CustomerContact model = new CustomerContact(
                !StringUtils.validString(id) ? null
                : Long.valueOf(id),
                !StringUtils.validString(custId) ? null
                : Double.parseDouble(custId),
                taxCode,
                name,
                telNumber,
                email,
                regency,
                status, staffCode);
        return model;
    }

    @Override
    public Long getFWModelId() {
        return !StringUtils.validString(id) ? null : Long.valueOf(id);
    }

    @Override
    public String catchName() {
        return getName();
    }
}
