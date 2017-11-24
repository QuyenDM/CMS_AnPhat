/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.anphat.list.controller.ObjectsController;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
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
public class ObjectsView extends CommonOnePanelUI implements View {

    private GridLayout searchLayout;
    private Button btnSearch;
    private Button btnRefresh;
    private CommonTableFilterPanel tblObjects;

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

    public ObjectsView() {

        layoutMain.setMargin(true);
        layoutMain.setSpacing(true);
        buildSearchLayout();
        layoutMain.addComponent(searchLayout);
        panelMain.setCaption(BundleUtils.getString("title.Objects"));

        GridManyButton gridManyButton = new GridManyButton(new String[]{Constants.BUTTON_SEARCH, Constants.BUTTON_REFRESH});
        btnSearch = gridManyButton.getBtnCommon().get(0);
        btnRefresh = gridManyButton.getBtnCommon().get(1);
        layoutMain.addComponent(gridManyButton);
        layoutMain.setComponentAlignment(gridManyButton, Alignment.MIDDLE_CENTER);
        tblObjects = new CommonTableFilterPanel();
        tblObjects.setImmediate(true);
        tblObjects.setWidth("100%");
        tblObjects.setHeight("-1px");
        tblObjects.getHorizoltalLayout().setVisible(false);
        layoutMain.addComponent(tblObjects);

//        btnPrintBB.setEnabled(false);
        ObjectsController objectsController = new ObjectsController(this);
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

        lblCode = new Label();
        lblCode.setImmediate(true);
        lblCode.setWidth("100.0%");
        lblCode.setHeight("-1px");
        lblCode.setValue(BundleUtils.getString("label.Objects.code"));
        searchLayout.addComponent(lblCode, 0, 0);

        txtCode = new TextField();
        txtCode.setImmediate(true);
        txtCode.setWidth("100.0%");
        txtCode.setHeight("-1px");
        searchLayout.addComponent(txtCode, 1, 0);
        lblName = new Label();
        lblName.setImmediate(true);
        lblName.setWidth("100.0%");
        lblName.setHeight("-1px");
        lblName.setValue(BundleUtils.getString("label.Objects.name"));
        searchLayout.addComponent(lblName, 2, 0);

        txtName = new TextField();
        txtName.setImmediate(true);
        txtName.setWidth("100.0%");
        txtName.setHeight("-1px");
        searchLayout.addComponent(txtName, 3, 0);
        lblUrl = new Label();
        lblUrl.setImmediate(true);
        lblUrl.setWidth("100.0%");
        lblUrl.setHeight("-1px");
        lblUrl.setValue(BundleUtils.getString("label.Objects.url"));
        searchLayout.addComponent(lblUrl, 0, 1);

        txtUrl = new TextField();
        txtUrl.setImmediate(true);
        txtUrl.setWidth("100.0%");
        txtUrl.setHeight("-1px");
        searchLayout.addComponent(txtUrl, 1, 1);
        lblDescription = new Label();
        lblDescription.setImmediate(true);
        lblDescription.setWidth("100.0%");
        lblDescription.setHeight("-1px");
        lblDescription.setValue(BundleUtils.getString("label.Objects.description"));
        searchLayout.addComponent(lblDescription, 2, 1);

        txtDescription = new TextArea();
        txtDescription.setImmediate(true);
        txtDescription.setWidth("100.0%");
        txtDescription.setHeight("-1px");
        searchLayout.addComponent(txtDescription, 3, 1);
        lblObjectType = new Label();
        lblObjectType.setImmediate(true);
        lblObjectType.setWidth("100.0%");
        lblObjectType.setHeight("-1px");
        lblObjectType.setValue(BundleUtils.getString("label.Objects.objectType"));
        searchLayout.addComponent(lblObjectType, 0, 2);

        txtObjectType = new TextField();
        txtObjectType.setImmediate(true);
        txtObjectType.setWidth("100.0%");
        txtObjectType.setHeight("-1px");
        searchLayout.addComponent(txtObjectType, 1, 2);
        lblStatus = new Label();
        lblStatus.setImmediate(true);
        lblStatus.setWidth("100.0%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("label.Objects.status"));
        searchLayout.addComponent(lblStatus, 2, 2);

        cbxStatus = new ComboBox();
        cbxStatus.setImmediate(true);
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

    public CommonTableFilterPanel getTblObjects() {
        return tblObjects;
    }

    public void setTblObjects(CommonTableFilterPanel tblObjects) {
        this.tblObjects = tblObjects;
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
