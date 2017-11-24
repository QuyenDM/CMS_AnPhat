package com.cms.view;

import com.cms.login.dto.ObjectsDTO;
import com.cms.login.ws.WSObjects;
import com.cms.login.ws.WSStaff;
import com.google.common.collect.Lists;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;
import com.vaadin.ui.themes.ValoTheme;
import com.cms.dto.StaffDTO;
import com.cms.event.DashboardEvent;
import com.cms.event.DashboardEventBus;
import com.cms.ui.ChangPasswordUI;
import com.cms.utils.BundleUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.vwf5.base.utils.ConditionBean;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class LoginView extends VerticalLayout implements View {

//    private Label lblUsername;
//    private Label lblPassword;
    private ChangPasswordUI changPasswordUI;
    private List<ObjectsDTO> lstObjecsDTO;

    public LoginView() {
        setSizeFull();
        VerticalLayout loginView = new VerticalLayout();
        loginView.setSizeFull();
        loginView.addStyleName("login-layout");
        addComponent(loginView);
        Component loginForm = buildLoginForm();
        loginForm.setWidth("25%");
        loginView.addComponent(loginForm);
        loginView.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

//        Notification notification = new Notification(
//                "Welcome to Dashboard Demo");
//        notification
//                .setDescription("<span>This application is not real, it only demonstrates an application built with the <a href=\"https://vaadin.com\">Vaadin framework</a>.</span> <span>No username or password is required, just click the <b>Sign In</b> button to continue.</span>");
//        notification.setHtmlContentAllowed(true);
//        notification.setStyleName("tray dark small closable login-help");
//        notification.setPosition(Position.BOTTOM_CENTER);
//        notification.show(Page.getCurrent());
    }

    private Component buildLoginForm() {
        final VerticalLayout loginPanel = new VerticalLayout();
        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        loginPanel.setMargin(true);
        Responsive.makeResponsive(loginPanel);
        loginPanel.addStyleName("login-panel");
//        Component logo = buildLogo();
//        loginPanel.addComponent(logo);
//        loginPanel.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        loginPanel.addComponent(buildLabels());
        loginPanel.addComponent(buildFields());
        loginPanel.addComponent(buildButtonLayout());
        return loginPanel;
    }

    private Component buildLogo() {
        CssLayout logo = new CssLayout();
        ThemeResource resourceLogo = new ThemeResource("../dashboard/img/logo_anphat.jpg");
        Image imgLogo = new Image(null, resourceLogo);
        imgLogo.setWidth("180px");
        logo.addComponent(imgLogo);
        return logo;
    }

    private Component buildFields() {
        VerticalLayout fields = new VerticalLayout();
        fields.setSpacing(true);
        fields.addStyleName("fields");
//        lblUsername = new Label(BundleUtils.getString(Constants.USERNAME));
//        lblUsername.setImmediate(false);
//        lblUsername.setWidth("50.0%");
//        lblUsername.setHeight("-1px");
        final TextField username = new TextField(BundleUtils.getString(Constants.USERNAME));
        username.setRequired(true);
//        username.setRequiredError(BundleUtils.getString("login.username.require"));
        username.setId("username");
//        username.setIcon(FontAwesome.USER);
        username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
//        lblPassword = new Label(BundleUtils.getString(Constants.PASS));
//        lblPassword.setImmediate(false);
//        lblPassword.setWidth("50.0%");
//        lblPassword.setHeight("-1px");
        final PasswordField password = new PasswordField(BundleUtils.getString(Constants.PASS));
        password.setRequired(true);
//        password.setRequiredError(BundleUtils.getString("login.password.require"));
        username.setId("password");
//        password.setIcon(FontAwesome.LOCK);
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

//        GridOneButton buttonLayout = new GridOneButton(BundleUtils.getString(Constants.SIGNIN));
        final Button signin = new Button(BundleUtils.getString(Constants.SIGNIN), FontAwesome.USER);
        signin.setWidth("100%");
        signin.setPrimaryStyleName("sign-in-button");
        signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
        signin.setClickShortcut(KeyCode.ENTER);

        username.focus();

        fields.addComponents(username, password, signin);
        fields.setComponentAlignment(signin, Alignment.MIDDLE_CENTER);

        signin.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                if (DataUtil.isStringNullOrEmpty(username.getValue())) {
                    username.setRequiredError(BundleUtils.getString("login.username.require"));
                    username.focus();
                } else if (DataUtil.isStringNullOrEmpty(password.getValue())) {
                    password.setRequiredError(BundleUtils.getString("login.password.require"));
                    password.focus();
                } else {
//                    Boolean check = checkLogIn(username.getValue(), password.getValue());
                    StaffDTO staff = getStaff(username.getValue(), password.getValue());
                    if (staff == null){
                        Notification.show(BundleUtils.getString("notification.login"));
                    } else {                        
                        if(Constants.DEACTIVE.equals(staff.getStatus())){
                            Notification.show(BundleUtils.getString("account.disabled"));
                        } else {
//                            List<ObjectsDTO> lstFunctions = new ArrayList<>();
                            getListObjects(staff);
                            VaadinSession.getCurrent().setAttribute("staff", staff);
                            VaadinSession.getCurrent().setAttribute("lstFunctions", lstObjecsDTO);
                            DashboardEventBus.post(new DashboardEvent.UserLoginRequestedEvent(username
                                .getValue(), password.getValue()));
                        }
                    }
                }
            }
        });
        return fields;
    }

    private StaffDTO getStaff(String username, String password) {
        StaffDTO staffLogined = null;
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setCode(username.toUpperCase());
        staffDTO.setPassword(DataUtil.md5(password));
        List<StaffDTO> lstStaffDTO = new ArrayList<>();
        List<ObjectsDTO> lstFunctions = new ArrayList<>();
        List<ConditionBean> lstConditionBean = Lists.newArrayList();
        ConditionBean conditionBean = new ConditionBean();
        conditionBean.setField("code");
        conditionBean.setValue(username.toUpperCase());
        conditionBean.setOp(ConditionBean.Operator.NAME_EQUAL);
        conditionBean.setType(ConditionBean.Type.STRING);
        lstConditionBean.add(conditionBean);

        ConditionBean conditionBean1 = new ConditionBean();
        conditionBean1.setField("password");
        conditionBean1.setValue(DataUtil.md5(password));
        conditionBean1.setOp(ConditionBean.Operator.NAME_EQUAL);
        conditionBean1.setType(ConditionBean.Type.STRING);
        lstConditionBean.add(conditionBean1);
        try {
            lstStaffDTO = WSStaff.getListStaffByCondition(lstConditionBean, 0, Integer.MAX_VALUE, "", "code");
            if (lstStaffDTO.size() == 1) {
                staffLogined = lstStaffDTO.get(0);                
            }
        } catch (Exception e) {

        }
        return staffLogined;
    }

    private Component buildButtonLayout() {
        VerticalLayout buttonLayout = new VerticalLayout();
        Button btnChangPassword = new Button(BundleUtils.getString("btn.changePassword"));
        btnChangPassword.addStyleName(Runo.BUTTON_LINK);
        btnChangPassword.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                changPasswordUI = new ChangPasswordUI();
                UI.getCurrent().addWindow(changPasswordUI);
            }
        });
        buttonLayout.addComponent(btnChangPassword);
        buttonLayout.setComponentAlignment(btnChangPassword, Alignment.MIDDLE_RIGHT);
        return buttonLayout;
    }

    private Component buildLabels() {
        CssLayout labels = new CssLayout();
        labels.addStyleName("labels");

        Label welcome = new Label("quản lý khách hàng");
        welcome.setSizeUndefined();
        welcome.setPrimaryStyleName("label-login-wellcome");
//        welcome.addStyleName(ValoTheme.LABEL_H4);
//        welcome.addStyleName(ValoTheme.LABEL_COLORED);
        labels.addComponent(welcome);

        Label title = new Label("ANPHAT DITECH.,JSC", ContentMode.HTML);
        title.setSizeUndefined();
        title.setPrimaryStyleName("label-name-company");
//        title.addStyleName(ValoTheme.LABEL_H3);
//        title.addStyleName(ValoTheme.LABEL_COLORED);
        labels.addComponent(title);
        return labels;
    }

    public Boolean checkLogIn(String username, String password) {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setCode(username.toUpperCase());
        staffDTO.setPassword(DataUtil.md5(password));
        List<StaffDTO> lstStaffDTO = new ArrayList<>();
        List<ObjectsDTO> lstFunctions = new ArrayList<>();
        List<ConditionBean> lstConditionBean = Lists.newArrayList();
        ConditionBean conditionBean = new ConditionBean();
        conditionBean.setField("code");
        conditionBean.setValue(username.toUpperCase());
        conditionBean.setOp(ConditionBean.Operator.NAME_EQUAL);
        conditionBean.setType(ConditionBean.Type.STRING);
        lstConditionBean.add(conditionBean);

        ConditionBean conditionBean1 = new ConditionBean();
        conditionBean1.setField("password");
        conditionBean1.setValue(DataUtil.md5(password));
        conditionBean1.setOp(ConditionBean.Operator.NAME_EQUAL);
        conditionBean1.setType(ConditionBean.Type.STRING);
        lstConditionBean.add(conditionBean1);
        try {
            lstStaffDTO = WSStaff.getListStaffByCondition(lstConditionBean, 0, Integer.MAX_VALUE, "", "code");
            if (lstStaffDTO.size() == 1) {
                StaffDTO staffLogined = lstStaffDTO.get(0);
                getListObjects(staffLogined);
                VaadinSession.getCurrent().setAttribute("staff", staffLogined);
                VaadinSession.getCurrent().setAttribute("lstFunctions", lstFunctions);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    //Lay danh sach chuc nang ung voi user dang nhap
    private void getListObjects(StaffDTO staff) {
        try {
            lstObjecsDTO = WSObjects.getListObjectsDTO(new ObjectsDTO(), 0, 100, "asc", "name");
        } catch (Exception e) {
            lstObjecsDTO = new ArrayList<>();
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
