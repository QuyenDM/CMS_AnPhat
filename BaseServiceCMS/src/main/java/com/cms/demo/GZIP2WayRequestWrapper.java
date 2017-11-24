/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.demo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 *
 * @author TruongBx3
 */
public class GZIP2WayRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest origRequest = null;
    private ServletInputStream inStream = null;
    private BufferedReader reader = null;

    public GZIP2WayRequestWrapper(HttpServletRequest req)
            throws IOException {
        super(req);

        this.inStream = new GZIP2WayRequestStream(req);
        this.reader = new BufferedReader(new InputStreamReader(this.inStream));
    }

    public ServletInputStream getInputStream()
            throws IOException {
        return this.inStream;
    }

    public BufferedReader getReader()
            throws IOException {
        return this.reader;
    }
}
