/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.controller;

import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.cms.component.WindowProgress;
import com.cms.dto.StaffDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.ExcelReaderXLSX;
import com.cms.utils.ValidateCells;
import com.google.common.collect.Lists;
import com.vaadin.annotations.Push;
import com.vaadin.shared.communication.PushMode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author quyen
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
@Push(PushMode.MANUAL) 
public class CommonUploader implements Upload.Receiver, Upload.SucceededListener, Upload.StartedListener, Serializable {

    protected Upload xmlUpload;
    protected WindowProgress wp;
    protected File tempFile;
    protected String fileName = "";
    protected String mimeType = "";
    protected boolean isSucc = true;
    protected CommonTableFilterPanel tblPanel;
    protected LinkedHashMap<String, CustomTable.Align> HEADER;
    protected StaffDTO staff;
    //
    public FileInputStream fileInput;
    public FileInputStream flieInput;
    public XSSFWorkbook workbookIp;
    // sheet loi
    public FileOutputStream fileOut;
    public XSSFWorkbook workbookEp;
    public XSSFSheet worksheetEp;
    public XSSFCellStyle cellStyle = null;
    public boolean isError = false;
    public HSSFWorkbook hSSFWorkbook = null;
    public XSSFSheet worksheetIp = null;
    public XSSFRow row5 = null;
    public XSSFCell cellB1 = null;
    public String fileErrorName;
    public boolean isCreateSheet = true;
    public StringBuilder err;
    public String messErr = "";

    public CommonUploader(Upload upload, WindowProgress wp) {
        this.xmlUpload = upload;
        this.xmlUpload.addSucceededListener(this);
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        try {
            isCreateSheet = true;
            this.mimeType = mimeType;
            this.fileName = filename;
            File file = new File(Constants.PATH_UPLOAD + "Error");
            file.deleteOnExit();
            if (!filename.equals("")) {
                try {
                    if (mimeType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                            || mimeType.equals("application/vnd.ms-excel")) {
                        if (mimeType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                            tempFile = File.createTempFile(Constants.PATH_UPLOAD + filename, "xlsx");
//                                Notification.show(BundleUtils.getString("common.message.excelfomat"));
                        } else {
                            tempFile = File.createTempFile(Constants.PATH_UPLOAD + filename, "xls");
                        }
                        isSucc = true;
                        tempFile.deleteOnExit();
                    } else {
                        Notification.show(BundleUtils.getString("common.message.invalidfileformat"));
                        return new FileOutputStream(file);
                    }

                    return new FileOutputStream(tempFile);
                } catch (IOException e) {
                    Notification.show(BundleUtils.getString("common.message.invalidfileformat"));
                    return new FileOutputStream(file);
                }
            } else {
                isSucc = false;
                Notification.show(BundleUtils.getString("common.message.notselectfileyet"));
                return new FileOutputStream(file);
            }
        } catch (Exception e) {
            return null;
        }
    }

