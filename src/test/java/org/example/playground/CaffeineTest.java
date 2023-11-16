package org.example.playground;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class CaffeineTest {

    Cache<String, Integer> cache = Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofMinutes(30))
            .build(s -> s.length() * 2);

    @Test
    void testLoader() {
        cache.put("hello", 3);
        Assertions.assertEquals(cache.getIfPresent("hello"), 3);
    }
}
