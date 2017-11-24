/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.dto;

import com.cms.common.basedto.BaseDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/23/2016 11:13 PM
 */
public class TaxAuthorityDTO extends BaseDTO {
    //Fields

    private String id;
    private String maCqt;
    private String tenCqt;
    private String maTinh;
    private String maQuanHuyen;
    private String status;
    private String maMST;

    //Constructor
    public TaxAuthorityDTO() {
    }

    public TaxAuthorityDTO(String id, String maCqt, String tenCqt, String maTinh, 
            String maQuanHuyen, String status, String maMST) {
        this.id = id;
        this.maCqt = maCqt;
        this.tenCqt = tenCqt;
        this.maTinh = maTinh;
        this.maQuanHuyen = maQuanHuyen;
        this.status = status;
        this.maMST = maMST;
    }
    //Getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setMaCqt(String maCqt) {
        this.maCqt = maCqt;
    }

    public String getMaCqt() {
        return maCqt;
    }

    public void setTenCqt(String tenCqt) {
        this.tenCqt = tenCqt;
    }

    public String getTenCqt() {
        return tenCqt;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    public String getMaTinh() {
        return maTinh;
    }

    public void setMaQuanHuyen(String maQuanHuyen) {
        this.maQuanHuyen = maQuanHuyen;
    }

    public String getMaQuanHuyen() {
        return maQuanHuyen;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getMaMST() {
        return maMST;
    }

    public void setMaMST(String maMST) {
        this.maMST = maMST;
    }

}
