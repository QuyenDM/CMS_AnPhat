/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;
import com.anphat.list.controller.ContractTemplateListController;
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


public class ContractTemplateListView extends CommonOnePanelUI implements View {

    private GridLayout searchLayout;
    private Button btnSearch;
    private Button btnRefresh;
    private CommonTableFilterPanel tblContractTemplateList;

    private Label lblCode;
    private TextField txtCode;
    private Label lblName;
    private TextField txtName;
    private Label lblPathFile;
    private TextField txtPathFile;
    private Label lblService;
    private ComboBox cboService;
    private Label lblType;
    private ComboBox cboType;
    private Label lblProvider;
    private ComboBox cboProvider;
    private Label lblCreatedDate;
    private PopupDateField popCreatedDate;
    private Label lblLastUpdatedDate;
    private PopupDateField popLastUpdatedDate;
    private Label lblStatus;
    private ComboBox cbxStatus;

    public ContractTemplateListView() {

        layoutMain.setMargin(true);
        layoutMain.setSpacing(true);
        buildSearchLayout();
        layoutMain.addComponent(searchLayout);
        panelMain.setCaption(BundleUtils.getString("title.ContractTemplateList"));

        GridManyButton gridManyButton = new GridManyButton(new String[]{Constants.BUTTON_SEARCH, Constants.BUTTON_REFRESH});
        btnSearch = gridManyButton.getBtnCommon().get(0);
        btnRefresh = gridManyButton.getBtnCommon().get(1);
        layoutMain.addComponent(gridManyButton);
        layoutMain.setComponentAlignment(gridManyButton, Alignment.MIDDLE_CENTER);
        tblContractTemplateList = new CommonTableFilterPanel();
        
        tblContractTemplateList.setImmediate(true);
        tblContractTemplateList.setWidth("100%");
        tblContractTemplateList.setHeight("-1px");
        tblContractTemplateList.getHorizoltalLayout().setVisible(false);
        layoutMain.addComponent(tblContractTemplateList);

//        btnPrintBB.setEnabled(false);
        ContractTemplateListController contractTemplateListController = new ContractTemplateListController(this);
    }

