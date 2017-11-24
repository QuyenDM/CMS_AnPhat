/**
 * @(#)DepartmentForm.java , Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.dto;

import com.vfw5.base.dto.BaseFWDTO;
import com.vfw5.base.utils.DateTimeUtils;
import com.cms.model.Department;
import com.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 22-Apr-15 6:02 PM
 */
@XmlRootElement(name = "Department")
public class DepartmentDTO extends BaseFWDTO<Department> {

    //Fields
    private String deptId;
    private String code;
    private String name;
    private String parentDeptId;
    private String address;
    private String telNumber;
    private String fax;
    private String deptType;
    private String contactName;
    private String contactTitle;
    private String email;
    private String description;
    private String status;
    private String deptPath;
    private String createDate;

    //Constructor
    public DepartmentDTO() {
        setDefaultSortField("code");
    }

    public DepartmentDTO(String deptId, String code, String name, String parentDeptId, String address, String telNumber, String fax, String deptType, String contactName, String contactTitle, String email, String description, String status, String deptPath, String createDate) {
        this.deptId = deptId;
        this.code = code;
        this.name = name;
        this.parentDeptId = parentDeptId;
        this.address = address;
        this.telNumber = telNumber;
        this.fax = fax;
        this.deptType = deptType;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.email = email;
        this.description = description;
        this.status = status;
        this.deptPath = deptPath;
        this.createDate = createDate;
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

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getTelNumber() {
        return telNumber;
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

    @Override
    public Department toModel() {
        try {
            Department model = new Department(
                    !StringUtils.validString(deptId) ? null
                    : Long.valueOf(deptId),
                    code,
                    name,
                    !StringUtils.validString(parentDeptId) ? null
                    : Long.valueOf(parentDeptId),
                    address,
                    telNumber,
                    fax,
                    deptType,
                    contactName,
                    contactTitle,
                    email,
                    description,
                    status,
                    deptPath,
                    !StringUtils.validString(createDate) ? null
                    : DateTimeUtils.convertStringToDate(createDate));
            model.setUserNameLogging(userNameLogging);
            return model;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long getFWModelId() {
        return !StringUtils.validString(deptId) ? null : Long.valueOf(deptId);
    }

    @Override
    public String catchName() {
        return getCode().toString();
    }

}
