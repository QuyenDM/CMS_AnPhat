/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.component;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author quyen
 */
public class CommonLayout extends CustomComponent {

    protected VerticalLayout root;

    public CommonLayout() {
        buildRootLayout();
    }

    private void buildRootLayout() {
        root = new VerticalLayout();
        root.setImmediate(true);
        root.setWidth("100%");
        root.setHeight("100%");
        root.setMargin(true);
        root.setSpacing(true);
        // top-level component properties
        setWidth("100.0%");
        setHeight("100.0%");

        //Them cac thanh phan vao grid
        setCompositionRoot(root);
    }
}
