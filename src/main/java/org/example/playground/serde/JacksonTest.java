package org.example.playground.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.example.util.JacksonObjectMapperFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JacksonTest {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = JacksonObjectMapperFactory.createJsonMapper();

        TimeHolder clazz = new TimeHolder();
        clazz.setLocalDate(LocalDate.now());
        clazz.setLocalDateTime(LocalDateTime.now());

        String jsonStr = objectMapper.writeValueAsString(clazz);
        System.out.println("1: " + jsonStr);
        TimeHolder fromJson = objectMapper.readValue(jsonStr, TimeHolder.class);
        System.out.println("2: " + fromJson);

        String jsonStr2 = "{\"localDate\":\"2024-01-26\",\"localDateTime\":\"\", \"extra\":\"xx\"}";
        TimeHolder fromJson2 = objectMapper.readValue(jsonStr2, TimeHolder.class);
        System.out.println("3: " + fromJson2);

        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("localDateTime", System.currentTimeMillis());
        TimeHolder fromJson3 = objectMapper.readValue(jsonObj.toString(), TimeHolder.class);
        System.out.println("4: " + fromJson3);

        System.out.println("=====================================================================");

        DataHolder dataHolder = new DataHolder();
        DataHolder.InnerClazz innerClazz = new DataHolder.InnerClazz();
        innerClazz.setFieldA("aaa");
        innerClazz.setFieldB("bbb");
        dataHolder.setField("ccc");
        dataHolder.setInnerClazz(innerClazz);
        String jsonStr3 = objectMapper.writeValueAsString(dataHolder);
        System.out.println("5: " + jsonStr3);
        DataHolder fromJson4 = objectMapper.readValue(jsonStr3, DataHolder.class);
        System.out.println("6: " + fromJson4);
        String jsonStr4 = "{\"field\":\"ccc\",\"innerClazz\":\"\"}";
        DataHolder fromJson5 = objectMapper.readValue(jsonStr4, DataHolder.class);
        System.out.println("7: " + fromJson5);
    }
}
