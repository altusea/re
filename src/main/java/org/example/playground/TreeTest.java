package org.example.playground;

import com.google.common.collect.Lists;
import kala.collection.mutable.MutableMap;
import org.apache.commons.compress.utils.Sets;
import org.apache.poi.ss.formula.functions.T;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TreeTest {

    @SuppressWarnings("unchecked")
    public static <T, Coll extends Collection<T>, E> void buildTreeGeneric(Collection<T> data,
                                                                           Function<T, E> idMapper,
                                                                           Function<T, E> parentMapper,
                                                                           BiConsumer<T, Coll> consumer,
                                                                           Class<Coll> childrenClass) {
        MutableMap<E, Coll> mutableMap = MutableMap.create();
        Supplier<Coll> collSupplier;
        if (Set.class.equals(childrenClass)) {
            collSupplier = () -> (Coll) Sets.newHashSet();
        } else if (List.class.equals(childrenClass) || Collection.class.equals(childrenClass)) {
            collSupplier = () -> (Coll) Lists.newArrayList();
        } else {
            throw new IllegalArgumentException("Unsupported children class" +  childrenClass);
        }
        for (T element : data) {
            E parentId = parentMapper.apply(element);
            if (parentId != null) {
                Coll coll = mutableMap.getOrPut(parentId, collSupplier);
                coll.add(element);
            }
        }
        for (T element : data) {
            E id = idMapper.apply(element);
            if (mutableMap.containsKey(id)) {
                consumer.accept(element, mutableMap.get(id));
            }
        }
    }

    public static void main() {
        Collection<Organization> organizationList = Lists.newArrayList(
                new Organization(0L, "A", null),
                new Organization(1L, "B", 0L),
                new Organization(2L, "C", 0L),
                new Organization(3L, "D", 1L)
        );

        buildTreeGeneric(organizationList, Organization::getId, Organization::getParentId, Organization::setChildren, List.class);
        organizationList.forEach(System.out::println);

        Collection<Organization2> organizationList2 = Lists.newArrayList(
                new Organization2(0L, "A", null),
                new Organization2(1L, "B", 0L),
                new Organization2(2L, "C", 0L),
                new Organization2(3L, "D", 1L)
        );

        buildTreeGeneric(organizationList2, Organization2::getId, Organization2::getParentId, Organization2::setChildren, Set.class);
        organizationList2.forEach(System.out::println);
    }
}
