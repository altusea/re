package org.example.playground;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.*;
import java.util.Date;

public class TimeTest {

    public static void main(String[] args) {
        System.out.println("==================== hutool test ====================");
        long thirtyMinutes = 30 * 60 * 1000;
        long timestampNow = System.currentTimeMillis();
        long timestampThirtyMinutesLater = timestampNow + thirtyMinutes;

        System.out.println(LocalDateTimeUtil.of(timestampNow));
        System.out.println(LocalDateTimeUtil.of(timestampThirtyMinutesLater));

        System.out.println("\n==================== java build-in time classes ====================");
        LocalTime localTime = LocalTime.now();
        System.out.println("LocalTime.now(): " + localTime);
        LocalDate localDate = LocalDate.now();
        System.out.println("LocalDate.now(): " + localDate);
        System.out.println("LocalDate.now().atStartOfDay(): " + localDate.atStartOfDay());
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("LocalDateTime: " + localDateTime);
        System.out.println("ZoneId.systemDefault(): " + ZoneId.systemDefault());

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println("OffsetDateTime.now(): " + offsetDateTime + " with offset " + offsetDateTime.getOffset());
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("ZonedDateTime.now(): " + zonedDateTime + " with zone " + zonedDateTime.getZone());

        System.out.println("\n==================== other test ====================");
        LocalDateTime fromDate = LocalDateTimeUtil.of(new Date());
        System.out.println(fromDate);
    }
}
