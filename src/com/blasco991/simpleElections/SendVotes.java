package com.blasco991.simpleElections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by blasco991 on 04/04/17.
 * eg:  sendvotes?howmany=XXX&parties=P1,P2,...,Pn
 */
@WebServlet("/SendVotes")
public class SendVotes extends HttpServlet {

    private static final String howMany = "howmany", parties = "parties";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey(howMany) && req.getParameterMap().containsKey(parties)) {
            String[] partiesList = req.getParameter(parties).split(",");
            int max = Integer.parseInt(req.getParameter(howMany));
            String[] votes = new String[max];

            for(int i=0; i<max; i++){
                int random = ThreadLocalRandom.current().nextInt(partiesList.length);
                votes[i] = partiesList[random];
            }

            resp.setContentType("text/html");
            resp.getWriter().print(Arrays.toString(votes));
        }

    }
}
