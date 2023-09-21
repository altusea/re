package org.example.codewar;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static kala.Conditions.assertEquals;

/**
 * <a href="https://www.codewars.com/kata/54c183dd3f069611c3000f97/train/java">source</a>
 */
public class Interfacing {

    public interface Date {

        Integer getYear();

        void setYear(Integer i);

        Integer getMonth();

        void setMonth(Integer i);

    }

    public static Object create(Class<?>[] interfaces) {

        InvocationHandler handler = new InvocationHandler() {

            final Map<String, Object> map = new HashMap<>();

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                String variableName = methodName.substring(3);
                if (methodName.startsWith("set")) {
                    map.put(variableName, args[0]);
                    return args[0];
                } else if (methodName.startsWith("get")) {
                    return map.get(variableName);
                }
                return null;
            }
        };

        Object o = Proxy.newProxyInstance(String.class.getClassLoader(), interfaces, handler);
        return o;
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Random random = new Random();
        Class<?>[] interfaces = {Date.class};
        Object o = create(interfaces);
        for (Class<?> clazz : interfaces) {
            for (Method method : clazz.getMethods()) {
                String name = method.getName();
                if (name.startsWith("set")) {
                    // Get the corresponding getter
                    String getterName = "get" + name.substring(3);
                    Method getter = clazz.getMethod(getterName);

                    int number = random.nextInt();
                    method.invoke(o, number);

                    // Check that we got the right value
                    assertEquals(number, getter.invoke(o));
                }
            }
        }
    }
}
