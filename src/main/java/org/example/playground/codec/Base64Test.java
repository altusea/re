package org.example.playground.codec;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;

public class Base64Test {

    public static void main(String[] args) {
        String url = "https://www.baidu.com?query=时间";
        String a1 = Base64Encoder.encode(url);
        System.out.println(a1);
        System.out.println(Base64Decoder.decodeStr(a1)); // aHR0cHM6Ly93d3cuYmFpZHUuY29tP3F1ZXJ0PeaXtumXtA==
        String a2 = Base64Encoder.encodeUrlSafe(url);
        System.out.println(a2); // aHR0cHM6Ly93d3cuYmFpZHUuY29tP3F1ZXJ0PeaXtumXtA
        System.out.println(Base64Decoder.decodeStr(a2));
    }
}
