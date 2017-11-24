/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.login.dto;

import com.cms.common.basedto.BaseDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:27 AM
 */
public class RolesDTO extends BaseDTO {
    //Fields

    private String roleId;
    private String code;
    private String name;
    private String description;
    private String status;

    //Constructor
    public RolesDTO() {
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

}
