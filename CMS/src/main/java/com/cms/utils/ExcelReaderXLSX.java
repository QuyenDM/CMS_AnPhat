package com.cms.utils;

import com.google.common.collect.Lists;
import java.io.File;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 *
 * @author TruongBx3
 */
public class ExcelReaderXLSX {

    private static HashMap<Integer, XSSFCellStyle> styleMap_1 = new HashMap<>();

    public static List importExcel(File file, int iSheet, int iBeginRow, int iFromCol, int iToCol, int rowBack) throws FileNotFoundException {
        List lst = new ArrayList();
        FileInputStream flieInput = new FileInputStream(file);
        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook(flieInput);
            XSSFSheet worksheet = workbook.getSheetAt(iSheet);
            int irowBack = 0;
            Row row;
            Cell cell;
            for (int i = iBeginRow; i <= worksheet.getLastRowNum(); i++) {
                Object[] obj = new Object[iToCol - iFromCol + 1];
                row = worksheet.getRow(i);

                if (row != null) {
                    int iCount = 0;
                    int check = 0;
                    for (int j = iFromCol; j <= iToCol; j++) {
                        cell = row.getCell(j);
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    obj[iCount] = cell.getStringCellValue().trim();
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        Date date = cell.getDateCellValue();
                                        obj[iCount] = DateTimeUtils.convertDateToString(date, "dd/MM/yyyy");
                                    } else {

                                        Double doubleValue = (Double) cell.getNumericCellValue();
                                        //String.format("%.0f", doubleValue);
                                        List<String> lstValue = DataUtil.splitDot(String.valueOf(doubleValue));
                                        if (lstValue.get(1).matches("[0]+")) {
                                            obj[iCount] = lstValue.get(0);
                                        } else {
                                            obj[iCount] = String.format("%.2f", doubleValue).trim();
                                        }
                                    }

                                    break;
                                case Cell.CELL_TYPE_BLANK:
                                    check++;
                                    break;
                            }
                        } else {
                            obj[iCount] = null;
                        }
                        iCount += 1;
                    }
                    if (check != (iToCol - iFromCol + 1)) {
                        lst.add(obj);
                    }

                } else {
                    irowBack += 1;
                }
                if (irowBack == rowBack) {
                    break;
                }
            }
        } catch (IOException ex) {
            lst = Lists.newArrayList();
            ex.printStackTrace();
        }
        return lst;
    }

    /**
     * TiepNv6
     */
    public static void copySheets(XSSFSheet newSheet, XSSFSheet sheet, int column) {
        copySheets(newSheet, sheet, column, true);
    }

    public static void copySheets(XSSFSheet newSheet, XSSFSheet sheet, int column, boolean copyStyle) {
        int maxColumnNum = 0;
        Map<Integer, XSSFCellStyle> styleMap = (copyStyle) ? new HashMap<Integer, XSSFCellStyle>() : null;
        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
            XSSFRow srcRow = sheet.getRow(i);
            XSSFRow destRow = newSheet.createRow(i);
            if (srcRow != null) {
                copyRow(sheet, newSheet, srcRow, destRow, column, styleMap);
                if (srcRow.getLastCellNum() > maxColumnNum) {
                    maxColumnNum = srcRow.getLastCellNum();
                }
            }
        }
        for (int i = 0; i <= maxColumnNum; i++) {
            newSheet.setColumnWidth(i, sheet.getColumnWidth(i));
        }
    }

    public static void copyRow(XSSFSheet srcSheet, XSSFSheet destSheet, XSSFRow srcRow, XSSFRow destRow, int column, Map<Integer, XSSFCellStyle> styleMap) {
        List<CellRangeAddress> mergedRegions = new ArrayList<CellRangeAddress>();
        destRow.setHeight(srcRow.getHeight());
        for (int j = srcRow.getFirstCellNum(); j <= srcRow.getLastCellNum(); j++) {
            XSSFCell oldCell = srcRow.getCell(j);
            XSSFCell newCell = destRow.getCell(j);
            if (oldCell != null) {
                if (newCell == null) {
                    newCell = destRow.createCell(j);
                }
                copyCell(oldCell, newCell, styleMap);
                CellRangeAddress mergedRegion = getMergedRegion(srcSheet, srcRow.getRowNum(), (short) oldCell.getColumnIndex());
                if (mergedRegion != null) {
                    CellRangeAddress newMergedRegion = new CellRangeAddress(mergedRegion.getFirstRow(), mergedRegion.getLastRow(), mergedRegion.getFirstColumn(), mergedRegion.getLastColumn());
                    if (isNewMergedRegion(newMergedRegion, mergedRegions)) {
                        mergedRegions.add(newMergedRegion);
                        destSheet.addMergedRegion(newMergedRegion);
                    }
                }
            }
        }

    }

    public static void copyCell(XSSFCell oldCell, XSSFCell newCell, Map<Integer, XSSFCellStyle> styleMap) {
        if (styleMap != null) {
            if (oldCell.getSheet().getWorkbook() == newCell.getSheet().getWorkbook()) {
                newCell.setCellStyle(oldCell.getCellStyle());
            } else {
                int stHashCode = oldCell.getCellStyle().hashCode();
                XSSFCellStyle newCellStyle = styleMap.get(stHashCode);
                if (newCellStyle == null) {
                    newCellStyle = newCell.getSheet().getWorkbook().createCellStyle();
                    newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
                    styleMap.put(stHashCode, newCellStyle);
                }
                newCell.setCellStyle(newCellStyle);
            }
        }
        switch (oldCell.getCellType()) {
            case XSSFCell.CELL_TYPE_STRING:
                newCell.setCellValue(oldCell.getStringCellValue());
                break;
            case XSSFCell.CELL_TYPE_NUMERIC:
                newCell.setCellValue(oldCell.getNumericCellValue());
                break;
            case XSSFCell.CELL_TYPE_BLANK:
                newCell.setCellType(XSSFCell.CELL_TYPE_BLANK);
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                newCell.setCellValue(oldCell.getBooleanCellValue());
                break;
            case XSSFCell.CELL_TYPE_ERROR:
                newCell.setCellErrorValue(oldCell.getErrorCellValue());
                break;
            case XSSFCell.CELL_TYPE_FORMULA:
                newCell.setCellFormula(oldCell.getCellFormula());
                break;
            default:
                break;
        }

    }

    public static CellRangeAddress getMergedRegion(XSSFSheet sheet, int rowNum, short cellNum) {
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress merged = (CellRangeAddress) sheet.getMergedRegion(i);
            if (merged.isInRange(rowNum, cellNum)) {
                return merged;
            }
        }
        return null;
    }

    private static boolean isNewMergedRegion(CellRangeAddress newMergedRegion, List<CellRangeAddress> mergedRegions) {
        return !mergedRegions.contains(newMergedRegion);
    }

    // tiepnv6 add, 29/06/2015
    // copy sheet xls to xlsx
