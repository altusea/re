package org.example.codewar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.MessageFormat;

public class HQ9p {
    public static void main(String[] args) throws Exception {
        StringBuilder code = new StringBuilder();
        BufferedReader input;
        if (args.length > 0) {
            input = new BufferedReader(new FileReader(args[0]));
        } else {
            input = new BufferedReader(new InputStreamReader(System.in));
        }
        while (input.ready()) {
            code.append(input.readLine().toLowerCase());
        }

        for (char instr : code.toString().toCharArray()) {
            switch (instr) {
                case 'q' -> System.out.println(code);
                case 'h' -> System.out.println("Hello, World!");
                case '9' -> printBottles();
            }
        }
    }

    public static void printBottles() {
        String byob = bottles(99);
        for (int x = 99; x > 0; ) {
            System.out.println(byob + " on the wall");
            System.out.println(byob);
            System.out.println("Take one down, pass it around");
            byob = bottles(--x);
            System.out.println(byob + " on the wall\n");
        }
    }

    static String bottles(int n) {
        return MessageFormat.format("{0,choice,0#No more bottles|1#One bottle|2#{0} bottles} of beer", n);
    }
}
