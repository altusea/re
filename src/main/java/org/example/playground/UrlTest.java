package org.example.playground;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.core.util.URLUtil;
import org.example.util.HttpUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.example.playground.CommonTest.printSeparateLine;

public class UrlTest {

    public static void main(String[] args) {
        System.out.println(Convert.toStr("测试"));
        System.out.println(URLEncoder.encode("测试", StandardCharsets.UTF_8));
        System.out.println(URLEncoder.encode("hello", StandardCharsets.UTF_8));

        UrlQuery urlQuery = new UrlQuery();
        urlQuery.add("a", "xxx");
        urlQuery.add("b", "yyy");
        urlQuery.add("a", "zzz");
        System.out.println(urlQuery.build(StandardCharsets.UTF_8));

        Map<String, String> parmas = Map.of("a", "a", "b", "测试");
        System.out.println(UrlQuery.of(parmas).build(StandardCharsets.UTF_8));


        System.out.println("test anchor:");
        String anchorUrl = UrlBuilder.of("https://www.baidu.com#toc")
                .addQuery("key1", "a")
                .addQuery("key2", "b")
                .build();
        System.out.println(anchorUrl);

        printSeparateLine();

        System.out.println("test anchor 2:");
        String anchorUrl2 = UrlBuilder.of("https://www.baidu.com#toc")
                .addQuery("key1", "a")
                .addQuery("key2", "b")
                .setFragment("/login")
                .build();
        System.out.println(anchorUrl2);

        printSeparateLine();

        UrlBuilder urlBuilder = UrlBuilder.of("https://www.google.com", StandardCharsets.UTF_8);
        urlBuilder.addPath("/path1/path2");
        urlBuilder.addPathSegment("path3");
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
