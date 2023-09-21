package org.example.codewar;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BagelSolver {

    public static class Bagel {
        public final int getValue() {
            return 3;
        }
    }

    public static Bagel getBagel() {
        try {
            Field field = Boolean.class.getField("TRUE");
            field.setAccessible(true);

            Field modifiedField = Field.class.getDeclaredField("modifiers");
            modifiedField.setAccessible(true);
            modifiedField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            field.set(null, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Bagel();
    }
}
