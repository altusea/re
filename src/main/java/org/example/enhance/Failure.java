package org.example.enhance;

public record Failure<T>(Exception err) implements Try<T> {

    @Override
    public T get() throws Exception {
        throw err;
    }

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public boolean isErr() {
        return true;
    }

    @Override
    public <U> Try<U> map(Function<T, U> mapper) {
        return new Failure<>(err);
    }

    @Override
    public <U> Try<U> flatMap(Function<? super T, Try<U>> mapper) {
        return new Failure<>(err);
    }
}
