/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.ui;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.cms.component.CommonDialog;
import com.cms.component.GridManyButton;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.Constants;
import com.cms.utils.MakeURL;
/**
 *
 * @author quyendm
 */
public class DialogAddMapStaffCustomer extends CommonDialog {

    private CommonTableFilterPanel panelStaffAdd;
    private CommonTableFilterPanel panelStaffOld;
    private GridLayout searchLayout;
    private VerticalLayout leftLayout;
    private VerticalLayout rightLayout;
    private VerticalLayout centerLayout;
    private HorizontalLayout horizontalLayout;
    //Cac thanh phan cua giao dien tim kiem
    private TextField txtStaffCode;
    private TextField txtStaffName;
    private Label txtCustCode;
    private Label txtCustName;
    private TextField txtEmail;
    private TextField txtTelephone;
    private ComboBox cboStaffType;
    private GridLayout infoLayout;

    private Button btnAdd;
    private Button btnSearch;
    private Button btnSave;
    private Button btnCancel;

    public DialogAddMapStaffCustomer(String caption) {
        setInfo("1200px", "-1px", caption);
    }

    public void initDialog(boolean isCustInfo) {
        buildInfoLayout(isCustInfo);
        mainLayout.addComponent(infoLayout);
        // searchLayout
        buildSearchLayout(isCustInfo);
        mainLayout.addComponent(searchLayout);
        // btnSearch
        btnSearch = new Button();
        btnSearch.setCaption(BundleUtils.getString("common.button.search"));
        btnSearch.setIcon(new ThemeResource(Constants.ICON.SEARCH));
        btnSearch.setImmediate(true);
        btnSearch.setWidth("-1px");
        btnSearch.setHeight("-1px");
        mainLayout.addComponent(btnSearch);
        mainLayout.setComponentAlignment(btnSearch, new Alignment(48));
        buildMainLayout();
        mainLayout.addComponent(horizontalLayout);
        //Nut luu va huy bo
        GridManyButton gridManyButton = new GridManyButton(new String[]{Constants.BUTTON_SAVE, Constants.BUTTON_CANCEL});
        btnSave = gridManyButton.getBtnCommon().get(0);
        btnCancel = gridManyButton.getBtnCommon().get(1);
        mainLayout.addComponent(gridManyButton);

    }

    private void buildInfoLayout(boolean isCustInfo) {
        infoLayout = new GridLayout();
        infoLayout.setImmediate(true);
//        infoLayout.setStyleName("custom-feildset");
//        infoLayout.setCaptionAsHtml(true);
        infoLayout.setWidth("100.0%");
        infoLayout.setHeight("-1px");
        infoLayout.setMargin(true);
        infoLayout.setSpacing(true);
        infoLayout.setColumns(6);
        infoLayout.setRows(1);

        // lblCustCode
        Label lblCustCode = new Label();
        lblCustCode.addStyleName("v-label-bold");
        lblCustCode.setImmediate(true);
        lblCustCode.setWidth("100.0%");
        lblCustCode.setHeight("-1px");

        // tfCustCode
        txtCustCode = new Label();
        txtCustCode.setImmediate(true);
        txtCustCode.setWidth("100.0%");
        txtCustCode.setHeight("-1px");
        // lblCustCode
        Label lblCustName = new Label();
        lblCustName.addStyleName("v-label-bold");
        lblCustName.setImmediate(true);
        lblCustName.setWidth("100.0%");
        lblCustName.setHeight("-1px");

        // tfCustCode
        txtCustName = new Label();
        txtCustName.setImmediate(true);
        txtCustName.setWidth("100.0%");
        txtCustName.setHeight("-1px");

        if (isCustInfo) {
//            infoLayout.setCaption(BundleUtils.getString("goods.list.panel.caption.customerinfo"));
//            lblCustCode.setValue(BundleUtils.getString("goods.list.label.customercode"));
            lblCustName.setValue(BundleUtils.getString("goods.list.label.customername"));
        } else {
//            infoLayout.setCaption(BundleUtils.getString("goods.list.panel.caption.staffInfo"));
//            lblCustCode.setValue(BundleUtils.getString("lb.deptstaff.emp.code"));
            lblCustName.setValue(BundleUtils.getString("lb.deptstaff.emp.name"));
        }
//        infoLayout.addComponent(lblCustCode, 0, 0);
//        infoLayout.addComponent(txtCustCode, 1, 0);
        infoLayout.addComponent(lblCustName, 0, 0);
        infoLayout.addComponent(txtCustName, 1, 0, 5, 0);
    }

