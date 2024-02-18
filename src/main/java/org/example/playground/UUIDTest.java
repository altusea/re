package org.example.playground;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class UUIDTest {

    public static void main(String[] args) {
        UUID v1 = Generators.timeBasedGenerator().generate();
        System.out.println("timeBasedGenerator:");
        System.out.println("UUID Version 1: " + v1);
        System.out.println(v1.timestamp());

        System.out.println("=======================================");
        System.out.println("timeBasedReorderedGenerator:");
        UUID v6 = Generators.timeBasedReorderedGenerator().generate();
        System.out.println("UUID Version 6: " + v6);

        System.out.println("=======================================");
        System.out.println("timeBasedEpochGenerator:");
        UUID v7 = Generators.timeBasedEpochGenerator().generate();
        System.out.println("UUID Version 7: " + v7);
    }
}
