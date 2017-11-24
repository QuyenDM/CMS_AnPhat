/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.dto;

/**
 *
 * @author QuyenDM
 */
public class CustomerUserInfoDTO {

    private String custId;
    private String custCode;
    private String custName;
    private String custType;
    private String userCode;
    private String userName;
    private String custUserType;
    private String userEmail;
    private String userTelNumber;

    public CustomerUserInfoDTO() {
    }

    public CustomerUserInfoDTO(String custId, String custCode, String custName, String custType, String userCode, String userName, String custUserType, String userEmail, String userTelNumber) {
        this.custId = custId;
        this.custCode = custCode;
        this.custName = custName;
        this.custType = custType;
        this.userCode = userCode;
        this.userName = userName;
        this.custUserType = custUserType;
        this.userEmail = userEmail;
        this.userTelNumber = userTelNumber;
    }

    
    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCustUserType() {
        return custUserType;
    }

    public void setCustUserType(String custUserType) {
        this.custUserType = custUserType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserTelNumber() {
        return userTelNumber;
    }

    public void setUserTelNumber(String userTelNumber) {
        this.userTelNumber = userTelNumber;
    }

}
