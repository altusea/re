package org.example.playground;

import com.google.common.collect.Maps;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.Map;

public class CollectionTest {

    public static void main(String[] args) {
        Map<String, String> map = Maps.newTreeMap();
        map.put("zoo", "3");
        map.put("ball", "4");
        map.put("dog", "5");
        map.forEach((k, v) -> System.out.println(k + "=" + v));

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
