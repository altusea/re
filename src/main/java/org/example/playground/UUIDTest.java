package org.example.playground;

import cn.hutool.core.lang.id.NanoId;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;

import java.util.UUID;

import static org.example.util.ConsoleUtil.printSeparateLine;

public class UUIDTest {

    public static void main(String[] args) {
        System.out.println("generate a nanoid which is url-friendly:");
        System.out.println(NanoId.randomNanoId());

        printSeparateLine();
        NoArgGenerator v1Gen = Generators.timeBasedGenerator();
        UUID v1 = v1Gen.generate();
        System.out.println("timeBasedGenerator:");
        System.out.println(v1Gen.getType());
        System.out.println("UUID Version 1: " + v1);
        System.out.println(v1.timestamp());

        printSeparateLine();
        System.out.println("timeBasedReorderedGenerator:");
        NoArgGenerator v6Gen = Generators.timeBasedReorderedGenerator();
        System.out.println(v6Gen.getType());
        UUID v6 = v6Gen.generate();
        System.out.println("UUID Version 6: " + v6);

        printSeparateLine();
        System.out.println("timeBasedEpochGenerator:");
        NoArgGenerator v7Gen = Generators.timeBasedEpochGenerator();
        System.out.println(v7Gen.getType());
        UUID v7 = v7Gen.generate();
        System.out.println("UUID Version 7: " + v7);
    }
}
