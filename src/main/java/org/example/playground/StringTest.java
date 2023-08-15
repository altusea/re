package org.example.playground;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class StringTest {
    public static void main(String[] args) {
        System.out.println(StringUtils.stripStart("0000abc", "0"));
        System.out.println(StringUtils.stripStart("0000", "0"));
        System.out.println(StringUtils.stripStart("0000abc", "00"));
        System.out.println(StringUtils.stripStart("0000abc", "000"));

        System.out.println("========================================");

        Set<String> sm = Sets.newHashSet("f", "female", "女");
        System.out.println(sm.contains("女".toLowerCase()));
        System.out.println(sm.contains("M".toLowerCase()));

        System.out.println("========================================");

        String a = "  ccccc   ";
        String b = "abcd";
        String aTrimmed = a.trim();
        String bTrimmed = b.trim();

        System.out.println(a == aTrimmed); // false
        System.out.println(b == bTrimmed); // true

        System.out.println("========================================");

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