//    static HSSFWorkbook source;
//
//    static XSSFWorkbook des;
//    public static void setSource(HSSFWorkbook source) {
//        ExcelReaderXLSX.source = source;
//    }
    public static XSSFWorkbook convertWorkbookHSSFToXSSF(HSSFWorkbook source) {
        XSSFWorkbook des = new XSSFWorkbook();
//        setSource(source);
        for (int i = 0; i < source.getNumberOfSheets(); i++) {
            XSSFSheet xssfSheet = des.createSheet();
            HSSFSheet hssfsheet = source.getSheetAt(i);
            copySheets(source, des, hssfsheet, xssfSheet);
        }
        return des;
    }

    public static void copySheets(HSSFWorkbook sourceWb, XSSFWorkbook des, HSSFSheet source, XSSFSheet destination) {
        copySheets(sourceWb, des, source, destination, true);
    }

    /**
     * @param destination the sheet to create from the copy.
     * @param the sheet to copy.
     * @param copyStyle true copy the style.
     */
    public static void copySheets(HSSFWorkbook sourceWb, XSSFWorkbook des, HSSFSheet source, XSSFSheet destination, boolean copyStyle) {
        int maxColumnNum = 0;
        Map<Integer, XSSFCellStyle> styleMap = (copyStyle) ? new HashMap<Integer, XSSFCellStyle>() : null;
        for (int i = source.getFirstRowNum(); i <= source.getLastRowNum(); i++) {
            HSSFRow srcRow = source.getRow(i);
            XSSFRow destRow = destination.createRow(i);
            if (srcRow != null) {
                copyRow(sourceWb, des, source, destination, srcRow, destRow, styleMap);
                if (srcRow.getLastCellNum() > maxColumnNum) {
                    maxColumnNum = srcRow.getLastCellNum();
                }
            }
        }
        for (int i = 0; i <= maxColumnNum; i++) {
            destination.setColumnWidth(i, source.getColumnWidth(i));
        }
    }

    /**
     * @param srcSheet the sheet to copy.
     * @param destSheet the sheet to create.
     * @param srcRow the row to copy.
     * @param destRow the row to create.
     * @param styleMap -
     */
    public static void copyRow(HSSFWorkbook sourceWb, XSSFWorkbook des, HSSFSheet srcSheet, XSSFSheet destSheet, HSSFRow srcRow, XSSFRow destRow,
            Map<Integer, XSSFCellStyle> styleMap) {
        // manage a list of merged zone in order to not insert two times a
        // merged zone
        Set<CellRangeAddressWrapper> mergedRegions = new TreeSet<CellRangeAddressWrapper>();
        destRow.setHeight(srcRow.getHeight());
        // pour chaque row
        for (int j = srcRow.getFirstCellNum(); j <= srcRow.getLastCellNum(); j++) {
            HSSFCell oldCell = srcRow.getCell(j); // ancienne cell
            XSSFCell newCell = destRow.getCell(j); // new cell
            if (oldCell != null) {
                if (newCell == null) {
                    newCell = destRow.createCell(j);
                }
                // copy chaque cell
                copyCell(sourceWb, des, oldCell, newCell, styleMap);
                // copy les informations de fusion entre les cellules
                // System.out.println("row num: " + srcRow.getRowNum() +
                // " , col: " + (short)oldCell.getColumnIndex());
                CellRangeAddress mergedRegion = getMergedRegion(srcSheet, srcRow.getRowNum(),
                        (short) oldCell.getColumnIndex());

                if (mergedRegion != null) {
                    // System.out.println("Selected merged region: " +
                    // mergedRegion.toString());
                    CellRangeAddress newMergedRegion = new CellRangeAddress(mergedRegion.getFirstRow(),
                            mergedRegion.getLastRow(), mergedRegion.getFirstColumn(), mergedRegion.getLastColumn());
                    // System.out.println("New merged region: " +
                    // newMergedRegion.toString());
                    CellRangeAddressWrapper wrapper = new CellRangeAddressWrapper(newMergedRegion);
                    if (isNewMergedRegion(wrapper, mergedRegions)) {
                        mergedRegions.add(wrapper);
                        destSheet.addMergedRegion(wrapper.range);
                    }
                }
            }
        }

    }

    private static void transform(HSSFWorkbook sourceWb, XSSFWorkbook des, Integer hash, HSSFCellStyle styleOld,
            XSSFCellStyle styleNew, Map<Integer, XSSFCellStyle> styleMap) {
        styleNew.setAlignment(styleOld.getAlignment());
        styleNew.setBorderBottom(styleOld.getBorderBottom());
        styleNew.setBorderLeft(styleOld.getBorderLeft());
        styleNew.setBorderRight(styleOld.getBorderRight());
        styleNew.setBorderTop(styleOld.getBorderTop());
        styleNew.setDataFormat(transform(sourceWb, des, styleOld.getDataFormat()));
        styleNew.setFillBackgroundColor(styleOld.getFillBackgroundColor());
        styleNew.setFillForegroundColor(styleOld.getFillForegroundColor());
        styleNew.setFillPattern(styleOld.getFillPattern());
        styleNew.setFont(transform(sourceWb, des, styleOld.getFont(sourceWb)));
        styleNew.setHidden(styleOld.getHidden());
        styleNew.setIndention(styleOld.getIndention());
        styleNew.setLocked(styleOld.getLocked());
        styleNew.setVerticalAlignment(styleOld.getVerticalAlignment());
        styleNew.setWrapText(styleOld.getWrapText());
        styleMap.put(hash, styleNew);
    }

    private static short transform(HSSFWorkbook sourceWb, XSSFWorkbook des, short index) {
        DataFormat formatOld = sourceWb.createDataFormat();
        DataFormat formatNew = des.createDataFormat();
        return formatNew.getFormat(formatOld.getFormat(index));
    }

    private static XSSFFont transform(HSSFWorkbook sourceWb, XSSFWorkbook des, HSSFFont fontOld) {
        XSSFFont fontNew = des.createFont();
        fontNew.setBoldweight(fontOld.getBoldweight());
        fontNew.setCharSet(fontOld.getCharSet());
        fontNew.setColor(fontOld.getColor());
        fontNew.setFontName(fontOld.getFontName());
        fontNew.setFontHeight(fontOld.getFontHeight());
        fontNew.setItalic(fontOld.getItalic());
        fontNew.setStrikeout(fontOld.getStrikeout());
        fontNew.setTypeOffset(fontOld.getTypeOffset());
        fontNew.setUnderline(fontOld.getUnderline());
        return fontNew;
    }

    /**
     * @param oldCell
     * @param newCell
     * @param styleMap
     */
    public static void copyCell(HSSFWorkbook sourceWb, XSSFWorkbook des, HSSFCell oldCell, XSSFCell newCell, Map<Integer, XSSFCellStyle> styleMap) {
        if (styleMap != null) {
            int stHashCode = oldCell.getCellStyle().hashCode();
//            HSSFCellStyle sourceCellStyle = styleMap.get(stHashCode);
//            XSSFCellStyle destnCellStyle = newCell.getCellStyle();
//            if (sourceCellStyle == null) {
//                sourceCellStyle = oldCell.getSheet().getWorkbook().createCellStyle();
//            }
//            transform(stHashCode, sourceCellStyle,
//                    destnCellStyle);
//    cellNew.setCellStyle(this.styleMap.get(hash));
//            transform(sourceCellStyle, destnCellStyle);
//            destnCellStyle.cloneStyleFrom((CellStyle) oldCell.getCellStyle());
//            styleMap.put(stHashCode, sourceCellStyle);
            if (!styleMap.containsKey(stHashCode)) {
                transform(sourceWb, des, stHashCode, oldCell.getCellStyle(),
                        des.createCellStyle(), styleMap);
            }
            newCell.setCellStyle(styleMap.get(stHashCode));
//            newCell.setCellStyle(destnCellStyle);
        }
        switch (oldCell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                newCell.setCellValue(oldCell.getStringCellValue());
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                newCell.setCellValue(oldCell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                newCell.setCellType(HSSFCell.CELL_TYPE_BLANK);
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                newCell.setCellValue(oldCell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                newCell.setCellErrorValue(oldCell.getErrorCellValue());
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                newCell.setCellFormula(oldCell.getCellFormula());
                break;
            default:
                break;
        }

    }

    /**
     * Récupère les informations de fusion des cellules dans la sheet source
     * pour les appliquer à la sheet destination... Récupère toutes les zones
     * merged dans la sheet source et regarde pour chacune d'elle si elle se
     * trouve dans la current row que nous traitons. Si oui, retourne l'objet
     * CellRangeAddress.
     *
     * @param sheet the sheet containing the data.
     * @param rowNum the num of the row to copy.
     * @param cellNum the num of the cell to copy.
     * @return the CellRangeAddress created.
     */
    public static CellRangeAddress getMergedRegion(HSSFSheet sheet, int rowNum, short cellNum) {
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress merged = sheet.getMergedRegion(i);
            if (merged.isInRange(rowNum, cellNum)) {
                return merged;
            }
        }
        return null;
    }

    private static boolean isNewMergedRegion(CellRangeAddressWrapper newMergedRegion, Set<CellRangeAddressWrapper> mergedRegions) {
        return !mergedRegions.contains(newMergedRegion);
    }

}
