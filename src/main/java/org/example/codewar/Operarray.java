package org.example.codewar;

import java.util.Arrays;
import java.util.function.BiFunction;

public class Operarray {
    public static long gcdi(long x, long y) {
        return gcd(Math.abs(x), Math.abs(y));
    }

    static long gcd(long a, long b) {
        long min = Math.min(a, b), max = a + b - min, div = min;
        for (int i = 1; i < min; div = min / ++i)
            if (min % div == 0 && max % div == 0)
                return div;
        return 1;
    }

    public static long lcmu(long a, long b) {
        long aa = Math.abs(a), ab = Math.abs(b);
        return aa * ab / gcd(aa, ab);
    }

    public static long som(long a, long b) {
        return a + b;
    }

    public static long maxi(long a, long b) {
        return Math.max(a, b);
    }

    public static long mini(long a, long b) {
        return Math.min(a, b);
    }

    public static long[] operArray(BiFunction<Long, Long, Long> operator, long[] arr, long init) {
        long acc = init;
        int n = arr.length;
        long[] res = new long[arr.length];
        for (int i = 0; i < n; i++) {
            acc = operator.apply(acc, arr[i]);
            res[i] = acc;
        }
        return res;
    }

    public static void main(String[] args) {
        long[] a = new long[]{18, 69, -90, -78, 65, 40};

        long[] r = new long[]{18, 3, 3, 3, 1, 1};
        testing(Arrays.toString(Operarray.operArray(Operarray::gcdi, a, a[0])),
                Arrays.toString(r));
        r = new long[]{18, 414, 2070, 26910, 26910, 107640};
        testing(Arrays.toString(Operarray.operArray(Operarray::lcmu, a, a[0])),
                Arrays.toString(r));
        r = new long[]{18, 87, -3, -81, -16, 24};
        testing(Arrays.toString(Operarray.operArray(Operarray::som, a, 0)),
                Arrays.toString(r));
        r = new long[]{18, 18, -90, -90, -90, -90};
        testing(Arrays.toString(Operarray.operArray(Operarray::mini, a, a[0])),
                Arrays.toString(r));
        r = new long[]{18, 69, 69, 69, 69, 69};
        testing(Arrays.toString(Operarray.operArray(Operarray::maxi, a, a[0])),
                Arrays.toString(r));
    }

    private static void testing(String a, String b) {
        System.out.println(a);
        System.out.println(b);
    }
}
