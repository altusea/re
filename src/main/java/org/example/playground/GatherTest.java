package org.example.playground;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;

import static org.example.playground.CommonTest.printSeparateLine;

public class GatherTest {

    public static void main(String[] args) {
        var numbers = List.of(1, 2, 3, 4, 5);
        var slidingWindows = numbers.stream()
                .gather(Gatherers.windowSliding(3))
                .toList();
        System.out.println(slidingWindows);

        printSeparateLine();
        var streamList = List.of("123456", "foo", "bar", "baz", "quux", "anton", "egon", "banton");
        var result = streamList
                .stream()
                .gather(distinctBy(String::length))
                .toList();
        System.out.println(result);
    }

    static <T, A> Gatherer<T, ?, T> distinctBy(Function<? super T, ? extends A> classifier) {
        Supplier<Map<A, List<T>>> initializer = HashMap::new;
        //
        Gatherer.Integrator<Map<A, List<T>>, T, T> integrator = (state, element, downstream) -> {
            A apply = classifier.apply(element);
            state.computeIfAbsent(apply, (_) -> new ArrayList<>()).add(element);
            return true;
        };
        //
        BiConsumer<Map<A, List<T>>, Gatherer.Downstream<? super T>> finisher = (state, downstream) -> {
            state.forEach((_, value) -> downstream.push(value.getLast()));
        };
        //
        return Gatherer.ofSequential(initializer, integrator, finisher);
    }
}
