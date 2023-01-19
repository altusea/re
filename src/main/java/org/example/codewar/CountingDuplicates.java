package org.example.codewar;

public class CountingDuplicates {
    public static int duplicateCount(String text) {
        String tx = text.toLowerCase();
        int[] count = new int[36];
        for (char c : tx.toCharArray()) {
            if (Character.isDigit(c)) {
                count[c - '0']++;
            } else {
                count[c - 'a' + 10]++;
            }
        }
        int res = 0;
        for (int i = 0; i < 36; i++) {
            if (count[i] > 1) res++;
        }
        return res;
    }
}
