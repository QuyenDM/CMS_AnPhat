/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.cms.dto.RolesDTO;
import com.cms.utils.DataUtil;
import com.cms.view.RolesView;
import com.google.common.collect.Lists;
import com.vaadin.data.Property;
import java.util.List;
import java.util.Set;

/**
 *
 * @author quyen
 */
public class RolesObjectsController {

    private final RolesView rolesView;
    private RolesController rolesController;
    private TreeObjectsController treeObjectsController;

    public RolesObjectsController(RolesView rolesView) {
        this.rolesView = rolesView;
    }

    public void init() {
        rolesController = new RolesController(rolesView.getRolesSearchPanel());
        rolesController.init();
        treeObjectsController = new TreeObjectsController(rolesView.getTreeObjects());
        treeObjectsController.init();

        addListeners();
    }

    private void addListeners() {
        addTblRolesValueChangeListener();
    }

    /**
     * Them su kien khi nguoi dung click vao 1 ban ghi
     */
    private void addTblRolesValueChangeListener() {
        rolesController.addTblRolesValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                Set<RolesDTO> roles = (Set<RolesDTO>) event.getProperty().getValue();
                List<RolesDTO> lstRoles = Lists.newArrayList(roles);
                RolesDTO role = null;
                if (!DataUtil.isListNullOrEmpty(lstRoles)) {
                    role = lstRoles.get(0);
                }
                treeObjectsController.setEventWhenTblRoleValueChanged(role);
            }
        });
    }

    
}
