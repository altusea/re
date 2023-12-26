package org.example.codewar;

public class Bob {

    public static int enough(int cap, int on, int wait) {
        return Math.max(0, on + wait - cap);
    }
}
