package com.cms.model;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.cms.dto.PriceInfoDTO;
import com.vfw5.base.model.BaseFWModel;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 16/01/2017 09:45:29
 */
@Entity
@Table(name = "PRICE_INFO")
public class PriceInfo extends BaseFWModel {

    //Fields
    private Long id;
    private String code;
    private String name;
    private Long price;
    private String tokenPrice;
    private String provider;
    private String type;
    private String status;

    public PriceInfo() {
        setColId("id");
        setColName("code");
    }

    public PriceInfo(Long id) {
        this.id = id;
    }

    public PriceInfo(Long id, String code, String name, Long price, String tokenPrice, String provider, String type, String status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.tokenPrice = tokenPrice;
        this.provider = provider;
        this.type = type;
        this.status = status;
    }

    //Getters and Setters
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "PRICE_INFO_SEQ")
            }
    )
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "CODE")
    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    @Column(name = "NAME")
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Column(name = "PRICE")
    public Long getPrice() {
        return this.price;
    }

    public void setPrice(final Long price) {
        this.price = price;
    }

    @Column(name = "TOKEN_PRICE")
    public String getTokenPrice() {
        return this.tokenPrice;
    }

    public void setTokenPrice(final String tokenPrice) {
        this.tokenPrice = tokenPrice;
    }

    @Column(name = "PROVIDER")
    public String getProvider() {
        return this.provider;
    }

    public void setProvider(final String provider) {
        this.provider = provider;
    }

    @Column(name = "TYPE")
    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @Override
    public PriceInfoDTO toDTO() {
        PriceInfoDTO dto = new PriceInfoDTO(
                id == null ? null : id.toString(),
                code,
                name,
                price == null ? null : price.toString(),
                tokenPrice,
                provider,
                type,
                status
        );
        return dto;
    }
}
