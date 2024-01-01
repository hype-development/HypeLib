package games.negative.alumina.registry;

import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * A simple implementation of a registry.
 * @param <K> The key type.
 * @param <V> The value type.
 */
public class SimpleRegistry<K, V> implements Registry<K, V> {

    private final Map<K, V> registry;

    public SimpleRegistry(@NotNull Map<K, V> registry) {
        this.registry = registry;
    }

    public SimpleRegistry() {
        this.registry = Maps.newHashMap();
    }

    @Override
    public int size() {
        return registry.size();
    }

    @Override
    public boolean isEmpty() {
        return registry.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return registry.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return registry.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return registry.get(key);
    }

    @Nullable
    @Override
    public V put(K key, V value) {
        return registry.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return registry.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends K, ? extends V> m) {
        registry.putAll(m);
    }

    @Override
    public void clear() {
        registry.clear();
    }

    @NotNull
    @Override
    public Set<K> keySet() {
        return registry.keySet();
    }

    @NotNull
    @Override
    public Collection<V> values() {
        return registry.values();
    }

    @NotNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        return registry.entrySet();
    }
}
