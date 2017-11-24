/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.cms.dto.AppParamsDTO;
import com.google.common.collect.Lists;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 *
 * @author vtsoft
 */
public class ComboComponent extends CustomComponent {

    VerticalLayout mainLayout = new VerticalLayout();
    public ComboBox combo;
    AppParamsDTO objDefault = new AppParamsDTO();
    AppParamsDTO objall = new AppParamsDTO("", "", "", "", "");
    AppParamsDTO active = new AppParamsDTO("", "1", "", "1", Constants.APP_PARAMS.STATUS_TYPE);
    AppParamsDTO deActive = new AppParamsDTO("", "0", "", "2", Constants.APP_PARAMS.STATUS_TYPE);
    List<AppParamsDTO> lstStatus = Lists.newArrayList();
    Object objectAll;

    public ComboComponent() {

    }

    public ComboComponent(String all, String vaDefault,
            List<AppParamsDTO> appPatamDTO, String parType) {
        if (DataUtil.isListNullOrEmpty(appPatamDTO)) {
            appPatamDTO = Lists.newArrayList();
        }
        Layout layout = new HorizontalLayout();

        final BeanItemContainer<AppParamsDTO> container = new BeanItemContainer<AppParamsDTO>(
                AppParamsDTO.class);

        parType = parType.replace('_', '.').toLowerCase() + ".";
        if (appPatamDTO != null && appPatamDTO.size() > 0) {
            for (int i = 0; i < appPatamDTO.size(); i++) {
                if (appPatamDTO.get(i).getParCode().equalsIgnoreCase(vaDefault)
                        && !DataUtil.isStringNullOrEmpty(vaDefault)) {
                    appPatamDTO.get(i)
                            .setDisplayName(BundleUtils.getString(parType + appPatamDTO.get(i).getParName()));
                    objDefault = appPatamDTO.get(i);
                }
            }
        }

        // select all
        if (!DataUtil.isStringNullOrEmpty(all)) {

            objall.setDisplayName(BundleUtils.getString(parType + all));
            appPatamDTO.add(0, objall);
        }
        container.addAll(appPatamDTO);
        combo = new ComboBox("", container);
        layout.addComponent(combo);
        combo.setItemCaptionPropertyId("valueLangView");
        combo.setNullSelectionAllowed(false);
        // default
        if (!DataUtil.isStringNullOrEmpty(all)) {
            combo.setValue(objall);
        } else if (objDefault != null && !DataUtil.isStringNullOrEmpty(vaDefault)) {
            combo.setValue(objDefault);
        }

        setCompositionRoot(layout);
        setSizeFull();

    }

    public void fillDataCombo(ComboBox combo, String addAll, String valueDefault,
            List<AppParamsDTO> appPatamDTO, String parType) {
        if (DataUtil.isListNullOrEmpty(appPatamDTO)) {
            appPatamDTO = Lists.newArrayList();
        }
        List<AppParamsDTO> appPatamDTOs = Lists.newArrayList();
        appPatamDTOs.addAll(appPatamDTO);
        Layout layout = new HorizontalLayout();

        final BeanItemContainer<AppParamsDTO> container = new BeanItemContainer<AppParamsDTO>(
                AppParamsDTO.class);
        parType = parType.replace('_', '.').toLowerCase() + ".";
        //Set gia tri 
        if (appPatamDTOs.size() > 0) {
            String displayName;

            for (int i = 0; i < appPatamDTOs.size(); i++) {
                displayName = BundleUtils.getString(parType + appPatamDTOs.get(i).getParCode());
                displayName = displayName.contains(parType) ? appPatamDTO.get(i).getParName() : displayName;
                appPatamDTOs.get(i).setDisplayName(displayName);
                if (!DataUtil.isStringNullOrEmpty(valueDefault) && appPatamDTOs.get(i).getParCode().equalsIgnoreCase(valueDefault)) {
                    objDefault = appPatamDTOs.get(i);
                }
            }
        }
        // Them gia tri 
        if (!DataUtil.isStringNullOrEmpty(addAll)) {

            objall.setDisplayName(BundleUtils.getString(addAll));
            appPatamDTOs.add(0, objall);
        }
        container.addAll(appPatamDTOs);
        combo.setFilteringMode(FilteringMode.CONTAINS);
        combo.setContainerDataSource(container);
        combo.setItemCaptionPropertyId("displayName");
        combo.setNullSelectionAllowed(false);
        // default
        if (!DataUtil.isStringNullOrEmpty(valueDefault)) {
            combo.setValue(objDefault);
        } else if (!DataUtil.isStringNullOrEmpty(addAll)) {
            combo.setValue(objall);
        }

    }

