package org.example.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;
import java.util.Map;

import static org.example.util.FunctionalUtils.invokeSafely;

public class JacksonUtil {

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = JacksonObjectMapperFactory.create();
    }

    public static String toJson(Object value) {
        return invokeSafely(() -> OBJECT_MAPPER.writeValueAsString(value));
    }

    public static <T> T fromJson(String content, Class<T> valueType) {
        return invokeSafely(() -> OBJECT_MAPPER.readValue(content, valueType));
    }

    public static <T> T fromJson(String content, TypeReference<T> valueTypeRef) {
        return invokeSafely(() -> OBJECT_MAPPER.readValue(content, valueTypeRef));
    }

    public static <T> T fromJson(String content, JavaType valueType) {
        return invokeSafely(() -> OBJECT_MAPPER.readValue(content, valueType));
    }

    public static JavaType buildJavaTypeLinearly(Class<?>... classes) {
        if (ArrayUtils.isEmpty(classes)) {
            return null;
        }
        TypeFactory typeFactory = OBJECT_MAPPER.getTypeFactory();
        int n = classes.length;
        if (n == 1) {
            return typeFactory.constructType(classes[0]);
        } else if (n == 2) {
            return typeFactory.constructParametricType(classes[0], classes[1]);
        } else {
            int cur = n - 3;
            JavaType out = typeFactory.constructParametricType(classes[cur + 1], classes[cur + 2]);
            while (cur > -1) {
                var inner = out;
                out = typeFactory.constructParametricType(classes[cur--], inner);
            }
            return out;
        }
    }

    public static void main(String[] args) {
        System.out.println(buildJavaTypeLinearly());
        System.out.println(buildJavaTypeLinearly(String.class));
        System.out.println(buildJavaTypeLinearly(List.class, Integer.class));
        System.out.println(buildJavaTypeLinearly(List.class, List.class, String.class));

        TypeFactory typeFactory = OBJECT_MAPPER.getTypeFactory();
        System.out.println(typeFactory.constructMapType(Map.class, String.class, Integer.class));
    }
}
