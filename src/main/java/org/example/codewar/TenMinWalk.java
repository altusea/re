package org.example.codewar;

public class TenMinWalk {
    public static boolean isValid(char[] walk) {
        if (walk.length != 10) return false;
        int horizontal = 0, vertical = 0;
        for (var c : walk) {
            switch (c) {
                case 'n' -> vertical++;
                case 's' -> vertical--;
                case 'e' -> horizontal++;
                case 'w' -> horizontal--;
            }
        }
        return horizontal == 0 && vertical == 0;
    }
}
