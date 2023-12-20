package games.negative.alumina.model.pair;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class LocationPair implements Pair<Location, Location> {

    private Location one;
    private Location two;

    public LocationPair(@NotNull Location one, @NotNull Location two) {
        this.one = one;
        this.two = two;
    }

    public LocationPair(@NotNull Location one) {
        this.one = one;
    }

    @Override
    public Location key() {
        return one;
    }

    public void setOne(@NotNull Location one) {
        this.one = one;
    }

    @Override
    public Location value() {
        return two;
    }

    public void setTwo(@NotNull Location two) {
        this.two = two;
    }
}
