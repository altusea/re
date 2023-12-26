package org.example.enhance;

public record Success<T>(T item) implements Try<T> {

    @Override
    public T get() throws Exception {
        return item;
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public boolean isErr() {
        return false;
    }

    @Override
    public <U> Try<U> map(Function<T, U> mapper) {
        try {
            return new Success<>(mapper.apply(item));
        } catch (Exception err) {
            return new Failure<>(err);
        }
    }

    @Override
    public <U> Try<U> flatMap(Function<? super T, Try<U>> mapper) {
        try {
            return mapper.apply(item);
        } catch (Exception e) {
            return Try.failure(e);
        }
    }
}
