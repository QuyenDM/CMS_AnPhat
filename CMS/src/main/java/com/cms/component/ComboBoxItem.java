/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.component;

/**
 *
 * @author phamthanh
 */
public class ComboBoxItem {

    private String id;
    private String description;

    public ComboBoxItem(final String id, final String description) {
        this.id = id;
        this.description = description;
    }

    public final void setId(final String id) {
        this.id = id;
    }

    public final void setDescription(final String description) {
        this.description = description;
    }

    public final String getId() {
        return id;
    }

    public final String getDescription() {
        return description;
    }
}
