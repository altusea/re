package org.example.codewar;

import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class Utility {

    public static IntStream generateFibonacciSequence() {
        return IntStream.iterate(1, new IntUnaryOperator() {
            private int lastFib = 0;

            @Override
            public int applyAsInt(int operand) {
                int ret = operand + lastFib;
                lastFib = operand;
                return ret;
            }
        });
    }

    public static int fib(long n) {
        return generateFibonacciSequence().limit(n).reduce((prev, last) -> last).getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(fib(10));
    }

}
