/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.component;

import com.cms.ui.CommonButtonClickListener;
import com.vaadin.annotations.Push;
import com.vaadin.data.Property;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.UI;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quyen
 */
@Push(PushMode.MANUAL)
public class CommonValueChangeListener implements Property.ValueChangeListener {

    public WindowProgress progress;
    public Set collect;
    public Object selected;

    public CommonValueChangeListener() {
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        selected = event.getProperty().getValue();
        if (selected instanceof Set) {
            collect = (Set) event.getProperty().getValue();
        }
        if (isValidated()) {
            WorkThread workThread = new WorkThread();
            workThread.start();
        }
    }

    /**
     * Override to validate data before execute
     *
     * @return true if data is validated false if data is not validated
     */
    public boolean isValidated() {
        return true;
    }

    /**
     * Override to execute value change listener
     */
    public void execute() {
    }

    class WorkThread extends Thread {

        @Override
        public void run() {
            UI.getCurrent().access(new Runnable() {
                @Override
                public void run() {
                    try {
                        progress = new WindowProgress();
                        UI.getCurrent().addWindow(progress);
                        UI.getCurrent().push();
                        execute();
                    } catch (IllegalArgumentException | NullPointerException e) {
                        Logger.getLogger(CommonButtonClickListener.class.getName()).log(Level.SEVERE, null, e);
                    } catch (Exception e) {
                        Logger.getLogger(CommonButtonClickListener.class.getName()).log(Level.SEVERE, null, e);
                    } finally {
                        progress.close();
                        UI.getCurrent().setPollInterval(-1);
                    }
                }
            });
        }
    }
}
