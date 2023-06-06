package org.example.playground;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

public class CollectionTest {

    public static void main(String[] args) {
        ImmutableList<String> stringImmutableList = Lists.immutable.of("A", "Bb", "Ccc");
        System.out.println(stringImmutableList);
    }
}
