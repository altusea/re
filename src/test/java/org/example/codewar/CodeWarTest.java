package org.example.codewar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class CodeWarTest {

    @Test
    void testConvertToBase36() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Base36.convertToBase36(-1));
        Assertions.assertEquals("0", Base36.convertToBase36(0));
        Assertions.assertEquals("10", Base36.convertToBase36(36));
        Assertions.assertEquals("11", Base36.convertToBase36(37));
        Assertions.assertEquals("20", Base36.convertToBase36(72));
    }

    @Test
    void testFusc() {
        BigInteger twoPThous = BigInteger.valueOf(2).pow(1000);
        Assertions.assertEquals(BigInteger.valueOf(1001), Fusc.fusc(twoPThous.add(BigInteger.ONE)));
        Assertions.assertEquals(BigInteger.valueOf(1000), Fusc.fusc(twoPThous.subtract(BigInteger.ONE)));
        Assertions.assertEquals(BigInteger.valueOf(2996), Fusc.fusc(twoPThous.add(BigInteger.valueOf(5))));
        Assertions.assertEquals(BigInteger.valueOf(7973), Fusc.fusc(twoPThous.add(BigInteger.valueOf(21))));
        Assertions.assertEquals(BigInteger.valueOf(50245), Fusc.fusc(twoPThous.add(BigInteger.valueOf(9007199254740991L))));
    }
}
