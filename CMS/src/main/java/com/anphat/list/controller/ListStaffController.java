/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.abcd
 */
package com.anphat.list.controller;

import com.anphat.list.ui.MapStaffRolesDiaglog;
import com.cms.login.ws.WSStaff;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Runo;
import com.cms.component.CommonFunctionTableFilter;
import com.cms.component.CustomPageTableFilter;
import com.cms.dto.DepartmentDTO;
import com.cms.dto.StaffDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CasBundleUtils;
import com.cms.utils.CommonMessages;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.TableUtils;
import com.google.common.collect.Lists;
import com.vwf5.base.utils.ConditionBean;
import java.util.LinkedHashMap;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author hungkv
 */
public class ListStaffController implements Serializable {

    CustomPageTableFilter<IndexedContainer> tblStaffs;
    CommonTableFilterPanel staffTablePanel;
    DepartmentDTO departmentDTO = new DepartmentDTO();
    StaffDTO staffDTO = new StaffDTO();
    List<DepartmentDTO> lstDepartmentDTOs = Lists.newArrayList();
    List<StaffDTO> lstStaffDTOs = Lists.newArrayList();
    LinkedHashMap<String, CustomTable.Align> headerData = BundleUtils.getHeadersFilter("staff.header");
    private BeanItemContainer<StaffDTO> itemContainer;
    private final String captionfieldsetListStaff = BundleUtils.getString("caption.dept.staff.listStaffInfo");
    Button btnExcelExport;
    Button btnDel;
    //test ibm
    static final String CODE = "code";
    static final String NAME = "name";
    static final String BIRTHDAY = "birthDate";
    static final String EMAIL = "email";
    static final String PHONE = "telNumber";
    static final String DEPARTMENT = "deptName";
    static final String TYPE = "staffTypeName";
    static final String STATUS = "statusName";
    String lblDetail = BundleUtils.getString("add.staff.for.stock");
//    private DialogCreateStockForStaffController addStock2StaffController;
    private Map<String, DepartmentDTO> mapId2DeparmentDTO;
    private final String PATH_EXPORT = CasBundleUtils.getResourceDefault("path_export");
    List<ConditionBean> lstConditionStaffCustomer = new ArrayList<>();
//    List<MapStaffCustomerDTO> listMapStaffCustomer = Lists.newArrayList();
    private TableUtils tableUtils;

    public ListStaffController(CommonTableFilterPanel empTablePanel) {
        CommonUtils.setVisibleBtnTablePanel(empTablePanel, true, false, true, true);
        this.staffTablePanel = empTablePanel;
        this.tblStaffs = staffTablePanel.getMainTable();
        this.btnExcelExport = staffTablePanel.getExportButton();//goi button export tu tooltip
        btnDel = empTablePanel.getDelContraintButton();
        empTablePanel.getDeleteButton().setVisible(false);
        initTable();
        addActionListener();
        staffTablePanel.getImportButton().setVisible(false);
        staffTablePanel.getExportButton().setVisible(false);
    }

