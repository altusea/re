package org.example.playground;

import org.apache.commons.collections4.queue.CircularFifoQueue;

public class CollectionTest {

    public static void main(String[] args) {
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
