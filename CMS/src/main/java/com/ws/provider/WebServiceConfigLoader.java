package com.ws.provider;

//import com.viettel.ws.common.utils.WsRequestCreator;
import com.cms.base.servicecaller.WsRequestCreator;
import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.beanutils.BeanDeclaration;
import org.apache.commons.configuration.beanutils.BeanHelper;
import org.apache.commons.configuration.beanutils.XMLBeanDeclaration;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

//import com.viettel.web.common.WebConfiguration;

/**
 * Created by thiendn1 on 1/16/2015.
 */
public class WebServiceConfigLoader {
    private static final Logger logger = Logger.getLogger(WebServiceConfigLoader.class);

    CombinedConfiguration configuration;
    private final String DEFAULT_ROOT_FILE = "wsconfig.xml";

    private ConcurrentHashMap<String, ConcurrentHashMap<String, WsTemplate>> wsTemplateMap = new ConcurrentHashMap<>();

    private static WebServiceConfigLoader instance;
    private String notifyAddress = null;

    static {
        instance = new WebServiceConfigLoader();
    }

    public WebServiceConfigLoader() {
        XMLConfiguration rootConfig = null;
        configuration = new CombinedConfiguration();
        try {
            ResourceBundle rb1 = null;
            rb1 = ResourceBundle.getBundle("cas", Locale.US);
            String runNotify = rb1.getString("runNotify");
            if (Boolean.valueOf(runNotify)) {
                //load all config file
                List<Object> configList = new ArrayList<>();
                configList.add("NotifyServiceRequest.xml");
                configList.add("NotifyServiceResponse.xml");
                for (Object configFile : configList) {
                    XMLConfiguration newRootConfig = new XMLConfiguration(configFile.toString());
                    configuration.addConfiguration(newRootConfig);
                }
//            loadAllWsConfig();
                loadNotifyCationWsConfig();
            }

        } catch (Exception e) {
            logger.error(e);
        }
    }

    private void loadNotifyCationWsConfig() {
//        //prepare hashmap for webservice
//        try {
//            ResourceBundle rb1 = null;
//            rb1 = ResourceBundle.getBundle("cas", Locale.US);
//            notifyAddress = rb1.getString("notifyUrl");
//            //test if wsdl is handled
//            if (notifyAddress != null) {
//                if (wsTemplateMap.get(notifyAddress) == null) {
//                    ConcurrentHashMap<String, WsTemplate> temp = null;
//                    try {
//                        temp = importWSDL(notifyAddress);
//                    } catch (Exception e) {
//                        logger.error(e);
////                        WebConfiguration.setRunNotify(false);
//                    }
//                    if (temp != null && !temp.isEmpty()) {
//                        wsTemplateMap.put(notifyAddress.replaceAll("(?ui)\\?wsdl", ""), temp);
//                    }
//                }
//            }
//        } finally {
//            closeSoapUI();
//        }
    }

