package org.example.playground;

import com.google.common.collect.*;

import java.util.List;
import java.util.Map;

/**
 * test Guava
 */
public class GuavaTest {

    public static void main(String[] args) {
        var intList = Lists.newArrayList(1, 2, 3);
        intList.forEach(System.out::println);

        var strList = Lists.newArrayList("a", "b", "c");

        var cartesianProdRes = Lists.cartesianProduct(List.of(intList, strList));
        System.out.println(cartesianProdRes);

        var map = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        map.forEach((k, v) -> System.out.printf("[%s] -> (%d)%n", k, v));

        List<Pair<String, Integer>> pairList = Lists.newArrayList();
        pairList.add(new Pair<>("one", 3));
        pairList.add(new Pair<>("three", 5));

        List<String> immutableStrList = pairList.stream().map(Pair::left).collect(ImmutableList.toImmutableList());
        try {
            immutableStrList.add("seven");
        } catch (UnsupportedOperationException e) {
            System.out.println("unsupported operation [list.add()] ...");
        }

        Map<String, Integer> immutableMap = pairList.stream().collect(ImmutableMap.toImmutableMap(Pair::left, Pair::right));
        try {
            immutableMap.put("ten", 3);
        } catch (UnsupportedOperationException e) {
            System.out.println("unsupported operation [map.put()] ...");
        }

        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        pairList.add(new Pair<>("one", 1));
        pairList.add(new Pair<>("ten", 10));
        for (Pair<String, Integer> pair : pairList) {
            multimap.put(pair.left(), pair.right());
        }
        System.out.println(multimap);
    }
}
