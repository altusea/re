package org.example.playground;

import kala.value.LateInitValue;
import kala.value.LazyValue;

public class LazyTest {

    public static void main(String[] args) {
        LazyValue<String> lazyValue = LazyValue.of(() -> System.getProperty("os.name").toLowerCase());

        System.out.println(lazyValue.isReady());
        System.out.println(lazyValue.get());
        System.out.println(lazyValue.get().contains("windows"));
        System.out.println(lazyValue.isReady());

        LateInitValue<String> lateInitValue = new LateInitValue<>();
        System.out.println(lateInitValue.isInitialized());
        lateInitValue.initialize("sss");
        System.out.println(lateInitValue.isInitialized());
    }
}
