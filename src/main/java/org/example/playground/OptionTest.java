package org.example.playground;

import org.example.visit.control.Option;

public class OptionTest {

    public static void main(String[] args) {
        Option<String> some = Option.Some("aaa");
        System.out.println(some.getOrElse("bbb"));
        Option<String> none = Option.None();
        System.out.println(none.getOrElse("bbb"));
    }
}
