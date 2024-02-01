package org.example.util;

import dev.failsafe.function.CheckedRunnable;
import dev.failsafe.function.CheckedSupplier;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RetryUtilTest {

    @Test
    void testRunnable() {
        CheckedRunnable runnable = () -> System.out.println("something");
        Assertions.assertDoesNotThrow(() -> RetryUtil.withTry(runnable, 3, null));
    }

    @Test
    void testCallable() {
        CheckedSupplier<Integer> callable = () -> 100;
        Assertions.assertEquals(RetryUtil.withTry(callable, 3, null), 100);
    }

    @Test
    void testThrowingRunnable() {
        CheckedRunnable runnable = () -> {
            throw new NotImplementedException();
        };
        Assertions.assertThrows(RuntimeException.class, () -> RetryUtil.withTry(runnable, 3, null));
    }

    @Test
    void testThrowingCallable() {
        CheckedSupplier<Integer> callable = () -> {
            throw new NotImplementedException();
        };
        Assertions.assertThrows(RuntimeException.class, () -> RetryUtil.withTry(callable, 3, null));
    }
}
