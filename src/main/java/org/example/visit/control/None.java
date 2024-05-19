package org.example.visit.control;

public record None<T>() implements Option<T> {

    @Override
    public <R> R match(OptionVisitor<? super T, ? extends R> optionVisitor) {
        return optionVisitor.forNone();
    }
}
