/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.anphat.list.controller.CategoryListController;
import com.cms.component.CommonOnePanelUI;
import com.cms.component.GridManyButton;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.MakeURL;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author
 */
public class CategoryListView extends CommonOnePanelUI implements View {

    private GridLayout searchLayout;
    private Button btnSearch;
    private Button btnRefresh;
    private CommonTableFilterPanel tblCategoryList;

    private Label lblCode;
    private TextField txtCode;
    private Label lblName;
    private TextField txtName;
    private Label lblReceivedDate;
    private DateField dfReceivedDate;
    private Label lblEndDate;
    private DateField dfEndDate;
    private Label lblDescription;
    private TextField txtDescription;
    private Label lblCreator;
    private TextField txtCreator;
    private Label lblService;
    private ComboBox cboService;

    public CategoryListView() {

        layoutMain.setMargin(true);
        layoutMain.setSpacing(true);
        buildSearchLayout();
        layoutMain.addComponent(searchLayout);
        panelMain.setCaption(BundleUtils.getString("title.CategoryList"));

        GridManyButton gridManyButton = new GridManyButton(new String[]{Constants.BUTTON_SEARCH, Constants.BUTTON_REFRESH});
        btnSearch = gridManyButton.getBtnCommon().get(0);
        btnRefresh = gridManyButton.getBtnCommon().get(1);
        layoutMain.addComponent(gridManyButton);
        layoutMain.setComponentAlignment(gridManyButton, Alignment.MIDDLE_CENTER);
        tblCategoryList = new CommonTableFilterPanel();
        tblCategoryList.setImmediate(true);
        tblCategoryList.setWidth("100%");
        tblCategoryList.setHeight("-1px");
        tblCategoryList.getHorizoltalLayout().setVisible(false);
        layoutMain.addComponent(tblCategoryList);

//        btnPrintBB.setEnabled(false);
        CategoryListController categoryListController = new CategoryListController(this);
    }

    public void buildSearchLayout() {
        searchLayout = new GridLayout();
        searchLayout.setCaption(MakeURL.makeURLForGrid(BundleUtils.getString("caption.search.info")));
        searchLayout.setCaptionAsHtml(true);
        searchLayout.setImmediate(true);
        searchLayout.setWidth("100.0%");
        searchLayout.setHeight("-1px");
        searchLayout.setMargin(true);
        searchLayout.setSpacing(true);
        searchLayout.setColumns(4);
        searchLayout.setRows(4);
        searchLayout.setStyleName("custom-feildset");

        lblCode = new Label();
        lblCode.setImmediate(true);
        lblCode.setWidth("100.0%");
        lblCode.setHeight("-1px");
        lblCode.setValue(BundleUtils.getString("label.CategoryList.code"));
        searchLayout.addComponent(lblCode, 0, 0);

        txtCode = new TextField();
//        txtCode.setRequired(true);
        txtCode.setImmediate(true);
        txtCode.setWidth("100.0%");
        txtCode.setHeight("-1px");
        searchLayout.addComponent(txtCode, 1, 0);
        
        lblName = new Label();
        lblName.setImmediate(true);
        lblName.setWidth("100.0%");
        lblName.setHeight("-1px");
        lblName.setValue(BundleUtils.getString("label.CategoryList.name"));
        searchLayout.addComponent(lblName, 2, 0);

        txtName = new TextField();
        txtName.setImmediate(true);
        txtName.setWidth("100.0%");
        txtName.setHeight("-1px");
        searchLayout.addComponent(txtName, 3, 0);
        
        lblReceivedDate = new Label();
        lblReceivedDate.setImmediate(true);
        lblReceivedDate.setWidth("100.0%");
        lblReceivedDate.setHeight("-1px");
        lblReceivedDate.setValue(BundleUtils.getString("label.CategoryList.receivedDate"));
        searchLayout.addComponent(lblReceivedDate, 0, 1);

        dfReceivedDate = new DateField();
//        dfReceivedDate.setRequired(true);
        dfReceivedDate.setImmediate(true);
        dfReceivedDate.setWidth("100.0%");
        dfReceivedDate.setHeight("-1px");
        searchLayout.addComponent(dfReceivedDate, 1, 1);
        lblEndDate = new Label();
        lblEndDate.setImmediate(true);
        lblEndDate.setWidth("100.0%");
        lblEndDate.setHeight("-1px");
        lblEndDate.setValue(BundleUtils.getString("label.CategoryList.endDate"));
        searchLayout.addComponent(lblEndDate, 2, 1);

        dfEndDate = new DateField();
//        dfEndDate.setRequired(true);
        dfEndDate.setImmediate(true);
        dfEndDate.setWidth("100.0%");
        dfEndDate.setHeight("-1px");
        searchLayout.addComponent(dfEndDate, 3, 1);
        lblDescription = new Label();
        lblDescription.setImmediate(true);
        lblDescription.setWidth("100.0%");
        lblDescription.setHeight("-1px");
        lblDescription.setValue(BundleUtils.getString("label.CategoryList.description"));
        searchLayout.addComponent(lblDescription, 0, 2);

        txtDescription = new TextField();
        txtDescription.setImmediate(true);
        txtDescription.setWidth("100.0%");
        txtDescription.setHeight("-1px");
        searchLayout.addComponent(txtDescription, 1, 2);
        lblCreator = new Label();
        lblCreator.setImmediate(true);
        lblCreator.setWidth("100.0%");
        lblCreator.setHeight("-1px");
        lblCreator.setValue(BundleUtils.getString("label.CategoryList.creator"));
        searchLayout.addComponent(lblCreator, 2, 2);

        txtCreator = new TextField();
        txtCreator.setImmediate(true);
        txtCreator.setWidth("100.0%");
        txtCreator.setHeight("-1px");
        searchLayout.addComponent(txtCreator, 3, 2);

        lblService = new Label();
        lblService.setImmediate(true);
        lblService.setWidth("100.0%");
        lblService.setHeight("-1px");
        lblService.setValue(BundleUtils.getString("customerStatusForm.service"));
        searchLayout.addComponent(lblService, 0, 3);

        cboService = CommonUtils.buildComboBox();        
        cboService.setImmediate(true);
        cboService.setWidth("100.0%");
        cboService.setHeight("-1px");
        
        searchLayout.addComponent(cboService, 1, 3);

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

    public CommonTableFilterPanel getTblCategoryList() {
        return tblCategoryList;
    }

    public void setTblCategoryList(CommonTableFilterPanel tblCategoryList) {
        this.tblCategoryList = tblCategoryList;
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

    public DateField getDfReceivedDate() {
        return dfReceivedDate;
    }

    public void setDfReceivedDate(DateField dfReceivedDate) {
        this.dfReceivedDate = dfReceivedDate;
    }

    public DateField getDfEndDate() {
        return dfEndDate;
    }

    public void setDfEndDate(DateField dfEndDate) {
        this.dfEndDate = dfEndDate;
    }

    public TextField getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(TextField txtDescription) {
        this.txtDescription = txtDescription;
    }

    public TextField getTxtCreator() {
        return txtCreator;
    }

    public void setTxtCreator(TextField txtCreator) {
        this.txtCreator = txtCreator;
    }

    public ComboBox getCboService() {
        return cboService;
    }

    public void setCboService(ComboBox cboService) {
        this.cboService = cboService;
    }

}
