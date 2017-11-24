/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.view;

import com.anphat.list.controller.RolesObjectsController;
import com.anphat.list.ui.RolesSearchPanel;
import com.anphat.list.ui.TreeObjects;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.cms.ui.CommonUI;
import com.cms.utils.BundleUtils;

/**
 *
 * @author QuyenDM
 */
public class RolesView extends CommonUI implements View {

    private RolesSearchPanel rolesSearchPanel;
    private TreeObjects treeObjects;
    private final RolesObjectsController rolesObjectsController;

    public RolesView() {
        super(BundleUtils.getString("roles.search.caption"), BundleUtils.getString("roles.detail.caption"));
//        mainLayout.setSplitPosition(50f);
        buildLeftLayout();
        buildRightLayout();
        rolesObjectsController = new RolesObjectsController(this);
        rolesObjectsController.init();
    }

    private void buildLeftLayout() {
        rolesSearchPanel = new RolesSearchPanel();
        leftLayout.addComponent(rolesSearchPanel);
    }

    private void buildRightLayout() {
        treeObjects = new TreeObjects();
        rightLayout.addComponent(treeObjects);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public RolesSearchPanel getRolesSearchPanel() {
        return rolesSearchPanel;
    }

    public TreeObjects getTreeObjects() {
        return treeObjects;
    }

}
