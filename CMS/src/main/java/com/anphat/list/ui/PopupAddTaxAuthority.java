/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.ui;

import com.cms.component.GridManyButton;
import com.cms.utils.BundleUtils;
import com.cms.utils.Constants;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author
 */
public class PopupAddTaxAuthority extends Window {

    private VerticalLayout mainLayout = new VerticalLayout();
    private GridLayout addTaxAuthorityLayout;
    private Button btnSave;
    private Button btnClose;
    private Label lblId;
    private TextField txtId;
    private Label lblMaCqt;
    private TextField txtMaCqt;
    private Label lblTenCqt;
    private TextField txtTenCqt;
    private Label lblMaTinh;
    private ComboBox cboMaTinh;
    private Label lblMaQuanHuyen;
    private TextField txtMaQuanHuyen;
    private Label lblStatus;
    private ComboBox cboStatus;

    public PopupAddTaxAuthority() {
        mainLayout.setImmediate(true);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("-1px");
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setStyleName("main-popup");

        addTaxAuthorityLayout = new GridLayout();
        addTaxAuthorityLayout.setImmediate(true);
        addTaxAuthorityLayout.setWidth("100.0%");
        addTaxAuthorityLayout.setHeight("-1px");
        addTaxAuthorityLayout.setMargin(true);
        addTaxAuthorityLayout.setSpacing(true);
        addTaxAuthorityLayout.setColumns(4);
        addTaxAuthorityLayout.setRows(3);
        setWidth("70.0%");
        setHeight("-1px");
        setModal(true);
//        lblId = new Label();
//        lblId.setImmediate(true);
//        lblId.setWidth("100.0%");
//        lblId.setHeight("-1px");
//        lblId.setValue(BundleUtils.getString("label.TaxAuthority.id"));
//        addTaxAuthorityLayout.addComponent(lblId, 0, 0);

//        txtId = new TextField();
//        txtId.setImmediate(true);
//        txtId.setWidth("100.0%");
//        txtId.setHeight("-1px");
//        addTaxAuthorityLayout.addComponent(txtId, 1, 0);
        lblMaCqt = new Label();
        lblMaCqt.setImmediate(true);
        lblMaCqt.setWidth("100.0%");
        lblMaCqt.setHeight("-1px");
        lblMaCqt.setValue(BundleUtils.getString("label.TaxAuthority.maCqt"));
        addTaxAuthorityLayout.addComponent(lblMaCqt, 0, 0);

        txtMaCqt = new TextField();
        txtMaCqt.setImmediate(true);
        txtMaCqt.setWidth("100.0%");
        txtMaCqt.setHeight("-1px");
        addTaxAuthorityLayout.addComponent(txtMaCqt, 1, 0);
        lblTenCqt = new Label();
        lblTenCqt.setImmediate(true);
        lblTenCqt.setWidth("100.0%");
        lblTenCqt.setHeight("-1px");
        lblTenCqt.setValue(BundleUtils.getString("label.TaxAuthority.tenCqt"));
        addTaxAuthorityLayout.addComponent(lblTenCqt, 2, 0);

        txtTenCqt = new TextField();
        txtTenCqt.setImmediate(true);
        txtTenCqt.setWidth("100.0%");
        txtTenCqt.setHeight("-1px");
        addTaxAuthorityLayout.addComponent(txtTenCqt, 3, 0);
        lblMaTinh = new Label();
        lblMaTinh.setImmediate(true);
        lblMaTinh.setWidth("100.0%");
        lblMaTinh.setHeight("-1px");
        lblMaTinh.setValue(BundleUtils.getString("label.TaxAuthority.maTinh"));
        addTaxAuthorityLayout.addComponent(lblMaTinh, 0, 1);

        cboMaTinh = new ComboBox();
        cboMaTinh.setImmediate(true);
        cboMaTinh.setWidth("100.0%");
        cboMaTinh.setHeight("-1px");
        addTaxAuthorityLayout.addComponent(cboMaTinh, 1, 1);
        lblMaQuanHuyen = new Label();
        lblMaQuanHuyen.setImmediate(true);
        lblMaQuanHuyen.setWidth("100.0%");
        lblMaQuanHuyen.setHeight("-1px");
        lblMaQuanHuyen.setValue(BundleUtils.getString("label.TaxAuthority.maQuanHuyen"));
        addTaxAuthorityLayout.addComponent(lblMaQuanHuyen, 2, 1);

        txtMaQuanHuyen = new TextField();
        txtMaQuanHuyen.setImmediate(true);
        txtMaQuanHuyen.setWidth("100.0%");
        txtMaQuanHuyen.setHeight("-1px");
        addTaxAuthorityLayout.addComponent(txtMaQuanHuyen, 3, 1);
        lblStatus = new Label();
        lblStatus.setImmediate(true);
        lblStatus.setWidth("100.0%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("label.TaxAuthority.status"));
        addTaxAuthorityLayout.addComponent(lblStatus, 0, 2);

        cboStatus = new ComboBox();
        cboStatus.setImmediate(true);
        cboStatus.setWidth("100.0%");
        cboStatus.setHeight("-1px");
        addTaxAuthorityLayout.addComponent(cboStatus, 1, 2);

        mainLayout.addComponent(addTaxAuthorityLayout);

        GridManyButton gridBtnPrint = new GridManyButton(new String[]{Constants.BUTTON_SAVE, Constants.BUTTON_CLOSE});
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

    public GridLayout getAddTaxAuthorityLayout() {
        return addTaxAuthorityLayout;
    }

    public void setAddTaxAuthorityLayout(GridLayout addTaxAuthorityLayout) {
        this.addTaxAuthorityLayout = addTaxAuthorityLayout;
    }

    public TextField getTxtId() {
        return txtId;
    }

    public void setTxtId(TextField txtId) {
        this.txtId = txtId;
    }

    public TextField getTxtMaCqt() {
        return txtMaCqt;
    }

    public void setTxtMaCqt(TextField txtMaCqt) {
        this.txtMaCqt = txtMaCqt;
    }

    public TextField getTxtTenCqt() {
        return txtTenCqt;
    }

    public void setTxtTenCqt(TextField txtTenCqt) {
        this.txtTenCqt = txtTenCqt;
    }

    public ComboBox getCboMaTinh() {
        return cboMaTinh;
    }

    public void setCboMaTinh(ComboBox cboMaTinh) {
        this.cboMaTinh = cboMaTinh;
    }

    public TextField getTxtMaQuanHuyen() {
        return txtMaQuanHuyen;
    }

    public void setTxtMaQuanHuyen(TextField txtMaQuanHuyen) {
        this.txtMaQuanHuyen = txtMaQuanHuyen;
    }

    public ComboBox getCboStatus() {
        return cboStatus;
    }

    public void setTxtStatus(ComboBox cboStatus) {
        this.cboStatus = cboStatus;
    }

}
