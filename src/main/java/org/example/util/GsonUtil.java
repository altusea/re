package org.example.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.util.internal.gson.DateTypeAdapter;
import org.example.util.internal.gson.LocalDateTimeTypeAdapter;
import org.example.util.internal.gson.LocalDateTypeAdapter;
import org.example.util.internal.gson.YearMonthTypeAdapter;

public class GsonUtil {

    private static final Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapterFactory(DateTypeAdapter.FACTORY)
                .registerTypeAdapterFactory(LocalDateTypeAdapter.FACTORY)
                .registerTypeAdapterFactory(LocalDateTimeTypeAdapter.FACTORY)
                .registerTypeAdapterFactory(YearMonthTypeAdapter.FACTORY);
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
