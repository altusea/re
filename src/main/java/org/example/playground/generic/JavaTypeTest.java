package org.example.playground.generic;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.example.util.JacksonObjectMapperFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JavaTypeTest {

    public static void main(String[] args) {
        TypeFactory typeFactory1 = TypeFactory.defaultInstance();
        TypeFactory typeFactory2 = JacksonObjectMapperFactory.create().getTypeFactory();
        System.out.println("typeFactory1 == typeFactory2: " + (typeFactory1 == typeFactory2));

        JavaType javaType = typeFactory1.constructParametricType(List.class, String.class);
        System.out.println(javaType);

        JavaType javaType2 = typeFactory1.constructParametricType(Map.class, String.class, Integer.class);
        System.out.println(javaType2);

        JavaType inner = typeFactory1.constructParametricType(Set.class, Integer.class);
        JavaType javaType3 = typeFactory1.constructParametricType(List.class, inner);
        System.out.println(javaType3);
    }
}
