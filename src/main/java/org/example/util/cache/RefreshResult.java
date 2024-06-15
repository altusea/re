package org.example.util.cache;

import org.example.util.builder.CopyableBuilder;
import org.example.util.builder.ToCopyableBuilder;

import java.time.Instant;
import java.util.function.Supplier;

/**
 * A wrapper for the value returned by the {@link Supplier} underlying a {@link CachedSupplier}. The underlying {@link Supplier}
 * returns this to specify when the underlying value should be refreshed.
 */
public final class RefreshResult<T> implements ToCopyableBuilder<RefreshResult.Builder<T>, RefreshResult<T>> {

    private final T value;
    private final Instant staleTime;
    private final Instant prefetchTime;

    private RefreshResult(Builder<T> builder) {
        this.value = builder.value;
        this.staleTime = builder.staleTime;
        this.prefetchTime = builder.prefetchTime;
    }

    /**
     * Get a builder for creating a {@link RefreshResult}.
     *
     * @param value The value that should be cached by the supplier.
     */
    public static <T> Builder<T> builder(T value) {
        return new Builder<>(value);
    }

    /**
     * The value resulting from the refresh.
     */
    public T value() {
        return value;
    }

    /**
     * When the configured value is stale and should no longer be used. All threads will block until the value is updated.
     */
    public Instant staleTime() {
        return staleTime;
    }

    /**
     * When the configured value is getting close to stale and should be updated using the supplier's
     * {@link CachedSupplier#prefetchStrategy}.
     */
    public Instant prefetchTime() {
        return prefetchTime;
    }

    @Override
    public Builder<T> toBuilder() {
        return new Builder<>(this);
    }

    /**
     * A builder for a {@link RefreshResult}.
     */
    public static final class Builder<T> implements CopyableBuilder<Builder<T>, RefreshResult<T>> {
        private final T value;
        private Instant staleTime = Instant.MAX;
        private Instant prefetchTime = Instant.MAX;

        private Builder(T value) {
            this.value = value;
        }

        private Builder(RefreshResult<T> value) {
            this.value = value.value;
            this.staleTime = value.staleTime;
            this.prefetchTime = value.prefetchTime;
        }

        /**
         * Specify the time at which the value in this cache is stale, and all calls to {@link CachedSupplier#get()} should block
         * to try to update the value.
         * <p>
         * If this isn't specified, all threads will never block to update the value.
         */
        public Builder<T> staleTime(Instant staleTime) {
            this.staleTime = staleTime;
            return this;
        }

        /**
         * Specify the time at which a thread that calls {@link CachedSupplier#get()} should trigger a cache prefetch. The
         * exact behavior of a "prefetch" is defined when the cache is created with
         * {@link CachedSupplier.Builder#prefetchStrategy(CachedSupplier.PrefetchStrategy)}, and may either have one thread block
         * to refresh the cache or have an asynchronous task reload the value in the background.
         * <p>
         * If this isn't specified, the prefetch strategy will never be used and all threads will block to update the value when
         * the {@link #staleTime(Instant)} arrives.
         */
        public Builder<T> prefetchTime(Instant prefetchTime) {
            this.prefetchTime = prefetchTime;
            return this;
        }

        /**
         * Build a {@link RefreshResult} using the values currently configured in this builder.
         */
        public RefreshResult<T> build() {
            return new RefreshResult<>(this);
        }
    }
}
