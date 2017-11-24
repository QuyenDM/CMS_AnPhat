/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.anphat.list.controller.ServicesController;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
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
public class ServicesView extends CommonOnePanelUI implements View {

    private GridLayout searchLayout;
    private Button btnSearch;
    private Button btnRefresh;
    private CommonTableFilterPanel tblServices;

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

    public ServicesView() {

        layoutMain.setMargin(true);
        layoutMain.setSpacing(true);
        buildSearchLayout();
        layoutMain.addComponent(searchLayout);
        panelMain.setCaption(BundleUtils.getString("title.Services"));

        GridManyButton gridManyButton = new GridManyButton(new String[]{Constants.BUTTON_SEARCH, Constants.BUTTON_REFRESH});
        btnSearch = gridManyButton.getBtnCommon().get(0);
        btnRefresh = gridManyButton.getBtnCommon().get(1);
        layoutMain.addComponent(gridManyButton);
        layoutMain.setComponentAlignment(gridManyButton, Alignment.MIDDLE_CENTER);
        tblServices = new CommonTableFilterPanel();
        tblServices.setImmediate(true);
        tblServices.setWidth("100%");
        tblServices.setHeight("-1px");
        tblServices.getHorizoltalLayout().setVisible(false);
        layoutMain.addComponent(tblServices);

//        btnPrintBB.setEnabled(false);
        ServicesController servicesController = new ServicesController(this);
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
        searchLayout.setRows(10);
        searchLayout.setStyleName("custom-feildset");

        lblCode = new Label();
        lblCode.setImmediate(false);
        lblCode.setWidth("100.0%");
        lblCode.setHeight("-1px");
        lblCode.setValue(BundleUtils.getString("label.Services.code"));
        searchLayout.addComponent(lblCode, 0, 0);


     txtCode = new TextField();
        txtCode.setImmediate(false);
        txtCode.setWidth("100.0%");
        txtCode.setHeight("-1px");
        searchLayout.addComponent(txtCode, 1, 0);
        lblDescription = new Label();
        lblDescription.setImmediate(false);
        lblDescription.setWidth("100.0%");
        lblDescription.setHeight("-1px");
        lblDescription.setValue(BundleUtils.getString("label.Services.description"));
        searchLayout.addComponent(lblDescription, 2, 0);


     txtDescription = new TextArea();
        txtDescription.setImmediate(false);
        txtDescription.setWidth("100.0%");
        txtDescription.setHeight("-1px");
        searchLayout.addComponent(txtDescription, 3, 0);
        lblExpiryDate = new Label();
        lblExpiryDate.setImmediate(false);
        lblExpiryDate.setWidth("100.0%");
        lblExpiryDate.setHeight("-1px");
        lblExpiryDate.setValue(BundleUtils.getString("label.Services.expiryDate"));
        searchLayout.addComponent(lblExpiryDate, 0, 1);


     popExpiryDate = new PopupDateField();
        popExpiryDate.setImmediate(false);
        popExpiryDate.setWidth("100.0%");
        popExpiryDate.setHeight("-1px");
        searchLayout.addComponent(popExpiryDate, 1, 1);
        lblIssueDate = new Label();
        lblIssueDate.setImmediate(false);
        lblIssueDate.setWidth("100.0%");
        lblIssueDate.setHeight("-1px");
        lblIssueDate.setValue(BundleUtils.getString("label.Services.issueDate"));
        searchLayout.addComponent(lblIssueDate, 2, 1);


     popIssueDate = new PopupDateField();
        popIssueDate.setImmediate(false);
        popIssueDate.setWidth("100.0%");
        popIssueDate.setHeight("-1px");
        searchLayout.addComponent(popIssueDate, 3, 1);
        lblName = new Label();
        lblName.setImmediate(false);
        lblName.setWidth("100.0%");
        lblName.setHeight("-1px");
        lblName.setValue(BundleUtils.getString("label.Services.name"));
        searchLayout.addComponent(lblName, 0, 2);


     txtName = new TextField();
        txtName.setImmediate(false);
        txtName.setWidth("100.0%");
        txtName.setHeight("-1px");
        searchLayout.addComponent(txtName, 1, 2);
        lblServiceGroup = new Label();
        lblServiceGroup.setImmediate(false);
        lblServiceGroup.setWidth("100.0%");
        lblServiceGroup.setHeight("-1px");
        lblServiceGroup.setValue(BundleUtils.getString("label.Services.serviceGroup"));
        searchLayout.addComponent(lblServiceGroup, 2, 2);


     txtServiceGroup = new TextField();
        txtServiceGroup.setImmediate(false);
        txtServiceGroup.setWidth("100.0%");
        txtServiceGroup.setHeight("-1px");
        searchLayout.addComponent(txtServiceGroup, 3, 2);
        lblStatus = new Label();
        lblStatus.setImmediate(false);
        lblStatus.setWidth("100.0%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("label.Services.status"));
        searchLayout.addComponent(lblStatus, 0, 3);


     cbxStatus = new ComboBox();
        cbxStatus.setImmediate(false);
        cbxStatus.setWidth("100.0%");
        cbxStatus.setHeight("-1px");
        searchLayout.addComponent(cbxStatus, 1, 3);
        lblType = new Label();
        lblType.setImmediate(false);
        lblType.setWidth("100.0%");
        lblType.setHeight("-1px");
        lblType.setValue(BundleUtils.getString("label.Services.type"));
        searchLayout.addComponent(lblType, 2, 3);


     cbxType = new ComboBox();
        cbxType.setImmediate(false);
        cbxType.setWidth("100.0%");
        cbxType.setHeight("-1px");
        searchLayout.addComponent(cbxType, 3, 3);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public VerticalLayout getMainLayout() {
        return layoutMain;
    }

    public void setMainLayout(VerticalLayout layoutMain) {
        this.layoutMain = layoutMain;
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

    public CommonTableFilterPanel getTblServices() {
        return tblServices;
    }

    public void setTblServices(CommonTableFilterPanel tblServices) {
        this.tblServices = tblServices;
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

