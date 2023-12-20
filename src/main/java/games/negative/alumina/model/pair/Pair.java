package games.negative.alumina.model.pair;

/**
 * The Pair interface represents a key-value pair.
 * It allows the user to get the key and value associated with the pair.
 *
 * @param <K> The type of the key.
 * @param <V> The type of the value.
 */
public interface Pair<K, V> {

    /**
     * Returns the key associated with this Pair.
     *
     * @return the key associated with this Pair.
     */
    K key();

    /**
     * Returns the value associated with the pair.
     *
     * @return The value associated with the pair.
     */
    V value();

}
