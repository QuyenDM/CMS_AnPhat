/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.common.basedto;

import java.io.Serializable;

/**
 *
 * @author TiepNV6
 */
public class BaseDTO implements Serializable, Cloneable {

//    private String changedTime = "0";
    private String defaultSortField;

//    public String getChangedTime() {
//        return changedTime;
//    }
//
//    public void setChangedTime(String changedTime) {
//        this.changedTime = changedTime;
//    }

    public String getDefaultSortField() {
        return defaultSortField;
    }

    public void setDefaultSortField(String defaultSortField) {
        this.defaultSortField = defaultSortField;
    }
    //
}
