package org.example.visit.document.internal;

import com.google.errorprone.annotations.Immutable;
import org.example.collection.ExtNumber;
import org.example.util.Validate;
import org.example.visit.document.Document;
import org.example.visit.document.DocumentVisitor;
import org.example.visit.document.VoidDocumentVisitor;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;

@Immutable
public final class ListDocument implements Document {

    @Serial
    private static final long serialVersionUID = 42L;

    private final List<Document> value;

    /**
     * Create a New {@link ListDocument} with List of Document documentList as passed in constructor
     *
     * @param documentList ListDocument documentList.
     */
    public ListDocument(List<Document> documentList) {
        Validate.notNull(documentList, "List documentList cannot be null");
        this.value = Collections.unmodifiableList(documentList);
    }

    /**
     * Provides Builder methods of {@link ListBuilderInternal} to directly create Document with List of Documents
     *
     * @return Builder methods to Construct Document with List of Documents.
     */
    public static ListBuilder listBuilder() {
        return new ListBuilderInternal();
    }

    /**
     * Gets the value of the document as a Java type that represents the
     * Loops through the individual Document and unwraps each of the document.
     *
     * @return Returns the {@code List<Object>}.
     */
    @Override
    public Object unwrap() {
        return value.stream().map(Document::unwrap).collect(toList());
    }

    @Override
    public boolean asBoolean() {
        throw new UnsupportedOperationException("A Document List cannot be converted to a Boolean.");
    }

    @Override
    public String asString() {
        throw new UnsupportedOperationException("A Document List cannot be converted to a String.");
    }

    @Override
    public ExtNumber asNumber() {
        throw new UnsupportedOperationException("A Document List cannot be converted to a Number.");
    }

    @Override
    public Map<String, Document> asMap() {
        throw new UnsupportedOperationException("A Document List cannot be converted to a Map.");
    }

    /**
     * @return true, since this is a Document List.
     */
    @Override
    public boolean isList() {
        return true;
    }

    /**
     * @return unmodifiableList of the List of Documents in the {{@link ListDocument}}.
     */
    @Override
    public List<Document> asList() {
        return value;
    }

    /**
     * Accepts a visitor with the Document.
     *
     * @param <R>     visitor return type.
     * @param visitor Visitor to dispatch to.
     * @return Returns the accepted result by calling visitList of visitor.
     */
    @Override
    public <R> R accept(DocumentVisitor<? extends R> visitor) {
        return visitor.visitList(this.asList());
    }

    /**
     * Accepts a visitor with the Document. Calls visitList of visitor.
     *
     * @param visitor Visitor to dispatch to.
     */
    @Override
    public void accept(VoidDocumentVisitor visitor) {
        visitor.visitList(this.asList());
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ListDocument that)) {
            return false;
        }
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /**
     * Internal List Builder for easy construction of Document Lists.
     */
    public static class ListBuilderInternal implements ListBuilder {

        private final List<Document> documentList = new ArrayList<>();

        @Override
        public ListBuilder addString(String stringValue) {
            documentList.add(Document.fromString(stringValue));
            return this;
        }

        @Override
        public ListBuilder addBoolean(boolean booleanValue) {
            documentList.add(Document.fromBoolean(booleanValue));
            return this;
        }

        @Override
        public ListBuilder addNumber(ExtNumber numberValue) {
            documentList.add(Document.fromNumber(numberValue));
            return this;
        }

        @Override
        public ListBuilder addNumber(int numberValue) {
            documentList.add(Document.fromNumber(numberValue));
            return this;
        }

        @Override
        public ListBuilder addNumber(long numberValue) {
            documentList.add(Document.fromNumber(numberValue));
            return this;
        }

        @Override
        public ListBuilder addNumber(float numberValue) {
            documentList.add(Document.fromNumber(numberValue));
            return this;
        }

        @Override
        public ListBuilder addNumber(double numberValue) {
            documentList.add(Document.fromNumber(numberValue));
            return this;
        }

        @Override
        public ListBuilder addNumber(BigDecimal numberValue) {
            documentList.add(Document.fromNumber(numberValue));
            return this;
        }

        @Override
        public ListBuilder addNumber(BigInteger numberValue) {
            documentList.add(Document.fromNumber(numberValue));
            return this;
        }

        @Override
        public ListBuilder addNumber(String numberValue) {
            documentList.add(Document.fromNumber(numberValue));
            return this;
        }

        @Override
        public ListBuilder addDocument(Document document) {
            documentList.add(document);
            return this;
        }

        @Override
        public ListBuilder addMap(Consumer<MapBuilder> mapBuilderConsumer) {
            MapBuilder mapBuilder = MapDocument.mapBuilder();
            mapBuilderConsumer.accept(mapBuilder);
            documentList.add(mapBuilder.build());
            return this;
        }

        @Override
        public ListBuilder addNull() {
            documentList.add(Document.fromNull());
            return this;
        }

        @Override
        public Document build() {
            return new ListDocument(documentList);
        }
    }
}
