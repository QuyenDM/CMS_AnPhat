/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.demo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author TruongBx3
 */
public class GZIP2WayResponseWrapper extends HttpServletResponseWrapper {

    private HttpServletResponse response = null;
    private GZIP2WayResponseStream outStream = null;
    private PrintWriter writer = null;

    public GZIP2WayResponseWrapper(HttpServletResponse response) {
        super(response);

        this.response = response;
    }

    public ServletOutputStream getOutputStream()
            throws IOException {
        if (this.outStream == null) {
            this.outStream = new GZIP2WayResponseStream(this.response);
        }
        return this.outStream;
    }

    public PrintWriter getWriter()
            throws IOException {
        if (this.writer == null) {
            this.writer = new PrintWriter(getOutputStream());
        }
        return this.writer;
    }
}
