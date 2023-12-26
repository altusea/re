package org.example.visit;

public abstract class Option<T> {

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

    public static void main(String[] args) {
        Option<String> some = Option.Some("aaa");
        System.out.println(some.getOrElse("bbb"));
        Option<String> none = Option.None();
        System.out.println(none.getOrElse("bbb"));
    }
}
