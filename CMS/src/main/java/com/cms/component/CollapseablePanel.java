/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.component;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

/**
 *
 * @author SONY
 */

public class CollapseablePanel extends CustomComponent {

    private static final long serialVersionUID = 632703177884323377L;
    final private CssLayout root;
    private CssLayout bodyContainer;
    
    void toggleBodyVisible(){
        bodyContainer.setVisible(!bodyContainer.isVisible());
    }
    
    public CollapseablePanel(String title, Component body) {
        super();
        root = new CssLayout();
        root.addStyleName("collapseblebox-container");
        setCompositionRoot(root);
        draw(title, body);
    }

    void draw(String title, Component body) {
        CssLayout titleLayout = new CssLayout();
        titleLayout.addStyleName("collapsablebox-title");
        titleLayout.addComponent(new Label(title));
        titleLayout.addListener(new LayoutClickListener() {
            private static final long serialVersionUID = -4750845792730551399L;
            @Override
            public void layoutClick(LayoutClickEvent event) {
                toggleBodyVisible();
            }
        });
        
        bodyContainer = new CssLayout();
        bodyContainer.addStyleName("collapsablebox-body");
        bodyContainer.addComponent(body);
        
        root.addComponent(titleLayout);
        root.addComponent(bodyContainer);
    }
}
