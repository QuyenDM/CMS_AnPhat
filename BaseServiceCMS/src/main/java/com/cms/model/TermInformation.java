/**
 * @(#)TermInformationBO.java 7/19/2016 12:01 AM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.model;

import com.vfw5.base.model.BaseFWModel;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.cms.dto.TermInformationDTO;
import com.vfw5.base.utils.DateTimeUtils;
import com.vfw5.base.utils.ParamUtils;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/19/2016 12:01 AM
 */
@Entity
@Table(name = "TERM_INFORMATION")
public class TermInformation extends BaseFWModel {

    //Fields
    private Long id;
    private Double custId;
    private String taxCode;
    private Date startTime;
    private Date endTime;
    private String provider;
    private String email;
    private String phone;
    private String service;
    private String mineName;
    private String sourceData;
    private String isContactInfo;

    //Constructors
    public TermInformation() {
        setColId("id");
        setColName("startTime");
        setUniqueColumn(new String[]{"id"});
    }

    public TermInformation(Long id) {
        this.id = id;
    }

    public TermInformation(Long id, Double custId, String taxCode, Date startTime,
            Date endTime, String provider, String email, String phone, String service,
            String mineName, String sourceData, String isContactInfo) {
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
        this.sourceData = sourceData;
        this.isContactInfo = isContactInfo;
    }

    //Getters and Setters
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "TERM_INFORMATION_SEQ")
            }
    )
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "CUST_ID", nullable = true)
    public Double getCustId() {
        return this.custId;
    }

    public void setCustId(final Double custId) {
        this.custId = custId;
    }

    @Column(name = "TAX_CODE", nullable = false)
    public String getTaxCode() {
        return this.taxCode;
    }

    public void setTaxCode(final String taxCode) {
        this.taxCode = taxCode;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "START_TIME")
    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(final Date startTime) {
        this.startTime = startTime;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "END_TIME")
    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(final Date endTime) {
        this.endTime = endTime;
    }

    @Column(name = "PROVIDER")
    public String getProvider() {
        return this.provider;
    }

    public void setProvider(final String provider) {
        this.provider = provider;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Column(name = "PHONE")
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    @Column(name = "SERVICE")
    public String getService() {
        return this.service;
    }

    public void setService(final String service) {
        this.service = service;
    }

    @Column(name = "MINE_NAME")
    public String getMineName() {
        return mineName;
    }

    public void setMineName(String mineName) {
        this.mineName = mineName;
    }
    
    @Column(name = "SOURCE_DATA")
    public String getSourceData() {
        return sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }
    
    @Column(name = "IS_CONTACT_INFO")
    public String getIsContactInfo() {
        return isContactInfo;
    }

    public void setIsContactInfo(String isContactInfo) {
        this.isContactInfo = isContactInfo;
    }

    
    @Override
    public TermInformationDTO toDTO() {
        TermInformationDTO dto = new TermInformationDTO(
                id == null ? null : id.toString(), custId == null ? null : custId.toString(),
                taxCode, startTime == null ? null : DateTimeUtils.convertDateToString(startTime, ParamUtils.ddMMyyyy),
                endTime == null ? null : DateTimeUtils.convertDateToString(endTime, ParamUtils.ddMMyyyy),
                provider, email, phone, service, mineName, sourceData, isContactInfo
        );
        return dto;
    }
    
}
