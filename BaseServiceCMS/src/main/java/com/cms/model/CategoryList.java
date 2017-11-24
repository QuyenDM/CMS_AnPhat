/**
 * @(#)CategoryListBO.java 8/23/2016 11:02 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.model;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.cms.dto.CategoryListDTO;
import com.vfw5.base.model.BaseFWModel;
import com.vfw5.base.utils.DateTimeUtils;
import com.vfw5.base.utils.ParamUtils;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/23/2016 11:02 PM
 */
@Entity
@Table(name = "CATEGORY_LIST")
public class CategoryList extends BaseFWModel {

    //Fields
    private Long id;
    private String code;
    private String name;
    private Date receivedDate;
    private Date endDate;
    private String description;
    private String creator;
    private String service;
    private Long custQuantity;
    private Long dividedQuantity;

    //Constructors
    public CategoryList() {
        setColId("id");
        setColName("code");
        setUniqueColumn(new String[]{"id"});
    }

    public CategoryList(Long id) {
        this.id = id;
    }

    public CategoryList(Long id, String code, String name, Date receivedDate, Date endDate, String description, String creator, Long custQuantity, Long dividedQuanlity, String service) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.receivedDate = receivedDate;
        this.endDate = endDate;
        this.description = description;
        this.creator = creator;
        this.custQuantity = custQuantity;
        this.dividedQuantity = dividedQuanlity;
        this.service = service;
    }

    //Getters and Setters
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CATEGORY_LIST_SEQ")
            }
    )
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "CODE")
    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    @Column(name = "NAME")
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "RECEIVED_DATE", nullable = false)
    public Date getReceivedDate() {
        return this.receivedDate;
    }

    public void setReceivedDate(final Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "END_DATE")
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Column(name = "CREATOR", nullable = false)
    public String getCreator() {
        return this.creator;
    }

    public void setCreator(final String creator) {
        this.creator = creator;
    }

    @Column(name = "CUST_QUANTITY")
    public Long getCustQuantity() {
        return this.custQuantity;
    }

    public void setCustQuantity(final Long custQuantity) {
        this.custQuantity = custQuantity;
    }

    @Column(name = "DEVIDED_QUANTITY")
    public Long getDividedQuantity() {
        return this.dividedQuantity;
    }

    public void setDividedQuantity(final Long dividedQuantity) {
        this.dividedQuantity = dividedQuantity;
    }

    @Column(name = "SERVICE")
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public CategoryListDTO toDTO() {
        CategoryListDTO dto = new CategoryListDTO(
                id == null ? null : id.toString(), code, name,
                receivedDate == null ? null : DateTimeUtils.convertDateToString(receivedDate, ParamUtils.ddMMyyyy),
                endDate == null ? null : DateTimeUtils.convertDateToString(endDate, ParamUtils.ddMMyyyy),
                description, creator, custQuantity == null ? null : custQuantity.toString(), dividedQuantity == null ? null : dividedQuantity.toString(),
                service
        );
        return dto;
    }
}
