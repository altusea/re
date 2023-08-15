package org.example.codewar;

import java.lang.reflect.Method;

/**
 * <a href="https://www.codewars.com/kata/54c183dd3f069611c3000f97/train/java">source</a>
 */
public class Interfacing {

    public static Object create(Class<?>[] interfaces) {

        Object object = new Object();
        for (Class<?> clazz : interfaces) {
            for (Method method : clazz.getMethods()) {
                String methodName = method.getName();
                if (methodName.startsWith("get")) {
                    String variableName = methodName.substring(3);
                    Class<?> returnType = method.getReturnType();


                }
            }
        }

        // todo
        return object;
    }
}
