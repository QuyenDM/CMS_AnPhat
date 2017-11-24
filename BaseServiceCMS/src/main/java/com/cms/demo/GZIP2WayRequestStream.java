/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.demo;

import java.io.IOException;
import java.util.zip.GZIPInputStream;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author TruongBx3
 */
public class GZIP2WayRequestStream extends ServletInputStream {

    private HttpServletRequest request = null;
    private ServletInputStream inStream = null;
    private GZIPInputStream in = null;

    public GZIP2WayRequestStream(HttpServletRequest request)
            throws IOException {
        this.request = request;
        this.inStream = request.getInputStream();
        this.in = new GZIPInputStream(this.inStream);
    }

    public int read()
            throws IOException {
        return this.in.read();
    }

    public int read(byte[] b)
            throws IOException {
        return this.in.read(b);
    }

    public int read(byte[] b, int off, int len)
            throws IOException {
        return this.in.read(b, off, len);
    }

    public void close()
            throws IOException {
        this.in.close();
    }

    @Override
    public boolean isFinished() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isReady() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setReadListener(ReadListener rl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
