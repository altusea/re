package org.example.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class MonadList {

    static class Monad<T> {

        public static <T> Monad<T> unit(List<T> aList) {
            return new Monad<>(aList);
        }

        public <U> Monad<U> bind(Function<List<T>, Monad<U>> aFunction) {
            return aFunction.apply(list);
        }

        public List<T> getValue() {
            return list;
        }

        private Monad(List<T> aList) {
            list = new ArrayList<>(aList);
        }

        private final List<T> list;

    }

    private static Monad<Integer> doubler(List<Integer> aList) {
        return Monad.unit(aList.stream().map(i -> 2 * i).toList());
    }

    private static Monad<String> letters(List<Integer> aList) {
        return Monad.unit(aList.stream().map(i -> Character.toString((char) (64 + i)).repeat(i)).toList());
    }

    public static void main(String[] aArgs) {
        Monad<Integer> integers = Monad.unit(List.of(2, 3, 4));
        Monad<String> strings = integers.bind(MonadList::doubler).bind(MonadList::letters);
        System.out.println(strings.getValue());
    }
}
