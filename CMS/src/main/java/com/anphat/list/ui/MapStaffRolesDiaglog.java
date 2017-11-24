/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.ui;

import com.cms.component.CommonDialog;
import com.cms.component.GridManyButton;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.vaadin.ui.Button;

/**
 *
 * @author quyen
 */
public class MapStaffRolesDiaglog extends CommonDialog {

    private RolesSearchPanel rolesSearchPanel;
    private Button btnSave;

    public MapStaffRolesDiaglog(String caption) {
        init(caption);
    }

    private void init(String caption) {
        setInfo("70%", "-1px", caption);
        buildMainDialog();
    }

    private void buildMainDialog() {
        rolesSearchPanel = new RolesSearchPanel();
        mainLayout.addComponent(rolesSearchPanel);

        GridManyButton gridButton = CommonUtils.getCommonButtonDialog(this);
        btnSave = gridButton.getBtnCommon().get(0);
        mainLayout.addComponent(gridButton);
    }

    public void setTblRolesVisiableOnly() {
        rolesSearchPanel.setTblRoleVisiableOnly();
    }

    public void addBtnSaveListener(Button.ClickListener e) {
        btnSave.addClickListener(e);
    }

    //Getter and setter
    public RolesSearchPanel getRolesSearchPanel() {
        return rolesSearchPanel;
    }

    public void setBtnSaveVisible(boolean b) {
        btnSave.setVisible(false);
    }

}
