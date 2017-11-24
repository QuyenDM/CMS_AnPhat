/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TruongBx3
 */
public class GZIP2WayFilter implements Filter {

    public void init(FilterConfig arg0)
            throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if ((request instanceof HttpServletRequest)) {
            HttpServletRequest req = (HttpServletRequest) request;
            String contentEncoding = req.getHeader("Content-Encoding");
            if ((contentEncoding != null)
                    && (contentEncoding.toLowerCase().indexOf("gzip") > -1)) {
                request = new GZIP2WayRequestWrapper(
                        (HttpServletRequest) request);
            }
        }
        if ((response instanceof HttpServletResponse)) {
            HttpServletRequest req = (HttpServletRequest) request;
            String acceptEncoding = req.getHeader("Accept-Encoding");
            if ((acceptEncoding != null)
                    && (acceptEncoding.toLowerCase().indexOf("gzip") > -1)) {
                response = new GZIP2WayResponseWrapper(
                        (HttpServletResponse) response);
            }
        }
        chain.doFilter(request, response);
        if ((response instanceof GZIP2WayResponseWrapper)) {
            ((GZIP2WayResponseStream) response.getOutputStream()).finish();
        }
    }

    public void destroy() {
    }
}
