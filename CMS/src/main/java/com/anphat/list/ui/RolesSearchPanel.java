/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.cms.component.CommonOnePanelUI;
import com.cms.component.GridManyButton;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.Constants;
import com.cms.utils.MakeURL;

/**
 *
 * @author QuyenDM
 */


public class RolesSearchPanel extends VerticalLayout {

    private GridLayout searchLayout;
    private GridManyButton gridManyButton;
    private Button btnSearch;
    private Button btnRefresh;
    private CommonTableFilterPanel tblRoles;

    private Label lblCode;
    private TextField txtCode;
    private Label lblName;
    private TextField txtName;
    private Label lblDescription;
    private TextArea txtDescription;
    private Label lblStatus;
    private ComboBox cbxStatus;

    public RolesSearchPanel() {
        buildMainLayout();
    }

    private void buildMainLayout() {
        setImmediate(true);
        setWidth("100%");
        setHeight("-1px");
        setMargin(true);
        setSpacing(true);
        buildSearchLayout();
        addComponent(searchLayout);
        
        gridManyButton = new GridManyButton(new String[]{Constants.BUTTON_SEARCH, Constants.BUTTON_REFRESH});
        btnSearch = gridManyButton.getBtnCommon().get(0);
        btnRefresh = gridManyButton.getBtnCommon().get(1);
        addComponent(gridManyButton);
        setComponentAlignment(gridManyButton, Alignment.MIDDLE_CENTER);
        
        tblRoles = new CommonTableFilterPanel();
        tblRoles.setImmediate(true);
        tblRoles.setWidth("100%");
        tblRoles.setHeight("-1px");
        tblRoles.getHorizoltalLayout().setVisible(false);
        addComponent(tblRoles);
    }

    public void buildSearchLayout() {
        searchLayout = new GridLayout();
        searchLayout.setCaption(MakeURL.makeURLForGrid(BundleUtils.getString("caption.search.info")));
        searchLayout.setCaptionAsHtml(true);
        searchLayout.setImmediate(false);
        searchLayout.setWidth("100.0%");
        searchLayout.setHeight("-1px");
        searchLayout.setMargin(true);
        searchLayout.setSpacing(true);
        searchLayout.setColumns(4);
        searchLayout.setRows(2);
        searchLayout.setStyleName("custom-feildset");

        lblCode = new Label();
        lblCode.setImmediate(false);
        lblCode.setWidth("100.0%");
        lblCode.setHeight("-1px");
        lblCode.setValue(BundleUtils.getString("label.Roles.code"));
        searchLayout.addComponent(lblCode, 0, 0);

        txtCode = new TextField();
        txtCode.setImmediate(false);
        txtCode.setWidth("100.0%");
        txtCode.setHeight("-1px");
        searchLayout.addComponent(txtCode, 1, 0);
        lblName = new Label();
        lblName.setImmediate(false);
        lblName.setWidth("100.0%");
        lblName.setHeight("-1px");
        lblName.setValue(BundleUtils.getString("label.Roles.name"));
        searchLayout.addComponent(lblName, 2, 0);

        txtName = new TextField();
        txtName.setImmediate(false);
        txtName.setWidth("100.0%");
        txtName.setHeight("-1px");
        searchLayout.addComponent(txtName, 3, 0);
        lblDescription = new Label();
        lblDescription.setImmediate(false);
        lblDescription.setWidth("100.0%");
        lblDescription.setHeight("-1px");
        lblDescription.setValue(BundleUtils.getString("label.Roles.description"));
        searchLayout.addComponent(lblDescription, 0, 1);

        txtDescription = new TextArea();
        txtDescription.setImmediate(false);
        txtDescription.setWidth("100.0%");
        txtDescription.setHeight("-1px");
        searchLayout.addComponent(txtDescription, 1, 1);
        lblStatus = new Label();
        lblStatus.setImmediate(false);
        lblStatus.setWidth("100.0%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("label.Roles.status"));
        searchLayout.addComponent(lblStatus, 2, 1);

        cbxStatus = new ComboBox();
        cbxStatus.setImmediate(false);
        cbxStatus.setWidth("100.0%");
        cbxStatus.setHeight("-1px");
        searchLayout.addComponent(cbxStatus, 3, 1);

    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(Button btnSearch) {
        this.btnSearch = btnSearch;
    }

    public Button getBtnRefresh() {
        return btnRefresh;
    }

    public void setBtnRefresh(Button btnRefresh) {
        this.btnRefresh = btnRefresh;
    }

    public CommonTableFilterPanel getTblRoles() {
        return tblRoles;
    }

    public void setTblRoles(CommonTableFilterPanel tblRoles) {
        this.tblRoles = tblRoles;
    }

    public GridLayout getSearchLayout() {
        return searchLayout;
    }

    public void setSearchLayout(GridLayout searchLayout) {
        this.searchLayout = searchLayout;
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

    public void setTblRoleVisiableOnly(){
        searchLayout.setVisible(false);
        gridManyButton.setVisible(false);
    }
}
