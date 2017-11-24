/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.ui;

import com.cms.component.GridManyButton;
import com.cms.component.TreeCommon;
import com.cms.dto.RolesDTO;
import com.cms.login.dto.Constants;
import com.cms.login.dto.ObjectsDTO;
import com.cms.utils.BundleUtils;
import com.cms.utils.DataUtil;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

/**
 *
 * @author quyen
 */
public class TreeObjects extends VerticalLayout {

    private TreeCommon treeObjects;
    private Button btnSave;
    private Button btnEdit;
    private ObjectsDTO objectParents;
    private final static String TREE_NAME = BundleUtils.getString("objects.caption");

    public TreeObjects() {
        buildMainLayout();
    }

    private void buildMainLayout() {
        setImmediate(true);
        setWidth("100%");
        setHeight("-1px");
        setMargin(true);
        setSpacing(true);

        objectParents = new ObjectsDTO();
        objectParents.setName(TREE_NAME);
        objectParents.setObjectId("0");
        objectParents.setObjectType("");
        treeObjects = new TreeCommon();
        addComponent(treeObjects);
        setComponentAlignment(treeObjects, Alignment.MIDDLE_CENTER);
        GridManyButton gridSaveButton = new GridManyButton(
                new String[]{Constants.BUTTON_SAVE, Constants.BUTTON_UPDATE});
        btnSave = gridSaveButton.getBtnCommon().get(0);
        btnEdit = gridSaveButton.getBtnCommon().get(1);
        addComponent(gridSaveButton);
    }

    public void addBtnSaveClickedListener(Button.ClickListener e) {
        btnSave.addClickListener(e);
    }

    public void addBtnEditClickedListener(Button.ClickListener e) {
        btnEdit.addClickListener(e);
    }

    public List<ObjectsDTO> getSelectedObjects() {
        return (List<ObjectsDTO>) treeObjects.getAllValues();
    }

    /**
     * Truyen du lieu cho cay chuc nang
     *
     * @param lstObjects
     * @param lstDefault
     */
    public void setData2TreeObjects(List<ObjectsDTO> lstObjects, List<String> lstDefault) {
        if (DataUtil.isListNullOrEmpty(lstDefault)) {
            treeObjects.setDataSource(Constants.NULL, objectParents, lstObjects, false, "name", "objectId", "objectType");
        } else {
            treeObjects.setDataSource(Constants.NULL, objectParents, lstObjects, lstDefault, false, "name", "objectId", "objectType");
        }
    }

    public TreeCommon getTreeObjects() {
        return treeObjects;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public void setTreeEnabled(boolean isEnable) {
        treeObjects.setTreeEnabled(isEnable);
    }

    public void setButtonEnable(RolesDTO role) {
        if (DataUtil.isNullObject(role)) {
            btnEdit.setEnabled(false);
            btnSave.setEnabled(false);
            treeObjects.tree.setEnabled(false);
        } else {
            btnEdit.setEnabled(true);
            btnSave.setEnabled(false);
            treeObjects.tree.setEnabled(false);
        }
        btnEdit.setCaption(BundleUtils.getString(Constants.BUTTON_UPDATE));
    }

    public void setBtnSaveEnable(boolean isEnable) {
        btnSave.setEnabled(isEnable);
    }

    public void setBtnEditEnable(boolean isEnable) {
        btnEdit.setEnabled(isEnable);
    }
}
