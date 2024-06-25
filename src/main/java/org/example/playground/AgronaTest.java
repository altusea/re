package org.example.playground;

import org.agrona.collections.IntArrayList;
import org.agrona.collections.IntHashSet;

public class AgronaTest {

    public static void main(String[] args) {
        var a = new IntArrayList();
        a.add(1);
        a.add(2);
        a.add(3);
        a.remove(1);
        System.out.println(a);

        var set = new IntHashSet();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(-1);
        System.out.println(set);

    }
}
