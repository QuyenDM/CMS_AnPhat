package com.cms.ui;

import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.JavaScript;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.ClientConnector;
import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ConnectorTracker;
import com.vaadin.ui.UI;
import static com.vaadin.ui.UI.getCurrent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.cms.dashboard.DashboardMenu;
import com.cms.dto.StaffDTO;
import com.cms.event.DashboardEvent.BrowserResizeEvent;
import com.cms.event.DashboardEvent.CloseOpenWindowsEvent;
import com.cms.event.DashboardEvent.UserLoggedOutEvent;
import com.cms.event.DashboardEvent.UserLoginRequestedEvent;
import com.cms.event.DashboardEventBus;
import com.cms.utils.BundleUtils;
import com.cms.utils.Constants;
import com.cms.view.LoginView;
import com.vaadin.annotations.Push;
import com.vaadin.shared.ui.ui.Transport;
import java.util.Locale;
import javax.servlet.ServletException;

/**
 *
 */
@Theme("tests-valo")
@Push(transport = Transport.LONG_POLLING)
@Widgetset("com.vcs.ui.MyAppWidgetset")
@Title("Hệ thống quản lý khách hàng - Anphat ditech")
@JavaScript({"vaadin://js/jquery-2.1.3.js", "vaadin://js/myjs.js"})
//@PreserveOnRefresh
public class MyUI extends UI {

    private boolean signOk = false;
    private ConnectorTracker tracker;

    @Override
    public ConnectorTracker getConnectorTracker() {
        if (this.tracker == null) {
            this.tracker = new ConnectorTracker(this) {

                @Override
                public void registerConnector(ClientConnector connector) {
                    try {
                        super.registerConnector(connector);
                    } catch (RuntimeException e) {
                        System.out.println("Failed connector: " + connector.getClass().getSimpleName());
//                        throw e;
                    }
                }

            };
        }

        return tracker;
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyUI.class, widgetset = "com.vcs.ui.MyAppWidgetset")
    public static class Servlet extends VaadinServlet {

        protected String sessionExpiredURL = null;
        protected boolean sessionExpiredNotificationEnabled = true;
        protected String sessionExpiredCaption = "Phiên làm việc hết hạn";
        protected String sessionExpiredMessage = "Phiên làm việc của bạn đã hết, click <u>ở đây</u> hoặc ấn ESC để tiếp tục.";

        protected String communicationErrorURL = null;
        protected boolean communicationErrorNotificationEnabled = true;
        protected String communicationErrorCaption = "Giao tiếp bị lỗi";
        protected String communicationErrorMessage = "Liên hệ quản trị để biết thêm chi tiết, click <u>ở đây</u> ấn ESC để tiếp tục.";

        protected String authenticationErrorURL = null;
        protected boolean authenticationErrorNotificationEnabled = true;
        protected String authenticationErrorCaption = "Xác thực bị lỗi";
        protected String authenticationErrorMessage = "Liên hệ quản trị để biết thêm chi tiết, click <u>ở đây</u> ấn ESC để tiếp tục.";

        protected String internalErrorURL = null;
        protected boolean internalErrorNotificationEnabled = true;
        protected String internalErrorCaption = "Lỗi";
        protected String internalErrorMessage = "Liên hệ quản trị để biết thêm chi tiết, click <u>ở đây</u> ấn ESC để tiếp tục.";

        protected String outOfSyncURL = null;
        protected boolean outOfSyncNotificationEnabled = true;
        protected String outOfSyncCaption = "Lỗi đồng bộ";
        protected String outOfSyncMessage = "Liên hệ quản trị để biết thêm chi tiết, click <u>ở đây</u> ấn ESC để tiếp tục.";

        protected String cookiesDisabledURL = null;
        protected boolean cookiesDisabledNotificationEnabled = true;
        protected String cookiesDisabledCaption = "Lỗi Cookies";
        protected String cookiesDisabledMessage = "Liên hệ quản trị để biết thêm chi tiết.<br/>Hãy kích hoạt Cookies của trình duyệt <u>click ở đây</u> hoặc ấn ESC để thử lại.";

