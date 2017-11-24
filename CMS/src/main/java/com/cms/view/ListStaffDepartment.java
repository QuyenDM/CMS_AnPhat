/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.anphat.list.controller.ListDeptAndStaffController;
import com.anphat.list.ui.DepartmentSearchPanel;
import com.anphat.list.ui.StaffSearchPanel;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.ui.CommonUI;
import com.cms.utils.BundleUtils;
import com.cms.utils.Constants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.themes.Runo;

/**
 *
 * @author hungkv
 */
public class ListStaffDepartment extends CommonUI implements View {

    //controller va ui cho panel left va panel right
    DepartmentSearchPanel searchDepartmentForm;
    StaffSearchPanel searchStaffForm;
    CommonTableFilterPanel tblListDepartmentUI;
    CommonTableFilterPanel tblListStaffUI;
    ListDeptAndStaffController listDeptAndStaffController;
    //them button duoi form search
    private Button btnSearchDept;
    private Button btnRefreshDept;
    //them button duoi table
    private Button btnAddDept;
    private Button btnEditDept;
    private Button btnDeleteDept;
    private Button btnCopyDept;
    private Button btnTransferRoleCusts;
    private Button btnAssignRole;

    private Button btnSearchEmp;
    private Button btnRefreshEmp;
    //
    private Button btnAddMapStaffCustomer;
    //them button duoi table
    private Button btnAddEmp;
    private Button btnEditEmp;
    private Button btnDeleteEmp;
    private Button btnCopyEmp;
    //define button labels

    //100316 NgocND6 chuyen quyen quan ly hang hoa
    public static final String TRANSFERROLE = BundleUtils.getString("transfer.goods.manage.tranfrolestaff");
    public static final String CAPTION_GRID_LAYOUT_SEARCH = BundleUtils.getString("caption.title.dept.info");
    public static final String CAPTION_VERLAYOUT_TABLE = BundleUtils.getString("caption.table.list.department");

    //them cac thanh phan cua panel right
    public ListStaffDepartment() {
        super(BundleUtils.getString("caption.title.listDept"), BundleUtils.getString("caption.title.listEmp"));
        mainLayout.setSplitPosition(30, Unit.PERCENTAGE);
        setCompositionRoot(mainLayout);
        //khoi tao cac thanh phan
        //khoi tao form search
        searchDepartmentForm = new DepartmentSearchPanel();
        //khoi tao table ui
        tblListDepartmentUI = new CommonTableFilterPanel();

        //100316 NgocND6 chuyen quyen quan ly hang hoa
        btnTransferRoleCusts = new Button(TRANSFERROLE);
        btnTransferRoleCusts.setIcon(new ThemeResource("img/transfer_icon.png"));
        //them cac component vao layout
        GridLayout horizontalLayout = new GridLayout(2, 1);
        horizontalLayout.setWidth("-1px");
        horizontalLayout.setMargin(true);
        horizontalLayout.setSpacing(true);
        //btn search
        btnSearchDept = new Button(Constants.BUTTON_SEARCH);
        btnSearchDept.setIcon(new ThemeResource(Constants.ICON.SEARCH));
        horizontalLayout.addComponent(btnSearchDept, 0, 0);
        //btn refresh
        btnRefreshDept = new Button(Constants.BUTTON_REFRESH);
        btnRefreshDept.setIcon(new ThemeResource(Constants.ICON.RESET));
        horizontalLayout.addComponent(btnRefreshDept, 1, 0);
        //add component
        leftLayout.addComponent(searchDepartmentForm);
        leftLayout.addComponent(horizontalLayout);
        leftLayout.addComponent(tblListDepartmentUI);
        leftLayout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
        leftLayout.setMargin(true);

        //===============right==========================
        searchStaffForm = new StaffSearchPanel();
        //khoi tao table ui
        tblListStaffUI = new CommonTableFilterPanel();

        btnAddMapStaffCustomer = new Button(BundleUtils.getString("staff.customer.map"));
        btnAddMapStaffCustomer.setDescription(BundleUtils.getString("staff.customer.map"));
        btnAddMapStaffCustomer.setIcon(new ThemeResource(Constants.ICON.IMPORT));

        btnAssignRole = new Button(BundleUtils.getString("assign.roles.button"));
        btnAssignRole.setIcon(FontAwesome.ANCHOR);
        //them cac component vao layout
        GridLayout horizontalLayout2 = new GridLayout(3, 1);
        horizontalLayout2.setWidth("-1px");
        horizontalLayout2.setMargin(true);
        horizontalLayout2.setSpacing(true);
        //NgocND6 tao layout de add button chuyen quyen cho nhan vien
        GridLayout gridLayout = new GridLayout(3, 1);
        gridLayout.setMargin(true);
        gridLayout.setSpacing(true);
        gridLayout.addComponent(btnAssignRole, 0, 0);
//        gridLayout.addComponent(btnAddMapStaffCustomer, 1, 0);
        gridLayout.addComponent(btnTransferRoleCusts, 2, 0);
        //Tam thoi an nut phan bo lai khach hang
        btnTransferRoleCusts.setVisible(false);
        //btn search emp
        btnSearchEmp = new Button(Constants.BUTTON_SEARCH);
        btnSearchEmp.setIcon(new ThemeResource(Constants.ICON.SEARCH));
        horizontalLayout2.addComponent(btnSearchEmp, 0, 0);
        //btn refresh emp
        btnRefreshEmp = new Button(Constants.BUTTON_REFRESH);
        btnRefreshEmp.setIcon(new ThemeResource(Constants.ICON.RESET));
        horizontalLayout2.addComponent(btnRefreshEmp, 1, 0);
        //

        //add component
        rightLayout.addComponent(searchStaffForm);
        rightLayout.addComponent(horizontalLayout2);
        rightLayout.addComponent(tblListStaffUI);
        rightLayout.setComponentAlignment(horizontalLayout2, Alignment.MIDDLE_CENTER);
        rightLayout.addComponent(gridLayout);
        rightLayout.setComponentAlignment(gridLayout, Alignment.MIDDLE_CENTER);
        rightLayout.setMargin(true);
        //=============

        this.listDeptAndStaffController = new ListDeptAndStaffController(this);
    }

