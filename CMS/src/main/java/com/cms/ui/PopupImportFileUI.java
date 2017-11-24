/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.ui;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.cms.utils.BundleUtils;
import com.cms.utils.MakeURL;

/**
 *
 * @author QUANG THIEU
 */
public class PopupImportFileUI extends Window {

    private VerticalLayout mainLayout = new VerticalLayout();
    private Upload uploadFile;
    private Link linkTemplate;
    private GridLayout gridLayout;

    public PopupImportFileUI() {
        mainLayout.setImmediate(true);
        mainLayout.setCaption(BundleUtils.getString("title.choseFile"));
        mainLayout.setWidth("100%");
        mainLayout.setHeight("-1px");
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setStyleName("main-popup");
        setWidth("30.0%");
        setHeight("-1px");
        setModal(true);

        gridLayout = new GridLayout();
//        gridLayout.setCaption(MakeURL.makeURLForGrid(BundleUtils.getString("stone.label.search.info")));
//        gridLayout.setCaptionAsHtml(true);
        gridLayout.setImmediate(false);
        gridLayout.setWidth("100.0%");
        gridLayout.setHeight("-1px");
        gridLayout.setMargin(true);
        gridLayout.setSpacing(true);
        gridLayout.setColumns(2);
        gridLayout.setRows(5);
        gridLayout.setStyleName("custom-feildset");

        uploadFile = new Upload();
        uploadFile.setImmediate(false);
        uploadFile.setWidth("100.0%");
        uploadFile.setHeight("-1px");
        gridLayout.addComponent(uploadFile, 0, 0);

        linkTemplate = new Link();
        linkTemplate.setCaption("Tải file biểu mẫu");
        linkTemplate.setImmediate(false);
        linkTemplate.setWidth("100%");
        linkTemplate.setHeight("-1px");
        gridLayout.addComponent(linkTemplate, 0, 1);

        mainLayout.addComponent(gridLayout);
        setContent(mainLayout);
    }

    public VerticalLayout getMainLayout() {
        return mainLayout;
    }

    public void setMainLayout(VerticalLayout mainLayout) {
        this.mainLayout = mainLayout;
    }

    public Upload getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(Upload uploadFile) {
        this.uploadFile = uploadFile;
    }

    public Link getLinkTemplate() {
        return linkTemplate;
    }

    public void setLinkTemplate(Link linkTemplate) {
        this.linkTemplate = linkTemplate;
    }

    public GridLayout getGridLayout() {
        return gridLayout;
    }

    public void setGridLayout(GridLayout gridLayout) {
        this.gridLayout = gridLayout;
    }
    
}
