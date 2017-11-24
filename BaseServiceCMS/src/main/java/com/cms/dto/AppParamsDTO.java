/**
 * @(#)AppParamsForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.dto;

import com.vfw5.base.dto.BaseFWDTO;
import com.cms.model.AppParams;
import com.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 16-Apr-15 11:11 AM
 */
@XmlRootElement(name = "AppParams")
public class AppParamsDTO extends BaseFWDTO<AppParams> {
    //Fields

    private String parId;
    private String parType;
    private String parCode;
    private String parName;
    private String description;
    private String status;
    private String parOrder;

    //Constructor
    public AppParamsDTO() {
        setDefaultSortField("parOrder");
    }

    public AppParamsDTO(String parId, String parType, String parCode, String parName, String description, String status, String parOrder) {
        this.parId = parId;
        this.parType = parType;
        this.parCode = parCode;
        this.parName = parName;
        this.description = description;
        this.status = status;
        this.parOrder = parOrder;
    }
    //Getters and setters

    public void setParId(String parId) {
        this.parId = parId;
    }

    public String getParId() {
        return parId;
    }

    public void setParType(String parType) {
        this.parType = parType;
    }

    public String getParType() {
        return parType;
    }

    public void setParCode(String parCode) {
        this.parCode = parCode;
    }

    public String getParCode() {
        return parCode;
    }

    public void setParName(String parName) {
        this.parName = parName;
    }

    public String getParName() {
        return parName;
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

    public void setParOrder(String parOrder) {
        this.parOrder = parOrder;
    }

    public String getParOrder() {
        return parOrder;
    }

    @Override
    public AppParams toModel() {
        AppParams model = new AppParams(
                !StringUtils.validString(parId) ? null
                : Long.valueOf(parId),
                parType,
                parCode,
                parName,
                description,
                status,
                !StringUtils.validString(parOrder) ? null
                : Long.valueOf(parOrder));
        model.setUserNameLogging(userNameLogging);
        return model;
    }

    @Override
    public Long getFWModelId() {
        return !StringUtils.validString(parId) ? null : Long.valueOf(parId);
    }

    @Override
    public String catchName() {
        return getParOrder().toString();
    }
}
