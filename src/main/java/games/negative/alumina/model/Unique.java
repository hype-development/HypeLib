package games.negative.alumina.model;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * The Unique interface represents an object that has a universally unique identifier (UUID).
 *
 * <p>
 * Implementing classes must provide an implementation for the 'uuid' method, which returns
 * the unique identifier for the object.
 * </p>
 *
 * @see UUID
 */
public interface Unique {

    /**
     * Generates a universally unique identifier (UUID) for this object.
     *
     * @return A UUID representing the unique identifier for this object.
     *
     * @throws NullPointerException if the UUID is null.
     *
     * @see UUID
     */
    @NotNull
    UUID uuid();

}
