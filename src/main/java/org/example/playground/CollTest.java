package org.example.playground;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.SequencedMap;
import java.util.SequencedSet;

import static org.example.util.ConsoleUtil.printSeparateLine;

public class CollTest {

    public static void main(String[] args) {
        printSeparateLine("test SequencedMap:");
        SequencedMap<String, Integer> map = new LinkedHashMap<>();
        map.putLast("A", 1);
        map.putLast("B", 2);
        map.putLast("C", 3);
        map.putLast("A", 1);
        map.forEach((key, value) -> System.out.println(key + " : " + value));

        printSeparateLine("test SequencedSet:");
        SequencedSet<String> set = new LinkedHashSet<>();
        set.addLast("A");
        set.addLast("B");
        set.addLast("C");
        set.addLast("A");
        set.forEach(System.out::println);
    }
}
