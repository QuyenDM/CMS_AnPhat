/**
 * @(#)CustomerBO.java 7/31/2016 12:21 AM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.model;

import com.vfw5.base.model.BaseFWModel;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.cms.dto.CustomerDTO;
import com.vfw5.base.utils.DateTimeUtils;
import java.util.Date;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/31/2016 12:21 AM
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer extends BaseFWModel {

    //Fields
    private Long custId;
    private String accountNo;
    private String address;
    private String agency;
    private String bankName;
    private String custType;
    private String deployAddress;
    private String email;
    private String fax;
    private String name;
    private Long provinceId;
    private String status;
    private String taxCode;
    private String telNumber;
    private String officeAddress;
    private String description;
    private String mineName;
    private String representativeId;
    private String representativeName;
    private String staffName;
    private String taxAuthority;
    private String taxDepartment;
    private Date dateRegister;
    private Date lastUploadDate;

    //Constructors
    public Customer() {
        setColId("custId");
        setColName("name");
        setUniqueColumn(new String[]{"taxCode"});
    }
    
    public Customer(String taxCode) {
        this.taxCode = taxCode;
    }
    
    public Customer(Long custId, String accountNo, String address, String agency, String bankName, String custType, String deployAddress, String email, String fax, String name, Long provinceId, String status, String taxCode, String telNumber, String officeAddress, String description, String mineName, String representativeId, String representativeName, String staffName, String taxAuthority, String taxDepartment) {
        this.custId = custId;
        this.accountNo = accountNo;
        this.address = address;
        this.agency = agency;
        this.bankName = bankName;
        this.custType = custType;
        this.deployAddress = deployAddress;
        this.email = email;
        this.fax = fax;
        this.name = name;
        this.provinceId = provinceId;
        this.status = status;
        this.taxCode = taxCode;
        this.telNumber = telNumber;
        this.officeAddress = officeAddress;
        this.description = description;
        this.mineName = mineName;
        this.representativeId = representativeId;
        this.representativeName = representativeName;
        this.staffName = staffName;
        this.taxAuthority = taxAuthority;
        this.taxDepartment = taxDepartment;
    }
    
    public Customer(Long custId, String accountNo, String address, String agency, String bankName, String custType, String deployAddress, String email, String fax, String name, Long provinceId, String status, String taxCode, String telNumber, String officeAddress, String description, String mineName, String representativeId, String representativeName, String staffName, String taxAuthority, String taxDepartment, Date dateRegister, Date lastUploadDate) {
        this.custId = custId;
        this.accountNo = accountNo;
        this.address = address;
        this.agency = agency;
        this.bankName = bankName;
        this.custType = custType;
        this.deployAddress = deployAddress;
        this.email = email;
        this.fax = fax;
        this.name = name;
        this.provinceId = provinceId;
        this.status = status;
        this.taxCode = taxCode;
        this.telNumber = telNumber;
        this.officeAddress = officeAddress;
        this.description = description;
        this.mineName = mineName;
        this.representativeId = representativeId;
        this.representativeName = representativeName;
        this.staffName = staffName;
        this.taxAuthority = taxAuthority;
        this.taxDepartment = taxDepartment;
        this.dateRegister = dateRegister;
        this.lastUploadDate = lastUploadDate;
    }

    //Getters and Setters
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CUSTOMER_SEQ")
            }
    )
    @Column(name = "CUST_ID")
    public Long getCustId() {
        return this.custId;
    }
    
    public void setCustId(final Long custId) {
        this.custId = custId;
    }
    
    @Column(name = "ACCOUNT_NO")
    public String getAccountNo() {
        return this.accountNo;
    }
    
    public void setAccountNo(final String accountNo) {
        this.accountNo = accountNo;
    }
    
    @Column(name = "ADDRESS")
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(final String address) {
        this.address = address;
    }
    
    @Column(name = "AGENCY")
    public String getAgency() {
        return this.agency;
    }
    
    public void setAgency(final String agency) {
        this.agency = agency;
    }
    
    @Column(name = "BANK_NAME")
    public String getBankName() {
        return this.bankName;
    }
    
    public void setBankName(final String bankName) {
        this.bankName = bankName;
    }
    
    @Column(name = "CUST_TYPE")
    public String getCustType() {
        return this.custType;
    }
    
    public void setCustType(final String custType) {
        this.custType = custType;
    }
    
    @Column(name = "DEPLOY_ADDRESS")
    public String getDeployAddress() {
        return this.deployAddress;
    }
    
    public void setDeployAddress(final String deployAddress) {
        this.deployAddress = deployAddress;
    }
    
    @Column(name = "EMAIL")
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    @Column(name = "FAX")
    public String getFax() {
        return this.fax;
    }
    
    public void setFax(final String fax) {
        this.fax = fax;
    }
    
    @Column(name = "NAME", nullable = false)
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    @Column(name = "PROVINCE_ID")
    public Long getProvinceId() {
        return this.provinceId;
    }
    
    public void setProvinceId(final Long provinceId) {
        this.provinceId = provinceId;
    }
    
    @Column(name = "STATUS")
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    @Column(name = "TAX_CODE", unique = true, nullable = false)
    public String getTaxCode() {
        return this.taxCode;
    }
    
    public void setTaxCode(final String taxCode) {
        this.taxCode = taxCode;
    }
    
    @Column(name = "TEL_NUMBER")
    public String getTelNumber() {
        return this.telNumber;
    }
    
    public void setTelNumber(final String telNumber) {
        this.telNumber = telNumber;
    }
    
    @Column(name = "OFFICE_ADDRESS")
    public String getOfficeAddress() {
        return this.officeAddress;
    }
    
    public void setOfficeAddress(final String officeAddress) {
        this.officeAddress = officeAddress;
    }
    
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    @Column(name = "MINE_NAME")
    public String getMineName() {
        return this.mineName;
    }
    
    public void setMineName(final String mineName) {
        this.mineName = mineName;
    }
    
    @Column(name = "REPRESENTATIVE_ID")
    public String getRepresentativeId() {
        return this.representativeId;
    }
    
    public void setRepresentativeId(final String representativeId) {
        this.representativeId = representativeId;
    }
    
    @Column(name = "REPRESENTATIVE_NAME")
    public String getRepresentativeName() {
        return this.representativeName;
    }
    
    public void setRepresentativeName(final String representativeName) {
        this.representativeName = representativeName;
    }
    
    @Column(name = "STAFF_NAME")
    public String getStaffName() {
        return this.staffName;
    }
    
    public void setStaffName(final String staffName) {
        this.staffName = staffName;
    }
    
    @Column(name = "TAX_AUTHORITY")
    public String getTaxAuthority() {
        return this.taxAuthority;
    }
    
    public void setTaxAuthority(final String taxAuthority) {
        this.taxAuthority = taxAuthority;
    }
    
    @Column(name = "TAX_DEPARTMENT")
    public String getTaxDepartment() {
        return this.taxDepartment;
    }
    
    public void setTaxDepartment(final String taxDepartment) {
        this.taxDepartment = taxDepartment;
    }
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "DATE_REGISTER")
    public Date getDateRegister() {
        return this.dateRegister;
    }
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPLOAD_DATE")
    public Date getLastUploadDate() {
        return this.lastUploadDate;
    }
    
    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }
    
    public void setLastUploadDate(Date lastUploadDate) {
        this.lastUploadDate = lastUploadDate;
    }
    
    @Override
    public CustomerDTO toDTO() {
        CustomerDTO dto = new CustomerDTO(
                custId == null ? null : custId.toString(), accountNo, address, agency,
                bankName, custType, deployAddress, email, fax, name,
                provinceId == null ? null : provinceId.toString(), status, taxCode,
                telNumber, officeAddress, description, mineName, representativeId,
                representativeName, staffName, taxAuthority, taxDepartment,
                dateRegister == null ? null : DateTimeUtils.date2ddMMyyyyString(dateRegister),
                lastUploadDate == null ? null : DateTimeUtils.date2ddMMyyyyString(lastUploadDate)
        );
        return dto;
    }
    
}
