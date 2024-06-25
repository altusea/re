package org.example.playground.codec;

import com.google.common.io.BaseEncoding;
import org.dromara.hutool.core.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

import static org.example.util.ConsoleUtil.printSeparateLine;

public class Base64Test {

    public static void main(String[] args) {
        String url = "https://www.baidu.com?query=时间";
        String a1 = Base64.encode(url);
        System.out.println(a1);
        System.out.println(Base64.decodeStr(a1)); // aHR0cHM6Ly93d3cuYmFpZHUuY29tP3F1ZXJ0PeaXtumXtA==
        String a2 = Base64.encodeUrlSafe(url);
        System.out.println(a2); // aHR0cHM6Ly93d3cuYmFpZHUuY29tP3F1ZXJ0PeaXtumXtA
        System.out.println(Base64.decodeStr(a2));

        printSeparateLine();
        String c = "测试一下子啊a";
        byte[] cs = c.getBytes(StandardCharsets.UTF_8);
        System.out.println("length of byte array: " + cs.length);

        printSeparateLine();
        System.out.println(BaseEncoding.base64Url().encode(cs));
        System.out.println(BaseEncoding.base64Url().omitPadding().encode(cs));
        System.out.println(java.util.Base64.getUrlEncoder().encodeToString(cs));
        System.out.println(java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(cs));

        printSeparateLine();
        var withPadding = "5rWL6K-V5LiA5LiL5a2Q5ZWKYQ==";
        var withoutPadding = "5rWL6K-V5LiA5LiL5a2Q5ZWKYQ";
        printSeparateLine("java.util");
        System.out.println(new String(
                java.util.Base64.getUrlDecoder().decode(withPadding.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8));
        System.out.println(new String(
                java.util.Base64.getUrlDecoder().decode(withoutPadding.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8));
        printSeparateLine("org.apache.commons.codec");
        System.out.println(new String(
                org.apache.commons.codec.binary.Base64.decodeBase64(withPadding.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8));
        System.out.println(new String(
                org.apache.commons.codec.binary.Base64.decodeBase64(withoutPadding),
                StandardCharsets.UTF_8));
    }
}
