package org.example.playground;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class UUIDTest {

    public static void main(String[] args) {
        UUID v1 = Generators.timeBasedGenerator().generate();
        System.out.println("UUID Version 1: " + v1);
        System.out.println(v1.timestamp());

        UUID v6 = Generators.timeBasedReorderedGenerator().generate();
        System.out.println("UUID Version 6: " + v6);

        UUID v7 = Generators.timeBasedEpochGenerator().generate();
        System.out.println("UUID Version 7: " + v7);
    }
}
