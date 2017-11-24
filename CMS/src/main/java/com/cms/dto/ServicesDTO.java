package com.cms.dto;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 06/08/2016 10:03:59
 */
public class ServicesDTO {
    //Fields

    private String serviceId;
    private String applyAreaType;
    private String code;
    private String description;
    private String expiryDate;
    private String issueDate;
    private String name;
    private String orderServiceType;
    private String serviceGroup;
    private String status;
    private String type;
    //Constructor

    public ServicesDTO() {

    }

    public ServicesDTO(String serviceId, String applyAreaType, String code, String description, String expiryDate, String issueDate, String name, String orderServiceType, String serviceGroup, String status, String type) {
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

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(final String serviceId) {
        this.serviceId = serviceId;
    }

    public String getApplyAreaType() {
        return this.applyAreaType;
    }

    public void setApplyAreaType(final String applyAreaType) {
        this.applyAreaType = applyAreaType;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(final String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate(final String issueDate) {
        this.issueDate = issueDate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getOrderServiceType() {
        return this.orderServiceType;
    }

    public void setOrderServiceType(final String orderServiceType) {
        this.orderServiceType = orderServiceType;
    }

    public String getServiceGroup() {
        return this.serviceGroup;
    }

    public void setServiceGroup(final String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

}
