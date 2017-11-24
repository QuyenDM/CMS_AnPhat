/**
 * @(#)CustomerContactBO.java 7/26/2016 10:32 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.model;

import com.vfw5.base.model.BaseFWModel;
import javax.persistence.*;
import com.cms.dto.CustomerContactDTO;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/26/2016 10:32 PM
 */
@Entity
@Table(name = "CUSTOMER_CONTACT")
public class CustomerContact extends BaseFWModel {

    //Fields
    private Long id;
    private Double custId;
    private String taxCode;
    private String name;
    private String telNumber;
    private String email;
    private String regency;
    private String status;
    private String staffCode;

    //Constructors
    public CustomerContact() {
        setColId("id");
        setColName("name");
        setUniqueColumn(new String[]{"id"});
    }

    public CustomerContact(Long id, Double custId, String taxCode, String name,
            String telNumber, String email, String regency, 
            String status, String staffCode) {
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

    //Getters and Setters
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence", value = "CUSTOMER_CONTACT_SEQ")
            }
    )
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "CUST_ID")
    public Double getCustId() {
        return this.custId;
    }

    public void setCustId(final Double custId) {
        this.custId = custId;
    }

    @Column(name = "TAX_CODE")
    public String getTaxCode() {
        return this.taxCode;
    }

    public void setTaxCode(final String taxCode) {
        this.taxCode = taxCode;
    }

    @Column(name = "NAME")
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Column(name = "TEL_NUMBER")
    public String getTelNumber() {
        return this.telNumber;
    }

    public void setTelNumber(final String telNumber) {
        this.telNumber = telNumber;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Column(name = "REGENCY")
    public String getRegency() {
        return this.regency;
    }

    public void setRegency(final String regency) {
        this.regency = regency;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @Column(name = "STAFF_CODE")
    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    @Override
    public CustomerContactDTO toDTO() {
        CustomerContactDTO dto = new CustomerContactDTO(
                id == null ? null : id.toString(), custId == null ? null : custId.toString(),
                taxCode, name, telNumber, email, regency, status, staffCode
        );
        return dto;
    }
}
