package org.example.playground;

import org.apache.commons.lang3.function.FailableCallable;
import org.apache.commons.lang3.function.FailableRunnable;

public class ReRetryUtil {

    public static <R> R withTry(FailableCallable<R, ?> callable, final int maxTryTimes) {
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

    public static void withTry(FailableRunnable<?> runnable, final int maxTryTimes) {
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
