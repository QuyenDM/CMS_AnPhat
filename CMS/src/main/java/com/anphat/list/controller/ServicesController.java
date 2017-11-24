/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.list.ui.PopupAddServices;
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
import com.cms.dto.ServicesDTO;
import com.cms.service.AppParamsServiceImpl;
import com.cms.service.ServicesServiceImpl;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.DateUtil;
import com.cms.utils.TableUtils;
import com.cms.view.ServicesView;
import com.google.common.collect.Lists;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.text.ParseException;
import java.util.Date;
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
public class ServicesController extends ConmonController<ServicesDTO> {

    private ServicesView servicesView;
    private CommonTableFilterPanel panelServices;
    private CustomPageTableFilter<IndexedContainer> tblServices;
    private BeanItemContainer beanItemContainerServices;
    private List<ServicesDTO> lstServicesDTO = Lists.newArrayList();
    private ServicesServiceImpl serviceServices = new ServicesServiceImpl();
    private AppParamsServiceImpl serviceAppParams = new AppParamsServiceImpl();
    private String lblDelete = "delete";
    private String lblEdit = "edit";
    private LinkedHashMap<String, CustomTable.Align> headerData = BundleUtils.getHeadersFilter("services.header");
    private List<AppParamsDTO> lstAppParamsDTO;
    private List<AppParamsDTO> lstStatus;
    private Map<String, AppParamsDTO> mapStatus;
    private List<AppParamsDTO> lstType;
    private Map<String, AppParamsDTO> mapType;
    private String[] headerServices = new String[]{Constants.STT, "code", "description", "expiryDate", "issueDate", "name", "serviceGroup", "status", "type", "delete", "edit"};
    private TableUtils tableUtils = new TableUtils();
    private PopupAddServices popupAddServices;

    public ServicesController(ServicesView servicesView) {
        super(ServicesDTO.class);
        this.servicesView = servicesView;
        panelServices = servicesView.getTblServices();
        tblServices = servicesView.getTblServices().getMainTable();
        init();
    }

    public void init() {
        initButton();
        getDataWS();
        initComboBox();
        initTable();
    }

    public void initComboBox() {
        BeanItemContainer containerStatus = new BeanItemContainer<>(AppParamsDTO.class);
        containerStatus.addAll(lstStatus);
        CommonUtils.initCombobox(servicesView.getCbxStatus(), containerStatus, Constants.APP_PARAMS.PAR_NAME);
        BeanItemContainer containerType = new BeanItemContainer<>(AppParamsDTO.class);
        containerType.addAll(lstType);
        CommonUtils.initCombobox(servicesView.getCbxType(), containerType, Constants.APP_PARAMS.PAR_NAME);
    }

    public void getDataWS() {
// ds appparam
        AppParamsDTO appParamsDTO = new AppParamsDTO();
        appParamsDTO.setStatus(Constants.ACTIVE);
        lstAppParamsDTO = WSAppParams.getListAppParamsDTO(appParamsDTO, 0, Integer.MAX_VALUE, "", "parOrder");
        if (lstAppParamsDTO == null) {
            lstAppParamsDTO = Lists.newArrayList();
        }
        lstStatus = DataUtil.getListApParams(lstAppParamsDTO, "COMMONS_STATUS");
        mapStatus = CommonUtils.putAppParams2MapByCode(lstStatus);
        lstType = DataUtil.getListApParams(lstAppParamsDTO, "SERVICES_TYPE");
        mapType = CommonUtils.putAppParams2MapByCode(lstType);
    }

