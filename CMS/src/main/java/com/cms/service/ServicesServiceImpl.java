package com.cms.service;

import com.cms.dto.ServicesDTO;
import com.cms.utils.BundleUtils;
import com.vwf5.base.dto.ResultDTO;
import com.vwf5.base.utils.ConditionBean;
import com.ws.provider.CxfWsClientFactory;
import com.ws.provider.WsEndpoint;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 06/08/2016 10:03:59
 */
public class ServicesServiceImpl implements ServicesService {

    public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ServicesServiceImpl.class);
    CxfWsClientFactory wsClientFactory;
    private ServicesService client;
    public static String strWsWMSUrl = BundleUtils.getStringCas("cms_ws_url");
    public static String targetNamePath = BundleUtils.getStringCas("cms_ws_targeNameSpace");
    public static String timeOut = BundleUtils.getStringCas("timeOut");

    private static ServicesServiceImpl instance;

    /**
     *
     * @return
     */
    public static synchronized ServicesServiceImpl getInstance() {
        if (instance == null) {
            instance = new ServicesServiceImpl();
        }
        return instance;
    }

    public ServicesServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(ServicesService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(ServicesService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public String updateServices(ServicesDTO servicesDTO) {
        return client.updateServices(servicesDTO);
    }

    @Override
    public String deleteServices(Long id) {
        return client.deleteServices(id);
    }

    @Override
    public String deleteListServices(List<ServicesDTO> servicesListDTO) {
        return client.deleteListServices(servicesListDTO);
    }

    @Override
    public ServicesDTO findServicesById(Long id) {
        return client.findServicesById(id);
    }

    @Override
    public List<ServicesDTO> getListServicesDTO(ServicesDTO servicesDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListServicesDTO(servicesDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public ResultDTO insertServices(ServicesDTO servicesDTO) {
        return client.insertServices(servicesDTO);
    }

    @Override
    public String insertOrUpdateListServices(List<ServicesDTO> servicesDTO) {
        return client.insertOrUpdateListServices(servicesDTO);
    }

    @Override
    public List<String> getSequenseServices(String seqName, int... size) {
        return client.getSequenseServices(seqName, size);
    }

    @Override
    public List<ServicesDTO> getListServicesByCondition(List<ConditionBean> lstCondition, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListServicesByCondition(lstCondition, rowStart, maxRow, sortType, sortFieldList);
    }

}
