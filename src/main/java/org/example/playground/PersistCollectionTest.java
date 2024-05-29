package org.example.playground;

import org.pcollections.HashTreePSet;
import org.pcollections.PSet;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

import static org.example.util.ConsoleUtil.printSeparateLine;

public class PersistCollectionTest {

    public static void main(String[] args) {
        PSet<String> set = HashTreePSet.empty();
        set = set.plus("something");

        System.out.println(set);
        System.out.println(set.plus("something else"));
        System.out.println(set);

        printSeparateLine();
        PVector<String> vector = TreePVector.empty();
        System.out.println(vector.plus("something"));
        System.out.println(vector);
    }
}
