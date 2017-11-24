/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;


/**
 *
 * @author TruongBx3
 */
public class ExcellHeaderComment {
    
    int row1;
    int row2;
    int column1;
    int column2;
    int dx1;
    int dx2;
    int dy1;
    int dy2;
    String value;

    public ExcellHeaderComment(int row1, int row2, int column1, int column2, int dx1, int dx2, int dy1, int dy2, String value) {
        this.row1 = row1;
        this.row2 = row2;
        this.column1 = column1;
        this.column2 = column2;
        this.dx1 = dx1;
        this.dx2 = dx2;
        this.dy1 = dy1;
        this.dy2 = dy2;
        this.value = value;
    }

    public int getRow1() {
        return row1;
    }

    public void setRow1(int row1) {
        this.row1 = row1;
    }

    public int getRow2() {
        return row2;
    }

    public void setRow2(int row2) {
        this.row2 = row2;
    }

    public int getColumn1() {
        return column1;
    }

    public void setColumn1(int column1) {
        this.column1 = column1;
    }

    public int getColumn2() {
        return column2;
    }

    public void setColumn2(int column2) {
        this.column2 = column2;
    }

    public int getDx1() {
        return dx1;
    }

    public void setDx1(int dx1) {
        this.dx1 = dx1;
    }

    public int getDx2() {
        return dx2;
    }

    public void setDx2(int dx2) {
        this.dx2 = dx2;
    }

    public int getDy1() {
        return dy1;
    }

    public void setDy1(int dy1) {
        this.dy1 = dy1;
    }

    public int getDy2() {
        return dy2;
    }

    public void setDy2(int dy2) {
        this.dy2 = dy2;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
    
}
