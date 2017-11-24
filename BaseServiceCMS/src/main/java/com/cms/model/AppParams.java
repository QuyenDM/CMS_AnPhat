/**
 * @(#)AppParamsBO.java 16-Apr-15 11:11 AM, Copyright 2011 Viettel Telecom. All
 * rights reserved VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.model;

import com.vfw5.base.model.BaseFWModel;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.cms.dto.AppParamsDTO;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
@Entity
@Table(name = "APP_PARAMS")
@SequenceGenerator(name="appParamsSeq", sequenceName="APP_PARAMS_SEQ", allocationSize=1)
public class AppParams extends BaseFWModel {

    //Fields
    private Long parId;
    private String parType;
    private String parCode;
    private String parName;
    private String description;
    private String status;
    private Long parOrder;

    //Constructors
    public AppParams() {
        setColId("parId");
        setColName("parOrder");
        setUniqueColumn(new String[]{"parType", "parCode"});
    }

    public AppParams(Long parId) {
        this.parId = parId;
    }

    public AppParams(Long parId, String parType, String parCode, String parName, String description, String status, Long parOrder) {
        this.parId = parId;
        this.parType = parType;
        this.parCode = parCode;
        this.parName = parName;
        this.description = description;
        this.status = status;
        this.parOrder = parOrder;
    }

    //Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appParamsSeq")
    @Column(name = "PAR_ID", unique = true, nullable = false)
    public Long getParId() {
        return this.parId;
    }

    public void setParId(final Long parId) {
        this.parId = parId;
    }

    @Column(name = "PAR_TYPE", nullable = false)
    public String getParType() {
        return this.parType;
    }

    public void setParType(final String parType) {
        this.parType = parType;
    }

    @Column(name = "PAR_CODE", nullable = false)
    public String getParCode() {
        return this.parCode;
    }

    public void setParCode(final String parCode) {
        this.parCode = parCode;
    }

    @Column(name = "PAR_NAME")
    public String getParName() {
        return this.parName;
    }

    public void setParName(final String parName) {
        this.parName = parName;
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

    @Column(name = "PAR_ORDER")
    public Long getParOrder() {
        return this.parOrder;
    }

    public void setParOrder(final Long parOrder) {
        this.parOrder = parOrder;
    }

    @Override
    public AppParamsDTO toDTO() {
        AppParamsDTO dto = new AppParamsDTO(
                parId == null ? null : parId.toString(), parType, parCode, parName, description, status, parOrder == null ? null : parOrder.toString()
        );
        return dto;
    }
}
