package org.example.playground;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {

    public static void main(String[] args) {
        String link = "https://test.regex.com/markStart/water/123456-ff3fd979581267a4";
        Pattern pattern = Pattern.compile("markStart/(\\w+)/(\\d+)-([0-9a-f]{16})$");
        Matcher matcher = pattern.matcher(link);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
        } else {
            System.out.println("not matched!");
        }
    }
}
