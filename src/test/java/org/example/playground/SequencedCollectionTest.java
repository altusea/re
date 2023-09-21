package org.example.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.SequencedCollection;

class SequencedCollectionTest {

    @Test
    void ordering() {

        var list = LinkedHashSet.<String>newLinkedHashSet(100);
        if (list instanceof SequencedCollection<String> sequencedCollection) {
            sequencedCollection.add("ciao");
            sequencedCollection.add("ni hao");
            sequencedCollection.add("hello");
            sequencedCollection.addFirst("ola");
            Assertions.assertEquals(sequencedCollection.getFirst(), "ola");
        }
    }
}
