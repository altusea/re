package org.example.visit.document.internal;

import com.google.errorprone.annotations.Immutable;
import org.example.collection.ExtNumber;
import org.example.util.Validate;
import org.example.visit.document.Document;
import org.example.visit.document.DocumentVisitor;
import org.example.visit.document.VoidDocumentVisitor;

import java.io.Serial;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Immutable
public class NumberDocument implements Document {

    @Serial
    private static final long serialVersionUID = 1L;

    private final ExtNumber number;

    /**
     * Created a {{@link NumberDocument}} with the specified {{@link ExtNumber}}.
     * {{@link ExtNumber}} is provided as an input to NumberDocument to maintain arbitrary precision of any given number.
     *
     * @param number number
     */
    public NumberDocument(ExtNumber number) {
        Validate.notNull(number, "Number cannot be null.");
        this.number = number;
    }

    /**
     * Unwraps the Document Number to string value of the {{@link ExtNumber}}.
     *
     * @return {{@link ExtNumber}} value.
     */
    @Override
    public Object unwrap() {
        return number.stringValue();
    }

    @Override
    public boolean asBoolean() {
        throw new UnsupportedOperationException("A Document Number cannot be converted to a Boolean.");
    }

    @Override
    public String asString() {
        throw new UnsupportedOperationException("A Document Number cannot be converted to a String.");
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    /**
     * Returned as {{@link ExtNumber}}.
     * The number value can be extracted from the Document by using below methods
     *
     * @return {{@link ExtNumber}} value.
     * @see ExtNumber#intValue()
     * @see ExtNumber#longValue() ()
     * @see ExtNumber#floatValue() ()
     * @see ExtNumber#shortValue() ()
     * @see ExtNumber#bigDecimalValue() ()
     * @see ExtNumber#stringValue()
     * @see ExtNumber#floatValue() ()
     * @see ExtNumber#doubleValue() () ()
     */
    @Override
    public ExtNumber asNumber() {
        return number;
    }

    @Override
    public Map<String, Document> asMap() {
        throw new UnsupportedOperationException("A Document Number cannot be converted to a Map.");
    }

    @Override
    public List<Document> asList() {
        throw new UnsupportedOperationException("A Document Number cannot be converted to a List.");
    }

    /**
     * Accepts a visitor with the Document.
     *
     * @param <R>     visitor return type.
     * @param visitor Visitor to dispatch to.
     * @return Returns the accepted result by calling visitNumber of visitor.
     */
    @Override
    public <R> R accept(DocumentVisitor<? extends R> visitor) {
        return visitor.visitNumber(this.asNumber());
    }

    /**
     * Accepts a visitor with the Document. Calls visitNumber of visitor.
     *
     * @param visitor Visitor to dispatch to.
     */
    @Override
    public void accept(VoidDocumentVisitor visitor) {
        visitor.visitNumber(this.asNumber());
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NumberDocument that)) {
            return false;
        }
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
