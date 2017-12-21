/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.ui;

import com.cms.component.CommonDialog;
import com.cms.dto.CustomerDTO;
import com.cms.utils.Constants;
import com.vaadin.data.Property;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.RichTextArea;
import com.vwf5.base.utils.DataUtil;
import java.util.Map;

/**
 *
 * @author quyen
 */
public class CreateEmailForm extends CommonDialog {

    private OptionGroup optionService;
    private RichTextArea sample;
    private final Map<String, String> mapInputedValue;
    private final CustomerDTO customer;

    public CreateEmailForm(CustomerDTO customer, Map<String, String> mapInputedValue) {
        setInfo("80%", "-1px", "Gửi email cho nhân viên điều phối");
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        this.customer = customer;
        this.mapInputedValue = mapInputedValue;
        buildEmailContent();
        buildServiceComboBox();
    }

    private void buildServiceComboBox() {
        optionService = new OptionGroup("Dịch vụ");
        optionService.setStyleName("horizontal");
        optionService.addItem("Chữ ký số");
        optionService.addItem("IVAN");

        optionService.addValueChangeListener((Property.ValueChangeEvent event) -> {
            String service = (String) event.getProperty().getValue();
            String mailContent = getMail(service);
            sample.clear();
            sample.setValue(mailContent);
        });
        optionService.select("Chữ ký số");
        mainLayout.addComponentAsFirst(optionService);
    }

    private void buildEmailContent() {
        sample = new RichTextArea();
        sample.setImmediate(true);
        sample.setWidth("100%");
        sample.setHeight("500px");
        mainLayout.addComponent(sample);
    }

    private String getMail(String service) {
        String sb;
        if (service.equals("Chữ ký số")) {
            sb = "<b>TÊN CÔNG TY</b> :  #tencongty <br>"
                    + "<b>MST</b> :  #masothue  <br>"
                    + "<b>Địa chỉ VAT</b> :  #diachitruso <br>"
                    + "<b>Người đại diện</b> :  #nguoidaidien <br>"
                    + "<b>Email</b> :  #emaillienhe  <br>"
                    + "<b>Hóa đơn ghi</b> :  #sotien  <br>"
                    + "<b>Chiết khấu</b> :  #chietkhau <br>"
                    + "<b>Địa chỉ COD/ EMS</b> :  #diachigiaodich <br>"
                    + "<b>Người nhận</b> : #nguoilienhe <br> "
                    + "<b>Điện thoại</b> :  #sodienthoailienhe";

        } else {
            sb = "<b>TÊN CÔNG TY</b> : #tencongty <br> "
                    + "<b>MST</b> : #masothue <br> "
                    + "<b>Mã đơn vị</b> :​​​   <br> "
                    + "<b>Cơ quan bảo hiểm</b> :​​ #coquanquanlythue <br> "
                    + "<b>Địa chỉ hoá đơn</b> : #diachitruso <br> "
                    + "<b>Địa chỉ giao dịch</b> : #diachigiaodich <br> "
                    + "<b>Giá hóa đơn</b> :  <br> "
                    + "​​<b>Người đại diện</b> : #nguoidaidien ​​​  <b>CV</b> : #chucvunguoidaidien <br> "
                    + "<b>Người liên hệ</b> : #nguoilienhe <br> "
                    + "<b>Số điện thoại</b> : #sodienthoailienhe <br> "
                    + "<b>Email</b> :​ #emaillienhe <br> "
                    + "<b>Đăng ký chữ ký số (nếu có)</b> :  <br> "
                    + "<b>Đăng ký IVAN</b> : ​  <br> "
                    + "<b>Hình thức thanh toán</b> : ";
        }
        sb = sb.replaceFirst(Constants.REPORT.NAME, DataUtil.nvl(mapInputedValue.get(Constants.REPORT.NAME)));
        sb = sb.replaceFirst(Constants.REPORT.TAX_CODE, DataUtil.nvl(mapInputedValue.get(Constants.REPORT.TAX_CODE)));
        sb = sb.replaceFirst(Constants.REPORT.OFFICE_ADDRESS, DataUtil.nvl(mapInputedValue.get(Constants.REPORT.OFFICE_ADDRESS)));
        sb = sb.replaceFirst(Constants.REPORT.NGUOI_DAIDIEN, DataUtil.nvl(mapInputedValue.get(Constants.REPORT.NGUOI_DAIDIEN)));
        sb = sb.replaceFirst(Constants.REPORT.CHUVU_NGUOI_DAIDIEN, DataUtil.nvl(mapInputedValue.get(Constants.REPORT.CHUVU_NGUOI_DAIDIEN)));
        sb = sb.replaceFirst(Constants.REPORT.EMAIL_NGUOI_LIENHE, DataUtil.nvl(mapInputedValue.get(Constants.REPORT.EMAIL_NGUOI_LIENHE)));
        sb = sb.replaceFirst(Constants.REPORT.SOTIEN, DataUtil.nvl(mapInputedValue.get(Constants.REPORT.SOTIEN)));
        sb = sb.replaceFirst(Constants.REPORT.CHIETKHAU, DataUtil.nvl(mapInputedValue.get(Constants.REPORT.CHIETKHAU)));
        sb = sb.replaceFirst(Constants.REPORT.DEPLOY_ADDRESS, DataUtil.nvl(mapInputedValue.get(Constants.REPORT.DEPLOY_ADDRESS)));
        sb = sb.replaceFirst(Constants.REPORT.NGUOI_LIENHE, DataUtil.nvl(mapInputedValue.get(Constants.REPORT.NGUOI_LIENHE)));
        sb = sb.replaceFirst(Constants.REPORT.SDT_NGUOI_LIENHE, DataUtil.nvl(mapInputedValue.get(Constants.REPORT.SDT_NGUOI_LIENHE)));
        sb = sb.replaceFirst(Constants.REPORT.CMND, DataUtil.nvl(mapInputedValue.get(Constants.REPORT.CMND)));
        sb = sb.replaceFirst(Constants.REPORT.NGAY_CAP_CMND, DataUtil.nvl(mapInputedValue.get(Constants.REPORT.NGAY_CAP_CMND)));
        sb = sb.replaceFirst(Constants.REPORT.NOI_CAP, DataUtil.nvl(DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.NOI_CAP))));
        sb = sb.replaceFirst(Constants.REPORT.TAX_DEPARTMENT, DataUtil.nvl(DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.TAX_DEPARTMENT))));
        return sb;
    }
}
