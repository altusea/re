package org.example.playground;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.longs.LongSets;
import it.unimi.dsi.fastutil.longs.LongStack;

import static org.example.util.ConsoleUtil.printSeparateLine;

public class FastTest {

    public static void main(String[] args) {
        IntList a = new IntArrayList();
        a.add(1);
        a.add(2);
        a.add(3);
        System.out.println(a.getFirst());

        printSeparateLine();
        LongStack longStack = new LongArrayList();
        longStack.push(3);
        longStack.push(5);
        longStack.push(7);
        System.out.println(longStack + "<");
        System.out.println(longStack.popLong());
        System.out.println(longStack.popLong());
        System.out.println(longStack.popLong());
        System.out.println(longStack.isEmpty());

        printSeparateLine();
        LongSet longSet = LongSets.fromTo(100L, 1000L);
        System.out.println(longSet.contains(500L));
    }
}
