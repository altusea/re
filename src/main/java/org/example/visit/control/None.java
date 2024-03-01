package org.example.visit.control;

public final class None<T> extends Option<T> {

    @Override
    public <R> R match(OptionVisitor<? super T, ? extends R> optionVisitor) {
        return optionVisitor.forNone();
    }
}
