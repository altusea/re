package org.example.playground;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JacksonTest {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        // deal with empty string
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        TimeHolder clazz = new TimeHolder();
        clazz.setLocalDate(LocalDate.now());
        clazz.setLocalDateTime(LocalDateTime.now());

        String jsonStr = objectMapper.writeValueAsString(clazz);
        System.out.println(jsonStr);
        TimeHolder fromJson = objectMapper.readValue(jsonStr, TimeHolder.class);
        System.out.println(fromJson);

        String jsonStr2 = "{\"localDate\":\"2024-01-26\",\"localDateTime\":\"\"}";
        TimeHolder fromJson2 = objectMapper.readValue(jsonStr2, TimeHolder.class);
        System.out.println(fromJson2);

        DataHolder dataHolder = new DataHolder();
        DataHolder.InnerClazz innerClazz = new DataHolder.InnerClazz();
        innerClazz.setFieldA("aaa");
        innerClazz.setFieldB("bbb");
        dataHolder.setField("ccc");
        dataHolder.setInnerClazz(innerClazz);
        String jsonStr3 = objectMapper.writeValueAsString(dataHolder);
        System.out.println(jsonStr3);
        DataHolder fromJson3 = objectMapper.readValue(jsonStr3, DataHolder.class);
        System.out.println(fromJson3);
        String jsonStr4 = "{\"field\":\"ccc\",\"innerClazz\":\"\"}";
        DataHolder fromJson4 = objectMapper.readValue(jsonStr4, DataHolder.class);
        System.out.println(fromJson4);
    }
}
