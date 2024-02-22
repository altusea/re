package org.example.codewar;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Machine {

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
    private static final Map<String, BiFunction<Integer, Integer, Integer>> OP_FUNCS = new HashMap<>();

    static {
        OP_FUNCS.put("add", Integer::sum);
        OP_FUNCS.put("sub", (a, b) -> a - b);
        OP_FUNCS.put("mul", (a, b) -> a * b);
        OP_FUNCS.put("div", (a, b) -> a / b);
        OP_FUNCS.put("and", (a, b) -> a & b);
        OP_FUNCS.put("or", (a, b) -> a | b);
        OP_FUNCS.put("xor", (a, b) -> a ^ b);
    }

    public Machine(CPU cpu) {
        this.cpu = cpu;
    }

    public void exec(String instr) {
        String[] split = instr.split("[ ,]+");
        switch (split[0]) {
            case "pop":
                if (split.length == 1) {
                    pop("");
                } else {
                    pop(split[1]);
                }
                break;
            case "push":
                push(split[1]);
                break;
            case "popr":
                popr();
                break;
            case "poprr":
                poprr();
                break;
            case "pushr":
                pushr();
                break;
            case "pushrr":
                pushrr();
                break;
            case "mov":
                if (split.length == 2) {
                    mov(split[1], "a");
                } else {
                    mov(split[1], split[2]);
                }
                break;
            default:
                if (split.length == 2) {
                    operation(split[0], split[1], "a");
                } else {
                    operation(split[0], split[1], split[2]);
                }
                break;
        }
    }

    private void popr() {
        for (String reg : new String[]{"d", "c", "b", "a"}) {
            pop(reg);
        }
    }

    private void poprr() {
        for (String reg : new String[]{"a", "b", "c", "d"}) {
            pop(reg);
        }
    }

    private void pop(String reg) {
        if (reg.isEmpty()) {
            // Discard
            cpu.popStack();
        } else {
            // Pop to a register
            cpu.writeReg(reg, cpu.popStack());
        }
    }

    private void pushr() {
        for (String reg : new String[]{"a", "b", "c", "d"}) {
            push(reg);
        }
    }

    private void pushrr() {
        for (String reg : new String[]{"d", "c", "b", "a"}) {
            push(reg);
        }
    }

    private void push(String regint) {
        if ("abcd".contains(regint)) {
            cpu.writeStack(cpu.readReg(regint));
        } else {
            cpu.writeStack(Integer.parseInt(regint));
        }
    }

    private void mov(String n, String reg) {
        cpu.writeReg(reg, Integer.parseInt(n));
    }

    private void operation(String opType, String regint, String reg) {

        int nToPop = "abcd".contains(regint) ? cpu.readReg(regint) : Integer.parseInt(regint);

        if (opType.charAt(opType.length() - 1) == 'a') {
            opType = opType.substring(0, opType.length() - 1);
            push("a");
        }
        int res = cpu.popStack();
        for (int x = 0; x < nToPop - 1; x++)
            res = OP_FUNCS.get(opType).apply(res, cpu.popStack());
        cpu.writeReg(reg, res);
    }
}
