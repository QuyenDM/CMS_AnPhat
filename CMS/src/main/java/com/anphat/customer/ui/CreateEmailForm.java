/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anphat.customer.ui;

import com.cms.component.CommonDialog;
import com.cms.dto.CustomerDTO;
import com.cms.utils.Constants;
import com.vaadin.ui.RichTextArea;
import com.vwf5.base.utils.DataUtil;
import java.util.Map;

/**
 *
 * @author quyen
 */
public class CreateEmailForm extends CommonDialog {

    private RichTextArea sample;
    private final Map<String, String> mapInputedValue;
    private final CustomerDTO customer;

    public CreateEmailForm(CustomerDTO customer, Map<String, String> mapInputedValue) {
        setInfo("80%", "-1px", "Gửi email cho nhân viên điều phối");
        this.customer = customer;
        this.mapInputedValue = mapInputedValue;
        buildEmailContent();
    }

    private void buildEmailContent() {
        sample = new RichTextArea();

        String mailContent = getMail();

        sample.setValue(mailContent);
        sample.setImmediate(true);
        sample.setWidth("100%");
        sample.setHeight("500px");
        mainLayout.addComponent(sample);
    }

    private String getMail() {
        StringBuilder sb = new StringBuilder();
        sb.append("<b>TÊN CÔNG TY: </b> %s <br>MST: %s <br>Địa chỉ VAT: %s <br>NGƯỜI ĐẠI DIỆN: %s"
                + "<br>Email: %s <br>Hóa đơn ghi: %s <br>Chiết khấu: %s "
                + "<br>Địa chỉ COD/ EMS: %s <br>Người nhận: %s <br>Điện thoại: %s <br>"
                + "Số CMT: %s <br>Ngày Cấp: %s <br>Nơi Cấp: %s");
        return String.format(sb.toString(),
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.NAME)),
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.TAX_CODE)),
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.OFFICE_ADDRESS)),
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.NGUOI_DAIDIEN)),
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.EMAIL_NGUOI_LIENHE)),
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.SOTIEN)),
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.CHIETKHAU)),
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.DEPLOY_ADDRESS)),
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.NGUOI_LIENHE)),
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.SDT_NGUOI_LIENHE)),
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.CMND)),
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.NGAY_CAP_CMND)),
                DataUtil.getStringNullOrZero(mapInputedValue.get(Constants.REPORT.NOI_CAP)));
    }
}
