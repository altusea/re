package org.example.playground;

import org.apache.commons.lang3.function.Consumers;

import java.util.function.Consumer;

public class CommonsTest {

    public static void main(String[] args) {
        Consumer<String> consumer = Consumers.nop();
        consumer.accept("hello");
    }
}
