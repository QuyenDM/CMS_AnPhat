/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.cms.component.CommonDialog;
import com.cms.component.GridManyButton;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.ComboBox;
;
import com.vaadin.ui.TextField;

/**
 *
 * @author QuyenDM
 */


public class PopupAddPriceInfo extends CommonDialog {

    private VerticalLayout mainLayout = new VerticalLayout();
    private GridLayout addPriceInfoLayout;
    private Button btnSave;
    private Button btnClose;
    private Label lblCode;
    private TextField txtCode;
    private Label lblName;
    private TextField txtName;
    private Label lblPrice;
    private TextField txtPrice;
    private Label lblTokenPrice;
    private TextField txtTokenPrice;
    private Label lblProvider;
    private ComboBox cbxProvider;
    private Label lblType;
    private ComboBox cbxType;
    private Label lblStatus;
    private ComboBox cbxStatus;
    private boolean isUpdate;

    public PopupAddPriceInfo() {
        setCaption(BundleUtils.getString("dialog.PriceInfo.caption"));
        mainLayout.setImmediate(true);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("-1px");
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setStyleName("main-popup");

        addPriceInfoLayout = new GridLayout();
        CommonUtils.setBasicAttributeLayout(addPriceInfoLayout, BundleUtils.getString("table.PriceInfo.caption"), true);
        addPriceInfoLayout.setColumns(4);
        addPriceInfoLayout.setRows(4);
        setWidth("80.0%");
        setHeight("-1px");
        setModal(true);
        lblCode = new Label();
        lblCode.setImmediate(false);
        lblCode.setWidth("100.0%");
        lblCode.setHeight("-1px");
        lblCode.setValue(BundleUtils.getString("label.PriceInfo.code"));
        addPriceInfoLayout.addComponent(lblCode, 0, 0);

        txtCode = new TextField();
        txtCode.setImmediate(false);
        txtCode.setWidth("100.0%");
        txtCode.setHeight("-1px");
        addPriceInfoLayout.addComponent(txtCode, 1, 0);
        lblName = new Label();
        lblName.setImmediate(false);
        lblName.setWidth("100.0%");
        lblName.setHeight("-1px");
        lblName.setValue(BundleUtils.getString("label.PriceInfo.name"));
        addPriceInfoLayout.addComponent(lblName, 2, 0);

        txtName = new TextField();
        txtName.setImmediate(false);
        txtName.setWidth("100.0%");
        txtName.setHeight("-1px");
        addPriceInfoLayout.addComponent(txtName, 3, 0);
        lblPrice = new Label();
        lblPrice.setImmediate(false);
        lblPrice.setWidth("100.0%");
        lblPrice.setHeight("-1px");
        lblPrice.setValue(BundleUtils.getString("label.PriceInfo.price"));
        addPriceInfoLayout.addComponent(lblPrice, 0, 1);

        txtPrice = new TextField();
        txtPrice.setImmediate(false);
        txtPrice.setWidth("100.0%");
        txtPrice.setHeight("-1px");
        addPriceInfoLayout.addComponent(txtPrice, 1, 1);

        lblTokenPrice = new Label();
        lblTokenPrice.setImmediate(false);
        lblTokenPrice.setWidth("100.0%");
        lblTokenPrice.setHeight("-1px");
        lblTokenPrice.setValue(BundleUtils.getString("label.PriceInfo.tokenPrice"));
        addPriceInfoLayout.addComponent(lblTokenPrice, 2, 1);

        txtTokenPrice = new TextField();
        txtTokenPrice.setImmediate(false);
        txtTokenPrice.setWidth("100.0%");
        txtTokenPrice.setHeight("-1px");
        addPriceInfoLayout.addComponent(txtTokenPrice, 3, 1);

        lblProvider = new Label();
        lblProvider.setImmediate(false);
        lblProvider.setWidth("100.0%");
        lblProvider.setHeight("-1px");
        lblProvider.setValue(BundleUtils.getString("label.PriceInfo.provider"));
        addPriceInfoLayout.addComponent(lblProvider, 0, 2);

        cbxProvider = new ComboBox();
        cbxProvider.setImmediate(false);
        cbxProvider.setWidth("100.0%");
        cbxProvider.setHeight("-1px");
        addPriceInfoLayout.addComponent(cbxProvider, 1, 2);

        lblType = new Label();
        lblType.setImmediate(false);
        lblType.setWidth("100.0%");
        lblType.setHeight("-1px");
        lblType.setValue(BundleUtils.getString("label.PriceInfo.type"));
        addPriceInfoLayout.addComponent(lblType, 2, 2);

        cbxType = new ComboBox();
        cbxType.setImmediate(false);
        cbxType.setWidth("100.0%");
        cbxType.setHeight("-1px");
        addPriceInfoLayout.addComponent(cbxType, 3, 2);

        lblStatus = new Label();
        lblStatus.setImmediate(false);
        lblStatus.setWidth("100.0%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("label.PriceInfo.status"));
        addPriceInfoLayout.addComponent(lblStatus, 0, 3);

        cbxStatus = new ComboBox();
        cbxStatus.setImmediate(false);
        cbxStatus.setWidth("100.0%");
        cbxStatus.setHeight("-1px");
        addPriceInfoLayout.addComponent(cbxStatus, 1, 3);

        mainLayout.addComponent(addPriceInfoLayout);

        GridManyButton gridBtnPrint = CommonUtils.getCommonButtonDialog(this);
        mainLayout.addComponent(gridBtnPrint);
        btnSave = gridBtnPrint.getBtnCommon().get(0);
        btnClose = gridBtnPrint.getBtnCommon().get(1);
        setContent(mainLayout);
    }

    public VerticalLayout getMainLayout() {
        return mainLayout;
    }

    public void setMainLayout(VerticalLayout mainLayout) {
        this.mainLayout = mainLayout;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(Button btnSave) {
        this.btnSave = btnSave;
    }

    public Button getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(Button btnClose) {
        this.btnClose = btnClose;
    }

    public GridLayout getAddPriceInfoLayout() {
        return addPriceInfoLayout;
    }

    public void setAddPriceInfoLayout(GridLayout addPriceInfoLayout) {
        this.addPriceInfoLayout = addPriceInfoLayout;
    }

    public TextField getTxtCode() {
        return txtCode;
    }

    public void setTxtCode(TextField txtCode) {
        this.txtCode = txtCode;
    }

    public TextField getTxtName() {
        return txtName;
    }

    public void setTxtName(TextField txtName) {
        this.txtName = txtName;
    }

    public TextField getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(TextField txtPrice) {
        this.txtPrice = txtPrice;
    }

    public TextField getTxtTokenPrice() {
        return txtTokenPrice;
    }

    public void setTxtTokenPrice(TextField txtTokenPrice) {
        this.txtTokenPrice = txtTokenPrice;
    }

    public ComboBox getCbxProvider() {
        return cbxProvider;
    }

    public void setCbxProvider(ComboBox cbxProvider) {
        this.cbxProvider = cbxProvider;
    }

    public ComboBox getCbxType() {
        return cbxType;
    }

    public void setCbxType(ComboBox cbxType) {
        this.cbxType = cbxType;
    }

    public ComboBox getCbxStatus() {
        return cbxStatus;
    }

    public void setCbxStatus(ComboBox cbxStatus) {
        this.cbxStatus = cbxStatus;
    }

    public boolean isIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
        if (isUpdate) {
            this.setCaption(BundleUtils.getString("dialog.PriceInfo.caption.edit"));
        }
    }
}
