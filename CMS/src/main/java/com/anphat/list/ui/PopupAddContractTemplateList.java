/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.list.ui;

import com.cms.component.GridManyButton;
import com.cms.dto.AppParamsDTO;
import com.cms.dto.ContractTemplateListDTO;
import com.cms.login.ws.WSContractTemplateList;
import com.cms.utils.BundleUtils;
import com.cms.utils.CommonUtils;
import com.cms.utils.Constants;
import com.cms.utils.DateUtil;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.DataUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import org.apache.commons.io.output.NullOutputStream;

/**
 *
 * @author QuyenDM
 */
public class PopupAddContractTemplateList extends Window {

    private VerticalLayout mainLayout = new VerticalLayout();
    private GridLayout addContractTemplateListLayout;
    private Button btnSave;
    private Button btnClose;
    private Label lblCode;
    private TextField txtCode;
    private Label lblName;
    private TextField txtName;
    private Label lblPathFile;
    private TextField txtPathFile;
    private Upload uFile;
    private Label lblService;
    private ComboBox cboService;
    private Label lblType;
    private ComboBox cboType;
    private Label lblCreatedDate;
    private PopupDateField popCreatedDate;
    private Label lblLastUpdatedDate;
    private PopupDateField popLastUpdatedDate;
    private Label lblStatus;
    private ComboBox cbxStatus;
    private Label lblProvider;
    private ComboBox cboProvider;
    private String fileNameUploaded;
    private String contractTemplateId;
    private boolean isUploadSuccess;
    private boolean isUpdate;
    private boolean isAddOrUpdateSuccess;
    private ContractTemplateListDTO contractTemplateListDTO;

    public PopupAddContractTemplateList() {
        setCaption(BundleUtils.getString("dialog.ContractTemplateList.caption"));
        mainLayout.setImmediate(true);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("-1px");
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setStyleName("main-popup");

        addContractTemplateListLayout = new GridLayout();
        CommonUtils.setBasicAttributeLayout(addContractTemplateListLayout, BundleUtils.getString("dialog.ContractTemplateList.caption"), true);
        addContractTemplateListLayout.setColumns(4);
        addContractTemplateListLayout.setRows(4);
        setWidth("80.0%");
        setHeight("-1px");
        setModal(true);
        lblCode = new Label();
        lblCode.setImmediate(true);
        lblCode.setWidth("100.0%");
        lblCode.setHeight("-1px");
        lblCode.setValue(BundleUtils.getString("label.ContractTemplateList.code"));
        addContractTemplateListLayout.addComponent(lblCode, 0, 0);

        txtCode = new TextField();
        txtCode.setImmediate(true);
        txtCode.setWidth("100.0%");
        txtCode.setHeight("-1px");
        txtCode.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.TIMEOUT);
        txtCode.setTextChangeTimeout(10);
        addContractTemplateListLayout.addComponent(txtCode, 1, 0);

        lblName = new Label();
        lblName.setImmediate(true);
        lblName.setWidth("100.0%");
        lblName.setHeight("-1px");
        lblName.setValue(BundleUtils.getString("label.ContractTemplateList.name"));
        addContractTemplateListLayout.addComponent(lblName, 2, 0);

