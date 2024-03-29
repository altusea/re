package org.example.playground;

import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.WildcardType;
import java.util.Collection;

public class TypeTest {

    public static void main(String[] args) {
        WildcardType b = TypeUtils.wildcardType().withUpperBounds(Collection.class).build();
        System.out.println(b);
    }
}
