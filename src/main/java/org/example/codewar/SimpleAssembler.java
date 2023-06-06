package org.example.codewar;

import java.util.HashMap;
import java.util.Map;

public class SimpleAssembler {
    public static Map<String, Integer> interpret(String[] program) {
        Map<String, Integer> register = new HashMap<>();
        int n = program.length, i = 0;
        while (i < n) {
            String instruction = program[i];
            String[] info = instruction.split(" ");
            if (instruction.startsWith("m")) {
                // mov x y
                if (register.containsKey(info[2])) {
                    register.put(info[1], register.get(info[2]));
                } else {
                    register.put(info[1], Integer.parseInt(info[2]));
                }
            } else if (instruction.startsWith("i")) {
                // inc x
                register.put(info[1], register.get(info[1]) + 1);
            } else if (instruction.startsWith("d")) {
                // dec x
                register.put(info[1], register.get(info[1]) - 1);
            } else {
                // jnz x y
                int x;
                if (register.containsKey(info[1])) {
                    x = register.get(info[1]);
                } else {
                    x = Integer.parseInt(info[2]);
                }
                if (x != 0) {
                    i = i + Integer.parseInt(info[2]) - 1;
                }
            }

            i++;
        }

        return register;
    }

    public static void main(String[] args) {
        String[] program = new String[]{"mov a -10", "mov b a", "inc a", "dec b", "jnz a -2"};
        Map<String, Integer> out = new HashMap<>();
        out.put("a", 0);
        out.put("b", -20);
        Printer.print(out, interpret(program));
    }
}
