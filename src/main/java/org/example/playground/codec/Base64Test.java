package org.example.playground.codec;

import org.dromara.hutool.core.codec.binary.Base64;

public class Base64Test {

    public static void main(String[] args) {
        String url = "https://www.baidu.com?query=时间";
        String a1 = Base64.encode(url);
        System.out.println(a1);
        System.out.println(Base64.decodeStr(a1)); // aHR0cHM6Ly93d3cuYmFpZHUuY29tP3F1ZXJ0PeaXtumXtA==
        String a2 = Base64.encodeUrlSafe(url);
        System.out.println(a2); // aHR0cHM6Ly93d3cuYmFpZHUuY29tP3F1ZXJ0PeaXtumXtA
        System.out.println(Base64.decodeStr(a2));
    }
}
