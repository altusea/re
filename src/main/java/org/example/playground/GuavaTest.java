package org.example.playground;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

/**
 * test Guava
 */
public class GuavaTest {

    public static void main(String[] args) {
        var intList = Lists.newArrayList(1, 2, 3);
        intList.forEach(System.out::println);

        var map = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        map.forEach((k, v) -> System.out.printf("[%s] -> (%d)%n", k, v));
    }
}
