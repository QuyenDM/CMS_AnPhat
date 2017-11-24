/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import com.cms.component.CustomPageTableFilter;
import java.io.Serializable;


/**
 *
 * @author truongBx3 
 */
@SuppressWarnings("serial")
public class PagedTableChangeEvent implements Serializable {
    private final CustomPageTableFilter<?> table;

    public PagedTableChangeEvent(CustomPageTableFilter<?> table) {
        this.table = table;
    }

    public CustomPageTableFilter<?> getTable() {
        return table;
    }

    public int getCurrentPage() {
        return table.getCurrentPage();
    }

    public int getTotalAmountOfPages() {
        return table.getTotalAmountOfPages();
    }
}

