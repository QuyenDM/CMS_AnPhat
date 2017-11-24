/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.customer.controller.CommonUploader;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.WindowProgress;
import com.cms.dto.StaffDTO;
import com.cms.dto.StatisticStaffPointDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.DataUtil;
import com.cms.utils.ValidateCells;
import com.google.common.collect.Lists;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author quyen
 */
public class StatisticStaffPointUploader extends CommonUploader {

    private List<StatisticStaffPointDTO> lstUploaded;
    private BeanItemContainer container;
    private final LinkedHashMap<String, CustomTable.Align> HEADER_TABLE;

    public StatisticStaffPointUploader(Upload upload, WindowProgress wp, CommonTableFilterPanel tblUpload,
            LinkedHashMap<String, CustomTable.Align> HEADER) {
        super(upload, wp);
        this.HEADER_TABLE = HEADER;
        this.tblPanel = tblUpload;
    }

    @Override
    public void uploadFile() {
        try {
            List lstUpload;
            List<ValidateCells> lstValidateCells = Lists.newArrayList();
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 20));//Ma nhan vien
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 100));// Ten nhan vien
            lstValidateCells.add(new ValidateCells(DataUtil.LONG, false, 10));// So luong data giao
            lstValidateCells.add(new ValidateCells(DataUtil.LONG, false, 10));// So luong goi
            lstValidateCells.add(new ValidateCells(DataUtil.DOUBLE, false, 5));// Diem tuan 1             
            lstValidateCells.add(new ValidateCells(DataUtil.DOUBLE, false, 5));// Diem tuan 2
            lstValidateCells.add(new ValidateCells(DataUtil.DOUBLE, false, 5));// Diem tuan 3
            lstValidateCells.add(new ValidateCells(DataUtil.DOUBLE, false, 5));// Diem tuan 4
            lstValidateCells.add(new ValidateCells(DataUtil.DOUBLE, false, 5));// Diem tuan 5
            lstValidateCells.add(new ValidateCells(DataUtil.DOUBLE, false, 5));// Diem huy            
            lstUpload = DataUtil.isValidExcells(mimeType, tempFile, 0, 1, 0, 10, 3, lstValidateCells);
            if (lstUpload == null) {
                Notification.show(BundleUtils.getString("valid.import.file"), Notification.Type.WARNING_MESSAGE);
                return;
            }
            staff = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");

            //LAY DANH DACH DU LIEU - CELL DA NHAP
            Object[] tmp;

            String staffCode;
            String staffName;
            String numberData;
            String numberCall;
            String w1Point;
            String w2Point;
            String w3Point;
            String w4Point;
            String w5Point;
            String monthPoint;
            String cancelPoint;
            String totalPoint;

            lstUploaded = new ArrayList<>();
            StatisticStaffPointDTO tempObject;
            for (Object object : lstUpload) {
                tmp = (Object[]) object;
                if (!DataUtil.isNullObject(tmp)
                        && !"null".equalsIgnoreCase(String.valueOf(tmp[0]))) {
                    staffCode = DataUtil.getStringNullOrZero(String.valueOf(tmp[0]));
                    staffName = DataUtil.getStringNullOrZero(String.valueOf(tmp[1]));
                    numberData = DataUtil.getStringNullOrZero(String.valueOf(tmp[2]));
                    numberCall = DataUtil.getStringNullOrZero(String.valueOf(tmp[3]));
                    w1Point = DataUtil.getStringNullOrZero(String.valueOf(tmp[4]));
                    w2Point = DataUtil.getStringNullOrZero(String.valueOf(tmp[5]));
                    w3Point = DataUtil.getStringNullOrZero(String.valueOf(tmp[6]));
                    w4Point = DataUtil.getStringNullOrZero(String.valueOf(tmp[7]));
                    w5Point = DataUtil.getStringNullOrZero(String.valueOf(tmp[8]));
                    cancelPoint = DataUtil.getStringNullOrZero(String.valueOf(tmp[9]));

                    tempObject = new StatisticStaffPointDTO(staffCode, staffName,
                            numberData, numberCall, w1Point, w2Point, w3Point,
                            w4Point, w5Point, cancelPoint);
                    lstUploaded.add(tempObject);

                }
            }
            container = new BeanItemContainer(StatisticStaffPointDTO.class);
            container.addAll(lstUploaded);
            CommonFunctionTableFilter.refreshTable(tblPanel, HEADER_TABLE, container);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            wp.close();
            UI.getCurrent().setPollInterval(-1);
        }
    }
}
