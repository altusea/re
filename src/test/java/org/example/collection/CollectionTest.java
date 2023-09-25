package org.example.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CollectionTest {

    @Test
    void testCommonPath() {
        String[] paths = {"/home/user1/tmp/coverage/test", "/home/user1/tmp/covert/operator", "/home/user1/tmp/coven/members"};
        Assertions.assertEquals("/home/user1/tmp/", CommonPath.commonPath(paths));
        String[] paths2 = {"/hame/user1/tmp/coverage/test", "/home/user1/tmp/covert/operator", "/home/user1/tmp/coven/members"};
        Assertions.assertEquals("/", CommonPath.commonPath(paths2));
    }

    @Test
    void testLevenshtein() {

        Assertions.assertEquals(0, Levenshtein.ld("kitten", "kitten"));
        Assertions.assertEquals(1, Levenshtein.ld("kitten", "sitten"));
        Assertions.assertEquals(2, Levenshtein.ld("kitten", "sittes"));
        Assertions.assertEquals(3, Levenshtein.ld("kitten", "sityteng"));
        Assertions.assertEquals(4, Levenshtein.ld("kitten", "sittYing"));
        Assertions.assertEquals(8, Levenshtein.ld("rosettacode", "raisethysword"));
        Assertions.assertEquals(17, Levenshtein.ld("kitten", "kittenaaaaaaaaaaaaaaaaa"));
        Assertions.assertEquals(17, Levenshtein.ld("kittenaaaaaaaaaaaaaaaaa", "kitten"));

        Assertions.assertTrue(Levenshtein.ld("kitten", "kitten", 3));
        Assertions.assertTrue(Levenshtein.ld("kitten", "sitten", 3));
        Assertions.assertTrue(Levenshtein.ld("kitten", "sittes", 3));
        Assertions.assertTrue(Levenshtein.ld("kitten", "sityteng", 3));
        Assertions.assertFalse(Levenshtein.ld("kitten", "sittYing", 3));
        Assertions.assertFalse(Levenshtein.ld("rosettacode", "raisethysword", 3));
        Assertions.assertFalse(Levenshtein.ld("kitten", "kittenaaaaaaaaaaaaaaaaa", 3));
        Assertions.assertFalse(Levenshtein.ld("kittenaaaaaaaaaaaaaaaaa", "kitten", 3));
    }
}
