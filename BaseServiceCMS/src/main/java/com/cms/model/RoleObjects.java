/**
 * @(#)RoleObjectsBO.java 7/8/2016 9:32 AM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.model;

import com.vfw5.base.model.BaseFWModel;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.cms.dto.RoleObjectsDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:32 AM
 */
@Entity
@Table(name = "ROLE_OBJECTS")
public class RoleObjects extends BaseFWModel {

    //Fields
    private Long id;
    private Long roleId;
    private Long objectId;
    private Long appId;

    //Constructors
    public RoleObjects() {
        setColId("id");
        setColName("roleId");
        setUniqueColumn(new String[]{"id"});
    }

    public RoleObjects(Long id) {
        this.id = id;
    }

    public RoleObjects(Long id, Long roleId, Long objectId, Long appId) {
        this.id = id;
        this.roleId = roleId;
        this.objectId = objectId;
        this.appId = appId;
    }

    //Getters and Setters
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "ROLE_OBJECTS_SEQ")
            }
    )
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "ROLE_ID", nullable = false)
    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(final Long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "OBJECT_ID", nullable = false)
    public Long getObjectId() {
        return this.objectId;
    }

    public void setObjectId(final Long objectId) {
        this.objectId = objectId;
    }

    @Column(name = "APP_ID", nullable = false)
    public Long getAppId() {
        return this.appId;
    }

    public void setAppId(final Long appId) {
        this.appId = appId;
    }

    @Override
    public RoleObjectsDTO toDTO() {
        RoleObjectsDTO dto = new RoleObjectsDTO(
                id == null ? null : id.toString(), roleId == null ? null : roleId.toString(), objectId == null ? null : objectId.toString(), appId == null ? null : appId.toString()
        );
        return dto;
    }
}
