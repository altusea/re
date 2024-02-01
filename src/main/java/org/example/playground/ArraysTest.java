package org.example.playground;

import java.util.Arrays;

public class ArraysTest {

    public static void main(String[] args) {
        int[] t = new int[]{1, 2, 4, 5};
        int x = Arrays.binarySearch(t, 3);

        assert ~x == 2;
    }
}
