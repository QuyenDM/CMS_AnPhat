package com.vwf5.base.utils;

import com.vwf5.base.servicecaller.XmlSchema;
import java.io.Serializable;

public class BpmParameter implements Serializable {

    private String key;
    private String type;
    private String value;

    public BpmParameter() {

    }

    public BpmParameter(String key, Object value) {
        this.key = key;
        parseValue(value);
    }

    public BpmParameter(String key, Object value, String type) {
        this.key = key;
        parseValue(value, type);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    private void parseValue(Object value) {
        this.type = value.getClass().getSimpleName();
        this.value = XmlSchema.objectToXml(value);

    }

    private void parseValue(Object value, String type) {
        this.type = type;
        this.value = XmlSchema.objectToXml(value);


    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return BpmParameter.class.getSimpleName()+": key = "+key+"/ value = "+value +"/type = "+type;
    }
}
