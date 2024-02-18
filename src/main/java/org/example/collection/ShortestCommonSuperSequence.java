package org.example.collection;

import org.apache.commons.lang3.StringUtils;

public class ShortestCommonSuperSequence {

    private static String scs(String x, String y) {
        if (StringUtils.isEmpty(x)) {
            return y;
        }
        if (StringUtils.isEmpty(y)) {
            return x;
        }

        if (x.charAt(0) == y.charAt(0)) {
            return x.charAt(0) + scs(x.substring(1), y.substring(1));
        }

        if (scs(x, y.substring(1)).length() <= scs(x.substring(1), y).length()) {
            return y.charAt(0) + scs(x, y.substring(1));
        } else {
            return x.charAt(0) + scs(x.substring(1), y);
        }
    }

    public static void main(String[] args) {
        System.out.println(scs("abcbdab", "bdcaba"));
    }
}
