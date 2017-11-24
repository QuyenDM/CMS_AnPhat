/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.anphat.statistics.controller.StatisticsCategoryListController;
import com.anphat.statistics.ui.StatisticsCategoryListSearchPanel;
import com.cms.component.CommonOnePanelUI;
import com.cms.dto.StaffDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;

/**
 *
 * @author quyen
 */
public class StatisticsCategoryListView extends CommonOnePanelUI implements View {

    private StatisticsCategoryListSearchPanel categoryListForm;
    private CommonTableFilterPanel panelTblStatisticsCategoryList;

    private StatisticsCategoryListController categoryListController;
    private StaffDTO staff;

    public StatisticsCategoryListView() {

        buildMainLayout();
//        btnPrintBB.setEnabled(false);
    }

    private void buildMainLayout() {

        layoutMain.setMargin(true);
        layoutMain.setSpacing(true);
        categoryListForm = new StatisticsCategoryListSearchPanel();
        categoryListForm.btnSearch.setCaption(BundleUtils.getString("statistics.button.caption"));
        layoutMain.addComponent(categoryListForm);
        panelTblStatisticsCategoryList = new CommonTableFilterPanel();
        panelTblStatisticsCategoryList.getToolbar().setVisible(false);
        layoutMain.addComponent(panelTblStatisticsCategoryList);

        panelMain.setCaption(BundleUtils.getString("title.statistics.categoryList"));
        StatisticsCategoryListController servicesController = new StatisticsCategoryListController(this);
        staff = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");
        servicesController.setStaff(staff);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public StatisticsCategoryListSearchPanel getCategoryListForm() {
        return categoryListForm;
    }

    public void setCategoryListForm(StatisticsCategoryListSearchPanel categoryListForm) {
        this.categoryListForm = categoryListForm;
    }

    public CommonTableFilterPanel getPanelTblStatisticsCategoryList() {
        return panelTblStatisticsCategoryList;
    }

    public void setPanelTblStatisticsCategoryList(CommonTableFilterPanel panelTblStatisticsCategoryList) {
        this.panelTblStatisticsCategoryList = panelTblStatisticsCategoryList;
    }

    public StatisticsCategoryListController getCategoryListController() {
        return categoryListController;
    }

    public void setCategoryListController(StatisticsCategoryListController categoryListController) {
        this.categoryListController = categoryListController;
    }

    public StaffDTO getStaff() {
        return staff;
    }

    public void setStaff(StaffDTO staff) {
        this.staff = staff;
    }

}
