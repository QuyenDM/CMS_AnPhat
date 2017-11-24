/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.controller;

import com.anphat.list.ui.PopupAddTaxAuthority;
import com.cms.common.controller.ConmonController;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.TaxAuthorityDTO;
import com.cms.login.ws.WSTaxAuthority;
import com.cms.service.AppParamsServiceImpl;
import com.cms.service.TaxAuthorityServiceImpl;
import com.cms.ui.CommonButtonClickListener;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.ShortcutUtils;
import com.cms.utils.TableUtils;
import com.cms.view.TaxAuthorityView;
import com.google.common.collect.Lists;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author
 */
public class TaxAuthorityController extends ConmonController<TaxAuthorityDTO> {

    private TaxAuthorityView taxAuthorityView;
    private CommonTableFilterPanel panelTaxAuthority;
    private CustomPageTableFilter<IndexedContainer> tblTaxAuthority;
    private BeanItemContainer beanItemContainerTaxAuthority;
    private List<TaxAuthorityDTO> lstTaxAuthorityDTO = Lists.newArrayList();
    private TaxAuthorityServiceImpl serviceTaxAuthority = new TaxAuthorityServiceImpl();
    private AppParamsServiceImpl serviceAppParams = new AppParamsServiceImpl();
    private String lblDelete = "delete";
    private String lblEdit = "edit";
    private LinkedHashMap<String, CustomTable.Align> headerData = BundleUtils.getHeadersFilter("taxAuthority.header");
    private TableUtils tableUtils = new TableUtils();
    private PopupAddTaxAuthority popupAddTaxAuthority;
    private ComboComponent comboComponent;
    private List<TaxAuthorityDTO> lstTaxAuthority;
    private Map<String, TaxAuthorityDTO> mapTaxAuthority;

    public TaxAuthorityController(TaxAuthorityView taxAuthorityView) {
        super(TaxAuthorityDTO.class);
        this.taxAuthorityView = taxAuthorityView;
        panelTaxAuthority = taxAuthorityView.getTblTaxAuthority();
        tblTaxAuthority = taxAuthorityView.getTblTaxAuthority().getMainTable();
        init();
    }

    public void init() {
        initButton();
        getDataWS();
        initComboBox();
        initTable();
    }

    public void initComboBox() {
        comboComponent = new ComboComponent();
        comboComponent.fillDataCombo(taxAuthorityView.getCboStatus(), Constants.ALL, Constants.ACTIVE);

        comboComponent.setValues(taxAuthorityView.getTxtMaTinh(), lstTaxAuthority, Constants.TAXAUTHORITY.TEN_CQT, true);
    }

