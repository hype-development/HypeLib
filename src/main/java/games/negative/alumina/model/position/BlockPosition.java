package games.negative.alumina.model.position;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Represents a simple block position.
 * @param x X coordinate
 * @param y Y coordinate
 * @param z Z coordinate
 */
public record BlockPosition(int x, int y, int z) implements ConfigurationSerializable {

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        return Map.of(
                "x", x,
                "y", y,
                "z", z
        );
    }

    @Override
    public String toString() {
        return "BlockPosition{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
