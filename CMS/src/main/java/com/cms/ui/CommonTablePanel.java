package com.cms.ui;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;
import com.cms.utils.BundleUtils;

public class CommonTablePanel extends CustomComponent {

    /*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
    @AutoGenerated
    private VerticalLayout mainLayout;
    @AutoGenerated
    private VerticalLayout verticalLayout;
    //    toolbar table
    private HorizontalLayout toolbar;
    @AutoGenerated
    private Table tableCommon;
    @AutoGenerated
    private GridLayout gridLayoutTableCommon;
    @AutoGenerated
    private Label txtTotalWeightService;
    @AutoGenerated
    private Label lbTotalWeight;

    Button addButton;
    Button coppyButton;
    Button deleteButton;
    Button editButton;
    Button importButton;
    Button exportButton;
    Button configButton;

//   layout addition
    HorizontalLayout horizoltalLayout;
    Button btnAdd;
    Button btnSave;
    Button btnDelelete;
    boolean isPaging;

    /**
     * The constructor should first build the main layout, set the composition
     * root and then do any custom initialization.
     *
     * The constructor will not be automatically regenerated by the visual
     * editor.
     *
     */
    public CommonTablePanel() {
        buildMainLayout();
        setCompositionRoot(mainLayout);

        // TODO add user code here
    }

    public VerticalLayout getMainLayout() {
        return mainLayout;
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

        // verticalLayout
        verticalLayout = buildVerTBLListGoodInfomation();
        mainLayout.addComponent(verticalLayout);

        return mainLayout;
    }

    @AutoGenerated
    private VerticalLayout buildVerTBLListGoodInfomation() {
        // common part: create layout
        verticalLayout = new VerticalLayout();
        verticalLayout.setStyleName("custom-feildset");
        verticalLayout.setImmediate(true);
        verticalLayout.setWidth("100.0%");
        verticalLayout.setHeight("-1px");
        verticalLayout.setMargin(false);

        // gridLayoutTableCommon
        gridLayoutTableCommon = buildGridTBLInfomation();
        verticalLayout.addComponent(gridLayoutTableCommon);

        //      toolbar
        toolbar = builHorizontalToolBar();
        verticalLayout.addComponent(toolbar);
        verticalLayout.setComponentAlignment(toolbar, Alignment.TOP_RIGHT);

        // tableCommon
        tableCommon = new Table();
//        tableCommon.setImmediate(true);
        tableCommon.setSelectable(false);
        tableCommon.setWidth("100.0%");
//        tableCommon.refreshRowCache();
//        tableCommon.requestRepaint();
        tableCommon.setStyleName(Runo.TABLE_SMALL);
//        tableCommon.setPageLength(Integer.valueOf(Constants.PAGE_SIZE_DEFAULT_5));
        tableCommon.setColumnCollapsingAllowed(true);
//        tableCommon.setMultiSelect(true);

        verticalLayout.addComponent(tableCommon);
        horizoltalLayout = new HorizontalLayout();
        horizoltalLayout.setWidth("-1px");
//        BundleUtils.getString("common.button.add")
        btnAdd = new Button();
        btnAdd.setIcon(new ThemeResource("img/add-icon.png"));
//        BundleUtils.getString("common.button.save")
        btnSave = new Button();
        btnSave.setIcon(new ThemeResource("img/icon-save.png"));
//        BundleUtils.getString("common.button.delete")
        btnDelelete = new Button();
        btnDelelete.setIcon(new ThemeResource("img/icon_delete.png"));
        btnAdd.setVisible(false);
        btnSave.setVisible(false);
        btnDelelete.setVisible(false);
        horizoltalLayout.setVisible(false);
        horizoltalLayout.addComponent(btnAdd);
        horizoltalLayout.addComponent(btnSave);
        horizoltalLayout.addComponent(btnDelelete);
        verticalLayout.addComponent(horizoltalLayout);
        return verticalLayout;
    }

    @AutoGenerated
    private GridLayout buildGridTBLInfomation() {
        // common part: create layout
        gridLayoutTableCommon = new GridLayout();
        gridLayoutTableCommon.setImmediate(false);
        gridLayoutTableCommon.setWidth("100.0%");
        gridLayoutTableCommon.setHeight("-1px");
        gridLayoutTableCommon.setMargin(false);
        gridLayoutTableCommon.setColumns(8);

        // lbTotalWeight
        lbTotalWeight = new Label();
        lbTotalWeight.setImmediate(false);
        lbTotalWeight.setWidth("-1px");
        lbTotalWeight.setHeight("-1px");
        lbTotalWeight.setValue("Tổng trọng lượng");
        lbTotalWeight.setVisible(false);
        gridLayoutTableCommon.addComponent(lbTotalWeight, 5, 0);

        // txtTotalWeightService
        txtTotalWeightService = new Label();
        txtTotalWeightService.setImmediate(false);
        txtTotalWeightService.setWidth("-1px");
        txtTotalWeightService.setHeight("-1px");
        txtTotalWeightService.setValue("");
        txtTotalWeightService.setVisible(false);
        gridLayoutTableCommon.addComponent(txtTotalWeightService, 6, 0);

        return gridLayoutTableCommon;
    }

