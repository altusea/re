package org.example.playground;

import org.apache.commons.lang3.StringUtils;

public class StringTest {
    public static void main(String[] args) {
        System.out.println(StringUtils.stripStart("0000abc", "0"));
        System.out.println(StringUtils.stripStart("0000", "0"));
        System.out.println(StringUtils.stripStart("0000abc", "00"));
        System.out.println(StringUtils.stripStart("0000abc", "000"));
    }
}