    private void buildMainLayout() {
        // common part: create layout
        horizontalLayout = new HorizontalLayout();
        horizontalLayout.setImmediate(true);
        horizontalLayout.setWidth("100%");
        horizontalLayout.setHeight("-1px");
        horizontalLayout.setMargin(false);

//        // top-level component properties
//        setWidth("100.0%");
//        setHeight("-1px");
        // leftLayout
        buildLeftLayout();
        horizontalLayout.addComponent(leftLayout);
        horizontalLayout.setExpandRatio(leftLayout, 1.0f);

        // centerLayout
        buildCenterLayout();
        horizontalLayout.addComponent(centerLayout);
        horizontalLayout.setComponentAlignment(centerLayout, new Alignment(48));

        // rightLayout
        buildRightLayout();
        horizontalLayout.addComponent(rightLayout);
        horizontalLayout.setExpandRatio(rightLayout, 1.0f);
    }

    private void buildLeftLayout() {
        // common part: create layout
        leftLayout = new VerticalLayout();
        leftLayout.setImmediate(true);
        leftLayout.setWidth("100.0%");
        leftLayout.setHeight("-1px");
        leftLayout.setMargin(true);
        leftLayout.setSpacing(true);

        // tblLeftLayout
        panelStaffAdd = new CommonTableFilterPanel();
        leftLayout.addComponent(panelStaffAdd);
    }

    private void buildSearchLayout(boolean isCustInfo) {
        // common part: create layout
        searchLayout = new GridLayout();
        searchLayout.setImmediate(true);
        searchLayout.setStyleName("custom-feildset");
        searchLayout.setCaption(MakeURL.makeURLForGrid(BundleUtils.getString("common.searchLabel")));
        searchLayout.setCaptionAsHtml(true);
        searchLayout.setWidth("100.0%");
        searchLayout.setHeight("-1px");
        searchLayout.setMargin(true);
        searchLayout.setSpacing(true);

        // lblCustCode
        Label lblCustCode = new Label();
        lblCustCode.setImmediate(true);
        lblCustCode.setWidth("100.0%");
        lblCustCode.setHeight("-1px");

        // tfCustCode
        txtStaffCode = new TextField();
        txtStaffCode.setImmediate(true);
        txtStaffCode.setWidth("100.0%");
        txtStaffCode.setHeight("-1px");

        // lblCustName
        Label lblCustName = new Label();
        lblCustName.setImmediate(true);
        lblCustName.setWidth("100.0%");
        lblCustName.setHeight("-1px");

        // tfCustName
        txtStaffName = new TextField();
        txtStaffName.setImmediate(true);
        txtStaffName.setWidth("100.0%");
        txtStaffName.setHeight("-1px");
        // lblCustCode
        Label lblEmail = new Label();
        lblEmail.setImmediate(true);
        lblEmail.setWidth("100.0%");
        lblEmail.setHeight("-1px");

        // tfCustCode
        txtEmail = new TextField();
        txtEmail.setImmediate(true);
        txtEmail.setWidth("100.0%");
        txtEmail.setHeight("-1px");

        // lblCustName
        Label lblTelephone = new Label();
        lblTelephone.setImmediate(true);
        lblTelephone.setWidth("100.0%");
        lblTelephone.setHeight("-1px");

        // tfCustName
        txtTelephone = new TextField();
        txtTelephone.setImmediate(true);
        txtTelephone.setWidth("100.0%");
        txtTelephone.setHeight("-1px");
        // lblCustName
        Label lblStaffType = new Label();
        lblStaffType.setImmediate(true);
        lblStaffType.setWidth("100.0%");
        lblStaffType.setHeight("-1px");

        // tfCustName
        cboStaffType = new ComboBox();
        cboStaffType.setImmediate(true);
        cboStaffType.setWidth("100.0%");
        cboStaffType.setHeight("-1px");

        if (isCustInfo) {
            lblCustCode.setValue(BundleUtils.getString("map.staff.customer.staffCode"));
            lblCustName.setValue(BundleUtils.getString("map.staff.customer.staffName"));
            lblEmail.setValue(BundleUtils.getString("goods.list.label.email"));
            lblTelephone.setValue(BundleUtils.getString("lb.deptstaff.common.phone"));
            lblStaffType.setValue(BundleUtils.getString("map.staff.customer.staffType"));
            searchLayout.setColumns(6);
            searchLayout.setRows(2);
            searchLayout.addComponent(lblCustCode, 0, 0);
            searchLayout.addComponent(txtStaffCode, 1, 0);
            searchLayout.addComponent(lblCustName, 2, 0);
            searchLayout.addComponent(txtStaffName, 3, 0);
            searchLayout.addComponent(lblEmail, 4, 0);
            searchLayout.addComponent(txtEmail, 5, 0);
            searchLayout.addComponent(lblStaffType, 0, 1);
            searchLayout.addComponent(cboStaffType, 1, 1);
            searchLayout.addComponent(lblTelephone, 2, 1);
            searchLayout.addComponent(txtTelephone, 3, 1);
        } else {
            lblCustCode.setValue(BundleUtils.getString("map.staff.customer.custCode"));
            lblCustName.setValue(BundleUtils.getString("map.staff.customer.custName"));
            lblEmail.setValue(BundleUtils.getString("goods.list.label.email"));
            lblTelephone.setValue(BundleUtils.getString("lb.deptstaff.common.phone"));
            searchLayout.setColumns(4);
            searchLayout.setRows(2);
            searchLayout.addComponent(lblCustCode, 0, 0);
            searchLayout.addComponent(txtStaffCode, 1, 0);
            searchLayout.addComponent(lblCustName, 2, 0);
            searchLayout.addComponent(txtStaffName, 3, 0);
            searchLayout.addComponent(lblEmail, 0, 1);
            searchLayout.addComponent(txtEmail, 1, 1);
            searchLayout.addComponent(lblTelephone, 2, 1);
            searchLayout.addComponent(txtTelephone, 3, 1);
        }
    }

