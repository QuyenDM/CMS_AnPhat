package com.cms.dto;

import com.vfw5.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;
import com.cms.model.PriceInfo;
import com.vfw5.base.dto.BaseFWDTO;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 16/01/2017 09:45:29
 */
@XmlRootElement(name = "PriceInfo")
public class PriceInfoDTO extends BaseFWDTO<PriceInfo> {
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
        setDefaultSortField("CODE");
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

    @Override
    public PriceInfo toModel() {
        try {
            PriceInfo model = new PriceInfo(
                    !StringUtils.validString(id) ? null : Long.valueOf(id),
                    code,
                    name,
                    !StringUtils.validString(price) ? null : Long.valueOf(price),
                    tokenPrice,
                    provider,
                    type,
                    status
            );
            return model;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Long getFWModelId() {
        return !StringUtils.validString(id) ? null : Long.valueOf(id);
    }

    @Override
    public String catchName() {
        return getCode().toString();
    }
}
