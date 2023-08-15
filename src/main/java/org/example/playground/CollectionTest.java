package org.example.playground;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * test EclipseCollection
 */
public class CollectionTest {

    static class Foo {

        String code;

        String name;

        public Foo(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return String.format("Foo[%s, %s]", code, name);
        }
    }

    public static void main(String[] args) {
        ImmutableList<Foo> immutableList = Lists.immutable.of(
                new Foo("a", "1"),
                new Foo("a", "2"),
                new Foo("a", "3"),

                new Foo("b", "1"),
                new Foo("b", "2"),
                new Foo("b", "3"),

                new Foo("c", "1"),
                new Foo("c", "2"),
                new Foo("c", "3"),

                new Foo("d", "1"),
                new Foo("d", "2"),
                new Foo("d", "3"));

        Map<String, String> res = immutableList.stream()
                .collect(Collectors.groupingBy(Foo::getCode, Collectors.mapping(Foo::getName, Collectors.joining(","))));

        for (Map.Entry<String, String> entry : res.entrySet()) {
            System.out.printf("%s : %s%n", entry.getKey(), entry.getValue());
        }

        ImmutableListMultimap<String, Foo> map = immutableList.groupBy(Foo::getCode);
        for (var k : map.keySet()) {
            System.out.println(k.getClass());
            System.out.println(map.get(k).getClass());
        }
    }
}
