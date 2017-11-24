package com.cms.dto;

import com.cms.common.basedto.BaseDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 12/08/2016 00:03:43
 */
public class RolesDTO extends BaseDTO {
    //Fields

    private String roleId;
    private String code;
    private String name;
    private String description;
    private String status;
    private String mapId;
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
    //Getters and Setters

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(final String roleId) {
        this.roleId = roleId;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

}
