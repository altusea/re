package org.example.visit.document.internal;

import com.google.errorprone.annotations.Immutable;
import org.example.collection.ExtNumber;
import org.example.visit.document.Document;
import org.example.visit.document.DocumentVisitor;
import org.example.visit.document.VoidDocumentVisitor;

import java.io.Serial;
import java.util.List;
import java.util.Map;

@Immutable
public final class NullDocument implements Document {

    @Serial
    private static final long serialVersionUID = 42L;

    /**
     * Unwraps NullDocument as null.
     *
     * @return null
     */
    @Override
    public Object unwrap() {
        return null;
    }

    @Override
    public boolean asBoolean() {
        throw new UnsupportedOperationException("A Document Null cannot be converted to a Boolean.");
    }

    @Override
    public String asString() {
        throw new UnsupportedOperationException("A Document Null cannot be converted to a String.");
    }

    @Override
    public ExtNumber asNumber() {
        throw new UnsupportedOperationException("A Document Null cannot be converted to a Number.");
    }

    @Override
    public Map<String, Document> asMap() {
        throw new UnsupportedOperationException("A Document Null cannot be converted to a Map.");
    }

    /**
     * @return true ,since this is a Document Null.
     */
    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public List<Document> asList() {
        throw new UnsupportedOperationException("A Document Null cannot be converted to a List.");
    }

    /**
     * Accepts a visitor with the Document.
     *
     * @param <R>     visitor return type.
     * @param visitor Visitor to dispatch to.
     * @return Returns the accepted result by calling visitNull of visitor.
     */
    @Override
    public <R> R accept(DocumentVisitor<? extends R> visitor) {
        return visitor.visitNull();
    }

    /**
     * Accepts a visitor with the Document. Calls visitNull of visitor.
     *
     * @param visitor Visitor to dispatch to.
     */
    @Override
    public void accept(VoidDocumentVisitor visitor) {
        visitor.visitNull();
    }

    @Override
    public String toString() {
        return "null";
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NullDocument that)) {
            return false;
        }
        return that.isNull() == this.isNull();
    }
}
