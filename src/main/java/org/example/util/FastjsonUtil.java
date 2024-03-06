package org.example.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

import java.lang.reflect.Type;
import java.util.HashMap;

public class FastjsonUtil {

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T fromJson(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    public static <T> T fromJson(String jsonStr, TypeReference<T> typeReference) {
        return JSON.parseObject(jsonStr, typeReference);
    }

    public static <T> T fromJson(String jsonStr, Type type) {
        return JSON.parseObject(jsonStr, type);
    }

    public static void main(String[] args) {
        Type type = TypeReference.mapType(HashMap.class, String.class, Object.class);
        System.out.println(type);
    }
}
