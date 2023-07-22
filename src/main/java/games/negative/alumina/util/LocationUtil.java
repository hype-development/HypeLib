package games.negative.alumina.util;

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

        if (locationWorld == null || minWorld == null || maxWorld == null)
            return false;

        if (!locationWorld.equals(minWorld) || !locationWorld.equals(maxWorld))
            return false;

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
