/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.utils;

import com.vaadin.server.VaadinSession;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author thienkq1@viettel.com.vn
 * @since 12,Apr,2010
 * @version 1.0
 */
public class CasBundleUtils {

    /**
     * rb.
     */
    private static ResourceBundle rb = null;

    /**
     * Creates a new instance of ResourceBundleUtils
     */
    private CasBundleUtils() {
    }

    /**
     * method get resource
     *
     * @param key String
     * @return String
     */
    public static String getResourceDefault(String key) {
        try {
            rb = ResourceBundle.getBundle("cas");
            String str = rb.getString(key);
            if (DataUtil.isStringNullOrEmpty(str)) {
                return key;
            } else {
                return str;
            }
        } catch (Exception e) {
            return key;
        }
    }

    public static String getResource(String key, Locale... locale) {
        Locale mlocale = (Locale) VaadinSession.getCurrent().getSession().getAttribute("locale");
        try {
            if (locale != null) {
                if (locale.length == 0) {
                    rb = ResourceBundle.getBundle(Constants.CAS, mlocale);
                } else {
                    rb = ResourceBundle.getBundle(Constants.CAS, locale[0]);
                }
            } else {
                rb = ResourceBundle.getBundle(Constants.CAS, new Locale(getResourceDefault("defaultLanguage")));
            }

            String str = rb.getString(key);
            if (DataUtil.isStringNullOrEmpty(str)) {
                return key;
            } else {
                return str;
            }
        } catch (Exception e) {
            return key;
        }

    }
}
