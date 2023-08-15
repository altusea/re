package org.example.playground;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add(null);
        a.add(null);
        System.out.println(a.size());
    }
}
