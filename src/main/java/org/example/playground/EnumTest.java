package org.example.playground;

import org.apache.commons.lang3.EnumUtils;

import java.util.Map;

public class EnumTest {

    enum Color {

        RED("red"),
        GREEN("green"),
        BLUE("blue"),
        ;

        private final String code;

        Color(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public static void main(String[] args) {
        Map<String, Color> map = EnumUtils.getEnumMap(Color.class, Color::getCode);
        System.out.println(map.get(Color.RED.code));
    }
}
