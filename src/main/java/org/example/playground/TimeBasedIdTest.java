package org.example.playground;

import com.github.f4b6a3.tsid.TsidCreator;

import static org.example.util.ConsoleUtil.printSeparateLine;

public class TimeBasedIdTest {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(TsidCreator.getTsid256().toLong());
        }

        printSeparateLine();
        var a = TsidCreator.getTsid256();
        System.out.println(a);
        System.out.println(a.toLong());
        System.out.println(a.getInstant());
    }
}
