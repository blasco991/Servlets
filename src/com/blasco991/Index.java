package com.blasco991;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * Created by blasco991 on 04/04/17.
 */

@WebServlet("/")
public class Index extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        ServletContext servletContext = getServletContext();
        Map<String, ? extends ServletRegistration> mappings = servletContext.getServletRegistrations();
        for (Map.Entry<String, ? extends ServletRegistration> servletRegistrationEntry : mappings.entrySet())
            resp.getOutputStream().println(servletRegistrationEntry.toString());
    }
}
