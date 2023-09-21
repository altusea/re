package org.example.codewar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeFormatter {

    public record Pair(String single, String duplicate) {
    }

    public static Map<Integer, Pair> UNIT_MAP = new HashMap<>() {
        {
            put(0, new Pair("year", "years"));
            put(1, new Pair("day", "days"));
            put(2, new Pair("hour", "hours"));
            put(3, new Pair("minute", "minutes"));
            put(4, new Pair("second", "seconds"));
        }
    };

    // https://www.codewars.com/kata/52742f58faf5485cae000b9a/train/java
    public static String formatDuration(int seconds) {
        if (seconds == 0) return "now";

        int[] arr = new int[5]; // y/d/h/m/s

        int secsPerMin = 60;
        int secsPerHour = secsPerMin * 60;
        int secsPerDay = secsPerHour * 24;
        int secsPerYear = secsPerDay * 365;

        arr[0] = seconds / secsPerYear;
        seconds -= arr[0] * secsPerYear;

        arr[1] = seconds / secsPerDay;
        seconds -= arr[1] * secsPerDay;

        arr[2] = seconds / secsPerHour;
        seconds -= arr[2] * secsPerHour;

        arr[3] = seconds / secsPerMin;
        seconds -= arr[3] * secsPerMin;

        arr[4] = seconds;

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int val = arr[i];
            if (val > 0) {
                strings.add(arr[i] + " " + (val > 1 ? UNIT_MAP.get(i).duplicate : UNIT_MAP.get(i).single()));
            }
        }

        if (strings.size() == 1) {
            return strings.get(0);
        } else if (strings.size() == 2) {
            return strings.get(0) + " and " + strings.get(1);
        } else if (strings.size() == 3) {
            return strings.get(0) + ", " + strings.get(1) + " and " + strings.get(2);
        } else if (strings.size() == 4) {
            return strings.get(0) + ", " + strings.get(1) + ", " + strings.get(2) + " and " + strings.get(3);
        } else {
            return strings.get(0) + ", " + strings.get(1) + ", " + strings.get(2) + ", " + strings.get(3) + " and " + strings.get(4);
        }
    }
}
