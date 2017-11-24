/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.component;

/**
 *
 * @author Truongbx3
 *
 */
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.ItemSorter;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.DateTimeUtils;
import com.cms.utils.DefaultFilterDecorator;
import com.cms.utils.DefaultFilterGenerator;
import com.cms.utils.MakeURL;
import com.cms.utils.ShortcutUtils;
import com.cms.utils.StringUtils;
import com.cms.utils.pagedFilterControlConfigDefaul;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vaadin.dialogs.ConfirmDialog;

public class CommonFunctionTableFilter {

    public static void showMessage(String message) {
        Notification noti = new Notification(message);
        noti.setDelayMsec(1500);
        noti.show(message);
    }

    public static void initTable(CommonTableFilterPanel tblPn, LinkedHashMap<String, CustomTable.Align> headerData,
            BeanItemContainer container, String caption, int tblSize, String lang) {
        initTable(tblPn, headerData, container, caption, tblSize, lang, true, false, false, false, true);

    }

    public static void initTable(CommonTableFilterPanel tblPn, LinkedHashMap<String, CustomTable.Align> headerData,
            BeanItemContainer container, String caption, boolean viewSearchBar, int tblSize, String lang) {
        initTable(tblPn, headerData, container, caption, tblSize, lang, viewSearchBar, false, false, false, true);

    }

