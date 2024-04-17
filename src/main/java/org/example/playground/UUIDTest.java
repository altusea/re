package org.example.playground;

import cn.hutool.core.lang.id.NanoId;
import com.fasterxml.uuid.Generators;

import java.util.UUID;

import static org.example.playground.CommonTest.printSeparateLine;

public class UUIDTest {

    public static void main(String[] args) {
        System.out.println("generate a nanoid which is url-friendly:");
        System.out.println(NanoId.randomNanoId());

        printSeparateLine();
        UUID v1 = Generators.timeBasedGenerator().generate();
        System.out.println("timeBasedGenerator:");
        System.out.println("UUID Version 1: " + v1);
        System.out.println(v1.timestamp());

        printSeparateLine();
        System.out.println("timeBasedReorderedGenerator:");
        UUID v6 = Generators.timeBasedReorderedGenerator().generate();
        System.out.println("UUID Version 6: " + v6);

        printSeparateLine();
        System.out.println("timeBasedEpochGenerator:");
        UUID v7 = Generators.timeBasedEpochGenerator().generate();
        System.out.println("UUID Version 7: " + v7);
    }
}
