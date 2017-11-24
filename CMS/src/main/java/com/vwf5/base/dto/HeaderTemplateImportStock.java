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
public class HeaderTemplateImportStock {

    String stockName;
    String numberTemplate = "Mẫu số: 02-VT";
    String code;
    String command;

    public HeaderTemplateImportStock() {
    }

    public HeaderTemplateImportStock(String stockName, String code, String command, String numberTemplate) {
        this.stockName = stockName;
        this.code = code;
        this.command = command;
        this.numberTemplate = numberTemplate;
    }

    public HeaderTemplateImportStock(String stockName, String code, String command) {
        this.stockName = stockName;
        this.code = code;
        this.command = command;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getNumberTemplate() {
        return numberTemplate;
    }

    public void setNumberTemplate(String numberTemplate) {
        this.numberTemplate = numberTemplate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
