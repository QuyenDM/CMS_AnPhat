/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.cms.component.GridManyButton;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.ShortcutUtils;
import com.vaadin.server.ThemeResource;

/**
 *
 * @author quyen
 */
public class ImportCustomerUploadForm extends CustomComponent {

    private VerticalLayout root;
    private GridLayout uploadInfoLayout;
    private Upload uFileCommonInfo;
    private Upload uFileTermInfo;
    private ComboBox choiceTypeUpload;
    private Button btnUpload;
    private CommonTableFilterPanel tblUploadCommon;
    private CommonTableFilterPanel tblUploadTerm;
    private Button btnSave;
    private Link linkTemplate;

    private GridLayout gridTermInfo;
    private ComboBox cboService;
    private ComboBox cboMineName;
    private Button btnAddMineName;
    private TextField tfMineName;
    private final String GRID_UPLOAD_CAPTION = BundleUtils.getString("import.customer.infor.caption");
    private final String LINK_CAPTION = BundleUtils.getString("import.link.template");

    public ImportCustomerUploadForm() {
        buildRootLayout();
    }

    private void buildRootLayout() {
        root = new VerticalLayout();
        root.setImmediate(false);
        root.setWidth("100%");
        root.setHeight("100%");
        root.setMargin(true);
        root.setSpacing(true);
        // top-level component properties
        setWidth("100.0%");
        setHeight("100.0%");
        //Khoi tao cac thanh phan
        uploadInfoLayout = buildGridUpload();
        buildGridTermInfo();
        tblUploadCommon = new CommonTableFilterPanel();
        tblUploadTerm = new CommonTableFilterPanel();
        GridManyButton gridBtnUpload = new GridManyButton(new String[]{Constants.BUTTON_UPLOAD});
        btnUpload = gridBtnUpload.getBtnCommon().get(0);
        GridManyButton gridBtnSave = new GridManyButton(new String[]{Constants.BUTTON_SAVE});
        btnSave = gridBtnSave.getBtnCommon().get(0);

        //Them cac thanh phan vao grid
        root.addComponent(uploadInfoLayout);
        root.addComponent(gridTermInfo);
        root.addComponent(gridBtnUpload);
        root.addComponent(tblUploadCommon);
        root.addComponent(tblUploadTerm);
//        root.addComponent(gridBtnSave);
        setCompositionRoot(root);
    }

    private GridLayout buildGridUpload() {
        uploadInfoLayout = new GridLayout(4, 1);
        CommonUtils.setBasicAttributeLayout(uploadInfoLayout, null, false);
        choiceTypeUpload = CommonUtils.buildComboBox();
        uploadInfoLayout.addComponent(choiceTypeUpload, 1, 0, 2, 0);
//        uploadInfoLayout.setComponentAlignment(choiceTypeUpload, Alignment.MIDDLE_LEFT);
        linkTemplate = new Link(LINK_CAPTION, FontAwesome.LINK);
        linkTemplate.setImmediate(true);
        uploadInfoLayout.addComponent(linkTemplate, 3, 0);
//        uploadInfoLayout.setComponentAlignment(linkTemplate, Alignment.MIDDLE_LEFT);

        return uploadInfoLayout;
    }

    private void buildGridTermInfo() {
        gridTermInfo = new GridLayout(4, 3);
        CommonUtils.setBasicAttributeLayout(gridTermInfo, GRID_UPLOAD_CAPTION, true);
        gridTermInfo.setImmediate(true);
        gridTermInfo.setWidth("100%");
        gridTermInfo.setHeight("-1px");

        cboService = CommonUtils.buildComboBox("term.information.service");
        cboService.setRequired(true);
        cboService.setRequiredError(CommonMessages.messageRequire("customerStatusForm.service"));
        cboMineName = CommonUtils.buildComboBox("term.information.mineName");
        cboMineName.setRequired(true);
        tfMineName = CommonUtils.buildTextField(BundleUtils.getString("term.information.mineName"), 100);
        tfMineName.setRequired(true);
        gridTermInfo.addComponent(cboService, 2, 1);
        gridTermInfo.addComponent(cboMineName, 3, 1);
        btnAddMineName = new Button(BundleUtils.getString("button.add.mineName"));
        btnAddMineName.setIcon(new ThemeResource(Constants.ICON.ADD));
        btnAddMineName.setDisableOnClick(true);
        btnAddMineName.addStyleName("v-button-link");
        gridTermInfo.addComponent(btnAddMineName, 3, 2);

        uFileCommonInfo = new Upload();
        uFileCommonInfo.setCaption(Constants.NULL);
        uFileTermInfo = new Upload();
        uFileTermInfo.setCaption(Constants.NULL);
        uFileCommonInfo.setWidth("100%");
        uFileTermInfo.setWidth("100%");

        gridTermInfo.addComponent(uFileTermInfo, 0, 1, 1, 1);

        gridTermInfo.addComponent(uFileCommonInfo, 0, 0, 1, 0);
        gridTermInfo.setComponentAlignment(uFileCommonInfo, Alignment.MIDDLE_CENTER);
        gridTermInfo.setComponentAlignment(uFileTermInfo, Alignment.MIDDLE_CENTER);
    }

    /**
     * Hien thi ui tai thong tin han
     *
     * @param isTermUploadVisible
     */
    public void setVisibleUpload(boolean isTermUploadVisible) {
        uFileCommonInfo.setVisible(!isTermUploadVisible);
        tblUploadCommon.setVisible(!isTermUploadVisible);

        uFileTermInfo.setVisible(isTermUploadVisible);
        tblUploadTerm.setVisible(isTermUploadVisible);
        cboMineName.setVisible(isTermUploadVisible);
        cboService.setVisible(isTermUploadVisible);
        btnAddMineName.setVisible(isTermUploadVisible);
    }

    //Getter and Setter
    public Button getBtnUpload() {
        return btnUpload;
    }

    public CommonTableFilterPanel getTblUploadCommon() {
        return tblUploadCommon;
    }

    public CommonTableFilterPanel getTblUploadTerm() {
        return tblUploadTerm;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public Link getLinkTemplate() {
        return linkTemplate;
    }

    public Upload getuFileCommonInfo() {
        return uFileCommonInfo;
    }

    public void setuFileCommonInfo(Upload uFileCommonInfo) {
        this.uFileCommonInfo = uFileCommonInfo;
    }

    public Upload getuFileTermInfo() {
        return uFileTermInfo;
    }

    public void setuFileTermInfo(Upload uFileTermInfo) {
        this.uFileTermInfo = uFileTermInfo;
    }

    public ComboBox getChoiceTypeUpload() {
        return choiceTypeUpload;
    }

    public ComboBox getCboService() {
        return cboService;
    }

    public void setCboService(ComboBox cboService) {
        this.cboService = cboService;
    }

    public ComboBox getCboMineName() {
        return cboMineName;
    }

    public void setCboMineName(ComboBox cboMineName) {
        this.cboMineName = cboMineName;
    }

    public TextField getTfMineName() {
        return tfMineName;
    }

    public void setTfMineName(TextField tfMineName) {
        this.tfMineName = tfMineName;
    }

    //Them su kien cho nut them ds han
    public void addBtnAddMineNameListner(Button.ClickListener listener) {
        ShortcutUtils.setShortkeyAddNew(btnAddMineName);
        btnAddMineName.addClickListener(listener);
    }
}
