package org.example.visit.control;

public final class Some<T> extends Option<T> {

    private final T value;

    Some(T v) {
        value = v;
    }

    @Override
    public <R> R match(OptionVisitor<? super T, ? extends R> optionVisitor) {
        return optionVisitor.forSome(value);
    }
}
