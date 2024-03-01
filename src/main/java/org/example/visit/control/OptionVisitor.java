package org.example.visit.control;

public interface OptionVisitor<T, R> {

    R forSome(T v);

    R forNone();
}
