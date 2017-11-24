package com.cms.dto;

import com.cms.common.basedto.BaseDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 16/01/2017 09:45:29
 */
public class PriceInfoDTO extends BaseDTO {
    //Fields

    private String id;
    private String code;
    private String name;
    private String price;
    private String tokenPrice;
    private String provider;
    private String type;
    private String status;
    //Constructor

    public PriceInfoDTO() {

    }

    public PriceInfoDTO(PriceInfoDTO another) {        
        this.code = another.code;
        this.name = another.name;
        this.price = another.price;
        this.tokenPrice = another.tokenPrice;
        this.provider = another.provider;
        this.type = another.type;
        this.status = another.status;
    }

    public PriceInfoDTO(String id, String code, String name, String price, String tokenPrice, String provider, String type, String status) {
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

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getTokenPrice() {
        return this.tokenPrice;
    }

    public void setTokenPrice(final String tokenPrice) {
        this.tokenPrice = tokenPrice;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(final String provider) {
        this.provider = provider;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

}
