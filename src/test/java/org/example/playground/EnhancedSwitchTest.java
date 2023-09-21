package org.example.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

class EnhancedSwitchTest {

    int calculateTimeOff(DayOfWeek dayOfWeek) {
        return switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> 16;
            case SATURDAY, SUNDAY -> 24;
        };
    }

    @Test
    void testTimeOff() {
        Assertions.assertEquals(calculateTimeOff(DayOfWeek.FRIDAY), 16);
        Assertions.assertEquals(calculateTimeOff(DayOfWeek.SUNDAY), 24);
    }
}
