/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.validator.DateRangeValidator;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

import com.cms.component.CommonDialog;
import com.cms.component.CustomPageTable;
import com.cms.component.CustomPageTableFilter;
import com.cms.component.GridManyButton;
import com.cms.dto.AppParamsDTO;
import com.cms.ui.CommonTableFilterPanel;
import static com.cms.utils.DateUtil.maxDate;
import static com.cms.utils.DateUtil.minDate;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author TiepNV6
 */
public class CommonUtils {

    public static void setVisibleBtnTablePanel(CommonTableFilterPanel filterPanel, boolean add, boolean del, boolean copy, boolean edit) {
        filterPanel.getAddButton().setVisible(add);
        filterPanel.getCoppyButton().setVisible(copy);
        filterPanel.getDeleteButton().setVisible(del);
        filterPanel.getEditButton().setVisible(edit);
    }

    /**
     *
     * @param areaDTO
     */
    /**
     *
     * @param areaDTO
     */
    public static void boldLabel(Label label) {
        label.setStyleName("v-label-bold");
    }

    /**
     * Discard any field edits
     *
     * @param fields
     */
    public static void discard(List<Field> fields) {
        for (Field field : fields) {
            field.discard();
        }
    }

    /**
     *
     * @return
     */
    public static boolean commit(List<Field> fields) {
        boolean isOk = true;
        for (Field field : fields) {
            if (field.getValidators() != null && DataUtil.isStringNullOrEmpty(field.getValue())) {
                try {
                    field.commit();
                } catch (Exception e) {
                    field.focus();
                    isOk = false;
                }
            } else {
                field.commit();
            }
        }
        return isOk;
    }

