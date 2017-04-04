package com.blasco991.primeGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/NextPrimes")
public class NextPrimes extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int k = Integer.parseInt(request.getParameter("howMany"));
        final NextPrime primeGenerator = new NextPrime();

        response.getOutputStream().print(
                new java.util.Formatter(new StringBuilder()).format("<h1>%s</1>", primeGenerator.getPrimes(k)).toString()
        );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}