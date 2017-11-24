/**
 * @(#)ObjectsBO.java 7/8/2016 9:18 AM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.model;

import com.vfw5.base.model.BaseFWModel;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.cms.dto.ObjectsDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 7/8/2016 9:18 AM
 */
@Entity
@Table(name = "OBJECTS")
public class Objects extends BaseFWModel {

    //Fields
    private Long objectId;
    private Long appId;
    private String code;
    private String name;
    private String url;
    private String description;
    private String objectType;
    private String status;

    //Constructors
    public Objects() {
        setColId("objectId");
        setColName("code");
        setUniqueColumn(new String[]{"code", "name"});
    }

    public Objects(Long objectId) {
        this.objectId = objectId;
    }

    public Objects(Long objectId, Long appId, String code, String name, String url, String description, String objectType, String status) {
        this.objectId = objectId;
        this.appId = appId;
        this.code = code;
        this.name = name;
        this.url = url;
        this.description = description;
        this.objectType = objectType;
        this.status = status;
    }

    //Getters and Setters
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "OBJECTS_SEQ")
            }
    )
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

    @Column(name = "URL")
    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Column(name = "OBJECT_TYPE")
    public String getObjectType() {
        return this.objectType;
    }

    public void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

    @Column(name = "STATUS", nullable = false)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @Override
    public ObjectsDTO toDTO() {
        ObjectsDTO dto = new ObjectsDTO(
                objectId == null ? null : objectId.toString(), appId == null ? null : appId.toString(), code, name, url, description, objectType, status
        );
        return dto;
    }
}
