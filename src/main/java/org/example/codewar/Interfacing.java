package org.example.codewar;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://www.codewars.com/kata/54c183dd3f069611c3000f97/train/java">done</a>
 */
public class Interfacing {

    public static Object create(Class<?>[] interfaces) {

        InvocationHandler handler = new InvocationHandler() {

            // A table to store field values
            private final Map<String, Object> fields = new HashMap<>();

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                String methodName = method.getName();
                String propertyName = methodName.substring(3);
                if (methodName.startsWith("get")) {
                    // Getter method was called
                    return fields.get(propertyName);
                } else if (method.getName().startsWith("set")) {
                    // Setter method was called
                    fields.put(propertyName, args[0]);
                    return null;
                } else {
                    // Something went horribly wrong
                    throw new UnsupportedOperationException();
                }
            }
        };

        return Proxy.newProxyInstance(interfaces[0].getClassLoader(), interfaces, handler);
    }

    interface MyDate {

        void setYear(int year);

        void setMonth(int month);

        int getYear();

        int getMonth();
    }

    interface MyTime {

        void setHour(int hour);

        int getHour();

        void setMinute(int minute);

        int getMinute();
    }

    public static void main(String[] args) {
        Object object = create(new Class[]{MyDate.class, MyTime.class});
        MyDate myDate = (MyDate) object;
        myDate.setYear(2017);
        myDate.setMonth(12);
        System.out.println(myDate.getYear());
        System.out.println(myDate.getMonth());
        MyTime myTime = (MyTime) object;
        myTime.setHour(12);
        myTime.setMinute(23);
        System.out.println(myTime.getHour());
        System.out.println(myTime.getMinute());
    }
}
