/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.list.ui.PopupAddObjects;
import com.cms.common.ws.WSAppParams;
import com.cms.login.dto.ObjectsDTO;
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
import com.cms.service.ObjectsServiceImpl;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.view.ObjectsView;
import com.google.common.collect.Lists;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author QuyenDM
 */
public class ObjectsController extends ConmonController<ObjectsDTO> {

    private ObjectsView objectsView;
    private CommonTableFilterPanel panelObjects;
    private CustomPageTableFilter<IndexedContainer> tblObjects;
    private BeanItemContainer beanItemContainerObjects;
    private List<ObjectsDTO> lstObjectsDTO = Lists.newArrayList();
    private ObjectsServiceImpl serviceObjects = new ObjectsServiceImpl();
    private String lblDelete = "delete";
    private String lblEdit = "edit";
    private LinkedHashMap<String, CustomTable.Align> headerData = BundleUtils.getHeadersFilter("objects.header");
    private List<AppParamsDTO> lstAppParamsDTO;
    private List<AppParamsDTO> lstStatus;
    private Map<String, AppParamsDTO> mapStatus;
    private Map<String, String> mapCode2Name;
    private PopupAddObjects popupAddObjects;

    public ObjectsController(ObjectsView objectsView) {
        super(ObjectsDTO.class);
        this.objectsView = objectsView;
        panelObjects = objectsView.getTblObjects();
        tblObjects = objectsView.getTblObjects().getMainTable();
        init();
    }

    public void init() {
        initButton();
        getDataWS();
        initComboBox();
        initTable();
    }

    public void initComboBox() {
        ComboComponent combo = new ComboComponent();
        combo.fillDataCombo(objectsView.getCbxStatus(), Constants.ALL, Constants.ACTIVE);
//        BeanItemContainer containerStatus = new BeanItemContainer<>(AppParamsDTO.class);
//        containerStatus.addAll(lstStatus);
//        CommonUtils.initCombobox(objectsView.getCbxStatus(), containerStatus, Constants.APP_PARAMS.PAR_NAME);
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

        mapStatus = CommonUtils.putAppParams2MapByCode(lstStatus);
        try {
            mapCode2Name = DataUtil.buildHasmap(lstStatus, "parCode", "parName");
        } catch (Exception e) {
            mapCode2Name = null;
        }
    }

