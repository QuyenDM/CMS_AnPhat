/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.anphat.list.controller.TaxAuthorityController;
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
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author
 */
public class TaxAuthorityView extends CommonOnePanelUI implements View {

    private GridLayout searchLayout;
    private Button btnSearch;
    private Button btnRefresh;
    private CommonTableFilterPanel tblTaxAuthority;

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

    public TaxAuthorityView() {

        layoutMain.setMargin(true);
        layoutMain.setSpacing(true);
        buildSearchLayout();
        layoutMain.addComponent(searchLayout);
        panelMain.setCaption(BundleUtils.getString("title.TaxAuthority"));

        GridManyButton gridManyButton = new GridManyButton(new String[]{Constants.BUTTON_SEARCH, Constants.BUTTON_REFRESH});
        btnSearch = gridManyButton.getBtnCommon().get(0);
        btnRefresh = gridManyButton.getBtnCommon().get(1);
        layoutMain.addComponent(gridManyButton);
        layoutMain.setComponentAlignment(gridManyButton, Alignment.MIDDLE_CENTER);
        tblTaxAuthority = new CommonTableFilterPanel();
        tblTaxAuthority.setImmediate(true);
        tblTaxAuthority.setWidth("100%");
        tblTaxAuthority.setHeight("-1px");
        tblTaxAuthority.getHorizoltalLayout().setVisible(false);
        layoutMain.addComponent(tblTaxAuthority);

//        btnPrintBB.setEnabled(false);
        TaxAuthorityController taxAuthorityController = new TaxAuthorityController(this);
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
        searchLayout.setRows(3);
        searchLayout.setStyleName("custom-feildset");

//        lblId = new Label();
//        lblId.setImmediate(true);
//        lblId.setWidth("100.0%");
//        lblId.setHeight("-1px");
//        lblId.setValue(BundleUtils.getString("label.TaxAuthority.id"));
//        searchLayout.addComponent(lblId, 0, 0);
//        txtId = new TextField();
//        txtId.setImmediate(true);
//        txtId.setWidth("100.0%");
//        txtId.setHeight("-1px");
//        searchLayout.addComponent(txtId, 1, 0);
        lblMaCqt = new Label();
        lblMaCqt.setImmediate(true);
        lblMaCqt.setWidth("100.0%");
        lblMaCqt.setHeight("-1px");
        lblMaCqt.setValue(BundleUtils.getString("label.TaxAuthority.maCqt"));
        searchLayout.addComponent(lblMaCqt, 0, 0);

        txtMaCqt = new TextField();
        txtMaCqt.setImmediate(true);
        txtMaCqt.setWidth("100.0%");
        txtMaCqt.setHeight("-1px");
        searchLayout.addComponent(txtMaCqt, 1, 0);
        lblTenCqt = new Label();
        lblTenCqt.setImmediate(true);
        lblTenCqt.setWidth("100.0%");
        lblTenCqt.setHeight("-1px");
        lblTenCqt.setValue(BundleUtils.getString("label.TaxAuthority.tenCqt"));
        searchLayout.addComponent(lblTenCqt, 2, 0);

        txtTenCqt = new TextField();
        txtTenCqt.setImmediate(true);
        txtTenCqt.setWidth("100.0%");
        txtTenCqt.setHeight("-1px");
        searchLayout.addComponent(txtTenCqt, 3, 0);
        lblMaTinh = new Label();
        lblMaTinh.setImmediate(true);
        lblMaTinh.setWidth("100.0%");
        lblMaTinh.setHeight("-1px");
        lblMaTinh.setValue(BundleUtils.getString("label.TaxAuthority.maTinh"));
        searchLayout.addComponent(lblMaTinh, 0, 1);

        cboMaTinh = new ComboBox();
        cboMaTinh.setImmediate(true);
        cboMaTinh.setWidth("100.0%");
        cboMaTinh.setHeight("-1px");
        searchLayout.addComponent(cboMaTinh, 1, 1);
        lblMaQuanHuyen = new Label();
        lblMaQuanHuyen.setImmediate(true);
        lblMaQuanHuyen.setWidth("100.0%");
        lblMaQuanHuyen.setHeight("-1px");
        lblMaQuanHuyen.setValue(BundleUtils.getString("label.TaxAuthority.maQuanHuyen"));
        searchLayout.addComponent(lblMaQuanHuyen, 2, 1);

        txtMaQuanHuyen = new TextField();
        txtMaQuanHuyen.setImmediate(true);
        txtMaQuanHuyen.setWidth("100.0%");
        txtMaQuanHuyen.setHeight("-1px");
        searchLayout.addComponent(txtMaQuanHuyen, 3, 1);
        lblStatus = new Label();
        lblStatus.setImmediate(true);
        lblStatus.setWidth("100.0%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("label.TaxAuthority.status"));
        searchLayout.addComponent(lblStatus, 0, 2);

        cboStatus = new ComboBox();
        cboStatus.setImmediate(true);
        cboStatus.setWidth("100.0%");
        cboStatus.setHeight("-1px");
        searchLayout.addComponent(cboStatus, 1, 2);

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

    public CommonTableFilterPanel getTblTaxAuthority() {
        return tblTaxAuthority;
    }

    public void setTblTaxAuthority(CommonTableFilterPanel tblTaxAuthority) {
        this.tblTaxAuthority = tblTaxAuthority;
    }

    public GridLayout getSearchLayout() {
        return searchLayout;
    }

    public void setSearchLayout(GridLayout searchLayout) {
        this.searchLayout = searchLayout;
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

    public ComboBox getTxtMaTinh() {
        return cboMaTinh;
    }

    public void setTxtMaTinh(ComboBox cboMaTinh) {
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

    public void setCboStatus(ComboBox cboStatus) {
        this.cboStatus = cboStatus;
    }

}