        txtName = new TextField();
        txtName.setImmediate(true);
        txtName.setWidth("100.0%");
        txtName.setHeight("-1px");
        txtName.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.TIMEOUT);
        txtName.setTextChangeTimeout(10);
        addContractTemplateListLayout.addComponent(txtName, 3, 0);

        lblPathFile = new Label();
        lblPathFile.setImmediate(true);
        lblPathFile.setWidth("100.0%");
        lblPathFile.setHeight("-1px");
        lblPathFile.setValue(BundleUtils.getString("label.ContractTemplateList.pathFile"));
        uFile = new Upload();
        uFile.setButtonCaption("Tải file");
        uFile.addStyleName("upload-hide");
        uFile.setReceiver(new Upload.Receiver() {
            @Override
            public OutputStream receiveUpload(String filename, String mimeType) {
                if (DataUtil.isStringNullOrEmpty(filename)) {
                    CommonUtils.showMessage("err.choosefileBeforeUpload");
                    uFile.interruptUpload();
                    isUploadSuccess = false;
                    return new NullOutputStream();
                }
                String code = txtCode.getValue();
                String name = txtName.getValue();
                if (DataUtil.isStringNullOrEmpty(code)) {
                    txtCode.focus();
                    CommonUtils.showMessageRequired("label.ContractTemplateList.code");
                    uFile.interruptUpload();
                    isUploadSuccess = false;
                    return new NullOutputStream();
                }
                if (DataUtil.isStringNullOrEmpty(name)) {
                    txtName.focus();
                    CommonUtils.showMessageRequired("label.ContractTemplateList.name");
                    isUploadSuccess = false;
                    uFile.interruptUpload();
                    return new NullOutputStream();
                }
                long contentLength = uFile.getUploadSize();
                if (contentLength > Constants.FILE_SIZE_IMPORT && contentLength != -1) {
                    CommonUtils.showContentLengthValid();
                    isUploadSuccess = false;
                    uFile.interruptUpload();
                    return new NullOutputStream();
                }

                fileNameUploaded = filename;
                FileOutputStream fos = null; // Output stream to write to
                File file = new File(Constants.PATH_TEMPLATE + filename);
                try {
                    if (Constants.FORMATFILE.WORD_DOCX.equals(mimeType)) {
                        // Open the file for writing.
                        fos = new FileOutputStream(file);
                        isUploadSuccess = true;
                    } else {
                        fileNameUploaded = null;
                        isUploadSuccess = false;
                        uFile.interruptUpload();
                        CommonUtils.showErrorMessage("err.only.docx.file");
                        return new NullOutputStream();
                    }
                } catch (final java.io.FileNotFoundException e) {
                    fileNameUploaded = null;
                    CommonUtils.showErrorMessage("common.message.invalidfileformat");
                    isUploadSuccess = false;
                    uFile.interruptUpload();
                    return new NullOutputStream();
                }
                return fos; // Return the output stream to write to
            }
        });
        uFile.addSucceededListener(e -> {
            if (isUploadSuccess) {
                CommonUtils.showMessage("Tải file thành công.");
            }
        });
//        addContractTemplateListLayout.addComponent(uFile, 0, 1, 1, 1);
        lblService = new Label();
        lblService.setImmediate(true);
        lblService.setWidth("100.0%");
        lblService.setHeight("-1px");
        lblService.setValue(BundleUtils.getString("label.ContractTemplateList.service"));
        addContractTemplateListLayout.addComponent(lblService, 0, 1);

        cboService = new ComboBox();
        cboService.setImmediate(true);
        cboService.setWidth("100.0%");
        cboService.setHeight("-1px");
        addContractTemplateListLayout.addComponent(cboService, 1, 1);

        lblType = new Label();
        lblType.setImmediate(true);
        lblType.setWidth("100.0%");
        lblType.setHeight("-1px");
        lblType.setValue(BundleUtils.getString("label.ContractTemplateList.type"));
        addContractTemplateListLayout.addComponent(lblType, 2, 1);

        cboType = new ComboBox();
        cboType.setImmediate(true);
        cboType.setWidth("100.0%");
        cboType.setHeight("-1px");
        addContractTemplateListLayout.addComponent(cboType, 3, 1);

        lblCreatedDate = new Label();
        lblCreatedDate.setImmediate(true);
        lblCreatedDate.setWidth("100.0%");
        lblCreatedDate.setHeight("-1px");
        lblCreatedDate.setValue(BundleUtils.getString("label.ContractTemplateList.createdDate"));
//        addContractTemplateListLayout.addComponent(lblCreatedDate, 0, 2);

        popCreatedDate = new PopupDateField();
        popCreatedDate.setImmediate(true);
        popCreatedDate.setWidth("100.0%");
        popCreatedDate.setHeight("-1px");
//        addContractTemplateListLayout.addComponent(popCreatedDate, 1, 2);
        lblLastUpdatedDate = new Label();
        lblLastUpdatedDate.setImmediate(true);
        lblLastUpdatedDate.setWidth("100.0%");
        lblLastUpdatedDate.setHeight("-1px");
        lblLastUpdatedDate.setValue(BundleUtils.getString("label.ContractTemplateList.lastUpdatedDate"));
//        addContractTemplateListLayout.addComponent(lblLastUpdatedDate, 2, 2);

        popLastUpdatedDate = new PopupDateField();
        popLastUpdatedDate.setImmediate(true);
        popLastUpdatedDate.setWidth("100.0%");
        popLastUpdatedDate.setHeight("-1px");
