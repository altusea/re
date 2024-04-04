package org.example.playground;

import kala.collection.mutable.MutableMap;
import org.apache.commons.lang3.mutable.MutableInt;

import static org.example.playground.CommonTest.printSeparateLine;

public class MutableTest {

    public static void main(String[] args) {
        MutableInt mutableInt = new MutableInt(100);
        mutableInt.increment();
        System.out.println(mutableInt.getAndAdd(5)); // should be 101
        System.out.println(mutableInt.getValue()); // should be 106

        printSeparateLine();
        MutableMap<String, Integer> mutableMap = MutableMap.of();
        mutableMap.put("a", 1);
        mutableMap.put("b", 2);
        mutableMap.put("c", 3);
        System.out.println(mutableMap.getOption("a"));
        System.out.println(mutableMap.getOrDefault("b", 1));
        System.out.println(mutableMap.getOrElse("c", () -> 100));
    }
}
