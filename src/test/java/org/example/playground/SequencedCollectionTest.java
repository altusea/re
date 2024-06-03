package org.example.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.SequencedCollection;

class SequencedCollectionTest {

    @Test
    void ordering() {
        SequencedCollection<String> sequencedCollection = new ArrayList<>();
        sequencedCollection.add("ciao");
        sequencedCollection.add("ni hao");
        sequencedCollection.add("hello");
        sequencedCollection.addFirst("ola");
        Assertions.assertEquals(sequencedCollection.getFirst(), "ola");
    }
}
