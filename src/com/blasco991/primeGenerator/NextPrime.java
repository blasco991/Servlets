package com.blasco991.primeGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by blasco991 on 21/03/17.
 */

@WebServlet("/NextPrime")
public class NextPrime extends javax.servlet.http.HttpServlet {

    private static List<Long> primes = Collections.synchronizedList(new LinkedList<>());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long num = getNextPrime();
        response.getOutputStream().println(num);
    }

    public long getNextPrime() {
        long num = !primes.isEmpty() ? primes.get(primes.size() - 1) + 1 : 2;
        while (!isPrime(num))
            num++;
        primes.add(num);
        return num;
    }

    public List<Long> getPrimes(int howMany) {
        if (primes.size() > howMany)
            return primes.subList(0, howMany - 1);
        else {
            IntStream.range(0, howMany - primes.size()).forEach(i -> getNextPrime());
            return primes;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Checks to see if the requested value is prime.
     */
    public static boolean isPrime(final long number) {
        return number != 0 &&
                LongStream.range(2, (long) Math.ceil(Math.sqrt(number + 1)))
                        .noneMatch(divisor -> number % divisor == 0);
    }

}