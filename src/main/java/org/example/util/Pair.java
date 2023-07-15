package org.example.util;

import java.util.Objects;

public final class Pair<T1, T2> {

    public final T1 component1;
    public final T2 component2;

    public Pair(T1 component1, T2 component2) {
        this.component1 = component1;
        this.component2 = component2;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 component1, T2 component2) {
        return new Pair<>(component1, component2);
    }

    public T1 getComponent1() {
        return component1;
    }

    public T2 getComponent2() {
        return component2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pair<?, ?> pair)) {
            return false;
        }
        return Objects.equals(component1, pair.component1) && Objects.equals(component2, pair.component2);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(component1) * 31 + Objects.hashCode(component2);
    }

    @Override
    public String toString() {
        return "Pair[" + component1 + ", " + component2 + ']';
    }
}