    private void buildCenterLayout() {
        // common part: create layout
        centerLayout = new VerticalLayout();
        centerLayout.setImmediate(true);
        centerLayout.setWidth("30px");
        centerLayout.setHeight("-1px");
        centerLayout.setMargin(false);

        // btnAdd
        btnAdd = new Button();
        btnAdd.setIcon(new ThemeResource(Constants.ICON.FORWARD));
        btnAdd.setImmediate(true);
        btnAdd.setWidth("-1px");
        btnAdd.setHeight("-1px");
        centerLayout.addComponent(btnAdd);
        centerLayout.setComponentAlignment(btnAdd, new Alignment(48));

    }

    private void buildRightLayout() {
        // common part: create layout
        rightLayout = new VerticalLayout();
        rightLayout.setImmediate(true);
        rightLayout.setWidth("100.0%");
        rightLayout.setHeight("-1px");
        rightLayout.setMargin(true);
        rightLayout.setSpacing(true);
        //Thong tin chung ve khach hang
//        buildInfoLayout(isCustInfo);
//        rightLayout.addComponent(infoLayout);
        // selectedLayout
        panelStaffOld = new CommonTableFilterPanel();
        rightLayout.addComponent(panelStaffOld);
    }

    //Getter and Setter
    public CommonTableFilterPanel getPanelStaffAdd() {
        return panelStaffAdd;
    }

    public void setPanelStaffAdd(CommonTableFilterPanel panelStaffAdd) {
        this.panelStaffAdd = panelStaffAdd;
    }

    public CommonTableFilterPanel getPanelStaffOld() {
        return panelStaffOld;
    }

    public void setPanelStaffOld(CommonTableFilterPanel panelStaffOld) {
        this.panelStaffOld = panelStaffOld;
    }

    public TextField getTxtStaffCode() {
        return txtStaffCode;
    }

    public void setTxtStaffCode(TextField txtStaffCode) {
        this.txtStaffCode = txtStaffCode;
    }

    public TextField getTxtStaffName() {
        return txtStaffName;
    }

    public void setTxtStaffName(TextField txtStaffName) {
        this.txtStaffName = txtStaffName;
    }

    public TextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(TextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public TextField getTxtTelephone() {
        return txtTelephone;
    }

    public void setTxtTelephone(TextField txtTelephone) {
        this.txtTelephone = txtTelephone;
    }

    public Button getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(Button btnAdd) {
        this.btnAdd = btnAdd;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(Button btnSearch) {
        this.btnSearch = btnSearch;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(Button btnSave) {
        this.btnSave = btnSave;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }

    public Label getTxtCustCode() {
        return txtCustCode;
    }

    public void setTxtCustCode(Label txtCustCode) {
        this.txtCustCode = txtCustCode;
    }

    public Label getTxtCustName() {
        return txtCustName;
    }

    public void setTxtCustName(Label txtCustName) {
        this.txtCustName = txtCustName;
    }

    public ComboBox getCboStaffType() {
        return cboStaffType;
    }

    public void setCboStaffType(ComboBox cboStaffType) {
        this.cboStaffType = cboStaffType;
    }

}
