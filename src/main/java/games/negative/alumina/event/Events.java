package games.negative.alumina.event;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

/**
 * Events utility class for more simply calling events
 */
public class Events {

    /**
     * Call an event
     *
     * @param event Event to call
     */
    public static void call(Event event) {
        Bukkit.getPluginManager().callEvent(event);
    }

}
