/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.cms.component.CommonDialog;
import com.cms.component.GridManyButton;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.CustomerCareHistoryDTO;
import com.cms.dto.CustomerContactDTO;
import com.cms.dto.CustomerDTO;
import com.cms.dto.StaffDTO;
import com.cms.utils.BundleUtils;
import com.cms.utils.ComboComponent;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DataUtil;
import com.cms.utils.DateUtil;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author quyen
 */
public class CustomerContactDialog extends CommonDialog {

    private GridLayout gridCustomerContact;
    private GridLayout gridCareHistory;
    private TextField txtName;
    private TextField txtTelNumber;
    private TextField txtEmail;
    private TextField txtRegency;
    private ComboBox cboRegency;
//    private ComboBox cboStatus;
    private DateField dfDateTracking;
    private TextArea taNotes;
    private Button btnSave;
    private List<AppParamsDTO> lstRegency;
    private List<AppParamsDTO> lstCustomerStatus;
    private List<CustomerContactDTO> lstCustContact;
    private Locale locale;
    private ComboComponent combo;
    private GridLayout gridStatusButton;
    private CustomerContactDTO selectedContactDTO;
    private CustomerCareHistoryDTO custCareDTO;
    private OptionGroup ogCustStatus;
    private Boolean isEdit = false;
    private String lastValue;

    public CustomerContactDialog(String caption, List<AppParamsDTO> lstCustomerStatus, List<CustomerContactDTO> lstCustContact) {
        super.setInfo("95%", "-1px", caption);
        this.lstCustomerStatus = lstCustomerStatus;
        this.lstCustContact = lstCustContact;
        buildGridCustomerContact();
//        txtName.focus();
    }

    private void buildGridCustomerContact() {
        gridCustomerContact = new GridLayout(4, 1);
        gridCareHistory = new GridLayout(6, 3);
        locale = (Locale) VaadinSession.getCurrent().getAttribute("locale");
        if (locale == null) {
            locale = new Locale("vi");
        }
        CommonUtils.setBasicAttributeLayout(gridCustomerContact, "", false);
        CommonUtils.setBasicAttributeLayout(gridCareHistory, BundleUtils.getString("label.history.care.caption"), true);
        txtName = CommonUtils.buildTextField(BundleUtils.getString("customer.contact.name"), 100);
        txtEmail = CommonUtils.buildTextField(BundleUtils.getString("customer.contact.email"), 100);
        txtTelNumber = CommonUtils.buildTextField(BundleUtils.getString("customer.contact.telNumber"), 100);
        txtRegency = CommonUtils.buildTextField(BundleUtils.getString("customer.contact.regency"), 100);
        dfDateTracking = new DateField(BundleUtils.getString("customerCareHistoryForm.dateTracking"));
        dfDateTracking.setWidth("100%");
        dfDateTracking.setImmediate(true);
        dfDateTracking.setLocale(locale);
        taNotes = new TextArea(BundleUtils.getString("customerCareHistoryForm.notes"));
        taNotes.setRequired(true);
        taNotes.setWidth("100%");
//        cboStatus = CommonUtils.buildComboBox(BundleUtils.getString("customerStatusForm.status"));
//        cboStatus.setNullSelectionAllowed(true);
        cboRegency = CommonUtils.buildComboBox("customer.contact.regency");

        gridStatusButton = new GridLayout(1, 2);
//        gridStatusButton.setWidth("100%");
        gridCustomerContact.addComponent(txtName, 0, 0);
        gridCustomerContact.addComponent(txtEmail, 1, 0);
        gridCustomerContact.addComponent(txtTelNumber, 2, 0);
        gridCustomerContact.addComponent(cboRegency, 3, 0);

        gridStatusButton.addComponent(dfDateTracking, 0, 1);

        ogCustStatus = getCustomerStatus();
        ogCustStatus.setMultiSelect(false);
        ogCustStatus.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
        ogCustStatus.setItemCaptionPropertyId("parName");
        ogCustStatus.addStyleName("horizontal");
        gridStatusButton.addComponent(ogCustStatus, 0, 0);

        gridCareHistory.addComponent(gridStatusButton, 0, 0, 5, 0);
        gridCareHistory.addComponent(taNotes, 0, 2, 5, 2);

        mainLayout.addComponent(gridCustomerContact);
        mainLayout.addComponent(gridCareHistory);

        lstRegency = DataUtil.getListApParams(Constants.APP_PARAMS.CUSTOMER_CONTACT_REGENCY);
        String valueRegencyDefault = Constants.NULL;
        if (!DataUtil.isListNullOrEmpty(lstRegency)) {
            valueRegencyDefault = lstRegency.get(0).getParCode();
        }

        combo = new ComboComponent();
        combo.fillDataCombo(cboRegency, Constants.NULL, valueRegencyDefault, lstRegency, Constants.APP_PARAMS.CUSTOMER_CONTACT_REGENCY);
//        combo.fillDataCombo(cboStatus, Constants.NULL, valueRegencyDefault, lstCustomerStatus, Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS);
        GridManyButton gridManyButton = CommonUtils.getCommonButtonDialog(this);
        btnSave = gridManyButton.getBtnCommon().get(0);
        mainLayout.addComponent(gridManyButton);
//        DataUtil.addFocusWindow(this, txtName);
    }

