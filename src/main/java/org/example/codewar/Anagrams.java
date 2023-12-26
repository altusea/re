package org.example.codewar;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
    public BigInteger listPosition(String word) {
        BigInteger rank = BigInteger.ONE;
        BigInteger suffixPermCount = BigInteger.ONE;
        Map<Character, BigInteger> charCounts = new HashMap<>();
        for (int i = word.length() - 1; i > -1; i--) {
            char x = word.charAt(i);
            BigInteger xCount = charCounts.containsKey(x) ? charCounts.get(x).add(BigInteger.ONE) : BigInteger.ONE;
            charCounts.put(x, xCount);
            for (Map.Entry<Character, BigInteger> e : charCounts.entrySet()) {
                if (e.getKey() < x) {
                    rank = rank.add(suffixPermCount.multiply(e.getValue()).divide(xCount));
                }
            }
            suffixPermCount = suffixPermCount.multiply(BigInteger.valueOf(word.length() - i));
            suffixPermCount = suffixPermCount.divide(xCount);
        }
        return rank;
    }

    public static void main(String[] args) {

        System.out.println(new Anagrams().listPosition("BAAA"));
    }
}
