package org.example.codewar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class AnagramTest {

    @Test
    public void testKnownInputs() {
        Anagrams anagram = new Anagrams();

        Assertions.assertEquals(BigInteger.ONE, anagram.listPosition("A"), "Position for 'A' incorrect");
        Assertions.assertEquals(BigInteger.valueOf(2), anagram.listPosition("ABAB"), "Position for 'ABAB' incorrect");
        Assertions.assertEquals(BigInteger.ONE, anagram.listPosition("AAAB"), "Position for 'AAAB' incorrect");
        Assertions.assertEquals(BigInteger.valueOf(4), anagram.listPosition("BAAA"), "Position for 'BAAA' incorrect");
        Assertions.assertEquals(BigInteger.valueOf(24572), anagram.listPosition("QUESTION"), "Position for 'QUESTION' incorrect");
        Assertions.assertEquals(BigInteger.valueOf(10743), anagram.listPosition("BOOKKEEPER"), "Position for 'BOOKKEEPER' incorrect");
    }
}