    public OptionGroup getCustomerStatus() {
        OptionGroup og = new OptionGroup("Trạng thái");
        BeanItemContainer container = new BeanItemContainer(AppParamsDTO.class);
        container.addAll(lstCustomerStatus.subList(1, lstCustomerStatus.size()));
        og.setContainerDataSource(container);
        og.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                AppParamsDTO selectedStatus = (AppParamsDTO) event.getProperty().getValue();
                List<String> lstStatusAutoFillData = new ArrayList<>();
                lstStatusAutoFillData.add("KNM");
                lstStatusAutoFillData.add("KLL");
                lstStatusAutoFillData.add("STT");
                lstStatusAutoFillData.add("Từ chối");
                lstStatusAutoFillData.add("Tạm dừng");
                lstStatusAutoFillData.add("Đã ĐK");
                lstStatusAutoFillData.add("CHH(từ chối)");
                lstStatusAutoFillData.add("CG");
                String timeStamp = DateUtil.date2ddMMyyyyHHMMss(new Date()) + ": ";
                String note;
                if (!DataUtil.isStringNullOrEmpty(lastValue)) {
                    note = lastValue + "\n" + timeStamp;
                } else {
                    note = timeStamp;
                }

                if (lstStatusAutoFillData.contains(selectedStatus.getParName().trim())) {
                    note += selectedStatus.getParName();
                    if (selectedContactDTO != null
                            && !DataUtil.isStringNullOrEmpty(selectedContactDTO.getId())
                            || isEdit) {
                    } else if (DataUtil.isStringNullOrEmpty(txtName.getValue())) {
                        txtName.setValue(selectedStatus.getParName());
                    }
                    taNotes.focus();
                } else if (selectedContactDTO != null
                        && !DataUtil.isStringNullOrEmpty(selectedContactDTO.getId())) {
                    taNotes.focus();
                } else if (custCareDTO != null) {
                    taNotes.focus();
                } else {
                    txtName.focus();
                    txtName.selectAll();
                }
                taNotes.setValue(note);
            }
        });
        return og;
    }

    /**
     * Get input object from input form and from customer
     *
     * @param customerDTO customer is selected by staff
     * @return CustomerContactDTO object will be save to db
     */
    public CustomerContactDTO getInputContactObject(CustomerDTO customerDTO) {
        String phoneNumber = DataUtil.getStringNullOrZero(txtTelNumber.getValue());
        String name = DataUtil.getStringNullOrZero(txtName.getValue());
        CustomerContactDTO contactDTO;
        contactDTO = getContactExisted(phoneNumber, name);
        if (DataUtil.isNullObject(contactDTO)) {
            contactDTO = new CustomerContactDTO();
            contactDTO.setTelNumber(phoneNumber);
            contactDTO.setStatus(Constants.ACTIVE);
            contactDTO.setName(name);
            contactDTO.setEmail(DataUtil.getStringNullOrZero(txtEmail.getValue()));
            AppParamsDTO regency = (AppParamsDTO) cboRegency.getValue();
            contactDTO.setRegency(DataUtil.getStringNullOrZero(regency.getParCode()));
            contactDTO.setTaxCode(customerDTO.getTaxCode());
            contactDTO.setCustId(customerDTO.getCustId());
        } else {

            contactDTO.setName(name);
            contactDTO.setEmail(DataUtil.getStringNullOrZero(txtEmail.getValue()));
            AppParamsDTO regency = (AppParamsDTO) cboRegency.getValue();
            contactDTO.setRegency(DataUtil.getStringNullOrZero(regency.getParCode()));
        }
        StaffDTO staff = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");
        contactDTO.setStaffCode(staff.getCode());
        return contactDTO;
    }
