/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vwf5.base.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vtsoft
 */
@XmlRootElement
public class ResultDTO {

    private String id;
    private String key;
    private String message;
    private int quantitySucc;
    private int quantityFail;
    

    public ResultDTO(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public ResultDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getQuantitySucc() {
        return quantitySucc;
    }

    public void setQuantitySucc(int quantitySucc) {
        this.quantitySucc = quantitySucc;
    }

    public int getQuantityFail() {
        return quantityFail;
    }

    public void setQuantityFail(int quantityFail) {
        this.quantityFail = quantityFail;
    }

}
