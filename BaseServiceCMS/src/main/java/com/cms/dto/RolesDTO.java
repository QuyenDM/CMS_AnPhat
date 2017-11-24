/**
 * @(#)RolesForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.dto;

import com.vfw5.base.dto.BaseFWDTO;
import com.cms.model.Roles;
import com.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:27 AM
 */
@XmlRootElement(name = "Roles")
public class RolesDTO extends BaseFWDTO<Roles> {
    //Fields

    private String roleId;
    private String code;
    private String name;
    private String description;
    private String status;
    private String mapId;

    //Constructor
    public RolesDTO() {
        setDefaultSortField("code");
    }

    public RolesDTO(String roleId, String code, String name, String description, String status) {
        this.roleId = roleId;
        this.code = code;
        this.name = name;
        this.description = description;
        this.status = status;
    }
    //Getters and setters

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
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

    @Override
    public Roles toModel() {
        Roles model = new Roles(
                !StringUtils.validString(roleId) ? null
                : Long.valueOf(roleId),
                code,
                name,
                description,
                status);
        return model;
    }

    @Override
    public Long getFWModelId() {
        return !StringUtils.validString(roleId) ? null : Long.valueOf(roleId);
    }

    @Override
    public String catchName() {
        return getCode().toString();
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

}
