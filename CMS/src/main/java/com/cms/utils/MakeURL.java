/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

/**
 *
 * @author TiepNV6
 */
public class MakeURL {

    public static String makeURLForGrid(String caption) {

        String URL = "<button class=\"caption\" onclick=\"myFunction(this)\">"
                + "<img src=\"VAADIN/themes/tests-valo/img/navigate_close.png\">  "
                + caption
                + "  </button>";
        return URL;
    }

    public static String makeURLForTable(String caption) {
        String URL = "<button class=\"caption\" onclick=\"myFunctionTable(this)\">"
                + "<img src=\"VAADIN/themes/tests-valo/img/navigate_close.png\">   "
                + caption
                + " </button>";
        return URL;
    }

}
