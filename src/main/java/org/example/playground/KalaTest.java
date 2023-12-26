package org.example.playground;

import kala.collection.immutable.ImmutableArray;
import kala.collection.immutable.ImmutableMap;
import kala.collection.mutable.*;
import kala.control.Either;

import java.util.function.Function;
import java.util.stream.Collectors;

public class KalaTest {

    public static void main(String[] args) {
        System.out.println("=====> Either");
        Either<String, Integer> either = Either.left("error msg");
        System.out.println(either.isLeft());
        System.out.println(either.isRight());
        System.out.println(either.toResult());

        System.out.println("\n=====> ImmutableArray");
        ImmutableArray<Integer> l = ImmutableArray.of(1, 2, 3, 4, 5);
        System.out.println(l.stream().reduce(Integer::sum));

        System.out.println("\n=====> MutableMap");
        MutableMap<String, Integer> lengthMap = new MutableHashMap<>();
        lengthMap.put("a", 1);
        lengthMap.put("b", 2);
        lengthMap.putIfAbsent("b", 3);
        System.out.println(lengthMap.size());

        System.out.println("\n=====> ImmutableMap");
        MutableList<String> stringMutableList = MutableList.of("hello", "world", "i", "wanna", "say");
        ImmutableMap<String, Integer> immutableMap = stringMutableList.stream()
                .collect(ImmutableMap.collector(Function.identity(), String::length));
        immutableMap.forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println("\n=====> MutableDeque");
        MutableDeque<String> deque = new MutableArrayDeque<>();
        deque.append("aaa");
        deque.append("bbb");
        deque.prepend("ccc");
        System.out.println(deque);

        System.out.println("\n=====> MutableList");
        MutableList<Integer> mutableList = MutableList.of(1, 2, 3);
        mutableList.append(4);
        mutableList.append(5);
        mutableList.append(6);
        System.out.println(mutableList.stream().map(String::valueOf).collect(Collectors.joining(", ")));
    }
}
