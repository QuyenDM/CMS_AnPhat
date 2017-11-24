package com.cms.dto;

/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
import com.cms.common.basedto.BaseDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/19/2016 12:01 AM
 */
public class TermInformationDTO extends BaseDTO {

    //Fields
    private String id;
    private String custId;
    private String custIdName;
    private String taxCode;
    private String startTime;
    private String endTime;
    private String provider;
    private String email;
    private String phone;
    private String service;
    private String mineName;
    private String status;
    private String sourceData;
    private String isContactInfo;

    //Constructor
    public TermInformationDTO() {
    }

    public TermInformationDTO(String taxCode) {
        this.taxCode = taxCode;
    }

    public CustomerContactDTO convert2CustomerContact() {
        return new CustomerContactDTO(taxCode, phone, email);
    }

    public TermInformationDTO(String id, String custId, String taxCode, String startTime, String endTime, String provider, String email, String phone, String service, String mineName) {
        this.id = id;
        this.custId = custId;
        this.taxCode = taxCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.provider = provider;
        this.email = email;
        this.phone = phone;
        this.service = service;
        this.mineName = mineName;
    }
    
    public TermInformationDTO(String id, String custId, String custIdName, String taxCode, String startTime, String endTime, String provider, String email, String phone, String service, String mineName, String status) {
        this.id = id;
        this.custId = custId;
        this.custIdName = custIdName;
        this.taxCode = taxCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.provider = provider;
        this.email = email;
        this.phone = phone;
        this.service = service;
        this.mineName = mineName;
        this.status = status;
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

    public void setCustIdName(String custIdName) {
        this.custIdName = custIdName;
    }

    public String getCustIdName() {
        return custIdName;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getMineName() {
        return mineName;
    }

    public void setMineName(String mineName) {
        this.mineName = mineName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSourceData() {
        return sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    public String getIsContactInfo() {
        return isContactInfo;
    }

    public void setIsContactInfo(String isContactInfo) {
        this.isContactInfo = isContactInfo;
    }

}
