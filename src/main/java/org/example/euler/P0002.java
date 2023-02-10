package org.example.euler;

import org.example.collection.PrimeGenerator;

public class P0002 {

    public static void main(String[] args) {
        System.out.printf("The 1st prime is %,d.\n", nthPrime(1));
        System.out.printf("The 10,001st prime is %,d.\n", nthPrime(10001));
    }

    private static int nthPrime(int n) {
        assert n > 0;
        PrimeGenerator primeGen = new PrimeGenerator(10000, 100000);
        int prime = primeGen.nextPrime();
        while (--n > 0)
            prime = primeGen.nextPrime();
        return prime;
    }
}
