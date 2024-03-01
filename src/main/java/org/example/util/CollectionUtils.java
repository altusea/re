package org.example.util;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import org.apache.commons.collections4.map.UnmodifiableMap;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class CollectionUtils {

    private CollectionUtils() {
    }

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    /**
     * Returns a new list containing the second list appended to the first list.
     */
    public static <T> List<T> mergeLists(List<T> list1, List<T> list2) {
        List<T> merged = new LinkedList<>();
        if (list1 != null) {
            merged.addAll(list1);
        }
        if (list2 != null) {
            merged.addAll(list2);
        }
        return merged;
    }

    /**
     * @param list List to get first element from.
     * @param <T>  Type of elements in the list.
     * @return The first element in the list if it exists. If the list is null or empty this will
     * return null.
     */
    public static <T> T firstIfPresent(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        } else {
            return list.getFirst();
        }
    }

    /**
     * Perform a deep copy of the provided map of lists. This only performs a deep copy of the map and lists. Entries are not
     * copied, so care should be taken to ensure that entries are immutable if preventing unwanted mutations of the elements is
     * desired.
     */
    public static <T, U> Map<T, List<U>> deepCopyMap(Map<T, ? extends List<U>> map) {
        return deepCopyMap(map, () -> new LinkedHashMap<>(map.size()));
    }

    /**
     * Perform a deep copy of the provided map of lists. This only performs a deep copy of the map and lists. Entries are not
     * copied, so care should be taken to ensure that entries are immutable if preventing unwanted mutations of the elements is
     * desired.
     */
    public static <T, U> Map<T, List<U>> deepCopyMap(Map<T, ? extends List<U>> map, Supplier<Map<T, List<U>>> mapConstructor) {
        Map<T, List<U>> result = mapConstructor.get();
        map.forEach((k, v) -> result.put(k, new ArrayList<>(v)));
        return result;
    }

    public static <T, U> Map<T, List<U>> unmodifiableMapOfLists(Map<T, List<U>> map) {
        return UnmodifiableMap.unmodifiableMap(map);
    }

    /**
     * Perform a deep copy of the provided map of lists, and make the result unmodifiable.
     * <p>
     * This is equivalent to calling {@link #deepCopyMap} followed by {@link #unmodifiableMapOfLists}.
     */
    public static <T, U> Map<T, List<U>> deepUnmodifiableMap(Map<T, ? extends List<U>> map) {
        return unmodifiableMapOfLists(deepCopyMap(map));
    }

    /**
     * Perform a deep copy of the provided map of lists, and make the result unmodifiable.
     * <p>
     * This is equivalent to calling {@link #deepCopyMap} followed by {@link #unmodifiableMapOfLists}.
     */
    public static <T, U> Map<T, List<U>> deepUnmodifiableMap(Map<T, ? extends List<U>> map,
                                                             Supplier<Map<T, List<U>>> mapConstructor) {
        return unmodifiableMapOfLists(deepCopyMap(map, mapConstructor));
    }


    /**
     * Collect a stream of {@link Map.Entry} to a {@link Map} with the same key/value types
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return a map
     */
    public static <K, V> Collector<Map.Entry<K, V>, ?, Map<K, V>> toMap() {
        return Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue);
    }

    /**
     * Transforms the values of a map to another map with the same keys, using the supplied function.
     *
     * @param inputMap the input map
     * @param mapper   the function used to transform the map values
     * @param <K>      the key type
     * @param <VInT>   the value type for the input map
     * @param <VOutT>  the value type for the output map
     * @return a map
     */
    public static <K, VInT, VOutT> Map<K, VOutT> mapValues(Map<K, VInT> inputMap, Function<VInT, VOutT> mapper) {
        return inputMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> mapper.apply(e.getValue())));
    }

    /**
     * Filters a map based on a condition
     *
     * @param map       the input map
     * @param condition the predicate to filter on
     * @param <K>       the key type
     * @param <V>       the value type
     * @return the filtered map
     */
    public static <K, V> Map<K, V> filterMap(Map<K, V> map, Predicate<Map.Entry<K, V>> condition) {
        return map.entrySet()
                .stream()
                .filter(condition)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Return a new map that is the inverse of the supplied map, with the values becoming the keys
     * and vice versa. Requires the values to be unique.
     *
     * @param inputMap a map where both the keys and values are unique
     * @param <K>      the key type
     * @param <V>      the value type
     * @return a map
     */
    public static <K, V> Map<K, V> inverseMap(Map<V, K> inputMap) {
        return inputMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    public static List<List<Integer>> splitList(List<Integer> list, List<Double> ratios) {
        // 空列表处理
        if (org.apache.commons.collections4.CollectionUtils.isEmpty(list)) {
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
            if (org.apache.commons.collections4.CollectionUtils.isEmpty(elementCounts)) {
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
