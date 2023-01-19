package org.example.codewar;

import java.math.BigInteger;


// not done
public class SumFct {

    public static BigInteger perimeter(BigInteger n) {
        return fib(n).multiply(BigInteger.valueOf(4));
    }

    private static BigInteger fib(BigInteger n) {
        BigInteger a = BigInteger.valueOf(1), b = BigInteger.valueOf(1), temp;
        while (n.compareTo(BigInteger.ONE) > 0) {
            temp = a;
            a = b;
            b = temp.add(a);

            n = n.add(BigInteger.valueOf(-1));
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(SumFct.perimeter(BigInteger.valueOf(5)));
        System.out.println(SumFct.perimeter(BigInteger.valueOf(7)));
    }
}
