package org.example.playground;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ClassTest {

    public static void main() {
        System.out.println(Set.class.isAssignableFrom(HashSet.class));
        System.out.println(HashSet.class.isAssignableFrom(Set.class));

        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getPlatformClassLoader());

        System.out.println(Date.class.getClassLoader());
        System.out.println(Car.class.getClassLoader());
    }
}
