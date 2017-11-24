package com.cms.dto;

import com.cms.common.basedto.BaseDTO;
import com.cms.utils.StringUtils;

/**
 * @author QuyenDM
 * @version 1.0
 * @since 06/12/2016 23:05:01
 */
public class StatisticStaffPointDTO extends BaseDTO {
    //Fields

    private String id;
    private String staffId;
    private String staffCode;
    private String staffName;
    private String numberData;
    private String numberCall;
    private String w1Point;
    private String w2Point;
    private String w3Point;
    private String w4Point;
    private String w5Point;
    private String monthPoint;
    private String cancelPoint;
    private String totalPoint;
    //Constructor

    public StatisticStaffPointDTO() {

    }

    public StatisticStaffPointDTO(String id, String staffId, String staffCode, String staffName, String numberData, String numberCall, String w1Point, String w2Point, String w3Point, String w4Point, String w5Point, String monthPoint, String cancelPoint, String totalPoint) {
        this.id = id;
        this.staffId = staffId;
        this.staffCode = staffCode;
        this.staffName = staffName;
        this.numberData = numberData;
        this.numberCall = numberCall;
        this.w1Point = w1Point;
        this.w2Point = w2Point;
        this.w3Point = w3Point;
        this.w4Point = w4Point;
        this.w5Point = w5Point;
        this.monthPoint = monthPoint;
        this.cancelPoint = cancelPoint;
        this.totalPoint = totalPoint;
    }

    public StatisticStaffPointDTO(String staffCode, String staffName, String numberData, String numberCall, String w1Point, String w2Point, String w3Point, String w4Point, String w5Point, String cancelPoint) {
        this.staffCode = staffCode;
        this.staffName = staffName;
        this.numberData = numberData;
        this.numberCall = numberCall;
        this.w1Point = w1Point;
        this.w2Point = w2Point;
        this.w3Point = w3Point;
        this.w4Point = w4Point;
        this.w5Point = w5Point;
        this.cancelPoint = cancelPoint;
        calculatePoint();
    }

    private void calculatePoint() {
        Double totalPointT = StringUtils.isDouble(w1Point) ? Double.parseDouble(w1Point) : 0D;
        totalPointT += StringUtils.isDouble(w2Point) ? Double.parseDouble(w2Point) : 0D;
        totalPointT += StringUtils.isDouble(w3Point) ? Double.parseDouble(w3Point) : 0D;
        totalPointT += StringUtils.isDouble(w4Point) ? Double.parseDouble(w4Point) : 0D;
        totalPointT += StringUtils.isDouble(w5Point) ? Double.parseDouble(w5Point) : 0D;
        this.monthPoint = totalPointT.toString();
        totalPointT -= StringUtils.isDouble(cancelPoint) ? Double.parseDouble(cancelPoint) : 0D;
        this.totalPoint = totalPointT.toString();
    }

    //Getters and Setters
    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getStaffId() {
        return this.staffId;
    }

    public void setStaffId(final String staffId) {
        this.staffId = staffId;
    }

    public String getStaffCode() {
        return this.staffCode;
    }

    public void setStaffCode(final String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return this.staffName;
    }

    public void setStaffName(final String staffName) {
        this.staffName = staffName;
    }

    public String getNumberData() {
        return this.numberData;
    }

    public void setNumberData(final String numberData) {
        this.numberData = numberData;
    }

    public String getNumberCall() {
        return this.numberCall;
    }

    public void setNumberCall(final String numberCall) {
        this.numberCall = numberCall;
    }

    public String getW1Point() {
        return this.w1Point;
    }

    public void setW1Point(final String w1Point) {
        this.w1Point = w1Point;
    }

    public String getW2Point() {
        return this.w2Point;
    }

    public void setW2Point(final String w2Point) {
        this.w2Point = w2Point;
    }

    public String getW3Point() {
        return this.w3Point;
    }

    public void setW3Point(final String w3Point) {
        this.w3Point = w3Point;
    }

    public String getW4Point() {
        return this.w4Point;
    }

    public void setW4Point(final String w4Point) {
        this.w4Point = w4Point;
    }

    public String getW5Point() {
        return this.w5Point;
    }

    public void setW5Point(final String w5Point) {
        this.w5Point = w5Point;
    }

    public String getMonthPoint() {
        return this.monthPoint;
    }

    public void setMonthPoint(final String monthPoint) {
        this.monthPoint = monthPoint;
    }

    public String getCancelPoint() {
        return this.cancelPoint;
    }

    public void setCancelPoint(final String cancelPoint) {
        this.cancelPoint = cancelPoint;
    }

    public String getTotalPoint() {
        return this.totalPoint;
    }

    public void setTotalPoint(final String totalPoint) {
        this.totalPoint = totalPoint;
    }

}
