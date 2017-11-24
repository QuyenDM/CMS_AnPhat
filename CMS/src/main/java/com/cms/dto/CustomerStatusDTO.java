/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dto;

import com.cms.common.basedto.BaseDTO;
import com.vwf5.base.utils.DateUtil;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/5/2016 12:21 AM
 */
public class CustomerStatusDTO extends BaseDTO {
    //Fields

    private String id;
    private String custId;
    private String taxCode;
    private String taxCodeName;
    private String service;
    private String staffId;
    private String staffCode;
    private String status;
    private String lastUpdated;
    private String createdDate;
    private String staffName;
    private String custName;
    private String mineName;
    private String taxAuthority;
    private String lastNotes;

    //Constructor
    public CustomerStatusDTO() {
    }

    public CustomerStatusDTO(String custId, String taxCode, String service,
            String staffId, String staffCode, String status,
            String staffName, String custName, String mineName, String taxAuthority, String lastNotes) {
        this.custId = custId;
        this.taxCode = taxCode;
        this.service = service;
        this.staffId = staffId;
        this.staffCode = staffCode;
        this.status = status;
        this.staffName = staffName;
        this.custName = custName;
        this.mineName = mineName;
        this.taxAuthority = taxAuthority;
        this.createdDate = DateUtil.sysdateStringddMMyyyyhhmmss();
        this.lastUpdated = DateUtil.sysdateStringddMMyyyyhhmmss();
        this.lastNotes = lastNotes;
    }

    public CustomerStatusDTO(String staffId, String staffCode, String staffName) {
        this.staffId = staffId;
        this.staffCode = staffCode;
        this.staffName = staffName;
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

    public void setTaxCodeName(String taxCodeName) {
        this.taxCodeName = taxCodeName;
    }

    public String getTaxCodeName() {
        return taxCodeName;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustName() {
        return custName;
    }

    public String getMineName() {
        return mineName;
    }

    public void setMineName(String mineName) {
        this.mineName = mineName;
    }

    public String getTaxAuthority() {
        return taxAuthority;
    }

    public void setTaxAuthority(String taxAuthority) {
        this.taxAuthority = taxAuthority;
    }

    public String getLastNotes() {
        return lastNotes;
    }

    public void setLastNotes(String lastNotes) {
        this.lastNotes = lastNotes;
    }

}
