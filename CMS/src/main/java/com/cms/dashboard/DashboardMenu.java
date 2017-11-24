package com.cms.dashboard;

import com.cms.login.dto.ObjectsDTO;
import com.cms.login.ws.WSObjects;
import com.google.common.eventbus.Subscribe;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.cms.dto.StaffDTO;

import com.cms.event.DashboardEvent;
import com.cms.event.DashboardEvent.PostViewChangeEvent;
import com.cms.event.DashboardEvent.UserLoggedOutEvent;
import com.cms.event.DashboardEventBus;
import com.cms.ui.ChangPasswordUI;
import com.cms.utils.BundleUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.view.Home;
import com.cms.view.ObjectsView;
import com.cms.view.RolesView;
import com.cms.view.ValoMenuLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * A responsive menu component providing user information and the controls for
 * primary navigation between the views.
 */
@SuppressWarnings({"serial", "unchecked"})
public final class DashboardMenu extends CustomComponent implements View {

    public static final String ID = "dashboard-menu";
    public static final String REPORTS_BADGE_ID = "dashboard-menu-reports-badge";
    public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";
    private static final String STYLE_VISIBLE = "valo-menu-visible";
    private MenuItem settingsItem;
    private CssLayout menu = new CssLayout();
    private CssLayout menuItemsLayout = new CssLayout();
    private CssLayout titleMenu = new CssLayout();
    private StaffDTO staffDTO = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");
    private List<ObjectsDTO> lstObjecsDTO = new ArrayList<>();
    private final LinkedHashMap<String, String> menuItems = new LinkedHashMap<String, String>();

    private MenuBar menuBar = new MenuBar();
    private Navigator navigator;
    private VerticalLayout main;
    public HorizontalLayout menuNgang = new HorizontalLayout();
    private ValoMenuLayout root = new ValoMenuLayout();
    private HorizontalLayout menuLayout = new HorizontalLayout();
    private ChangPasswordUI changPasswordUI;

    public DashboardMenu() {
//        
        DashboardEventBus.register(this);
        main = new VerticalLayout();
        Responsive.makeResponsive(main);
        main.setPrimaryStyleName("v-scrollable");
        main.addStyleName("mainview");
        main.setSizeFull();
        main.addStyleName("v-scrollable");
        
        ComponentContainer viewDisplay = root.getContentContainer();
        buildNavigator(viewDisplay);
//        buildMenuDoc();
        buildMenuNgang();
//        setSizeFull();
//        setHeight("100%");

        Panel panel = new Panel();
        VerticalLayout bodyNFooter = new VerticalLayout();
        bodyNFooter.addComponent(root);
//        bodyNFooter.addComponent(buildFooter());
        panel.setSizeFull();
        panel.setContent(bodyNFooter);

        main.addComponent(menuNgang);
        main.addComponent(panel);
//        main.setExpandRatio(panel, 1);
        setCompositionRoot(main);
    }

    //Build Menu doc
    private void buildMenuDoc() {        
        root.addMenu(buildMenu());
        root.addtitleMenu(buildTitle());

    }

    //Build Menu doc
    private void buildMenuNgang() {
        menuNgang = buildMenuHorizontal();
    }

    public HorizontalLayout buildMenuHorizontal() {
        HorizontalLayout layoutParent = new HorizontalLayout();
        layoutParent.setWidth("100%");
        layoutParent.addStyleName("menuBar-vertical");
        layoutParent.setSpacing(false);
        layoutParent.setMargin(false);

//        HorizontalLayout homeLayout = new HorizontalLayout();
        HorizontalLayout functionLayout = new HorizontalLayout();
        HorizontalLayout logoutLayout = new HorizontalLayout();
        functionLayout.setStyleName("menuBar-vertical");
        functionLayout.setWidth("-1px");
        logoutLayout.setStyleName("menuBar-vertical");
        logoutLayout.setWidth("-1px");

        //Nut home
        Button btnHome = new Button();
        btnHome.setCaption(BundleUtils.getString("home.caption"));
        btnHome.addStyleName("v-button-link");
        btnHome.addStyleName("hide-menu");
        btnHome.setIcon(new ThemeResource(Constants.ICON.HOME));
        btnHome.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    navigator.navigateTo("home.vt");
//                    showBannerOnOff();
                } catch (Exception e) {
                }
            }
        });
        GridLayout layoutFunction = new GridLayout(2, 1);
        layoutFunction.setWidth("-1px");
