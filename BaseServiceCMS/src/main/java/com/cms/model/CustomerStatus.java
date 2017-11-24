package com.cms.model;

import com.cms.dto.CustomerStatusDTO;
import com.vfw5.base.model.BaseFWModel;
import com.vfw5.base.utils.DateTimeUtils;
import com.vfw5.base.utils.ParamUtils;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 09/08/2016 21:46:21
 */
@Entity
@Table(name = "CUSTOMER_STATUS")
public class CustomerStatus extends BaseFWModel {

    //Fields
    private Long id;
    private Long custId;
    private String taxCode;
    private String service;
    private Long staffId;
    private String staffCode;
    private Long status;
    private Date lastUpdated;
    private Date createdDate;
    private String staffName;
    private String custName;
    private String mineName;
    private String taxAuthority;
    private String lastNotes;

    public CustomerStatus() {
        setColId("id");
        setColName("createdDate");
    }

    public CustomerStatus(Long id) {
        this.id = id;
    }

    public CustomerStatus(Long id, Long custId, String taxCode, String service,
            Long staffId, String staffCode, Long status, Date lastUpdated,
            Date createdDate, String staffName, String custName,
            String mineName, String taxAuthority, String lastNotes) {
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
    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence",
            parameters = {
                @Parameter(name = "sequence", value = "CUSTOMER_STATUS_SEQ")
            }
    )
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "CUST_ID")
    public Long getCustId() {
        return this.custId;
    }

    public void setCustId(final Long custId) {
        this.custId = custId;
    }

    @Column(name = "TAX_CODE", nullable = false)
    public String getTaxCode() {
        return this.taxCode;
    }

    public void setTaxCode(final String taxCode) {
        this.taxCode = taxCode;
    }

    @Column(name = "SERVICE", nullable = false)
    public String getService() {
        return this.service;
    }

    public void setService(final String service) {
        this.service = service;
    }

    @Column(name = "STAFF_ID")
    public Long getStaffId() {
        return this.staffId;
    }

    public void setStaffId(final Long staffId) {
        this.staffId = staffId;
    }

    @Column(name = "STAFF_CODE", nullable = false)
    public String getStaffCode() {
        return this.staffCode;
    }

    public void setStaffCode(final String staffCode) {
        this.staffCode = staffCode;
    }

    @Column(name = "STATUS", nullable = false)
    public Long getStatus() {
        return this.status;
    }

    public void setStatus(final Long status) {
        this.status = status;
    }

    @Column(name = "LAST_UPDATED")
    public Date getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(final Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Column(name = "CREATED_DATE")
    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "STAFF_NAME")
    public String getStaffName() {
        return this.staffName;
    }

    public void setStaffName(final String staffName) {
        this.staffName = staffName;
    }

    @Column(name = "CUST_NAME")
    public String getCustName() {
        return this.custName;
    }

    public void setCustName(final String custName) {
        this.custName = custName;
    }

    @Column(name = "MINE_NAME")
    public String getMineName() {
        return this.mineName;
    }

    public void setMineName(final String mineName) {
        this.mineName = mineName;
    }

    @Column(name = "TAX_AUTHORITY")
    public String getTaxAuthority() {
        return this.taxAuthority;
    }

    public void setTaxAuthority(final String taxAuthority) {
        this.taxAuthority = taxAuthority;
    }
    
    @Column(name = "LAST_NOTES")
    public String getLastNotes() {
        return lastNotes;
    }

    public void setLastNotes(String lastNotes) {
        this.lastNotes = lastNotes;
    }

    
    @Override
    public CustomerStatusDTO toDTO() {
        CustomerStatusDTO dto = new CustomerStatusDTO(
                id == null ? null : id.toString(),
                custId == null ? null : custId.toString(),
                taxCode,
                service,
                staffId == null ? null : staffId.toString(),
                staffCode,
                status == null ? null : status.toString(),
                lastUpdated == null ? null : DateTimeUtils.convertDateToString(lastUpdated, ParamUtils.ddMMyyyy),
                createdDate == null ? null : DateTimeUtils.convertDateToString(createdDate, ParamUtils.ddMMyyyy),
                staffName,
                custName,
                mineName,
                taxAuthority, lastNotes
        );
        return dto;
    }
}
