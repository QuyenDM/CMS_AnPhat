/**
 * @(#)MapStaffRolesBO.java 8/14/2016 2:37 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.model;

import com.cms.dto.MapStaffRolesDTO;
import com.vfw5.base.model.BaseFWModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/14/2016 2:37 PM
 */
@Entity
@Table(name = "MAP_STAFF_ROLES")
public class MapStaffRoles extends BaseFWModel {

    //Fields
    private Long mapId;
    private Long staffId;
    private Long roleId;
    private String status;

    //Constructors
    public MapStaffRoles() {
        setColId("mapId");
        setColName("staffId");
        setUniqueColumn(new String[]{"mapId"});
    }

    public MapStaffRoles(Long mapId) {
        this.mapId = mapId;
    }

    public MapStaffRoles(Long mapId, Long staffId, Long roleId, String status) {
        this.mapId = mapId;
        this.staffId = staffId;
        this.roleId = roleId;
        this.status = status;
    }

    //Getters and Setters
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "MAP_STAFF_ROLE_SEQ")
            }
    )
    @Column(name = "MAP_ID", nullable = false)
    public Long getMapId() {
        return this.mapId;
    }

    public void setMapId(final Long mapId) {
        this.mapId = mapId;
    }

    @Id

    @Column(name = "STAFF_ID", nullable = false)
    public Long getStaffId() {
        return this.staffId;
    }

    public void setStaffId(final Long staffId) {
        this.staffId = staffId;
    }

    @Column(name = "ROLE_ID", nullable = false)
    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(final Long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "STATUS", nullable = false)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @Override
    public MapStaffRolesDTO toDTO() {
        MapStaffRolesDTO dto = new MapStaffRolesDTO(
                mapId == null ? null : mapId.toString(), staffId == null ? null : staffId.toString(), roleId == null ? null : roleId.toString(), status
        );
        return dto;
    }
}
