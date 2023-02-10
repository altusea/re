package org.example.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonacci {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        long n = Long.parseLong(input);
        System.out.println(fib(n));
    }

    /**
     * O(log(n))
     */
    public static long fib(long n) {
        if (n <= 0)
            return 0;

        long i = (int) (n - 1);
        long a = 1, b = 0, c = 0, d = 1, tmp1, tmp2;

        while (i > 0) {
            if (i % 2 != 0) {
                tmp1 = d * b + c * a;
                tmp2 = d * (b + a) + c * b;
                a = tmp1;
                b = tmp2;
            }

            tmp1 = (long) (Math.pow(c, 2) + Math.pow(d, 2));
            tmp2 = d * (2 * c + d);

            c = tmp1;
            d = tmp2;

            i = i / 2;
        }
        return a + b;
    }
}
