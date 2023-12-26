package org.example.collection;

import java.util.function.Function;

public final class MonadWriter {

    static class Monad<T> {

        public static <T> Monad<T> unit(T aValue, String aText) {
            return new Monad<>(aValue, aText);
        }

        public Monad<T> bind(Function<T, Monad<T>> aFunction) {
            Monad<T> monad = aFunction.apply(value);
            monad.text = text + monad.text;
            return monad;
        }

        public T getValue() {
            return value;
        }

        public String getText() {
            return text;
        }

        private Monad(T aValue, String aText) {
            value = aValue;
            text = String.format("%-21s%s%n", "    " + aText, ": " + aValue);
        }

        private final T value;
        private String text;

    }

    private static Monad<Double> root(double aD) {
        return Monad.unit(Math.sqrt(aD), "Took square root");
    }

    private static Monad<Double> addOne(double aD) {
        return Monad.unit(aD + 1.0, "Added one");
    }

    private static Monad<Double> half(double aD) {
        return Monad.unit(aD / 2.0, "Divided by two");
    }

    public static void main(String[] aArgs) {
        Monad<Double> initial = Monad.unit(5.0, "Initial value");
        Monad<Double> result = initial.bind(MonadWriter::root).bind(MonadWriter::addOne).bind(MonadWriter::half);
        System.out.println("The Golden Ratio is " + result.getValue() + System.lineSeparator());
        System.out.println("This was derived as follows:" + System.lineSeparator() + result.getText());

        System.out.println("===========================================\n");

        Monad<Double> initial1 = Monad.unit(31.0, "Initial value");
        Monad<Double> result1 = initial1.bind(MonadWriter::addOne).bind(MonadWriter::half).bind(MonadWriter::root);
        System.out.println("The Golden Ratio is " + result1.getValue() + System.lineSeparator());
        System.out.println("This was derived as follows:" + System.lineSeparator() + result1.getText());
    }
}
