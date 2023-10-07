package org.example.playground;

import kala.collection.mutable.MutableArrayList;
import kala.collection.mutable.MutableStack;
import kala.control.Either;
import kala.text.StringSlice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KalaTest {

    @Test
    void testMutableStack1() {
        MutableStack<String> stack = MutableStack.create();
        Assertions.assertInstanceOf(MutableArrayList.class, stack);
    }

    @Test
    void testMutableStack2() {
        MutableStack<String> stack = MutableStack.create();
        stack.push("hello");
        stack.push("world");
        Assertions.assertEquals("world", stack.pop());
    }

    @Test
    void testString() {
        var a = "hello, world";
        Assertions.assertTrue(StringSlice.of(a).contains(','));
    }

    @Test
    void testEither() {
        Either<String, Integer> a = Either.left("aaa");
        a.bifold(Either.left("bbb"), "default", String::concat, Integer::sum);
        Assertions.assertEquals("aaabbb", a.bifold(Either.left("bbb"), "default", String::concat, Integer::sum));
        Assertions.assertEquals("default", a.bifold(Either.right(42), "default", String::concat, Integer::sum));
    }
}
