package org.example.codewar;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class DirReduction {
    public static String[] dirReduce(String[] arr) {
        Map<String, String> map = Map.of("NORTH", "SOUTH", "SOUTH", "NORTH", "EAST", "WEST", "WEST", "EAST");
        Deque<String> stack = new ArrayDeque<>();
        for (String d : arr) {
            if (!stack.isEmpty() && map.get(d).equals(stack.getLast())) {
                stack.pollLast();
            } else {
                stack.offerLast(d);
            }
        }
        return stack.toArray(String[]::new);
    }
}
