package org.example.codewar;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    public static String whoLikesIt(String... names) {

        int len = names.length;

        if (len == 0) {
            return "no one likes this";
        } else if (len == 1) {
            return String.format("%s likes this", names[0]);
        } else if (len == 2) {
            return String.format("%s and %s like this", names[0], names[1]);
        } else if (len == 3) {
            return String.format("%s, %s and %s like this", names[0], names[1], names[2]);
        } else {
            return String.format("%s, %s and %d others like this", names[0], names[1], len - 2);
        }
    }

    public static boolean validParentheses(String parens) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : parens.toCharArray()) {
            if (c == '(') {
                stack.offerLast(c);
            } else if (c == ')') {
                if (stack.size() == 0 || stack.peekLast() != '(') {
                    return false;
                } else {
                    stack.pollLast();
                }
            }
        }
        return stack.size() == 0;
    }
}
