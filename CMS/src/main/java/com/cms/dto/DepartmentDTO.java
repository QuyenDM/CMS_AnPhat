/**
 * @(#)DepartmentForm.java , Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.dto;

import com.cms.common.basedto.BaseDTO;

/**
 * @author ngocnd6
 * @version 1.0
 * @since 08-Apr-15 10:49 AM
 */

public class DepartmentDTO extends BaseDTO {

    //Fields
    private String deptId;
    private String code;
    private String name;
    private String parentDeptId;
    private String parentDeptName;//map name
    private String address;
    private String tel;
    private String fax;
    private String deptType;
    private String contactName;
    private String contactTitle;
    private String telNumber;
    private String email;
    private String description;
    private String status;
    private String statusName;//map status name
    private String deptPath;
    private String createDate;

    //Constructor
    public DepartmentDTO() {
        setDefaultSortField("code");
    }

    public DepartmentDTO(String deptId, String code, String name, String parentDeptId, String address, String tel, String fax, String deptType, String contactName, String contactTitle, String telNumber, String email, String description, String status, String deptPath, String createDate) {
        this.deptId = deptId;
        this.code = code;
        this.name = name;
        this.parentDeptId = parentDeptId;
        this.address = address;
        this.tel = tel;
        this.fax = fax;
        this.deptType = deptType;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.telNumber = telNumber;
        this.email = email;
        this.description = description;
        this.status = status;
        this.deptPath = deptPath;
        this.createDate = createDate;
    }

    public DepartmentDTO copy() {
        return new DepartmentDTO(deptId, code, name, parentDeptId, address, tel, fax, deptType, contactName, contactTitle, telNumber, email, description, status, deptPath, createDate);
    }
    //Getters and setters

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptId() {
        return deptId;
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

    public void setParentDeptId(String parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public String getParentDeptId() {
        return parentDeptId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFax() {
        return fax;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDeptPath(String deptPath) {
        this.deptPath = deptPath;
    }

    public String getDeptPath() {
        return deptPath;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getParentDeptName() {
        return parentDeptName;
    }

    public void setParentDeptName(String parentDeptName) {
        this.parentDeptName = parentDeptName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
