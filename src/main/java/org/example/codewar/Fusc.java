package org.example.codewar;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Fusc {
    static final Map<BigInteger, BigInteger> cache = new HashMap<>() {{
        put(BigInteger.ZERO, BigInteger.ZERO);
        put(BigInteger.ONE, BigInteger.ONE);
    }};

    private static BigInteger impl(BigInteger n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        BigInteger quo = n.divide(BigInteger.TWO);
        BigInteger rem = n.remainder(BigInteger.TWO);
        BigInteger res;
        if (rem.equals(BigInteger.ZERO)) {
            res = impl(quo);
        } else {
            res = impl(quo).add(impl(quo.add(BigInteger.ONE)));
        }
        cache.put(n, res);
        return res;
    }

    public static BigInteger fusc(BigInteger n) {
        return (n.compareTo(BigInteger.TWO) < 0) ? n : impl(n);
    }
}
