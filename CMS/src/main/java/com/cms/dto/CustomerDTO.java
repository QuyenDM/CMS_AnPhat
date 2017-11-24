/**
 * @(#)CustomerForm.java , Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.dto;

import com.cms.common.basedto.BaseDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 16-Apr-15 11:55 AM
 */
public class CustomerDTO extends BaseDTO {

    //Fields
    private String custId; //Id khach hang
    private String taxCode; //Ma so thue
    private String name; //Ten khach hang
    private String taxAuthority; //Cơ quan thuế 
    private String dateRegister;//Ngay dang ky kkt
    private String lastUploadDate;//Ngay kkt gan nhat
    private String taxDepartment; //Chi cục thuế
    private String custType; //Loai khach hang
    private String telNumber; //So dien thoai
    private String fax; //So fax
    private String email; //Email
    private String accountNo; //So tai khoan
    private String bankName; //Ngan hang
    private String agency; //Dai ly thue
    private String status; //Trang thai
    private String deployAddress;
    private String officeAddress; //Dia chi tru so
    private String representativeName; // Tên người đại diện
    private String representativeId; //Chứng minh thư
    private String description; //Mo ta
    private String mineName; //Tên danh sách khai thác
    private String staffName; //Nhan vien tai len

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
    }

    public CustomerDTO(String taxCode, String name, String taxAuthority, String dateRegister, String lastUploadDate, String custType, String taxDepartment, String telNumber, String fax, String email, String accountNo, String bankName, String agency, String deployAddress, String officeAddress, String representativeName, String representativeId, String description, String staffName) {
        this.taxCode = taxCode;
        this.name = name;
        this.taxAuthority = taxAuthority;
        this.dateRegister = dateRegister;
        this.lastUploadDate = lastUploadDate;
        this.taxDepartment = taxDepartment;
        this.custType = custType;
        this.telNumber = telNumber;
        this.fax = fax;
        this.email = email;
        this.accountNo = accountNo;
        this.bankName = bankName;
        this.agency = agency;
        this.deployAddress = deployAddress;
        this.officeAddress = officeAddress;
        this.representativeName = representativeName;
        this.representativeId = representativeId;
        this.description = description;
        this.staffName = staffName;
    }

    public CustomerStatusDTO convert2CustomerStatus(StaffDTO staff) {
        return new CustomerStatusDTO(custId, taxCode, service, staff.getStaffId(), 
                staff.getCode(), "1", staff.getName(), name, mineName, taxAuthority, "");
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

    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public String getRepresentativeId() {
        return representativeId;
    }

    public void setRepresentativeId(String representativeId) {
        this.representativeId = representativeId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeployAddress() {
        return deployAddress;
    }

    public void setDeployAddress(String deployAddress) {
        this.deployAddress = deployAddress;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getTaxDepartment() {
        return taxDepartment;
    }

    public void setTaxDepartment(String taxDepartment) {
        this.taxDepartment = taxDepartment;
    }

    public String getTaxAuthority() {
        return taxAuthority;
    }

    public void setTaxAuthority(String taxAuthority) {
        this.taxAuthority = taxAuthority;
    }

    public String getMineName() {
        return mineName;
    }

    public void setMineName(String mineName) {
        this.mineName = mineName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
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
