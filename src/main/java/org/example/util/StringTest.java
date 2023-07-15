package org.example.util;

public class StringTest {

    public static void main(String[] args) {
        String a = "  ccccc   ";
        String b = "abcd";
        String aTrimmed = a.trim();
        String bTrimmed = b.trim();

        System.out.println(a == aTrimmed); // false
        System.out.println(b == bTrimmed); // true

        String chineseText = "我能吞下玻璃而不伤害身体";
        System.out.println(chineseText.charAt(2));
        System.out.println(chineseText.codePointAt(2));

        StringBuilder sb = new StringBuilder();
        sb.append(15);
        sb.append("hell");
        sb.append('o');
        sb.append(3.2f);
        System.out.println(sb);
    }
}
