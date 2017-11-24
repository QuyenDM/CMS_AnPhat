/**
 * @(#)RoleObjectsForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.dto;

import com.vfw5.base.dto.BaseFWDTO;
import com.cms.model.RoleObjects;
import com.vfw5.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:32 AM
 */
@XmlRootElement(name = "RoleObjects")
public class RoleObjectsDTO extends BaseFWDTO<RoleObjects> {
    //Fields

    private String id;
    private String roleId;
    private String roleIdName;
    private String objectId;
    private String appId;

    //Constructor
    public RoleObjectsDTO() {
        setDefaultSortField("roleId");
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

    @Override
    public RoleObjects toModel() {
        RoleObjects model = new RoleObjects(
                !StringUtils.validString(id) ? null
                : Long.valueOf(id),
                !StringUtils.validString(roleId) ? null
                : Long.valueOf(roleId),
                !StringUtils.validString(objectId) ? null
                : Long.valueOf(objectId),
                !StringUtils.validString(appId) ? null
                : Long.valueOf(appId));
        return model;
    }

    @Override
    public Long getFWModelId() {
        return !StringUtils.validString(id) ? null : Long.valueOf(id);
    }

    @Override
    public String catchName() {
        return getRoleId().toString();
    }

}
