package org.example.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GCD {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("\\s");
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        System.out.println(gcd(a, b));

    }

    // GCD
    public static int gcd(int a, int b) {
        int min = Math.min(a, b), max = a + b - min, div = min;
        for (int i = 1; i < min; div = min / ++i)
            if (min % div == 0 && max % div == 0)
                return div;
        return 1;
    }
}
