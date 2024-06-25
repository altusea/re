package org.example.util;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class ConsoleUtil {

    private static final String SEPARATE_LINE = "=".repeat(80);

    private static final String SHORTER_SEPARATE_LINE = "=".repeat(20);

    public static void printLine() {
        System.out.println();
    }

    public static void printSeparateLine() {
        System.out.println(SEPARATE_LINE);
    }

    public static void printSeparateLine(final @NotNull String msg) {
        var str = StringUtils.center(msg, 40);
        System.out.println(SHORTER_SEPARATE_LINE + str + SHORTER_SEPARATE_LINE);
    }
}
