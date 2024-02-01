package org.example.playground;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.URLUtil;

import java.nio.charset.StandardCharsets;

public class UrlTest {

    public static void main(String[] args) {
        UrlBuilder urlBuilder = UrlBuilder.of("https://www.google.com", StandardCharsets.UTF_8);
        urlBuilder.addQuery("order", "ascend");
        System.out.println(urlBuilder.build());

        UrlBuilder urlBuilder1 = UrlBuilder.of("https://www.google.com?order=ascend", StandardCharsets.UTF_8);
        urlBuilder1.addQuery("pageSize", 10);
        String url = urlBuilder1.build();
        System.out.println(url);
        System.out.println(URLUtil.encode(url, StandardCharsets.UTF_8));
    }
}
