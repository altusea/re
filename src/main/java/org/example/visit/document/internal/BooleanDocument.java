package org.example.visit.document.internal;

import com.google.errorprone.annotations.Immutable;
import org.example.collection.ExtNumber;
import org.example.visit.document.Document;
import org.example.visit.document.DocumentVisitor;
import org.example.visit.document.VoidDocumentVisitor;

import java.io.Serial;
import java.util.List;
import java.util.Map;

/**
 * Represents a Boolean Document.
 */
@Immutable
public final class BooleanDocument implements Document {

    @Serial
    private static final long serialVersionUID = 42L;

    private final boolean value;

    /**
     * Create a New {@link BooleanDocument} with boolean value as passed in constructor
     *
     * @param value boolean value.
     */
    public BooleanDocument(boolean value) {
        this.value = value;
    }

    /**
     * Unwraps the Document Boolean to a Boolean Object.
     *
     * @return boolean value.
     */
    @Override
    public Object unwrap() {
        return value;
    }

    /**
     * Indicates this is a Boolean Document.
     *
     * @return true since this is a Boolean Document.
     */
    @Override
    public boolean isBoolean() {
        return true;
    }

    /**
     * Gets the boolean value of the Document.
     *
     * @return boolean value.
     */
    @Override
    public boolean asBoolean() {
        return value;
    }

    @Override
    public String asString() {
        throw new UnsupportedOperationException("A Document Boolean cannot be converted to a String.");
    }

    @Override
    public ExtNumber asNumber() {
        throw new UnsupportedOperationException("A Document Boolean cannot be converted to a Number.");
    }

    @Override
    public Map<String, Document> asMap() {
        throw new UnsupportedOperationException("A Document Boolean cannot be converted to a Map.");
    }

    @Override
    public List<Document> asList() {
        throw new UnsupportedOperationException("A Document Boolean cannot be converted to a List.");
    }


    /**
     * Accepts a visitor with the Document.
     *
     * @param <R>     visitor return type.
     * @param visitor Visitor to dispatch to.
     * @return Returns the accepted result by calling visitBoolean of visitor.
     */
    @Override
    public <R> R accept(DocumentVisitor<? extends R> visitor) {
        return visitor.visitBoolean(asBoolean());
    }

    /**
     * Accepts a visitor with the Document. Calls visitBoolean of visitor.
     *
     * @param visitor Visitor to dispatch to.
     */
    @Override
    public void accept(VoidDocumentVisitor visitor) {
        visitor.visitBoolean(asBoolean());
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BooleanDocument that)) {
            return false;
        }
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(value);
    }
}
