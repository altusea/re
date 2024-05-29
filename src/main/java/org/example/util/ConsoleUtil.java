package org.example.util;

import org.jetbrains.annotations.NotNull;

public class ConsoleUtil {

    private static final String SEPARATE_LINE = "=".repeat(80);

    private static final String SHORTER_LINE = "=".repeat(37);

    public static void printSeparateLine() {
        System.out.println(SEPARATE_LINE);
    }

    public static void printSeparateLine(final @NotNull String msg) {
        System.out.println(SHORTER_LINE + " " + msg + " " + SHORTER_LINE);
    }
}
