/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.dashboard;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import static com.cms.dashboard.DashboardMenu.ID;

/**
 *
 * @author QUANG THIEU
 */
public class MenuLayout extends CustomComponent {

    public MenuLayout(Component componentt) {
        addStyleName("valo-menu");
        setId(ID);
        setSizeUndefined();
        setCompositionRoot(componentt);

    }

}
