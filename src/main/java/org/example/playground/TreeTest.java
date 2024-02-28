package org.example.playground;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import kala.collection.mutable.MutableMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TreeTest {

    public static <T, E> void buildTree(Collection<T> data,
                                        Function<T, E> idMapper,
                                        Function<T, E> parentMapper,
                                        BiConsumer<T, List<T>> consumer) {
        ArrayListValuedHashMap<E, T> multiValuedMap = new ArrayListValuedHashMap<>();
        for (T element : data) {
            E parentId = parentMapper.apply(element);
            if (parentId != null) {
                multiValuedMap.put(parentId, element);
            }
        }
        for (T element : data) {
            E id = idMapper.apply(element);
            if (multiValuedMap.containsKey(id)) {
                consumer.accept(element, multiValuedMap.get(id));
            }
        }
    }

    public static <T, E, Coll extends Collection<T>> void buildTreeGeneric(Collection<T> data,
                                                                           Function<T, E> idMapper,
                                                                           Function<T, E> parentMapper,
                                                                           BiConsumer<T, Coll> consumer,
                                                                           TypeToken<Coll> typeToken) {
        MutableMap<E, Coll> mutableMap = MutableMap.create();
        Supplier<Coll> supplier;
        if (typeToken.getRawType() == HashSet.class || typeToken.getRawType() == Set.class) {
            supplier = () -> (Coll) new HashSet<>();
        } else if (typeToken.getRawType() == ArrayList.class || typeToken.getRawType() == List.class || typeToken.getRawType() == Collection.class) {
            supplier = () -> (Coll) new ArrayList<>();
        } else {
            throw new RuntimeException("Unsupported type: " + typeToken.getRawType());
        }

        for (T element : data) {
            E parentId = parentMapper.apply(element);
            if (parentId != null) {
                mutableMap.getOrPut(parentId, supplier).add(element);
            }
        }
        for (T element : data) {
            E id = idMapper.apply(element);
            if (mutableMap.containsKey(id)) {
                consumer.accept(element, mutableMap.get(id));
            }
        }
    }

    public static void main(String[] args) {
        Collection<Organization> organizationList = Lists.newArrayList(
                new Organization(0L, "A", null),
                new Organization(1L, "B", 0L),
                new Organization(2L, "C", 0L),
                new Organization(3L, "D", 1L)
        );

        buildTree(organizationList, Organization::getId, Organization::getParentId, Organization::setChildren);
        organizationList.forEach(System.out::println);

        Collection<Organization> organizationList2 = Lists.newArrayList(
                new Organization(0L, "A", null),
                new Organization(1L, "B", 0L),
                new Organization(2L, "C", 0L),
                new Organization(3L, "D", 1L)
        );

        buildTreeGeneric(organizationList2, Organization::getId, Organization::getParentId, Organization::setChildren,
                new TypeToken<>() {
                });
        organizationList2.forEach(System.out::println);
    }
}