    public static void showSuccess() {
        Notification.show(BundleUtils.getString("actionSuccess"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showNotFountData() {
        Notification.show(BundleUtils.getString("notFoundData"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showNoGoods() {
        Notification.show(BundleUtils.getString("stock.no.goods.1"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showValidDate() {
        Notification.show(BundleUtils.getString("validTime"), Notification.Type.TRAY_NOTIFICATION);
    }

    //Ngay ky hop dong phai nho hon boac bang ngay bat dau hop dong
    public static void showValidDateSignContract() {
        Notification.show(BundleUtils.getString("signdateStartdate"), Notification.Type.TRAY_NOTIFICATION);
    }

    //Ngay bat dau hop dong phai nho hon ngay ket thuc hop dong
    public static void showStartEndDate() {
        Notification.show(BundleUtils.getString("startEndDate"), Notification.Type.TRAY_NOTIFICATION);
    }

    //Ngay tinh cuoc phai nho hon ngay ket thuc hop dong
    public static void showEndBillingDate() {
        Notification.show(BundleUtils.getString("endBillingDate"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showFillAllData() {
        Notification.show(BundleUtils.getString("fillAllData"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showValidFileImport() {
        Notification.show(BundleUtils.getString("valid.import.file"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showChoseOne() {
        Notification.show(BundleUtils.getString("chooseOneRecord"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showChoseLocation() {
        Notification.show(BundleUtils.getString("chooseOneLocation"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showChoseOnlyOne() {
        Notification.show(BundleUtils.getString("onlyChooseOneRecord"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showFail() {
        Notification.show(BundleUtils.getString("actionFail"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showNotEnoughGoods() {
        Notification.show(BundleUtils.getString("amount.issue.not.enough"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showCancel() {
        Notification.show(BundleUtils.getString("actionCalcel"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showCancelExport() {
        Notification.show(BundleUtils.getString("actionCalcelExport"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showFomatSize() {
        Notification.show(BundleUtils.getString("fomatSize"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showOnlyNumber(String message) {
        Notification.show(BundleUtils.getString("onlyNumber") + " " + (BundleUtils.getString(message)), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showCompareVolume() {
        Notification.show(BundleUtils.getString("compareVolume"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showFomatSizePacking() {
        Notification.show(BundleUtils.getString("error.number.fomatpacksize"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showChooseCust() {
        Notification.show(BundleUtils.getString("chooseCust"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showChooseStock() {
        Notification.show(BundleUtils.getString("chooseStock"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showChooseStockEXport() {
        Notification.show(BundleUtils.getString("chooseStockExport"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showMessageRequired(String message) {
        Notification.show(BundleUtils.getString("common.input.required") + " " + (BundleUtils.getString(message)), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showLocatChooseGoods(String strLocation) {
        Notification.show(BundleUtils.getString("location.no.goods").replace("@s", strLocation), Notification.Type.TRAY_NOTIFICATION);
    }

    // phan tich tang, ke, o cua cell tu name
    public static void showCodeExist() {
        Notification.show(BundleUtils.getString("common.error.code.existed"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showStockExist() {
        Notification.show(BundleUtils.getString("common.error.stock.existed"), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showUpdateSuccess() {
        Notification.show(BundleUtils.getString("common.notitfication.update.success"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showDeleteSuccess() {
        Notification.show(BundleUtils.getString("common.notitfication.delete.success"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showInsertSuccess() {
        Notification.show(BundleUtils.getString("common.notitfication.insert.success"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showUpdateFail() {
        Notification.show(BundleUtils.getString("common.notitfication.update.fail"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showGreaterError() {
        Notification.show(BundleUtils.getString("common.error.pricemin.greater.price"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showFromValue2ToValueError() {
        Notification.show(BundleUtils.getString("common.error.fromValue2ToValue"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showServicePriceCantAdd() {
        Notification.show(BundleUtils.getString("service.error.cant.add"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showFromValueAndToValuePreviousError() {
        Notification.show(BundleUtils.getString("common.error.fromValueAndToValuePrevious"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showToValueAndFromValueNextError() {
        Notification.show(BundleUtils.getString("common.error.toValueNotGreaterThanFromValueNext"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showExpiryDateBeforeIssueDateError() {
        Notification.show(BundleUtils.getString("common.error.expiryDateBeforeIssueDate"), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showMessageFormatDate(String message) {
        Notification.show(BundleUtils.getString("common.input.formatdate") + " " + BundleUtils.getString(message), Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showMessage(String message) {
        Notification.show(BundleUtils.getString(message), Notification.Type.TRAY_NOTIFICATION);
    }

    //Them button cho cac dialog chi co chuc nang ghi lai va huy bo
    public static GridManyButton getCommonButtonDialog(final CommonDialog dialog) {
        String[] commonBtn = new String[]{BundleUtils.getString("common.button.save"),
            BundleUtils.getString("common.button.cancel")};
        GridManyButton gridManyButton = new GridManyButton(commonBtn);
        Button btnCancel = gridManyButton.getBtnCommon().get(1);
        btnCancel.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                dialog.close();
            }
        });
        return gridManyButton;
    }

    public static XSSFCellStyle styleCell(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setWrapText(false);
        return cellStyle;
    }

    //SetCommon info Layout
    public static void setBasicAttributeLayout(GridLayout gridLayout, String caption, boolean isCollapseable) {
        gridLayout.setWidth("100%");
        gridLayout.setHeight("-1px");
        gridLayout.setImmediate(true);
        gridLayout.setMargin(true);
        gridLayout.setSpacing(true);
        if (isCollapseable && !DataUtil.isStringNullOrEmpty(caption)) {
            gridLayout.setStyleName("custom-feildset");
            gridLayout.setCaption(MakeURL.makeURLForGrid(caption));
            gridLayout.setCaptionAsHtml(isCollapseable);
        }
    }

    //Set data 2 optionGroup cho bang du lieu
    public static OptionGroup setData2OptionGroup(OptionGroup og, List<?> lst, String id, String name, String... defaultValue) {
        if (lst == null || lst.isEmpty()) {
            return og;
        }
        try {
            Class<?> c = lst.get(0).getClass();
            Method methodId = c.getMethod(DataUtil.getGetterOfColumn(id));
            Method methodName = c.getMethod(DataUtil.getGetterOfColumn(name));
            for (int i = 0; i < lst.size(); i++) {
                og.addItem(methodId.invoke(lst.get(i)));
                og.setItemCaption(methodId.invoke(lst.get(i)), String.valueOf(methodName.invoke(lst.get(i))));
            }
            if (defaultValue != null && defaultValue.length > 0) {
                for (String defaultValue1 : defaultValue) {
                    if (og.containsId(defaultValue1.trim())) {
                        og.select(defaultValue1.trim());
                    }
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, null, ex);
            return og;
        }
        return og;
    }

    /**
     * Chuyen doi tuong String thanh doi tuong Date.
     *
     * @param date Xau ngay, co dinh dang duoc quy trinh trong file Constants
     * @return Doi tuong Date
     */
    public static Date convertStringToDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            return null;
        } else {
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            dateFormat.setLenient(false);
            try {
                return dateFormat.parse(date);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    /**
     * Chuyen doi tuong string ve doi tuong date theo dinh dang
     *
     * @param date
     * @param regex mau du lieu
     * @param pattern mau dinh dang
     * @return
     */
    public static Date convertStringToDate(String date, String regex, String pattern) {
        if (date == null || date.trim().isEmpty()) {
            return null;
        } else if (!date.matches(regex)) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setLenient(false);
            try {
                return sdf.parse(date);
            } catch (ParseException ex) {
                return null;
            }
        }
    }

    //Get value from OptionGroup to save 2 database
    public static String getValueOptionGroup(OptionGroup og) {
        String value = String.valueOf(og.getValue());

        return value.replace("[", "").replace("]", "").replace(" ", "");
    }

    public static void enableButtonAfterClick(Button.ClickEvent event) {
        event.getButton().setEnabled(true);
    }

    public static String getStatus(String strStatus) {
        if (strStatus.equalsIgnoreCase("1") || strStatus.equalsIgnoreCase("0")) {
            return strStatus;
        }
        if (strStatus.equalsIgnoreCase(BundleUtils.getString("status.1"))) {
            return "1";
        } else {
            return "0";
        }
    }

    //Reload table sau khi them sua xoa
    public static void reloadTable(CustomPageTable table, Object itemId) {
        table.setImmediate(true);
        table.addItemAfter(null, itemId);
        table.select(null);
        table.select(itemId);
        table.resetPage();
        table.refreshRowCache();
        table.setPageLength(table.size());
    }

    public static void reloadTable(CustomPageTableFilter table, Object itemId) {
        table.setImmediate(true);
        table.addItemAfter(null, itemId);
        table.resetPage();
        table.refreshRowCache();
        table.select(null);
        table.select(itemId);
    }

    public static void reloadTable(String action, CustomPageTableFilter table, Object itemId) {
        table.setImmediate(true);
        table.addItemAfter(null, itemId);
        table.select(null);
        table.select(itemId);
        if (action.equalsIgnoreCase(Constants.ADD) || action.equalsIgnoreCase(Constants.COPY)) {
            table.addItem(itemId);
        }
        table.refreshRowCache();
        table.resetPage();
    }

    //Them opgion
    public static Panel addOg2Panel(OptionGroup og, String caption, String height) {
        og.setWidth("100%");
        og.setHeight("-1px");
        og.setImmediate(true);
        og.setMultiSelect(true);
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setHeightUndefined();
        layout.setImmediate(true);
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.addComponent(og);
        layout.setComponentAlignment(og, Alignment.MIDDLE_LEFT);
        Panel panel = new Panel();
        if (!DataUtil.isStringNullOrEmpty(caption)) {
            panel.setCaption(caption);
        }
        panel.setWidth("100%");
        panel.setImmediate(true);
        if (!DataUtil.isStringNullOrEmpty(height)) {
            panel.setHeight(height);
        } else {
            panel.setHeight("200px");
        }
        panel.addStyleName(Runo.PANEL_LIGHT);
        panel.setContent(layout);
        return panel;
    }

    public static String getHeight(Component co) {
        return (co.getHeight() + "" + co.getHeightUnits().getSymbol());
    }

    public static boolean addDateValidator(PopupDateField pdf) {
        pdf.removeAllValidators();
        Validator rangeValidator = new DateRangeValidator(BundleUtils.getString("valid.pattern.date"), minDate, maxDate, Resolution.DAY);
        try {
            pdf.setRequired(false);
            pdf.addValidator(rangeValidator);
            pdf.validate();
            return true;
        } catch (Validator.InvalidValueException e) {
            CommonUtils.showMessageFormatDate(DateUtil.DATE_FM_DD_MM_YYYY);
            pdf.focus();
            return false;
        }
    }

    public static boolean addDateValidator(PopupDateField fromDate, PopupDateField toDate) {
        toDate.removeAllValidators();
        Validator rangeValidator = new DateRangeValidator(BundleUtils.getString("valid.pattern.date"), fromDate.getValue(), maxDate, Resolution.DAY);
        try {
            toDate.setRequired(false);
            toDate.addValidator(rangeValidator);
            toDate.validate();
            return true;
        } catch (Validator.InvalidValueException e) {
            CommonUtils.showStartEndDate();
            toDate.focus();
            return false;
        }
    }

    //Kiem tra header trong excel xem co trung vs header trong template khong. Neu k trung return false
    public static boolean checkHeaderExcel(Object[] lstObjects, String headerExcel) {
        String header = BundleUtils.getString(headerExcel);
        String[] headerEx = header.split(Constants.COMMA);
        if (lstObjects.length != headerEx.length) {
            return false;
        } else {
            for (int i = 0; i < headerEx.length; i++) {
                if (!headerEx[i].trim().equals(lstObjects[i].toString().trim())) {
                    return false;
                }
            }
        }
        return true;
    }

    //Kiem tra 1 mang co cac thanh phan nao null khong, neu co tra ve vi tri cua mang, neu khong tra ve -1
    public static int checkCellNull(Object[] lstCells, int[] cellNotNulls) {
        for (int i = 0; i < cellNotNulls.length; i++) {
            if (DataUtil.isNullObject(lstCells[cellNotNulls[i]])) {
                return cellNotNulls[i];
            }
        }
        return -1;
    }

    public static void showMessageCellNull(String headerExcel, int cellNull, int record) {
        String header = BundleUtils.getString(headerExcel);
        String[] headerEx = header.split(Constants.COMMA);
        String column = headerEx[cellNull];
        record += 1;
        Notification.show(BundleUtils.getString("common.input.required") + column + "  :  " + BundleUtils.getString("in.record") + record, Notification.Type.TRAY_NOTIFICATION);
    }

    public static void showWrongTemplateFile() {
        Notification.show(BundleUtils.getString("error.excel.template.wrong"), Notification.Type.TRAY_NOTIFICATION);
    }

    //Excel Stlye Left
    public static HSSFCellStyle styleLeft(HSSFWorkbook workbook) {
        HSSFCellStyle cellStyleLeft = workbook.createCellStyle();
        cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cellStyleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyleLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyleLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyleLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyleLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyleLeft.setWrapText(false);
        return cellStyleLeft;
    }

    //Excel Stlye Right
    public static HSSFCellStyle styleRight(HSSFWorkbook workbook) {
        //phai
        HSSFCellStyle cellStyleRight = workbook.createCellStyle();
        cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        cellStyleRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyleRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyleRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyleRight.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyleRight.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyleRight.setWrapText(false);
        return cellStyleRight;
    }

    //Excel Stlye Center
    /**
     *
     * @param workbook
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle styleCenter(HSSFWorkbook workbook) {
        //giua
        HSSFCellStyle cellStyleCenter = workbook.createCellStyle();
        cellStyleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyleCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyleCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyleCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyleCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyleCenter.setWrapText(false);
        return cellStyleCenter;
    }

    //Excel Stlye Center
    /**
     *
     * @param workbook
     * @return HSSFCellStyle
     */
    public static HSSFCellStyle styleCell(HSSFWorkbook workbook) {
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setWrapText(false);
        return cellStyle;
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

    //show message duplicate
    /**
     *
     * @param result
     */
    public static void showMessageDuplicate(com.vwf5.base.dto.ResultDTO result) {
        //Check record duplicate in database table staffstock
        if (result.getKey() != null && result.getKey().equalsIgnoreCase(Constants.ERROR.ERROR_EXISTED)) {
            CommonMessages.showErrorMessage(BundleUtils.getString(Constants.ERROR.ERROR_EXISTED));
        } else if (result.getKey() != null && result.getKey().equalsIgnoreCase(Constants.ERROR.ERROR_SYSTEM)) {
            CommonMessages.showErrorMessage(BundleUtils.getString(Constants.ERROR.ERROR_SYSTEM));
        } else {
            CommonUtils.showFail();
        }
    }

    public static void convertDateTimeTable(CustomPageTableFilter<IndexedContainer> tbl, String propertyId) {
        tbl.setConverter(propertyId, new Converter<String, String>() {
            @Override
            public String convertToModel(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                return "";
            }

            @Override
            public String convertToPresentation(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                if (value == null || value.equals("")) {
                    return "";
                }
                value = DateUtil.convertDateTime(value);
                return value;
            }

            @Override
            public Class<String> getModelType() {
                return String.class;
            }

            @Override
            public Class<String> getPresentationType() {
                return String.class;
            }
        });
    }

    public static void convertDateTimeTable(CustomPageTable tbl, String propertyId) {
        tbl.setConverter(propertyId, new Converter<String, String>() {
            @Override
            public String convertToModel(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                return "";
            }

            @Override
            public String convertToPresentation(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                if (value == null || value.equals("")) {
                    return "";
                }
                value = DateUtil.convertDateTime(value);
                return value;
            }

            @Override
            public Class<String> getModelType() {
                return String.class;
            }

            @Override
            public Class<String> getPresentationType() {
                return String.class;
            }
        });
    }

    public static void initCombobox(ComboBox comboBox, BeanItemContainer container, String propertyId) {
        comboBox.setContainerDataSource(container);
        comboBox.setFilteringMode(FilteringMode.CONTAINS);
        comboBox.setImmediate(true);
        comboBox.setItemCaptionPropertyId(propertyId);
        comboBox.setNullSelectionAllowed(true);
    }

    public static void showCodeImportExist(String existcode) {
        Notification.show(BundleUtils.getString("detail.serial.goodsCode") + " ( " + existcode + " ) " + BundleUtils.getString("goods.error.code.existed"), BundleUtils.getString("clickToClose"), Notification.Type.ERROR_MESSAGE);
    }

    public static void showUpdateSuccess(String message) {
        Notification.show(BundleUtils.getString("common.noti.update.success").replace("@s", message), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showDeleteSuccess(String message) {
        Notification.show(BundleUtils.getString("common.noti.delete.success").replace("@s", message), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showInsertFail() {
        Notification.show(BundleUtils.getString("common.notitfication.import.fail"), Notification.Type.ERROR_MESSAGE);
    }

    public static void showInsertSuccess(String message) {
        Notification.show(BundleUtils.getString("common.noti.insert.success").replace("@s", message), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showCopySuccess(String message) {
        Notification.show(BundleUtils.getString("common.noti.copy.success").replace("@s", message), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showUpdateFail(String message) {
        Notification.show(BundleUtils.getString("common.noti.update.fail").replace("@s", message), Notification.Type.ERROR_MESSAGE);
    }

    public static void showInsertFail(String message) {
        Notification.show(BundleUtils.getString("common.noti.insert.fail").replace("@s", message), Notification.Type.ERROR_MESSAGE);
    }

    public static void showDeleteFail(String message) {
        Notification.show(BundleUtils.getString("common.noti.delete.fail").replace("@s", message), Notification.Type.ERROR_MESSAGE);
    }

    public static void showCopyFail(String message) {
        Notification.show(BundleUtils.getString("common.noti.copy.fail").replace("@s", message), Notification.Type.ERROR_MESSAGE);
    }

    public static void showLengthValid(int length) {
        Notification.show(BundleUtils.getString("valid.length.txt").replace("@length", String.valueOf(length)), Notification.Type.WARNING_MESSAGE);
    }

    public static void showContentLengthValid() {
        Notification.show(BundleUtils.getString("valid.file.contentLength"), Notification.Type.WARNING_MESSAGE);
    }

    public static void showWarningMessage(String message) {
        Notification.show(BundleUtils.getString(message), Notification.Type.WARNING_MESSAGE);
    }

    public static void showErrorMessage(String message) {
        Notification.show(BundleUtils.getString(message), BundleUtils.getString("clickToClose"), Notification.Type.ERROR_MESSAGE);
    }
//    public static Map<String, AppParamsDTO> putAppParam2Map(List<AppParamsDTO> lstAppParamsDTO) {
//        Map<String, AppParamsDTO> maps = new HashMap<>();
//        if (lstAppParamsDTO != null) {
//            for (AppParamsDTO appParamsDTO : lstAppParamsDTO) {
//                maps.put(appParamsDTO.getParType() + appParamsDTO.getParCode(), appParamsDTO);
//            }
//        }
//        return maps;
//    }

//    public static GoodsSerialLogDTO convertGoodsSerial2GoodSerialLog(GoodsSerialDTO goodsSerial) {
//        //Ghi log
//        GoodsSerialLogDTO goodsSerialLog = new GoodsSerialLogDTO();
//
//        goodsSerialLog.setBatchCode(goodsSerial.getBatchCode());
//        goodsSerialLog.setDeptId(goodsSerial.getDeptId());
//        goodsSerialLog.setGoodsId(goodsSerial.getGoodsId());
//        goodsSerialLog.setSerialStatus(goodsSerial.getSerialStatus());
//        goodsSerialLog.setOrd(goodsSerial.getOrd());
//        goodsSerialLog.setOwner("STYLESTONE");
//        goodsSerialLog.setProductTyp(goodsSerial.getProductType());
//        goodsSerialLog.setQualityType(goodsSerial.getQualityType());
//        goodsSerialLog.setSerial(goodsSerial.getSerial());
//        goodsSerialLog.setShadeId(goodsSerial.getShadeId());
//        goodsSerialLog.setStaffId(goodsSerial.getStaffId());
//        goodsSerialLog.setSurfaceType(goodsSerial.getSurfaceType());
//        return goodsSerialLog;
//    }
//
//    public static Map<String, GoodsDTO> putGoods2MapByCode(List<GoodsDTO> lstGoodsDTO) {
//        Map<String, GoodsDTO> maps = new HashMap<>();
//        if (lstGoodsDTO != null) {
//            for (GoodsDTO goodsDTO : lstGoodsDTO) {
//                maps.put(goodsDTO.getCode(), goodsDTO);
//            }
//        }
//        return maps;
//    }
//
//    public static Map<String, AppParamsDTO> putAppParams2MapByCode(List<AppParamsDTO> lstGoodsDTO) {
//        Map<String, AppParamsDTO> maps = new HashMap<>();
//        if (lstGoodsDTO != null) {
//            for (AppParamsDTO goodsDTO : lstGoodsDTO) {
//                maps.put(goodsDTO.getParCode(), goodsDTO);
//            }
//        }
//        return maps;
//    }
//
//    public static Map<String, DepartmentDTO> putDepartment2Map(List<DepartmentDTO> lstDepartmentDTO) {
//        Map<String, DepartmentDTO> maps = new HashMap<>();
//        if (lstDepartmentDTO != null) {
//            for (DepartmentDTO departmentDTO : lstDepartmentDTO) {
//                maps.put(departmentDTO.getDeptId(), departmentDTO);
//            }
//        }
//        return maps;
//    }
//
//    public static Map<String, GoodsDTO> putGoods2Map(List<GoodsDTO> lstGoodsDTO) {
//        Map<String, GoodsDTO> maps = new HashMap<>();
//        if (lstGoodsDTO != null) {
//            for (GoodsDTO goodsDTO : lstGoodsDTO) {
//                maps.put(goodsDTO.getGoodsId(), goodsDTO);
//            }
//        }
//        return maps;
//    }
//
//    public static Map<String, StaffDTO> putStaff2Map(List<StaffDTO> lstStaffDTO) {
//        Map<String, StaffDTO> maps = new HashMap<>();
//        if (lstStaffDTO != null) {
//            for (StaffDTO staffDTO : lstStaffDTO) {
//                maps.put(staffDTO.getStaffId(), staffDTO);
//            }
//        }
//        return maps;
//    }
//
//    public static Map<String, ShadeDTO> putShade2Map(List<ShadeDTO> lstGoodsDTO) {
//        Map<String, ShadeDTO> maps = new HashMap<>();
//        if (lstGoodsDTO != null) {
//            for (ShadeDTO goodsDTO : lstGoodsDTO) {
//                maps.put(goodsDTO.getShadeId(), goodsDTO);
//            }
//        }
//        return maps;
//    }
//
//    public static Map<String, ShadeDTO> putShade2MapByCode(List<ShadeDTO> lstGoodsDTO) {
//        Map<String, ShadeDTO> maps = new HashMap<>();
//        if (lstGoodsDTO != null) {
//            for (ShadeDTO goodsDTO : lstGoodsDTO) {
//                maps.put(goodsDTO.getCode(), goodsDTO);
//            }
//        }
//        return maps;
//    }
    public static void convertFieldAppParamTable(CustomPageTableFilter tbl, String propertyId, final String parType) {
        tbl.setConverter(propertyId, new Converter<String, String>() {
            @Override
            public String convertToModel(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                return "";
            }

            @Override
            public String convertToPresentation(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                if (value == null || value.equals("")) {
                    return "";
                }
                try {
                    String parCode = value;
                    if (DataUtil.isInteger(parCode)) {
                        value = BundleUtils.getString(parType.toLowerCase().replace("_", ".") + "." + parCode);
                    }
                } catch (Exception e) {
                }
                return value;
            }

            @Override
            public Class<String> getModelType() {
                return String.class;
            }

            @Override
            public Class<String> getPresentationType() {
                return String.class;
            }
        });
    }

    public static void convertFieldAppParamTable(CustomPageTableFilter tbl,
            String propertyId, final String parType, final Map<String, String> mapCode2Name) {
        tbl.setConverter(propertyId, new Converter<String, String>() {
            @Override
            public String convertToModel(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                return "";
            }

            @Override
            public String convertToPresentation(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                if (value == null || value.equals("")) {
                    return "";
                }
                try {
                    String parCode = value;
                    if (DataUtil.isInteger(parCode)) {
                        value = BundleUtils.getString(parType.toLowerCase().replace("_", ".") + "." + parCode);
                        if (value.contains(parType.toLowerCase().replace("_", "."))
                                && mapCode2Name != null) {
                            return mapCode2Name.get(parCode);
                        }
                    }
                } catch (Exception e) {
                }
                return value;
            }

            @Override
            public Class<String> getModelType() {
                return String.class;
            }

            @Override
            public Class<String> getPresentationType() {
                return String.class;
            }
        });
    }

    public static void convertFieldTable(CustomPageTableFilter tbl,
            String propertyId, final Map<String, String> mapCode2Name) {
        tbl.setConverter(propertyId, new Converter<String, String>() {
            @Override
            public String convertToModel(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                return "";
            }

            @Override
            public String convertToPresentation(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                if (value == null || value.equals("")) {
                    return "";
                }
                try {
                    String parCode = value;
                    return mapCode2Name.get(parCode);
                } catch (Exception e) {                    
                }
                return value;
            }

            @Override
            public Class<String> getModelType() {
                return String.class;
            }

            @Override
            public Class<String> getPresentationType() {
                return String.class;
            }
        });
    }

    public static void convertFieldAppParamTable(CustomPageTableFilter tbl, String propertyId) {
        tbl.setConverter(propertyId, new Converter<String, String>() {
            @Override
            public String convertToModel(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                return "";
            }

            @Override
            public String convertToPresentation(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                if (value == null || value.equals("")) {
                    return "";
                }
                try {
                    String parCode = value;
                    value = BundleUtils.getString(Constants.APP_PARAMS.COMMON_STATUS.toLowerCase().replaceAll("_", ".") + parCode);
                } catch (Exception e) {
                }
                return value;
            }

            @Override
            public Class<String> getModelType() {
                return String.class;
            }

            @Override
            public Class<String> getPresentationType() {
                return String.class;
            }
        });
    }

    public static void convertFieldAppParamTable(CustomPageTable tbl, String propertyId, final String parType) {
        tbl.setConverter(propertyId, new Converter<String, String>() {
            @Override
            public String convertToModel(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                return "";
            }

            @Override
            public String convertToPresentation(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                if (value == null || value.equals("")) {
                    return "";
                }
                try {
                    String parCode = value;
                    if (DataUtil.isInteger(parCode)) {
                        value = BundleUtils.getString(parType.toLowerCase().replace("_", ".") + "." + parCode);
                    }
                } catch (Exception e) {
                }
                return value;
            }

            @Override
            public Class<String> getModelType() {
                return String.class;
            }

            @Override
            public Class<String> getPresentationType() {
                return String.class;
            }
        });
    }

    public static void convertFieldAppParamTable(TreeTable tbl, String propertyId, final String parType) {
        tbl.setConverter(propertyId, new Converter<String, String>() {
            @Override
            public String convertToModel(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                return "";
            }

            @Override
            public String convertToPresentation(String value, Class<? extends String> targetType, Locale locale) throws Converter.ConversionException {
                if (value == null || value.equals("")) {
                    return "";
                }
                try {
                    String parCode = value;
                    if (DataUtil.isInteger(parCode)) {
                        value = BundleUtils.getString(parType.toLowerCase().replace("_", ".") + "." + parCode);
                    }
                } catch (Exception e) {
                }
                return value;
            }

            @Override
            public Class<String> getModelType() {
                return String.class;
            }

            @Override
            public Class<String> getPresentationType() {
                return String.class;
            }
        });
    }

    /**
     * Build label
     *
     * @param value caption of label
     * @param isBold if bold --> label will have bold style
     * @return
     */
    public static Label buildLabel(String value, boolean isBold) {
        Label label = new Label();
        label.setWidth("100%");
        label.setHeight("-1px");
        label.setValue(value);
        label.setImmediate(true);
        if (isBold) {
            label.addStyleName("v-label-bold");
        }
        return label;
    }

    public static TextField buildTextField(String caption, int maxlength, String... valueHint) {
        TextField returnTextField = new TextField();
        if (!DataUtil.isStringNullOrEmpty(caption)) {
            returnTextField.setCaption(BundleUtils.getString(caption));
        }
        returnTextField.setWidth("100%");
        returnTextField.setHeight("-1px");
        returnTextField.setImmediate(true);
        returnTextField.setMaxLength(maxlength);
        if (valueHint.length > 0) {
            returnTextField.setInputPrompt(valueHint[0]);
        }
        return returnTextField;
    }

    public static DateField buildDateField(String caption) {
        DateField returnTextField = new DateField();
        if (!DataUtil.isStringNullOrEmpty(caption)) {
            returnTextField.setCaption(BundleUtils.getString(caption));
        }
        returnTextField.setWidth("100%");
        returnTextField.setHeight("-1px");
        returnTextField.setImmediate(true);
        return returnTextField;
    }

    public static ComboBox buildComboBox(String caption) {
        ComboBox comboBox = new ComboBox();
        comboBox.setCaption(BundleUtils.getString(caption));
        comboBox.setFilteringMode(FilteringMode.CONTAINS);
        comboBox.setWidth("100%");
        comboBox.setHeight("-1px");
        comboBox.setImmediate(true);
        return comboBox;
    }

    public static ComboBox buildComboBox() {
        ComboBox comboBox = new ComboBox();
        comboBox.setFilteringMode(FilteringMode.CONTAINS);
        comboBox.setWidth("100%");
        comboBox.setHeight("-1px");
        comboBox.setImmediate(true);
        return comboBox;
    }

    public static String getDataTextfield(TextField tf) {
        return tf.getValue();
    }

    public static Map<String, AppParamsDTO> putAppParams2MapByCode(List<AppParamsDTO> lstAppParams) {
        Map<String, AppParamsDTO> mapReturn = new HashMap<>();
        if (!DataUtil.isListNullOrEmpty(lstAppParams)) {
            for (AppParamsDTO a : lstAppParams) {
                mapReturn.put(a.getParCode(), a);
            }
        }
        return mapReturn;
    }
}
