package com.cms.model;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.cms.dto.ContractTemplateListDTO;
import com.vfw5.base.model.BaseFWModel;

import com.vfw5.base.utils.ParamUtils;
import com.vfw5.base.utils.DateTimeUtils;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 03/11/2016 22:17:52
 */
@Entity
@Table(name = "CONTRACT_TEMPLATE_LIST")
public class ContractTemplateList extends BaseFWModel {

    //Fields
    private Long contractTemplateId;
    private String code;
    private String name;
    private String pathFile;
    private String service;
    private String type;
    private String provider;
    private Date createdDate;
    private Date lastUpdatedDate;
    private String status;

    public ContractTemplateList() {
        setColId("contractTemplateId");
        setColName("name");
    }

    public ContractTemplateList(Long contractTemplateId) {
        this.contractTemplateId = contractTemplateId;
    }

    public ContractTemplateList(Long contractTemplateId, String code, String name, String pathFile, String service, String type, String provider, Date createdDate, Date lastUpdatedDate, String status) {
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
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CONTRACT_TEMPLATE_SEQ")
            }
    )
    @Column(name = "CONTRACT_TEMPLATE_ID", nullable = false)
    public Long getContractTemplateId() {
        return this.contractTemplateId;
    }

    public void setContractTemplateId(final Long contractTemplateId) {
        this.contractTemplateId = contractTemplateId;
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

    @Column(name = "PATH_FILE")
    public String getPathFile() {
        return this.pathFile;
    }

    public void setPathFile(final String pathFile) {
        this.pathFile = pathFile;
    }

    @Column(name = "CREATED_DATE")
    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "LAST_UPDATED_DATE")
    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }

    public void setLastUpdatedDate(final Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @Column(name = "SERVICE")
    public String getService() {
        return this.service;
    }

    public void setService(final String service) {
        this.service = service;
    }

    @Column(name = "TYPE")
    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Column(name = "PROVIDER")
    public String getProvider() {
        return this.provider;
    }

    public void setProvider(final String provider) {
        this.provider = provider;
    }

    @Override
    public ContractTemplateListDTO toDTO() {
        ContractTemplateListDTO dto = new ContractTemplateListDTO(
                contractTemplateId == null ? null : contractTemplateId.toString(),
                code,
                name,
                pathFile,
                service,
                type,
                provider,
                createdDate == null ? null : DateTimeUtils.convertDateToString(createdDate, ParamUtils.ddMMyyyy),
                lastUpdatedDate == null ? null : DateTimeUtils.convertDateToString(lastUpdatedDate, ParamUtils.ddMMyyyy),
                status
        );
        return dto;
    }
}
