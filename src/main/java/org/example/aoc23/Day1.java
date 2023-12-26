package org.example.aoc23;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Day1 {

    public static void part1(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\xxx\\Downloads\\aoc2023\\day01.txt"));
        String str;
        int res = 0;

        while ((str = in.readLine()) != null) {
            String digits = getFirstAndLastDigit(getAllDigits(str));
            if (StringUtils.isNotBlank(digits)) {
                res += Integer.parseInt(getFirstAndLastDigit(digits));
            }
        }
        System.out.println("=====");
        System.out.println(res);
    }

    private static String getAllDigits(final String input) {
        return input.replaceAll("[a-z]", "");
    }

    private static String getFirstAndLastDigit(final String input) {
        return input.charAt(0) + "" + input.charAt(input.length() - 1);
    }

    private static final Map<String, String> digitMap = Map.of(
            "one", "o1e",
            "two", "t2o",
            "three", "t3e",
            "four", "f4r",
            "five", "f5e",
            "six", "s6x",
            "seven", "s7n",
            "eight", "e8t",
            "nine", "n9e"
    );

    private static String replaceWrittenDigits(final String input) {
        String res = input;
        for (Map.Entry<String, String> entry : digitMap.entrySet()) {
            res = res.replaceAll(entry.getKey(), entry.getValue());
        }
        return res;
    }

    public static void part2(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\szt\\Downloads\\aoc2023\\day01.txt"));
        String str;
        int res = 0;
        while ((str = in.readLine()) != null) {
            String digits = getFirstAndLastDigit(getAllDigits(replaceWrittenDigits(str)));
            if (StringUtils.isNotBlank(digits)) {
                res += Integer.parseInt(getFirstAndLastDigit(digits));
            }
        }
        System.out.println("=====");
        System.out.println(res);
    }

    public static void main(String[] args) throws IOException {
        part1(args);
        part2(args);
    }
}
