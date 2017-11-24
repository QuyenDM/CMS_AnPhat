/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author doanLV4
 */
public class ValidateInput {

    //so tu nhien >=0, so sanh voi sau rong ra false (dung khi bat buoc nhap)
    public static final String regexPhoneNum = "^\\+[0-9]\\[.;, ]\\[0-9]$";
    public static final String regexPhoneNum1 = "(\\d-)?(\\d{1,20}-)?\\d{1,20}-\\d{1,20}";
    
    //so tu nhien >=0, so sanh voi sau rong ra false (dung khi bat buoc nhap)
    public static final String regexDigitReq = "\\d+";

    //so tu nhien >=0, so sanh voi sau rong ra true (dung khi khong bat buoc nhap)
    public static final String regexDigit = "\\d*";

    //so nguyen duong, so sanh voi sau rong ra false (dung khi bat buoc nhap)
    public static final String regexPosDigitReq = "[0-9]+";

    //so nguyen duong, so sanh voi sau rong ra true (dung khi khong bat buoc nhap)
    public static final String regexPosDigit = "[0-9]*";

    //so thuc >=0, so sanh voi sau rong ra false (khong cho phep co khoang trang, truoc so match phai trim)
    public static final String regexDobPos = "\\d+.\\d*";

    //so thuc (khong cho phep co khoang trang, truoc so match phai trim)
    public static final String regexDob = "\\s-?(\\d)+(\\.)?(\\d)*";

    //kieu kien DxRxC hoac D x R x C
    public static final String regexPacking = "(\\d)+(\\.)?(\\d)*(\\s*[xX]\\s*)(\\d)+(\\.)?(\\d)*(\\s*[xX]\\s*)(\\d)+(\\.)?(\\d)*";

    public static boolean checkInput(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static void main(String[] args) {
        boolean b = checkInput("12341213", regexPhoneNum);
        System.out.println("" + b);
    }
    
}
