package org.example.playground;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalTest {

    record Point(int x, int y) {
    }

    public static void main(String[] args) {
        Point origin = new Point(0, 0);
        System.out.println(Optional.of(origin));
        System.out.println(Optional.empty());

        System.out.println("\n========== test Optional ==========");
        List<Optional<String>> optionals = new ArrayList<>();
        optionals.add(Optional.of("hello"));
        optionals.add(Optional.of("john"));
        optionals.add(Optional.empty());
        optionals.add(Optional.of("world"));

        optionals.stream().map(e -> e.orElse("default")).forEach(System.out::println);
    }
}
