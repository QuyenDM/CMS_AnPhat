/*
 * Copyright 2000-2013 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.cms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.anphat.cms.appparams.controller.ListAppParamsController;
import com.anphat.cms.appparams.ui.SearchAppParamsForm;
import com.cms.component.CommonOnePanelUI;
import com.cms.component.GridManyButton;
import com.cms.ui.CommonTableFilterPanel;
import com.cms.utils.BundleUtils;
import com.cms.utils.Constants;

public class AppParams extends CommonOnePanelUI implements View {

    public static final String SEARCH = BundleUtils.getString("common.button.search");
    //define controller
    private ListAppParamsController appParamsController;
    //define form
    private SearchAppParamsForm appParamsForm;
    //define table ui
    private CommonTableFilterPanel appParamTablePanel;
    //caption for panel
    public static String captionAppParamPanel = BundleUtils.getString("caption.appParam.panel");
    //Button in view
    private Button btnSearch;
    private Button btnRefresh;

    public AppParams() {
        //khoi tao
        appParamsForm = new SearchAppParamsForm();
        appParamTablePanel = new CommonTableFilterPanel();
        GridManyButton gridManyButton = new GridManyButton(new String[]{Constants.BUTTON_SEARCH,Constants.BUTTON_REFRESH});
        btnSearch = gridManyButton.getBtnCommon().get(0);
        btnRefresh = gridManyButton.getBtnCommon().get(1);        
        //Them cac component vao panel
//        btnSearch = new Button(SEARCH);
//        btnRefresh = new Button(BundleUtils.getString("common.button.refresh"));
//        //Them cac component vao panel
//        HorizontalLayout horizontalLayout = new HorizontalLayout();
//        horizontalLayout.setWidth("-1px");
//        horizontalLayout.setHeight("-1px");
//        btnSearch.setIcon(new ThemeResource(Constants.ICON.SEARCH));
//        horizontalLayout.addComponent(btnSearch);
//        btnRefresh.setIcon(new ThemeResource(Constants.ICON.RESET));
//        horizontalLayout.addComponent(btnRefresh);

        panelMain.setCaption(captionAppParamPanel);
        layoutMain.addComponent(appParamsForm);
        layoutMain.addComponent(gridManyButton);
        layoutMain.setComponentAlignment(gridManyButton, Alignment.MIDDLE_CENTER);
        layoutMain.addComponent(appParamTablePanel);
        this.appParamsController = new ListAppParamsController(this);
    }

    public ListAppParamsController getAppParamsController() {
        return appParamsController;
    }

    public void setAppParamsController(ListAppParamsController appParamsController) {
        this.appParamsController = appParamsController;
    }

    public SearchAppParamsForm getAppParamsForm() {
        return appParamsForm;
    }

    public void setAppParamsForm(SearchAppParamsForm appParamsForm) {
        this.appParamsForm = appParamsForm;
    }

    public CommonTableFilterPanel getAppParamTablePanel() {
        return appParamTablePanel;
    }

    public void setAppParamTablePanel(CommonTableFilterPanel appParamTablePanel) {
        this.appParamTablePanel = appParamTablePanel;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(Button btnSearch) {
        this.btnSearch = btnSearch;
    }

    public Button getBtnRefresh() {
        return btnRefresh;
    }

    public void setBtnRefresh(Button btnRefresh) {
        this.btnRefresh = btnRefresh;
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }

}
