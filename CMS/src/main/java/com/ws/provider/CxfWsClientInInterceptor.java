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
//import com.viettel.gnoc.common.dto.UserTokenGNOC;
//import com.viettel.gnoc.utils.Constants;
//import com.viettel.gnoc.utils.DateTimeUtils;
import java.io.InputStream;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.util.Date;
import java.util.logging.Logger;
import org.apache.cxf.attachment.DelegatingInputStream;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.AbstractLoggingInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingMessage;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedWriter;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.service.model.InterfaceInfo;

/**
 *
 * @author dungnv50
 */
public class CxfWsClientInInterceptor extends AbstractLoggingInterceptor {

    static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CxfWsClientInInterceptor.class);
    static Logger LOG = null;

    public CxfWsClientInInterceptor() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(Message message)
            throws Fault {
//        Logger log = getMessageLogger(message);
//        if ((this.writer != null) || (log.isLoggable(Level.INFO))) {
//            logging(log, message);
//        }
        try {

            String id = (String) message.getExchange().get(LoggingMessage.ID_KEY);
            if (id == null) {
                id = LoggingMessage.nextId();
                message.getExchange().put(LoggingMessage.ID_KEY, id);
            }

            String method = CxfWsClientOutInterceptor.mapMethodWS.get(id);
            String startLog = CxfWsClientOutInterceptor.mapLogWS.get(id);
            Date startDate = CxfWsClientOutInterceptor.mapDateWS.get(id);
            Date endDate = new Date();
//            System.out.println(startDate);
//            System.out.println(endDate);
//            System.out.println(endDate.getTime() - startDate.getTime());
            Long responeTime = (endDate.getTime() - startDate.getTime()) / 1000;

//            UserTokenGNOC user = (UserTokenGNOC) VaadinSession.getCurrent().getSession().getAttribute(Constants.vsaUserTokenAttribute);
            String endLog = "end_action|GNOC"
                    + "|" + DateTimeUtils.convertDateToString(new Date(), "dd/MM/yyyy HH:mm:ss")
//                    + "|" + user.getUserName()
                    + "|" + UI.getCurrent().getPage().getWebBrowser().getAddress()
                    + "|" + UI.getCurrent().getPage().getLocation().getHost()
                    + ":" + UI.getCurrent().getPage().getLocation().getPort()
                    + "|" + method + ".do"
                    + "|" + ""
                    + "|" + method
                    + "|" + responeTime
                    + "|[RequestId=" + VaadinSession.getCurrent().getCsrfToken() + "_" + id
//                    + "][Unit=" + user.getUnitCode()
                    + "][VTS-KPIID=null]";
            logger.info(startLog.replace("{responeTime}", responeTime.toString()));
            logger.info(endLog);
            CxfWsClientOutInterceptor.mapLogWS.remove(id);
            CxfWsClientOutInterceptor.mapDateWS.remove(id);
            CxfWsClientOutInterceptor.mapMethodWS.remove(id);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    protected void logging(Logger logger, Message message)
            throws Fault {
        if (message.containsKey(LoggingMessage.ID_KEY)) {
            return;
        }
        String id = (String) message.getExchange().get(LoggingMessage.ID_KEY);
        if (id == null) {
            id = LoggingMessage.nextId();
            message.getExchange().put(LoggingMessage.ID_KEY, id);
        }
        message.put(LoggingMessage.ID_KEY, id);
        LoggingMessage buffer = new LoggingMessage("Inbound Message\n----------------------------", id);
        if (!Boolean.TRUE.equals(message.get("decoupled.channel.message"))) {
            Integer responseCode = (Integer) message.get(Message.RESPONSE_CODE);
            if (responseCode != null) {
                buffer.getResponseCode().append(responseCode);
            }
        }
        String encoding = (String) message.get(Message.ENCODING);
        if (encoding != null) {
            buffer.getEncoding().append(encoding);
        }
        String httpMethod = (String) message.get("org.apache.cxf.request.method");
        if (httpMethod != null) {
            buffer.getHttpMethod().append(httpMethod);
        }
        String ct = (String) message.get("Content-Type");
        if (ct != null) {
            buffer.getContentType().append(ct);
        }
        Object headers = message.get(Message.PROTOCOL_HEADERS);
        if (headers != null) {
            buffer.getHeader().append(headers);
        }
        String uri = (String) message.get("org.apache.cxf.request.url");
        if (uri == null) {
            String address = (String) message.get(Message.ENDPOINT_ADDRESS);
            uri = (String) message.get("org.apache.cxf.request.uri");
            if ((uri != null) && (uri.startsWith("/"))) {
                if ((address != null) && (!address.startsWith(uri))) {
                    if ((address.endsWith("/")) && (address.length() > 1)) {
                        address = address.substring(0, address.length());
                    }
                    uri = address + uri;
                }
            } else {
                uri = address;
            }
        }
        if (uri != null) {
            buffer.getAddress().append(uri);
            String query = (String) message.get(Message.QUERY_STRING);
            if (query != null) {
                buffer.getAddress().append("?").append(query);
            }
        }
        if ((!isShowBinaryContent()) && (isBinaryContent(ct))) {
            buffer.getMessage().append("--- Binary Content ---").append('\n');
            log(logger, buffer.toString());
            return;
        }
//        if ((!isShowMultipartContent()) && (isMultipartContent(ct))) {
//            buffer.getMessage().append("--- Multipart Content ---").append('\n');
//            log(logger, buffer.toString());
//            return;
//        }
        InputStream is = (InputStream) message.getContent(InputStream.class);
        if (is != null) {
            logInputStream(message, is, buffer, encoding, ct);
        } else {
            Reader reader = (Reader) message.getContent(Reader.class);
            if (reader != null) {
                logReader(message, reader, buffer);
            }
        }
        log(logger, formatLoggingMessage(buffer));
    }

    protected void logReader(Message message, Reader reader, LoggingMessage buffer) {
        try {
            CachedWriter writer = new CachedWriter();
            IOUtils.copyAndCloseInput(reader, writer);
            message.setContent(Reader.class, writer.getReader());
            if (writer.getTempFile() != null) {
                buffer.getMessage().append("\nMessage (saved to tmp file):\n");
                buffer.getMessage().append("Filename: " + writer.getTempFile().getAbsolutePath() + "\n");
            }
            if ((writer.size() > this.limit) && (this.limit != -1)) {
                buffer.getMessage().append("(message truncated to " + this.limit + " bytes)\n");
            }
            writer.writeCacheTo(buffer.getPayload(), this.limit);
        } catch (Exception e) {
            throw new Fault(e);
        }
    }

    protected void logInputStream(Message message, InputStream is, LoggingMessage buffer, String encoding, String ct) {
        CachedOutputStream bos = new CachedOutputStream();
        if (this.threshold > 0L) {
            bos.setThreshold(this.threshold);
        }
        try {
            InputStream bis = (is instanceof DelegatingInputStream) ? ((DelegatingInputStream) is).getInputStream() : is;

//            IOUtils.copyAtLeast(bis, bos, this.limit == -1 ? Integer.MAX_VALUE : this.limit);
            IOUtils.copyAtLeast(bis, bos, 1000);
            bos.flush();
            bis = new SequenceInputStream(bos.getInputStream(), bis);
            if ((is instanceof DelegatingInputStream)) {
                ((DelegatingInputStream) is).setInputStream(bis);
            } else {
                message.setContent(InputStream.class, bis);
            }
            if (bos.getTempFile() != null) {
                buffer.getMessage().append("\nMessage (saved to tmp file):\n");
                buffer.getMessage().append("Filename: " + bos.getTempFile().getAbsolutePath() + "\n");
            }
            if ((bos.size() > this.limit) && (this.limit != -1)) {
                buffer.getMessage().append("(message truncated to " + this.limit + " bytes)\n");
            }
            writePayload(buffer.getPayload(), bos, encoding, ct);

            bos.close();
        } catch (Exception e) {
            throw new Fault(e);
        }
    }

    protected String formatLoggingMessage(LoggingMessage loggingMessage) {
        return loggingMessage.toString();
    }

    Logger getMessageLogger(Message message) {
        Endpoint ep = message.getExchange().getEndpoint();
        if ((ep == null) || (ep.getEndpointInfo() == null)) {
            return getLogger();
        }
        EndpointInfo endpoint = ep.getEndpointInfo();
        if (endpoint.getService() == null) {
            return getLogger();
        }
        LOG = (Logger) endpoint.getProperty("MessageLogger", Logger.class);
        if (LOG == null) {
            String serviceName = endpoint.getService().getName().getLocalPart();
            InterfaceInfo iface = endpoint.getService().getInterface();
            String portName = endpoint.getName().getLocalPart();
            String portTypeName = iface.getName().getLocalPart();
            String logName = "org.apache.cxf.services." + serviceName + "." + portName + "." + portTypeName;

            LOG = LogUtils.getL7dLogger(getClass(), null, logName);
            endpoint.setProperty("MessageLogger", logger);
        }
        return LOG;
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
