/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.ui;

import com.cms.component.WindowProgress;
import com.vaadin.annotations.Push;
import com.vaadin.data.Property;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author quyen
 */
@Push(PushMode.MANUAL)
public class CommonValueChangeListener implements Property.ValueChangeListener {
    
    public WindowProgress progress;

    public CommonValueChangeListener() {
    }    

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        if (isValidated()) {
            WorkThread workThread = new WorkThread();
            workThread.start();
        }
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
                        UI.getCurrent().setPollInterval(1);
                        execute();
                    } catch (IllegalArgumentException | NullPointerException e) {
                        Logger.getLogger(CommonButtonClickListener.class.getName()).log(Level.SEVERE, null, e);
//                        e.printStackTrace();
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

    //Override lai ham execute
    public void execute() throws Exception {

    }

    //Override lai ham isValid
    public boolean isValidated() {
        return true;
    }
}
