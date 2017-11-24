/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.common.basedto;

/**
 *
 * @author Truongbx3
 */
public class ConditionBean {

    private String field;
    private String value;
    private Operator operator;
    private Type type;

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

    public ConditionBean(String field, String value, Operator operator, Type type) {
        this.field = field;
        this.value = value;
        this.operator = operator;
        this.type = type;
    }

    public ConditionBean() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Operator getOp() {
        return operator;
    }

    public void setOp(Operator op) {
        this.operator = op;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
