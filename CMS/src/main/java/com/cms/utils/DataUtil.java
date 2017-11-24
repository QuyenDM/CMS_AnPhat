/*
 * Copyright (C) 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cms.utils;

//import com.viettel.language.util.EnumWordType;
//import com.viettel.language.util.MultiLanguageNumberToWords;
import com.cms.common.ws.WSAppParams;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Window;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CustomerDTO;
import com.cms.dto.CustomerStatusDTO;
import com.cms.dto.StaffDTO;
import com.cms.login.dto.MapStaffRolesDTO;
import com.cms.login.ws.WSMapStaffRoles;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Admin
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class DataUtil {

    static String[] arrAcceptFile = new String[]{".doc", ".docx", ".txt", ".xls", ".xlsx", ".ppt", ".pptx"};
    public static String DOUBLE = "double";
    public static String STRING = "string";
    public static String LONG = "long";
    public static String DATE = "date";

    public static final String X_CHARFORM_NOHORN = "aaaaaaaaaaaaaaaaaeeeeeeeeeeeiiiiiooooooooooooooooouuuuuuuuuuuyyyyydAAAAAAAAAAAAAAAAAEEEEEEEEEEEIIIIIOOOOOOOOOOOOOOOOOUUUUUUUUUUUYYYYYD";
    public static final String X_CHARFORM_UNICODE = "áàã??a?????â?????éè???ê?????íìi??óòõ??ô?????o?????úùu??u?????ý????dÁÀÃ??A?????Â?????ÉÈ???Ê?????ÍÌI??ÓÒÕ??Ô?????O?????ÚÙU??U?????Ý????Ð";

    /**
     *
     * @param file
     * @param iSheet
     * @param iBeginRow
     * @param iFromCol
     * @param iToCol
     * @param rowBack
     * @param lstValidateCells
     * @return
     */
    public static List isValidExcells(String mimeType, File file, int iSheet, int iBeginRow, int iFromCol, int iToCol, int rowBack, List<ValidateCells> lstValidateCells) {
        String fileName = file.getName();
        fileName = removeDotInteger(fileName);
        boolean isCopySheet = true;
        File fileError = null;
        Map<String, String> mapsNameError = new HashMap<>();
        List lst = null;
        try {
            if (mimeType.equalsIgnoreCase(Constants.FORMATFILE.EXCEL_XLSX)) { // xlsx
                lst = ExcelReaderXLSX.importExcel(file, iSheet, iBeginRow, iFromCol, iToCol, rowBack);
            } else if (mimeType.equalsIgnoreCase(Constants.FORMATFILE.EXCEL_XLS)) { // xls
                lst = ExcelReader.importExcel(file, iSheet, iBeginRow, iFromCol, iToCol, rowBack);
            }
            //If lst null return
            if (lst == null) {
                return lst;
            }
            String error = "";
            ValidateCells validateCells = null;
            int index = 0;
            if (iBeginRow == 0) {
                index = 1;
            } else {
                index = 0;
            }
            int rowErr = 0;
            Object[] temp;
            List<String> lstReturn = Lists.newArrayList();
            for (int i = index, size = lst.size(); i < size; i++) {
                temp = (Object[]) lst.get(i);
                if (checkObjectNull(temp)) {
//                    lst.remove(temp);
//                    i--;
//                    continue;
                    if (i == 0) {
                        lst = Lists.newArrayList();
                    }
                    break;
                }
                error = "";
                for (int j = 0; j < lstValidateCells.size(); j++) {
                    validateCells = lstValidateCells.get(j);
                    if (validateCells.getPattern() != null) {
                        error += DataUtil.validCell(i, j, (String) temp[j], validateCells.getType(), validateCells.isIsNotNull(), validateCells.getPattern());
                    } else {
                        lstReturn = DataUtil.validCell(i, j, (String) temp[j], validateCells.getType(), validateCells.isIsNotNull(), validateCells.getLength());
                        error += lstReturn.get(0);
                        temp[j] = lstReturn.get(1);
                    }
                }
                if (!isStringNullOrEmpty(error)) {
                    rowErr = i + iBeginRow;
                    mapsNameError.put(rowErr + "", error);
                }
            }

            if (!mapsNameError.isEmpty()) {
                //
                FileInputStream flieInput = new FileInputStream(file);
                XSSFWorkbook workbookIp = null;
                String fileCreate = fileName + "_Error.xlsx";
                FileOutputStream fileOut = new FileOutputStream(fileCreate);
                XSSFWorkbook workbookEp = new XSSFWorkbook();
                XSSFSheet worksheetEp = workbookEp.createSheet("Thong_Tin_Loi");
                XSSFCellStyle cellStyle = null;
                //
                if (isCopySheet) {
                    if (mimeType.equalsIgnoreCase(Constants.FORMATFILE.EXCEL_XLSX)) { // xlsx
                        workbookIp = new XSSFWorkbook(flieInput);
                    } else if (mimeType.equalsIgnoreCase(Constants.FORMATFILE.EXCEL_XLS)) { // xls
                        HSSFWorkbook hSSFWorkbook = new HSSFWorkbook(flieInput);
                        workbookIp = ExcelReaderXLSX.convertWorkbookHSSFToXSSF(hSSFWorkbook);
                    }
                    XSSFSheet worksheetIp = workbookIp.getSheetAt(iSheet);
                    ExcelReaderXLSX.copySheets(worksheetEp, worksheetIp, iToCol);
                    //
                    cellStyle = CommonUtils.styleCell(workbookEp);
                    isCopySheet = false;
                }
                for (Map.Entry<String, String> entrySet : mapsNameError.entrySet()) {
                    String key = entrySet.getKey();
                    String value = entrySet.getValue();
                    int row = Integer.valueOf(key);
                    XSSFRow row5 = worksheetEp.getRow(row);
                    if (row5 != null) {
                        XSSFCell cellB1 = row5.createCell(iToCol + 1);
                        cellB1.setCellValue(value);
                        cellB1.setCellStyle(cellStyle);
                    }
                }
                workbookEp.write(fileOut);
                fileOut.flush();
                fileOut.close();
                fileError = new File(fileCreate);
                Resource resource = new FileResource(fileError);
                Page.getCurrent().open(resource, null, false);
                lst = null;
                fileError.deleteOnExit();
            }

        } catch (Exception e) {
            e.printStackTrace();;
            lst = null;
        }
        return lst;
    }

    public static String md5(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }

    //Check Object[] null
    /**
     *
     * @param objs
     * @return
     */
    public static boolean checkObjectNull(Object[] objs) {
        for (Object obj : objs) {
            if (!DataUtil.isStringNullOrEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

    //
    public static String validCell(int row, int col, String content, String type, boolean isNotNull, String datePattern) {
        String error = "";
        content = getStringNullOrZero(content);
        content = content.trim();
        // check du lieu khac null
        if (isNotNull && DataUtil.isStringNullOrEmpty(content)) {
            error += "không được để trống";
            row = row + 2;
            col = col + 2;
            error = BundleUtils.getString("valid.cell") + col + ": " + error.substring(0, error.length() - 1) + "; ";
            return error;
        }
        if (!isNotNull && DataUtil.isStringNullOrEmpty(content)) {
            return "";
        }
        if (type.equalsIgnoreCase(DOUBLE)) {
            try {
                double d = Double.valueOf(content);
                if (d < 0) {
                    error += BundleUtils.getString("valid.cell.amount").replace(":type", BundleUtils.getString("double"));
                }
            } catch (Exception e) {
                error += BundleUtils.getString("valid.cell.type").replace(":type", BundleUtils.getString("double"));
            }
        } else if (type.equalsIgnoreCase(STRING)) {

        } else if (type.equalsIgnoreCase(LONG)) {
            try {
                long l = Long.valueOf(content);
                if (l < 0) {
                    error += BundleUtils.getString("valid.cell.amount").replace(":type", BundleUtils.getString("long"));
                }
            } catch (Exception e) {
                error += BundleUtils.getString(""
                        + "").replace(":type", BundleUtils.getString("long"));
            }
        } else {
            try {
                Date date = null;
                if (!DataUtil.isStringNullOrEmpty(datePattern)) {
                    date = DateUtil.string2DateByPattern(content, datePattern);
                } else {
                    date = DateUtil.string2DateTime(content);
                }
                if (date == null) {
                    if (!DataUtil.isStringNullOrEmpty(datePattern)) {
                        error += BundleUtils.getString("valid.cell.type").replace(":type", datePattern);
                    } else {
                        error += BundleUtils.getString("valid.cell.type").replace(":type", DateUtil.DATE_TIME_FORMAT);
                    }
                }
            } catch (Exception e) {
            }
        }
        if (!DataUtil.isStringNullOrEmpty(error)) {
            row++;
            col = col + 2;
            error = BundleUtils.getString("valid.cell") + col + ": " + error.substring(0, error.length() - 1) + "; ";
        }
        return error;
    }

    /**
     *
     * @param content
     * @param type
     * @param isNull
     * @param length
     * @param datePattern
     * @return
     */
    public static List<String> validCell(int row, int col, String content, String type, boolean isNotNull, int length) {
        List<String> lst = Lists.newArrayList();
        String error = "";
        String datePattern = DateUtil.DATE_TIME_FORMAT;
        content = getStringNullOrZero(content);
        content = content.trim();
        // check du lieu khac null
        if (isNotNull && DataUtil.isStringNullOrEmpty(content)) {
            error += BundleUtils.getString("valid.cell.null");
            row++;
            col = col + 2;
            error = BundleUtils.getString("valid.cell") + col + ": " + error.substring(0, error.length() - 1) + "; ";
            lst.add(error);
            lst.add(content);
            return lst;
        }
        if (!isNotNull && DataUtil.isStringNullOrEmpty(content)) {
            lst.add(error);
            lst.add(content);
            return lst;
        }
        if (type.equalsIgnoreCase(DOUBLE)) {
            try {
                double d = Double.valueOf(content);
                if (d < 0) {
                    error += BundleUtils.getString("valid.cell.amount").replace(":type", BundleUtils.getString("double"));
                }
            } catch (Exception e) {
                error += BundleUtils.getString("valid.cell.type").replace(":type", BundleUtils.getString("double"));
            }
        } else if (type.equalsIgnoreCase(STRING)) {
            if (DataUtil.isInteger(removeDotInteger(content))) {
                content = removeDotInteger(content);
            }
        } else if (type.equalsIgnoreCase(LONG)) {
            try {
                content = removeDotInteger(content);
                long l = Long.valueOf(content);
                if (l < 0) {
                    error += BundleUtils.getString("valid.cell.amount").replace(":type", BundleUtils.getString("long"));
                }
            } catch (Exception e) {
                error += BundleUtils.getString("valid.cell.type").replace(":type", BundleUtils.getString("long"));
            }
        } else {
            try {
                if (!DataUtil.isStringNullOrEmpty(datePattern)) {
                    DateUtil.string2DateByPattern(content, datePattern);
                } else {
                    DateUtil.string2DateTime(content);
                }
            } catch (Exception e) {
                if (!DataUtil.isStringNullOrEmpty(datePattern)) {
                    error += BundleUtils.getString("valid.cell.type").replace(":type", datePattern);
                } else {
                    error += BundleUtils.getString("valid.cell.type").replace(":type", DateUtil.DATE_TIME_FORMAT);
                }

            }
        }

        if (!type.equalsIgnoreCase(DATE)) {
            if (!DataUtil.isStringNullOrEmpty(content) && content.length() > length) {
                error += BundleUtils.getString("valid.cell.length").replace(":length", length + "");
            }
        }
        if (!DataUtil.isStringNullOrEmpty(error)) {
            row++;
            col = col + 2;
            error = BundleUtils.getString("valid.cell") + col + ": " + error.substring(0, error.length() - 1) + "; ";
        }
        lst.add(error);
        lst.add(content);
        return lst;
    }

    public static boolean isInteger(String str) {
        if (str == null || !str.matches("[0-9]+$")) {
            return false;
        }
        return true;
    }

    // xoa dau cham 
    public static String removeDotInteger(String number) {
        if (number.contains(".")) {
            number = number.substring(0, number.lastIndexOf("."));
        }
        return number;
    }

    //
    public static List<String> getListAcceptFile() {
        List<String> lst = Lists.newArrayList();
        lst = Arrays.asList(arrAcceptFile);
        return lst;
    }

    public static boolean isListNullOrEmpty(List<?> lst) {
        return lst == null || lst.isEmpty();
    }

    //
    public static List<String> splitListFile(String strFiles) {
        List<String> lstFile = Lists.newArrayList();
        if (!isStringNullOrEmpty(strFiles)) {
            String lst[] = strFiles.split(";");
            lstFile = Arrays.asList(lst);
        }
        return lstFile;
    }

    public static List<String> splitListFile(String strFiles, String seperator) {
        List<String> lstFile = Lists.newArrayList();
        if (!isStringNullOrEmpty(strFiles)) {
            String lst[] = strFiles.split(seperator);
            lstFile = Arrays.asList(lst);
        }
        return lstFile;
    }

    public static String lPad(String input, String replace, int length) {
        String format = "%" + length + "s";
        return String.format(format, input).replace(" ", replace);
    }

    public static String rPad(String input, String replace, int length) {
        return String.format("%1$-" + length + "s", input).replace(" ", replace);
    }

    public static String getStringNullOrZero(String strNullOrZero) {
        return isStringNullOrEmpty(strNullOrZero) ? "" : removeCtlChar(strNullOrZero);
    }
    
    public static String removeCtlChar(String str){
        return str.replaceAll("[\\x00-\\x09\\x11\\x12\\x14-\\x1F\\x7F]", "");
    }
    /**
     * forward page
     *
     * @return
     * @author ThanhNT
     */
    public static String forwardPage(String pageName) {
        return "pretty:" + pageName.trim();
    }

    /*
     * Kiem tra Long bi null hoac zero
     *
     * @param value
     * @return
     */
    public static boolean isNullOrZero(Long value) {
        return (value == null || value.equals(0L));
    }

    /**
     * Kiem tra Bigdecimal bi null hoac zero
     *
     * @param value
     * @return
     */
    public static boolean isNullOrZero(BigDecimal value) {
        return (value == null || value.equals(BigDecimal.ZERO));
    }

    /**
     * Lay ten phuong thuc getter
     *
     * @param columnName
     * @return
     */
    public static String getHibernateName(String columnName) {
        columnName = columnName.toLowerCase();
        String[] arrs = columnName.split("_");
        String method = "";
        for (String arr : arrs) {
            method += DataUtil.upperFirstChar(arr);
        }
        return method;
    }

    /**
     * Lay getter
     *
     * @param columnName
     * @return
     */
    public static String getGetterByColumnName(String columnName) {
        return "get" + getHibernateName(columnName);
    }

    //truong bx3 modify 20/04/2015 for tree
    public static String getGetterOfColumn(String column) {
        return "get" + upperFirstChar(column);

    }

    public static String getSetterOfColumn(String column) {
        return "set" + upperFirstChar(column);

    }
//       truongbx3 finish modify

    /**
     * Lay ten phuong thuc setter
     *
     * @param columnName
     * @return
     */
    public static String getSetterByColumnName(String columnName) {
        return "set" + getHibernateName(columnName);
    }

    /**
     * Upper first character
     *
     * @param input
     * @return
     */
    public static String upperFirstChar(String input) {
        if (DataUtil.isNullOrEmpty(input)) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    /**
     * Lower first characater
     *
     * @param input
     * @return
     */
    public static String lowerFirstChar(String input) {
        if (DataUtil.isNullOrEmpty(input)) {
            return input;
        }
        return input.substring(0, 1).toLowerCase() + input.substring(1);
    }

    /**
     * connect String
     *
     * @param objs Object
     * @return String
     */
    public static String connectString(String separateChar, Object... objs) {
        if (objs == null || objs.length == 0) {
            return "";
        }

        StringBuilder content = new StringBuilder();
        content.append(convertToStringLog(objs[0]));

        for (int i = 1; i < objs.length; i++) {
            content.append(separateChar).append(convertToStringLog(objs[i]));
        }

        return content.toString();
    }

    private static String convertToStringLog(Object obj) {
        if (obj == null) {
            return "";
        } else if (obj instanceof Date) {
            return DateUtil.date2ddMMyyyyHHMMss((Date) obj);
        } else {
            return obj.toString();
        }
    }

    /**
     * @param obj1 Object
     * @return Long
     */
    public static Long safeToLong(Object obj1) {
        Long result = 0L;
        if (obj1 != null) {
            try {
                result = Long.parseLong(obj1.toString());
            } catch (Exception ex) {
            }
        }

        return result;
    }

    public static Double safeToDouble(Object obj1) {
        Double result = 0.0;
        if (obj1 != null) {
            try {
                result = Double.parseDouble(obj1.toString());
            } catch (Exception ex) {
            }
        }

        return result;
    }

    public static Short safeToShort(Object obj1) {
        Short result = 0;
        if (obj1 != null) {
            try {
                result = Short.parseShort(obj1.toString());
            } catch (Exception ex) {
            }
        }

        return result;
    }

    /**
     * @param obj1 Object
     * @return int
     */
    public static int safeToInt(Object obj1) {
        int result = 0;
        if (obj1 == null) {
            return 0;
        }
        try {
            result = Integer.parseInt(obj1.toString());
        } catch (Exception ex) {
        }

        return result;
    }

    /**
     * @param obj1 Object
     * @return String
     */
    public static String safeToString(Object obj1) {
        if (obj1 == null) {
            return "";
        }

        return obj1.toString();
    }

    /**
     * safe equal
     *
     * @param obj1 Long
     * @param obj2 Long
     * @return boolean
     */
    public static boolean safeEqual(Long obj1, Long obj2) {
        return ((obj1 != null) && (obj2 != null) && (obj1.compareTo(obj2) == 0));
    }

    /**
     * safe equal
     *
     * @param obj1 String
     * @param obj2 String
     * @return boolean
     */
    public static boolean safeEqual(String obj1, String obj2) {
        return ((obj1 != null) && (obj2 != null) && obj1.equals(obj2));
    }

    /**
     * increase cur no
     *
     * @param obj1 String
     * @param obj2 String
     * @return String
     */
    public static String increaseCurNo(String obj1, int obj2) {
        return String.format("%05d", Integer.parseInt(obj1) + obj2);
    }

    /**
     * create log
     *
     * @param info String
     * @return String
     */
    public static String createLog(String info) {
        //return (DateUtil.dateTime2String(DateUtil.sysDate()) + ": " + info);
        return info;
    }

    /**
     * check null or empty
     *
     * @param obj1 String
     * @return boolean
     */
    public static boolean isNullOrEmpty(String obj1) {
        return (obj1 == null || "".equals(obj1.trim()));
    }

    public static boolean isStringNullOrEmpty(Object obj1) {
        return obj1 == null || obj1.toString().trim().equals("");
    }

    /**
     * @param obj1 Object
     * @return BigDecimal
     */
    public static BigDecimal safeToBigDecimal(Object obj1) {
        BigDecimal result = new BigDecimal(0);
        if (obj1 == null) {
            return result;
        }
        try {
            result = new BigDecimal(obj1.toString());
        } catch (Exception ex) {
        }

        return result;
    }

    public static BigInteger safeToBigInterger(Object obj1) {
        BigInteger result = null;
        if (obj1 == null) {
            return null;
        }
        try {
            result = new BigInteger(obj1.toString());
        } catch (Exception ex) {
        }

        return result;
    }

    /**
     * add
     *
     * @param obj1 BigDecimal
     * @param obj2 BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal add(BigDecimal obj1, BigDecimal obj2) {
        if (obj1 == null) {
            return obj2;
        } else if (obj2 == null) {
            return obj1;
        }

        return obj1.add(obj2);
    }

    /**
     * Convert an IP address to a number
     *
     * @param ipAddress Input IP address
     * @return The IP address as a number
     */
    public final static String MAX_NUMBER_RANGE = "1000000";

    public static BigInteger ipv4ToNumber(String ipAddress) {
        BigInteger result = BigInteger.valueOf(0);
        String[] atoms = ipAddress.split("\\.");

        for (int i = 3; i >= 0; i--) {
            BigInteger bi = new BigInteger(atoms[3 - i]);
            result = result.shiftLeft(8).add(bi);
        }

        return result;
    }

    public static String numberToIpv4(BigInteger ipNumber) {

        String ipString = "";
        BigInteger a = new BigInteger("FF", 16);

        for (int i = 0; i < 4; i++) {
            ipString = ipNumber.and(a).toString() + "." + ipString;

            ipNumber = ipNumber.shiftRight(8);
        }

        return ipString.substring(0, ipString.length() - 1);
    }

    public static BigInteger ipv6ToNumber(String addr) {
        int startIndex = addr.indexOf("::");

        if (startIndex != -1) {

            String firstStr = addr.substring(0, startIndex);
            String secondStr = addr.substring(startIndex + 2, addr.length());

            BigInteger first = ipv6ToNumber(firstStr);

            int x = countChar(addr, ':');
            int y = countChar(secondStr, ':');
            //first = first.shiftLeft(16 * (7 - x)).add(ipv6ToNumber(secondStr));
            first = first.shiftLeft(16 * (7 - x + y));
            first = first.add(ipv6ToNumber(secondStr));

            return first;
        }

        String[] strArr = addr.split(":");

        BigInteger retValue = BigInteger.valueOf(0);
        for (int i = 0; i < strArr.length; i++) {
            BigInteger bi = new BigInteger(strArr[i], 16);
            retValue = retValue.shiftLeft(16).add(bi);
        }
        return retValue;
    }

    public static String numberToIPv6(BigInteger ipNumber) {
        String ipString = "";
        BigInteger a = new BigInteger("FFFF", 16);

        for (int i = 0; i < 8; i++) {
            ipString = ipNumber.and(a).toString(16) + ":" + ipString;

            ipNumber = ipNumber.shiftRight(16);
        }

        return ipString.substring(0, ipString.length() - 1);

    }

    public static int countChar(String str, char reg) {
        char[] ch = str.toCharArray();
        int count = 0;
        for (int i = 0; i < ch.length; ++i) {
            if (ch[i] == reg) {
                if (ch[i + 1] == reg) {
                    ++i;
                    continue;
                }
                ++count;
            }
        }
        return count;
    }

    public static boolean checkValidateIPv4(String fromIPAddress, String toIPAddress, int mask) {

        BigInteger fromIP = ipv4ToNumber(fromIPAddress);
        BigInteger toIP = ipv4ToNumber(toIPAddress);
        BigInteger subnet = new BigInteger("FFFFFFFF", 16);

        fromIP = fromIP.shiftRight(32 - mask).shiftLeft(32 - mask);
        subnet = subnet.shiftRight(mask);

        BigInteger broadcastIP = fromIP.xor(subnet);

        if (toIP.compareTo(broadcastIP) == 1) {
            return false;
        }

        return true;
    }

    public static boolean checkLengthIPV4numberRange(String fromIPAddress, String toIPAddress) {
        BigInteger fromIP = ipv4ToNumber(fromIPAddress);
        BigInteger toIP = ipv4ToNumber(toIPAddress);
        BigInteger limit = toIP.subtract(fromIP);
        if (limit.compareTo(new BigInteger(MAX_NUMBER_RANGE)) == 1) {
            return false;
        }
        return true;
    }

    public static boolean checkValidateIPv6(String fromIPAddress, String toIPAddress, int mask) {

        BigInteger fromIP = ipv6ToNumber(fromIPAddress);
        BigInteger toIP = ipv6ToNumber(toIPAddress);
        BigInteger limit = toIP.subtract(fromIP);
        if (limit.compareTo(new BigInteger(MAX_NUMBER_RANGE)) == 1) {
            return false;
        }
        BigInteger subnet = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF", 16);

        fromIP = fromIP.shiftRight(128 - mask).shiftLeft(128 - mask);
        subnet = subnet.shiftRight(mask);

        BigInteger broadcastIP = fromIP.xor(subnet);

        if (toIP.compareTo(broadcastIP) == 1) {
            return false;
        }

        return true;
    }

    public static String safeStringToSearch(String input) {
        return input.replace("_", "\\_").replace("-", "\\-").replace("%", "\\%");
    }

    public static boolean isLongNumber(BigDecimal minCar) {
        try {
            Long.parseLong(minCar.toString());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * @param min
     * @param max
     * @return
     * @author minhvh1
     */
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    /**
     * @param number
     * @param pattern
     * @return
     * @author KhuongDV Ham format so thuc ve dang co max la 4 chu so thap phan.
     * Trim() so 0 vo nghia
     */
    public static String getFormattedString4Digits(String number, String pattern) {
        double amount = 0;
        try {
            amount = Double.parseDouble(number);
            DecimalFormat formatter = new DecimalFormat(pattern);
            return formatter.format(amount);
        } catch (Exception ex) {
            return number;
        }
    }

    public static Character safeToCharacter(Object value) {
        return safeToCharacter(value, '0');
    }

    public static Character safeToCharacter(Object value, Character defaulValue) {
        if (value == null) {
            return defaulValue;
        }
        return String.valueOf(value).charAt(0);
    }

    public static Collection<Long> strToCollectionLong(List<String> list) {
        Collection<Long> result = new ArrayList<>();
        if (list.isEmpty()) {
            return result;
        }
        for (String s : list) {
            result.add(DataUtil.safeToLong(s));
        }
        return result;
    }

    public static Collection<Long> objLstToLongLst(List<Object> list) {
        Collection<Long> result = new ArrayList<>();
        if (!list.isEmpty()) {
            for (Object item : list) {
                result.add(safeToLong(item));
            }
        }
        return result;
    }

    public static Collection<Short> objLstToShortLst(List<Object> list) {
        Collection<Short> result = new ArrayList<>();
        if (!list.isEmpty()) {
            for (Object item : list) {
                result.add(safeToShort(item));
            }
        }
        return result;
    }

    public static Collection<BigDecimal> objLstToBigDecimalLst(List<Object> list) {
        Collection<BigDecimal> result = new ArrayList<>();
        if (!list.isEmpty()) {
            for (Object item : list) {
                result.add(safeToBigDecimal(item));
            }
        }
        return result;
    }

    public static Collection<Character> objLstToCharLst(List<Object> list) {
        Collection<Character> result = new ArrayList<>();
        if (!list.isEmpty()) {
            for (Object item : list) {
                result.add(item.toString().charAt(0));
            }
        }

        return result;
    }

    public static boolean isDelete(Character isDelete) {
        return isDelete != null && !DataUtil.isNullOrEmpty(String.valueOf(isDelete)) && Objects.equals(isDelete, 0);
    }

    /**
     * Check an object is active
     *
     * @param status status of object
     * @param isDelete isdetete status of object
     * @return
     */
    public static boolean isActive(Character status, Character isDelete) {
        return Objects.equals(status, '1') && (isDelete == null || isDelete.equals('0'));
    }

    public static <T> T getMapValue(Map<String, Object> params, String key, Class<T> type) {
        Object obj = params.get(key);
        if (obj == null) {
            return null;
        }
        if (obj.getClass().isAssignableFrom(obj.getClass())) {
            return type.cast(obj);
        }

        return null;
    }

    public static <T> T nvl(T... objs) {
        for (T obj : objs) {
            if (obj != null) {
                return obj;
            }
        }

        return null;
    }

    public static String strNvl(String... objs) {
        for (String obj : objs) {
            if (!DataUtil.isNullOrEmpty(obj)) {
                return obj;
            }
        }

        return null;
    }

    public static boolean isNullObject(Object obj1) {
        if (obj1 == null) {
            return true;
        }
        if (obj1 instanceof String) {
            return isNullOrEmpty(obj1.toString());
        }
        return false;
    }

    public static String convertToDisplayName(String parType, String index) {
        return parType.replace('_', '.').toLowerCase() + "." + index;
    }

    public static Map<String, String> buildHasmap(List<?> lstHashmap, String columnKey, String columnValue) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException {

        if (lstHashmap == null || lstHashmap.size() == 0) {
            return new HashMap<>();
        }
        Map<String, String> lstReturn = new HashMap<>();
        Class<?> c = lstHashmap.get(0).getClass();
        Method mdGetKey = c.getMethod(DataUtil.getGetterOfColumn(columnKey));
        Method mdGetValue = c.getMethod(DataUtil.getGetterOfColumn(columnValue));
        for (Object item : lstHashmap) {
            try {
                lstReturn.put((String) mdGetKey.invoke(item), (String) mdGetValue.invoke(item));
            } catch (InvocationTargetException ex) {
                Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lstReturn;

    }

    public static <T> Map<String, T> buildHasmap(List<T> lstHashmap, String columnKey) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException {

        if (lstHashmap == null || lstHashmap.isEmpty()) {
            return new HashMap<>();
        }
        Map<String, T> lstReturn = new HashMap<>();
        Class<?> c = lstHashmap.get(0).getClass();
        Method mdGetKey = c.getMethod(DataUtil.getGetterOfColumn(columnKey));
        for (Object item : lstHashmap) {
            try {
                lstReturn.put((String) mdGetKey.invoke(item), (T) item);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstReturn;
    }

    public static String getNameWebserviceFromObject(String name) {
        List<String> lstString = Splitter.on(".").trimResults().omitEmptyStrings().splitToList(name);
        String nameObject = lstString.get((lstString.size() - 1));
        String wsName = "WS" + nameObject.replace("DTO", "");

        return BundleUtils.getConfigString(wsName);

    }

    public static String getNameDeleteFunction(String name) {
        List<String> lstString = Splitter.on(".").trimResults().omitEmptyStrings().splitToList(name);
        String nameObject = lstString.get((lstString.size() - 1));
        return "deleteLst" + nameObject.replace("DTO", "");

    }

    public static Object getValueIdFromObject(Object objName) {

        Object newObj = null;
        try {
            newObj = objName.getClass().newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            Method methods = objName.getClass().getMethod("getColumnId");
            try {
                String methodGetId = methods.invoke(objName).toString();
                if (!DataUtil.isStringNullOrEmpty(methodGetId)) {
                    Method methodsGetID = objName.getClass().getMethod(DataUtil.getGetterOfColumn(methodGetId));
                    String value = "";
                    value = methodsGetID.invoke(objName).toString();
                    Method methodsSetID = objName.getClass().getMethod(DataUtil.getSetterOfColumn(methodGetId), String.class);
                    methodsSetID.invoke(newObj, value);
                }
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newObj;

    }

    public static String deleteObject(List<?> id, String objectName) {

        String nameWS = getNameWebserviceFromObject(objectName);
        String methodWS = getNameDeleteFunction(objectName);
        String returnValue = "";
        try {
            Class cls = Class.forName(nameWS);
            Object obj = null;
            try {
                obj = cls.newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
            Method method = cls.getDeclaredMethod(methodWS, List.class);
            try {
                returnValue = method.invoke(obj, id).toString();
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return returnValue;
    }

    public static List<String> parseInputList(String input) {
        return Splitter.on(",").trimResults().omitEmptyStrings().splitToList(input);
    }

    public static List<String> splitDot(String input) {
        return Splitter.on(".").trimResults().omitEmptyStrings().splitToList(input);
    }

    public static void reloadWindow(Window subWindow) {

        subWindow.addWindowModeChangeListener(new Window.WindowModeChangeListener() {
            @Override
            public void windowModeChanged(Window.WindowModeChangeEvent event) {

                try {
                    Thread.sleep(500L);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public static void addFocusWindow(Window window, final Component.Focusable component) {
        window.addFocusListener(new FocusListener() {

            @Override
            public void focus(FocusEvent event) {
                component.focus();
            }
        });
    }
//String message = convertCharForm(content, Constants.X_CHARFORM_UNICODE, Constants.X_CHARFORM_NOHORN);
    //Chuyen tieng viet co dau thanh ko dau

    public static String convertCharForm(String paramString1) {
        if (paramString1 == null) {
            return null;
        }
        int i = paramString1.length();
        int j = 0;
        StringBuffer localStringBuffer = new StringBuffer();
        for (int k = 0; k < i; ++k) {
            char c = paramString1.charAt(k);
            if ((j = X_CHARFORM_UNICODE.indexOf(c)) >= 0) {
                localStringBuffer.append(X_CHARFORM_NOHORN.charAt(j));
            } else {
                localStringBuffer.append(c);
            }
        }
        return localStringBuffer.toString();
    }

    public static List<AppParamsDTO> getListApParams(List<AppParamsDTO> lstApparamsInput, String type) {
        List<AppParamsDTO> lstAppParams = new ArrayList<>();

        for (AppParamsDTO item : lstApparamsInput) {
            if (item.getParType().equalsIgnoreCase(type)) {
                lstAppParams.add(item);

            }
        }
        return lstAppParams;
    }

    public static List<AppParamsDTO> getListApParams(String type) {
        List<AppParamsDTO> list;
        AppParamsDTO apdto = new AppParamsDTO();
        apdto.setStatus(Constants.ACTIVE);
        apdto.setParType(type);
        try {
            list = WSAppParams.getListAppParamsDTO(apdto, 0, Integer.MAX_VALUE, Constants.ASC, Constants.APP_PARAMS.PAR_ORDER);
        } catch (Exception e) {
            e.printStackTrace();
            list = new ArrayList<>();
        }
        return list;
    }

    public static String getStringEscapeHTML4(String strNullOrZero) {
        return isStringNullOrEmpty(strNullOrZero) ? "" : strNullOrZero.trim();
    }

    //Get list AppParam
    public static List<AppParamsDTO> getListAppParamsDTOs() {
        List<AppParamsDTO> list;
        AppParamsDTO apdto = new AppParamsDTO();
        apdto.setStatus(Constants.ACTIVE);
        try {
            list = WSAppParams.getListAppParamsDTO(apdto, 0, Integer.MAX_VALUE, Constants.ASC, Constants.APP_PARAMS.PAR_ORDER);
        } catch (Exception e) {
            e.printStackTrace();
            list = new ArrayList<>();
        }
        return list;
    }

    public static String getDateNullOrZero(DateField date) {
        return isStringNullOrEmpty(date.getValue()) ? ""
                : com.vwf5.base.utils.DateUtil.date2ddMMyyyyString(date.getValue());
    }

    public static boolean isAdmin(StaffDTO staff) {
        MapStaffRolesDTO a = new MapStaffRolesDTO();
        a.setStaffId(staff.getStaffId());
        try {
            List<MapStaffRolesDTO> lstRoles = WSMapStaffRoles.getListMapStaffRolesDTO(a, 0, 100, "asc", "staffId");
            if (!DataUtil.isListNullOrEmpty(lstRoles)) {
                for (MapStaffRolesDTO msrd : lstRoles) {
                    if ("1".equalsIgnoreCase(msrd.getRoleId())
                            || "3".equalsIgnoreCase(msrd.getRoleId())) {
                        return true;
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    public static List<CustomerStatusDTO> convertListCust2CustStatus(List<CustomerDTO> lstCustomers, StaffDTO staff) {
        List<CustomerStatusDTO> lstReturns = new ArrayList<>();
        for (CustomerDTO c : lstCustomers) {
            lstReturns.add(c.convert2CustomerStatus(staff));
        }
        return lstReturns;
    }

    public static List<String> getTaxCodes(List<CustomerDTO> lstCusts) {
        if (isListNullOrEmpty(lstCusts)) {
            return null;
        }
        List<String> lstTaxCodeString = new ArrayList<>();
        StringBuilder lstTaxCodes = new StringBuilder();
        int count = 0;
        for (CustomerDTO c : lstCusts) {
            lstTaxCodes.append(",");
            lstTaxCodes.append(c.getTaxCode());
            count++;
            if (count == 900) {
                lstTaxCodeString.add(getStringNullOrZero(lstTaxCodes.substring(1)));
                count = 0;
                lstTaxCodes = new StringBuilder();
            }
        }
        if (!DataUtil.isStringNullOrEmpty(lstTaxCodes.toString())) {
            lstTaxCodeString.add(getStringNullOrZero(lstTaxCodes.substring(1)));
        }
        return lstTaxCodeString;
    }

    public static <T> List<String> getListValueFromList(final List<T> lst, String column) throws NoSuchMethodException, IllegalAccessException {
        if (lst == null || lst.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> lstReturn = new ArrayList<>();
        Class<?> c = lst.get(0).getClass();
        Method mdGetValue = c.getMethod(DataUtil.getGetterOfColumn(column));
        for (Object item : lst) {
            try {
                lstReturn.add((String) mdGetValue.invoke(item));
            } catch (InvocationTargetException ex) {
                Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstReturn;
    }

    public static int getMaxSearch(ComboBox cboSearch) {
        AppParamsDTO maxSearchDTO = (AppParamsDTO) cboSearch.getValue();
        int maxSearch = Constants.INT_1000;
        if (!DataUtil.isNullObject(maxSearchDTO)) {
            if (DataUtil.isInteger(maxSearchDTO.getParCode())) {
                maxSearch = Integer.parseInt(maxSearchDTO.getParCode());
            }
        }
        return maxSearch;
    }

    public static String convertList2StringToSearchConditionIN(List<String> list) {
        if (DataUtil.isListNullOrEmpty(list)) {
            return Constants.NULL;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(Constants.COMMA);
            sb.append(s);
        }
        return sb.substring(1);
    }
}
