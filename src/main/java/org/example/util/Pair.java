package org.example.util;

import java.util.Objects;

public record Pair<T1, T2>(T1 component1, T2 component2) {

    public static <T1, T2> Pair<T1, T2> of(T1 component1, T2 component2) {
        return new Pair<>(component1, component2);
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
    public String toString() {
        return "Pair[" + component1 + ", " + component2 + ']';
    }
}