//Kiem tra contact co ton tai chua

    public CustomerContactDTO getContactExisted(String phoneNumber, String name) {
        if (!DataUtil.isListNullOrEmpty(lstCustContact)) {
            for (CustomerContactDTO cc : lstCustContact) {
                if (cc.getTelNumber().equals(DataUtil.getStringNullOrZero(phoneNumber))) {
                    return cc;
                }
            }
        }
        return null;
    }

    public CustomerCareHistoryDTO getInputCustomerCareObject(CustomerDTO customer) {

        if (custCareDTO != null) {
            AppParamsDTO customerStatus = (AppParamsDTO) ogCustStatus.getValue();
            if (customerStatus != null) {
                custCareDTO.setStatus(customerStatus.getParCode());
            }
            custCareDTO.setNotes(DataUtil.getStringNullOrZero(taNotes.getValue()));
            custCareDTO.setDateTracking(DataUtil.getDateNullOrZero(dfDateTracking));
            custCareDTO.setCreateDate(DateUtil.date2ddMMyyyyHHMMss(new Date()));
            return custCareDTO;
        } else {
            StaffDTO staff = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");

            CustomerCareHistoryDTO careHistoryDTO = new CustomerCareHistoryDTO();
            //24/04/2017 - Them truong mineName
            careHistoryDTO.setMineName(customer.getMineName());
            careHistoryDTO.setCustId(customer.getCustId());
            careHistoryDTO.setTaxCode(customer.getTaxCode());
            careHistoryDTO.setStaffId(staff.getStaffId());
            careHistoryDTO.setStaffCode(staff.getCode());
            careHistoryDTO.setService(customer.getService());
            careHistoryDTO.setDateTracking(DataUtil.getDateNullOrZero(dfDateTracking));
            careHistoryDTO.setCreateDate(DateUtil.date2ddMMyyyyHHMMss(new Date()));
            careHistoryDTO.setNotes(DataUtil.getStringNullOrZero(taNotes.getValue()));
//        AppParamsDTO customerStatus = (AppParamsDTO) cboStatus.getValue();
//        if (customerStatus != null) {
//            careHistoryDTO.setStatus(customerStatus.getParCode());
//        }
            AppParamsDTO customerStatus = (AppParamsDTO) ogCustStatus.getValue();
            if (customerStatus != null) {
                careHistoryDTO.setStatus(customerStatus.getParCode());
            }
            careHistoryDTO.setContact(DataUtil.getStringNullOrZero(txtName.getValue()));
            careHistoryDTO.setTelNumber(DataUtil.getStringNullOrZero(txtTelNumber.getValue()));
            return careHistoryDTO;
        }
    }

    public void fillData2Dialog(CustomerContactDTO contactDTO) {
        CustomerContactDTO contactExisted = getContactExisted(contactDTO.getTelNumber(), contactDTO.getName());
        if (!DataUtil.isNullObject(contactExisted)) {
            selectedContactDTO = contactExisted;
            txtName.setValue(DataUtil.getStringNullOrZero(contactExisted.getName()));
            txtTelNumber.setValue(DataUtil.getStringNullOrZero(contactExisted.getTelNumber()));
            txtEmail.setValue(DataUtil.getStringNullOrZero(contactExisted.getEmail()));
            String valueRegency = contactExisted.getRegency();
            combo.fillDataCombo(cboRegency, Constants.NULL, valueRegency, lstRegency, Constants.APP_PARAMS.CUSTOMER_CONTACT_REGENCY);
        } else {
            selectedContactDTO = contactDTO;
            txtName.setValue(DataUtil.getStringNullOrZero(contactDTO.getName()));
            txtTelNumber.setValue(DataUtil.getStringNullOrZero(contactDTO.getTelNumber()));
            txtEmail.setValue(DataUtil.getStringNullOrZero(contactDTO.getEmail()));
        }
    }

    public void fillData2Dialog(CustomerCareHistoryDTO careDTO) {
        CustomerContactDTO contactExisted = getContactExisted(careDTO.getTelNumber(), careDTO.getContact());
        custCareDTO = careDTO;
        if (!DataUtil.isNullObject(contactExisted)) {
            txtName.setValue(DataUtil.getStringNullOrZero(contactExisted.getName()));
            txtTelNumber.setValue(DataUtil.getStringNullOrZero(contactExisted.getTelNumber()));
            txtEmail.setValue(DataUtil.getStringNullOrZero(contactExisted.getEmail()));
            String valueRegency = contactExisted.getRegency();
            combo.fillDataCombo(cboRegency, Constants.NULL, valueRegency, lstRegency, Constants.APP_PARAMS.CUSTOMER_CONTACT_REGENCY);
        } else {
            txtName.setValue(DataUtil.getStringNullOrZero(custCareDTO.getContact()));
            txtTelNumber.setValue(DataUtil.getStringNullOrZero(custCareDTO.getTelNumber()));
        }
        for (AppParamsDTO a : lstCustomerStatus) {
            if (a.getParCode().equals(careDTO.getStatus())) {
//                ogCustStatus.setValue(a);
                ogCustStatus.select(a);
                break;
            }
        }
        taNotes.setValue(careDTO.getNotes());
        taNotes.focus();
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public GridLayout getGridCustomerContact() {
        return gridCustomerContact;
    }

    public void setGridCustomerContact(GridLayout gridCustomerContact) {
        this.gridCustomerContact = gridCustomerContact;
    }

    public TextField getTxtName() {
        return txtName;
    }

    public void setTxtName(TextField txtName) {
        this.txtName = txtName;
    }

    public TextField getTxtTelNumber() {
        return txtTelNumber;
    }

    public void setTxtTelNumber(TextField txtTelNumber) {
        this.txtTelNumber = txtTelNumber;
    }

    public TextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(TextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public TextField getTxtRegency() {
        return txtRegency;
    }

    public void setTxtRegency(TextField txtRegency) {
        this.txtRegency = txtRegency;
    }

    public ComboBox getCboRegency() {
        return cboRegency;
    }

    public void setCboRegency(ComboBox cboRegency) {
        this.cboRegency = cboRegency;
    }

    public DateField getDfDateTracking() {
        return dfDateTracking;
    }

    public void setDfDateTracking(DateField dfDateTracking) {
        this.dfDateTracking = dfDateTracking;
    }

    public TextArea getTaNotes() {
        return taNotes;
    }

    public void setTaNotes(TextArea taNotes) {
        this.taNotes = taNotes;
    }

    public OptionGroup getOgCustStatus() {
        return ogCustStatus;
    }

    public void setOgCustStatus(OptionGroup ogCustStatus) {
        this.ogCustStatus = ogCustStatus;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
        lastValue = taNotes.getValue();
    }

}
