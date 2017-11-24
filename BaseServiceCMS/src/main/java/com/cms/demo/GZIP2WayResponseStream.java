/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.demo;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TruongBx3
 */
public class GZIP2WayResponseStream extends ServletOutputStream {

    private HttpServletResponse response = null;
    private ServletOutputStream outStream;
    private GZIPOutputStream out;

    public GZIP2WayResponseStream(HttpServletResponse response)
            throws IOException {
        this.response = response;
        this.outStream = response.getOutputStream();

        this.out = new GZIPOutputStream(this.outStream);
        response.addHeader("Content-Encoding", "gzip");
    }

    public void write(int b)
            throws IOException {
        this.out.write(b);
    }

    public void write(byte[] b)
            throws IOException {
        this.out.write(b);
    }

    public void write(byte[] b, int off, int len)
            throws IOException {
        this.out.write(b, off, len);
    }

    public void close()
            throws IOException {
        finish();
        this.out.close();
    }

    public void flush()
            throws IOException {
        this.out.flush();
    }

    public void finish()
            throws IOException {
        this.out.finish();
    }

    @Override
    public boolean isReady() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setWriteListener(WriteListener wl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