    public DepartmentSearchPanel getSearchDepartmentForm() {
        return searchDepartmentForm;
    }

    public void setSearchDepartmentForm(DepartmentSearchPanel searchDepartmentForm) {
        this.searchDepartmentForm = searchDepartmentForm;
    }

    public CommonTableFilterPanel getTblListDepartmentUI() {
        return tblListDepartmentUI;
    }

    public void setTblListDepartmentUI(CommonTableFilterPanel tblListDepartmentUI) {
        this.tblListDepartmentUI = tblListDepartmentUI;
    }

    public ListDeptAndStaffController getListDeptAndStaffController() {
        return listDeptAndStaffController;
    }

    public void setListDeptAndStaffController(ListDeptAndStaffController listDeptAndStaffController) {
        this.listDeptAndStaffController = listDeptAndStaffController;
    }

    public Button getBtnSearchDept() {
        return btnSearchDept;
    }

    public void setBtnSearchDept(Button btnSearchDept) {
        this.btnSearchDept = btnSearchDept;
    }

    public Button getBtnRefreshDept() {
        return btnRefreshDept;
    }

    public void setBtnRefreshDept(Button btnRefreshDept) {
        this.btnRefreshDept = btnRefreshDept;
    }

    public Button getBtnAddDept() {
        return btnAddDept;
    }

    public void setBtnAddDept(Button btnAddDept) {
        this.btnAddDept = btnAddDept;
    }

    public Button getBtnEditDept() {
        return btnEditDept;
    }

    public void setBtnEditDept(Button btnEditDept) {
        this.btnEditDept = btnEditDept;
    }

    public Button getBtnDeleteDept() {
        return btnDeleteDept;
    }

    public void setBtnDeleteDept(Button btnDeleteDept) {
        this.btnDeleteDept = btnDeleteDept;
    }

    public Button getBtnCopyDept() {
        return btnCopyDept;
    }

    public void setBtnCopyDept(Button btnCopyDept) {
        this.btnCopyDept = btnCopyDept;
    }

    public StaffSearchPanel getSearchStaffForm() {
        return searchStaffForm;
    }

    public void setSearchStaffForm(StaffSearchPanel searchStaffForm) {
        this.searchStaffForm = searchStaffForm;
    }

    public CommonTableFilterPanel getTblListStaffUI() {
        return tblListStaffUI;
    }

    public void setTblListStaffUI(CommonTableFilterPanel tblListStaffUI) {
        this.tblListStaffUI = tblListStaffUI;
    }

    public Button getBtnSearchEmp() {
        return btnSearchEmp;
    }

    public void setBtnSearchEmp(Button btnSearchEmp) {
        this.btnSearchEmp = btnSearchEmp;
    }

    public Button getBtnRefreshEmp() {
        return btnRefreshEmp;
    }

    public void setBtnRefreshEmp(Button btnRefreshEmp) {
        this.btnRefreshEmp = btnRefreshEmp;
    }

    public Button getBtnAddEmp() {
        return btnAddEmp;
    }

    public void setBtnAddEmp(Button btnAddEmp) {
        this.btnAddEmp = btnAddEmp;
    }

    public Button getBtnEditEmp() {
        return btnEditEmp;
    }

    public void setBtnEditEmp(Button btnEditEmp) {
        this.btnEditEmp = btnEditEmp;
    }

    public Button getBtnDeleteEmp() {
        return btnDeleteEmp;
    }

    public void setBtnDeleteEmp(Button btnDeleteEmp) {
        this.btnDeleteEmp = btnDeleteEmp;
    }

    public Button getBtnCopyEmp() {
        return btnCopyEmp;
    }

    public void setBtnCopyEmp(Button btnCopyEmp) {
        this.btnCopyEmp = btnCopyEmp;
    }

    public Button getBtnAddMapStaffCustomer() {
        return btnAddMapStaffCustomer;
    }

    public void setBtnAddMapStaffCustomer(Button btnAddMapStaffCustomer) {
        this.btnAddMapStaffCustomer = btnAddMapStaffCustomer;
    }

    public Button getBtnTransferRoleCusts() {
        return btnTransferRoleCusts;
    }

    public void setBtnTransferRoleCusts(Button btnTransferRoleCusts) {
        this.btnTransferRoleCusts = btnTransferRoleCusts;
    }

    //Gan su kien click nut gan vai tro
    public void addBtnAssignRolesListener(Button.ClickListener e) {
        btnAssignRole.addClickListener(e);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
