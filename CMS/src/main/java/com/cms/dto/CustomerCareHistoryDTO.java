/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dto;

import com.cms.common.basedto.BaseDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/30/2016 1:26 AM
 */
public class CustomerCareHistoryDTO extends BaseDTO {
    //Fields

    private String id;
    private String custId;
    private String taxCode;
    private String staffId;
    private String staffCode;
    private String dateTracking;
    private String notes;
    private String createDate;
    private String contact;
    private String service;
    private String telNumber;

    private String status;
    private String mineName;
    //Constructor
    public CustomerCareHistoryDTO() {
    }

    public CustomerCareHistoryDTO(String taxCode) {
        this.taxCode = taxCode;
    }

    public CustomerCareHistoryDTO(String id, String custId, String taxCode,
            String staffId, String staffCode, String dateTracking, String notes, 
            String createDate, String contact, String service, String telNumber, 
            String status, String mineName) {
        this.id = id;
        this.custId = custId;
        this.taxCode = taxCode;
        this.staffId = staffId;
        this.staffCode = staffCode;
        this.dateTracking = dateTracking;
        this.notes = notes;
        this.createDate = createDate;
        this.contact = contact;
        this.service = service;
        this.telNumber = telNumber;
        this.status = status;
        this.mineName = mineName;
    }
    //Getters and setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public void setDateTracking(String dateTracking) {
        this.dateTracking = dateTracking;
    }

    public String getDateTracking() {
        return dateTracking;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getMineName() {
        return mineName;
    }

    public void setMineName(String mineName) {
        this.mineName = mineName;
    }

}
