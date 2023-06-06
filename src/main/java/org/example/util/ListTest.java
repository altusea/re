package org.example.util;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add(null);
        a.add(null);
        System.out.println(a.size());

        System.out.println("=========================");

        var intList = Lists.newArrayList(1, 2, 3);
        intList.forEach(System.out::println);
    }
}
