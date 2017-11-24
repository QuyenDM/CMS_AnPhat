/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import org.vaadin.teemu.VaadinIcons;

/**
 *
 * @author phamthanh
 */
public class VcsIcon {

    public final static Resource EDIT = VaadinIcons.EDIT;
    public final static Resource SEARCH = FontAwesome.SEARCH;
    public final static Resource DELETE = FontAwesome.TIMES;
    public final static Resource TRASH = VaadinIcons.TRASH;
    public final static Resource CANCEL = FontAwesome.BAN;
    public final static Resource CHECK = FontAwesome.CHECK;
    public final static Resource ACCEPT = FontAwesome.CHECK;//
    public final static Resource REJECT = VaadinIcons.CLOSE;//
    public final static Resource CLOSE = FontAwesome.BAN;//
    public final static Resource ADD = FontAwesome.PLUS;
    public final static Resource REFRESH = FontAwesome.REFRESH;
    public final static Resource SAVE = FontAwesome.SAVE;
    public final static Resource SEND = VaadinIcons.PAPERPLANE;//
    public final static Resource VIEW = FontAwesome.FILE_TEXT_O;
    public final static Resource IMPORT = FontAwesome.FILE_EXCEL_O;
    public final static Resource DUBLICATE = VaadinIcons.PASTE;
    public final static Resource DOWNLOAD = FontAwesome.DOWNLOAD;
    public final static Resource CONFIG = VaadinIcons.COG;
    public final static Resource COPY = VaadinIcons.COPY;
    public final static Resource BAR_CHART = VaadinIcons.BAR_CHART;
    public final static Resource SPLIT = VaadinIcons.SPLIT;
    public final static Resource GROUP = VaadinIcons.GROUP;
    public final static Resource LOCK = VaadinIcons.LOCK;
    public final static Resource UNLOCK = VaadinIcons.UNLOCK;

    public final static Resource USER_CHECK = VaadinIcons.USER_CHECK;
    public final static Resource BELL = VaadinIcons.BELL;
    public final static Resource FILE_TREE = VaadinIcons.FILE_TREE;

    public final static Resource EXPORT = FontAwesome.FILE_EXCEL_O;//
    public final static Resource RESET = FontAwesome.REPEAT;//
    public final static Resource MINUS_CIRCLE = FontAwesome.MINUS_CIRCLE;
    public final static Resource SHIELD = VaadinIcons.CHECK_SQUARE;
    public final static Resource STETHOSCOPE = FontAwesome.STETHOSCOPE;
    public final static Resource GAVEL = FontAwesome.GAVEL;
    public final static Resource CALENDAR = FontAwesome.CALENDAR;
    public final static Resource TASKS_RESOVLE = FontAwesome.TASKS;
    public final static Resource TASKS_RECEIVE = VaadinIcons.TASKS;
    public final static Resource INFO_CIRCLE = FontAwesome.INFO_CIRCLE;
    public final static Resource SIGNAL = FontAwesome.SIGNAL;
    public final static Resource BARCODE = FontAwesome.BARCODE;
    public final static Resource DOT_CIRCLE = VaadinIcons.DOT_CIRCLE;
    public final static Resource LINE_V = VaadinIcons.LINE_V;
    public final static Resource CHECK_DUPLIDATE = VaadinIcons.COPY;

    public final static Resource DOWN = VaadinIcons.CARET_DOWN;
    public final static Resource UP = VaadinIcons.CARET_UP;

    public final static Resource COLLAPSE = FontAwesome.CARET_SQUARE_O_UP;//
    public final static Resource EXPAND = FontAwesome.CARET_SQUARE_O_DOWN;//

    public final static Resource COGS = VaadinIcons.COGS;
    public final static Resource NOTEBOOK = VaadinIcons.NOTEBOOK;
    public final static Resource CHILD = VaadinIcons.CHILD;
    public final static Resource STAR_O = VaadinIcons.STAR_O;

    public final static Resource MINUS = FontAwesome.MINUS;
    public final static Resource ARROW_RIGHT = FontAwesome.ARROW_RIGHT;

    public static String getHtml(Resource icon) {
        if (icon instanceof FontAwesome) {
            return ((FontAwesome) icon).getHtml();
        } else if (icon instanceof VaadinIcons) {
            return ((VaadinIcons) icon).getHtml();
        }
        return "";
    }
}
