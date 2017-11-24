/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextArea;
;
import com.vaadin.ui.TextField;
import com.cms.component.GridManyButton;
import com.cms.utils.BundleUtils;
import com.cms.utils.Constants;

/**
 *
 * @author QuyenDM
 */


public class PopupAddRoles extends Window {

    private VerticalLayout mainLayout = new VerticalLayout();
    private GridLayout addRolesLayout;
    private Button btnSave;
    private Button btnClose;
    private Label lblCode;
    private TextField txtCode;
    private Label lblName;
    private TextField txtName;
    private Label lblDescription;
    private TextArea txtDescription;
    private Label lblStatus;
    private ComboBox cbxStatus;

    public PopupAddRoles() {
        mainLayout.setImmediate(true);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("-1px");
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setStyleName("main-popup");

        addRolesLayout = new GridLayout();
        addRolesLayout.setImmediate(false);
        addRolesLayout.setWidth("100.0%");
        addRolesLayout.setHeight("-1px");
        addRolesLayout.setMargin(true);
        addRolesLayout.setSpacing(true);
        addRolesLayout.setColumns(4);
        addRolesLayout.setRows(2);
        setWidth("70.0%");
        setHeight("-1px");
        setModal(true);
        lblCode = new Label();
        lblCode.setImmediate(false);
        lblCode.setWidth("100.0%");
        lblCode.setHeight("-1px");
        lblCode.setValue(BundleUtils.getString("label.Roles.code"));
        addRolesLayout.addComponent(lblCode, 0, 0);

        txtCode = new TextField();
        txtCode.setImmediate(false);
        txtCode.setWidth("100.0%");
        txtCode.setHeight("-1px");
        addRolesLayout.addComponent(txtCode, 1, 0);
        lblName = new Label();
        lblName.setImmediate(false);
        lblName.setWidth("100.0%");
        lblName.setHeight("-1px");
        lblName.setValue(BundleUtils.getString("label.Roles.name"));
        addRolesLayout.addComponent(lblName, 2, 0);

        txtName = new TextField();
        txtName.setImmediate(false);
        txtName.setWidth("100.0%");
        txtName.setHeight("-1px");
        addRolesLayout.addComponent(txtName, 3, 0);
        lblDescription = new Label();
        lblDescription.setImmediate(false);
        lblDescription.setWidth("100.0%");
        lblDescription.setHeight("-1px");
        lblDescription.setValue(BundleUtils.getString("label.Roles.description"));
        addRolesLayout.addComponent(lblDescription, 0, 1);

        txtDescription = new TextArea();
        txtDescription.setImmediate(false);
        txtDescription.setWidth("100.0%");
        txtDescription.setHeight("-1px");
        addRolesLayout.addComponent(txtDescription, 1, 1);
        lblStatus = new Label();
        lblStatus.setImmediate(false);
        lblStatus.setWidth("100.0%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("label.Roles.status"));
        addRolesLayout.addComponent(lblStatus, 2, 1);

        cbxStatus = new ComboBox();
        cbxStatus.setImmediate(false);
        cbxStatus.setWidth("100.0%");
        cbxStatus.setHeight("-1px");
        addRolesLayout.addComponent(cbxStatus, 3, 1);

        mainLayout.addComponent(addRolesLayout);

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

    public GridLayout getAddRolesLayout() {
        return addRolesLayout;
    }

    public void setAddRolesLayout(GridLayout addRolesLayout) {
        this.addRolesLayout = addRolesLayout;
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

    public TextArea getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(TextArea txtDescription) {
        this.txtDescription = txtDescription;
    }

    public ComboBox getCbxStatus() {
        return cbxStatus;
    }

    public void setCbxStatus(ComboBox cbxStatus) {
        this.cbxStatus = cbxStatus;
    }

}
