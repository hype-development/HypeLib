package games.negative.alumina.model.registry;

import java.util.Map;

/**
 * A registry is a map that can be used to store values.
 * @param <K> The key type.
 * @param <V> The value type.
 * @deprecated Use {@link games.negative.alumina.registry.Registry} instead.
 */
@Deprecated
public interface Registry<K, V> extends Map<K, V> {

}
