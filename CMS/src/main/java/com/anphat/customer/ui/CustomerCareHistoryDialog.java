/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.ui;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextArea;
import com.cms.component.CommonDialog;
import com.cms.component.GridManyButton;
import com.cms.component.MappingCombobox;
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
import com.cms.utils.ShortcutUtils;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author quyen
 */
public class CustomerCareHistoryDialog extends CommonDialog {

    private GridLayout gridCareHistoryLayout;
    private ComboBox cbxService;
    private ComboBox cbxCustomerServiceStatus;
    private MappingCombobox f9Contact;
    private DateField dfDateTracking;
    private TextArea taNotes;
    private Locale locale;
    private Button btnSave;
    private Button btnAddContact;
    private final List<AppParamsDTO> lstServices;
    private final List<AppParamsDTO> lstCustomerServiceStatus;
    private final List<CustomerContactDTO> lstContacts;

    public CustomerCareHistoryDialog(String caption, List<AppParamsDTO> lstServices,
            List<AppParamsDTO> lstCustomerServiceStatus, List<CustomerContactDTO> lstCustomerContactDTOs) {
        this.lstServices = lstServices;
        this.lstCustomerServiceStatus = lstCustomerServiceStatus;
        this.lstContacts = lstCustomerContactDTOs;
        super.setInfo("90%", "-1px", caption);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(false);
        buildGridCareHistory();
        fillData2ComboBoxs();
    }

    private void buildGridCareHistory() {
        gridCareHistoryLayout = new GridLayout(2, 4);
        CommonUtils.setBasicAttributeLayout(gridCareHistoryLayout, BundleUtils.getString("label.history.care.caption"), false);
        locale = (Locale) VaadinSession.getCurrent().getAttribute("locale");
        if (locale == null) {
            locale = new Locale("vi");
        }
        dfDateTracking = new DateField(BundleUtils.getString("customerCareHistoryForm.dateTracking"));
        dfDateTracking.setWidth("100%");
        dfDateTracking.setImmediate(true);
        dfDateTracking.setLocale(locale);
        gridCareHistoryLayout.addComponent(dfDateTracking, 0, 3);
        taNotes = new TextArea(BundleUtils.getString("customerCareHistoryForm.notes"));
        taNotes.setRequired(true);
        taNotes.setWidth("100%");
        gridCareHistoryLayout.addComponent(taNotes, 0, 2, 1, 2);
        cbxService = CommonUtils.buildComboBox(BundleUtils.getString("term.information.service"));
        cbxService.setNullSelectionAllowed(true);
        gridCareHistoryLayout.addComponent(cbxService, 0, 0);

        cbxCustomerServiceStatus = CommonUtils.buildComboBox(BundleUtils.getString("customerStatusForm.status"));
        cbxCustomerServiceStatus.setNullSelectionAllowed(true);
        gridCareHistoryLayout.addComponent(cbxCustomerServiceStatus, 1, 3);

        f9Contact = new MappingCombobox(BundleUtils.getString("customer.contact.name"),
                BundleUtils.getString("customerCareHistoryForm.telNumber"));
        gridCareHistoryLayout.addComponent(f9Contact.getLayout(), 1, 0);
        btnAddContact = new Button(BundleUtils.getString("label.customer.contact.addNew"));
        btnAddContact.addStyleName("v-button-link");
        btnAddContact.setDisableOnClick(true);
        ShortcutUtils.setShortkeyF2(btnAddContact);
        gridCareHistoryLayout.addComponent(btnAddContact, 1, 1);

        mainLayout.addComponent(gridCareHistoryLayout);
        GridManyButton gridManyButton = CommonUtils.getCommonButtonDialog(this);
        btnSave = gridManyButton.getBtnCommon().get(0);
        mainLayout.addComponent(gridManyButton);

        DataUtil.addFocusWindow(this, taNotes);
    }

    private void fillData2ComboBoxs() {
        //Fill du lieu cho comboBox dich vu
        ComboComponent c = new ComboComponent();
        String valueDefault = DataUtil.isStringNullOrEmpty(lstServices) ? Constants.NULL : lstServices.get(0).getParCode();        
        c.fillDataCombo(cbxService, Constants.NULL, valueDefault, lstServices, Constants.APP_PARAMS.SERVICE_TYPE);
        String valueDefaultCustomerStatus = DataUtil.isStringNullOrEmpty(lstCustomerServiceStatus) ? Constants.NULL : lstCustomerServiceStatus.get(0).getParCode();
        c.fillDataCombo(cbxCustomerServiceStatus, Constants.NULL, valueDefaultCustomerStatus, lstCustomerServiceStatus, Constants.APP_PARAMS.CUSTOMER_SERVICE_STATUS);
        //Fill du lieu cho truong lien he
        f9Contact.setValues(lstContacts, "name", "telNumber");
    }

    public CustomerCareHistoryDTO getInputObject(CustomerDTO customer) {
        StaffDTO staff = (StaffDTO) VaadinSession.getCurrent().getAttribute("staff");

        CustomerCareHistoryDTO careHistoryDTO = new CustomerCareHistoryDTO();     
        //24/04/2017 - Them truong mineName
        careHistoryDTO.setMineName(customer.getMineName());
        careHistoryDTO.setCustId(customer.getCustId());
        careHistoryDTO.setTaxCode(customer.getTaxCode());
        careHistoryDTO.setStaffId(staff.getStaffId());
        careHistoryDTO.setStaffCode(staff.getCode());
        careHistoryDTO.setDateTracking(DataUtil.getDateNullOrZero(dfDateTracking));
        careHistoryDTO.setCreateDate(DateUtil.date2ddMMyyyyHHMMss(new Date()));
        careHistoryDTO.setNotes(DataUtil.getStringNullOrZero(taNotes.getValue()));
        CustomerContactDTO contactDTO = (CustomerContactDTO) f9Contact.codeCombo.getValue();
        if (!DataUtil.isNullObject(contactDTO)) {
            careHistoryDTO.setContact(contactDTO.getName());
            careHistoryDTO.setTelNumber(contactDTO.getTelNumber());
        }
        AppParamsDTO service = (AppParamsDTO) cbxService.getValue();
        if (service != null) {
            careHistoryDTO.setService(service.getParCode());
        }
//        AppParamsDTO service = (AppParamsDTO) ;
        if (service != null) {
            careHistoryDTO.setService(service.getParCode());
        }
        AppParamsDTO customerStatus = (AppParamsDTO) cbxCustomerServiceStatus.getValue();
        if (customerStatus != null) {
            careHistoryDTO.setStatus(customerStatus.getParCode());
        }
        return careHistoryDTO;
    }

    public void addContact2ListContact(CustomerContactDTO customerContactDTO) {
        lstContacts.add(0, customerContactDTO);
        f9Contact.setValues(lstContacts, "name", "telNumber");
        f9Contact.codeCombo.select(customerContactDTO);
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public Button getBtnAddContact() {
        return btnAddContact;
    }

    public MappingCombobox getF9Contact() {
        return f9Contact;
    }

    public TextArea getTaNotes() {
        return taNotes;
    }

    public ComboBox getCbxService() {
        return cbxService;
    }

    public void setCbxService(ComboBox cbxService) {
        this.cbxService = cbxService;
    }

    public ComboBox getCbxCustomerServiceStatus() {
        return cbxCustomerServiceStatus;
    }

    public void setCbxCustomerServiceStatus(ComboBox cbxCustomerServiceStatus) {
        this.cbxCustomerServiceStatus = cbxCustomerServiceStatus;
    }

}
