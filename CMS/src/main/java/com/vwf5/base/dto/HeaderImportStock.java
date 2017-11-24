/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vwf5.base.dto;

/**
 *
 * @author TruongBx3
 */
public class HeaderImportStock extends HeaderTemplateImportStock {

    String cusName;
    String transporter;
    String phoneNumber;
    String inputer;
    String inputDate;
    String note;

    public HeaderImportStock() {
    }

    public HeaderImportStock(String cusName, String transporter, String phoneNumber, String inputer, String inputDate, String note) {
        this.cusName = cusName;
        this.transporter = transporter;
        this.phoneNumber = phoneNumber;
        this.inputer = inputer;
        this.inputDate = inputDate;
        this.note = note;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getTransporter() {
        return transporter;
    }

    public void setTransporter(String transporter) {
        this.transporter = transporter;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInputer() {
        return inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
