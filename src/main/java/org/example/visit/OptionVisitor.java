package org.example.visit;

public interface OptionVisitor<T, R> {

    R forSome(T v);

    R forNone();
}