    public void initButton() {
        objectsView.getBtnSearch().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                doSearch();
                event.getButton().setEnabled(true);
            }
        });
        objectsView.getBtnRefresh().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                objectsView.getTxtCode().setValue("");
                objectsView.getTxtName().setValue("");
                objectsView.getTxtUrl().setValue("");
                objectsView.getTxtDescription().setValue("");
                objectsView.getTxtObjectType().setValue("");
                objectsView.getCbxStatus().setValue(null);
                event.getButton().setEnabled(true);
            }
        });
        panelObjects.getAddButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                InsertOrUpdate(new ObjectsDTO());
                event.getButton().setEnabled(true);
            }
        });
        panelObjects.getEditButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<ObjectsDTO> selected = (Set) tblObjects.getValue();
                List<ObjectsDTO> lstSelecteds = Lists.newArrayList(selected);
                if (!DataUtil.isListNullOrEmpty(lstSelecteds)) {
                    InsertOrUpdate(lstSelecteds.get(0));
                } else {
                    CommonUtils.showChoseOne();
                }
                event.getButton().setEnabled(true);
            }
        });
    }

    public void InsertOrUpdate(final ObjectsDTO objectsDTO) {
        popupAddObjects = new PopupAddObjects();
        ComboComponent combo = new ComboComponent();
        combo.fillDataCombo(popupAddObjects.getCbxStatus(), Constants.NULL, Constants.ACTIVE);
//        BeanItemContainer containerStatus = new BeanItemContainer<>(AppParamsDTO.class);
//        containerStatus.addAll(lstStatus);
//        CommonUtils.initCombobox(popupAddObjects.getCbxStatus(), containerStatus, Constants.APP_PARAMS.PAR_NAME);
        if (objectsDTO.getCode() != null) {
            popupAddObjects.getTxtCode().setValue(objectsDTO.getCode());
        } else {
            popupAddObjects.getTxtCode().setValue("");
        }
        if (objectsDTO.getName() != null) {
            popupAddObjects.getTxtName().setValue(objectsDTO.getName());
        } else {
            popupAddObjects.getTxtName().setValue("");
        }
        if (objectsDTO.getUrl() != null) {
            popupAddObjects.getTxtUrl().setValue(objectsDTO.getUrl());
        } else {
            popupAddObjects.getTxtUrl().setValue("");
        }
        if (objectsDTO.getDescription() != null) {
            popupAddObjects.getTxtDescription().setValue(objectsDTO.getDescription());
        } else {
            popupAddObjects.getTxtDescription().setValue("");
        }
        if (objectsDTO.getObjectType() != null) {
            popupAddObjects.getTxtObjectType().setValue(objectsDTO.getObjectType());
        } else {
            popupAddObjects.getTxtObjectType().setValue("");
        }
        if (objectsDTO.getStatus() != null) {
            AppParamsDTO statusDefault = null;
            for (AppParamsDTO status : lstStatus) {
                if (status.getParCode().equals(objectsDTO.getStatus())) {
                    statusDefault = status;
                }
            }
            popupAddObjects.getCbxStatus().setValue(statusDefault);
        } else {
            popupAddObjects.getCbxStatus().setValue(null);
        }

        popupAddObjects.getBtnSave().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {

                if (popupAddObjects.getTxtCode().getValue() != null || popupAddObjects.getTxtCode().getValue().equals("")) {
                    objectsDTO.setCode(popupAddObjects.getTxtCode().getValue());
                } else {
                    Notification.show(BundleUtils.getString("objects.code.isnotnull"));
                    event.getButton().setEnabled(true);
                    return;
                }
                if (popupAddObjects.getTxtName().getValue() != null || popupAddObjects.getTxtName().getValue().equals("")) {
                    objectsDTO.setName(popupAddObjects.getTxtName().getValue());
                } else {
                    Notification.show(BundleUtils.getString("objects.code.isnotnull"));
                    event.getButton().setEnabled(true);
                    return;
                }
                objectsDTO.setAppId("1");
                objectsDTO.setUrl(popupAddObjects.getTxtUrl().getValue());
                objectsDTO.setDescription(popupAddObjects.getTxtDescription().getValue());
                objectsDTO.setObjectType(popupAddObjects.getTxtObjectType().getValue());
                AppParamsDTO status = (AppParamsDTO) popupAddObjects.getCbxStatus().getValue();
                if (status != null) {
                    objectsDTO.setStatus(status.getParCode());
                } else {
                    Notification.show(BundleUtils.getString("Objects.status.require"));
                }
                if (objectsDTO.getObjectId() == null) {
                    try {
                        ResultDTO resultDTO = serviceObjects.insertObjects(objectsDTO);
                        if (resultDTO.getMessage().equals(Constants.SUCCESS)) {
                            CommonMessages.showMessageImportSuccess("function.object");
                        } else {
                            CommonMessages.showInsertFail("function.object");
                        }

                    } catch (Exception e) {
                        CommonMessages.showInsertFail("function.object");
                    }

                } else {
                    try {
                        String message = serviceObjects.updateObjects(objectsDTO);
                        if (message.equals(Constants.SUCCESS)) {
                            CommonMessages.showMessageUpdateSuccess("function.object");
                        } else {
                            CommonMessages.showUpdateFail("function.object");
                        }
                    } catch (Exception e) {
                        CommonMessages.showUpdateFail("function.object");
                    }
                }
                event.getButton().setEnabled(true);
            }
        });
        popupAddObjects.getBtnClose().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                popupAddObjects.close();
            }
        });

        UI.getCurrent().addWindow(popupAddObjects);
    }

    public void initTable() {
        beanItemContainerObjects = new BeanItemContainer<>(ObjectsDTO.class);
        tblObjects.setSortDisabled(true);
        tblObjects.addGeneratedColumn(lblDelete, new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
                final ObjectsDTO objectsDTO = (ObjectsDTO) itemId;
                ThemeResource iconVi = new ThemeResource("img/icon_delete.png");
                Image image = new Image(null, iconVi);
                image.addClickListener(new MouseEvents.ClickListener() {
                    @Override
                    public void click(MouseEvents.ClickEvent event) {
                        ConfirmDialog.show(UI.getCurrent(), "Thông báo", "Bạn có muốn thực hiện?",
                                "Có", "Không", new ConfirmDialog.Listener() {
                            @Override
                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    objectsDTO.setStatus(Constants.DEACTIVE);
                                    String message = serviceObjects.updateObjects(objectsDTO);
                                    if (message.equals(Constants.SUCCESS)) {
                                        CommonMessages.showMessageDeleteSuccess("function.object");
                                        tblObjects.removeItem(itemId);
                                    } else {
                                        CommonMessages.showDeleteFail("function.object");
                                    }
                                }
                            }
                        });
                    }
                });

                return image;
            }

        });
        tblObjects.addGeneratedColumn(lblEdit, new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
                final ObjectsDTO objectsDTO = (ObjectsDTO) itemId;
                ThemeResource iconVi = new ThemeResource("img/icon_edit.png");
                Image image = new Image(null, iconVi);
                image.addClickListener(new MouseEvents.ClickListener() {
                    @Override
                    public void click(MouseEvents.ClickEvent event) {
                        InsertOrUpdate(objectsDTO);
                    }
                });

                return image;
            }

        });
        CommonUtils.setVisibleBtnTablePanel(panelObjects, true, true, false, true);
        CommonFunctionTableFilter.initTable(panelObjects, headerData, beanItemContainerObjects, BundleUtils.getString("table.list.objects"), 10, "objects.header", true, true, false, false, false);
        CommonUtils.convertFieldAppParamTable(tblObjects, "status", Constants.APP_PARAMS.COMMON_STATUS, mapCode2Name);
    }

    @Override
    public void onDoSearch() {
        List<ConditionBean> lstConditionBeanSearch = getLstConditionBeanSearch();
        try {
            lstObjectsDTO = serviceObjects.getListObjectsByCondition(lstConditionBeanSearch, 0, Integer.MAX_VALUE, "", "code");
        } catch (Exception e) {
            lstObjectsDTO = Lists.newArrayList();
        }
        beanItemContainerObjects.removeAllItems();
        if (DataUtil.isListNullOrEmpty(lstObjectsDTO)) {
            CommonMessages.showDataNotFound();
        } else {
            beanItemContainerObjects.addAll(lstObjectsDTO);
            CommonFunctionTableFilter.refreshTable(panelObjects, headerData, beanItemContainerObjects);
        }
        objectsView.getBtnSearch().setEnabled(true);
    }

    public List<ConditionBean> getLstConditionBeanSearch() {
        List<ConditionBean> lstConditionBean = Lists.newArrayList();
        if (!DataUtil.isStringNullOrEmpty(objectsView.getTxtCode().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("code");
            conditionBean.setValue(objectsView.getTxtCode().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(objectsView.getTxtName().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("name");
            conditionBean.setValue(objectsView.getTxtName().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(objectsView.getTxtUrl().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("url");
            conditionBean.setValue(objectsView.getTxtUrl().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(objectsView.getTxtDescription().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("description");
            conditionBean.setValue(objectsView.getTxtDescription().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(objectsView.getTxtObjectType().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("objectType");
            conditionBean.setValue(objectsView.getTxtObjectType().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (objectsView.getCbxStatus().getValue() != null) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("status");
            conditionBean.setValue(((AppParamsDTO) objectsView.getCbxStatus().getValue()).getParCode());
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        return lstConditionBean;

    }
}
