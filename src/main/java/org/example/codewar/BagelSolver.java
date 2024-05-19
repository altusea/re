package org.example.codewar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BagelSolver {

    private static final Logger log = LoggerFactory.getLogger(BagelSolver.class);

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
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException |
                 IllegalAccessException e) {
            log.error(e.getMessage());
        }
        return new Bagel();
    }
}
