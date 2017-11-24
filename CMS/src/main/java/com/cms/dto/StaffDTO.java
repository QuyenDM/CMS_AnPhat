/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.dto;

import com.cms.common.basedto.BaseDTO;
import java.util.List;

/**
 *
 * @author quyen
 */
public class StaffDTO extends BaseDTO {

    private String staffId;
    private String deptId;
    private String code;
    private String name;
    private String birthDate;
    private String joinDate;
    private String outDate;
    private String email;
    private String telNumber;
    private String staffType;
    private String status;
    private String password;
    private String cardNo;

    private String quanlity = "0";
    private List<CustomerDTO> lstCustDTO;
    private List<CustomerStatusDTO> lstCustomers;

    public StaffDTO() {
    }

    public StaffDTO(String staffId, String deptId, String code, String name, String birthDate, String joinDate, String outDate, String email, String telNumber, String staffType, String status, String password, String cardNo) {
        this.staffId = staffId;
        this.deptId = deptId;
        this.code = code;
        this.name = name;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.outDate = outDate;
        this.email = email;
        this.telNumber = telNumber;
        this.staffType = staffType;
        this.status = status;
        this.password = password;
        this.cardNo = cardNo;
    }

    public CustomerStatusDTO convert2CustomerStatus() {
        return new CustomerStatusDTO(staffId, code, name);
    }

    public StaffDTO copy() {
        return new StaffDTO(staffId, deptId, code, name, birthDate, joinDate, outDate, email, telNumber, staffType, status, password, cardNo);
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public List<CustomerStatusDTO> getLstCustomers() {
        return lstCustomers;
    }

    public void setLstCustomers(List<CustomerStatusDTO> lstCustomers) {
        this.lstCustomers = lstCustomers;
    }

    public List<CustomerDTO> getLstCustDTO() {
        return lstCustDTO;
    }

    public void setLstCustDTO(List<CustomerDTO> lstCustDTO) {
        this.lstCustDTO = lstCustDTO;
    }

    public String getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(String quanlity) {
        this.quanlity = quanlity;
    }

}