    //Them thong tin loi err vao danh sach cac dong lstRows
    public void createFileError(String err, List<Integer> lstRows) {
        if (DataUtil.isListNullOrEmpty(lstRows)) {
            return;
        }
        cellStyle = null;
        if (isCreateSheet) {
            try {
                // sheet
                fileInput = new FileInputStream(tempFile);
                fileOut = new FileOutputStream(fileErrorName);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            // sheet loi
            workbookEp = new XSSFWorkbook();
            worksheetEp = workbookEp.createSheet("error");
            cellStyle = null;
            hSSFWorkbook = null;
            worksheetIp = null;
            row5 = null;
            cellB1 = null;
            isCreateSheet = false;
            //
            workbookIp = null;
            if (mimeType.equalsIgnoreCase(Constants.FORMATFILE.EXCEL_XLSX)) {
                try {
                    // xlsx
                    workbookIp = new XSSFWorkbook(fileInput);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else if (mimeType.equalsIgnoreCase(Constants.FORMATFILE.EXCEL_XLS)) { // xls
                HSSFWorkbook hSSFWorkbook = null;
                try {
                    hSSFWorkbook = new HSSFWorkbook(fileInput);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                workbookIp = ExcelReaderXLSX.convertWorkbookHSSFToXSSF(hSSFWorkbook);
            }
            XSSFSheet worksheetNew = workbookIp.getSheetAt(0);
            ExcelReaderXLSX.copySheets(worksheetEp, worksheetNew, 8);
            // style
            cellStyle = CommonUtils.styleCell(workbookEp);
        }
        for (Integer row : lstRows) {
            row5 = worksheetEp.getRow(row + 1);
            if (row5 != null) {
                isError = true;
                cellB1 = row5.createCell(9);
                cellB1.setCellValue(err);
                cellB1.setCellStyle(cellStyle);
            }
        }
    }

    public void createFileError(String err, int i) {
        cellStyle = null;
        if (isCreateSheet) {
            try {
                // sheet
                flieInput = new FileInputStream(tempFile);
                fileOut = new FileOutputStream(fileErrorName);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            workbookIp = null;
            // sheet loi
            workbookEp = new XSSFWorkbook();
            worksheetEp = workbookEp.createSheet("error");
            cellStyle = null;

            hSSFWorkbook = null;
            worksheetIp = null;
            row5 = null;
            cellB1 = null;
            isCreateSheet = false;
            //
            workbookIp = null;
            if (mimeType.equalsIgnoreCase(Constants.FORMATFILE.EXCEL_XLSX)) {
                try {
                    // xlsx
                    workbookIp = new XSSFWorkbook(flieInput);
                } catch (IOException ex) {
                }
            } else if (mimeType.equalsIgnoreCase(Constants.FORMATFILE.EXCEL_XLS)) { // xls
                HSSFWorkbook hSSFWorkbook = null;
                try {
                    hSSFWorkbook = new HSSFWorkbook(flieInput);
                } catch (IOException ex) {
                }
                workbookIp = ExcelReaderXLSX.convertWorkbookHSSFToXSSF(hSSFWorkbook);
            }
            XSSFSheet worksheetIp = workbookIp.getSheetAt(0);
            ExcelReaderXLSX.copySheets(worksheetEp, worksheetIp, 8);
            // style
            cellStyle = CommonUtils.styleCell(workbookEp);
        }
        row5 = worksheetEp.getRow(i + 1);
        if (row5 != null) {
            cellB1 = row5.createCell(9);
            cellB1.setCellValue(err);
            cellB1.setCellStyle(cellStyle);
        }
    }

    //Xuat ra file loi
    public void exportFileErr(String mess, String fileErrorName) {
        try {
            workbookEp.write(fileOut);
            fileOut.close();
            fileOut.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        File fileError = new File(fileErrorName);
        fileError.deleteOnExit();
        Resource resource = new FileResource(fileError);
        Page.getCurrent().open(resource, null, false);
//            Notification.show(mess, Notification.Type.WARNING_MESSAGE);
        wp.close();
        UI.getCurrent().setPollInterval(-1);
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent event) {

        if (!isSucc) {
            return;
        }
        if (fileName.contains(".")) {
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        }
        fileErrorName = Constants.PATH_UPLOAD + fileName + "_Error.xlsx";
        isError = false;

        WorkThread workThread = new WorkThread();
        workThread.start();

    }

    @Override
    public void uploadStarted(Upload.StartedEvent event) {
        long contentLength = event.getContentLength();
        if (contentLength > Constants.FILE_SIZE_IMPORT && contentLength != -1) {
            event.getUpload().interruptUpload();
            CommonUtils.showContentLengthValid();
        }
    }

    class WorkThread extends Thread {

        @Override
        public void run() {
            UI.getCurrent().access(new Runnable() {
                @Override
                public void run() {
                    try {
                        wp = new WindowProgress();
                        UI.getCurrent().addWindow(wp);
                        UI.getCurrent().push();
                        UI.getCurrent().setPollInterval(500);
                        uploadFile();
                    } catch (IllegalArgumentException | NullPointerException e) {
                        e.printStackTrace();
                    } finally {
                        wp.close();
                        System.gc();
                        UI.getCurrent().setPollInterval(-1);
                    }
                }
            });
        }
    }

    //Overide lại ham upload nay
    public void uploadFile() {
        try {
            List lstUpload;
            List<ValidateCells> lstValidateCells = new ArrayList<>();
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, true, 50));//ma hh
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 400));//ten hh
            lstValidateCells.add(new ValidateCells(DataUtil.DOUBLE, true, 10));// so luong                    
            lstValidateCells.add(new ValidateCells(DataUtil.LONG, true, 1));// trang thai hh
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 25));//So thung
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 50));//tu serial
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 50));//den serial
            lstValidateCells.add(new ValidateCells(DataUtil.STRING, false, 25));//ma vi tri
            lstUpload = DataUtil.isValidExcells(mimeType, tempFile, 0, 1, 1, 8, 3, lstValidateCells);
            if (lstUpload == null) {
                Notification.show(BundleUtils.getString("valid.import.file"), Notification.Type.WARNING_MESSAGE);
                return;
            }
            //LAY DANH DACH HANG HOA - CELL DA NHAP
            Object[] tmp;
            String cell;
            List<String> lstCell = Lists.newArrayList();
            String strLstCell = "";
            for (Object lst1 : lstUpload) {
                tmp = (Object[]) lst1;
                cell = (String) tmp[7];
                if (!lstCell.contains(cell)) {
                    lstCell.add(cell);
                    strLstCell = strLstCell + "," + cell;
                }
            }
            Object[] temp;
            String goodsCode;
            String goodsName;
            String quantity;
            String state;
            String barcode;
            String fromSerial;
            String toSerial;
            String cellCode;
            Long amountReal;
            Long amount;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            wp.close();
            UI.getCurrent().setPollInterval(-1);
        }
    }
}
