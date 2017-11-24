/**
 * @(#)RolesBO.java 7/8/2016 9:27 AM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.model;

import com.vfw5.base.model.BaseFWModel;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.cms.dto.RolesDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:27 AM
 */
@Entity
@Table(name = "ROLES")
public class Roles extends BaseFWModel {

    //Fields
    private Long roleId;
    private String code;
    private String name;
    private String description;
    private String status;

    //Constructors
    public Roles() {
        setColId("roleId");
        setColName("code");
        setUniqueColumn(new String[]{"code", "name"});
    }

    public Roles(Long roleId) {
        this.roleId = roleId;
    }

    public Roles(Long roleId, String code, String name, String description, String status) {
        this.roleId = roleId;
        this.code = code;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    //Getters and Setters
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "ROLES_SEQ")
            }
    )
    @Column(name = "ROLE_ID", unique = true, nullable = false)
    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(final Long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "CODE", nullable = false)
    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
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

    @Override
    public RolesDTO toDTO() {
        RolesDTO dto = new RolesDTO(
                roleId == null ? null : roleId.toString(), code, name, description, status
        );
        return dto;
    }
}
