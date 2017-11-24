/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.login.dto;

import com.cms.common.basedto.BaseDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:32 AM
 */
public class RoleObjectsDTO extends BaseDTO {
    //Fields

    private String id;
    private String roleId;
    private String roleIdName;
    private String objectId;
    private String appId;

    //Constructor
    public RoleObjectsDTO() {
        this.appId = "1";
    }

    public RoleObjectsDTO(String id, String roleId, String objectId, String appId) {
        this.id = id;
        this.roleId = roleId;
        this.objectId = objectId;
        this.appId = appId;
    }
    //Getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleIdName(String roleIdName) {
        this.roleIdName = roleIdName;
    }

    public String getRoleIdName() {
        return roleIdName;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppId() {
        return appId;
    }

}