    public void fillDataCombo(ComboBox combo, String addAll, String valueDefault) {
        lstStatus.clear();
        lstStatus.add(active);
        lstStatus.add(deActive);

        final BeanItemContainer<AppParamsDTO> container = new BeanItemContainer<>(
                AppParamsDTO.class);
        String statusType = Constants.APP_PARAMS.STATUS_TYPE;
        statusType = statusType.replace('_', '.').toLowerCase() + ".";
        //Set gia tri 
        if (lstStatus != null && lstStatus.size() > 0) {
            for (AppParamsDTO lstStatu : lstStatus) {
                lstStatu.setDisplayName(BundleUtils.getString(statusType + lstStatu.getParCode()));
                if (!DataUtil.isStringNullOrEmpty(valueDefault) && lstStatu.getParCode().equalsIgnoreCase(valueDefault)) {
                    objDefault = lstStatu;
                }
            }
        }
        // Them gia tri 
        if (!DataUtil.isStringNullOrEmpty(addAll)) {

            objall.setDisplayName(BundleUtils.getString(addAll));
            lstStatus.add(0, objall);
        }
        Map<String, String> maps = new HashMap<>();
        container.addAll(lstStatus);
        combo.setContainerDataSource(container);
        combo.setItemCaptionPropertyId("displayName");
        combo.setFilteringMode(FilteringMode.CONTAINS);
        combo.setNullSelectionAllowed(false);
        // default
        if (!DataUtil.isStringNullOrEmpty(valueDefault)) {
            combo.setValue(objDefault);
        } else if (!DataUtil.isStringNullOrEmpty(addAll)) {
            combo.setValue(objall);
        }
    }

    //Fill data to ComboBox trang thai
    public void fillCboStatusInEditTable(ComboBox combo, String addAll, String valueDefault) {
        lstStatus.clear();
        lstStatus.add(active);
        lstStatus.add(deActive);

        //Set gia tri 
        // Them gia tri 
        if (!DataUtil.isStringNullOrEmpty(addAll)) {
            combo.addItem(BundleUtils.getString(addAll));
        }
        if (lstStatus != null && lstStatus.size() > 0) {
            String statusType = lstStatus.get(0).getParType();
            statusType = statusType.replace('_', '.').toLowerCase() + ".";
            for (AppParamsDTO status : lstStatus) {
                combo.addItem(BundleUtils.getString(statusType + status.getParCode()));
            }
            if (!DataUtil.isStringNullOrEmpty(valueDefault)) {
                if (combo.containsId(BundleUtils.getString(statusType + valueDefault))) {
                    combo.setValue(BundleUtils.getString(statusType + valueDefault));
                }
            } else if (!DataUtil.isStringNullOrEmpty(addAll)) {
                combo.setValue(BundleUtils.getString(addAll));
            }
        }
        combo.setWidth("100%");
        combo.setFilteringMode(FilteringMode.CONTAINS);
        combo.setNullSelectionAllowed(false);
        combo.setBuffered(true);
        // default
    }

