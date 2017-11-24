package com.anphat.customer.ui;

import com.anphat.customer.controller.ExportContractToDocController;
import com.cms.component.CommonDialog;
import com.cms.component.GridManyButton;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.ContractTemplateListDTO;
import com.cms.dto.CustomerContactDTO;
import com.cms.dto.CustomerDTO;
import com.cms.dto.CustomerTHAPDTO;
import com.cms.dto.PriceInfoDTO;
import com.cms.dto.TermInformationDTO;
import com.cms.login.ws.WSContractTemplateList;
import com.cms.login.ws.WSCustomer;
import com.cms.login.ws.WSPriceInfo;
import com.cms.ui.CommonButtonClickListener;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.DateUtil;
import com.cms.utils.ExportExcell;
import com.cms.utils.FormatExcell;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vwf5.base.utils.ConditionBean;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 *
 * @author quyen
 */
public class CustomerDetailToCreateContractDialog extends CommonDialog {

    private TextField txtTaxCode;//MST
    private TextField txtName;//Ten cong ty
    private TextField txtTelNumber;//So dien thoai
    private TextField txtEmail;//Email
    private TextField txtFax;//Fax
    private TextField txtOfficeAddress;//Dia chi tru so
    private TextField txtDeployAddress;//Dia chi giao dich
    private TextField txtTaxDepartment;//Chi cuc thue
    //Thong tin nguoi dai dien
    private TextField txtTenNguoiDaidien;//Nguoi dai dien
    private TextField txtCMND;//Chung minh thu
    private TextField txtNgaycapCMND;//Ngay cap cmt
    private ComboBox cboChuvuNguoiDaidien;//Chuc vu nguoi dai dien
    private TextField txtEmailNguoiDaidien;
    private TextField txtSDTNguoiDaidien;
    //Thong tin nguoi lien he
    private ComboBox cboChonNguoilienhe;//Chon nguoi lien he
    private TextField txtTenNguoiLienhe;//Ten nguoi lien he
    private ComboBox cboChucvuNguoiLienhe;//Ten nguoi lien he
    private TextField txtSDTNguoiLienhe;//SDT nguoi lien he
    private TextField txtEmailNguoiLienhe;//Email nguoi lien he
    //Thong tin dich vu su dung
    private ComboBox cboNhacungcap;
    private ComboBox cboGoicuoc;
    private ComboBox cboHinhthuc;
    private ComboBox cboBieuMau;
    private TextField tfSotienCuoc;
    private TextField tfSotienChietkhau;
    private GridLayout gridThongtinChung;
    private GridLayout gridThongtinLienHe;
    private GridLayout gridGoicuoc;
    private Button btnSave;
    private Button btnCreateDoc;
    private Button btnEmail;
    private Button btnClose;
    private Button btnExportExcel;

    private List<ContractTemplateListDTO> lstContractTemplate;
    private List<ContractTemplateListDTO> lstContractTemplateFilered;
    private List<AppParamsDTO> lstChucvu;
    private List<AppParamsDTO> lstProvider;
    private List<AppParamsDTO> lstHinhthuc;
    private List<PriceInfoDTO> lstGoicuoc;
    private List<PriceInfoDTO> lstGoicuocFiltered;
    private ComboComponent cboUtils;
    private List<CustomerContactDTO> lstCustomerContactDTO;
    private CustomerDTO customer;
    private Map<String, AppParamsDTO> mapChucvu;

    public CustomerDetailToCreateContractDialog() {
        super.setInfo("100%", "-1px", BundleUtils.getString("export.contract.template"));
        getDatas();
        buildComponents();
    }

