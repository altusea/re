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
public final class StringDocument implements Document {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String value;

    /**
     * Create a New {@link StringDocument} with boolean value as passed in constructor
     *
     * @param string boolean value.
     */
    public StringDocument(String string) {
        Validate.notNull(string, "String cannot be null");
        this.value = string;
    }

    /**
     * Unwraps the Document Boolean to a String Object.
     *
     * @return string value.
     */
    @Override
    public Object unwrap() {
        return value;
    }

    @Override
    public boolean asBoolean() {
        throw new UnsupportedOperationException("A Document String cannot be converted to a Boolean.");
    }

    /**
     * @return true, since this is a Document String.
     */
    @Override
    public boolean isString() {
        return true;
    }

    /**
     * Gets the String value of the Document.
     *
     * @return string value.
     */
    @Override
    public String asString() {
        return value;
    }

    /**
     * @return true, since this is a Document String.
     */
    @Override
    public ExtNumber asNumber() {
        throw new UnsupportedOperationException("A Document String cannot be converted to a Number.");
    }

    /**
     * @return true, since this is a Document String.
     */
    @Override
    public Map<String, Document> asMap() {
        throw new UnsupportedOperationException("A Document String cannot be converted to a Map.");
    }

    /**
     * @return true, since this is a Document String.
     */
    @Override
    public List<Document> asList() {
        throw new UnsupportedOperationException("A Document String cannot be converted to a List.");
    }

    /**
     * Accepts a visitor with the Document.
     *
     * @param <R>     visitor return type.
     * @param visitor Visitor to dispatch to.
     * @return Returns the accepted result by calling visitString of visitor.
     */
    @Override
    public <R> R accept(DocumentVisitor<? extends R> visitor) {
        return visitor.visitString(asString());
    }

    /**
     * Accepts a visitor with the Document. Calls visitString of visitor.
     *
     * @param visitor Visitor to dispatch to.
     */
    @Override
    public void accept(VoidDocumentVisitor visitor) {
        visitor.visitString(asString());
    }


    @Override
    public String toString() {
        // Does not handle unicode control characters
        return "\"" +
                value.replace("\\", "\\\\")
                        .replace("\"", "\\\"")
                + "\"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StringDocument that)) {
            return false;
        }
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
