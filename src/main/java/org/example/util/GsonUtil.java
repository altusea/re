package org.example.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GsonUtil {

    private static final Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new TypeAdapter<LocalDate>() {
                    @Override
                    public void write(final JsonWriter jsonWriter, final LocalDate localDate) throws IOException {
                        if (localDate == null) {
                            jsonWriter.nullValue();
                        } else {
                            jsonWriter.value(localDate.toString());
                        }
                    }

                    @Override
                    public LocalDate read(final JsonReader jsonReader) throws IOException {
                        if (jsonReader.peek() == JsonToken.NULL) {
                            jsonReader.nextNull();
                            return null;
                        } else {
                            return LocalDate.parse(jsonReader.nextString());
                        }
                    }
                })
                .registerTypeAdapter(LocalDateTime.class, new TypeAdapter<LocalDateTime>() {
                    @Override
                    public void write(final JsonWriter jsonWriter, final LocalDateTime localDateTime) throws IOException {
                        if (localDateTime == null) {
                            jsonWriter.nullValue();
                        } else {
                            jsonWriter.value(localDateTime.toString());
                        }
                    }

                    @Override
                    public LocalDateTime read(final JsonReader jsonReader) throws IOException {
                        if (jsonReader.peek() == JsonToken.NULL) {
                            jsonReader.nextNull();
                            return null;
                        } else {
                            return LocalDateTime.parse(jsonReader.nextString());
                        }
                    }
                });
        gson = builder.create();
    }

    public static <T> T fromJson(String jsonStr, Class<T> classOfT) {
        return gson.fromJson(jsonStr, classOfT);
    }

    public static <T> T fromJson(String jsonStr, TypeToken<T> typeOfT) {
        return gson.fromJson(jsonStr, typeOfT);
    }

    public static String fromObject(Object obj) {
        return gson.toJson(obj);
    }

    static class TestClazz {
        private LocalDate localDate;

        private LocalDateTime localDateTime;

        public LocalDate getLocalDate() {
            return localDate;
        }

        public void setLocalDate(LocalDate localDate) {
            this.localDate = localDate;
        }

        public LocalDateTime getLocalDateTime() {
            return localDateTime;
        }

        public void setLocalDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
        }

        @Override
        public String toString() {
            return "TestClazz{" +
                    "localDate=" + localDate +
                    ", localDateTime=" + localDateTime +
                    '}';
        }
    }

    public static void main(String[] args) {
        TestClazz clazz = new TestClazz();
        clazz.setLocalDate(LocalDate.now());
        clazz.setLocalDateTime(LocalDateTime.now());

        String jsonStr = fromObject(clazz);
        System.out.println(jsonStr);
        TestClazz fromJson = fromJson(jsonStr, TestClazz.class);
        System.out.println(fromJson);
    }
}
