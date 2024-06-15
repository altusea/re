package org.example.util.builder;

/**
 * A special type of {@link Builder} that can be used when the built type implements {@link ToCopyableBuilder}.
 */
public interface CopyableBuilder<B extends CopyableBuilder<B, T>, T extends ToCopyableBuilder<B, T>> extends Builder<B, T> {

    /**
     * A shallow copy of this object created by building an immutable T and then transforming it back to a builder.
     *
     * @return a copy of this object
     */
    default B copy() {
        return build().toBuilder();
    }

}
