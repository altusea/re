package org.example.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.math.IntMath;

/**
 * test Guava
 */
public class GuavaTest {

    public static void main(String[] args) {
        var map = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        map.forEach((k, v) -> System.out.printf("[%s] -> (%d)%n", k, v));

        var b = IntMath.checkedAdd(12, 15);
        System.out.println(b);
    }
}
