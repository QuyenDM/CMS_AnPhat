package com.cms.dto;

import com.cms.model.ContractTemplateList;
import com.vfw5.base.dto.BaseFWDTO;
import com.vfw5.base.utils.DateTimeUtils;
import com.vfw5.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 03/11/2016 22:28:02
 */
@XmlRootElement(name = "ContractTemplateList")
public class ContractTemplateListDTO extends BaseFWDTO<ContractTemplateList> {
    //Fields

    private String contractTemplateId;
    private String code;
    private String name;
    private String pathFile;
    private String service;
    private String type;
    private String provider;
    private String createdDate;
    private String lastUpdatedDate;
    private String status;
    //Constructor

    public ContractTemplateListDTO() {
        setDefaultSortField("name");
    }

    public ContractTemplateListDTO(String contractTemplateId, String code,
            String name, String pathFile, String service, String type,String provider,
            String createdDate, String lastUpdatedDate, String status) {
        this.contractTemplateId = contractTemplateId;
        this.code = code;
        this.name = name;
        this.pathFile = pathFile;
        this.service = service;
        this.type = type;
        this.provider = provider;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.status = status;
    }
    //Getters and Setters

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getContractTemplateId() {
        return this.contractTemplateId;
    }

    public void setContractTemplateId(final String contractTemplateId) {
        this.contractTemplateId = contractTemplateId;
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

    public String getPathFile() {
        return this.pathFile;
    }

    public void setPathFile(final String pathFile) {
        this.pathFile = pathFile;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }

    public void setLastUpdatedDate(final String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public ContractTemplateList toModel() {
        try {
            ContractTemplateList model = new ContractTemplateList(
                    !StringUtils.validString(contractTemplateId) ? null : Long.valueOf(contractTemplateId),
                    code,
                    name,
                    pathFile,
                    service,
                    type,
                    provider,
                    !StringUtils.validString(createdDate) ? null : DateTimeUtils.convertStringToDate(createdDate),
                    !StringUtils.validString(lastUpdatedDate) ? null : DateTimeUtils.convertStringToDate(lastUpdatedDate),
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
        return !StringUtils.validString(contractTemplateId) ? null : Long.valueOf(contractTemplateId);
    }

    @Override
    public String catchName() {
        return getName().toString();
    }
}
