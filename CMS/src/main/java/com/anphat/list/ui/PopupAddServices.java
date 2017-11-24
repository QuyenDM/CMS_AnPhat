/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.cms.component.GridManyButton;
import com.cms.utils.BundleUtils;
import com.cms.utils.Constants;

/**
 *
 * @author QuyenDM
 */
public class PopupAddServices extends Window {

    private VerticalLayout mainLayout = new VerticalLayout();
    private GridLayout addServicesLayout;
    private Button btnSave;
    private Button btnClose;
    private Label lblCode;
    private TextField txtCode;
    private Label lblDescription;
    private TextArea txtDescription;
    private Label lblExpiryDate;
    private PopupDateField popExpiryDate;
    private Label lblIssueDate;
    private PopupDateField popIssueDate;
    private Label lblName;
    private TextField txtName;
    private Label lblServiceGroup;
    private TextField txtServiceGroup;
    private Label lblStatus;
    private ComboBox cbxStatus;
    private Label lblType;
    private ComboBox cbxType;

    public PopupAddServices() {
        mainLayout.setImmediate(true);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("-1px");
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setStyleName("main-popup");

        addServicesLayout = new GridLayout();
        addServicesLayout.setImmediate(false);
        addServicesLayout.setWidth("100.0%");
        addServicesLayout.setHeight("-1px");
        addServicesLayout.setMargin(true);
        addServicesLayout.setSpacing(true);
        addServicesLayout.setColumns(4);
        addServicesLayout.setRows(10);
        setWidth("70.0%");
        setHeight("-1px");
        setModal(true);
        lblCode = new Label();
        lblCode.setImmediate(false);
        lblCode.setWidth("100.0%");
        lblCode.setHeight("-1px");
        lblCode.setValue(BundleUtils.getString("label.Services.code"));
        addServicesLayout.addComponent(lblCode, 0, 0);

        txtCode = new TextField();
        txtCode.setImmediate(false);
        txtCode.setWidth("100.0%");
        txtCode.setHeight("-1px");
        addServicesLayout.addComponent(txtCode, 1, 0);
        lblDescription = new Label();
        lblDescription.setImmediate(false);
        lblDescription.setWidth("100.0%");
        lblDescription.setHeight("-1px");
        lblDescription.setValue(BundleUtils.getString("label.Services.description"));
        addServicesLayout.addComponent(lblDescription, 2, 0);

        txtDescription = new TextArea();
        txtDescription.setImmediate(false);
        txtDescription.setWidth("100.0%");
        txtDescription.setHeight("-1px");
        addServicesLayout.addComponent(txtDescription, 3, 0);
        lblExpiryDate = new Label();
        lblExpiryDate.setImmediate(false);
        lblExpiryDate.setWidth("100.0%");
        lblExpiryDate.setHeight("-1px");
        lblExpiryDate.setValue(BundleUtils.getString("label.Services.expiryDate"));
        addServicesLayout.addComponent(lblExpiryDate, 0, 1);

        popExpiryDate = new PopupDateField();
        popExpiryDate.setImmediate(false);
        popExpiryDate.setWidth("100.0%");
        popExpiryDate.setHeight("-1px");
        addServicesLayout.addComponent(popExpiryDate, 1, 1);
        lblIssueDate = new Label();
        lblIssueDate.setImmediate(false);
        lblIssueDate.setWidth("100.0%");
        lblIssueDate.setHeight("-1px");
        lblIssueDate.setValue(BundleUtils.getString("label.Services.issueDate"));
        addServicesLayout.addComponent(lblIssueDate, 2, 1);

        popIssueDate = new PopupDateField();
        popIssueDate.setImmediate(false);
        popIssueDate.setWidth("100.0%");
        popIssueDate.setHeight("-1px");
        addServicesLayout.addComponent(popIssueDate, 3, 1);
        lblName = new Label();
        lblName.setImmediate(false);
        lblName.setWidth("100.0%");
        lblName.setHeight("-1px");
        lblName.setValue(BundleUtils.getString("label.Services.name"));
        addServicesLayout.addComponent(lblName, 0, 2);

        txtName = new TextField();
        txtName.setImmediate(false);
        txtName.setWidth("100.0%");
        txtName.setHeight("-1px");
        addServicesLayout.addComponent(txtName, 1, 2);
        lblServiceGroup = new Label();
        lblServiceGroup.setImmediate(false);
        lblServiceGroup.setWidth("100.0%");
        lblServiceGroup.setHeight("-1px");
        lblServiceGroup.setValue(BundleUtils.getString("label.Services.serviceGroup"));
        addServicesLayout.addComponent(lblServiceGroup, 2, 2);

        txtServiceGroup = new TextField();
        txtServiceGroup.setImmediate(false);
        txtServiceGroup.setWidth("100.0%");
        txtServiceGroup.setHeight("-1px");
        addServicesLayout.addComponent(txtServiceGroup, 3, 2);
        lblStatus = new Label();
        lblStatus.setImmediate(false);
        lblStatus.setWidth("100.0%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("label.Services.status"));
        addServicesLayout.addComponent(lblStatus, 0, 3);

        cbxStatus = new ComboBox();
        cbxStatus.setImmediate(false);
        cbxStatus.setWidth("100.0%");
        cbxStatus.setHeight("-1px");
        addServicesLayout.addComponent(cbxStatus, 1, 3);
        lblType = new Label();
        lblType.setImmediate(false);
        lblType.setWidth("100.0%");
        lblType.setHeight("-1px");
        lblType.setValue(BundleUtils.getString("label.Services.type"));
        addServicesLayout.addComponent(lblType, 2, 3);

        cbxType = new ComboBox();
        cbxType.setImmediate(false);
        cbxType.setWidth("100.0%");
        cbxType.setHeight("-1px");
        addServicesLayout.addComponent(cbxType, 3, 3);

        mainLayout.addComponent(addServicesLayout);

        GridManyButton gridBtnPrint = new GridManyButton(new String[]{Constants.BUTTON_SAVE, Constants.BUTTON_CLOSE});
        mainLayout.addComponent(gridBtnPrint);
        btnSave = gridBtnPrint.getBtnCommon().get(0);
        btnClose = gridBtnPrint.getBtnCommon().get(1);
        setContent(mainLayout);
        txtCode.focus();
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

    public GridLayout getAddServicesLayout() {
        return addServicesLayout;
    }

    public void setAddServicesLayout(GridLayout addServicesLayout) {
        this.addServicesLayout = addServicesLayout;
    }

    public TextField getTxtCode() {
        return txtCode;
    }

    public void setTxtCode(TextField txtCode) {
        this.txtCode = txtCode;
    }

    public TextArea getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(TextArea txtDescription) {
        this.txtDescription = txtDescription;
    }

    public PopupDateField getPopExpiryDate() {
        return popExpiryDate;
    }

    public void setPopExpiryDate(PopupDateField popExpiryDate) {
        this.popExpiryDate = popExpiryDate;
    }

    public PopupDateField getPopIssueDate() {
        return popIssueDate;
    }

    public void setPopIssueDate(PopupDateField popIssueDate) {
        this.popIssueDate = popIssueDate;
    }

    public TextField getTxtName() {
        return txtName;
    }

    public void setTxtName(TextField txtName) {
        this.txtName = txtName;
    }

    public TextField getTxtServiceGroup() {
        return txtServiceGroup;
    }

    public void setTxtServiceGroup(TextField txtServiceGroup) {
        this.txtServiceGroup = txtServiceGroup;
    }

    public ComboBox getCbxStatus() {
        return cbxStatus;
    }

    public void setCbxStatus(ComboBox cbxStatus) {
        this.cbxStatus = cbxStatus;
    }

    public ComboBox getCbxType() {
        return cbxType;
    }

    public void setCbxType(ComboBox cbxType) {
        this.cbxType = cbxType;
    }

}
