package org.example.codewar;

import java.util.Arrays;
import java.util.Map;

public class Printer {

    public static String printerError(String s) {
        return s.replaceAll("[a-m]", "").length() + "/" + s.length();
    }

    public static void print(int[] a, int[] b) {
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }

    public static <K, V> void print(Map<K, V> map1, Map<K, V> map2) {
        System.out.println(map1);
        System.out.println(map2);
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 4};
        int[] b = {5, 6, 7, 8, 9};
        print(a, b);
    }
}
