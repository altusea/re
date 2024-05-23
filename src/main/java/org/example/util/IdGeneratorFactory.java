package org.example.util;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

import java.util.UUID;

public class IdGeneratorFactory {

    public static TimeBasedGenerator getTimeBasedGenerator() {
        EthernetAddress ethernetAddress = EthernetAddress.fromInterface();
        return Generators.timeBasedGenerator(ethernetAddress);
    }

    public static void main(String[] args) {
        TimeBasedGenerator uuidGenerator = getTimeBasedGenerator();
        UUID uuid = uuidGenerator.generate();
        System.out.println(uuid.toString());
        System.out.println(uuid.timestamp());
    }
}
