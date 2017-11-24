/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.ui;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author TiepNV6
 */
public class CommonUI extends CustomComponent{

    public HorizontalSplitPanel mainLayout;
    public Panel panelLeft;
    public Panel panelRight;
    public VerticalLayout leftLayout;
    public VerticalLayout rightLayout;

   
    public CommonUI(String leftCaption,String rightCaption) {
//        Style
//        Page.Styles styles = Page.getCurrent().getStyles();
//        styles.add("*{font-family: tahoma; font-size: 13px;}");
//        styles.add(".custom-feildset{border: 1px solid #ccc;}");
//        styles.add(".v-caption-custom-feildset{position: relative}");
//        styles.add(".v-caption-custom-feildset span{display: block; margin-top: -8px; position: absolute; z-index: 1000 !important; background: #fff;}");
//        styles.add(".v-textfield-dashing{background: #99EE6B;}");

        mainLayout = new HorizontalSplitPanel();
        mainLayout.setSizeFull();
        mainLayout.setStyleName("v-scrollable");
        setStyleName("v-scrollable");
        setWidth("100.0%");
        setHeight("100%");
        panelLeft = new Panel();
        panelRight = new Panel();
        panelLeft.setImmediate(true);
        mainLayout.setImmediate(true);
        panelRight.setImmediate(true);
        leftLayout = new VerticalLayout();
        rightLayout = new VerticalLayout();
//         set margin
        leftLayout.setMargin(true);
        rightLayout.setMargin(true);
//        set caption
        panelLeft.setCaption(leftCaption);
        panelRight.setCaption(rightCaption);

        panelLeft.setContent(leftLayout);
        panelRight.setContent(rightLayout);

        panelLeft.setSizeFull();
        panelRight.setSizeFull();

        leftLayout.setWidth("100%");
        leftLayout.setHeight("-1px");
        rightLayout.setWidth("100%");
        rightLayout.setHeight("-1px");
//        panelLeft.setHeight("1200px");
//        panelRight.setHeight("1200px");
        mainLayout.setFirstComponent(panelLeft);
        mainLayout.setSecondComponent(panelRight);
        setCompositionRoot(mainLayout);

    }


}
