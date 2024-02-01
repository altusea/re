package org.example.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
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
import java.util.Date;

public class GsonUtil {

    private static final Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapter(Date.class, new TypeAdapter<Date>() {
                    @Override
                    public void write(JsonWriter jsonWriter, Date date) throws IOException {
                        if (date == null) {
                            jsonWriter.nullValue();
                        } else {
                            jsonWriter.value(DateUtil.format(date, DatePattern.NORM_DATETIME_FORMAT));
                        }
                    }

                    @Override
                    public Date read(JsonReader jsonReader) throws IOException {
                        if (jsonReader.peek() == JsonToken.NULL) {
                            jsonReader.nextNull();
                            return null;
                        } else {
                            return DateUtil.parse(jsonReader.nextString(), DatePattern.NORM_DATETIME_FORMAT);
                        }
                    }
                })
                .registerTypeAdapter(LocalDate.class, new TypeAdapter<LocalDate>() {
                    @Override
                    public void write(final JsonWriter jsonWriter, final LocalDate localDate) throws IOException {
                        if (localDate == null) {
                            jsonWriter.nullValue();
                        } else {
                            jsonWriter.value(LocalDateTimeUtil.formatNormal(localDate));
                        }
                    }

                    @Override
                    public LocalDate read(final JsonReader jsonReader) throws IOException {
                        if (jsonReader.peek() == JsonToken.NULL) {
                            jsonReader.nextNull();
                            return null;
                        } else {
                            return LocalDateTimeUtil.parseDate(jsonReader.nextString(), DatePattern.NORM_DATE_FORMATTER);
                        }
                    }
                })
                .registerTypeAdapter(LocalDateTime.class, new TypeAdapter<LocalDateTime>() {
                    @Override
                    public void write(final JsonWriter jsonWriter, final LocalDateTime localDateTime) throws IOException {
                        if (localDateTime == null) {
                            jsonWriter.nullValue();
                        } else {
                            jsonWriter.value(LocalDateTimeUtil.format(localDateTime, DatePattern.NORM_DATETIME_FORMATTER));
                        }
                    }

                    @Override
                    public LocalDateTime read(final JsonReader jsonReader) throws IOException {
                        if (jsonReader.peek() == JsonToken.NULL) {
                            jsonReader.nextNull();
                            return null;
                        } else {
                            return LocalDateTimeUtil.parse(jsonReader.nextString(), DatePattern.NORM_DATETIME_FORMATTER);
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

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

}
