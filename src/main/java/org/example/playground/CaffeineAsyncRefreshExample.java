package org.example.playground;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class CaffeineAsyncRefreshExample {

    public static void main(String[] args) {
        AsyncLoadingCache<String, String> cache = Caffeine.newBuilder()
                .refreshAfterWrite(Duration.ofSeconds(5))  // 设置刷新时间间隔
                .buildAsync(CaffeineAsyncRefreshExample::loadFromDataSource);  // 缓存项异步加载方法

        // 获取缓存值
        CompletableFuture<String> future = cache.get("key");
        future.thenAccept(value -> System.out.println("Cached Value: " + value));

        // 等待异步刷新完成
        try {
            Thread.sleep(Duration.ofSeconds(10));
        } catch (InterruptedException _) {
        }

        // 再次获取缓存值，触发异步刷新
        future = cache.get("key");
        future.thenAccept(value -> System.out.println("Refreshed Value: " + value));
    }

    // 模拟从数据源加载缓存项的方法
    private static String loadFromDataSource(String key) {
        System.out.println(Thread.currentThread() + " loading data from data source for key: " + key);
        // 模拟加载延迟
        try {
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException _) {
        }
        return key.toUpperCase();
    }
}