    //QuyenDM them nut danh sach khach hang gan voi nhan vien
    private void initTable() {
        itemContainer = new BeanItemContainer<>(StaffDTO.class);
        //add detail link into tblstaff
        tblStaffs.addGeneratedColumn("resetPassword", new CustomTable.ColumnGenerator() {
            @Override
            public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
                final Button linkDetails = new Button();
                linkDetails.setIcon(FontAwesome.KEY);
                linkDetails.setStyleName(Runo.BUTTON_LINK);
                linkDetails.addStyleName("v-button-link-left");
                linkDetails.addClickListener(new Button.ClickListener() {

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        final StaffDTO staffDTO = (StaffDTO) itemId;
                        ConfirmDialog.show(UI.getCurrent(), "Reset mật khẩu",
                                "Đặt lại mật khẩu mặc định cho tài khoản " + staffDTO.getName(),
                                "Đồng ý", "Huỷ bỏ", new ConfirmDialog.Listener() {
                            @Override
                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    staffDTO.setPassword(DataUtil.md5(BundleUtils.getStringCas("password_default")));
                                    // Confirmed to continue
                                    String result = WSStaff.updateStaff(staffDTO);
                                    if (Constants.SUCCESS.equalsIgnoreCase(result)) {
                                        CommonMessages.showMessageUpdateSuccess("pass");
                                    }
                                }
                            }
                        });
                    }
                });
                linkDetails.setDescription(BundleUtils.getString("resetPassword"));
                return linkDetails;
            }
        });
        tblStaffs.addGeneratedColumn("detailRole", new CustomTable.ColumnGenerator() {

            @Override
            public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
                final Button btnAddRole = new Button(BundleUtils.getString("detail.roles"));
                btnAddRole.setStyleName(Runo.BUTTON_LINK);
                btnAddRole.addStyleName("v-button-link-left");
                btnAddRole.addClickListener(new Button.ClickListener() {

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        StaffDTO staffDTO = (StaffDTO) itemId;
                        MapStaffRolesDiaglog diaglog = new MapStaffRolesDiaglog(BundleUtils.getString("map.staff.roles.view"));
                        diaglog.setTblRolesVisiableOnly();

                        MapStaffRolesController controller = new MapStaffRolesController(diaglog);
                        controller.initOnlyView(staffDTO);
                        UI.getCurrent().addWindow(diaglog);
                    }
                });
                btnAddRole.setDescription(BundleUtils.getString("lb.header.staff.addCustomer.decr"));
                return btnAddRole;
            }
        });
        //160316 NgocND6 them chuc nang hang hoa quan ly cua tung nhan vien
        tblStaffs.addGeneratedColumn("goodsManage", new CustomTable.ColumnGenerator() {

            @Override
            public Object generateCell(CustomTable source, final Object itemId, Object columnId) {
                final Button linkDetails = new Button(BundleUtils.getString("lb.header.staff.goodsManage"));
                linkDetails.setStyleName(Runo.BUTTON_LINK);
                linkDetails.addStyleName("v-button-link-left");
                linkDetails.addClickListener(new Button.ClickListener() {

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
//                        StaffDTO staffDTO = (StaffDTO) itemId;
//                        if (staffDTO.getStatus().equalsIgnoreCase("1")) {
//                            getListMapStaffCustomer(staffDTO);
//                            if (listMapStaffCustomer != null) {
//                                DialogGoodsManagement dialogGoodsManagement = new DialogGoodsManagement(BundleUtils.getString("transfer.goods.manage.managoodsassignstaff"));
//                                dialogGoodsManagement.initDialog();
//                                DialogGoodsManagementController dgmc = new DialogGoodsManagementController(staffDTO, dialogGoodsManagement);
//                                UI.getCurrent().addWindow(dialogGoodsManagement);
//                            }else{
//                                Notification.show(BundleUtils.getString("transfer.goods.manage.staffdontassigncust"), Notification.Type.WARNING_MESSAGE);
//                            }
//                        } else {
//                            Notification.show(BundleUtils.getString("dept.staff.alert.message.notActive"));
//                        }
                    }
                });
                linkDetails.setDescription(BundleUtils.getString("lb.header.staff.addCustomer.decr"));
                return linkDetails;
            }
        });
        tableUtils = new TableUtils();
        tableUtils.generateColumn(tblStaffs);
        CommonFunctionTableFilter.initTable(staffTablePanel, headerData, itemContainer,
                captionfieldsetListStaff, 5, "lb.header.staff");
        CommonUtils.convertFieldAppParamTable(tblStaffs, Constants.STAFF.STATUS, Constants.APP_PARAMS.COMMON_STATUS);
        CommonUtils.convertFieldAppParamTable(tblStaffs, Constants.STAFF.STAFF_TYPE, Constants.APP_PARAMS.STAFF_TYPE);
