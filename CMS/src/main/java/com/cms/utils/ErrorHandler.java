/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.utils;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TiepNV6
 */
@WebServlet("/ErrorHandler")
public class ErrorHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    private void processError(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        String cause = (String) request.getAttribute("javax.servlet.error.message");
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request
                .getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request
                .getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>\n"
                + "    <head>\n"
                + "        <title>Error Page</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body >\n"
                + "        <div class=\"main\" align=\"left\">\n"
                + "       \n"
                + "            <div class=\"text\">\n"
                + "                <h1 style=\"padding-left:50px\">Error Code : " + statusCode + "</h1>\n"
                + "                <h3 style=\"padding-left:50px\"><b>Cause by : </b></h3>\n"
                + "                <h4 style=\"padding-left:100px\">" + cause + "</h4>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "\n"
                + "    </body>\n"
                + "</html>");

//        request.setAttribute("error", "Servlet " + servletName
//                + " has thrown an exception " + throwable.getClass().getName()
//                + " : " + throwable.getMessage());
//        response.sendRedirect("http://localhost:8079/CMS/VAADIN/themes/tests-valo/layouts/ErrorPage.jsp");
    }
}
