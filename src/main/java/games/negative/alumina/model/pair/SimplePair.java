package games.negative.alumina.model.pair;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

/**
 * The SimplePair class is an implementation of the Pair interface.
 * It represents a key-value pair and provides methods to get and set the key and value.
 *
 * @param <K> The type of the key.
 * @param <V> The type of the value.
 */
public class SimplePair<K, V> implements Pair<K, V> {

    private K key;
    private V value;

    /**
     * Constructs a new instance of SimplePair with the given key and value.
     *
     * @param key   The key of the pair. Cannot be null.
     * @param value The value of the pair. Cannot be null.
     */
    public SimplePair(@NotNull K key, @NotNull V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key associated with this Pair.
     *
     * @return the key associated with this Pair.
     */
    @Override
    public K key() {
        return key;
    }

    /**
     * Set the key of the Pair.
     *
     * @param key The key to set.
     * @throws NullPointerException if the given key is null.
     */
    public void setKey(@NotNull K key) {
        Preconditions.checkNotNull(key, "Key cannot be null.");

        this.key = key;
    }

    /**
     * Returns the value associated with the pair.
     *
     * @return The value associated with the pair.
     */
    @Override
    public V value() {
        return value;
    }

    /**
     * Sets the value associated with the pair.
     *
     * @param value The new value to be associated with the pair.
     * @throws NullPointerException if value is null.
     */
    public void setValue(@NotNull V value) {
        Preconditions.checkNotNull(key, "Key cannot be null.");

        this.value = value;
    }

}
