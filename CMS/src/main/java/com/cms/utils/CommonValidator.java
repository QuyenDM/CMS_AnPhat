/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author hungkv
 */
public class CommonValidator {

    //=====set maxlength for field====
    public static boolean isTrueMaxLength(String value, int minLength, int maxlength) {
        if (value.length() <= maxlength && value.length() > minLength) {
            return true;
        } else {
            return false;
        }
    }

    public static void LengthValidator(final TextField textField, final String message, final int minLength, final int maxLength, final boolean allowNull) {
        textField.setImmediate(true);
        textField.setRequired(false);
        textField.addTextChangeListener(new FieldEvents.TextChangeListener() {

            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                int lengthField = event.getText().length();
                StringLengthValidator lengthValidate = new StringLengthValidator(message, minLength, maxLength, allowNull);
                if (lengthField > maxLength) {
                    textField.setCursorPosition(event.getCursorPosition());
                    textField.addValidator(lengthValidate);
                    if (textField.getValidators().size() > 1) {
                        for (Validator obj : textField.getValidators()) {
                            textField.removeValidator(obj);
                        }
                    }
                    textField.focus();
                }
                if (textField.isEmpty()) {
                    textField.removeAllValidators();
                }
                textField.commit();
//                textField.removeValidator(lengthValidate);
            }
        });

    }

    //Validate so
    public static Boolean validateNumber(String str) {
        String regex = "[0-9]+";
        return str.matches(regex);
    }

    //validate email
    public static Boolean validateEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean validateNumber(TextField field, String message) {
        try {
            field.validate();
        } catch (Validator.InvalidValueException e) {
            field.focus();
            Notification.show(message);
            return false;
        }
        return true;
    }

    //valid email
    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        if ("".equalsIgnoreCase(email)) {
            return true;
        }else{
            //initialize regex for email
            String expPattern = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            CharSequence inputStr = email;
            Pattern pattern = Pattern.compile(expPattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputStr);
            if (matcher.matches()) {
                isValid = true;
            }
        }
        return isValid;
    }

    public static boolean isNumberCharSpaceValid(String text) {
        boolean isValid = false;
        //initialize regex for email
        String expPattern = "^[a-zA-Z0-9 _-]+$";
        CharSequence inputStr = text;
        Pattern pattern = Pattern.compile(expPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isNumberCharValid(String text) {
        boolean isValid = false;
        //initialize regex for email
        String expPattern = "^[a-zA-Z0-9_-]+$";
        CharSequence inputStr = text;
        Pattern pattern = Pattern.compile(expPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    //valid email
    public static boolean isPhoneValid(String phone) {
        boolean isValid = false;
        if ("".equalsIgnoreCase(phone)) {
            return true;
        }else{
            //initialize regex for email
            String expPattern = "^\\(?(\\d{3,4})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
            CharSequence inputStr = phone;
            Pattern pattern = Pattern.compile(expPattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputStr);
            if (matcher.matches()) {
                isValid = true;
            }
        }
        return isValid;
    }

    public static boolean checkMaxlength(TextField field, int length, boolean allowNull) {
        if (allowNull == true) {
            return false;
        } else {
            if (field.getValue().trim().length() > length) {
                Notification.show(BundleUtils.getString("message.error.overlengthString"), Notification.Type.WARNING_MESSAGE);
                field.focus();
                return true;
            }
        }
        return false;
    }

    public static boolean checkMaxlength(TextArea field, int length, boolean allowNull) {
        if (allowNull == true) {
            return false;
        } else {
            if (field.getValue().trim().length() > length) {
                Notification.show(BundleUtils.getString("message.error.overlengthString"), Notification.Type.WARNING_MESSAGE);
                field.focus();
                return true;
            }
        }
        return false;
    }

    public static Boolean validateNumber(TextField field) {
        String regex = "[0-9]+";
        String str = field.getValue().trim();
        if (str.matches(regex)) {
            return true;
        }
        return false;
    }

    public static Boolean validateTextField(TextField field, int length, boolean allowNull) {
        String text = field.getValue().trim();
        if (!isNumberCharSpaceValid(text)) {
            return true;
        } else if (checkMaxlength(field, length, allowNull)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValueInteger(String s) {
        if ("".equalsIgnoreCase(s)) {
            return true;
        }else{
            try {
                Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return false;//For example "bhushan" 
            }
        }
        return true;// For example "21" 
    }
}