    private void getDatas() {
        try {
            ContractTemplateListDTO searchDTO = new ContractTemplateListDTO();
            searchDTO.setStatus(Constants.ACTIVE);
            lstContractTemplate = WSContractTemplateList.getListContractTemplateListDTO(searchDTO, 0, Constants.INT_1000, "asc", "code");
        } catch (Exception ex) {
            lstContractTemplate = new ArrayList<>();
        }
        try {
            lstGoicuoc = WSPriceInfo.getListPriceInfoDTO(new PriceInfoDTO(), 0, Constants.INT_1000, "asc", "code");
        } catch (Exception ex) {
            lstGoicuoc = new ArrayList<>();
        }
        lstChucvu = com.cms.utils.DataUtil.getListApParams(Constants.APP_PARAMS.CUSTOMER_CONTACT_REGENCY);
        try {
            mapChucvu = DataUtil.buildHasmap(lstChucvu, Constants.APP_PARAMS.PAR_CODE);
        } catch (IllegalAccessException | NoSuchMethodException | IllegalArgumentException ex) {
            Logger.getLogger(CustomerDetailToCreateContractDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        lstProvider = com.cms.utils.DataUtil.getListApParams(Constants.APP_PARAMS.PROVIDER);
        lstHinhthuc = com.cms.utils.DataUtil.getListApParams(Constants.APP_PARAMS.TYPE_CONTRACT);
        cboUtils = new ComboComponent();
    }

    private void buildComponents() {
        buildGridThongtinChung();
        buildGridThongtinLienHe();
        buildGridGoicuoc();
        buildGridButton();
        addValueChangeListener();
        addButtonClickListener();
    }

    private void buildGridThongtinChung() {
        gridThongtinChung = new GridLayout(4, 4);
        CommonUtils.setBasicAttributeLayout(gridThongtinChung, BundleUtils.getString("customer.common.infor"), true);
        txtTaxCode = CommonUtils.buildTextField("customer.taxCode", 14);

        txtName = CommonUtils.buildTextField("customer.name", 200);
        txtName.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                String code = event.getText();
                if (!DataUtil.isStringNullOrEmpty(code)) {
                    txtName.setValue(code.toUpperCase());
                }
            }
        });
        txtName.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.EAGER);
        txtEmail = CommonUtils.buildTextField("customer.email", 50);
        txtTelNumber = CommonUtils.buildTextField("customer.telNumber", 50);
        txtFax = CommonUtils.buildTextField("customer.fax", 50);
        txtOfficeAddress = CommonUtils.buildTextField("customer.officeAddress", 500);
        txtDeployAddress = CommonUtils.buildTextField("customer.deployAddress", 500);
        txtTaxDepartment = CommonUtils.buildTextField("customer.taxAuthority", 100);

        txtTenNguoiDaidien = CommonUtils.buildTextField("customer.representativeName", 500);
        txtCMND = CommonUtils.buildTextField("customer.representativeId", 20);
        txtNgaycapCMND = CommonUtils.buildTextField("customer.ngaycapCMND", 20);
        cboChuvuNguoiDaidien = CommonUtils.buildComboBox("customer.contact.regency");
        txtEmailNguoiDaidien = CommonUtils.buildTextField("customer.contact.email", 100);
        txtSDTNguoiDaidien = CommonUtils.buildTextField("customer.contact.telNumber", 100);

        cboUtils.fillDataCombo(cboChuvuNguoiDaidien, Constants.NULL, Constants.NULL, lstChucvu, Constants.APP_PARAMS.CUSTOMER_CONTACT_REGENCY);

        gridThongtinChung.addComponent(txtTaxCode, 0, 0);
        gridThongtinChung.addComponent(txtName, 1, 0, 2, 0);
        gridThongtinChung.addComponent(txtEmail, 3, 0);

        gridThongtinChung.addComponent(txtDeployAddress, 0, 1, 1, 1);
        gridThongtinChung.addComponent(txtOfficeAddress, 2, 1, 3, 1);

        gridThongtinChung.addComponent(txtTelNumber, 0, 2);
        gridThongtinChung.addComponent(txtFax, 1, 2);
        gridThongtinChung.addComponent(txtTaxDepartment, 2, 2);
        gridThongtinChung.addComponent(txtCMND, 3, 2);
