package org.example.visit.control;

public record Some<T>(T value) implements Option<T> {

    @Override
    public <R> R match(OptionVisitor<? super T, ? extends R> optionVisitor) {
        return optionVisitor.forSome(value);
    }
}
