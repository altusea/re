package org.example.playground;

import org.example.util.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringTest {

    String onlyAscii = "hello, world";
    String hybrid = "我能吞下glass而不伤害body";

    @Test
    void testStringLength() {
        Assertions.assertEquals(12, onlyAscii.length());
        Assertions.assertEquals(17, hybrid.length());
    }

    @Test
    void testCodePoint() {
        int[] codePoints = StringUtil.getCodePoints(hybrid);
        Assertions.assertEquals(17, codePoints.length);
        Assertions.assertEquals("能", Character.toString(codePoints[1]));
    }
}
