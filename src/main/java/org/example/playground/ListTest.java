package org.example.playground;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListTest {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add(null);
        a.add(null);
        System.out.println(a.size());

        List<Pair<String, Integer>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("one", 3));
        pairList.add(new Pair<>("three", 5));
        pairList.add(new Pair<>("one", 1));
        pairList.add(new Pair<>("ten", 10));
        System.out.println(pairList.stream().map(Pair::right).toList());
        System.out.println(pairList.stream().map(Pair::left).collect(Collectors.toUnmodifiableSet()));
    }
}
