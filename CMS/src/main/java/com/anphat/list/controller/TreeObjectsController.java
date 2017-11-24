/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.list.ui.TreeObjects;
import com.cms.dto.RolesDTO;
import com.cms.login.dto.ObjectsDTO;
import com.cms.login.dto.RoleObjectsDTO;
import com.cms.login.ws.WSObjects;
import com.cms.login.ws.WSRoleObjects;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonMessages;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.vaadin.ui.Button;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quyen
 */
public class TreeObjectsController {

    private final TreeObjects treeObjects;
    private List<ObjectsDTO> lstObjects;
    private RolesDTO roles;

    public TreeObjectsController(TreeObjects treeObjects) {
        this.treeObjects = treeObjects;
    }

    public void init() {
        getDatas();
        initComponents();
        addListeners();
    }

    private void getDatas() {
        ObjectsDTO o = new ObjectsDTO();
        lstObjects = WSObjects.getListObjectsDTO(o, 0, 100, "asc", "name");
    }

    private void initComponents() {
        treeObjects.setData2TreeObjects(lstObjects, null);
        treeObjects.setTreeEnabled(false);
        treeObjects.setBtnSaveEnable(false);
        treeObjects.setBtnEditEnable(false);
    }

    private void addListeners() {
        addBtnSaveClickedListener();
        addBtnEditClickedListener();
    }

    /**
     * Them su kien khi click nut edit
     */
    private void addBtnEditClickedListener() {
        treeObjects.addBtnEditClickedListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (Constants.BUTTON_UPDATE.equalsIgnoreCase(event.getButton().getCaption())) {
                    treeObjects.setTreeEnabled(true);
                    event.getButton().setCaption(Constants.BUTTON_CANCEL);
                    treeObjects.setBtnSaveEnable(true);
                } else {
                    treeObjects.setTreeEnabled(false);
                    treeObjects.setBtnSaveEnable(false);
                    event.getButton().setCaption(Constants.BUTTON_UPDATE);
                }
                event.getButton().setEnabled(true);
            }
        });
    }

    private void addBtnSaveClickedListener() {
        treeObjects.addBtnSaveClickedListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!DataUtil.isNullObject(roles)) {
                    doSave();
                }
                event.getButton().setEnabled(true);
            }
        });
    }

    /**
     * Truyen du lieu cho cay chuc nang
     *
     * @param lstValues
     */
    public void setData2TreeObjects(List<String> lstValues) {
        treeObjects.setData2TreeObjects(lstObjects, lstValues);
    }

    /**
     * Lay du lieu do nguoi dung chon tu cay chuc nang
     *
     * @return
     */
    public List<ObjectsDTO> getSelectedObjects() {
        return treeObjects.getSelectedObjects();
    }

    /**
     * Xu ly su kien khi nguoi dung chon 1 ban ghi trong bang vai tro
     *
     * @param roles
     */
    public void setEventWhenTblRoleValueChanged(RolesDTO roles) {
        this.roles = roles;
        List<String> lstValuesFromRoles = getObjectsFromRoles(roles);
        setData2TreeObjects(lstValuesFromRoles);
        treeObjects.setButtonEnable(roles);
    }

    /**
     * Lay du lieu chuc nang tu vai tro
     *
     * @param roles
     * @return
     */
    protected List<String> getObjectsFromRoles(RolesDTO roles) {
        List<ObjectsDTO> lstObjectsFromRoles = getListObjectsDTO(roles);

        if (DataUtil.isListNullOrEmpty(lstObjectsFromRoles)) {
            return null;
        } else {
            List<String> lstObjectIds = new ArrayList<>();
            for (ObjectsDTO o : lstObjectsFromRoles) {
                lstObjectIds.add(o.getObjectId());
            }
            return lstObjectIds;
        }
    }

    private List<ObjectsDTO> getListObjectsDTO(RolesDTO roles) {
        if (DataUtil.isNullObject(roles)) {
            return null;
        }
        List<ObjectsDTO> lstObjectsFromRoles = WSObjects.getListObjectByRole(roles);
        if (DataUtil.isListNullOrEmpty(lstObjectsFromRoles)) {
            return null;
        } else {
            return lstObjectsFromRoles;
        }
    }

    protected void doSave() {
        List<RoleObjectsDTO> lstInsertOrUpdateRoleObjects = new ArrayList<>();
        List<ObjectsDTO> lstObjects2Save = getSelectedObjects();
        RoleObjectsDTO roleObjectsDTO;
        List<ObjectsDTO> lstValuesFromRoles = getListObjectsDTO(roles);
        List<String> lstObjectIds = null;
        try {
            lstObjectIds = DataUtil.getListValueFromList(lstValuesFromRoles, "objectId");
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException ex) {
            Logger.getLogger(TreeObjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!DataUtil.isListNullOrEmpty(lstObjects2Save)) {
            //Lay danh sach cap nhat
            for (ObjectsDTO o : lstObjects2Save) {
                if (!DataUtil.isListNullOrEmpty(lstObjectIds)) {
                    if (!lstObjectIds.contains(o.getObjectId())) {
                        roleObjectsDTO = new RoleObjectsDTO();
                        roleObjectsDTO.setObjectId(o.getObjectId());
                        roleObjectsDTO.setRoleId(roles.getRoleId());
                        lstInsertOrUpdateRoleObjects.add(roleObjectsDTO);
                    }
                } else {
                    roleObjectsDTO = new RoleObjectsDTO();
                    roleObjectsDTO.setObjectId(o.getObjectId());
                    roleObjectsDTO.setRoleId(roles.getRoleId());
                    lstInsertOrUpdateRoleObjects.add(roleObjectsDTO);
                }
            }
            String resultInsert = WSRoleObjects.insertOrUpdateListRoleObjects(lstInsertOrUpdateRoleObjects);

            //Lay danh sach xoa
            List<RoleObjectsDTO> lstDeletes = new ArrayList<>();
            List<String> lstId2Save = new ArrayList<>();
            try {
                lstId2Save = DataUtil.getListValueFromList(lstObjects2Save, "objectId");
            } catch (NoSuchMethodException | IllegalAccessException ex) {
                Logger.getLogger(TreeObjectsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!DataUtil.isListNullOrEmpty(lstValuesFromRoles)) {
                for (ObjectsDTO o : lstValuesFromRoles) {
                    if (DataUtil.isListNullOrEmpty(lstDeletes)
                            && !lstId2Save.contains(o.getObjectId())) {
                        roleObjectsDTO = new RoleObjectsDTO();
                        roleObjectsDTO.setId(o.getRoleObjectId());
                        roleObjectsDTO.setObjectId(o.getObjectId());
                        roleObjectsDTO.setRoleId(roles.getRoleId());
                        lstDeletes.add(roleObjectsDTO);
                    }
                }
            }
            String resultDel = Constants.SUCCESS;
            if (!DataUtil.isListNullOrEmpty(lstDeletes)) {
                resultDel = WSRoleObjects.deleteLstRoleObjects(lstDeletes);
            }
            if (Constants.SUCCESS.equalsIgnoreCase(resultInsert)
                    && Constants.SUCCESS.equalsIgnoreCase(resultDel)) {
                CommonMessages.showMessageUpdateSuccess("roles.object");
            } else {
                CommonMessages.showUpdateFail(BundleUtils.getString("roles.object"));
            }
        }
    }
}
