/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.component;

/**
 *
 * @author vtsoft
 */
public class ComBoboxCommon {

    String itemCode;
    String itemName;
    Object object;

    public ComBoboxCommon(String itemCode, String itemName, Object object) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.object = object;
    }

    public ComBoboxCommon() {
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
