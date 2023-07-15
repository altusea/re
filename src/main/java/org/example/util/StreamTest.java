package org.example.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        List<Pair<String, Integer>> pairList = new ArrayList<>();
        pairList.add(Pair.of("aaa", 1));
        pairList.add(Pair.of("bbb", null));
        pairList.add(Pair.of("ccc", 3));
        // will throw Exception
        Map<String, Integer> map = pairList.stream()
                .collect(Collectors.toMap(Pair::getComponent1, Pair::getComponent2));
        System.out.println(map);
    }
}
