package org.example.playground;

import cn.hutool.core.date.DateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class TimeTimeTime {

    public static void main(String[] args) {

        System.out.println("==================== java build-in time classes ====================");
        LocalTime localTime = LocalTime.now();
        System.out.println("LocalTime.now(): " + localTime);
        LocalDate localDate = LocalDate.now();
        System.out.println("LocalDate.now(): " + localDate);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("LocalDateTime: " + localDateTime);
        Date date = new Date();
        System.out.println("new Date(): " + date);

        System.out.println("\n==================== hutool time classes ====================");
        DateTime dateTime1 = DateTime.now();
        System.out.println("hutool DateTime.now(): " + dateTime1 + " with timezone " + dateTime1.getTimeZone());
    }
}
