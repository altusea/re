package org.example.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.machinezoo.noexception.Exceptions;

public class JacksonUtil {

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = JacksonObjectMapperFactory.create();
    }

    public static <T> T fromJson(String jsonStr, Class<T> valueType) {
        return Exceptions.sneak().get(() -> OBJECT_MAPPER.readValue(jsonStr, valueType));
    }

    public static <T> T fromJson(String jsonStr, TypeReference<T> valueTypeRef) {
        return Exceptions.sneak().get(() -> OBJECT_MAPPER.readValue(jsonStr, valueTypeRef));
    }
}
