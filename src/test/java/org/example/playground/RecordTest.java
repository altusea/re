package org.example.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RecordTest {

    private final Logger logger = LoggerFactory.getLogger(RecordTest.class);

    record JdkReleasedEvent(String name) {
    }

    @Test
    void records() {
        var event = new JdkReleasedEvent("java 21");
        Assertions.assertEquals(event.name(), "java 21");
        logger.info(event.name());
    }
}
