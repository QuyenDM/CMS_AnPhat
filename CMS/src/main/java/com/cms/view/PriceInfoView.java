/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.anphat.list.controller.PriceInfoController;
import com.cms.component.CommonOnePanelUI;
import com.cms.component.GridManyButton;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.Constants;
import com.cms.utils.MakeURL;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ComboBox;
;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author QuyenDM
 */


public class PriceInfoView extends CommonOnePanelUI implements View {

    private GridLayout searchLayout;
    private Button btnSearch;
    private Button btnRefresh;
    private CommonTableFilterPanel tblPriceInfo;

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

    public PriceInfoView() {

        layoutMain.setMargin(true);
        layoutMain.setSpacing(true);
        buildSearchLayout();
        layoutMain.addComponent(searchLayout);
        panelMain.setCaption(BundleUtils.getString("title.PriceInfo"));

        GridManyButton gridManyButton = new GridManyButton(new String[]{Constants.BUTTON_SEARCH, Constants.BUTTON_REFRESH});
        btnSearch = gridManyButton.getBtnCommon().get(0);
        btnRefresh = gridManyButton.getBtnCommon().get(1);
        layoutMain.addComponent(gridManyButton);
        layoutMain.setComponentAlignment(gridManyButton, Alignment.MIDDLE_CENTER);
        tblPriceInfo = new CommonTableFilterPanel();
        tblPriceInfo.setImmediate(true);
        tblPriceInfo.setWidth("100%");
        tblPriceInfo.setHeight("-1px");
        tblPriceInfo.getHorizoltalLayout().setVisible(false);
        layoutMain.addComponent(tblPriceInfo);

//        btnPrintBB.setEnabled(false);
        PriceInfoController priceInfoController = new PriceInfoController(this);
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
        searchLayout.setRows(4);
        searchLayout.setStyleName("custom-feildset");

        lblCode = new Label();
        lblCode.setImmediate(false);
        lblCode.setWidth("100.0%");
        lblCode.setHeight("-1px");
        lblCode.setValue(BundleUtils.getString("label.PriceInfo.code"));
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
        lblName.setValue(BundleUtils.getString("label.PriceInfo.name"));
        searchLayout.addComponent(lblName, 2, 0);

        txtName = new TextField();
        txtName.setImmediate(false);
        txtName.setWidth("100.0%");
        txtName.setHeight("-1px");
        searchLayout.addComponent(txtName, 3, 0);
        lblPrice = new Label();
        lblPrice.setImmediate(false);
        lblPrice.setWidth("100.0%");
        lblPrice.setHeight("-1px");
        lblPrice.setValue(BundleUtils.getString("label.PriceInfo.price"));
        searchLayout.addComponent(lblPrice, 0, 1);

        txtPrice = new TextField();
        txtPrice.setImmediate(false);
        txtPrice.setWidth("100.0%");
        txtPrice.setHeight("-1px");
        searchLayout.addComponent(txtPrice, 1, 1);
        lblTokenPrice = new Label();
        lblTokenPrice.setImmediate(false);
        lblTokenPrice.setWidth("100.0%");
        lblTokenPrice.setHeight("-1px");
        lblTokenPrice.setValue(BundleUtils.getString("label.PriceInfo.tokenPrice"));
        searchLayout.addComponent(lblTokenPrice, 2, 1);

        txtTokenPrice = new TextField();
        txtTokenPrice.setImmediate(false);
        txtTokenPrice.setWidth("100.0%");
        txtTokenPrice.setHeight("-1px");
        searchLayout.addComponent(txtTokenPrice, 3, 1);
        lblProvider = new Label();
        lblProvider.setImmediate(false);
        lblProvider.setWidth("100.0%");
        lblProvider.setHeight("-1px");
        lblProvider.setValue(BundleUtils.getString("label.PriceInfo.provider"));
        searchLayout.addComponent(lblProvider, 0, 2);

        cbxProvider = new ComboBox();
        cbxProvider.setImmediate(false);
        cbxProvider.setWidth("100.0%");
        cbxProvider.setHeight("-1px");
        searchLayout.addComponent(cbxProvider, 1, 2);
        lblType = new Label();
        lblType.setImmediate(false);
        lblType.setWidth("100.0%");
        lblType.setHeight("-1px");
        lblType.setValue(BundleUtils.getString("label.PriceInfo.type"));
        searchLayout.addComponent(lblType, 2, 2);

        cbxType = new ComboBox();
        cbxType.setImmediate(false);
        cbxType.setWidth("100.0%");
        cbxType.setHeight("-1px");
        searchLayout.addComponent(cbxType, 3, 2);
        lblStatus = new Label();
        lblStatus.setImmediate(false);
        lblStatus.setWidth("100.0%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("label.PriceInfo.status"));
        searchLayout.addComponent(lblStatus, 0, 3);

        cbxStatus = new ComboBox();
        cbxStatus.setImmediate(false);
        cbxStatus.setWidth("100.0%");
        cbxStatus.setHeight("-1px");
        searchLayout.addComponent(cbxStatus, 1, 3);

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

    public CommonTableFilterPanel getTblPriceInfo() {
        return tblPriceInfo;
    }

    public void setTblPriceInfo(CommonTableFilterPanel tblPriceInfo) {
        this.tblPriceInfo = tblPriceInfo;
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

}
