package org.example.codewar;

public class HumanReadableTime {
    public static String makeReadable(int seconds) {
        int h, m;

        int s = seconds;
        h = s / 3600;
        s = s - h * 3600;

        m = s / 60;
        s = s - m * 60;

        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
