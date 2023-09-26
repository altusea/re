package org.example.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class MathematicsTest {

    @Test
    void givenValueWithinRange_whenClamp_thenReturnValue() {
        Assertions.assertEquals(20, Math.clamp(20, 17, 98));
        Assertions.assertEquals(17, Math.clamp(15, 17, 98));
        Assertions.assertEquals(98, Math.clamp(100, 17, 98));
    }

    @Test
    void divisions() {
        var five = Math.divideExact(10, 2);
        Assertions.assertEquals(five, 5);
    }

    @Test
    void multiplication() {
        var start = BigInteger.valueOf(10);
        var result = start.parallelMultiply(BigInteger.TWO);
        Assertions.assertEquals(BigInteger.valueOf(10 * 2), result);
    }
}
