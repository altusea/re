package org.example.playground.codec;

import com.google.common.io.BaseEncoding;

import java.nio.charset.StandardCharsets;

import static org.example.util.ConsoleUtil.printSeparateLine;

public class EncodeTest {

    public static void main(String[] args) {
        String s = "hello, world";

        String base32Str = BaseEncoding.base32().encode(s.getBytes());
        System.out.println(s.length());
        System.out.println(base32Str.length());

        printSeparateLine();
        String b = "测试";
        byte[] bs = b.getBytes(StandardCharsets.UTF_8);
        System.out.println(BaseEncoding.base32().encode(bs));
        System.out.println("length of byte array: " + bs.length);

        printSeparateLine();
        String c = "测试一下子";
        byte[] cs = c.getBytes(StandardCharsets.UTF_8);
        System.out.println(BaseEncoding.base32().encode(cs));
        System.out.println("length of byte array: " + cs.length);
    }
}
