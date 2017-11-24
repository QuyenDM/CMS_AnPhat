/**
 * @(#)CustomerCareHistoryBO.java 7/30/2016 1:26 AM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.model;

import com.vfw5.base.model.BaseFWModel;
import java.util.Date;
import javax.persistence.*;
import com.cms.dto.CustomerCareHistoryDTO;
import com.vfw5.base.utils.DateTimeUtils;
import com.vfw5.base.utils.ParamUtils;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/30/2016 1:26 AM
 */
@Entity
@Table(name = "CUSTOMER_CARE_HISTORY")
public class CustomerCareHistory extends BaseFWModel {

    //Fields
    private Long id;
    private Long custId;
    private String taxCode;
    private Long staffId;
    private String staffCode;
    private Date dateTracking;
    private String notes;
    private Date createDate;
    private String contact;
    private String service;
    private String telNumber;
    private String status;
    private String mineName;

    //Constructors
    public CustomerCareHistory() {
        setColId("id");
        setColName("dateTracking");
        setUniqueColumn(new String[]{"id"});
    }

    public CustomerCareHistory(String taxCode) {
        this.taxCode = taxCode;
    }

    public CustomerCareHistory(Long id, Long custId, String taxCode, Long staffId, String staffCode, Date dateTracking, String notes, Date createDate, String contact, String service, String telNumber, String status, String mineName) {
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

    //Getters and Setters
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence", value = "CUSTOMER_CARE_HISTORY_SEQ")
            }
    )
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "CUST_ID")
    public Long getCustId() {
        return this.custId;
    }

    public void setCustId(final Long custId) {
        this.custId = custId;
    }

    @Column(name = "TAX_CODE")
    public String getTaxCode() {
        return this.taxCode;
    }

    public void setTaxCode(final String taxCode) {
        this.taxCode = taxCode;
    }

    @Column(name = "STAFF_ID")
    public Long getStaffId() {
        return this.staffId;
    }

    public void setStaffId(final Long staffId) {
        this.staffId = staffId;
    }

    @Column(name = "STAFF_CODE")
    public String getStaffCode() {
        return this.staffCode;
    }

    public void setStaffCode(final String staffCode) {
        this.staffCode = staffCode;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "DATE_TRACKING")
    public Date getDateTracking() {
        return this.dateTracking;
    }

    public void setDateTracking(final Date dateTracking) {
        this.dateTracking = dateTracking;
    }

    @Column(name = "NOTES")
    public String getNotes() {
        return this.notes;
    }

    public void setNotes(final String notes) {
        this.notes = notes;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(final Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "CONTACT")
    public String getContact() {
        return this.contact;
    }

    public void setContact(final String contact) {
        this.contact = contact;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @Column(name = "SERVICE")
    public String getService() {
        return this.service;
    }

    public void setService(final String service) {
        this.service = service;
    }

    @Column(name = "TEL_NUMBER")
    public String getTelNumber() {
        return this.telNumber;
    }

    public void setTelNumber(final String telNumber) {
        this.telNumber = telNumber;
    }

    @Column(name = "MINE_NAME")
    public String getMineName() {
        return mineName;
    }

    public void setMineName(String mineName) {
        this.mineName = mineName;
    }

    @Override
    public CustomerCareHistoryDTO toDTO() {
        CustomerCareHistoryDTO dto = new CustomerCareHistoryDTO(
                id == null ? null : id.toString(), custId == null ? null : custId.toString(), taxCode, staffId == null ? null : staffId.toString(), staffCode, dateTracking == null ? null : DateTimeUtils.convertDateToString(dateTracking, ParamUtils.ddMMyyyy), notes, createDate == null ? null : DateTimeUtils.convertDateToString(createDate, ParamUtils.ddMMyyyyHHmmss), contact, service, telNumber, status, mineName
        );
        return dto;
    }
}