    private void loadAllWsConfig() {
//        //prepare hashmap for webservice
//        try {
//            Iterator<String> keys = configuration.getKeys();
//            while (keys.hasNext()) {
//                String key = keys.next();
//                Object object = configuration.getProperty(key);
//                if (key.contains("config-class") && object.equals(WsRequestCreator.class.getName())) {
//                    WsRequestCreator wsConfig = null;
//                    try {
//                        wsConfig = getWsConfig(key.replaceAll("\\[.*\\]", ""));
//                    } catch (Exception e) {
//                        logger.error(e);
//                    }
//                    String wsdl = wsConfig.getWsAddress();
//                    //test if wsdl is handled
//                    if (wsdl != null) {
//                        if (wsTemplateMap.get(wsdl) == null) {
//                            ConcurrentHashMap<String, WsTemplate> temp = null;
//                            try {
//                                temp = importWSDL(wsdl);
//                            } catch (Exception e) {
//                                logger.error(e);
//                            }
//                            if (temp != null && !temp.isEmpty()) {
//                                wsTemplateMap.put(wsdl.replaceAll("(?ui)\\?wsdl", ""), temp);
//                            }
//                        }
//                    }
//                }
//            }
//        } finally {
//            closeSoapUI();
//        }
    }

//    private ConcurrentHashMap<String, WsTemplate> importWSDL(String wsdl) throws Exception {
//        ConcurrentHashMap<String, WsTemplate> temp = new ConcurrentHashMap<>();
//        WsdlProject project = new WsdlProject();
//        for (Operation operation : WsdlImporter.importWsdl(project, wsdl)[0].getAllOperations()) {
//            WsdlOperation wsdlOperation = (WsdlOperation) operation;
//            ByteArrayInputStream is = new ByteArrayInputStream(wsdlOperation.createRequest(true).getBytes());
//            SOAPMessage request = MessageFactory.newInstance().createMessage(null, is);
//            WsTemplate template = new WsTemplate();
//            //prepare for header
//            SOAPHeader header = request.getSOAPHeader();
//            if (header.getChildNodes() != null && header.getChildNodes().getLength() > 0) {
//                List<String> headerNodeList = new ArrayList<>();
//                template.setHeaderList(printAlias(headerNodeList, header.getChildNodes()));
//                header.removeContents();
//                header.setTextContent(Const.WEB_SERVICE_CONS.ARG_HEADER);
//            }
//            //prepare for body
//            SOAPBody body = request.getSOAPBody();
//            String operatorMethod = body.getChildNodes().item(1).getNodeName();
//            List<String> list = new ArrayList<>();
//            template.setAliasList(printAlias(list, body.getChildNodes().item(1).getChildNodes()));
//            body.removeContents();
//            SOAPBodyElement bodyElement = body
//                    .addBodyElement(new QName(operatorMethod));
//            bodyElement.setTextContent(Const.WEB_SERVICE_CONS.ARG_BODY);
//            template.setSoapMessage(XmlSchema.node2String(request.getSOAPPart()));
//            if (operatorMethod.contains(Const.WEB_SERVICE_CONS.SEPERATE_OPERATOR)) {
//                operatorMethod = operatorMethod.split(Const.WEB_SERVICE_CONS.SEPERATE_OPERATOR)[1];
//            }
//            temp.put(operatorMethod, template);
//        }
//        return temp;
//
//    }

    public static WebServiceConfigLoader getInstance() {
        return instance;
    }

    private CombinedConfiguration getConfiguration() {
        return configuration;
    }

    public ConcurrentHashMap<String, ConcurrentHashMap<String, WsTemplate>> getWsTemplateMap() {
        return wsTemplateMap;
    }

    //use print note to get all level 1 alias
    private List<String> printAlias(List<String> list, NodeList nodeList) {
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tempNode = nodeList.item(count);
            // get node name
            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                list.add(tempNode.getNodeName());
            }
        }
        return list;

    }

    private void closeSoapUI() {
//        // Need to shutdown all the threads invoked by each SoapUI TestSuite
//        SoapUI.getThreadPool().shutdown();
//        try {
//            SoapUI.getThreadPool().awaitTermination(1, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            logger.error(e);
//        }
//        // Now to shutdown the monitor thread setup by SoapUI
//        Thread[] tarray = new Thread[Thread.activeCount()];
//        Thread.enumerate(tarray);
//        for (Thread t : tarray) {
//            if (t instanceof SoapUIMultiThreadedHttpConnectionManager.IdleConnectionMonitorThread) {
//                ((SoapUIMultiThreadedHttpConnectionManager.IdleConnectionMonitorThread) t)
//                        .shutdown();
//            }
//        }
//        // Finally Shutdown SoapUI itself.
//        SoapUI.shutdown();
    }

    public WsRequestCreator getWsConfig(String key) throws Exception {
        BeanDeclaration decl = new XMLBeanDeclaration(configuration, key);
        if (decl == null) {
            throw new Exception("The key " + key + "not found");
        }
        WsRequestCreator ws = (WsRequestCreator) BeanHelper.createBean(decl);
        return ws;
    }

    public WsRequestCreator getWsConfigOperator(String key) throws Exception {
        String wsKey = configuration.getString(key + ".url");
        String serviceMethod = configuration.getString(key + ".service");
        BeanDeclaration decl = new XMLBeanDeclaration(configuration, wsKey);
        if (decl == null) {
            throw new Exception("The key " + wsKey + "not found");
        }
        WsRequestCreator ws = (WsRequestCreator) BeanHelper.createBean(decl);
        ws.setServiceName(serviceMethod);
        if (ws.getWsAddress().equals("notify")) {
            ws.setWsAddress(notifyAddress);
        }
        return ws;
    }

    public static void main(String[] args) throws Exception {
        WebServiceConfigLoader.getInstance();

    }

}
