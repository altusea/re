package org.example.playground.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CaffeineTest {

    public static void main(String[] args) throws InterruptedException {
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        System.out.println(cache.getIfPresent("key1"));
        Thread.sleep(Duration.ofSeconds(10));
        System.out.println(cache.getIfPresent("key2"));
    }
}
