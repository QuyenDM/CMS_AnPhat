/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.component;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import com.cms.utils.BundleUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.MakeURL;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author quyen
 */
public class CommonSearchForm extends CustomComponent {

    protected VerticalLayout root;
    protected GridLayout searchLayout;
    protected GridManyButton buttonLayout;
    protected String caption;
    protected Locale mlocale = (Locale) VaadinSession.getCurrent().getSession().getAttribute("locale");
    protected List<Component> lstComponents;
    protected int column;
    protected int row;

    public Button btnSearch;
    public Button btnReset;

    public void init() {
        root = new VerticalLayout();
        root.setImmediate(true);
        root.setWidth("100%");
        root.setHeight("100%");
        root.setMargin(false);
        root.setSpacing(false);
        // top-level component properties
        setWidth("100.0%");
        setHeight("100.0%");
        root = buildMainLayout();
        setCompositionRoot(root);
    }

    public VerticalLayout buildMainLayout() {
        searchLayout = buildSearchLayout();
        root.addComponent(searchLayout);
        buttonLayout = buildButtonLayout();
        root.addComponent(buttonLayout);
        return root;
    }

    public GridLayout buildSearchLayout() {
        searchLayout = new GridLayout();
        searchLayout.setImmediate(true);
        searchLayout.setWidth("100.0%");
        searchLayout.setHeight("-1px");
        searchLayout.setMargin(true);
        searchLayout.setSpacing(false);
        return searchLayout;
    }

    public GridManyButton buildButtonLayout() {
        buttonLayout = new GridManyButton(new String[]{Constants.BUTTON_SEARCH, Constants.BUTTON_REFRESH});
        btnSearch = buttonLayout.getBtnCommon().get(0);
        btnReset = buttonLayout.getBtnCommon().get(1);
        return buttonLayout;
    }

    public void setCaption() {
        searchLayout.setStyleName("custom-feildset");
        searchLayout.setCaption(MakeURL.makeURLForGrid(BundleUtils.getString(caption)));
        searchLayout.setCaptionAsHtml(true);
    }

    public void initSearchContents() {
        searchLayout.setColumns(column);
        searchLayout.setColumns(row);
        if (DataUtil.isListNullOrEmpty(lstComponents)) {
            return;
        }
        for (Component c : lstComponents) {
            searchLayout.addComponent(c);
        }
    }
}
