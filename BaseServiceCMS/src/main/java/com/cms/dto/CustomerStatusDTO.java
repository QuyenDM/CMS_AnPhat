package com.cms.dto;

import com.cms.model.CustomerStatus;
import com.vfw5.base.dto.BaseFWDTO;
import com.vfw5.base.utils.DateTimeUtils;
import com.vfw5.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 09/08/2016 21:46:21
 */
@XmlRootElement(name = "CustomerStatus")
public class CustomerStatusDTO extends BaseFWDTO<CustomerStatus> {
    //Fields

    private String id;
    private String custId;
    private String taxCode;
    private String service;
    private String staffId;
    private String staffCode;
    private String status;
    private String lastUpdated;
    private String createdDate;
    private String staffName;
    private String custName;
    private String mineName;
    private String taxAuthority;
    private String lastNotes;
    //Constructor

    public CustomerStatusDTO() {
        setDefaultSortField("CREATED_DATE");
    }

    public CustomerStatusDTO(String taxCode) {
        this.taxCode = taxCode;
    }

    public CustomerStatusDTO(String id, String custId, String taxCode,
            String service, String staffId, String staffCode, String status,
            String lastUpdated, String createdDate, String staffName,
            String custName, String mineName, String taxAuthority, String lastNotes) {
        this.id = id;
        this.custId = custId;
        this.taxCode = taxCode;
        this.service = service;
        this.staffId = staffId;
        this.staffCode = staffCode;
        this.status = status;
        this.lastUpdated = lastUpdated;
        this.createdDate = createdDate;
        this.staffName = staffName;
        this.custName = custName;
        this.mineName = mineName;
        this.taxAuthority = taxAuthority;
        this.lastNotes = lastNotes;
    }
    //Getters and Setters

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getCustId() {
        return this.custId;
    }

    public void setCustId(final String custId) {
        this.custId = custId;
    }

    public String getTaxCode() {
        return this.taxCode;
    }

    public void setTaxCode(final String taxCode) {
        this.taxCode = taxCode;
    }

    public String getService() {
        return this.service;
    }

    public void setService(final String service) {
        this.service = service;
    }

    public String getStaffId() {
        return this.staffId;
    }

    public void setStaffId(final String staffId) {
        this.staffId = staffId;
    }

    public String getStaffCode() {
        return this.staffCode;
    }

    public void setStaffCode(final String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(final String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
    }

    public String getStaffName() {
        return this.staffName;
    }

    public void setStaffName(final String staffName) {
        this.staffName = staffName;
    }

    public String getCustName() {
        return this.custName;
    }

    public void setCustName(final String custName) {
        this.custName = custName;
    }

    public String getMineName() {
        return mineName;
    }

    public void setMineName(String mineName) {
        this.mineName = mineName;
    }

    public String getTaxAuthority() {
        return taxAuthority;
    }

    public void setTaxAuthority(String taxAuthority) {
        this.taxAuthority = taxAuthority;
    }

    public String getLastNotes() {
        return lastNotes;
    }

    public void setLastNotes(String lastNotes) {
        this.lastNotes = lastNotes;
    }

    public CustomerStatus toModel() {
        try {
            CustomerStatus model = new CustomerStatus(
                    !StringUtils.validString(id) ? null : Long.valueOf(id),
                    !StringUtils.validString(custId) ? null : Long.valueOf(custId),
                    taxCode,
                    service,
                    !StringUtils.validString(staffId) ? null : Long.valueOf(staffId),
                    staffCode,
                    !StringUtils.validString(status) ? null : Long.valueOf(status),
                    !StringUtils.validString(lastUpdated) ? null : DateTimeUtils.convertStringToDate(lastUpdated),
                    !StringUtils.validString(createdDate) ? null : DateTimeUtils.convertStringToDate(createdDate),
                    staffName,
                    custName,
                    mineName,
                    taxAuthority, lastNotes
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
        return getCreatedDate().toString();
    }
}
