/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.ui;

import com.cms.component.GridManyButton;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.cms.dto.CustomerDTO;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;

/**
 *
 * @author quyen
 */
public class CustomerDetailForm extends CustomComponent {

    private Label txtTaxCode;
    private Label txtName;
    private Label txtDeployAddress;
    private Label txtOfficeAddress;
    private Label txtTaxDepartment;
    private Label txtTaxAuthority;
    private Label txtStatus;
    private VerticalLayout root;
    private GridLayout gridCustDetail;

    //Them thong tin han tren trang chi tiet khach hang
    private TabSheet tabSheet;
    private VerticalLayout layoutTermInfor;
    private CommonTableFilterPanel tblTermInfor;
    private VerticalLayout layoutContact;
    private CommonTableFilterPanel tblContact;
    private CustomerContactForm customerContactForm;
    private Button btnCreateContractDoc;
    private Button btnCreateContract;

    private final String GRID_UPLOAD_CAPTION = BundleUtils.getString("customer.management.header.customerinfo");

    public CustomerDetailForm() {
        buildRootLayout();
//        buildTabSheet();
        buildTabContact();
        buildGridButton();
        buildTabTermInformation();
    }

    private void buildRootLayout() {
        root = new VerticalLayout();
        root.setImmediate(false);
        root.setWidth("100%");
        root.setHeight("100%");
        root.setMargin(false);
        root.setSpacing(false);
        // top-level component properties
        setWidth("100.0%");
        setHeight("100.0%");
        //Khoi tao cac thanh phan
        gridCustDetail = buildGridDetail();

        //Them cac thanh phan vao grid
        root.addComponent(gridCustDetail);
        setCompositionRoot(root);
    }

    private GridLayout buildGridDetail() {
        gridCustDetail = new GridLayout(4, 2);
        CommonUtils.setBasicAttributeLayout(gridCustDetail, GRID_UPLOAD_CAPTION, false);
        Label lbTaxCode = CommonUtils.buildLabel(BundleUtils.getString("customer.code"), true);
        Label lbName = CommonUtils.buildLabel(BundleUtils.getString("customer.name"), true);
//        Label lbDeployAddress = CommonUtils.buildLabel(BundleUtils.getString("customer.deployAddress"), true);
//        Label lbOfficeAddress = CommonUtils.buildLabel(BundleUtils.getString("customer.officeAddress"), true);
        Label lbTaxAuthority = CommonUtils.buildLabel(BundleUtils.getString("label.taxAuthority"), true);
//        Label lbTaxDepartment = CommonUtils.buildLabel(BundleUtils.getString("customer.taxDepartment"), true);
//        Label lbStatus = CommonUtils.buildLabel(BundleUtils.getString("customer.status"), true);
        txtTaxCode = CommonUtils.buildLabel("", false);
        txtName = CommonUtils.buildLabel("", false);
//        txtDeployAddress = CommonUtils.buildLabel("", false);
//        txtOfficeAddress = CommonUtils.buildLabel("", false);
//        txtTaxDepartment = CommonUtils.buildLabel("", false);
        txtTaxAuthority = CommonUtils.buildLabel("", false);
//        txtStatus = CommonUtils.buildLabel("", false);

        gridCustDetail.addComponent(lbTaxCode,
                0, 0);
        gridCustDetail.addComponent(txtTaxCode,
                1, 0);
//        gridCustDetail.addComponent(lbStatus,
//                2, 0);
//        gridCustDetail.addComponent(txtStatus,
//                3, 0);
        gridCustDetail.addComponent(lbName,
                0, 1);
        gridCustDetail.addComponent(txtName,
                1, 1, 3, 1);
        gridCustDetail.addComponent(lbTaxAuthority,
                2, 0);
        gridCustDetail.addComponent(txtTaxAuthority,
                3, 0);
//        gridCustDetail.addComponent(lbTaxDepartment,
//                2, 2);
//        gridCustDetail.addComponent(txtTaxDepartment,
//                3, 2);
//        gridCustDetail.addComponent(lbDeployAddress,
//                0, 3);
//        gridCustDetail.addComponent(txtDeployAddress,
//                1, 3, 3, 3);
//        gridCustDetail.addComponent(lbOfficeAddress,
//                0, 4);
//        gridCustDetail.addComponent(txtOfficeAddress,
//                1, 4, 3, 4);
        return gridCustDetail;
    }

    public void setData2Detail(CustomerDTO custDTO) {
        txtTaxCode.setValue(DataUtil.getStringNullOrZero(custDTO.getTaxCode()));
        txtName.setValue(DataUtil.getStringNullOrZero(custDTO.getName()));
//        txtStatus.setValue(DataUtil.getStringNullOrZero(custDTO.getStatus()));
//        txtTaxDepartment.setValue(DataUtil.getStringNullOrZero(custDTO.getTaxDepartment()));
        txtTaxAuthority.setValue(DataUtil.getStringNullOrZero(custDTO.getTaxAuthority()));
//        txtDeployAddress.setValue(DataUtil.getStringNullOrZero(custDTO.getDeployAddress()));
//        txtOfficeAddress.setValue(DataUtil.getStringNullOrZero(custDTO.getOfficeAddress()));
    }

//    private void buildTabSheet() {
//        tabSheet = new TabSheet();
//        tabSheet.setHeight(100.0f, Unit.PERCENTAGE);
//        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
//        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
//        root.addComponent(tabSheet);
//    }
    private void buildTabTermInformation() {
        layoutTermInfor = new VerticalLayout();
        tblTermInfor = new CommonTableFilterPanel();
        tblTermInfor.getToolbar().setVisible(false);
        root.addComponent(tblTermInfor);
//        layoutTermInfor.addComponent(tblTermInfor);
//        tabSheet.addTab(layoutTermInfor, "Thông tin hạn");
    }

    private void buildTabContact() {
        customerContactForm = new CustomerContactForm();
        root.addComponent(customerContactForm);
    }

    private void buildGridButton() {
        GridManyButton gridManyButton = new GridManyButton(new String[]{Constants.BUTTON_INSERT});
        btnCreateContractDoc = gridManyButton.getBtnCommon().get(0);
        btnCreateContractDoc.setCaption(BundleUtils.getString("button.contract.create.doc"));        
        btnCreateContractDoc.setEnabled(false);
        root.addComponent(gridManyButton);
    }
//    private void buildTabContact() {
//        layoutContact = new VerticalLayout();
//        tblContact = new CommonTableFilterPanel();
//        layoutContact.addComponent(tblContact);
//        tabSheet.addTab(layoutContact, "Liên hệ");
//    }
    //Getter tblTermInfor

    public CommonTableFilterPanel getTblTermInfor() {
        return tblTermInfor;
    }

    public CommonTableFilterPanel getTblContact() {
        return tblContact;
    }

    public CustomerContactForm getCustomerContactForm() {
        return customerContactForm;
    }

    public Button getBtnCreateContractDoc() {
        return btnCreateContractDoc;
    }

    public void setBtnCreateContractDoc(Button btnCreateContractDoc) {
        this.btnCreateContractDoc = btnCreateContractDoc;
    }

    public Button getBtnCreateContract() {
        return btnCreateContract;
    }

    public void setBtnCreateContract(Button btnCreateContract) {
        this.btnCreateContract = btnCreateContract;
    }

}
