package org.example.playground;

import io.vavr.collection.Stream;
import org.example.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {

        Stream.of("a", "b", "c")
                .zipWithIndex()
                .take(2)
                .map(e -> e._2)
                .forEach(System.out::println);

        System.out.println("===================================");

        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        System.out.println("===================================");

        List<Pair<String, Integer>> pairList = new ArrayList<>();
        pairList.add(Pair.of("aaa", 1));
        pairList.add(Pair.of("bbb", null));
        pairList.add(Pair.of("ccc", 3));
        // will throw Exception
        Map<String, Integer> map = pairList.stream()
                .collect(Collectors.toMap(Pair::component1, Pair::component2));
        System.out.println(map);
    }
}
