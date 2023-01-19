package org.example.codewar;

public class DRoot {
    public static int digital_root(int n) {
        while (n > 9) {
            int t = 0;
            while (n > 0) {
                t += n % 10;
                n /= 10;
            }
            n = t;
        }
        return n;
    }
}
