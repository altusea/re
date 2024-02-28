package org.example.util;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class NumberUtil {

    public static List<List<Integer>> splitList(List<Integer> list, List<Double> ratios) {
        // 空列表处理
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("The input parameter [list] is empty.");
        }

        // 计算总元素数量
        int totalElements = list.size();

        // 计算每个子列表应该包含的元素数量
        IntList elementCounts = new IntArrayList();
        int remainingElements = totalElements;
        for (Double ratio : ratios) {
            int count = (int) (totalElements * ratio);
            elementCounts.add(count);
            remainingElements -= count;
        }

        if (remainingElements > 0) {
            if (CollectionUtils.isEmpty(elementCounts)) {
                elementCounts.add(remainingElements);
            } else {
                elementCounts.set(elementCounts.size() - 1, elementCounts.getLast() + remainingElements);
            }
        }

        // 分割列表
        List<List<Integer>> result = new ArrayList<>();
        int startIndex = 0;
        for (int count : elementCounts) {
            List<Integer> sublist = list.subList(startIndex, startIndex + count);
            result.add(sublist);
            startIndex += count;
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = IntStream.rangeClosed(1, 102).boxed().toList();

        List<Double> ratios = new ArrayList<>();
        ratios.add(0.4);
        ratios.add(0.3);
        ratios.add(0.2);
        ratios.add(0.1);

        List<List<Integer>> result = splitList(list, ratios);

        // 输出结果
        for (int i = 0; i < result.size(); i++) {
            System.out.println("List " + (i + 1) + ": " + result.get(i));
        }
    }

}