    public void getDataWS() {
        try {
            lstTaxAuthority = WSTaxAuthority.getListProvineTaxAuthority();
            mapTaxAuthority = DataUtil.buildHasmap(lstTaxAuthority, Constants.TAXAUTHORITY.MA_TINH);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(TaxAuthorityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initButton() {
        ShortcutUtils.setShortcutKey(taxAuthorityView.getBtnSearch());
        taxAuthorityView.getBtnSearch().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                doSearch();
                event.getButton().setEnabled(true);
            }
        });
        taxAuthorityView.getBtnRefresh().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                taxAuthorityView.getTxtMaCqt().clear();
                taxAuthorityView.getTxtTenCqt().clear();
                taxAuthorityView.getTxtMaTinh().clear();
                taxAuthorityView.getTxtMaQuanHuyen().clear();
                comboComponent.fillDataCombo(taxAuthorityView.getCboStatus(), Constants.ALL, Constants.ACTIVE);

                event.getButton().setEnabled(true);
            }
        });

        panelTaxAuthority.getAddButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                InsertOrUpdate(new TaxAuthorityDTO(), false);
                event.getButton().setEnabled(true);
            }
        });
        panelTaxAuthority.getCoppyButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<TaxAuthorityDTO> selected = (Set) tblTaxAuthority.getValue();
                List<TaxAuthorityDTO> lstSelecteds = Lists.newArrayList(selected);
                if (!DataUtil.isListNullOrEmpty(lstSelecteds)) {
                    InsertOrUpdate(lstSelecteds.get(0), true);
                } else {
                    CommonUtils.showChoseOne();
                }
                event.getButton().setEnabled(true);
            }
        });

        panelTaxAuthority.getEditButton().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<TaxAuthorityDTO> selected = (Set) tblTaxAuthority.getValue();
                List<TaxAuthorityDTO> lstSelecteds = Lists.newArrayList(selected);
                if (!DataUtil.isListNullOrEmpty(lstSelecteds)) {
                    InsertOrUpdate(lstSelecteds.get(0), false);
                } else {
                    CommonUtils.showChoseOne();
                }
                event.getButton().setEnabled(true);
            }
        });
    }

    public void InsertOrUpdate(final TaxAuthorityDTO taxAuthorityDTO, final boolean isCopy) {
        popupAddTaxAuthority = new PopupAddTaxAuthority();

        if (taxAuthorityDTO.getMaCqt() == null || isCopy) {
            popupAddTaxAuthority.setCaption(BundleUtils.getString("popup.tax.authority.caption.add"));
            popupAddTaxAuthority.getTxtMaCqt().clear();
            popupAddTaxAuthority.getTxtMaCqt().focus();
        } else {
            popupAddTaxAuthority.setCaption(BundleUtils.getString("popup.tax.authority.caption.edit"));
            popupAddTaxAuthority.getTxtMaCqt().setValue(taxAuthorityDTO.getMaCqt());
        }
        if (taxAuthorityDTO.getTenCqt() != null) {
            popupAddTaxAuthority.getTxtTenCqt().setValue(taxAuthorityDTO.getTenCqt());
        } else {
            popupAddTaxAuthority.getTxtTenCqt().clear();
        }
        if (taxAuthorityDTO.getMaTinh() != null) {
            popupAddTaxAuthority.getCboMaTinh().setData(mapTaxAuthority.get(taxAuthorityDTO.getMaTinh()));
        } else {
            popupAddTaxAuthority.getCboMaTinh().clear();
        }
        if (taxAuthorityDTO.getMaQuanHuyen() != null) {
            popupAddTaxAuthority.getTxtMaQuanHuyen().setValue(taxAuthorityDTO.getMaQuanHuyen());
        } else {
            popupAddTaxAuthority.getTxtMaQuanHuyen().clear();
        }
        if (taxAuthorityDTO.getStatus() != null) {
            popupAddTaxAuthority.getCboStatus().setValue(taxAuthorityDTO.getStatus());
        } else {
            popupAddTaxAuthority.getCboStatus().clear();
        }

        popupAddTaxAuthority.getBtnSave().addClickListener(new CommonButtonClickListener() {
            String maCqt;
            String tenCqt;
            String maTinh;
            String maQuanHuyen;
            String status;
            TaxAuthorityDTO tinh;
            AppParamsDTO statusDTO;

            @Override
            public boolean isValidated() {
                maCqt = popupAddTaxAuthority.getTxtMaCqt().getValue();
                tenCqt = popupAddTaxAuthority.getTxtTenCqt().getValue();
                maQuanHuyen = popupAddTaxAuthority.getTxtMaQuanHuyen().getValue();
                tinh = (TaxAuthorityDTO) popupAddTaxAuthority.getCboMaTinh().getValue();
                statusDTO = (AppParamsDTO) popupAddTaxAuthority.getCboStatus().getValue();
                if (DataUtil.isStringNullOrEmpty(maCqt)) {
                    CommonMessages.messageRequire("taxAuthority.header.maCqt");
                    popupAddTaxAuthority.getTxtMaCqt().focus();
                    return false;
                }
                if (DataUtil.isStringNullOrEmpty(tenCqt)) {
                    CommonMessages.messageRequire("taxAuthority.header.tenCqt");
                    popupAddTaxAuthority.getTxtTenCqt().focus();
                    return false;
                }
                if (DataUtil.isStringNullOrEmpty(maQuanHuyen)) {
                    CommonMessages.messageRequire("taxAuthority.header.maQuanHuyen");
                    popupAddTaxAuthority.getTxtMaQuanHuyen().focus();
                    return false;
                }
                if (DataUtil.isNullObject(tinh)) {
                    CommonMessages.messageRequire("taxAuthority.header.maTinh");
                    popupAddTaxAuthority.getCboMaTinh().focus();
                    return false;
                }
                if (DataUtil.isNullObject(statusDTO)) {
                    CommonMessages.messageRequire("taxAuthority.header.status");
                    popupAddTaxAuthority.getCboStatus().focus();
                    return false;
                }

                maTinh = tinh.getMaTinh();
                status = statusDTO.getParCode();
                return true;
            }

            @Override
            public void execute() {
                taxAuthorityDTO.setMaCqt(maCqt);
                taxAuthorityDTO.setTenCqt(tenCqt);
                taxAuthorityDTO.setMaTinh(maTinh);
                taxAuthorityDTO.setMaQuanHuyen(maQuanHuyen);
                taxAuthorityDTO.setStatus(status);

                if (taxAuthorityDTO.getId() == null || isCopy) {
                    try {
                        ResultDTO resultDTO = serviceTaxAuthority.insertTaxAuthority(taxAuthorityDTO);
                        if (resultDTO.getMessage().equals(Constants.SUCCESS)) {
                            popupAddTaxAuthority.close();
                            CommonUtils.reloadTable(tblTaxAuthority, taxAuthorityDTO);
                            CommonMessages.showMessageInsertSuccess("taxAuthority");
                        } else {
                            CommonMessages.showInsertFail(BundleUtils.getString("taxAuthority"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        CommonMessages.showInsertFail(BundleUtils.getString("taxAuthority"));
                    }

                } else {
                    try {
                        String message = serviceTaxAuthority.updateTaxAuthority(taxAuthorityDTO);
                        if (message.equals(Constants.SUCCESS)) {
                            tblTaxAuthority.addItem(taxAuthorityDTO);
                            popupAddTaxAuthority.close();
                            CommonMessages.showMessageUpdateSuccess("taxAuthority");
                        } else {
                            CommonMessages.showUpdateFail(BundleUtils.getString("taxAuthority"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        CommonMessages.showUpdateFail(BundleUtils.getString("taxAuthority"));
                    }
                }
            }
        });
        popupAddTaxAuthority.getBtnClose().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                popupAddTaxAuthority.close();
            }
        });

        UI.getCurrent().addWindow(popupAddTaxAuthority);
    }

    public void initTable() {
        beanItemContainerTaxAuthority = new BeanItemContainer<>(TaxAuthorityDTO.class);

        tblTaxAuthority.setSortDisabled(true);
        tblTaxAuthority.addGeneratedColumn(lblDelete, new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
                final TaxAuthorityDTO taxAuthorityDTO = (TaxAuthorityDTO) itemId;
                ThemeResource iconVi = new ThemeResource("img/icon_delete.png");
                Image image = new Image(null, iconVi);
                image.addClickListener(new MouseEvents.ClickListener() {
                    @Override
                    public void click(MouseEvents.ClickEvent event) {
                        ConfirmDialog.show(UI.getCurrent(), BundleUtils.getString("titleMessage"), BundleUtils.getString("bodyMessage"),
                                BundleUtils.getString("yes"), BundleUtils.getString("no"), new ConfirmDialog.Listener() {
                            @Override
                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    taxAuthorityDTO.setStatus(Constants.DEACTIVE);
                                    String message = serviceTaxAuthority.updateTaxAuthority(taxAuthorityDTO);
                                    if (message.equals(Constants.SUCCESS)) {
                                        Notification.show(BundleUtils.getString("delete.success"));
                                        tblTaxAuthority.removeItem(itemId);
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
        tblTaxAuthority.addGeneratedColumn(lblEdit, new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
                final TaxAuthorityDTO taxAuthorityDTO = (TaxAuthorityDTO) itemId;
                ThemeResource iconVi = new ThemeResource("img/icon_edit.png");
                Image image = new Image(null, iconVi);
                image.addClickListener(new MouseEvents.ClickListener() {
                    @Override
                    public void click(MouseEvents.ClickEvent event) {
                        InsertOrUpdate(taxAuthorityDTO, false);
                    }
                });

                return image;
            }

        });
//        tableUtils.generateColumn(tblTaxAuthority);
        CommonFunctionTableFilter.initTable(panelTaxAuthority, headerData, beanItemContainerTaxAuthority, BundleUtils.getString("table.list.taxAuthority"), 10, "taxAuthority.header", true, true, false, false, false);
        CommonUtils.setVisibleBtnTablePanel(panelTaxAuthority, true, false, true, true);
        CommonUtils.convertFieldAppParamTable(tblTaxAuthority, Constants.TAXAUTHORITY.STATUS, Constants.APP_PARAMS.COMMON_STATUS);
    }

    @Override
    public void onDoSearch() {
        List<ConditionBean> lstConditionBeanSearch = getLstConditionBeanSearch();
        if (DataUtil.isListNullOrEmpty(lstConditionBeanSearch)) {
            lstTaxAuthorityDTO = serviceTaxAuthority.getListTaxAuthorityDTO(new TaxAuthorityDTO(), 0, Constants.INT_100, Constants.ASC, Constants.TAXAUTHORITY.MA_TINH);
        } else {
            try {
                lstTaxAuthorityDTO = serviceTaxAuthority.getListTaxAuthorityByCondition(lstConditionBeanSearch, 0, Integer.MAX_VALUE, "", "maTinh");
            } catch (Exception e) {
                lstTaxAuthorityDTO = Lists.newArrayList();
            }
        }
        beanItemContainerTaxAuthority.removeAllItems();
        if (DataUtil.isListNullOrEmpty(lstTaxAuthorityDTO)) {
            CommonMessages.showDataNotFound();
        } else {
            beanItemContainerTaxAuthority.addAll(lstTaxAuthorityDTO);
        }
        CommonFunctionTableFilter.refreshTable(panelTaxAuthority, headerData, beanItemContainerTaxAuthority);
        taxAuthorityView.getBtnSearch().setEnabled(true);
    }

    public List<ConditionBean> getLstConditionBeanSearch() {
        List<ConditionBean> lstConditionBean = Lists.newArrayList();
        if (!DataUtil.isStringNullOrEmpty(taxAuthorityView.getTxtMaCqt().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("maCqt");
            conditionBean.setValue(taxAuthorityView.getTxtMaCqt().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        if (!DataUtil.isStringNullOrEmpty(taxAuthorityView.getTxtTenCqt().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("tenCqt");
            conditionBean.setValue(taxAuthorityView.getTxtTenCqt().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        TaxAuthorityDTO taxAuth = (TaxAuthorityDTO) taxAuthorityView.getTxtMaTinh().getValue();
        if (!DataUtil.isNullObject(taxAuth)
                && taxAuth.getMaTinh() != null) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("maTinh");
            conditionBean.setValue(taxAuth.getMaTinh());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }

        if (!DataUtil.isStringNullOrEmpty(taxAuthorityView.getTxtMaQuanHuyen().getValue())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("maQuanHuyen");
            conditionBean.setValue(taxAuthorityView.getTxtMaQuanHuyen().getValue());
            conditionBean.setOperator(Constants.OPERATOR.NAME_LIKE);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        AppParamsDTO statusDTO = (AppParamsDTO) taxAuthorityView.getCboStatus().getValue();
        if (!DataUtil.isNullObject(statusDTO) && !DataUtil.isStringNullOrEmpty(statusDTO.getParCode())) {
            ConditionBean conditionBean = new ConditionBean();
            conditionBean.setField("status");
            conditionBean.setValue(statusDTO.getParCode());
            conditionBean.setOperator(Constants.OPERATOR.NAME_EQUAL);
            conditionBean.setType(Constants.TYPEWS.TYPE_STRING);
            lstConditionBean.add(conditionBean);
        }
        return lstConditionBean;

    }
}