    public void fillCboAppParamsInEditTable(ComboBox combo, String addAll, String valueDefault,
            List<AppParamsDTO> lstAppParams) {
        // Them gia tri 
        if (!DataUtil.isStringNullOrEmpty(addAll)) {
            combo.addItem(BundleUtils.getString(addAll));
        }
        //Set gia tri        
        if (!lstAppParams.isEmpty() && lstAppParams.size() > 0) {
            String parType = lstAppParams.get(0).getParType().replace('_', '.').toLowerCase() + ".";
            for (AppParamsDTO apdto : lstAppParams) {
                combo.addItem(BundleUtils.getString(parType + apdto.getParCode()));
            }
            // default
            if (!DataUtil.isStringNullOrEmpty(valueDefault)) {
                if (combo.containsId(BundleUtils.getString(parType + valueDefault))) {
                    combo.setValue(BundleUtils.getString(parType + valueDefault));
                }
            } else if (!DataUtil.isStringNullOrEmpty(addAll)) {
                combo.setValue(BundleUtils.getString(addAll));
            }
        }
        combo.setWidth("100%");
        combo.setBuffered(true);
        combo.setFilteringMode(FilteringMode.CONTAINS);
        combo.setNullSelectionAllowed(false);
    }

//    
    public void setValue2ComboBox(ComboBox combo, String valueDefault) {
        Container container = combo.getContainerDataSource();
        List<AppParamsDTO> appPatamDTOs = Lists.newArrayList();
        Collection a = container.getItemIds();
        appPatamDTOs.addAll(a);

//        parType = parType.replace('_', '.').toLowerCase() + ".";
        //Set gia tri 
        if (!appPatamDTOs.isEmpty()) {
            for (AppParamsDTO appPatamDTO : appPatamDTOs) {
                if (!DataUtil.isStringNullOrEmpty(valueDefault) && appPatamDTO.getParCode().equalsIgnoreCase(valueDefault)) {
                    objDefault = appPatamDTO;
                }
            }
        }
        combo.select(objDefault);
    }

    public void setValues(ComboBox comboBox, List<?> lstCbo, String columnName) {
        comboBox.removeAllItems();
        if (DataUtil.isListNullOrEmpty(lstCbo)) {
            return;
        }
        Object obj = lstCbo.get(0);
        Class c = obj.getClass();

        final BeanItemContainer<Object> container
                = new BeanItemContainer<>(c);

        container.removeAllItems();
        container.addAll(lstCbo);

        comboBox.setContainerDataSource(container);

        comboBox.setItemCaptionPropertyId(columnName);
        comboBox.setNullSelectionAllowed(false);

        comboBox.setFilteringMode(FilteringMode.CONTAINS);
        comboBox.setImmediate(true);
    }

    public void setValues(ComboBox comboBox, List<?> lstCbo, String columnName, boolean isAllSelected) {
        comboBox.removeAllItems();
        if (DataUtil.isListNullOrEmpty(lstCbo)) {
            return;
        }
        Object obj = lstCbo.get(0);
        Class c = obj.getClass();

        final BeanItemContainer<Object> container
                = new BeanItemContainer<>(c);

        container.removeAllItems();
        container.addAll(lstCbo);

        comboBox.setContainerDataSource(container);
        comboBox.setItemCaptionPropertyId(columnName);
        if (isAllSelected) {
            comboBox.setNullSelectionAllowed(false);
            try {
                Object objectAll = c.newInstance();
                Method[] allMethods = c.getDeclaredMethods();
                for (Method m : allMethods) {
                    String mname = m.getName();
                    if (!mname.equals(columnName)) {
                        Field chars = c.getDeclaredField(columnName);
                        chars.setAccessible(true);
                        chars.set(objectAll, BundleUtils.getString("all"));
                        break;
                    }
                }
                container.addItemAt(0, objectAll);
                comboBox.select(objectAll);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            comboBox.setNullSelectionAllowed(false);
        }

        comboBox.setFilteringMode(FilteringMode.CONTAINS);
        comboBox.setImmediate(true);
    }

}