    public static void initTable(final CommonTableFilterPanel tblPn, LinkedHashMap<String, CustomTable.Align> headerData,
            final BeanItemContainer container, String caption, int tblSize, String lang, boolean viewSearchBar, boolean isShowBtnAdd, boolean isShowBtnSave, boolean isShowBtnDelete, boolean isActionDelele) {
        //INIT INSIDE COMPONENT
        VerticalLayout verTbl;
        final CustomPageTableFilter<IndexedContainer> tbl = tblPn.getMainTable();
        tbl.setSortEnabled(true);
        verTbl = tblPn.getVerTBLListGoodInfomation();
//        tblPn.getGridTBLInfomation().removeAllComponents();
//        tblPn.getVerTBLListGoodInfomation().setCaption(caption);
        tblPn.getVerTBLListGoodInfomation().setCaption(MakeURL.makeURLForTable(caption));
        tblPn.getVerTBLListGoodInfomation().setCaptionAsHtml(true);
//        tblPn.getVerTBLListGoodInfomation().setCaption("aaaa");

        //Set nicer header names
        tbl.addGeneratedColumn(Constants.STT, new CustomTable.ColumnGenerator() {

            @Override
            public Object generateCell(CustomTable source, Object itemId, Object columnId) {
                List lstObj = (List) source.getItemIds();
                int i = lstObj.indexOf(itemId);
                return i + 1;
            }
        });
        tbl.setColumnAlignment(Constants.STT, CustomTable.Align.CENTER);
        tbl.setColumnWidth(Constants.STT, 45);
        for (String headerStr : headerData.keySet()) {
            if (!Constants.CHECKBOX_COLUMN.equalsIgnoreCase(headerStr)) {
                tbl.setColumnHeader(headerStr.trim(), BundleUtils.getString(lang + "." + headerStr));
            }
            if ("delete".equalsIgnoreCase(headerStr) || "edit".equalsIgnoreCase(headerStr)) {
                tbl.setColumnHeader(headerStr.trim(), BundleUtils.getString(headerStr));
            }
            tbl.setColumnAlignment(headerStr.trim(), headerData.get(headerStr));
        }
        //QuyenDM set tooltip
        ShortcutUtils.setTooltipForFields(tbl, StringUtils.convertSetToArray(headerData.keySet()));
        //SET DATASOURCE
        tbl.setContainerDataSource(container);
        tbl.setWidth("100%");
        tbl.setHeight("-1px");
        tbl.setFilterDecorator(new DefaultFilterDecorator());
        tbl.setFilterGenerator(new DefaultFilterGenerator());
        tbl.setSortEnabled(true);
        tbl.setFilterBarVisible(viewSearchBar);
        tbl.setSelectable(true);
        tbl.setImmediate(true);
        tbl.setMultiSelect(true);

        tbl.setColumnCollapsingAllowed(true);
        tbl.setColumnReorderingAllowed(true);

        pagedFilterControlConfigDefaul defaultConfig = new pagedFilterControlConfigDefaul();
        switch (tblSize) {
            case 0:
                tbl.setPageLength(Integer.valueOf(Constants.PAGE_SIZE_DEFAULT_5));
                break;
            case -1:
                tbl.createControls(1);
                break;
            default:
                verTbl.addComponent(tbl.createControls(defaultConfig, String.valueOf(tblSize)));
                break;
        }
        tbl.setVisibleColumns((Object[]) StringUtils.convertSetToArray(headerData.keySet()));
        tblPn.getHorizoltalLayout().setVisible(isShowBtnSave || isShowBtnAdd || isShowBtnDelete);
        tblPn.getBtnAdd().setVisible(false);
        tblPn.getBtnSave().setVisible(false);
        tblPn.getBtnDelelete().setVisible(false);

        container.setItemSorter(new ItemSorter() {
            String propertyID = "";
            boolean sort = false;

            @Override
            public void setSortProperties(Container.Sortable container, Object[] propertyId, boolean[] ascending) {
                propertyID = (String) propertyId[0];
                sort = ascending[0];
            }

            @Override
            public int compare(Object itemId1, Object itemId2) {
                Class<?> c = itemId1.getClass();
                if (propertyID.contains("Date") || propertyID.contains("Time")) {
                    try {
                        Method methodId = c.getMethod(DataUtil.getGetterOfColumn(propertyID));

                        Date date1 = DateTimeUtils.dateTimeCompare((String) methodId.invoke(itemId1));

                        Date date2 = DateTimeUtils.dateTimeCompare((String) methodId.invoke(itemId2));
                        if (date1 == null) {
                            return sort ? -1 : 1;
                        }
                        if (date2 == null) {
                            return sort ? 1 : -1;
                        }
                        return sort ? date1.compareTo(date2) : date2.compareTo(date1);
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(CommonFunctionTableFilter.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SecurityException ex) {
                        Logger.getLogger(CommonFunctionTableFilter.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(CommonFunctionTableFilter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {

                    try {
                        Method mt = c.getMethod(DataUtil.getGetterOfColumn(propertyID));
                        String value1 = (String) mt.invoke(itemId1);
                        if (value1 == null) {
                            value1 = "";
                        }
                        String value2 = (String) mt.invoke(itemId2);
                        if (value2 == null) {
                            value2 = "";
                        }
                        return sort ? value1.compareTo(value2) : value2.compareTo(value1);
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(CommonFunctionTableFilter.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SecurityException ex) {
                        Logger.getLogger(CommonFunctionTableFilter.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(CommonFunctionTableFilter.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                return 1;
            }

        });
        if (tblPn.getDeleteButton().isVisible() && isActionDelele) {
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
                                        tbl.resetPage();
                                        Notification.show(BundleUtils.getString("actionSuccess"), Notification.Type.HUMANIZED_MESSAGE);
                                    } else {
                                        Notification.show(BundleUtils.getString("actionFail"), Notification.Type.ERROR_MESSAGE);
                                    }
                                }

                            }
                        });
                    } else {
                        Notification.show(BundleUtils.getString("chooseOneRecord"), Notification.Type.WARNING_MESSAGE);
                    }
                    tblPn.getDeleteButton().setEnabled(true);
                }
            });
        }

    }

    public static void refreshTable(CommonTableFilterPanel tblPn, LinkedHashMap<String, CustomTable.Align> headerData, BeanItemContainer container) {
        CustomPageTableFilter tbl = tblPn.getMainTable();
        //SET DATASOURCE
        tbl.setContainerDataSource(container);
        //Set visible columns
        tbl.setVisibleColumns((Object[]) StringUtils.convertSetToArray(headerData.keySet()));
    }

    public static void refreshTable(CommonTableFilterPanel tblPn, LinkedHashMap<String, CustomTable.Align> tblGoodsHeaderData, BeanItemContainer container, String lang) {
        CustomPageTableFilter tbl = tblPn.getMainTable();
        //SET COLUMN HEADER AND ALIGNMENT
        //Set nicer header names
        for (String headerStr : tblGoodsHeaderData.keySet()) {
            tbl.setColumnHeader(headerStr, BundleUtils.getString(lang + "." + headerStr));
            tbl.setColumnAlignment(headerStr, tblGoodsHeaderData.get(headerStr));
        }
        //SET DATASOURCE
        tbl.setContainerDataSource(container);
        //Set visible columns
        tbl.setVisibleColumns((Object[]) StringUtils.convertSetToArray(tblGoodsHeaderData.keySet()));
    }

    public static void refreshTable(CommonTableFilterPanel tblPn, LinkedHashMap<String, CustomTable.Align> headerData, BeanItemContainer container, boolean isScollbar) {
        CustomPageTableFilter tbl = tblPn.getMainTable();
        //SET DATASOURCE
        tbl.setContainerDataSource(container);
        //Set table scrollbar
        if (isScollbar) {
            tbl.setPageLength(Integer.MAX_VALUE);
        }
        //Set visible columns
        tbl.setVisibleColumns((Object[]) StringUtils.convertSetToArray(headerData.keySet()));
    }

    public static void refreshTable(CommonTableFilterPanel tblPn, LinkedHashMap<String, CustomTable.Align> tblGoodsHeaderData, BeanItemContainer container, String lang, boolean isScollbar) {
        CustomPageTableFilter tbl = tblPn.getMainTable();
        //SET COLUMN HEADER AND ALIGNMENT
        //Set nicer header names
        for (String headerStr : tblGoodsHeaderData.keySet()) {
            tbl.setColumnHeader(headerStr, BundleUtils.getString(lang + "." + headerStr));
            tbl.setColumnAlignment(headerStr, tblGoodsHeaderData.get(headerStr));
        }
        //SET DATASOURCE
        tbl.setContainerDataSource(container);
        //Set table scrollbar
        if (isScollbar) {
            tbl.setPageLength(Integer.MAX_VALUE);
        }
        //Set visible columns
        tbl.setVisibleColumns((Object[]) StringUtils.convertSetToArray(tblGoodsHeaderData.keySet()));
    }
}
