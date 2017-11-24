/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.common.controller;

import com.vaadin.ui.UI;
import com.cms.component.WindowProgress;
import com.google.common.collect.Lists;
import com.vwf5.base.utils.ConditionBean;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author SONY
 */
public class ConmonController<T> implements Serializable {

    protected T dataSearch;
    protected T dataSelected;
    protected T dataSave;
    protected List<ConditionBean> lstConditionBeanSearch = Lists.newArrayList();
    protected List<T> lstDataSelected = Lists.newArrayList();
    protected List<T> lstDataSearch = Lists.newArrayList();
    protected WindowProgress wp;

    public ConmonController(Class<T> classEntity) {
        try {
            dataSearch = classEntity.newInstance();
            dataSelected = classEntity.newInstance();
            dataSave = classEntity.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doSearch() {
        wp = new WindowProgress("Running...");
        UI.getCurrent().addWindow(wp);
        UI.getCurrent().setPollInterval(500);
        WorkThreadSearch workThread = new WorkThreadSearch();
        workThread.start();
    }

    public void onDoSearch() {

    }

    public void doSave() {
        onDoSave();
    }

    public void doAdd() {
        wp = new WindowProgress("Running...");
        UI.getCurrent().addWindow(wp);
        UI.getCurrent().setPollInterval(500);
        WorkThreadAdd workThread = new WorkThreadAdd();
        workThread.start();
    }

    public void onDoSave() {

    }

    public void onDoAdd() {
    }

    public void actionAdd() {
        onActionAdd();
//        wp = new WindowProgress("Running...");
//        UI.getCurrent().addWindow(wp);
//        UI.getCurrent().setPollInterval(500);
//        WorkThreadActionAdd workThread = new WorkThreadActionAdd();
//        workThread.start();
    }

    public void onActionAdd() {

    }

    public void doUpdate() {
        onDoUpdate();
    }

    public void onDoUpdate() {

    }

    public void doDelete() {
        onDoDelete();
    }

    public void onDoDelete() {

    }

    public void resetDataSearch() {

    }

    /**
     *
     */
    class WorkThreadSearch extends Thread {

        @Override
        public void run() {
            try {
                UI.getCurrent().access(new Runnable() {
                    @Override
                    public void run() {
                        UI.getCurrent().getSession().getLockInstance().lock();
                        try {
                            onDoSearch();
                        } finally {
                            UI.getCurrent().getSession().getLockInstance().unlock();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                UI.getCurrent().access(new Runnable() {
                    @Override
                    public void run() {
                        wp.close();
                        UI.getCurrent().setPollInterval(-1);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     */
    class WorkThreadAdd extends Thread {

        @Override
        public void run() {
            try {
                onDoAdd();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                wp.close();
                UI.getCurrent().setPollInterval(-1);
            }
        }
    }

    /**
     *
     */
    class WorkThreadActionAdd extends Thread {

        @Override
        public void run() {
            try {
                onActionAdd();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                wp.close();
                UI.getCurrent().setPollInterval(-1);
            }
        }
    }
}
