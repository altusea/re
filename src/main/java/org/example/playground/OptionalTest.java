package org.example.playground;

import java.util.Optional;

public class OptionalTest {

    record Point(int x, int y) {
    }

    public static void main(String[] args) {
        Point origin = new Point(0, 0);
        System.out.println(Optional.of(origin));
        System.out.println(Optional.ofNullable(null));
        System.out.println(Optional.empty());
    }
}
