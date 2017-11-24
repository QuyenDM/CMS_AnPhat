/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.anphat.customer.controller.SearchCustomerFromTaxCodeController;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CommonTwoPanelUI;
import com.cms.dto.TermInformationDTO;
import com.cms.ui.CommonButtonClickListener;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class SearchCustomerFromTaxCode extends CommonTwoPanelUI implements View {

    private GridLayout searchGrid;

    private TextField taxCode; //Ma so thue
    private Button btnSearch;
    private Button btnCreateDoc;

    private CommonTableFilterPanel tblPanelCustomers;
    private CommonTableFilterPanel tblPanelTermInformations;
    private final LinkedHashMap<String, CustomTable.Align> HEADER_TERM_INFORMATION = BundleUtils.getHeadersFilter("term.information.full");
    private BeanItemContainer<TermInformationDTO> containerTermInformation;

    public SearchCustomerFromTaxCode() {
        splitLayout.setSplitPosition(35, Unit.PERCENTAGE);
        buildSearchGrid();
        buildTableGrid();
        buildTermInformationTable();
        SearchCustomerFromTaxCodeController controller = new SearchCustomerFromTaxCodeController(this);
        controller.buildController();
    }

    private void buildTermInformationTable() {
        tblPanelTermInformations = new CommonTableFilterPanel();
        tblPanelTermInformations.setToolbar(Boolean.FALSE);
        containerTermInformation = new BeanItemContainer(TermInformationDTO.class);
        CommonFunctionTableFilter.initTable(tblPanelTermInformations, HEADER_TERM_INFORMATION,
                containerTermInformation, "Danh sách hạn và danh bạ", -1, Constants.CAPTION.TERM_INFORMATION);
        rightPanel.setCaption("Thông tin hạn");
        tblPanelTermInformations.setMultiSelected(false);
        rightLayout.addComponent(tblPanelTermInformations);
    }

    private void buildSearchGrid() {
        leftPanel.setCaption("Tìm kiếm khách hàng");
        Label lbTaxCode = CommonUtils.buildLabel("Mã số thuế", false);
        taxCode = CommonUtils.buildTextField(null, 20, "ALT + 1");
        taxCode.focus();
        taxCode.addShortcutListener(
                new AbstractField.FocusShortcut(taxCode, ShortcutAction.KeyCode.NUM1,
                        ShortcutAction.ModifierKey.ALT));
        btnSearch = new Button("Tìm kiếm", FontAwesome.SEARCH);
        searchGrid = new GridLayout(3, 1);
        CommonUtils.setBasicAttributeLayout(searchGrid, "Tìm kiếm thông tin theo mã số thuế", true);
        searchGrid.addComponent(lbTaxCode, 0, 0);
        searchGrid.addComponent(taxCode, 1, 0);
        searchGrid.addComponent(btnSearch, 2, 0);
        searchGrid.setComponentAlignment(lbTaxCode, Alignment.MIDDLE_RIGHT);
        searchGrid.setComponentAlignment(taxCode, Alignment.MIDDLE_LEFT);
        searchGrid.setComponentAlignment(btnSearch, Alignment.MIDDLE_LEFT);
        leftLayout.addComponent(searchGrid);
    }

    private void buildTableGrid() {
        tblPanelCustomers = new CommonTableFilterPanel();
        tblPanelCustomers.setToolbar(Boolean.FALSE);
        leftLayout.addComponent(tblPanelCustomers);
        btnCreateDoc = new Button("Tạo hợp đồng", FontAwesome.WORDPRESS);
        btnCreateDoc.setEnabled(false);
        leftLayout.addComponent(btnCreateDoc);
        leftLayout.setComponentAlignment(btnCreateDoc, Alignment.MIDDLE_CENTER);
    }

    public void setDataToTblTermInformation(List<TermInformationDTO> lstTermInfos) {
        containerTermInformation.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstTermInfos)) {
            containerTermInformation.addAll(lstTermInfos);
        }
        CommonFunctionTableFilter.refreshTable(tblPanelTermInformations, HEADER_TERM_INFORMATION, containerTermInformation);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public void setTaxCodeFocus() {
        taxCode.focus();
    }

    public String getTaxCodeInputted() {
        return taxCode.getValue();
    }

    public GridLayout getSearchGrid() {
        return searchGrid;
    }

    public void setSearchGrid(GridLayout searchGrid) {
        this.searchGrid = searchGrid;
    }

    public TextField getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(TextField taxCode) {
        this.taxCode = taxCode;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(Button btnSearch) {
        this.btnSearch = btnSearch;
    }

    public CommonTableFilterPanel getTblPanelCustomers() {
        return tblPanelCustomers;
    }

    public void setTblPanelCustomers(CommonTableFilterPanel tblPanelCustomers) {
        this.tblPanelCustomers = tblPanelCustomers;
    }

    public void addBtnSearchListener(CommonButtonClickListener e) {
        btnSearch.addClickListener(e);
    }

    public void addBtnCreateDocListener(CommonButtonClickListener e) {
        btnCreateDoc.addClickListener(e);
    }

    public void addTableCustomerValueChangeListener(Property.ValueChangeListener e) {
        tblPanelCustomers.getMainTable().addValueChangeListener(e);
    }

    public void setButtonCreateDocEnabled(boolean isEnabled) {
        btnCreateDoc.setEnabled(isEnabled);
    }

    public TermInformationDTO getSelectedTermInfo() {
        return (TermInformationDTO) tblPanelTermInformations.getMainTable().getValue();
    }
}
