package com.cms.view;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.cms.dashboard.DashboardMenu;
//import com.vcs.dashboard.DashboardViewType;
import com.cms.event.DashboardEvent;
import com.cms.event.DashboardEventBus;
/*
 * Dashboard MainView is a simple HorizontalLayout that wraps the menu on the
 * left and creates a simple container for the navigator on the right.
 */

@SuppressWarnings("serial")
public class MainView extends HorizontalLayout implements View {

    private Navigator navigator;

    public MainView() {
        setSizeFull();
        addStyleName("mainview");
        addComponent(new DashboardMenu());
        ComponentContainer content = new CssLayout();
        content.addStyleName("view-content");
        content.setSizeFull();
        addComponent(content);
        setExpandRatio(content, 1.0f);
        content.addStyleName("view-content");
        navigator = new Navigator(UI.getCurrent(), content);
//        buildNavigator();
        navigator.addView("home.vt", Home.class);
//        navigator.addView("mai.vt", Mai.class);
//        navigator.navigateTo("home.vt");
        navigator.setErrorView(Error.class);
         final String f = Page.getCurrent().getUriFragment();
        if (f == null || f.equals("")) {
            navigator.navigateTo("home.vt");
        }
        navigator.addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(final ViewChangeListener.ViewChangeEvent event) {
                
                return true;
            }

            @Override
            public void afterViewChange(final ViewChangeListener.ViewChangeEvent event) {
//                DashboardViewType view = DashboardViewType.getByViewNavigator(event
//                        .getViewName());
                // Appropriate events get fired after the view is changed.
//                DashboardEventBus.post(new DashboardEvent.PostViewChangeEvent(view));
                DashboardEventBus.post(new DashboardEvent.BrowserResizeEvent());
                DashboardEventBus.post(new DashboardEvent.CloseOpenWindowsEvent());
            }
        });
    }

//    public void buildNavigator() {
//        for (DashboardViewType viewType : DashboardViewType.values()) {
//            navigator.addView(viewType.getViewNavigator(), viewType.getViewClass());
//        }
//    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
