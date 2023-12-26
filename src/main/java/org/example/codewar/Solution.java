package org.example.codewar;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    public static int solveSuperMarketQueue(int[] customers, int n) {
        int[] result = new int[n];
        for (int customer : customers) {
            result[0] += customer;
            Arrays.sort(result);
        }
        return result[n - 1];
    }

    public static String camelCase(String input) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isUpperCase(c)) {
                res.add(sb.toString());
                sb = new StringBuilder();
                sb.append(c);
            } else {
                sb.append(c);
            }
        }
        res.add(sb.toString());
        return String.join(" ", res);
    }

    public static int[] sumParts(int[] ls) {
        int n = ls.length;
        int[] res = new int[n + 1];
        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += ls[i];
            res[i] = sum;
        }
        return res;
    }

    public static int century(int number) {
        return (number + 99) / 100;
    }

    public String dnaToRna(String dna) {
        return dna.replace('T', 'U');
    }

    public static List<String> number(List<String> lines) {
        return lines.stream().map(new Function<String, String>() {

            int counter = 0;

            @Override
            public String apply(String s) {
                counter++;
                return counter + ": " + s;
            }
        }).toList();
    }

    public static boolean validatePin(String pin) {
        return pin.matches("\\d{4}|\\d{6}");
    }

    public static boolean scramble(String str1, String str2) {
        int[] count = new int[26];
        for (char c : str1.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : str2.toCharArray()) {
            if (count[c - 'a'] == 0) return false;
            count[c - 'a']--;
        }
        return true;
    }

    public static int sumPositiveNumbers(int[] arr) {
        return Arrays.stream(arr).filter(i -> i > 0).sum();
    }

    public static int nbYear(int p0, double percent, int aug, int p) {
        int acc = p0;
        int count = 0;
        while (acc < p) {
            acc = acc + (int) (acc * percent / 100) + aug;
            count++;
        }
        return count;
    }

    public static int findSmallestInt(int[] args) {
        return Arrays.stream(args).min().getAsInt();
    }

    public static String repeatStr(final int repeat, final String string) {
        return IntStream.rangeClosed(1, repeat).mapToObj(i -> string).collect(Collectors.joining());
    }

    public static int sortDesc(final int num) {
        ArrayList<Integer> digits = new ArrayList<>();
        int n = num;
        while (n > 0) {
            digits.add(n % 10);
            n = n / 10;
        }
        digits.sort(Comparator.reverseOrder());
        return digits.stream().reduce(0, (a, b) -> a * 10 + b);
    }

    public static boolean feast(String beast, String dish) {
        int n = beast.length(), m = dish.length();
        return beast.charAt(0) == dish.charAt(0) && beast.charAt(n - 1) == dish.charAt(m - 1);
    }

    public static String[] stringSplit(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            s = s + "_";
            n = n + 1;
        }
        n = n / 2;
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            res[i] = s.substring(i * 2, i * 2 + 2);
        }
        return res;
    }

    public static int sum(List<?> mixed) {
        // elements in mixed coule be String("42") or Integer(12)
        return mixed.stream().mapToInt(o -> Integer.parseInt(o.toString())).sum();
    }

    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0; i < numbers.length; ++i) {
            if (hashtable.containsKey(target - numbers[i])) {
                return new int[]{hashtable.get(target - numbers[i]), i};
            }
            hashtable.put(numbers[i], i);
        }
        return new int[0];
    }

    static class Node<T> {
        public T data;
        public Node<T> next;

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        Node(T data) {
            this(data, null);
        }
    }

    static <T> Node<T> filter(Node<T> head, Predicate<T> p) {
        if (head == null) return null;
        if (p.test(head.data)) {
            head.next = filter(head.next, p);
            return head;
        }
        return filter(head.next, p);
    }

    static <T> boolean anyMatch(Node<T> head, Predicate<T> p) {
        if (head == null) return false;
        if (p.test(head.data)) return true;
        return anyMatch(head.next, p);
    }

    static <T> boolean allMatch(Node<T> head, Predicate<T> p) {
        if (head == null) return true;
        return p.test(head.data) && allMatch(head.next, p);
    }

    static <T> T reduce(Node<T> head, BiFunction<T, T, T> f, T init) {
        if (head == null) return init;
        T acc = f.apply(init, head.data);
        return reduce(head.next, f, acc);
    }

    static <T, R> Node<R> map(Node<T> head, Function<T, R> f) {
        if (head == null) return null;
        Node<R> res = new Node<>(f.apply(head.data));
        res.next = map(head.next, f);
        return res;
    }

    static int length(Node head) {
        return head == null ? 0 : (1 + length(head.next));
    }

    static <T> int countIf(Node<T> head, Predicate<T> p) {
        return head == null ? 0 : (p.test(head.data) ? 1 : 0) + countIf(head.next, p);
    }

    public static String rot13(String message) {
        StringBuilder sb = new StringBuilder();
        for (char c : message.toCharArray()) {
            sb.append(rot13(c));
        }
        return sb.toString();
    }

    private static char rot13(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char) (((ch - 'A') + 13) % 26 + 'A');
        }
        if (ch >= 'a' && ch <= 'z') {
            return (char) (((ch - 'a') + 13) % 26 + 'a');
        }
        return ch;
    }

    public static boolean validParentheses(String parens) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : parens.toCharArray()) {
            if (c == '(') {
                stack.offerLast(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peekLast() != '(') {
                    return false;
                } else {
                    stack.pollLast();
                }
            }
        }
        return stack.isEmpty();
    }
}
