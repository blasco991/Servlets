package com.blasco991.simpleElections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by blasco991 on 04/04/17.
 * eg:  sendvotes?howmany=XXX&parties=P1,P2,...,Pn
 */
@WebServlet("/SendVotes")
public class SendVotes extends HttpServlet {

    private final Random random = new Random();
    private static final String howMany = "howmany", parties = "parties";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey(howMany) && req.getParameterMap().containsKey(parties)) {
            String[] partiesList = req.getParameter(parties).split(",");
            int max = Integer.parseInt(req.getParameter(howMany));
            String[] votes = new String[max];

            List<String> list = Arrays.asList(partiesList);
            Collections.shuffle(list);
            partiesList = list.toArray(partiesList);

            int std = partiesList.length > 4 ? partiesList.length / 4 : partiesList.length / 2;
            for (int i = 0; i < max; i++) {
                int random = gaussianInt(partiesList.length / 2, std, partiesList.length - 1);
                votes[i] = partiesList[random];
            }

            resp.setContentType("application/json");
            resp.getWriter().print(Arrays.toString(votes));
        }
    }

    private int gaussianInt(int avg, int std, int max) {
        int random = (int) Math.abs(this.random.nextGaussian() * std + avg);
        return random > max ? max : random;
    }
}
