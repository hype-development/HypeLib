package games.negative.alumina.model;

/**
 * The Keyd interface represents an object that has a key associated with it.
 *
 * @param <T> the type of the key
 */
public interface Keyd<T> {

    /**
     * Returns the key associated with the object.
     *
     * @return the key
     */
    T key();

}
