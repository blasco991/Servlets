package com.blasco991;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Formatter;

/**
 * Created by blasco991 on 04/04/17.
 */

@WebServlet("/")
public class Index extends HttpServlet {

    private final Formatter formatter = new Formatter();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Map<String, ? extends ServletRegistration> mappings = getServletContext().getServletRegistrations();
        mappings.forEach((key, value) -> {
            try {
                resp.getWriter().print("<h3>" + value.getName() + "</h3>");
                for (String mapping : value.getMappings())
                    resp.getWriter().println("<a href=\"" + mapping + "\">" + mapping + "</a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
