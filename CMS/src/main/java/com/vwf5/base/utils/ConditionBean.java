/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.vwf5.base.utils;

/**
 *
 * @author kdvt_binhnt22@viettel.com.vn
 * @version 1.0
 * @since since_text
 */
public class ConditionBean {

    private String field;
    private String value;
    private String operator;
    private String type;
    private boolean columnSet;

    public enum Operator {

        NAME_EQUAL,
        NAME_LESS_EQUAL,
        NAME_GREATER_EQUAL,
        NAME_NOT_EQUAL,
        NAME_LESS,
        NAME_GREATER,
        NAME_LIKE,
        NAME_OR,
        NAME_AND,
        NAME_IN,
    }

    public enum Type {
        DATE,
        STRING,
        NUMBER,
        DOUBLE,
    }

    public ConditionBean() {

    }

    public ConditionBean(String field, String operator, String value) {
        this.field = field;
        this.value = value;
        this.operator = operator;
    }

    public ConditionBean(String field, String operator, String value, String type) {
        this.field = field;
        this.value = value;
        this.operator = operator;
        this.type = type;
    }

    public ConditionBean(String field, String value, Operator operator, Type type) {
        this.field = field;
        this.value = value;
        this.operator = String.valueOf(operator);
        this.type = String.valueOf(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType(Type type) {
        this.type = String.valueOf(type);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOp() {
        return operator;
    }

    public void setOp(Operator operator) {
        this.operator = String.valueOf(operator);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isColumnSet() {
        return columnSet;
    }

    public void setColumnSet(boolean columnSet) {
        this.columnSet = columnSet;
    }

}
