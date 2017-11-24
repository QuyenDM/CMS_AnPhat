/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.ClientConnector;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomTable;
import com.cms.component.CustomPageTableFilter;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TruongBx3
 */
public class TableUtils implements Serializable {

    public Map<Object, CheckBox> itemIdToCheckbox = new HashMap<>();
    public Map<String, Boolean> mapPageSelected = new HashMap<>();
    List<Object> lst;

    public TableUtils() {
    }

    public void generateColumn(final CustomPageTableFilter<IndexedContainer> tbl) {
        tbl.addGeneratedColumn(Constants.CHECKBOX_COLUMN, new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(final CustomTable source, final Object itemId, Object columnId) {
                setHeader(tbl);
                final CheckBox checkBox = new CheckBox("");
                checkBox.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                        // Don't react to the event if we're being changed from the table-value-change event
                        Boolean selected = (Boolean) valueChangeEvent.getProperty().getValue();
                        if (selected) {
                            source.select(itemId);
                        } else {
                            source.unselect(itemId);
                        }

                    }
                });
//                set value 
                checkBox.setValue(isItemIdSelected(source, itemId));

                // Let's keep track of the checkboxes
                checkBox.addAttachListener(new ClientConnector.AttachListener() {
                    @Override
                    public void attach(ClientConnector.AttachEvent event) {
                        itemIdToCheckbox.put(itemId, checkBox);
                    }
                });

                return checkBox;
            }
        });
        tbl.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
//                return
                Object newSelectionValue = event.getItemId();
                CheckBox cb = itemIdToCheckbox.get(newSelectionValue);
                if (!event.isCtrlKey()) {
                    Collection<Object> collection = (Collection<Object>) tbl.getValue();
                    if (collection.size() > 1) {
                        cb.setValue(true);
                    } else {
                        cb.setValue(!cb.getValue());
                    }

                    for (Object item : itemIdToCheckbox.keySet()) {
                        if (item != newSelectionValue) {
                            itemIdToCheckbox.get(item).setValue(false);
                        }
                    }
                } else {

                    cb.setValue(!cb.getValue());
                }
            }
        });

        tbl.addListener(new CustomTable.HeaderClickListener() {

            @Override
            public void headerClick(CustomTable.HeaderClickEvent event) {
                if (Constants.CHECKBOX_COLUMN.equals(event.getPropertyId())) {
                    String currentPage = String.valueOf(tbl.getCurrentPage());
                    if (mapPageSelected.get(currentPage) == null) {
                        mapPageSelected.put(currentPage, false);
                    }
                    if (!mapPageSelected.get(currentPage)) {
                        List<Object> lstObject = (List<Object>) tbl.getContainerDataSource().getItemIds();
                        for (Object itemId : lstObject) {
                            setCheckBoxes(itemId, true);
                            tbl.select(itemId);
                        }

                        // Change the header value
                        tbl.setColumnHeader(Constants.CHECKBOX_COLUMN, "<span class=\"v-checkbox v-widget\">\n"
                                + "<input  checked=\"\" type=\"checkbox\" >\n"
                                + "<label></label>\n"
                                + "</span>");
                    } else {
                        for (Object itemId : tbl.getItemIds()) {
                            setCheckBoxes(itemId, false);
                            tbl.unselect(itemId);
                        }
                        tbl.setColumnHeader(Constants.CHECKBOX_COLUMN, "<span class=\"v-checkbox v-widget\">\n"
                                + "<input  type=\"checkbox\" >\n"
                                + "<label></label>\n"
                                + "</span>");
                    }
                    mapPageSelected.put(currentPage, !mapPageSelected.get(currentPage));
                }
            }
        });

        /* Just some cosmetics : no caption for the checkbox, and make it the first column */
        tbl.setColumnHeader(Constants.CHECKBOX_COLUMN, "<span class=\"v-checkbox v-widget\">\n"
                + "<input  type=\"checkbox\" >\n"
                + "<label></label>\n"
                + "</span>");
        tbl.setColumnAlignment(Constants.CHECKBOX_COLUMN, CustomTable.Align.CENTER);
    }

    public void setDefaul(List lst) {
        this.lst = lst;
    }

    public void setCheckBoxes(Object itemIdOrIds, boolean value) {
        if (itemIdOrIds instanceof Collection) {
            Collection ids = (Collection) itemIdOrIds;
            for (Object id : ids) {
                setCheckBox(id, value);
            }
        } else {
            setCheckBox(itemIdOrIds, value);
        }
    }

    public void disableCheckboxes(Object itemIdOrIds, boolean value) {
        if (itemIdOrIds instanceof Collection) {
            Collection ids = (Collection) itemIdOrIds;
            for (Object id : ids) {
                disableCheckbox(id, value);
            }
        } else {
            setCheckBox(itemIdOrIds, value);
        }
    }

    private void disableCheckbox(Object id, boolean value) {
        CheckBox checkBox = itemIdToCheckbox.get(id);
        if (checkBox != null) {
            checkBox.setEnabled(value);
        }

    }

    private void setCheckBox(Object id, boolean value) {
        CheckBox checkBox = itemIdToCheckbox.get(id);
        if (checkBox != null) {
            checkBox.setValue(value);
        }

    }

    private boolean isItemIdSelected(AbstractSelect select, Object itemId) {
        if (lst != null && lst.contains(itemId)) {
            return true;

        }
        Object value = select.getValue();
        if (itemId == null || value == null) {
            return false;
        }

        if (select.isMultiSelect()) {
            return ((Collection) value).contains(itemId);
        }

        return itemId.equals(value);
    }

    public void setHeader(CustomPageTableFilter<IndexedContainer> tbl) {
        String currentPage = String.valueOf(tbl.getCurrentPage());
        if (mapPageSelected.get(currentPage) == null) {
            tbl.setColumnHeader(Constants.CHECKBOX_COLUMN, "<span class=\"v-checkbox v-widget\">\n"
                    + "<input  type=\"checkbox\" >\n"
                    + "<label></label>\n"
                    + "</span>");
            return;
        }
        if (mapPageSelected.get(currentPage) == false) {
            tbl.setColumnHeader(Constants.CHECKBOX_COLUMN, "<span class=\"v-checkbox v-widget\">\n"
                    + "<input  type=\"checkbox\" >\n"
                    + "<label></label>\n"
                    + "</span>");
        } else {
            tbl.setColumnHeader(Constants.CHECKBOX_COLUMN, "<span class=\"v-checkbox v-widget\">\n"
                    + "<input  checked=\"\" type=\"checkbox\" >\n"
                    + "<label></label>\n"
                    + "</span>");
        }
    }

    public void refreshCheckBox(CustomPageTableFilter<IndexedContainer> tbl) {

        tbl.setColumnHeader(Constants.CHECKBOX_COLUMN, "<span class=\"v-checkbox v-widget\">\n"
                + "<input  type=\"checkbox\" >\n"
                + "<label></label>\n"
                + "</span>");
    }

    public Map<Object, CheckBox> getItemIdToCheckbox() {
        return itemIdToCheckbox;
    }

    public void setItemIdToCheckbox(Map<Object, CheckBox> itemIdToCheckbox) {
        this.itemIdToCheckbox = itemIdToCheckbox;
    }

}
