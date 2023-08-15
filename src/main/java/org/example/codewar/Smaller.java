package org.example.codewar;

public class Smaller {

    // O(n*n), too slow
    public static int[] smaller(int[] unsorted) {
        if (unsorted == null || unsorted.length == 0) return new int[]{};
        int n = unsorted.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = i + 1; j < n; j++) {
                if (unsorted[j] < unsorted[i]) count++;
            }
            res[i] = count;
        }
        return res;
    }
}
