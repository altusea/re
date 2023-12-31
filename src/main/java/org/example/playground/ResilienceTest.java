package org.example.playground;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.apache.commons.lang3.NotImplementedException;

import java.time.Duration;
import java.time.LocalDateTime;

public class ResilienceTest {

    public static void runWithException() {
        System.out.println("exec time: " + LocalDateTime.now());
        throw new NotImplementedException("not implemented ...");
    }

    public static void main(String[] args) {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofSeconds(1L))
                .retryExceptions(UnsupportedOperationException.class)
                .build();

        // Create a RetryRegistry with a custom global configuration
        Retry retry = Retry.of("0", config);
        Runnable retryRunnable = Retry.decorateRunnable(retry, ResilienceTest::runWithException);
        retryRunnable.run();
    }
}
