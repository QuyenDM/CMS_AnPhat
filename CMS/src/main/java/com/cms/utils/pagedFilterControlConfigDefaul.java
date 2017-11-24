/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Truongbx3 created date 25/04/2015
 */
public class pagedFilterControlConfigDefaul {

    private String itemsPerPage = BundleUtils.getString("common.table.numberRecord");
    private String page = BundleUtils.getString("common.table.page");

    private String first = "<<";
    private String last = ">>";

    private String previous = "<";
    private String next = ">";

    private List<Integer> pageLengths;

    public String getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(String itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<Integer> getPageLengths() {
        return pageLengths;
    }

    public void setPageLengthsAndCaptions(List<Integer> pageLengths) {
        this.pageLengths = pageLengths;
    }

    public pagedFilterControlConfigDefaul() {
        pageLengths = new ArrayList<>();
        pageLengths.add(5);
        pageLengths.add(10);
        pageLengths.add(15);
        pageLengths.add(20);
        pageLengths.add(25);
    }
}
