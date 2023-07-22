package games.negative.alumina.util;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

/**
 * A location utility to handle some location related tasks.
 */
public class LocationUtil {

    /**
     * Check if a location is inside a cuboid.
     * @param location The location to check.
     * @param min The minimum location of the cuboid.
     * @param max The maximum location of the cuboid.
     * @return Whether the location is inside the cuboid.
     */
    public static boolean isInside(@NotNull Location location, @NotNull Location min, @NotNull Location max) {
        World locationWorld = location.getWorld();
        World minWorld = min.getWorld();
        World maxWorld = max.getWorld();

        Preconditions.checkNotNull(locationWorld, "Location world cannot be null!");
        Preconditions.checkNotNull(minWorld, "Min world cannot be null!");
        Preconditions.checkNotNull(maxWorld, "Max world cannot be null!");

        Preconditions.checkArgument(minWorld.equals(maxWorld), "Min world must be the same as max world!");
        Preconditions.checkArgument(locationWorld.equals(minWorld), "Location world must be the same as min world!");
        Preconditions.checkArgument(locationWorld.equals(maxWorld), "Location world must be the same as max world!");

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        int x1 = Math.min(min.getBlockX(), max.getBlockX());
        int y1 = Math.min(min.getBlockY(), max.getBlockY());
        int z1 = Math.min(min.getBlockZ(), max.getBlockZ());

        int x2 = Math.max(min.getBlockX(), max.getBlockX());
        int y2 = Math.max(min.getBlockY(), max.getBlockY());
        int z2 = Math.max(min.getBlockZ(), max.getBlockZ());

        return (x >= x1 && x <= x2) && (y >= y1 && y <= y2) && (z >= z1 && z <= z2);
    }
}
