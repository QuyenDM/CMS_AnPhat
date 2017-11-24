/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.dto;

/**
 *
 * @author quyen
 */
public class StatusQuantityDTO {
    private String status;
    private String statusName;
    private String quantity;
    private String mineName;

    public StatusQuantityDTO(String status, String statusName, String quantity, String mineName) {
        this.status = status;
        this.statusName = statusName;
        this.quantity = quantity;
        this.mineName = mineName;
    }

    public StatusQuantityDTO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMineName() {
        return mineName;
    }

    public void setMineName(String mineName) {
        this.mineName = mineName;
    }
}
