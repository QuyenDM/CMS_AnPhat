/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.component;

import com.google.common.collect.Lists;
import com.vaadin.data.util.BeanItemContainer;

import java.util.List;
import com.vaadin.data.Property;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.cms.utils.DataUtil;

/**
 *
 * @author vtsoft
 */
@SuppressWarnings("serial")
public class MappingCombobox extends GridLayout {

    GridLayout layout = new GridLayout(4, 1);
    Object obj = new Object();

    public ComboBox codeCombo;
    public ComboBox nameCombo;
    List<?> combo = Lists.newArrayList();

    public void setVisibleCombo(boolean isVisible) {
        layout.setVisible(isVisible);
    }

    public MappingCombobox() {
        layout.setImmediate(true);
        layout.setWidth("100%");
        layout.setHeight("-1px");
        codeCombo = new ComboBox();
        nameCombo = new ComboBox();
        codeCombo.setWidth("100%");
        nameCombo.setWidth("100%");
        codeCombo.setHeight("-1px");
        nameCombo.setHeight("-1px");

        layout.addComponent(codeCombo, 0, 0);
        layout.addComponent(nameCombo, 1, 0, 3, 0);
    }

    public MappingCombobox(String codeCaption, String nameCaption) {
        layout.setImmediate(true);
        layout.setWidth("100%");
        layout.setHeight("-1px");
        codeCombo = new ComboBox();
        nameCombo = new ComboBox();
        codeCombo.setWidth("100%");
        nameCombo.setWidth("100%");
        codeCombo.setHeight("-1px");
        nameCombo.setHeight("-1px");

        if (!DataUtil.isStringNullOrEmpty(codeCaption)) {
            codeCombo.setCaption(codeCaption);
            nameCombo.setCaption(nameCaption);
        }
        layout.addComponent(codeCombo, 0, 0);
        layout.addComponent(nameCombo, 1, 0, 3, 0);
    }

    public MappingCombobox(int column, int first) {
        layout = new GridLayout(column, 1);
        layout.setImmediate(true);
        layout.setWidth("100%");
        layout.setHeight("-1px");
        codeCombo = new ComboBox();
        nameCombo = new ComboBox();
        codeCombo.setWidth("100%");
        nameCombo.setWidth("100%");
        codeCombo.setHeight("-1px");
        nameCombo.setHeight("-1px");
        if (first >= column) {
            first = 1;
        }
        layout.addComponent(codeCombo, 0, 0, first - 1, 0);
        layout.addComponent(nameCombo, first, 0, column - 1, 0);
    }

    public void setValues(List<?> lstCbo, String columnCode, String columnName) {
        this.combo = lstCbo;
        if (DataUtil.isListNullOrEmpty(combo)) {
            return;
        }
        obj = combo.get(0);
        Class c = obj.getClass();

        final BeanItemContainer<Object> container
                = new BeanItemContainer<>(c);
        final BeanItemContainer<Object> container1
                = new BeanItemContainer<>(c);
        container.removeAllItems();
        container1.removeAllItems();
        container1.addAll(combo);
        container.addAll(combo);

        codeCombo.setContainerDataSource(container);

        nameCombo.setContainerDataSource(container1);

        codeCombo.setItemCaptionPropertyId(columnCode);
        codeCombo.setNullSelectionAllowed(false);
        nameCombo.setItemCaptionPropertyId(columnName);

        nameCombo.setNullSelectionAllowed(false);

        codeCombo.setFilteringMode(FilteringMode.CONTAINS);
        nameCombo.setFilteringMode(FilteringMode.CONTAINS);
        codeCombo.setImmediate(true);
        nameCombo.setImmediate(true);

        codeCombo.addListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                nameCombo.setValue(event.getProperty().getValue());
            }
        });
        nameCombo.addListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                codeCombo.setValue(event.getProperty().getValue());
            }
        });
    }

    public void resetData() {
        nameCombo.setValue(obj);
    }

    public void setData(int i) {
        nameCombo.setValue(combo.get(i));
    }

    public void clearData() {
        codeCombo.removeAllItems();
        nameCombo.removeAllItems();
    }

    public GridLayout getLayout() {
        return layout;
    }

    public void setLayout(GridLayout layout) {
        this.layout = layout;
    }

    public ComboBox getCodeCombo() {
        return codeCombo;
    }

    public void setCodeCombo(ComboBox codeCombo) {
        this.codeCombo = codeCombo;
    }

    public ComboBox getNameCombo() {
        return nameCombo;
    }

    public void setNameCombo(ComboBox l1) {
        this.nameCombo = l1;
    }
}
