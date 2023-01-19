package org.example.codewar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MorseCodeDecoder {

    private static final Map<String, String> MorseCode = new HashMap<>();

    public static String decode(String morseCode) {
        String[] words = morseCode.trim().split("\\s{3}");
        List<String> wordList = new ArrayList<>();

        for (String word : words) {
            String[] chars = word.split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (String chr : chars) {
                sb.append(MorseCode.get(chr));
            }

            wordList.add(sb.toString());
        }

        return String.join(" ", wordList);
    }

    public static void main(String[] args) {
        String regex = "\\s{3}";
        System.out.println(Pattern.compile(regex).matcher("   ").matches());
    }
}
