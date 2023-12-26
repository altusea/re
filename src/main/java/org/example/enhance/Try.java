package org.example.enhance;

public sealed interface Try<T> permits Failure, Success {

    T get() throws Exception;

    boolean isOk();

    boolean isErr();

    static <T> Try<T> success(T t) {
        return new Success<>(t);
    }

    static <T> Try<T> failure(Exception err) {
        return new Failure<>(err);
    }

    static <T> Try<T> tryIt(Supplier<? extends T> supplier) {
        try {
            return Try.success(supplier.get());
        } catch (Exception err) {
            return Try.failure(err);
        }
    }

    <U> Try<U> map(Function<T, U> mapper);

    <U> Try<U> flatMap(Function<? super T, Try<U>> mapper);
}
