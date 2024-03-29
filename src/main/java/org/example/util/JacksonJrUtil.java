package org.example.util;

import com.fasterxml.jackson.jr.ob.JSON;

import java.io.IOException;
import java.util.Map;

import static org.example.util.FunctionalUtils.invokeSafely;

public class JacksonJrUtil {

    public static String toJson(Object obj) {
        return invokeSafely(() -> JSON.std.asString(obj));
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return invokeSafely(() -> JSON.std.beanFrom(clazz, json));
    }

    public static void main(String[] args) throws IOException {
        String INPUT = "{\"a\":[1,2,{\"b\":true},3],\"c\":3}";
        Object ob = JSON.std.anyFrom(INPUT);
        System.out.println(ob.getClass());
        // or
        Map<String, Object> map = JSON.std.mapFrom(INPUT);
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
