/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.login.dto;

import com.cms.common.basedto.BaseDTO;


/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/14/2016 2:37 PM
 */
public class MapStaffRolesDTO extends BaseDTO {
    //Fields

    private String mapId;
    private String staffId;
    private String roleId;
    private String status;

    //Constructor
    public MapStaffRolesDTO() {
    }

    public MapStaffRolesDTO(String mapId, String staffId, String roleId, String status) {
        this.mapId = mapId;
        this.staffId = staffId;
        this.roleId = roleId;
        this.status = status;
    }
    //Getters and setters

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public String getMapId() {
        return mapId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
