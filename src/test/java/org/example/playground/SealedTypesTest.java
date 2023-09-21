package org.example.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SealedTypesTest {

    sealed interface Animal permits Bird, Cat, Dog {
    }

    static final class Cat implements Animal {
        String meow() {
            return "meow";
        }
    }

    static final class Dog implements Animal {
        String bark() {
            return "woof";
        }
    }

    static final class Bird implements Animal {
        String chirp() {
            return "chirp";
        }
    }

    String communicate(Animal animal) {
        return switch (animal) {
            case Cat cat -> cat.meow();
            case Dog dog -> dog.bark();
            case Bird bird -> bird.chirp();
        };
    }

    @Test
    void doLittleTest() {
        Assertions.assertEquals(communicate(new Dog()), "woof");
        Assertions.assertEquals(communicate(new Cat()), "meow");
    }
}
