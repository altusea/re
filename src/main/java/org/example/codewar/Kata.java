package org.example.codewar;

import org.apache.commons.lang3.StringUtils;

public class Kata {

    public static String longToIP(long ip) {
        long all_1 = 0xff;

        long r1 = (ip >> 24) & all_1;
        long r2 = (ip >> 16) & all_1;
        long r3 = (ip >> 8) & all_1;
        long r4 = (ip >> 0) & all_1;

        return String.format("%d.%d.%d.%d", r1, r2, r3, r4);
    }

    public static String sumStrings(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? a.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? b.charAt(j) - '0' : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            sb.append(tmp % 10);
            i--;
            j--;
        }
        if (carry == 1) sb.append(1);
        String tmpRes = sb.reverse().toString();
        return StringUtils.stripStart(tmpRes, "0");
    }

    public static int fusc(int n) {
        if (n == 0 || n == 1) return n;
        if (n % 2 == 0) return fusc(n / 2);
        return fusc(n / 2) + fusc(n / 2 + 1);
    }

    public static void main(String[] args) {
        System.out.println(1_0000);
        System.out.println(fusc(1024));
    }
}
