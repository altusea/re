package org.example.playground;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class CaffeineTest {

    public static void main(String[] args) {
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(3, TimeUnit.MINUTES)
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        System.out.println(cache.getIfPresent("key1"));
        System.out.println(cache.getIfPresent("key3"));
    }
}
