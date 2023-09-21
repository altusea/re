package org.example.playground;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest {

    public static void main(String[] args) {

        List<String> a = List.of("Aaaaa", "Adddddd", "Ccc", "Aa", "Ddddddd");

        List<Predicate<String>> allPredicates = new ArrayList<>();
        allPredicates.add(str -> str.startsWith("A"));
        allPredicates.add(str -> str.contains("d"));
        allPredicates.add(str -> str.length() > 4);

        List<String> result = a.stream()
                .filter(allPredicates.stream().reduce(x -> true, Predicate::and))
                .toList();

        System.out.println(result);
    }
}
