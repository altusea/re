package org.example.playground;

import cn.hutool.core.util.URLUtil;
import org.example.util.HttpUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class EncodeTest {

    public static void main(String[] args) {
        String url = "http://www.google.com?query=todo&timezone=taipei+qwe";
        System.out.println("net:       " + URLEncoder.encode(url, StandardCharsets.UTF_8));
        System.out.println("hutool:    " + URLUtil.encode(url));
        System.out.println("HttpUtils: " + HttpUtils.urlEncode(url));
    }
}
