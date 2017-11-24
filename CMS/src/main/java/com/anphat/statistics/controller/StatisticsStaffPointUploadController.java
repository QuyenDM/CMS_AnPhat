/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.statistics.controller;

import com.anphat.list.controller.StatisticStaffPointUploader;
import com.anphat.statistics.ui.StatisticStaffPointUploadDialog;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.component.WindowProgress;
import com.cms.dto.StatisticStaffPointDTO;
import com.cms.login.ws.WSStatisticStaffPoint;
import com.cms.ui.CommonButtonClickListener;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonMessages;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Upload;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quyen
 */
public class StatisticsStaffPointUploadController {

    private final StatisticStaffPointUploadDialog statisticStaffPointUploadDialog;
    private Upload uFileCommon;
    private Button btnUpload;
    private Button btnSave;
    private StatisticStaffPointUploader staffPointUploader;
    private final LinkedHashMap<String, CustomTable.Align> HEADER
            = BundleUtils.getHeadersFilter("statisticStaffPoint.header");
    private final String CAPTION = BundleUtils.getString("statisticStaffPoint.tbl.upload.caption");
    private BeanItemContainer container;
    private List<StatisticStaffPointDTO> lstUploads;
    private CommonTableFilterPanel panelTblPoints;
    private CustomPageTableFilter tblPoints;
    private CommonTableFilterPanel panelPointsInHome;
    private WindowProgress wp;

    public StatisticsStaffPointUploadController(StatisticStaffPointUploadDialog statisticStaffPointUploadDialog,
            CommonTableFilterPanel panelPointsInHome) {
        this.statisticStaffPointUploadDialog = statisticStaffPointUploadDialog;
        this.panelPointsInHome = panelPointsInHome;
    }

    public void initComponents() {
        initTable();
        initUpload();
        initBtnUpload();
        initBtnSave();
    }

    private void initUpload() {
        uFileCommon = statisticStaffPointUploadDialog.getuFileCommonInfo();
        staffPointUploader = new StatisticStaffPointUploader(uFileCommon, wp, panelTblPoints, HEADER);
        uFileCommon.setReceiver(staffPointUploader);
    }

    private void initBtnUpload() {
        btnUpload = statisticStaffPointUploadDialog.getBtnUpload();
        addListenerBtnUpload();
    }

    private void initBtnSave() {
        btnSave = statisticStaffPointUploadDialog.getBtnSave();
    }

    private void initTable() {
        panelTblPoints = statisticStaffPointUploadDialog.getTblUpload();
        tblPoints = panelTblPoints.getMainTable();
        panelTblPoints.getToolbar().setVisible(false);
        container = new BeanItemContainer<>(StatisticStaffPointDTO.class);
        CommonFunctionTableFilter.initTable(panelTblPoints, HEADER, container, CAPTION, 10, "statisticStaffPoint");
    }

    private void centerDialog(){
    }
    private void addListenerBtnUpload() {
        btnUpload.addClickListener(new CommonButtonClickListener() {
            @Override
            public void execute() {
                uploadData();
            }

            @Override
            public boolean isValidated() {
                getDataFromTblCustCommonInfor();
                if (DataUtil.isListNullOrEmpty(lstUploads)) {
                    CommonMessages.showChooseFileUpload();
                    return false;
                }
                return true;
            }
        });
    }

    protected void uploadData() {
        if (DataUtil.isListNullOrEmpty(lstUploads)) {
            CommonMessages.showChooseFileUpload();
        } else {
            try {
                List<StatisticStaffPointDTO> lstDeleteBeforeInsert = WSStatisticStaffPoint.getListStatisticStaffPointDTO(new StatisticStaffPointDTO(), 0, Constants.INT_100, "asc", "staffName");
                if (!DataUtil.isListNullOrEmpty(lstDeleteBeforeInsert)) {
                    String deleteResult = WSStatisticStaffPoint.deleteLstStatisticStaffPoint(lstDeleteBeforeInsert);
                }
            } catch (Exception ex) {
                Logger.getLogger(StatisticsStaffPointUploadController.class.getName()).log(Level.SEVERE, null, ex);
            }

            String result = WSStatisticStaffPoint.insertOrUpdateListStatisticStaffPoint(lstUploads);

            if (Constants.SUCCESS.equalsIgnoreCase(result)) {
                statisticStaffPointUploadDialog.close();
                CommonMessages.showMessageImportSuccess("caption.tbl.statistic.staff.point");
//                container.removeAllItems();
//                try {
//                    List<StatisticStaffPointDTO> lstStatisticStaffPoint = WSStatisticStaffPoint.getListStatisticStaffPointDTO(new StatisticStaffPointDTO(), 0, 100, "desc", "totalPoint");
//                    container.addAll(lstStatisticStaffPoint);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                CommonFunctionTableFilter.refreshTable(panelPointsInHome, HEADER, container);
            } else {
                CommonMessages.showErrorMessage(result);
            }
        }
    }

    protected void getDataFromTblCustCommonInfor() {
        tblPoints = panelTblPoints.getMainTable();
        if (DataUtil.isListNullOrEmpty(lstUploads)) {
            lstUploads = new ArrayList<>();
        } else {
            lstUploads.clear();
        }
        lstUploads.addAll((Collection<? extends StatisticStaffPointDTO>) tblPoints.getItemIds());
    }
}
