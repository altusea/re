package org.example.playground;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeApiTest {

    public static void main(String[] args) {
        String s = DateTimeFormatter
                .ofPattern("B")
                .format(LocalDateTime.now());
        System.out.println(s);
    }
}
