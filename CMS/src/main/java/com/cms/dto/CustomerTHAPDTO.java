package com.cms.dto;

import com.cms.common.basedto.BaseDTO;

/**
 *
 * @author quyen
 */
public class CustomerTHAPDTO extends BaseDTO {

    private String custTHAPId;
    private String custId; //Id khach hang
    private String taxCode; //Ma so thue
    private String name; //Ten khach hang
    private String taxAuthority; //Cơ quan thuế     
    private String taxDepartment; //Chi cục thuế
    private String deployAddress; //Địa chỉ trả thiết bị
    private String officeAddress; //Dia chi VAT
    private String lastUpdateDate;//Ngay cập nhật gan nhat
    private String phone; //So dien thoai
    private String fax; //So fax
    private String email; //Email
    private String bankAccountNo; //So tai khoan
    private String bankName; //Ngan hang

    private String contactName; //Tên người liên hệ
    private String contactId; //CMND người liên hệ
    private String contactPhone; //SDT người liên hệ
    private String contactEmail; //Email người liên hệ

    private String representativeName; // Tên người đại diện
    private String representativeId; //Chứng minh thư người đại diện
    private String representativeEmail; //Email người đại diện
    private String representativePhone; //SĐT người đại diện
    private String representativeIdCreateDate; //Ngày đăng ký Chứng minh thư người đại diện
    private String description; //Mo ta
    private String service; // Dịch vụ
    private String staffId; // ID nhân viên nhận thông tin
    private String staffCode; // Mã nhân viên nhận thông tin
    private String staffName; // Tên nhân viên nhận thông tin
    private String provider; //Nhà cung cấp
    
    private String notes; //Ghi chú 
    
    private String orderDate; //Ngày gửi yêu cầu sang nhà cung cấp
    private String receiveInfoDate; //Ngày nhận thông tin (Hay ngày đẩy OK)

    private String priceInfoCode; //Gói cước
    private String contractType; //Loại hình hợp đồng 

    private String paymentAmount; //Số tiền cần thanh toán
    private String discount; //Chiết khấu
    private String receivableBalance; //Số tiền còn lại
    private String discountedBack; //Chiết khấu lùi
    private String payments; //Hình thức thanh toán

    private String paymentStatus; //Trạng thái thanh toán
    private String fileStatus; //Trạng thái hồ sơ
    private String codeCOD; // Mã COD
    private String invoiceStatus; //Trạng thái hoá đơn

    private String status; //Trang thai của KH THAP

    public CustomerTHAPDTO() {
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxAuthority() {
        return taxAuthority;
    }

    public void setTaxAuthority(String taxAuthority) {
        this.taxAuthority = taxAuthority;
    }

    public String getTaxDepartment() {
        return taxDepartment;
    }

    public void setTaxDepartment(String taxDepartment) {
        this.taxDepartment = taxDepartment;
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

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
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

    public String getRepresentativeEmail() {
        return representativeEmail;
    }

    public void setRepresentativeEmail(String representativeEmail) {
        this.representativeEmail = representativeEmail;
    }

    public String getRepresentativePhone() {
        return representativePhone;
    }

    public void setRepresentativePhone(String representativePhone) {
        this.representativePhone = representativePhone;
    }

    public String getRepresentativeIdCreateDate() {
        return representativeIdCreateDate;
    }

    public void setRepresentativeIdCreateDate(String representativeIdCreateDate) {
        this.representativeIdCreateDate = representativeIdCreateDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getReceiveInfoDate() {
        return receiveInfoDate;
    }

    public void setReceiveInfoDate(String receiveInfoDate) {
        this.receiveInfoDate = receiveInfoDate;
    }

    public String getPriceInfoCode() {
        return priceInfoCode;
    }

    public void setPriceInfoCode(String priceInfoCode) {
        this.priceInfoCode = priceInfoCode;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getReceivableBalance() {
        return receivableBalance;
    }

    public void setReceivableBalance(String receivableBalance) {
        this.receivableBalance = receivableBalance;
    }

    public String getDiscountedBack() {
        return discountedBack;
    }

    public void setDiscountedBack(String discountedBack) {
        this.discountedBack = discountedBack;
    }

    public String getPayments() {
        return payments;
    }

    public void setPayments(String payments) {
        this.payments = payments;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getCodeCOD() {
        return codeCOD;
    }

    public void setCodeCOD(String codeCOD) {
        this.codeCOD = codeCOD;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

}
