/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.ui;

import com.cms.login.ws.WSStaff;
import com.google.common.collect.Lists;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.cms.component.GridManyButton;
import com.cms.dto.StaffDTO;
//import com.vcs.Staff.ConditionBean;
//import com.vcs.Staff.StaffDTO;
//import com.vcs.Staff.StaffServiceImplService;
import com.cms.event.DashboardEvent;
import com.cms.event.DashboardEventBus;
import com.cms.utils.BundleUtils;
import com.cms.utils.DataUtil;
import com.vwf5.base.utils.ConditionBean;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANG THIEU
 */
public class ChangPasswordUI extends Window {

    private VerticalLayout mainLayout = new VerticalLayout();
    private TextField txtUserName;
    private PasswordField txtPasswordOld;
    private PasswordField txtPasswordNew;
    private PasswordField txtConfirmPasswordNew;
    private Button btnChange;
    private Button btnClose;
//    private ReCaptcha captcha;
    List<StaffDTO> lstStaffDTO = new ArrayList<>();
//    StaffServiceImplService serviedw = new StaffServiceImplService();

    public ChangPasswordUI() {
        mainLayout.setImmediate(true);
        mainLayout.setCaption(BundleUtils.getString("title.changPassword"));
        mainLayout.setWidth("100%");
        mainLayout.setHeight("-1px");
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setStyleName("main-popup");
        setWidth("30.0%");
        setHeight("-1px");
        setModal(true);

        txtUserName = new TextField();
        txtUserName.setWidth("100%");
        txtUserName.setHeight("-1px");
        txtUserName.setRequired(true);
        txtUserName.setCaption(BundleUtils.getString("userName"));
        txtUserName.setImmediate(false);
        mainLayout.addComponent(txtUserName);

        txtPasswordOld = new PasswordField();
        txtPasswordOld.setWidth("100%");
        txtPasswordOld.setHeight("-1px");
        txtPasswordOld.setRequired(true);
        txtPasswordOld.setCaption(BundleUtils.getString("passwordOld"));
        txtPasswordOld.setImmediate(false);
        mainLayout.addComponent(txtPasswordOld);

        txtPasswordNew = new PasswordField();
        txtPasswordNew.setWidth("100%");
        txtPasswordNew.setHeight("-1px");
        txtPasswordNew.setRequired(true);
        txtPasswordNew.setCaption(BundleUtils.getString("passwordNew"));
        txtPasswordNew.setImmediate(false);
        mainLayout.addComponent(txtPasswordNew);

        txtConfirmPasswordNew = new PasswordField();
        txtConfirmPasswordNew.setWidth("100%");
        txtConfirmPasswordNew.setHeight("-1px");
        txtConfirmPasswordNew.setRequired(true);
        txtConfirmPasswordNew.setCaption(BundleUtils.getString("passwordConfirm"));
        txtConfirmPasswordNew.setImmediate(false);
        mainLayout.addComponent(txtConfirmPasswordNew);

//        captcha = new ReCaptcha("6Lfv5OoSAAAAAPEbWhNB0ERopfQpRxr8_5yncOmg", "6Lfv5OoSAAAAAHa4zmExf6w2ja3vm-8ABKgyepq-",
//                new ReCaptchaOptions() {
//            {
//                theme = "white";
//            }
//        });
//        mainLayout.addComponent(captcha);
        GridManyButton gridButton
                = new GridManyButton(
                        new String[]{
                            BundleUtils.getString("btn.changePassword"),
                            BundleUtils.getString("common.button.cancel")});
        btnChange = gridButton.getBtnCommon().get(0);
//        btnChange.setWidth("100%");
//        btnChange.setHeight("-1px");
//        btnChange.setCaption(BundleUtils.getString("btn.changePassword"));
        btnChange.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                String userName = txtUserName.getValue();
                if (userName == null || userName.equals("")) {
                    Notification.show("Bạn chưa nhập tên tài khoản", Notification.Type.ERROR_MESSAGE);
                    return;
                }
                String passwordOld = txtPasswordOld.getValue();
                if (passwordOld == null || passwordOld.equals("")) {
                    Notification.show("Bạn chưa nhập mật khẩu cũ", Notification.Type.ERROR_MESSAGE);
                    return;
                }
                String passwordNew = txtPasswordNew.getValue();
                if (passwordNew == null || passwordNew.equals("")) {
                    Notification.show("Bạn chưa nhập mật khẩu mới", Notification.Type.ERROR_MESSAGE);
                    return;
                }
                String confirmPassword = txtConfirmPasswordNew.getValue();
                if (confirmPassword == null || confirmPassword.equals("")) {
                    Notification.show("Bạn chưa xác nhận mật khẩu mới", Notification.Type.ERROR_MESSAGE);
                    return;
                }
                if (!passwordNew.equals(confirmPassword)) {
                    Notification.show("Xác nhận chưa chính xác", Notification.Type.ERROR_MESSAGE);
                    return;
                }
                Boolean check = checkLogIn(userName, passwordOld);
                if (!check) {
                    Notification.show("Mật khẩu cũ chưa chính xác", Notification.Type.ERROR_MESSAGE);
                    return;
                }

                StaffDTO staff = lstStaffDTO.get(0);
                staff.setPassword(DataUtil.md5(passwordNew));
                String message = WSStaff.updateStaff(staff);
                if (!message.equals("SUCCESS")) {
                    Notification.show("Thay đổi không thành công", Notification.Type.ERROR_MESSAGE);
                    return;
                } else {
                    Notification.show("Thay đổi thành công");
                    close();
                    DashboardEventBus.post(new DashboardEvent.UserLoggedOutEvent());
                }

            }
        });
        btnChange.setImmediate(false);

