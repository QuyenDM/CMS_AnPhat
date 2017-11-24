/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vfw5.base.dto;

import java.util.List;

/**
 *
 * @author TruongBx3
 */



public class BaseHashMapObject<K, V> {

    K objKey;
    List<V> objValue;

    public BaseHashMapObject(K objKey, List<V> objValue) {
        this.objKey = objKey;
        this.objValue = objValue;
    }

    public BaseHashMapObject(K objKey) {
        this.objKey = objKey;
    }

    public BaseHashMapObject() {
    }

    public K getObjKey() {
        return objKey;
    }

    public void setObjKey(K objKey) {
        this.objKey = objKey;
    }

    public List<V> getObjValue() {
        return objValue;
    }

    public void setObjValue(List<V> objValue) {
        this.objValue = objValue;
    }

}
