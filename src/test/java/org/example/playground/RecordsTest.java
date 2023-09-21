package org.example.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class RecordsTest {

    record User(String name, long accountNumber) {
    }

    record UserDeletedEvent(User user) {
    }

    record UserCreatedEvent(String name) {
    }

    record ShutdownEvent(Instant instant) {
    }

    String respond(Object o) {
        if (o instanceof ShutdownEvent(Instant instant)) {
            System.out.println("going to to shutdown the system at " + instant.toEpochMilli());
        }
        return switch (o) {
            case UserDeletedEvent(var user) -> "the user " + user.name() + " has been deleted";
            case UserCreatedEvent(var name) -> "the new user with name " + name + " has been created";
            default -> null;
        };
    }

    @Test
    void respondToEvents() {
        Assertions.assertEquals(respond(new UserCreatedEvent("li")), "the new user with name li has been created");
        Assertions.assertEquals(respond(new UserDeletedEvent(new User("li", 1))), "the user li has been deleted");
    }
}
