package org.example.playground;

import io.vavr.collection.List;
import io.vavr.collection.Map;

import java.util.function.Function;

public class VavrTest {

    public static void main(String[] args) {
        String a = "a", b = "bb", c = "ccc";
        List<String> lst = List.of(a, b, c);
        Map<String, Integer> lengthMap = lst.toMap(Function.identity(), String::length);
        System.out.println(lengthMap);
    }
}
