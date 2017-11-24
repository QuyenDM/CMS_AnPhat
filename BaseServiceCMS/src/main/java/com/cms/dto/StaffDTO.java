/**
 * @(#)StaffForm.java , Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.dto;

import com.vfw5.base.dto.BaseFWDTO;
import com.vfw5.base.utils.DateTimeUtils;
import com.cms.model.Staff;
import com.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 1:28 PM
 */
@XmlRootElement(name = "Staff")
public class StaffDTO extends BaseFWDTO<Staff> {

    //Fields
    private String staffId;
    private String code;
    private String name;
    private String birthDate;
    private String joinDate;
    private String outDate;
    private String email;
    private String telNumber;
    private String deptId;
    private String staffType;
    private String status;
    private String password;
    private String cardNo;
    private String quanlity;

    //Constructor
    public StaffDTO() {
//        setDefaultSortField("code");
    }

    public StaffDTO(String staffId, String code, String name, String birthDate, String joinDate, String outDate, String email, String telNumber, String deptId, String staffType, String status, String password, String cardNo) {
        this.staffId = staffId;
        this.code = code;
        this.name = name;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.outDate = outDate;
        this.email = email;
        this.telNumber = telNumber;
        this.deptId = deptId;
        this.staffType = staffType;
        this.status = status;
        this.password = password;
        this.cardNo = cardNo;
    }

    //Getters and setters
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
//
//    public String getOtherAccount() {
//        return otherAccount;
//    }
//
//    public void setOtherAccount(String otherAccount) {
//        this.otherAccount = otherAccount;
//    }

    @Override
    public Staff toModel() {
        try {
            Staff model = new Staff(
                    !StringUtils.validString(staffId) ? null
                    : Long.valueOf(staffId),
                    !StringUtils.validString(deptId) ? null
                    : Long.valueOf(deptId),
                    code,
                    name,
                    !StringUtils.validString(birthDate) ? null
                    : DateTimeUtils.convertStringToDate(birthDate),
                    !StringUtils.validString(joinDate) ? null
                    : DateTimeUtils.convertStringToDate(joinDate),
                    !StringUtils.validString(outDate) ? null
                    : DateTimeUtils.convertStringToDate(outDate),
                    telNumber,
                    staffType,
                    email,
                    status,
                    password,
                    cardNo);
            model.setUserNameLogging(userNameLogging);
            return model;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long getFWModelId() {
        return !StringUtils.validString(staffId) ? null : Long.valueOf(staffId);
    }

    @Override
    public String catchName() {
        return getCode().toString();
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

    public String getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(String quanlity) {
        this.quanlity = quanlity;
    }

}