//        tblStaffs.setColumnHeader("addStock", BundleUtils.getString("add.staff.for.stock"));

    }

    //NgocND6 240316 check phan quyen khach hang cho tung nhan vien
    /**
     * Set conditon cho lay danh sach khach hang trong bang map
     *
     * @param sdto
     * @return
     */
    public List<ConditionBean> listConditionSearchStaffCustomer(StaffDTO sdto) {
        if (sdto.getStaffId() != null) {
            lstConditionStaffCustomer.add(new ConditionBean("staffId", sdto.getStaffId(), ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.NUMBER));
        }
        return lstConditionStaffCustomer;

    }

    /**
     * Map so sanh de hien thi ket qua tim kiem chi hien thi tim kiem hang hoa
     * theo khach hang da duoc gan quyen voi nhan vien
     *
     * @param staffDTO
     */
    public void getListMapStaffCustomer(StaffDTO staffDTO) {
        try {
            lstConditionStaffCustomer.clear();
            listConditionSearchStaffCustomer(staffDTO);
//            listMapStaffCustomer = WSMapStaffCustomer.getListMapStaffCustomerByCondition(lstConditionStaffCustomer, 0, Integer.MAX_VALUE, "", "mapId");
        } catch (Exception e) {
        }
    }

    //get list
    public List<StaffDTO> getListStaffDTO(StaffDTO staffDTO) {
        try {
            lstStaffDTOs = WSStaff.getListStaffDTO(staffDTO, 0, Constants.INT_100, Constants.ASC, "code");
        } catch (Exception e) {
            lstStaffDTOs = new ArrayList<>();
            e.printStackTrace();
        }
        return lstStaffDTOs;
    }

    //set data
    public void setDataTable(List<StaffDTO> lstStaff) {
        //fill data
        itemContainer.removeAllItems();
        if (!DataUtil.isListNullOrEmpty(lstStaff)) {
            itemContainer.addAll(lstStaff);
            btnExcelExport.setEnabled(true);
        } else {
            btnExcelExport.setEnabled(false);
        }
        CommonFunctionTableFilter.refreshTable(staffTablePanel, headerData, itemContainer);
    }

    public boolean checkDuplicate(String code) {
        //code = searchPriceFactorsForm.getTxtFactorCode().getValue().toString();
        getListStaffDTO(staffDTO);
        if (code != null) {
            for (StaffDTO staffCode : lstStaffDTOs) {
                if (code.equalsIgnoreCase(staffCode.getCode().toLowerCase())) {
                    return false;
                } else {
                    return true;
                }
            }
        } else {

        }
        return true;
    }

    private void addActionListener() {

        btnExcelExport.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                List<StaffDTO> lstStaffInfo = new ArrayList<>();
                Collection collection = (Collection) tblStaffs.getItemIds();
                lstStaffInfo.addAll(collection);
                if (!DataUtil.isListNullOrEmpty(lstStaffInfo)) {
                    File fileExport = exportFile(lstStaffInfo);
                    Resource res = new FileResource(fileExport);
                    Page.getCurrent().open(res,
                            BundleUtils.getString("label.caption.download").
                            replace("@name", BundleUtils.getString("staff.department.listStaff")), true);
                } else {
                    //Thong bao khong co du lieu xuat file
                    CommonMessages.showWarningMessage(BundleUtils.getString("common.warning.export.noData"));
                }
            }
        });

    }

    //xu li chi tiet
    public File exportFile(List<StaffDTO> lstStaffInfo) {
        try {
            FileOutputStream fileOut = new FileOutputStream(PATH_EXPORT + Constants.FILE_NAME.LIST_STAFF);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet("danhsachnhanvien");
            HSSFCellStyle cellStyle;
            HSSFCellStyle rowStyle;

            HSSFCellStyle cellStyleLeft = null;
            HSSFCellStyle cellStyleRight = null;
            //style left
            cellStyleLeft = workbook.createCellStyle();
            cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            //phai
            cellStyleRight = workbook.createCellStyle();
            cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
            cellStyleRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyleRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyleRight.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyleRight.setBorderTop(HSSFCellStyle.BORDER_THIN);

//                //header bang
            HSSFRow row5 = worksheet.createRow(0);

            HSSFCell cellA1 = row5.createCell(0);
            cellA1.setCellValue(BundleUtils.getString("STT"));
            cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setWrapText(true);
            //rowstyle
            rowStyle = workbook.createCellStyle();
            rowStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            rowStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            rowStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            rowStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            rowStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            rowStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            rowStyle.setFillForegroundColor(HSSFColor.WHITE.index);
            rowStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            rowStyle.setWrapText(true);

            cellA1.setCellStyle(cellStyle);

            HSSFCell cellB1 = row5.createCell(1);
            cellB1.setCellValue(BundleUtils.getString("cms.StaffInfo.code"));
            cellB1.setCellStyle(cellStyle);

            HSSFCell cellC1 = row5.createCell(2);
            cellC1.setCellValue(BundleUtils.getString("cms.StaffInfo.name"));
            cellC1.setCellStyle(cellStyle);
            HSSFCell cellD1 = row5.createCell(3);
            cellD1.setCellValue(BundleUtils.getString("cms.StaffInfo.email"));
            cellD1.setCellStyle(cellStyle);
            HSSFCell cellE1 = row5.createCell(4);
            cellE1.setCellValue(BundleUtils.getString("cms.StaffInfo.birthdate"));
            cellE1.setCellStyle(cellStyle);
            HSSFCell cellF1 = row5.createCell(5);
            cellF1.setCellValue(BundleUtils.getString("cms.StaffInfo.phoneNumber"));
            cellF1.setCellStyle(cellStyle);
            HSSFCell cellG1 = row5.createCell(6);
            cellG1.setCellValue(BundleUtils.getString("cms.StaffInfo.deptName"));
            cellG1.setCellStyle(cellStyle);
            HSSFCell cellH1 = row5.createCell(7);
            cellH1.setCellValue(BundleUtils.getString("cms.StaffInfo.staffType"));
            cellH1.setCellStyle(cellStyle);
            HSSFCell cellI1 = row5.createCell(8);
            cellI1.setCellValue(BundleUtils.getString("cms.StaffInfo.status"));
            cellI1.setCellStyle(cellStyle);
            //content bang
            if (!lstStaffInfo.isEmpty()) {
                int j = 0;
                for (StaffDTO staff : lstStaffInfo) {
                    HSSFRow row = worksheet.createRow(j + 1);

                    HSSFCell cellA = row.createCell(0);
                    cellA.setCellValue(j + 1);
                    cellA.setCellStyle(rowStyle);
                    HSSFCell cellB = row.createCell(1);
                    cellB.setCellValue((staff.getDeptId() == null) ? Constants.NULL : staff.getCode());
                    cellB.setCellStyle(cellStyleLeft);
                    HSSFCell cellC = row.createCell(2);
                    cellC.setCellValue((staff.getCode() == null) ? Constants.NULL : staff.getName());
                    cellC.setCellStyle(cellStyleLeft);
                    HSSFCell cellD = row.createCell(3);
                    cellD.setCellValue((staff.getName() == null) ? Constants.NULL : staff.getEmail());
                    cellD.setCellStyle(cellStyleLeft);
                    HSSFCell cellE = row.createCell(4);
//                    cellE.setCellValue((staff.getEmail() == null) ? Constants.NULL : staff.getBirthDate());
                    cellE.setCellStyle(cellStyleLeft);
                    HSSFCell cellF = row.createCell(5);
                    cellF.setCellValue((staff.getTelNumber() == null) ? Constants.NULL : staff.getTelNumber());
                    cellF.setCellStyle(cellStyleLeft);
                    HSSFCell cellG = row.createCell(6);
//                    cellG.setCellValue((staff.getDeptName() == null) ? Constants.NULL : staff.getDeptName());
                    cellG.setCellStyle(cellStyleLeft);
                    HSSFCell cellH = row.createCell(7);
                    cellH.setCellValue((staff.getStaffType() == null) ? Constants.NULL : BundleUtils.getString("staff.type." + DataUtil.getStringEscapeHTML4(staff.getStaffType())));
                    cellH.setCellStyle(cellStyleLeft);
                    HSSFCell cellI = row.createCell(8);
                    cellI.setCellValue((staff.getStatus() == null) ? Constants.NULL : BundleUtils.getString("common.status." + DataUtil.getStringEscapeHTML4(staff.getStatus())));
                    cellI.setCellStyle(cellStyleLeft);
                    j++;
                }
                //Set Width
                for (int i = 0; i <= 0; i++) {
                    worksheet.setColumnWidth(i, 2000);
                }
                for (int i = 1; i <= 7; i++) {
                    worksheet.setColumnWidth(i, 5000);
                }
                for (int i = 8; i <= 10; i++) {
                    worksheet.setColumnWidth(i, 3000);
                }
            }
            try {
                workbook.write(fileOut);
                fileOut.flush();
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File file = new File(PATH_EXPORT + Constants.FILE_NAME.LIST_STAFF);
        return file;

    }

    public Button getBtnDel() {
        return btnDel;
    }

    public void setBtnDel(Button btnDel) {
        this.btnDel = btnDel;
    }

    public Map<String, DepartmentDTO> getMapId2DeparmentDTO() {
        return mapId2DeparmentDTO;
    }

    public void setMapId2DeparmentDTO(Map<String, DepartmentDTO> mapId2DeparmentDTO) {
        this.mapId2DeparmentDTO = mapId2DeparmentDTO;
    }

}
