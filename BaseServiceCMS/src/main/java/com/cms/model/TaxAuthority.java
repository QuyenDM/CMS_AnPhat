/**
 * @(#)TaxAuthorityBO.java 8/23/2016 11:13 PM,
 * Copyright 2011 Viettel Telecom. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.cms.model;

import com.cms.dto.TaxAuthorityDTO;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.vfw5.base.model.BaseFWModel;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 8/23/2016 11:13 PM
 */
@Entity
@Table(name = "TAX_AUTHORITY")
public class TaxAuthority extends BaseFWModel {

    //Fields
    private Long id;
    private String maCqt;
    private String tenCqt;
    private String maTinh;
    private String maQuanHuyen;
    private String status;
    private String maMST;

    //Constructors
    public TaxAuthority() {
        setColId("id");
        setColName("maCqt");
        setUniqueColumn(new String[]{"id"});
    }

    public TaxAuthority(Long id) {
        this.id = id;
    }

    public TaxAuthority(Long id, String maCqt, String tenCqt,
            String maTinh, String maQuanHuyen, String status, String maMST) {
        this.id = id;
        this.maCqt = maCqt;
        this.tenCqt = tenCqt;
        this.maTinh = maTinh;
        this.maQuanHuyen = maQuanHuyen;
        this.status = status;
        this.maMST = maMST;
    }

    //Getters and Setters
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "TAX_AUTHORITY_SEQ")
            }
    )
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "MA_CQT")
    public String getMaCqt() {
        return this.maCqt;
    }

    public void setMaCqt(final String maCqt) {
        this.maCqt = maCqt;
    }

    @Column(name = "TEN_CQT")
    public String getTenCqt() {
        return this.tenCqt;
    }

    public void setTenCqt(final String tenCqt) {
        this.tenCqt = tenCqt;
    }

    @Column(name = "MA_TINH")
    public String getMaTinh() {
        return this.maTinh;
    }

    public void setMaTinh(final String maTinh) {
        this.maTinh = maTinh;
    }

    @Column(name = "MA_QUAN_HUYEN")
    public String getMaQuanHuyen() {
        return this.maQuanHuyen;
    }

    public void setMaQuanHuyen(final String maQuanHuyen) {
        this.maQuanHuyen = maQuanHuyen;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @Column(name = "MA_MST")
    public String getMaMST() {
        return maMST;
    }

    public void setMaMST(String maMST) {
        this.maMST = maMST;
    }

    @Override
    public TaxAuthorityDTO toDTO() {
        TaxAuthorityDTO dto = new TaxAuthorityDTO(
                id == null ? null : id.toString(), maCqt, tenCqt, maTinh, maQuanHuyen, status, maMST
        );
        return dto;
    }
}
