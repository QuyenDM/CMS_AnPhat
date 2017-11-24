/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.component;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.MakeURL;
import com.cms.utils.StringUtils;
import com.cms.ui.CommonTablePanel;
//import com.viettel.logistics.component.CustomPageTable;
//import com.viettel.logistics.ui.CommonTablePanel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author duyot
 */
public class CommonFunction {

    public static void showMessage(String message) {
        Notification noti = new Notification(message);
        noti.setDelayMsec(1500);
        Notification.show(message);
    }

    public static void initTable(CommonTablePanel tblPn, LinkedHashMap<String, Table.Align> tblGoodsHeaderData,
            BeanItemContainer container, String caption, int tblSize, String lang) {
        initTable(tblPn, tblGoodsHeaderData, null, container, caption, tblSize, lang, false, false, false);

    }

    public static void initTable(CommonTablePanel tblPn, LinkedHashMap<String, Table.Align> tblGoodsHeaderData, String[] header,
            final BeanItemContainer container, String caption, int tblSize, String lang, boolean isShowBtnAdd, boolean isShowBtnSave, boolean isShowBtnDelete) {
        //INIT INSIDE COMPONENT
        VerticalLayout verTbl;
        final Table tbl = tblPn.getTableCommon();
        verTbl = tblPn.getVerticalLayout();
        tblPn.getGridLayoutTableCommon().removeAllComponents();
//      Neu caption != null thi set caption
        if (!DataUtil.isStringNullOrEmpty(caption)) {
            tblPn.getVerticalLayout().setCaption(MakeURL.makeURLForTable(caption));
            tblPn.getVerticalLayout().setCaptionAsHtml(true);
        }
        //Set nicer header names
        for (String headerStr : tblGoodsHeaderData.keySet()) {
            tbl.setColumnHeader(headerStr, BundleUtils.getString(lang + "." + headerStr));
            tbl.setColumnAlignment(headerStr, tblGoodsHeaderData.get(headerStr));
        }
//        switch (tblSize) {
//            case 0:
//                tbl.setPageLength(Integer.valueOf(Constants.PAGE_SIZE_DEFAULT_5));
//                break;
//            case 5:
//                tbl.setPageLength(Integer.valueOf(Constants.PAGE_SIZE_DEFAULT_5));
//                verTbl.addComponent(tbl.createControls(Constants.PAGE_SIZE_DEFAULT_5));
//                break;
//            case 10:
//                tbl.setPageLength(Integer.valueOf(Constants.PAGE_SIZE_DEFAULT_10));
//                verTbl.addComponent(tbl.createControls(Constants.PAGE_SIZE_DEFAULT_10));
//                break;
//            case 15:
//                tbl.setPageLength(Integer.valueOf(Constants.PAGE_SIZE_DEFAULT_15));
//                verTbl.addComponent(tbl.createControls(Constants.PAGE_SIZE_DEFAULT_15));
//                break;
//            case 20:
//                tbl.setPageLength(Integer.valueOf(Constants.PAGE_SIZE_DEFAULT_20));
//                verTbl.addComponent(tbl.createControls(Constants.PAGE_SIZE_DEFAULT_20));
//                break;
//            case 25:
//                tbl.setPageLength(Integer.valueOf(Constants.PAGE_SIZE_DEFAULT_25));
//                verTbl.addComponent(tbl.createControls(Constants.PAGE_SIZE_DEFAULT_25));
//                break;
//            default:
//                tbl.setPageLength(Integer.valueOf(Constants.PAGE_SIZE_DEFAULT_5));
//                verTbl.addComponent(tbl.createControls(Constants.PAGE_SIZE_DEFAULT_5));
//                break;
//        }
        //SET DATASOURCE
        tbl.setContainerDataSource(container);
        //Set visible columns
        tbl.setVisibleColumns((Object[]) StringUtils.convertSetToArray(tblGoodsHeaderData.keySet()));
        tbl.setRowHeaderMode(Table.RowHeaderMode.INDEX);

        tblPn.getHorizoltalLayout().setVisible(isShowBtnSave || isShowBtnAdd || isShowBtnDelete);
        tblPn.getBtnAdd().setVisible(isShowBtnAdd);
        tblPn.getBtnSave().setVisible(isShowBtnSave);
        tblPn.getBtnDelelete().setVisible(isShowBtnDelete);
        if (tblPn.getDeleteButton().isVisible()) {
            tblPn.getDeleteButton().addClickListener(new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    final List<Object> lstId = new ArrayList<>();
                    if (tbl.getValue() != null) {
                        if (tbl.isMultiSelect()) {
                            lstId.addAll((Collection<? extends Object>) tbl.getValue());
                        } else {
                            lstId.add(tbl.getValue());
                        }
                    }

                    if (lstId != null && lstId.size() > 0) {
                        ConfirmDialog.show(UI.getCurrent(), BundleUtils.getString("delete.item.title"), BundleUtils.getString("delete.item.body"),
                                BundleUtils.getString("yes"), BundleUtils.getString("no"), new ConfirmDialog.Listener() {
                                    @Override
                                    public void onClose(ConfirmDialog dialog) {
                                        if (dialog.isConfirmed()) {

//                                        lstId.add(DataUtil.getValueIdFromObject(obj));
                                            String className = container.getBeanType().toString();
                                            String returnValue = DataUtil.deleteObject(lstId, className);
                                            if (returnValue.equalsIgnoreCase(Constants.SUCCESS)) {
                                                for (Object item : lstId) {
                                                    tbl.removeItem(item);
                                                }
                                                CommonUtils.showSuccess();
                                            } else {
                                                CommonUtils.showFail();
                                            }
                                        }

                                    }
                                });
                    } else {
                        CommonUtils.showChoseOne();
                    }
                }
            });
        }
        //add action for table         
    }

    public static void initDetailTable(CommonTablePanel tblPn, LinkedHashMap<String, Table.Align> tblGoodsHeaderData, String[] header, BeanItemContainer container, String caption, int tblSize, String lang) {
        //INIT INSIDE COMPONENT
        VerticalLayout verTbl;
        Table tbl = tblPn.getTableCommon();
        verTbl = tblPn.getVerticalLayout();
        tblPn.getGridLayoutTableCommon().removeAllComponents();
        tblPn.getVerticalLayout().setCaption(caption);

        //SET COLUMN HEADER AND ALIGNMENT
        tbl.setRowHeaderMode(Table.RowHeaderMode.INDEX);
        //Set nicer header names
        for (String headerStr : tblGoodsHeaderData.keySet()) {
            tbl.setColumnHeader(headerStr, BundleUtils.getString("wms.common.columnheader." + lang + "." + headerStr));
            tbl.setColumnAlignment(headerStr, tblGoodsHeaderData.get(headerStr));
        }
        //SET DATASOURCE
        tbl.setContainerDataSource(container);
        //Set visible columns
        tbl.setVisibleColumns((Object[]) StringUtils.convertSetToArray(tblGoodsHeaderData.keySet()));
//        tbl.setVisibleColumns(header);

        //SET TABLE ATTRIBUTE
        tbl.setSelectable(true);
        tbl.setColumnReorderingAllowed(true);
        tbl.setColumnCollapsingAllowed(true);

    }

    public static void refreshTable(CommonTablePanel tblPn, LinkedHashMap<String, Table.Align> tblGoodsHeaderData, BeanItemContainer container, String lang) {
        Table tbl = tblPn.getTableCommon();
        //SET DATASOURCE
        tbl.setContainerDataSource(container);
        //Set visible columns
        tbl.setVisibleColumns((Object[]) StringUtils.convertSetToArray(tblGoodsHeaderData.keySet()));
    }

    public static void refreshDetailTable(CommonTablePanel tblPn, LinkedHashMap<String, Table.Align> tblGoodsHeaderData, BeanItemContainer container, String lang) {
        Table tbl = tblPn.getTableCommon();
        tbl.setContainerDataSource(container);
        tbl.setVisibleColumns((Object[]) StringUtils.convertSetToArray(tblGoodsHeaderData.keySet()));
    }

    //QuyenDM setEnable button sau khi click button
    public static void enableButtonAfterClick(Button.ClickEvent event) {
        event.getButton().setEnabled(true);
    }

    //Show Error
    public static void showError(String message) {
        Notification noti = new Notification(message);
        noti.setDelayMsec(1500);
        Notification.show(message, Notification.Type.ERROR_MESSAGE);
    }

    public static void initTable(CommonTablePanel tblPn, LinkedHashMap<String, Table.Align> tblGoodsHeaderData,
            BeanItemContainer container, String caption, int tblSize, String lang, boolean isToolbar) {
        initTable(tblPn, tblGoodsHeaderData, null, container, caption, tblSize, lang, false, false, false);
        if (!isToolbar) {
            tblPn.getVerticalLayout().removeComponent(tblPn.getToolbar());
        }
    }

    public static void refreshTable(CustomPageTable tbl, LinkedHashMap<String, Table.Align> tblGoodsHeaderData, BeanItemContainer container, String lang) {
        //SET DATASOURCE
        tbl.setContainerDataSource(container);
        //Set visible columns
        //Set nicer header names
        for (String headerStr : tblGoodsHeaderData.keySet()) {
            tbl.setColumnHeader(headerStr, BundleUtils.getString(lang + "." + headerStr));
            tbl.setColumnAlignment(headerStr, tblGoodsHeaderData.get(headerStr));
        }
        tbl.setVisibleColumns((Object[]) StringUtils.convertSetToArray(tblGoodsHeaderData.keySet()));
    }

    public static void resetTable(CustomPageTable tbl, LinkedHashMap<String, Table.Align> tblGoodsHeaderData, String lang) {
        tbl.removeAllItems();
        tbl.refreshRowCache();
        //Set nicer header names
        for (String headerStr : tblGoodsHeaderData.keySet()) {
            tbl.setColumnHeader(headerStr, BundleUtils.getString(lang + "." + headerStr));
            tbl.setColumnAlignment(headerStr, tblGoodsHeaderData.get(headerStr));
        }
        tbl.setVisibleColumns((Object[]) StringUtils.convertSetToArray(tblGoodsHeaderData.keySet()));
    }
}
