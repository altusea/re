package org.example.playground;

import cn.hutool.core.date.DateUtil;

public class TimeTest {

    public static void main(String[] args) {
        long thirtyMinutes = 30 * 60 * 1000;
        long timestampNow = System.currentTimeMillis();
        long timestampThirtyMinutesLater = timestampNow + thirtyMinutes;

        System.out.println(DateUtil.date(timestampNow));
        System.out.println(DateUtil.date(timestampThirtyMinutesLater));
    }
}