//        gridThongtinChung.addComponent(txtNgaycapCMND, 4, 2);

        gridThongtinChung.addComponent(txtTenNguoiDaidien, 0, 3);
        gridThongtinChung.addComponent(cboChuvuNguoiDaidien, 1, 3);
        gridThongtinChung.addComponent(txtSDTNguoiDaidien, 2, 3);
        gridThongtinChung.addComponent(txtEmailNguoiDaidien, 3, 3);

        mainLayout.addComponent(gridThongtinChung);
    }

    private void buildGridThongtinLienHe() {
        gridThongtinLienHe = new GridLayout(5, 1);
        CommonUtils.setBasicAttributeLayout(gridThongtinLienHe, BundleUtils.getString("customer.contact"), true);
        cboChonNguoilienhe = CommonUtils.buildComboBox("label.choose.contact");
        txtTenNguoiLienhe = CommonUtils.buildTextField("customerContact.name", 200);
        cboChucvuNguoiLienhe = CommonUtils.buildComboBox("customer.contact.regency");
        txtSDTNguoiLienhe = CommonUtils.buildTextField("customer.contact.telNumber", 50);
        txtEmailNguoiLienhe = CommonUtils.buildTextField("customer.contact.email", 100);

        cboUtils.fillDataCombo(cboChucvuNguoiLienhe, Constants.NULL, Constants.NULL, lstChucvu, Constants.APP_PARAMS.CUSTOMER_CONTACT_REGENCY);

//        gridThongtinLienHe.addComponent(cboChonNguoilienhe, 0, 0);
//        gridThongtinLienHe.addComponent(txtTenNguoiLienhe, 1, 0);
//        gridThongtinLienHe.addComponent(cboChucvuNguoiLienhe, 2, 0);
//        gridThongtinLienHe.addComponent(txtSDTNguoiLienhe, 3, 0);
//        gridThongtinLienHe.addComponent(txtEmailNguoiLienhe, 4, 0);
        mainLayout.addComponent(gridThongtinLienHe);
    }

    private void buildGridGoicuoc() {
        gridGoicuoc = new GridLayout(6, 1);
        CommonUtils.setBasicAttributeLayout(gridGoicuoc, BundleUtils.getString("information.package"), true);

        cboNhacungcap = CommonUtils.buildComboBox("term.information.provider");
        cboNhacungcap.setRequired(true);
        cboGoicuoc = CommonUtils.buildComboBox("package");
        cboGoicuoc.setRequired(true);

        cboHinhthuc = CommonUtils.buildComboBox("type.contract.register");
        cboHinhthuc.setRequired(true);

        cboBieuMau = CommonUtils.buildComboBox("contract.template");
        cboBieuMau.setRequired(true);
        tfSotienCuoc = CommonUtils.buildTextField("value.contract", 15);
        tfSotienChietkhau = CommonUtils.buildTextField("discount", 15);
        gridGoicuoc.addComponent(cboNhacungcap, 0, 0);
        gridGoicuoc.addComponent(cboHinhthuc, 1, 0);
        gridGoicuoc.addComponent(cboBieuMau, 2, 0);
        gridGoicuoc.addComponent(cboGoicuoc, 3, 0);
        gridGoicuoc.addComponent(tfSotienCuoc, 4, 0);
        gridGoicuoc.addComponent(tfSotienChietkhau, 5, 0);
        mainLayout.addComponent(gridGoicuoc);
    }

    private void buildGridButton() {
        GridManyButton gridManyButton = new GridManyButton(new String[]{Constants.BUTTON_SAVE, Constants.BUTTON_EXPORT, Constants.BUTTON_DETAIL, Constants.BUTTON_DEFAULT, Constants.BUTTON_CANCEL});
        btnSave = gridManyButton.getBtnCommon().get(0);
        btnCreateDoc = gridManyButton.getBtnCommon().get(1);
        btnCreateDoc.setCaption(BundleUtils.getString("common.button.export.word"));
        btnCreateDoc.setIcon(new ThemeResource(Constants.ICON.DOCX));
        btnCreateDoc.setEnabled(false);
        btnEmail = gridManyButton.getBtnCommon().get(2);
        btnEmail.setCaption(BundleUtils.getString("common.button.email"));
        btnEmail.setIcon(FontAwesome.MAIL_FORWARD);

        btnExportExcel = gridManyButton.getBtnCommon().get(3);
        btnExportExcel.setCaption(BundleUtils.getString("common.button.exportFile"));
        btnExportExcel.setIcon(new ThemeResource(Constants.ICON.EXPORT_EXCEL));

        btnClose = gridManyButton.getBtnCommon().get(4);
        mainLayout.addComponent(gridManyButton);
    }

    public void initDialog(CustomerDTO customer, List<CustomerContactDTO> lstCustomerContactDTO) {
        this.customer = customer;
        this.lstCustomerContactDTO = lstCustomerContactDTO;
        if (!DataUtil.isListNullOrEmpty(lstCustomerContactDTO)) {
            gridThongtinLienHe.setColumns(5);
            gridThongtinLienHe.addComponent(cboChonNguoilienhe, 0, 0);
            gridThongtinLienHe.addComponent(txtTenNguoiLienhe, 1, 0);
            gridThongtinLienHe.addComponent(cboChucvuNguoiLienhe, 2, 0);
            gridThongtinLienHe.addComponent(txtSDTNguoiLienhe, 3, 0);
            gridThongtinLienHe.addComponent(txtEmailNguoiLienhe, 4, 0);
            cboUtils.setValues(cboChonNguoilienhe, lstCustomerContactDTO, "name");
        } else {
            gridThongtinLienHe.setColumns(4);
            gridThongtinLienHe.addComponent(txtTenNguoiLienhe, 0, 0);
            gridThongtinLienHe.addComponent(cboChucvuNguoiLienhe, 1, 0);
            gridThongtinLienHe.addComponent(txtSDTNguoiLienhe, 2, 0);
            gridThongtinLienHe.addComponent(txtEmailNguoiLienhe, 3, 0);
        }
        txtTaxCode.setValue(DataUtil.getStringNullOrZero(customer.getTaxCode()));
        if (!DataUtil.isStringNullOrEmpty(customer.getName())) {
            txtName.setValue(customer.getName().trim().toUpperCase());
        }
        getCompany(customer.getTaxCode());
        initGridGoicuoc();
    }

    public void addBtnCreateDocListener(Button.ClickListener e) {
        btnCreateDoc.addClickListener(e);
    }

    public void setContactInformation(TermInformationDTO termInformationDTO) {
        if (termInformationDTO!= null){
            txtSDTNguoiLienhe.setValue(termInformationDTO.getPhone());
            txtEmailNguoiLienhe.setValue(termInformationDTO.getEmail());
        }
    }

    public void addBtnCreateDocListener(CustomerDTO customer) {
        btnCreateDoc.addClickListener(new CommonButtonClickListener() {
            ExportContractToDocController docController;

            @Override
            public void execute() throws Exception {
                if (validate()) {
                    try {
                        docController = new ExportContractToDocController(getValueInputed());
                        docController.generateFile(customer);
                    } catch (Exception e) {
                        CommonUtils.showErrorMessage("err.cannot.read.template");
                    }
                }
                btnCreateDoc.setEnabled(true);
            }
        });
    }

    public void addBtnSaveListener() {
        btnSave.addClickListener(event -> {
            if (isValidated()) {
                Map<String, String> mapInputValue = getValueInputed();
                customer.setOfficeAddress(mapInputValue.get(Constants.REPORT.OFFICE_ADDRESS));
                customer.setDeployAddress(mapInputValue.get(Constants.REPORT.DEPLOY_ADDRESS));
                customer.setRepresentativeName(mapInputValue.get(Constants.REPORT.NGUOI_DAIDIEN));
                customer.setRepresentativeId(mapInputValue.get(Constants.REPORT.CMND));
                customer.setEmail(mapInputValue.get(Constants.REPORT.EMAIL));
                customer.setFax(mapInputValue.get(Constants.REPORT.FAX));
                customer.setTelNumber(mapInputValue.get(Constants.REPORT.TEL_NUMBER));

                String updateStatus = WSCustomer.updateCustomer(customer);
                if (Constants.SUCCESS.equals(updateStatus)) {
                    CommonUtils.showUpdateSuccess(BundleUtils.getString("customer.management.header.customerinfo"));
                    btnCreateDoc.setEnabled(true);
                } else {
                    CommonUtils.showUpdateFail(BundleUtils.getString("customer.management.header.customerinfo"));
                }
            }
            CommonUtils.enableButtonAfterClick(event);
        });
    }

    public boolean isValidated() {
        AppParamsDTO nhacungcap = (AppParamsDTO) cboNhacungcap.getValue();
        if (nhacungcap == null) {
            CommonUtils.showMessageRequired("term.information.provider");
            cboNhacungcap.focus();
            return false;
        }

        AppParamsDTO hinhthuc = (AppParamsDTO) cboHinhthuc.getValue();
        if (hinhthuc == null) {
            CommonUtils.showMessageRequired("type.contract.register");
            cboHinhthuc.focus();
            return false;
        }
        //18/04/2017: Bo nut bat buoc chon bieu mau
//        ContractTemplateListDTO bieumau = (ContractTemplateListDTO) cboBieuMau.getValue();
//        if (bieumau == null) {
//            CommonUtils.showMessageRequired("contract.template");
//            cboBieuMau.focus();
//            return false;
//        }
        return true;
    }

    public void addBtnEmailListener() {
        btnEmail.addClickListener(event -> {
            Map<String, String> mapInputedValue = getValueInputed();
            CreateEmailForm createEmailForm = new CreateEmailForm(customer, mapInputedValue);
            UI.getCurrent().addWindow(createEmailForm);
            CommonUtils.enableButtonAfterClick(event);
        });
    }

    public void addBtnCloseListener() {
        btnClose.addClickListener(event -> close());
    }

    public void addBtnExportExcelListener() {
        btnExportExcel.addClickListener(event -> {
            Map<String, String> mapInputedValue = getValueInputed();
            List<CustomerTHAPDTO> lstDataExport = getData2ExportExcel(mapInputedValue);
            doExportExcel(lstDataExport);
            CommonUtils.enableButtonAfterClick(event);
        });
    }

    public void getCompany(String taxCode) {
        String url = "https://thongtindoanhnghiep.co/api/company/" + taxCode;
        try {
            List<ConditionBean> lstCon = new ArrayList<>();
            lstCon.add(new ConditionBean("taxCode", customer.getTaxCode(), ConditionBean.Operator.NAME_EQUAL, ConditionBean.Type.STRING));

            List<CustomerDTO> customers = WSCustomer.getListCustomerByCondition(lstCon, 0, 1, "asc", "taxCode");

            if (!DataUtil.isListNullOrEmpty(customers)) {
                customer = customers.get(0);
            }
            String genreJson = IOUtils.toString(new URL(url), "UTF-8");
            JSONObject genreJsonObject = (JSONObject) JSONValue.parseWithException(genreJson);
            // Lay cac thong tin lay duoc tu website
            if (DataUtil.isStringNullOrEmpty(customer.getOfficeAddress())) {
                String diachiCongty = DataUtil.getStringNullOrZero((String) genreJsonObject.get("DiaChiCongTy"));
                txtOfficeAddress.setValue(diachiCongty);
            } else {
                txtOfficeAddress.setValue(customer.getOfficeAddress());
            }

            if (DataUtil.isStringNullOrEmpty(customer.getRepresentativeName())) {
                String chusohuu = DataUtil.getStringNullOrZero((String) genreJsonObject.get("ChuSoHuu"));
                txtTenNguoiDaidien.setValue(chusohuu);
            } else {
                txtTenNguoiDaidien.setValue(customer.getRepresentativeName());
            }

            if (DataUtil.isStringNullOrEmpty(customer.getTelNumber())) {
                String dienthoaiTruso = DataUtil.getStringNullOrZero((String) genreJsonObject.get("NoiDangKyQuanLy_DienThoai"));
                txtTelNumber.setValue(dienthoaiTruso);
            } else {
                txtTelNumber.setValue(customer.getTelNumber());
            }
            if (DataUtil.isStringNullOrEmpty(customer.getTaxDepartment())) {
                String coquanthue = DataUtil.getStringNullOrZero((String) genreJsonObject.get("NoiDangKyQuanLy_CoQuanTitle"));
                txtTaxDepartment.setValue(coquanthue);
            } else {
                String coquanthue = DataUtil.getStringNullOrZero((String) genreJsonObject.get("NoiDangKyQuanLy_CoQuanTitle"));
                txtTaxDepartment.setValue(coquanthue);
            }
            if (DataUtil.isStringNullOrEmpty(customer.getDeployAddress())) {
                String diachiCoquan = DataUtil.getStringNullOrZero((String) genreJsonObject.get("DiaChiNhanThongBaoThue"));
                txtDeployAddress.setValue(diachiCoquan);
            } else {
                txtDeployAddress.setValue(customer.getDeployAddress());
            }
            if (!DataUtil.isStringNullOrEmpty(customer.getEmail())) {
                txtEmail.setValue(customer.getEmail());
            }
            if (!DataUtil.isStringNullOrEmpty(customer.getRepresentativeId())) {
                txtCMND.setValue(customer.getRepresentativeId());
            }
            if (!DataUtil.isStringNullOrEmpty(customer.getFax())) {
                txtFax.setValue(customer.getFax());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lay du lieu do nguoi dung nhap vao
     *
     * @return
     */
    public Map<String, String> getValueInputed() {
        Map<String, String> inputted = new HashMap<>();
        inputted.put(Constants.REPORT.TAX_CODE, DataUtil.getStringNullOrZero(txtTaxCode.getValue()));
        inputted.put(Constants.REPORT.NAME, DataUtil.getStringNullOrZero(txtName.getValue()));
        inputted.put(Constants.REPORT.OFFICE_ADDRESS, DataUtil.getStringNullOrZero(txtOfficeAddress.getValue()));
        inputted.put(Constants.REPORT.DEPLOY_ADDRESS, DataUtil.getStringNullOrZero(txtDeployAddress.getValue()));
        inputted.put(Constants.REPORT.TAX_DEPARTMENT, DataUtil.getStringNullOrZero(txtTaxDepartment.getValue()));
        inputted.put(Constants.REPORT.CMND, DataUtil.getStringNullOrZero(txtCMND.getValue()));
        inputted.put(Constants.REPORT.NGUOI_DAIDIEN, DataUtil.getStringNullOrZero(txtTenNguoiDaidien.getValue()));
        inputted.put(Constants.REPORT.EMAIL, DataUtil.getStringNullOrZero(txtEmail.getValue()));
        inputted.put(Constants.REPORT.TEL_NUMBER, DataUtil.getStringNullOrZero(txtTelNumber.getValue()));
        inputted.put(Constants.REPORT.FAX, DataUtil.getStringNullOrZero(txtFax.getValue()));
        inputted.put(Constants.REPORT.NGUOI_LIENHE, DataUtil.getStringNullOrZero(txtTenNguoiLienhe.getValue()));
        inputted.put(Constants.REPORT.SDT_NGUOI_LIENHE, DataUtil.getStringNullOrZero(txtSDTNguoiLienhe.getValue()));
        inputted.put(Constants.REPORT.SDT_NGUOI_DAIDIEN, DataUtil.getStringNullOrZero(txtSDTNguoiDaidien.getValue()));
        inputted.put(Constants.REPORT.EMAIL_NGUOI_DAIDIEN, DataUtil.getStringNullOrZero(txtEmailNguoiDaidien.getValue()));
        inputted.put(Constants.REPORT.EMAIL_NGUOI_LIENHE, DataUtil.getStringNullOrZero(txtEmailNguoiLienhe.getValue()));

        AppParamsDTO chucvuNguoiLienhe = (AppParamsDTO) cboChucvuNguoiLienhe.getValue();
        if (chucvuNguoiLienhe != null) {
            inputted.put(Constants.REPORT.CHUCVU_NGUOI_LIENHE, DataUtil.getStringNullOrZero(chucvuNguoiLienhe.getParName()));
        }
        AppParamsDTO chucvuNguoiDaidien = (AppParamsDTO) cboChuvuNguoiDaidien.getValue();
        if (chucvuNguoiDaidien != null) {
            inputted.put(Constants.REPORT.CHUVU_NGUOI_DAIDIEN, DataUtil.getStringNullOrZero(chucvuNguoiDaidien.getParName()));
        }

        AppParamsDTO nhacungcap = (AppParamsDTO) cboNhacungcap.getValue();
        if (nhacungcap != null) {
            inputted.put(Constants.REPORT.PROVIDER, DataUtil.getStringNullOrZero(nhacungcap.getParName()));
        }

        AppParamsDTO hinhthuc = (AppParamsDTO) cboHinhthuc.getValue();
        if (hinhthuc != null) {
            inputted.put(Constants.REPORT.HINHTHUC, DataUtil.getStringNullOrZero(hinhthuc.getParName()));
        }
        PriceInfoDTO goicuoc = (PriceInfoDTO) cboGoicuoc.getValue();
        if (goicuoc != null) {
            inputted.put(Constants.REPORT.GOICUOC, DataUtil.getStringNullOrZero(goicuoc.getCode()));
        }

        ContractTemplateListDTO bieumau = (ContractTemplateListDTO) cboBieuMau.getValue();
        if (bieumau != null) {
            inputted.put(Constants.REPORT.CONTRACT_TEMPLATE_PATH, DataUtil.getStringNullOrZero(bieumau.getPathFile()));
            inputted.put(Constants.REPORT.CONTRACT_TEMPLATE_CODE, DataUtil.getStringNullOrZero(bieumau.getCode()));
        }

        String sotien = tfSotienCuoc.getValue();
        if (!DataUtil.isStringNullOrEmpty(sotien)) {
            inputted.put(Constants.REPORT.SOTIEN, DataUtil.getStringNullOrZero(sotien));
        }

        String chietkhau = tfSotienChietkhau.getValue();
        if (!DataUtil.isStringNullOrEmpty(chietkhau)) {
            inputted.put(Constants.REPORT.CHIETKHAU, DataUtil.getStringNullOrZero(chietkhau));
        }
        return inputted;
    }

    public void addValueChangeListener() {
        cboNhacungcap.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                filterContractTemplate();
                cboUtils.setValues(cboBieuMau, lstContractTemplateFilered, "code");
                cboUtils.setValues(cboGoicuoc, lstGoicuocFiltered, "code");
            }
        });
        cboHinhthuc.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                filterContractTemplate();
                cboUtils.setValues(cboBieuMau, lstContractTemplateFilered, "code");
                cboUtils.setValues(cboGoicuoc, lstGoicuocFiltered, "code");
            }
        });

        cboGoicuoc.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                PriceInfoDTO price = (PriceInfoDTO) cboGoicuoc.getValue();
                if (price != null) {
                    tfSotienCuoc.setValue(price.getPrice());
                }
            }
        });
        cboChonNguoilienhe.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                CustomerContactDTO contact = (CustomerContactDTO) cboChonNguoilienhe.getValue();
                if (contact != null) {
                    txtTenNguoiLienhe.setValue(contact.getName());
                    txtEmailNguoiLienhe.setValue(DataUtil.getStringNullOrZero(contact.getEmail()));
                    txtSDTNguoiLienhe.setValue(DataUtil.getStringNullOrZero(contact.getTelNumber()));
                    if (mapChucvu != null) {
                        AppParamsDTO chucvu = mapChucvu.get(contact.getRegency());
                        if (chucvu != null) {
                            cboChucvuNguoiLienhe.setValue(chucvu);
                        }
                    }
                }
            }
        });
    }

    public void initGridGoicuoc() {
        cboUtils.fillDataCombo(cboNhacungcap, Constants.NULL, Constants.NULL, lstProvider, Constants.APP_PARAMS.PROVIDER);
        cboUtils.fillDataCombo(cboHinhthuc, Constants.NULL, Constants.NULL, lstHinhthuc, Constants.APP_PARAMS.TYPE_CONTRACT);
        cboUtils.setValues(cboBieuMau, lstContractTemplateFilered, "code");
        cboUtils.setValues(cboGoicuoc, lstGoicuocFiltered, "code");
    }

    public void filterContractTemplate() {
        lstContractTemplateFilered = new ArrayList<>();
        lstGoicuocFiltered = new ArrayList<>();
        AppParamsDTO nhacungcap = (AppParamsDTO) cboNhacungcap.getValue();
        String strNcc = null;
        if (nhacungcap != null && !DataUtil.isStringNullOrEmpty(nhacungcap.getParCode())) {
            strNcc = nhacungcap.getParCode();
        }
        String strHt = null;
        AppParamsDTO hinhthuc = (AppParamsDTO) cboHinhthuc.getValue();
        if (hinhthuc != null && !DataUtil.isStringNullOrEmpty(hinhthuc.getParCode())) {
            strHt = hinhthuc.getParCode();
        }

        if (!DataUtil.isStringNullOrEmpty(strNcc) && !DataUtil.isStringNullOrEmpty(strHt)) {
            if (!DataUtil.isListNullOrEmpty(lstContractTemplate)) {
                for (ContractTemplateListDTO contract : lstContractTemplate) {
                    if (strNcc.equals(contract.getProvider()) && strHt.equals(contract.getType())) {
                        lstContractTemplateFilered.add(contract);
                    }
                }
            }
            if (!DataUtil.isListNullOrEmpty(lstGoicuoc)) {
                for (PriceInfoDTO price : lstGoicuoc) {
                    if (strNcc.equals(price.getProvider()) && strHt.equals(price.getType())) {
                        lstGoicuocFiltered.add(price);
                    }
                }
            }
        }
//        } else if (!DataUtil.isStringNullOrEmpty(strNcc) && DataUtil.isStringNullOrEmpty(strHt)) {
//            if (!DataUtil.isListNullOrEmpty(lstContractTemplate)) {
//                for (ContractTemplateListDTO contract : lstContractTemplate) {
//                    if (strNcc.equals(contract.getProvider())) {
//                        lstContractTemplateFilered.add(contract);
//                    }
//                }
//            }
//            if (!DataUtil.isListNullOrEmpty(lstGoicuoc)) {
//                for (PriceInfoDTO price : lstGoicuoc) {
//                    if (strNcc.equals(price.getProvider())) {
//                        lstGoicuocFiltered.add(price);
//                    }
//                }
//            }
//        } else if (!DataUtil.isStringNullOrEmpty(strHt) && DataUtil.isStringNullOrEmpty(strNcc)) {
//            if (!DataUtil.isListNullOrEmpty(lstContractTemplate)) {
//                for (ContractTemplateListDTO contract : lstContractTemplate) {
//                    if (strHt.equals(contract.getType())) {
//                        lstContractTemplateFilered.add(contract);
//                    }
//                }
//            }
//            if (!DataUtil.isListNullOrEmpty(lstGoicuoc)) {
//                for (PriceInfoDTO price : lstGoicuoc) {
//                    if (strHt.equals(price.getType())) {
//                        lstGoicuocFiltered.add(price);
//                    }
//                }
//            }
//        }
    }

    public boolean validate() {
        AppParamsDTO nhacungcap = (AppParamsDTO) cboNhacungcap.getValue();
        if (nhacungcap == null) {
            CommonUtils.showMessageRequired("term.information.provider");
            cboNhacungcap.focus();
            return false;
        }

        AppParamsDTO hinhthuc = (AppParamsDTO) cboHinhthuc.getValue();
        if (hinhthuc == null) {
            CommonUtils.showMessageRequired("type.contract.register");
            cboHinhthuc.focus();
            return false;
        }

        ContractTemplateListDTO bieumau = (ContractTemplateListDTO) cboBieuMau.getValue();
        if (bieumau == null) {
            CommonUtils.showMessageRequired("contract.template");
            cboBieuMau.focus();
            return false;
        }
        return true;
    }

    private void addButtonClickListener() {
        addBtnCloseListener();
        addBtnSaveListener();
        addBtnEmailListener();
        addBtnExportExcelListener();
    }

    public void doExportExcel(List<CustomerTHAPDTO> lstCustomer) {
        if (!DataUtil.isListNullOrEmpty(lstCustomer)) {
            ExportExcell exportExcell = null;

            String fileName = Constants.PATH_EXPORT + "Danh sach khach hang" + ".xlsx";
            try {
                exportExcell = new ExportExcell(fileName);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            //Khoi tao dinh dang fomat cho cell
            List<FormatExcell> formatExcells = new ArrayList<>();

            formatExcells.add(new FormatExcell("receiveInfoDate", null, FormatExcell.CellAlign.ALIGN_CENTER, 3000, true));
            formatExcells.add(new FormatExcell("taxCode", null, FormatExcell.CellAlign.ALIGN_CENTER, 3000, true));
            formatExcells.add(new FormatExcell("name", null, FormatExcell.CellAlign.ALIGN_LEFT, 10000, true));
            formatExcells.add(new FormatExcell("officeAddress", null, FormatExcell.CellAlign.ALIGN_LEFT, 5000, true));
            formatExcells.add(new FormatExcell("email", null, FormatExcell.CellAlign.ALIGN_LEFT, 3000, true));
            formatExcells.add(new FormatExcell("contactName", null, FormatExcell.CellAlign.ALIGN_LEFT, 3000, true));
            formatExcells.add(new FormatExcell("contactPhone", null, FormatExcell.CellAlign.ALIGN_LEFT, 3000, true));
            formatExcells.add(new FormatExcell("deployAddress", null, FormatExcell.CellAlign.ALIGN_LEFT, 3000, true));
            formatExcells.add(new FormatExcell("priceInfoCode", null, FormatExcell.CellAlign.ALIGN_LEFT, 3000, true));
            formatExcells.add(new FormatExcell("provider", null, FormatExcell.CellAlign.ALIGN_LEFT, 3000, true));
            formatExcells.add(new FormatExcell("contractType", null, FormatExcell.CellAlign.ALIGN_LEFT, 3000, true));
            formatExcells.add(new FormatExcell("paymentAmount", null, FormatExcell.CellAlign.ALIGN_RIGHT, 3000, true, FormatExcell.DOUBLE_TYPE));
            formatExcells.add(new FormatExcell("discount", null, FormatExcell.CellAlign.ALIGN_RIGHT, 3000, true, FormatExcell.DOUBLE_TYPE));
            formatExcells.add(new FormatExcell("receivableBalance", null, FormatExcell.CellAlign.ALIGN_RIGHT, 3000, true, FormatExcell.DOUBLE_TYPE));
            formatExcells.add(new FormatExcell("discountedBack", null, FormatExcell.CellAlign.ALIGN_RIGHT, 3000, true, FormatExcell.DOUBLE_TYPE));
            formatExcells.add(new FormatExcell("payments", null, FormatExcell.CellAlign.ALIGN_LEFT, 3000, true));
            formatExcells.add(new FormatExcell("paymentStatus", null, FormatExcell.CellAlign.ALIGN_LEFT, 3000, true));
            formatExcells.add(new FormatExcell("fileStatus", null, FormatExcell.CellAlign.ALIGN_LEFT, 3000, true));
            formatExcells.add(new FormatExcell("codeCOD", null, FormatExcell.CellAlign.ALIGN_LEFT, 3000, true));
            formatExcells.add(new FormatExcell("invoiceStatus", null, FormatExcell.CellAlign.ALIGN_LEFT, 3000, true));
            formatExcells.add(new FormatExcell("notes", null, FormatExcell.CellAlign.ALIGN_LEFT, 3000, true));

            exportExcell.buildSheet("Danh sach khach hang", lstCustomer, formatExcells, "customer.thap");

            exportExcell.writeFileOutputStream();
            File file = new File(fileName);
            Resource resource = new FileResource(file);
            Page.getCurrent().open(resource, null, false);
        }
    }

    public List<CustomerTHAPDTO> getData2ExportExcel(Map<String, String> mapInputedValue) {
        List<CustomerTHAPDTO> lstData = new ArrayList<>();
        CustomerTHAPDTO customerTHAPDTO = new CustomerTHAPDTO();
        customerTHAPDTO.setReceiveInfoDate(DateUtil.date2ddMMyyyyString(new Date()));
        customerTHAPDTO.setTaxCode(customer.getTaxCode());
        customerTHAPDTO.setName(customer.getName());
        customerTHAPDTO.setOfficeAddress(
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.OFFICE_ADDRESS)));
        customerTHAPDTO.setEmail(
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.EMAIL)));
        customerTHAPDTO.setContactName(
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.NGUOI_LIENHE)));
        customerTHAPDTO.setContactPhone(
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.SDT_NGUOI_LIENHE)));
        customerTHAPDTO.setDeployAddress(
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.DEPLOY_ADDRESS)));
        customerTHAPDTO.setPriceInfoCode(
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.GOICUOC)));
        customerTHAPDTO.setProvider(
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.PROVIDER)));
        customerTHAPDTO.setContractType(
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.HINHTHUC)));
        String sotien = DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.SOTIEN));
        customerTHAPDTO.setPaymentAmount(sotien);
        String chietkhau = DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.CHIETKHAU));
        customerTHAPDTO.setDiscount(chietkhau);
        Long chietKhauLui = 0L;
        try {
            Long dSotien = Long.parseLong(sotien);
            Long dChietKhau = Long.parseLong(chietkhau);
            chietKhauLui = dSotien - dChietKhau;
        } catch (Exception e) {
        }
        customerTHAPDTO.setReceivableBalance(chietKhauLui == 0L ? "" : String.valueOf(chietKhauLui));
        customerTHAPDTO.setDiscountedBack(
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.DISCOUNT_BACK)));
        customerTHAPDTO.setPayments(Constants.NULL);
        customerTHAPDTO.setPaymentStatus(Constants.NULL);
        customerTHAPDTO.setFileStatus(Constants.NULL);
        customerTHAPDTO.setCodeCOD(Constants.NULL);
        customerTHAPDTO.setInvoiceStatus(Constants.NULL);
        customerTHAPDTO.setNotes(mapInputedValue.get(Constants.REPORT.NOTES));

        lstData.add(customerTHAPDTO);
        return lstData;
    }
}
