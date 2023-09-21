package org.example.playground;

public class StringTest {

    public static void main(String[] args) {
        String text = "我能吞下glass而不伤害body";
        for (int offset = 0; offset < text.length(); ) {
            int ch = text.codePointAt(offset);
            System.out.println(Character.toString(ch));
            offset += Character.charCount(ch);
        }
    }
}
