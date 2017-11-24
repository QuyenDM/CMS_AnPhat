/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import static com.cms.utils.CasBundleUtils.getResourceDefault;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author TiepNV6
 */
public class BundleUtils {

//    public static final Locale locale = (Locale) VaadinSession.getCurrent().getSession().getAttribute("locale");
    private static final String BUSINESS_CONFIG = "businessconfig";
    private static final String CONTRAINT = "AppParamsContrants";
    //contraints table type --pricefactor--
    private static final String CONTRAINT_TABLE = "DataTypeContrants";
    private static final String SEPARATOR = "#";
    private static ResourceBundle rsConfig = null;
    private static Map<Integer, Table.Align> mapAlign;
    private static Map<Integer, CustomTable.Align> mapAligns;

    //mapAlign giup thuc hien set align cho cac cot trong bang
    //voi gia tri tuong ung: 1: can trai, 2: can phai, 3: can giua
    static {
        mapAligns = new HashMap<>();
        mapAligns.put(1, CustomTable.Align.LEFT);
        mapAligns.put(2, CustomTable.Align.CENTER);
        mapAligns.put(3, CustomTable.Align.RIGHT);
        mapAlign = new HashMap<>();
        mapAlign.put(1, Table.Align.LEFT);
        mapAlign.put(2, Table.Align.CENTER);
        mapAlign.put(3, Table.Align.RIGHT);
    }

    public static String getString(String key, Locale... locale) {
        try {
            if (locale != null) {
                if (locale.length == 0) {
                    Locale mlocale = (Locale) VaadinSession.getCurrent().getSession().getAttribute("locale");
                    rsConfig = ResourceBundle.getBundle(Constants.LANGUAGE, mlocale);
                } else {
                    rsConfig = ResourceBundle.getBundle(Constants.LANGUAGE, locale[0]);
                }
            } else {
                rsConfig = ResourceBundle.getBundle(Constants.LANGUAGE, new Locale(getResourceDefault("defaultLanguage")));
            }
            return rsConfig.getString(key);
        } catch (Exception e) {
            return key;
        }
    }

    public static String getStringCas(String key) {
        rsConfig = ResourceBundle.getBundle(Constants.CAS);
        return rsConfig.getString(key);
    }

    public static String getConfigString(String key, Locale... locale) {
        if (locale == null) {
            rsConfig = ResourceBundle.getBundle(Constants.CAS, locale[0]);
        } else {
            rsConfig = ResourceBundle.getBundle(Constants.CAS, Locale.ENGLISH);
        }
        return rsConfig.getString(key);
    }

    public static String getConfigContraint(String key, Locale... locale) {
        if (locale == null) {
            rsConfig = ResourceBundle.getBundle(CONTRAINT, locale[0]);
        } else {
            rsConfig = ResourceBundle.getBundle(CONTRAINT, Locale.ENGLISH);
        }
        return rsConfig.getString(key);
    }

    //contraints data type
    public static String getConfigTableContraint(String key, Locale... locale) {
        if (locale == null) {
            rsConfig = ResourceBundle.getBundle(CONTRAINT_TABLE, locale[0]);
        } else {
            rsConfig = ResourceBundle.getBundle(CONTRAINT_TABLE, Locale.ENGLISH);
        }
        return rsConfig.getString(key);
    }

    public static LinkedHashMap<String, Table.Align> getHeaders(String key) {
        String headerList = BundleUtils.getConfigString(key);
        String[] headers = headerList.split(",");
        LinkedHashMap<String, Table.Align> headerData = new LinkedHashMap<String, Table.Align>();
        for (int i = 0; i < headers.length; i++) {
            String[] headerDataType = headers[i].split(SEPARATOR);
            headerData.put(headerDataType[0], mapAlign.get(Integer.parseInt(headerDataType[1])));
        }
        return headerData;
    }

    //Modifier NgocND6
    public static LinkedHashMap<String, CustomTable.Align> getHeadersFilter(String key) {
        String headerList = BundleUtils.getStringCas(key);
        String[] headers = headerList.split(",");
        LinkedHashMap<String, CustomTable.Align> headerData = new LinkedHashMap<String, CustomTable.Align>();
        for (int i = 0; i < headers.length; i++) {
            String[] headerDataType = headers[i].split(SEPARATOR);
            headerData.put(headerDataType[0], mapAligns.get(Integer.parseInt(headerDataType[1])));
        }
        return headerData;
    }

    static Page getString(String string, Notification.Type type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