    public void initButton() {
        servicesView.getBtnSearch().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                doSearch();
                event.getButton().setEnabled(true);
            }
        });
        servicesView.getBtnRefresh().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                servicesView.getTxtCode().setValue("");
                servicesView.getTxtDescription().setValue("");
                servicesView.getPopExpiryDate().setValue(null);
                servicesView.getPopIssueDate().setValue(null);
                servicesView.getTxtName().setValue("");
                servicesView.getTxtServiceGroup().setValue("");
                servicesView.getCbxStatus().setValue(null);
                servicesView.getCbxType().setValue(null);
                event.getButton().setEnabled(true);
            }
        });
        panelServices.getAddButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                InsertOrUpdate(new ServicesDTO());
                event.getButton().setEnabled(true);
            }
        });
        panelServices.getEditButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<ServicesDTO> selected = (Set) tblServices.getValue();
                List<ServicesDTO> lstSelecteds = Lists.newArrayList(selected);
                if (!DataUtil.isListNullOrEmpty(lstSelecteds)) {
                    InsertOrUpdate(lstSelecteds.get(0));
                } else {
                    CommonUtils.showChoseOne();
                }
                event.getButton().setEnabled(true);
            }
        });
    }

    public void InsertOrUpdate(final ServicesDTO servicesDTO) {
        popupAddServices = new PopupAddServices();

        BeanItemContainer containerStatus = new BeanItemContainer<>(AppParamsDTO.class);
        containerStatus.addAll(lstStatus);
        CommonUtils.initCombobox(popupAddServices.getCbxStatus(), containerStatus, Constants.APP_PARAMS.PAR_NAME);
        BeanItemContainer containerType = new BeanItemContainer<>(AppParamsDTO.class);
        containerType.addAll(lstType);
        CommonUtils.initCombobox(popupAddServices.getCbxType(), containerType, Constants.APP_PARAMS.PAR_NAME);
        if (servicesDTO.getCode() != null) {
            popupAddServices.getTxtCode().setValue(servicesDTO.getCode());
        } else {
            popupAddServices.getTxtCode().setValue("");
        }
        if (servicesDTO.getDescription() != null) {
            popupAddServices.getTxtDescription().setValue(servicesDTO.getDescription());
        } else {
            popupAddServices.getTxtDescription().setValue("");
        }
        if (servicesDTO.getExpiryDate() != null) {
            try {
                popupAddServices.getPopExpiryDate().setValue(DateUtil.convertStringToTime(servicesDTO.getExpiryDate(), "dd/MM/yyyy HH:mm:ss"));
            } catch (ParseException ex) {
                Logger.getLogger(ServicesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            popupAddServices.getPopExpiryDate().setValue(null);
        }
        if (servicesDTO.getIssueDate() != null) {
            try {
                popupAddServices.getPopIssueDate().setValue(DateUtil.convertStringToTime(servicesDTO.getIssueDate(), "dd/MM/yyyy HH:mm:ss"));
            } catch (ParseException ex) {
                Logger.getLogger(ServicesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            popupAddServices.getPopIssueDate().setValue(null);
        }
        if (servicesDTO.getName() != null) {
            popupAddServices.getTxtName().setValue(servicesDTO.getName());
        } else {
            popupAddServices.getTxtName().setValue("");
        }
        if (servicesDTO.getServiceGroup() != null) {
            popupAddServices.getTxtServiceGroup().setValue(servicesDTO.getServiceGroup());
        } else {
            popupAddServices.getTxtServiceGroup().setValue("");
        }
        if (servicesDTO.getStatus() != null) {
            AppParamsDTO statusDefault = null;
            for (AppParamsDTO status : lstStatus) {
                if (status.getParCode().equals(servicesDTO.getStatus())) {
                    statusDefault = status;
                }
            }
            popupAddServices.getCbxStatus().setValue(statusDefault);
        } else {
            popupAddServices.getCbxStatus().setValue(null);
        }
        if (servicesDTO.getType() != null) {
            AppParamsDTO typeDefault = null;
            for (AppParamsDTO type : lstType) {
                if (type.getParCode().equals(servicesDTO.getType())) {
                    typeDefault = type;
                }
            }
            popupAddServices.getCbxType().setValue(typeDefault);
        } else {
            popupAddServices.getCbxType().setValue(null);
        }

        popupAddServices.getBtnSave().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (popupAddServices.getTxtCode().getValue() != null || popupAddServices.getTxtCode().getValue().equals("")) {
                    servicesDTO.setCode(popupAddServices.getTxtCode().getValue());
                } else {
                    Notification.show(BundleUtils.getString("services.code.isnotnull"));
                    return;
                }
                Date expiryDate = popupAddServices.getPopExpiryDate().getValue();
                if (expiryDate != null) {
                    servicesDTO.setExpiryDate(DateUtil.date2ddMMyyyyString(expiryDate));
                } else {
                    servicesDTO.setExpiryDate(null);
                }
                Date issueDate = popupAddServices.getPopIssueDate().getValue();
                if (issueDate != null) {
                    servicesDTO.setIssueDate(DateUtil.date2ddMMyyyyString(issueDate));
                } else {
                    Notification.show(BundleUtils.getString("Services.issueDate.require"));
                }
                if (popupAddServices.getTxtName().getValue() != null || popupAddServices.getTxtName().getValue().equals("")) {
                    servicesDTO.setName(popupAddServices.getTxtName().getValue());
                } else {
                    Notification.show(BundleUtils.getString("services.code.isnotnull"));
                    return;
                }
                servicesDTO.setServiceGroup(popupAddServices.getTxtServiceGroup().getValue());
                AppParamsDTO status = (AppParamsDTO) popupAddServices.getCbxStatus().getValue();
                if (status != null) {
                    servicesDTO.setStatus(status.getParCode());
                } else {
                    Notification.show(BundleUtils.getString("Services.status.require"));
                }
                AppParamsDTO type = (AppParamsDTO) popupAddServices.getCbxType().getValue();
                servicesDTO.setType(type.getParCode());
                if (servicesDTO.getServiceId() == null) {
                    try {
                        ResultDTO resultDTO = serviceServices.insertServices(servicesDTO);
                        if (resultDTO.getMessage().equals(Constants.SUCCESS)) {
                            Notification.show(BundleUtils.getString("services.insert.success"));
                        } else {
                            Notification.show(BundleUtils.getString("services.insert.fail"));
                        }

                    } catch (Exception e) {
                        Notification.show(BundleUtils.getString("services.insert.fail"));
                    }

                } else {
                    try {
                        String message = serviceServices.updateServices(servicesDTO);
                        if (message.equals(Constants.SUCCESS)) {
                            Notification.show(BundleUtils.getString("services.update.success"));
                        } else {
                            Notification.show(BundleUtils.getString("services.update.fail"));
                        }
                    } catch (Exception e) {
                        Notification.show(BundleUtils.getString("services.update.fail"));
                    }
                }
            }
        });
        popupAddServices.getBtnClose().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                popupAddServices.close();
            }
        });

        UI.getCurrent().addWindow(popupAddServices);
    }

    public void initTable() {
        beanItemContainerServices = new BeanItemContainer<>(ServicesDTO.class);

        CommonFunctionTableFilter.initTable(panelServices, headerData, beanItemContainerServices, BundleUtils.getString("table.list.services"), 10, "services.header", true, true, false, false, false);
        tblServices.setSortDisabled(true);
        tblServices.addGeneratedColumn(lblDelete, new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
                final ServicesDTO servicesDTO = (ServicesDTO) itemId;
                ThemeResource iconVi = new ThemeResource("img/icon_delete.png");
                Image image = new Image(null, iconVi);
                image.addClickListener(new MouseEvents.ClickListener() {
                    @Override
                    public void click(MouseEvents.ClickEvent event) {
                        ConfirmDialog.show(UI.getCurrent(), "Th�ng b�o", "B?n c� mu?n th?c hi?n?",
                                "C�", "Kh�ng", new ConfirmDialog.Listener() {
                            @Override
                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    servicesDTO.setStatus(Constants.DEACTIVE);
                                    String message = serviceServices.updateServices(servicesDTO);
                                    if (message.equals(Constants.SUCCESS)) {
                                        Notification.show(BundleUtils.getString("delete.success"));
                                        tblServices.removeItem(itemId);
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
        tblServices.addGeneratedColumn(lblEdit, new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
                final ServicesDTO servicesDTO = (ServicesDTO) itemId;
                ThemeResource iconVi = new ThemeResource("img/icon_edit.png");
                Image image = new Image(null, iconVi);
                image.addClickListener(new MouseEvents.ClickListener() {
                    @Override
                    public void click(MouseEvents.ClickEvent event) {
                        InsertOrUpdate(servicesDTO);
                    }
                });

                return image;
            }

        });
        tableUtils.generateColumn(tblServices);
        CommonUtils.setVisibleBtnTablePanel(panelServices, true, true, false, true);
        tblServices.setVisibleColumns(headerServices);

    }

    @Override
    public void onDoSearch() {
        List<ConditionBean> lstConditionBeanSearch = getLstConditionBeanSearch();
        try {
            lstServicesDTO = serviceServices.getListServicesByCondition(lstConditionBeanSearch, 0, Integer.MAX_VALUE, "", "code");
        } catch (Exception e) {
            lstServicesDTO = Lists.newArrayList();
        }
        if (DataUtil.isListNullOrEmpty(lstServicesDTO)) {
            CommonMessages.showHumanizedMessage("Kh�ng t�m th?y d? li?u.");
        }
        beanItemContainerServices.removeAllItems();
        beanItemContainerServices.addAll(lstServicesDTO);
        CommonFunctionTableFilter.refreshTable(panelServices, headerData, beanItemContainerServices);
        panelServices.getMainTable().setVisibleColumns(headerServices);
        servicesView.getBtnSearch().setEnabled(true);
    }

    public List<ConditionBean> getLstConditionBeanSearch() {
        List<ConditionBean> lstConditionBean = Lists.newArrayList();
        if (!DataUtil.isStringNullOrEmpty(servicesView.getTxtCode().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("code");
            conditionBean.setValue(servicesView.getTxtCode().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(servicesView.getTxtDescription().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("description");
            conditionBean.setValue(servicesView.getTxtDescription().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (servicesView.getPopExpiryDate().getValue() != null) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("expiryDate");
            conditionBean.setValue(DateUtil.date2ddMMyyyyString(servicesView.getPopExpiryDate().getValue()));
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_DATE);
            lstConditionBean.add(conditionBean);
        }
        if (servicesView.getPopIssueDate().getValue() != null) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("issueDate");
            conditionBean.setValue(DateUtil.date2ddMMyyyyString(servicesView.getPopIssueDate().getValue()));
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_DATE);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(servicesView.getTxtName().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("name");
            conditionBean.setValue(servicesView.getTxtName().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(servicesView.getTxtServiceGroup().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("serviceGroup");
            conditionBean.setValue(servicesView.getTxtServiceGroup().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (servicesView.getCbxStatus().getValue() != null) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("status");
            conditionBean.setValue(((AppParamsDTO) servicesView.getCbxStatus().getValue()).getParCode());
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_NUMBER);
            lstConditionBean.add(conditionBean);
        }
        if (servicesView.getCbxType().getValue() != null) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("type");
            conditionBean.setValue(((AppParamsDTO) servicesView.getCbxType().getValue()).getParCode());
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_NUMBER);
            lstConditionBean.add(conditionBean);
        }
        ConditionBean conditionBean = new ConditionBean();
        conditionBean.setField("status");
        conditionBean.setValue(Constants.ACTIVE);
        conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
        conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
        lstConditionBean.add(conditionBean);
        return lstConditionBean;

    }
}
