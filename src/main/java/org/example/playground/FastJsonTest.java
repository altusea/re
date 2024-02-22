package org.example.playground;

import com.alibaba.fastjson2.JSON;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class FastJsonTest {

    public static void main(String[] args) {
        TimeHolder timeHolder = new TimeHolder();
        timeHolder.setLocalDate(LocalDate.now());
        timeHolder.setLocalDateTime(LocalDateTime.now());

        String jsonStr = JSON.toJSONString(timeHolder);
        System.out.println(jsonStr);
        TimeHolder fromJson = JSON.parseObject(jsonStr, TimeHolder.class);
        System.out.println(fromJson);

        String jsonStr2 = "{\"localDate\":\"2024-01-26\",\"localDateTime\":\"\"}";
        TimeHolder fromJson2 = JSON.parseObject(jsonStr2, TimeHolder.class);
        System.out.println(fromJson2);

        DataHolder dataHolder = new DataHolder();
        DataHolder.InnerClazz innerClazz = new DataHolder.InnerClazz();
        innerClazz.setFieldA("aaa");
        innerClazz.setFieldB("bbb");
        dataHolder.setField("ccc");
        dataHolder.setInnerClazz(innerClazz);
        String jsonStr3 = JSON.toJSONString(dataHolder);
        System.out.println(jsonStr3);
        DataHolder fromJson3 = JSON.parseObject(jsonStr3, DataHolder.class);
        System.out.println(fromJson3);
        String jsonStr4 = "{\"field\":\"ccc\",\"innerClazz\":\"\"}";
        DataHolder fromJson4 = JSON.parseObject(jsonStr4, DataHolder.class);
        System.out.println(fromJson4);

        System.out.println();
        TimeHolder timeHolder2 = new TimeHolder();
        timeHolder2.setDate(new Date());
        System.out.println(JSON.toJSONString(timeHolder2));
        System.out.println(JSON.toJSONString(timeHolder2, "millis"));
        System.out.println(JSON.toJSONString(timeHolder2, "unixtime"));
    }
}
