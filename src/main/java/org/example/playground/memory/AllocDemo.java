package org.example.playground.memory;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

public class AllocDemo {

    public static void main(String[] args) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment cString = arena.allocateFrom("Panama");
            String jString = cString.getString(0L);
            System.out.println(jString);
        }
    }

}
