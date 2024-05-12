package org.example.collection;

import com.google.errorprone.annotations.Immutable;
import org.example.util.Validate;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Immutable
public final class ExtNumber extends Number implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Number numberValue;
    private final String stringValue;

    /**
     * @param value Number value as passed in the from-Constructor.
     * @see #fromBigDecimal(BigDecimal)
     * @see #fromBigInteger(BigInteger)
     * @see #fromDouble(double)
     * @see #fromFloat(float)
     * @see #fromLong(long)
     * @see #fromShort(short)
     * @see #fromInteger(int)
     */
    private ExtNumber(Number value) {
        this.numberValue = value;
        this.stringValue = null;
    }

    /**
     * .
     *
     * @param stringValue String value.
     * @see #fromString(String)
     */
    private ExtNumber(String stringValue) {
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    private static boolean isNumberValueNaN(Number numberValue) {
        return (numberValue instanceof Double && Double.isNaN((double) numberValue)) ||
                (numberValue instanceof Float && Float.isNaN((float) numberValue));
    }

    private static boolean isNumberValueInfinite(Number numberValue) {
        return (numberValue instanceof Double && Double.isInfinite((double) numberValue)) ||
                (numberValue instanceof Float && Float.isInfinite((float) numberValue));
    }

    private static Number valueOf(Number numberValue) {
        Number valueOfInfiniteOrNaN = valueOfInfiniteOrNaN(numberValue);
        return valueOfInfiniteOrNaN != null ? valueOfInfiniteOrNaN : valueInBigDecimal(numberValue);
    }

    private static Number valueOfInfiniteOrNaN(Number numberValue) {
        if (numberValue instanceof Double
                && (Double.isInfinite((double) numberValue) || Double.isNaN((double) numberValue))) {
            return numberValue.doubleValue();
        } else if ((numberValue instanceof Float && (Float.isInfinite((float) numberValue) || Float.isNaN((float) numberValue)))) {
            return numberValue.floatValue();
        } else {
            return null;
        }
    }

    /**
     * This function converts a given number to BigDecimal Number where the caller can convert to a primitive number.
     * This is done to keep the precision.
     *
     * @param numberValue The number value.
     * @return Big Decimal value for the given number.
     */
    private static BigDecimal valueInBigDecimal(Number numberValue) {
        return switch (numberValue) {
            case Double _ -> BigDecimal.valueOf((double) numberValue);
            case Float _ -> BigDecimal.valueOf((float) numberValue);
            case Integer _ -> new BigDecimal((int) numberValue);
            case Short _ -> new BigDecimal((short) numberValue);
            case Long l -> BigDecimal.valueOf(l);
            case BigDecimal bigDecimal -> bigDecimal;
            case BigInteger bigInteger -> new BigDecimal(bigInteger);
            default -> new BigDecimal(numberValue.toString());
        };
    }

    /**
     * Create {@link ExtNumber} from an integer value.
     *
     * @param integerValue Integer value.
     * @return new {@link ExtNumber} for the given int value.
     */
    public static ExtNumber fromInteger(int integerValue) {
        return new ExtNumber(integerValue);
    }

    /**
     * Create {@link ExtNumber} from a BigInteger value.
     *
     * @param bigIntegerValue BigInteger value.
     * @return new {@link ExtNumber} for the given BigInteger value.
     */
    public static ExtNumber fromBigInteger(BigInteger bigIntegerValue) {
        return new ExtNumber(bigIntegerValue);
    }

    /**
     * Create {@link ExtNumber} from a BigDecimal value.
     *
     * @param bigDecimalValue BigInteger value.
     * @return new {@link ExtNumber} for the given BigDecimal value.
     */
    public static ExtNumber fromBigDecimal(BigDecimal bigDecimalValue) {
        Validate.notNull(bigDecimalValue, "BigDecimal cannot be null");
        return new ExtNumber(bigDecimalValue);
    }

    /**
     * Create {@link ExtNumber} from a long Value.
     *
     * @param longValue long value.
     * @return new {@link ExtNumber} for the given long value.
     */
    public static ExtNumber fromLong(long longValue) {
        return new ExtNumber(longValue);
    }

    /**
     * Create {@link ExtNumber} from a double Value.
     *
     * @param doubleValue long value.
     * @return new {@link ExtNumber} for the given double value.
     */
    public static ExtNumber fromDouble(double doubleValue) {
        return new ExtNumber(doubleValue);
    }

    /**
     * Create {@link ExtNumber} from a long Value.
     *
     * @param shortValue long value.
     * @return new {@link ExtNumber} for the given long value.
     */
    public static ExtNumber fromShort(short shortValue) {
        return new ExtNumber(shortValue);
    }

    /**
     * Create {@link ExtNumber} from a float Value.
     *
     * @param floatValue float value.
     * @return new {@link ExtNumber} for the given float value.
     */
    public static ExtNumber fromFloat(float floatValue) {
        return new ExtNumber(floatValue);
    }

    /**
     * Create {@link ExtNumber} from a long Value.
     *
     * @param stringValue String value.
     * @return new {@link ExtNumber} for the given stringValue value.
     */
    public static ExtNumber fromString(String stringValue) {
        return new ExtNumber(stringValue);
    }

    /**
     * Gets the integer value of the  {@link ExtNumber}.
     * If we do a intValue() for {@link ExtNumber} constructed
     * from float, double, long, BigDecimal, BigInteger number type then it
     * may result in loss of magnitude and a loss of precision.
     * The result may lose some of the least significant bits of the value.
     * Precision is not lost while getting a {@link ExtNumber} which was constructed as
     * lower precision number type like short, byte, integer.
     *
     * @return integer value of  {@link ExtNumber} .
     */
    @Override
    public int intValue() {
        return numberValue instanceof Integer ? numberValue.intValue() :
                stringValue != null ? new BigDecimal(stringValue).intValue()
                        : valueOf(numberValue).intValue();
    }

    /**
     * Gets the long value of the  {@link ExtNumber}.
     * If we do a longValue() for {@link ExtNumber} constructed from
     * float, double, BigDecimal, BigInteger number type then it
     * may result in loss of magnitude and a loss of precision.
     * Precision is not lost while getting a {@link ExtNumber} which was constructed from
     * lower precision type like short, byte, integer.
     *
     * @return long value of  {@link ExtNumber}.
     */
    @Override
    public long longValue() {
        return numberValue instanceof Long ? numberValue.longValue() :
                stringValue != null ? new BigDecimal(stringValue).longValue() : valueOf(numberValue).longValue();
    }

    /**
     * Gets the float value of the  {@link ExtNumber}.
     * If we do a floatValue() for {@link ExtNumber} constructed from
     * double, BigDecimal, BigInteger number type then it
     * may result in loss of magnitude and a loss of precision.
     * Precision is not lost while getting a {@link ExtNumber} which was constructed from
     * precision type like short, byte, integer, long.
     *
     * @return long value of  {@link ExtNumber}.
     */
    @Override
    public float floatValue() {
        return numberValue instanceof Float ? numberValue.floatValue() :
                numberValue != null ? valueOf(numberValue).floatValue() : new BigDecimal(stringValue).floatValue();
    }

    /**
     * Gets the double value of the  {@link ExtNumber}.
     * If we do a doubleValue() for {@link ExtNumber} constructed from BigDecimal, BigInteger number type then it
     * may result in loss of magnitude and a loss of precision.
     * Precision is not lost while getting a {@link ExtNumber} which was constructed from
     * precision type like short, byte, integer, long, float.
     *
     * @return long value of  {@link ExtNumber}.
     */
    @Override
    public double doubleValue() {
        return numberValue instanceof Double ? numberValue.doubleValue() :
                numberValue != null ? valueOf(numberValue).doubleValue() :
                        new BigDecimal(stringValue).doubleValue();
    }

    /**
     * Gets the bigDecimalValue of the {@link ExtNumber}.
     * Precision is not lost in this case.
     * However, bigDecimalValue cannot be performed on
     * a {{@link ExtNumber}} constructed from Float/Double Nan/Infinity.
     *
     * @return BigDecimal value of  {@link ExtNumber}
     * @throws NumberFormatException Exception in thrown if a {@link ExtNumber} was constructed asNan/Infinite number
     *                               of Double/Float type.Since we cannot convert NaN/Infinite numbers to BigDecimal.
     */
    public BigDecimal bigDecimalValue() {

        if (stringValue != null) {
            return new BigDecimal(stringValue);
        }
        if (numberValue instanceof BigDecimal) {
            return (BigDecimal) numberValue;
        }
        if (isNumberValueNaN(numberValue) || isNumberValueInfinite(numberValue)) {
            throw new NumberFormatException("Nan or Infinite Number can not be converted to BigDecimal.");
        } else {
            return valueInBigDecimal(numberValue);
        }
    }

    /**
     * Gets the String value of the  {@link ExtNumber}.
     *
     * @return the stringValue
     */
    public String stringValue() {
        return stringValue != null ? stringValue : numberValue.toString();
    }

    @Override
    public String toString() {
        return stringValue != null ? stringValue : numberValue.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExtNumber extNumber)) {
            return false;
        }
        return Objects.equals(stringValue(), extNumber.stringValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(stringValue());
    }
}
