package org.example.util;

import java.util.function.Supplier;

public class Lazy<T> implements AutoCloseable {

    private final Supplier<T> initializer;

    private volatile T value;

    public Lazy(Supplier<T> initializer) {
        this.initializer = initializer;
    }

    public static <T> Lazy<T> withValue(T initialValue) {
        return new ResolvedLazy<>(initialValue);
    }

    public boolean hasValue() {
        return value != null;
    }

    public T getValue() {
        T result = value;
        if (result == null) {
            synchronized (this) {
                result = value;
                if (result == null) {
                    result = initializer.get();
                    value = result;
                }
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "Lazy{" + "value=" + (value == null ? "Uninitialized" : value) + "}";
    }

    @Override
    public void close() {
        try {
            // Make sure the value has been initialized before we attempt to close it
            getValue();
        } catch (RuntimeException e) {
            // Failed to initialize the value.
        }

        IoUtils.closeIfCloseable(initializer, null);
        IoUtils.closeIfCloseable(value, null);
    }

    private static class ResolvedLazy<T> extends Lazy<T> {
        private final T initialValue;

        private ResolvedLazy(T initialValue) {
            super(null);
            this.initialValue = initialValue;
        }

        @Override
        public boolean hasValue() {
            return true;
        }

        @Override
        public T getValue() {
            return initialValue;
        }

        @Override
        public String toString() {
            return "Lazy{" + "value=" + initialValue + "}";
        }

        @Override
        public void close() {
            IoUtils.closeIfCloseable(initialValue, null);
        }
    }
}
