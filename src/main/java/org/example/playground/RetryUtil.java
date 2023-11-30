package org.example.playground;

import com.machinezoo.noexception.throwing.ThrowingRunnable;

import java.util.concurrent.Callable;

public class RetryUtil {

    public static <R> R withTry(Callable<R> callable, final int maxTryTimes) {
        int i = 0;
        final int canTryTimes = Math.max(maxTryTimes, 1);
        while (i < canTryTimes) {
            try {
                i++;
                return callable.call();
            } catch (Throwable e) {
                // do nothing
            }
        }
        throw new RuntimeException("failed after " + canTryTimes + "tries");
    }

    public static void withTry(ThrowingRunnable runnable, final int maxTryTimes) {
        int i = 0;
        final int canTryTimes = Math.max(maxTryTimes, 1);
        while (i < canTryTimes) {
            try {
                i++;
                runnable.run();
                return;
            } catch (Throwable e) {
                // do nothing
            }
        }
        throw new RuntimeException("failed after " + canTryTimes + "tries");
    }
}
