package org.example.codewar;

import java.util.regex.Pattern;

public class SecureTester {
    public static boolean alphanumeric(String s) {
        Pattern pattern = Pattern.compile("[A-Za-z-0-9]+");
        return pattern.matcher(s).matches();
    }

    public static void main(String[] args) {
        System.out.println(alphanumeric("Add123"));
        System.out.println(alphanumeric("_hello"));
    }
}
