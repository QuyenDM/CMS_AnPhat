package com.cms.view;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.cms.utils.BundleUtils;
import java.util.Date;

import java.util.Locale;

public class Mai extends CustomComponent implements View{

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
    @AutoGenerated
    private VerticalLayout mainLayout;
    @AutoGenerated
    private GridLayout gridLayout;
    @AutoGenerated
    private TextField txtStockName;
    @AutoGenerated
    private Label lblStockName;
    @AutoGenerated
    private TextField txtStockCode;
    @AutoGenerated
    private Label lblStockCode;
    @AutoGenerated
    private ComboBox cbxStatus;
    @AutoGenerated
    private Label lblStatus;
    @AutoGenerated
    private TextField txtSerial;
    @AutoGenerated
    private Label lblCustCode;
    @AutoGenerated
    private PopupDateField popToDate;
    @AutoGenerated
    private Label lblToDate;
    @AutoGenerated
    private PopupDateField popFromDate;
    @AutoGenerated
    private Label lblSerial;
    @AutoGenerated
    private TextField txtStatus;
    @AutoGenerated
    private Label lblProduc;
    @AutoGenerated
    private TextField txtBatchCode;
    @AutoGenerated
    private Label lblBatchCode;
    private ComboBox cbProduct;

    private VerticalLayout verticalLayout;

    Locale locale = (Locale) VaadinSession.getCurrent().getSession().getAttribute("locale");
    private Label lblFromDate;

    public Mai() {
        buildMainLayout();
        setCompositionRoot(mainLayout);

        // TODO add user code here
    }

    @AutoGenerated
    private VerticalLayout buildMainLayout() {
        // common part: create layout
        mainLayout = new VerticalLayout();
        mainLayout.setImmediate(true);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("-1px");
        mainLayout.setMargin(false);

        // top-level component properties
        setWidth("100.0%");
        setHeight("-1px");

        // gridLayout
        gridLayout = buildGridLayout();
        verticalLayout = new VerticalLayout();
        verticalLayout.setImmediate(false);
        verticalLayout.setWidth("100.0%");
        verticalLayout.setHeight("100.0%");
        verticalLayout.setMargin(false);
        verticalLayout.addComponent(gridLayout);
        mainLayout.addComponent(verticalLayout);

        return mainLayout;
    }

