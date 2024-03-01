package org.example.visit.document;

import org.example.collection.ExtNumber;

import java.util.List;
import java.util.Map;

/**
 * Document visitor interface with no return type.
 */
public interface VoidDocumentVisitor {

    /**
     * Visits a Document Null.
     */
    default void visitNull() {
    }

    /**
     * Visits a Boolean Document.
     *
     * @param document Document to visit,
     */
    default void visitBoolean(Boolean document) {
    }

    /**
     * Visits a String Document.
     *
     * @param document Document to visit,
     */
    default void visitString(String document) {
    }

    /**
     * Visits a Number Document.
     *
     * @param document Document to visit,
     */
    default void visitNumber(ExtNumber document) {
    }

    /**
     * Visits a Map Document.
     *
     * @param documentMap Document to visit,
     */
    default void visitMap(Map<String, Document> documentMap) {
    }

    /**
     * Visits a List Document.
     *
     * @param documentList Document to visit,
     */
    default void visitList(List<Document> documentList) {
    }

}
