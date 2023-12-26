package org.example.collection;

import java.util.Collection;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Collections.singletonMap;

public interface YinYang {

    static boolean circle(int x, int y, int c, int r) {
        return
                (r * r) >= ((x = x / 2) * x) + ((y = y - c) * y);
    }

    static String pixel(int x, int y, int r) {
        return Stream.<Map<BooleanSupplier, Supplier<String>>>of(
                        singletonMap(
                                () -> circle(x, y, -r / 2, r / 6),
                                () -> "#"
                        ),
                        singletonMap(
                                () -> circle(x, y, r / 2, r / 6),
                                () -> "."
                        ),
                        singletonMap(
                                () -> circle(x, y, -r / 2, r / 2),
                                () -> "."
                        ),
                        singletonMap(
                                () -> circle(x, y, r / 2, r / 2),
                                () -> "#"
                        ),
                        singletonMap(
                                () -> circle(x, y, 0, r),
                                () -> x < 0 ? "." : "#"
                        )
                )
                .sequential()
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .filter(e -> e.getKey().getAsBoolean())
                .map(Map.Entry::getValue)
                .map(Supplier::get)
                .findAny()
                .orElse(" ")
                ;
    }

    static void yinYang(int r) {
        IntStream.rangeClosed(-r, r)
                .mapToObj(
                        y -> IntStream.rangeClosed(-2 * r, 2 * r)
                                .mapToObj(x -> pixel(x, y, r))
                                .reduce("", String::concat)
                )
                .forEach(System.out::println)
        ;
    }

    static void main(String... arguments) {
        yinYang(20);
    }
}
