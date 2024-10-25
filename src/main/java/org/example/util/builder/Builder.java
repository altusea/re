package org.example.util.builder;

import java.util.function.Consumer;

/**
 * A mutable object that can be used to create an immutable object of type T.
 *
 * @param <B> the builder type (this)
 * @param <T> the type that the builder will build
 */
public interface Builder<B extends Builder<B, T>, T> extends Buildable {

    /**
     * An immutable object that is created from the
     * properties that have been set on the builder.
     *
     * @return an instance of T
     */
    @Override
    T build();

    /**
     * A convenience operator that takes something that will
     * mutate the builder in some way and allows inclusion of it
     * in chaining operations. For example instead of:
     *
     * <pre><code>
     * Builder builder = ClassBeingBuilt.builder();
     * builder = Util.addSomeDetailToTheBuilder(builder);
     * ClassBeingBuilt clz = builder.build();
     * </code></pre>
     * <p>
     * This can be done in a statement:
     *
     * <pre><code>
     * ClassBeingBuilt = ClassBeingBuilt.builder().applyMutation(Util::addSomeDetailToTheBuilder).build();
     * </code></pre>
     *
     * @param mutator the function that mutates the builder
     * @return B the mutated builder instance
     */
    @SuppressWarnings("unchecked")
    default B applyMutation(Consumer<B> mutator) {
        mutator.accept((B) this);
        return (B) this;
    }
}