        @Override
        protected void servletInitialized() throws ServletException {
            super.servletInitialized();

            getService().setSystemMessagesProvider(
                    new SystemMessagesProvider() {
                @Override
                public SystemMessages getSystemMessages(
                        SystemMessagesInfo systemMessagesInfo) {
                    CustomizedSystemMessages messages
                            = new CustomizedSystemMessages();
                    // Communication Error
                    messages.setCommunicationErrorCaption(communicationErrorCaption);
                    messages.setCommunicationErrorMessage(communicationErrorMessage);
                    messages.setCommunicationErrorNotificationEnabled(communicationErrorNotificationEnabled);
                    messages.setCommunicationErrorURL(communicationErrorURL);
                    // Session Expired
                    messages.setSessionExpiredCaption(sessionExpiredCaption);
                    messages.setSessionExpiredMessage(sessionExpiredMessage);
                    messages.setSessionExpiredNotificationEnabled(sessionExpiredNotificationEnabled);
                    messages.setSessionExpiredURL(sessionExpiredURL);
                    //authenticationError
                    messages.setAuthenticationErrorCaption(authenticationErrorCaption);
                    messages.setAuthenticationErrorMessage(authenticationErrorMessage);
                    messages.setAuthenticationErrorNotificationEnabled(authenticationErrorNotificationEnabled);
                    messages.setAuthenticationErrorURL(authenticationErrorURL);
                    //internalError
                    messages.setInternalErrorCaption(internalErrorCaption);
                    messages.setInternalErrorMessage(internalErrorMessage);
                    messages.setInternalErrorNotificationEnabled(internalErrorNotificationEnabled);
                    messages.setInternalErrorURL(internalErrorURL);
                    //outOfSync
//                    messages.setOutOfSyncCaption(outOfSyncCaption);
//                    messages.setOutOfSyncMessage(outOfSyncMessage);
//                    messages.setOutOfSyncNotificationEnabled(outOfSyncNotificationEnabled);
//                    messages.setOutOfSyncURL(outOfSyncURL);
                    //cookiesDisabled
                    messages.setCookiesDisabledCaption(cookiesDisabledCaption);
                    messages.setCookiesDisabledMessage(cookiesDisabledMessage);
                    messages.setCookiesDisabledNotificationEnabled(cookiesDisabledNotificationEnabled);
                    messages.setCookiesDisabledURL(cookiesDisabledURL);

                    return messages;
                }

//                @Override
//                public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                }
            });

//            getService().addSessionInitListener(
//                    new ValoThemeSessionInitListener());
        }
    }
    VerticalLayout main = new VerticalLayout();
// private final DataProvider dataProvider = new DummyDataProvider();
    private final DashboardEventBus dashboardEventbus = new DashboardEventBus();
//    public static EmployeeDTO user=new EmployeeDTO();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        if (VaadinSession.getCurrent().getSession().getAttribute("locale") == null) {
            VaadinSession.getCurrent().getSession().setAttribute("locale", new Locale("vi"));
        }
        setLocale(new Locale("vi"));
//        Page.getCurrent().setTitle(BundleUtils.getStringCas("title.vsc"));
        VaadinSession.getCurrent().getSession().setMaxInactiveInterval(Constants.TIMEOUT);
        DashboardEventBus.register(this);
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.UI_WITH_MENU);

        updateContent();

        // Some views need to be aware of browser resize events so a
        // BrowserResizeEvent gets fired to the event bus on every occasion.
        Page.getCurrent().addBrowserWindowResizeListener(
                new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(
                    final Page.BrowserWindowResizeEvent event) {
                DashboardEventBus.post(new BrowserResizeEvent());
            }
        });
    }

    /**
     * Updates the correct content for this UI based on the current user status.
     * If the user is logged in with appropriate privileges, main view is shown.
     * Otherwise login view is shown.
     */
    private void updateContent() {
        StaffDTO staff;
        if ("true".equalsIgnoreCase(BundleUtils.getStringCas("firstTime"))) {
            staff = new StaffDTO("1", "1", "quyendm", "Dang Manh Quyen", "11/11/2011", "11/11/2011",
                    "11/11/2011", "quyendm.ptit@gmail.com", "1", "Admin", "1", "1111", "CarNo");
            VaadinSession.getCurrent().setAttribute("staff", staff);
        } else {
            staff = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");
        }

        if (staff != null) {
            // Authenticated user
            setContent(new DashboardMenu());
            removeStyleName("loginview");
            getNavigator().navigateTo(getNavigator().getState());
//            getNavigator().navigateTo("home.vt");
        } else {
            //Sau nay dung login view
            setContent(new LoginView());
            addStyleName("loginview");
        }
    }

    @Subscribe
    public void userLoginRequested(final UserLoginRequestedEvent event) {
        signOk = true;
        updateContent();
    }

    @Subscribe
    public void userLoggedOut(final UserLoggedOutEvent event) {
        VaadinSession.getCurrent().close();
        Page.getCurrent().reload();
    }

    @Subscribe
    public void closeOpenWindows(final CloseOpenWindowsEvent event) {
        for (Window window : getWindows()) {
            window.close();
        }
    }

    /**
     * @return An instance for accessing the (dummy) services layer.
     */
//    public static DataProvider getDataProvider() {
//        return ((MyUI) getCurrent()).dataProvider;
//    }
//
    public static DashboardEventBus getDashboardEventbus() {
        return ((MyUI) getCurrent()).dashboardEventbus;
    }
}
