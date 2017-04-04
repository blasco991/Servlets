package com.blasco991.helloWorld;

import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Created by blasco991 on 03/04/17.
 */

@WebServlet("/HelloWorld")
public class HelloWorld extends HttpServlet {

    private static final String message = "Hello World";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)  {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1>" + message + "</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}