/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.list.ui.PopupAddRoles;
import com.anphat.list.ui.RolesSearchPanel;
import com.cms.common.ws.WSAppParams;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.cms.common.controller.ConmonController;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.RolesDTO;
import com.cms.service.RolesServiceImpl;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.TableUtils;
import com.google.common.collect.Lists;
import com.vaadin.data.Property;
import com.vfw5.base.pojo.ConditionBean;
import com.vwf5.base.dto.ResultDTO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author QuyenDM
 */
public class RolesController extends ConmonController<RolesDTO> {

    private RolesSearchPanel rolesView;
    private CommonTableFilterPanel panelRoles;
    private CustomPageTableFilter<IndexedContainer> tblRoles;
    private BeanItemContainer beanItemContainerRoles;
    private List<RolesDTO> lstRolesDTO = Lists.newArrayList();
    private RolesServiceImpl serviceRoles = new RolesServiceImpl();
    private String lblDelete = "delete";
    private String lblEdit = "edit";
    private LinkedHashMap<String, CustomTable.Align> headerData = BundleUtils.getHeadersFilter("roles.header");
    private List<AppParamsDTO> lstAppParamsDTO;
    private List<AppParamsDTO> lstStatus;
    private Map<String, String> mapStatus;
    private String[] headerRoles = new String[]{Constants.STT, "code", "name", "description", "status", "delete", "edit"};
    private TableUtils tableUtils = new TableUtils();
    private PopupAddRoles popupAddRoles;
    private ComboComponent combo;

    public RolesController(RolesSearchPanel rolesView) {
        super(RolesDTO.class);
        this.rolesView = rolesView;
        panelRoles = rolesView.getTblRoles();
        tblRoles = rolesView.getTblRoles().getMainTable();
    }

    public void init() {
        initButton();
        getDataWS();
        initComboBox();
        initTable();
    }

    public void initOnlyTbl() {
        initTable();
    }

    public void initDialogMapStaffRole(LinkedHashMap<String, CustomTable.Align> HEADER) {
        initButton();
        getDataWS();
        initComboBox();
        tableUtils.generateColumn(tblRoles);
        initTable(HEADER);
    }

    public void initComboBox() {
//        BeanItemContainer containerStatus = new BeanItemContainer<>(AppParamsDTO.class);
//        containerStatus.addAll(lstStatus);
        combo = new ComboComponent();
        combo.fillDataCombo(rolesView.getCbxStatus(), Constants.NULL, Constants.ACTIVE, lstStatus, Constants.APP_PARAMS.COMMON_STATUS);
//        CommonUtils.initCombobox(rolesView.getCbxStatus(), containerStatus, Constants.APP_PARAMS.PAR_NAME);
    }

