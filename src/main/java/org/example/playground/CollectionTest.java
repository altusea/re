package org.example.playground;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.TreeMap;

public class CollectionTest {

    public static void main(String[] args) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("A", "B");
        treeMap.put("Z", "D");
        treeMap.put("C", "E");
        treeMap.put("Y", "F");
        treeMap.put("E", "F");
        treeMap.forEach((k, v) -> System.out.println(k + ": " + v));

        CircularFifoQueue<String> circularFifoQueue = new CircularFifoQueue<>(3);
        circularFifoQueue.add("1");
        circularFifoQueue.add("2");
        circularFifoQueue.add("3");
        System.out.println(circularFifoQueue);
        circularFifoQueue.add("4");
        System.out.println(circularFifoQueue);
        circularFifoQueue.add("5");
        System.out.println(circularFifoQueue);
    }
}
