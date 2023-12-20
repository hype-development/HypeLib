package games.negative.alumina.model.pair;

import org.jetbrains.annotations.NotNull;

public record ImmutablePair<K, V>(K key, V value) implements Pair<K, V> {

    public ImmutablePair(@NotNull K key, @NotNull V value) {
        this.key = key;
        this.value = value;
    }

}
