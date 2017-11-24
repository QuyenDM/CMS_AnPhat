/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import com.vaadin.ui.Notification;

/**
 *
 * @author Ngocnd6
 */
public class CommonMessages {

    // Message alert when insert, delete, coppy, update... 
    public static void showMessageInsertSuccess(String message) {
        Notification.show(BundleUtils.getString("common.create.sucessfully") + " " + (BundleUtils.getString(message)), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showMessageInsertFail(String message) {
        Notification.show(BundleUtils.getString("common.create.failed") + " " + (BundleUtils.getString(message)), Notification.Type.ERROR_MESSAGE);
    }

    public static void showMessageUpdateSuccess(String message) {
        Notification.show(BundleUtils.getString("common.update.sucessfully") + " " + (BundleUtils.getString(message)), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showMessageCoppySuccess(String message) {
        Notification.show(BundleUtils.getString("common.copy.sucessfully") + " " + (BundleUtils.getString(message)), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showMessageDeleteSuccess(String message) {
        Notification.show(BundleUtils.getString("common.delete.sucessfully") + " " + (BundleUtils.getString(message)), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showMessageImportSuccess(String message) {
        Notification.show(BundleUtils.getString("common.import.sucessfully") + " " + (BundleUtils.getString(message)), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showMessageImportSuccess(String message, int quantity) {
        Notification.show(BundleUtils.getString("common.import.sucessfully.quantity").replace("@q", String.valueOf(quantity)) + " " + (BundleUtils.getString(message)), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showMessageExportSuccess(String message) {
        Notification.show(BundleUtils.getString("common.export.sucessfully") + " " + (BundleUtils.getString(message)), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showUpdateFail(String message) {
        Notification.show(BundleUtils.getString("common.noti.update.fail").replace("@s", BundleUtils.getString(message)), BundleUtils.getString("clickToClose"), Notification.Type.ERROR_MESSAGE);
    }

    public static void showMessageFail(String message) {
        Notification.show(message, BundleUtils.getString("clickToClose"), Notification.Type.ERROR_MESSAGE);
    }

    public static void showInsertFail(String message) {
        Notification.show(BundleUtils.getString("common.noti.insert.fail").replace("@s", BundleUtils.getString(message)), BundleUtils.getString("clickToClose"), Notification.Type.ERROR_MESSAGE);
    }

    public static void showDeleteFail(String message) {
        Notification.show(BundleUtils.getString("common.noti.delete.fail").replace("@s", BundleUtils.getString(message)), BundleUtils.getString("clickToClose"), Notification.Type.ERROR_MESSAGE);
    }

    public static void showCopyFail(String message) {
        Notification.show(BundleUtils.getString("common.noti.copy.fail").replace("@s", BundleUtils.getString(message)), BundleUtils.getString("clickToClose"), Notification.Type.ERROR_MESSAGE);
    }

    //Message validate form input
    public static void showMessageRequired(String message) {
        Notification.show(BundleUtils.getString("common.input.required") + " " + (BundleUtils.getString(message)), Notification.Type.WARNING_MESSAGE);
    }

    public static void showOnlyNegativeNumber(String message) {
        Notification.show(BundleUtils.getString("onlyNegativeNumber") + " " + (BundleUtils.getString(message)), Notification.Type.WARNING_MESSAGE);
    }

    public static void showFomatSizePacking() {
        Notification.show(BundleUtils.getString("error.number.fomatpacksize"), Notification.Type.WARNING_MESSAGE);
    }

    public static void showCompareVolume() {
        Notification.show(BundleUtils.getString("compareVolume"), Notification.Type.WARNING_MESSAGE);
    }

    public static void showDataNotFound() {
        Notification.show(BundleUtils.getString("notFoundData"), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showCompareStartEndDate() {
        Notification.show(BundleUtils.getString("startEndDate"), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showMessageOverMaxLength(String message) {
        Notification.show(BundleUtils.getString("message.error.input.over.maxlength") + " " + (BundleUtils.getString(message)), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showMessageFormatNumber(String message) {
        Notification.show(BundleUtils.getString((BundleUtils.getString(message)) + " " + "message.error.numberformat"), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showGoodsPackRequire() {
        Notification.show(BundleUtils.getString("requireGoodsPack"), Notification.Type.WARNING_MESSAGE);
    }

    public static void invalidSize() {
        Notification.show(BundleUtils.getString("invalidSize"), Notification.Type.WARNING_MESSAGE);
    }

    public static void showChooseFileUpload() {
        Notification.show(BundleUtils.getString("cms.common.message.notselectfileyet"), Notification.Type.WARNING_MESSAGE);
    }

    public static void showFomatFileUpload() {
        Notification.show(BundleUtils.getString("cms.common.message.invalidfileformat"), Notification.Type.WARNING_MESSAGE);
    }

    public static void showValidFileImport() {
        Notification.show(BundleUtils.getString("valid.import.file"), Notification.Type.WARNING_MESSAGE);
    }

    public static void showCancelFail(String message) {
        Notification.show(BundleUtils.getString("common.noti.cancel.fail").replace("@s", BundleUtils.getString(message)), BundleUtils.getString("clickToClose"), Notification.Type.ERROR_MESSAGE);
    }
    
    public static void showExportSuccess(String message) {
        Notification.show(BundleUtils.getString("common.noti.export.success").replace("@s", BundleUtils.getString(message)), BundleUtils.getString("clickToClose"), Notification.Type.ERROR_MESSAGE);
    }
    
    public static void showExportFail(String message) {
        Notification.show(BundleUtils.getString("common.noti.export.fail").replace("@s", BundleUtils.getString(message)), BundleUtils.getString("clickToClose"), Notification.Type.ERROR_MESSAGE);
    }

    public static void showCancelSuccess(String message) {
        Notification.show(BundleUtils.getString("common.noti.cancel.success").replace("@s", BundleUtils.getString(message)), Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showWarningMessage(String message) {
        Notification.show(message, Notification.Type.WARNING_MESSAGE);
    }

    public static void showHumanizedMessage(String message) {
        Notification.show(message, Notification.Type.HUMANIZED_MESSAGE);
    }

    public static void showErrorMessage(String message) {
        Notification.show(message, BundleUtils.getString("clickToClose"), Notification.Type.ERROR_MESSAGE);
    }

    public static String messageRequire(String fieldName) {
        return BundleUtils.getString("message.input.require").
                replace("@name", BundleUtils.getString(fieldName));
    }

}
