package org.example.codewar;

public class StringToNumber {
    public static int stringToNumber(String str) {
        boolean negativeFlag = str.charAt(0) == '-';
        int acc = 0;
        int idx = negativeFlag ? 1 : 0;
        int n = str.length();
        while (idx < n) {
            acc = acc * 10 + (str.charAt(idx) - '0');
            idx++;
        }
        return negativeFlag ? (-acc) : acc;
    }

    public static void main(String[] args) {
        System.out.println(stringToNumber("1234"));
        System.out.println(stringToNumber("-7"));
    }
}
