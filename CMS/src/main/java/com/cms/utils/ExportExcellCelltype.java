/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;



import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author TruongBx3
 */
public class ExportExcellCelltype {

    SXSSFWorkbook wb;

    public ExportExcellCelltype(SXSSFWorkbook wb) {
        this.wb = wb;
    }

    public CellStyle cellTypeColumnStt() {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setWrapText(false);
        return cellStyle;

    }

    public CellStyle cellTypeAlignLeft(Boolean wraptext) {

        CellStyle cellStyleLeft = wb.createCellStyle();
        cellStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);
        cellStyleLeft.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyleLeft.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyleLeft.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyleLeft.setBorderRight(CellStyle.BORDER_THIN);
        cellStyleLeft.setBorderTop(CellStyle.BORDER_THIN);
        cellStyleLeft.setWrapText(wraptext);
        return cellStyleLeft;

    }

    public CellStyle cellTypeAlignCenter(Boolean wraptext) {
        CellStyle cellStyleCenter = wb.createCellStyle();
        cellStyleCenter.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleCenter.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyleCenter.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyleCenter.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyleCenter.setBorderRight(CellStyle.BORDER_THIN);
        cellStyleCenter.setBorderTop(CellStyle.BORDER_THIN);
        cellStyleCenter.setWrapText(wraptext);
        return cellStyleCenter;
    }

    public CellStyle cellTypeHeader(Boolean wraptext) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setWrapText(wraptext);
        return cellStyle;
    }

    public CellStyle cellTypeFormatNumber(Boolean wraptext) {
        CellStyle cellStyleFormatNumber = wb.createCellStyle();
        cellStyleFormatNumber.setDataFormat((short) BuiltinFormats.getBuiltinFormat("#,##0"));
        cellStyleFormatNumber.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyleFormatNumber.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyleFormatNumber.setBorderRight(CellStyle.BORDER_THIN);
        cellStyleFormatNumber.setBorderTop(CellStyle.BORDER_THIN);
        return cellStyleFormatNumber;
    }

    public CellStyle cellTypeAlignRight(Boolean wraptext) {
        CellStyle cellStyleRight = wb.createCellStyle();
        cellStyleRight.setAlignment(CellStyle.ALIGN_RIGHT);
        cellStyleRight.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyleRight.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyleRight.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyleRight.setBorderRight(CellStyle.BORDER_THIN);
        cellStyleRight.setBorderTop(CellStyle.BORDER_THIN);
        cellStyleRight.setWrapText(wraptext);
        return cellStyleRight;
    }

}
