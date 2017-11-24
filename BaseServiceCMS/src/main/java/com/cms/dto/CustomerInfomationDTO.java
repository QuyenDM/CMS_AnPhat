/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author quyen
 */
public class CustomerInfomationDTO implements Serializable {

    private List<TermInformationDTO> lstTermInformationDTOs;
    private List<CustomerContactDTO> lstCustomerContacts;
    private List<CustomerCareHistoryDTO> lstCustomerCareHistoryDTOs;
    private List<CustomerStatusDTO> lstCustomerStatusDTOs;

    public CustomerInfomationDTO() {
    }

    public CustomerInfomationDTO(List<TermInformationDTO> lstTermInformationDTOs, List<CustomerContactDTO> lstCustomerContacts, List<CustomerCareHistoryDTO> lstCustomerCareHistoryDTOs, List<CustomerStatusDTO> lstCustomerStatusDTOs) {
        this.lstTermInformationDTOs = lstTermInformationDTOs;
        this.lstCustomerContacts = lstCustomerContacts;
        this.lstCustomerCareHistoryDTOs = lstCustomerCareHistoryDTOs;
        this.lstCustomerStatusDTOs = lstCustomerStatusDTOs;
    }

    public List<CustomerStatusDTO> getLstCustomerStatusDTOs() {
        return lstCustomerStatusDTOs;
    }

    public void setLstCustomerStatusDTOs(List<CustomerStatusDTO> lstCustomerStatusDTOs) {
        this.lstCustomerStatusDTOs = lstCustomerStatusDTOs;
    }

    public List<TermInformationDTO> getLstTermInformationDTOs() {
        return lstTermInformationDTOs;
    }

    public void setLstTermInformationDTOs(List<TermInformationDTO> lstTermInformationDTOs) {
        this.lstTermInformationDTOs = lstTermInformationDTOs;
    }

    public List<CustomerContactDTO> getLstCustomerContacts() {
        return lstCustomerContacts;
    }

    public void setLstCustomerContacts(List<CustomerContactDTO> lstCustomerContacts) {
        this.lstCustomerContacts = lstCustomerContacts;
    }

    public List<CustomerCareHistoryDTO> getLstCustomerCareHistoryDTOs() {
        return lstCustomerCareHistoryDTOs;
    }

    public void setLstCustomerCareHistoryDTOs(List<CustomerCareHistoryDTO> lstCustomerCareHistoryDTOs) {
        this.lstCustomerCareHistoryDTOs = lstCustomerCareHistoryDTOs;
    }

}
