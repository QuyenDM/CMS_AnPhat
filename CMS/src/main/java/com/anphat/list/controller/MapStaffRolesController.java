/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.list.ui.MapStaffRolesDiaglog;
import com.cms.dto.RolesDTO;
import com.cms.dto.StaffDTO;
import com.cms.login.dto.Constants;
import com.cms.login.dto.MapStaffRolesDTO;
import com.cms.login.ws.WSMapStaffRoles;
import com.cms.login.ws.WSRoles;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.DataUtil;
import com.google.common.collect.Lists;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quyen
 */
public class MapStaffRolesController {

    private final MapStaffRolesDiaglog mapStaffRolesDiaglog;
    private RolesController rolesController;
    private final static LinkedHashMap<String, CustomTable.Align> HEADER_ROLE_VIEW = BundleUtils.getHeadersFilter("roles.header.onlyView");
    private final static LinkedHashMap<String, CustomTable.Align> HEADER_ROLE_ADD = BundleUtils.getHeadersFilter("roles.header.add");
    private StaffDTO staff;
    private List<RolesDTO> lstRolesFromStaff;

    public MapStaffRolesController(MapStaffRolesDiaglog mapStaffRolesDiaglog) {
        this.mapStaffRolesDiaglog = mapStaffRolesDiaglog;
    }

    /**
     * Khoi tao khi nguoi dung mo cua so gan nhan vien - vai tro
     *
     * @param staffDTO
     */
    public void init(StaffDTO staffDTO) {
        staff = staffDTO;
        rolesController = new RolesController(mapStaffRolesDiaglog.getRolesSearchPanel());
        rolesController.setTblRolesToolBarVisiable(false);
        rolesController.initDialogMapStaffRole(HEADER_ROLE_ADD);

        if (!DataUtil.isNullObject(staffDTO)) {
            lstRolesFromStaff = WSRoles.getListRolesByStaffId(staff.getStaffId());
            rolesController.setData2Table(lstRolesFromStaff);
        }
        addListeners();
    }

    /**
     * Khi nguoi dung hien thi chi tiet vai tro
     *
     * @param staffDTO
     */
    public void initOnlyView(StaffDTO staffDTO) {
        rolesController = new RolesController(mapStaffRolesDiaglog.getRolesSearchPanel());
        rolesController.setTblRolesToolBarVisiable(false);
        rolesController.initTable(HEADER_ROLE_VIEW);
        mapStaffRolesDiaglog.setBtnSaveVisible(false);

        if (!DataUtil.isNullObject(staffDTO)) {
            lstRolesFromStaff = WSRoles.getListRolesByStaffId(staffDTO.getStaffId());
            rolesController.setData2Table(lstRolesFromStaff);
        }
    }

    private void addListeners() {
        mapStaffRolesDiaglog.addBtnSaveListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                List<RolesDTO> lstRoles = rolesController.getLstRolesFromTable();
                if (DataUtil.isListNullOrEmpty(lstRoles)) {
                    CommonUtils.showChoseOne();
                } else if (DataUtil.isListNullOrEmpty(lstRolesFromStaff)) {
                    List<MapStaffRolesDTO> lstMapStaffRolesDTOs = Lists.newArrayList();
                    MapStaffRolesDTO t;
                    for (RolesDTO r : lstRoles) {
                        t = new MapStaffRolesDTO();
                        t.setRoleId(r.getRoleId());
                        t.setStatus(Constants.ACTIVE);
                        t.setStaffId(staff.getStaffId());
                        lstMapStaffRolesDTOs.add(t);
                    }
                    //Luu thong tin nhan vien - vai tro
                    String result = WSMapStaffRoles.insertOrUpdateListMapStaffRoles(lstMapStaffRolesDTOs);
                    if (Constants.SUCCESS.equalsIgnoreCase(result)) {
                        CommonUtils.showSuccess();
                    } else {
                        CommonUtils.showFail();
                    }
                } else {
                    List<String> lstRolesIdsFromStaff;

                    try {
                        lstRolesIdsFromStaff = DataUtil.getListValueFromList(lstRoles, "roleId");
                    } catch (NoSuchMethodException | IllegalAccessException ex) {
                        Logger.getLogger(MapStaffRolesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
}
