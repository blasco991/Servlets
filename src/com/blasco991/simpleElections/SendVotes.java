package com.blasco991.simpleElections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

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
            List<String> partiesList = Arrays.asList(req.getParameter(parties).split(","));

            int max = Integer.parseInt(req.getParameter(howMany));
            List<String> votes = new ArrayList<>(max);
            Iterator<String> partyIterator = partiesList.iterator();

            for (int j, i = 0; i < max; i += j) {
                j = ThreadLocalRandom.current().nextInt(max);
                String party = partyIterator.next();
                IntStream.range(0, j).forEach(k -> votes.add(party));
            }
            resp.setContentType("text/html");
            resp.getWriter().print(votes.toString());
        }

    }
}