//        addContractTemplateListLayout.addComponent(popLastUpdatedDate, 3, 2);

        lblProvider = new Label();
        lblProvider.setImmediate(true);
        lblProvider.setWidth("100.0%");
        lblProvider.setHeight("-1px");
        lblProvider.setValue(BundleUtils.getString("label.ContractTemplateList.provider"));
        addContractTemplateListLayout.addComponent(lblProvider, 0, 2);

        cboProvider = new ComboBox();
        cboProvider.setImmediate(true);
        cboProvider.setWidth("100.0%");
        cboProvider.setHeight("-1px");
        addContractTemplateListLayout.addComponent(cboProvider, 1, 2);

        lblStatus = new Label();
        lblStatus.setImmediate(true);
        lblStatus.setWidth("100.0%");
        lblStatus.setHeight("-1px");
        lblStatus.setValue(BundleUtils.getString("label.ContractTemplateList.status"));
        addContractTemplateListLayout.addComponent(lblStatus, 2, 2);

        cbxStatus = new ComboBox();
        cbxStatus.setImmediate(true);
        cbxStatus.setWidth("100.0%");
        cbxStatus.setHeight("-1px");
        addContractTemplateListLayout.addComponent(cbxStatus, 3, 2);

        mainLayout.addComponent(addContractTemplateListLayout);
        mainLayout.addComponent(uFile);
        GridManyButton gridBtnPrint = new GridManyButton(new String[]{Constants.BUTTON_SAVE, Constants.BUTTON_CLOSE});
        mainLayout.addComponent(gridBtnPrint);
        btnSave = gridBtnPrint.getBtnCommon().get(0);

        btnClose = gridBtnPrint.getBtnCommon().get(1);
        btnClose.addClickListener(e -> {
            close();
        });
        setContent(mainLayout);
    }

    public ContractTemplateListDTO getDataInputted() {
        String code = txtCode.getValue();
        String name = txtName.getValue();
        contractTemplateListDTO.setCode(code);
        contractTemplateListDTO.setName(name);

        contractTemplateListDTO.setPathFile(fileNameUploaded);
        if (DataUtil.isStringNullOrEmpty(contractTemplateListDTO.getCreatedDate())) {
            contractTemplateListDTO.setCreatedDate(DateUtil.date2ddMMyyyyString(new Date()));
        }
        AppParamsDTO serviceDTO = (AppParamsDTO) cboService.getValue();
        if (serviceDTO != null) {
            contractTemplateListDTO.setService(serviceDTO.getParCode());
        }
        AppParamsDTO statusDTO = (AppParamsDTO) cbxStatus.getValue();
        if (statusDTO != null) {
            contractTemplateListDTO.setStatus(statusDTO.getParCode());
        }
        AppParamsDTO typeDTO = (AppParamsDTO) cboType.getValue();
        if (typeDTO != null) {
            contractTemplateListDTO.setType(typeDTO.getParCode());
        }
        AppParamsDTO providerDTO = (AppParamsDTO) cboProvider.getValue();
        if (providerDTO != null) {
            contractTemplateListDTO.setProvider(providerDTO.getParCode());
        }
        contractTemplateListDTO.setLastUpdatedDate(DateUtil.date2ddMMyyyyString(new Date()));
        return contractTemplateListDTO;
    }

    public VerticalLayout getMainLayout() {
        return mainLayout;
    }

    public void setMainLayout(VerticalLayout mainLayout) {
        this.mainLayout = mainLayout;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(Button btnSave) {
        this.btnSave = btnSave;
    }

    public Button getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(Button btnClose) {
        this.btnClose = btnClose;
    }

    public GridLayout getAddContractTemplateListLayout() {
        return addContractTemplateListLayout;
    }

    public void setAddContractTemplateListLayout(GridLayout addContractTemplateListLayout) {
        this.addContractTemplateListLayout = addContractTemplateListLayout;
    }

    public TextField getTxtCode() {
        return txtCode;
    }

    public void setTxtCode(TextField txtCode) {
        this.txtCode = txtCode;
    }

    public TextField getTxtName() {
        return txtName;
    }

    public void setTxtName(TextField txtName) {
        this.txtName = txtName;
    }

    public TextField getTxtPathFile() {
        return txtPathFile;
    }

    public void setTxtPathFile(TextField txtPathFile) {
        this.txtPathFile = txtPathFile;
    }

    public PopupDateField getPopCreatedDate() {
        return popCreatedDate;
    }

    public void setPopCreatedDate(PopupDateField popCreatedDate) {
        this.popCreatedDate = popCreatedDate;
    }

    public PopupDateField getPopLastUpdatedDate() {
        return popLastUpdatedDate;
    }

    public void setPopLastUpdatedDate(PopupDateField popLastUpdatedDate) {
        this.popLastUpdatedDate = popLastUpdatedDate;
    }

    public ComboBox getCbxStatus() {
        return cbxStatus;
    }

    public void setCbxStatus(ComboBox cbxStatus) {
        this.cbxStatus = cbxStatus;
    }

    public ComboBox getCboService() {
        return cboService;
    }

    public void setCboService(ComboBox cboService) {
        this.cboService = cboService;
    }

    public ComboBox getCboType() {
        return cboType;
    }

    public void setCboType(ComboBox cboType) {
        this.cboType = cboType;
    }

    public ComboBox getCboProvider() {
        return cboProvider;
    }

    public void setCboProvider(ComboBox cboProvider) {
        this.cboProvider = cboProvider;
    }

    public boolean isIsUpdate() {
        return isUpdate;
    }

    public boolean isValid() {
        String code = txtCode.getValue();
        String name = txtName.getValue();
        if (DataUtil.isStringNullOrEmpty(code)) {
            txtCode.focus();
            CommonUtils.showMessageRequired("label.ContractTemplateList.code");
            return false;
        }
        if (DataUtil.isStringNullOrEmpty(name)) {
            txtName.focus();
            CommonUtils.showMessageRequired("label.ContractTemplateList.name");
            return false;
        }
        if (!isUpdate) {
            File file = new File(Constants.PATH_TEMPLATE + fileNameUploaded);
            if (!file.isFile()) {
                uFile.focus();
                Notification.show("Tải file lên trước khi ghi lại!", Notification.Type.TRAY_NOTIFICATION);
                return false;
            }
        }
        return true;
    }

    public void setIsUpdate(boolean isUpdate, ContractTemplateListDTO contractTemplateListDTO) {
        fileNameUploaded = null;
        this.isUpdate = isUpdate;
        if (isUpdate) {
            this.setCaption(BundleUtils.getString("dialog.ContractTemplateList.update.caption"));
        }
        btnSave.addClickListener(e -> {
            if (isValid()) {
                String code = txtCode.getValue();
                String name = txtName.getValue();

                contractTemplateListDTO.setCode(code);
                contractTemplateListDTO.setName(name);
                if (!DataUtil.isStringNullOrEmpty(fileNameUploaded)) {
                    contractTemplateListDTO.setPathFile(fileNameUploaded);
                }
                if (DataUtil.isStringNullOrEmpty(contractTemplateListDTO.getCreatedDate())) {
                    contractTemplateListDTO.setCreatedDate(DateUtil.date2ddMMyyyyString(new Date()));
                }
                AppParamsDTO serviceDTO = (AppParamsDTO) cboService.getValue();
                if (serviceDTO != null) {
                    contractTemplateListDTO.setService(serviceDTO.getParCode());
                }
                AppParamsDTO statusDTO = (AppParamsDTO) cbxStatus.getValue();
                if (statusDTO != null) {
                    contractTemplateListDTO.setStatus(statusDTO.getParCode());
                }
                AppParamsDTO typeDTO = (AppParamsDTO) cboType.getValue();
                if (typeDTO != null) {
                    contractTemplateListDTO.setType(typeDTO.getParCode());
                }
                AppParamsDTO providerDTO = (AppParamsDTO) cboProvider.getValue();
                if (providerDTO != null) {
                    contractTemplateListDTO.setProvider(providerDTO.getParCode());
                }
                contractTemplateListDTO.setLastUpdatedDate(DateUtil.date2ddMMyyyyString(new Date()));

                if (isUpdate) {
//                    contractTemplateListDTO.setContractTemplateId(contractTemplateId);
                    String update = WSContractTemplateList.updateContractTemplateList(contractTemplateListDTO);
                    if (Constants.SUCCESS.equals(update)) {
                        isAddOrUpdateSuccess = true;
                        close();
                        CommonUtils.showUpdateSuccess();
                    } else {
                        txtCode.focus();
                        isAddOrUpdateSuccess = false;
                        CommonUtils.showUpdateFail();
                    }
                } else {
                    ResultDTO insertResult = WSContractTemplateList.insertContractTemplateList(contractTemplateListDTO);
                    if (insertResult != null && Constants.SUCCESS.equals(insertResult.getMessage())) {
                        isAddOrUpdateSuccess = true;
                        close();
                        CommonUtils.showInsertSuccess();
                    } else {
                        isAddOrUpdateSuccess = false;
                        txtCode.focus();
                        CommonUtils.showInsertFail();
                    }
                }
            }
            e.getButton().setEnabled(true);
        });
    }

    public String getContractTemplateId() {
        return contractTemplateId;
    }

    public void setContractTemplateId(String contractTemplateId) {
        this.contractTemplateId = contractTemplateId;
    }

    public boolean isIsAddOrUpdateSuccess() {
        return isAddOrUpdateSuccess;
    }

    public void setIsAddOrUpdateSuccess(boolean isAddOrUpdateSuccess) {
        this.isAddOrUpdateSuccess = isAddOrUpdateSuccess;
    }

    public ContractTemplateListDTO getContractTemplateListDTO() {
        return contractTemplateListDTO;
    }

    public void setContractTemplateListDTO(ContractTemplateListDTO contractTemplateListDTO) {
        this.contractTemplateListDTO = contractTemplateListDTO;
    }

}
