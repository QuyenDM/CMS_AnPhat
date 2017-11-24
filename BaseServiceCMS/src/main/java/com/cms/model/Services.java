package com.cms.model;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.vfw5.base.model.BaseFWModel;
import com.cms.dto.ServicesDTO;
import com.vfw5.base.utils.DateTimeUtils;
import com.vfw5.base.utils.ParamUtils;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 06/08/2016 10:03:59
 */
@Entity
@Table(name = "SERVICES")
public class Services extends BaseFWModel {

    //Fields
    private Long serviceId;
    private String applyAreaType;
    private String code;
    private String description;
    private Date expiryDate;
    private Date issueDate;
    private String name;
    private String orderServiceType;
    private String serviceGroup;
    private String status;
    private String type;

    public Services() {
        setColId("serviceId");
        setColName("code");
    }

    public Services(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Services(Long serviceId, String applyAreaType, String code, String description, Date expiryDate, Date issueDate, String name, String orderServiceType, String serviceGroup, String status, String type) {
        this.serviceId = serviceId;
        this.applyAreaType = applyAreaType;
        this.code = code;
        this.description = description;
        this.expiryDate = expiryDate;
        this.issueDate = issueDate;
        this.name = name;
        this.orderServiceType = orderServiceType;
        this.serviceGroup = serviceGroup;
        this.status = status;
        this.type = type;
    }

    //Getters and Setters
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "SERVICES_SEQ")
            }
    )
    @Column(name = "SERVICE_ID", nullable = false)
    public Long getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(final Long serviceId) {
        this.serviceId = serviceId;
    }

    @Column(name = "APPLY_AREA_TYPE")
    public String getApplyAreaType() {
        return this.applyAreaType;
    }

    public void setApplyAreaType(final String applyAreaType) {
        this.applyAreaType = applyAreaType;
    }

    @Column(name = "CODE")
    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Column(name = "EXPIRY_DATE")
    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(final Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Column(name = "ISSUE_DATE")
    public Date getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate(final Date issueDate) {
        this.issueDate = issueDate;
    }

    @Column(name = "NAME")
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Column(name = "ORDER_SERVICE_TYPE")
    public String getOrderServiceType() {
        return this.orderServiceType;
    }

    public void setOrderServiceType(final String orderServiceType) {
        this.orderServiceType = orderServiceType;
    }

    @Column(name = "SERVICE_GROUP")
    public String getServiceGroup() {
        return this.serviceGroup;
    }

    public void setServiceGroup(final String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    @Column(name = "STATUS", nullable = false)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @Column(name = "TYPE")
    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public ServicesDTO toDTO() {
        ServicesDTO dto = new ServicesDTO(
                serviceId == null ? null : serviceId.toString(),
                applyAreaType,
                code,
                description,
                expiryDate == null ? null : DateTimeUtils.convertDateToString(expiryDate, ParamUtils.ddMMyyyy),
                issueDate == null ? null : DateTimeUtils.convertDateToString(issueDate, ParamUtils.ddMMyyyy),
                name,
                orderServiceType,
                serviceGroup,
                status,
                type
        );
        return dto;
    }
}
