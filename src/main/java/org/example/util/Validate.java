package org.example.util;

import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class Validate {

    private static final String DEFAULT_IS_NULL_EX_MESSAGE = "The validated object is null";

    private Validate() {
    }

    public static void isTrue(final boolean expression, final String message, final Object... values) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, values));
        }
    }

    public static void isFalse(final boolean expression, final String message, final Object... values) {
        isTrue(!expression, message, values);
    }

    public static <T> T notNull(final T object, final String message, final Object... values) {
        if (object == null) {
            throw new NullPointerException(String.format(message, values));
        }
        return object;
    }

    public static <T> void isNull(final T object, final String message, final Object... values) {
        if (object != null) {
            throw new IllegalArgumentException(String.format(message, values));
        }
    }

    public static <T> T paramNotNull(final T object, final String paramName) {
        if (object == null) {
            throw new NullPointerException(String.format("%s must not be null.", paramName));
        }
        return object;
    }

    public static <T extends CharSequence> T paramNotBlank(final T chars, final String paramName) {
        if (chars == null) {
            throw new NullPointerException(String.format("%s must not be null.", paramName));
        }
        if (StringUtils.isBlank(chars)) {
            throw new IllegalArgumentException(String.format("%s must not be blank or empty.", paramName));
        }
        return chars;
    }

    public static <T> T validState(final T object, final Predicate<T> test, final String message, final Object... values) {
        if (!test.test(object)) {
            throw new IllegalStateException(String.format(message, values));
        }
        return object;
    }

    public static <T> T[] notEmpty(final T[] array, final String message, final Object... values) {
        if (array == null) {
            throw new NullPointerException(String.format(message, values));
        }
        if (array.length == 0) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return array;
    }

    public static <T extends Collection<?>> T notEmpty(final T collection, final String message, final Object... values) {
        if (collection == null) {
            throw new NullPointerException(String.format(message, values));
        }
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return collection;
    }

    public static <T extends Map<?, ?>> T notEmpty(final T map, final String message, final Object... values) {
        if (map == null) {
            throw new NullPointerException(String.format(message, values));
        }
        if (map.isEmpty()) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return map;
    }

    public static <T extends CharSequence> T notEmpty(final T chars, final String message, final Object... values) {
        if (chars == null) {
            throw new NullPointerException(String.format(message, values));
        }
        if (chars.isEmpty()) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return chars;
    }

    public static <T extends CharSequence> T notBlank(final T chars, final String message, final Object... values) {
        if (chars == null) {
            throw new NullPointerException(String.format(message, values));
        }
        if (StringUtils.isBlank(chars)) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return chars;
    }

    public static <T> T[] noNullElements(final T[] array, final String message, final Object... values) {
        Validate.notNull(array, message);
        for (T anArray : array) {
            if (anArray == null) {
                throw new IllegalArgumentException(String.format(message, values));
            }
        }
        return array;
    }

    public static <T extends Iterable<?>> T noNullElements(final T iterable, final String message, final Object... values) {
        Validate.notNull(iterable, DEFAULT_IS_NULL_EX_MESSAGE);
        for (Object o : iterable) {
            if (o == null) {
                throw new IllegalArgumentException(String.format(message, values));
            }
        }
        return iterable;
    }

    public static void validState(final boolean expression, final String message, final Object... values) {
        if (!expression) {
            throw new IllegalStateException(String.format(message, values));
        }
    }

    public static <T extends Comparable<U>, U> T inclusiveBetween(final U start, final U end, final T value,
                                                                  final String message, final Object... values) {
        if (value.compareTo(start) < 0 || value.compareTo(end) > 0) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return value;
    }

    public static long inclusiveBetween(final long start, final long end, final long value, final String message) {
        if (value < start || value > end) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public static double inclusiveBetween(final double start, final double end, final double value, final String message) {
        if (value < start || value > end) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public static <T extends Comparable<U>, U> T exclusiveBetween(final U start, final U end, final T value,
                                                                  final String message, final Object... values) {
        if (value.compareTo(start) <= 0 || value.compareTo(end) >= 0) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return value;
    }

    public static long exclusiveBetween(final long start, final long end, final long value, final String message) {
        if (value <= start || value >= end) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public static double exclusiveBetween(final double start, final double end, final double value, final String message) {
        if (value <= start || value >= end) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public static <T, U> U isInstanceOf(final Class<U> type, final T obj, final String message, final Object... values) {
        if (!type.isInstance(obj)) {
            throw new IllegalArgumentException(String.format(message, values));
        }
        return type.cast(obj);
    }

    public static int isPositive(int num, String fieldName) {
        if (num <= 0) {
            throw new IllegalArgumentException(String.format("%s must be positive", fieldName));
        }
        return num;
    }

    public static long isPositive(long num, String fieldName) {
        if (num <= 0) {
            throw new IllegalArgumentException(String.format("%s must be positive", fieldName));
        }
        return num;
    }

    public static double isPositive(double num, String fieldName) {
        if (num <= 0) {
            throw new IllegalArgumentException(String.format("%s must be positive", fieldName));
        }
        return num;
    }

    public static int isNotNegative(int num, String fieldName) {

        if (num < 0) {
            throw new IllegalArgumentException(String.format("%s must not be negative", fieldName));
        }

        return num;
    }

    public static Long isNotNegativeOrNull(Long num, String fieldName) {

        if (num == null) {
            return null;
        }

        if (num < 0) {
            throw new IllegalArgumentException(String.format("%s must not be negative", fieldName));
        }

        return num;
    }

    public static long isNotNegative(long num, String fieldName) {

        if (num < 0) {
            throw new IllegalArgumentException(String.format("%s must not be negative", fieldName));
        }

        return num;
    }

    public static Duration isPositive(Duration duration, String fieldName) {
        if (duration == null) {
            throw new IllegalArgumentException(String.format("%s cannot be null", fieldName));
        }

        if (duration.isNegative() || duration.isZero()) {
            throw new IllegalArgumentException(String.format("%s must be positive", fieldName));
        }
        return duration;
    }

    public static Duration isPositiveOrNull(Duration duration, String fieldName) {
        if (duration == null) {
            return null;
        }

        return isPositive(duration, fieldName);
    }

    public static Integer isPositiveOrNull(Integer num, String fieldName) {
        if (num == null) {
            return null;
        }

        return isPositive(num, fieldName);
    }

    public static Double isPositiveOrNull(Double num, String fieldName) {
        if (num == null) {
            return null;
        }

        return isPositive(num, fieldName);
    }

    public static Long isPositiveOrNull(Long num, String fieldName) {
        if (num == null) {
            return null;
        }

        return isPositive(num, fieldName);
    }

    public static Duration isNotNegative(Duration duration, String fieldName) {
        if (duration == null) {
            throw new IllegalArgumentException(String.format("%s cannot be null", fieldName));
        }

        if (duration.isNegative()) {
            throw new IllegalArgumentException(String.format("%s must not be negative", fieldName));
        }

        return duration;
    }

    public static <T> T getOrDefault(T param, Supplier<T> defaultValue) {
        paramNotNull(defaultValue, "defaultValue");
        return param != null ? param : defaultValue.get();
    }

    /**
     * Verify that only one of the objects is non-null. If all objects are null this method
     * does not throw.
     *
     * @param message Error message if more than one object is non-null.
     * @param objs    Objects to validate.
     * @throws IllegalArgumentException if more than one of the objects was non-null.
     */
    public static void mutuallyExclusive(String message, Object... objs) {
        boolean oneProvided = false;
        for (Object o : objs) {
            if (o != null) {
                if (oneProvided) {
                    throw new IllegalArgumentException(message);
                } else {
                    oneProvided = true;
                }
            }
        }
    }
}