    public void getDataWS() {
// ds appparam
        AppParamsDTO appParamsDTO = new AppParamsDTO();
        appParamsDTO.setStatus(Constants.ACTIVE);
        lstAppParamsDTO = WSAppParams.getListAppParamsDTO(appParamsDTO, 0, Integer.MAX_VALUE, "", "parOrder");
        if (lstAppParamsDTO == null) {
            lstAppParamsDTO = Lists.newArrayList();
        }
        lstStatus = DataUtil.getListApParams(lstAppParamsDTO, "COMMON_STATUS");
        try {
            mapStatus = DataUtil.buildHasmap(lstStatus, Constants.APP_PARAMS.PAR_CODE, Constants.APP_PARAMS.PAR_NAME);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException ex) {
            Logger.getLogger(RolesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initButton() {
        rolesView.getBtnSearch().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                doSearch();
                event.getButton().setEnabled(true);
            }
        });
        rolesView.getBtnRefresh().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                rolesView.getTxtCode().setValue("");
                rolesView.getTxtName().setValue("");
                rolesView.getTxtDescription().setValue("");
                combo.fillDataCombo(rolesView.getCbxStatus(), Constants.NULL, Constants.ACTIVE, lstStatus, Constants.APP_PARAMS.COMMON_STATUS);
                event.getButton().setEnabled(true);
            }
        });
        panelRoles.getAddButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                InsertOrUpdate(new RolesDTO());
                event.getButton().setEnabled(true);
            }
        });
        panelRoles.getEditButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<RolesDTO> selected = (Set) tblRoles.getValue();
                List<RolesDTO> lstSelecteds = Lists.newArrayList(selected);
                if (!DataUtil.isListNullOrEmpty(lstSelecteds)) {
                    InsertOrUpdate(lstSelecteds.get(0));
                } else {
                    CommonUtils.showChoseOne();
                }
                event.getButton().setEnabled(true);
            }
        });
    }

    public void InsertOrUpdate(final RolesDTO rolesDTO) {
        popupAddRoles = new PopupAddRoles();

        BeanItemContainer containerStatus = new BeanItemContainer<>(AppParamsDTO.class);
        containerStatus.addAll(lstStatus);
        CommonUtils.initCombobox(popupAddRoles.getCbxStatus(), containerStatus, Constants.APP_PARAMS.PAR_NAME);
        if (rolesDTO.getCode() != null) {
            popupAddRoles.getTxtCode().setValue(rolesDTO.getCode());
        } else {
            popupAddRoles.getTxtCode().setValue("");
        }
        if (rolesDTO.getName() != null) {
            popupAddRoles.getTxtName().setValue(rolesDTO.getName());
        } else {
            popupAddRoles.getTxtName().setValue("");
        }
        if (rolesDTO.getDescription() != null) {
            popupAddRoles.getTxtDescription().setValue(rolesDTO.getDescription());
        } else {
            popupAddRoles.getTxtDescription().setValue("");
        }
        if (rolesDTO.getStatus() != null) {
            AppParamsDTO statusDefault = null;
            for (AppParamsDTO status : lstStatus) {
                if (status.getParCode().equals(rolesDTO.getStatus())) {
                    statusDefault = status;
                }
            }
            popupAddRoles.getCbxStatus().setValue(statusDefault);
        } else {
            popupAddRoles.getCbxStatus().setValue(null);
        }

        popupAddRoles.getBtnSave().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (popupAddRoles.getTxtCode().getValue() != null || popupAddRoles.getTxtCode().getValue().equals("")) {
                    rolesDTO.setCode(popupAddRoles.getTxtCode().getValue());
                } else {
                    Notification.show(BundleUtils.getString("roles.code.isnotnull"));
                    return;
                }
                if (popupAddRoles.getTxtName().getValue() != null || popupAddRoles.getTxtName().getValue().equals("")) {
                    rolesDTO.setName(popupAddRoles.getTxtName().getValue());
                } else {
                    Notification.show(BundleUtils.getString("roles.code.isnotnull"));
                    return;
                }
                AppParamsDTO status = (AppParamsDTO) popupAddRoles.getCbxStatus().getValue();
                rolesDTO.setStatus(status.getParCode());
                if (rolesDTO.getRoleId() == null) {
                    try {
                        ResultDTO resultDTO = serviceRoles.insertRoles(rolesDTO);
                        if (resultDTO.getMessage().equals(Constants.SUCCESS)) {
                            Notification.show(BundleUtils.getString("roles.insert.success"));
                        } else {
                            Notification.show(BundleUtils.getString("roles.insert.fail"));
                        }

                    } catch (Exception e) {
                        Notification.show(BundleUtils.getString("roles.insert.fail"));
                    }

                } else {
                    try {
                        String message = serviceRoles.updateRoles(rolesDTO);
                        if (message.equals(Constants.SUCCESS)) {
                            Notification.show(BundleUtils.getString("roles.update.success"));
                        } else {
                            Notification.show(BundleUtils.getString("roles.update.fail"));
                        }
                    } catch (Exception e) {
                        Notification.show(BundleUtils.getString("roles.update.fail"));
                    }
                }
            }
        });
        popupAddRoles.getBtnClose().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                popupAddRoles.close();
            }
        });

        UI.getCurrent().addWindow(popupAddRoles);
    }

    public void initTable() {

        tblRoles.setSortDisabled(true);
        tblRoles.addGeneratedColumn(lblDelete, new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
                final RolesDTO rolesDTO = (RolesDTO) itemId;
                ThemeResource iconVi = new ThemeResource("img/icon_delete.png");
                Image image = new Image(null, iconVi);
                image.addClickListener(new MouseEvents.ClickListener() {
                    @Override
                    public void click(MouseEvents.ClickEvent event) {
                        ConfirmDialog.show(UI.getCurrent(), BundleUtils.getString("d"), BundleUtils.getString("bodyMessage"),
                                BundleUtils.getString("yes"), BundleUtils.getString("no"), new ConfirmDialog.Listener() {
                            @Override
                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    rolesDTO.setStatus(Constants.DEACTIVE);
                                    String message = serviceRoles.updateRoles(rolesDTO);
                                    if (message.equals(Constants.SUCCESS)) {
                                        Notification.show(BundleUtils.getString("delete.success"));
                                        tblRoles.removeItem(itemId);
                                    } else {
                                        Notification.show(BundleUtils.getString("delete.fail"));
                                    }
                                } else {
                                    // User did not confirm
                                }
                            }
                        });
                    }
                });

                return image;
            }

        });
        tblRoles.addGeneratedColumn(lblEdit, new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
                final RolesDTO rolesDTO = (RolesDTO) itemId;
                ThemeResource iconVi = new ThemeResource("img/icon_edit.png");
                Image image = new Image(null, iconVi);
                image.addClickListener(new MouseEvents.ClickListener() {
                    @Override
                    public void click(MouseEvents.ClickEvent event) {
                        InsertOrUpdate(rolesDTO);
                    }
                });

                return image;
            }

        });
        tableUtils.generateColumn(tblRoles);
        CommonUtils.setVisibleBtnTablePanel(panelRoles, true, true, false, true);
        initTable(headerData);
    }

    public void initTable(LinkedHashMap<String, CustomTable.Align> HEADER) {
        headerData = HEADER;
        beanItemContainerRoles = new BeanItemContainer<>(RolesDTO.class);
        CommonFunctionTableFilter.initTable(panelRoles, HEADER, beanItemContainerRoles, BundleUtils.getString("table.list.roles"), 10, "roles.header");
        CommonUtils.convertFieldAppParamTable(tblRoles, "status", Constants.APP_PARAMS.COMMON_STATUS, mapStatus);
    }

    @Override
    public void onDoSearch() {
        List<ConditionBean> lstConditionBeanSearch = getLstConditionBeanSearch();
        try {
            lstRolesDTO = serviceRoles.getListRolesByCondition(lstConditionBeanSearch, 0, Integer.MAX_VALUE, "", "name");
        } catch (Exception e) {
            lstRolesDTO = Lists.newArrayList();
        }
        if (DataUtil.isListNullOrEmpty(lstRolesDTO)) {
            CommonMessages.showHumanizedMessage(BundleUtils.getString("notification.notData"));
        }
        setData2Table(lstRolesDTO);
//        panelRoles.getMainTable().setVisibleColumns(headerRoles);
        rolesView.getBtnSearch().setEnabled(true);
    }

    public List<ConditionBean> getLstConditionBeanSearch() {
        List<ConditionBean> lstConditionBean = Lists.newArrayList();
        if (!DataUtil.isStringNullOrEmpty(rolesView.getTxtCode().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("code");
            conditionBean.setValue(rolesView.getTxtCode().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(rolesView.getTxtName().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("name");
            conditionBean.setValue(rolesView.getTxtName().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(rolesView.getTxtDescription().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("description");
            conditionBean.setValue(rolesView.getTxtDescription().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (rolesView.getCbxStatus().getValue() != null) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("status");
            conditionBean.setValue(((AppParamsDTO) rolesView.getCbxStatus().getValue()).getParCode());
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        return lstConditionBean;

    }

    public void addTblRolesValueChangeListener(Property.ValueChangeListener e) {
        tblRoles.addValueChangeListener(e);
    }

    public void setTblRolesToolBarVisiable(boolean isVisiable) {
        panelRoles.getToolbar().setVisible(isVisiable);
    }

    //Truyen du lieu vao bang vai tro
    public void setData2Table(List<RolesDTO> lstRolesDTO) {
        beanItemContainerRoles.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstRolesDTO)) {

            beanItemContainerRoles.addAll(lstRolesDTO);
        }
        CommonFunctionTableFilter.refreshTable(panelRoles, headerData, beanItemContainerRoles);
    }

    //Lay du lieu tu bang vai tro
    public List<RolesDTO> getLstRolesFromTable() {
        Set<RolesDTO> roles = (Set<RolesDTO>) tblRoles.getValue();
        return Lists.newArrayList(roles);
    }
}
