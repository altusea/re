package org.example.codewar;

import java.util.List;

public class Machine {

    // https://www.codewars.com/kata/54c1bf903f0696f04600068b/train/java
    public interface CPU {
        /**
         * Returns the value of the named register.
         */
        int readReg(String name);

        /**
         * Stores the value into the given register.
         */
        void writeReg(String name, int value);

        /**
         * Pops the top element of the stack, returning the value.
         */
        int popStack();

        /**
         * Pushes an element onto the stack.
         */
        void writeStack(int value);
    }

    private final CPU cpu;

    private static final List<String> registers = List.of("a", "b", "c", "d");
    private static final List<String> revRegisters = List.of("d", "c", "b", "a");

    public Machine(CPU cpu) {
        this.cpu = cpu;
    }

    public void exec(String instr) {

        String[] parts = instr.split(" ");

        switch (parts[0]) {
            case "push" -> {
                // Pushes a register [reg] or an immediate value [int] to the stack.
                String operand = parts[1];
                if (registers.contains(operand)) {
                    cpu.writeStack(cpu.readReg(operand));
                } else {
                    cpu.writeStack(Integer.parseInt(operand));
                }
            }
            case "pop" -> {
                if (parts.length == 1) {
                    // Pops a value of the stack, discarding the value.
                    cpu.popStack();
                } else {
                    int v = cpu.popStack();
                    cpu.writeReg(parts[1], v);
                }
            }
            case "pushr" -> {
                // Pushes the general registers onto the stack, in order. (a, b, c, d)
                for (String reg : registers) {
                    cpu.writeStack(cpu.readReg(reg));
                }
            }
            case "pushrr" -> {
                // Pushes the general registers onto the stack, in reverse order. (d, c, b, a)
                for (String reg : revRegisters) {
                    cpu.writeStack(cpu.readReg(reg));
                }
            }
            case "popr" -> {
                // Pops values off the stack, and loads them into the general registers,
                // in order so that the two executions `pushr()`  and `popr()` would leave the registers unchanged.
                for (String reg : revRegisters) {
                    cpu.writeReg(reg, cpu.popStack());
                }
            }
            case "poprr" -> {
                // Pops values off the stack, and loads them into the general registers,
                // in order so that the two executions `pushr()`  and `poprr()` would invert the values of the registers from left to right.
                for (String reg : registers) {
                    cpu.writeReg(reg, cpu.popStack());
                }
            }
            case "mov" -> {
                int v;
                if (registers.contains(parts[1])) {
                    v = cpu.readReg(parts[1]);
                } else {
                    v = Integer.parseInt(parts[1]);
                }
                cpu.writeReg(parts[2], v);
            }
            case "add" -> {
                int times;
                if (registers.contains(parts[1])) {
                    times = cpu.readReg(parts[1]);
                } else {
                    times = Integer.parseInt(parts[1]);
                }
                int sum = 0;
                for (int i = 0; i < times; i++) {
                    sum += cpu.popStack();
                }
                cpu.writeReg("a", sum);
            }
            case "mul" -> {
                int times;
                if (registers.contains(parts[1])) {
                    times = cpu.readReg(parts[1]);
                } else {
                    times = Integer.parseInt(parts[1]);
                }
                int product = 1;
                for (int i = 0; i < times; i++) {
                    product *= cpu.popStack();
                }
                cpu.writeReg("a", product);
            }
        }
    }
}
