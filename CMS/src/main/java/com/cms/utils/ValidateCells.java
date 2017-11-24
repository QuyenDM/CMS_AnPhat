/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

/**
 *
 * @author TiepNV6
 */
public class ValidateCells {

    private String type;
    private boolean isNotNull;
    private int length;
    private String pattern;

    public ValidateCells(String type, boolean isNotNull, String pattern) {
        this.type = type;
        this.isNotNull = isNotNull;
        this.pattern = pattern;
    }

    public ValidateCells(String type, boolean isNotNull, int length) {
        this.type = type;
        this.isNotNull = isNotNull;
        this.length = length;
    }

    public ValidateCells() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIsNotNull() {
        return isNotNull;
    }

    public void setIsNotNull(boolean isNotNull) {
        this.isNotNull = isNotNull;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}
