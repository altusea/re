package org.example.util;

import kala.collection.mutable.MutableArrayDeque;
import kala.collection.mutable.MutableDeque;
import kala.collection.mutable.MutableHashMap;
import kala.collection.mutable.MutableMap;

public class KalaTest {

    public static void main(String[] args) {
        MutableMap<String, Integer> lengthMap = new MutableHashMap<>();
        lengthMap.put("a", 1);
        lengthMap.put("b", 2);
        lengthMap.putIfAbsent("b", 3);
        System.out.println(lengthMap.size());

        MutableDeque<String> deque = new MutableArrayDeque<>();
        deque.append("aaa");
        deque.append("bbb");
        deque.prepend("ccc");
        System.out.println(deque);
    }
}
