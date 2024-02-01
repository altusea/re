package org.example.playground;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FunctionTest {

    public static void main(String[] args) {
        Consumer<List<String>> consumer = e -> e.addLast("tail");
        List<String> stringList = new ArrayList<>();
        stringList.addFirst("head");
        consumer.accept(stringList);
        System.out.println(stringList);
    }
}
