package com.blasco991.simpleElections;

import com.sun.istack.internal.NotNull;

import javax.naming.Name;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by blasco991 on 04/04/17.
 * eg:  sendvotes?howmany=XXX&parties=P1,P2,...,Pn
 */
@WebServlet("/SendVotes")
public class SendVotes extends HttpServlet {

    private final Random random = new Random();
    private final NameGenerator nameGenerator = new NameGenerator();
    private static final String howMany = "howmany", parties = "parties";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int max;
        final List<String> list;
        resp.setContentType("application/json");

        max = req.getParameterMap().containsKey(howMany) ?
                Integer.parseInt(req.getParameter(howMany)) :
                ThreadLocalRandom.current().nextInt(10, 200);

        list = req.getParameterMap().containsKey(parties) ?
                Arrays.asList(req.getParameter(parties).split(",")) :
                IntStream.range(4, ThreadLocalRandom.current().nextInt(max/4))
                        .mapToObj(i -> nameGenerator.getName()).collect(Collectors.toList());

        List<String> votes = generateVotes(max, list);
        resp.getWriter().print(Arrays.toString(votes.toArray(new String[votes.size()])));
    }

    private int gaussianInt(int avg, int std, int max) {
        int random = (int) Math.abs(this.random.nextGaussian() * std + avg);
        return random > max ? max : random;
    }

    private List<String> generateVotes(int howmany, List<String> partiesList) {
        List<String> votes = new ArrayList<>(howmany);

        Collections.shuffle(partiesList);

        int std = partiesList.size() > 4 ? partiesList.size() / 4 : partiesList.size() / 2;
        for (int i = 0; i < howmany; i++) {
            int random = gaussianInt(partiesList.size() / 2, std, partiesList.size() - 1);
            votes.add(partiesList.get(random));
        }
        return votes;
    }
}
