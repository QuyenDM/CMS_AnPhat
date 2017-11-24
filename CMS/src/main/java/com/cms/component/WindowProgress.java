/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.component;

import com.cms.utils.BundleUtils;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Window;

/**
 *
 * @author TruongBx3
 */
public class WindowProgress extends Window {

    ProgressBar pb;
    Label lb;

    public WindowProgress(String caption) {
        setClosable(false);
        setReadOnly(true);
        setResizable(false);
        setWidth("-1px");
        setHeight("-1px");
        setModal(true);
//        setStyleName("loading-window");
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("-1px");
        horizontalLayout.setHeight("-1px");
        horizontalLayout.setSpacing(true);
        horizontalLayout.setMargin(true);

        pb = new ProgressBar();
        pb.setIndeterminate(true);
        lb = new Label();
        lb.setValue(BundleUtils.getString("Running"));
        horizontalLayout.addComponent(pb);
        horizontalLayout.addComponent(lb);

        setContent(horizontalLayout);
    }

    public WindowProgress() {
        setClosable(false);
        setReadOnly(true);
        setResizable(false);
        setWidth("-1px");
        setHeight("-1px");
        setModal(true);
//        setStyleName("loading-window");
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("-1px");
        horizontalLayout.setHeight("-1px");
        horizontalLayout.setSpacing(true);
        horizontalLayout.setMargin(true);

        pb = new ProgressBar();
        pb.setIndeterminate(true);
        lb = new Label();
        lb.setValue(BundleUtils.getString("Running"));
        horizontalLayout.addComponent(pb);
        horizontalLayout.addComponent(lb);

        setContent(horizontalLayout);
    }

    public Label getLb() {
        return lb;
    }

    public void setLb(Label lb) {
        this.lb = lb;
    }

    public ProgressBar getPb() {
        return pb;
    }

    public void setPb(ProgressBar pb) {
        this.pb = pb;
    }

}
