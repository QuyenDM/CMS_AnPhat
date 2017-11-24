/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.WindowProgress;
import com.cms.dto.TermInformationDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.DataUtil;
import com.cms.utils.ValidateCells;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author quyen
 */
public class TermInformationUploader extends CommonUploader {

    private List<TermInformationDTO> lstTermUpload;
    private BeanItemContainer container;
    private String typeOfTermInformation;

    public TermInformationUploader(Upload upload, WindowProgress wp,
            CommonTableFilterPanel tblTermInformation, LinkedHashMap<String, CustomTable.Align> HEADER) {
        super(upload, wp);
        this.tblPanel = tblTermInformation;
        this.HEADER = HEADER;
    }

    @Override
    public void uploadFile() {
        try {
            List lstUpload;
            List<ValidateCells> lstValidateCells = new ArrayList<>();

            lstValidateCells.add(new ValidateCells(DataUtil.STRING, true, 14));//mst
            if ("2".equals(typeOfTermInformation)) {
                lstValidateCells.add(new ValidateCells(DataUtil.DATE, true, 10));//ngay bat dau
            }else{
                lstValidateCells.add(new ValidateCells(DataUtil.DATE, false, 10));//ngay bat dau
            }
            lstValidateCells.add(new ValidateCells(DataUtil.DATE, false, 10));// ngay ket thuc     
            if ("2".equals(typeOfTermInformation)) {
                lstValidateCells.add(new ValidateCells(DataUtil.STRING, true, 500));// Nha cung cap
            }else{
                lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 500));// Nha cung cap
            }
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 500));//email
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 500));//so dien thoai
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 200));//Nguon du lieu
            lstUpload = DataUtil.isValidExcells(mimeType, tempFile, 0, 1, 0, 6, 3, lstValidateCells);
            if (lstUpload == null) {
                Notification.show(BundleUtils.getString("valid.import.file"), Notification.Type.WARNING_MESSAGE);
                return;
            }
            //LAY DANH DACH DU LIEU - CELL DA NHAP
            Object[] tmp;
            String taxCode;
            String startTime;
            String endTime;
            String provider;
            String email;
            String phone;
            String sourceData;
            lstTermUpload = new ArrayList<>();
            Set<String> set = new HashSet<>();
            TermInformationDTO termInfor;
            String sTermInfo;
            for (Object object : lstUpload) {

                termInfor = new TermInformationDTO();
                tmp = (Object[]) object;
                if (!DataUtil.isNullObject(tmp)) {
                    taxCode = DataUtil.getStringNullOrZero(String.valueOf(tmp[0]));
                    if (!DataUtil.isStringNullOrEmpty(taxCode)
                            && !"null".equals(taxCode)) {
                        startTime = DataUtil.getStringNullOrZero(String.valueOf(tmp[1]));
                        endTime = DataUtil.getStringNullOrZero(String.valueOf(tmp[2]));
                        provider = DataUtil.getStringNullOrZero(String.valueOf(tmp[3]));
                        email = DataUtil.getStringNullOrZero(String.valueOf(tmp[4]));
                        phone = DataUtil.getStringNullOrZero(String.valueOf(tmp[5]));
                        sourceData = DataUtil.getStringNullOrZero(String.valueOf(tmp[6]));
                        termInfor.setTaxCode(taxCode);
                        termInfor.setStartTime(startTime);
                        termInfor.setEndTime(endTime);
                        termInfor.setSourceData(sourceData);
                        if("3".equals(typeOfTermInformation)){
                            termInfor.setIsContactInfo("1");
                        }
                        if (!DataUtil.isStringNullOrEmpty(email)) {
                            termInfor.setEmail(email.toLowerCase());
                        }
                        termInfor.setPhone(phone);
                        if (!DataUtil.isStringNullOrEmpty(provider)) {
                            termInfor.setProvider(provider.toUpperCase());
                        }
                        sTermInfo = taxCode + "/" + startTime + "/" + endTime + "/" + provider + "/" + phone + "/" + email + "/" + sourceData;
                        if (set.add(sTermInfo)) {
                            lstTermUpload.add(termInfor);
                        }
                    }
                }
            }
            if (!DataUtil.isListNullOrEmpty(lstTermUpload)) {
                container = new BeanItemContainer(TermInformationDTO.class);
                container.addAll(lstTermUpload);
                CommonFunctionTableFilter.refreshTable(tblPanel, HEADER, container);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            wp.close();
            UI.getCurrent().setPollInterval(-1);
        }
    }

    public List<TermInformationDTO> getListUploaded() {
        return lstTermUpload;
    }

    private boolean validate(TermInformationDTO termInformationDTO) {

        return true;
    }

    public String getTypeOfTermInformation() {
        return typeOfTermInformation;
    }

    public void setTypeOfTermInformation(String typeOfTermInformation) {
        this.typeOfTermInformation = typeOfTermInformation;
    }

}
