package org.example.playground;

import cn.hutool.core.map.MapUtil;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.Map;

public class CollectionTest {

    public static void main(String[] args) {
        Map<String, String> map1 = Maps.newTreeMap();
        map1.put("zoo", "3");
        map1.put("ball", "4");
        map1.put("dog", "5");
        map1.forEach((k, v) -> System.out.println(k + "=" + v));

        Map<String, Integer> map2 = MapUtil.newHashMap();
        map2.put("a", 1);
        map2.put("b", 2);
        map2.put("c", 3);
        map2.put("d", 4);
        var partitionList = MapUtil.partition(map2, 2);
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
