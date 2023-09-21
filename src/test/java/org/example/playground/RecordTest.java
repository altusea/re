package org.example.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RecordTest {

    record JdkReleasedEvent(String name) {
    }

    @Test
    void records() {
        var event = new JdkReleasedEvent("java 21");
        Assertions.assertEquals(event.name(), "java 21");
        System.out.println(event);
    }
}
