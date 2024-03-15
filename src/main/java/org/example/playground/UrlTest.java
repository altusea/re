package org.example.playground;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.URLUtil;
import org.example.util.HttpUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.example.playground.CommonTest.printSeparateLine;

public class UrlTest {

    public static void main(String[] args) {
        UrlBuilder urlBuilder = UrlBuilder.of("https://www.google.com", StandardCharsets.UTF_8);
        urlBuilder.addQuery("order", "ascend");
        urlBuilder.addQuery("name", "张三");
        System.out.println(urlBuilder.build());
        System.out.println(HttpUtils.urlEncode("张三"));

        UrlBuilder urlBuilder1 = UrlBuilder.of("https://www.google.com?order=ascend", StandardCharsets.UTF_8);
        urlBuilder1.addQuery("pageSize", 10);
        String url = urlBuilder1.build();
        System.out.println(url);
        System.out.println(URLUtil.encode(url, StandardCharsets.UTF_8));

        System.out.println(URLEncoder.encode(url, StandardCharsets.UTF_8));

        printSeparateLine();
        String url2 = "https://www.google.com?query=todo&timezone=taipei+qwe";
        System.out.println("net:       " + URLEncoder.encode(url2, StandardCharsets.UTF_8));
        System.out.println("hutool:    " + URLUtil.encode(url2));
        System.out.println("HttpUtils: " + HttpUtils.urlEncode(url2));
    }
}
