package org.example.visit.document;

import org.example.collection.ExtNumber;

import java.util.List;
import java.util.Map;

/**
 * Document visitor interface.
 *
 * @param <R> Return type of the visitor.
 */
public interface DocumentVisitor<R> {

    /**
     * Visits a Document Null.
     *
     * @return value of the visitor
     */
    R visitNull();

    /**
     * Visits a Boolean Document.
     *
     * @param document Document to visit,
     * @return Return value of the visitor.
     */
    R visitBoolean(Boolean document);

    /**
     * Visits a String Document.
     *
     * @param document Document to visit,
     * @return Return value of the visitor.
     */
    R visitString(String document);

    /**
     * Visits a Number Document.
     *
     * @param document Document to visit,
     * @return Return value of the visitor.
     */
    R visitNumber(ExtNumber document);

    /**
     * Visits a Map Document.
     *
     * @param documentMap Document to visit,
     * @return Return value of the visitor.
     */
    R visitMap(Map<String, Document> documentMap);

    /**
     * Visits a List Document.
     *
     * @param documentList Document to visit,
     * @return Return value of the visitor.
     */
    R visitList(List<Document> documentList);
}