//        buttonLayout.addComponent(btnChange);
        btnClose = gridButton.getBtnCommon().get(1);
//        btnChange.setWidth("100%");
//        btnClose.setHeight("-1px");
//        btnClose.setCaption(BundleUtils.getString("btn.close"));
        btnClose.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });
//        buttonLayout.addComponent(btnClose);
        mainLayout.addComponent(gridButton);
        mainLayout.setComponentAlignment(gridButton, Alignment.MIDDLE_CENTER);
        setContent(mainLayout);

    }

    public Boolean checkLogIn(String username, String password) {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setCode(username);
        staffDTO.setPassword(DataUtil.md5(password));
        lstStaffDTO = new ArrayList<>();

        List<ConditionBean> lstConditionBean = Lists.newArrayList();
        ConditionBean conditionBean = new ConditionBean();
        conditionBean.setField("code");
        conditionBean.setValue(username);
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
                VaadinSession.getCurrent().setAttribute("staff", lstStaffDTO.get(0));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public VerticalLayout getMainLayout() {
        return mainLayout;
    }

    public void setMainLayout(VerticalLayout mainLayout) {
        this.mainLayout = mainLayout;
    }

    public TextField getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(TextField txtUserName) {
        this.txtUserName = txtUserName;
    }

    public PasswordField getTxtPasswordOld() {
        return txtPasswordOld;
    }

    public void setTxtPasswordOld(PasswordField txtPasswordOld) {
        this.txtPasswordOld = txtPasswordOld;
    }

    public PasswordField getTxtPasswordNew() {
        return txtPasswordNew;
    }

    public void setTxtPasswordNew(PasswordField txtPasswordNew) {
        this.txtPasswordNew = txtPasswordNew;
    }

    public PasswordField getTxtConfirmPasswordNew() {
        return txtConfirmPasswordNew;
    }

    public void setTxtConfirmPasswordNew(PasswordField txtConfirmPasswordNew) {
        this.txtConfirmPasswordNew = txtConfirmPasswordNew;
    }

    public Button getBtnChange() {
        return btnChange;
    }

    public void setBtnChange(Button btnChange) {
        this.btnChange = btnChange;
    }

//    public ReCaptcha getCaptcha() {
//        return captcha;
//    }
//
//    public void setCaptcha(ReCaptcha captcha) {
//        this.captcha = captcha;
//    }
}
