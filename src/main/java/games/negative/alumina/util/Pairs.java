package games.negative.alumina.util;

import com.google.common.base.Preconditions;
import games.negative.alumina.model.pair.LocationPair;
import games.negative.alumina.model.pair.Pair;
import games.negative.alumina.model.pair.ImmutablePair;
import games.negative.alumina.model.pair.SimplePair;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

/**
 * The Pairs class provides utility methods for creating various types of pairs.
 */
public class Pairs {

    /**
     * Creates a {@link ImmutablePair} object with the given key and value.
     *
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param key The key for the pair.
     * @param value The value for the pair.
     * @return An immutable Pair object with the given key and value.
     * @throws NullPointerException if either the key or value is null.
     */
    @NotNull
    public static <K, V> Pair<K, V> immutablePair(@NotNull K key, @NotNull V value) {
        Preconditions.checkNotNull(key, "Key cannot be null.");
        Preconditions.checkNotNull(value, "Value cannot be null.");

        return new ImmutablePair<>(key, value);
    }

    /**
     * Creates a {@link SimplePair} object with the given key and value.
     *
     * @param <K> The type of the key.
     * @param <V> The type of the value.
     * @param key The key for the pair.
     * @param value The value for the pair.
     * @return A Pair object with the given key and value.
     * @throws NullPointerException if either the key or value is null.
     */
    @NotNull
    public static <K, V> Pair<K, V> simplePair(@NotNull K key, @NotNull V value) {
        Preconditions.checkNotNull(key, "Key cannot be null.");
        Preconditions.checkNotNull(value, "Value cannot be null.");

        return new SimplePair<>(key, value);
    }

    /**
     * Creates a {@link LocationPair} object with the given key and value.
     *
     * @param one The first Location object.
     * @param two The second Location object.
     * @return A Pair object containing the two Location objects.
     * @throws NullPointerException if either one or two is null.
     */
    @NotNull
    public static Pair<Location, Location> locationPair(@NotNull Location one, @NotNull Location two) {
        Preconditions.checkNotNull(one, "Location-One cannot be null.");
        Preconditions.checkNotNull(two, "Location-Two cannot be null.");

        return new LocationPair(one, two);
    }
}
