/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vwf5.base.dto;

import java.util.List;

/**
 *
 * @author Truongbx3 createdate 16/04/2015
 */
public class TreeNote {

    String id;
    String name;
    List<TreeNote> lstChild;

    public TreeNote(String id, String name, List<TreeNote> lstChild) {
        this.id = id;
        this.name = name;
        this.lstChild = lstChild;
    }

    public TreeNote(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public TreeNote() {
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNote> getLstChild() {
        return lstChild;
    }

    public void setLstChild(List<TreeNote> lstChild) {
        this.lstChild = lstChild;
    }

    
    
}
