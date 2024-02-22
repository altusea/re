package org.example.util;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Unmodifiable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Convertors {

    @Unmodifiable
    public static <T, R> List<R> mapAsList(Collection<T> collection, Function<? super T, ? extends R> mapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().map(mapper).collect(Collectors.toUnmodifiableList());
    }

    public static <T, R> List<R> mapAsMutableList(Collection<T> collection, Function<? super T, ? extends R> mapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().map(mapper).collect(Collectors.toCollection(ArrayList::new));
    }

    public static void main(String[] args) {
        List<Character> characters = Lists.newArrayList('a', 'b', 'c');
        List<CharSequence> charSequences = mapAsList(characters, e -> StringUtils.repeat(e, 3));
        System.out.println(charSequences);
        List<String> strings = mapAsList(characters, e -> StringUtils.repeat(e, 3));
        System.out.println(strings);
    }
}
