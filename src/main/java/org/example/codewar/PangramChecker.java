package org.example.codewar;

public class PangramChecker {
    public boolean check(String sentence) {
        return sentence.toLowerCase().chars().filter(Character::isAlphabetic).distinct().count() == 26;
    }
}
