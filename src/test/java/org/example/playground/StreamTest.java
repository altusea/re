package org.example.playground;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StreamTest {

    @Test
    public void test1() {
        // unmodifiable list
        var l = Stream.of(null, "Green", "Yellow").toList();
        assertEquals(3, l.size());
        assertThrows(UnsupportedOperationException.class, () -> l.add("Red"));
        assertThrows(UnsupportedOperationException.class, () -> l.set(0, "Red"));
    }

    @Test
    public void test2() {
        assertThrows(NullPointerException.class, () -> Stream.of(null, "Green", "Yellow").toList());
    }

    record Employee(String firstName,
                    String lastName,
                    String position,
                    int salary) {
    }

    @Test
    public void test3() {
        Stream<Employee> s1 = Stream.of(
                new Employee("AAA", "BBB", "developer", 10000),
                new Employee("AAB", "BBC", "architect", 15000),
                new Employee("AAC", "BBD", "developer", 13000),
                new Employee("AAD", "BBE", "tester", 7000),
                new Employee("AAE", "BBF", "tester", 9000)
        );

        var m = s1.collect(Collectors.groupingBy(Employee::position,
                Collectors.summingInt(Employee::salary)));
        assertEquals(3, m.size());
        assertEquals(m.get("developer"), 23000);
        assertEquals(m.get("architect"), 15000);
        assertEquals(m.get("tester"), 16000);
    }

    @Test
    public void test4() {
        Stream<Employee> s1 = Stream.of(
                new Employee("AAA", "BBB", "developer", 10000),
                new Employee("AAB", "BBC", "architect", 15000),
                new Employee("AAC", "BBD", "developer", 13000),
                new Employee("AAD", "BBE", "tester", 7000),
                new Employee("AAE", "BBF", "tester", 9000)
        );

        var m = s1.collect(Collectors.partitioningBy(emp -> emp.salary() > 10000));
        assertEquals(2, m.size());
        assertEquals(m.get(true).size(), 2);
        assertEquals(m.get(false).size(), 3);
    }

}
