package org.example.enhance;

public interface Supplier<T> extends Triable<T> {

    T get() throws Exception;

    @Override
    default Try<T> collect() {
        try {
            return Try.success(get());
        } catch (Exception err) {
            return Try.failure(err);
        }
    }
}