//        layoutFunction.addComponent(btnHome, 0, 0);
//        layoutFunction.setComponentAlignment(btnHome, Alignment.MIDDLE_CENTER);

        functionLayout.addComponent(layoutFunction);
        functionLayout.setComponentAlignment(layoutFunction, Alignment.MIDDLE_LEFT);

        MenuBar functionMenu;
        //Lay danh sach chuc nang
        if (DataUtil.isListNullOrEmpty(lstObjecsDTO)) {
            getListObjects();
        } //Neu danh sach chuc nang da co roi
        functionMenu = new MenuBar();
        functionMenu.addStyleName("menu-bar-stone-1");
        final Map<String, String> mapCurrentPage = new HashMap<>();
        //Them nut home
        functionMenu.addItem(BundleUtils.getString("home.caption"), new ThemeResource(Constants.ICON.HOME), new Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                UI.getCurrent().getNavigator()
                        .navigateTo("home.vt");
                mapCurrentPage.put("current", "home.vt");
            }
        });
        if ("true".equalsIgnoreCase(BundleUtils.getStringCas("firstTime"))) {
            functionMenu.addItem(BundleUtils.getString("function"), new ThemeResource(Constants.ICON.RUN), new Command() {
                @Override
                public void menuSelected(MenuItem selectedItem) {
                    UI.getCurrent().getNavigator()
                            .navigateTo("objects.vt");
                    mapCurrentPage.put("current", "objects.vt");
                }
            });
        }
        if (!DataUtil.isListNullOrEmpty(lstObjecsDTO)) {
            for (final ObjectsDTO objecsDTO : lstObjecsDTO) {
                if (objecsDTO.getObjectType() == null || "0".equals(objecsDTO.getObjectType())) {
                    MenuBar.MenuItem functionItem = functionMenu.addItem(objecsDTO.getName(), null, null);
                    mapCurrentPage.put("current", "");
                    for (final ObjectsDTO childObject : lstObjecsDTO) {
                        if (childObject.getObjectType() != null && !"0".equals(childObject.getObjectType())) {
                            if (childObject.getObjectType().equals(objecsDTO.getObjectId())) {
                                if (getClassFromTokenUrl(childObject.getUrl()) != null) {
                                    navigator.addView(childObject.getUrl(), getClassFromTokenUrl(childObject.getUrl()));
                                    functionItem.addItem(childObject.getName(), null, new Command() {

                                        @Override
                                        public void menuSelected(MenuItem selectedItem) {
                                            UI.getCurrent().getNavigator()
                                                    .navigateTo(childObject.getUrl());
                                            mapCurrentPage.put("current", childObject.getUrl());
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }
        }
        layoutFunction.addComponent(functionMenu, 1, 0);
        layoutFunction.setComponentAlignment(functionMenu, Alignment.MIDDLE_LEFT);

        MenuBar userLayout = buildMenuUser();
        userLayout.addStyleName("menu-bar-stone-1");
        logoutLayout.addComponent(userLayout);
        logoutLayout.setComponentAlignment(userLayout, Alignment.MIDDLE_LEFT);

        layoutParent.addComponent(functionLayout);
        layoutParent.setComponentAlignment(functionLayout, Alignment.MIDDLE_LEFT);
        layoutParent.addComponent(logoutLayout);
        layoutParent.setComponentAlignment(logoutLayout, Alignment.MIDDLE_RIGHT);
        return layoutParent;
    }

    private void buildNavigator(ComponentContainer content) {
        navigator = new Navigator(UI.getCurrent(), content);
        navigator.addView("home.vt", Home.class);
        if ("true".equalsIgnoreCase(BundleUtils.getStringCas("firstTime"))) {
            navigator.addView("objects.vt", ObjectsView.class);
        }
//        navigator.addView("roles.vt", RolesView.class);
        navigator.setErrorView(com.cms.view.Error.class);

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
                DashboardEventBus.post(new DashboardEvent.BrowserResizeEvent());
                DashboardEventBus.post(new DashboardEvent.CloseOpenWindowsEvent());
            }
        });
    }

    private CssLayout buildMenu() {

        menu.addComponent(buildUserMenu());
        menu.addComponent(buildMenuItems());
        return menu;
    }

    private CssLayout buildTitle() {
        final Button hideMenu = new Button();
        hideMenu.setIcon(FontAwesome.ANGLE_DOUBLE_LEFT);
        hideMenu.addStyleName("v-button-link");
        hideMenu.addStyleName("hide-menu");
        hideMenu.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                if (root.menuArea.isVisible()) {
                    root.menuArea.setVisible(false);
                    hideMenu.setIcon(FontAwesome.ANGLE_DOUBLE_RIGHT);
                } else {
                    root.menuArea.setVisible(true);
                    hideMenu.setIcon(FontAwesome.ANGLE_DOUBLE_LEFT);
                }
            }
        });

        hideMenu.setHeight("20px");
        hideMenu.setWidth("20px");
        root.menuArea.setVisible(false);
        hideMenu.setIcon(FontAwesome.ANGLE_DOUBLE_RIGHT);
        titleMenu.addComponent(hideMenu);

        return titleMenu;
    }

    private Component buildUserMenu() {
        StaffDTO user = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");
        final MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");
        settingsItem = settings.addItem(
                DataUtil.isStringNullOrEmpty(user.getName()) ? user.getCode() : user.getName(),
                new ThemeResource("img/profile-pic-300px.jpg"), null);
        settingsItem.addItem(BundleUtils.getString("title.changPassword"), new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                changPasswordUI = new ChangPasswordUI();
                changPasswordUI.getTxtUserName().setValue(staffDTO.getCode());
                UI.getCurrent().addWindow(changPasswordUI);
            }
        });
        settingsItem.addSeparator();
        settingsItem.addItem(BundleUtils.getString("sign.out"), new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                DashboardEventBus.post(new UserLoggedOutEvent());
            }
        });
        return settings;
    }

    private Component buildMenuItems() {
        getListObjects();
//        lstObjecsDTO = new ArrayList<>();
        menuItemsLayout = new CssLayout();
//        VerticalLayout menuItemsLayout = new VerticalLayout();
        menuItemsLayout.addStyleName("valo-menuitems");
        menuItemsLayout.setHeight(100.0f, Unit.PERCENTAGE);

        if (!DataUtil.isListNullOrEmpty(lstObjecsDTO)) {
            for (final ObjectsDTO objecsDTO : lstObjecsDTO) {
                if (objecsDTO.getObjectType() == null || "0".equals(objecsDTO.getObjectType())) {
                    boolean checkIsChild = false;
                    menuBar = new MenuBar();
                    menuBar.addStyleName("menu-bar-stone");
                    MenuBar.MenuItem menu1 = menuBar.addItem(objecsDTO.getName(), null, null);
                    final Map<String, String> mapCurrentPage = new HashMap<>();
                    mapCurrentPage.put("current", "");
                    for (final ObjectsDTO childObject : lstObjecsDTO) {
                        if (childObject.getObjectType() != null && !"0".equals(childObject.getObjectType())) {
                            if (childObject.getObjectType().equals(objecsDTO.getObjectId())) {
                                checkIsChild = true;
                                if (getClassFromTokenUrl(childObject.getUrl()) != null) {
                                    navigator.addView(childObject.getUrl(), getClassFromTokenUrl(childObject.getUrl()));
                                    menu1.addItem(childObject.getName(), null, new Command() {

                                        @Override
                                        public void menuSelected(MenuItem selectedItem) {
                                            UI.getCurrent().getNavigator()
                                                    .navigateTo(childObject.getUrl());
                                            mapCurrentPage.put("current", childObject.getUrl());
                                        }
                                    });
                                }
                            }
                        }
                    }
                    if (!checkIsChild) {
                        if (getClassFromTokenUrl(objecsDTO.getUrl()) != null) {
                            Button menuItem = new Button(objecsDTO.getName());
                            navigator.addView(objecsDTO.getUrl(), getClassFromTokenUrl(objecsDTO.getUrl()));
                            menuItem.setPrimaryStyleName("valo-menu-item");
                            menuItem.addClickListener(new Button.ClickListener() {
                                @Override
                                public void buttonClick(final Button.ClickEvent event) {
                                    UI.getCurrent().getNavigator().navigateTo(objecsDTO.getUrl());
                                }
                            });
                            menuItemsLayout.addComponent(menuItem);
                        }

                    } else {
                        menuItemsLayout.addComponent(menuBar);
                    }

                }
            }
        }
        return menuItemsLayout;
    }

    //Lay danh sach chuc nang
    private void getListObjects() {
        lstObjecsDTO = new ArrayList<>();
        try {
            if ("true".equalsIgnoreCase(BundleUtils.getStringCas("firstTime"))) {
                lstObjecsDTO = WSObjects.getListObjectsDTO(new ObjectsDTO(), 0, 100, Constants.ASC, "code");
            } else {
                lstObjecsDTO = WSObjects.getListObjectDTOByStaffId(staffDTO.getStaffId());
            }
        } catch (Exception e) {
            lstObjecsDTO = new ArrayList<>();
        }
    }

    //Nut logout
    public MenuBar buildMenuUser() {
        final MenuBar settings = new MenuBar();
//        settings.setStyleName("menuBar-vertical");
        final MenuItem settingsItemVer = settings.addItem(staffDTO.getName(), null);
        settingsItemVer.addItem(BundleUtils.getString("title.changPassword"), new Command() {
            @Override
            public void menuSelected(final MenuItem selectedItem) {
                changPasswordUI = new ChangPasswordUI();
                changPasswordUI.getTxtUserName().setValue(staffDTO.getCode());
                UI.getCurrent().addWindow(changPasswordUI);
            }
        });
        settingsItemVer.addSeparator();
        settingsItemVer.addItem(BundleUtils.getString("sign.out"), new MenuBar.Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                DashboardEventBus.post(new UserLoggedOutEvent());
            }
        });
        return settings;
    }

    private Class getClassFromTokenUrl(String vsaUrl) {
        Class c = null;
        try {
            c = Class.forName("com.cms.view." + convertVsaUrlToClassName(vsaUrl));
        } catch (Exception e) {
            System.out.println(e.getMessage() + ": url in vsa " + vsaUrl + " is invalid format - uiClassName.vt");
        }
        return c;
    }

    private String convertVsaUrlToClassName(String vsaUrl) {
        String className = null;
        try {
            // Format: problem.view.uiClassName.vt, viet hoa chu cai dau tien
            className = vsaUrl.substring(0, vsaUrl.indexOf("."));
            className = className.substring(0, 1).toUpperCase() + className.substring(1);
            return className;
        } catch (Exception e) {
            System.out.println(e.getMessage() + ": url in vsa " + vsaUrl + " is invalid format - uiClassName.vt");
        }
        return null;
    }

    @Override
    public void attach() {
        super.attach();
//        updateNotificationsCount(null);
    }

    @Subscribe
    public void postViewChange(final PostViewChangeEvent event) {
        // After a successful view change the menu can be hidden in mobile view.
//        getCompositionRoot().removeStyleName(STYLE_VISIBLE);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Component buildBadgeWrapper(final Component menuItemButton,
            final Component badgeLabel) {
        CssLayout dashboardWrapper = new CssLayout(menuItemButton);
        dashboardWrapper.addStyleName("badgewrapper");
        dashboardWrapper.addStyleName(ValoTheme.MENU_ITEM);
        badgeLabel.addStyleName(ValoTheme.MENU_BADGE);
        badgeLabel.setWidthUndefined();
        badgeLabel.setVisible(false);
        dashboardWrapper.addComponent(badgeLabel);
        return dashboardWrapper;
    }

    public final class ValoMenuItemButton extends Button {

        public ValoMenuItemButton() {

        }

        @Subscribe
        public void postViewChange(final PostViewChangeEvent event) {
        }
    }

    public VerticalLayout buildFooter() {

        Locale mlocale = (Locale) VaadinSession.getCurrent().getSession().getAttribute("locale");
        String copyright = "";
        String cskh = "";
//        if (mlocale.getLanguage().equals("vi")) {
//            copyright = getContentFooter(Constants.CONFIG_SYS.CONFIG_FOOTER_COPYRIGHT_VI);
//            cskh = getContentFooter(Constants.CONFIG_SYS.CONFIG_FOOTER_SUPPORT_VI);
//        } else {
//            copyright = getContentFooter(Constants.CONFIG_SYS.CONFIG_FOOTER_COPYRIGHT_EN);
//            cskh = getContentFooter(Constants.CONFIG_SYS.CONFIG_FOOTER_SUPPORT_EN);
//        }

        VerticalLayout footer = new VerticalLayout();

        footer.setMargin(true);
        footer.setWidth("100%");
        GridLayout gridFooter = new GridLayout();
        gridFooter.setMargin(true);
        gridFooter.setSpacing(true);
        gridFooter.setColumns(2);
        footer.addComponent(gridFooter);
        footer.setComponentAlignment(gridFooter, Alignment.MIDDLE_CENTER);
        footer.setStyleName("backColor");
        Label label1 = new Label(copyright, ContentMode.HTML);
        Label label2 = new Label(cskh, ContentMode.HTML);
        label1.setStyleName("v-label-white");
        label2.setStyleName("v-label-white");
        gridFooter.addComponent(label1);
        gridFooter.addComponent(label2);
        return footer;
    }
}
