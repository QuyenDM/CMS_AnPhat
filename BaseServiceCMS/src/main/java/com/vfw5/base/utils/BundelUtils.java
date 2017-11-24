/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vfw5.base.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TruongBx3
 */
public class BundelUtils {
     public static String getkey(String key) {
        try {
            InputStream input = null;
            String filename = "config.properties";
            input = BundelUtils.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return null;
            }
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(key);
        } catch (IOException ex) {
            Logger.getLogger(BundelUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
     public static void main(String[] args) {
         System.out.println(getkey("cms_ws_url"));
    }
}
