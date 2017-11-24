/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import com.vaadin.event.FieldEvents;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import java.util.Locale;

/**
 *
 * @author thieulq1
 */
public class InitDateTime extends CustomComponent {

    public HorizontalLayout date = new HorizontalLayout();

    public InitDateTime() {
        date.setWidth("100.0%");
        date.setHeight("-1px");
        final PopupDateField popupDateField = new PopupDateField();
        float dateWidth = date.getWidth() - 14;
        popupDateField.setWidth(String.valueOf(dateWidth));
        popupDateField.setId("date");
//        popupDateField.addStyleName("v-textfield v-widget v-textfield-required v-required v-has-width");
        popupDateField.setLocale(new Locale("vi"));
        popupDateField.setImmediate(false);
        popupDateField.setValidationVisible(false);
        popupDateField.addBlurListener(new FieldEvents.BlurListener() {
            @Override
            public void blur(FieldEvents.BlurEvent event) {
                com.vaadin.ui.JavaScript.getCurrent().execute("setValueDate();");
            }
        });
        popupDateField.addFocusListener(new FieldEvents.FocusListener() {

            @Override
            public void focus(FieldEvents.FocusEvent event) {
                com.vaadin.ui.JavaScript.getCurrent().execute("focusDate();");
            }
        });
        Label label = new Label("<div id=\"error-date\" class=\"v-errorindicator\" onmouseover=\"showError()\" onmouseout=\"hideError()\" aria-hidden=\"true\" style=\"display: none;\">&nbsp;</div>", ContentMode.HTML);
        label.setId("label");
//        label.addStyleName("v-required-field-indicator");
        label.setWidth("14px");
//        label.setWidth("10px");
//        label.setHeight("10px");
        label.setDescription("<span id=\"notification-date\" style=\" background:#fff;color:red;padding:0\">Ngày tháng chưa đúng định dạng</span>");
        date.addComponent(popupDateField);
//        Page.getCurrent().getJavaScript().execute("addCsstotip()");
        date.addComponent(label);
        date.setExpandRatio(popupDateField, 3);
        date.setExpandRatio(label, 1);
//        layoutMain.addComponent(date);
    }
}