    private HorizontalLayout builHorizontalToolBar() {
        toolbar = new HorizontalLayout();
        toolbar.setImmediate(true);
        toolbar.setMargin(false);
        toolbar.setWidth("-1px");
        toolbar.setHeight("30px");
        toolbar.setSpacing(true);
        toolbar.setVisible(true);
        toolbar.addStyleName("v-spacing-toolbar");
        //Button import =NgocND6 Modifier
        importButton = new Button();
        importButton.setIcon(new ThemeResource("img/import_excel.png"));
        importButton.setWidth("-1px");
        importButton.setHeight("-1px");
        importButton.setStyleName("v-button-link");
        importButton.setVisible(false);
        importButton.setDescription(BundleUtils.getString("common.button.importFile"));
        toolbar.addComponent(importButton);
        //Button export
        exportButton = new Button();
        exportButton.setIcon(new ThemeResource("img/export_excel.png"));
        exportButton.setWidth("-1px");
        exportButton.setHeight("-1px");
        exportButton.setStyleName("v-button-link");
        exportButton.setVisible(false);
        exportButton.setDescription(BundleUtils.getString("common.button.exportFile"));
        toolbar.addComponent(exportButton);

        //QuyenDM configButton
        configButton = new Button(new ThemeResource("img/config_button.png"));
        configButton.setWidth("-1px");
        configButton.setHeight("-1px");
        configButton.setStyleName("v-button-link");
        configButton.setVisible(false);
        configButton.setDescription(BundleUtils.getString("common.button.config"));
        toolbar.addComponent(configButton);

        //        button add;
        addButton = new Button();
        addButton.setIcon(new ThemeResource("img/add-icon.png"));
        addButton.setVisible(false);
        addButton.setWidth("-1px");
        addButton.setHeight("-1px");
        addButton.setStyleName("v-button-link");
        addButton.setDescription(BundleUtils.getString("common.button.add"));
        toolbar.addComponent(addButton);

        coppyButton = new Button();
        coppyButton.setIcon(new ThemeResource("img/copy.png"));
        coppyButton.setVisible(false);
        coppyButton.setWidth("-1px");
        coppyButton.setHeight("-1px");
        coppyButton.setStyleName("v-button-link");
        coppyButton.setDescription(BundleUtils.getString("common.button.copy"));
        toolbar.addComponent(coppyButton);

        editButton = new Button();
        editButton.setIcon(new ThemeResource("img/edit-icon.png"));
        editButton.setVisible(false);
        editButton.setWidth("-1px");
        editButton.setHeight("-1px");
        editButton.setStyleName("v-button-link");
        editButton.setDescription(BundleUtils.getString("common.button.edit"));
        toolbar.addComponent(editButton);

        deleteButton = new Button();
        deleteButton.setIcon(new ThemeResource("img/icondelete.png"));
        deleteButton.setVisible(false);
        deleteButton.setWidth("-1px");
        deleteButton.setHeight("-1px");
        deleteButton.setStyleName("v-button-link");
        deleteButton.setDescription(BundleUtils.getString("common.button.delete"));
        toolbar.addComponent(deleteButton);

        return toolbar;
    }

    public VerticalLayout getVerticalLayout() {
        return verticalLayout;
    }

    public void setVerticalLayout(VerticalLayout verticalLayout) {
        this.verticalLayout = verticalLayout;
    }

    public Table getTableCommon() {
        return tableCommon;
    }

    public void setTableCommon(Table tableCommon) {
        this.tableCommon = tableCommon;
    }

    public GridLayout getGridLayoutTableCommon() {
        return gridLayoutTableCommon;
    }

    public void setGridLayoutTableCommon(GridLayout gridLayoutTableCommon) {
        this.gridLayoutTableCommon = gridLayoutTableCommon;
    }

    public Label getTxtTotalWeightService() {
        return txtTotalWeightService;
    }

    public void setTxtTotalWeightService(Label txtTotalWeightService) {
        this.txtTotalWeightService = txtTotalWeightService;
    }

    public Label getLbTotalWeight() {
        return lbTotalWeight;
    }

    public void setLbTotalWeight(Label lbTotalWeight) {
        this.lbTotalWeight = lbTotalWeight;
    }

    public HorizontalLayout getHorizoltalLayout() {
        return horizoltalLayout;
    }

    public void setHorizoltalLayout(HorizontalLayout horizoltalLayout) {
        this.horizoltalLayout = horizoltalLayout;
    }

    public Button getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(Button btnAdd) {
        this.btnAdd = btnAdd;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(Button btnSave) {
        this.btnSave = btnSave;
    }

    public Button getBtnDelelete() {
        return btnDelelete;
    }

    public void setBtnDelelete(Button btnDelelete) {
        this.btnDelelete = btnDelelete;
    }

    public boolean isIsPaging() {
        return isPaging;
    }

    public void setIsPaging(boolean isPaging) {
        this.isPaging = isPaging;
    }

    public HorizontalLayout getToolbar() {
        return toolbar;
    }

    public void setToolbar(HorizontalLayout toolbar) {
        this.toolbar = toolbar;
    }

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }

    public Button getCoppyButton() {
        return coppyButton;
    }

    public void setCoppyButton(Button coppyButton) {
        this.coppyButton = coppyButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    public Button getEditButton() {
        return editButton;
    }

    public void setEditButton(Button editButton) {
        this.editButton = editButton;
    }

    public Button getImportButton() {
        return importButton;
    }

    public void setImportButton(Button importButton) {
        this.importButton = importButton;
    }

    public Button getExportButton() {
        return exportButton;
    }

    public void setExportButton(Button exportButton) {
        this.exportButton = exportButton;
    }

    public Button getConfigButton() {
        return configButton;
    }

    public void setConfigButton(Button configButton) {
        this.configButton = configButton;
    }

}
