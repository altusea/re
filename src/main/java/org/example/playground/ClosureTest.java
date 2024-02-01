package org.example.playground;

import org.apache.commons.collections4.ClosureUtils;

public class ClosureTest {

    public static void main(String[] args) {
        ClosureUtils.forClosure(3, System.out::println).execute("hello");
    }
}
