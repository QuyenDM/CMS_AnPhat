/**
 * @(#)MapStaffRolesForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.dto;

import com.cms.model.MapStaffRoles;
import com.vfw5.base.dto.BaseFWDTO;
import com.vfw5.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/14/2016 2:37 PM
 */
@XmlRootElement(name = "MapStaffRoles")
public class MapStaffRolesDTO extends BaseFWDTO<MapStaffRoles> {
    //Fields

    private String mapId;
    private String staffId;
    private String roleId;
    private String status;

    //Constructor
    public MapStaffRolesDTO() {
        setDefaultSortField("staffId");
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

    @Override
    public MapStaffRoles toModel() {
        MapStaffRoles model = new MapStaffRoles(
                !StringUtils.validString(mapId) ? null
                : Long.valueOf(mapId),
                !StringUtils.validString(staffId) ? null
                : Long.valueOf(staffId),
                !StringUtils.validString(roleId) ? null
                : Long.valueOf(roleId),
                status);
        return model;
    }

    @Override
    public Long getFWModelId() {
        return !StringUtils.validString(mapId) ? null : Long.valueOf(mapId);
    }

    @Override
    public String catchName() {
        return getStaffId().toString();
    }
}
