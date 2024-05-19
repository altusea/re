package org.example.visit.control;

public sealed interface Option<T> permits Some, None {

    <R> R match(OptionVisitor<? super T, ? extends R> optionVisitor);

    static <T> Option<T> Some(T v) {
        if (v == null) {
            throw new NullPointerException();
        }
        return new Some<>(v);
    }

    static <T> Option<T> None() {
        return new None<>();
    }

    default T getOrElse(T other) {
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

    default T getOrElse2(T other) {
        if (this instanceof Some(T v)) {
            return v;
        }
        return other;
    }

}
