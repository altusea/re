package org.example.visit.control;

public abstract sealed class Option<T> permits Some, None {

    public abstract <R> R match(OptionVisitor<? super T, ? extends R> optionVisitor);

    public static <T> Option<T> Some(T v) {
        if (v == null) {
            throw new NullPointerException();
        }
        return new Some<>(v);
    }

    public static <T> Option<T> None() {
        return new None<>();
    }

    public T getOrElse(T other) {
        return this.match(new OptionVisitor<>() {
            @Override
            public T forSome(T v) {
                return v;
            }

            @Override
            public T forNone() {
                return other;
            }
        });
    }

}
