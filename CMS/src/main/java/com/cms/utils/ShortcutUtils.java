/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import com.vaadin.data.Item;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutAction.ModifierKey;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import com.cms.component.CustomPageTable;
import com.cms.component.CustomPageTableFilter;

/**
 *
 * @author hungkv
 */
public class ShortcutUtils {

    public static FieldEvents setFocus(TextField component) {
        component.focus();
        return null;
    }

    public static ShortcutAction setShortcutKey(Button button) {
        button.setClickShortcut(KeyCode.ENTER);
        return null;
    }

    public static ShortcutAction setShortkeyCopy(Button button) {
        button.setClickShortcut(KeyCode.P, ModifierKey.ALT);
        return null;
    }

    public static ShortcutAction setShortkeyEdit(Button button) {
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER, ModifierKey.ALT);
        return null;
    }

    public static ShortcutAction setShortkeyDelete(Button button) {
        button.setClickShortcut(KeyCode.DELETE);
        return null;
    }

    public static ShortcutAction setShortkeyF2(Button button) {
        button.setClickShortcut(ShortcutAction.KeyCode.F2);
        return null;
    }

    public static ShortcutAction setShortkeyALT1(Button button) {
        button.setClickShortcut(ShortcutAction.KeyCode.NUM1, ModifierKey.ALT);
        return null;
    }

    public static ShortcutAction setShortkeyAddNew(Button button) {
        button.setClickShortcut(KeyCode.INSERT);
        return null;
    }

    public static void setTooltipForButton(Button button, String field) {

        button.setDescription(field);
    }

    public static ShortcutAction setQuit(final Button btnClose) {
        new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Button button = (Button) event.getSource();
                if (button.equals(btnClose)) {
                    button.setClickShortcut(KeyCode.ESCAPE);
                }
            }
        };
        return null;
    }

    public static void doTextChangeUppercase(final TextField field) {
        field.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                try {
                    field.setValue(event.getText().toUpperCase());
                    // workaround cursor position problem
                    field.setCursorPosition(event.getCursorPosition());
                    field.validate();
                } catch (InvalidValueException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void doTextChangePhoneNumber(final TextField field) {
        field.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                try {
                    String phone;

                    if (field.getValue().length() < 10) {
                        field.setBuffered(true);
                        phone = event.getText();
                        field.setValue(phone);
                    }
                    if (field.getValue().length() >= 10) {
                        field.setCursorPosition(event.getCursorPosition());
                        phone = convertText2PhoneFormat(event.getText());
                        field.setValue(phone);
                    }
                    if (field.getValue().length() == 11) {
                        field.setCursorPosition(event.getCursorPosition());
                        phone = convertText2PhoneFormat(event.getText());
                        field.setValue(phone);
                    }
                    field.validate();
                } catch (InvalidValueException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static String convertText2PhoneFormat(String number) {
        String phoneNumber = "";
        if (number != null) {
            number.trim();
            if (number.length() == 10) {
                //nhom theo dinh dang 3-3-4
                phoneNumber = number.substring(0, 3) + " " + number.substring(3, 6) + " " + number.substring(6);
            }
            //nhom theo 4-3-4
            if (number.length() == 11) {
                phoneNumber = number.substring(0, 4) + " " + number.substring(4, 7) + " " + number.substring(7);
            }
            if (number.length() > 11 || number.length() < 10) {
                return number;
            }
        }
        return phoneNumber;
    }

    public static StringLengthValidator checkLength(String value) {
        if (value.length() < 10) {
            return new StringLengthValidator("common.error.phone.length");
        }
        if (value.length() > 11) {
            return new StringLengthValidator("common.error.phone.length");
        }
        return null;
    }

    public static void setVisibleTextfield(TextField field, boolean ok) {
        if (field.getValue() != null) {
            field.setEnabled(ok);
        }
    }

    public static void setTooltipForFields(final CustomPageTable table, final String[] field) {
        table.setItemDescriptionGenerator(new AbstractSelect.ItemDescriptionGenerator() {

            @Override
            public String generateDescription(Component source, Object itemId, Object propertyId) {
                Item row = table.getItem(itemId);
                if (field != null) {
                    for (int i = 0; i < field.length; i++) {
                        if (propertyId == field[i] && row.getItemProperty(field[i]).getValue() != null) {
                            return row.getItemProperty(field[i]).getValue().toString();
                        }
                    }
                }
                return null;
            }
        });
    }

    public static void setTooltipForFields(final CustomPageTableFilter<IndexedContainer> table, final String[] field) {
        table.setItemDescriptionGenerator(new AbstractSelect.ItemDescriptionGenerator() {

            @Override
            public String generateDescription(Component source, Object itemId, Object propertyId) {
                Item row = table.getItem(itemId);
                if (field != null) {
                    for (int i = 0; i < field.length; i++) {
                        try {
                            if (propertyId == field[i] && row.getItemProperty(field[i]).getValue() != null) {
                                return row.getItemProperty(field[i]).getValue().toString();
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                return null;
            }
        });
    }

    public static void doTextTrim(final TextField field) {
        field.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                try {
                    field.setValue(event.getText().trim());
                    // workaround cursor position problem
                    field.setCursorPosition(event.getCursorPosition());
                    field.validate();
                } catch (InvalidValueException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
