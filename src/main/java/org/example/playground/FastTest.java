package org.example.playground;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.longs.LongSets;
import it.unimi.dsi.fastutil.longs.LongStack;

import java.util.Objects;

import static org.example.util.ConsoleUtil.printSeparateLine;

public class FastTest {

    public static void main(String[] args) {
        Pair<Integer, Boolean> integerBooleanPair = Pair.of(2, true);
        Pair<Integer, Boolean> integerBooleanPair2 = Pair.of(2, true);
        System.out.println(integerBooleanPair);
        System.out.println(Objects.equals(integerBooleanPair, integerBooleanPair2));
        System.out.println(Objects.compare(integerBooleanPair, integerBooleanPair2, Pair.lexComparator()));

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
