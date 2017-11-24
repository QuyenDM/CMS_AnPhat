package com.ws.provider;

import com.google.common.collect.Lists;
import com.cms.base.servicecaller.Mapper;
import com.cms.base.servicecaller.WsRequestCreator;

import java.util.*;

/**
 * Created by vtsoft on 4/3/2015.
 */
public class WsCallerFactory {
    private Map<String, WsEndpoint> wsEndpointMap;
    private List<String> keys;


    private static void main(String[] args) {
        WsCallerFactory wsCallerFactory = new WsCallerFactory();
        Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
        map.put("com.viettel.ws", new WsEndpoint("ep1", "h1", "s1"));
        map.put("com.viettel.ws.provider", new WsEndpoint("ep2", "h2", "s2"));
        map.put("com.viettel.ws.provider.WsAttContent", new WsEndpoint("ep3", "h3", "s3"));

        wsCallerFactory.setWsEndpointMap(map);

        System.out.println(wsCallerFactory.getWsEndpoint(WsAttContent.class));
        System.out.println(wsCallerFactory.getWsEndpoint(WsEndpoint.class));
        System.out.println(wsCallerFactory.getWsEndpoint(Mapper.class));
    }

    public WsCallerFactory() {
    }

    /**
     * Ham tao WSCaller mac dinh voi fixAddress = false
     * @param callClass
     * @param function
     * @return
     * @throws Exception
     */
    public WsRequestCreator createWsCaller(Class callClass, String function) throws Exception {
        return createWsCaller(callClass, function, false);
    }

    public WsRequestCreator createWsCaller(Class callClass, String function, boolean fixAddress) throws Exception {

        WsEndpoint wsEndpoint = getWsEndpoint(callClass);
        if (wsEndpoint == null) {
            throw new WsEndpointNotFound(callClass.getName());
        }

        ///ProductSpecCharacterServiceImpl?wsdl
        WsRequestCreator requestCreator = new WsRequestCreator();
        
        if (!wsEndpoint.getAddress().endsWith("wsdl")) {
            if (fixAddress) {
                requestCreator.setWsAddress(wsEndpoint.getAddress() + "?wsdl");
            } else {
                requestCreator.setWsAddress(wsEndpoint.getAddress() + callClass.getSimpleName() + "?wsdl");
            }
        } else {
            requestCreator.setWsAddress(wsEndpoint.getAddress());
        }

        requestCreator.setTargetNameSpace(wsEndpoint.getTargetNameSpace());
        requestCreator.setServiceName(function);
        requestCreator.setUsername(wsEndpoint.getUserName());
        requestCreator.setPassword(wsEndpoint.getPassword());

        return requestCreator;
    }

    /**
     * @param callClass
     * @return com.viettel.sale => ep1
     * com.viettel.sale.connect => ep2
     * com.viettel.sale.connect.InputSubImpl => ep3
     * <p/>
     * input: com.viettel.sale.trans.SaleTransImpl
     * output: ep1
     * <p/>
     * input: com.viettel.sale.connect.InputCustImpl
     * output: ep2
     * <p/>
     * input: com.viettel.sale.connect.InputSubImpl
     * output: ep3
     */
    private WsEndpoint getWsEndpoint(Class callClass) {
        String classPath = callClass.getName();
        for (String key : keys) {
            if (classPath.startsWith(key))
                return wsEndpointMap.get(key);
        }

        return null;
    }

    public Map<String, WsEndpoint> getWsEndpointMap() {
        return wsEndpointMap;
    }

    public void setWsEndpointMap(Map<String, WsEndpoint> wsEndpointMap) {
        this.wsEndpointMap = wsEndpointMap;
        keys = Lists.newArrayList(wsEndpointMap.keySet());
        Collections.sort(keys, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
    }
}
