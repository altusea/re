package org.example.aoc23;

import kala.collection.immutable.ImmutableMap;
import kala.collection.mutable.MutableMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {

    public static void part1() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\xxx\\Downloads\\aoc2023\\day02.txt"));
        String str;
        int res = 0;

        ImmutableMap<String, Integer> all = ImmutableMap.of("red", 12, "green", 13, "blue", 14);

        while ((str = in.readLine()) != null) {
            boolean flag = false;
            String[] info = str.split(": ");
            int id = Integer.parseInt(info[0].split(" ")[1]);

            String[] sets = info[1].split("; ");
            for (String set : sets) {
                String[] balls = set.split(", ");
                for (String ball : balls) {
                    String[] item = ball.split(" ");
                    if (all.get(item[1]) < Integer.parseInt(item[0])) {
                        flag = true;
                    }
                    if (flag) {
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            if (!flag) {
                res += id;
            }
        }
        System.out.println("=====");
        System.out.println(res);
    }

    public static void part2() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\szt\\Downloads\\aoc2023\\day02.txt"));
        String str;
        int res = 0;

        ImmutableMap<String, Integer> all = ImmutableMap.of("red", 12, "green", 13, "blue", 14);

        while ((str = in.readLine()) != null) {
            MutableMap<String, Integer> map = MutableMap.of();

            String[] info = str.split(": ");

            String[] sets = info[1].split("; ");
            for (String set : sets) {
                String[] balls = set.split(", ");
                for (String ball : balls) {
                    String[] item = ball.split(" ");
                    map.put(item[1], Math.max(map.getOrDefault(item[1], 0), Integer.parseInt(item[0])));
                }
            }

            res += map.valuesView().stream().reduce(1, (a, b) -> a * b);

        }
        System.out.println("=====");
        System.out.println(res);
    }

    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }
}
