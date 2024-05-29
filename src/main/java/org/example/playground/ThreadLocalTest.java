package org.example.playground;

public class ThreadLocalTest {

    public static void main(String[] args) {
        var threadLocal = ThreadLocal.withInitial(() -> "foo");
        threadLocal.set("bar");
        System.out.println(threadLocal.get());
        threadLocal.remove();
        System.out.println(threadLocal.get());
    }
}
