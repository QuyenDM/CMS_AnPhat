/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import com.vaadin.server.VaadinService;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author TruongBx3
 */
public class FileUtils {

    public static String getParthTemplateImportStock() {
        String path = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        path = path + "/VAADIN/filetemplate/" + "importstock.docx";
        //xuat file       
        return path;
    }

    public static String getParthExportFileImportStock() {
        String path = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

        String filePath = path + "/VAADIN/filetemplate/" + RandomStringUtils.randomAlphabetic(12) + ".docx";
        return filePath;
    }
}
