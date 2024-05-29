package org.example.playground;

import org.apache.commons.lang3.function.Consumers;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.function.Consumer;

import static org.example.util.ConsoleUtil.printSeparateLine;

public class CommonsLangTest {

    public static void main(String[] args) {
        MutableInt mutableInt = new MutableInt(100);
        mutableInt.increment();
        System.out.println(mutableInt.getAndAdd(5)); // should be 101
        System.out.println(mutableInt.getValue()); // should be 106

        printSeparateLine();
        Consumer<String> consumer = Consumers.nop();
        consumer.accept("hello");
    }
}
