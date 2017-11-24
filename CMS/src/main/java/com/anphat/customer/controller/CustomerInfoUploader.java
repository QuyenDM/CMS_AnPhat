/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.controller;

import com.cms.login.ws.WSCustomer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.WindowProgress;
import com.cms.dto.CustomerDTO;
import com.cms.dto.StaffDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.DataUtil;
import com.cms.utils.ValidateCells;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author quyen
 */
public class CustomerInfoUploader extends CommonUploader {

    private List<CustomerDTO> lstUploaded;
    private BeanItemContainer container;
    private Map<String, CustomerDTO> mapTaxCode2Cust;

    public CustomerInfoUploader(Upload upload, WindowProgress wp, CommonTableFilterPanel tblUpload,
            LinkedHashMap<String, CustomTable.Align> HEADER) {
        super(upload, wp);
        this.HEADER = HEADER;
        this.tblPanel = tblUpload;
    }

    @Override
    public void uploadFile() {
        try {
            List lstUpload;
            List<ValidateCells> lstValidateCells = new ArrayList<>();
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, true, 14));//mst
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, true, 200));//Ten khach hang
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 50));// CQT - Tinh
            lstValidateCells.add(new ValidateCells(DataUtil.DATE, false, 14));// Ngay dang ky kkt
            lstValidateCells.add(new ValidateCells(DataUtil.DATE, false, 14));// Ngay kkt gan nhat
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 50));// chi cuc thue             
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 25));//Loai khach hang
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 25));//so dien thoai
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 25));//fax
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 25));//Email
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 50));//So tai khoan
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 50));//Ngan hang
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 50));//Dai ly thue
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 200));//Dia chi kinh doanh
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 200));//Dia chi tru so
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 100));//Tên người đại diện
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 25));//Chứng minh thư
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 200));//Mo ta
            lstUpload = DataUtil.isValidExcells(mimeType, tempFile, 0, 1, 0, 17, 3, lstValidateCells);
            if (lstUpload == null) {
                Notification.show(BundleUtils.getString("valid.import.file"), Notification.Type.WARNING_MESSAGE);
                return;
            }
            staff = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");

            //LAY DANH DACH DU LIEU - CELL DA NHAP
            Object[] tmp;

            String taxCode; //Ma so thue
            String name; //Ten khach hang
            String taxAuthority; //Cơ quan thuế 
            String dateRegister; //Ngay dang ky kkt
            String lastUploadDate; //Ngay kkt gan nhat
            String taxDepartment; //Chi cục thuế
            String custType; //Loai khach hang
            String telNumber; //So dien thoai
            String fax; //So fax
            String email; //Email
            String accountNo; //So tai khoan
            String bankName; //Ngan hang
            String agency; //Dai ly thue
            String deployAddress;//Dia chi kinh doanh
            String officeAddress; //Dia chi tru so
            String representativeName; // Tên người đại diện
            String representativeId = ""; //Chứng minh thư
            String description; //Mo ta

            String staffName = staff.getName(); //Nhan vien tai len
            lstUploaded = new ArrayList<>();
            CustomerDTO tempObject;
            List<String> lstTaxCodes = new ArrayList<>();
            for (Object object : lstUpload) {
                tmp = (Object[]) object;
                if (!DataUtil.isNullObject(tmp)) {
                    taxCode = DataUtil.getStringNullOrZero(String.valueOf(tmp[0]));
                    if (!lstTaxCodes.contains(taxCode)
                            && !"null".equalsIgnoreCase(taxCode)) {
                        lstTaxCodes.add(taxCode);
                        name = DataUtil.getStringNullOrZero(String.valueOf(tmp[1]));
                        taxAuthority = DataUtil.getStringNullOrZero(String.valueOf(tmp[2]));
                        dateRegister = DataUtil.getStringNullOrZero(String.valueOf(tmp[3]));
                        lastUploadDate = DataUtil.getStringNullOrZero(String.valueOf(tmp[4]));
                        taxDepartment = DataUtil.getStringNullOrZero(String.valueOf(tmp[5]));
                        custType = DataUtil.getStringNullOrZero(String.valueOf(tmp[6]));
                        telNumber = DataUtil.getStringNullOrZero(String.valueOf(tmp[7]));
                        fax = DataUtil.getStringNullOrZero(String.valueOf(tmp[8]));
                        email = DataUtil.getStringNullOrZero(String.valueOf(tmp[9]));
                        accountNo = DataUtil.getStringNullOrZero(String.valueOf(tmp[10]));
                        bankName = DataUtil.getStringNullOrZero(String.valueOf(tmp[11]));
                        agency = DataUtil.getStringNullOrZero(String.valueOf(tmp[12]));
                        deployAddress = DataUtil.getStringNullOrZero(String.valueOf(tmp[13]));
                        officeAddress = DataUtil.getStringNullOrZero(String.valueOf(tmp[14]));
                        representativeName = DataUtil.getStringNullOrZero(String.valueOf(tmp[15]));
                        representativeId = DataUtil.getStringNullOrZero(String.valueOf(tmp[16]));
                        description = DataUtil.getStringNullOrZero(String.valueOf(tmp[17]));

                        tempObject = new CustomerDTO(taxCode, name,
                                taxAuthority, dateRegister, lastUploadDate,
                                taxDepartment, custType, telNumber, fax, email,
                                accountNo, bankName, agency, deployAddress, officeAddress,
                                representativeName, representativeId, description, staffName);
                        lstUploaded.add(tempObject);
                    }
                }
            }
            if (!DataUtil.isListNullOrEmpty(lstUploaded)) {
//                lstUploaded = removeDupplicationDatas(lstUploaded);
                List<CustomerDTO> lstCustomerExisted = checkCustomersExisted(lstUploaded);

//                List<CustomerDTO> lstCustomerHTKK = getInfoOfCustomerFromIHTKK(lstUploaded);

                if (DataUtil.isListNullOrEmpty(lstCustomerExisted)) {
                    container = new BeanItemContainer(CustomerDTO.class);
//                    mapTaxCode2Cust = DataUtil.buildHasmap(lstCustomerHTKK, "taxCode");
                    CustomerDTO temp;
//                    for (CustomerDTO c : lstUploaded) {
//                        if (mapTaxCode2Cust.containsKey(c.getTaxCode())) {
//                            temp = mapTaxCode2Cust.get(c.getTaxCode());
//                            c.setName(temp.getName());
//                            c.setTaxAuthority(temp.getTaxAuthority() + "00");
//                        }
//                    }
                    container.addAll(lstUploaded);
                    CommonFunctionTableFilter.refreshTable(tblPanel, HEADER, container);
                } else {
                    mapTaxCode2Cust = DataUtil.buildHasmap(lstCustomerExisted, "taxCode");
//                    Map<String, CustomerDTO> mapTaxCode2CustIHTKK = DataUtil.buildHasmap(lstCustomerHTKK, "taxCode");
                    CustomerDTO temp;
                    String taxCode1;
                    for (CustomerDTO c : lstUploaded) {
                        taxCode1 = c.getTaxCode();
                        if (mapTaxCode2Cust.containsKey(taxCode1)) {
                            temp = mapTaxCode2Cust.get(taxCode1);
                            c.setCustId(temp.getCustId());
//                            c.setTaxAuthority(temp.getTaxAuthority());
//                            if (Objects.equal("90000", temp.getTaxAuthority())) {
//                                if (mapTaxCode2CustIHTKK.containsKey(taxCode1)) {
//                                    temp = mapTaxCode2CustIHTKK.get(taxCode1);
//                                    c.setTaxAuthority(temp.getTaxAuthority() + "00");
//                                }
//                            }

                        } else {
//                            if (mapTaxCode2CustIHTKK.containsKey(taxCode1)) {
//                                temp = mapTaxCode2CustIHTKK.get(taxCode1);
//                                if (!DataUtil.isStringNullOrEmpty(temp.getName())) {
//                                    c.setName(temp.getName());
//                                }
//                                if (!DataUtil.isStringNullOrEmpty(temp.getTaxAuthority())) {
//                                    c.setTaxAuthority(temp.getTaxAuthority() + "00");
//                                }
//                            }
                        }
//                        lstInserts.add(c);
                    }

                    container = new BeanItemContainer(CustomerDTO.class);
                    container.addAll(lstUploaded);
                    CommonFunctionTableFilter.refreshTable(tblPanel, HEADER, container);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            wp.close();
            UI.getCurrent().setPollInterval(-1);
        }
    }

    //Kiem tra cac cong ty da ton tai
    public List<CustomerDTO> checkCustomersExisted(List<CustomerDTO> lstUploaded) {
        //Lay danh sach mst
        List<String> taxCodes = DataUtil.getTaxCodes(lstUploaded);
        if (DataUtil.isListNullOrEmpty(taxCodes)) {
            return null;
        } else {
            List<CustomerDTO> lstCustomerExisted = new ArrayList<>();
            List<CustomerDTO> lstTemp;
            for (String taxCode : taxCodes) {
                lstTemp = WSCustomer.getCustomerExisted(Lists.newArrayList(taxCode));
                if (!DataUtil.isListNullOrEmpty(lstTemp)) {
                    lstCustomerExisted.addAll(lstTemp);
                }
            }
            return lstCustomerExisted;
        }
    }

    public List<CustomerDTO> getInfoOfCustomerFromIHTKK(List<CustomerDTO> lstUploaded) {
        //Lay danh sach mst
        List<String> taxCodes = DataUtil.getTaxCodes(lstUploaded);
        if (DataUtil.isListNullOrEmpty(taxCodes)) {
            return null;
        } else {
            List<CustomerDTO> lstCustomerExisted = new ArrayList<>();
            List<CustomerDTO> lstTemp;
            for (String taxCode : taxCodes) {
                lstTemp = WSCustomer.getCustomerFromIHTKK(Lists.newArrayList(taxCode));
                if (!DataUtil.isListNullOrEmpty(lstTemp)) {
                    lstCustomerExisted.addAll(lstTemp);
                }
            }
            return lstCustomerExisted;
        }
    }
}
