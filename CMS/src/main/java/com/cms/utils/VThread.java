/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import com.vaadin.ui.UI;

/**
 *
 * @author Tiep
 */
public class VThread extends Thread {

    public VThread() {
        UI.getCurrent().getSession().getLockInstance().lock();
    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        UI.getCurrent().getSession().getLockInstance().unlock();
    }

}
