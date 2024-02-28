package org.example.playground;

import com.google.common.reflect.TypeToken;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassTest {

    public static void main(String[] args) {
        System.out.println(Set.class.isAssignableFrom(HashSet.class));
        System.out.println(HashSet.class.isAssignableFrom(Set.class));

        var token = new TypeToken<List<String>>() {
        };
        System.out.println(token.getRawType());
        System.out.println(token.isSubtypeOf(List.class));
    }
}
