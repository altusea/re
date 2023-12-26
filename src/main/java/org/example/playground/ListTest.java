package org.example.playground;

import kala.tuple.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListTest {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add(null);
        a.add(null);
        System.out.println(a.size());

        List<Tuple2<String, Integer>> pairList = new ArrayList<>();
        pairList.add(new Tuple2<>("one", 3));
        pairList.add(new Tuple2<>("three", 5));
        pairList.add(new Tuple2<>("one", 1));
        pairList.add(new Tuple2<>("ten", 10));
        System.out.println(pairList.stream().map(Tuple2::component1).toList());
        System.out.println(pairList.stream().map(Tuple2::component2).collect(Collectors.toUnmodifiableSet()));
    }
}