    private void buildSearchLayout() {
        searchLayout = new GridLayout();
        searchLayout.setCaption(MakeURL.makeURLForGrid(BundleUtils.getString("caption.search.info")));
        searchLayout.setCaptionAsHtml(true);
        searchLayout.setImmediate(false);
        searchLayout.setWidth("100.0%");
        searchLayout.setHeight("-1px");
        searchLayout.setMargin(true);
        searchLayout.setSpacing(true);
        searchLayout.setColumns(4);
        searchLayout.setRows(4);
        searchLayout.setStyleName("custom-feildset");

        lblCode = new Label();
        lblCode.setImmediate(false);
        lblCode.setWidth("100.0%");
        lblCode.setHeight("-1px");
        lblCode.setValue(BundleUtils.getString("label.ContractTemplateList.code"));
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
        lblName.setValue(BundleUtils.getString("label.ContractTemplateList.name"));
        searchLayout.addComponent(lblName, 2, 0);

        txtName = new TextField();
        txtName.setImmediate(false);
        txtName.setWidth("100.0%");
        txtName.setHeight("-1px");
        searchLayout.addComponent(txtName, 3, 0);
        
        lblPathFile = new Label();
        lblPathFile.setImmediate(false);
        lblPathFile.setWidth("100.0%");
        lblPathFile.setHeight("-1px");
        lblPathFile.setValue(BundleUtils.getString("label.ContractTemplateList.pathFile"));
//        searchLayout.addComponent(lblPathFile, 0, 1);

        txtPathFile = new TextField();
        txtPathFile.setImmediate(false);
        txtPathFile.setWidth("100.0%");
        txtPathFile.setHeight("-1px");
//        searchLayout.addComponent(txtPathFile, 1, 1);

        lblCreatedDate = new Label();
        lblCreatedDate.setImmediate(false);
        lblCreatedDate.setWidth("100.0%");
        lblCreatedDate.setHeight("-1px");
        lblCreatedDate.setValue(BundleUtils.getString("label.ContractTemplateList.createdDate"));
//        searchLayout.addComponent(lblCreatedDate, 0, 1);

        popCreatedDate = new PopupDateField();
        popCreatedDate.setImmediate(false);
        popCreatedDate.setWidth("100.0%");
        popCreatedDate.setHeight("-1px");
//        searchLayout.addComponent(popCreatedDate, 3, 1);
        lblLastUpdatedDate = new Label();
        lblLastUpdatedDate.setImmediate(false);
        lblLastUpdatedDate.setWidth("100.0%");
        lblLastUpdatedDate.setHeight("-1px");
        lblLastUpdatedDate.setValue(BundleUtils.getString("label.ContractTemplateList.lastUpdatedDate"));
//        searchLayout.addComponent(lblLastUpdatedDate, 0, 2);

        popLastUpdatedDate = new PopupDateField();
        popLastUpdatedDate.setImmediate(false);
        popLastUpdatedDate.setWidth("100.0%");
        popLastUpdatedDate.setHeight("-1px");
//        searchLayout.addComponent(popLastUpdatedDate, 1, 2);

        lblService = new Label();
        lblService.setImmediate(false);
        lblService.setWidth("100.0%");
        lblService.setHeight("-1px");
        lblService.setValue(BundleUtils.getString("label.ContractTemplateList.service"));
        searchLayout.addComponent(lblService, 0, 1);

        cboService = new ComboBox();
        cboService.setImmediate(false);
        cboService.setWidth("100.0%");
        cboService.setHeight("-1px");
        searchLayout.addComponent(cboService, 1, 1);

        lblProvider = new Label();
        lblProvider.setImmediate(false);
        lblProvider.setWidth("100.0%");
        lblProvider.setHeight("-1px");
        lblProvider.setValue(BundleUtils.getString("label.ContractTemplateList.provider"));
        searchLayout.addComponent(lblProvider, 2, 1);

        cboProvider = new ComboBox();
        cboProvider.setImmediate(false);
        cboProvider.setWidth("100.0%");
        cboProvider.setHeight("-1px");
        searchLayout.addComponent(cboProvider, 3, 1);
        
        lblType = new Label();
        lblType.setImmediate(false);
        lblType.setWidth("100.0%");
        lblType.setHeight("-1px");
        lblType.setValue(BundleUtils.getString("label.ContractTemplateList.type"));
        searchLayout.addComponent(lblType, 0, 2);

        cboType = new ComboBox();
        cboType.setImmediate(false);
        cboType.setWidth("100.0%");
        cboType.setHeight("-1px");
        searchLayout.addComponent(cboType, 1, 2);
        
        lblStatus = new Label();
        lblStatus.setImmediate(false);
        lblStatus.setWidth("100.0%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("label.ContractTemplateList.status"));
        searchLayout.addComponent(lblStatus, 2, 2);

        cbxStatus = new ComboBox();
        cbxStatus.setImmediate(false);
        cbxStatus.setWidth("100.0%");
        cbxStatus.setHeight("-1px");
        searchLayout.addComponent(cbxStatus, 3, 2);
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

    public CommonTableFilterPanel getTblContractTemplateList() {
        return tblContractTemplateList;
    }

    public void setTblContractTemplateList(CommonTableFilterPanel tblContractTemplateList) {
        this.tblContractTemplateList = tblContractTemplateList;
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

    public TextField getTxtPathFile() {
        return txtPathFile;
    }

    public void setTxtPathFile(TextField txtPathFile) {
        this.txtPathFile = txtPathFile;
    }

    public PopupDateField getPopCreatedDate() {
        return popCreatedDate;
    }

    public void setPopCreatedDate(PopupDateField popCreatedDate) {
        this.popCreatedDate = popCreatedDate;
    }

    public PopupDateField getPopLastUpdatedDate() {
        return popLastUpdatedDate;
    }

    public void setPopLastUpdatedDate(PopupDateField popLastUpdatedDate) {
        this.popLastUpdatedDate = popLastUpdatedDate;
    }

    public ComboBox getCbxStatus() {
        return cbxStatus;
    }

    public void setCbxStatus(ComboBox cbxStatus) {
        this.cbxStatus = cbxStatus;
    }

    public ComboBox getCboService() {
        return cboService;
    }

    public void setCboService(ComboBox cboService) {
        this.cboService = cboService;
    }

    public ComboBox getCboType() {
        return cboType;
    }

    public void setCboType(ComboBox cboType) {
        this.cboType = cboType;
    }

    public ComboBox getCboProvider() {
        return cboProvider;
    }

    public void setCboProvider(ComboBox cboProvider) {
        this.cboProvider = cboProvider;
    }

    
}
