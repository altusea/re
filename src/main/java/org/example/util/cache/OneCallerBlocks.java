package org.example.util.cache;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A {@link CachedSupplier.PrefetchStrategy} that will have one caller at a time block to update the value.
 * <p>
 * Multiple calls to {@link #prefetch(Runnable)} will result in only one caller actually performing the update, with the others
 * immediately returning.
 */
public class OneCallerBlocks implements CachedSupplier.PrefetchStrategy {

    /**
     * Whether we are currently refreshing the supplier. This is used to make sure only one caller is blocking at a time.
     */
    private final AtomicBoolean currentlyRefreshing = new AtomicBoolean(false);

    @Override
    public void prefetch(Runnable valueUpdater) {
        if (currentlyRefreshing.compareAndSet(false, true)) {
            try {
                valueUpdater.run();
            } finally {
                currentlyRefreshing.set(false);
            }
        }
    }
}
