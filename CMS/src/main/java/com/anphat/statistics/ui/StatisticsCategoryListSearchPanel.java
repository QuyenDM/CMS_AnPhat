/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.statistics.ui;

import com.cms.component.CommonSearchForm;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;

/**
 *
 * @author quyen
 */
public class StatisticsCategoryListSearchPanel extends CommonSearchForm {

    private ComboBox cboService;
    private ComboBox cboMineName;
    private ComboBox cboStaff;
    private DateField dfStartTime;
    private DateField dfEndTime;

    public StatisticsCategoryListSearchPanel() {
        this.caption = BundleUtils.getString("statistics.categoryList.caption");
        initForm();
    }

    private void initForm() {
        column = 5;

        row = 1;
        init();
        setCaption();
        buildComponents();
        initSearchContents();
//        addShortcuts();
    }

    public void buildComponents() {
        cboService = CommonUtils.buildComboBox("term.information.service");
        cboService.setRequired(true);
        cboMineName = CommonUtils.buildComboBox("statistics.categoryList.mineName");
        cboStaff = CommonUtils.buildComboBox("statistics.categoryList.staff");
        dfStartTime = CommonUtils.buildDateField("statistics.categoryList.startTime");
        dfEndTime = CommonUtils.buildDateField("statistics.categoryList.endTime");
    }

    @Override
    public void initSearchContents() {
        searchLayout.setColumns(column);
        searchLayout.setRows(row);
        searchLayout.addComponent(cboService, 0, 0);
        searchLayout.addComponent(cboMineName, 1, 0);
        searchLayout.addComponent(dfStartTime, 2, 0);
        searchLayout.addComponent(dfEndTime, 3, 0);
        searchLayout.addComponent(cboStaff, 4, 0);
    }

    public void refreshData(){
        dfStartTime.clear();
        dfEndTime.clear();
    }
    
    public ComboBox getCboMineName() {
        return cboMineName;
    }

    public void setCboMineName(ComboBox cboMineName) {
        this.cboMineName = cboMineName;
    }

    public ComboBox getCboStaff() {
        return cboStaff;
    }

    public void setCboStaff(ComboBox cboStaff) {
        this.cboStaff = cboStaff;
    }

    public DateField getDfStartTime() {
        return dfStartTime;
    }

    public void setDfStartTime(DateField dfStartTime) {
        this.dfStartTime = dfStartTime;
    }

    public DateField getDfEndTime() {
        return dfEndTime;
    }

    public void setDfEndTime(DateField dfEndTime) {
        this.dfEndTime = dfEndTime;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(Button btnSearch) {
        this.btnSearch = btnSearch;
    }

    public Button getBtnReset() {
        return btnReset;
    }

    public void setBtnReset(Button btnReset) {
        this.btnReset = btnReset;
    }

    public ComboBox getCboService() {
        return cboService;
    }

    public void setCboService(ComboBox cboService) {
        this.cboService = cboService;
    }

}
