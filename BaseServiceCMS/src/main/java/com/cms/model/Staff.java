/**
 * @(#)StaffBO.java 16-Apr-15 1:28 PM, Copyright 2011 Viettel Telecom. All
 * rights reserved VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.model;

import com.vfw5.base.model.BaseFWModel;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.cms.dto.StaffDTO;
import com.vfw5.base.utils.DateTimeUtils;
import com.vfw5.base.utils.ParamUtils;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 1:28 PM
 */
@Entity
@Table(name = "STAFF")
public class Staff extends BaseFWModel {

    //Fields
    private Long staffId;
    private Long deptId;
    private String code;
    private String name;
    private Date birthDate;
    private Date joinDate;
    private Date outDate;
    private String telNumber;
    private String staffType;
    private String email;
    private String status;
    private String password;
    private String cardNo;

    //Constructors
    public Staff() {
        setColId("staffId");
        setColName("code");
        setUniqueColumn(new String[]{"code"});
    }

    public Staff(Long staffId) {
        this.staffId = staffId;
    }

    public Staff(Long staffId, Long deptId, 
            String code, String name, Date birthDate, Date joinDate, Date outDate,
            String telNumber, String staffType, String email, String status, 
            String password, String cardNo) {
        this.staffId = staffId;
        this.code = code;
        this.name = name;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.outDate = outDate;
        this.telNumber = telNumber;
        this.deptId = deptId;
        this.staffType = staffType;
        this.email = email;
        this.status = status;
        this.password = password;
        this.cardNo = cardNo;
    }    

    //Getters and Setters
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "STAFF_SEQ")
            }
    )
    @Column(name = "STAFF_ID", unique = true, nullable = false)
    public Long getStaffId() {
        return this.staffId;
    }

    public void setStaffId(final Long staffId) {
        this.staffId = staffId;
    }

    @Column(name = "CODE", unique = true)
    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    @Column(name = "NAME")
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "BIRTH_DATE")
    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "JOIN_DATE")
    public Date getJoinDate() {
        return this.joinDate;
    }

    public void setJoinDate(final Date joinDate) {
        this.joinDate = joinDate;
    }
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "OUT_DATE")
    public Date getOutDate() {
        return this.outDate;
    }

    public void setOutDate(final Date outDate) {
        this.outDate = outDate;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Column(name = "TEL_NUMBER")
    public String getTelNumber() {
        return this.telNumber;
    }

    public void setTelNumber(final String telNumber) {
        this.telNumber = telNumber;
    }

    @Column(name = "DEPT_ID", nullable = false)
    public Long getDeptId() {
        return this.deptId;
    }

    public void setDeptId(final Long deptId) {
        this.deptId = deptId;
    }

    @Column(name = "STAFF_TYPE")
    public String getStaffType() {
        return this.staffType;
    }

    public void setStaffType(final String staffType) {
        this.staffType = staffType;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Column(name = "CARD_NO")
    public String getOtherAccount() {
        return this.cardNo;
    }

    public void setOtherAccount(final String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public StaffDTO toDTO() {
        StaffDTO dto = new StaffDTO(
                staffId == null ? null : staffId.toString(), code, name, 
                birthDate == null ? null : DateTimeUtils.convertDateToString(birthDate, ParamUtils.ddMMyyyy), 
                joinDate == null ? null : DateTimeUtils.convertDateToString(joinDate, ParamUtils.ddMMyyyy), 
                outDate == null ? null : DateTimeUtils.convertDateToString(outDate, ParamUtils.ddMMyyyy), 
                email, telNumber, deptId == null ? null : deptId.toString(),
                staffType, status, password, cardNo
        );
        return dto;
    }
}
