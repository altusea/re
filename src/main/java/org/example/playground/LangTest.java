package org.example.playground;

import org.apache.commons.lang3.function.Consumers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LangTest {

    public static void main(String[] args) {
        List<String> urls = new ArrayList<>();
        urls.add(null);
        urls.add(null);
        System.out.println(urls.size());

        Consumer<String> consumer = Consumers.nop();
        consumer.accept("hello");

        System.out.println(Integer.toBinaryString('A'));
        System.out.println(Integer.toBinaryString('a'));
    }
}
