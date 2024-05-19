package org.example.playground;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionTest {

    @Test
    public void testTreeMap() {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("A", "B");
        treeMap.put("Z", "D");
        treeMap.put("C", "E");
        treeMap.put("Y", "F");
        treeMap.put("E", "F");
        assertEquals(treeMap.firstKey(), "A");
        assertEquals(treeMap.lastKey(), "Z");
    }

    @Test
    public void testListCircularFifoQueue() {
        CircularFifoQueue<String> circularFifoQueue = new CircularFifoQueue<>(3);
        circularFifoQueue.add("1");
        assertEquals(1, circularFifoQueue.size());
        circularFifoQueue.add("2");
        assertEquals(2, circularFifoQueue.size());
        circularFifoQueue.add("3");
        assertEquals(3, circularFifoQueue.size());
        circularFifoQueue.add("4");
        assertEquals(3, circularFifoQueue.size());
        circularFifoQueue.add("5");
        assertEquals(3, circularFifoQueue.size());
    }

    @Test
    public void test1() {
        var map = new HashMap<Integer, Integer>();
        var nums = List.of(2, 3, 4, 2, 3, 5, 1, 3, 4, 4);
        nums.forEach(num -> map.merge(num, 1, Integer::sum));
        assertEquals(5, map.size());
        assertEquals(map.get(4), 3);
    }

    public record Employee(String firstName,
                           String lastName,
                           String position,
                           int salary) {
    }

    @Test
    public void test2() {
        var s1 = List.of(
                new Employee("AAA", "BBB", "developer", 10000),
                new Employee("AAB", "BBC", "architect", 15000),
                new Employee("AAC", "BBD", "developer", 13000),
                new Employee("AAD", "BBE", "tester", 7000),
                new Employee("AAE", "BBF", "tester", 9000)
        );
        var map = new HashMap<String, Integer>();
        s1.forEach(emp -> map.merge(emp.position(), emp.salary(), Integer::sum));
        assertEquals(3, map.size());
        assertEquals(map.get("developer"), 23000);
    }
}
