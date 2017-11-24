/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.statistics.ui;

import com.cms.component.CommonDialog;
import com.cms.component.GridManyButton;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.FileDownloader;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Upload;
import java.io.File;

/**
 *
 * @author quyen
 */
public class StatisticStaffPointUploadDialog extends CommonDialog {

    private GridLayout uploadInfoLayout;
    private Upload uFileCommonInfo;
    private CommonTableFilterPanel tblUploads;
    private Button btnSave;
    private Button btnUpload;
    private Link linkTemplate;
    private final String LINK_CAPTION = BundleUtils.getString("import.link.template");
    private final String GRID_UPLOAD_CAPTION = BundleUtils.getString("statisticStaffPoint.tbl.upload.caption");

    public StatisticStaffPointUploadDialog() {
        super.setInfo("90%", "-1px", GRID_UPLOAD_CAPTION);
        initDialog();
    }

    private void initDialog() {
        uploadInfoLayout = buildGridUpload();
        tblUploads = new CommonTableFilterPanel();
        GridManyButton gridBtnUpload = new GridManyButton(new String[]{Constants.BUTTON_UPLOAD});
        btnUpload = gridBtnUpload.getBtnCommon().get(0);
        GridManyButton gridBtnSave = CommonUtils.getCommonButtonDialog(this);
        btnSave = gridBtnSave.getBtnCommon().get(0);

        //Them cac thanh phan vao grid
        mainLayout.addComponent(uploadInfoLayout);
        mainLayout.addComponent(gridBtnUpload);
        mainLayout.addComponent(tblUploads);
//        mainLayout.addComponent(gridBtnSave);
//        setCompositionRoot(root);
    }

    private GridLayout buildGridUpload() {
        uploadInfoLayout = new GridLayout(4, 1);
        CommonUtils.setBasicAttributeLayout(uploadInfoLayout, GRID_UPLOAD_CAPTION, true);
        uFileCommonInfo = new Upload();
        uFileCommonInfo.setCaption(Constants.NULL);
        uFileCommonInfo.setWidth("100%");

        uploadInfoLayout.addComponent(uFileCommonInfo, 0, 0, 1, 0);
        uploadInfoLayout.setComponentAlignment(uFileCommonInfo, Alignment.MIDDLE_CENTER);

        linkTemplate = new Link(LINK_CAPTION, FontAwesome.LINK);
        linkTemplate.setImmediate(true);
        File file = new File(Constants.PATH_TEMPLATE + "Thong ke diem.xlsx");
        FileDownloader downloader = new FileDownloader(file, "Thong ke diem.xlsx");
        linkTemplate.setResource(downloader);
        uploadInfoLayout.addComponent(linkTemplate, 2, 0);

        return uploadInfoLayout;
    }

    public Upload getuFileCommonInfo() {
        return uFileCommonInfo;
    }

    public void setuFileCommonInfo(Upload uFileCommonInfo) {
        this.uFileCommonInfo = uFileCommonInfo;
    }

    public CommonTableFilterPanel getTblUpload() {
        return tblUploads;
    }

    public void setTblUploads(CommonTableFilterPanel tblUploads) {
        this.tblUploads = tblUploads;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(Button btnSave) {
        this.btnSave = btnSave;
    }

    public Button getBtnUpload() {
        return btnUpload;
    }

    public void setBtnUpload(Button btnUpload) {
        this.btnUpload = btnUpload;
    }

    public Link getLinkTemplate() {
        return linkTemplate;
    }

    public void setLinkTemplate(Link linkTemplate) {
        this.linkTemplate = linkTemplate;
    }

}
