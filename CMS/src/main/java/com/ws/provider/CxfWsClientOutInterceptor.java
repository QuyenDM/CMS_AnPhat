/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.provider;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.cms.utils.Constants;
import com.cms.utils.DateTimeUtils;
import com.cms.utils.StringUtils;
//import com.viettel.dashboard.utils.StringUtils;
//import com.viettel.gnoc.common.dto.UserTokenGNOC;
//import com.viettel.gnoc.utils.Constants;
//import com.viettel.gnoc.utils.DateTimeUtils;
//import com.viettel.gnoc.utils.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;
import org.apache.cxf.interceptor.AbstractLoggingInterceptor;
import org.apache.cxf.interceptor.LoggingMessage;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;

/**
 *
 * @author dungnv50
 */
public class CxfWsClientOutInterceptor extends AbstractLoggingInterceptor {

    static final Logger logger = Logger.getLogger(CxfWsClientInInterceptor.class.getName());

    public CxfWsClientOutInterceptor() {
        super(Phase.PRE_STREAM);
    }
    public static final Map<String, String> mapLogWS = new HashMap();
    public static final Map<String, Date> mapDateWS = new HashMap();
    public static final Map<String, String> mapMethodWS = new HashMap();

    @Override
    public void handleMessage(Message message) {
        try {
//            UserTokenGNOC user = (UserTokenGNOC) VaadinSession.getCurrent().getSession().getAttribute(Constants.vsaUserTokenAttribute);
            String id = (String) message.getExchange().get(LoggingMessage.ID_KEY);
            if (id == null) {
                id = LoggingMessage.nextId();
                message.getExchange().put(LoggingMessage.ID_KEY, id);
                System.out.println("id: " + id);
            }

            try {
                List<String> listRequest = new ArrayList<>();
                listRequest.add(VaadinSession.getCurrent().getCsrfToken() + "_" + id);
                Map<String, List<String>> protocolHeader = (TreeMap) message.get(Message.PROTOCOL_HEADERS);
                if (protocolHeader == null) {
                    protocolHeader = new TreeMap<>();
                }
                protocolHeader.put("RequestId", listRequest);
                message.put(Message.PROTOCOL_HEADERS, protocolHeader);
            } catch (Exception e) {
                //            ex.printStackTrace();
            }

            String log = "start_action|GNOC"
                    + "|" + DateTimeUtils.convertDateToString(new Date(), "dd/MM/yyyy HH:mm:ss")
//                    + "|" + user.getUserName()
                    + "|" + UI.getCurrent().getPage().getWebBrowser().getAddress()
                    + "|" + UI.getCurrent().getPage().getLocation().getHost()
                    + ":" + UI.getCurrent().getPage().getLocation().getPort()
                    + "|" + getMethod(message) + ".do"
                    + "|" + ""
                    + "|" + getMethod(message)
                    + "|{responeTime}|[RequestId=" + VaadinSession.getCurrent().getCsrfToken() + "_" + id
//                    + "][Unit=" + user.getUnitCode()
                    + "][VTS-KPIID=null]";

            mapDateWS.put(id, new Date());
            mapMethodWS.put(id, getMethod(message));
            mapLogWS.put(id, log);

        } catch (Exception ex) {
//            ex.printStackTrace();
        }
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    private String getMethod(Message message) {
        return StringUtils.getStringParttern(message.get("java.lang.reflect.Method").toString(), "com.viettel.[\\w\\.]+\\(").replace("(", "");
    }
}
