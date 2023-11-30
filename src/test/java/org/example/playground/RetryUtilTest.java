package org.example.playground;

import com.machinezoo.noexception.throwing.ThrowingRunnable;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

public class RetryUtilTest {

    @Test
    void testRunnable() {
        ThrowingRunnable runnable = () -> System.out.println("something");
        Assertions.assertDoesNotThrow(() -> RetryUtil.withTry(runnable, 3));
    }

    @Test
    void testCallable() {
        Callable<Integer> callable = () -> 100;
        Assertions.assertEquals(RetryUtil.withTry(callable, 3), 100);
    }

    @Test
    void testThrowingRunnable() {
        ThrowingRunnable runnable = () -> {
            throw new NotImplementedException();
        };
        Assertions.assertThrows(RuntimeException.class, () -> RetryUtil.withTry(runnable, 3));
    }

    @Test
    void testThrowingCallable() {
        Callable<Integer> callable = () -> {
            throw new NotImplementedException();
        };
        Assertions.assertThrows(RuntimeException.class, () -> RetryUtil.withTry(callable, 3));
    }
}
