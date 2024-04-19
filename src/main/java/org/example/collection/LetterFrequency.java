package org.example.collection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LetterFrequency {

    public static Map<Integer, Long> countLetters(String filename) throws IOException {
        return Files.lines(Paths.get(filename), StandardCharsets.UTF_8)
                .flatMapToInt(String::chars)
                .filter(Character::isLetter)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static int[] countLetters2(String filename) throws IOException {
        int[] freqs = new int[26];
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = in.readLine()) != null) {
                line = line.toUpperCase();
                for (char ch : line.toCharArray()) {
                    if (Character.isLetter(ch)) {
                        freqs[ch - 'A']++;
                    }
                }
            }
        }
        return freqs;
    }
}
