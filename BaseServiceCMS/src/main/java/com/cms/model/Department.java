/**
 * @(#)DepartmentBO.java 22-Apr-15 6:02 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.model;

import com.vfw5.base.model.BaseFWModel;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.cms.dto.DepartmentDTO;
import com.vfw5.base.utils.DateTimeUtils;
import com.vfw5.base.utils.ParamUtils;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 22-Apr-15 6:02 PM
 */
@Entity
@Table(name = "DEPARTMENT")
public class Department extends BaseFWModel {

    //Fields
    private Long deptId;
    private String code;
    private String name;
    private Long parentDeptId;
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
    private Date createDate;

    //Constructors
    public Department() {
        setColId("deptId");
        setColName("code");
        setUniqueColumn(new String[]{"code"});
    }

    public Department(Long deptId) {
        this.deptId = deptId;
    }

    public Department(Long deptId, String code, String name, Long parentDeptId, String address, String telNumber, String fax, String deptType, String contactName, String contactTitle, String email, String description, String status, String deptPath, Date createDate) {
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

    //Getters and Setters
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "DEPARTMENT_SEQ")
            }
    )
    @Column(name = "DEPT_ID", unique = true, nullable = false)
    public Long getDeptId() {
        return this.deptId;
    }

    public void setDeptId(final Long deptId) {
        this.deptId = deptId;
    }

    @Column(name = "CODE", unique = true, nullable = false)
    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Column(name = "PARENT_DEPT_ID")
    public Long getParentDeptId() {
        return this.parentDeptId;
    }

    public void setParentDeptId(final Long parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    @Column(name = "TEL_NUMBER")
    public String getTelNumber() {
        return this.telNumber;
    }

    public void setTelNumber(final String telNumber) {
        this.telNumber = telNumber;
    }

    @Column(name = "FAX")
    public String getFax() {
        return this.fax;
    }

    public void setFax(final String fax) {
        this.fax = fax;
    }

    @Column(name = "DEPT_TYPE")
    public String getDeptType() {
        return this.deptType;
    }

    public void setDeptType(final String deptType) {
        this.deptType = deptType;
    }

    @Column(name = "CONTACT_NAME")
    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(final String contactName) {
        this.contactName = contactName;
    }

    @Column(name = "CONTACT_TITLE")
    public String getContactTitle() {
        return this.contactTitle;
    }

    public void setContactTitle(final String contactTitle) {
        this.contactTitle = contactTitle;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @Column(name = "DEPT_PATH")
    public String getDeptPath() {
        return this.deptPath;
    }

    public void setDeptPath(final String deptPath) {
        this.deptPath = deptPath;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(final Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public DepartmentDTO toDTO() {
        DepartmentDTO dto = new DepartmentDTO(
                deptId == null ? null : deptId.toString(), code, name, parentDeptId == null ? null : parentDeptId.toString(), address, telNumber, fax, deptType, contactName, contactTitle, email, description, status, deptPath, createDate == null ? null : DateTimeUtils.convertDateToString(createDate, ParamUtils.ddMMyyyy)
        );
        return dto;
    }
}