    @AutoGenerated
    private GridLayout buildGridLayout() {
        // common part: create layout
        gridLayout = new GridLayout();
        gridLayout.setCaptionAsHtml(true);
        gridLayout.setCaption(BundleUtils.getString("stone.label.search.info1212121"));
        gridLayout.setImmediate(false);
        gridLayout.setWidth("100.0%");
        gridLayout.setHeight("-1px");
        gridLayout.setMargin(true);
        gridLayout.setSpacing(true);
        gridLayout.setColumns(4);
        gridLayout.setRows(3);
        gridLayout.setStyleName("custom-feildset");

        // label ma lo
        lblBatchCode = new Label();
        lblBatchCode.setImmediate(false);
        lblBatchCode.setWidth("100.0%");
        lblBatchCode.setHeight("-1px");
        lblBatchCode.setValue(BundleUtils.getString("stone.label.batch.code"));

        gridLayout.addComponent(lblBatchCode, 0, 0);

        // txtBatchCode
        txtBatchCode = new TextField();
        txtBatchCode.setImmediate(false);
        txtBatchCode.focus();
        txtBatchCode.setWidth("100.0%");
        txtBatchCode.setHeight("-1px");
        gridLayout.addComponent(txtBatchCode, 1, 0);

        // lblProduc
        lblProduc = new Label();
        lblProduc.setImmediate(false);
        lblProduc.setWidth("100%");
        lblProduc.setHeight("-1px");
        lblProduc.setValue(BundleUtils.getString("stone.label.product"));
        gridLayout.addComponent(lblProduc, 2, 0);

        //cbProduct
        cbProduct = new ComboBox();
        cbProduct.setImmediate(true);
        cbProduct.setWidth("100.0%");
        cbProduct.setHeight("-1px");
        gridLayout.addComponent(cbProduct, 3, 0);

        // lblSerial
        lblSerial = new Label();
        lblSerial.setImmediate(false);
        lblSerial.setWidth("100.0%");
        lblSerial.setHeight("-1px");
        lblSerial.setValue(BundleUtils.getString("stone.label.serial"));
        gridLayout.addComponent(lblSerial, 0, 1);

        // txtSerial
        txtSerial = new TextField();
        txtSerial.setImmediate(false);
        txtSerial.setWidth("100.0%");
        txtSerial.setHeight("-1px");
        gridLayout.addComponent(txtSerial, 1, 1);

        // lblStatus
        lblStatus = new Label();
        lblStatus.setImmediate(false);
        lblStatus.setWidth("100.0%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("stone.label.status"));
        gridLayout.addComponent(lblStatus, 2, 1);

        // cbxStatus
        cbxStatus = new ComboBox();
        cbxStatus.setImmediate(false);
        cbxStatus.setWidth("100.0%");
        cbxStatus.setHeight("-1px");
        gridLayout.addComponent(cbxStatus, 3, 1);

        lblFromDate = new Label();
        lblFromDate.setImmediate(false);
        lblFromDate.setWidth("100.0%");
        lblFromDate.setHeight("-1px");
        lblFromDate.setValue(BundleUtils.getString("stone.label.fromdate"));
        gridLayout.addComponent(lblFromDate, 0, 2);

        // popFromDate
        popFromDate = new PopupDateField();
        popFromDate.setImmediate(false);
        popFromDate.setParseErrorMessage(BundleUtils.getString("valid.pattern.date"));
        popFromDate.setWidth("100.0%");
        popFromDate.setHeight("-1px");
        popFromDate.setLocale(locale);
        popFromDate.setValue(new Date());
        gridLayout.addComponent(popFromDate, 1, 2);

        // lblToDate
        lblToDate = new Label();
        lblToDate.setImmediate(false);
        lblToDate.setWidth("100.0%");
        lblToDate.setHeight("-1px");
        lblToDate.setValue(BundleUtils.getString("stone.label.fromdate"));
        gridLayout.addComponent(lblToDate, 2, 2);

        // popToDate
        popToDate = new PopupDateField();
        popToDate.setParseErrorMessage(BundleUtils.getString("valid.pattern.date"));
        popToDate.setImmediate(false);
        popToDate.setLocale(locale);
        popToDate.setWidth("100.0%");
        popToDate.setHeight("-1px");
        popToDate.setValue(new Date());
        gridLayout.addComponent(popToDate, 3, 2);

//        // lblStockCode
//        lblStockCode = new Label();
//        lblStockCode.setImmediate(false);
//        lblStockCode.setWidth("100.0%");
//        lblStockCode.setHeight("-1px");
//        lblStockCode.setValue("");
//        gridLayout.addComponent(lblStockCode, 0, 4);
        return gridLayout;
    }

    public void setVisibleComponents(boolean isVisible) {
        if (!isVisible) {
//            gridLayout.removeAllComponents();
            gridLayout.removeComponent(0, 2);
            gridLayout.removeComponent(1, 2);
            gridLayout.removeComponent(2, 2);
            gridLayout.removeComponent(3, 2);
            gridLayout.removeComponent(0, 4);
            gridLayout.removeComponent(1, 4);
            gridLayout.removeComponent(2, 4);
            gridLayout.removeComponent(3, 4);
            gridLayout.removeComponent(0, 3);
            gridLayout.removeComponent(1, 3);
            gridLayout.removeComponent(2, 3);
            gridLayout.removeComponent(3, 3);
            gridLayout.setRows(2);
        } else {
            gridLayout.setRows(5);
            gridLayout.addComponent(lblCustCode, 0, 2);
            gridLayout.addComponent(txtSerial, 1, 2);
            gridLayout.addComponent(lblStatus, 2, 2);
            gridLayout.addComponent(cbxStatus, 3, 2);
            gridLayout.addComponent(lblStockCode, 0, 4);
        }

//        lblCustCode.setVisible(isVisible);
//        lblStatus.setVisible(isVisible);
//        txtSerial.setVisible(isVisible);
//        cbxStatus.setVisible(isVisible);
//        lblStockCode.setVisible(isVisible);
//        lblStockProvince.setVisible(isVisible);
//        comboProvince.setVisibleCombo(isVisible);
//        comboStock.setVisibleCombo(isVisible);
//        if (!isAdvance) {
//            txtSerial.setValue("");
//            cbxStatus.setValue("");
//            comboProvince.resetData();
//            comboStock.resetData();
//        }
    }

    public ComboBox getCbStatus() {
        return cbProduct;
    }

    public void setCbStatus(ComboBox cbProduct) {
        this.cbProduct = cbProduct;
    }

    public TextField getTxtStockName() {
        return txtStockName;
    }

    public void setTxtStockName(TextField txtStockName) {
        this.txtStockName = txtStockName;
    }

    public TextField getTxtStockCode() {
        return txtStockCode;
    }

    public void setTxtStockCode(TextField txtStockCode) {
        this.txtStockCode = txtStockCode;
    }

    public TextField getTxtCustCode() {
        return txtSerial;
    }

    public void setTxtCustCode(TextField txtSerial) {
        this.txtSerial = txtSerial;
    }

    public PopupDateField getPopToDate() {
        return popToDate;
    }

    public void setPopToDate(PopupDateField popToDate) {
        this.popToDate = popToDate;
    }

    public PopupDateField getPopFromDate() {
        return popFromDate;
    }

    public void setPopFromDate(PopupDateField popFromDate) {
        this.popFromDate = popFromDate;
    }

    public TextField getTxtStatus() {
        return txtStatus;
    }

    public void setTxtStatus(TextField txtStatus) {
        this.txtStatus = txtStatus;
    }

    public TextField getTxtOrderCode() {
        return txtBatchCode;
    }

    public void setTxtOrderCode(TextField txtBatchCode) {
        this.txtBatchCode = txtBatchCode;
    }

    public VerticalLayout getMainLayout() {
        return mainLayout;
    }

    public void setMainLayout(VerticalLayout mainLayout) {
        this.mainLayout = mainLayout;
    }

    public GridLayout getGridLayout() {
        return gridLayout;
    }

    public void setGridLayout(GridLayout gridLayout) {
        this.gridLayout = gridLayout;
    }

    public Label getLblStockName() {
        return lblStockName;
    }

    public void setLblStockName(Label lblStockName) {
        this.lblStockName = lblStockName;
    }

    public Label getLblStockCode() {
        return lblStockCode;
    }

    public void setLblStockCode(Label lblStockCode) {
        this.lblStockCode = lblStockCode;
    }

    public ComboBox getCbxStatus() {
        return cbxStatus;
    }

    public void setCbxStatus(ComboBox cbxStatus) {
        this.cbxStatus = cbxStatus;
    }

    public Label getLblStatus() {
        return lblStatus;
    }

    public void setLblStatus(Label lblStatus) {
        this.lblStatus = lblStatus;
    }

    public TextField getTxtSerial() {
        return txtSerial;
    }

    public void setTxtSerial(TextField txtSerial) {
        this.txtSerial = txtSerial;
    }

    public Label getLblCustCode() {
        return lblCustCode;
    }

    public void setLblCustCode(Label lblCustCode) {
        this.lblCustCode = lblCustCode;
    }

    public Label getLblToDate() {
        return lblToDate;
    }

    public void setLblToDate(Label lblToDate) {
        this.lblToDate = lblToDate;
    }

    public Label getLblSerial() {
        return lblSerial;
    }

    public void setLblSerial(Label lblSerial) {
        this.lblSerial = lblSerial;
    }

    public Label getLblProduc() {
        return lblProduc;
    }

    public void setLblProduc(Label lblProduc) {
        this.lblProduc = lblProduc;
    }

    public TextField getTxtBatchCode() {
        return txtBatchCode;
    }

    public void setTxtBatchCode(TextField txtBatchCode) {
        this.txtBatchCode = txtBatchCode;
    }

    public Label getLblBatchCode() {
        return lblBatchCode;
    }

    public void setLblBatchCode(Label lblBatchCode) {
        this.lblBatchCode = lblBatchCode;
    }

    public ComboBox getCbProduct() {
        return cbProduct;
    }

    public void setCbProduct(ComboBox cbProduct) {
        this.cbProduct = cbProduct;
    }

    public VerticalLayout getVerticalLayout() {
        return verticalLayout;
    }

    public void setVerticalLayout(VerticalLayout verticalLayout) {
        this.verticalLayout = verticalLayout;
    }

    public Label getLblFromDate() {
        return lblFromDate;
    }

    public void setLblFromDate(Label lblFromDate) {
        this.lblFromDate = lblFromDate;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}