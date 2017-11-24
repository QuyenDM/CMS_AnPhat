/**
 * @(#)CategoryListForm.java ,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.dto;

import com.vfw5.base.utils.DateTimeUtils;
import com.vfw5.base.utils.StringUtils;
import com.cms.model.CategoryList;
import com.vfw5.base.dto.BaseFWDTO;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/23/2016 11:02 PM
 */
@XmlRootElement(name = "CategoryList")
public class CategoryListDTO extends BaseFWDTO<CategoryList> {
    //Fields

    private String id;
    private String code;
    private String name;
    private String receivedDate;
    private String endDate;
    private String description;
    private String creator;
    private String custQuantity;
    private String dividedQuantity;
    private String service;

    //Constructor
    public CategoryListDTO() {
        setDefaultSortField("code");
    }

    public CategoryListDTO(String id, String code, String name, String receivedDate,
            String endDate, String description, String creator, String custQuantity, String devidedQuantity, String service) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.receivedDate = receivedDate;
        this.endDate = endDate;
        this.description = description;
        this.creator = creator;
        this.custQuantity = custQuantity;
        this.dividedQuantity = devidedQuantity;
        this.service = service;
    }
    //Getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    public void setCustQuantity(String custQuantity) {
        this.custQuantity = custQuantity;
    }

    public String getCustQuantity() {
        return custQuantity;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public CategoryList toModel() {
        try {
            CategoryList model = new CategoryList(
                    !StringUtils.validString(id) ? null
                    : Long.valueOf(id),
                    code,
                    name,
                    !StringUtils.validString(receivedDate) ? null
                    : DateTimeUtils.convertStringToDate(receivedDate),
                    !StringUtils.validString(endDate) ? null
                    : DateTimeUtils.convertStringToDate(endDate),
                    description,
                    creator,
                    !StringUtils.validString(custQuantity) ? null
                    : Long.valueOf(custQuantity),
                    !StringUtils.validString(dividedQuantity) ? null
                    : Long.valueOf(dividedQuantity),
                    service);
            return model;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long getFWModelId() {
        return !StringUtils.validString(id) ? null : Long.valueOf(id);
    }

    @Override
    public String catchName() {
        return getCode().toString();
    }

    public String getDividedQuantity() {
        return dividedQuantity;
    }

    public void setDividedQuantity(String dividedQuantity) {
        this.dividedQuantity = dividedQuantity;
    }

}
