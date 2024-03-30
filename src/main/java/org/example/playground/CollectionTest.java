package org.example.playground;

import cn.hutool.core.map.MapUtil;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.Map;

public class CollectionTest {

    public static void main(String[] args) {
        Map<String, Integer> map = MapUtil.newHashMap();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        var partitionList = MapUtil.partition(map, 2);
        partitionList.forEach(System.out::println);

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
