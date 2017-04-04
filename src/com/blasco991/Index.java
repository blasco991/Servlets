package com.blasco991;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        final PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");
        Map<String, ? extends ServletRegistration> mappings = getServletContext().getServletRegistrations();
        mappings.forEach((key, value) -> {
            writer.print(formatter.format("<h3>%s</h3>", value.getName()));
            for (String mapping : value.getMappings())
                writer.println(formatter.format("<a href=\"%s\">%s</a>", mapping, mapping));
        });
    }

}
