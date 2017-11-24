/**
 * @(#)CustomerForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.dto;

import com.vfw5.base.dto.BaseFWDTO;
import com.cms.model.Customer;
import com.vfw5.base.utils.DateTimeUtils;
import com.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/31/2016 12:21 AM
 */
@XmlRootElement(name = "Customer")
public class CustomerDTO extends BaseFWDTO<Customer> {
    //Fields

    private String custId;
    private String accountNo;
    private String address;
    private String agency;
    private String bankName;
    private String custType;
    private String deployAddress;
    private String email;
    private String fax;
    private String name;
    private String provinceId;
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
    private String dateRegister;
    private String lastUploadDate;
    private String service;
    private String staffId;
    private String startTime;
    private String endTime;
    private String provider;
    private String custCareHistoryCreatedDate;
    private String notes;
    private String createDate;
    private String customerStatus;

    //Constructor
    public CustomerDTO() {
        setDefaultSortField("name");
    }

    public CustomerDTO(String custId, String accountNo, String address,
            String agency, String bankName, String custType, String deployAddress,
            String email, String fax, String name, String provinceId, String status,
            String taxCode, String telNumber, String officeAddress, String description,
            String mineName, String representativeId, String representativeName,
            String staffName, String taxAuthority, String taxDepartment,
            String dateRegister, String lastUploadDate) {
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

    //Getters and setters
    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustId() {
        return custId;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAgency() {
        return agency;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getCustType() {
        return custType;
    }

    public void setDeployAddress(String deployAddress) {
        this.deployAddress = deployAddress;
    }

    public String getDeployAddress() {
        return deployAddress;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFax() {
        return fax;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setMineName(String mineName) {
        this.mineName = mineName;
    }

    public String getMineName() {
        return mineName;
    }

    public void setRepresentativeId(String representativeId) {
        this.representativeId = representativeId;
    }

    public String getRepresentativeId() {
        return representativeId;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setTaxAuthority(String taxAuthority) {
        this.taxAuthority = taxAuthority;
    }

    public String getTaxAuthority() {
        return taxAuthority;
    }

    public void setTaxDepartment(String taxDepartment) {
        this.taxDepartment = taxDepartment;
    }

    public String getTaxDepartment() {
        return taxDepartment;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(String dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getLastUploadDate() {
        return lastUploadDate;
    }

    public void setLastUploadDate(String lastUploadDate) {
        this.lastUploadDate = lastUploadDate;
    }

    @Override
    public Customer toModel() {
        try {
            Customer model = new Customer(
                    !StringUtils.validString(custId) ? null
                    : Long.valueOf(custId),
                    accountNo,
                    address,
                    agency,
                    bankName,
                    custType,
                    deployAddress,
                    email,
                    fax,
                    name,
                    !StringUtils.validString(provinceId) ? null
                    : Long.valueOf(provinceId),
                    status,
                    taxCode,
                    telNumber,
                    officeAddress,
                    description,
                    mineName,
                    representativeId,
                    representativeName,
                    staffName,
                    taxAuthority,
                    taxDepartment,
                    !StringUtils.validString(dateRegister) ? null
                    : DateTimeUtils.convertStringToDate(dateRegister),
                    !StringUtils.validString(lastUploadDate) ? null
                    : DateTimeUtils.convertStringToDate(lastUploadDate));
            return model;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long getFWModelId() {
        return !StringUtils.validString(taxCode) ? null : Long.valueOf(taxCode);
    }

    @Override
    public String catchName() {
        return getName().toString();
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getCustCareHistoryCreatedDate() {
        return custCareHistoryCreatedDate;
    }

    public void setCustCareHistoryCreatedDate(String custCareHistoryCreatedDate) {
        this.custCareHistoryCreatedDate = custCareHistoryCreatedDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

}
