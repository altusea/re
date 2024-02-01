package org.example.playground;

import kala.control.Try;
import org.apache.commons.collections4.ClosureUtils;
import org.apache.commons.lang3.NotImplementedException;

public class StrangeThings {

    public static void main(String[] args) {
        ClosureUtils.forClosure(3, System.out::println).execute("hello");

        Try<String> stringTry = Try.of(() -> {
            throw new NotImplementedException("todo");
        });
        System.out.println(stringTry.getOrThrow());


    }
}
