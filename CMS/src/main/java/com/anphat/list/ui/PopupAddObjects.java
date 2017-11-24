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

/**
 *
 * @author QuyenDM
 */


public class PopupAddObjects extends Window {

    private VerticalLayout mainLayout = new VerticalLayout();
    private GridLayout addObjectsLayout;
    private Button btnSave;
    private Button btnClose;
    private Label lblCode;
    private TextField txtCode;
    private Label lblName;
    private TextField txtName;
    private Label lblUrl;
    private TextField txtUrl;
    private Label lblDescription;
    private TextArea txtDescription;
    private Label lblObjectType;
    private TextField txtObjectType;
    private Label lblStatus;
    private ComboBox cbxStatus;

    public PopupAddObjects() {
        mainLayout.setImmediate(true);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("-1px");
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setStyleName("main-popup");

        addObjectsLayout = new GridLayout();
        addObjectsLayout.setImmediate(true);
        addObjectsLayout.setWidth("100.0%");
        addObjectsLayout.setHeight("-1px");
        addObjectsLayout.setMargin(true);
        addObjectsLayout.setSpacing(true);
        addObjectsLayout.setColumns(4);
        addObjectsLayout.setRows(3);
        setWidth("70.0%");
        setHeight("-1px");
        setModal(true);
        lblCode = new Label();
        lblCode.setImmediate(true);
        lblCode.setWidth("100.0%");
        lblCode.setHeight("-1px");
        lblCode.setValue(BundleUtils.getString("label.Objects.code"));
        addObjectsLayout.addComponent(lblCode, 0, 0);

        txtCode = new TextField();
        txtCode.setImmediate(true);
        txtCode.setWidth("100.0%");
        txtCode.setHeight("-1px");
        addObjectsLayout.addComponent(txtCode, 1, 0);
        lblName = new Label();
        lblName.setImmediate(true);
        lblName.setWidth("100.0%");
        lblName.setHeight("-1px");
        lblName.setValue(BundleUtils.getString("label.Objects.name"));
        addObjectsLayout.addComponent(lblName, 2, 0);

        txtName = new TextField();
        txtName.setImmediate(true);
        txtName.setWidth("100.0%");
        txtName.setHeight("-1px");
        addObjectsLayout.addComponent(txtName, 3, 0);
        lblUrl = new Label();
        lblUrl.setImmediate(true);
        lblUrl.setWidth("100.0%");
        lblUrl.setHeight("-1px");
        lblUrl.setValue(BundleUtils.getString("label.Objects.url"));
        addObjectsLayout.addComponent(lblUrl, 0, 1);

        txtUrl = new TextField();
        txtUrl.setImmediate(true);
        txtUrl.setWidth("100.0%");
        txtUrl.setHeight("-1px");
        addObjectsLayout.addComponent(txtUrl, 1, 1);
        lblDescription = new Label();
        lblDescription.setImmediate(true);
        lblDescription.setWidth("100.0%");
        lblDescription.setHeight("-1px");
        lblDescription.setValue(BundleUtils.getString("label.Objects.description"));
        addObjectsLayout.addComponent(lblDescription, 2, 1);

        txtDescription = new TextArea();
        txtDescription.setImmediate(true);
        txtDescription.setWidth("100.0%");
        txtDescription.setHeight("-1px");
        addObjectsLayout.addComponent(txtDescription, 3, 1);
        lblObjectType = new Label();
        lblObjectType.setImmediate(true);
        lblObjectType.setWidth("100.0%");
        lblObjectType.setHeight("-1px");
        lblObjectType.setValue(BundleUtils.getString("label.Objects.objectType"));
        addObjectsLayout.addComponent(lblObjectType, 0, 2);

        txtObjectType = new TextField();
        txtObjectType.setImmediate(true);
        txtObjectType.setWidth("100.0%");
        txtObjectType.setHeight("-1px");
        addObjectsLayout.addComponent(txtObjectType, 1, 2);
        lblStatus = new Label();
        lblStatus.setImmediate(true);
        lblStatus.setWidth("100.0%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("label.Objects.status"));
        addObjectsLayout.addComponent(lblStatus, 2, 2);

        cbxStatus = new ComboBox();
        cbxStatus.setImmediate(true);
        cbxStatus.setWidth("100.0%");
        cbxStatus.setHeight("-1px");
        addObjectsLayout.addComponent(cbxStatus, 3, 2);

        mainLayout.addComponent(addObjectsLayout);

        GridManyButton gridBtnPrint = new GridManyButton(new String[]{BundleUtils.getString("btn.ok"), BundleUtils.getString("btn.close")});
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

    public GridLayout getAddObjectsLayout() {
        return addObjectsLayout;
    }

    public void setAddObjectsLayout(GridLayout addObjectsLayout) {
        this.addObjectsLayout = addObjectsLayout;
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

    public TextField getTxtUrl() {
        return txtUrl;
    }

    public void setTxtUrl(TextField txtUrl) {
        this.txtUrl = txtUrl;
    }

    public TextArea getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(TextArea txtDescription) {
        this.txtDescription = txtDescription;
    }

    public TextField getTxtObjectType() {
        return txtObjectType;
    }

    public void setTxtObjectType(TextField txtObjectType) {
        this.txtObjectType = txtObjectType;
    }

    public ComboBox getCbxStatus() {
        return cbxStatus;
    }

    public void setCbxStatus(ComboBox cbxStatus) {
        this.cbxStatus = cbxStatus;
    }

}
